package days2021;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day11_DumboOctopusTest {

	@Test
	void runA() {
		String input;
		int answer;
//
//		input = "11111\n" +
//				"19991\n" +
//				"19191\n" +
//				"19991\n" +
//				"11111";
//		answer = 9;
//		assertEquals(answer, Day11_DumboOctopus.runA(Arrays.stream(input.split("\n")),3), "input = " + input + " answer: " + answer);


		input = "5483143223\n" +
				"2745854711\n" +
				"5264556173\n" +
				"6141336146\n" +
				"6357385478\n" +
				"4167524645\n" +
				"2176841721\n" +
				"6882881134\n" +
				"4846848554\n" +
				"5283751526";
		answer = 1656;
		assertEquals(answer, Day11_DumboOctopus.runA(Arrays.stream(input.split("\n")), 100), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "5483143223\n" +
				"2745854711\n" +
				"5264556173\n" +
				"6141336146\n" +
				"6357385478\n" +
				"4167524645\n" +
				"2176841721\n" +
				"6882881134\n" +
				"4846848554\n" +
				"5283751526";
		answer = 195;
		assertEquals(answer, Day11_DumboOctopus.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}