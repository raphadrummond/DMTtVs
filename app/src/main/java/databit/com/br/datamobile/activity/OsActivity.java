package databit.com.br.datamobile.activity;

import android.Manifest;
import android.app.Dialog;
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
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.dao.HistoricoDAO;
import databit.com.br.datamobile.dao.HistoricoTELDAO;
import databit.com.br.datamobile.dao.ItemDAO;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.dao.OsDefeitoDAO;
import databit.com.br.datamobile.dao.PendenteDAO;
import databit.com.br.datamobile.dao.SerialDAO;
import databit.com.br.datamobile.dao.TrocadaDAO;
import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.domain.Historico;
import databit.com.br.datamobile.domain.HistoricoTEL;
import databit.com.br.datamobile.domain.HistoricoTI;
import databit.com.br.datamobile.domain.Item;
import databit.com.br.datamobile.domain.Localizacao;
import databit.com.br.datamobile.domain.Logsinc;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.domain.OsDefeito;
import databit.com.br.datamobile.domain.Pendente;
import databit.com.br.datamobile.domain.PendenteOS;
import databit.com.br.datamobile.domain.Serial;
import databit.com.br.datamobile.domain.Trocada;
import databit.com.br.datamobile.domain.Usuario;
import databit.com.br.datamobile.fragment.HistoricoFragment;
import databit.com.br.datamobile.fragment.HistoricoTELFragment;
import databit.com.br.datamobile.fragment.HistoricoTIFragment;
import databit.com.br.datamobile.fragment.ItemFragment;
import databit.com.br.datamobile.fragment.LogFragment;
import databit.com.br.datamobile.fragment.OsDefeitoFragment;
import databit.com.br.datamobile.fragment.OsFragment;
import databit.com.br.datamobile.fragment.PendenteFragment;
import databit.com.br.datamobile.fragment.PendenteOSFragment;
import databit.com.br.datamobile.fragment.SerialFragment;
import databit.com.br.datamobile.fragment.TrocadaFragment;
import databit.com.br.datamobile.infra.Internet;
import databit.com.br.datamobile.interfaces.SelecionaHistorico;
import databit.com.br.datamobile.interfaces.SelecionaHistoricoTEL;
import databit.com.br.datamobile.interfaces.SelecionaHistoricoTI;
import databit.com.br.datamobile.interfaces.SelecionaItem;
import databit.com.br.datamobile.interfaces.SelecionaLog;
import databit.com.br.datamobile.interfaces.SelecionaOs;
import databit.com.br.datamobile.interfaces.SelecionaOsDefeito;
import databit.com.br.datamobile.interfaces.SelecionaPendente;
import databit.com.br.datamobile.interfaces.SelecionaPendenteOS;
import databit.com.br.datamobile.interfaces.SelecionaSerial;
import databit.com.br.datamobile.interfaces.SelecionaTrocada;
import databit.com.br.datamobile.location.Localizador;
import databit.com.br.datamobile.pages.PagerFragment;
import databit.com.br.datamobile.ponto.ControlePonto;
import databit.com.br.datamobile.wsclient.HistoricoTELWSClient;
import databit.com.br.datamobile.wsclient.HistoricoWSClient;
import databit.com.br.datamobile.wsclient.ItemWSClient;
import databit.com.br.datamobile.wsclient.OsDefeitoWSClient;
import databit.com.br.datamobile.wsclient.OsWSClient;
import databit.com.br.datamobile.wsclient.PendenteWSClient;
import databit.com.br.datamobile.wsclient.SerialWSClient;
import databit.com.br.datamobile.wsclient.TrocadaWSClient;


