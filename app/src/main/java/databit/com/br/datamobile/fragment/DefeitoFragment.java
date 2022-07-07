package databit.com.br.datamobile.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.interfaces.SelecionaOs;

public class DefeitoFragment extends Fragment {
    private SelecionaOs mListener;
    private Button btnGravarDefeito;
    private EditText edtDefeito;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mListener = (SelecionaOs) getActivity();
        View layout = inflater.inflate(R.layout.content_defeito_fragment, container, false);
        Toast.makeText(getActivity(), "Favor descrever os defeitos e soluções encontradas nesta OS", Toast.LENGTH_SHORT).show();
        edtDefeito = (EditText) layout.findViewById(R.id.editDefeito);
        btnGravarDefeito = (Button) layout.findViewById(R.id.btnGravardefeito);
        btnGravarDefeito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OsDAO osDAO = new OsDAO();
                    if (mListener.getOsSelecionada().getFechaok().equals(1)) {
                        mListener.getOsSelecionada().setDefeito(edtDefeito.getText().toString());
                        osDAO.gravaOs(mListener.getOsSelecionada());
                        Toast.makeText(getActivity(), "Informações Salvas com Sucesso !", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getActivity(), "Primeiramente deve-se salvar as informações no botão INFOR", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Problemas ao Salvar:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (mListener.getOsSelecionada().getDefeito() != null) {
            edtDefeito.setText(mListener.getOsSelecionada().getDefeito().toString());
        }
        if (mListener.getOsSelecionada().getSync().equals("S")) {
            edtDefeito.setEnabled(false);
            btnGravarDefeito.setEnabled(false);
        }
        return layout;
    }
}
