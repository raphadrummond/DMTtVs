package databit.com.br.datamobile.interfaces;

import databit.com.br.datamobile.domain.PendenteOS;

/**
 * Created by Sidney on 13/12/2017.
 */
public interface SelecionaPendenteOS {
    void onPendenteOSSelecionado(PendenteOS pendenteOS);
    PendenteOS onPendenteOSSelecionado();
}
