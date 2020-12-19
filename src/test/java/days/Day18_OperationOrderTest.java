package days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day18_OperationOrderTest {
	@Test
	void runA() {
		String input;
		int answer;

		input = "1 + (2 * 3) + (4 * (5 + 6))";
		answer = 51;
		assertEquals(answer, Day18_OperationOrder.doSum(input), "input = " + input + " answer: " + answer);

		input = "1 + 2 * 3 + 4 * 5 + 6";
		answer = 71;
		assertEquals(answer, Day18_OperationOrder.doSum(input), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "2 * 3 + (4 * 5)";
		answer = 46;
		assertEquals(answer, Day18_OperationOrder.doSumB(input), "input = " + input + " answer: " + answer);


		input = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2";
		answer = 23340;
		assertEquals(answer, Day18_OperationOrder.doSumB(input), "input = " + input + " answer: " + answer);

		input = "5 + (2 + 3)";
		answer = 10;
		assertEquals(answer, Day18_OperationOrder.doSumB(input), "input = " + input + " answer: " + answer);

		input = "5 + (8 * 3 + 9 + 3 * 4 * 3)";
		answer = 1445;
		assertEquals(answer, Day18_OperationOrder.doSumB(input), "input = " + input + " answer: " + answer);

		input = "1 + (2 * 3) + (4 * (5 + 6))";
		answer = 51;
		assertEquals(answer, Day18_OperationOrder.doSumB(input), "input = " + input + " answer: " + answer);


		input = "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))";
		answer = 669060;
		assertEquals(answer, Day18_OperationOrder.doSumB(input), "input = " + input + " answer: " + answer);
	}
}