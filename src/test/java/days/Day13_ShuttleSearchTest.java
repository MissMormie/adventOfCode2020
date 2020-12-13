package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day13_ShuttleSearchTest {
	@Test
	void runA() {
		String input;
		int answer;
		int timestamp;

		input = "7,13,x,x,59,x,31,19";
		timestamp = 939;
		answer = 295;
		assertEquals(answer, Day13_ShuttleSearch.runA(input, timestamp), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		long answer;

		input = "7,13,x,x,59,x,31,19";
		answer = 1068781;
		assertEquals(answer, Day13_ShuttleSearch.runB(input), "input = " + input + " answer: " + answer);

		input = "17,x,13,19";
		answer = 3417;
		assertEquals(answer, Day13_ShuttleSearch.runB(input), "input = " + input + " answer: " + answer);

		input = "67,7,59,61";
		answer = 754018;
		assertEquals(answer, Day13_ShuttleSearch.runB(input), "input = " + input + " answer: " + answer);

		input = "67,x,7,59,61";
		answer = 779210;
		assertEquals(answer, Day13_ShuttleSearch.runB(input), "input = " + input + " answer: " + answer);

		input = "1789,37,47,1889";
		answer = 1202161486;
		assertEquals(answer, Day13_ShuttleSearch.runB(input), "input = " + input + " answer: " + answer);
	}
}