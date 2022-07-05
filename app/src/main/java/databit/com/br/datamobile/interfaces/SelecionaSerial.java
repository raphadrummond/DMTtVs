package databit.com.br.datamobile.interfaces;

import databit.com.br.datamobile.domain.Serial;

/**
 * Created by Sidney on 11/04/2018.
 */
public interface SelecionaSerial {
    void onSerialSelecionado(Serial serial);
    Serial onSerialSelecionado();
}
