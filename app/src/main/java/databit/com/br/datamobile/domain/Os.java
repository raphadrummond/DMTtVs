package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sidney on 07/03/2016.
 */
public class Os implements Serializable {

    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String codigo;
    @DatabaseField
    private Date cadastro;
    @DatabaseField
    private String operador;
    @DatabaseField
    private String contrato;
    @DatabaseField
    private String codcli;
    @DatabaseField
    private String produto;
    @DatabaseField
    private String numserie;
    @DatabaseField
    private Date data;
    @DatabaseField
    private String atendente;
    @DatabaseField
    private String solicitante;
    @DatabaseField
    private String codemp;
    @DatabaseField
    private String situacao;
    @DatabaseField
    private String motivo;
    @DatabaseField
    private String preventiva;
    @DatabaseField
    private String obs;
    @DatabaseField
    private Date fecha;
    @DatabaseField
    private String reincidencia;
    @DatabaseField
    private String numos;
    @DatabaseField
    private String origem;
    @DatabaseField
    private String codtec;
    @DatabaseField
    private String prest;
    @DatabaseField
    private String tipointerv;
    @DatabaseField
    private Integer ordem;
    @DatabaseField
    private Date instalacao;
    @DatabaseField
    private String logradouro;
    @DatabaseField
    private Integer num;
    @DatabaseField
    private String comp;
    @DatabaseField
    private String bairro;
    @DatabaseField
    private String cidade;
    @DatabaseField
    private String estado;
    @DatabaseField
    private String cep;
    @DatabaseField
    private String fone;
    @DatabaseField
    private String foneaux;
    @DatabaseField
    private String fax;
    @DatabaseField
    private String contato;
    @DatabaseField
    private String email;
    @DatabaseField
    private Integer horas;
    @DatabaseField
    private String tipohoras;
    @DatabaseField
    private String codtecequip;
    @DatabaseField
    private String prestequip;
    @DatabaseField
    private String obsequip;
    @DatabaseField
    private String cnpj;
    @DatabaseField
    private String cpf;
    @DatabaseField
    private String inscest;
    @DatabaseField
    private String inscmun;
    @DatabaseField
    private String nomecli;
    @DatabaseField
    private String codauxiliar;
    @DatabaseField
    private String codbarras;
    @DatabaseField
    private String nomeprod;
    @DatabaseField
    private String referencia;
    @DatabaseField
    private String nometec;
    @DatabaseField
    private String nomeprest;
    @DatabaseField
    private String nomeempresa;
    @DatabaseField
    private String tipocontr;
    @DatabaseField
    private Date validade;
    @DatabaseField
    private String tipoantende;
    @DatabaseField
    private Integer dialeitura;
    @DatabaseField
    private Integer copiagarantia;
    @DatabaseField
    private String condicao;
    @DatabaseField
    private String nomecondicao;
    @DatabaseField
    private String cor;
    @DatabaseField
    private String numfecha;
    @DatabaseField
    private Date previsao;
    @DatabaseField
    private Integer restante;
    @DatabaseField
    private String pat;
    @DatabaseField
    private String statusos;
    @DatabaseField
    private String concluir;
    @DatabaseField
    private String pendente;
    @DatabaseField
    private String trocada;
    @DatabaseField
    private String inicial;
    @DatabaseField
    private String sla;
    @DatabaseField
    private String nomestatusos;
    @DatabaseField
    private String tecnico;
    @DatabaseField
    private String tipoatende;
    @DatabaseField
    private String tipointervencao;
    @DatabaseField
    private String nometecnicoprest;
    @DatabaseField
    private String imagem;
    @DatabaseField
    private String outsourcing;
    @DatabaseField
    private Date garantia;
    @DatabaseField
    private String local;
    @DatabaseField
    private Integer status_check;
    @DatabaseField
    private String sync;
    @DatabaseField
    private Date checkin;
    @DatabaseField
    private Date checkout;
    @DatabaseField
    private Double latitude;
    @DatabaseField
    private Double longitude;
    @DatabaseField
    private String banco;
    @DatabaseField
    private String bancoos;
    @DatabaseField
    private String nometecnico;
    @DatabaseField
    private String obscanc;
    @DatabaseField
    private Integer checkinok;
    @DatabaseField
    private Integer checkoutok;
    @DatabaseField
    private Integer fechaok;
    @DatabaseField
    private Date datain;
    @DatabaseField
    private Date dataout;
    @DatabaseField
    private Date datafecha;
    @DatabaseField
    private String endin;
    @DatabaseField
    private Integer numin;
    @DatabaseField
    private String compin;
    @DatabaseField
    private String bairroin;
    @DatabaseField
    private String cidadein;
    @DatabaseField
    private String estadoin;
    @DatabaseField
    private String cepin;
    @DatabaseField
    private Double latitudein;
    @DatabaseField
    private Double longitudein;
    @DatabaseField
    private String endout;
    @DatabaseField
    private Integer numout;
    @DatabaseField
    private String compout;
    @DatabaseField
    private String bairroout;
    @DatabaseField
    private String cidadeout;
    @DatabaseField
    private String estadoout;
    @DatabaseField
    private String cepout;
    @DatabaseField
    private Double latitudeout;
    @DatabaseField
    private Double longitudeout;
    @DatabaseField
    private Integer contador;
    @DatabaseField
    private Integer credcopias;
    @DatabaseField
    private String cilindro;
    @DatabaseField
    private String sitcilindro;
    @DatabaseField
    private Integer contcilindro;
    @DatabaseField
    private String revelador;
    @DatabaseField
    private String condicaofinal;
    @DatabaseField
    private String incompleto;
    @DatabaseField
    private String servincompl;
    @DatabaseField
    private String obsfecha;
    @DatabaseField
    private Integer contadorc;
    @DatabaseField
    private Integer credcopiasc;
    @DatabaseField
    private String fusor;
    @DatabaseField
    private String belt;
    @DatabaseField
    private Integer kminicial;
    @DatabaseField
    private Integer kmfinal;
    @DatabaseField
    private String cilindroc;
    @DatabaseField
    private String reveladorc;
    @DatabaseField
    private String sitcilindroc;
    @DatabaseField
    private Integer contcilindroc;
    @DatabaseField
    private String reservatorio;
    @DatabaseField
    private String placa;
    @DatabaseField
    private String obspendente;
    @DatabaseField
    private String obstrocada;
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private byte[] assinatura;
    @DatabaseField
    private String numlocalin;
    @DatabaseField
    private String numlocalout;
    @DatabaseField
    private String tipocontrato;
    @DatabaseField
    private String obsservicos;
    @DatabaseField
    private Integer contadordg;
    @DatabaseField
    private Integer credcopiasdg;
    @DatabaseField
    private Integer contadorgf;
    @DatabaseField
    private Integer credcopiasgf;
    @DatabaseField
    private Integer contadorgfc;
    @DatabaseField
    private Integer credcopiasgfc;
    @DatabaseField
    private String site;
    @DatabaseField
    private String departamento;
    @DatabaseField
    private Integer contadorostotal;
    @DatabaseField
    private Integer contadorospb;
    @DatabaseField
    private Integer contadoroscolor;
    @DatabaseField
    private String classificacao;
    @DatabaseField
    private String lido;
    @DatabaseField
    private Integer idpendente;
    @DatabaseField
    private Integer idtrocada;
    @DatabaseField
    private Integer idservico;
    @DatabaseField
    private String requisicao;
    @DatabaseField
    private Integer contreq;
    @DatabaseField
    private Boolean online;
    @DatabaseField
    private Integer ordemtec;
    @DatabaseField
    private String usuariodataservice;
    @DatabaseField
    private Integer operacaomobile;
    @DatabaseField
    private String sigla;
    @DatabaseField
    private String obsadd;
    @DatabaseField
    private String defeito;
    @DatabaseField
    private String laudo;
    @DatabaseField
    private String modulo;
    @DatabaseField
    private String participantes;
    @DatabaseField
    private String recebido;
    @DatabaseField
    private String respondido;
    @DatabaseField
    private Date previsaoretorno;
    @DatabaseField
    private String verlaboratorio;
    @DatabaseField
    private String verremoto;
    @DatabaseField
    private String verpresencial;
    @DatabaseField
    private String verprojeto;
    @DatabaseField
    private String versistema;
    @DatabaseField
    private String verimplantacao;
    @DatabaseField
    private Integer qtdeequip;
    @DatabaseField
    private Float vlproposta;
    @DatabaseField
    private String classificacaoatende;
    @DatabaseField
    private String conferido;
    @DatabaseField
    private String codrecolha;
    @DatabaseField
    private String obsrecolha;
    @DatabaseField
    private Integer idarquivo;
    @DatabaseField
    private Integer idrecolha;
    @DatabaseField
    private String ntfisc;
    @DatabaseField
    private Integer identrega;
    @DatabaseField
    private String obsrecolha2;
    @DatabaseField
    private String nomeajudante;
    @DatabaseField
    private String nomeoperacaomobile;

