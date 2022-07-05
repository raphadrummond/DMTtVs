package databit.com.br.datamobile.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.dao.CustoDAO;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.domain.Custo;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.domain.Servico;

/**
 * Created by Sidney on 15/12/2017.
 */
public class AdapterServico extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Servico> listServico;
    private RecyclerView mRecyclerView;
    private Context mContext;
    public Os os;

    public AdapterServico(List<Servico> listServico, RecyclerView rv, Os os) {
        this.listServico = listServico;
        this.mRecyclerView = rv;
        this.mContext = rv.getContext();
        this.os = os;
    }

    @Override
    public int getItemCount() {
        return listServico.size();
    }

    public static class ServicoViewHolder extends RecyclerView.ViewHolder {
        public TextView txtServico;
        public TextView txtNome;
        public View layoutRow;

        public ServicoViewHolder(View v) {
            super(v);
            layoutRow = v;
            txtServico = (TextView) v.findViewById(R.id.txtServico);
            txtNome = (TextView) v.findViewById(R.id.txtNome);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagem_servico, parent, false);
        ServicoViewHolder vh = new ServicoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Servico servico = listServico.get(position);
        ServicoViewHolder h = ((ServicoViewHolder)holder);

        h.layoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final OsDAO osDAO = new OsDAO();
                if (os != null) {
                    os = osDAO.procuraOs("banco = '" + os.getBanco().toString() + "' and codigo = '" + os.getCodigo().toString() + "'");
                    final CustoDAO custoDAO = new CustoDAO();
                    final Custo custo;
                    custo = custoDAO.procuraCusto("id = '" + os.getBanco().toString() + os.getCodigo().toString() + servico.getCodigo().toString() + "'");
                    if (custo == null) {
                        final Dialog dialog = new Dialog(((Activity) mContext));
                        dialog.setContentView(R.layout.dialog);
                        dialog.setTitle("Digite o Valor:");
                        final EditText txtMotivo = (EditText) dialog.findViewById(R.id.txtMotivo);
                        txtMotivo.setText("0");
                        txtMotivo.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                        dialog.show();
                        Button btnOk = (Button) dialog.findViewById(R.id.btnOK);
                        btnOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (os.getIdservico() != null) {
                                    os.setIdservico(os.getIdservico() + 1);
                                } else {
                                    os.setIdservico(1);
                                }
                                Float fvalor;

                                try {
                                    fvalor = Float.parseFloat(txtMotivo.getText().toString());
                                    osDAO.createOrUpdate(os);
                                    Custo custo2 = new Custo();
                                    custo2.setId(os.getBanco().toString() + os.getCodigo().toString() + servico.getCodigo().toString());
                                    custo2.setOs(os.getCodigo().toString());
                                    custo2.setBanco(os.getBanco().toString());
                                    custo2.setCodigo(servico.getCodigo().toString());
                                    custo2.setNome(servico.getNome().toString());
                                    custo2.setItem(os.getIdservico());
                                    custo2.setValor(fvalor);
                                    custoDAO.createOrUpdate(custo2);
                                    Toast.makeText(mContext, "Serviço salvo com Sucesso !", Toast.LENGTH_SHORT).show();
                                    ((Activity) mContext).finish();
                                } catch (Exception e) {
                                    Toast.makeText(mContext, "Foi registrado um valor inválido, favor utilizar ponto para separar casas decimais!", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
                        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
                        btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                    else {
                        Toast.makeText(mContext, "Item já lançado anteriormente !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        h.txtServico.setText(servico.getCodigo());
        h.txtNome.setText(servico.getNome());

    }

}
