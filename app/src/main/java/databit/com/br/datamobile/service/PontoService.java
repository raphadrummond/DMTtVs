package databit.com.br.datamobile.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.activity.MainActivity;
import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.dao.PontoDAO;
import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.domain.Ponto;
import databit.com.br.datamobile.wsclient.PontoWSClient;

public class PontoService extends Service {

    public PontoService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        PontoAsyncTask task = new PontoAsyncTask();
        task.execute();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class PontoAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            while (true) {
                try {
                    String sRetorno = "";
                    PontoWSClient pontoWSClient = new PontoWSClient();
                    PontoDAO pontoDAO = new PontoDAO();
                    List<Ponto> listPonto = pontoDAO.listarPonto(" integra = 'N' and integra is not null ");
                    for (Ponto ponto : listPonto) {
                        sRetorno = pontoWSClient.enviaPonto(ponto);
                        if (sRetorno.equals("OK")) {
                            ponto.setIntegra("S");
                            pontoDAO.gravaPonto(ponto);
                        }
                        else {
                            ponto.setIntegra("N");
                            pontoDAO.gravaPonto(ponto);
                        }
                    }
                    ConfiguracaoDAO configuracaoDAO = new ConfiguracaoDAO();
                    Configuracao configuracao = configuracaoDAO.procuraConfiguracao("1 = 1");
                    Date datahora = new Date();
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    String sData = formato.format(datahora);
                    List<Ponto> lista = new ArrayList<>();
                    lista = pontoDAO.listarPonto("sdata = '"+sData+"' ");
                    DateFormat formatodatahora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date dIntervalo = new Date();
                    try {
                        dIntervalo = formatodatahora.parse(sData + " " + configuracao.getHoraintervalo().toString() + ":00");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Integer iTipo = lista.size();
                    if (iTipo.equals(1)) {
                        long iIntervalo = (dIntervalo.getTime() - datahora.getTime());
                        if ((iIntervalo <= 3600000L) && (dIntervalo.after(datahora))) {
                            sendNotification("Controle de Ponto", "Atenção, está chegando a hora para você iniciar o seu intervalo !");
                        }
                    }
                    Thread.sleep(450000);
                } catch (Throwable t) {
                    try {
                        Thread.sleep(450000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    private void sendNotification(String title, String message) {
        try {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            intent.putExtra("title", title);
            intent.putExtra("message", message);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_databit_novo);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_access_alarm2)
                    .setLargeIcon(largeIcon)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            Notification note = notificationBuilder.build();
            note.defaults |= Notification.DEFAULT_VIBRATE;
            note.defaults |= Notification.DEFAULT_SOUND;
            note.flags |= Notification.FLAG_SHOW_LIGHTS;
            note.ledARGB = 0xff00ff00;
            note.ledOnMS = 400;
            note.ledOffMS = 10000;
            notificationManager.notify(0, note);
        } catch (Throwable t) {
            t.printStackTrace();
        }


    }
}