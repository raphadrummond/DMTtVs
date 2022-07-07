package databit.com.br.datamobile.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.domain.HistoricoTEL;
import databit.com.br.datamobile.interfaces.SelecionaHistoricoTEL;
/**
 * Created by Sidney on 12/04/2018.
 */
public class AdapterHistoricoTEL extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<HistoricoTEL> listhistoricoTEL;
    private SelecionaHistoricoTEL listener;

    public AdapterHistoricoTEL(List<HistoricoTEL> listhistoricoTEL, SelecionaHistoricoTEL listener) {
        this.listhistoricoTEL = listhistoricoTEL;
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return listhistoricoTEL.size();
    }

    public static class HistoricoTELViewHolder extends RecyclerView.ViewHolder  {
        public TextView txtOS;
        public TextView txtAbertura;
        public TextView txtPrevisao;
        public TextView txtFechamento;
        public TextView txtContato;
        public TextView txtOperador;
        public TextView txtOperadoratual;
        public TextView txtStatus;
        public TextView txtClassificacao;
        public TextView txtQuant;
        public TextView txtVlrproposta;
        public TextView txtObs;
        public TextView txtResposta;
        public ImageButton imgOS;

        public View layoutRow;

        public HistoricoTELViewHolder(View v) {
            super(v);
            layoutRow = v;
            txtOS = (TextView) v.findViewById(R.id.txtOS);
            txtAbertura = (TextView) v.findViewById(R.id.txtAbertura);
            txtPrevisao = (TextView) v.findViewById(R.id.txtPrevisao);
            txtFechamento = (TextView) v.findViewById(R.id.txtFechamento);
            txtContato = (TextView) v.findViewById(R.id.txtContato);
            txtOperador = (TextView) v.findViewById(R.id.txtOperador);
            txtOperadoratual = (TextView) v.findViewById(R.id.txtOperadoratual);
            txtStatus = (TextView) v.findViewById(R.id.txtStatus);
            txtClassificacao = (TextView) v.findViewById(R.id.txtClassificacao);
            txtQuant = (TextView) v.findViewById(R.id.txtQuant);
            txtVlrproposta = (TextView) v.findViewById(R.id.txtVlrproposta);
            txtObs = (TextView) v.findViewById(R.id.txtObs);
            txtResposta = (TextView) v.findViewById(R.id.txtResposta);
            imgOS = (ImageButton) v.findViewById(R.id.imageOS);
        }

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagem_historico_tel, parent, false);
        HistoricoTELViewHolder vh = new HistoricoTELViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy HH:mm");
        final HistoricoTEL historicoTEL = listhistoricoTEL.get(position);
        HistoricoTELViewHolder h = ((HistoricoTELViewHolder)holder);
        h.txtOS.setText(historicoTEL.getCodigo());
        h.txtAbertura.setText(sdf.format(historicoTEL.getData()));
        h.txtPrevisao.setText(sdf.format(historicoTEL.getPrevisao()));
        try {
            h.txtFechamento.setText(sdf.format(historicoTEL.getConcluido()));
            h.imgOS.setImageResource(R.mipmap.ic_osexecucao);
        } catch (Throwable t) {
            h.txtFechamento.setText("");
            h.imgOS.setImageResource(R.mipmap.ic_ospendente);
        }
        h.txtContato.setText(historicoTEL.getContato());
        h.txtOperador.setText(historicoTEL.getOperador());
        h.txtOperadoratual.setText(historicoTEL.getOperadoratual());
        h.txtStatus.setText(historicoTEL.getNomestatus());
        h.txtClassificacao.setText(historicoTEL.getClassificacao());
        h.txtQuant.setText(historicoTEL.getQtde().toString());
        h.txtVlrproposta.setText(historicoTEL.getVlrproposta().toString());
        h.txtObs.setText(historicoTEL.getAtendimento());
        h.txtResposta.setText(historicoTEL.getResposta());

    }

}
