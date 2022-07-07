package databit.com.br.datamobile.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.adapter.AdapterHistorico;
import databit.com.br.datamobile.dao.HistoricoDAO;
import databit.com.br.datamobile.domain.Historico;
import databit.com.br.datamobile.interfaces.SelecionaHistorico;
import databit.com.br.datamobile.interfaces.SelecionaOs;

public class HistoricoFragment extends Fragment {


    private SelecionaOs mListener;

    public HistoricoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.content_historico_fragment, container, false);
        FragmentActivity c = getActivity();
        mListener = (SelecionaOs) getActivity();
        final RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.historico_recycler_view);

        LinearLayoutManager layoutManager = new  LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        HistoricoDAO historicoDAO = new HistoricoDAO();
        List<Historico> listHistorico = historicoDAO.listarHistorico(" numserie = '"+mListener.getOsSelecionada().getNumserie()+"'");

        final RecyclerView.Adapter adapter = new AdapterHistorico(listHistorico, (SelecionaHistorico) getActivity());
        recyclerView.setAdapter(adapter);

        return layout;
    }



}
