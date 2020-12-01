package days;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {
	@Test
	void runA() {
		String input;
		int answer;

		input = "12";
		answer = 2;
		assertEquals(answer, Day1.runA(input), "input = " + input + " answer: " + answer);

		input = "14";
		answer = 2;
		assertEquals(answer, Day1.runA(input), "input = " + input + " answer: " + answer);

		input = "1969";
		answer = 654;
		assertEquals(answer, Day1.runA(input), "input = " + input + " answer: " + answer);

		input = "100756";
		answer = 33583;
		assertEquals(answer, Day1.runA(input), "input = " + input + " answer: " + answer);

		input = "12\n" +
				"14\n" +
				"1969\n" +
				"100756";
		answer = 2 +2 +654 + 33583;
		assertEquals(answer, Day1.runA(input), "input = " + input + " answer: " + answer);

	}

	@Test
	void runB() {
		String input;
		int answer;

		input = "12";
		answer = 2;
		assertEquals(answer, Day1.runB(input), "input = " + input + " answer: " + answer);

		input = "1969";
		answer = 966;
		assertEquals(answer, Day1.runB(input), "input = " + input + " answer: " + answer);

		input = "100756";
		answer = 50346;
		assertEquals(answer, Day1.runB(input), "input = " + input + " answer: " + answer);

	}
}