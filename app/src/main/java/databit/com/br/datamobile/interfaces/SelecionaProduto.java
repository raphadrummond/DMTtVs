package databit.com.br.datamobile.interfaces;

import databit.com.br.datamobile.domain.Produto;

/**
 * Created by Sidney on 14/12/2017.
 */
public interface SelecionaProduto {
    void onProdutoSelecionado(Produto produto);
    Produto onProdutoSelecionado();
}
