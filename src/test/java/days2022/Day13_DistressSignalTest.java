package days2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day13_DistressSignalTest {

	@Test
	void runA() {
		String input;
		int answer;

		input = "[1,1,3,1,1]\n" +
				"[1,1,5,1,1]\n" +
				"\n" +
				"[[1],[2,3,4]]\n" +
				"[[1],4]\n" +
				"\n" +
				"[9]\n" +
				"[[8,7,6]]\n" +
				"\n" +
				"[[4,4],4,4]\n" +
				"[[4,4],4,4,4]\n" +
				"\n" +
				"[7,7,7,7]\n" +
				"[7,7,7]\n" +
				"\n" +
				"[]\n" +
				"[3]\n" +
				"\n" +
				"[[[]]]\n" +
				"[[]]\n" +
				"\n" +
				"[1,[2,[3,[4,[5,6,7]]]],8,9]\n" +
				"[1,[2,[3,[4,[5,6,0]]]],8,9]\n";

				answer = 13;
//		assertEquals(answer, Day13_DistressSignal.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);


		// If both values are integers, the lower integer should come first.
		// If the left integer is lower than the right integer, the inputs are in the right order.
		input = "[1]\n" +
				"[2]";

		answer = 1;
//		assertEquals(answer, Day13_DistressSignal.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

		// If the left integer is higher than the right integer, the inputs are not in the right order.
		input = "[2]\n" +
				"[1]";
		answer = 0;
//		assertEquals(answer, Day13_DistressSignal.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

		// Otherwise, the inputs are the same integer; continue checking the next part of the input.
		input = "[2,]\n" +
				"[2]";
		answer = 0;
//		assertEquals(answer, Day13_DistressSignal.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);


		input ="[[[],[[1,1],9]],[],[[10,[1,6,3,8,6]],8,[2],10]]\n" +
				"[[[6,[2],6,5,[4,10,5,8,9]]" +
				 ",[3,[10,4,0],1]" +
				"]," +
				"[7,[[2,10,7,9,3],[10,6],2,6],[7,[1],[3,5],[3,8,5,8,2],[]],2],[9],[[9,[0],[0,6,3,7],2,6],[1,1],4,2],[]]\n";


		input ="[[[],[[1,1],9]],[],[[10,[1,6,3,8,6]],8,[2],10]]\n" +
				"[[[6,[2],6,5,[4,10,5,8,9]]" +
				",[3,[10,4,0],1]" +
				"]," +
				"[7,[[2,10,7,9,3],[10,6],2,6],[7,[1],[3,5],[3,8,5,8,2],[]],2],[9],[[9,[0],[0,6,3,7],2,6],[1,1],4,2],[]]\n";

//		input = "[[1],1]\n" +
//				"[2,2]";

		input = "[" +
					"[]," +
					"[" +
						"5," +
						"[" +
							"[" +
								"9,2,5,1,8" +
							"]," +
							"[]," +
							"9," +
							"[" +
								"7" +
							"]" +
						"]" +
					"]" +
				"]\n" +


				"[" +
					"[" +
						"[" +
							"9,10," +
							"[3,10,10,8,2]" +
							",2" +
						"]" +
						",8," +
						"[" +
							"[3]" +
							",8," +
							"[2,2,1,3]" +
						"]" +
					"]" +
				"]";

		answer = 1;
		assertEquals(answer, Day13_DistressSignal.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}


	@Test
	void runB() {
		String input;
		int answer;

		input = "";
		answer = 0;
		assertEquals(answer, Day13_DistressSignal.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
	}
}