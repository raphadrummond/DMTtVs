package databit.com.br.datamobile.comparator;

import databit.com.br.datamobile.domain.Os;
import java.util.Comparator;

/**
 * Created by Sidney on 16/09/2016.
 */
public class OsComparator implements Comparator<Os> {
    public int compare(Os os, Os os2) {
        return os.getDatafecha().compareTo(os2.getDatafecha());
    }
}
