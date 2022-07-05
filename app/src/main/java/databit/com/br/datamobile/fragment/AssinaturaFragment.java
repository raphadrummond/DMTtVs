package databit.com.br.datamobile.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.internal.overlay.zzo;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.dao.FechamentoDAO;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.domain.Fechamento;
import databit.com.br.datamobile.interfaces.SelecionaOs;

public class AssinaturaFragment extends Fragment {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;
    private SelecionaOs mListener;
    private ImageView imgAssinatura;
    private byte[] byteArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.content_assinatura_fragment, container, false);
        mListener = (SelecionaOs) getActivity();

        imgAssinatura = (ImageView) layout.findViewById(R.id.imageAssinatura);
        if (mListener.getOsSelecionada().getAssinatura() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(mListener.getOsSelecionada().getAssinatura(), 0, mListener.getOsSelecionada().getAssinatura().length);
            byteArray = mListener.getOsSelecionada().getAssinatura();
            imgAssinatura.setImageBitmap(bitmap);
        }
        return layout;
    }


}
