package days2023;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day3GearRatiosTest {
  	@Test
  	void runA() {
  		String input;
  		int answer;

  		input = "467..114..\n" +
              "...*......\n" +
              "..35..633.\n" +
              "......#...\n" +
              "617*......\n" +
              ".....+.58.\n" +
              "..592.....\n" +
              "......755.\n" +
              "...$.*....\n" +
              ".664.598..";
  		answer = 4361;
  		assertEquals(answer, Day3GearRatios.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

  	}


  	@Test
  	void runB() {
  		String input;
  		int answer;

			input = "467..114..\n" +
							"...*......\n" +
							"..35..633.\n" +
							"......#...\n" +
							"617*......\n" +
							".....+.58.\n" +
							"..592.....\n" +
							"......755.\n" +
							"...$.*....\n" +
							".664.598..";
			answer = 467835;
  		assertEquals(answer, Day3GearRatios.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
  	}
}