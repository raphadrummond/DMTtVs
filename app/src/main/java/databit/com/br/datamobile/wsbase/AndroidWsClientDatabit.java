package databit.com.br.datamobile.wsbase;

public class AndroidWsClientDatabit extends WsClient {

    @Override
    protected String getUrl() {
        return "http://databitbh.com:8088/restdatabit.dll/datasnap/rest/tmetodos/";
    }

    @Override
    protected String getName() {
        return getUrl();
    }
}
