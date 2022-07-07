package databit.com.br.datamobile.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import databit.com.br.datamobile.R;
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
