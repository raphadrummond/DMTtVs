package databit.com.br.datamobile.fragment;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import databit.com.br.datamobile.adapter.AdapterPendenteOS;
import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.dao.PendenteOSDAO;
import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.domain.PendenteOS;
import databit.com.br.datamobile.infra.Internet;
import databit.com.br.datamobile.interfaces.SelecionaOs;
import databit.com.br.datamobile.interfaces.SelecionaPendenteOS;
import databit.com.br.datamobile.wsclient.OsWSClient;


public class PendenteOSFragment extends Fragment {

    private SelecionaOs mListener;
    private Button btnIncluirpend;
    private Button btnExcluirpend;
    private Button btnSolicitar;
    private RecyclerView.Adapter adapter;
    private List<PendenteOS> listPendenteOS;
    private PendenteOSDAO pendenteOSDAO;
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View layout = inflater.inflate(R.layout.content_pendenteos_fragment, container, false);
        FragmentActivity c = getActivity();

        mListener = (SelecionaOs) getActivity();

        recyclerView = (RecyclerView) layout.findViewById(R.id.pendenteos_recycler_view);

        LinearLayoutManager layoutManager = new  LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        pendenteOSDAO = new PendenteOSDAO();
        listPendenteOS = pendenteOSDAO.listarPendenteOS(" os = '"+mListener.getOsSelecionada().getCodigo()+"'" +
                                                        " and banco = '"+mListener.getOsSelecionada().getBanco()+"'");
        adapter = new AdapterPendenteOS(listPendenteOS , (SelecionaPendenteOS) getActivity());
        recyclerView.setAdapter(adapter);

        ConfiguracaoDAO configuracaoDAO = new ConfiguracaoDAO();
        Configuracao configuracao = configuracaoDAO.procuraConfiguracao("id = 1");

        btnIncluirpend = (Button) layout.findViewById(R.id.btnIncluirpend);
        btnIncluirpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener.getOsSelecionada().getRequisicao() == null) {
                    if (mListener.getOsSelecionada().getStatus_check() >= 3) {
                        Intent intent = new Intent(getActivity(), ProdutoActivity.class);
                        intent.putExtra("os", mListener.getOsSelecionada());
                        intent.putExtra("opcao", 1);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getActivity(), "Esta OS ainda não foi inicializada!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Já foi gerada requisição: "+mListener.getOsSelecionada().getRequisicao().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnExcluirpend = (Button) layout.findViewById(R.id.btnExcluirpend);
        btnExcluirpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener.getOsSelecionada().getRequisicao() == null) {
                    if (mListener.getOsSelecionada().getStatus_check() >= 3) {
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
                                PendenteOSDAO pendenteOSDAO = new PendenteOSDAO();
                                PendenteOS pendenteOS = pendenteOSDAO.procuraPendenteOS(" os = '" + mListener.getOsSelecionada().getCodigo() + "' " +
                                                                                        " and banco = '" + mListener.getOsSelecionada().getBanco() + "' " +
                                                                                        " and item = " + txtMotivo.getText().toString());
                                if (pendenteOS != null) {
                                    pendenteOSDAO.delete(pendenteOS);
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
                    Toast.makeText(getActivity(), "Já foi gerada requisição: "+mListener.getOsSelecionada().getRequisicao().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnSolicitar = (Button) layout.findViewById(R.id.btnSolicitar);
        if (configuracao.getReq().toString().equals("N")) {
            btnSolicitar.setVisibility(View.INVISIBLE);
        }
        else {
            btnSolicitar.setVisibility(View.VISIBLE);
        }
        btnSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener.getOsSelecionada().getRequisicao() == null) {
                    if (mListener.getOsSelecionada().getStatus_check() >= 3) {
                        // Peças Pendentes
                        PendenteOSDAO pendenteOSDAO = new PendenteOSDAO();
                        List<PendenteOS> listPendenteOS = new ArrayList<>();
                        listPendenteOS = pendenteOSDAO.listarPendenteOS(" os = '" +  mListener.getOsSelecionada().getCodigo() + "' " +
                                " and banco = '" +  mListener.getOsSelecionada().getBanco() + "' ");
                        String sPecaspendente = "";
                        if (listPendenteOS.size() > 0) {
                            for (PendenteOS pendenteOS : listPendenteOS) {
                                sPecaspendente = sPecaspendente + "@";
                                sPecaspendente = sPecaspendente + pendenteOS.getCodigo().toString();
                                sPecaspendente = sPecaspendente + ";";
                                sPecaspendente = sPecaspendente + pendenteOS.getQtprod().toString();
                                sPecaspendente = sPecaspendente + "#";
                            }
                            mListener.getOsSelecionada().setObspendente(sPecaspendente);

                            final Dialog dialogmedidor = new Dialog(getActivity());
                            dialogmedidor.setContentView(R.layout.dialog);
                            dialogmedidor.setTitle("Digite o Medidor Total:");
                            final EditText txtMotivo = (EditText) dialogmedidor.findViewById(R.id.txtMotivo);
                            txtMotivo.setText("0");
                            txtMotivo.setInputType(InputType.TYPE_CLASS_NUMBER);
                            dialogmedidor.show();
                            Button btnOk = (Button) dialogmedidor.findViewById(R.id.btnOK);
                            btnOk.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Integer iMedidor = Integer.parseInt(txtMotivo.getText().toString());
                                    if (iMedidor > 0) {
                                        OsDAO osDAO = new OsDAO();
                                        mListener.getOsSelecionada().setContreq(iMedidor);
                                        osDAO.gravaOs(mListener.getOsSelecionada());
                                        sincronizarRequisicao();
                                        dialogmedidor.dismiss();
                                    } else {
                                        Toast.makeText(getActivity(), "È necessário lançar o medidor do equipamento para realizar a operação !", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            Button btnCancel = (Button) dialogmedidor.findViewById(R.id.btnCancel);
                            btnCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialogmedidor.dismiss();
                                }
                            });

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
                    Toast.makeText(getActivity(), "Já foi gerada Requisição : "+mListener.getOsSelecionada().getRequisicao().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (mListener.getOsSelecionada().getSync().equals("S")) {
            btnIncluirpend.setEnabled(false);
            btnExcluirpend.setEnabled(false);
            btnSolicitar.setEnabled(false);
        }


        return layout;
    }

    @Override
    public void onStart() {
        pendenteOSDAO = new PendenteOSDAO();
        listPendenteOS = pendenteOSDAO.listarPendenteOS(" os = '"+mListener.getOsSelecionada().getCodigo()+"'" +
                                                        " and banco = '"+mListener.getOsSelecionada().getBanco()+"'");
        adapter = new AdapterPendenteOS(listPendenteOS , (SelecionaPendenteOS) getActivity());
        recyclerView.setAdapter(adapter);
        super.onStart();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void sincronizarRequisicao() {
        SincRequisicaoAsyncTask task = new SincRequisicaoAsyncTask();
        task.execute();
    }

    class SincRequisicaoAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private boolean bErro = false;
        private String sResult;
        private String sErro;
        private String sRequisicao;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getActivity(), null, "Gerando Requisição");
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
                    sResult = osWSClient.gerarRequisicao(mListener.getOsSelecionada());
                    bErro = !(sResult.substring(0,2).equals("OK"));
                    if (bErro == false) {
                        sRequisicao = sResult.substring(2,8);
                        mListener.getOsSelecionada().setRequisicao(sRequisicao);
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
                Toast.makeText(getActivity(), "Operação realizada com Sucesso ! Requisição : "+sRequisicao, Toast.LENGTH_SHORT).show();
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
