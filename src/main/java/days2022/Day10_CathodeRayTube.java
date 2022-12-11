package days2022;

import helpers.InputProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day10_CathodeRayTube {
	public static int day = 10;

	public static int year = 2022;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		List<String> collect = input.collect(Collectors.toList());

		int signalStrengthSum = 0;
		int x = 1;
		int cycle = 1;
		for (String line : collect) {
			String[] split = line.split(" ");
			cycle++;
			signalStrengthSum = updateSignalStrength(signalStrengthSum, x, cycle);
			if (split.length > 1) { // noop instruction.
				cycle++;
				x += Integer.parseInt(split[1]);
				signalStrengthSum = updateSignalStrength(signalStrengthSum, x, cycle);
			}
		}
		return signalStrengthSum;
	}

	public static int runB(Stream<String> input) {
		int x = 1;
		int cycle = 0;
		List<String> collect = input.collect(Collectors.toList());
		List<String> crtOutput = new ArrayList<>();
		crtOutput.add(0, " ");
		for (String line : collect) {
			String[] split = line.split(" ");
			cycle = moveToNextCycle(cycle, x, crtOutput);
			if (split.length > 1) { // noop instruction.
				cycle = moveToNextCycle(cycle, x, crtOutput);
				x += Integer.parseInt(split[1]);
			}
		}

		String singleLineOutput = crtOutput.stream().collect(Collectors.joining());
		IntStream.range(0, crtOutput.size() / 40)
				.mapToObj(i -> singleLineOutput.substring(i * 40 + 1, i * 40 + 40))
				.forEach(System.out::println);
		return 0;
	}

	private static int moveToNextCycle(int cycle, int x, List<String> output) {
		cycle++;
		if (Math.abs(cycle % 40 - 1 - x) <= 1) {
			output.add(cycle, "#");
		} else {
			output.add(cycle, ".");
		}
//		System.out.println(output.stream().collect(Collectors.joining()));
		return cycle;
	}

	private static int updateSignalStrength(int signalStrengthSum, int x, int cycle) {
		if (cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220) {
			signalStrengthSum += x * cycle;
		}
		return signalStrengthSum;
	}
}
