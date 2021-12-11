package days2021;

import helpers.InputProvider;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day10_SyntaxScoring {
	public static int day = 10;
	public static int year = 2021;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static long runA(Stream<String> input) {
		Map<Character, Long> collect = input.map(line -> getFirstFaultyChar(line.toCharArray()))
				.filter(Objects::nonNull)
				.collect(Collectors.groupingBy(c -> c, Collectors.counting()));

		long answer = 0;
		if(collect.containsKey(')')) {
			answer += collect.get(')') * 3;
		}

		if(collect.containsKey(']')) {
			answer += collect.get(']') * 57;
		}

		if(collect.containsKey('}')) {
			answer += collect.get('}') * 1197;
		}

		if(collect.containsKey('>')) {
			answer += collect.get('>') * 25137;
		}
		return answer;

	}

	public static Character getFirstFaultyChar(char[] input) {
		int index = 0;
		Stack<Character> stack = new Stack<>();
		while(index < input.length) {
			char nextChar = input[index];
			if (isOpeningChar(nextChar)) {
				stack.push(nextChar);
			} else {
				if (getMatchingChar(stack.pop()) != nextChar) {
					return nextChar;
				}
			}
			index++;
		}
		// no faulty chars, might still be missing chars
		return null;
	}

	private static char getMatchingChar(char c) {
		switch (c) {
			case '{': return '}';
			case '<': return '>';
			case '(': return ')';
			case '[': return ']';
		}
		return 'x';
	}

	private static boolean isOpeningChar(char c) {
		return c == '(' || c == '<' || c == '[' || c == '{';
	}

	public static long runB(Stream<String> input) {
		List<Long> collect = input.filter(line -> getFirstFaultyChar(line.toCharArray()) == null)
				.map(line -> getCompletionScore(line.toCharArray())).collect(Collectors.toList());
		Collections.sort(collect);
		int median = collect.size() / 2;
		return collect.get(median);
		// 3354962212 too high
	}


	public static long getCompletionScore(char[] input) {
		int index = 0;
		Stack<Character> stack = new Stack<>();
		while(index < input.length) {
			char nextChar = input[index];
			if (isOpeningChar(nextChar)) {
				stack.push(nextChar);
			} else {
				stack.pop();
			}
			index++;
		}
		long score = 0;
		while(!stack.isEmpty()) {
			score *= 5;
			switch (stack.pop()) {
				case '{': score += 3; break;
				case '<': score += 4; break;
				case '(': score += 1; break;
				case '[': score += 2; break;
			}
		}
		return score;
	}
}
