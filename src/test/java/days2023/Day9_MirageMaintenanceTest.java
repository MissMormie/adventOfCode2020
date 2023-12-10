package days2023;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day9_MirageMaintenanceTest {
  	@Test
  	void runA() {
  		String input;
  		int answer;

  		input = "0 3 6 9 12 15\n" +
              "1 3 6 10 15 21\n" +
              "10 13 16 21 30 45";
  		answer = 114;
//  		assertEquals(answer, Day9_MirageMaintenance.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

      input = "-9 -9 -4 1 -4 -34 -109 -254 -499 -879 -1434 -2209 -3254 -4624 -6379 -8584 -11309 -14629 -18624 -23379 -28984";
      answer = 0;
      assertEquals(answer, Day9_MirageMaintenance.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

  	}


  	@Test
  	void runB() {
  		String input;
  		int answer;

  		input = "";
  		answer = 0;
  		assertEquals(answer, Day9_MirageMaintenance.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
  	}

}