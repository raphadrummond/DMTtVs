package databit.com.br.datamobile.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.domain.OsDefeito;
import databit.com.br.datamobile.interfaces.SelecionaOsDefeito;

/**
 * Created by Sidney on 12/05/2016.
 */
public class AdapterOsDefeito extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<OsDefeito> listosDefeito;
    private SelecionaOsDefeito listener;

    public AdapterOsDefeito(List<OsDefeito> listosDefeito, SelecionaOsDefeito listener) {
        this.listosDefeito = listosDefeito;
        this.listener = listener;
    }


    public static class OsDefeitoViewHolder extends RecyclerView.ViewHolder {
        public TextView txtCadastro;
        public TextView txtUsuario;
        public TextView txtCodigo;
        public TextView txtNome;
        public TextView txtObs;
        public TextView txtSolucao;
        public View layoutRow;


        public OsDefeitoViewHolder(View v) {
            super(v);
            layoutRow = v;
            txtCadastro = (TextView) v.findViewById(R.id.txtCadastro);
            txtUsuario = (TextView) v.findViewById(R.id.txtUsuario);
            txtCodigo = (TextView) v.findViewById(R.id.txtCodigo);
            txtNome = (TextView) v.findViewById(R.id.txtNome);
            txtObs = (TextView) v.findViewById(R.id.txtObs);
            txtSolucao = (TextView) v.findViewById(R.id.txtSolucao);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagem_os_defeito, parent, false);
        OsDefeitoViewHolder vh = new OsDefeitoViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy HH:mm");
        final OsDefeito osDefeito = listosDefeito.get(position);
        OsDefeitoViewHolder h = ((OsDefeitoViewHolder)holder);
        h.txtCadastro.setText(sdf.format(osDefeito.getCadastro()));
        h.txtCodigo.setText(osDefeito.getCodigo());
        h.txtNome.setText(osDefeito.getNomedefeito());
        h.txtObs.setText(osDefeito.getObs());
        h.txtUsuario.setText(osDefeito.getOperador());
        h.txtSolucao.setText(osDefeito.getSolucao());

    }

    @Override
    public int getItemCount() {
        return listosDefeito.size();
    }
}
