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
import databit.com.br.datamobile.adapter.AdapterHistoricoTEL;
import databit.com.br.datamobile.dao.HistoricoTELDAO;
import databit.com.br.datamobile.domain.HistoricoTEL;
import databit.com.br.datamobile.interfaces.SelecionaHistoricoTEL;
import databit.com.br.datamobile.interfaces.SelecionaOs;

public class HistoricoTELFragment extends Fragment {

    private SelecionaOs mListener;

    public HistoricoTELFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.content_historico_tel, container, false);
        FragmentActivity c = getActivity();
        mListener = (SelecionaOs) getActivity();
        final RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.historico_tel_recycler_view);

        LinearLayoutManager layoutManager = new  LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        HistoricoTELDAO historicoTELDAO = new HistoricoTELDAO();
        List<HistoricoTEL> listHistoricoTEL = historicoTELDAO.listarHistoricoTEL(" codcli = '"+mListener.getOsSelecionada().getCodcli()+"' and banco = '"+mListener.getOsSelecionada().getBanco()+"'");

        final RecyclerView.Adapter adapter = new AdapterHistoricoTEL(listHistoricoTEL, (SelecionaHistoricoTEL) getActivity());
        recyclerView.setAdapter(adapter);

        return layout;
    }

}
