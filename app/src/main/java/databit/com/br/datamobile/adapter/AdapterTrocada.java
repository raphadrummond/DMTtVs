package databit.com.br.datamobile.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.domain.Trocada;
import databit.com.br.datamobile.interfaces.SelecionaTrocada;

/**
 * Created by Sidney on 13/05/2016.
 */
public class AdapterTrocada extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Trocada> listTrocada;
    private SelecionaTrocada listener;

    public AdapterTrocada(List<Trocada> listTrocada, SelecionaTrocada listener) {
        this.listTrocada = listTrocada;
        this.listener = listener;
    }

    public static class TrocadaViewHolder extends RecyclerView.ViewHolder {
        public TextView txtCodigo;
        public TextView txtCadastro;
        public TextView txtOperador;
        public TextView txtProduto;
        public TextView txtReferencia;
        public TextView txtAuxiliar;
        public TextView txtNome;
        public TextView txtQtprod;
        public TextView txtContador;
        public TextView txtDataos;
        public View layoutRow;

        public TrocadaViewHolder(View v) {
            super(v);
            layoutRow = v;
            txtCodigo = (TextView) v.findViewById(R.id.txtCodigo);
            txtCadastro = (TextView) v.findViewById(R.id.txtCadastro);
            txtOperador = (TextView) v.findViewById(R.id.txtOperador);
            txtProduto = (TextView) v.findViewById(R.id.txtProduto);
            txtReferencia = (TextView) v.findViewById(R.id.txtReferencia);
            txtAuxiliar = (TextView) v.findViewById(R.id.txtAuxiliar);
            txtNome = (TextView) v.findViewById(R.id.txtNome);
            txtQtprod = (TextView) v.findViewById(R.id.txtQtprod);
            txtContador = (TextView) v.findViewById(R.id.txtContador);
            txtDataos = (TextView) v.findViewById(R.id.txtDataos);
        }

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagem_trocada, parent, false);
        TrocadaViewHolder vh = new TrocadaViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy HH:mm");
        final Trocada trocada = listTrocada.get(position);
        TrocadaViewHolder h = ((TrocadaViewHolder)holder);
        h.txtCodigo.setText(trocada.getNumos());
        h.txtCadastro.setText(sdf.format(trocada.getCadastro()));
        h.txtOperador.setText(trocada.getOperador());
        h.txtProduto.setText(trocada.getProduto());
        h.txtReferencia.setText(trocada.getReferencia());
        h.txtAuxiliar.setText(trocada.getCodauxiliar());
        h.txtNome.setText(trocada.getNomeprod());
        h.txtQtprod.setText(trocada.getQtprod().toString());
        h.txtContador.setText(trocada.getContador().toString());
        h.txtDataos.setText(sdf.format(trocada.getDataos()));

    }

    @Override
    public int getItemCount() {
        return listTrocada.size();
    }
}
