package databit.com.br.datamobile.interfaces;

import databit.com.br.datamobile.domain.Historico;

/**
 * Created by Sidney on 11/05/2016.
 */
public interface SelecionaHistorico {
    void onHistoricoSelecionado(Historico historico);
    Historico onHistoricoSelecionado();

}
