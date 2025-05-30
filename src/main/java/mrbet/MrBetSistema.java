package mrbet;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

/**
 * Sistema MrBet para gerenciamento de times, campeonatos e apostas.
 * author: Adson
 */
public class MrBetSistema {
	private HashMap<String, Time> times;
	private HashMap<String, Campeonato> campeonatos;
	private ArrayList<Aposta> apostas;

	public MrBetSistema() {
		this.times = new HashMap<>();
		this.campeonatos = new HashMap<>();
		this.apostas = new ArrayList<>();
	}

	/**
	* Cadastra um novo time no sistema.
	*
	* @param id     O código único do time.
	* @param nome   O nome do time.
	* @param mascote O mascote do time.
	* @throws NullPointerException Se algum dos parâmetros for nulo.
	* @return Uma String "TIME JÁ EXISTE!" ou "INCLUSÃO REALIZADA!"
	*/
	public String cadastraTime(String id, String nome, String mascote) {
		verificaSeVazio(id, "CÓDIGO DO TIME");
		verificaSeVazio(nome, "NOME DO TIME");
		verificaSeVazio(mascote, "MASCOTE DO TIME");

		if(times.containsKey(id)) {
			return "TIME JÁ EXISTE!";
		}

		times.put(id, new Time(id, nome, mascote));
		return "INCLUSÃO REALIZADA!";
	}

	/**
	* Cadastra um novo campeonato no sistema.
	*
	* @param nome             O nome do campeonato.
	* @param qntdParticipantes A quantidade máxima de participantes no campeonato.
	* @throws NullPointerException Se algum dos parâmetros for nulo.
	* @return Uma String com "CAMPEONATO JÁ EXISTE!" ou "CAMPEONATO JÁ EXISTE!"
	*/
	public String cadastraCampeonato(String nome, Integer qntdParticipantes) {
		verificaSeVazio(nome, "NOME DO CAMPEONATO");
		verificaSeVazio(qntdParticipantes, "QUANTIDADE DE PARTICIPANTES");

		String nomeLower = nome.toLowerCase();
		if (campeonatos.containsKey(nomeLower)) {
			return "CAMPEONATO JÁ EXISTE!";
		}

		campeonatos.put(nomeLower, new Campeonato(nome, qntdParticipantes));
		return "CAMPEONATO ADICIONADO!";
	}

