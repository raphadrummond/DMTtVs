package databit.com.br.datamobile.interfaces;

import databit.com.br.datamobile.domain.OsDefeito;

/**
 * Created by Sidney on 12/05/2016.
 */
public interface SelecionaOsDefeito {
    void onOsDefeitoSelecionado(OsDefeito osDefeito);
    OsDefeito onOsDefeitoSelecionado();

}
