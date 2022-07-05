package databit.com.br.datamobile.application;

import android.app.Application;
import android.content.Context;

import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.database.DatabaseManager;
import databit.com.br.datamobile.domain.Configuracao;


/**
 * Created by user on 05/04/2016.
 */
public class DataMobileApplication extends Application {

    public static Context getContext;

    @Override
    public void onCreate() {
        super.onCreate();
        getContext = getApplicationContext();
        DatabaseManager.init(getApplicationContext());
    }
}
