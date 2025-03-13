package MrBet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class CampeonatoTest {

	@ParameterizedTest
	@CsvSource({
	"Campeonato 1, 10",
		"Campeonato 2, 5",
		"Campeonato 3, 20"
	})
	void testConstrutorEntradasValidas(String nome, int qntdParticipantes) {
		Campeonato campeonato = new Campeonato(nome, qntdParticipantes);

		assertEquals(nome, campeonato.getNome());
		assertEquals(qntdParticipantes, campeonato.getMaxParticipantes());
		assertTrue(campeonato.getParticipantes().isEmpty());
	}

	@Test
	void testConstrutorNomeNulo() {
		NullPointerException excecao = assertThrows(NullPointerException.class,() -> {
			new Campeonato(null, 10);
		});

		assertEquals("NOME DO CAMPEONATO NULO OU EM BRANCO!", excecao.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testConstrutorNomeVazioOuEmBranco(String nome) {
		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,() -> {
			new Campeonato(nome, 10);
		});

		assertEquals("NOME DO CAMPEONATO NULO OU EM BRANCO!", excecao.getMessage());
	}

	@ParameterizedTest
	@ValueSource(ints = {0, -1})
	void testConstrutorQuantidadeParticipantesInvalida(int qntdParticipantes) {
		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,() -> {
			new Campeonato("Campeonato A", qntdParticipantes);
		});

		assertEquals("QUANTIDADE DE PARTICIPANTES DEVE SER MAIOR QUE ZERO!", excecao.getMessage());
	}

	@Test
	void testAdicionaTimeValido() {
		Campeonato campeonato = new Campeonato("Campeonato A", 2);
		campeonato.adicionaTime("123_AB");

		assertTrue(campeonato.getParticipantes().contains("123_AB"));
	}

	@Test
	void testAdicionaTimeCampeonatoCheio() {
		Campeonato campeonato = new Campeonato("Campeonato A", 1);
		campeonato.adicionaTime("123_AB");

		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,() -> {
			campeonato.adicionaTime("345_CD");
		});

		assertEquals("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!", excecao.getMessage());
	}

	@Test
	void testAdicionaTimeIdNulo() {
		Campeonato campeonato = new Campeonato("Campeonato A", 10);

		NullPointerException excecao = assertThrows(NullPointerException.class,() -> {
			campeonato.adicionaTime(null);
		});

		assertEquals("CÓDIGO DO TIME NULO OU EM BRANCO!", excecao.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testAdicionaTimeIdVazioOuEmBranco(String idTime) {
		Campeonato campeonato = new Campeonato("Campeonato A", 10);

		Exception excecao = assertThrows(
				IllegalArgumentException.class,
				() -> campeonato.adicionaTime(idTime)
				);

		assertEquals("CÓDIGO DO TIME NULO OU EM BRANCO!", excecao.getMessage());
	}

	@Test
	void testVerificaTimePresente() {
		Campeonato campeonato = new Campeonato("Campeonato A", 10);
		campeonato.adicionaTime("123_AB");

		assertTrue(campeonato.verificaTime("123_AB"));
	}

	@Test
	void testVerificaTimeNaoPresente() {
		Campeonato campeonato = new Campeonato("Campeonato A", 10);

		assertFalse(campeonato.verificaTime("123_AB"));
	}

	@Test
	void testVerificaTimeIdNulo() {
		Campeonato campeonato = new Campeonato("Campeonato A", 10);

		Exception excecao = assertThrows(NullPointerException.class, () -> {
			campeonato.verificaTime(null);
		});

		assertEquals("CÓDIGO DO TIME NULO OU EM BRANCO!", excecao.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testVerificaTimeIdVazioOuEmBranco(String idTime) {
		Campeonato campeonato = new Campeonato("Campeonato A", 10);

		Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
			campeonato.verificaTime(idTime);
		});

		assertEquals("CÓDIGO DO TIME NULO OU EM BRANCO!", excecao.getMessage());
	}

	@Test
	void testEqualsMesmoObjeto() {
		Campeonato campeonato = new Campeonato("Campeonato A", 10);
		assertTrue(campeonato.equals(campeonato));
	}

	@Test
	void testEqualsObjetosIguais() {
		Campeonato campeonato1 = new Campeonato("Campeonato A", 10);
		Campeonato campeonato2 = new Campeonato("Campeonato A", 5);
		assertTrue(campeonato1.equals(campeonato2));
	}

	@Test
	void testEqualsObjetosDiferentes() {
		Campeonato campeonato1 = new Campeonato("Campeonato A", 10);
		Campeonato campeonato2 = new Campeonato("Campeonato B", 10);
		assertFalse(campeonato1.equals(campeonato2));
	}

	@Test
	void testEqualsComNull() {
		Campeonato campeonato = new Campeonato("Campeonato A", 10);
		assertFalse(campeonato.equals(null));
	}

	@Test
	void testEqualsComOutraClasse() {
		Campeonato campeonato = new Campeonato("Campeonato A", 10);
		assertFalse(campeonato.equals("Não sou um Campeonato"));
	}

	@Test
	void testToString() {
		Campeonato campeonato = new Campeonato("Campeonato A", 10);
		campeonato.adicionaTime("123_AB");

		String resultadoEsperado = "Campeonato A - 1/10";
		assertEquals(resultadoEsperado, campeonato.toString());
	}
}
