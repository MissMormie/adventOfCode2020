package days2021;

import helpers.InputProvider;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static days2021.Day24_ArithmeticLogicUnit.*;
import static org.junit.jupiter.api.Assertions.*;

class Day24_ArithmeticLogicUnitTest {

	@Test
	void runA() throws IOException {
		String input;
		int answer;

		input = "inp z\n" +
				"inp x\n" +
				"mul z 3\n" +
				"eql z x";


		Map<String, Long> emptyVariableMap = getEmptyVariableMap();
		InputProvider.getInputFor(2021,24).forEach(line -> runInstruction(line, 9l, emptyVariableMap));
//
//		List<String> collect = Arrays.stream(input.split("\n")).collect(Collectors.toList());
//		Map<String, Long> emptyVariableMap = getEmptyVariableMap();
//		runInstruction(collect.get(0),1, emptyVariableMap);
//		runInstruction(collect.get(1),3, emptyVariableMap);
//		runInstruction(collect.get(2),8, emptyVariableMap);
//		runInstruction(collect.get(3),8, emptyVariableMap);
		int i =0;
//		answer = 0;
//		assertEquals(answer, run(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

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