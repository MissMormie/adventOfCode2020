package days2021;

import days2020.Day0;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day21_DiracDiceTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "Player 1 starting position: 4\n" +
				"Player 2 starting position: 8";
		answer = 739785;
		assertEquals(answer, Day21_DiracDice.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		long answer;

//		input = "Player 1 starting position: 4\n" +
//				"Player 2 starting position: 1";
//		answer = 0L;
//		assertEquals(answer, Day21_DiracDice.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);


		input = "Player 1 starting position: 4\n" +
				"Player 2 starting position: 8";
		answer = 444356092776315L;
		assertEquals(answer, Day21_DiracDice.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}