package days2021;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13_TransparentOrigamiTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "6,10\n" +
				"0,14\n" +
				"9,10\n" +
				"0,3\n" +
				"10,4\n" +
				"4,11\n" +
				"6,0\n" +
				"6,12\n" +
				"4,1\n" +
				"0,13\n" +
				"10,12\n" +
				"3,4\n" +
				"3,0\n" +
				"8,4\n" +
				"1,10\n" +
				"2,14\n" +
				"8,10\n" +
				"9,0\n" +
				"\n" +
				"fold along y=7\n" +
				"fold along x=5";
		answer = 17;
		assertEquals(answer, Day13_TransparentOrigami.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}
//
//
//	@Test
//	void runB() {
//		String input;
//		int answer;
//
//		input = "";
//		answer = 0;
//		assertEquals(answer, Day0.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
//	}
}
