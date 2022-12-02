package days2021;

import helpers.InputProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day24_ArithmeticLogicUnit {
	public static int day = 24;
	public static int year = 2021;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static String runA(Stream<String> input) {
		List<String> collect = input.collect(Collectors.toList());
		List<List<String>> instructionGroups = new ArrayList<>();
		List<String> group = new ArrayList<>();
		for(String line : collect) {
			if (line.contains("inp")) {
				group = new ArrayList<>();
				instructionGroups.add(group);
			}
			group.add(line);
		}

		return getMonadNumber(0, instructionGroups, getEmptyVariableMap(), "");

	}

	public static String getMonadNumber(int index, List<List<String>> instructionGroups, Map<String, Long> variableMap, String monad ) {
		if (index == 14) {
			return monad;
		}
		for (int i = 9; i > 0; i--) {
			Map<String, Long> map = cloneMap(variableMap);
			try {
				for(String instruction : instructionGroups.get(index)) {
					runInstruction(instruction, i, map);
				}
				return getMonadNumber(index+1, instructionGroups, map, monad + i);

			} catch (Exception e) {
				int b = 0;
				//
			}
		}
		throw new RuntimeException("no valid options");
	}

	public static Map<String, Long> cloneMap(Map<String, Long> variableMap) {
		HashMap<String, Long> clone = new HashMap<>();
		clone.putAll(variableMap);
		return clone;
	}

	public static void runInstruction(String line, long input, Map<String, Long> variableMap) {
		String[] s = line.split(" ");

		long num1 = variableMap.get(s[1]);
		long num2 = 0L;
		if(s.length >2 ) {
			if (s[2].equals("x") || s[2].equals("y") || s[2].equals("z") || s[2].equals("w")) {
				num2 = variableMap.get(s[2]);
			} else {
				num2 = Integer.parseInt(s[2]);
			}
		}
		switch(s[0]){
			case "inp": variableMap.put(s[1], input); break;
			case "add": variableMap.put(s[1], num1 + num2); break;
			case "mul": variableMap.put(s[1], num1 * num2); break;
			case "div":
				if(num2 == 0) {
					throw new RuntimeException("not allowed to div by 0");
				}
				variableMap.put(s[1], num1 / num2); break;
			case "mod":
				if(num1 < 0 || num2 <= 0) {
					throw new RuntimeException("mod exception");
				}

			 variableMap.put(s[1], num1 % num2); break;
			case "eql":
				long i = num1 == num2 ? 1 : 0;
				variableMap.put(s[1],i);
				break;
		}

	}

	public static Map<String, Long> getEmptyVariableMap() {
		Map<String, Long> variableMap = new HashMap<>();
		variableMap.put("x", 0L);
		variableMap.put("y", 0L);
		variableMap.put("z", 0L);
		variableMap.put("w", 0L);
		return variableMap;
	}


	public static int runB(Stream<String> input) {
		return 0;
	}
}
