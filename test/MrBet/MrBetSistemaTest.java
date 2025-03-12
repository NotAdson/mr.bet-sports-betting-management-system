package MrBet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SistemaMrBetTest {

	private MrBetSistema sistema;

	@BeforeEach
	void setUp() {
		sistema = new MrBetSistema();
	}

	@Test
	void testCadastraTimeValido() {
		sistema.cadastraTime("250_PB", "Nacional de Patos", "Canário");
	}

	@Test
	void testCadastraTimeCodigoNulo() {
		NullPointerException excecao = assertThrows(NullPointerException.class, () -> {
			sistema.cadastraTime(null, "Nacional de Patos", "Canário");
		});
		assertEquals("CÓDIGO DO TIME NULO!", excecao.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testCadastraTimeCodigoVazioOuEmBranco(String codigo) {
		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
			sistema.cadastraTime(codigo, "Nacional de Patos", "Canário");
		});
		assertEquals("CÓDIGO DO TIME VAZIO!", excecao.getMessage());
	}

	@Test
	void testCadastraTimeNomeNulo() {
		NullPointerException excecao = assertThrows(NullPointerException.class, () -> {
			sistema.cadastraTime("250_PB", null, "Canário");
		});
		assertEquals("NOME DO TIME NULO!", excecao.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testCadastraTimeNomeVazioOuEmBranco(String nome) {
		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
			sistema.cadastraTime("250_PB", nome, "Canário");
		});
		assertEquals("NOME DO TIME VAZIO!", excecao.getMessage());
	}

	@Test
	void testCadastraTimeMascoteNulo() {
		NullPointerException excecao = assertThrows(NullPointerException.class, () -> {
			sistema.cadastraTime("250_PB", "Nacional de Patos", null);
		});
		assertEquals("MASCOTE DO TIME NULO!", excecao.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testCadastraTimeMascoteVazioOuEmBranco(String mascote) {
		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
			sistema.cadastraTime("250_PB", "Nacional de Patos", mascote);
		});
		assertEquals("MASCOTE DO TIME VAZIO!", excecao.getMessage());
	}

	@Test
	void testCadastraTimeCodigoExistente() {
		sistema.cadastraTime("250_PB", "Nacional de Patos", "Canário");

		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
			sistema.cadastraTime("250_PB", "Sport Lagoa Seca", "Carneiro");
		});
		assertEquals("TIME JÁ EXISTE!", excecao.getMessage());
	}

	@Test
	void testCadastraCampeonatoValido() {
		sistema.cadastraCampeonato("Brasileirão Série A 2023", 20);
		assertTrue(sistema.getCampeonato("Brasileirão Série A 2023") != null);
	}

	@Test
	void testCadastraCampeonatoNomeNulo() {
		NullPointerException excecao = assertThrows(NullPointerException.class, () -> {
			sistema.cadastraCampeonato(null, 20);
		});
		assertEquals("NOME DO CAMPEONATO NULO!", excecao.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testCadastraCampeonatoNomeVazioOuEmBranco(String nome) {
		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
			sistema.cadastraCampeonato(nome, 20);
		});
		assertEquals("NOME DO CAMPEONATO VAZIO!", excecao.getMessage());
	}

	@Test
	void testCadastraCampeonatoQuantidadeParticipantesNula() {
		NullPointerException excecao = assertThrows(NullPointerException.class, () -> {
			sistema.cadastraCampeonato("Brasileirão Série A 2023", null);
		});
		assertEquals("QUANTIDADE DE PARTICIPANTES NULO!", excecao.getMessage());
	}

	@Test
	void testCadastraCampeonatoQuantidadeParticipantesInvalida() {
		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
			sistema.cadastraCampeonato("Brasileirão Série A 2023", 0);
		});
		assertEquals("QUANTIDADE DE PARTICIPANTES INVÁLIDA!", excecao.getMessage());
	}

	@Test
	void testCadastraCampeonatoNomeExistente() {
		sistema.cadastraCampeonato("Brasileirão Série A 2023", 20);

		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
			sistema.cadastraCampeonato("brasileirão série a 2023", 20);
		});
		assertEquals("CAMPEONATO JÁ EXISTE!", excecao.getMessage());
	}

	@Test
	void testAdicionaTimeNoCampeonatoValido() {
		sistema.cadastraTime("250_PB", "Nacional de Patos", "Canário");
		sistema.cadastraCampeonato("Brasileirão Série A 2023", 20);

		sistema.adicionaTimeNoCampeonato("250_PB", "Brasileirão Série A 2023");
		assertTrue(sistema.verificaTimeEmCampeonato("250_PB", "brasileirão série a 2023"));
	}

	@Test
	void testAdicionaTimeNoCampeonatoCodigoTimeNulo() {
		NullPointerException excecao = assertThrows(NullPointerException.class, () -> {
			sistema.adicionaTimeNoCampeonato(null, "Brasileirão Série A 2023");
		});
		assertEquals("CÓDIGO DO TIME NULO!", excecao.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testAdicionaTimeNoCampeonatoCodigoTimeVazioOuEmBranco(String idTime) {
		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
			sistema.adicionaTimeNoCampeonato(idTime, "Brasileirão Série A 2023");
		});
		assertEquals("CÓDIGO DO TIME VAZIO!", excecao.getMessage());
	}

	@Test
	void testAdicionaTimeNoCampeonatoNomeCampeonatoNulo() {
		NullPointerException excecao = assertThrows(NullPointerException.class, () -> {
			sistema.adicionaTimeNoCampeonato("250_PB", null);
		});
		assertEquals("NOME DO CAMPEONATO NULO!", excecao.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testAdicionaTimeNoCampeonatoNomeCampeonatoVazioOuEmBranco(String nomeCampeonato) {
		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
			sistema.adicionaTimeNoCampeonato("250_PB", nomeCampeonato);
		});
		assertEquals("NOME DO CAMPEONATO VAZIO!", excecao.getMessage());
	}

	@Test
	void testAdicionaTimeNoCampeonatoTimeNaoCadastrado() {
		sistema.cadastraCampeonato("Brasileirão Série A 2023", 20);

		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
			sistema.adicionaTimeNoCampeonato("250_PB", "Brasileirão Série A 2023");
		});
		assertEquals("TIME NÃO EXISTE", excecao.getMessage());
	}

	@Test
	void testAdicionaTimeNoCampeonatoCampeonatoNaoCadastrado() {
		sistema.cadastraTime("250_PB", "Nacional de Patos", "Canário");

		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
			sistema.adicionaTimeNoCampeonato("250_PB", "Brasileirão Série A 2023");
		});
		assertEquals("CAMPEONATO NÃO EXISTE", excecao.getMessage());
	}

	@Test
	void testAdicionaTimeNoCampeonatoLimiteParticipantesAtingido() {
		assertAll(
				() -> sistema.cadastraTime("250_PB", "Nacional de Patos", "Canário"),
				() -> sistema.cadastraTime("252_PB", "Sport Lagoa Seca", "Carneiro"),
				() -> sistema.cadastraCampeonato("Brasileirão Série A 2023", 1)
			 );

		sistema.adicionaTimeNoCampeonato("250_PB", "Brasileirão Série A 2023");

		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
			sistema.adicionaTimeNoCampeonato("252_PB", "Brasileirão Série A 2023");
		});
		assertEquals("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!", excecao.getMessage());
	}

	@Test
	void testApostarValido() {
		sistema.cadastraTime("250_PB", "Nacional de Patos", "Canário");
		sistema.cadastraCampeonato("Campeonato Paraibano 2023", 14);

		sistema.apostar("250_PB", "Campeonato Paraibano 2023", 2, 50.0);
		assertEquals("1. [250_PB] Nacional de Patos / Canário\nCampeonato Paraibano 2023\n2/14\nR$ 50.0\n", sistema.listarApostas());
	}

	@Test
	void testApostarCodigoTimeNulo() {
		NullPointerException excecao = assertThrows(NullPointerException.class, () -> {
			sistema.apostar(null, "Brasileirão Série A 2023", 1, 100.0);
		});
		assertEquals("CÓDIGO DO TIME NULO!", excecao.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testApostarCodigoTimeVazioOuEmBranco(String codigo) {
		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
			sistema.apostar(codigo, "Brasileirão Série A 2023", 1, 100.0);
		});
		assertEquals("CÓDIGO DO TIME VAZIO!", excecao.getMessage());
	}

	@Test
	void testApostarNomeCampeonatoNulo() {
		NullPointerException excecao = assertThrows(NullPointerException.class, () -> {
			sistema.apostar("250_PB", null, 1, 100.0);
		});
		assertEquals("NOME DO CAMPEONATO NULO!", excecao.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testApostarNomeCampeonatoVazioOuEmBranco(String nomeCampeonato) {
		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
			sistema.apostar("250_PB", nomeCampeonato, 1, 100.0);
		});
		assertEquals("NOME DO CAMPEONATO VAZIO!", excecao.getMessage());
	}

	@Test
	void testApostarColocacaoNula() {
		NullPointerException excecao = assertThrows(NullPointerException.class, () -> {
			sistema.apostar("250_PB", "Brasileirão Série A 2023", null, 100.0);
		});
		assertEquals("COLOCAÇÃO DO TIME NULO!", excecao.getMessage());
	}

	@Test
	void testApostarColocacaoInvalida() {
		sistema.cadastraTime("250_PB", "Nacional de Patos", "Canário");
		sistema.cadastraCampeonato("Brasileirão Série A 2023", 20);

		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
			sistema.apostar("250_PB", "Brasileirão Série A 2023", 21, 100.0);
		});
		assertEquals("APOSTA NÃO REGISTADA!", excecao.getMessage());
	}

	@Test
	void testApostarValorApostaNulo() {
		NullPointerException excecao = assertThrows(NullPointerException.class, () -> {
			sistema.apostar("250_PB", "Brasileirão Série A 2023", 1, null);
		});
		assertEquals("VALOR DA APOSTA NULO!", excecao.getMessage());
	}

	@Test
	void testApostarCampeonatoNaoCadastrado() {
		sistema.cadastraTime("250_PB", "Nacional de Patos", "Canário");

		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
			sistema.apostar("250_PB", "Brasileirão Série A 2023", 1, 100.0);
		});
		assertEquals("APOSTA NÃO REGISTADA!", excecao.getMessage());
	}

	@Test
	void testListarApostas() {
		sistema.cadastraTime("250_PB", "Nacional de Patos", "Canário");
		sistema.cadastraCampeonato("Brasileirão Série A 2023", 20);

		sistema.apostar("250_PB", "Brasileirão Série A 2023", 1, 100.0);
		sistema.apostar("250_PB", "Brasileirão Série A 2023", 2, 200.0);

		String resultado = sistema.listarApostas();
		assertTrue(resultado.contains("Nacional de Patos"));
		assertTrue(resultado.contains("Brasileirão Série A 2023"));
		assertTrue(resultado.contains("R$ 100.0"));
		assertTrue(resultado.contains("R$ 200.0"));
	}

	@Test
	void testGetCampeonatosDoTimeValido() {
		sistema.cadastraTime("250_PB", "Nacional de Patos", "Canário");
		sistema.cadastraCampeonato("Brasileirão Série A 2023", 20);
		sistema.adicionaTimeNoCampeonato("250_PB", "Brasileirão Série A 2023");

		String resultado = sistema.getCampeonatosDoTime("250_PB");
		assertTrue(resultado.contains("Campeonatos do Nacional de Patos:"));
		assertTrue(resultado.contains("* Brasileirão Série A 2023 - 1/20"));
	}

	@Test
	void testGetCampeonatosDoTimeCodigoNulo() {
		NullPointerException excecao = assertThrows(NullPointerException.class, () -> {
			sistema.getCampeonatosDoTime(null);
		});
		assertEquals("CÓDIGO DO TIME NULO!", excecao.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testGetCampeonatosDoTimeCodigoVazioOuEmBranco(String codigo) {
		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
			sistema.getCampeonatosDoTime(codigo);
		});
		assertEquals("CÓDIGO DO TIME VAZIO!", excecao.getMessage());
	}

	@Test
	void testGetCampeonatosDoTimeNaoCadastrado() {
		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
			sistema.getCampeonatosDoTime("250_PB");
		});
		assertEquals("TIME NÃO EXISTE", excecao.getMessage());
	}

	@Test
	void testGetCampeonatosDoTimeSemCampeonatos() {
		sistema.cadastraTime("250_PB", "Nacional de Patos", "Canário");

		String resultado = sistema.getCampeonatosDoTime("250_PB");
		assertTrue(resultado.contains("Campeonatos do Nacional de Patos:"));
		assertFalse(resultado.contains("*"));
	}

	@Test
	void testGetHistoricoSemDados() {
		String resultado = sistema.getHistorico();

		assertTrue(resultado.contains("Participação mais frequente em campeonatos"));
		assertTrue(resultado.contains("Ainda não participou de campeonato"));
		assertTrue(resultado.contains("Popularidade em apostas"));
	}

	@Test
	void testGetHistoricoComParticipacao() {
		sistema.cadastraTime("250_PB", "Nacional de Patos", "Canário");
		sistema.cadastraTime("252_PB", "Sport Lagoa Seca", "Carneiro");
		sistema.cadastraCampeonato("Brasileirão Série A 2023", 20);
		sistema.cadastraCampeonato("Copa do Nordeste 2023", 10);

		sistema.adicionaTimeNoCampeonato("250_PB", "Brasileirão Série A 2023");
		sistema.adicionaTimeNoCampeonato("250_PB", "Copa do Nordeste 2023");
		sistema.adicionaTimeNoCampeonato("252_PB", "Brasileirão Série A 2023");

		String resultado = sistema.getHistorico();

		assertTrue(resultado.contains("Participação mais frequente em campeonatos"));
		assertTrue(resultado.contains("Nacional de Patos"));
		assertTrue(resultado.contains("Ainda não participou de campeonato"));
		assertTrue(resultado.contains("Popularidade em apostas"));
	}

	@Test
	void testGetHistoricoComApostas() {
		sistema.cadastraTime("250_PB", "Nacional de Patos", "Canário");
		sistema.cadastraTime("252_PB", "Sport Lagoa Seca", "Carneiro");
		sistema.cadastraCampeonato("Brasileirão Série A 2023", 20);

		sistema.apostar("250_PB", "Brasileirão Série A 2023", 1, 100.0);
		sistema.apostar("250_PB", "Brasileirão Série A 2023", 1, 200.0);
		sistema.apostar("252_PB", "Brasileirão Série A 2023", 2, 50.0);

		String resultado = sistema.getHistorico();

		assertTrue(resultado.contains("Participação mais frequente em campeonatos"));
		assertTrue(resultado.contains("Ainda não participou de campeonato"));
		assertTrue(resultado.contains("Popularidade em apostas"));
		assertTrue(resultado.contains("Nacional de Patos"));
	}

	@Test
	void testGetHistoricoCompleto() {
		sistema.cadastraTime("250_PB", "Nacional de Patos", "Canário");
		sistema.cadastraTime("252_PB", "Sport Lagoa Seca", "Carneiro");
		sistema.cadastraTime("002_RJ", "Flamengo", "Urubu");
		sistema.cadastraCampeonato("Brasileirão Série A 2023", 20);
		sistema.cadastraCampeonato("Copa do Nordeste 2023", 10);

		sistema.adicionaTimeNoCampeonato("250_PB", "Brasileirão Série A 2023");
		sistema.adicionaTimeNoCampeonato("250_PB", "Copa do Nordeste 2023");
		sistema.adicionaTimeNoCampeonato("252_PB", "Brasileirão Série A 2023");

		sistema.apostar("250_PB", "Brasileirão Série A 2023", 1, 100.0);
		sistema.apostar("250_PB", "Brasileirão Série A 2023", 1, 200.0);
		sistema.apostar("252_PB", "Brasileirão Série A 2023", 2, 50.0);

		String resultado = sistema.getHistorico();

		assertTrue(resultado.contains("Participação mais frequente em campeonatos"));
		assertTrue(resultado.contains("Nacional de Patos"));
		assertTrue(resultado.contains("Ainda não participou de campeonato"));
		assertTrue(resultado.contains("Flamengo"));
		assertTrue(resultado.contains("Popularidade em apostas"));
		assertTrue(resultado.contains("Nacional de Patos"));
	}

	@Test
	void testGetTimeValido() {
		sistema.cadastraTime("250_PB", "Nacional de Patos", "Canário");

		String resultado = sistema.getTime("250_PB");
		assertEquals("[250_PB] Nacional de Patos / Canário", resultado);
	}

	@Test
	void testGetTimeCodigoNulo() {
		NullPointerException excecao = assertThrows(NullPointerException.class, () -> {
			sistema.getTime(null);
		});
		assertEquals("CÓDIGO DO TIME NULO!", excecao.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testGetTimeCodigoVazioOuEmBranco(String codigo) {
		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
			sistema.getTime(codigo);
		});
		assertEquals("CÓDIGO DO TIME VAZIO!", excecao.getMessage());
	}

	@Test
	void testGetTimeNaoCadastrado() {
		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
			sistema.getTime("250_PB");
		});
		assertEquals("TIME NÃO EXISTE", excecao.getMessage());
	}
}
