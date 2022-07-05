package databit.com.br.datamobile.wsbase;

import android.util.Base64;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import databit.com.br.datamobile.BuildConfig;

/**
 * Created by user on 11/04/2016.
 */
public class HttpClient {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z", Locale.US);
    public static final int AUTH_BASIC = 0;
    public static final int AUTH_AWS = 1;

    public String user;
    private String password;
    private boolean compress;
    private int authType = AUTH_BASIC;
    private String sessionCookie;
    private String pin;
    private String versao = BuildConfig.VERSION_NAME + " (" + BuildConfig.VERSION_CODE + ")";
    private static HashMap<String, HttpClient> instances = new HashMap<String, HttpClient>();

    private enum Method {
        GET, POST, PUT;
    }

    public static HttpClient getInstance(String name) {
        if (!instances.containsKey(name)) {
            instances.put(name, new HttpClient());
        }
        return instances.get(name);
    }

    protected HttpClient() {
    }

    public void init(String user, String password) {
        init(user, password, false);
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setCodigoEmpregador(String codigoEmpregador) {
        this.password = codigoEmpregador;
    }

    public void init(String user, String password, boolean compress) {
        this.user = user;
        this.password = password;
        this.compress = compress;
        this.sessionCookie = null;
        System.out.println("Iniciando HttpClient");
    }

    public void setAuthType(int authType) {
        this.authType = authType;
    }

    public String callGet(String urlString) {
        return callGet(urlString, null);
    }

    public String callGet(String urlString, OutputStream output) {
        try {
            HttpURLConnection connection = connect(urlString);
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(1000000);
            connection.setReadTimeout(1000000);
            headers(connection, Method.GET);
            String result = httpResult(connection, output);
            setCookie(connection);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String callPost(String urlString, String post) {
        return callPost(urlString, post, null);
    }

    public String callPost(String urlString, InputStream in) {
        return callPost(urlString, in, null);
    }

    public String callPost(String urlString, String post, OutputStream output) {
        ByteArrayInputStream bis = new ByteArrayInputStream(post.getBytes());
        return callPost(urlString, bis, output);
    }

    public String callPost(String urlString, InputStream in, OutputStream output) {
        try {
            BufferedInputStream input = new BufferedInputStream(in);

            HttpURLConnection connection = connect(urlString);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setConnectTimeout(1000000);
            connection.setReadTimeout(1000000);
            headers(connection, Method.POST);
            printPostParameters(connection, input);
            String result = httpResult(connection, output);
            setCookie(connection);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String callPut(String urlString, InputStream in, long length) {
        try {
            BufferedInputStream input = new BufferedInputStream(in);

            HttpURLConnection connection = connect(urlString);
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            headers(connection, Method.PUT, input, length);
            printPostParameters(connection, input);
            String result = httpResult(connection, null);
            setCookie(connection);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void headers(HttpURLConnection connection, Method method) throws IOException {
        headers(connection, method, null, 0);
    }

    private void headers(HttpURLConnection connection, Method method, InputStream in, long length) throws IOException {
        Date now = new Date();
        connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        if (authType == AUTH_BASIC) {
            if (user != null) {
                String enc = Base64.encodeToString((user + ":" + password).getBytes(), 0);
                connection.setRequestProperty("authorization", "Basic " + enc.trim());
            } else {
                System.out.println("USUARIO E SENHA NULOS");
            }

            if (sessionCookie != null) {
                connection.setRequestProperty("Cookie", sessionCookie);
            }
            if (pin != null) {
                connection.setRequestProperty("pin", pin);
            }
            if (versao != null) {
                connection.setRequestProperty("versao", versao);
            }
        }

        if (compress) {
            connection.setRequestProperty("Accept-Encoding", "gzip");
            if (method.equals(Method.POST) || method.equals(Method.PUT)) {
                connection.setRequestProperty("Content-Encoding", "gzip");
            }
        }

        if (method.equals(Method.PUT)) {
            connection.setRequestProperty("Date", dateFormat.format(now));
            try {
                connection.setRequestProperty("Content-MD5", encryptMd5(in));
            } catch (Exception e) {
            }
            connection.setFixedLengthStreamingMode((int) length);
            connection.setRequestProperty("Content-Type", URLConnection.guessContentTypeFromName(connection.getURL().getPath()));
        }
        if (method.equals(Method.POST)) {
            connection.setRequestProperty("Content-Type", "application/json");
        }
        if (authType == AUTH_AWS) {
            if (user != null) {
                connection.setRequestProperty("User-Agent", "aws-sdk-java/1.5.4 Windows_7/6.1 Java_HotSpot(TM)_64-Bit_Server_VM/20.7-b02");
                StringBuilder code = new StringBuilder(method.toString());
                code = code.append("\n");
                code = code.append(connection.getRequestProperty("Content-MD5")).append("\n");
                code = code.append(connection.getRequestProperty("Content-Type")).append("\n");
                code = code.append(connection.getRequestProperty("Date")).append("\n");
// TODO verificar path
                code = code.append("/laudodistanciaexames" + connection.getURL().getPath());
                try {
                    connection.setRequestProperty("authorization", "AWS " + user + ":" + encryptSha(password, code.toString()));
                } catch (Exception e) {
                }
            }
        }
    }

    public String encryptMd5(InputStream in) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] buffer = new byte[65536];
        System.out.println("MARK SUPPORTED: " + in.markSupported());
        in.mark(Integer.MAX_VALUE);
        int len = in.read(buffer);
        while (len != -1) {
            digest.update(buffer, 0, len);
            len = in.read(buffer);
        }
        in.reset();
        byte result[] = digest.digest();
        return Base64.encodeToString(result, 0);
    }

    public String encryptSha(String key, String value) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(keySpec);
        byte[] result = mac.doFinal(value.getBytes("UTF-8"));

        return Base64.encodeToString(result, 0);
    }

    private void setCookie(HttpURLConnection connection) {
        String cookie = connection.getHeaderField("Set-Cookie");
        if (cookie != null && !cookie.trim().equals("")) {
            cookie = cookie.substring(0, cookie.indexOf(';'));
            sessionCookie = cookie;
            user = null;
            password = null;
        }
    }

    private void printPostParameters(HttpURLConnection connection, InputStream in) throws IOException {
        OutputStream out = connection.getOutputStream();
        if (compress) {
            out = new GZIPOutputStream(out);
        }
        write(in, out);
    }

    private HttpURLConnection connect(String urlString) throws MalformedURLException, IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        return connection;
    }

    private String httpResult(HttpURLConnection connection, OutputStream output) throws IOException {
        int resultCode = connection.getResponseCode();
        String encoding = connection.getHeaderField("Content-Encoding");
        boolean gzip = encoding != null && encoding.toLowerCase().contains("gzip");
        if (resultCode >= HttpURLConnection.HTTP_OK && resultCode <= HttpURLConnection.HTTP_PARTIAL) {
            return readResult(connection.getInputStream(), output, gzip);
        } else {
            String s = readResult(connection.getErrorStream(), null, gzip);
            throw new RuntimeException("HTTP error [" + resultCode + "]: " + s);
        }
    }

    private String readResult(InputStream input, OutputStream output, boolean gzip) throws IOException {
        OutputStream out = output;
        if (output == null) {
            out = new ByteArrayOutputStream();
        }
        InputStream in = input;
        if (gzip) {
            in = new GZIPInputStream(in);
        }

        write(in, out);

        if (output == null) {
            return out.toString();
        } else {
            return null;
        }
    }

    private void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[65536];
        int len = in.read(buffer);
        while (len != -1) {
            out.write(buffer, 0, len);
            len = in.read(buffer);
        }
        in.close();
        out.flush();
        out.close();
    }
}
