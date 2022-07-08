package databit.com.br.datamobile.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.navigation.NavigationView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.adapter.AdapterOs;
import databit.com.br.datamobile.dao.AparelhoDAO;
import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.dao.PontoDAO;
import databit.com.br.datamobile.dao.UsuarioDAO;
import databit.com.br.datamobile.domain.Aparelho;
import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.domain.Localizacao;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.domain.Ponto;
import databit.com.br.datamobile.domain.Usuario;
import databit.com.br.datamobile.infra.Internet;
import databit.com.br.datamobile.location.Localizador;
import databit.com.br.datamobile.ponto.ControlePonto;
import databit.com.br.datamobile.service.PontoService;
import databit.com.br.datamobile.service.SincronizacaoDatabit;
import databit.com.br.datamobile.wsclient.ConfiguracaoWSClient;
import databit.com.br.datamobile.wsclient.OsWSClient;

import static databit.com.br.datamobile.badger.Badger.setBadge;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    List<Os> listMaps = new ArrayList<>();
    private Double latitudeatual;
    private Double longitudeatual;
    private String slocal;
    private Boolean bOnline;
    private TextView txtStatus;
    private Localizacao localizacaoatual;
    private Usuario usuario;
    private Date dInicio;
    private GoogleApiClient mGoogleApiClient;
    private TextView txtFiltro;
    private Spinner lcbFiltro;
    private TextView txtTermo;
    private EditText edtTermo;
    private CheckBox chFiltro;
    private Integer iPosicaofiltro;
    private LinearLayout lnFiltro2;
    private LinearLayout lnFiltro3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_dataservice);

        setSupportActionBar(toolbar);

        dInicio = new Date();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
        Date d= new Date();
        String strDate = sdf.format(d);
        localizacaoatual = new Localizacao();
        Toast.makeText(MainActivity.this, "Buscando Localização", Toast.LENGTH_SHORT).show();
        verificaPermissaoLocalizacao();


        recyclerView = (RecyclerView) findViewById(R.id.os_recycler_view);


        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        String nomeusuario = getIntent().getStringExtra("nomeusuario");
        String email = getIntent().getStringExtra("email");
        String empresa = getIntent().getStringExtra("empresa");
        latitudeatual = getIntent().getDoubleExtra("latitudeatual",0.00);
        longitudeatual = getIntent().getDoubleExtra("longitudeatual",0.00);
        slocal = getIntent().getStringExtra("endereco");
        Boolean bSincauto = getIntent().getBooleanExtra("sincauto", false);
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        View header = navigationView.getHeaderView(0);

        TextView textViewUsuario = (TextView) header.findViewById(R.id.txtUsuario);
        TextView textViewEmail = (TextView) header.findViewById(R.id.txtEmail);
        TextView textViewEmpresa = (TextView) header.findViewById(R.id.txtEmpresa);
        txtStatus = (TextView) header.findViewById(R.id.txtStatus);

        textViewUsuario.setText(nomeusuario);
        textViewEmail.setText(email);
        textViewEmpresa.setText(empresa);

        txtFiltro = (TextView) findViewById(R.id.txtFiltro);
        lcbFiltro = (Spinner) findViewById(R.id.lcbFiltro);
        lcbFiltro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iPosicaofiltro = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                iPosicaofiltro=0;
            }
        });

        txtTermo = (TextView) findViewById(R.id.txtTermo);
        edtTermo = (EditText) findViewById(R.id.edtTermo);
        chFiltro = (CheckBox) findViewById(R.id.checkFiltro);
        lnFiltro2 = (LinearLayout) findViewById(R.id.lnFiltro2);
        lnFiltro3 = (LinearLayout) findViewById(R.id.lnFiltro3);
        chFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chFiltro.isChecked()) {
                    txtFiltro.setVisibility(View.VISIBLE);
                    lcbFiltro.setVisibility(View.VISIBLE);
                    txtTermo.setVisibility(View.VISIBLE);
                    edtTermo.setVisibility(View.VISIBLE);
                    lnFiltro2.setVisibility(View.VISIBLE);
                    lnFiltro3.setVisibility(View.VISIBLE);
                }
                else {
                    txtFiltro.setVisibility(View.GONE);
                    lcbFiltro.setVisibility(View.GONE);
                    txtTermo.setVisibility(View.GONE);
                    edtTermo.setVisibility(View.GONE);
                    lnFiltro2.setVisibility(View.GONE);
                    lnFiltro3.setVisibility(View.GONE);
                }
            }
        });

        sincronizarConfig();
        if (bSincauto) {
           sincronizarOs();
        }

        filtrar();
        try {
            txtStatus.setText(ControlePonto.StatusPonto());
        } catch (ParseException e) {
            e.printStackTrace();
        }



    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        ConfiguracaoDAO configuracaoDAO = new ConfiguracaoDAO();
        Configuracao configuracao = configuracaoDAO.procuraConfiguracao("id = 1");
        int id = item.getItemId();
        if (id == R.id.nav_config) {
            Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_sincr) {
            Intent intent = new Intent(MainActivity.this, SincronizaActivity.class);
            intent.putExtra("novo",false);
            intent.putExtra("somenteos",false);
            intent.putExtra("somenteponto",false);
            startActivity(intent);
            try {
                txtStatus.setText(ControlePonto.StatusPonto());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            filtrar();
        } else if (id == R.id.nav_sincros) {
            Intent intent = new Intent(MainActivity.this, SincronizaActivity.class);
            intent.putExtra("novo",false);
            intent.putExtra("somenteos",true);
            intent.putExtra("somenteponto",false);
            startActivity(intent);
            try {
                txtStatus.setText(ControlePonto.StatusPonto());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            filtrar();
        } else if (id == R.id.nav_sincpontoenv) {
            Intent intent = new Intent(MainActivity.this, SincronizaActivity.class);
            intent.putExtra("novo",false);
            intent.putExtra("somenteos",false);
            intent.putExtra("somenteponto",true);
            startActivity(intent);
            try {
                txtStatus.setText(ControlePonto.StatusPonto());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            filtrar();
        } else if (id == R.id.nav_user) {
            Intent intent = new Intent(MainActivity.this, InforActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_produto) {
            Intent intent = new Intent(MainActivity.this, ProdutoActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_servico) {
            Intent intent = new Intent(MainActivity.this, ServicoActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_rota) {
            String sLink = "https://www.google.com.br/maps/dir/";
            if ((latitudeatual != 0l) && (longitudeatual != 0l)) {
                sLink = sLink + latitudeatual + "," + longitudeatual + "/";
            }
            for (Os os : listMaps) {
                if ((os.getLatitude() != 0l) && (os.getLongitude() != 0l)) {
                    sLink = sLink + os.getLatitude().toString() + "," + os.getLongitude().toString() + "/";
                }
                else {
                    Toast.makeText(MainActivity.this, "Não foi possivel encontrar a localização da Ordem de Serviço: "+os.getCodigo(), Toast.LENGTH_SHORT).show();
                }
            }
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(sLink));
            startActivity(intent);
        } else if (id == R.id.nav_ponto) {
            if (configuracao.getPonto().equals("S")) {
                Date datahora = new Date();
                String sRetorno = "";
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                String sData = formato.format(datahora);
                List<Ponto> lista = new ArrayList<>();
                AparelhoDAO aparelhoDAO = new AparelhoDAO();
                Aparelho aparelho = aparelhoDAO.procuraAparelho("id = 1");
                PontoDAO pontoDAO = new PontoDAO();
                lista = pontoDAO.listarPonto("sdata = '"+sData+"' and usuario = '" + aparelho.getUsuario().toString() + "' ");
                Integer iTipo = lista.size();
                switch (iTipo) {
                    case 0: {  // Inicio Jornada
                        sRetorno = "iniciar o dia de trabalho ?";
                        break;
                    }
                    case 1: {  // Inicio Jornada
                        sRetorno = "entrar em intervalo ?";
                        break;
                    }
                    case 2: { // Inicio Intervalo
                        sRetorno = "voltar do intervalo ?";
                        break;
                    }
                    case 3: { // Finalizar Intervalo
                        sRetorno = "finalizar o dia de trabalho ?";
                        break;
                    }
                    case 4: { // Finalizar Intervalo
                        //Toast.makeText(MainActivity.this, "O dia de expediente foi encerrado !", Toast.LENGTH_LONG).show();
                        registrarPonto();
                        break;
                    }

                }
                if (iTipo < 4) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                    alertDialog.setTitle("Controle de Ponto");
                    alertDialog.setMessage("Deseja "+sRetorno);
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Sim",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dInicio = new Date();
                                    registrarPonto();
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
            }
            else {
                Toast.makeText(MainActivity.this, "O Sistema não esta configurado para trabalhar com CONTROLE DE PONTO !", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_senha) {
            Intent intent = new Intent(MainActivity.this, SenhaActivity.class);
            intent.putExtra("usuario", usuario);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_sair) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.op_filtrar: {
                filtrar();
                break;
            }
            case R.id.op_sync: {
                dInicio = new Date();
                sincronizarOs();
                filtrar();
                break;
            }
            case R.id.op_qrcode: {
                scanCode();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        filtrar();
        super.onRestart();
    }

    private void sincronizarOs() {
        SincOsAsyncTask task = new SincOsAsyncTask();
        task.execute();
    }
    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Aponte a câmera para o QR Code");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureActivity.class);
        barLauncher.launch(options);

    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result ->{
        if(result.getContents() !=null) {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Resultado");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }
    });

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mGoogleApiClient.connect();
    }

    public boolean isGpsAvaiable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER));
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i("LOCATION", "onConnected");
        final Boolean bSemlocalidade=!(isGpsAvaiable(this));
        final Boolean bSemhoraauto=(Confighora() == 0);
        Integer iTenta = 0;
        String sRetorno = null;
        Boolean bErro = false;
        if (bSemlocalidade ==  false) {
            if (bSemhoraauto == false) {
                Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                while ((mLastLocation.equals(null)) && (iTenta < 3)) {
                    mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                    iTenta =  iTenta + 1;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (mLastLocation != null) {
                    Log.i("LOCATION", "Lat: " + mLastLocation.getLatitude() + " | Lng: " + mLastLocation.getLongitude());
                    Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                    Localizacao localizacao = new Localizacao();
                    try {
                        localizacao.setLatitude(mLastLocation.getLatitude());
                        localizacao.setLongitude(mLastLocation.getLongitude());
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
                            if (descricao != null) {
                                System.out.println(descricao);
                                localizacao.setLogradouro(descricao.toString());
                            }
                            mGoogleApiClient.disconnect();
                            Gravarponto(localizacao);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        bErro = true;
                        sRetorno = "Não foi possível buscar a localidade : "+e.getMessage();
                    }
                }
                else {
                    bErro = true;
                    sRetorno = "Não foi possível buscar a localidade do dispositivo pelo GPS, verifique se o seu GPS está ativo, e se o mesmo está em modo de ALTA PRECISÂO, reiniciar o aplicativo, e tentar novamente !";
                }
            }
            else {
                bErro = true;
                sRetorno = "Não foi possível realizar o PONTO, verifique se a HORA do dispositivo está automaticamente com o horário da rede, reiniciar o aplicativo, e tentar novamente !";
            }
        }
        else {
            bErro = true;
            sRetorno = "Não foi possível buscar a localidade do dispositivo pelo GPS, verifique se o seu GPS está ativo, e se o mesmo está em modo de ALTA PRECISÂO, reiniciar o aplicativo, e tentar novamente !";
        }
        if (bErro == true) {
            final AlertDialog alertDialog2 = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog2.setTitle("Controle de Ponto");
            alertDialog2.setMessage("Não foi possível realizar a operação: "+sRetorno);
            alertDialog2.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (bSemlocalidade == true) {
                                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                            }
                            if (bSemhoraauto == true) {
                                startActivity(new Intent(Settings.ACTION_DATE_SETTINGS));
                            }
                        }
                    });
            try {
                alertDialog2.show();
            } catch (Throwable t) {
            }
        }

    }

    public void Gravarponto (Localizacao localizacao) {
        localizacaoatual = new Localizacao();
        localizacaoatual = localizacao;
        SincPonto();
    }


    private void SincPonto() {
        SincPontoAsyncTask task = new SincPontoAsyncTask();
        task.execute();
    }

    class SincPontoAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Boolean bErro = false;
        private String  sRetorno = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MainActivity.this , null, "Gravando Ponto!");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                bOnline = ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext())));
                //if (bOnline) {
                    sRetorno = ControlePonto.GravarPonto(localizacaoatual.getLatitude(), localizacaoatual.getLongitude(), localizacaoatual.getLogradouro(), bOnline);
                    bErro = !(sRetorno.equals("OK"));
                //}
            } catch (Throwable t) {
                bErro = true;
                sRetorno = t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                //if (bOnline == false) {
                //    Toast.makeText(MainActivity.this, "Não foi possivel registrar o PONTO, o dispositivo esta sem Internet ou servidor esta OFF-LINE !", Toast.LENGTH_SHORT).show();
                //}
                //else {
                    try {
                        txtStatus.setText(ControlePonto.StatusPonto());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Controle de Ponto");
                    try {
                        Date dataatual = new Date();
                        DateFormat formatodata = new SimpleDateFormat("dd/MM/yyyy");
                        DateFormat formatohora = new SimpleDateFormat("HH:mm:ss");
                        DateFormat formatomin = new SimpleDateFormat("mm:ss");
                        alertDialog.setMessage("Ponto registrado com Sucesso !" + "\n" +
                                "Status Atual: "+ControlePonto.StatusPonto() + "\n" +
                                "Data: "+formatodata.format(dataatual) + "\n" +
                                "Hora: "+formatohora.format(dataatual) + "\n" +
                                //"Localidade: "+localizacaoatual.getLogradouro()+ "\n" +
                                "Tempo de Processamento: "+formatomin.format(dataatual.getTime() - dInicio.getTime())
                        );
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
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

                //}
            }
            else {
                final AlertDialog alertDialog2 = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog2.setTitle("Controle de Ponto");
                alertDialog2.setMessage("Não foi possível realizar a operação: "+sRetorno);
                alertDialog2.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //finish();
                            }
                        });
                try {
                    alertDialog2.show();
                } catch (Throwable t) {
                }
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }


    @Override
    public void onConnectionSuspended(int i) {
        Log.i("LOCATION", "onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("LOCATION", "onConnectionFailed" + connectionResult.getErrorCode());

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    class SincOsAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Integer iRegOs;
        private Boolean bErro = false;
        private String sMensagem = "";
        private long iTempo;
        private String sTempo;
        private Date dFim;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MainActivity.this , null, "Sincronizando OSs...");

        }

        @Override
        protected Void doInBackground(Void... params) {
            iRegOs = 0;
            try {
                if (bOnline) {
                    OsDAO osDAO = new OsDAO();
                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    AparelhoDAO aparelhoDAO = new AparelhoDAO();
                    Aparelho aparelho = aparelhoDAO.procuraAparelho("id = 1");
                    Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");
                    if (usuario == null) {
                        usuario = usuarioDAO.procuraUsuario("login = '"+aparelho.getLogin().toString()+"'");
                    }
                    OsWSClient wsClient = new OsWSClient();
                    wsClient.usuario = usuario;
                    List<Os> listOs = wsClient.buscaOS();
                    List<Os> listOsant = osDAO.findAll();
                    if (listOsant.size() > 0) {
                        for (Os os : listOsant) {
                            if ((os.getStatus_check().equals(1)) || (os.getStatus_check().equals(2))) {
                                osDAO.delete(os);
                            }
                        }
                    }
                    Date dtatual = new Date();
                    if (listOs.size() > 0) {
                        for (Os os : listOs) {
                            Os ospesq = osDAO.procuraOs(" id = '"+os.getId().toString()+"' ");
                            if (ospesq == null) {
                                if (os.getPrevisao().before(dtatual)) {
                                    os.setStatus_check(2);
                                }
                                osDAO.createOrUpdate(os);
                                iRegOs = iRegOs + 1;
                            }
                            else
                            {
                                if ((ospesq.getStatus_check().equals(1)) || (ospesq.getStatus_check().equals(2))) {
                                    if (os.getPrevisao().before(dtatual)) {
                                        os.setStatus_check(2);
                                    }
                                    osDAO.createOrUpdate(os);
                                    iRegOs = iRegOs + 1;
                                }
                            }
                        }
                    }
                    setBadge(MainActivity.this,0);
                    bErro = false;
                    dFim = new Date();
                    iTempo = (dFim.getTime() - dInicio.getTime());
                }
            } catch (Throwable t) {
                bErro = true;
                sMensagem = t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                if (bOnline) {
                    DateFormat formatohora = new SimpleDateFormat("mm:ss");
                    Toast.makeText(MainActivity.this, iRegOs + " OSs Sincronizadas, tempo de processamento: "+formatohora.format(dFim.getTime() - dInicio.getTime()), Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Não foi possivel sincronização de OS, o dispositivo esta sem Internet ou servidor esta OFF-LINE !", Toast.LENGTH_SHORT).show();
                }

            }
            else {
                Toast.makeText(MainActivity.this, "Erro - OS, "+sMensagem, Toast.LENGTH_SHORT).show();
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }


    private void filtrar() {
        SincFiltrarAsyncTask task = new SincFiltrarAsyncTask();
        task.execute();
    }

    class SincFiltrarAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private CheckBox chPendente = (CheckBox) findViewById(R.id.checkPendente);
        private CheckBox chAtraso = (CheckBox) findViewById(R.id.checkAtraso);
        private CheckBox chExecucao = (CheckBox) findViewById(R.id.checkExecucao);
        private CheckBox chConcluido = (CheckBox) findViewById(R.id.checkConcluido);
        private RadioButton rbCrescente = (RadioButton) findViewById(R.id.rbCrescente);

        private Boolean bPendente = chPendente.isChecked();
        private Boolean bAtraso = chAtraso.isChecked();
        private Boolean bExecucao = chExecucao.isChecked();
        private Boolean bConcluido = chConcluido.isChecked();

        List<Os> listos = new ArrayList<>();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MainActivity.this , null, "Filtrando OSs");
        }

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... params) {
            OsDAO osDAO = new OsDAO();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            AparelhoDAO aparelhoDAO = new AparelhoDAO();
            Aparelho aparelho = aparelhoDAO.procuraAparelho("id = 1");
            Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");
            if (usuario == null) {
               usuario = usuarioDAO.procuraUsuario("login = '"+aparelho.getLogin().toString()+"'");
            }

            String sSQL = "usuariodataservice = '"+usuario.getNome().toString()+"'";

            if (bPendente == false) {
                sSQL = sSQL + " and status_check <> 1 ";
            }
            if (bAtraso == false) {
                sSQL = sSQL + " and status_check <> 2 ";
            }
            if (bExecucao == false) {
                sSQL = sSQL + " and status_check <> 3 ";
            }
            if (bConcluido == false) {
                sSQL = sSQL + " and status_check <> 4 ";
            }

            String sFiltro = "";
            try {
                sFiltro = edtTermo.getText().toString();
            } catch (Exception e) { ;
                sFiltro = "";
            }



            if (chFiltro.isChecked() && (!(sFiltro.equals("")))) {
                switch (iPosicaofiltro) {
                    case 0: {
                        sSQL = sSQL + " and codigo = '"+edtTermo.getText().toString().toUpperCase()+"' ";
                        break;
                    }
                    case 1: {
                        sSQL = sSQL + " and numserie like '"+edtTermo.getText().toString().toUpperCase()+"%' ";
                        break;
                    }
                    case 2: {
                        sSQL = sSQL + " and pat like '"+edtTermo.getText().toString().toUpperCase()+"%' ";
                        break;
                    }
                    case 3: {
                        sSQL = sSQL + " and nomeprod like '"+edtTermo.getText().toString().toUpperCase()+"%' ";
                        break;
                    }
                    case 4: {
                        sSQL = sSQL + " and nomecli like '"+edtTermo.getText().toString().toUpperCase()+"%' ";
                        break;
                    }
                    case 5: {
                        sSQL = sSQL + " and CNPJ = '"+edtTermo.getText().toString().toUpperCase()+"' ";
                        break;
                    }
                    case 6: {
                        sSQL = sSQL + " and cidade like '"+edtTermo.getText().toString().toUpperCase()+"%' ";
                        break;
                    }
                    case 7: {
                        sSQL = sSQL + " and bairro like '"+edtTermo.getText().toString().toUpperCase()+"%' ";
                        break;
                    }
                    case 8: {
                        sSQL = sSQL + " and site like '"+edtTermo.getText().toString().toUpperCase()+"%' ";
                        break;
                    }
                    case 9: {
                        sSQL = sSQL + " and departamento like '"+edtTermo.getText().toString().toUpperCase()+"%' ";
                        break;
                    }
                    case 10: {
                        sSQL = sSQL + " and classificacao like '"+edtTermo.getText().toString().toUpperCase()+"%' ";
                        break;
                    }
                    case 11: {
                        sSQL = sSQL + " and local like '"+edtTermo.getText().toString().toUpperCase()+"%' ";
                        break;
                    }
                    case 12: {
                        sSQL = sSQL + " and contrato = '"+edtTermo.getText().toString().toUpperCase()+"' ";
                        break;
                    }
                }
            }

            if (rbCrescente.isChecked() == true) {
                sSQL = sSQL + " order by previsao,ordem";
            }
            else {
                sSQL = sSQL + " order by previsao desc,ordem";
            }

            listos = osDAO.listarOs(sSQL);
            listMaps = listos;

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter = new AdapterOs(listos, recyclerView);
            recyclerView.setAdapter(adapter);
            Toast.makeText(MainActivity.this, listos.size() + " registros encontrados !", Toast.LENGTH_SHORT).show();
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }





    private void registrarPonto() {
        PontoAsyncTask task = new PontoAsyncTask();
        task.execute();
    }

    class PontoAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Boolean bErro = false;
        private String  sRetorno = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MainActivity.this , null, "Buscando Localização Atualizada!");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                bOnline = ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext())));
                //if (bOnline) {
                    buildGoogleApiClient();
                //}
            } catch (Throwable t) {
                bErro = true;
                sRetorno = t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //if (bErro == false) {
            //    if (bOnline == false) {
            //        Toast.makeText(MainActivity.this, "Não foi possivel registrar o PONTO, o dispositivo esta sem Internet ou servidor esta OFF-LINE !", Toast.LENGTH_SHORT).show();
            //    }
            // }
            //else {
            if (bErro == true) {
                final AlertDialog alertDialog2 = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog2.setTitle("Controle de Ponto");
                alertDialog2.setMessage("Não foi possível realizar a operação: "+sRetorno);
                alertDialog2.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //finish();
                            }
                        });
                try {
                    alertDialog2.show();
                } catch (Throwable t) {
                }
            }
            //}
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }


    private void sincronizarConfig() {
        MainActivity.SincConfigAsyncTask task = new MainActivity.SincConfigAsyncTask();
        task.execute();
    }

    class SincConfigAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Integer iRegOs;
        private Boolean bErro = false;
        private String sMensagem = "";
        private Configuracao configuracao;
        private TextView txtConfig = (TextView) findViewById(R.id.textConfiggeral);
        private ImageView imgConfig = (ImageView) findViewById(R.id.imageConfiggeral);

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this, null, "Sincronizando Dados");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            iRegOs = 0;
            bOnline = ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext())));
            try {
                if (bOnline) {
                    ConfiguracaoDAO configuracaoDAO = new ConfiguracaoDAO();
                    configuracao = configuracaoDAO.procuraConfiguracao("id = 1");
                    ConfiguracaoWSClient configuracaoWSClient = new ConfiguracaoWSClient();
                    String sResult = configuracaoWSClient.recebeConfiguracao(configuracao);
                    configuracao.setMaischeckin(sResult.substring(0, 1));
                    configuracao.setObrkm(sResult.substring(2, 3));
                    configuracao.setTipolan(sResult.substring(4, 5));
                    configuracao.setTemposinc(Integer.parseInt(sResult.substring(7, 10)));
                    configuracao.setTemposmens(Integer.parseInt(sResult.substring(12, 15)));
                    configuracao.setReq(sResult.substring(16, 17));
                    configuracao.setRecolha(sResult.substring(18, 19));
                    configuracao.setPonto(sResult.substring(20, 21));
                    configuracao.setIntervalo(Integer.parseInt(sResult.substring(22, 26)));
                    configuracao.setHoraintervalo(sResult.substring(27, 32));
                    configuracao.setHorafinal(sResult.substring(33, 38));
                    configuracao.setHorainimanha(sResult.substring(39, 44));
                    configuracao.setHorafimmanha(sResult.substring(45, 50));
                    configuracao.setHorainitarde(sResult.substring(51, 56));
                    configuracao.setHorafimtarde(sResult.substring(57, 62));
                    configuracaoDAO.gravaConfiguracao(configuracao);
                    startService(new Intent(getApplicationContext(), SincronizacaoDatabit.class));
                    if (configuracao.getPonto().equals("S")) {
                        startService(new Intent(getApplicationContext(), PontoService.class));
                    }
                    bErro = false;
                }
            } catch (Throwable t) {
                bErro = true;
                sMensagem = t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                if (bOnline) {
                    DateFormat formatohora = new SimpleDateFormat("mm:ss");
                    Toast.makeText(MainActivity.this, "Configuração sincronizada com Sucesso !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Não foi possivel sincronização da Configuração, o dispositivo esta sem Internet ou servidor esta OFF-LINE !", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(MainActivity.this, "Erro - Configuração, " + sMensagem, Toast.LENGTH_SHORT).show();
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
            Toast.makeText(MainActivity.this, "GPS não habilitado", Toast.LENGTH_SHORT).show();
        }
        Localizador.getInstance(this, localizacao).requestLocation();
        return localizacao;
    }


}
