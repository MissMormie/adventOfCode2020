package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day22_CrabCombatTest {
	@Test
	void runA() {
		int answer;

		String inputPlayer1 = "9\n" +
				"2\n" +
				"6\n" +
				"3\n" +
				"1";
		String inputPlayer2 = "5\n" +
				"8\n" +
				"4\n" +
				"7\n" +
				"10";
		answer = 306;
		assertEquals(answer, Day22_CrabCombat.runA(inputPlayer1, inputPlayer2), "input = " + inputPlayer1 + " answer: " + answer);

	}


	@Test
	void runB() {
		int answer;

		String inputPlayer1 = "9\n" +
				"2\n" +
				"6\n" +
				"3\n" +
				"1";
		String inputPlayer2 = "5\n" +
				"8\n" +
				"4\n" +
				"7\n" +
				"10";
		answer = 291;
		assertEquals(answer, Day22_CrabCombat.runB(inputPlayer1, inputPlayer2), "input = " + inputPlayer1 + " answer: " + answer);

	}
}