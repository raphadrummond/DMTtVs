package databit.com.br.datamobile.interfaces;

import databit.com.br.datamobile.domain.Pendente;

/**
 * Created by Sidney on 12/05/2016.
 */
public interface SelecionaPendente {
    void onPendenteSelecionado(Pendente pendente);
    Pendente onPendenteSelecionado();
}
