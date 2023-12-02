package days2023;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day1TrebuchetTest {
  	@Test
  	void runA() {
  		String input;
  		int answer;

  		input = "";
  		answer = 0;
  		assertEquals(answer, Day1Trebuchet.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

  	}


  	@Test
  	void runB() {
  		String input;
  		int answer;

  		input = "two1nine\n" +
              "eightwothree\n" +
              "abcone2threexyz\n" +
              "xtwone3four\n" +
              "4nineeightseven2\n" +
              "zoneight234\n" +
              "7pqrstsixteen";
  		answer = 281;
  		assertEquals(answer, Day1Trebuchet.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
  	}
}