package databit.com.br.datamobile.interfaces;

import databit.com.br.datamobile.domain.Custo;

/**
 * Created by Sidney on 13/12/2017.
 */
public interface SelecionaCusto {
    void onCustoSelecionado(Custo custo);
    Custo onCustoSelecionado();
}

