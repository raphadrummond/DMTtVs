package databit.com.br.datamobile.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.domain.Pendente;
import databit.com.br.datamobile.interfaces.SelecionaPendente;

/**
 * Created by Sidney on 12/05/2016.
 */
public class AdapterPendente extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Pendente> listPendente;
    private SelecionaPendente listener;

    public AdapterPendente(List<Pendente> listPendente, SelecionaPendente  listener) {
        this.listPendente = listPendente;
        this.listener = listener;
    }

    public static class PendenteViewHolder extends RecyclerView.ViewHolder {
        public TextView txtCadastro;
        public TextView txtOperador;
        public TextView txtCodigo;
        public TextView txtReferencia;
        public TextView txtBarras;
        public TextView txtNome;
        public TextView txtQtprod;
        public TextView txtCodauxiliar;
        public View layoutRow;

        public PendenteViewHolder(View v) {
            super(v);
            layoutRow = v;
            txtCadastro = (TextView) v.findViewById(R.id.txtCadastro);
            txtOperador = (TextView) v.findViewById(R.id.txtOperador);
            txtCodigo = (TextView) v.findViewById(R.id.txtCodigo);
            txtReferencia = (TextView) v.findViewById(R.id.txtReferencia);
            txtBarras = (TextView) v.findViewById(R.id.txtBarras);
            txtNome = (TextView) v.findViewById(R.id.txtNome);
            txtQtprod = (TextView) v.findViewById(R.id.txtQtprod);
            txtCodauxiliar = (TextView) v.findViewById(R.id.txtCodauxiliar);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagem_pendente, parent, false);
        PendenteViewHolder vh = new PendenteViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy HH:mm");
        final Pendente pendente = listPendente.get(position);
        PendenteViewHolder h = ((PendenteViewHolder)holder);
        h.txtCadastro.setText(sdf.format(pendente.getCadastro()));
        h.txtCodigo.setText(pendente.getCodigo());
        h.txtOperador.setText(pendente.getOperador());
        h.txtReferencia.setText(pendente.getReferencia());
        h.txtBarras.setText(pendente.getCodbarras());
        h.txtNome.setText(pendente.getNomeprod());
        h.txtQtprod.setText(pendente.getQtprod().toString());
        h.txtCodauxiliar.setText(pendente.getCodbarras());

    }

    @Override
    public int getItemCount() {
        return listPendente.size();
    }
}
