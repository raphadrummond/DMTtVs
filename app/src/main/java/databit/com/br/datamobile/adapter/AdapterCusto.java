package databit.com.br.datamobile.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.domain.Custo;
import databit.com.br.datamobile.interfaces.SelecionaCusto;

/**
 * Created by Sidney on 13/12/2017.
 */
public class AdapterCusto extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Custo> listcusto;
    private SelecionaCusto listener;

    public AdapterCusto(List<Custo> listcusto, SelecionaCusto  listener) {
        this.listcusto = listcusto;
        this.listener = listener;
    }

    public static class custoViewHolder extends RecyclerView.ViewHolder {
        public TextView txtId;
        public TextView txtCodigo;
        public TextView txtNome;
        public TextView txtValor;
        public View layoutRow;

        public custoViewHolder(View v) {
            super(v);
            layoutRow = v;
            txtCodigo = (TextView) v.findViewById(R.id.txtCodigo);
            txtNome = (TextView) v.findViewById(R.id.txtNome);
            txtValor = (TextView) v.findViewById(R.id.txtValor);
            txtId = (TextView) v.findViewById(R.id.txtId);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagem_custo, parent, false);
        custoViewHolder vh = new custoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Custo custo = listcusto.get(position);
        custoViewHolder h = ((custoViewHolder)holder);
        h.txtCodigo.setText(custo.getCodigo());
        h.txtNome.setText(custo.getNome());
        h.txtValor.setText(custo.getValor().toString());
        h.txtId.setText(custo.getItem().toString());

    }

    @Override
    public int getItemCount() {
        return listcusto.size();
    }

}
