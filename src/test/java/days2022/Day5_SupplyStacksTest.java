package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day5_SupplyStacksTest {

	@Test
	void runA() {
		String input;
		String answer;

		input = "    [D]    \n" +
				"[N] [C]    \n" +
				"[Z] [M] [P]\n" +
				" 1   2   3 \n" +
				"\n" +
				"move 1 from 2 to 1\n" +
				"move 3 from 1 to 3\n" +
				"move 2 from 2 to 1\n" +
				"move 1 from 1 to 2";
		answer = "CMZ";
		assertEquals(answer, Day5_SupplyStacks.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		String answer;

		input = "    [D]    \n" +
				"[N] [C]    \n" +
				"[Z] [M] [P]\n" +
				" 1   2   3 \n" +
				"\n" +
				"move 1 from 2 to 1\n" +
				"move 3 from 1 to 3\n" +
				"move 2 from 2 to 1\n" +
				"move 1 from 1 to 2";
		answer = "MCD";
		assertEquals(answer, Day5_SupplyStacks.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}