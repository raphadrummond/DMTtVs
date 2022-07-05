package databit.com.br.datamobile.interfaces;

import databit.com.br.datamobile.domain.HistoricoTI;

/**
 * Created by Sidney on 09/04/2018.
 */
public interface SelecionaHistoricoTI  {
    void onHistoricoTISelecionado(HistoricoTI historicoTI);
    HistoricoTI onHistoricoTISelecionado();
}
