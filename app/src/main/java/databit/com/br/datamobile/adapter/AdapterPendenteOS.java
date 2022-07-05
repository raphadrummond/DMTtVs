package databit.com.br.datamobile.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.domain.PendenteOS;
import databit.com.br.datamobile.interfaces.SelecionaPendenteOS;

/**
 * Created by Sidney on 13/12/2017.
 */
public class AdapterPendenteOS extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<PendenteOS> listPendenteOS;
    private SelecionaPendenteOS listener;

    public AdapterPendenteOS(List<PendenteOS> listPendenteOS, SelecionaPendenteOS  listener) {
        this.listPendenteOS = listPendenteOS;
        this.listener = listener;
    }

    public static class PendenteOSViewHolder extends RecyclerView.ViewHolder {
        public TextView txtId;
        public TextView txtCodigo;
        public TextView txtReferencia;
        public TextView txtNome;
        public TextView txtQtprod;
        public View layoutRow;

        public PendenteOSViewHolder(View v) {
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagem_pendenteos, parent, false);
        PendenteOSViewHolder vh = new PendenteOSViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final PendenteOS pendenteOS = listPendenteOS.get(position);
        PendenteOSViewHolder h = ((PendenteOSViewHolder)holder);
        h.txtCodigo.setText(pendenteOS.getCodigo());
        if (pendenteOS.getReferencia() != null) {
            h.txtReferencia.setText(pendenteOS.getReferencia());
        }
        h.txtNome.setText(pendenteOS.getNome());
        h.txtQtprod.setText(pendenteOS.getQtprod().toString());
        h.txtId.setText(pendenteOS.getItem().toString());

    }

    @Override
    public int getItemCount() {
        return listPendenteOS.size();
    }

}
