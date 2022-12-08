package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day8_TreetopTreeHouseTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "30373\n" +
				"25512\n" +
				"65332\n" +
				"33549\n" +
				"35390";
		answer = 21;
		assertEquals(answer, Day8_TreetopTreeHouse.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;
		input = "30373\n" +
				"25512\n" +
				"65332\n" +
				"33549\n" +
				"35390";
		answer = 8;
		assertEquals(answer, Day8_TreetopTreeHouse.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}