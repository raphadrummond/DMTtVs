package databit.com.br.datamobile.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Address;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.location.GMapV2Direction;
import databit.com.br.datamobile.location.Localizador;
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        Localizador.getInstance(this, null).requestLocation();

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=" + marker.getPosition().latitude + "," + marker.getPosition().longitude));
                startActivity(intent);
                return true;
            }
        });


    }

    @Override
    protected void onResume() {
        registerReceiver(receiverLocalizacao, new IntentFilter("LOCALIZACAO"));
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(receiverLocalizacao);
        super.onPause();
    }

    private BroadcastReceiver receiverLocalizacao = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
          double latitude = intent.getDoubleExtra("latitude",0);
          double longitude = intent.getDoubleExtra("longitude",0);
          String endereco = intent.getStringExtra("endereco");

          LatLng minhalocalizacao = new LatLng(latitude, longitude);

          //BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_icon_service);
          //mMap.addMarker(new MarkerOptions().position(minhalocalizacao).title(endereco).icon(icon));

          mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(minhalocalizacao, 17));

          BuscaRotaAsyncTask task = new BuscaRotaAsyncTask();
          task.execute(minhalocalizacao);

        }
    };

    class BuscaRotaAsyncTask extends AsyncTask<LatLng, Void, Address> {
        private PolylineOptions rota;
        private LatLng location;
        private LatLng enderecoFinal;

        @Override
        protected Address doInBackground(LatLng... params) {
            try {
                location = params[0];
                GMapV2Direction direction = new GMapV2Direction();
                GMapV2Direction.Rota r = direction.getRota(params[0], "Rua Nelson Clayton Linon, 142, Santa Helena, Belo Horizonte, MG, 30642220", "driving");
                enderecoFinal = r.getPolylineOptions().getPoints().get(r.getPolylineOptions().getPoints().size()-1);
                rota = r.getPolylineOptions();
            } catch (Throwable t) {
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Address address) {
            try {
                if (rota != null) {
                    mMap.addPolyline(rota);
                    mMap.addMarker(new MarkerOptions().position(enderecoFinal).title("Endereço Final"));
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
            super.onPostExecute(address);
        }
    }



}
