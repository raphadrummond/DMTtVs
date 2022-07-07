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
import databit.com.br.datamobile.adapter.AdapterTrocada;
import databit.com.br.datamobile.dao.TrocadaDAO;
import databit.com.br.datamobile.domain.Trocada;
import databit.com.br.datamobile.interfaces.SelecionaOs;
import databit.com.br.datamobile.interfaces.SelecionaTrocada;

public class TrocadaFragment extends Fragment {

    private SelecionaOs mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.content_trocada_fragment, container, false);
        FragmentActivity c = getActivity();

        mListener = (SelecionaOs) getActivity();

        final RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.pcstrocada_recycler_view);

        LinearLayoutManager layoutManager = new  LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        TrocadaDAO trocadaDAO = new TrocadaDAO();
        List<Trocada> listTrocada = trocadaDAO.listarTrocada(" numserie = '"+mListener.getOsSelecionada().getNumserie()+"'");


        final RecyclerView.Adapter adapter = new AdapterTrocada(listTrocada, (SelecionaTrocada) getActivity());
        recyclerView.setAdapter(adapter);

        return layout;

    }
}
