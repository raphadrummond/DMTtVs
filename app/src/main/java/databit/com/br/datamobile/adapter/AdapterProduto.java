package databit.com.br.datamobile.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
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
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.dao.PendenteOSDAO;
import databit.com.br.datamobile.dao.RecolhaDAO;
import databit.com.br.datamobile.dao.TrocadaOSDAO;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.domain.PendenteOS;
import databit.com.br.datamobile.domain.Produto;
import databit.com.br.datamobile.domain.Recolha;
import databit.com.br.datamobile.domain.TrocadaOS;
import databit.com.br.datamobile.interfaces.SelecionaOs;

/**
 * Created by Sidney on 14/12/2017.
 */
public class AdapterProduto extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SelecionaOs {

    private List<Produto> listProduto;
    private RecyclerView mRecyclerView;
    private Context mContext;
    public Os os;
    private Integer iOpcao;


    public AdapterProduto(List<Produto> listProduto, RecyclerView rv, Os os, Integer iOpcao) {
        this.listProduto = listProduto;
        this.mRecyclerView = rv;
        this.mContext = rv.getContext();
        this.os = os;
        this.iOpcao = iOpcao;
    }

    @Override
    public int getItemCount() {
        return listProduto.size();
    }

    @Override
    public void onOsSelecionada(Os os) {

    }

    @Override
    public Os getOsSelecionada() {
        return null;
    }

    public static class ProdutoViewHolder extends RecyclerView.ViewHolder {
        public TextView txtProduto;
        public TextView txtReferencia;
        public TextView txtAuxiliar;
        public TextView txtNome;
        public View layoutRow;

        public ProdutoViewHolder(View v) {
            super(v);
            layoutRow = v;
            txtProduto = (TextView) v.findViewById(R.id.txtProduto);
            txtReferencia = (TextView) v.findViewById(R.id.txtReferencia);
            txtAuxiliar = (TextView) v.findViewById(R.id.txtAuxiliar);
            txtNome = (TextView) v.findViewById(R.id.txtNome);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagem_produto, parent, false);
        ProdutoViewHolder vh = new ProdutoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Produto produto = listProduto.get(position);
        ProdutoViewHolder h = ((ProdutoViewHolder)holder);

