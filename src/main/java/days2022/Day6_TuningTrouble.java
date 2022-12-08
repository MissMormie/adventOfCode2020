package days2022;

import helpers.InputProvider;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6_TuningTrouble {
	public static int day = 6;

	public static int year = 2022;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		return input.map(line -> {
					char c0 = line.charAt(0);
					char c1 = line.charAt(1);
					char c2 = line.charAt(2);
					char c3;
					for (int i = 3; i < line.length(); i++) {
						c3 = line.charAt(i);
						if (!(c0 == c1 || c0 == c2 || c0 == c3 ||
								c1 == c2 || c1 == c3
								|| c2 == c3)) {
							return i + 1;
						}
						c0 = c1;
						c1 = c2;
						c2 = c3;
					}
					return -1;
				})
				.collect(Collectors.toList()).get(0);
	}


	public static int runB(Stream<String> input) {
		return input.map(line -> {
					for (int i = 14; i < line.length(); i++) {
						String part = line.substring(i - 14, i);
						Set<Character> set = part.chars()
								.mapToObj(ch -> (char) ch)
								.collect(Collectors.toSet());
						if (set.size() == 14) {
							return i ;
						}
					}
					return -1;
				})
				.collect(Collectors.toList()).get(0);
	}
}
