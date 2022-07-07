package databit.com.br.datamobile.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.adapter.AdapterHistoricoTI;
import databit.com.br.datamobile.dao.HistoricoTIDAO;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.domain.HistoricoTI;
import databit.com.br.datamobile.infra.Internet;
import databit.com.br.datamobile.interfaces.SelecionaHistoricoTI;
import databit.com.br.datamobile.interfaces.SelecionaOs;
import databit.com.br.datamobile.wsclient.HistoricoTIWSClient;

public class HistoricoTIFragment extends Fragment {


    private SelecionaOs mListener;
    private RecyclerView.Adapter adapter;
    private List<HistoricoTI> listHistoricoTI;
    private RecyclerView recyclerView;

    public HistoricoTIFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.content_historico_ti, container, false);
        FragmentActivity c = getActivity();
        mListener = (SelecionaOs) getActivity();
        recyclerView = (RecyclerView) layout.findViewById(R.id.historico_ti_recycler_view);

        final CheckBox chLaboratorio = (CheckBox) layout.findViewById(R.id.checkLaboratorio);
        final CheckBox chRemoto = (CheckBox) layout.findViewById(R.id.checkRemoto);
        final CheckBox chPresencial = (CheckBox) layout.findViewById(R.id.checkPresencial);
        final CheckBox chProjeto  = (CheckBox) layout.findViewById(R.id.checkProjeto);
        final CheckBox chSistemas  = (CheckBox) layout.findViewById(R.id.checkSistemas);
        final CheckBox chImplantacao  = (CheckBox) layout.findViewById(R.id.checkImplantacao);


        chLaboratorio.setChecked((mListener.getOsSelecionada().getOperacaomobile().equals(4)) || (mListener.getOsSelecionada().getOperacaomobile().equals(6)));
        chRemoto.setChecked((mListener.getOsSelecionada().getOperacaomobile().equals(4)) || (mListener.getOsSelecionada().getOperacaomobile().equals(6)));
        chPresencial.setChecked((mListener.getOsSelecionada().getOperacaomobile().equals(4)) || (mListener.getOsSelecionada().getOperacaomobile().equals(6)));
        chProjeto.setChecked((mListener.getOsSelecionada().getOperacaomobile().equals(4)) || (mListener.getOsSelecionada().getOperacaomobile().equals(6)));
        chSistemas.setChecked(mListener.getOsSelecionada().getOperacaomobile().equals(5));
        chImplantacao.setChecked(mListener.getOsSelecionada().getOperacaomobile().equals(5));

        chLaboratorio.setEnabled((mListener.getOsSelecionada().getOperacaomobile().equals(4)) || (mListener.getOsSelecionada().getOperacaomobile().equals(6)));
        chRemoto.setEnabled((mListener.getOsSelecionada().getOperacaomobile().equals(4)) || (mListener.getOsSelecionada().getOperacaomobile().equals(6)));
        chPresencial.setEnabled((mListener.getOsSelecionada().getOperacaomobile().equals(4)) || (mListener.getOsSelecionada().getOperacaomobile().equals(6)));
        chProjeto.setEnabled((mListener.getOsSelecionada().getOperacaomobile().equals(4)) || (mListener.getOsSelecionada().getOperacaomobile().equals(6)));
        chSistemas.setEnabled(mListener.getOsSelecionada().getOperacaomobile().equals(5));
        chImplantacao.setEnabled(mListener.getOsSelecionada().getOperacaomobile().equals(5));


        LinearLayoutManager layoutManager = new  LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final Button btnCarregar = (Button) layout.findViewById(R.id.btnCarregar);
        btnCarregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OsDAO osDAO = new OsDAO();
                if ((mListener.getOsSelecionada().getOperacaomobile().equals(4)) || (mListener.getOsSelecionada().getOperacaomobile().equals(6))) {
                    if (chLaboratorio.isChecked()) {
                        mListener.getOsSelecionada().setVerlaboratorio("S");
                    }
                    else {
                        mListener.getOsSelecionada().setVerlaboratorio("N");
                    }
                    if (chRemoto.isChecked()) {
                        mListener.getOsSelecionada().setVerremoto("S");
                    }
                    else {
                        mListener.getOsSelecionada().setVerremoto("N");
                    }
                    if (chPresencial.isChecked()) {
                        mListener.getOsSelecionada().setVerpresencial("S");
                    }
                    else {
                        mListener.getOsSelecionada().setVerpresencial("N");
                    }
                }
                else {
                    if (chSistemas.isChecked()) {
                        mListener.getOsSelecionada().setVersistema("S");
                    }
                    else {
                        mListener.getOsSelecionada().setVersistema("N");
                    }
                    if (chImplantacao.isChecked()) {
                        mListener.getOsSelecionada().setVerimplantacao("S");
                    }
                    else {
                        mListener.getOsSelecionada().setVerimplantacao("N");
                    }
                }
                osDAO.createOrUpdate(mListener.getOsSelecionada());
                Filtrar();
            }
        });
        return layout;
    }

    public void Filtrar() {
        SincHistoricoTIAsyncTask task = new SincHistoricoTIAsyncTask();
        task.execute();
    }

    class SincHistoricoTIAsyncTask extends AsyncTask<Void, Void,Void> {
        private ProgressDialog progressDialog;
        private Integer iRegOs;
        private Boolean bErro = false;
        private String sMensagem = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(getActivity() , null, "Filtrando Histórico");
        }

        @Override
        protected Void doInBackground(Void... params) {
            iRegOs = 0;
            try {
                if ((Internet.isDeviceOnline(getActivity().getBaseContext())) && (Internet.urlOnline(getActivity().getBaseContext()))) {
                    HistoricoTIDAO historicoTIDAO = new HistoricoTIDAO();
                    List<HistoricoTI> listHistoricoTI2 = historicoTIDAO.allHistoricoTI();
                    for (HistoricoTI historicoTI : listHistoricoTI2) {
                        historicoTIDAO.delete(historicoTI);
                    }
                    HistoricoTIWSClient historicoTIWSClient = new HistoricoTIWSClient();
                    historicoTIWSClient.os = mListener.getOsSelecionada();
                    if ((mListener.getOsSelecionada().getOperacaomobile().equals(4)) || (mListener.getOsSelecionada().getOperacaomobile().equals(6))) {
                        listHistoricoTI = historicoTIWSClient.buscaHistoricoTI();
                    }
                    else {
                        listHistoricoTI = historicoTIWSClient.buscaHistoricoIMP();
                    }
                    for (HistoricoTI historicoTI : listHistoricoTI) {
                        if (historicoTI.getCodigo().equals(mListener.getOsSelecionada().getCodigo())) {
                            listHistoricoTI.remove(historicoTI);
                            historicoTIDAO.delete(historicoTI);
                            break;
                        }
                    }
                    bErro = false;
                }
            } catch (Throwable t) {
                bErro = true;
                sMensagem = t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                Toast.makeText(getActivity(), listHistoricoTI.size() + " Registros Sincronizados!", Toast.LENGTH_SHORT).show();
                adapter = new AdapterHistoricoTI(listHistoricoTI, (SelecionaHistoricoTI) getActivity());
                recyclerView.setAdapter(adapter);
            }
            else {
                Toast.makeText(getActivity(), "Erro - Histórico, "+sMensagem, Toast.LENGTH_SHORT).show();
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

    }



}
