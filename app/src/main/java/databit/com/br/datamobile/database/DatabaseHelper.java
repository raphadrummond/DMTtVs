package databit.com.br.datamobile.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import databit.com.br.datamobile.dao.AparelhoDAO;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.domain.Aparelho;
import databit.com.br.datamobile.domain.Arquivo;
import databit.com.br.datamobile.domain.Composicao;
import databit.com.br.datamobile.domain.Condicao;
import databit.com.br.datamobile.domain.ConfigFTP;
import databit.com.br.datamobile.domain.Configmobile;
import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.domain.Custo;
import databit.com.br.datamobile.domain.Defeito;
import databit.com.br.datamobile.domain.Fechamento;
import databit.com.br.datamobile.domain.Historico;
import databit.com.br.datamobile.domain.HistoricoTEL;
import databit.com.br.datamobile.domain.HistoricoTI;
import databit.com.br.datamobile.domain.Informacao;
import databit.com.br.datamobile.domain.Item;
import databit.com.br.datamobile.domain.Logsenha;
import databit.com.br.datamobile.domain.Logsinc;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.domain.OsDefeito;
import databit.com.br.datamobile.domain.Pendente;
import databit.com.br.datamobile.domain.PendenteOS;
import databit.com.br.datamobile.domain.Ponto;
import databit.com.br.datamobile.domain.Produto;
import databit.com.br.datamobile.domain.Recolha;
import databit.com.br.datamobile.domain.Serial;
import databit.com.br.datamobile.domain.Servico;
import databit.com.br.datamobile.domain.ServicoIncompleto;
import databit.com.br.datamobile.domain.Trocada;
import databit.com.br.datamobile.domain.TrocadaOS;
import databit.com.br.datamobile.domain.Usuario;

