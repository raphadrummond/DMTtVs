package databit.com.br.datamobile.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import databit.com.br.datamobile.domain.Localizacao;

public class Mapa implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;

    public Mapa() {
    }

    public Localizacao LocalMapa(Context context) {
        Localizacao localizacao = new Localizacao();
        localizacao.setLatitude((double) 0L);
        localizacao.setLongitude((double) 0L);
        if (isGpsAvaiable(context)) {
            mGoogleApiClient = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
            }
            else {
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            }
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
                                } else {
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
                            descricao.append(address.getSubLocality() + ", ");
                            if (localizacao != null) {
                                localizacao.setBairro(address.getSubLocality());
                            }
                        }
                        if (address.getLocality() != null) {
                            descricao.append(address.getLocality() + ", ");
                            if (localizacao != null) {
                                localizacao.setCidade(address.getLocality());
                            }
                        }
                        if (address.getPostalCode() != null) {
                            descricao.append(address.getPostalCode() + ", ");
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
                        if (descricao != null) {
                            System.out.println(descricao);
                            localizacao.setLogradouro(descricao.toString());
                            intent.putExtra("endereco", descricao.toString());
                            Toast.makeText(context, "Endereço Encontrado : " + descricao.toString(), Toast.LENGTH_SHORT).show();
                        }
                        context.sendBroadcast(intent);
                    }
                } catch (IOException e) {
                    Toast.makeText(context, "Erro ao buscar localização : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                mGoogleApiClient.disconnect();
            }
        } else {
            Toast.makeText(context, "GPS não habilitado", Toast.LENGTH_SHORT).show();
        }
        return localizacao;
    }

    static boolean isGpsAvaiable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
