package databit.com.br.datamobile.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.activity.MainActivity;
import databit.com.br.datamobile.dao.AparelhoDAO;
import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.dao.UsuarioDAO;
import databit.com.br.datamobile.domain.Aparelho;
import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.infra.Internet;
import databit.com.br.datamobile.wsclient.OsWSClient;

import static databit.com.br.datamobile.badger.Badger.setBadge;

/**
 * Created by Sidney on 04/01/2018.
 */
public class SincronizacaoPendencia extends Service     {

    private Configuracao configuracao;
    private ConfiguracaoDAO configuracaoDAO;
    private Integer iTempo;
    private Aparelho aparelho;
    private AparelhoDAO aparelhoDAO = new AparelhoDAO();


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        aparelhoDAO = new AparelhoDAO();
        aparelho = aparelhoDAO.procuraAparelho("id = 1");
        PendenciaAsyncTask task = new PendenciaAsyncTask();
        task.execute();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    class PendenciaAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            configuracaoDAO = new ConfiguracaoDAO();
            configuracao = configuracaoDAO.procuraConfiguracao("id = 1");
            if (configuracao.getTemposmens() == null) {
                iTempo = 360000;
            } else {
                iTempo = configuracao.getTemposmens() * 60000;
            }
            while (true) {
                try {
                    if ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext()))) {
                        OsWSClient wsClient = new OsWSClient();
                        UsuarioDAO usuarioDAO = new UsuarioDAO();
                        wsClient.usuario = usuarioDAO.procuraUsuario("login = '" + aparelho.getLogin().toString() + "'");
                        OsDAO osDAO = new OsDAO();
                        List<Os> listOs = wsClient.buscaOS();
                        Integer iOs = 0;
                        for (Os os : listOs) {
                            Os osPesq = osDAO.procuraOs(" id = '"+os.getId().toString()+"' ");
                            if (osPesq == null) {
                                iOs = iOs + 1;
                            }
                        }
                        if (iOs > 0) {
                            sendNotification("Ordem de Serviços", "Você possui " + iOs + " nova(s) ordem(ns) de serviço em aberto, favor verificar !");
                            setBadge(SincronizacaoPendencia.this, iOs);
                        }
                        Thread.sleep(iTempo);
                    }
                    else {
                        Thread.sleep(60000);
                    }
                } catch (Throwable t) {
                    try {
                        Thread.sleep(iTempo);
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
            intent.putExtra("sincauto", true);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_databit_novo);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_tools)
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
