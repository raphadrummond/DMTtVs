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
import databit.com.br.datamobile.adapter.AdapterOsDefeito;
import databit.com.br.datamobile.dao.OsDefeitoDAO;
import databit.com.br.datamobile.domain.OsDefeito;
import databit.com.br.datamobile.interfaces.SelecionaOs;
import databit.com.br.datamobile.interfaces.SelecionaOsDefeito;

public class OsDefeitoFragment extends Fragment {

    private SelecionaOs mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.content_osdefeito_fragment, container, false);
        FragmentActivity c = getActivity();

        mListener = (SelecionaOs) getActivity();

        final RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.osdefeito_recycler_view);

        LinearLayoutManager layoutManager = new  LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        OsDefeitoDAO osDefeitoDAO = new OsDefeitoDAO();
        List<OsDefeito> listosDefeito = osDefeitoDAO.listarOsDefeito(" numserie = '"+mListener.getOsSelecionada().getNumserie()+"'");

        final RecyclerView.Adapter adapter = new AdapterOsDefeito(listosDefeito, (SelecionaOsDefeito) getActivity());
        recyclerView.setAdapter(adapter);

        return layout;
    }

}
