package MrBet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ApostaTest {

	@ParameterizedTest
	@MethodSource("entradasValidas")
	void testConstructorValidInputs(String idTime, double valor, int colocacao, String nomeCampeonato) {
		Aposta aposta = new Aposta(idTime, valor, colocacao, nomeCampeonato);

		assertEquals(idTime, aposta.getIdTime());
		assertEquals(valor, aposta.getValor(), 0.001);
		assertEquals(colocacao, aposta.getColocacao());
		assertEquals(nomeCampeonato, aposta.getNomeCampeonato());
	}

	private static Stream<Arguments> entradasValidas() {
		return Stream.of(
				Arguments.of("TIME1", 100.0, 1, "Campeonato A"),
				Arguments.of("TIME2", 200.0, 2, "Campeonato B"),
				Arguments.of("TIME3", 300.0, 3, "Campeonato C")
				);
	}

	@Test
	void testConstrutorInvalidoNomeNulo() {
		NullPointerException exception = assertThrows(NullPointerException.class, () -> {
			new Aposta(null, 100.0, 1, "Campeonato A");
		});

		assertEquals("CÓDIGO DO TIME NULO OU EM BRANCO!", exception.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testConstrutorCodigoVazio(String codigo) {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Aposta(codigo, 100.0, 1, "Campeonato A");
		});

		assertEquals("CÓDIGO DO TIME NULO OU EM BRANCO!", exception.getMessage());
	}

	@ParameterizedTest
	@ValueSource(doubles = { -100.0, 0.0 })
	void testConstrutorValorInvalido(double valor) {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Aposta("TIME1", valor, 1, "Campeonato A");
		});

		assertEquals("VALOR DA APOSTA DEVE SER MAIOR QUE ZERO!", exception.getMessage());
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, -1 })
	void testConstructorInvalidColocacao(int colocacao) {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Aposta("TIME1", 100.0, colocacao, "Campeonato A");
		});

		assertEquals("COLOCAÇÃO DEVE SER MAIOR OU IGUAL A 1!", exception.getMessage());
	}

	@Test
	void testNomeCampeonatoInvalidoConstrutorNomeNulo() {
		NullPointerException exception = assertThrows(NullPointerException.class, () -> {
			new Aposta("TIME1", 100.0, 1, null);
		});

		assertEquals("NOME DO CAMPEONATO NULO OU EM BRANCO!", exception.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testNomeCampeonatoInvalidoConstrutorNomeVazio() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			new Aposta("TIME1", 100.0, 1, "");
		});

		assertEquals("NOME DO CAMPEONATO NULO OU EM BRANCO!", exception.getMessage());
	}

	@Test
	void testValidaSeNulo() {
		Exception exception = assertThrows(NullPointerException.class, () -> {
			new Aposta(null, 100.0, 1, "Campeonato A");
		});

		assertEquals("CÓDIGO DO TIME NULO OU EM BRANCO!", exception.getMessage());
	}

	@Test
	void testValidaSeNuloWithBlankString() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Aposta("   ", 100.0, 1, "Campeonato A");
		});

		assertEquals("CÓDIGO DO TIME NULO OU EM BRANCO!", exception.getMessage());
	}
}