        h.layoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final OsDAO osDAO = new OsDAO();
                if (os != null) {
                    os = osDAO.procuraOs("banco = '" + os.getBanco().toString() + "' and codigo = '" + os.getCodigo().toString() + "'");
                    switch (iOpcao) {
                        case 1:{ // Peças Pendentes
                            final PendenteOSDAO pendenteOSDAO = new PendenteOSDAO();
                            PendenteOS pendenteOS;
                            pendenteOS = pendenteOSDAO.procuraPendenteOS("id = '" + os.getBanco().toString() + os.getCodigo().toString() + produto.getCodigo().toString() + "'");
                            if (pendenteOS == null) {
                                final Dialog dialog = new Dialog(((Activity) mContext));
                                dialog.setContentView(R.layout.dialog);
                                dialog.setTitle("Digite a Quantidade:");
                                final EditText txtMotivo = (EditText) dialog.findViewById(R.id.txtMotivo);
                                txtMotivo.setText("1");
                                txtMotivo.setInputType(InputType.TYPE_CLASS_NUMBER);
                                dialog.show();
                                Button btnOk = (Button) dialog.findViewById(R.id.btnOK);
                                btnOk.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (os.getIdpendente() != null) {
                                            os.setIdpendente(os.getIdpendente() + 1);
                                        } else {
                                            os.setIdpendente(1);
                                        }
                                        osDAO.createOrUpdate(os);
                                        PendenteOS pendenteOS2 = new PendenteOS();
                                        pendenteOS2.setId(os.getBanco().toString() + os.getCodigo().toString() + produto.getCodigo().toString());
                                        pendenteOS2.setCodigo(produto.getCodigo().toString());
                                        pendenteOS2.setBanco(os.getBanco().toString());
                                        pendenteOS2.setItem(os.getIdpendente());
                                        pendenteOS2.setNome(produto.getNome().toString());
                                        pendenteOS2.setOs(os.getCodigo().toString());
                                        pendenteOS2.setQtprod(Integer.parseInt(txtMotivo.getText().toString()));
                                        pendenteOSDAO.createOrUpdate(pendenteOS2);
                                        if (produto.getReferencia() != null) {
                                            pendenteOS2.setReferencia(produto.getReferencia().toString());
                                        }
                                        Toast.makeText(mContext, "Peça pendente Salva com Sucesso !", Toast.LENGTH_SHORT).show();
                                        ((Activity) mContext).finish();
                                    }
                                });
                                Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
                                btnCancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                            } else {
                                Toast.makeText(mContext, "Item já lançado anteriormente !", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        }
                        case 2:{ // Peças Trocadas
                            final TrocadaOSDAO trocadaOSDAO = new TrocadaOSDAO();
                            TrocadaOS trocadaOS;
                            trocadaOS = trocadaOSDAO.procuraTrocadaOS("id = '" + os.getBanco().toString() + os.getCodigo().toString() + produto.getCodigo().toString() + "'");
                            if (trocadaOS == null) {
                                final Dialog dialog = new Dialog(((Activity) mContext));
                                dialog.setContentView(R.layout.dialog);
                                dialog.setTitle("Digite a Quantidade:");
                                final EditText txtMotivo = (EditText) dialog.findViewById(R.id.txtMotivo);
                                txtMotivo.setText("1");
                                txtMotivo.setInputType(InputType.TYPE_CLASS_NUMBER);
                                dialog.show();
                                Button btnOk = (Button) dialog.findViewById(R.id.btnOK);
                                btnOk.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (os.getIdtrocada() != null) {
                                            os.setIdtrocada(os.getIdtrocada() + 1);
                                        } else {
                                            os.setIdtrocada(1);
                                        }
                                        osDAO.createOrUpdate(os);
                                        TrocadaOS trocadaOS2 = new TrocadaOS();
                                        trocadaOS2.setId(os.getBanco().toString() + os.getCodigo().toString() + produto.getCodigo().toString());
                                        trocadaOS2.setCodigo(produto.getCodigo().toString());
                                        trocadaOS2.setBanco(os.getBanco().toString());
                                        trocadaOS2.setItem(os.getIdtrocada());
                                        trocadaOS2.setNome(produto.getNome().toString());
                                        trocadaOS2.setOs(os.getCodigo().toString());
                                        trocadaOS2.setQtprod(Integer.parseInt(txtMotivo.getText().toString()));
                                        if (produto.getReferencia() != null) {
                                            trocadaOS2.setReferencia(produto.getReferencia().toString());
                                        }
                                        trocadaOSDAO.createOrUpdate(trocadaOS2);
                                        Toast.makeText(mContext, "Peça trocada Salva com Sucesso !", Toast.LENGTH_SHORT).show();
                                        ((Activity) mContext).finish();
                                    }
                                });
                                Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
                                btnCancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                            } else {
                                Toast.makeText(mContext, "Item já lançado anteriormente !", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        }
                        case 3:{ // Recolha
                            final RecolhaDAO recolhaDAO = new RecolhaDAO();
                            Recolha recolha;
                            recolha = recolhaDAO.procuraRecolha("id = '" + os.getBanco().toString() + os.getCodigo().toString() + produto.getCodigo().toString() + "'");
                            if (recolha == null) {
                                final Dialog dialog = new Dialog(((Activity) mContext));
                                dialog.setContentView(R.layout.dialog);
                                dialog.setTitle("Digite a Quantidade:");
                                final EditText txtMotivo = (EditText) dialog.findViewById(R.id.txtMotivo);
                                txtMotivo.setText("1");
                                txtMotivo.setInputType(InputType.TYPE_CLASS_NUMBER);
                                dialog.show();
                                Button btnOk = (Button) dialog.findViewById(R.id.btnOK);
                                btnOk.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (os.getIdrecolha() != null) {
                                            os.setIdrecolha(os.getIdrecolha() + 1);
                                        } else {
                                            os.setIdrecolha(1);
                                        }
                                        osDAO.createOrUpdate(os);
                                        Recolha recolha2 = new Recolha();
                                        recolha2.setId(os.getBanco().toString() + os.getCodigo().toString() + produto.getCodigo().toString());
                                        recolha2.setCodigo(produto.getCodigo().toString());
                                        recolha2.setBanco(os.getBanco().toString());
                                        recolha2.setItem(os.getIdrecolha());
                                        recolha2.setNome(produto.getNome().toString());
                                        recolha2.setOs(os.getCodigo().toString());
                                        recolha2.setQtprod(Integer.parseInt(txtMotivo.getText().toString()));
                                        if (produto.getReferencia() != null) {
                                            recolha2.setReferencia(produto.getReferencia().toString());
                                        }
                                        recolhaDAO.createOrUpdate(recolha2);
                                        Toast.makeText(mContext, "Peça para recolha Salva com Sucesso !", Toast.LENGTH_SHORT).show();
                                        ((Activity) mContext).finish();
                                    }
                                });
                                Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
                                btnCancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                            } else {
                                Toast.makeText(mContext, "Item já lançado anteriormente !", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        }

                    }

                }
            }
        });


        h.txtProduto.setText(produto.getCodigo());
        h.txtReferencia.setText(produto.getReferencia());
        h.txtAuxiliar.setText(produto.getCodauxiliar());
        h.txtNome.setText(produto.getNome());

    }


}
