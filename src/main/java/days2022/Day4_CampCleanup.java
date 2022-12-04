package days2022;

import helpers.InputProvider;

import java.io.IOException;
import java.util.stream.Stream;

public class Day4_CampCleanup {
	public static int day = 4;

	public static int year = 2022;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static long runA(Stream<String> input) {
		return input
				.map(line -> line.split("\\D"))
				.filter(split ->
						Integer.parseInt(split[0]) <= Integer.parseInt(split[2]) && Integer.parseInt(split[1]) >= Integer.parseInt(split[3]) ||
								Integer.parseInt(split[0]) >= Integer.parseInt(split[2]) && Integer.parseInt(split[1]) <= Integer.parseInt(split[3])
				)
				.count();
	}

	public static long runB(Stream<String> input) {
		return input
				.map(line -> line.split("\\D"))
				.filter(split -> findOverlap(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3])))
				.count();
	}

	public static boolean findOverlap(int a1, int a2, int b1, int b2) {
		return a1 < b1 && a2 >= b1 ||
			a1 > b1 && a1 <= b2 ||
				a1 == b1 || a2 == b2;
	}
}
