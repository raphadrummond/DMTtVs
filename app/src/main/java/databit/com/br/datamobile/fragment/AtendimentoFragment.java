package databit.com.br.datamobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.interfaces.SelecionaOs;

public class AtendimentoFragment extends Fragment {

    private SelecionaOs mListener;
    private Button btnGravarAtendimento;
    private EditText edtAtendimento;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mListener = (SelecionaOs) getActivity();
        View layout = inflater.inflate(R.layout.content_atendimento_fragment, container, false);
        Toast.makeText(getActivity(), "Favor descrever as observações deste Atendimento", Toast.LENGTH_SHORT).show();
        edtAtendimento = (EditText) layout.findViewById(R.id.editAtendimento);
        btnGravarAtendimento = (Button) layout.findViewById(R.id.btnGravaratendimento);
        btnGravarAtendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OsDAO osDAO = new OsDAO();
                    if ((edtAtendimento.getText().toString().equals("")) || (edtAtendimento.getText().toString() == null)) {
                        Toast.makeText(getActivity(), "Observação do atendimento é de preenchimento Obrigatório !", Toast.LENGTH_SHORT).show();
                        edtAtendimento.requestFocus();
                        return;
                    }
                    else {
                        Date data = new Date();
                        mListener.getOsSelecionada().setLaudo(edtAtendimento.getText().toString());
                        mListener.getOsSelecionada().setFechaok(1);
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
        if (mListener.getOsSelecionada().getLaudo() != null) {
            edtAtendimento.setText(mListener.getOsSelecionada().getLaudo().toString());
        }
        if (mListener.getOsSelecionada().getSync().equals("S")) {
            edtAtendimento.setEnabled(false);
            btnGravarAtendimento.setEnabled(false);
        }
        return layout;
    }
}
