package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day5_BinaryBoardingTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "FBFBBFFRLR";
		answer = 357;
		assertEquals(answer, Day5_BinaryBoarding.runA(input), "input = " + input + " answer: " + answer);

		input = "BFFFBBFRRR";
		answer = 567;
		assertEquals(answer, Day5_BinaryBoarding.runA(input), "input = " + input + " answer: " + answer);

		input = "FFFBBBFRRR";
		answer = 119;
		assertEquals(answer, Day5_BinaryBoarding.runA(input), "input = " + input + " answer: " + answer);

		input = "BBFFBBFRLL";
		answer = 820;
		assertEquals(answer, Day5_BinaryBoarding.runA(input), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "";
		answer = 0;
		assertEquals(answer, Day0.runB(input), "input = " + input + " answer: " + answer);
	}
}