public class OsActivity extends AppCompatActivity implements SelecionaOs,
        NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback,
        SelecionaHistorico, SelecionaOsDefeito, SelecionaPendente,
        SelecionaTrocada, SelecionaPendenteOS,SelecionaLog,
        SelecionaHistoricoTI, SelecionaItem, SelecionaSerial,
        SelecionaHistoricoTEL{

    private FragmentManager mFragmentManager;
    private FragmentManager mFragmentManager2;
    private FragmentManager mFragmentManager3;
    private FragmentManager mFragmentManager4;
    private FragmentManager mFragmentManager5;
    private FragmentManager mFragmentManager6;
    private FragmentManager mFragmentManager7;
    private FragmentManager mFragmentManager8;
    private FragmentManager mFragmentManager9;
    private FragmentManager mFragmentManager10;
    private FragmentManager mFragmentManager11;
    private FragmentManager mFragmentManager12;
    private PagerFragment pagerFragment;
    private Os osselec;
    private Os os;
    private Usuario usuario;
    private FragmentTransaction fragmentTransaction_os;
    private OsFragment osFragment;
    private Localizacao localizacaoatual;
    private Boolean bOnline;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_dataservice);
        setSupportActionBar(toolbar);

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager2 = getSupportFragmentManager();
        mFragmentManager3 = getSupportFragmentManager();
        mFragmentManager4 = getSupportFragmentManager();
        mFragmentManager5 = getSupportFragmentManager();
        mFragmentManager6 = getSupportFragmentManager();
        mFragmentManager7 = getSupportFragmentManager();
        mFragmentManager8 = getSupportFragmentManager();
        mFragmentManager9 = getSupportFragmentManager();
        mFragmentManager10 = getSupportFragmentManager();
        mFragmentManager11 = getSupportFragmentManager();
        mFragmentManager12 = getSupportFragmentManager();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        os = (Os) getIntent().getSerializableExtra("os");

        localizacaoatual = new Localizacao();
        Toast.makeText(OsActivity.this, "Buscando Localização", Toast.LENGTH_SHORT).show();
        verificaPermissaoLocalizacao();
        onOsSelecionada(os);
        sincronizarStatus();
        Button btnAtualiza = (Button) findViewById(R.id.btnAtualizar);
        btnAtualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (osselec.getOperacaomobile()) {
                    case 1: {
                        sincronizarOsDefeito();
                        sincronizarPendente();
                        sincronizarTrocada();
                        sincronizarHistorico();
                        break;
                    }
                    case 2: case 3: {
                        sincronizarItem();
                        sincronizarSerial();
                        break;
                    }
                    case 7: {
                        sincronizarHistoricoTEL();
                        break;
                    }
                }
            }
        });

    }

    @Override
    public void onOsSelecionada(Os os) {
        osselec = os;

        fragmentTransaction_os = mFragmentManager.beginTransaction();
        osFragment = new OsFragment();
        fragmentTransaction_os.add(R.id.contentOs, osFragment);
        fragmentTransaction_os.commit();


        pagerFragment =  new PagerFragment();
        pagerFragment.osselec = os;
        FragmentTransaction fragmentTransaction_page = mFragmentManager2.beginTransaction();
        fragmentTransaction_page.add(R.id.contentInfor, pagerFragment);
        fragmentTransaction_page.commit();
        pagerFragment.getMapFragment().getMapAsync(this);

        pagerFragment =  new PagerFragment();
        pagerFragment.osselec = os;
        FragmentTransaction fragmentTransaction_historico = mFragmentManager3.beginTransaction();
        HistoricoFragment historicoFragment  = new HistoricoFragment();
        fragmentTransaction_historico.add(R.id.contentInfor, pagerFragment);
        fragmentTransaction_historico.commit();

        pagerFragment =  new PagerFragment();
        pagerFragment.osselec = os;
        FragmentTransaction fragmentTransaction_osdefeito = mFragmentManager4.beginTransaction();
        OsDefeitoFragment osDefeitoFragment  = new OsDefeitoFragment();
        fragmentTransaction_osdefeito.add(R.id.contentInfor, pagerFragment);
        fragmentTransaction_osdefeito.commit();

        pagerFragment =  new PagerFragment();
        pagerFragment.osselec = os;
        FragmentTransaction fragmentTransaction_pendente = mFragmentManager5.beginTransaction();
        PendenteFragment pendenteFragment  = new PendenteFragment();
        fragmentTransaction_pendente.add(R.id.contentInfor, pagerFragment);
        fragmentTransaction_pendente.commit();


        pagerFragment =  new PagerFragment();
        pagerFragment.osselec = os;
        FragmentTransaction fragmentTransaction_trocada = mFragmentManager6.beginTransaction();
        TrocadaFragment trocadaFragment = new TrocadaFragment();
        fragmentTransaction_trocada.add(R.id.contentInfor, pagerFragment);
        fragmentTransaction_trocada.commit();

        pagerFragment =  new PagerFragment();
        pagerFragment.osselec = os;
        FragmentTransaction fragmentTransaction_pendenteOS = mFragmentManager7.beginTransaction();
        PendenteOSFragment pendenteOSFragment = new PendenteOSFragment();
        fragmentTransaction_pendenteOS.add(R.id.contentInfor, pagerFragment);
        fragmentTransaction_pendenteOS.commit();

        pagerFragment =  new PagerFragment();
        pagerFragment.osselec = os;
        FragmentTransaction fragmentTransaction_Log = mFragmentManager8.beginTransaction();
        LogFragment logFragment = new LogFragment();
        fragmentTransaction_Log.add(R.id.contentInfor, pagerFragment);
        fragmentTransaction_Log.commit();

        pagerFragment =  new PagerFragment();
        pagerFragment.osselec = os;
        FragmentTransaction fragmentTransaction_historico_ti = mFragmentManager9.beginTransaction();
        HistoricoTIFragment historicoTIFragment = new HistoricoTIFragment();
        fragmentTransaction_historico_ti.add(R.id.contentInfor, pagerFragment);
        fragmentTransaction_historico_ti.commit();

        pagerFragment =  new PagerFragment();
        pagerFragment.osselec = os;
        FragmentTransaction fragmentTransaction_item = mFragmentManager10.beginTransaction();
        ItemFragment itemFragment = new ItemFragment();
        fragmentTransaction_item.add(R.id.contentInfor, pagerFragment);
        fragmentTransaction_item.commit();

        pagerFragment =  new PagerFragment();
        pagerFragment.osselec = os;
        FragmentTransaction fragmentTransaction_serial = mFragmentManager11.beginTransaction();
        SerialFragment serialFragment = new SerialFragment();
        fragmentTransaction_serial.add(R.id.contentInfor, pagerFragment);
        fragmentTransaction_serial.commit();


        pagerFragment =  new PagerFragment();
        pagerFragment.osselec = os;
        FragmentTransaction fragmentTransaction_historico_tel = mFragmentManager12.beginTransaction();
        HistoricoTELFragment historicoTELFragment = new HistoricoTELFragment();
        fragmentTransaction_historico_tel.add(R.id.contentInfor, pagerFragment);
        fragmentTransaction_historico_tel.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        switch (osselec.getOperacaomobile()) {
            case 1: case 4: case 5: case 6: {
                inflater.inflate(R.menu.activity_os_actions, menu);
                break;
            }
            case 2: case 3: {
                inflater.inflate(R.menu.activity_req_actions, menu);
                break;
            }
            case 7: {
                inflater.inflate(R.menu.activity_tel_actions, menu);
                break;
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String sRetorno = "OK";
        Boolean bMensagem = true;
        Integer iOperacao = 0;
        switch (item.getItemId()) {
            case R.id.op_checkout: {
                iOperacao = 2;
                break;
            }
            default: {
                iOperacao = 1;
                break;
            }
        }
        try {
            sRetorno = ControlePonto.ValidarPonto(true, iOperacao);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        switch (item.getItemId()) {
            case R.id.op_checkin: {
                if (sRetorno.equals("OK")) {
                   checkIn(osselec);
                }
                break;
            }
            case R.id.op_entrega: {
                if (sRetorno.equals("OK")) {
                    Intent intent = new Intent(OsActivity.this, FechaActivity.class);
                    intent.putExtra("os", osselec);
                    startActivity(intent);
                    finish();
                }
                break;
            }
            case R.id.op_atende: {
                if (sRetorno.equals("OK")) {
                    Intent intent = new Intent(OsActivity.this, FechaActivity.class);
                    intent.putExtra("os", osselec);
                    startActivity(intent);
                    finish();
                }
                break;
            }            
            case R.id.op_checkout: {
                if (sRetorno.equals("OK")) {
                    if (osselec.getStatus_check() >= 3) {
                        Intent intent = new Intent(OsActivity.this, FechaActivity.class);
                        intent.putExtra("os", osselec);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(OsActivity.this, "Esta OS ainda não foi inicializada!", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            }
            case R.id.op_sync: {
                bMensagem = false;
                final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Confirmação");
                alertDialog.setMessage("Deseja sincronizar esta OS ?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Sim",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sincronizarOs();
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
                break;
            }
            case R.id.op_canc: {
                if (sRetorno.equals("OK")) {
                    if (osselec.getStatus_check() < 4) {
                        final OsDAO osDAO = new OsDAO();
                        final Dialog dialog = new Dialog(OsActivity.this);
                        dialog.setContentView(R.layout.dialog);
                        dialog.setTitle("Motivo Cancelamento:");
                        final EditText txtMotivo = (EditText) dialog.findViewById(R.id.txtMotivo);
                        dialog.show();
                        Button btnOk = (Button) dialog.findViewById(R.id.btnOK);
                        btnOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                os.setObscanc(txtMotivo.getText().toString());
                                osDAO.gravaOs(os);
                                sincronizarCancelamento();
                            }
                        });
                        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
                        btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                    else {
                        Toast.makeText(OsActivity.this, "Esta OS já foi finalizada !", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            }
            case R.id.op_ordem: {
                if (sRetorno.equals("OK")) {
                    if (osselec.getStatus_check() < 4) {
                        final OsDAO osDAO = new OsDAO();
                        final Dialog dialog = new Dialog(OsActivity.this);
                        dialog.setContentView(R.layout.dialog);
                        dialog.setTitle("Digite nova Ordem:");
                        final EditText txtMotivo = (EditText) dialog.findViewById(R.id.txtMotivo);
                        txtMotivo.setText(osselec.getOrdem().toString());
                        txtMotivo.setInputType(InputType.TYPE_CLASS_NUMBER);
                        dialog.show();
                        Button btnOk = (Button) dialog.findViewById(R.id.btnOK);
                        btnOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                os.setOrdemtec(Integer.parseInt(txtMotivo.getText().toString()));
                                osDAO.gravaOs(os);
                                sincronizarOrdem();
                            }
                        });
                        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
                        btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                    else {
                        Toast.makeText(OsActivity.this, "Esta OS já foi finalizada !", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            }
            case R.id.op_email: {
                if (osselec.getStatus_check() >= 4) {
                    final OsDAO osDAO = new OsDAO();
                    final Dialog dialog = new Dialog(OsActivity.this);
                    dialog.setContentView(R.layout.dialog);
                    dialog.setTitle("Digite o Email:");
                    final EditText txtMotivo = (EditText) dialog.findViewById(R.id.txtMotivo);
                    txtMotivo.setText(osselec.getEmail().toString());
                    dialog.show();
                    Button btnOk = (Button) dialog.findViewById(R.id.btnOK);
                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            os.setEmail(txtMotivo.getText().toString());
                            osDAO.gravaOs(os);
                            sincronizarEmail();
                        }
                    });
                    Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }
                else {
                    Toast.makeText(OsActivity.this, "Esta OS não foi finalizada !", Toast.LENGTH_SHORT).show();
                }

            }

        }
        if (!(sRetorno.equals("OK")) && (bMensagem)) {
            final AlertDialog alertDialog = new AlertDialog.Builder(OsActivity.this).create();
            alertDialog.setTitle("Controle de Ponto");
            alertDialog.setMessage("Atenção, não é possivel realizar esta operação: " + sRetorno);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //finish();
                        }
                    });
            try {
                alertDialog.show();
            } catch (Throwable t) {
            }
        }
        return super.onOptionsItemSelected(item);
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
            Toast.makeText(OsActivity.this, "GPS não habilitado", Toast.LENGTH_SHORT).show();
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
                   localizacaoatual = buscaLocalizacao(localizacaoatual);
                }
            }
        }
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
    public void onMapReady(GoogleMap googleMap) {
        OsDAO osDAO = new OsDAO();
        String sLocaliza = "Localização Cliente: "+os.getNomecli();
        String sLocalizain;
        String sLocalizaout;
        if ((os.getLatitude() != null) && (os.getLongitude() != null)) {
            if (!(os.getLatitude().equals(0.0)) && !(os.getLongitude().equals(0.0))) {
                try {
                    LatLng mapaCliente = new LatLng(os.getLatitude(), os.getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(mapaCliente).title(sLocaliza).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapaCliente, 17));
                } catch (Throwable t) {
                    Toast.makeText(OsActivity.this, "Não é possivel visualizar Localização: "+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(OsActivity.this, "Não é possivel visualizar Localização deste equipamento, favor pedir para verificar no cadastro do Contrato", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(OsActivity.this, "Não é possivel visualizar Localização deste equipamento, favor pedir para verificar no cadastro do Contrato", Toast.LENGTH_SHORT).show();
        }
        if ((os.getLatitudein() != null) && (os.getLongitudein() != null)) {
            if (!(os.getLatitudein().equals(0.0)) && !(os.getLongitudein().equals(0.0))) {
                try {
                    sLocalizain = "Localização Check-IN: "+os.getNomecli();
                    LatLng mapaCheckin = new LatLng(os.getLatitudein(), os.getLongitudein());
                    googleMap.addMarker(new MarkerOptions().position(mapaCheckin).title(sLocalizain).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                } catch (Throwable t) {
                    Toast.makeText(OsActivity.this, "Não é possivel visualizar Localização: "+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    os.setLatitudein(os.getLatitude());
                    os.setLongitudein(os.getLongitude());
                    osDAO.gravaOs(os);
                }
            }
            else {
                if (os.getCheckinok().equals(1)) {
                    os.setLatitudein(os.getLatitude());
                    os.setLongitudein(os.getLongitude());
                    osDAO.gravaOs(os);
                }
            }
        }
        else {
            if (os.getCheckinok().equals(1)) {
                os.setLatitudein(os.getLatitude());
                os.setLongitudein(os.getLongitude());
                osDAO.gravaOs(os);
            }
        }
        if ((os.getLatitudeout() != null) && (os.getLongitudeout() != null)) {
            if (!(os.getLatitudeout().equals(0.0)) && !(os.getLongitudeout().equals(0.0))) {
                try {
                    sLocalizaout = "Localização Check-OUT: "+os.getNomecli();
                    LatLng mapaCheckout = new LatLng(os.getLatitudeout(), os.getLongitudeout());
                    googleMap.addMarker(new MarkerOptions().position(mapaCheckout).title(sLocalizaout).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                } catch (Throwable t) {
                    Toast.makeText(OsActivity.this, "Não é possivel visualizar Localização: " + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    os.setLatitudeout(os.getLatitude());
                    os.setLongitudeout(os.getLongitude());
                    osDAO.gravaOs(os);
                }
            } else {
                if (os.getCheckoutok().equals(1)) {
                    os.setLatitudeout(os.getLatitude());
                    os.setLongitudeout(os.getLongitude());
                    osDAO.gravaOs(os);
                }
            }
        }
        else {
            if (os.getCheckoutok().equals(1)) {
                os.setLatitudeout(os.getLatitude());
                os.setLongitudeout(os.getLongitude());
                osDAO.gravaOs(os);
            }
        }
        Localizador.getInstance(this, localizacaoatual).requestLocation();
    }



    private void sincronizarHistorico() {
        SincHistoricoAsyncTask task = new SincHistoricoAsyncTask();
        task.execute();
    }


    @Override
    public void onOsDefeitoSelecionado(OsDefeito osDefeito) {

    }

    @Override
    public OsDefeito onOsDefeitoSelecionado() {
        return null;
    }

    @Override
    public void onPendenteSelecionado(Pendente pendente) {

    }

    @Override
    public Pendente onPendenteSelecionado() {
        return null;
    }

    @Override
    public void onTrocadaSelecionado(Trocada trocada) {

    }

    @Override
    public Trocada onTrocadaSelecionado() {
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
    public void onLogSelecionado(Logsinc logsinc) {

    }

    @Override
    public Logsinc onLogSelecionado() {
        return null;
    }

    @Override
    public void onHistoricoSelecionado(Historico historico) {

    }

    @Override
    public Historico onHistoricoSelecionado() {
        return null;
    }

    @Override
    public void onHistoricoTISelecionado(HistoricoTI historicoTI) {

    }

    @Override
    public HistoricoTI onHistoricoTISelecionado() {
        return null;
    }

    @Override
    public void onItemSelecionado(Item item) {

    }

    @Override
    public Item onItemSelecionado() {
        return null;
    }

    @Override
    public void onSerialSelecionado(Serial serial) {

    }

    @Override
    public Serial onSerialSelecionado() {
        return null;
    }

    @Override
    public void onHistoricoTELSelecionado(HistoricoTEL historicoTEL) {

    }

    @Override
    public HistoricoTEL onHistoricoTELSelecionado() {
        return null;
    }

    class SincHistoricoAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Integer iRegHistorico;
        private boolean bErro = false;
        private String sMensagem = "";

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(OsActivity.this, null, "Sincronizando Historico");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Defeitos
            iRegHistorico = 0;
            sMensagem = "";
            try {
                if (bOnline) {
                    HistoricoDAO historicoDAO = new HistoricoDAO();
                    HistoricoWSClient wsClient = new HistoricoWSClient();
                    wsClient.os = os;
                    List<Historico> listHistorico = wsClient.buscaHistorico();
                    for (Historico historico : listHistorico) {
                        historicoDAO.createOrUpdate(historico);
                    }
                    iRegHistorico = listHistorico.size();
                }
                else {
                    bErro = true;
                    sMensagem = "Para realizar esta Operação, o dispositivo tem estar ON-LINE !";
                }
            } catch (Throwable t) {
                bErro = true;
                if (sMensagem.equals("")) {
                    sMensagem = t.getMessage().toString();
                }
                sMensagem=t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                Toast.makeText(OsActivity.this, iRegHistorico + " Historicos Sincronizados!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OsActivity.this, "Erro - Histórico,"+sMensagem, Toast.LENGTH_SHORT).show();
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    private void sincronizarOsDefeito() {
        SincOsDefeitoAsyncTask task = new SincOsDefeitoAsyncTask();
        task.execute();
    }

    class SincOsDefeitoAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Integer iRegDefeito;
        private boolean bErro = false;
        private String sMensagem = "";

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(OsActivity.this, null, "Sincronizando Defeitos");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Defeitos
            iRegDefeito = 0;
            sMensagem = "";
            try {
                if (bOnline) {
                    OsDefeitoDAO defeitoDAO = new OsDefeitoDAO();
                    List<OsDefeito> listOsDefeitoant = defeitoDAO.findAll();
                    for (OsDefeito osDefeito : listOsDefeitoant) {
                        defeitoDAO.delete(osDefeito);
                    }
                    OsDefeitoWSClient wsClient = new OsDefeitoWSClient();
                    wsClient.os = os;
                    List<OsDefeito> listOsDefeito = wsClient.buscaOSDefeito();
                    for (OsDefeito osDefeito : listOsDefeito) {
                        defeitoDAO.createOrUpdate(osDefeito);
                    }
                    iRegDefeito = listOsDefeito.size();
                }
                else {
                    bErro = true;
                    sMensagem = "Para realizar esta Operação, o dispositivo tem estar ON-LINE !";
                }
            } catch (Throwable t) {
                bErro = true;
                if (sMensagem.equals("")) {
                    sMensagem = t.getMessage().toString();
                }
                sMensagem=t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                Toast.makeText(OsActivity.this, iRegDefeito + " Defeitos Sincronizados!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OsActivity.this, "Erro - Defeito,"+sMensagem, Toast.LENGTH_SHORT).show();
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }


    private void sincronizarPendente() {
        SincPendenteAsyncTask task = new SincPendenteAsyncTask();
        task.execute();
    }

    class SincPendenteAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Integer iRegPendente;
        private boolean bErro = false;
        private String sMensagem = "";

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(OsActivity.this, null, "Sincronizando Peças Pendentes");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Defeitos
            iRegPendente = 0;
            sMensagem = "";
            try {
                if (bOnline) {
                    PendenteDAO pendenteDAO = new PendenteDAO();
                    PendenteWSClient wsClient = new PendenteWSClient();
                    wsClient.os = os;
                    List<Pendente> listPendente = wsClient.buscaPendente();
                    for (Pendente pendente : listPendente) {
                        pendenteDAO.createOrUpdate(pendente);
                    }
                    iRegPendente = listPendente.size();
                }
                else {
                    bErro = true;
                    sMensagem = "Para realizar esta Operação, o dispositivo tem estar ON-LINE !";
                }
            } catch (Throwable t) {
                bErro = true;
                if (sMensagem.equals("")) {
                    sMensagem = t.getMessage().toString();
                }
                sMensagem=t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                Toast.makeText(OsActivity.this, iRegPendente + " Peças Pendentes Sincronizadas!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OsActivity.this, "Erro - Pendente, "+sMensagem, Toast.LENGTH_SHORT).show();
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    private void sincronizarTrocada() {
        SincTrocadaAsyncTask task = new SincTrocadaAsyncTask();
        task.execute();
    }

    class SincTrocadaAsyncTask extends AsyncTask<Void, Void,Void> {

            private ProgressDialog progressDialog;
            private Integer iRegTrocada;
            private boolean bErro = false;
            private String sMensagem = "";

            @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(OsActivity.this, null, "Sincronizando Peças Trocadas");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Defeitos
            iRegTrocada = 0;
            try {
                if (bOnline) {
                    TrocadaDAO trocadaDAO = new TrocadaDAO();
                    TrocadaWSClient wsClient = new TrocadaWSClient();
                    wsClient.os = os;
                    List<Trocada> listTrocada = wsClient.buscaTrocada();
                    for (Trocada trocada : listTrocada) {
                        trocadaDAO.createOrUpdate(trocada);
                    }
                    iRegTrocada = listTrocada.size();
                }
                else {
                    bErro = true;
                    sMensagem = "Para realizar esta Operação, o dispositivo tem estar ON-LINE !";
                }
            } catch (Throwable t) {
                bErro = true;
                if (sMensagem.equals("")) {
                    sMensagem = t.getMessage().toString();
                }
                sMensagem=t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                Toast.makeText(OsActivity.this, iRegTrocada + " Peças Trocadas Sincronizadas!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OsActivity.this, "Erro - Trocada,"+sMensagem, Toast.LENGTH_SHORT).show();
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    private void checkIn (final Os osin) {
        ConfiguracaoDAO configuracaoDAO = new ConfiguracaoDAO();
        Configuracao configuracao = configuracaoDAO.procuraConfiguracao("id = 1");
        OsDAO osDAO = new OsDAO();
        Os ospesq = osDAO.procuraOs("status_check = 3 and codigo <> '" + osin.getCodigo().toString() + "'");
        //if ((ospesq == null) || (configuracao.getMaischeckin().equals("S"))) {
        if (ospesq == null) {
            if (osin.getStatus_check() <= 2) {
                final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Confirmação");
                alertDialog.setMessage("Deseja iniciar o Atendimento desta OS ?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Sim",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    if (Confighora() == 1) {
                                        Toast.makeText(OsActivity.this, "Buscando Localização", Toast.LENGTH_SHORT).show();
                                        verificaPermissaoLocalizacao();
                                        localizacaoatual =  buscaLocalizacao(localizacaoatual);
                                        Thread.sleep(1000);
                                        Date data = new Date();
                                        OsDAO osDAO = new OsDAO();
                                        osin.setCheckinok(1);
                                        osin.setCheckin(data);
                                        osin.setStatus_check(3);
                                        osin.setDatain(data);
                                        if ((osin.getLatitudein().equals(0.0)) && (osin.getLongitudein().equals(0.0))) {
                                            if (localizacaoatual.getEnd() != null) {
                                                osin.setEndin(localizacaoatual.getEnd().toString());
                                            }
                                            if (localizacaoatual.getNum() != null) {
                                                osin.setNumin(localizacaoatual.getNum());
                                            }
                                            if (localizacaoatual.getBairro() != null) {
                                                osin.setBairroin(localizacaoatual.getBairro().toString());
                                            }
                                            if (localizacaoatual.getCidade() != null) {
                                                osin.setCidadein(localizacaoatual.getCidade().toString());
                                            }
                                            if (localizacaoatual.getEstado() != null) {
                                                osin.setEstadoin(localizacaoatual.getEstado().toString());
                                            }
                                            if (localizacaoatual.getCep() != null) {
                                                osin.setCepin(localizacaoatual.getCep().toString());
                                            }
                                            if (localizacaoatual.getNumlocal() != null) {
                                                osin.setNumlocalin(localizacaoatual.getNumlocal().toString());
                                            }
                                            osin.setLatitudein(localizacaoatual.getLatitude());
                                            osin.setLongitudein(localizacaoatual.getLongitude());
                                        }
                                        osDAO.gravaOs(osin);
                                        if (bOnline) {
                                            sincronizarCheckin();
                                        }
                                        else {
                                           fragmentTransaction_os = mFragmentManager.beginTransaction();
                                           fragmentTransaction_os.detach(osFragment);
                                           fragmentTransaction_os.attach(osFragment);
                                           fragmentTransaction_os.commit();
                                        }
                                    }
                                    else {
                                        Toast.makeText(OsActivity.this, "Não foi possivel realizar esta operação, verifique se a HORA do dispositivo está automaticamente com o horário da rede, reiniciar o aplicativo, e tentar novamente !", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Settings.ACTION_DATE_SETTINGS));
                                    }
                                } catch (Throwable t) {
                                    Toast.makeText(OsActivity.this, "Não foi possivel realizar esta operação: "+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
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
                switch (osin.getStatus_check()) {
                    case 3: {
                        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                        alertDialog.setTitle("Confirmação");
                        alertDialog.setMessage("Esta já foi inicializada, deseja desfazer a operação ?");
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Sim",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        sincronizarDesfazer();
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


                        break;
                    }
                    case 4: {
                        Toast.makeText(OsActivity.this, "Já foi finalizada a intervenção nesta OS !", Toast.LENGTH_SHORT).show();
                        break;
                    }

                }
            }

        } else {
            Toast.makeText(OsActivity.this, "Já foi iniciado o atendimento na OS :" + ospesq.getCodigo().toString(), Toast.LENGTH_SHORT).show();
        }

    }

    private void sincronizarCheckin() {
        SincCheckinAsyncTask task = new SincCheckinAsyncTask();
        task.execute();
    }

    class SincCheckinAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private boolean bErro = false;
        private String sResult = "";

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(OsActivity.this, null, "Sincronizando Check-In");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            sResult="";
            try {
                if (bOnline) {
                    OsWSClient osWSClient = new OsWSClient();
                    sResult = osWSClient.enviaOS(os);
                    bErro = !(sResult.equals("OK"));
                }
                else {
                    bErro = true;
                    sResult = "Para realizar esta Operação, o dispositivo tem estar ON-LINE !";
                }
                fragmentTransaction_os = mFragmentManager.beginTransaction();
                fragmentTransaction_os.detach(osFragment);
                fragmentTransaction_os.attach(osFragment);
                fragmentTransaction_os.commit();
            } catch (Throwable t) {
                bErro = true;
                if (sResult.equals("")) {
                    sResult = t.getMessage().toString();
                }
                sResult=t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                Toast.makeText(OsActivity.this, "Sincronização realizada com Sucesso !", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OsActivity.this, "Erro: "+sResult, Toast.LENGTH_SHORT).show();
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }


    private void sincronizarStatus() {
        SincStatusAsyncTask task = new SincStatusAsyncTask();
        task.execute();
    }

    class SincStatusAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private boolean bErro = false;
        private String sResult = "";
        private String sResultado = "OK";

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(OsActivity.this, null, "Consultando Situação da OS");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            sResult="";
            try {
                bOnline = ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext())));
                if (bOnline) {
                    if  (os.getStatus_check() != 4) {
                        OsWSClient osWSClient = new OsWSClient();
                        sResultado = osWSClient.enviaStatusos(os.getCodigo().toString(), os.getBanco().toString(), os.getCodtec().toString());
                    }
                    else {
                        sResultado = "OK";
                    }
                }
                else {
                    bErro = true;
                    sResult = "Para realizar esta Operação, o dispositivo tem estar ON-LINE !";
                }
            } catch (Throwable t) {
                bErro = true;
                if (sResult.equals("")) {
                    sResult = t.getMessage().toString();
                }
                sResult=t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                if (!(sResultado.equals("OK"))) {
                    final OsDAO osDAO = new OsDAO();
                    final AlertDialog alertDialog = new AlertDialog.Builder(OsActivity.this).create();
                    alertDialog.setTitle("Alerta");
                    alertDialog.setMessage("Atenção, não é possivel a visualização desta OS: " + sResultado);
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    osDAO.excluiOs(os);
                                    finish();
                                }
                            });
                    try {
                        alertDialog.show();
                    } catch (Throwable t) {
                    }

                }
                else {
                    Toast.makeText(OsActivity.this, "Ordem de Serviço OK !", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(OsActivity.this, "Erro: "+sResult, Toast.LENGTH_SHORT).show();
            }

            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }


    private void sincronizarDesfazer() {
        SincDesfazerAsyncTask task = new SincDesfazerAsyncTask();
        task.execute();
    }

    class SincDesfazerAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private boolean bErro = false;
        private String sResult = "";

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(OsActivity.this, null, "Desfazendo Check-In");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            sResult="";
            try {
                bOnline = ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext())));
                if (bOnline) {
                    OsWSClient osWSClient = new OsWSClient();
                    sResult = osWSClient.enviaDesfazer(os);
                    bErro = !(sResult.equals("OK"));
                    if (bErro == false) {
                        Date data = new Date();
                        OsDAO osDAO = new OsDAO();
                        os.setCheckin(null);
                        os.setCheckinok(0);
                        os.setLatitudein(0.0);
                        os.setLongitudein(0.0);
                        if (os.getPrevisao().before(data)) {
                            os.setStatus_check(2);
                        }
                        else {
                            os.setStatus_check(1);
                        }
                        osDAO.gravaOs(os);
                        fragmentTransaction_os = mFragmentManager.beginTransaction();
                        fragmentTransaction_os.detach(osFragment);
                        fragmentTransaction_os.attach(osFragment);
                        fragmentTransaction_os.commit();
                    }
                }
                else {
                    bErro = true;
                    sResult = "Para realizar esta Operação, o dispositivo tem estar ON-LINE !";
                }
            } catch (Throwable t) {
                bErro = true;
                if (sResult.equals("")) {
                    sResult = t.getMessage().toString();
                }
                sResult=t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                Toast.makeText(OsActivity.this, "Sincronização realizada com Sucesso !", Toast.LENGTH_SHORT).show();
              }
            else {
                Toast.makeText(OsActivity.this, "Erro: "+sResult, Toast.LENGTH_SHORT).show();
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    private void sincronizarOs() {
        SincOsAsyncTask task = new SincOsAsyncTask();
        task.execute();
    }

    class SincOsAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private boolean bErro = false;
        private String sResult = "";

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(OsActivity.this, null, "Sincronizando OS...");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            sResult="";
            try {
                if (bOnline) {
                    OsWSClient osWSClient = new OsWSClient();
                    OsDAO osDAO = new OsDAO();
                    sResult = osWSClient.enviaOS(os);
                    Thread.sleep(1000);
                    bErro = !(sResult.equals("OK"));
                    if (!bErro) {
                       if (os.getCheckoutok().equals(1)) {
                           os.setSync("S");
                           osDAO.gravaOs(os);
                       }
                    }
                }
                else {
                    bErro = true;
                    sResult = "Para realizar esta Operação, o dispositivo tem estar ON-LINE !";
                }
            } catch (Throwable t) {
                bErro = true;
                if (sResult.equals("")) {
                    sResult = t.getMessage().toString();
                }
                sResult=t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                Toast.makeText(OsActivity.this, "Sincronização realizada com Sucesso !", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OsActivity.this, "Erro: "+sResult, Toast.LENGTH_SHORT).show();
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    private void sincronizarCancelamento() {
        SincCancelamentoAsyncTask task = new SincCancelamentoAsyncTask();
        task.execute();
    }

    class SincCancelamentoAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private boolean bErro = false;
        private String sResult = "";

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(OsActivity.this, null, "Sincronizando Cancelamento...");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            sResult="";
            try {
                if (bOnline) {
                    OsDAO osDAO = new OsDAO();
                    OsWSClient osWSClient = new OsWSClient();
                    sResult = osWSClient.enviaCancelamento(os);
                    bErro = !(sResult.equals("OK"));
                    if (bErro == false) {
                        osDAO.excluiOs(os);
                        finish();
                    }
                }
                else {
                    bErro = true;
                    sResult = "Para realizar esta Operação, o dispositivo tem estar ON-LINE !";
                }
            } catch (Throwable t) {
                bErro = true;
                if (sResult.equals("")) {
                    sResult = t.getMessage().toString();
                }
                sResult=t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                Toast.makeText(OsActivity.this, "Sincronização realizada com Sucesso !", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OsActivity.this, "Erro: "+sResult, Toast.LENGTH_SHORT).show();
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }


    private void sincronizarOrdem() {
        SincOrdemAsyncTask task = new SincOrdemAsyncTask();
        task.execute();
    }

    class SincOrdemAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private boolean bErro = false;
        private String sResult = "";

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(OsActivity.this, null, "Sincronizando Ordem...");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            sResult="";
            try {
                if (bOnline) {
                    OsDAO osDAO = new OsDAO();
                    OsWSClient osWSClient = new OsWSClient();
                    sResult = osWSClient.alterarOrdem(os);
                    bErro = !(sResult.equals("OK"));
                    if (bErro == false) {
                        os.setOrdem(os.getOrdemtec());
                        osDAO.gravaOs(os);
                        finish();
                    }
                }
                else {
                    bErro = true;
                    sResult = "Para realizar esta Operação, o dispositivo tem estar ON-LINE !";
                }
            } catch (Throwable t) {
                bErro = true;
                if (sResult.equals("")) {
                    sResult = t.getMessage().toString();
                }
                sResult=t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                Toast.makeText(OsActivity.this, "Sincronização realizada com Sucesso !", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OsActivity.this, "Erro: "+sResult, Toast.LENGTH_SHORT).show();
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }


    private void sincronizarEmail() {
        SincEmailAsyncTask task = new SincEmailAsyncTask();
        task.execute();
    }

    class SincEmailAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private boolean bErro = false;
        private String sResult = "";

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(OsActivity.this, null, "Sincronizando Email...");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            sResult="";
            try {
                if (bOnline) {
                    OsDAO osDAO = new OsDAO();
                    OsWSClient osWSClient = new OsWSClient();
                    sResult = osWSClient.enviarEmail(os);
                    bErro = !(sResult.equals("OK"));
                    if (bErro == false) {
                        finish();
                    }
                }
                else {
                    bErro = true;
                    sResult = "Para realizar esta Operação, o dispositivo tem estar ON-LINE !";
                }
            } catch (Throwable t) {
                bErro = true;
                if (sResult.equals("")) {
                    sResult = t.getMessage().toString();
                }
                sResult=t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                Toast.makeText(OsActivity.this, "Operação realizada com Sucesso !", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OsActivity.this, "Erro: "+sResult, Toast.LENGTH_SHORT).show();
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    private void sincronizarItem() {
        SincItemAsyncTask task = new SincItemAsyncTask();
        task.execute();
    }

    class SincItemAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Integer iRegItem;
        private boolean bErro = false;
        private String sMensagem = "";

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(OsActivity.this, null, "Sincronizando Itens");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            iRegItem = 0;
            try {
                if (bOnline) {
                    ItemDAO itemDAO = new ItemDAO();
                    ItemWSClient wsClient = new ItemWSClient();
                    wsClient.os = os;
                    List<Item> listItem = wsClient.buscaItem();
                    for (Item item : listItem) {
                        itemDAO.createOrUpdate(item);
                    }
                    iRegItem = listItem.size();
                }
                else {
                    bErro = true;
                    sMensagem = "Para realizar esta Operação, o dispositivo tem estar ON-LINE !";
                }
            } catch (Throwable t) {
                bErro = true;
                if (sMensagem.equals("")) {
                    sMensagem = t.getMessage().toString();
                }
                sMensagem=t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                Toast.makeText(OsActivity.this, iRegItem + " Itens sincronizados!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OsActivity.this, "Erro - Item,"+sMensagem, Toast.LENGTH_SHORT).show();
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }


    private void sincronizarSerial() {
        SincSerialAsyncTask task = new SincSerialAsyncTask();
        task.execute();
    }

    class SincSerialAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Integer iRegSerial;
        private boolean bErro = false;
        private String sMensagem = "";

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(OsActivity.this, null, "Sincronizando Seriais");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            iRegSerial = 0;
            try {
                if (bOnline) {
                    SerialDAO serialDAO = new SerialDAO();
                    SerialWSClient wsClient = new SerialWSClient();
                    wsClient.os = os;
                    List<Serial> listSerial = wsClient.buscaSerial();
                    for (Serial serial : listSerial) {
                        serialDAO.createOrUpdate(serial);
                    }
                    iRegSerial = listSerial.size();
                }
                else {
                    bErro = true;
                    sMensagem = "Para realizar esta Operação, o dispositivo tem estar ON-LINE !";
                }
            } catch (Throwable t) {
                bErro = true;
                if (sMensagem.equals("")) {
                    sMensagem = t.getMessage().toString();
                }
                sMensagem=t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                Toast.makeText(OsActivity.this, iRegSerial + " Seriais sincronizados!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OsActivity.this, "Erro - Serial,"+sMensagem, Toast.LENGTH_SHORT).show();
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }


    private void sincronizarHistoricoTEL() {
        SincHistoricoTELAsyncTask task = new SincHistoricoTELAsyncTask();
        task.execute();
    }

    class SincHistoricoTELAsyncTask extends AsyncTask<Void, Void,Void> {
        private SelecionaOs mListener;


        private ProgressDialog progressDialog;
        private Integer iRegHistoricoTEL;
        private boolean bErro = false;
        private String sMensagem = "";

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(OsActivity.this, null, "Sincronizando Histórico");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            iRegHistoricoTEL = 0;
            try {
                if (bOnline) {
                    HistoricoTELDAO historicoTELDAO = new HistoricoTELDAO();
                    HistoricoTELWSClient wsClient = new HistoricoTELWSClient();
                    wsClient.os = os;
                    List<HistoricoTEL> listHistoricoTEL = wsClient.buscaHistoricoTEL();
                    for (HistoricoTEL historicoTEL : listHistoricoTEL) {
                        historicoTELDAO.createOrUpdate(historicoTEL);
                    }
                    iRegHistoricoTEL = listHistoricoTEL.size();
                }
                else {
                    bErro = true;
                    sMensagem = "Para realizar esta Operação, o dispositivo tem estar ON-LINE !";
                }
            } catch (Throwable t) {
                bErro = true;
                if (sMensagem.equals("")) {
                    sMensagem = t.getMessage().toString();
                }
                sMensagem=t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                Toast.makeText(OsActivity.this, iRegHistoricoTEL + " Históricos sincronizados!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OsActivity.this, "Erro - Historico,"+sMensagem, Toast.LENGTH_SHORT).show();
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
