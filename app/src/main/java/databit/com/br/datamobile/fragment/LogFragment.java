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
import databit.com.br.datamobile.adapter.AdapterLog;
import databit.com.br.datamobile.dao.LogsincDAO;
import databit.com.br.datamobile.domain.Logsinc;
import databit.com.br.datamobile.interfaces.SelecionaLog;
import databit.com.br.datamobile.interfaces.SelecionaOs;

public class LogFragment extends Fragment {


    private SelecionaOs mListener;

    public LogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.content_log_fragment, container, false);
        FragmentActivity c = getActivity();
        mListener = (SelecionaOs) getActivity();
        final RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.log_recycler_view);

        LinearLayoutManager layoutManager = new  LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        LogsincDAO logsincDAO = new LogsincDAO();
        List<Logsinc> listLogsinc = logsincDAO.listarLog("os = '"+mListener.getOsSelecionada().getCodigo()+"' and banco = '"+mListener.getOsSelecionada().getBanco()+"'");

        final RecyclerView.Adapter adapter = new AdapterLog(listLogsinc, (SelecionaLog) getActivity());
        recyclerView.setAdapter(adapter);

        return layout;
    }



}
