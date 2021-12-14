package days2021;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day14_ExtendedPolymerizationTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "NNCB\n" +
				"\n" +
				"CH -> B\n" +
				"HH -> N\n" +
				"CB -> H\n" +
				"NH -> C\n" +
				"HB -> C\n" +
				"HC -> B\n" +
				"HN -> C\n" +
				"NN -> C\n" +
				"BH -> H\n" +
				"NC -> B\n" +
				"NB -> B\n" +
				"BN -> B\n" +
				"BB -> N\n" +
				"BC -> B\n" +
				"CC -> N\n" +
				"CN -> C";
		answer = 1588;
		assertEquals(answer, Day14_ExtendedPolymerization.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}
	@Test
	void runB() {
		String input;
		BigInteger answer;

		input = "NNCB\n" +
				"\n" +
				"CH -> B\n" +
				"HH -> N\n" +
				"CB -> H\n" +
				"NH -> C\n" +
				"HB -> C\n" +
				"HC -> B\n" +
				"HN -> C\n" +
				"NN -> C\n" +
				"BH -> H\n" +
				"NC -> B\n" +
				"NB -> B\n" +
				"BN -> B\n" +
				"BB -> N\n" +
				"BC -> B\n" +
				"CC -> N\n" +
				"CN -> C";
		answer = new BigInteger("2188189693529");
		assertEquals(answer, Day14_ExtendedPolymerization.runB(Arrays.stream(input.split("\n")),40), "input = " + input + " answer: " + answer);

	}
}