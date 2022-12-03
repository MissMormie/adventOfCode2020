package days2022;

import com.google.common.collect.Iterables;
import helpers.InputProvider;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Day3_RuckSackReorganization {
	public static int day = 3;

	public static int year = 2022;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		return input.map(line -> List.of(line.substring(0, line.length() / 2), line.substring(line.length() / 2)))
				.map(Day3_RuckSackReorganization::getRepeatingChar)
				.map(c -> c > 96 ? c - 96 : c - 38)
				.mapToInt(i -> i)
				.sum();
	}

	public static int runB(Stream<String> input) {
		Iterator<List<String>> iterator = Iterables.partition(input.collect(Collectors.toList()), 3).iterator();
		Iterable<List<String>> iterable = () -> iterator;
		return StreamSupport.stream(iterable.spliterator(), false)
				.map(Day3_RuckSackReorganization::getRepeatingChar)
				.map(c -> c > 96 ? c - 96 : c - 38)
				.mapToInt(i -> i)
				.sum();
	}

	private static char getRepeatingChar(List<String> parts) {
		for (char c : parts.get(0).toCharArray()) {
			if (parts.stream().allMatch(part -> part.contains(c + ""))) {
				return c;
			}
		}
		throw new IllegalStateException();
	}
}
