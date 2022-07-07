package databit.com.br.datamobile.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.domain.Arquivo;
import databit.com.br.datamobile.interfaces.SelecionaArquivo;

/**
 * Created by Sidney on 17/04/2018.
 */
public class AdapterArquivo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Arquivo> listArquivo;
    private SelecionaArquivo listener;
    private Context mContext;

    public AdapterArquivo(List<Arquivo> listArquivo, SelecionaArquivo  listener, RecyclerView rv) {
        this.listArquivo = listArquivo;
        this.listener = listener;
        this.mContext = rv.getContext();
    }

    public static class ArquivoViewHolder extends RecyclerView.ViewHolder {
        public TextView txtId;
        public TextView txtArquivo;
        public TextView txtDiretorio;
        public View layoutRow;

        public ArquivoViewHolder(View v) {
            super(v);
            layoutRow = v;
            txtId = (TextView) v.findViewById(R.id.txtIDArquivo);
            txtArquivo = (TextView) v.findViewById(R.id.txtArquivo);
            txtDiretorio = (TextView) v.findViewById(R.id.txtPasta);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagem_arquivo, parent, false);
        ArquivoViewHolder vh = new ArquivoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Arquivo arquivo = listArquivo.get(position);
        ArquivoViewHolder h = ((ArquivoViewHolder)holder);
        h.txtArquivo.setText(arquivo.getArquivo());
        h.txtDiretorio.setText(arquivo.getDiretorio());
        h.txtId.setText(arquivo.getId().substring(11, 12));
        /*
        h.layoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arquivo != null) {
                    Uri uri = Uri.parse(arquivo.getDiretorio().toString());
                    File file = new File(FileOpen.getPath(uri,mContext));
                    try {
                        FileOpen.openFile(mContext, file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        */


    }

    @Override
    public int getItemCount() {
        return listArquivo.size();
    }



}
