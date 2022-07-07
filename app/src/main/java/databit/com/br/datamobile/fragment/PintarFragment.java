package databit.com.br.datamobile.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import databit.com.br.datamobile.R;
import databit.com.br.datamobile.assinatura.PintarTela;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.interfaces.SelecionaOs;

public class PintarFragment extends Fragment {

    private SelecionaOs mListener;
    private EditText edtSolicitante;
    private EditText edtEmailos;
    private Button btnGravar;
    private Button btnLimpar;
    private PintarTela pintarTela;
    private CheckBox chEmail;
    private byte[] byteArray;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View layout =  inflater.inflate(R.layout.content_pintar_fragment, container, false);
        mListener = (SelecionaOs) getActivity();

        edtSolicitante = (EditText) layout.findViewById(R.id.edtSolicitante);
        if (mListener.getOsSelecionada().getSolicitante() != null) {
            edtSolicitante.setText(mListener.getOsSelecionada().getSolicitante().toString());
        }
        edtEmailos = (EditText) layout.findViewById(R.id.edtEmailos);
        if (mListener.getOsSelecionada().getEmail() != null) {
            edtEmailos.setText(mListener.getOsSelecionada().getEmail().toString());
        }
        chEmail = (CheckBox) layout.findViewById(R.id.chEmail);
        chEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtEmailos.setEnabled(chEmail.isChecked());
                edtSolicitante.setEnabled(chEmail.isChecked());
            }
        });
        pintarTela = (PintarTela) layout.findViewById(R.id.desenho);
        btnGravar = (Button) layout.findViewById(R.id.btnGravarpintar);
        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mListener.getOsSelecionada().getFechaok().equals(1)) {
                        if (!(edtSolicitante.getText().toString().equals("")) && (edtSolicitante.getText() != null)) {
                            GravarImagem();
                            Toast.makeText(getActivity(), "Informações Salvas com Sucesso !", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getActivity(), "Cliente é de preenchimento obrigatório !", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getActivity(), "Primeiramente deve-se salvar as informações no botão INFOR", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Problemas ao Salvar:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnLimpar = (Button) layout.findViewById(R.id.btnLimpar);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener.getOsSelecionada().getFechaok().equals(1)) {
                    pintarTela.clear();
                    GravarImagem();
                    Toast.makeText(getActivity(), "Informações Salvas com Sucesso !", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), "Primeiramente deve-se salvar as informações no botão INFOR", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return layout;
    }

    public void GravarImagem() {
        OsDAO osDAO = new OsDAO();
        Bitmap bmp = pintarTela.getBitmap();
        if (bmp != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 10, stream);
            byteArray = stream.toByteArray();
            mListener.getOsSelecionada().setAssinatura(byteArray);
        }
        if (edtSolicitante.getText() != null) {
            mListener.getOsSelecionada().setSolicitante(edtSolicitante.getText().toString());
        }
        if (chEmail.isChecked()) {
            if (edtEmailos.getText() != null) {
                mListener.getOsSelecionada().setEmail(edtEmailos.getText().toString());
            }
        }
        else {
            mListener.getOsSelecionada().setEmail("");
        }
        osDAO.gravaOs(mListener.getOsSelecionada());
    }
}
