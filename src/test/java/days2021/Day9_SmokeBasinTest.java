package days2021;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day9_SmokeBasinTest {
	@Test
	void runA() {
		String input;
		int answer;

		input = "2199943210\n" +
				"3987894921\n" +
				"9856789892\n" +
				"8767896789\n" +
				"9899965678";
		answer = 15;
		assertEquals(answer, Day9_SmokeBasin.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

		input = "555" +
				"555" +
				"555";
		answer = 0;
		assertEquals(answer, Day9_SmokeBasin.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

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