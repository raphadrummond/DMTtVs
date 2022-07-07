package databit.com.br.datamobile.fragment;

import android.app.Dialog;
import android.content.Intent;
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

import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.activity.ServicoActivity;
import databit.com.br.datamobile.adapter.AdapterCusto;
import databit.com.br.datamobile.dao.CustoDAO;
import databit.com.br.datamobile.domain.Custo;
import databit.com.br.datamobile.interfaces.SelecionaOs;
import databit.com.br.datamobile.interfaces.SelecionaCusto;


public class CustoFragment extends Fragment {

    private SelecionaOs mListener;
    private Button btnIncluirserv;
    private Button btnExcluirserv;
    private RecyclerView.Adapter adapter;
    private List<Custo> listCusto;
    private CustoDAO custoDAO;
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View layout = inflater.inflate(R.layout.content_custo_fragment, container, false);
        FragmentActivity c = getActivity();

        mListener = (SelecionaOs) getActivity();

        recyclerView = (RecyclerView) layout.findViewById(R.id.custo_recycler_view);

        LinearLayoutManager layoutManager = new  LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        custoDAO = new CustoDAO();
        listCusto = custoDAO.listarCusto(" os = '"+mListener.getOsSelecionada().getCodigo()+"'" +
                                         " and banco = '"+mListener.getOsSelecionada().getBanco()+"'");
        adapter = new AdapterCusto(listCusto , (SelecionaCusto) getActivity());
        recyclerView.setAdapter(adapter);

        btnIncluirserv = (Button) layout.findViewById(R.id.btnIncluirserv);
        btnIncluirserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ServicoActivity.class);
                intent.putExtra("os", mListener.getOsSelecionada());
                startActivity(intent);
            }
        });

        btnExcluirserv = (Button) layout.findViewById(R.id.btnExcluirserv);
        btnExcluirserv.setOnClickListener(new View.OnClickListener() {
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
                        CustoDAO custoDAO = new CustoDAO();
                        Custo custo = custoDAO.procuraCusto(" os = '" + mListener.getOsSelecionada().getCodigo() + "' " +
                                                            " and banco = '" + mListener.getOsSelecionada().getBanco() + "' " +
                                                            " and item = " + txtMotivo.getText().toString());
                        if (custo != null) {
                            custoDAO.delete(custo);
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
            btnIncluirserv.setEnabled(false);
            btnExcluirserv.setEnabled(false);
        }
        return layout;
    }

    @Override
    public void onStart() {
        custoDAO = new CustoDAO();
        listCusto = custoDAO.listarCusto(" os = '"+mListener.getOsSelecionada().getCodigo()+"'" +
                                         " and banco = '"+mListener.getOsSelecionada().getBanco()+"'");
        adapter = new AdapterCusto(listCusto , (SelecionaCusto) getActivity());
        recyclerView.setAdapter(adapter);
        super.onStart();
    }
}
