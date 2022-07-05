package databit.com.br.datamobile.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.activity.ProdutoActivity;
import databit.com.br.datamobile.adapter.AdapterRecolha;
import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.dao.RecolhaDAO;
import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.domain.Recolha;
import databit.com.br.datamobile.infra.Internet;
import databit.com.br.datamobile.interfaces.SelecionaOs;
import databit.com.br.datamobile.interfaces.SelecionaRecolha;
import databit.com.br.datamobile.wsclient.OsWSClient;

public class RecolhaFragment extends Fragment {
    private SelecionaOs mListener;
    private Button btnIncluirrec;
    private Button btnExcluirrec;
    private Button btnGerarrec;
    private Button btnObsrec;
    private RecyclerView.Adapter adapter;
    private List<Recolha> listRecolha;
    private RecolhaDAO recolhaDAO;
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View layout = inflater.inflate(R.layout.content_recolha_fragment, container, false);
        FragmentActivity c = getActivity();

        mListener = (SelecionaOs) getActivity();

        recyclerView = (RecyclerView) layout.findViewById(R.id.recolha_recycler_view);

        LinearLayoutManager layoutManager = new  LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recolhaDAO = new RecolhaDAO();
        listRecolha = recolhaDAO.listarRecolha(" os = '"+mListener.getOsSelecionada().getCodigo()+"'" +
                                               " and banco = '"+mListener.getOsSelecionada().getBanco()+"'");
        adapter = new AdapterRecolha(listRecolha , (SelecionaRecolha) getActivity());
        recyclerView.setAdapter(adapter);

        ConfiguracaoDAO configuracaoDAO = new ConfiguracaoDAO();
        Configuracao configuracao = configuracaoDAO.procuraConfiguracao("id = 1");

