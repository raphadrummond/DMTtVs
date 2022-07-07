package databit.com.br.datamobile.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.domain.Recolha;
import databit.com.br.datamobile.interfaces.SelecionaRecolha;

/**
 * Created by Sidney on 16/04/2018.
 */
public class AdapterRecolha extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<Recolha> listRecolha;
    private SelecionaRecolha listener;

    public AdapterRecolha(List<Recolha> listRecolha, SelecionaRecolha  listener) {
        this.listRecolha = listRecolha;
        this.listener = listener;
    }

    public static class RecolhaViewHolder extends RecyclerView.ViewHolder {
        public TextView txtIdRecolha;
        public TextView txtCodigo;
        public TextView txtReferencia;
        public TextView txtNome;
        public TextView txtQtprod;
        public View layoutRow;

        public RecolhaViewHolder(View v) {
            super(v);
            layoutRow = v;
            txtCodigo = (TextView) v.findViewById(R.id.txtCodigo);
            txtReferencia = (TextView) v.findViewById(R.id.txtReferencia);
            txtNome = (TextView) v.findViewById(R.id.txtNome);
            txtQtprod = (TextView) v.findViewById(R.id.txtQtprod);
            txtIdRecolha = (TextView) v.findViewById(R.id.txtIdRecolha);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagem_recolha, parent, false);
        RecolhaViewHolder vh = new RecolhaViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Recolha recolha = listRecolha.get(position);
        RecolhaViewHolder h = ((RecolhaViewHolder)holder);
        h.txtCodigo.setText(recolha.getCodigo());
        if (recolha.getReferencia() != null){
            h.txtReferencia.setText(recolha.getReferencia());
        }
        h.txtNome.setText(recolha.getNome());
        h.txtQtprod.setText(recolha.getQtprod().toString());
        h.txtIdRecolha.setText(recolha.getItem().toString());

    }

    @Override
    public int getItemCount() {
        return listRecolha.size();
    }

}
