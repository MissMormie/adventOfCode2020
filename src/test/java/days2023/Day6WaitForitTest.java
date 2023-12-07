package days2023;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day6WaitForItTest {
  	@Test
  	void runA() {
  		String input;
  		int answer;

  		input = "";
  		answer = 4;
  		assertEquals(answer, Day6WaitForit.getNumberOfWaysToBeat(7,9), "input = " + input + " answer: " + answer);

      answer = 8;
      assertEquals(answer, Day6WaitForit.getNumberOfWaysToBeat(15,40), "input = " + input + " answer: " + answer);

      answer = 9;
      assertEquals(answer, Day6WaitForit.getNumberOfWaysToBeat(30,200), "input = " + input + " answer: " + answer);

    }


  	@Test
  	void runB() {
  		String input;
  		int answer;

  		input = "";
  		answer = 71503;
//  		assertEquals(answer, Day6WaitForit.getQuadraticDifference(1, 71530, 940200), "input = " + input + " answer: " + answer);
			assertEquals(answer, Day6WaitForit.getQuadratic(new BigDecimal("1"), new BigDecimal("71530"), new BigDecimal("940200")), "input = " + input + " answer: " + answer);

  	}
}