package databit.com.br.datamobile.fragment;

import android.app.Dialog;
import android.content.Intent;
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

import java.io.Serializable;
import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.activity.ProdutoActivity;
import databit.com.br.datamobile.adapter.AdapterTrocadaOS;
import databit.com.br.datamobile.dao.TrocadaOSDAO;
import databit.com.br.datamobile.domain.Trocada;
import databit.com.br.datamobile.domain.TrocadaOS;
import databit.com.br.datamobile.interfaces.SelecionaOs;
import databit.com.br.datamobile.interfaces.SelecionaTrocadaOS;


public class TrocadaOSFragment extends Fragment {

    private SelecionaOs mListener;
    private Button btnIncluirtro;
    private Button btnExcluirtro;
    private RecyclerView.Adapter adapter;
    private List<TrocadaOS> listTrocadaOS;
    private TrocadaOSDAO trocadaOSDAO;
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View layout = inflater.inflate(R.layout.content_trocadaos_fragment, container, false);
        FragmentActivity c = getActivity();

        mListener = (SelecionaOs) getActivity();

        recyclerView = (RecyclerView) layout.findViewById(R.id.trocadaos_recycler_view);

        LinearLayoutManager layoutManager = new  LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        trocadaOSDAO = new TrocadaOSDAO();
        listTrocadaOS = trocadaOSDAO.listarTrocadaOS(" os = '"+mListener.getOsSelecionada().getCodigo()+"'" +
                " and banco = '"+mListener.getOsSelecionada().getBanco()+"'");
        adapter = new AdapterTrocadaOS(listTrocadaOS , (SelecionaTrocadaOS) getActivity());
        recyclerView.setAdapter(adapter);

        btnIncluirtro = (Button) layout.findViewById(R.id.btnIncluirtro);
        btnIncluirtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProdutoActivity.class);
                intent.putExtra("os", mListener.getOsSelecionada());
                intent.putExtra("opcao", 2);
                startActivity(intent);
            }
        });

        btnExcluirtro = (Button) layout.findViewById(R.id.btnExcluirtro);
        btnExcluirtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        TrocadaOSDAO trocadaOSDAO = new TrocadaOSDAO();
                        TrocadaOS trocadaOS = trocadaOSDAO.procuraTrocadaOS(" os = '" + mListener.getOsSelecionada().getCodigo() + "' " +
                                                                            " and banco = '" + mListener.getOsSelecionada().getBanco() + "' " +
                                                                            " and item = " + txtMotivo.getText().toString());
                        if (trocadaOS != null) {
                            trocadaOSDAO.delete(trocadaOS);
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
        });
        if (mListener.getOsSelecionada().getSync().equals("S")) {
            btnIncluirtro.setEnabled(false);
            btnExcluirtro.setEnabled(false);
        }
        return layout;
    }

    @Override
    public void onStart() {
        trocadaOSDAO = new TrocadaOSDAO();
        listTrocadaOS = trocadaOSDAO.listarTrocadaOS(" os = '"+mListener.getOsSelecionada().getCodigo()+"'" +
                                                     " and banco = '"+mListener.getOsSelecionada().getBanco()+"'");
        adapter = new AdapterTrocadaOS(listTrocadaOS , (SelecionaTrocadaOS) getActivity());
        recyclerView.setAdapter(adapter);
        super.onStart();
    }
}
