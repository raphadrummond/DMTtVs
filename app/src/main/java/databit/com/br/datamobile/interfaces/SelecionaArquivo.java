package databit.com.br.datamobile.interfaces;

import databit.com.br.datamobile.domain.Arquivo;

/**
 * Created by Sidney on 17/04/2018.
 */
public interface SelecionaArquivo {
    void onArquivoSelecionado(Arquivo arquivo);
    Arquivo onArquivoSelecionado();

}
