package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day3_CalorieCountingTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "1000\n" +
				"2000\n" +
				"3000\n" +
				"\n" +
				"4000\n" +
				"\n" +
				"5000\n" +
				"6000\n" +
				"\n" +
				"7000\n" +
				"8000\n" +
				"9000\n" +
				"\n" +
				"10000";
		answer = 24000;
		assertEquals(answer, Day1_CalorieCounting.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "1000\n" +
				"2000\n" +
				"3000\n" +
				"\n" +
				"4000\n" +
				"\n" +
				"5000\n" +
				"6000\n" +
				"\n" +
				"7000\n" +
				"8000\n" +
				"9000\n" +
				"\n" +
				"10000";
		answer = 45000;
		assertEquals(answer, Day1_CalorieCounting.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}