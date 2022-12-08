package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day6_TuningTroubleTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "bvwbjplbgvbhsrlpgdmjqwftvncz";
		answer = 5;
		assertEquals(answer, Day6_TuningTrouble.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

		input = "nppdvjthqldpwncqszvftbrmjlhg";
		answer = 6;
		assertEquals(answer, Day6_TuningTrouble.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

		input = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
		answer = 10;
		assertEquals(answer, Day6_TuningTrouble.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

		input = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";
		answer = 11;
		assertEquals(answer, Day6_TuningTrouble.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
		answer = 19;
		assertEquals(answer, Day6_TuningTrouble.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

		input = "bvwbjplbgvbhsrlpgdmjqwftvncz";
		answer = 23;
		assertEquals(answer, Day6_TuningTrouble.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

		input = "nppdvjthqldpwncqszvftbrmjlhg";
		answer = 23;
		assertEquals(answer, Day6_TuningTrouble.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

		input = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
		answer = 29;
		assertEquals(answer, Day6_TuningTrouble.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

		input = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";
		answer = 26;
		assertEquals(answer, Day6_TuningTrouble.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}