/**
 * Created by user on 05/04/2016.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private Context context;
    public static final String DATABASE_FILE_NAME = "databit606.sqlite";
    private static final int DATABASE_VERSION = 70;
    private Map<Class, Dao<Object, Object>> daos = new HashMap<Class, Dao<Object, Object>>();

    public DatabaseHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Aparelho.class);
            TableUtils.createTable(connectionSource, Condicao.class);
            TableUtils.createTable(connectionSource, Configuracao.class);
            TableUtils.createTable(connectionSource, Defeito.class);
            TableUtils.createTable(connectionSource, Fechamento.class);
            TableUtils.createTable(connectionSource, Historico.class);
            TableUtils.createTable(connectionSource, Os.class);
            TableUtils.createTable(connectionSource, OsDefeito.class);
            TableUtils.createTable(connectionSource, Pendente.class);
            TableUtils.createTable(connectionSource, Produto.class);
            TableUtils.createTable(connectionSource, ServicoIncompleto.class);
            TableUtils.createTable(connectionSource, Trocada.class);
            TableUtils.createTable(connectionSource, Usuario.class);
            TableUtils.createTable(connectionSource, Informacao.class);
            TableUtils.createTable(connectionSource, PendenteOS.class);
            TableUtils.createTable(connectionSource, TrocadaOS.class);
            TableUtils.createTable(connectionSource, Custo.class);
            TableUtils.createTable(connectionSource, Composicao.class);
            TableUtils.createTable(connectionSource, Servico.class);
            TableUtils.createTable(connectionSource, Logsinc.class);
            TableUtils.createTable(connectionSource, Configmobile.class);
            TableUtils.createTable(connectionSource, ConfigFTP.class);
            TableUtils.createTable(connectionSource, HistoricoTI.class);
            TableUtils.createTable(connectionSource, HistoricoTEL.class);
            TableUtils.createTable(connectionSource, Item.class);
            TableUtils.createTable(connectionSource, Serial.class);
            TableUtils.createTable(connectionSource, Recolha.class);
            TableUtils.createTable(connectionSource, Arquivo.class);
            TableUtils.createTable(connectionSource, Ponto.class);
            TableUtils.createTable(connectionSource, Logsenha.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            List<String> allSql = new ArrayList<String>();
            switch (oldVersion) {
                case 1: {
                    TableUtils.dropTable(connectionSource, OsDefeito.class, true);
                    TableUtils.createTable(connectionSource, OsDefeito.class);
                    break;
                }
                case 2: {
                    TableUtils.dropTable(connectionSource, OsDefeito.class, true);
                    TableUtils.createTable(connectionSource, OsDefeito.class);

                    TableUtils.dropTable(connectionSource, Pendente.class, true);
                    TableUtils.createTable(connectionSource, Pendente.class);
                    break;
                }
                case 3: {
                    allSql.add("alter table fechamento add assinatura2");
                    break;
                }
                case 4: {
                    allSql.add("alter table fechamento drop assinatura2");
                    break;
                }
                case 5: {
                    allSql.add("alter table os add id");
                    allSql.add("alter table os add banco");
                    allSql.add("alter table os add bancoos");

                    allSql.add("alter table fechamento add banco");
                    allSql.add("alter table fechamento add bancoos");

                    allSql.add("alter table informacao add banco");
                    allSql.add("alter table informacao add bancoos");
                    break;
                }
                case 6: {
                    allSql.add("alter table os add nometecnico");
                    break;
                }
                case 7: {
                    allSql.add("alter table configuracao add maischeckin");
                    allSql.add("alter table configuracao add obrkm");
                    allSql.add("alter table os add obscanc");
                    break;
                }
                case 8: {
                    allSql.add("alter table fechamento add nometec");
                    break;
                }
                case 9: {
                    allSql.add("alter table fechamento add data");
                    break;
                }
                case 10: {
                    allSql.add("alter table os add checkinok");
                    allSql.add("alter table os add checkoutok");
                    allSql.add("alter table os add fechaok");
                    allSql.add("alter table os add datain");
                    allSql.add("alter table os add dataout");
                    allSql.add("alter table os add datafecha");
                    allSql.add("alter table os add endin");
                    allSql.add("alter table os add numin");
                    allSql.add("alter table os add compin");
                    allSql.add("alter table os add bairroin");
                    allSql.add("alter table os add cidadein");
                    allSql.add("alter table os add estadoin");
                    allSql.add("alter table os add cepin");
                    allSql.add("alter table os add latitudein");
                    allSql.add("alter table os add longitudein");
                    allSql.add("alter table os add endout");
                    allSql.add("alter table os add numout");
                    allSql.add("alter table os add compout");
                    allSql.add("alter table os add bairroout");
                    allSql.add("alter table os add cidadeout");
                    allSql.add("alter table os add estadoout");
                    allSql.add("alter table os add cepout");
                    allSql.add("alter table os add latitudeout");
                    allSql.add("alter table os add longitudeout");
                    allSql.add("alter table os add contador");
                    allSql.add("alter table os add credcopias");
                    allSql.add("alter table os add cilindro");
                    allSql.add("alter table os add sitcilindro");
                    allSql.add("alter table os add contcilindro");
                    allSql.add("alter table os add revelador");
                    allSql.add("alter table os add condicaofinal");
                    allSql.add("alter table os add incompleto");
                    allSql.add("alter table os add servincompl");
                    allSql.add("alter table os add obsfecha");
                    allSql.add("alter table os add contadorc");
                    allSql.add("alter table os add credcopiasc");
                    allSql.add("alter table os add fusor");
                    allSql.add("alter table os add belt");
                    allSql.add("alter table os add kminicial");
                    allSql.add("alter table os add kmfinal");
                    allSql.add("alter table os add cilindroc");
                    allSql.add("alter table os add reveladorc");
                    allSql.add("alter table os add sitcilindroc");
                    allSql.add("alter table os add contcilindroc");
                    allSql.add("alter table os add reservatorio");
                    allSql.add("alter table os add placa");
                    allSql.add("alter table os add obspendente");
                    allSql.add("alter table os add obstrocada");
                    allSql.add("alter table os add assinatura");
                    break;
                }
                case 11: {
                    allSql.add("alter table os add numlocalin");
                    allSql.add("alter table os add numlocalout");
                    break;
                }
                case 12: {
                    allSql.add("alter table aparelho add latitude");
                    allSql.add("alter table aparelho add longitude");
                    break;
                }
                case 13: {
                    allSql.add("alter table os add tipocontrato");
                    break;
                }
                case 14: {
                    allSql.add("alter table os add obsservicos");
                    allSql.add("alter table os add contadordg");
                    allSql.add("alter table os add credcopiasdg");
                    allSql.add("alter table os add contadorgf");
                    allSql.add("alter table os add credcopiasgf");
                    allSql.add("alter table os add contadorgfc");
                    allSql.add("alter table os add credcopiasgfc");
                    allSql.add("alter table os add site");
                    allSql.add("alter table os add departamento");
                    allSql.add("alter table os add contadorostotal");
                    allSql.add("alter table os add contadorospb");
                    allSql.add("alter table os add contadoroscolor");
                    allSql.add("alter table os add classificacao");
                    break;
                }
                case 15: {
                    TableUtils.createTable(connectionSource, PendenteOS.class);
                    TableUtils.createTable(connectionSource, TrocadaOS.class);
                    TableUtils.createTable(connectionSource, Custo.class);
                    break;
                }
                case 16: {
                    TableUtils.createTable(connectionSource, Composicao.class);
                    TableUtils.createTable(connectionSource, Servico.class);
                    break;
                }
                case 17: {
                    allSql.add("alter table produto add banco");
                    allSql.add("alter table servico add banco");
                    allSql.add("alter table composicao add banco");
                    allSql.add("alter table custo add banco");
                    allSql.add("alter table pendenteos add banco");
                    allSql.add("alter table trocadaos add banco");
                    break;
                }
                case 18: {
                    TableUtils.dropTable(connectionSource, PendenteOS.class, true);
                    TableUtils.dropTable(connectionSource, TrocadaOS.class, true);
                    TableUtils.dropTable(connectionSource, Custo.class, true);
                    TableUtils.dropTable(connectionSource, Composicao.class, true);
                    TableUtils.dropTable(connectionSource, Servico.class, true);
                    TableUtils.dropTable(connectionSource, Produto.class, true);

                    TableUtils.createTable(connectionSource, PendenteOS.class);
                    TableUtils.createTable(connectionSource, TrocadaOS.class);
                    TableUtils.createTable(connectionSource, Custo.class);
                    TableUtils.createTable(connectionSource, Composicao.class);
                    TableUtils.createTable(connectionSource, Servico.class);
                    TableUtils.createTable(connectionSource, Produto.class);
                    break;
                }
                case 19: {
                    TableUtils.dropTable(connectionSource, Servico.class, true);
                    break;
                }
                case 20: {
                    TableUtils.createTable(connectionSource, Servico.class);
                    break;
                }
                case 21: {
                    TableUtils.dropTable(connectionSource, Servico.class, true);
                    break;
                }
                case 22: {
                    TableUtils.createTable(connectionSource, Servico.class);
                    break;
                }
                case 23: {
                    TableUtils.dropTable(connectionSource, Servico.class, true);
                    TableUtils.createTable(connectionSource, Servico.class);
                    break;
                }
                case 24: {
                    allSql.add("alter table os add lido");
                    allSql.add("alter table configuracao add tipolan");
                    break;
                }
                case 25: {
                    allSql.add("alter table os add idpendente");
                    allSql.add("alter table os add idtrocada");
                    allSql.add("alter table os add idservico");
                    break;
                }
                case 26: {
                    TableUtils.dropTable(connectionSource, PendenteOS.class, true);
                    TableUtils.createTable(connectionSource, PendenteOS.class);
                    break;
                }
                case 27: {
                    TableUtils.dropTable(connectionSource, TrocadaOS.class, true);
                    TableUtils.createTable(connectionSource, TrocadaOS.class);
                    break;
                }
                case 28: {
                    TableUtils.dropTable(connectionSource, Composicao.class, true);
                    TableUtils.createTable(connectionSource, Composicao.class);
                    break;
                }
                case 29: {
                    TableUtils.dropTable(connectionSource, TrocadaOS.class, true);
                    TableUtils.createTable(connectionSource, TrocadaOS.class);
                    break;
                }
                case 30: {
                    TableUtils.dropTable(connectionSource, Custo.class, true);
                    TableUtils.createTable(connectionSource, Custo.class);
                    break;
                }
                case 31: {
                    allSql.add("alter table os add requisicao");
                    allSql.add("alter table os add contreq");
                    break;
                }
                case 32: {
                    allSql.add("alter table os add online");
                    break;
                }
                case 33: {
                    allSql.add("alter table os add ordemtec");
                    allSql.add("alter table configuracao add temposinc");
                    allSql.add("alter table configuracao add temposmens");
                    break;
                }
                case 34: {
                    TableUtils.dropTable(connectionSource, PendenteOS.class, true);
                    TableUtils.dropTable(connectionSource, TrocadaOS.class, true);
                    TableUtils.dropTable(connectionSource, Custo.class, true);
                    TableUtils.dropTable(connectionSource, Composicao.class, true);
                    TableUtils.dropTable(connectionSource, Servico.class, true);
                    TableUtils.dropTable(connectionSource, Produto.class, true);

                    TableUtils.createTable(connectionSource, PendenteOS.class);
                    TableUtils.createTable(connectionSource, TrocadaOS.class);
                    TableUtils.createTable(connectionSource, Custo.class);
                    TableUtils.createTable(connectionSource, Composicao.class);
                    TableUtils.createTable(connectionSource, Servico.class);
                    TableUtils.createTable(connectionSource, Produto.class);
                    break;
                }
                case 35: {
                    allSql.add("alter table configuracao add req");
                    break;
                }
                case 36: {
                    AparelhoDAO aparelhoDAO = new AparelhoDAO();
                    Aparelho aparelho = aparelhoDAO.procuraAparelho("id = 1");
                    if (aparelho == null) {
                        TableUtils.dropTable(connectionSource, Configuracao.class, true);
                        TableUtils.createTable(connectionSource, Configuracao.class);
                    }
                    else {
                        if (aparelho.getVersaocode() < 30) {
                            TableUtils.dropTable(connectionSource, Configuracao.class, true);
                            TableUtils.createTable(connectionSource, Configuracao.class);
                        }
                    }
                    break;
                }
                case 37: {
                    TableUtils.dropTable(connectionSource, Logsinc.class, true);
                    TableUtils.createTable(connectionSource, Logsinc.class);
                    break;
                }
                case 38: {
                    TableUtils.dropTable(connectionSource, Logsinc.class, true);
                    TableUtils.createTable(connectionSource, Logsinc.class);
                    break;
                }
                case 39: {
                    allSql.add("alter table os add usuariodataservice");
                    allSql.add("alter table os add operacaomobile");
                    allSql.add("alter table os add sigla");
                    allSql.add("alter table os add obsadd");
                    break;
                }
                case 40: {
                    OsDAO osDAO = new OsDAO();
                    List<Os> listOs = osDAO.findAll();
                    AparelhoDAO aparelhoDAO = new AparelhoDAO();
                    Aparelho aparelho = aparelhoDAO.procuraAparelho("id = 1");
                    if (listOs.size() > 0) {
                        for (Os os : listOs) {
                            os.setUsuariodataservice(aparelho.getUsuario().toString());
                            os.setOperacaomobile(1);
                            os.setSigla("OS");
                            osDAO.createOrUpdate(os);
                        }
                    }
                    break;
                }
                case 41: {
                    allSql.add("alter table os add defeito");
                    allSql.add("alter table os add laudo");
                    allSql.add("alter table os add modulo");
                    allSql.add("alter table os add participantes");
                    allSql.add("alter table os add recebido");
                    allSql.add("alter table os add respondido");
                    allSql.add("alter table os add previsaoretorno");
                    allSql.add("alter table os add verlaboratorio");
                    allSql.add("alter table os add verremoto");
                    allSql.add("alter table os add verpresencial");
                    allSql.add("alter table os add verprojeto");
                    allSql.add("alter table os add versistema");
                    allSql.add("alter table os add verimplantacao");
                    allSql.add("alter table os add qtdeequip");
                    allSql.add("alter table os add vlproposta");
                    allSql.add("alter table os add classificacaoatende");

                    allSql.add("alter table logsinc add operacaomobile");
                    break;
                }
                case 42: {
                    TableUtils.dropTable(connectionSource, Configmobile.class, true);
                    TableUtils.createTable(connectionSource, Configmobile.class);

                    TableUtils.dropTable(connectionSource, ConfigFTP.class, true);
                    TableUtils.createTable(connectionSource, ConfigFTP.class);

                    TableUtils.dropTable(connectionSource, HistoricoTI.class, true);
                    TableUtils.createTable(connectionSource, HistoricoTI.class);

                    TableUtils.dropTable(connectionSource, HistoricoTEL.class, true);
                    TableUtils.createTable(connectionSource, HistoricoTEL.class);

                    TableUtils.dropTable(connectionSource, Item.class, true);
                    TableUtils.createTable(connectionSource, Item.class);

                    TableUtils.dropTable(connectionSource, Serial.class, true);
                    TableUtils.createTable(connectionSource, Serial.class);
                    break;
                }
                case 43: {
                    TableUtils.dropTable(connectionSource, Serial.class, true);
                    TableUtils.createTable(connectionSource, Serial.class);
                    break;
                }
                case 44: {
                    allSql.add("alter table os add conferido");
                    break;
                }
                case 45: {
                    allSql.add("alter table historicotel add codcli");
                    allSql.add("alter table historicotel add banco");
                    break;
                }
                case 46: {
                    allSql.add("alter table historicotel add resposta");
                    break;
                }
                case 47: {
                    allSql.add("alter table configuracao add recolha");
                    allSql.add("alter table os add codrecolha");
                    allSql.add("alter table os add obsrecolha");
                    TableUtils.dropTable(connectionSource, Recolha.class, true);
                    TableUtils.createTable(connectionSource, Recolha.class);
                    break;
                }
                case 48: {
                    TableUtils.dropTable(connectionSource, Arquivo.class, true);
                    TableUtils.createTable(connectionSource, Arquivo.class);
                    break;
                }
                case 49: {
                    TableUtils.dropTable(connectionSource, Arquivo.class, true);
                    TableUtils.createTable(connectionSource, Arquivo.class);
                    break;
                }
                case 50: {
                    TableUtils.dropTable(connectionSource, Arquivo.class, true);
                    TableUtils.createTable(connectionSource, Arquivo.class);
                    break;
                }
                case 51: {
                    allSql.add("alter table os add idrecolha");
                    allSql.add("alter table os add idarquivo");
                    break;
                }
                case 52: {
                    allSql.add("alter table aparelho add data");
                    break;
                }
                case 53: {
                    allSql.add("alter table os add ntfisc");
                    break;
                }
                case 54: {
                    allSql.add("alter table os add identrega");
                    allSql.add("alter table os add obsrecolha2");
                    break;
                }
                case 55: {
                    TableUtils.dropTable(connectionSource, Ponto.class, true);
                    TableUtils.createTable(connectionSource, Ponto.class);
                    break;
                }
                case 56: {
                    allSql.add("alter table configuracao add ponto");
                    allSql.add("alter table configuracao add intervalo");
                    allSql.add("alter table configuracao add horaintervalo");
                    allSql.add("alter table configuracao add horafinal");
                    allSql.add("alter table configuracao add horainimanha");
                    allSql.add("alter table configuracao add horafimmanha");
                    allSql.add("alter table configuracao add horainitarde");
                    allSql.add("alter table configuracao add horafimtarde");
                    break;
                }
                case 57: {
                    allSql.add("alter table ponto add local");
                    allSql.add("alter table usuario add ponto");
                    break;
                }
                case 58: {
                    allSql.add("alter table ponto add fusohorario");
                    break;
                }
                case 59: {
                    allSql.add("alter table usuario add altsenha");
                    allSql.add("alter table usuario add hrinimanhaseg");
                    allSql.add("alter table usuario add hrfimmanhaseg");
                    allSql.add("alter table usuario add hrinitardeseg");
                    allSql.add("alter table usuario add hrfimtardeseg");
                    allSql.add("alter table usuario add hrinimanhater");
                    allSql.add("alter table usuario add hrfimmanhater");
                    allSql.add("alter table usuario add hrinitardeter");
                    allSql.add("alter table usuario add hrfimtardeter");
                    allSql.add("alter table usuario add hrinimanhaqua");
                    allSql.add("alter table usuario add hrfimmanhaqua");
                    allSql.add("alter table usuario add hrinitardequa");
                    allSql.add("alter table usuario add hrfimtardequa");
                    allSql.add("alter table usuario add hrinimanhaqui");
                    allSql.add("alter table usuario add hrfimmanhaqui");
                    allSql.add("alter table usuario add hrinitardequi");
                    allSql.add("alter table usuario add hrfimtardequi");
                    allSql.add("alter table usuario add hrinimanhasex");
                    allSql.add("alter table usuario add hrfimmanhasex");
                    allSql.add("alter table usuario add hrinitardesex");
                    allSql.add("alter table usuario add hrfimtardesex");
                    break;
                }
                case 60: {
                    allSql.add("update usuario set altsenha = 0");
                    break;
                }
                case 61: {
                    TableUtils.createTable(connectionSource, Logsenha.class);
                    break;
                }
                case 62: {
                    allSql.add("alter table ponto add integra");
                    break;
                }
                case 63: {
                    allSql.add("alter table ponto add sync");
                    break;
                }
                case 64: {
                    allSql.add("alter table os add ajudante");
                    break;
                }
                case 65: {
                    TableUtils.dropTable(connectionSource, Os.class, true);
                    TableUtils.createTable(connectionSource, Os.class);
                    break;
                }
                case 66: {
                    TableUtils.dropTable(connectionSource, Ponto.class, true);
                    TableUtils.createTable(connectionSource, Ponto.class);
                    break;
                }
                case 67: {
                    TableUtils.dropTable(connectionSource, Os.class, true);
                    TableUtils.createTable(connectionSource, Os.class);
                    break;
                }
                case 68: {
                    allSql.add("alter table os add nomeoperacaomobile");
                    break;
                }
                case 69: {
                    TableUtils.dropTable(connectionSource, Os.class, true);
                    TableUtils.createTable(connectionSource, Os.class);
                    break;
                }

            }
            for (String sql : allSql) {
                try {
                    db.execSQL(sql);
                } catch (Throwable t) {

                }
            }
        } catch (android.database.SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "exception during onUpgrade", e);
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T> Dao<T, Object> getDAO(Class<T> entidadeClass) {
        Dao<Object, Object> dao = null;
        if (daos.get(entidadeClass) == null) {
            try {
                dao = (Dao<Object, Object>) getDao(entidadeClass);
            } catch (SQLException e) {
                Log.e(DatabaseHelper.class.getName(), "exception during getDAO", e);
                throw new RuntimeException(e);
            }
            daos.put(entidadeClass, dao);
        }

        return (Dao<T, Object>) daos.get(entidadeClass);
    }
}
