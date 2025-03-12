package MrBet;

import java.util.Scanner;

class MainMrBet {
	public static void main(String[] args) {
		MrBetSistema sistema = new MrBetSistema();
		Scanner scanner = new Scanner(System.in);
		MainMrBet main = new MainMrBet();

		while (true) {
			System.out.println("(M)Minha inclusão de times\n(R)Recuperar time\n(.)Adicionar campeonato\n(B)Bora incluir time em campeonato e Verificar se time está em campeonato\n(E)Exibir campeonatos que o time participa\n(T)Tentar a sorte e status\n(H)Exibir histórico\n(!)Já pode fechar o programa!");
			System.out.print("Opção> ");
			String opcao = lerLinha(scanner).toUpperCase();

			if ("!".equals(opcao)) {
				System.out.println("Por hoje é só, pessoal!");
				break;
			}

			main.comandos(sistema, scanner, opcao);
		}

		scanner.close();
	}

	public void comandos(MrBetSistema sistema, Scanner scanner, String opcao) {
		switch (opcao) {
			case "M":
				adicionarTime(sistema, scanner);
				break;
			case "R":
				recuperarTime(sistema, scanner);
				break;
			case ".":
				adicionarCampeonato(sistema, scanner);
				break;
			case "B":
				System.out.println("(I) Incluir time em campeonato ou (V) Verificar se time está em campeonato?");
				String opcaoB = lerLinha(scanner).toLowerCase();

				if ("i".equals(opcaoB)) {
					incluirTimeEmCampeonato(sistema, scanner);
				} else if ("v".equals(opcaoB)) {
					verificarTimeEmCampeonato(sistema, scanner);
				}
				break;
			case "E":
				exibirCampeonatosQueTimeParticipa(sistema, scanner);
				break;
			case "T":
				System.out.println("(A) Apostar ou (S) Status das Apostas?");
				String opcaoT = lerLinha(scanner).toLowerCase();

				if ("a".equals(opcaoT)) {
					realizarAposta(sistema, scanner);
				} else if ("s".equals(opcaoT)) {
					statusDasApostas(sistema);
				}
				break;
			case "H":
				historico(sistema);
				break;
			default:
				System.out.println("Opção inválida!");
				break;
		}
	}

	private static void adicionarTime(MrBetSistema sistema, Scanner scanner) {
			System.out.print("Código: ");
			String codigo = lerLinha(scanner);

			System.out.print("Nome: ");
			String nome = lerLinha(scanner);

			System.out.print("Mascote: ");
			String mascote = lerLinha(scanner);

		try {
			sistema.cadastraTime(codigo, nome, mascote);
			System.out.println("INCLUSÃO REALIZADA!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void recuperarTime(MrBetSistema sistema, Scanner scanner) {
			System.out.print("Código: ");
			String codigo = lerLinha(scanner);

		try {
			System.out.println(sistema.getTime(codigo));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void adicionarCampeonato(MrBetSistema sistema, Scanner scanner) {
			System.out.print("Campeonato: ");
			String nome = lerLinha(scanner);

			System.out.print("Participantes: ");
			String quantidadeParticipantes = lerLinha(scanner);

		try {
			sistema.cadastraCampeonato(nome, Integer.parseInt(quantidadeParticipantes));
			System.out.println("CAMPEONATO ADICIONADO!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void incluirTimeEmCampeonato(MrBetSistema sistema, Scanner scanner) {
			System.out.print("Código: ");
			String codigo = lerLinha(scanner);

			System.out.print("Campeonato: ");
			String nomeCampeonato = lerLinha(scanner);

		try {
			sistema.adicionaTimeNoCampeonato(codigo, nomeCampeonato);
			System.out.println("TIME INCLUÍDO NO CAMPEONATO!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void verificarTimeEmCampeonato(MrBetSistema sistema, Scanner scanner) {
			System.out.print("Código: ");
			String codigo = lerLinha(scanner);

			System.out.print("Campeonato: ");
			String nomeCampeonato = lerLinha(scanner);

		try {
			boolean estaNoCampeonato = sistema.verificaTimeEmCampeonato(codigo, nomeCampeonato);
			if (estaNoCampeonato) {
				System.out.println("O TIME ESTÁ NO CAMPEONATO!");
			} else {
				System.out.println("O TIME NÃO ESTÁ NO CAMPEONATO!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void exibirCampeonatosQueTimeParticipa(MrBetSistema sistema, Scanner scanner) {
			System.out.print("Código: ");
			String codigo = lerLinha(scanner);

		try {
			System.out.println(sistema.getCampeonatosDoTime(codigo));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void realizarAposta(MrBetSistema sistema, Scanner scanner) {
			System.out.print("Código: ");
			String codigo = lerLinha(scanner);

			System.out.print("Campeonato: ");
			String nomeCampeonato = lerLinha(scanner);

			System.out.print("Colocação: ");
			String colocacao = lerLinha(scanner);

			System.out.print("Valor da Aposta: ");
			String valor = lerLinha(scanner);

		try {
			sistema.apostar(codigo, nomeCampeonato, Integer.parseInt(colocacao), Double.parseDouble(valor));
			System.out.println("APOSTA REGISTRADA!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void statusDasApostas(MrBetSistema sistema) {
		try {
			System.out.println(sistema.listarApostas());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void historico(MrBetSistema sistema){
		try {
			System.out.println(sistema.getHistorico());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static String lerLinha(Scanner sc){
		String linha = sc.nextLine();
		verificaSeVazio(linha);

		return linha;
	}

	private static void verificaSeVazio(Object o){
		if(o == null){
			throw new NullPointerException("A ENTRADA É NULA!");
		}

		if(o instanceof String){
			String str = (String)o;
			if(str.isBlank()){
				throw new IllegalArgumentException("A ENTRADA É VAZIA!");
			}
		}
	}
}
