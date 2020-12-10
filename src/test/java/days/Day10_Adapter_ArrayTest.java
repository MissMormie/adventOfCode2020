package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day10_Adapter_ArrayTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "16\n" +
				"10\n" +
				"15\n" +
				"5\n" +
				"1\n" +
				"11\n" +
				"7\n" +
				"19\n" +
				"6\n" +
				"12\n" +
				"4";
		answer = 35;
		assertEquals(answer, Day10_Adapter_Array.runA(input), "input = " + input + " answer: " + answer);

		input = "28\n" +
				"33\n" +
				"18\n" +
				"42\n" +
				"31\n" +
				"14\n" +
				"46\n" +
				"20\n" +
				"48\n" +
				"47\n" +
				"24\n" +
				"23\n" +
				"49\n" +
				"45\n" +
				"19\n" +
				"38\n" +
				"39\n" +
				"11\n" +
				"1\n" +
				"32\n" +
				"25\n" +
				"35\n" +
				"8\n" +
				"17\n" +
				"7\n" +
				"9\n" +
				"4\n" +
				"2\n" +
				"34\n" +
				"10\n" +
				"3";
		answer = 220;
		assertEquals(answer, Day10_Adapter_Array.runA(input), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "28\n" +
				"33\n" +
				"18\n" +
				"42\n" +
				"31\n" +
				"14\n" +
				"46\n" +
				"20\n" +
				"48\n" +
				"47\n" +
				"24\n" +
				"23\n" +
				"49\n" +
				"45\n" +
				"19\n" +
				"38\n" +
				"39\n" +
				"11\n" +
				"1\n" +
				"32\n" +
				"25\n" +
				"35\n" +
				"8\n" +
				"17\n" +
				"7\n" +
				"9\n" +
				"4\n" +
				"2\n" +
				"34\n" +
				"10\n" +
				"3";
		answer = 19208;
		assertEquals(answer, Day10_Adapter_Array.runA(input), "input = " + input + " answer: " + answer);
	}
}