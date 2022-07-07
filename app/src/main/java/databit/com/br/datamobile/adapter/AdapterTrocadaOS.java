package databit.com.br.datamobile.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.domain.TrocadaOS;
import databit.com.br.datamobile.interfaces.SelecionaTrocadaOS;

/**
 * Created by Sidney on 13/12/2017.
 */
public class AdapterTrocadaOS extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TrocadaOS> listTrocadaOS;
    private SelecionaTrocadaOS listener;

    public AdapterTrocadaOS(List<TrocadaOS> listTrocadaOS, SelecionaTrocadaOS  listener) {
        this.listTrocadaOS = listTrocadaOS;
        this.listener = listener;
    }

    public static class TrocadaOSViewHolder extends RecyclerView.ViewHolder {
        public TextView txtId;
        public TextView txtCodigo;
        public TextView txtReferencia;
        public TextView txtNome;
        public TextView txtQtprod;
        public View layoutRow;

        public TrocadaOSViewHolder(View v) {
            super(v);
            layoutRow = v;
            txtCodigo = (TextView) v.findViewById(R.id.txtCodigo);
            txtReferencia = (TextView) v.findViewById(R.id.txtReferencia);
            txtNome = (TextView) v.findViewById(R.id.txtNome);
            txtQtprod = (TextView) v.findViewById(R.id.txtQtprod);
            txtId = (TextView) v.findViewById(R.id.txtId);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagem_trocadaos, parent, false);
        TrocadaOSViewHolder vh = new TrocadaOSViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final TrocadaOS trocadaOS = listTrocadaOS.get(position);
        TrocadaOSViewHolder h = ((TrocadaOSViewHolder)holder);
        h.txtCodigo.setText(trocadaOS.getCodigo());
        if (trocadaOS.getReferencia() != null) {
            h.txtReferencia.setText(trocadaOS.getReferencia());
        }
        h.txtNome.setText(trocadaOS.getNome());
        h.txtQtprod.setText(trocadaOS.getQtprod().toString());
        h.txtId.setText(trocadaOS.getItem().toString());

    }

    @Override
    public int getItemCount() {
        return listTrocadaOS.size();
    }

}
