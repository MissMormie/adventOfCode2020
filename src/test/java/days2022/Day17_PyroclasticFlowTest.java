package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day17_PyroclasticFlowTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>";
		answer = 3068;
		assertEquals(answer, Day17_PyroclasticFlow.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		long answer;

		input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>";
		answer = 1514285714288L;
		assertEquals(answer, Day17_PyroclasticFlow.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}