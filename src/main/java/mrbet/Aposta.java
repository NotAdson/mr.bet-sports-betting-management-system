package mrbet;

/**
 * Representa uma aposta feita em um time para um campeonato.
 */
public class Aposta {
	private String idTime;
	private double valor;
	private int colocacao;
	private String nomeCampeonato;

	/**
	 * Constrói uma aposta com os dados fornecidos.
	 *
	 * @param idTime         O código do time.
	 * @param valor          O valor da aposta.
	 * @param colocacao      A colocação prevista para o time no campeonato.
	 * @param nomeCampeonato O nome do campeonato.
	 * @throws IllegalArgumentException Se o valor for negativo, a colocação for menor que 1, ou o nome do campeonato for inválido.
	 * @throws NullPointerException     Se algum dos parâmetros for nulo ou uma string em branco.
	 */
	public Aposta(String idTime, double valor, int colocacao, String nomeCampeonato) {
		validaSeNulo(idTime, "CÓDIGO DO TIME NULO OU EM BRANCO!");
		validaSeNulo(nomeCampeonato, "NOME DO CAMPEONATO NULO OU EM BRANCO!");

		if (valor <= 0) {
			throw new IllegalArgumentException("VALOR DA APOSTA DEVE SER MAIOR QUE ZERO!");
		}
		if (colocacao < 1) {
			throw new IllegalArgumentException("COLOCAÇÃO DEVE SER MAIOR OU IGUAL A 1!");
		}

		this.idTime = idTime;
		this.valor = valor;
		this.colocacao = colocacao;
		this.nomeCampeonato = nomeCampeonato;
	}

	public String getIdTime() {
		return idTime;
	}

	public double getValor() {
		return valor;
	}

	public int getColocacao() {
		return colocacao;
	}

	public String getNomeCampeonato() {
		return nomeCampeonato;
	}

	private void validaSeNulo(Object argumento, String mensagem) {
		if (argumento == null) {
			throw new NullPointerException(mensagem);
		}
		if (argumento instanceof String) {
			String str = (String) argumento;
			if (str.isBlank()){
				throw new IllegalArgumentException(mensagem);
			}
		}
	}
}
