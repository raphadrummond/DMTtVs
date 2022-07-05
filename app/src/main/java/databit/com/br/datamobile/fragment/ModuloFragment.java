package databit.com.br.datamobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import databit.com.br.datamobile.R;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.interfaces.SelecionaOs;

public class ModuloFragment extends Fragment {
    private SelecionaOs mListener;
    private Button btnGravarModulo;
    private EditText edtModulo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mListener = (SelecionaOs) getActivity();
        View layout = inflater.inflate(R.layout.content_modulo_fragment, container, false);
        Toast.makeText(getActivity(), "Favor descrever os módulos que foram passados", Toast.LENGTH_SHORT).show();
        edtModulo = (EditText) layout.findViewById(R.id.editModulo);
        btnGravarModulo = (Button) layout.findViewById(R.id.btnGravarmodulo);
        btnGravarModulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OsDAO osDAO = new OsDAO();
                    if (mListener.getOsSelecionada().getFechaok().equals(1)) {
                        mListener.getOsSelecionada().setModulo(edtModulo.getText().toString());
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
        if (mListener.getOsSelecionada().getModulo() != null) {
            edtModulo.setText(mListener.getOsSelecionada().getModulo().toString());
        }
        if (mListener.getOsSelecionada().getSync().equals("S")) {
            edtModulo.setEnabled(false);
            btnGravarModulo.setEnabled(false);
        }
        return layout;
    }

}
