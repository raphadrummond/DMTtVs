package databit.com.br.datamobile.interfaces;

import databit.com.br.datamobile.domain.Item;

/**
 * Created by Sidney on 11/04/2018.
 */
public interface SelecionaItem {
    void onItemSelecionado(Item item);
    Item onItemSelecionado();
}
