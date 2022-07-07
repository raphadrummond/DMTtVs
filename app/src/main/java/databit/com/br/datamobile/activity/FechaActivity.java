package databit.com.br.datamobile.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import com.google.android.material.navigation.NavigationView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.domain.Arquivo;
import databit.com.br.datamobile.domain.Custo;
import databit.com.br.datamobile.domain.Localizacao;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.domain.PendenteOS;
import databit.com.br.datamobile.domain.Recolha;
import databit.com.br.datamobile.domain.TrocadaOS;
import databit.com.br.datamobile.fragment.OsFragment;
import databit.com.br.datamobile.infra.Internet;
import databit.com.br.datamobile.interfaces.SelecionaArquivo;
import databit.com.br.datamobile.interfaces.SelecionaCusto;
import databit.com.br.datamobile.interfaces.SelecionaOs;
import databit.com.br.datamobile.interfaces.SelecionaPendenteOS;
import databit.com.br.datamobile.interfaces.SelecionaRecolha;
import databit.com.br.datamobile.interfaces.SelecionaTrocadaOS;
import databit.com.br.datamobile.location.Localizador;
import databit.com.br.datamobile.pages.PagerFechaFragment;
import databit.com.br.datamobile.wsclient.OsWSClient;


public class FechaActivity extends AppCompatActivity implements SelecionaOs,SelecionaPendenteOS,SelecionaTrocadaOS,SelecionaCusto,SelecionaRecolha, SelecionaArquivo,
        NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    private FragmentManager mFragmentManager;
    private FragmentManager mFragmentManager2;

    private PagerFechaFragment pagerFechaFragment;
    private Os osselec;
    private Os os;
    private FragmentTransaction fragmentTransaction_os;
    private OsFragment osFragment;
    private FragmentTransaction fragmentTransaction_fecha;
    private Localizacao localizacaoatual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fecha);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_dataservice);
        setSupportActionBar(toolbar);

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager2 = getSupportFragmentManager();
        os = (Os) getIntent().getSerializableExtra("os");

        localizacaoatual = new Localizacao();
        Toast.makeText(FechaActivity.this, "Buscando Localização", Toast.LENGTH_SHORT).show();
        verificaPermissaoLocalizacao();

        onOsSelecionada(os);

    }


    @Override
    public void onOsSelecionada(Os os) {
        osselec = os;

        fragmentTransaction_os = mFragmentManager.beginTransaction();
        osFragment = new OsFragment();
        fragmentTransaction_os.add(R.id.contentOsfecha, osFragment);
        fragmentTransaction_os.commit();


        pagerFechaFragment =  new PagerFechaFragment();
        pagerFechaFragment.osselec = os;
        fragmentTransaction_fecha = mFragmentManager2.beginTransaction();
        fragmentTransaction_fecha.add(R.id.contentInforfecha, pagerFechaFragment);
        fragmentTransaction_fecha.commit();
    }

    @Override
    public Os getOsSelecionada() {
        return osselec;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_assina_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.op_assinar: {
                if (!(osselec.getPreventiva().equals("A"))) {
                    assinar(osselec);
                }
                else {
                    FecharAfericao();
                }
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    private void assinar (final Os osin) {
        Boolean bValidar = true;
        switch (osin.getOperacaomobile()) {
            case 1: {
                 bValidar = (osin.getObs() != null);
                 break;
            }
            case 4: case 5: case 6: case 7:{
                 bValidar = (osin.getLaudo() != null);
                 break;
            }
        }
        if (osin.getFechaok().equals(1)) {
            if (osin.getSync().toString().equals("N") ) {
                if (bValidar) {
                    if (osin.getCheckoutok().equals(0)) {
                        Intent intent = new Intent(FechaActivity.this, PintarActivity.class);
                        intent.putExtra("os", osselec);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(FechaActivity.this, "OS já fechada !", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(FechaActivity.this, "Será necessario colocar o laudo da OS, favor clicar no botão LAUDO!", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(FechaActivity.this, "Os já enviada ao Servidor!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(FechaActivity.this, "Ainda não foi salva as informações de fechamento!", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onCustoSelecionado(Custo custo) {

    }

    @Override
    public Custo onCustoSelecionado() {
        return null;
    }

    @Override
    public void onPendenteOSSelecionado(PendenteOS pendenteOS) {

    }

    @Override
    public PendenteOS onPendenteOSSelecionado() {
        return null;
    }

    @Override
    public void onTrocadaOSSelecionado(TrocadaOS trocadaOS) {

    }

    @Override
    public TrocadaOS onTrocadaOSSelecionado() {
        return null;
    }

    @Override
    public void onRecolhaSelecionado(Recolha recolha) {

    }

    @Override
    public Recolha onRecolhaSelecionado() {
        return null;
    }

    @Override
    public void onArquivoSelecionado(Arquivo arquivo) {

    }

    @Override
    public Arquivo onArquivoSelecionado() {
        return null;
    }
    
    private void FecharAfericao() {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Confirmação");
        alertDialog.setMessage("Deseja finalizar o lançamento os medidores ?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Sim",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            if (Confighora() == 1) {
                                Toast.makeText(FechaActivity.this, "Buscando Localização", Toast.LENGTH_SHORT).show();
                                verificaPermissaoLocalizacao();
                                localizacaoatual =  buscaLocalizacao(localizacaoatual);                                            OsDAO osDAO = new OsDAO();
                                Thread.sleep(1000);
                                Date data = new Date();
                                osselec.setCheckoutok(1);
                                osselec.setCheckout(data);
                                osselec.setStatus_check(4);
                                osselec.setDataout(data);
                                if ((osselec.getLatitudeout().equals(0.0)) && (osselec.getLongitudeout().equals(0.0))) {
                                    if (localizacaoatual.getEnd() != null) {
                                        osselec.setEndout(localizacaoatual.getEnd().toString());
                                    }
                                    if (localizacaoatual.getNum() != null) {
                                        osselec.setNumout(localizacaoatual.getNum());
                                    }
                                    if (localizacaoatual.getBairro() != null) {
                                        osselec.setBairroout(localizacaoatual.getBairro().toString());
                                    }
                                    if (localizacaoatual.getCidade() != null) {
                                        osselec.setCidadeout(localizacaoatual.getCidade().toString());
                                    }
                                    if (localizacaoatual.getEstado() != null) {
                                        osselec.setEstadoout(localizacaoatual.getEstado().toString());
                                    }
                                    if (localizacaoatual.getCep() != null) {
                                        osselec.setCepout(localizacaoatual.getCep().toString());
                                    }
                                    if (localizacaoatual.getNumlocal() != null) {
                                        osselec.setNumlocalout(localizacaoatual.getNumlocal().toString());
                                    }
                                    osselec.setLatitudeout(localizacaoatual.getLatitude());
                                    osselec.setLongitudeout(localizacaoatual.getLongitude());
                                }
                                osDAO.gravaOs(osselec);
                                sincronizarFechamento();
                            }
                            else {
                                Toast.makeText(FechaActivity.this, "Não foi possivel realizar esta operação, verifique se a HORA do dispositivo está automaticamente com o horário da rede, reiniciar o aplicativo, e tentar novamente !", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Settings.ACTION_DATE_SETTINGS));
                            }
                        } catch (Throwable t) {
                            Toast.makeText(FechaActivity.this, "Não foi possivel realizar esta operação: "+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Não",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
        try {
            alertDialog.show();
        } catch (Throwable t) {

        }
    }


    private void sincronizarFechamento() {
        FechaActivity.SincFechamentoAsyncTask task = new FechaActivity.SincFechamentoAsyncTask();
        task.execute();
    }

    class SincFechamentoAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private boolean bErro = false;
        private String sResult;
        private String sErro;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(FechaActivity.this, null, "Sincronizando Check-Out");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            sResult="";
            sErro="";
            try {
                if ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext()))) {
                    OsWSClient osWSClient = new OsWSClient();
                    OsDAO osDAO = new OsDAO();
                    sResult = osWSClient.enviaOS(os);
                    Thread.sleep(1000);
                    bErro = !(sResult.equals("OK"));
                    if (bErro == false) {
                        os.setSync("S");
                        osDAO.gravaOs(os);
                    }
                }

            } catch (Throwable t) {
                bErro = true;
                sErro = t.getMessage().toString();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                Toast.makeText(FechaActivity.this, "Sincronização realizada com Sucesso !", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(FechaActivity.this, "Erro não foi possivel realizar a operação:"+sErro, Toast.LENGTH_SHORT).show();
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    public int Confighora() {
        int type = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            type =  Settings.Global.getInt(getContentResolver(), Settings.Global.AUTO_TIME, 0);
        }else{
            type =  Settings.System.getInt(getContentResolver(), Settings.System.AUTO_TIME, 0);
        }
        return type;
    }


    private void verificaPermissaoLocalizacao() {
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (permission != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 1);

        } else {
            localizacaoatual =  buscaLocalizacao(localizacaoatual);
        }
    }

    private Localizacao buscaLocalizacao(Localizacao localizacao) {
        if (!Localizador.getInstance(this, localizacao).isGpsAvaiable(this)) {
            Intent viewIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(viewIntent);
            Toast.makeText(FechaActivity.this, "GPS não habilitado", Toast.LENGTH_SHORT).show();
        }
        Localizador.getInstance(this, localizacao).requestLocation();
        return localizacao;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    finish();
                } else {
                    localizacaoatual =  buscaLocalizacao(localizacaoatual);
                }
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        String sLocaliza = "Cliente: "+os.getNomecli();
        LatLng sydney = new LatLng(os.getLatitude(), os.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(sydney).title(sLocaliza));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17));
        Localizador.getInstance(this, localizacaoatual).requestLocation();

    }

}
