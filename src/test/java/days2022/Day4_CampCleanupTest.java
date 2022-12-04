package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day4_CampCleanupTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "2-4,6-8\n" +
				"2-3,4-5\n" +
				"5-7,7-9\n" +
				"2-8,3-7\n" +
				"6-6,4-6\n" +
				"2-6,4-8";
		answer = 2;
		assertEquals(answer, Day4_CampCleanup.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}

	@Test
	void runB() {
		String input;
		int answer;

		input = "2-4,6-8\n" +
				"2-3,4-5\n" +
				"5-7,7-9\n" +
				"2-8,3-7\n" +
				"6-6,4-6\n" +
				"2-6,4-8\n";
		answer = 4;
		assertEquals(answer, Day4_CampCleanup.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}