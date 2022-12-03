package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day3_RuckSackReorganizationTest {
	@Test
	void runA() {
		String input;
		int answer;

		input = "vJrwpWtwJgWrhcsFMMfFFhFp\n" +
				"jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\n" +
				"PmmdzqPrVvPwwTWBwg\n" +
				"wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\n" +
				"ttgJtRGJQctTZtZT\n" +
				"CrZsJsPPZsGzwwsLwLmpwMDw";

		answer = 157;
		assertEquals(answer, Day3_RuckSackReorganization.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}

	@Test
	void runB() {
		String input;
		int answer;

		input = "vJrwpWtwJgWrhcsFMMfFFhFp\n" +
				"jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\n" +
				"PmmdzqPrVvPwwTWBwg\n" +
				"wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\n" +
				"ttgJtRGJQctTZtZT\n" +
				"CrZsJsPPZsGzwwsLwLmpwMDw";
		answer = 70;
		assertEquals(answer, Day3_RuckSackReorganization.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}