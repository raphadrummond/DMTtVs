package databit.com.br.datamobile.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.domain.Item;
import databit.com.br.datamobile.interfaces.SelecionaItem;

public class AdapterItem extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Item> listItem;
    private SelecionaItem listener;

    public AdapterItem(List<Item> listItem, SelecionaItem  listener) {
        this.listItem = listItem;
        this.listener = listener;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView txtCodigo;
        public TextView txtReferencia;
        public TextView txtNome;
        public TextView txtQtprod;
        public View layoutRow;

        public ItemViewHolder(View v) {
            super(v);
            layoutRow = v;
            txtCodigo = (TextView) v.findViewById(R.id.txtCodigo);
            txtReferencia = (TextView) v.findViewById(R.id.txtReferencia);
            txtNome = (TextView) v.findViewById(R.id.txtNome);
            txtQtprod = (TextView) v.findViewById(R.id.txtQtprod);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagem_item, parent, false);
        ItemViewHolder vh = new ItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = listItem.get(position);
        ItemViewHolder h = ((ItemViewHolder)holder);
        h.txtCodigo.setText(item.getProduto());
        h.txtReferencia.setText(item.getReferencia());
        h.txtNome.setText(item.getNome());
        DecimalFormat formato = new DecimalFormat("0.000");
        String sQtprod = item.getQtprod().toString().replace(",",".");
        try {
            h.txtQtprod.setText(formato.parse(sQtprod).toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

}
