package databit.com.br.datamobile.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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
import databit.com.br.datamobile.adapter.AdapterProduto;
import databit.com.br.datamobile.dao.ComposicaoDAO;
import databit.com.br.datamobile.dao.ProdutoDAO;
import databit.com.br.datamobile.domain.Composicao;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.domain.Produto;
import databit.com.br.datamobile.interfaces.SelecionaOs;


public class ProdutoActivity extends AppCompatActivity implements SelecionaOs {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Os os;
    private Integer iOpcao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.produto_recycler_view);

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
                EditText edtReferencia = (EditText) findViewById(R.id.edtReferencia);
                EditText edtCodbarras = (EditText) findViewById(R.id.edtCodbarras);
                EditText edtAuxiliar = (EditText) findViewById(R.id.edtAuxiliar);
                EditText edtNome = (EditText) findViewById(R.id.edtNome);
                edtCodigo.setText("");
                edtReferencia.setText("");
                edtCodbarras.setText("");
                edtAuxiliar.setText("");
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

    @Override
    public void onOsSelecionada(Os os) {

    }

    @Override
    public Os getOsSelecionada() {
        return null;
    }

    class SincFiltrarAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private EditText edtCodigo = (EditText) findViewById(R.id.edtCodigo);
        private EditText edtReferencia = (EditText) findViewById(R.id.edtReferencia);
        private EditText edtCodbarras = (EditText) findViewById(R.id.edtCodbarras);
        private EditText edtAuxiliar = (EditText) findViewById(R.id.edtAuxiliar);
        private EditText edtNome = (EditText) findViewById(R.id.edtNome);

        List<Produto> listproduto = new ArrayList<>();

        String sCodigo = edtCodigo.getText().toString();
        String sReferencia = edtReferencia.getText().toString();
        String sCodbarras = edtCodbarras.getText().toString();
        String sAuxiliar = edtAuxiliar.getText().toString();
        String sNome = edtNome.getText().toString();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(ProdutoActivity.this, null, "Filtrando Produtos");
        }

        @Override
        protected Void doInBackground(Void... params) {

            ProdutoDAO produtoDAO = new ProdutoDAO();

            String sSQL = " 0 = 0 ";

            if (!(sCodigo.equals("")) && (sCodigo != null)) {
               sSQL = sSQL + " and codigo = '"+sCodigo+"'";
            }
            if (!(sReferencia.equals("")) && (sReferencia != null)) {
                sSQL = sSQL + " and referencia like '"+sReferencia+"%'";
            }
            if (!(sCodbarras.equals("")) && (sCodbarras != null)) {
                sSQL = sSQL + " and codbarras like '"+sCodbarras+"%'";
            }
            if (!(sAuxiliar.equals("")) && (sAuxiliar != null)) {
                sSQL = sSQL + " and codauxiliar like '"+sAuxiliar+"%'";
            }
            if (!(sNome.equals("")) && (sNome != null)) {
                sSQL = sSQL + " and nome like '"+sNome+"%'";
            }

            if (os != null) {
                ComposicaoDAO composicaoDAO = new ComposicaoDAO();
                List<Composicao> listComposicao = new ArrayList<>();
                listComposicao = composicaoDAO.listarComposicao("banco = '" + os.getBanco().toString() + "' and codigoproduto = '" + os.getProduto().toString() + "'");
                String sProdcomp;
                if (listComposicao.size() > 0) {
                    sProdcomp = "(";
                    for (Composicao composicao : listComposicao) {
                        sProdcomp = sProdcomp + "'" + composicao.getCodigo().toString() + "',";
                    }
                    sProdcomp = sProdcomp.substring(0, sProdcomp.length() - 1);
                    sProdcomp = sProdcomp + ")";
                    sSQL = sSQL + " and codigo in " + sProdcomp;
                    sSQL = sSQL + " and banco = '" + os.getBanco().toString() + "'";
                }
            }

            listproduto = produtoDAO.listarProduto(sSQL);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter = new AdapterProduto(listproduto, recyclerView, os, iOpcao);
            recyclerView.setAdapter(adapter);
            Toast.makeText(ProdutoActivity.this, listproduto.size() + " registros encontrados !", Toast.LENGTH_SHORT).show();
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }



    }



}
