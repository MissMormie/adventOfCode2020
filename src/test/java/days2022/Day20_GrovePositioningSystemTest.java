package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day20_GrovePositioningSystemTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "1\n" +
				"2\n" +
				"-3\n" +
				"3\n" +
				"-2\n" +
				"0\n" +
				"4";
		answer = 3;
		assertEquals(answer, Day20_GrovePositioningSystem.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "1\n" +
				"2\n" +
				"-3\n" +
				"3\n" +
				"-2\n" +
				"0\n" +
				"4";
		answer = 1623178306;
		assertEquals(answer, Day20_GrovePositioningSystem.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}