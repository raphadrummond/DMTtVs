package databit.com.br.datamobile.wsbase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by user on 11/04/2016.
 */
public abstract class WsClient {
    //protected Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();

    protected Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();

    protected abstract String getUrl();

    protected abstract String getName();

    protected void init(String user, String password) {
        HttpClient.getInstance(getName()).init(user, password, true);
    }

    protected String wsGet(String wsPath, OutputStream output) {
        return HttpClient.getInstance(getName()).callGet(getUrl() + wsPath, output);
    }

    protected String wsPost(String wsPath, String params, OutputStream output) {
        return HttpClient.getInstance(getName()).callPost(getUrl() + wsPath, params, output);
    }

    protected String wsPut(String wsPath, InputStream input, long length) {
        return HttpClient.getInstance(getName()).callPut(getUrl() + wsPath, input, length);
    }

    protected String wsGet(String wsPath) {
        return wsGet(wsPath, null);
    }

    protected String wsPost(String wsPath, String params) {
        return wsPost(wsPath, params, null);
    }


}
