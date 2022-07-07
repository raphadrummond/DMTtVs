package databit.com.br.datamobile.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.adapter.AdapterArquivo;
import databit.com.br.datamobile.dao.ArquivoDAO;
import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.domain.Arquivo;
import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.file.FileOpen;
import databit.com.br.datamobile.ftp.MyFTP;
import databit.com.br.datamobile.infra.Internet;
import databit.com.br.datamobile.interfaces.SelecionaArquivo;
import databit.com.br.datamobile.interfaces.SelecionaOs;

public class ArquivoFragment extends Fragment {
    private SelecionaOs mListener;
    private Button btnIncluirarq;
    private Button btnExcluirarq;
    private Button btnCapturar;
    private RecyclerView.Adapter adapter;
    private List<Arquivo> listArquivo;
    private ArquivoDAO ArquivoDAO;
    private RecyclerView recyclerView;
    private static final int READ_REQUEST_CODE = 42;
    private static final int REQUEST_CAPTURE_IMAGE = 100;
    private Uri uri = null;
    private Arquivo arquivo;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View layout = inflater.inflate(R.layout.content_arquivo_fragment, container, false);
        FragmentActivity c = getActivity();

        mListener = (SelecionaOs) getActivity();

        recyclerView = (RecyclerView) layout.findViewById(R.id.arquivo_recycler_view);

        LinearLayoutManager layoutManager = new  LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArquivoDAO = new ArquivoDAO();
        listArquivo = ArquivoDAO.listarArquivo(" codigo = '" + mListener.getOsSelecionada().getCodigo() + "'" +
                                               " and banco = '" + mListener.getOsSelecionada().getBanco() + "'");
        adapter = new AdapterArquivo(listArquivo , (SelecionaArquivo) getActivity(), recyclerView);
        recyclerView.setAdapter(adapter);

        ConfiguracaoDAO configuracaoDAO = new ConfiguracaoDAO();
        Configuracao configuracao = configuracaoDAO.procuraConfiguracao("id = 1");

