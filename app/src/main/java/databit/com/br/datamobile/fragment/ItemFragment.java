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
import databit.com.br.datamobile.adapter.AdapterItem;
import databit.com.br.datamobile.dao.ItemDAO;
import databit.com.br.datamobile.domain.Item;
import databit.com.br.datamobile.interfaces.SelecionaOs;
import databit.com.br.datamobile.interfaces.SelecionaItem;

public class ItemFragment extends Fragment {

    private SelecionaOs mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.content_item_fragment, container, false);
        FragmentActivity c = getActivity();

        mListener = (SelecionaOs) getActivity();

        final RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.item_recycler_view);

        LinearLayoutManager layoutManager = new  LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ItemDAO itemDAO = new ItemDAO();

        String sTabela;

        if (mListener.getOsSelecionada().getOperacaomobile().equals(2)) {
            sTabela = "TB02021";
        }
        else  {
            sTabela = "TB02030";
        }

        List<Item> listItem = itemDAO.listarItem(" codigo = '"+mListener.getOsSelecionada().getCodigo()+"' and tabela = '"+sTabela+"' ");
        final RecyclerView.Adapter adapter = new AdapterItem(listItem, (SelecionaItem) getActivity());
        recyclerView.setAdapter(adapter);

        return layout;
    }
}
