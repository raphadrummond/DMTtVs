package databit.com.br.datamobile.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.dao.AparelhoDAO;
import databit.com.br.datamobile.domain.Aparelho;

public class InforActivity extends AppCompatActivity {

    private TextView txtSerial;
    private TextView txtVersao;
    private TextView txtModelo;
    private TextView txtFabricante;
    private TextView txtVersaocode;
    private TextView txtVersaoname;
    private TextView txtToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AparelhoDAO aparelhoDAO = new AparelhoDAO();
        Aparelho aparelho = aparelhoDAO.procuraAparelho("id = 1");

        txtSerial = (TextView) findViewById(R.id.txtSerial);
        txtVersao = (TextView) findViewById(R.id.txtVersao);
        txtModelo = (TextView) findViewById(R.id.txtModelo);
        txtFabricante = (TextView) findViewById(R.id.txtFabricante);
        txtVersaocode = (TextView) findViewById(R.id.txtVersaocode);
        txtVersaoname = (TextView) findViewById(R.id.txtVersaoname);
        txtToken = (TextView) findViewById(R.id.txtToken);

        txtSerial.setText(aparelho.getSerial());
        txtVersao.setText(aparelho.getVersao());
        txtModelo.setText(aparelho.getModelo());
        txtFabricante.setText(aparelho.getFabricante());
        txtVersaocode.setText(aparelho.getVersaocode().toString());
        txtVersaoname.setText(aparelho.getVersaoname());
        txtToken.setText(aparelho.getToken());

    }

}
