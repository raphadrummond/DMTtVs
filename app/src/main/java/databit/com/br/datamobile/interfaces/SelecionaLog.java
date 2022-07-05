package databit.com.br.datamobile.interfaces;

import databit.com.br.datamobile.domain.Logsinc;

/**
 * Created by Sidney on 02/02/2018.
 */
public interface SelecionaLog {
    void onLogSelecionado(Logsinc logsinc);
    Logsinc onLogSelecionado();
}
