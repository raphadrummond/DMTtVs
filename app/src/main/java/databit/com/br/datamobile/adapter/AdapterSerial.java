package databit.com.br.datamobile.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.domain.Serial;
import databit.com.br.datamobile.interfaces.SelecionaSerial;

public class AdapterSerial extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Serial> listSerial;
    private SelecionaSerial listener;

    public AdapterSerial(List<Serial> listSerial, SelecionaSerial  listener) {
        this.listSerial = listSerial;
        this.listener = listener;
    }

    public static class SerialViewHolder extends RecyclerView.ViewHolder {
        public TextView txtCodigo;
        public TextView txtReferencia;
        public TextView txtNome;
        public TextView txtSerial;
        public TextView txtPat;
        public View layoutRow;

        public SerialViewHolder(View v) {
            super(v);
            layoutRow = v;
            txtCodigo = (TextView) v.findViewById(R.id.txtCodigo);
            txtReferencia = (TextView) v.findViewById(R.id.txtReferencia);
            txtNome = (TextView) v.findViewById(R.id.txtNome);
            txtSerial = (TextView) v.findViewById(R.id.txtSerialitem);
            txtPat = (TextView) v.findViewById(R.id.txtPat);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagem_serial, parent, false);
        SerialViewHolder vh = new SerialViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Serial serial = listSerial.get(position);
        SerialViewHolder h = ((SerialViewHolder)holder);
        h.txtCodigo.setText(serial.getProduto());
        h.txtReferencia.setText(serial.getReferencia());
        h.txtNome.setText(serial.getNome());
        h.txtSerial.setText(serial.getNumserie());
        h.txtPat.setText(serial.getPat());

    }

    @Override
    public int getItemCount() {
        return listSerial.size();
    }

}
