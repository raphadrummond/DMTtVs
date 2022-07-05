package databit.com.br.datamobile.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.domain.Logsinc;
import databit.com.br.datamobile.interfaces.SelecionaLog;


/**
 * Created by Sidney on 11/05/2016.
 */
public class AdapterLog extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Logsinc> listlog;
    private SelecionaLog listener;

    public AdapterLog(List<Logsinc> listlog, SelecionaLog listener) {
        this.listlog = listlog;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return listlog.size();
    }


    public static class LogViewHolder extends RecyclerView.ViewHolder {
        public TextView txtData;
        public TextView txtEvento;
        public TextView txtRetorno;
        public View layoutRow;
        public ImageButton imgOK;

        public LogViewHolder(View v){
            super(v);
            layoutRow = v;
            txtData = (TextView) v.findViewById(R.id.txtDataevento);
            txtEvento = (TextView) v.findViewById(R.id.txtEvento);
            txtRetorno = (TextView) v.findViewById(R.id.txtResultado);
            imgOK = (ImageButton) v.findViewById(R.id.imageEvento);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagem_log, parent, false);
        LogViewHolder vh = new LogViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy HH:mm");
        final Logsinc logsinc = listlog.get(position);
        LogViewHolder h = ((LogViewHolder)holder);
        h.txtData.setText(sdf.format(logsinc.getData()));
        h.txtEvento.setText(logsinc.getEvento());
        h.txtRetorno.setText(logsinc.getRetorno());
        if (logsinc.getErro().equals(0)) {
            h.imgOK.setImageResource(R.mipmap.ic_osexecucao);
        }
        else  {
            h.imgOK.setImageResource(R.mipmap.ic_osatrasado);
        }


    }

}

