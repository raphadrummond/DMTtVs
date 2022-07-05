package databit.com.br.datamobile.interfaces;

import databit.com.br.datamobile.domain.Os;

/**
 * Created by Sidney on 19/03/2016.
 */
public interface SelecionaOs {
    void onOsSelecionada(Os os);
    Os getOsSelecionada();

}
