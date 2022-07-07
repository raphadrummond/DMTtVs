package databit.com.br.datamobile.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.text.SimpleDateFormat;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.interfaces.SelecionaOs;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InforFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InforFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InforFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SelecionaOs mListener;

    public InforFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InforFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InforFragment newInstance(String param1, String param2) {
        InforFragment fragment = new InforFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mListener = (SelecionaOs) getActivity();
        View layout =  inflater.inflate(R.layout.fragment_infor, container, false);
        TextView txtCnpj = (TextView) layout.findViewById(R.id.textCNPJ);
        TextView txtContrato = (TextView) layout.findViewById(R.id.textContrato);
        TextView lblContrato = (TextView) layout.findViewById(R.id.lblContrato);
        TextView txtHoras = (TextView) layout.findViewById(R.id.textHoras);
        TextView txtTipohoras = (TextView) layout.findViewById(R.id.textTipoHoras);
        TextView txtSolicitante = (TextView) layout.findViewById(R.id.textSolicitante);
        TextView txtEndereco = (TextView) layout.findViewById(R.id.textEndereco);
        TextView txtNumero = (TextView) layout.findViewById(R.id.textNumero);
        TextView txtComp = (TextView) layout.findViewById(R.id.textComp);
        TextView txtBairro = (TextView) layout.findViewById(R.id.textBairro);
        TextView txtCidade = (TextView) layout.findViewById(R.id.textCidade);
        TextView txtEstado = (TextView) layout.findViewById(R.id.textEstado);
        TextView txtCep = (TextView) layout.findViewById(R.id.textCep);
        TextView txtFone1 = (TextView) layout.findViewById(R.id.textFone1);
        TextView txtFone2 = (TextView) layout.findViewById(R.id.textFone2);
        TextView txtCelular = (TextView) layout.findViewById(R.id.textCelular);
        TextView txtContato = (TextView) layout.findViewById(R.id.textContato);
        TextView txtEmail = (TextView) layout.findViewById(R.id.textEmail);
        TextView txtLocal = (TextView) layout.findViewById(R.id.textLocal);
        TextView txtTipoatende = (TextView) layout.findViewById(R.id.textTipoAtende);
        TextView txtObs = (TextView) layout.findViewById(R.id.textObs);
        TextView txtObsequip = (TextView) layout.findViewById(R.id.textObsequip);
        TextView txtLatitude = (TextView) layout.findViewById(R.id.textLatitude);
        TextView txtLongitude = (TextView) layout.findViewById(R.id.textLongitude);
        TextView txtTipocontrato = (TextView) layout.findViewById(R.id.textTipocontrato);
        TextView txtClassificacao = (TextView) layout.findViewById(R.id.txtClassificacao);
        TextView txtDepto = (TextView) layout.findViewById(R.id.txtDepto);
        TextView txtSite = (TextView) layout.findViewById(R.id.txtSite);
        TextView txtContotal = (TextView) layout.findViewById(R.id.txtConttotal);
        TextView txtContpb = (TextView) layout.findViewById(R.id.txtContpb);
        TextView txtContcolor = (TextView) layout.findViewById(R.id.txtContcolor);
        LinearLayout linha3 = (LinearLayout) layout.findViewById(R.id.linha3);
        LinearLayout linha13 = (LinearLayout) layout.findViewById(R.id.linha13);
        LinearLayout linha15 = (LinearLayout) layout.findViewById(R.id.linha15);
        LinearLayout linha16 = (LinearLayout) layout.findViewById(R.id.linha16);
        LinearLayout linha17 = (LinearLayout) layout.findViewById(R.id.linha17);
        LinearLayout linha18 = (LinearLayout) layout.findViewById(R.id.linha18);
        LinearLayout linha20 = (LinearLayout) layout.findViewById(R.id.linha20);
        LinearLayout linha21 = (LinearLayout) layout.findViewById(R.id.linha21);
        LinearLayout linha22 = (LinearLayout) layout.findViewById(R.id.linha22);
        TextView txtObsadd = (TextView) layout.findViewById(R.id.textObsadd);
        TextView txtQtde  = (TextView) layout.findViewById(R.id.txtqtdeEquip);
        TextView txtProposta = (TextView) layout.findViewById(R.id.txtProposta);
        TextView txtNtfisc = (TextView) layout.findViewById(R.id.txtNtfisc);

        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy hh:mm");

        txtCnpj.setText(mListener.getOsSelecionada().getCnpj());
        txtContrato.setText(mListener.getOsSelecionada().getContrato());
        txtHoras.setText(mListener.getOsSelecionada().getHoras().toString());
        if (mListener.getOsSelecionada().getTipohoras().equals("1"))  {
            txtTipohoras.setText("HORAS ÚTEIS");
        }
        else {
            txtTipohoras.setText("HORAS CORRIDAS");
        }
        txtSolicitante.setText(mListener.getOsSelecionada().getSolicitante());
        txtEndereco.setText(mListener.getOsSelecionada().getLogradouro());
        txtNumero.setText(mListener.getOsSelecionada().getNum().toString());
        txtComp.setText(mListener.getOsSelecionada().getComp());
        txtBairro.setText(mListener.getOsSelecionada().getBairro());
        txtCidade.setText(mListener.getOsSelecionada().getCidade());
        txtEstado.setText(mListener.getOsSelecionada().getEstado());
        txtCep.setText(mListener.getOsSelecionada().getCep());
        txtFone1.setText(mListener.getOsSelecionada().getFone());
        txtFone2.setText(mListener.getOsSelecionada().getFoneaux());
        txtCelular.setText(mListener.getOsSelecionada().getFax());
        txtContato.setText(mListener.getOsSelecionada().getContato());
        txtEmail.setText(mListener.getOsSelecionada().getEmail());
        txtLocal.setText(mListener.getOsSelecionada().getLocal());
        txtLatitude.setText(mListener.getOsSelecionada().getLatitude().toString());
        txtLongitude.setText(mListener.getOsSelecionada().getLongitude().toString());
        txtTipocontrato.setText(mListener.getOsSelecionada().getTipocontrato());
        txtClassificacao.setText(mListener.getOsSelecionada().getClassificacao());
        txtDepto.setText(mListener.getOsSelecionada().getDepartamento());
        txtSite.setText(mListener.getOsSelecionada().getSite());
        txtContotal.setText(mListener.getOsSelecionada().getContadorostotal().toString());
        txtContpb.setText(mListener.getOsSelecionada().getContadorospb().toString());
        txtContcolor.setText(mListener.getOsSelecionada().getContadoroscolor().toString());
        txtQtde.setText(mListener.getOsSelecionada().getQtdeequip().toString());
        txtProposta.setText(mListener.getOsSelecionada().getVlproposta().toString());
        txtNtfisc.setText(mListener.getOsSelecionada().getNtfisc());

        switch (mListener.getOsSelecionada().getPreventiva()) {
            case "N":
                txtTipoatende.setText("NORMAL");
                break;
            case "S":
                txtTipoatende.setText("PREVENTIVA");
                break;
            case "I":
                txtTipoatende.setText("INSTALAÇÃO");
                break;
            case "D":
                txtTipoatende.setText("DESINSTALAÇÃO");
                break;
            case "R":
                txtTipoatende.setText("RETORNO-RECARGA");
                break;
            case "A":
                txtTipoatende.setText("AFERIÇÃO");
                break;
            default:
                txtTipoatende.setText(mListener.getOsSelecionada().getNomeoperacaomobile());
                break;
        }
        txtObs.setText(mListener.getOsSelecionada().getObs());
        txtObsequip.setText(mListener.getOsSelecionada().getObsequip());
        txtObsadd.setText(mListener.getOsSelecionada().getObsadd());

        if (mListener.getOsSelecionada().getOperacaomobile() > 3) {
            lblContrato.setVisibility(View.GONE);
            txtContato.setVisibility(View.GONE);
            linha13.setVisibility(View.GONE);
            linha15.setVisibility(View.GONE);
            linha16.setVisibility(View.GONE);
            linha17.setVisibility(View.GONE);
            linha18.setVisibility(View.GONE);
        }
        else {
            lblContrato.setVisibility(View.VISIBLE);
            txtContato.setVisibility(View.VISIBLE);
            linha13.setVisibility(View.VISIBLE);
            linha15.setVisibility(View.VISIBLE);
            linha16.setVisibility(View.VISIBLE);
            linha17.setVisibility(View.VISIBLE);
            linha18.setVisibility(View.VISIBLE);
        }

        if (mListener.getOsSelecionada().getOperacaomobile() > 1) {
            linha3.setVisibility(View.GONE);
        }
        else {
            linha3.setVisibility(View.VISIBLE);
        }

        if ((mListener.getOsSelecionada().getOperacaomobile().equals(2)) ||
            (mListener.getOsSelecionada().getOperacaomobile().equals(3))) {
            linha20.setVisibility(View.VISIBLE);
            linha22.setVisibility(View.VISIBLE);
        }
        else {
            linha20.setVisibility(View.GONE);
            linha22.setVisibility(View.GONE);
        }

        if (mListener.getOsSelecionada().getOperacaomobile().equals(7)){
            linha21.setVisibility(View.VISIBLE);
        }
        else {
            linha21.setVisibility(View.GONE);
        }


        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
