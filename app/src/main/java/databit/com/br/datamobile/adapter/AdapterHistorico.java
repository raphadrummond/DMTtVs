package databit.com.br.datamobile.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.domain.Historico;
import databit.com.br.datamobile.interfaces.SelecionaHistorico;


/**
 * Created by Sidney on 11/05/2016.
 */
public class AdapterHistorico extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Historico> listhistorico;
    private SelecionaHistorico listener;

    public AdapterHistorico(List<Historico> listhistorico, SelecionaHistorico listener) {
        this.listhistorico = listhistorico;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return listhistorico.size();
    }


    public static class HistoricoViewHolder extends RecyclerView.ViewHolder {
        public TextView txtOS;
        public TextView txtAbertura;
        public TextView txtFechamento;
        public TextView txtAtendente;
        public TextView txtSolicitante;
        public TextView txtCondicao;
        public TextView txtTecnico;
        public TextView txtContpb;
        public TextView txtContcolor;
        public TextView txtCilindropb;
        public TextView txtCilindrocolor;
        public TextView txtReveladorpb;
        public TextView txtReveladorcolor;
        public TextView txtFusor;
        public TextView txtBelt;
        public TextView txtPreventiva;
        public TextView txtReservatorio;
        public TextView txtObs;

        public View layoutRow;

        public HistoricoViewHolder(View v){
            super(v);
            layoutRow = v;
            txtOS = (TextView) v.findViewById(R.id.txtOS);
            txtAbertura = (TextView) v.findViewById(R.id.txtAbertura);
            txtFechamento = (TextView) v.findViewById(R.id.txtFechamento);
            txtAtendente = (TextView) v.findViewById(R.id.txtAtendente);
            txtSolicitante = (TextView) v.findViewById(R.id.txtSolicitante);
            txtCondicao = (TextView) v.findViewById(R.id.txtCondicao);
            txtTecnico = (TextView) v.findViewById(R.id.txtTecnico);
            txtContpb = (TextView) v.findViewById(R.id.txtContpb);
            txtContcolor = (TextView) v.findViewById(R.id.txtContcolor);
            txtCilindropb = (TextView) v.findViewById(R.id.txtCilindropb);
            txtCilindrocolor = (TextView) v.findViewById(R.id.txtCilindrocolor);
            txtReveladorpb = (TextView) v.findViewById(R.id.txtReveladorpb);
            txtReveladorcolor = (TextView) v.findViewById(R.id.txtReveladorcolor);
            txtFusor = (TextView) v.findViewById(R.id.txtFusor);
            txtBelt = (TextView) v.findViewById(R.id.txtBelt);
            txtPreventiva = (TextView) v.findViewById(R.id.txtPreventiva);
            txtReservatorio = (TextView) v.findViewById(R.id.txtReservatorio);
            txtObs = (TextView) v.findViewById(R.id.txtObs);

        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagem_historico, parent, false);
        HistoricoViewHolder vh = new HistoricoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy HH:mm");
        final Historico historico = listhistorico.get(position);
        HistoricoViewHolder h = ((HistoricoViewHolder)holder);
        h.txtOS.setText(historico.getCodigo());
        h.txtAbertura.setText(sdf.format(historico.getAbertura()));
        h.txtFechamento.setText(sdf.format(historico.getFechamento()));
        h.txtAtendente.setText(historico.getAtendente());
        h.txtSolicitante.setText(historico.getSolicitante());
        h.txtCondicao.setText(historico.getCondicao());
        h.txtTecnico.setText(historico.getTecnico());
        h.txtContpb.setText(historico.getContpb().toString());
        h.txtContcolor.setText(historico.getContcolor().toString());
        h.txtCilindropb.setText(historico.getCilindropb().toString());
        h.txtCilindrocolor.setText(historico.getCilindrocolor().toString());
        h.txtReveladorpb.setText(historico.getReveladorpb().toString());
        h.txtReveladorcolor.setText(historico.getReveladorcolor().toString());
        h.txtFusor.setText(historico.getFusor().toString());
        h.txtBelt.setText(historico.getBelt().toString());
        h.txtPreventiva.setText(historico.getPreventiva().toString());
        h.txtReservatorio.setText(historico.getReservatorio().toString());
        h.txtObs.setText(historico.getObs());

    }

}

