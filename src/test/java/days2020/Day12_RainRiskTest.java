package days2020;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day12_RainRiskTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "F10\n" +
				"N3\n" +
				"F7\n" +
				"R90\n" +
				"F11";
		answer = 25;
		assertEquals(answer, Day12_RainRisk.runA(input), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "F10\n" +
				"N3\n" +
				"F7\n" +
				"R90\n" +
				"F11";
		answer = 286;
		assertEquals(answer, Day12_RainRisk.runB(input), "input = " + input + " answer: " + answer);

		input = "F10\n" +
				"N3\n" +
				"F7\n" +
				"R90\n" +
				"L90\n" +
				"R90\n" +
				"F11";
		answer = 286;
		assertEquals(answer, Day12_RainRisk.runB(input), "input = " + input + " answer: " + answer);

	}
}