    public Os() {
    }

    public Os(String id, String codigo, Date cadastro, String operador, String contrato, String codcli, String produto, String numserie, Date data, String atendente, String solicitante, String codemp, String situacao, String motivo, String preventiva, String obs, Date fecha, String reincidencia, String numos, String origem, String codtec, String prest, String tipointerv, Integer ordem, Date instalacao, String logradouro, Integer num, String comp, String bairro, String cidade, String estado, String cep, String fone, String foneaux, String fax, String contato, String email, Integer horas, String tipohoras, String codtecequip, String prestequip, String obsequip, String cnpj, String cpf, String inscest, String inscmun, String nomecli, String codauxiliar, String codbarras, String nomeprod, String referencia, String nometec, String nomeprest, String nomeempresa, String tipocontr, Date validade, String tipoantende, Integer dialeitura, Integer copiagarantia, String condicao, String nomecondicao, String cor, String numfecha, Date previsao, Integer restante, String pat, String statusos, String concluir, String pendente, String trocada, String inicial, String sla, String nomestatusos, String tecnico, String tipoatende, String tipointervencao, String nometecnicoprest, String imagem, String outsourcing, Date garantia, String local, Integer status_check, String sync, Date checkin, Date checkout, Double latitude, Double longitude, String banco, String bancoos, String nometecnico, String obscanc, Integer checkinok, Integer checkoutok, Integer fechaok, Date datain, Date dataout, Date datafecha, String endin, Integer numin, String compin, String bairroin, String cidadein, String estadoin, String cepin, Double latitudein, Double longitudein, String endout, Integer numout, String compout, String bairroout, String cidadeout, String estadoout, String cepout, Double latitudeout, Double longitudeout, Integer contador, Integer credcopias, String cilindro, String sitcilindro, Integer contcilindro, String revelador, String condicaofinal, String incompleto, String servincompl, String obsfecha, Integer contadorc, Integer credcopiasc, String fusor, String belt, Integer kminicial, Integer kmfinal, String cilindroc, String reveladorc, String sitcilindroc, Integer contcilindroc, String reservatorio, String placa, String obspendente, String obstrocada, byte[] assinatura, String numlocalin, String numlocalout, String tipocontrato, String obsservicos, Integer contadordg, Integer credcopiasdg, Integer contadorgf, Integer credcopiasgf, Integer contadorgfc, Integer credcopiasgfc, String site, String departamento, Integer contadorostotal, Integer contadorospb, Integer contadoroscolor, String classificacao, String lido, Integer idpendente, Integer idtrocada, Integer idservico, String requisicao, Integer contreq, Boolean online, Integer ordemtec, String usuariodataservice, Integer operacaomobile, String sigla, String obsadd, String defeito, String laudo, String modulo, String participantes, String recebido, String respondido, Date previsaoretorno, String verlaboratorio, String verremoto, String verpresencial, String verprojeto, String versistema, String verimplantacao, Integer qtdeequip, Float vlproposta, String classificacaoatende, String conferido, String codrecolha, String obsrecolha, Integer idarquivo, Integer idrecolha, String ntfisc, Integer identrega, String obsrecolha2, String nomeajudante, String nomeoperacaomobile) {
        this.id = id;
        this.codigo = codigo;
        this.cadastro = cadastro;
        this.operador = operador;
        this.contrato = contrato;
        this.codcli = codcli;
        this.produto = produto;
        this.numserie = numserie;
        this.data = data;
        this.atendente = atendente;
        this.solicitante = solicitante;
        this.codemp = codemp;
        this.situacao = situacao;
        this.motivo = motivo;
        this.preventiva = preventiva;
        this.obs = obs;
        this.fecha = fecha;
        this.reincidencia = reincidencia;
        this.numos = numos;
        this.origem = origem;
        this.codtec = codtec;
        this.prest = prest;
        this.tipointerv = tipointerv;
        this.ordem = ordem;
        this.instalacao = instalacao;
        this.logradouro = logradouro;
        this.num = num;
        this.comp = comp;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.fone = fone;
        this.foneaux = foneaux;
        this.fax = fax;
        this.contato = contato;
        this.email = email;
        this.horas = horas;
        this.tipohoras = tipohoras;
        this.codtecequip = codtecequip;
        this.prestequip = prestequip;
        this.obsequip = obsequip;
        this.cnpj = cnpj;
        this.cpf = cpf;
        this.inscest = inscest;
        this.inscmun = inscmun;
        this.nomecli = nomecli;
        this.codauxiliar = codauxiliar;
        this.codbarras = codbarras;
        this.nomeprod = nomeprod;
        this.referencia = referencia;
        this.nometec = nometec;
        this.nomeprest = nomeprest;
        this.nomeempresa = nomeempresa;
        this.tipocontr = tipocontr;
        this.validade = validade;
        this.tipoantende = tipoantende;
        this.dialeitura = dialeitura;
        this.copiagarantia = copiagarantia;
        this.condicao = condicao;
        this.nomecondicao = nomecondicao;
        this.cor = cor;
        this.numfecha = numfecha;
        this.previsao = previsao;
        this.restante = restante;
        this.pat = pat;
        this.statusos = statusos;
        this.concluir = concluir;
        this.pendente = pendente;
        this.trocada = trocada;
        this.inicial = inicial;
        this.sla = sla;
        this.nomestatusos = nomestatusos;
        this.tecnico = tecnico;
        this.tipoatende = tipoatende;
        this.tipointervencao = tipointervencao;
        this.nometecnicoprest = nometecnicoprest;
        this.imagem = imagem;
        this.outsourcing = outsourcing;
        this.garantia = garantia;
        this.local = local;
        this.status_check = status_check;
        this.sync = sync;
        this.checkin = checkin;
        this.checkout = checkout;
        this.latitude = latitude;
        this.longitude = longitude;
        this.banco = banco;
        this.bancoos = bancoos;
        this.nometecnico = nometecnico;
        this.obscanc = obscanc;
        this.checkinok = checkinok;
        this.checkoutok = checkoutok;
        this.fechaok = fechaok;
        this.datain = datain;
        this.dataout = dataout;
        this.datafecha = datafecha;
        this.endin = endin;
        this.numin = numin;
        this.compin = compin;
        this.bairroin = bairroin;
        this.cidadein = cidadein;
        this.estadoin = estadoin;
        this.cepin = cepin;
        this.latitudein = latitudein;
        this.longitudein = longitudein;
        this.endout = endout;
        this.numout = numout;
        this.compout = compout;
        this.bairroout = bairroout;
        this.cidadeout = cidadeout;
        this.estadoout = estadoout;
        this.cepout = cepout;
        this.latitudeout = latitudeout;
        this.longitudeout = longitudeout;
        this.contador = contador;
        this.credcopias = credcopias;
        this.cilindro = cilindro;
        this.sitcilindro = sitcilindro;
        this.contcilindro = contcilindro;
        this.revelador = revelador;
        this.condicaofinal = condicaofinal;
        this.incompleto = incompleto;
        this.servincompl = servincompl;
        this.obsfecha = obsfecha;
        this.contadorc = contadorc;
        this.credcopiasc = credcopiasc;
        this.fusor = fusor;
        this.belt = belt;
        this.kminicial = kminicial;
        this.kmfinal = kmfinal;
        this.cilindroc = cilindroc;
        this.reveladorc = reveladorc;
        this.sitcilindroc = sitcilindroc;
        this.contcilindroc = contcilindroc;
        this.reservatorio = reservatorio;
        this.placa = placa;
        this.obspendente = obspendente;
        this.obstrocada = obstrocada;
        this.assinatura = assinatura;
        this.numlocalin = numlocalin;
        this.numlocalout = numlocalout;
        this.tipocontrato = tipocontrato;
        this.obsservicos = obsservicos;
        this.contadordg = contadordg;
        this.credcopiasdg = credcopiasdg;
        this.contadorgf = contadorgf;
        this.credcopiasgf = credcopiasgf;
        this.contadorgfc = contadorgfc;
        this.credcopiasgfc = credcopiasgfc;
        this.site = site;
        this.departamento = departamento;
        this.contadorostotal = contadorostotal;
        this.contadorospb = contadorospb;
        this.contadoroscolor = contadoroscolor;
        this.classificacao = classificacao;
        this.lido = lido;
        this.idpendente = idpendente;
        this.idtrocada = idtrocada;
        this.idservico = idservico;
        this.requisicao = requisicao;
        this.contreq = contreq;
        this.online = online;
        this.ordemtec = ordemtec;
        this.usuariodataservice = usuariodataservice;
        this.operacaomobile = operacaomobile;
        this.sigla = sigla;
        this.obsadd = obsadd;
        this.defeito = defeito;
        this.laudo = laudo;
        this.modulo = modulo;
        this.participantes = participantes;
        this.recebido = recebido;
        this.respondido = respondido;
        this.previsaoretorno = previsaoretorno;
        this.verlaboratorio = verlaboratorio;
        this.verremoto = verremoto;
        this.verpresencial = verpresencial;
        this.verprojeto = verprojeto;
        this.versistema = versistema;
        this.verimplantacao = verimplantacao;
        this.qtdeequip = qtdeequip;
        this.vlproposta = vlproposta;
        this.classificacaoatende = classificacaoatende;
        this.conferido = conferido;
        this.codrecolha = codrecolha;
        this.obsrecolha = obsrecolha;
        this.idarquivo = idarquivo;
        this.idrecolha = idrecolha;
        this.ntfisc = ntfisc;
        this.identrega = identrega;
        this.obsrecolha2 = obsrecolha2;
        this.nomeajudante = nomeajudante;
        this.nomeoperacaomobile = nomeoperacaomobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getCadastro() {
        return cadastro;
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getCodcli() {
        return codcli;
    }

    public void setCodcli(String codcli) {
        this.codcli = codcli;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getNumserie() {
        return numserie;
    }

    public void setNumserie(String numserie) {
        this.numserie = numserie;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getAtendente() {
        return atendente;
    }

    public void setAtendente(String atendente) {
        this.atendente = atendente;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getCodemp() {
        return codemp;
    }

    public void setCodemp(String codemp) {
        this.codemp = codemp;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getPreventiva() {
        return preventiva;
    }

    public void setPreventiva(String preventiva) {
        this.preventiva = preventiva;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getReincidencia() {
        return reincidencia;
    }

    public void setReincidencia(String reincidencia) {
        this.reincidencia = reincidencia;
    }

    public String getNumos() {
        return numos;
    }

    public void setNumos(String numos) {
        this.numos = numos;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getCodtec() {
        return codtec;
    }

    public void setCodtec(String codtec) {
        this.codtec = codtec;
    }

    public String getPrest() {
        return prest;
    }

    public void setPrest(String prest) {
        this.prest = prest;
    }

    public String getTipointerv() {
        return tipointerv;
    }

    public void setTipointerv(String tipointerv) {
        this.tipointerv = tipointerv;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public Date getInstalacao() {
        return instalacao;
    }

    public void setInstalacao(Date instalacao) {
        this.instalacao = instalacao;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getFoneaux() {
        return foneaux;
    }

    public void setFoneaux(String foneaux) {
        this.foneaux = foneaux;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public String getTipohoras() {
        return tipohoras;
    }

    public void setTipohoras(String tipohoras) {
        this.tipohoras = tipohoras;
    }

    public String getCodtecequip() {
        return codtecequip;
    }

    public void setCodtecequip(String codtecequip) {
        this.codtecequip = codtecequip;
    }

    public String getPrestequip() {
        return prestequip;
    }

    public void setPrestequip(String prestequip) {
        this.prestequip = prestequip;
    }

    public String getObsequip() {
        return obsequip;
    }

    public void setObsequip(String obsequip) {
        this.obsequip = obsequip;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getInscest() {
        return inscest;
    }

    public void setInscest(String inscest) {
        this.inscest = inscest;
    }

    public String getInscmun() {
        return inscmun;
    }

    public void setInscmun(String inscmun) {
        this.inscmun = inscmun;
    }

    public String getNomecli() {
        return nomecli;
    }

    public void setNomecli(String nomecli) {
        this.nomecli = nomecli;
    }

    public String getCodauxiliar() {
        return codauxiliar;
    }

    public void setCodauxiliar(String codauxiliar) {
        this.codauxiliar = codauxiliar;
    }

    public String getCodbarras() {
        return codbarras;
    }

    public void setCodbarras(String codbarras) {
        this.codbarras = codbarras;
    }

    public String getNomeprod() {
        return nomeprod;
    }

    public void setNomeprod(String nomeprod) {
        this.nomeprod = nomeprod;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNometec() {
        return nometec;
    }

    public void setNometec(String nometec) {
        this.nometec = nometec;
    }

    public String getNomeprest() {
        return nomeprest;
    }

    public void setNomeprest(String nomeprest) {
        this.nomeprest = nomeprest;
    }

    public String getNomeempresa() {
        return nomeempresa;
    }

    public void setNomeempresa(String nomeempresa) {
        this.nomeempresa = nomeempresa;
    }

    public String getTipocontr() {
        return tipocontr;
    }

    public void setTipocontr(String tipocontr) {
        this.tipocontr = tipocontr;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public String getTipoantende() {
        return tipoantende;
    }

    public void setTipoantende(String tipoantende) {
        this.tipoantende = tipoantende;
    }

    public Integer getDialeitura() {
        return dialeitura;
    }

    public void setDialeitura(Integer dialeitura) {
        this.dialeitura = dialeitura;
    }

    public Integer getCopiagarantia() {
        return copiagarantia;
    }

    public void setCopiagarantia(Integer copiagarantia) {
        this.copiagarantia = copiagarantia;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public String getNomecondicao() {
        return nomecondicao;
    }

    public void setNomecondicao(String nomecondicao) {
        this.nomecondicao = nomecondicao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getNumfecha() {
        return numfecha;
    }

    public void setNumfecha(String numfecha) {
        this.numfecha = numfecha;
    }

    public Date getPrevisao() {
        return previsao;
    }

    public void setPrevisao(Date previsao) {
        this.previsao = previsao;
    }

    public Integer getRestante() {
        return restante;
    }

    public void setRestante(Integer restante) {
        this.restante = restante;
    }

    public String getPat() {
        return pat;
    }

    public void setPat(String pat) {
        this.pat = pat;
    }

    public String getStatusos() {
        return statusos;
    }

    public void setStatusos(String statusos) {
        this.statusos = statusos;
    }

    public String getConcluir() {
        return concluir;
    }

    public void setConcluir(String concluir) {
        this.concluir = concluir;
    }

    public String getPendente() {
        return pendente;
    }

    public void setPendente(String pendente) {
        this.pendente = pendente;
    }

    public String getTrocada() {
        return trocada;
    }

    public void setTrocada(String trocada) {
        this.trocada = trocada;
    }

    public String getInicial() {
        return inicial;
    }

    public void setInicial(String inicial) {
        this.inicial = inicial;
    }

    public String getSla() {
        return sla;
    }

    public void setSla(String sla) {
        this.sla = sla;
    }

    public String getNomestatusos() {
        return nomestatusos;
    }

    public void setNomestatusos(String nomestatusos) {
        this.nomestatusos = nomestatusos;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getTipoatende() {
        return tipoatende;
    }

    public void setTipoatende(String tipoatende) {
        this.tipoatende = tipoatende;
    }

    public String getTipointervencao() {
        return tipointervencao;
    }

    public void setTipointervencao(String tipointervencao) {
        this.tipointervencao = tipointervencao;
    }

    public String getNometecnicoprest() {
        return nometecnicoprest;
    }

    public void setNometecnicoprest(String nometecnicoprest) {
        this.nometecnicoprest = nometecnicoprest;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getOutsourcing() {
        return outsourcing;
    }

    public void setOutsourcing(String outsourcing) {
        this.outsourcing = outsourcing;
    }

    public Date getGarantia() {
        return garantia;
    }

    public void setGarantia(Date garantia) {
        this.garantia = garantia;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Integer getStatus_check() {
        return status_check;
    }

    public void setStatus_check(Integer status_check) {
        this.status_check = status_check;
    }

    public String getSync() {
        return sync;
    }

    public void setSync(String sync) {
        this.sync = sync;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getBancoos() {
        return bancoos;
    }

    public void setBancoos(String bancoos) {
        this.bancoos = bancoos;
    }

    public String getNometecnico() {
        return nometecnico;
    }

    public void setNometecnico(String nometecnico) {
        this.nometecnico = nometecnico;
    }

    public String getObscanc() {
        return obscanc;
    }

    public void setObscanc(String obscanc) {
        this.obscanc = obscanc;
    }

    public Integer getCheckinok() {
        return checkinok;
    }

    public void setCheckinok(Integer checkinok) {
        this.checkinok = checkinok;
    }

    public Integer getCheckoutok() {
        return checkoutok;
    }

    public void setCheckoutok(Integer checkoutok) {
        this.checkoutok = checkoutok;
    }

    public Integer getFechaok() {
        return fechaok;
    }

    public void setFechaok(Integer fechaok) {
        this.fechaok = fechaok;
    }

    public Date getDatain() {
        return datain;
    }

    public void setDatain(Date datain) {
        this.datain = datain;
    }

    public Date getDataout() {
        return dataout;
    }

    public void setDataout(Date dataout) {
        this.dataout = dataout;
    }

    public Date getDatafecha() {
        return datafecha;
    }

    public void setDatafecha(Date datafecha) {
        this.datafecha = datafecha;
    }

    public String getEndin() {
        return endin;
    }

    public void setEndin(String endin) {
        this.endin = endin;
    }

    public Integer getNumin() {
        return numin;
    }

    public void setNumin(Integer numin) {
        this.numin = numin;
    }

    public String getCompin() {
        return compin;
    }

    public void setCompin(String compin) {
        this.compin = compin;
    }

    public String getBairroin() {
        return bairroin;
    }

    public void setBairroin(String bairroin) {
        this.bairroin = bairroin;
    }

    public String getCidadein() {
        return cidadein;
    }

    public void setCidadein(String cidadein) {
        this.cidadein = cidadein;
    }

    public String getEstadoin() {
        return estadoin;
    }

    public void setEstadoin(String estadoin) {
        this.estadoin = estadoin;
    }

    public String getCepin() {
        return cepin;
    }

    public void setCepin(String cepin) {
        this.cepin = cepin;
    }

    public Double getLatitudein() {
        return latitudein;
    }

    public void setLatitudein(Double latitudein) {
        this.latitudein = latitudein;
    }

    public Double getLongitudein() {
        return longitudein;
    }

    public void setLongitudein(Double longitudein) {
        this.longitudein = longitudein;
    }

    public String getEndout() {
        return endout;
    }

    public void setEndout(String endout) {
        this.endout = endout;
    }

    public Integer getNumout() {
        return numout;
    }

    public void setNumout(Integer numout) {
        this.numout = numout;
    }

    public String getCompout() {
        return compout;
    }

    public void setCompout(String compout) {
        this.compout = compout;
    }

    public String getBairroout() {
        return bairroout;
    }

    public void setBairroout(String bairroout) {
        this.bairroout = bairroout;
    }

    public String getCidadeout() {
        return cidadeout;
    }

    public void setCidadeout(String cidadeout) {
        this.cidadeout = cidadeout;
    }

    public String getEstadoout() {
        return estadoout;
    }

    public void setEstadoout(String estadoout) {
        this.estadoout = estadoout;
    }

    public String getCepout() {
        return cepout;
    }

    public void setCepout(String cepout) {
        this.cepout = cepout;
    }

    public Double getLatitudeout() {
        return latitudeout;
    }

    public void setLatitudeout(Double latitudeout) {
        this.latitudeout = latitudeout;
    }

    public Double getLongitudeout() {
        return longitudeout;
    }

    public void setLongitudeout(Double longitudeout) {
        this.longitudeout = longitudeout;
    }

    public Integer getContador() {
        return contador;
    }

    public void setContador(Integer contador) {
        this.contador = contador;
    }

    public Integer getCredcopias() {
        return credcopias;
    }

    public void setCredcopias(Integer credcopias) {
        this.credcopias = credcopias;
    }

    public String getCilindro() {
        return cilindro;
    }

    public void setCilindro(String cilindro) {
        this.cilindro = cilindro;
    }

    public String getSitcilindro() {
        return sitcilindro;
    }

    public void setSitcilindro(String sitcilindro) {
        this.sitcilindro = sitcilindro;
    }

    public Integer getContcilindro() {
        return contcilindro;
    }

    public void setContcilindro(Integer contcilindro) {
        this.contcilindro = contcilindro;
    }

    public String getRevelador() {
        return revelador;
    }

    public void setRevelador(String revelador) {
        this.revelador = revelador;
    }

    public String getCondicaofinal() {
        return condicaofinal;
    }

    public void setCondicaofinal(String condicaofinal) {
        this.condicaofinal = condicaofinal;
    }

    public String getIncompleto() {
        return incompleto;
    }

    public void setIncompleto(String incompleto) {
        this.incompleto = incompleto;
    }

    public String getServincompl() {
        return servincompl;
    }

    public void setServincompl(String servincompl) {
        this.servincompl = servincompl;
    }

    public String getObsfecha() {
        return obsfecha;
    }

    public void setObsfecha(String obsfecha) {
        this.obsfecha = obsfecha;
    }

    public Integer getContadorc() {
        return contadorc;
    }

    public void setContadorc(Integer contadorc) {
        this.contadorc = contadorc;
    }

    public Integer getCredcopiasc() {
        return credcopiasc;
    }

    public void setCredcopiasc(Integer credcopiasc) {
        this.credcopiasc = credcopiasc;
    }

    public String getFusor() {
        return fusor;
    }

    public void setFusor(String fusor) {
        this.fusor = fusor;
    }

    public String getBelt() {
        return belt;
    }

    public void setBelt(String belt) {
        this.belt = belt;
    }

    public Integer getKminicial() {
        return kminicial;
    }

    public void setKminicial(Integer kminicial) {
        this.kminicial = kminicial;
    }

    public Integer getKmfinal() {
        return kmfinal;
    }

    public void setKmfinal(Integer kmfinal) {
        this.kmfinal = kmfinal;
    }

    public String getCilindroc() {
        return cilindroc;
    }

    public void setCilindroc(String cilindroc) {
        this.cilindroc = cilindroc;
    }

    public String getReveladorc() {
        return reveladorc;
    }

    public void setReveladorc(String reveladorc) {
        this.reveladorc = reveladorc;
    }

    public String getSitcilindroc() {
        return sitcilindroc;
    }

    public void setSitcilindroc(String sitcilindroc) {
        this.sitcilindroc = sitcilindroc;
    }

    public Integer getContcilindroc() {
        return contcilindroc;
    }

    public void setContcilindroc(Integer contcilindroc) {
        this.contcilindroc = contcilindroc;
    }

    public String getReservatorio() {
        return reservatorio;
    }

    public void setReservatorio(String reservatorio) {
        this.reservatorio = reservatorio;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getObspendente() {
        return obspendente;
    }

    public void setObspendente(String obspendente) {
        this.obspendente = obspendente;
    }

    public String getObstrocada() {
        return obstrocada;
    }

    public void setObstrocada(String obstrocada) {
        this.obstrocada = obstrocada;
    }

    public byte[] getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(byte[] assinatura) {
        this.assinatura = assinatura;
    }

    public String getNumlocalin() {
        return numlocalin;
    }

    public void setNumlocalin(String numlocalin) {
        this.numlocalin = numlocalin;
    }

    public String getNumlocalout() {
        return numlocalout;
    }

    public void setNumlocalout(String numlocalout) {
        this.numlocalout = numlocalout;
    }

    public String getTipocontrato() {
        return tipocontrato;
    }

    public void setTipocontrato(String tipocontrato) {
        this.tipocontrato = tipocontrato;
    }

    public String getObsservicos() {
        return obsservicos;
    }

    public void setObsservicos(String obsservicos) {
        this.obsservicos = obsservicos;
    }

    public Integer getContadordg() {
        return contadordg;
    }

    public void setContadordg(Integer contadordg) {
        this.contadordg = contadordg;
    }

    public Integer getCredcopiasdg() {
        return credcopiasdg;
    }

    public void setCredcopiasdg(Integer credcopiasdg) {
        this.credcopiasdg = credcopiasdg;
    }

    public Integer getContadorgf() {
        return contadorgf;
    }

    public void setContadorgf(Integer contadorgf) {
        this.contadorgf = contadorgf;
    }

    public Integer getCredcopiasgf() {
        return credcopiasgf;
    }

    public void setCredcopiasgf(Integer credcopiasgf) {
        this.credcopiasgf = credcopiasgf;
    }

    public Integer getContadorgfc() {
        return contadorgfc;
    }

    public void setContadorgfc(Integer contadorgfc) {
        this.contadorgfc = contadorgfc;
    }

    public Integer getCredcopiasgfc() {
        return credcopiasgfc;
    }

    public void setCredcopiasgfc(Integer credcopiasgfc) {
        this.credcopiasgfc = credcopiasgfc;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Integer getContadorostotal() {
        return contadorostotal;
    }

    public void setContadorostotal(Integer contadorostotal) {
        this.contadorostotal = contadorostotal;
    }

    public Integer getContadorospb() {
        return contadorospb;
    }

    public void setContadorospb(Integer contadorospb) {
        this.contadorospb = contadorospb;
    }

    public Integer getContadoroscolor() {
        return contadoroscolor;
    }

    public void setContadoroscolor(Integer contadoroscolor) {
        this.contadoroscolor = contadoroscolor;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getLido() {
        return lido;
    }

    public void setLido(String lido) {
        this.lido = lido;
    }

    public Integer getIdpendente() {
        return idpendente;
    }

    public void setIdpendente(Integer idpendente) {
        this.idpendente = idpendente;
    }

    public Integer getIdtrocada() {
        return idtrocada;
    }

    public void setIdtrocada(Integer idtrocada) {
        this.idtrocada = idtrocada;
    }

    public Integer getIdservico() {
        return idservico;
    }

    public void setIdservico(Integer idservico) {
        this.idservico = idservico;
    }

    public String getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(String requisicao) {
        this.requisicao = requisicao;
    }

    public Integer getContreq() {
        return contreq;
    }

    public void setContreq(Integer contreq) {
        this.contreq = contreq;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Integer getOrdemtec() {
        return ordemtec;
    }

    public void setOrdemtec(Integer ordemtec) {
        this.ordemtec = ordemtec;
    }

    public String getUsuariodataservice() {
        return usuariodataservice;
    }

    public void setUsuariodataservice(String usuariodataservice) {
        this.usuariodataservice = usuariodataservice;
    }

    public Integer getOperacaomobile() {
        return operacaomobile;
    }

    public void setOperacaomobile(Integer operacaomobile) {
        this.operacaomobile = operacaomobile;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getObsadd() {
        return obsadd;
    }

    public void setObsadd(String obsadd) {
        this.obsadd = obsadd;
    }

    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public String getLaudo() {
        return laudo;
    }

    public void setLaudo(String laudo) {
        this.laudo = laudo;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getParticipantes() {
        return participantes;
    }

    public void setParticipantes(String participantes) {
        this.participantes = participantes;
    }

    public String getRecebido() {
        return recebido;
    }

    public void setRecebido(String recebido) {
        this.recebido = recebido;
    }

    public String getRespondido() {
        return respondido;
    }

    public void setRespondido(String respondido) {
        this.respondido = respondido;
    }

    public Date getPrevisaoretorno() {
        return previsaoretorno;
    }

    public void setPrevisaoretorno(Date previsaoretorno) {
        this.previsaoretorno = previsaoretorno;
    }

    public String getVerlaboratorio() {
        return verlaboratorio;
    }

    public void setVerlaboratorio(String verlaboratorio) {
        this.verlaboratorio = verlaboratorio;
    }

    public String getVerremoto() {
        return verremoto;
    }

    public void setVerremoto(String verremoto) {
        this.verremoto = verremoto;
    }

    public String getVerpresencial() {
        return verpresencial;
    }

    public void setVerpresencial(String verpresencial) {
        this.verpresencial = verpresencial;
    }

    public String getVerprojeto() {
        return verprojeto;
    }

    public void setVerprojeto(String verprojeto) {
        this.verprojeto = verprojeto;
    }

    public String getVersistema() {
        return versistema;
    }

    public void setVersistema(String versistema) {
        this.versistema = versistema;
    }

    public String getVerimplantacao() {
        return verimplantacao;
    }

    public void setVerimplantacao(String verimplantacao) {
        this.verimplantacao = verimplantacao;
    }

    public Integer getQtdeequip() {
        return qtdeequip;
    }

    public void setQtdeequip(Integer qtdeequip) {
        this.qtdeequip = qtdeequip;
    }

    public Float getVlproposta() {
        return vlproposta;
    }

    public void setVlproposta(Float vlproposta) {
        this.vlproposta = vlproposta;
    }

    public String getClassificacaoatende() {
        return classificacaoatende;
    }

    public void setClassificacaoatende(String classificacaoatende) {
        this.classificacaoatende = classificacaoatende;
    }

    public String getConferido() {
        return conferido;
    }

    public void setConferido(String conferido) {
        this.conferido = conferido;
    }

    public String getCodrecolha() {
        return codrecolha;
    }

    public void setCodrecolha(String codrecolha) {
        this.codrecolha = codrecolha;
    }

    public String getObsrecolha() {
        return obsrecolha;
    }

    public void setObsrecolha(String obsrecolha) {
        this.obsrecolha = obsrecolha;
    }

    public Integer getIdarquivo() {
        return idarquivo;
    }

    public void setIdarquivo(Integer idarquivo) {
        this.idarquivo = idarquivo;
    }

    public Integer getIdrecolha() {
        return idrecolha;
    }

    public void setIdrecolha(Integer idrecolha) {
        this.idrecolha = idrecolha;
    }

    public String getNtfisc() {
        return ntfisc;
    }

    public void setNtfisc(String ntfisc) {
        this.ntfisc = ntfisc;
    }

    public Integer getIdentrega() {
        return identrega;
    }

    public void setIdentrega(Integer identrega) {
        this.identrega = identrega;
    }

    public String getObsrecolha2() {
        return obsrecolha2;
    }

    public void setObsrecolha2(String obsrecolha2) {
        this.obsrecolha2 = obsrecolha2;
    }

    public String getNomeajudante() {
        return nomeajudante;
    }

    public void setNomeajudante(String nomeajudante) {
        this.nomeajudante = nomeajudante;
    }

    public String getNomeoperacaomobile() {
        return nomeoperacaomobile;
    }

    public void setNomeoperacaomobile(String nomeoperacaomobile) {
        this.nomeoperacaomobile = nomeoperacaomobile;
    }
}
