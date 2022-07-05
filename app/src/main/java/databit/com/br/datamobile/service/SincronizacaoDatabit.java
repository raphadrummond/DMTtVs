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

import java.util.Date;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.activity.MainActivity;
import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.infra.Internet;

public class SincronizacaoDatabit extends Service {
    public SincronizacaoDatabit() {
    }

    private Configuracao configuracao;
    private ConfiguracaoDAO configuracaoDAO;
    private Boolean bErro = false;
    private Integer iTempo;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SincronizacaoAsyncTask task = new SincronizacaoAsyncTask();
        task.execute();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class SincronizacaoAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            configuracaoDAO = new ConfiguracaoDAO();
            configuracao = configuracaoDAO.procuraConfiguracao("id = 1");

            if (configuracao.getTemposinc() == null) {
                iTempo = 60;
            }
            else {
                iTempo = configuracao.getTemposinc();
            }
            while (true) {
                try {
                    if ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext()))) {
                        Intent intentUsuario = new Intent(SincronizacaoDatabit.this, UsuarioIntentService.class);
                        startService(intentUsuario);

                        Thread.sleep(1000);
                        Intent intentCondicao = new Intent(SincronizacaoDatabit.this, CondicaoIntentService.class);
                        startService(intentCondicao);

                        Thread.sleep(1000);
                        Intent intentDefeito = new Intent(SincronizacaoDatabit.this, DefeitoIntentService.class);
                        startService(intentDefeito);

                        Thread.sleep(1000);

                        Intent intentServicoIncompleto = new Intent(SincronizacaoDatabit.this, ServicoIncompletoIntentService.class);
                        startService(intentServicoIncompleto);

                        Thread.sleep(1000);
                        Intent intentOs = new Intent(SincronizacaoDatabit.this,  OsIntentService.class);
                        startService(intentOs);

                        Thread.sleep(1000);
                        Intent intentPonto = new Intent(SincronizacaoDatabit.this, PontoIntentService.class);
                        startService(intentPonto);

                        Gravar();
                        sendNotification("Sincronização DataService", "Dados sincronizados com sucesso !");
                        Thread.sleep((iTempo * 60000));
                    }
                    else {
                        bErro = true;
                        sendNotification("Sincronização DataService", "Não foi possível realizar a operação, o dispositivo esta fora da Internet ou o endereço Webservice está indisponível ! !");
                        Thread.sleep((iTempo * 60000));
                    }

                } catch (Throwable t) {
                    sendNotification("Sincronização Sistema DataService", "Não foi possível realizar a operação, o dispositivo esta fora da Internet ou o endereço Webservice está indisponível ! !");
                    bErro = true;
                    try {
                        Thread.sleep((iTempo * 60000));
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

    private void Gravar() {
        if (bErro == false) {
            configuracaoDAO = new ConfiguracaoDAO();
            configuracao = configuracaoDAO.procuraConfiguracao("id = 1");
            Date data = new Date();
            configuracao.setDatasync(data);
            configuracaoDAO.gravaConfiguracao(configuracao);
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
                        .setSmallIcon(R.mipmap.ic_sync_l)
                        .setLargeIcon(largeIcon)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            Notification note = notificationBuilder.build();
            //note.defaults |= Notification.DEFAULT_VIBRATE;
            //note.defaults |= Notification.DEFAULT_SOUND;
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
