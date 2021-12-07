package days2021;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day7_TheTreacheryOfWhalesTest {

	@Test
	void runB() {
		String input;
		int answer;

		input = "16,1,2,0,4,2,7,1,2,14";
		answer = 168;
		assertEquals(answer, Day7_TheTreacheryOfWhales.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}