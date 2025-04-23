package mrbet;

import java.util.Scanner;

class MainMrBet {
	public static void main(String[] args) {
		MrBetSistema sistema = new MrBetSistema();
		Scanner scanner = new Scanner(System.in);
		MainMrBet main = new MainMrBet();

		while (true) {
			System.out.println("(M)Minha inclusão de times\n(R)Recuperar time\n(.)Adicionar campeonato\n(B)Bora incluir time em campeonato e Verificar se time está em campeonato\n(E)Exibir campeonatos que o time participa\n(T)Tentar a sorte e status\n(H)Exibir histórico\n(!)Já pode fechar o programa!");
			System.out.print("Opção> ");
			String opcao = scanner.nextLine().toUpperCase();

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
				String opcaoB = scanner.nextLine().toLowerCase();

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
				String opcaoT = scanner.nextLine().toLowerCase();

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
		String codigo = scanner.nextLine();

		System.out.print("Nome: ");
		String nome = scanner.nextLine();

		System.out.print("Mascote: ");
		String mascote = scanner.nextLine();

		System.out.println(sistema.cadastraTime(codigo, nome, mascote));
	}

	private static void recuperarTime(MrBetSistema sistema, Scanner scanner) {
		System.out.print("Código: ");
		String codigo = scanner.nextLine();

		System.out.println(sistema.getTime(codigo));
	}

	private static void adicionarCampeonato(MrBetSistema sistema, Scanner scanner) {
		System.out.print("Campeonato: ");
		String nome = scanner.nextLine();

		System.out.print("Participantes: ");
		String quantidadeParticipantes = scanner.nextLine();

		sistema.cadastraCampeonato(nome, Integer.parseInt(quantidadeParticipantes));
	}

	private static void incluirTimeEmCampeonato(MrBetSistema sistema, Scanner scanner) {
		System.out.print("Código: ");
		String codigo = scanner.nextLine();

		System.out.print("Campeonato: ");
		String nomeCampeonato = scanner.nextLine();

		sistema.adicionaTimeNoCampeonato(codigo, nomeCampeonato);
	}

	private static void verificarTimeEmCampeonato(MrBetSistema sistema, Scanner scanner) {
			System.out.print("Código: ");
			String codigo = scanner.nextLine();

			System.out.print("Campeonato: ");
			String nomeCampeonato = scanner.nextLine();

			System.out.println(sistema.verificaTimeEmCampeonato(codigo, nomeCampeonato));
	}

	private static void exibirCampeonatosQueTimeParticipa(MrBetSistema sistema, Scanner scanner) {
		System.out.print("Código: ");
		String codigo = scanner.nextLine();
		System.out.println(sistema.getCampeonatosDoTime(codigo));
	}

	private static void realizarAposta(MrBetSistema sistema, Scanner scanner) {
		System.out.print("Código: ");
		String codigo = scanner.nextLine();

		System.out.print("Campeonato: ");
		String nomeCampeonato = scanner.nextLine();

		System.out.print("Colocação: ");
		String colocacao = scanner.nextLine();

		System.out.print("Valor da Aposta: ");
		String valor = scanner.nextLine();

		sistema.apostar(codigo, nomeCampeonato, Integer.parseInt(colocacao), Double.parseDouble(valor));
	}

	private static void statusDasApostas(MrBetSistema sistema) {
		System.out.println(sistema.listarApostas());
	}

	private static void historico(MrBetSistema sistema){
		System.out.println(sistema.getHistorico());
	}

}
