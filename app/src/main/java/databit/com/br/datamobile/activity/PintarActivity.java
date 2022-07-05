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
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.dao.CustoDAO;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.dao.PendenteOSDAO;
import databit.com.br.datamobile.dao.RecolhaDAO;
import databit.com.br.datamobile.dao.TrocadaOSDAO;
import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.domain.Custo;
import databit.com.br.datamobile.domain.Localizacao;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.domain.PendenteOS;
import databit.com.br.datamobile.domain.Recolha;
import databit.com.br.datamobile.domain.TrocadaOS;
import databit.com.br.datamobile.fragment.OsFragment;
import databit.com.br.datamobile.fragment.PintarFragment;
import databit.com.br.datamobile.infra.Internet;
import databit.com.br.datamobile.interfaces.SelecionaOs;
import databit.com.br.datamobile.location.Localizador;
import databit.com.br.datamobile.wsclient.OsWSClient;

public class PintarActivity extends AppCompatActivity implements SelecionaOs,
        NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    private FragmentManager mFragmentManager;
    private FragmentManager mFragmentManager2;
    private FragmentTransaction fragmentTransaction_os;
    private FragmentTransaction fragmentTransaction_pintar;
    private Localizacao localizacaoatual;
    private Os osselec;
    private Os os;
    private OsFragment osFragment;
    private PintarFragment pintarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pintar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager2 = getSupportFragmentManager();

        os = (Os) getIntent().getSerializableExtra("os");
        onOsSelecionada(os);

        localizacaoatual = new Localizacao();
        Toast.makeText(PintarActivity.this, "Buscando Localização", Toast.LENGTH_SHORT).show();
        verificaPermissaoLocalizacao();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onOsSelecionada(Os os) {
        osselec = os;

        fragmentTransaction_os = mFragmentManager.beginTransaction();
        osFragment = new OsFragment();
        fragmentTransaction_os.add(R.id.contentOs, osFragment);
        fragmentTransaction_os.commit();

        fragmentTransaction_pintar = mFragmentManager2.beginTransaction();
        pintarFragment = new PintarFragment();
        fragmentTransaction_pintar.add(R.id.contentInfor, pintarFragment);
        fragmentTransaction_pintar.commit();
    }

    @Override
    public Os getOsSelecionada() {
        return osselec;
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
            Toast.makeText(PintarActivity.this, "GPS não habilitado", Toast.LENGTH_SHORT).show();
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_fecha_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.op_checkout: {
                checkOut(osselec);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkOut (final Os osin) {
        Boolean bValidar = true;
        switch (osin.getOperacaomobile()) {
            case 1: {
                bValidar = (osin.getObs() != null);
                break;
            }
            case 4: case 5: case 6:{
                bValidar = (osin.getLaudo() != null);
                break;
            }
        }
        if (osin.getFechaok().equals(1)) {
            if (osin.getSync().toString().equals("N") ) {
                if (bValidar) {
                    if (osin.getAssinatura() != null) {
                        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                        alertDialog.setTitle("Confirmação");
                        alertDialog.setMessage("Deseja Finalizar o Atendimento desta OS ?");
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Sim",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        try {
                                            if (Confighora() == 1) {
                                                Toast.makeText(PintarActivity.this, "Buscando Localização", Toast.LENGTH_SHORT).show();
                                                verificaPermissaoLocalizacao();
                                                localizacaoatual =  buscaLocalizacao(localizacaoatual);                                            OsDAO osDAO = new OsDAO();
                                                Thread.sleep(1000);
                                                Date data = new Date();
                                                osin.setCheckoutok(1);
                                                osin.setCheckout(data);
                                                osin.setStatus_check(4);
                                                osin.setDataout(data);
                                                if ((osin.getLatitudeout().equals(0.0)) && (osin.getLongitudeout().equals(0.0))) {
                                                    if (localizacaoatual.getEnd() != null) {
                                                        osin.setEndout(localizacaoatual.getEnd().toString());
                                                    }
                                                    if (localizacaoatual.getNum() != null) {
                                                        osin.setNumout(localizacaoatual.getNum());
                                                    }
                                                    if (localizacaoatual.getBairro() != null) {
                                                        osin.setBairroout(localizacaoatual.getBairro().toString());
                                                    }
                                                    if (localizacaoatual.getCidade() != null) {
                                                        osin.setCidadeout(localizacaoatual.getCidade().toString());
                                                    }
                                                    if (localizacaoatual.getEstado() != null) {
                                                        osin.setEstadoout(localizacaoatual.getEstado().toString());
                                                    }
                                                    if (localizacaoatual.getCep() != null) {
                                                        osin.setCepout(localizacaoatual.getCep().toString());
                                                    }
                                                    if (localizacaoatual.getNumlocal() != null) {
                                                        osin.setNumlocalout(localizacaoatual.getNumlocal().toString());
                                                    }
                                                    osin.setLatitudeout(localizacaoatual.getLatitude());
                                                    osin.setLongitudeout(localizacaoatual.getLongitude());
                                                }

                                                ConfiguracaoDAO configuracaoDAO = new ConfiguracaoDAO();
                                                Configuracao configuracao = configuracaoDAO.procuraConfiguracao("id = 1");
                                                if (configuracao.getTipolan().equals("L")) {

                                                    // Peças Pendentes
                                                    PendenteOSDAO pendenteOSDAO = new PendenteOSDAO();
                                                    List<PendenteOS> listPendenteOS = new ArrayList<>();
                                                    listPendenteOS = pendenteOSDAO.listarPendenteOS(" os = '" + osin.getCodigo() + "' " +
                                                            " and banco = '" + osin.getBanco() + "' ");
                                                    String sPecaspendente = "";
                                                    if (listPendenteOS.size() > 0) {
                                                        for (PendenteOS pendenteOS : listPendenteOS) {
                                                            sPecaspendente = sPecaspendente + "@";
                                                            sPecaspendente = sPecaspendente + pendenteOS.getCodigo().toString();
                                                            sPecaspendente = sPecaspendente + ";";
                                                            sPecaspendente = sPecaspendente + pendenteOS.getQtprod().toString();
                                                            sPecaspendente = sPecaspendente + "#";
                                                        }
                                                        osin.setObspendente(sPecaspendente);
                                                    }

                                                    // Peças Trocadas
                                                    TrocadaOSDAO trocadaOSDAO = new TrocadaOSDAO();
                                                    List<TrocadaOS> listTrocadaOS = new ArrayList<>();
                                                    listTrocadaOS = trocadaOSDAO.listarTrocadaOS(" os = '" + osin.getCodigo() + "' " +
                                                            " and banco = '" + osin.getBanco() + "' ");
                                                    String sPecastrocada = "";
                                                    if (listTrocadaOS.size() > 0) {
                                                        for (TrocadaOS trocadaOS : listTrocadaOS) {
                                                            sPecastrocada = sPecastrocada + "@";
                                                            sPecastrocada = sPecastrocada + trocadaOS.getCodigo().toString();
                                                            sPecastrocada = sPecastrocada + ";";
                                                            sPecastrocada = sPecastrocada + trocadaOS.getQtprod().toString();
                                                            sPecastrocada = sPecastrocada + "#";
                                                        }
                                                        osin.setObstrocada(sPecastrocada);
                                                    }

                                                    // Peças a Recolher
                                                    RecolhaDAO recolhaDAO = new RecolhaDAO();
                                                    List<Recolha> listRecolha = new ArrayList<>();
                                                    listRecolha = recolhaDAO.listarRecolha(" os = '" + osin.getCodigo() + "' " +
                                                            " and banco = '" + osin.getBanco() + "' ");
                                                    String sPecasrecolha = "";
                                                    if (listRecolha.size() > 0) {
                                                        for (Recolha recolha : listRecolha) {
                                                            sPecasrecolha = sPecasrecolha + "@";
                                                            sPecasrecolha = sPecasrecolha + recolha.getCodigo().toString();
                                                            sPecasrecolha = sPecasrecolha + ";";
                                                            sPecasrecolha = sPecasrecolha + recolha.getQtprod().toString();
                                                            sPecasrecolha = sPecasrecolha + "#";
                                                        }
                                                        osin.setObsrecolha(sPecasrecolha);
                                                    }

                                                    // Serviços Realizados
                                                    CustoDAO custoDAO = new CustoDAO();
                                                    List<Custo> listCusto = new ArrayList<>();
                                                    listCusto = custoDAO.listarCusto(" os = '" + osin.getCodigo() + "' " +
                                                            " and banco = '" + osin.getBanco() + "' ");
                                                    String sCustos = "";
                                                    if (listCusto.size() > 0) {
                                                        for (Custo custo : listCusto) {
                                                            sCustos= sCustos+ "@";
                                                            sCustos= sCustos+ custo.getCodigo().toString();
                                                            sCustos= sCustos+ ";";
                                                            sCustos= sCustos+ custo.getValor().toString();
                                                            sCustos= sCustos+ "#";
                                                        }
                                                        osin.setObsservicos(sCustos);
                                                    }
                                                }
                                                osDAO.gravaOs(osin);
                                                sincronizarFechamento();
                                            }
                                            else {
                                                Toast.makeText(PintarActivity.this, "Não foi possivel realizar esta operação, verifique se a HORA do dispositivo está automaticamente com o horário da rede, reiniciar o aplicativo, e tentar novamente !", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(Settings.ACTION_DATE_SETTINGS));
                                            }
                                        } catch (Throwable t) {
                                            Toast.makeText(PintarActivity.this, "Não foi possivel realizar esta operação: "+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
                    else {
                        Toast.makeText(PintarActivity.this, "Será necessario colocar a ASSINATURA da OS!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(PintarActivity.this, "Será necessario colocar o laudo da OS, favor clicar no botão LAUDO!", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(PintarActivity.this, "Os já enviada ao Servidor!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(PintarActivity.this, "Ainda não foi salva as informações de fechamento!", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }


    private void sincronizarFechamento() {
        SincFechamentoAsyncTask task = new SincFechamentoAsyncTask();
        task.execute();
    }

    class SincFechamentoAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private boolean bErro = false;
        private String sResult;
        private String sErro;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(PintarActivity.this, null, "Sincronizando Check-Out");
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
                Toast.makeText(PintarActivity.this, "Sincronização realizada com Sucesso !", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(PintarActivity.this, "Erro não foi possivel realizar a operação:"+sErro, Toast.LENGTH_SHORT).show();
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


}
