package databit.com.br.datamobile.ponto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import databit.com.br.datamobile.dao.AparelhoDAO;
import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.dao.PontoDAO;
import databit.com.br.datamobile.dao.UsuarioDAO;
import databit.com.br.datamobile.domain.Aparelho;
import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.domain.Ponto;
import databit.com.br.datamobile.domain.Usuario;
import databit.com.br.datamobile.wsclient.AparelhoWSClient;
import databit.com.br.datamobile.wsclient.PontoWSClient;

public class ControlePonto {

    public static String ValidarPonto(Boolean bExecucao, Integer iOperacao) throws ParseException {
        String sRetorno = "OK";
        ConfiguracaoDAO configuracaoDAO = new ConfiguracaoDAO();
        Configuracao configuracao = configuracaoDAO.procuraConfiguracao("1 = 1");
        PontoDAO pontoDAO = new PontoDAO();
        if (configuracao.getPonto().equals("S")) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            AparelhoDAO aparelhoDAO = new AparelhoDAO();
            Aparelho aparelho = aparelhoDAO.procuraAparelho("id = 1");
            String sHorainimanha = configuracao.getHorainimanha();
            Usuario usuario = usuarioDAO.procuraUsuario("nome = '" + aparelho.getUsuario().toString() + "'");
            if (usuario.getPonto().equals("S")) {
                Date datahora = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(datahora);
                Integer iDiasemana = calendar.get(Calendar.DAY_OF_WEEK);
                if (iDiasemana.equals(1) || iDiasemana.equals(7)) {
                    sRetorno = "Não é possível realizar ponto e atendimentos durante o FINAL DE SEMANA !";
                }
                else {
                    switch (iDiasemana) {
                        case 2: {
                            sHorainimanha = usuario.getHrinimanhaseg();
                            break;
                        }
                        case 3: {
                            sHorainimanha = usuario.getHrinimanhater();
                            break;
                        }
                        case 4: {
                            sHorainimanha = usuario.getHrinimanhaqua();
                            break;
                        }
                        case 5: {
                            sHorainimanha = usuario.getHrinimanhaqui();
                            break;
                        }
                        case 6: {
                            sHorainimanha = usuario.getHrinimanhasex();
                            break;
                        }
                    }
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    String sData = formato.format(datahora);
                    List<Ponto> lista = new ArrayList<>();
                    lista = pontoDAO.listarPonto("sdata = '"+sData+"' and usuario = '" + aparelho.getUsuario().toString() + "' ");
                    Integer iTipo = lista.size();
                    DateFormat formatodatahora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date dInimanha = formatodatahora.parse(sData + " " + sHorainimanha + ":00");
                    Date dIntervalo = formatodatahora.parse(sData + " " + configuracao.getHoraintervalo().toString() + ":00");
                    Date dFinal = formatodatahora.parse(sData + " " + configuracao.getHorafinal().toString() + ":00");
                    if (iTipo.equals(0) && (bExecucao)) {
                        sRetorno = "O Usuário ainda não bateu ponto de hoje !";
                    }
                    else {
                        if (!(bExecucao)) {
                            iTipo = iTipo + 1;
                        }
                        if (iTipo >= 5) {
                            iTipo = 4;
                        }
                        switch (iTipo) {
                            case 1: {  // Inicio Jornada
                                //if (datahora.before(dInimanha)) {
                                //    sRetorno = "Não é possível realizar ponto e atendimentos antes do início do expediente!";
                                //}
                                if ((bExecucao) && datahora.after(dIntervalo) && !(iOperacao.equals(2))) {
                                    sRetorno = "Deve-se neste momento realizar o intervalo para que possa posteriormente retornar as atividades !";
                                }
                                break;
                            }
                            case 2: { // Inicio Intervalo
                                if (bExecucao) {
                                    sRetorno = "Não é possível realizar trabalhos em horário de intervalo !";
                                }
                                break;
                            }
                            case 3: { // Finalizar Intervalo
                                Ponto ponto = pontoDAO.procuraPonto("sdata = '"+sData+"' and tipo = 2 and usuario = '" + aparelho.getUsuario().toString() + "' ");
                                if ((ponto != null) && !(bExecucao)) {
                                    Date dataatual = new Date();
                                    Date dIniintervalo = ponto.getDataponto();
                                    long iIntervalo = ((dataatual.getTime() - dIniintervalo.getTime())/60000);
                                    if (iIntervalo < configuracao.getIntervalo()) {
                                        sRetorno = "Somente deverá finalizar em aproximadamente "+String.valueOf(configuracao.getIntervalo() - iIntervalo)+" minuto(s) !" ;
                                    }
                                }
                                break;
                            }
                            case 4: { // Finalizar Jornada
                                if (bExecucao) {
                                    sRetorno = "O Dia já foi encerrado !";
                                }
                                else {
                                    OsDAO osDAO = new OsDAO();
                                    Os ospesq = osDAO.procuraOs("status_check = 3");
                                    Ponto ponto = pontoDAO.procuraPonto("sdata = '"+sData+"' and tipo = 4 and usuario = '" + aparelho.getUsuario().toString() + "' ");
                                    if (ponto != null) {
                                        sRetorno = "O Dia já foi encerrado !";
                                    }
                                    else {
                                        if (ospesq != null) {
                                            sRetorno = "Não é possivel realizar o encerramento do dia com OS em atendimento !";
                                        }
                                        else if ((datahora.after(dFinal)) && !(bExecucao)) {
                                            sRetorno = "Já foi excedido a hora máxima de fechar o ponto, favor comunicar ao departamento pessoal !";
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }

                }

            }

        }
        return sRetorno;
    }

    public static String GravarPonto (Double latitude, Double longitude, String sLocal, Boolean bOnline) throws ParseException {
        String sRetorno = "OK";
        ConfiguracaoDAO configuracaoDAO = new ConfiguracaoDAO();
        Configuracao configuracao = configuracaoDAO.procuraConfiguracao("1 = 1");
        PontoDAO pontoDAOGrava = new PontoDAO();
        AparelhoDAO aparelhoDAO = new AparelhoDAO();
        Aparelho aparelho = aparelhoDAO.procuraAparelho("id = 1");
        if (configuracao.getPonto().equals("S")) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.procuraUsuario("nome = '" + aparelho.getUsuario().toString() + "'");
            if (usuario.getPonto().equals("S")) {
                sRetorno = ValidarPonto(false, 0);
                if (sRetorno.equals("OK")) {
                    Date datahora = new Date();
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    String sData = formato.format(datahora);
                    List<Ponto> lista = new ArrayList<>();
                    List<Ponto> lista2 = new ArrayList<>();
                    lista = pontoDAOGrava.listarPonto("sdata = '"+sData+"' and usuario = '" + aparelho.getUsuario().toString() + "' ");
                    lista2 = pontoDAOGrava.listarPonto("");
                    Integer iTipo = lista.size() + 1;
                    Integer iId = lista2.size() + 1;

                    Locale locale = null;
                    Calendar c = new GregorianCalendar(locale);
                    TimeZone Fusohorario = c.getTimeZone();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("zzz", Locale.US);
                    simpleDateFormat.setTimeZone(Fusohorario);
                    String sUTC = simpleDateFormat.format(c.getTime());

                    Ponto pontoGrava = new Ponto();
                    pontoGrava.setId(iId);
                    pontoGrava.setData(formato.parse(sData));
                    pontoGrava.setDataponto(datahora);
                    pontoGrava.setTipo(iTipo);
                    pontoGrava.setUsuario(aparelho.getUsuario().toString());
                    pontoGrava.setLatitude(latitude);
                    pontoGrava.setLongitude(longitude);
                    pontoGrava.setSdata(sData);
                    pontoGrava.setLocal(sLocal);
                    pontoGrava.setFusohorario(sUTC);

                    if (bOnline) {
                        PontoWSClient pontoWSClient = new PontoWSClient();
                        sRetorno = pontoWSClient.enviaPonto(pontoGrava);
                        if (sRetorno.equals("OK")) {
                            pontoGrava.setIntegra("S");
                            pontoGrava.setSync("S");
                            pontoDAOGrava.gravaPonto(pontoGrava);
                            aparelho.setLatitude(latitude);
                            aparelho.setLongitude(longitude);
                            aparelho.setData(datahora);
                            aparelhoDAO.gravaAparelho(aparelho);
                            AparelhoWSClient aparelhoWSClient = new AparelhoWSClient();
                            aparelhoWSClient.enviaLocalidade(aparelho);
                        }
                        else {
                            pontoGrava.setIntegra("N");
                            pontoGrava.setSync("N");
                            pontoDAOGrava.gravaPonto(pontoGrava);
                        }
                    }
                    else {
                        pontoGrava.setIntegra("N");
                        pontoGrava.setSync("N");
                        pontoDAOGrava.gravaPonto(pontoGrava);
                    }
                }
            }
            else {
                sRetorno = "Usuário não esta configurado para bater ponto pelo MOBILE";
            }
        }
        return sRetorno;
    }

    public static String StatusPonto () throws ParseException {
        Date datahora = new Date();
        String sRetorno = "";
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String sData = formato.format(datahora);
        List<Ponto> lista = new ArrayList<>();
        AparelhoDAO aparelhoDAO = new AparelhoDAO();
        Aparelho aparelho = aparelhoDAO.procuraAparelho("id = 1");
        PontoDAO pontoDAO = new PontoDAO();
        lista = pontoDAO.listarPonto("sdata = '"+sData+"' and usuario = '" + aparelho.getUsuario().toString() + "' ");
        Integer iTipo = lista.size();
        switch (iTipo) {
            case 0: {  // Inicio Jornada
                sRetorno = "SEM INÍCIO DE TRABALHO";
                break;
            }
            case 1: {  // Inicio Jornada
                sRetorno = "EM TRABALHO (MANHÃ)";
                break;
            }
            case 2: { // Inicio Intervalo
                sRetorno = "EM INTERVALO";
                break;
            }
            case 3: { // Finalizar Intervalo
                sRetorno = "EM TRABALHO (TARDE)";
                break;
            }
            case 4: { // Finalizar Jornada
                sRetorno = "FINALIZADO O TRABALHO";
                break;
            }
        }
        if (iTipo > 0) {
            sRetorno = sRetorno + "\n" + "DATA: "+formato2.format(lista.get(iTipo-1).getDataponto());
        }
        return sRetorno;
    }

}
