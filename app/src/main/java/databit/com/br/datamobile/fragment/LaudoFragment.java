package databit.com.br.datamobile.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.dao.ConfigmobileDAO;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.domain.Configmobile;
import databit.com.br.datamobile.interfaces.SelecionaOs;

public class LaudoFragment extends Fragment {
    private SelecionaOs mListener;
    private Button btnGravarLaudo;
    private EditText edtLaudo;
    private EditText edtKminicial;
    private EditText edtKmfinal;
    private EditText edtPlaca;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mListener = (SelecionaOs) getActivity();
        View layout = inflater.inflate(R.layout.content_laudo_fragment, container, false);
        Toast.makeText(getActivity(), "Favor descrever o laudo técnico desta OS", Toast.LENGTH_SHORT).show();
        edtLaudo = (EditText) layout.findViewById(R.id.editLaudo);
        edtKminicial = (EditText) layout.findViewById(R.id.edtKminicial);
        edtKmfinal = (EditText) layout.findViewById(R.id.edtKmfinal);
        edtPlaca = (EditText) layout.findViewById(R.id.edtPlaca);
        btnGravarLaudo = (Button) layout.findViewById(R.id.btnGravarlaudo);
        btnGravarLaudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ConfigmobileDAO configmobileDAO = new ConfigmobileDAO();
                    OsDAO osDAO = new OsDAO();
                    Configmobile configmobile = configmobileDAO.procuraConfigmobile("id = "+mListener.getOsSelecionada().getOperacaomobile().toString());

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
                    if ((edtLaudo.getText().toString().equals("")) || (edtLaudo.getText().toString() == null)) {
                        Toast.makeText(getActivity(), "Laudo Técnico é de preenchimento Obrigatório !", Toast.LENGTH_SHORT).show();
                        edtLaudo.requestFocus();
                        return;
                    }
                    else {
                        mListener.getOsSelecionada().setLaudo(edtLaudo.getText().toString());
                        mListener.getOsSelecionada().setPlaca(edtPlaca.getText().toString());
                        mListener.getOsSelecionada().setKminicial(Integer.parseInt(edtKminicial.getText().toString()));
                        mListener.getOsSelecionada().setKmfinal(Integer.parseInt(edtKmfinal.getText().toString()));
                        Date data = new Date();
                        mListener.getOsSelecionada().setSync("N");
                        mListener.getOsSelecionada().setBanco(mListener.getOsSelecionada().getBanco().toString());
                        mListener.getOsSelecionada().setBancoos(mListener.getOsSelecionada().getBancoos().toString());
                        mListener.getOsSelecionada().setFechaok(1);
                        mListener.getOsSelecionada().setDatafecha(data);
                        osDAO.gravaOs(mListener.getOsSelecionada());
                        Toast.makeText(getActivity(), "Informações Salvas com Sucesso !", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Problemas ao Salvar:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (mListener.getOsSelecionada().getFechaok().equals(0)) {
            edtLaudo.setText("");
            edtPlaca.setText("");
            edtKminicial.setText("0");
            edtKmfinal.setText("0");
        }
        else {
           edtLaudo.setText(mListener.getOsSelecionada().getLaudo().toString());
           edtPlaca.setText(mListener.getOsSelecionada().getPlaca().toString());
           edtKminicial.setText(mListener.getOsSelecionada().getKminicial().toString());
           edtKmfinal.setText(mListener.getOsSelecionada().getKmfinal().toString());
        }
        if (mListener.getOsSelecionada().getSync().equals("S")) {
            edtLaudo.setEnabled(false);
            edtPlaca.setEnabled(false);
            edtKminicial.setEnabled(false);
            edtKmfinal.setEnabled(false);
            btnGravarLaudo.setEnabled(false);
        }
        return layout;
    }

}
