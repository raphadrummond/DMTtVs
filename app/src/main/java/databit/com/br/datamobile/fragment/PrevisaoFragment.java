package databit.com.br.datamobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.interfaces.SelecionaOs;

public class PrevisaoFragment extends Fragment {

    private SelecionaOs mListener;
    private Button btnGravarPrevisao;
    private EditText edtPrevisao;
    private CheckBox chConcluido;
    private CalendarView calendarView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mListener = (SelecionaOs) getActivity();
        View layout = inflater.inflate(R.layout.content_previsao_fragment, container, false);
        Toast.makeText(getActivity(), "Favor descrever as observações deste Atendimento", Toast.LENGTH_SHORT).show();
        edtPrevisao = (EditText) layout.findViewById(R.id.editPrevisao);
        calendarView = (CalendarView) layout.findViewById(R.id.calendarView);
        calendarView.setDate(mListener.getOsSelecionada().getPrevisao().getTime());
        final SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
        edtPrevisao.setText(sdf.format(mListener.getOsSelecionada().getPrevisao()));
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                month = month + 1;
                if (month == 13) {
                   month = 1;
                   year = year + 1;
                }
                String sData = day+"/"+month+"/"+year;
                try {
                    Date dData = sdf.parse(sData);
                    edtPrevisao.setText(sdf.format(dData));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
        chConcluido = (CheckBox) layout.findViewById(R.id.checkConcluido);
        chConcluido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtPrevisao.setEnabled(chConcluido.isChecked());
                calendarView.setEnabled(chConcluido.isChecked());
            }
        });
        btnGravarPrevisao = (Button) layout.findViewById(R.id.btnGravarprevisao);
        btnGravarPrevisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OsDAO osDAO = new OsDAO();
                    if (mListener.getOsSelecionada().getFechaok().equals(1)) {
                        Date date = new Date();
                        Date previsao = sdf.parse(edtPrevisao.getText().toString());
                        if (date.after(previsao)) {
                            Toast.makeText(getActivity(), "A previsão de retorno não pode ser MENOR ou IGUAL que a data atual !", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mListener.getOsSelecionada().setPrevisao(sdf.parse(edtPrevisao.getText().toString()));
                            if (chConcluido.isChecked()) {
                                mListener.getOsSelecionada().setRespondido("S");
                                mListener.getOsSelecionada().setFecha(date);
                            } else {
                                mListener.getOsSelecionada().setRespondido("N");
                                mListener.getOsSelecionada().setFecha(null);
                            }
                            osDAO.gravaOs(mListener.getOsSelecionada());
                            Toast.makeText(getActivity(), "Informações Salvas com Sucesso !", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getActivity(), "Primeiramente deve-se salvar as informações no botão INFOR", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Problemas ao Salvar:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (mListener.getOsSelecionada().getRespondido() != null) {
            chConcluido.setChecked(mListener.getOsSelecionada().getRespondido().equals("S"));
        }
        if (mListener.getOsSelecionada().getSync().equals("S")) {
            calendarView.setEnabled(false);
            chConcluido.setEnabled(false);
            btnGravarPrevisao.setEnabled(false);
        }
        return layout;
    }

}
