package databit.com.br.datamobile.comparator;

import databit.com.br.datamobile.domain.Fechamento;
import java.util.Comparator;

/**
 * Created by Sidney on 10/08/2016.
 */
public class FechamentoComparator implements Comparator<Fechamento> {
    public int compare(Fechamento fechamento, Fechamento fechamento2) {
        return fechamento.getData().compareTo(fechamento2.getData());
    }
}
