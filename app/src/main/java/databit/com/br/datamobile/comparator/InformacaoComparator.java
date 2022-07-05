package databit.com.br.datamobile.comparator;

import java.util.Comparator;

import databit.com.br.datamobile.domain.Informacao;

/**
 * Created by Sidney on 10/08/2016.
 */
public class InformacaoComparator implements Comparator<Informacao> {
    public int compare(Informacao informacao, Informacao informacao2) {
        return informacao.getData().compareTo(informacao2.getData());
    }
}
