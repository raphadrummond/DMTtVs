package databit.com.br.datamobile.location;

/**
 * Created by Sidney on 16/04/2016.
 */

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import databit.com.br.datamobile.domain.Localizacao;

public class Localizador implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private static Localizador ourInstance = new Localizador();
    private static Context context;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private static Localizacao localizacao;

    public static Localizador getInstance(Context appContext, Localizacao appLocalizacao) {
        context = appContext;
        localizacao = appLocalizacao;
        return ourInstance;
    }

    private Localizador() {
    }

    public void requestLocation() {
        buildGoogleApiClient();
    }

    public void disconnect() {
        Log.i("LOCATION", "onDisconnected");
        mGoogleApiClient.disconnect();
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mGoogleApiClient.connect();
    }



    @Override
    public void onConnected(Bundle bundle) {
        Log.i("LOCATION", "onConnected");
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            Date currentDate = new Date();
            long timeDelta = currentDate.getTime() - mLastLocation.getTime();
            boolean isSignificantlyNewer = timeDelta < 200000 && mLastLocation.getAccuracy() < 350.0f;

            if (isSignificantlyNewer) {
                salvarLocalizacao();
            } else {
                if (isGpsAvaiable(context)) {
                    salvarLocalizacao();
                } else {
                    LocationRequest mLocationRequest = new LocationRequest();
                    mLocationRequest.setInterval(10000);
                    mLocationRequest.setFastestInterval(5000);
                    mLocationRequest.setExpirationDuration(30000);
                    mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
                }
            }
        } else {
            salvarLocalizacao();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("LOCATION", "onConnectionSuspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("LOCATION", "onConnectionFailed" + connectionResult.getErrorCode());
        salvarLocalizacao();
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;

        if (mGoogleApiClient.isConnected())
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);

        salvarLocalizacao();
    }

    private void salvarLocalizacao() {
        if (mLastLocation != null)
            Log.i("LOCATION", "Lat: " + mLastLocation.getLatitude() + " | Lng: " + mLastLocation.getLongitude());
        BuscaEnderecoAsyncTask task = new BuscaEnderecoAsyncTask();
        task.execute();

        if (mGoogleApiClient.isConnected())
            mGoogleApiClient.disconnect();

    }

    public boolean isGpsAvaiable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER));
    }

    class BuscaEnderecoAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            if (mLastLocation != null) {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                try {
                    if (localizacao != null) {
                        Date data = new Date();
                        localizacao.setLatitude(mLastLocation.getLatitude());
                        localizacao.setLongitude(mLastLocation.getLongitude());
                    }
                    List<Address> listAddress = geocoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1);
                    if (listAddress != null && !listAddress.isEmpty()) {
                        Address address = listAddress.get(0);
                        StringBuilder descricao = new StringBuilder();
                        if (address.getThoroughfare() != null) {
                            descricao.append(address.getThoroughfare());
                            if (localizacao != null) {
                                localizacao.setEnd(address.getThoroughfare());
                            }
                        }
                        if (address.getFeatureName() != null) {
                            descricao.append(", " + address.getFeatureName() + ", ");
                            if (localizacao != null) {
                                if (address.getFeatureName() != null) {
                                    localizacao.setNumlocal(address.getFeatureName());
                                }
                                else {
                                    localizacao.setNumlocal("SEM NÚMERO");
                                }
                                try {
                                    localizacao.setNum(Integer.parseInt(address.getFeatureName()));
                                } catch (Throwable t) {
                                    localizacao.setNum(0);
                                }
                            }
                        }
                        if (address.getSubLocality() != null) {
                            descricao.append(address.getSubLocality()+", ");
                            if (localizacao != null) {
                                localizacao.setBairro(address.getSubLocality());
                            }
                        }
                        if (address.getLocality() != null) {
                            descricao.append(address.getLocality()+", ");
                            if (localizacao != null) {
                                localizacao.setCidade(address.getLocality());
                            }
                        }
                        if (address.getPostalCode() != null) {
                            descricao.append(address.getPostalCode()+", ");
                            if (localizacao != null) {
                                localizacao.setCep(address.getPostalCode());
                            }
                        }
                        if (address.getAdminArea() != null) {
                            descricao.append(address.getAdminArea() + ", ");
                            if (localizacao != null) {
                                localizacao.setEstado(address.getAdminArea().replace("State of ", ""));
                            }
                        }
                        Intent intent = new Intent("LOCALIZACAO");
                        intent.putExtra("latitude", mLastLocation.getLatitude());
                        intent.putExtra("longitude", mLastLocation.getLongitude());
                        if (descricao != null) {
                            System.out.println(descricao);
                            localizacao.setLogradouro(descricao.toString());
                            intent.putExtra("endereco", descricao.toString());
                        }
                        context.sendBroadcast(intent);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }


}