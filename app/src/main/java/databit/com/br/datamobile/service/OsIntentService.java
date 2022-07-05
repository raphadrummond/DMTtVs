package databit.com.br.datamobile.service;

import android.app.IntentService;
import android.content.Intent;

import java.util.Collections;
import java.util.List;

import databit.com.br.datamobile.comparator.OsComparator;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.wsclient.OsWSClient;

/**
 * Created by Sidney on 16/09/2016.
 */
public class OsIntentService extends IntentService {
    private String sResult;
    public OsIntentService() {
        super("OsIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        OsWSClient wsClient = new OsWSClient();
        OsDAO osDAO = new OsDAO();
        List<Os> listOs = osDAO.listarOs(" fechaok = 1 and sync = 'N' ");
        OsComparator osComparator = new OsComparator();
        Collections.sort(listOs, osComparator);
        for (Os os : listOs) {
            sResult = wsClient.enviaOS(os);
            if (sResult.equals("OK")) {
                if (os.getStatus_check() == 4) {
                    os.setSync("S");
                    osDAO.gravaOs(os);
                }
            }
        }
    }
}
