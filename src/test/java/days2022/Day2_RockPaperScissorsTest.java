package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static days2022.Day2_RockPaperScissors.Hand.ROCK;
import static org.junit.jupiter.api.Assertions.*;

class Day2_RockPaperScissorsTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "A Y\n" +
				"B X\n" +
				"C Z";
		answer = 15;
		assertEquals(answer, Day2_RockPaperScissors.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "A Y\n" +
				"B X\n" +
				"C Z";
		answer = 12;
		assertEquals(answer, Day2_RockPaperScissors.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}

	@Test
	void runHand() {
		assertEquals(ROCK, Day2_RockPaperScissors.Hand.SCISSOR.getWinner());
	}
}