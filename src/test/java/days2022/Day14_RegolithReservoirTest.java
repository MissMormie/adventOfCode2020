package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day14_RegolithReservoirTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "498,4 -> 498,6 -> 496,6\n" +
				"503,4 -> 502,4 -> 502,9 -> 494,9";
		answer = 24;
		assertEquals(answer, Day14_RegolithReservoir.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "498,4 -> 498,6 -> 496,6\n" +
				"503,4 -> 502,4 -> 502,9 -> 494,9";
		answer = 93;
		assertEquals(answer, Day14_RegolithReservoir.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}