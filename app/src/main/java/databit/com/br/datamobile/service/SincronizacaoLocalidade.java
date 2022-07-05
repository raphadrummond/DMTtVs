package databit.com.br.datamobile.service;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;

import java.util.Date;

import databit.com.br.datamobile.dao.AparelhoDAO;
import databit.com.br.datamobile.domain.Aparelho;
import databit.com.br.datamobile.wsclient.AparelhoWSClient;

public class SincronizacaoLocalidade extends Service
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private AparelhoDAO aparelhoDAO = new AparelhoDAO();
    private Aparelho aparelho;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        aparelhoDAO = new AparelhoDAO();
        aparelho = aparelhoDAO.procuraAparelho("id = 1");
        LocalidadeAsyncTask task = new LocalidadeAsyncTask();
        task.execute();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onConnected(Bundle bundle) {
        //if (ActivityCompat.checkSelfPermission(SincronizacaoLocalidade.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SincronizacaoLocalidade.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        //    return;
        //}
        try {
            Thread.sleep(2000);
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            Thread.sleep(2000);
            if (mLastLocation != null) {
                aparelho.setLatitude(mLastLocation.getLatitude());
                aparelho.setLongitude(mLastLocation.getLongitude());
                Date data = new Date();
                aparelho.setData(data);
                aparelhoDAO.gravaAparelho(aparelho);
            }
            else {
                aparelho.setFabricante("*");
                aparelhoDAO.gravaAparelho(aparelho);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erro " + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    class LocalidadeAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            while (true) {
                try {
                    mGoogleApiClient = new GoogleApiClient.Builder(SincronizacaoLocalidade.this)
                            .addApi(LocationServices.API).addConnectionCallbacks(SincronizacaoLocalidade.this)
                            .addOnConnectionFailedListener(SincronizacaoLocalidade.this).build();

                    Thread.sleep(4000);
                    mGoogleApiClient.connect();
                    Thread.sleep(4000);
                    if (mLastLocation != null) {
                        aparelho.setLatitude(mLastLocation.getLatitude());
                        aparelho.setLongitude(mLastLocation.getLongitude());
                        Date data = new Date();
                        aparelho.setData(data);
                        AparelhoWSClient aparelhoWSClient = new AparelhoWSClient();
                        aparelhoWSClient.enviaLocalidade(aparelho);
                    }
                    mGoogleApiClient.disconnect();
                    Thread.sleep(60000);
                } catch (Throwable t) {
                    try {
                        Thread.sleep(60000);
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





}
