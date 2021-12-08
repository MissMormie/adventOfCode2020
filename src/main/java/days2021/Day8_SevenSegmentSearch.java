package days2021;

import helpers.InputProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day8_SevenSegmentSearch {
	public static int day = 8;

	public static int year = 2021;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static long runA(Stream<String> input) {
		return input.map(Day8_SevenSegmentSearch::getOccurrencesOf1478).mapToLong(v -> v).sum();
	}

	public static long runB(Stream<String> input) {
		return input.map(Day8_SevenSegmentSearch::getOutputValue).mapToInt(v -> v).sum();
	}


	public static long getOccurrencesOf1478(String input) {
		String output = input.substring(input.indexOf("|") + 2);

		return Arrays.stream(output.split(" "))
				.filter(block -> block.length() < 5 || block.length() == 7).count();
	}

	public static Integer getOutputValue(String line) {
		String[] inputs = line.replace(" | ", " ").split(" ");
		Map<Integer, List<String>> countsMap = new HashMap<>();

		// remove doubles and store inputs in map by number of chars.
		Arrays.stream(inputs).map(toSortedString())
				.collect(Collectors.toSet())
				.forEach(input -> {
					int length = input.length();
					if (!countsMap.containsKey(length)) {
						countsMap.put(length, new ArrayList<>());
					}
					countsMap.get(length).add(input);
				});


		Map<Integer, String> numbers = new HashMap<>();
		numbers.put(1, countsMap.get(2).get(0)); // combination for 1
		numbers.put(7, countsMap.get(3).get(0)); // combination for 7
		numbers.put(8, countsMap.get(7).get(0)); // combination for 8
		numbers.put(4, countsMap.get(4).get(0)); // combination for 4
// 2, 3, 5

		// 6 does not contains both values from 1 and has 6 chars total.
		String string6 = countsMap.get(6).stream().filter(str -> {
			String s = numbers.get(1);
			return !(str.contains(Character.toString(s.charAt(0))) && str.contains(Character.toString(s.charAt(1))));
		}).findAny().get();
		numbers.put(6, string6);
		countsMap.get(6).remove(string6);

		// 0 does not contain all the same lines as 4, 6 total chars.
		String string0 = countsMap.get(6).stream().filter(str -> {
			String s = numbers.get(4);
			return !(str.contains(Character.toString(s.charAt(0))) && str.contains(Character.toString(s.charAt(1))) &&
					str.contains(Character.toString(s.charAt(2))) && str.contains(Character.toString(s.charAt(3))));
		}).findAny().get();
		numbers.put(0, string0);
		countsMap.get(6).remove(string0);

		// 9 has 6 total chars and is left
		String string9 = countsMap.get(6).get(0);
		numbers.put(9, string9);

		// 3 contains both values from 1 and 5chars total.
		String string3 = countsMap.get(5).stream().filter(str -> {
			String s = numbers.get(1);
			return str.contains(Character.toString(s.charAt(0))) && str.contains(Character.toString(s.charAt(1)));
		}).findAny().get();
		numbers.put(3, string3);
		countsMap.get(5).remove(string3);

		// 5 is the same as 9 with one less char.
		String string5 = countsMap.get(5).stream()
				.filter(in -> containsAllChars(numbers.get(9), in)).findAny().get();
		numbers.put(5,string5);
		countsMap.get(5).remove(string5);


		// 2 has 5 chars and is left
		numbers.put(2, countsMap.get(5).get(0));

		// get output value part
		String[] parts= line.substring(line.indexOf("| ") + 2).split(" ");

		StringBuilder sb = new StringBuilder();
		Arrays.stream(parts).map(toSortedString())
				.map(input -> numbers.entrySet().stream()
						.filter(entry -> entry.getValue().equals(input)).findAny().get().getKey())
				.forEach(num -> sb.append(num));
		return Integer.parseInt(sb.toString());

	}

	private static Function<String, String> toSortedString() {
		return input -> {
			char[] chars = input.toCharArray();
			Arrays.sort(chars);
			return new String(chars);
		};
	}

	public static Set<Character> stringToCharacterSet(String s) {
		Set<Character> set = new HashSet<>();
		for (char c : s.toCharArray()) {
			set.add(c);
		}
		return set;
	}

	public static boolean containsAllChars
			(String container, String containee) {
		return stringToCharacterSet(container).containsAll
				(stringToCharacterSet(containee));
	}

}
