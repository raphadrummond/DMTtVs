package databit.com.br.datamobile.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.activity.FechaActivity;
import databit.com.br.datamobile.activity.OsActivity;
import databit.com.br.datamobile.domain.Os;

/**
 * Created by Sidney on 07/03/2016.
 */

public class AdapterOs extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Os> listos;
    private RecyclerView mRecyclerView;
    private float scale;
    private int width, heigth, roundPixels;
    private Context mContext;


    public AdapterOs(List<Os> listos,  RecyclerView rv) {
        this.listos = listos;
        this.mRecyclerView = rv;
        this.mContext = rv.getContext();

        scale = mContext.getResources().getDisplayMetrics().density;
        width = mContext.getResources().getDisplayMetrics().widthPixels - (int)(14 * scale + 0.5f);
        heigth = (width/16) * 9;

        roundPixels = (int)(2 * scale + 0.5f);
    }

    @Override
    public int getItemCount() {
        return listos.size();
    }


    public class OsViewHolder extends RecyclerView.ViewHolder {
        public TextView txtOS;
        public TextView txtContrato;
        public TextView txtSerial;
        public TextView txtNomeCliente;
        public TextView txtNomeProduto;
        public TextView txtPrevisao;
        public ImageButton imgOS;
        public TextView txtOrdem;
        public TextView lblPat;
        public TextView txtPat;
        public TextView txtMotivo;
        public View layoutRow;
        public TextView txtTipocontrato;
        public TextView txtCidade;
        public TextView txtClassificacao;
        public TextView txtSite;
        public TextView txtBairro;
        public TextView txtDepto;
        public TextView txtLocal;
        public TextView txtContotal;
        public TextView txtContpb;
        public TextView txtContcolor;
        public TextView txtOsrein;
        public TextView txtObsadd;
        public TextView txtSigla;
        public TextView txtqtdeEquip;
        public TextView txtProposta;
        public TextView txtNtfisc;
        public LinearLayout linha1;
        public LinearLayout linha6;
        public LinearLayout linha7;
        public LinearLayout linha8;
        public LinearLayout linha11;
        public LinearLayout linha12;
        public LinearLayout linha13;
        public LinearLayout linha14;
        public LinearLayout linha16;
        public LinearLayout linha17;
        public LinearLayout linha18;





        public OsViewHolder(View v) {
            super(v);
            layoutRow = v;
            txtOS = (TextView) v.findViewById(R.id.txtOS);
            txtContrato = (TextView) v.findViewById(R.id.txtContrato);
            txtSerial = (TextView) v.findViewById(R.id.txtSerial);
            txtNomeCliente = (TextView) v.findViewById(R.id.txtNomeCliente);
            txtNomeProduto = (TextView) v.findViewById(R.id.txtNomeProduto);
            txtPrevisao = (TextView) v.findViewById(R.id.txtPrevisao);
            imgOS = (ImageButton) v.findViewById(R.id.imageOS);
            txtOrdem = (TextView) v.findViewById(R.id.txtOrdem);
            txtPat = (TextView) v.findViewById(R.id.txtPat);
            lblPat = (TextView) v.findViewById(R.id.lblPat);
            txtMotivo = (TextView) v.findViewById(R.id.txtMotivo);
            txtTipocontrato = (TextView) v.findViewById(R.id.txtTipocontrato);
            txtCidade = (TextView) v.findViewById(R.id.txtCidade);
            txtClassificacao = (TextView) v.findViewById(R.id.txtClassificacao);
            txtBairro = (TextView) v.findViewById(R.id.txtBairro);
            txtDepto = (TextView) v.findViewById(R.id.txtDepto);
            txtSite = (TextView) v.findViewById(R.id.txtSite);
            txtLocal  = (TextView) v.findViewById(R.id.txtLocal);
            txtContotal = (TextView) v.findViewById(R.id.txtConttotal);
            txtContpb = (TextView) v.findViewById(R.id.txtContpb);
            txtContcolor = (TextView) v.findViewById(R.id.txtContcolor);
            txtOsrein  = (TextView) v.findViewById(R.id.txtOsrein);
            txtObsadd  = (TextView) v.findViewById(R.id.txtObsadd);
            txtSigla  = (TextView) v.findViewById(R.id.txtSigla);
            txtqtdeEquip  = (TextView) v.findViewById(R.id.txtqtdeEquip);
            txtProposta  = (TextView) v.findViewById(R.id.txtProposta);
            txtNtfisc  = (TextView) v.findViewById(R.id.txtNtfisc);
            linha1 = (LinearLayout) v.findViewById(R.id.linha1);
            linha6 = (LinearLayout) v.findViewById(R.id.linha6);
            linha7 = (LinearLayout) v.findViewById(R.id.linha7);
            linha8 = (LinearLayout) v.findViewById(R.id.linha8);
            linha11 = (LinearLayout) v.findViewById(R.id.linha11);
            linha12 = (LinearLayout) v.findViewById(R.id.linha12);
            linha13 = (LinearLayout) v.findViewById(R.id.linha13);
            linha14 = (LinearLayout) v.findViewById(R.id.linha14);
            linha16 = (LinearLayout) v.findViewById(R.id.linha16);
            linha17 = (LinearLayout) v.findViewById(R.id.linha17);
            linha18 = (LinearLayout) v.findViewById(R.id.linha18);

            /*if (imgOS != null) {
                imgOS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        List<MenuOs> opcoes = new ArrayList<>();

                        opcoes.add( new MenuOs(R.mipmap.ic_visualizar, "Visualizar O.S"));
                        opcoes.add( new MenuOs(R.mipmap.ic_checkin, "Realizar Check-In"));
                        opcoes.add( new MenuOs(R.mipmap.ic_checkout, "Realizar Check-Out"));

                        AdapterMenuOs adapter = new AdapterMenuOs(mContext, opcoes);


                        ListPopupWindow listPopupWindow = new ListPopupWindow(mContext);
                        listPopupWindow.setAnchorView(imgOS);
                        listPopupWindow.setAdapter(adapter);
                        listPopupWindow.setWidth((int) (240 * scale + 0.5f));
                        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(mContext, getAdapterPosition() + " : " + position, Toast.LENGTH_SHORT).show();
                            }
                        });
                        listPopupWindow.setModal(true);
                        listPopupWindow.show();
                    }
                });
            }
            */
        }


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagem_os, parent, false);
        OsViewHolder vh = new OsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy HH:mm");

        final Os os = listos.get(position);
        OsViewHolder h = ((OsViewHolder)holder);

        h.layoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(os.getPreventiva().equals("A"))) {
                    Intent intent = new Intent(mContext, OsActivity.class);
                    intent.putExtra("os", os);
                    mContext.startActivity(intent);
                }
                else {
                    Intent intent = new Intent(mContext, FechaActivity.class);
                    intent.putExtra("os", os);
                    mContext.startActivity(intent);
                }
            }
        });

        h.txtOS.setText(os.getCodigo());
        h.txtContrato.setText(os.getContrato());
        h.txtSerial.setText(os.getNumserie());
        h.txtContrato.setText(os.getContrato());
        h.txtSerial.setText(os.getNumserie());
        h.txtNomeCliente.setText(os.getNomecli());
        h.txtNomeProduto.setText(os.getNomeprod());
        h.txtPrevisao.setText(sdf.format(os.getPrevisao()));
        h.txtOrdem.setText(os.getOrdem().toString());
        h.txtPat.setText(os.getPat());
        h.txtMotivo.setText(os.getMotivo());
        h.txtTipocontrato.setText(os.getTipocontrato());
        h.txtCidade.setText(os.getCidade());
        h.txtClassificacao.setText(os.getClassificacao());
        h.txtBairro.setText(os.getBairro());
        h.txtDepto.setText(os.getDepartamento());
        h.txtSite.setText(os.getSite());
        h.txtLocal.setText(os.getLocal());
        h.txtContotal.setText(os.getContadorostotal().toString());
        h.txtContpb.setText(os.getContadorospb().toString());
        h.txtContcolor.setText(os.getContadorospb().toString());
        h.txtOsrein.setText(os.getNumos());
        h.txtObsadd.setText(os.getObsadd());
        h.txtqtdeEquip.setText(os.getQtdeequip().toString());
        h.txtProposta.setText(os.getVlproposta().toString());
        h.txtSigla.setText(os.getSigla());
        h.txtNtfisc.setText(os.getNtfisc());
        if (!(os.getPreventiva().equals("A"))) {
            switch (os.getStatus_check()) {
                case 1:
                    h.imgOS.setImageResource(R.mipmap.ic_ospendente);
                    break;
                case 2:
                    h.imgOS.setImageResource(R.mipmap.ic_osatrasado);
                    break;
                case 3:
                    h.imgOS.setImageResource(R.mipmap.ic_osexecucao);
                    break;
                case 4:
                    h.imgOS.setImageResource(R.mipmap.ic_osconcluido);
                    break;

            }
        }
        else {
            if (os.getStatus_check().equals(4)) {
                h.imgOS.setImageResource(R.mipmap.ic_osconcluido);
            }
            else {
                h.imgOS.setImageResource(R.mipmap.ic_osafericao);
            }

        }

        if (os.getOperacaomobile() > 3) {
            h.lblPat.setVisibility(View.GONE);
            h.txtPat.setVisibility(View.GONE);
            h.linha1.setVisibility(View.GONE);
            h.linha6.setVisibility(View.GONE);
            h.linha7.setVisibility(View.GONE);
            h.linha8.setVisibility(View.GONE);
            h.linha11.setVisibility(View.GONE);
            h.linha12.setVisibility(View.GONE);
            h.linha13.setVisibility(View.GONE);
            h.linha14.setVisibility(View.GONE);
        }
        else {
            h.lblPat.setVisibility(View.VISIBLE);
            h.txtPat.setVisibility(View.VISIBLE);
            h.linha1.setVisibility(View.VISIBLE);
            h.linha6.setVisibility(View.VISIBLE);
            h.linha7.setVisibility(View.VISIBLE);
            h.linha8.setVisibility(View.VISIBLE);
            h.linha11.setVisibility(View.VISIBLE);
            h.linha12.setVisibility(View.VISIBLE);
            h.linha13.setVisibility(View.VISIBLE);
            h.linha14.setVisibility(View.VISIBLE);
        }

        if ((os.getOperacaomobile().equals(2)) || (os.getOperacaomobile().equals(3))) {
            h.linha16.setVisibility(View.VISIBLE);
            h.linha18.setVisibility(View.VISIBLE);
        }
        else {
            h.linha16.setVisibility(View.GONE);
            h.linha18.setVisibility(View.GONE);
        }

        if (os.getOperacaomobile().equals(7)) {
            h.linha17.setVisibility(View.VISIBLE);
        }
        else {
            h.linha17.setVisibility(View.GONE);
        }



    }

}
