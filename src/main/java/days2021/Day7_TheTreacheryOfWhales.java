package days2021;

import helpers.InputProvider;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7_TheTreacheryOfWhales {
	public static int day = 7;
	public static int year = 2021;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		IntBinaryOperator calculateFuel = (testHeight, height) -> Math.abs(testHeight - height);
		return getLeastFuel(input, calculateFuel);
	}

	public static int runB(Stream<String> input) {
		IntBinaryOperator calculateFuel = (testHeight, height) -> {
			int distance = Math.abs(testHeight - height);
			return (distance * (distance - 1))/2 + distance;
		};
		return getLeastFuel(input, calculateFuel);
	}

	private static int getLeastFuel(Stream<String> input, IntBinaryOperator fuelFunction) {
		List<Integer> heights = input.flatMap(in -> Arrays.stream(in.split(",")))
				.map(Integer::parseInt)
				.collect(Collectors.toList());

		int max = heights.stream().mapToInt(v -> v).max().getAsInt();
		int min = heights.stream().mapToInt(v -> v).min().getAsInt();

		int minFuel = 1000000000;
		for(int i = min; i <= max; i++) {
			int finalI = i;
			int sum = heights.stream().map(height -> fuelFunction.applyAsInt(finalI, height)).mapToInt(v -> v).sum();
			if(sum < minFuel) {
				minFuel = sum;
			}
		}
		return minFuel;
	}

}
