package days2021;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day15_ChitonTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "199\n" +
				"199\n" +
				"111";
		answer = 4;
		assertEquals(answer, Day15_Chiton.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

		input = "1163751742\n" +
				"1381373672\n" +
				"2136511328\n" +
				"3694931569\n" +
				"7463417111\n" +
				"1319128137\n" +
				"1359912421\n" +
				"3125421639\n" +
				"1293138521\n" +
				"2311944581";
		answer = 40;
		assertEquals(answer, Day15_Chiton.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);


	}


	@Test
	void runB() {
		String input;
		int answer;
		input = "1163751742\n" +
				"1381373672\n" +
				"2136511328\n" +
				"3694931569\n" +
				"7463417111\n" +
				"1319128137\n" +
				"1359912421\n" +
				"3125421639\n" +
				"1293138521\n" +
				"2311944581";
		answer = 315;
		assertEquals(answer, Day15_Chiton.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}