package days2021;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day12_PassagePathingTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "start-A\n" +
				"start-b\n" +
				"A-c\n" +
				"A-b\n" +
				"b-d\n" +
				"A-end\n" +
				"b-end";
		answer = 10;
		assertEquals(answer, Day12_PassagePathing.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}
//
//
//	@Test
//	void runB() {
//		String input;
//		int answer;
//
//		input = "";
//		answer = 0;
//		assertEquals(answer, Day0.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
//	}
}