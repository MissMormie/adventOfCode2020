package days2021;

import helpers.InputProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6_LanternFish {
	public static int day = 6;
	public static int year = 2021;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static String runA(Stream<String> input) {
		return breedFishForDays(input, 80);
	}

	private static String breedFishForDays(Stream<String> input, int runForDays) {
		BigInteger[] fish = new BigInteger[9];
		for(int i = 0; i < fish.length ; i++) {
			fish[i] = BigInteger.ZERO;
		}

		String[] split = input.collect(Collectors.joining()).split(",");
		for (int i =0; i < split.length; i++) {
			int fishNum = Integer.parseInt(split[i]);
			fish[fishNum] = fish[fishNum].add(BigInteger.ONE);
		}

		for (int days = 1; days <= runForDays; days++) {
			BigInteger fish0 = fish[0];
			for(int i =1; i < fish.length ; i++) {
				fish[i -1] = fish[i];
			}
			fish[6] = fish[6].add(fish0);
			fish[8] = fish0;
		}

		BigInteger totalFish = BigInteger.ZERO;
		for(int i = 0; i < fish.length ; i++) {
			totalFish = totalFish.add(fish[i]);
		}

		return totalFish.toString();
	}


	public static String runB(Stream<String> input) {
		return breedFishForDays(input, 256);
	}

	private static String textInput() {
		return "";

	}
}
