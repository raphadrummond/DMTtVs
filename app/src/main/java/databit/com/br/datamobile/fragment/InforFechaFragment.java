package databit.com.br.datamobile.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.dao.CondicaoDAO;
import databit.com.br.datamobile.dao.ConfigmobileDAO;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.dao.ServicoIncompletoDAO;
import databit.com.br.datamobile.domain.Configmobile;
import databit.com.br.datamobile.interfaces.SelecionaOs;

public class InforFechaFragment extends Fragment {

    private SelecionaOs mListener;
    private EditText edtContpb;
    private EditText edtCredpb;
    private EditText edtContcor;
    private EditText edtCredcor;
    private EditText edtContdg;
    private EditText edtCreddg;
    private EditText edtContgf;
    private EditText edtCredgf;
    private EditText edtContgfc;
    private EditText edtCredgfc;
    private CheckBox chCilindropb;
    private CheckBox chCilindrocor;
    private CheckBox chReveladorpb;
    private CheckBox chReveladorcor;
    private CheckBox chFusor;
    private CheckBox chBelt;
    private CheckBox chReservatorio;
    private CheckBox chServincompleto;
    private CheckBox chUsadopb;
    private EditText edtContpbusado;
    private TextView txtContpbusado;
    private CheckBox chUsadocor;
    private EditText edtContcolorusado;
    private TextView txtContcolorusado;
    private EditText edtKminicial;
    private EditText edtKmfinal;
    private EditText edtPlaca;
    private Spinner lcbCondicao;
    private Spinner lcbServincompleto;
    private Button btnGravarfecha;
    private TextView txtCondicao;
    private TextView txtKminicial;
    private TextView txtKmfinal;
    private TextView txtKmplaca;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mListener = (SelecionaOs) getActivity();
        View layout =  inflater.inflate(R.layout.content_infor_fecha_fragment, container, false);

        edtContpb = (EditText) layout.findViewById(R.id.edtContpb);
        edtCredpb = (EditText) layout.findViewById(R.id.edtCredpb);
        edtContcor = (EditText) layout.findViewById(R.id.edtContcor);
        edtCredcor = (EditText) layout.findViewById(R.id.edtCredcor);
        edtContdg = (EditText) layout.findViewById(R.id.edtContdg);
        edtCreddg = (EditText) layout.findViewById(R.id.edtCreddg);
        edtContgf = (EditText) layout.findViewById(R.id.edtContgf);
        edtCredgf = (EditText) layout.findViewById(R.id.edtCredgf);
        edtContgfc = (EditText) layout.findViewById(R.id.edtContgfc);
        edtCredgfc = (EditText) layout.findViewById(R.id.edtCredgfc);

