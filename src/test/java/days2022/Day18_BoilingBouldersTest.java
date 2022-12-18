package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day18_BoilingBouldersTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "2,2,2\n" +
				"1,2,2\n" +
				"3,2,2\n" +
				"2,1,2\n" +
				"2,3,2\n" +
				"2,2,1\n" +
				"2,2,3\n" +
				"2,2,4\n" +
				"2,2,6\n" +
				"1,2,5\n" +
				"3,2,5\n" +
				"2,1,5\n" +
				"2,3,5";
		answer = 64;
		assertEquals(answer, Day18_BoilingBoulders.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;


		input = "2,2,2\n" +
				"1,2,2\n" +
				"3,2,2\n" +
				"2,1,2\n" +
				"2,3,2\n" +
				"2,2,1\n" +
				"2,2,3\n" +
				"2,2,4\n" +
				"2,2,6\n" +
				"1,2,5\n" +
				"3,2,5\n" +
				"2,1,5\n" +
				"2,3,5";
		answer = 58;
		assertEquals(answer, Day18_BoilingBoulders.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}