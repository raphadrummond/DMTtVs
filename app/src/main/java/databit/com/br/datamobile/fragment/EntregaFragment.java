package databit.com.br.datamobile.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Date;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.dao.AparelhoDAO;
import databit.com.br.datamobile.dao.ConfigmobileDAO;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.domain.Aparelho;
import databit.com.br.datamobile.domain.Configmobile;
import databit.com.br.datamobile.interfaces.SelecionaOs;

public class EntregaFragment extends Fragment {
    private SelecionaOs mListener;
    private Button btnGravarentrega;
    private EditText edtConferido;
    private EditText edtRecebido;
    private EditText edtContpb;
    private EditText edtContcolor;
    private EditText edtConttotal;
    private EditText edtKminicial;
    private EditText edtKmfinal;
    private EditText edtPlaca;
    private RadioButton rbTotal;
    private RadioButton rbParcial;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AparelhoDAO aparelhoDAO = new AparelhoDAO();
        final Aparelho aparelho = aparelhoDAO.procuraAparelho("id = 1");
        mListener = (SelecionaOs) getActivity();
        View layout = inflater.inflate(R.layout.content_entrega_fragment, container, false);
        Toast.makeText(getActivity(), "Favor as informações da entrega / retirada", Toast.LENGTH_SHORT).show();
        edtConferido = (EditText) layout.findViewById(R.id.edtConferido);
        edtRecebido = (EditText) layout.findViewById(R.id.edtRecebido);
        edtContpb = (EditText) layout.findViewById(R.id.edtContpb);
        edtContcolor = (EditText) layout.findViewById(R.id.edtContcolor);
        edtConttotal = (EditText) layout.findViewById(R.id.edtConttotal);
        edtKminicial = (EditText) layout.findViewById(R.id.edtKminicial);
        edtKmfinal = (EditText) layout.findViewById(R.id.edtKmfinal);
        edtPlaca = (EditText) layout.findViewById(R.id.edtPlaca);
        rbTotal =  (RadioButton) layout.findViewById(R.id.rbtotal);
        rbTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbParcial.setChecked(!(rbTotal.isChecked()));
            }
        });
        rbParcial =  (RadioButton) layout.findViewById(R.id.rbparcial);
        rbParcial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbTotal.setChecked(!(rbParcial.isChecked()));
            }
        });
        btnGravarentrega = (Button) layout.findViewById(R.id.btnGravarentrega);
        btnGravarentrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ConfigmobileDAO configmobileDAO = new ConfigmobileDAO();
                    OsDAO osDAO = new OsDAO();
                    Configmobile configmobile = configmobileDAO.procuraConfigmobile("id = "+mListener.getOsSelecionada().getOperacaomobile().toString());
                    if ((edtKminicial.getText().toString() == null) || (edtKminicial.getText().equals(""))) {
                        edtKminicial.setText("0");
                    }
                    if ((edtKmfinal.getText().toString() == null)  || (edtKmfinal.getText().equals(""))) {
                        edtKmfinal.setText("0");
                    }
                    if (configmobile.getObrkmplaca().equals("S")) {
                        if (Integer.parseInt(edtKminicial.getText().toString()) == 0) {
                            Toast.makeText(getActivity(), "KM Inicial é de preenchimento Obrigatório !", Toast.LENGTH_SHORT).show();
                            edtKminicial.requestFocus();
                            return;
                        }
                        if (Integer.parseInt(edtKmfinal.getText().toString()) == 0) {
                            Toast.makeText(getActivity(), "KM Final é de preenchimento Obrigatório !", Toast.LENGTH_SHORT).show();
                            edtKmfinal.requestFocus();
                            return;
                        }
                        if ((edtPlaca.getText().toString().equals("")) || (edtPlaca.getText().toString() == null)) {
                            Toast.makeText(getActivity(), "Placa é de preenchimento Obrigatório !", Toast.LENGTH_SHORT).show();
                            edtPlaca.requestFocus();
                            return;
                        }
                    }
                    if ((edtRecebido.getText().toString().equals("")) || (edtRecebido.getText().toString() == null)) {
                        Toast.makeText(getActivity(), "Recebido por é de preenchimento Obrigatório !", Toast.LENGTH_SHORT).show();
                        edtRecebido.requestFocus();
                        return;
                    }

                    mListener.getOsSelecionada().setFechaok(1);
                    mListener.getOsSelecionada().setConferido(edtConferido.getText().toString());
                    mListener.getOsSelecionada().setRecebido(edtRecebido.getText().toString());
                    mListener.getOsSelecionada().setContadorospb(Integer.parseInt(edtContpb.getText().toString()));
                    mListener.getOsSelecionada().setContadoroscolor(Integer.parseInt(edtContcolor.getText().toString()));
                    mListener.getOsSelecionada().setContadorostotal(Integer.parseInt(edtConttotal.getText().toString()));
                    mListener.getOsSelecionada().setPlaca(edtPlaca.getText().toString());
                    mListener.getOsSelecionada().setKminicial(Integer.parseInt(edtKminicial.getText().toString()));
                    mListener.getOsSelecionada().setKmfinal(Integer.parseInt(edtKmfinal.getText().toString()));
                    Date data = new Date();
                    mListener.getOsSelecionada().setSync("N");
                    mListener.getOsSelecionada().setBanco(mListener.getOsSelecionada().getBanco().toString());
                    mListener.getOsSelecionada().setBancoos(mListener.getOsSelecionada().getBancoos().toString());
                    mListener.getOsSelecionada().setFechaok(1);
                    mListener.getOsSelecionada().setDatafecha(data);
                    mListener.getOsSelecionada().setNomeajudante(aparelho.getUsuario());
                    if (rbTotal.isChecked()) {
                        mListener.getOsSelecionada().setIdentrega(1);
                    }
                    else {
                        mListener.getOsSelecionada().setIdentrega(2);
                    }
                    osDAO.gravaOs(mListener.getOsSelecionada());
                    Toast.makeText(getActivity(), "Informações Salvas com Sucesso !", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Problemas ao Salvar:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (mListener.getOsSelecionada().getFechaok().equals(0)) {
            edtConferido.setText("");
            edtRecebido.setText("");
            edtContpb.setText("0");
            edtContcolor.setText("0");
            edtConttotal.setText("0");
            edtPlaca.setText("");
            edtKminicial.setText("0");
            edtKmfinal.setText("0");
        }
        else {
            edtConferido.setText(mListener.getOsSelecionada().getConferido().toString());
            edtRecebido.setText(mListener.getOsSelecionada().getRecebido().toString());
            edtContpb.setText(mListener.getOsSelecionada().getContadorospb().toString());
            edtContcolor.setText(mListener.getOsSelecionada().getContadoroscolor().toString());
            edtConttotal.setText(mListener.getOsSelecionada().getContadorostotal().toString());
            edtPlaca.setText(mListener.getOsSelecionada().getPlaca().toString());
            edtKminicial.setText(mListener.getOsSelecionada().getKminicial().toString());
            edtKmfinal.setText(mListener.getOsSelecionada().getKmfinal().toString());
        }
        if (mListener.getOsSelecionada().getSync().equals("S")) {
            edtConferido.setEnabled(false);
            edtRecebido.setEnabled(false);
            edtContpb.setEnabled(false);
            edtContcolor.setEnabled(false);
            edtConttotal.setEnabled(false);
            edtPlaca.setEnabled(false);
            edtKminicial.setEnabled(false);
            edtKmfinal.setEnabled(false);
            btnGravarentrega.setEnabled(false);
        }
        return layout;
    }

}