        btnIncluirarq = (Button) layout.findViewById(R.id.btnIncluirarq);
        btnIncluirarq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean bValidar = true;
                if (mListener.getOsSelecionada().getOperacaomobile().equals(1)) {
                    bValidar = ((mListener.getOsSelecionada().getStatus_check() >= 3) || (mListener.getOsSelecionada().getPreventiva().equals("A")));
                }
                if (bValidar) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, READ_REQUEST_CODE);
                }
                else {
                    Toast.makeText(getActivity(), "Esta OS ainda não foi inicializada!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnExcluirarq = (Button) layout.findViewById(R.id.btnExcluirarq);
        btnExcluirarq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean bValidar = true;
                if (mListener.getOsSelecionada().getOperacaomobile().equals(1)) {
                    bValidar = ((mListener.getOsSelecionada().getStatus_check() >= 3) || (mListener.getOsSelecionada().getPreventiva().equals("A")));
                }
                if (bValidar) {
                    final Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.dialog);
                    dialog.setTitle("Digite o ID:");
                    final EditText txtMotivo = (EditText) dialog.findViewById(R.id.txtMotivo);
                    txtMotivo.setText("1");
                    txtMotivo.setInputType(InputType.TYPE_CLASS_NUMBER);
                    dialog.show();
                    Button btnOk = (Button) dialog.findViewById(R.id.btnOK);
                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ArquivoDAO arquivoDAO = new ArquivoDAO();
                            arquivo = arquivoDAO.procuraArquivo(" codigo = '" + mListener.getOsSelecionada().getCodigo() + "' " +
                                                                " and banco = '" + mListener.getOsSelecionada().getBanco() + "' " +
                                                                " and item = " + txtMotivo.getText().toString());
                            if (arquivo != null) {
                                ExcluirArquivo();
                                dialog.dismiss();
                                onStart();
                            } else {
                                Toast.makeText(getActivity(), "Item não existente !", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getActivity(), "Esta OS ainda não foi inicializada!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnCapturar = (Button) layout.findViewById(R.id.btnCapturar);
        btnCapturar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean bValidar = true;
                if (mListener.getOsSelecionada().getOperacaomobile().equals(1)) {
                    bValidar = ((mListener.getOsSelecionada().getStatus_check() >= 3) || (mListener.getOsSelecionada().getPreventiva().equals("A")));
                }
                if (bValidar) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    uri = getOutputMediaFileUri(1);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(takePictureIntent, REQUEST_CAPTURE_IMAGE);
                }
                else {
                    Toast.makeText(getActivity(), "Esta OS ainda não foi inicializada!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        if (mListener.getOsSelecionada().getSync().equals("S")) {
            btnIncluirarq.setEnabled(false);
            btnExcluirarq.setEnabled(false);
            btnCapturar.setEnabled(false);
        }
        return layout;
    }

    private Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "DCIM/Camera");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == 1) { // image
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        } else if (type == 2) { // video
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }


    @Override
    public void onStart() {
        ArquivoDAO = new ArquivoDAO();
        listArquivo = ArquivoDAO.listarArquivo(" codigo = '" + mListener.getOsSelecionada().getCodigo() + "'" +
                                               " and banco = '" + mListener.getOsSelecionada().getBanco() + "'");
        adapter = new AdapterArquivo(listArquivo , (SelecionaArquivo) getActivity(), recyclerView);
        recyclerView.setAdapter(adapter);
        super.onStart();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
             if (data != null) {
                 uri = data.getData();
                 IncluirArquivo();
             }
        }
        if (requestCode == REQUEST_CAPTURE_IMAGE && resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                Bitmap scaledImage = scaleBitmap(bitmap , 1024, 768);
                uri = getImageUri(getContext(),scaledImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            IncluirArquivo();
        }
    }

    private Bitmap scaleBitmap(Bitmap bm, int maxWidth, int maxHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();

        if (width > height) {
            // landscape
            int ratio = width / maxWidth;
            width = maxWidth;
            height = height / ratio;
        } else if (height > width) {
            // portrait
            int ratio = height / maxHeight;
            height = maxHeight;
            width = width / ratio;
        } else {
            // square
            height = maxHeight;
            width = maxWidth;
        }

        bm = Bitmap.createScaledBitmap(bm, width, height, true);
        return bm;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 60, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void IncluirArquivo() {
        SincIncluirArquivoAsyncTask task = new SincIncluirArquivoAsyncTask();
        task.execute();
    }

    class SincIncluirArquivoAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private boolean bErro = false;
        private String sResult;
        private String sErro;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getActivity(), null, "Enviando Arquivo");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            sResult="";
            sErro="";
            try {
                if ((Internet.isDeviceOnline(getActivity().getBaseContext())) && (Internet.urlOnline(getActivity().getBaseContext()))) {
                    MyFTP myFTP = new MyFTP();
                    myFTP.os = mListener.getOsSelecionada();
                    sResult = myFTP.incluirFTP(FileOpen.getFileName(uri, getActivity()),uri,getActivity().getBaseContext());
                    bErro = !(sResult.equals("OK"));
                    if (bErro == true) {
                      sErro = sResult;
                    }
                }
                else {
                    bErro = true;
                    sErro = "Para realizar esta Operação, o dispositivo tem estar ON-LINE !";
                }

            } catch (Throwable t) {
                bErro = true;
                if (sErro.equals("")) {
                    sErro = t.getMessage().toString();
                }
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                Toast.makeText(getActivity(), "Operação realizada com Sucesso ! ", Toast.LENGTH_SHORT).show();
                onStart();
            }
            else {
                Toast.makeText(getActivity(), "Erro não foi possivel realizar a operação: "+sErro, Toast.LENGTH_SHORT).show();
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    private void ExcluirArquivo() {
        SincExcluirArquivoAsyncTask task = new SincExcluirArquivoAsyncTask();
        task.execute();
    }

    class SincExcluirArquivoAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private boolean bErro = false;
        private String sResult;
        private String sErro;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getActivity(), null, "Excluindo Arquivo");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            sResult="";
            sErro="";
            try {
                if ((Internet.isDeviceOnline(getActivity().getBaseContext())) && (Internet.urlOnline(getActivity().getBaseContext()))) {
                    MyFTP myFTP = new MyFTP();
                    myFTP.os = mListener.getOsSelecionada();
                    sResult = myFTP.excluirFTP(arquivo.getArquivo());
                    bErro = !(sResult.equals("OK"));
                    if (bErro == true) {
                        sErro = sResult;
                    }
                }
                else {
                    bErro = true;
                    sErro = "Para realizar esta Operação, o dispositivo tem estar ON-LINE !";
                }

            } catch (Throwable t) {
                bErro = true;
                if (sErro.equals("")) {
                    sErro = t.getMessage().toString();
                }
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                Toast.makeText(getActivity(), "Operação realizada com Sucesso ! ", Toast.LENGTH_SHORT).show();
                onStart();
            }
            else {
                Toast.makeText(getActivity(), "Erro não foi possivel realizar a operação: "+sErro, Toast.LENGTH_SHORT).show();
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

}
