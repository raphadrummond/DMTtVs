package databit.com.br.datamobile.interfaces;

import databit.com.br.datamobile.domain.Recolha;

/**
 * Created by Sidney on 16/04/2018.
 */
public interface SelecionaRecolha {
    void onRecolhaSelecionado(Recolha recolha);
    Recolha onRecolhaSelecionado();
}
