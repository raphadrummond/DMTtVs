package databit.com.br.datamobile.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.interfaces.SelecionaOs;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SelecionaOs mListener;

    public OsFragment() {
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
     * @return A new instance of fragment OsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OsFragment newInstance(String param1, String param2) {
        OsFragment fragment = new OsFragment();
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
        View layout =  inflater.inflate(R.layout.fragment_os, container, false);
        TextView txtOs = (TextView) layout.findViewById(R.id.txtOS);
        TextView txtContrato = (TextView) layout.findViewById(R.id.txtContrato);
        TextView txtSerial = (TextView) layout.findViewById(R.id.txtSerial);
        TextView txtCliente = (TextView) layout.findViewById(R.id.txtNomeCliente);
        TextView txtProduto = (TextView) layout.findViewById(R.id.txtNomeProduto);
        TextView txtPrevisao = (TextView) layout.findViewById(R.id.txtPrevisao);
        TextView txtOrdem = (TextView) layout.findViewById(R.id.txtOrdem);
        TextView txtPat = (TextView) layout.findViewById(R.id.txtPat);
        TextView lblPat = (TextView) layout.findViewById(R.id.lblPat);
        TextView txtMotivo = (TextView) layout.findViewById(R.id.txtMotivo);
        ImageButton imgOS = (ImageButton) layout.findViewById(R.id.imageOS);
        TextView txtSigla = (TextView) layout.findViewById(R.id.txtSigla);
        LinearLayout linha1 = (LinearLayout) layout.findViewById(R.id.linha1);
        LinearLayout linha3 = (LinearLayout) layout.findViewById(R.id.linha3);

        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy HH:mm");

        txtOs.setText(mListener.getOsSelecionada().getCodigo());
        txtCliente.setText(mListener.getOsSelecionada().getNomecli());
        txtOrdem.setText(mListener.getOsSelecionada().getOrdem().toString());
        txtContrato.setText(mListener.getOsSelecionada().getContrato());
        txtSerial.setText(mListener.getOsSelecionada().getNumserie());
        txtPat.setText(mListener.getOsSelecionada().getPat());
        txtProduto.setText(mListener.getOsSelecionada().getNomeprod());
        txtMotivo.setText(mListener.getOsSelecionada().getMotivo());
        txtPrevisao.setText(sdf.format(mListener.getOsSelecionada().getPrevisao()));
        txtSigla.setText(mListener.getOsSelecionada().getSigla());

        if (mListener.getOsSelecionada().getOperacaomobile() > 3) {
            lblPat.setVisibility(View.GONE);
            txtPat.setVisibility(View.GONE);
            linha1.setVisibility(View.GONE);
            linha3.setVisibility(View.GONE);
        }
        else {
            lblPat.setVisibility(View.VISIBLE);
            txtPat.setVisibility(View.VISIBLE);
            linha1.setVisibility(View.VISIBLE);
            linha3.setVisibility(View.VISIBLE);
        }

        if (!(mListener.getOsSelecionada().getPreventiva().equals("A"))) {
            switch (mListener.getOsSelecionada().getStatus_check()) {
                case 1:
                    imgOS.setImageResource(R.mipmap.ic_ospendente);
                    break;
                case 2:
                    imgOS.setImageResource(R.mipmap.ic_osatrasado);
                    break;
                case 3:
                    imgOS.setImageResource(R.mipmap.ic_osexecucao);
                    break;
                case 4:
                    imgOS.setImageResource(R.mipmap.ic_osconcluido);
                    break;

            }
        }
        else {
            if (mListener.getOsSelecionada().getStatus_check().equals(4)) {
                imgOS.setImageResource(R.mipmap.ic_osconcluido);
            }
            else {
                imgOS.setImageResource(R.mipmap.ic_osafericao);
            }

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
        void onFragmentInteraction(Uri uri);
    }


}
