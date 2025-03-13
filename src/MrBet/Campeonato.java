package MrBet;

import java.util.HashSet;

/**
 * Representa um campeonato com um nome, um conjunto de participantes e um limite máximo de participantes.
 */
public class Campeonato {
	private String nome;
	private HashSet<String> participantes;
	private int maxParticipantes;

	/**
	 * Constrói um campeonato com os dados fornecidos.
	 *
	 * @param nome             O nome do campeonato.
	 * @param qntdParticipantes A quantidade máxima de participantes no campeonato.
	 * @throws IllegalArgumentException Se o nome for nulo ou uma string em branco, ou se a quantidade de participantes for menor ou igual a zero.
	 */
	public Campeonato(String nome, int qntdParticipantes) {
		validaSeNulo(nome, "NOME DO CAMPEONATO NULO OU EM BRANCO!");

		this.nome = nome;
		this.participantes = new HashSet<>();
		this.maxParticipantes = qntdParticipantes;
	}

	/**
	 * Adiciona um time ao campeonato.
	 *
	 * @param idTime O código do time a ser adicionado.
	 * @throws IllegalArgumentException Se o campeonato já estiver cheio ou se o código do time for nulo ou uma string em branco.
	 */
	public void adicionaTime(String idTime) {
		validaSeNulo(idTime, "CÓDIGO DO TIME NULO OU EM BRANCO!");

		if (participantes.size() >= maxParticipantes) {
			throw new IllegalArgumentException("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!");
		}
		participantes.add(idTime);
	}

	/**
	 * Verifica se um time está participando do campeonato.
	 *
	 * @param idTime O código do time.
	 * @return true se o time estiver no campeonato, false caso contrário.
	 * @throws IllegalArgumentException Se o código do time for nulo ou uma string em branco.
	 */
	public boolean verificaTime(String idTime) {
		validaSeNulo(idTime, "CÓDIGO DO TIME NULO OU EM BRANCO!");
		return participantes.contains(idTime);
	}

	public String getNome() {
		return nome;
	}

	public HashSet<String> getParticipantes() {
		return participantes;
	}

	public int getMaxParticipantes() {
		return maxParticipantes;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Campeonato campeonato = (Campeonato) obj;
		return nome.equalsIgnoreCase(campeonato.nome);
	}

	@Override
	public int hashCode() {
		return nome.toLowerCase().hashCode();
	}

	@Override
	public String toString() {
		return nome + " - " + participantes.size() + "/" + maxParticipantes;
	}

	/**
	 * Valida se o objeto é nulo ou, no caso de ser uma string, se está em branco.
	 *
	 * @param argumento O objeto a ser validado.
	 * @param mensagem  A mensagem de erro a ser lançada caso o objeto seja nulo ou uma string em branco.
	 * @throws NullPointerException     Se o objeto for nulo.
	 * @throws IllegalArgumentException Se o objeto for uma string em branco.
	 */
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