        chCilindropb = (CheckBox) layout.findViewById(R.id.chCilindropb);
        chCilindrocor = (CheckBox) layout.findViewById(R.id.chCilindrocor);
        chReveladorpb = (CheckBox) layout.findViewById(R.id.chReveladorpb);
        chReveladorcor = (CheckBox) layout.findViewById(R.id.chReveladorcor);
        chFusor = (CheckBox) layout.findViewById(R.id.chFusor);
        chBelt = (CheckBox) layout.findViewById(R.id.chBelt);
        chReservatorio = (CheckBox) layout.findViewById(R.id.chReservatorio);
        lcbServincompleto = (Spinner) layout.findViewById(R.id.lcbServincompleto);
        chServincompleto = (CheckBox) layout.findViewById(R.id.chServincompleto);
        chServincompleto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chServincompleto.isChecked()) {
                    lcbServincompleto.setVisibility(View.VISIBLE);
                } else {
                    lcbServincompleto.setVisibility(View.GONE);
                }
            }
        });
        edtContpbusado = (EditText) layout.findViewById(R.id.edtContpbusado);
        txtContpbusado = (TextView) layout.findViewById(R.id.txtContpbusado);
        chUsadopb = (CheckBox) layout.findViewById(R.id.chUsadopb);
        chUsadopb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtContpbusado.setEnabled(chUsadopb.isChecked());
                edtContpbusado.setEnabled(chUsadopb.isChecked());
            }
        });
        edtContcolorusado = (EditText) layout.findViewById(R.id.edtContcolorusado);
        txtContcolorusado = (TextView) layout.findViewById(R.id.txtContcolorusado);
        chUsadocor = (CheckBox) layout.findViewById(R.id.chUsadocor);
        chUsadocor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtContcolorusado.setEnabled(chUsadocor.isChecked());
                edtContcolorusado.setEnabled(chUsadocor.isChecked());
            }
        });
        edtKminicial = (EditText) layout.findViewById(R.id.edtKminicial);
        edtKmfinal = (EditText) layout.findViewById(R.id.edtKmfinal);
        edtPlaca = (EditText) layout.findViewById(R.id.edtPlaca);
        lcbCondicao = (Spinner) layout.findViewById(R.id.lcbCondicao);
        txtCondicao = (TextView) layout.findViewById(R.id.txtCondicao);
        txtKminicial = (TextView) layout.findViewById(R.id.txtKminicial);
        txtKmfinal = (TextView) layout.findViewById(R.id.txtKmfinal);
        txtKmplaca = (TextView) layout.findViewById(R.id.txtKmplaca);

        btnGravarfecha = (Button) layout.findViewById(R.id.btnGravarfecha);
        btnGravarfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gravar();
            }
        });

        CondicaoDAO condicaoDAO = new CondicaoDAO();
        ServicoIncompletoDAO servicoIncompletoDAO = new ServicoIncompletoDAO();

        ArrayAdapter<String> arrayAdapter_condicao = new ArrayAdapter<String>(getActivity(),R.layout.spinner_padrao,condicaoDAO.allNomeCondicao());
        arrayAdapter_condicao.setDropDownViewResource(R.layout.spinner_dropdown_padrao);
        lcbCondicao.setAdapter(arrayAdapter_condicao);

        ArrayAdapter<String> arrayAdapter_servicompleto = new ArrayAdapter<String>(getActivity(),R.layout.spinner_padrao,servicoIncompletoDAO.allNomeServicoIncompleto());
        arrayAdapter_servicompleto.setDropDownViewResource(R.layout.spinner_dropdown_padrao);
        lcbServincompleto.setAdapter(arrayAdapter_servicompleto);

        if (mListener.getOsSelecionada().getFechaok().equals(0)) {
            Date data = new Date();
            mListener.getOsSelecionada().setCodigo(mListener.getOsSelecionada().getCodigo().toString());
            mListener.getOsSelecionada().setNometec(mListener.getOsSelecionada().getNometec().toString());
            mListener.getOsSelecionada().setData(data);
            edtContpb.setText("0");
            edtCredpb.setText("0");
            edtContcor.setText("0");
            edtCredcor.setText("0");
            edtContdg.setText("0");
            edtCreddg.setText("0");
            edtContgf.setText("0");
            edtCredgf.setText("0");
            edtContgfc.setText("0");
            edtCredgfc.setText("0");
            chCilindropb.setChecked(false);
            chCilindrocor.setChecked(false);
            chReveladorpb.setChecked(false);
            chReveladorcor.setChecked(false);
            chFusor.setChecked(false);
            chBelt.setChecked(false);
            chReservatorio.setChecked(false);
            chServincompleto.setChecked(false);
            edtKminicial.setText("0");
            edtKmfinal.setText("0");
            edtPlaca.setText("");
            chUsadopb.setChecked(false);
            chUsadocor.setChecked(false);
            edtContpbusado.setText("0");
            edtContcolorusado.setText("0");
        }
        else {
            edtContpb.setText(mListener.getOsSelecionada().getContador().toString());
            edtCredpb.setText(mListener.getOsSelecionada().getCredcopias().toString());
            edtContcor.setText(mListener.getOsSelecionada().getContadorc().toString());
            edtCredcor.setText(mListener.getOsSelecionada().getCredcopiasc().toString());
            edtContdg.setText(mListener.getOsSelecionada().getContadordg().toString());
            edtCreddg.setText(mListener.getOsSelecionada().getCredcopiasdg().toString());
            edtContgf.setText(mListener.getOsSelecionada().getContadorgf().toString());
            edtCredgf.setText(mListener.getOsSelecionada().getCredcopiasgf().toString());
            edtContgfc.setText(mListener.getOsSelecionada().getContadorgfc().toString());
            edtCredgfc.setText(mListener.getOsSelecionada().getCredcopiasgfc().toString());
            chCilindropb.setChecked(mListener.getOsSelecionada().getCilindro().equals("S"));
            chCilindrocor.setChecked(mListener.getOsSelecionada().getCilindroc().equals("S"));
            chReveladorpb.setChecked(mListener.getOsSelecionada().getRevelador().equals("S"));
            chReveladorcor.setChecked(mListener.getOsSelecionada().getReveladorc().equals("S"));
            chFusor.setChecked(mListener.getOsSelecionada().getFusor().equals("S"));
            chBelt.setChecked(mListener.getOsSelecionada().getBelt().equals("S"));
            chReservatorio.setChecked(mListener.getOsSelecionada().getReservatorio().equals("S"));
            chServincompleto.setChecked(mListener.getOsSelecionada().getIncompleto().equals("S"));
            edtKminicial.setText(mListener.getOsSelecionada().getKminicial().toString());
            edtKmfinal.setText(mListener.getOsSelecionada().getKmfinal().toString());
            edtPlaca.setText(mListener.getOsSelecionada().getPlaca().toString());
            chUsadopb.setChecked(mListener.getOsSelecionada().getSitcilindro().equals("U"));
            chUsadocor.setChecked(mListener.getOsSelecionada().getSitcilindroc().equals("U"));
            edtContpbusado.setText(mListener.getOsSelecionada().getContcilindro().toString());
            edtContcolorusado.setText(mListener.getOsSelecionada().getContcilindroc().toString());
            txtContpbusado.setEnabled(mListener.getOsSelecionada().getSitcilindro().equals("U"));
            txtContcolorusado.setEnabled(mListener.getOsSelecionada().getSitcilindroc().equals("U"));
            edtContpbusado.setEnabled(mListener.getOsSelecionada().getSitcilindro().equals("U"));
            edtContcolorusado.setEnabled(mListener.getOsSelecionada().getSitcilindroc().equals("U"));

            if (mListener.getOsSelecionada().getIncompleto().equals("S")) {
                lcbServincompleto.setVisibility(View.VISIBLE);
                List<String> servicos = servicoIncompletoDAO.allNomeServicoIncompleto();
                for (int i = 0; i < servicos.size(); i++) {
                    if (servicos.get(i).toString().equals(mListener.getOsSelecionada().getServincompl().toString())) {
                        lcbServincompleto.setSelection(i);
                        break;
                    }
                }
            }
            else {
                lcbServincompleto.setVisibility(View.GONE);
            }

            List<String> condicoes = condicaoDAO.allNomeCondicao();
            for (int i = 0; i < condicoes.size(); i++) {
                if (condicoes.get(i).toString().equals(mListener.getOsSelecionada().getCondicaofinal().toString())) {
                    lcbCondicao.setSelection(i);
                    break;
                }
            }
        }
        if (mListener.getOsSelecionada().getSync().equals("S")) {
            edtContpb.setEnabled(false);
            edtCredpb.setEnabled(false);
            edtContcor.setEnabled(false);
            edtCredcor.setEnabled(false);
            edtContdg.setEnabled(false);
            edtCreddg.setEnabled(false);
            edtContgf.setEnabled(false);
            edtCredgf.setEnabled(false);
            edtContgfc.setEnabled(false);
            edtCredgfc.setEnabled(false);
            chCilindropb.setEnabled(false);
            chCilindrocor.setEnabled(false);
            chReveladorpb.setEnabled(false);
            chReveladorcor.setEnabled(false);
            chFusor.setEnabled(false);
            chBelt.setEnabled(false);
            chReservatorio.setEnabled(false);
            chServincompleto.setEnabled(false);
            edtKminicial.setEnabled(false);
            edtKmfinal.setEnabled(false);
            edtPlaca.setEnabled(false);
            chUsadopb.setEnabled(false);
            chUsadocor.setEnabled(false);
            edtContpbusado.setEnabled(false);
            edtContcolorusado.setEnabled(false);
            txtContpbusado.setEnabled(false);
            txtContcolorusado.setEnabled(false);
            edtContpbusado.setEnabled(false);
            edtContcolorusado.setEnabled(false);
            lcbServincompleto.setEnabled(false);
            lcbCondicao.setEnabled(false);
            btnGravarfecha.setEnabled(false);
        }

        if (mListener.getOsSelecionada().getPreventiva().equals("A")) {
            chCilindropb.setVisibility(View.GONE);
            chCilindrocor.setVisibility(View.GONE);
            chReveladorpb.setVisibility(View.GONE);
            chReveladorcor.setVisibility(View.GONE);
            chFusor.setVisibility(View.GONE);
            chBelt.setVisibility(View.GONE);
            chReservatorio.setVisibility(View.GONE);
            chServincompleto.setVisibility(View.GONE);
            edtKminicial.setVisibility(View.GONE);
            edtKmfinal.setVisibility(View.GONE);
            edtPlaca.setVisibility(View.GONE);
            chUsadopb.setVisibility(View.GONE);
            chUsadocor.setVisibility(View.GONE);
            edtContpbusado.setVisibility(View.GONE);
            edtContcolorusado.setVisibility(View.GONE);
            txtContpbusado.setVisibility(View.GONE);
            txtContcolorusado.setVisibility(View.GONE);
            edtContpbusado.setVisibility(View.GONE);
            edtContcolorusado.setVisibility(View.GONE);
            lcbServincompleto.setVisibility(View.GONE);
            lcbCondicao.setVisibility(View.GONE);
            txtCondicao.setVisibility(View.GONE);
            txtKminicial.setVisibility(View.GONE);
            txtKmfinal.setVisibility(View.GONE);
            txtKmplaca.setVisibility(View.GONE);
            edtCredpb.setEnabled(false);
            edtCredcor.setEnabled(false);
            edtCreddg.setEnabled(false);
            edtCredgf.setEnabled(false);
            edtCredgfc.setEnabled(false);
        }
        return layout;
    }

    public void Gravar() {

        try {
            ConfigmobileDAO configmobileDAO = new ConfigmobileDAO();
            OsDAO osDAO = new OsDAO();
            Configmobile configmobile = configmobileDAO.procuraConfigmobile("id = 1");

            if ((edtKminicial.getText().toString() == null) || (edtKminicial.getText().equals(""))) {
                edtKminicial.setText("0");
            }
            if ((edtKmfinal.getText().toString() == null)  || (edtKmfinal.getText().equals(""))) {
                edtKmfinal.setText("0");
            }
            if ((configmobile.getObrkmplaca().equals("S")) && (!(mListener.getOsSelecionada().getPreventiva().equals("A")))) {
                if (Integer.parseInt(edtKminicial.getText().toString()) == 0) {
                    Toast.makeText(getActivity(), "KM Inicial é de preenchimento Obrigatório !", Toast.LENGTH_SHORT).show();
                    edtKminicial.requestFocus();
                    return;
                }
                if (Integer.parseInt(edtKmfinal.getText().toString()) == 0) {
                    Toast.makeText(getActivity(), "KM Final é de preenchimento Obrigatório !", Toast.LENGTH_SHORT).show();
                    edtKmfinal.requestFocus();
                    return;
                }
                if ((edtPlaca.getText().toString().equals("")) || (edtPlaca.getText().toString() == null)) {
                    Toast.makeText(getActivity(), "Placa é de preenchimento Obrigatório !", Toast.LENGTH_SHORT).show();
                    edtPlaca.requestFocus();
                    return;
                }
            }
            if ((Integer.parseInt(edtKmfinal.getText().toString()) < Integer.parseInt(edtKminicial.getText().toString())) && (!(mListener.getOsSelecionada().getPreventiva().equals("A")))) {
                Toast.makeText(getActivity(), "KM Final não pode ser menor que o KM Inicial !", Toast.LENGTH_SHORT).show();
                edtKmfinal.requestFocus();
                return;
            }

            if ((Integer.parseInt(edtContpb.getText().toString()) + Integer.parseInt(edtContcor.getText().toString()) +
                Integer.parseInt(edtContdg.getText().toString()) + Integer.parseInt(edtContgf.getText().toString())  +
                Integer.parseInt(edtContgfc.getText().toString())) <= 0) {
                Toast.makeText(getActivity(), "Medidores são de preenchimento Obrigatório !", Toast.LENGTH_SHORT).show();
                edtContpb.requestFocus();
                return;
            }


            try {
                mListener.getOsSelecionada().setContador(Integer.parseInt(edtContpb.getText().toString()));
                mListener.getOsSelecionada().setCredcopias(Integer.parseInt(edtCredpb.getText().toString()));

                if ((chCilindropb.isChecked()) && (!(mListener.getOsSelecionada().getPreventiva().equals("A")))) {
                    mListener.getOsSelecionada().setCilindro("S");
                }
                else {
                    mListener.getOsSelecionada().setCilindro("N");
                }

                if ((chUsadopb.isChecked()) && (!(mListener.getOsSelecionada().getPreventiva().equals("A")))) {
                    mListener.getOsSelecionada().setSitcilindro("U");
                }
                else {
                    mListener.getOsSelecionada().setSitcilindro("N");
                }

                mListener.getOsSelecionada().setContcilindro(Integer.parseInt(edtContpbusado.getText().toString()));

                if ((chReveladorpb.isChecked()) && (!(mListener.getOsSelecionada().getPreventiva().equals("A")))) {
                    mListener.getOsSelecionada().setRevelador("S");
                }
                else {
                    mListener.getOsSelecionada().setRevelador("N");
                }

                mListener.getOsSelecionada().setCondicaofinal(lcbCondicao.getSelectedItem().toString());

                if ((chServincompleto.isChecked()) && (!(mListener.getOsSelecionada().getPreventiva().equals("A")))) {
                    mListener.getOsSelecionada().setIncompleto("S");
                }
                else {
                    mListener.getOsSelecionada().setIncompleto("N");
                }

                mListener.getOsSelecionada().setServincompl(lcbServincompleto.getSelectedItem().toString());
                mListener.getOsSelecionada().setContadorc(Integer.parseInt(edtContcor.getText().toString()));
                mListener.getOsSelecionada().setCredcopiasc(Integer.parseInt(edtCredcor.getText().toString()));
                mListener.getOsSelecionada().setContadordg(Integer.parseInt(edtContdg.getText().toString()));
                mListener.getOsSelecionada().setCredcopiasdg(Integer.parseInt(edtCreddg.getText().toString()));
                mListener.getOsSelecionada().setContadorgf(Integer.parseInt(edtContgf.getText().toString()));
                mListener.getOsSelecionada().setCredcopiasgf(Integer.parseInt(edtCredgf.getText().toString()));
                mListener.getOsSelecionada().setContadorgfc(Integer.parseInt(edtContgfc.getText().toString()));
                mListener.getOsSelecionada().setCredcopiasgfc(Integer.parseInt(edtCredgfc.getText().toString()));

                if ((chFusor.isChecked()) && (!(mListener.getOsSelecionada().getPreventiva().equals("A")))) {
                    mListener.getOsSelecionada().setFusor("S");
                }
                else {
                    mListener.getOsSelecionada().setFusor("N");
                }

                if ((chBelt.isChecked())  && (!(mListener.getOsSelecionada().getPreventiva().equals("A")))) {
                    mListener.getOsSelecionada().setBelt("S");
                }
                else {
                    mListener.getOsSelecionada().setBelt("N");
                }

                mListener.getOsSelecionada().setKminicial(Integer.parseInt(edtKminicial.getText().toString()));
                mListener.getOsSelecionada().setKmfinal(Integer.parseInt(edtKmfinal.getText().toString()));

                if ((chCilindrocor.isChecked()) && (!(mListener.getOsSelecionada().getPreventiva().equals("A")))) {
                    mListener.getOsSelecionada().setCilindroc("S");
                }
                else {
                    mListener.getOsSelecionada().setCilindroc("N");
                }

                if ((chReveladorcor.isChecked()) && (!(mListener.getOsSelecionada().getPreventiva().equals("A")))) {
                    mListener.getOsSelecionada().setReveladorc("S");
                }
                else {
                    mListener.getOsSelecionada().setReveladorc("N");
                }

                if ((chUsadocor.isChecked()) && (!(mListener.getOsSelecionada().getPreventiva().equals("A")))) {
                    mListener.getOsSelecionada().setSitcilindroc("U");
                }
                else {
                    mListener.getOsSelecionada().setSitcilindroc("N");
                }

                mListener.getOsSelecionada().setContcilindroc(Integer.parseInt(edtContcolorusado.getText().toString()));

                if ((chReservatorio.isChecked()) && (!(mListener.getOsSelecionada().getPreventiva().equals("A")))) {
                    mListener.getOsSelecionada().setReservatorio("S");
                }
                else {
                    mListener.getOsSelecionada().setReservatorio("N");
                }
                Date data = new Date();
                mListener.getOsSelecionada().setPlaca(edtPlaca.getText().toString());
                mListener.getOsSelecionada().setSync("N");
                mListener.getOsSelecionada().setBanco(mListener.getOsSelecionada().getBanco().toString());
                mListener.getOsSelecionada().setBancoos(mListener.getOsSelecionada().getBancoos().toString());
                mListener.getOsSelecionada().setFechaok(1);
                mListener.getOsSelecionada().setDatafecha(data);
                osDAO.gravaOs(mListener.getOsSelecionada());
                Toast.makeText(getActivity(), "Informações Salvas com Sucesso !", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Problemas ao Salvar: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Problemas ao Salvar: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}
