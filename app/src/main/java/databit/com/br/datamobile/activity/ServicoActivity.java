package databit.com.br.datamobile.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.dao.ServicoDAO;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.domain.Servico;
import databit.com.br.datamobile.adapter.AdapterServico;


public class ServicoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Os os;
    private Integer iOpcao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.servico_recycler_view);

        os = (Os) getIntent().getSerializableExtra("os");
        iOpcao = (Integer) getIntent().getSerializableExtra("opcao");

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

        Button btnFiltrar = (Button) findViewById(R.id.btnFiltrar);
        btnFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtrar();
            }
        });

        Button btnLimpar = (Button) findViewById(R.id.btnLimpar);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtCodigo = (EditText) findViewById(R.id.edtCodigo);
                EditText edtNome = (EditText) findViewById(R.id.edtNome);
                edtCodigo.setText("");
                edtNome.setText("");
            }
        });

        Button btnFechar = (Button) findViewById(R.id.btnCancelar);
        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void filtrar() {
        SincFiltrarAsyncTask task = new SincFiltrarAsyncTask();
        task.execute();
    }

    class SincFiltrarAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private EditText edtCodigo = (EditText) findViewById(R.id.edtCodigo);
        private EditText edtNome = (EditText) findViewById(R.id.edtNome);

        List<Servico> listservico = new ArrayList<>();

        String sCodigo = edtCodigo.getText().toString();
        String sNome = edtNome.getText().toString();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(ServicoActivity.this , null, "Filtrando Serviços");
        }

        @Override
        protected Void doInBackground(Void... params) {

            ServicoDAO servicoDAO = new ServicoDAO();

            String sSQL = " 0 = 0 ";

            if (!(sCodigo.equals("")) && (sCodigo != null)) {
                sSQL = sSQL + " and codigo = '"+sCodigo+"'";
            }
            if (!(sNome.equals("")) && (sNome != null)) {
                sSQL = sSQL + " and nome like '"+sNome+"%'";
            }

            listservico = servicoDAO.listarServico(sSQL);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter = new AdapterServico(listservico, recyclerView,  os);
            recyclerView.setAdapter(adapter);
            Toast.makeText(ServicoActivity.this, listservico.size() + " registros encontrados !", Toast.LENGTH_SHORT).show();
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }



    }



}
