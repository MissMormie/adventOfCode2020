package days2023;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day8HauntedWastelandTest {
  	@Test
  	void runA() {
  		String input;
  		int answer;

  		input = "AAA = (BBB, CCC)\n" +
              "BBB = (DDD, EEE)\n" +
              "CCC = (ZZZ, GGG)\n" +
              "DDD = (DDD, DDD)\n" +
              "EEE = (EEE, EEE)\n" +
              "GGG = (GGG, GGG)\n" +
              "ZZZ = (ZZZ, ZZZ)";
  		answer = 2;
//  		assertEquals(answer, Day8HauntedWasteland.runA(Arrays.stream(input.split("\n")), "RL"), "input = " + input + " answer: " + answer);

			input = "AAA = (BBB, BBB)\n" +
							"BBB = (AAA, ZZZ)\n" +
							"ZZZ = (ZZZ, ZZZ)";
			answer = 6;
			assertEquals(answer, Day8HauntedWasteland.runA(Arrays.stream(input.split("\n")), "LLR"), "input = " + input + " answer: " + answer);
		}


  	@Test
  	void runB() {
  		String input;
  		int answer;

  		input = "";
  		answer = 0;
  		assertEquals(answer, Day8HauntedWasteland.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
  	}
}