        btnIncluirrec = (Button) layout.findViewById(R.id.btnIncluirrec);
        btnIncluirrec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean bValidar = true;
                if (mListener.getOsSelecionada().getOperacaomobile().equals(1)) {
                    bValidar = (mListener.getOsSelecionada().getStatus_check() >= 3);
                }
                if (mListener.getOsSelecionada().getCodrecolha() == null) {
                    if (bValidar) {                        Intent intent = new Intent(getActivity(), ProdutoActivity.class);
                        intent.putExtra("os", mListener.getOsSelecionada());
                        intent.putExtra("opcao", 3);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getActivity(), "Esta OS ainda não foi inicializada!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Já foi gerada recolha: "+mListener.getOsSelecionada().getCodrecolha().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnExcluirrec = (Button) layout.findViewById(R.id.btnExcluirrec);
        btnExcluirrec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean bValidar = true;
                if (mListener.getOsSelecionada().getOperacaomobile().equals(1)) {
                    bValidar = (mListener.getOsSelecionada().getStatus_check() >= 3);
                }
                if (mListener.getOsSelecionada().getCodrecolha() == null) {
                    if (bValidar) {
                        final Dialog dialog = new Dialog(getActivity());
                        dialog.setContentView(R.layout.dialog);
                        dialog.setTitle("Digite o ID:");
                        final EditText txtMotivo = (EditText) dialog.findViewById(R.id.txtMotivo);
                        txtMotivo.setText("1");
                        txtMotivo.setInputType(InputType.TYPE_CLASS_NUMBER);
                        dialog.show();
                        Button btnOk = (Button) dialog.findViewById(R.id.btnOK);
                        btnOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                RecolhaDAO recolhaDAO = new RecolhaDAO();
                                Recolha recolha = recolhaDAO.procuraRecolha(" os = '" + mListener.getOsSelecionada().getCodigo() + "' " +
                                                                            " and banco = '" + mListener.getOsSelecionada().getBanco() + "' " +
                                                                            " and item = " + txtMotivo.getText().toString());
                                if (recolha != null) {
                                    recolhaDAO.delete(recolha);
                                    Toast.makeText(getActivity(), "Item excluído com Sucesso !", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    onStart();
                                } else {
                                    Toast.makeText(getActivity(), "Item não existente !", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
                        btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                    else {
                        Toast.makeText(getActivity(), "Esta OS ainda não foi inicializada!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Já foi gerada recolha: "+mListener.getOsSelecionada().getCodrecolha().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnGerarrec = (Button) layout.findViewById(R.id.btnGerarrec);
        if (configuracao.getRecolha().toString().equals("N")) {
            btnGerarrec.setVisibility(View.INVISIBLE);
        }
        else {
            btnGerarrec.setVisibility(View.VISIBLE);
        }
        btnGerarrec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean bValidar = true;
                if (mListener.getOsSelecionada().getOperacaomobile().equals(1)) {
                    bValidar = (mListener.getOsSelecionada().getStatus_check() >= 3);
                }
                if (mListener.getOsSelecionada().getCodrecolha() == null) {
                    if (bValidar) {
                        // Peças a Recolher
                        RecolhaDAO recolhaDAO = new RecolhaDAO();
                        OsDAO osDAO = new OsDAO();
                        List<Recolha> listRecolha = new ArrayList<>();
                        listRecolha = recolhaDAO.listarRecolha(" os = '" +  mListener.getOsSelecionada().getCodigo() + "' " +
                                                               " and banco = '" +  mListener.getOsSelecionada().getBanco() + "' ");
                        String sRecolha = "";
                        if (listRecolha.size() > 0) {
                            for (Recolha recolha : listRecolha) {
                                sRecolha = sRecolha + "@";
                                sRecolha = sRecolha + recolha.getCodigo().toString();
                                sRecolha = sRecolha + ";";
                                sRecolha = sRecolha + recolha.getQtprod().toString();
                                sRecolha = sRecolha + "#";
                            }
                            mListener.getOsSelecionada().setObsrecolha(sRecolha);
                            osDAO.gravaOs(mListener.getOsSelecionada());
                            sincronizarRecolha();

                        }
                        else {
                            Toast.makeText(getActivity(), "Esta OS não possui peças para realizar a operação !", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getActivity(), "Esta OS ainda não foi inicializada!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Já foi gerada Recolha : "+mListener.getOsSelecionada().getCodrecolha().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnObsrec = (Button) layout.findViewById(R.id.btnObsrec);
        btnObsrec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("Observações da recolha");
                final EditText txtMotivo = (EditText) dialog.findViewById(R.id.txtMotivo);
                if (mListener.getOsSelecionada().getObsrecolha2() != null) {
                    txtMotivo.setText(mListener.getOsSelecionada().getObsrecolha2());
                }
                dialog.show();
                Button btnOk = (Button) dialog.findViewById(R.id.btnOK);
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.getOsSelecionada().setObsrecolha2(txtMotivo.getText().toString());
                        dialog.dismiss();
                    }
                });

                Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


            }
        });
        if (mListener.getOsSelecionada().getSync().equals("S")) {
            btnIncluirrec.setEnabled(false);
            btnExcluirrec.setEnabled(false);
            btnGerarrec.setEnabled(false);
        }
        return layout;
    }

    @Override
    public void onStart() {
        recolhaDAO = new RecolhaDAO();
        listRecolha = recolhaDAO.listarRecolha(" os = '"+mListener.getOsSelecionada().getCodigo()+"'" +
                                               " and banco = '"+mListener.getOsSelecionada().getBanco()+"'");
        adapter = new AdapterRecolha(listRecolha , (SelecionaRecolha) getActivity());
        recyclerView.setAdapter(adapter);
        super.onStart();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void sincronizarRecolha() {
        SincRecolhaAsyncTask task = new SincRecolhaAsyncTask();
        task.execute();
    }

    class SincRecolhaAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private boolean bErro = false;
        private String sResult;
        private String sErro;
        private String sCodRecolha;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getActivity(), null, "Gerando Recolha");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            sResult="";
            sErro="";
            try {
                if ((Internet.isDeviceOnline(getActivity().getBaseContext())) && (Internet.urlOnline(getActivity().getBaseContext()))) {
                    OsWSClient osWSClient = new OsWSClient();
                    OsDAO osDAO = new OsDAO();
                    sResult = osWSClient.gerarRecolha(mListener.getOsSelecionada());
                    bErro = !(sResult.substring(0,2).equals("OK"));
                    if (bErro == false) {
                        sCodRecolha = sResult.substring(2,8);
                        mListener.getOsSelecionada().setCodrecolha(sCodRecolha);
                        osDAO.gravaOs(mListener.getOsSelecionada());
                    }
                    else {
                        sErro = sResult;
                    }
                }
                else {
                    bErro = true;
                    sErro = "Para realizar esta Operação, o dispositivo tem estar ON-LINE !";
                }

            } catch (Throwable t) {
                bErro = true;
                if (sErro.equals("")) {
                    sErro = t.getMessage().toString();
                }
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                Toast.makeText(getActivity(), "Operação realizada com Sucesso ! Recolha : "+sCodRecolha, Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getActivity(), "Erro não foi possivel realizar a operação: "+sErro, Toast.LENGTH_SHORT).show();
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