	/**
	* Adiciona um time a um campeonato.
	*
	* @param idTime         O código do time a ser adicionado.
	* @param nomeCampeonato O nome do campeonato.
	* @throws IllegalArgumentException Se o time ou campeonato não existirem.
	* @throws NullPointerException Se algum dos parâmetros for nulo.
	* @return Uma String com "TIME INCLUÍDO NO CAMPEONATO!" ou uma mensagem de erro
	*/
	public String adicionaTimeNoCampeonato(String idTime, String nomeCampeonato) {
		verificaSeVazio(idTime, "CÓDIGO DO TIME");
		verificaSeVazio(nomeCampeonato, "NOME DO CAMPEONATO");
		validaTime(idTime);
		validaCampeonato(nomeCampeonato);

		String nomeLower = nomeCampeonato.toLowerCase();
		Campeonato campeonato = campeonatos.get(nomeLower);
		
		try {
			campeonato.adicionaTime(idTime);
			times.get(idTime).adicionaCampeonato(nomeCampeonato);
			return "TIME INCLUÍDO NO CAMPEONATO!";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	* Verifica se um time está participando de um campeonato.
	*
	* @param idTime         O código do time.
	* @param nomeCampeonato O nome do campeonato.
	* @return Uma String com o status da execução
	* @throws IllegalArgumentException Se o time ou campeonato não existirem.
	* @throws NullPointerException Se algum dos parâmetros for nulo.
	*/
	public String verificaTimeEmCampeonato(String idTime, String nomeCampeonato) {
		verificaSeVazio(idTime, "CÓDIGO DO TIME");
		verificaSeVazio(nomeCampeonato, "NOME DO CAMPEONATO");
		validaTime(idTime);
		validaCampeonato(nomeCampeonato);

		if(campeonatos.get(nomeCampeonato.toLowerCase()).verificaTime(idTime)){
			return "O TIME ESTÁ NO CAMPEONATO!";
		}else{
			return "O TIME NÃO ESTÁ NO CAMPEONATO!";
		}
	}

	/**
	* Retorna o histórico de participações e apostas no sistema.
	*
	* @return Uma string formatada com o histórico de participações e apostas.
	*/
	public String getHistorico(){
		StringBuilder historico = new StringBuilder();

		historico.append("Participação mais frequente em campeonatos\n");
		historico.append(getTimesMaisParticipantes()).append("\n");

		historico.append("Ainda não participou de campeonato\n");
		historico.append(getTimesSemParticipacao()).append("\n");

		historico.append("Popularidade em apostas\n");
		historico.append(getTimesMaisApostadosPrimeiro());

		return historico.toString();
	}


	/**
	* Retorna uma representação em string de um time.
	*
	* @param codigo O código do time.
	* @return Uma string formatada com as informações do time.
	* @throws IllegalArgumentException Se o time não existir.
	* @throws NullPointerException Se o código for nulo.
	*/
	public String getTime(String codigo){
		verificaSeVazio(codigo, "CÓDIGO DO TIME");
		validaTime(codigo);

		return times.get(codigo).toString();
	}
	
	/**
	* Retorna uma representação em string de um campeonato.
	*
	* @param nomeCampeonato O nome do campeonato
	* @return Uma string formatada com as informações do campeonato
	* @throws IllegalArgumentException Se o campeonato não existir ou o nome for vazio.
	* @throws NullPointerException Se o nome for nulo.
	*/
	public String getCampeonato(String nomeCampeonato) {
		verificaSeVazio(nomeCampeonato, "NOME DO CAMPEONATO");
		validaCampeonato(nomeCampeonato);
		
		return this.campeonatos.get(nomeCampeonato.toLowerCase()).toString();
	}

	/**
	* Realiza uma aposta em um time para um campeonato.
	*
	* @param codigo        O código do time.
	* @param nomeCampeonato O nome do campeonato.
	* @param colocacao     A colocação prevista para o time no campeonato.
	* @param valorAposta   O valor da aposta.
	* @throws IllegalArgumentException Se o campeonato não existir ou a colocação for inválida.
	* @throws NullPointerException Se algum dos parâmetros for nulo.
	* @return Uma String com "APOSTA NÃO REGISTADA!" ou "APOSTA REGISTRADA!"
	*/
	public String apostar(String codigo, String nomeCampeonato, Integer colocacao, Double valorAposta) {
		verificaSeVazio(codigo, "CÓDIGO DO TIME");
		verificaSeVazio(nomeCampeonato, "NOME DO CAMPEONATO");
		verificaSeVazio(colocacao, "COLOCAÇÃO DO TIME");
		verificaSeVazio(valorAposta, "VALOR DA APOSTA");
		
		if(!this.campeonatos.containsKey(nomeCampeonato.toLowerCase()) || !this.times.containsKey(codigo) || 0 >= colocacao || colocacao > this.campeonatos.get(nomeCampeonato.toLowerCase()).getMaxParticipantes()) {
			return "APOSTA NÃO REGISTADA!";
		}

		this.apostas.add(new Aposta(codigo, valorAposta, colocacao, nomeCampeonato));
		return "APOSTA REGISTRADA!";
	}

	/**
	* Lista todas as apostas realizadas no sistema.
	*
	* @return Uma string formatada com a lista de apostas.
	*/
	public String listarApostas(){
		StringBuilder resultado = new StringBuilder();
		int index = 1;

		for(Aposta atualAposta: this.apostas){
			Time atualTime = this.times.get(atualAposta.getIdTime());
			Campeonato atualCampeonato = this.campeonatos.get(atualAposta.getNomeCampeonato().toLowerCase());

			resultado.append(index++).append(". ").append(atualTime.toString()).append("\n");
			resultado.append(atualCampeonato.getNome()).append("\n");
			resultado.append(atualAposta.getColocacao()).append("/").append(atualCampeonato.getMaxParticipantes()).append("\n");
			resultado.append("R$ ").append(atualAposta.getValor()).append("\n");
		}

		return resultado.toString();
	}

	/**
	* Retorna os campeonatos em que um time está participando.
	*
	* @param codigo O código do time.
	* @return Uma string formatada com a lista de campeonatos do time.
	* @throws IllegalArgumentException Se o time não existir.
	* @throws NullPointerException Se o código for nulo.
	*/
	public String getCampeonatosDoTime(String codigo){
		verificaSeVazio(codigo, "CÓDIGO DO TIME");
		validaTime(codigo);

		StringBuilder resultado = new StringBuilder();
		Time time = this.times.get(codigo);
		String[] chavesCampeonatos = time.getCampeonatosParticipando();

		resultado.append("Campeonatos do ").append(time.getNome()).append(":");
		for(String chave: chavesCampeonatos){
			Campeonato atual = this.campeonatos.get(chave.toLowerCase());
			resultado.append("\n* ").append(atual.toString());
		}

		return resultado.toString();
	}

	private String getTimesMaisParticipantes(){
		int maxParticipacoes = 0;
		StringBuilder timesMaisParticipantes = new StringBuilder();

		for(Time time : this.times.values()){
			int participacoes = time.getCampeonatosParticipando().length;

			if(participacoes > maxParticipacoes){
				maxParticipacoes = participacoes;
				timesMaisParticipantes = new StringBuilder(time.toString() + " " + participacoes + "\n");
			}else if (participacoes == maxParticipacoes){
				timesMaisParticipantes.append(time.toString() + " " + participacoes + "\n");
			}
		}

		return timesMaisParticipantes.toString();
	}

	private String getTimesSemParticipacao(){
		StringBuilder timesSemParticipacao = new StringBuilder();

		for (Time time : this.times.values()) {
			if (time.getCampeonatosParticipando().length == 0) {
				timesSemParticipacao.append(time.toString()).append("\n");
			}
		}

		return timesSemParticipacao.toString();
	}

	private String getTimesMaisApostadosPrimeiro(){
		HashMap<String, Integer> contadorApostas = new HashMap<>();

		for (Aposta aposta : this.apostas) {
			if (aposta.getColocacao() == 1) {
				contadorApostas.put(aposta.getIdTime(), contadorApostas.getOrDefault(aposta.getIdTime(), 0) + 1);
			}
		}

		StringBuilder apostadosPrimeiro = new StringBuilder();
		for (Map.Entry<String, Integer> pair: contadorApostas.entrySet()) {
			String nomeTime = this.times.get(pair.getKey()).getNome();
			Integer quantidade = pair.getValue();
			apostadosPrimeiro.append(nomeTime).append(" / ").append(quantidade).append("\n");
		}

		return apostadosPrimeiro.toString();
	}

	private void validaTime(String codigo){
		if (!this.times.containsKey(codigo)) {
			throw new IllegalArgumentException("TIME NÃO EXISTE");
		}
	}

	private void validaCampeonato(String nome){
		if (!this.campeonatos.containsKey(nome.toLowerCase())) {
			throw new IllegalArgumentException("CAMPEONATO NÃO EXISTE");		
		}
	}

	private void verificaSeVazio(Object argumento, String mensagem){
		if(argumento == null){
			throw new NullPointerException(mensagem + " NULO!");
		}
		
		if(argumento instanceof String) {
			String str = (String) argumento;
			
			if(str.isBlank()) {
				throw new IllegalArgumentException(mensagem + " VAZIO!");	
			}
		}
	}
}
