package mrbet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class TimeTest {

	@ParameterizedTest
	@CsvSource({
		"123_AB, Time A, Gaviao",
		"456_CD, Time B, Tatu",
		"789_EF, Time C, Camelo"
	})
	void testConstrutorEntradasValidas(String id, String nome, String mascote) {
		Time time = new Time(id, nome, mascote);

		assertEquals(id, time.getId());
		assertEquals(nome, time.getNome());
		assertEquals(mascote, time.getMascote());
		assertEquals(0, time.getCampeonatosParticipando().length);
	}

	@Test
	void testConstrutorCodigoNulo() {
		NullPointerException excecao = assertThrows(NullPointerException.class, () -> {
			new Time(null, "Time A", "Mascote A");
		});

		assertEquals("CÓDIGO DO TIME NULO OU EM BRANCO!", excecao.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testConstrutorCodigoVazioOuEmBranco(String codigo) {
		Exception excecao = assertThrows(IllegalArgumentException.class,
				() -> new Time(codigo, "Time A", "Mascote A")
				);

		assertEquals("CÓDIGO DO TIME NULO OU EM BRANCO!", excecao.getMessage());
	}

	@Test
	void testConstrutorNomeNulo() {
		Exception excecao = assertThrows(NullPointerException.class, () -> {
			new Time("123_ABC", null, "Mascote A");
		});

		assertEquals("NOME DO TIME NULO OU EM BRANCO!", excecao.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testConstrutorNomeVazioOuEmBranco(String nome) {
		Exception excecao = assertThrows(
				IllegalArgumentException.class,
				() -> new Time("123_AB", nome, "Mascote A")
				);

		assertEquals("NOME DO TIME NULO OU EM BRANCO!", excecao.getMessage());
	}

	@Test
	void testConstrutorMascoteNulo() {
		Exception excecao = assertThrows(NullPointerException.class, () -> {
			new Time("123_AB", "Time A", null);
		});

		assertEquals("MASCOTE DO TIME NULO OU EM BRANCO!", excecao.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testConstrutorMascoteVazioOuEmBranco(String mascote) {
		Exception excecao = assertThrows(
				IllegalArgumentException.class,
				() -> new Time("123_AB", "Time A", mascote)
				);

		assertEquals("MASCOTE DO TIME NULO OU EM BRANCO!", excecao.getMessage());
	}

	@Test
	void testAdicionaCampeonatoValido() {
		Time time = new Time("123_AB", "Time A", "Mascote A");
		time.adicionaCampeonato("Campeonato XY");

		String[] campeonatos = time.getCampeonatosParticipando();
		assertEquals(1, campeonatos.length);
		assertEquals("Campeonato XY", campeonatos[0]);
	}

	@Test
	void testAdicionaCampeonatoNulo() {
		Time time = new Time("123_AB", "Time A", "Mascote");

		Exception excecao = assertThrows(NullPointerException.class, () -> {
			time.adicionaCampeonato(null);
		});

		assertEquals("NOME DO CAMPEONATO NULO OU EM BRANCO!", excecao.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testAdicionaCampeonatoVazioOuEmBranco(String nomeCampeonato) {
		Time time = new Time("123_AB", "Time A", "Mascote A");

		Exception excecao = assertThrows(IllegalArgumentException.class,
				() -> time.adicionaCampeonato(nomeCampeonato)
				);

		assertEquals("NOME DO CAMPEONATO NULO OU EM BRANCO!", excecao.getMessage());
	}

	@Test
	void testGetCampeonatosParticipandoVazio() {
		Time time = new Time("123_AB", "Time A", "Mascote A");
		String[] campeonatos = time.getCampeonatosParticipando();

		assertEquals(0, campeonatos.length);
	}

	@Test
	void testGetCampeonatosParticipandoComCampeonatos() {
		Time time = new Time("123_AB", "Time A", "Mascote A");
		time.adicionaCampeonato("Campeonato C");
		time.adicionaCampeonato("Campeonato G");

		String[] campeonatos = time.getCampeonatosParticipando();
		assertEquals(2, campeonatos.length);
		assertArrayEquals(new String[]{"Campeonato C", "Campeonato G"}, campeonatos);
	}

	@Test
	void testEqualsMesmoObjeto() {
		Time time = new Time("123_AB", "Time A", "Mascote A");
		assertTrue(time.equals(time));
	}

	@Test
	void testEqualsObjetosIguais() {
		Time time1 = new Time("123_AB", "Time A", "Mascote A");
		Time time2 = new Time("123_AB", "Time B", "Mascote B");
		assertTrue(time1.equals(time2));
	}

	@Test
	void testEqualsObjetosDiferentes() {
		Time time1 = new Time("123_AB", "Time A", "Mascote A");
		Time time2 = new Time("456_CD", "Time A", "Mascote A");
		assertFalse(time1.equals(time2));
	}

	@Test
	void testEqualsComNull() {
		Time time = new Time("123_AB", "Time A", "Mascote A");
		assertFalse(time.equals(null));
	}

	@Test
	void testEqualsComOutraClasse() {
		Time time = new Time("123_AB", "Time A", "Mascote A");
		assertFalse(time.equals("Não sou um Time"));
	}

	@Test
	void testToString() {
		Time time = new Time("123_AB", "Time A", "Mascote A");
		String resultadoEsperado = "[123_AB] Time A / Mascote A";
		assertEquals(resultadoEsperado, time.toString());
	}
}
