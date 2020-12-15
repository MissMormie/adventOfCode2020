package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class Day15_RambunctiousRecitationTest {
	@Test
	void runA() {
		String input;
		int answer;

		input = "0,3,6";
		answer = 436;
		assertEquals(answer, Day15_RambunctiousRecitation.runA(input), "input = " + input + " answer: " + answer);
	}

	@Test
	void runB() {
		String input;
		int answer;

		input = "mask = 000000000000000000000000000000X1001X\n" +
				"mem[42] = 100\n" +
				"mask = 00000000000000000000000000000000X0XX\n" +
				"mem[26] = 1";
		answer = 208;
		assertEquals(answer, Day15_RambunctiousRecitation.runB(input), "input = " + input + " answer: " + answer);

	}

}