package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day25_ComboBreakerTest {
	@Test
	void runA() {
		int input;
		int answer;

		input = 5764801;
		answer = 8;
		assertEquals(answer, Day25_ComboBreaker.findLoopSize(input), "input = " + input + " answer: " + answer);

		input = 17807724;
		answer = 11;
		assertEquals(answer, Day25_ComboBreaker.findLoopSize(input), "input = " + input + " answer: " + answer);

		answer = 14897079;
		assertEquals(answer, Day25_ComboBreaker.runA(5764801, 17807724), "input = " + input + " answer: " + answer);


//		assertEquals(answer, Day25_ComboBreaker.transform(8, 17807724), "input = " + input + " answer: " + answer);

	}

}