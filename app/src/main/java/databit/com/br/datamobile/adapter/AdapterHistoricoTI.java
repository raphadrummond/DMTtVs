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
import databit.com.br.datamobile.domain.HistoricoTI;
import databit.com.br.datamobile.interfaces.SelecionaHistoricoTI;

/**
 * Created by Sidney on 09/04/2018.
 */
public class AdapterHistoricoTI  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HistoricoTI>  listhistoricoTI;
    private SelecionaHistoricoTI listener;

    public AdapterHistoricoTI(List<HistoricoTI> listhistoricoTI, SelecionaHistoricoTI listener) {
        this.listhistoricoTI = listhistoricoTI;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return listhistoricoTI.size();
    }

    public static class HistoricoTIViewHolder extends RecyclerView.ViewHolder {
        public TextView txtOS;
        public TextView txtAbertura;
        public TextView txtExecucao;
        public TextView txtNomecliente;
        public TextView txtContato;
        public TextView txtTecnico;
        public TextView txtHorastrab;
        public TextView txtHorascom;
        public TextView txtHorasfim;
        public TextView txtNomeoperacao;
        public TextView txtProjeto;
        public TextView txtModulo;
        public TextView txtObs;
        public TextView txtDefeito;
        public TextView txtLaudo;
        public ImageButton imgOS;

        public View layoutRow;

        public HistoricoTIViewHolder(View v) {
            super(v);
            layoutRow = v;
            txtOS = (TextView) v.findViewById(R.id.txtOS);
            txtAbertura = (TextView) v.findViewById(R.id.txtAbertura);
            txtExecucao = (TextView) v.findViewById(R.id.txtExecucao);
            txtNomecliente = (TextView) v.findViewById(R.id.txtNomeCliente);
            txtContato = (TextView) v.findViewById(R.id.txtContato);
            txtTecnico = (TextView) v.findViewById(R.id.txtTecnico);
            txtHorastrab = (TextView) v.findViewById(R.id.txtHorastrab);
            txtHorascom = (TextView) v.findViewById(R.id.txtHorascom);
            txtHorasfim = (TextView) v.findViewById(R.id.txtHorasfim);
            txtNomeoperacao = (TextView) v.findViewById(R.id.txtNomeoperacao);
            txtProjeto = (TextView) v.findViewById(R.id.txtProjeto);
            txtModulo = (TextView) v.findViewById(R.id.txtModulo);
            txtObs = (TextView) v.findViewById(R.id.txtObs);
            txtDefeito = (TextView) v.findViewById(R.id.txtDefeito);
            txtLaudo = (TextView) v.findViewById(R.id.txtLaudo);
            imgOS = (ImageButton) v.findViewById(R.id.imageOS);

        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagem_historico_ti, parent, false);
        HistoricoTIViewHolder vh = new HistoricoTIViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat sdf2 =  new SimpleDateFormat("dd/MM/yyyy");
        final HistoricoTI historicoTI = listhistoricoTI.get(position);
        HistoricoTIViewHolder h = ((HistoricoTIViewHolder)holder);
        h.txtOS.setText(historicoTI.getCodigo());
        h.txtAbertura.setText(sdf.format(historicoTI.getData()));
        h.txtExecucao.setText(sdf2.format(historicoTI.getExecucao()));
        h.txtNomecliente.setText(historicoTI.getNomecli());
        h.txtContato.setText(historicoTI.getContato());
        h.txtTecnico.setText(historicoTI.getNometec());
        h.txtHorastrab.setText(historicoTI.getHorastrab());
        h.txtHorascom.setText(historicoTI.getHorascom());
        h.txtHorasfim.setText(historicoTI.getHorasfim());
        h.txtNomeoperacao.setText(historicoTI.getNomeoperacao());
        h.txtProjeto.setText(historicoTI.getProjeto());
        h.txtModulo.setText(historicoTI.getNomemodulo());
        h.txtObs.setText(historicoTI.getObs());
        h.txtDefeito.setText(historicoTI.getDefeito());
        h.txtLaudo.setText(historicoTI.getLaudo());

        if ((historicoTI.getNomeoperacao().equals("Laboratorio")) || (historicoTI.getNomeoperacao().equals("Treinamento"))) {
            h.imgOS.setImageResource(R.mipmap.ic_ospendente);
        }
        else if ((historicoTI.getNomeoperacao().equals("Remoto")) || (historicoTI.getNomeoperacao().equals("At. Sistemas"))) {
            h.imgOS.setImageResource(R.mipmap.ic_osexecucao);
        }
        else if (historicoTI.getNomeoperacao().equals("Presencial")) {
            h.imgOS.setImageResource(R.mipmap.ic_osconcluido);
        }
        else if (historicoTI.getNomeoperacao().equals("Projetos")) {
            h.imgOS.setImageResource(R.mipmap.ic_osatrasado);
        }



    }
}
