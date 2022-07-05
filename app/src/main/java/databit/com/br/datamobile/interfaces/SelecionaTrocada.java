package databit.com.br.datamobile.interfaces;

import databit.com.br.datamobile.domain.Trocada;

/**
 * Created by Sidney on 13/05/2016.
 */
public interface SelecionaTrocada {
    void onTrocadaSelecionado(Trocada trocada);
    Trocada onTrocadaSelecionado();
}
