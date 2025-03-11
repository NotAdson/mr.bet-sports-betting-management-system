package MrBet;

import java.util.HashSet;

/**
 * Representa um time com um identificador único, nome, mascote e uma lista de campeonatos em que participa.
 */
public class Time {
	private String id;
	private String nome;
	private String mascote;
	private HashSet<String> campeonatosParticipando;

	/**
	 * Constrói um time com os dados fornecidos.
	 *
	 * @param id      O código único do time.
	 * @param nome    O nome do time.
	 * @param mascote O mascote do time.
	 * @throws IllegalArgumentException Se algum dos parâmetros for nulo ou uma string em branco.
	 */
	public Time(String id, String nome, String mascote) {
		validaSeNulo(id, "CÓDIGO DO TIME NULO OU EM BRANCO!");
		validaSeNulo(nome, "NOME DO TIME NULO OU EM BRANCO!");
		validaSeNulo(mascote, "MASCOTE DO TIME NULO OU EM BRANCO!");

		this.id = id;
		this.nome = nome;
		this.mascote = mascote;
		this.campeonatosParticipando = new HashSet<>();
	}

	/**
	 * Adiciona um campeonato à lista de campeonatos em que o time participa.
	 *
	 * @param nomeCampeonato O nome do campeonato.
	 * @throws IllegalArgumentException Se o nome do campeonato for nulo ou uma string em branco.
	 */
	public void adicionaCampeonato(String nomeCampeonato) {
		validaSeNulo(nomeCampeonato, "NOME DO CAMPEONATO NULO OU EM BRANCO!");
		campeonatosParticipando.add(nomeCampeonato);
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getMascote() {
		return mascote;
	}

	/**
	 * Retorna um array com os nomes dos campeonatos em que o time participa.
	 *
	 * @return Um array de strings com os nomes dos campeonatos.
	 */
	public String[] getCampeonatosParticipando() {
		String[] resultado = new String[this.campeonatosParticipando.size()];

		int index = 0;
		for (String campeonato : this.campeonatosParticipando) {
			resultado[index++] = campeonato;
		}

		return resultado;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Time time = (Time) obj;
		return id.equals(time.id);
	}

	@Override
	public String toString() {
		return "[" + id + "] " + nome + " / " + mascote;
	}

	private void validaSeNulo(Object argumento, String mensagem) {
		if (argumento == null) {
			throw new NullPointerException(mensagem);
		}
		if (argumento instanceof String) {
			String str = (String) argumento;
			if (str.isBlank()) {
				throw new IllegalArgumentException(mensagem);
			}
		}
	}
}
