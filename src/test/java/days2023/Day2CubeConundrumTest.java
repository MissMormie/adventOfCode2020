package days2023;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day2CubeConundrumTest {
  	@Test
  	void runA() {
  		String input;
  		int answer;

  		input = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
              "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
              "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
              "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
              "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";
  		answer = 8;
  		assertEquals(answer, Day2CubeConundrum.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

  	}


  	@Test
  	void runB() {
  		String input;
  		int answer;

			input = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
							"Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
							"Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
							"Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
							"Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";
  		answer = 2286;
  		assertEquals(answer, Day2CubeConundrum.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
  	}
}