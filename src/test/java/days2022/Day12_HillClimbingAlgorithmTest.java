package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day12_HillClimbingAlgorithmTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "Sabqponm\n" +
				"abcryxxl\n" +
				"accszExk\n" +
				"acctuvwj\n" +
				"abdefghi";
		answer = 31;
		assertEquals(answer, Day12_HillClimbingAlgorithm.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "Sabqponm\n" +
				"abcryxxl\n" +
				"accszExk\n" +
				"acctuvwj\n" +
				"abdefghi";
		answer = 29;
		assertEquals(answer, Day12_HillClimbingAlgorithm.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}