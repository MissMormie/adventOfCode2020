package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day9_RopeBridgeTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "R 4\n" +
				"U 4\n" +
				"L 3\n" +
				"D 1\n" +
				"R 4\n" +
				"D 1\n" +
				"L 5\n" +
				"R 2";
		answer = 13;
		assertEquals(answer, Day9_RopeBridge.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}

	@Test
	void runB() {
		String input;
		int answer;

		input = "R 4\n" +
				"U 4\n" +
				"L 3\n" +
				"D 1\n" +
				"R 4\n" +
				"D 1\n" +
				"L 5\n" +
				"R 2";
		answer = 1;
//		assertEquals(answer, Day9_RopeBridge.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

		input = "R 5\n" +
				"U 8\n" +
				"L 8\n" +
				"D 3\n" +
				"R 17\n" +
				"D 10\n" +
				"L 25\n" +
				"U 20";
		answer = 36;
		assertEquals(answer, Day9_RopeBridge.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}
}