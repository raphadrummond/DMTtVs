package databit.com.br.datamobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.adapter.AdapterSerial;
import databit.com.br.datamobile.dao.SerialDAO;
import databit.com.br.datamobile.domain.Serial;
import databit.com.br.datamobile.interfaces.SelecionaOs;
import databit.com.br.datamobile.interfaces.SelecionaSerial;

public class SerialFragment extends Fragment {

    private SelecionaOs mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.content_serial_fragment, container, false);
        FragmentActivity c = getActivity();

        mListener = (SelecionaOs) getActivity();

        final RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.serial_recycler_view);

        LinearLayoutManager layoutManager = new  LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        SerialDAO serialDAO = new SerialDAO();

        String sTabela;

        if (mListener.getOsSelecionada().getOperacaomobile().equals(2)) {
            sTabela = "TB02021";
        }
        else  {
            sTabela = "TB02030";
        }

        List<Serial> listSerial = serialDAO.listarSerial(" codigo = '"+mListener.getOsSelecionada().getCodigo()+"' and tabela = '"+sTabela+"' ");
        final RecyclerView.Adapter adapter = new AdapterSerial(listSerial, (SelecionaSerial) getActivity());
        recyclerView.setAdapter(adapter);

        return layout;
    }
}
