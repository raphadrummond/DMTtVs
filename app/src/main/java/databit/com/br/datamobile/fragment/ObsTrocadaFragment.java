package databit.com.br.datamobile.fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.interfaces.SelecionaOs;

public class ObsTrocadaFragment extends Fragment {

    private SelecionaOs mListener;
    private Button btnGravarObs;
    private EditText edtObs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mListener = (SelecionaOs) getActivity();
        View layout = inflater.inflate(R.layout.content_obstrocada_fragment, container, false);
        edtObs = (EditText) layout.findViewById(R.id.editObstrocada);
        Toast.makeText(getActivity(), "Favor colocar referência,descrição e quantidade das Peças Trocadas", Toast.LENGTH_SHORT).show();

        btnGravarObs = (Button) layout.findViewById(R.id.btnGravarobstrocada);
        btnGravarObs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OsDAO osDAO = new OsDAO();
                    if (mListener.getOsSelecionada().getFechaok().equals(1)) {
                        mListener.getOsSelecionada().setObstrocada(edtObs.getText().toString());
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
        if (mListener.getOsSelecionada().getObstrocada() != null) {
            edtObs.setText(mListener.getOsSelecionada().getObstrocada().toString());
        }
        if (mListener.getOsSelecionada().getSync().equals("S")) {
            edtObs.setEnabled(false);
            btnGravarObs.setEnabled(false);
        }
        return layout;
    }


}
