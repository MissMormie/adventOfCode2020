package days2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day3_BinaryDiagnosticTest {
	@Test
	void runB() {
		String input;
		int answer;

		input = "00100\n" +
				"11110\n" +
				"10110\n" +
				"10111\n" +
				"10101\n" +
				"01111\n" +
				"00111\n" +
				"11100\n" +
				"10000\n" +
				"11001\n" +
				"00010\n" +
				"01010";
		answer = 230;
		assertEquals(answer, Day3_BinaryDiagnostic.runB(input), "input = " + input + " answer: " + answer);
	}
}