package days2022;

import helpers.InputProvider;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day5_SupplyStacks {
	public static int day = 5;

	public static int year = 2022;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static String runA(Stream<String> input) {
		return moveTheBoxes(input, Day5_SupplyStacks::moveStacks);
	}

	public static String runB(Stream<String> input) {
		return moveTheBoxes(input, Day5_SupplyStacks::movePilesOfStacks);
	}

	private static void addToStacks(String line, HashMap<Integer, ArrayDeque<Character>> stacks) {
		for (int i = 1; i <= 9; i++) {
			char c = line.charAt(2 + (i - 1) * 4 - 1);
			if (c != ' ') {
				stacks.get(i).addFirst(c);
			}
		}
	}

	private static void movePilesOfStacks(String[] split, HashMap<Integer, ArrayDeque<Character>> stacks) {
		ArrayDeque<Character> removedList = new ArrayDeque<>();
		for (int i = 1; i < Integer.parseInt(split[2]) + 1; i++) {
			Character remove = stacks.get(Integer.parseInt(split[6])).removeLast();
			removedList.addFirst(remove);
		}

		stacks.get(Integer.parseInt(split[10])).addAll(removedList);
	}

	private static void moveStacks(String[] split, HashMap<Integer, ArrayDeque<Character>> stacks) {
		for (int i = 1; i < Integer.parseInt(split[2]) + 1; i++) {
			Character remove = stacks.get(Integer.parseInt(split[6])).removeLast();
			stacks.get(Integer.parseInt(split[10])).add(remove);
		}
	}

	private static String moveTheBoxes(Stream<String> input, BiConsumer<String[], HashMap<Integer, ArrayDeque<Character>>> movingFunction) {
		HashMap<Integer, ArrayDeque<Character>> stacks = new HashMap<>();
		for (int i = 1; i <= 9; i++) {
			stacks.put(i, new ArrayDeque<>());
		}

		input.forEach(line -> {
			if (line.contains("[")) {
				addToStacks(line, stacks);
			} else if (line.contains("move")) {
				String[] split = line.split("\\b");
				movingFunction.accept(split, stacks);
			}
		});

		return stacks.values().stream()
				.map(characters -> characters.removeLast().toString())
				.collect(Collectors.joining());
	}
}
