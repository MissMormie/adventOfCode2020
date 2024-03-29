package days2021;

import java.util.Arrays;

public class Day2_Dive {
	public static void main(String[] args) {
		System.out.println("answer A: " + runA(textInput()));
		System.out.println("answer B: " + runB(textInput()));
	}

	public static int depth = 0;
	public static int forward = 0;
	public static int runA(String input) {
		Arrays.stream(input.split("\n")).forEach(line -> {
			String[] parts = line.split(" ");
			int number = Integer.parseInt(parts[1]);
			switch(parts[0]) {
				case "forward": forward += number; break;
				case "down": depth += number; break;
				case "up": depth -= number; break;
			}
		});

		return depth * forward;
	}

	public static int aim = 0;
	public static int runB(String input) {
		depth = 0;
		forward = 0;
		Arrays.stream(input.split("\n")).forEach(line -> {
			String[] parts = line.split(" ");
			int number = Integer.parseInt(parts[1]);
			switch(parts[0]) {
				case "forward":
					forward += number;
					depth += aim * number;
					break;
				case "down": aim += number; break;
				case "up": aim -= number; break;
			}
		});
		return depth * forward;
	}

	private static String textInput() {
		return "forward 9\n" +
				"forward 7\n" +
				"down 7\n" +
				"down 3\n" +
				"forward 2\n" +
				"forward 3\n" +
				"forward 7\n" +
				"down 6\n" +
				"forward 7\n" +
				"down 7\n" +
				"forward 9\n" +
				"down 9\n" +
				"up 2\n" +
				"down 5\n" +
				"up 1\n" +
				"forward 5\n" +
				"forward 6\n" +
				"up 4\n" +
				"down 3\n" +
				"down 4\n" +
				"down 5\n" +
				"up 6\n" +
				"down 3\n" +
				"forward 6\n" +
				"forward 4\n" +
				"down 4\n" +
				"forward 5\n" +
				"down 2\n" +
				"up 1\n" +
				"up 8\n" +
				"down 1\n" +
				"down 6\n" +
				"forward 8\n" +
				"down 4\n" +
				"forward 8\n" +
				"forward 6\n" +
				"forward 2\n" +
				"forward 2\n" +
				"forward 3\n" +
				"forward 8\n" +
				"up 9\n" +
				"up 2\n" +
				"down 3\n" +
				"up 3\n" +
				"forward 5\n" +
				"forward 2\n" +
				"up 5\n" +
				"forward 9\n" +
				"down 7\n" +
				"down 2\n" +
				"up 7\n" +
				"down 4\n" +
				"down 6\n" +
				"up 2\n" +
				"down 9\n" +
				"forward 7\n" +
				"down 8\n" +
				"forward 6\n" +
				"up 1\n" +
				"forward 6\n" +
				"forward 4\n" +
				"down 5\n" +
				"forward 6\n" +
				"down 8\n" +
				"down 3\n" +
				"forward 7\n" +
				"down 8\n" +
				"up 7\n" +
				"down 1\n" +
				"up 1\n" +
				"forward 9\n" +
				"down 7\n" +
				"up 3\n" +
				"down 6\n" +
				"down 6\n" +
				"down 6\n" +
				"down 7\n" +
				"down 9\n" +
				"down 6\n" +
				"down 9\n" +
				"down 8\n" +
				"down 3\n" +
				"down 7\n" +
				"down 3\n" +
				"up 8\n" +
				"down 5\n" +
				"down 9\n" +
				"up 4\n" +
				"forward 5\n" +
				"forward 5\n" +
				"forward 2\n" +
				"up 1\n" +
				"forward 6\n" +
				"down 6\n" +
				"down 2\n" +
				"forward 1\n" +
				"forward 8\n" +
				"down 4\n" +
				"down 1\n" +
				"down 8\n" +
				"down 7\n" +
				"forward 6\n" +
				"forward 8\n" +
				"down 8\n" +
				"up 1\n" +
				"up 1\n" +
				"forward 1\n" +
				"forward 3\n" +
				"up 8\n" +
				"down 1\n" +
				"forward 4\n" +
				"down 3\n" +
				"forward 3\n" +
				"forward 4\n" +
				"forward 3\n" +
				"down 3\n" +
				"down 9\n" +
				"down 3\n" +
				"up 6\n" +
				"forward 6\n" +
				"forward 8\n" +
				"forward 2\n" +
				"up 1\n" +
				"up 4\n" +
				"up 4\n" +
				"down 2\n" +
				"down 2\n" +
				"down 2\n" +
				"forward 7\n" +
				"up 9\n" +
				"down 9\n" +
				"up 1\n" +
				"down 5\n" +
				"forward 4\n" +
				"down 2\n" +
				"down 4\n" +
				"forward 3\n" +
				"down 7\n" +
				"down 9\n" +
				"forward 1\n" +
				"up 5\n" +
				"down 5\n" +
				"down 5\n" +
				"forward 2\n" +
				"down 6\n" +
				"forward 8\n" +
				"up 4\n" +
				"forward 6\n" +
				"up 1\n" +
				"down 4\n" +
				"forward 2\n" +
				"down 9\n" +
				"forward 5\n" +
				"down 7\n" +
				"down 8\n" +
				"down 9\n" +
				"forward 6\n" +
				"up 6\n" +
				"forward 9\n" +
				"forward 9\n" +
				"down 7\n" +
				"forward 7\n" +
				"forward 5\n" +
				"up 9\n" +
				"forward 3\n" +
				"down 9\n" +
				"down 1\n" +
				"down 8\n" +
				"down 4\n" +
				"forward 5\n" +
				"forward 6\n" +
				"forward 8\n" +
				"forward 8\n" +
				"down 4\n" +
				"down 3\n" +
				"down 8\n" +
				"forward 3\n" +
				"down 6\n" +
				"down 8\n" +
				"down 2\n" +
				"up 8\n" +
				"up 9\n" +
				"down 6\n" +
				"forward 3\n" +
				"down 4\n" +
				"down 6\n" +
				"forward 9\n" +
				"forward 6\n" +
				"up 2\n" +
				"down 8\n" +
				"forward 2\n" +
				"down 7\n" +
				"forward 9\n" +
				"up 9\n" +
				"down 9\n" +
				"down 2\n" +
				"forward 9\n" +
				"down 4\n" +
				"down 9\n" +
				"up 4\n" +
				"forward 6\n" +
				"down 2\n" +
				"down 9\n" +
				"forward 8\n" +
				"forward 2\n" +
				"up 8\n" +
				"forward 9\n" +
				"forward 2\n" +
				"forward 3\n" +
				"down 2\n" +
				"up 3\n" +
				"forward 9\n" +
				"down 6\n" +
				"down 3\n" +
				"down 1\n" +
				"forward 9\n" +
				"forward 8\n" +
				"down 9\n" +
				"up 7\n" +
				"down 8\n" +
				"up 7\n" +
				"forward 1\n" +
				"forward 1\n" +
				"forward 7\n" +
				"down 2\n" +
				"down 1\n" +
				"up 1\n" +
				"up 6\n" +
				"down 5\n" +
				"up 9\n" +
				"up 7\n" +
				"forward 1\n" +
				"forward 6\n" +
				"forward 1\n" +
				"up 4\n" +
				"down 6\n" +
				"forward 2\n" +
				"up 7\n" +
				"down 2\n" +
				"up 8\n" +
				"forward 9\n" +
				"forward 6\n" +
				"forward 3\n" +
				"forward 8\n" +
				"down 1\n" +
				"forward 8\n" +
				"up 3\n" +
				"forward 1\n" +
				"forward 1\n" +
				"up 9\n" +
				"down 1\n" +
				"down 8\n" +
				"down 2\n" +
				"forward 8\n" +
				"down 8\n" +
				"forward 7\n" +
				"down 5\n" +
				"forward 8\n" +
				"forward 3\n" +
				"forward 6\n" +
				"forward 7\n" +
				"up 5\n" +
				"down 5\n" +
				"forward 8\n" +
				"down 2\n" +
				"forward 3\n" +
				"down 4\n" +
				"down 9\n" +
				"forward 6\n" +
				"forward 5\n" +
				"up 4\n" +
				"forward 7\n" +
				"down 3\n" +
				"forward 9\n" +
				"forward 5\n" +
				"down 3\n" +
				"up 5\n" +
				"forward 4\n" +
				"forward 8\n" +
				"down 7\n" +
				"up 2\n" +
				"forward 7\n" +
				"down 5\n" +
				"up 2\n" +
				"down 9\n" +
				"forward 4\n" +
				"down 3\n" +
				"forward 5\n" +
				"forward 4\n" +
				"down 3\n" +
				"forward 6\n" +
				"up 1\n" +
				"forward 8\n" +
				"down 1\n" +
				"up 7\n" +
				"forward 8\n" +
				"up 1\n" +
				"up 1\n" +
				"forward 2\n" +
				"down 8\n" +
				"forward 4\n" +
				"forward 8\n" +
				"up 6\n" +
				"forward 5\n" +
				"forward 7\n" +
				"up 6\n" +
				"up 4\n" +
				"up 6\n" +
				"down 1\n" +
				"forward 3\n" +
				"down 1\n" +
				"down 1\n" +
				"down 8\n" +
				"forward 8\n" +
				"down 5\n" +
				"down 5\n" +
				"forward 5\n" +
				"forward 9\n" +
				"down 9\n" +
				"forward 7\n" +
				"down 3\n" +
				"down 5\n" +
				"forward 6\n" +
				"down 1\n" +
				"down 5\n" +
				"up 8\n" +
				"down 9\n" +
				"forward 3\n" +
				"down 6\n" +
				"up 2\n" +
				"down 2\n" +
				"forward 2\n" +
				"up 2\n" +
				"forward 8\n" +
				"down 2\n" +
				"forward 9\n" +
				"forward 2\n" +
				"down 7\n" +
				"down 5\n" +
				"forward 1\n" +
				"forward 7\n" +
				"up 6\n" +
				"up 8\n" +
				"forward 8\n" +
				"forward 8\n" +
				"up 3\n" +
				"forward 8\n" +
				"down 6\n" +
				"down 6\n" +
				"forward 4\n" +
				"down 8\n" +
				"down 5\n" +
				"down 7\n" +
				"forward 1\n" +
				"forward 9\n" +
				"forward 9\n" +
				"up 5\n" +
				"down 9\n" +
				"down 1\n" +
				"forward 4\n" +
				"forward 1\n" +
				"up 9\n" +
				"forward 6\n" +
				"down 6\n" +
				"forward 2\n" +
				"up 6\n" +
				"forward 9\n" +
				"up 1\n" +
				"down 2\n" +
				"up 3\n" +
				"forward 2\n" +
				"forward 1\n" +
				"forward 6\n" +
				"down 9\n" +
				"up 1\n" +
				"forward 7\n" +
				"up 3\n" +
				"forward 6\n" +
				"forward 6\n" +
				"up 2\n" +
				"down 8\n" +
				"forward 4\n" +
				"down 4\n" +
				"forward 2\n" +
				"forward 2\n" +
				"down 4\n" +
				"down 7\n" +
				"down 4\n" +
				"down 5\n" +
				"forward 3\n" +
				"down 1\n" +
				"forward 1\n" +
				"forward 8\n" +
				"down 7\n" +
				"up 1\n" +
				"forward 7\n" +
				"forward 2\n" +
				"down 9\n" +
				"down 2\n" +
				"up 2\n" +
				"forward 3\n" +
				"down 4\n" +
				"down 7\n" +
				"down 8\n" +
				"forward 4\n" +
				"forward 5\n" +
				"forward 3\n" +
				"up 3\n" +
				"down 6\n" +
				"forward 4\n" +
				"forward 4\n" +
				"forward 8\n" +
				"forward 1\n" +
				"up 2\n" +
				"up 3\n" +
				"down 4\n" +
				"up 9\n" +
				"forward 1\n" +
				"forward 1\n" +
				"forward 9\n" +
				"down 2\n" +
				"down 5\n" +
				"up 9\n" +
				"down 7\n" +
				"down 9\n" +
				"down 2\n" +
				"down 4\n" +
				"forward 1\n" +
				"forward 1\n" +
				"forward 8\n" +
				"down 9\n" +
				"down 6\n" +
				"forward 2\n" +
				"up 3\n" +
				"down 8\n" +
				"forward 1\n" +
				"forward 8\n" +
				"forward 4\n" +
				"up 7\n" +
				"forward 5\n" +
				"forward 2\n" +
				"forward 2\n" +
				"up 8\n" +
				"down 5\n" +
				"forward 6\n" +
				"down 3\n" +
				"up 5\n" +
				"forward 8\n" +
				"forward 3\n" +
				"forward 9\n" +
				"down 1\n" +
				"down 3\n" +
				"forward 8\n" +
				"down 2\n" +
				"forward 6\n" +
				"forward 2\n" +
				"down 3\n" +
				"down 3\n" +
				"forward 6\n" +
				"forward 4\n" +
				"forward 7\n" +
				"forward 2\n" +
				"up 7\n" +
				"up 4\n" +
				"up 6\n" +
				"forward 9\n" +
				"down 3\n" +
				"down 3\n" +
				"up 7\n" +
				"down 4\n" +
				"up 3\n" +
				"up 3\n" +
				"down 5\n" +
				"forward 1\n" +
				"up 3\n" +
				"down 1\n" +
				"forward 2\n" +
				"up 9\n" +
				"forward 7\n" +
				"down 6\n" +
				"forward 4\n" +
				"forward 8\n" +
				"up 1\n" +
				"forward 6\n" +
				"down 7\n" +
				"down 4\n" +
				"up 9\n" +
				"forward 4\n" +
				"down 7\n" +
				"up 1\n" +
				"forward 9\n" +
				"down 4\n" +
				"down 7\n" +
				"forward 1\n" +
				"down 6\n" +
				"down 6\n" +
				"forward 3\n" +
				"up 8\n" +
				"forward 3\n" +
				"down 1\n" +
				"down 5\n" +
				"down 8\n" +
				"forward 2\n" +
				"up 5\n" +
				"forward 2\n" +
				"up 7\n" +
				"forward 5\n" +
				"forward 1\n" +
				"forward 3\n" +
				"forward 4\n" +
				"forward 5\n" +
				"up 1\n" +
				"forward 9\n" +
				"down 5\n" +
				"down 7\n" +
				"up 9\n" +
				"down 9\n" +
				"forward 7\n" +
				"up 6\n" +
				"up 7\n" +
				"forward 2\n" +
				"forward 1\n" +
				"up 4\n" +
				"forward 6\n" +
				"forward 9\n" +
				"down 1\n" +
				"forward 4\n" +
				"down 5\n" +
				"forward 4\n" +
				"down 3\n" +
				"down 5\n" +
				"forward 6\n" +
				"forward 3\n" +
				"down 3\n" +
				"down 8\n" +
				"down 2\n" +
				"down 4\n" +
				"down 6\n" +
				"down 4\n" +
				"forward 2\n" +
				"up 9\n" +
				"down 3\n" +
				"forward 1\n" +
				"forward 9\n" +
				"forward 5\n" +
				"forward 5\n" +
				"forward 9\n" +
				"up 1\n" +
				"down 4\n" +
				"down 4\n" +
				"up 7\n" +
				"down 3\n" +
				"up 3\n" +
				"up 4\n" +
				"forward 3\n" +
				"forward 1\n" +
				"forward 8\n" +
				"up 6\n" +
				"down 8\n" +
				"down 4\n" +
				"forward 7\n" +
				"forward 9\n" +
				"forward 2\n" +
				"forward 8\n" +
				"up 2\n" +
				"down 4\n" +
				"down 5\n" +
				"forward 9\n" +
				"down 6\n" +
				"down 7\n" +
				"down 8\n" +
				"up 8\n" +
				"forward 3\n" +
				"forward 7\n" +
				"forward 8\n" +
				"up 2\n" +
				"down 9\n" +
				"down 6\n" +
				"forward 3\n" +
				"forward 4\n" +
				"down 4\n" +
				"forward 2\n" +
				"up 6\n" +
				"forward 1\n" +
				"forward 7\n" +
				"down 2\n" +
				"down 1\n" +
				"forward 2\n" +
				"forward 2\n" +
				"down 2\n" +
				"forward 2\n" +
				"forward 7\n" +
				"up 4\n" +
				"down 3\n" +
				"forward 9\n" +
				"down 7\n" +
				"down 7\n" +
				"down 6\n" +
				"forward 3\n" +
				"forward 9\n" +
				"down 9\n" +
				"forward 2\n" +
				"down 5\n" +
				"down 4\n" +
				"down 9\n" +
				"up 9\n" +
				"down 6\n" +
				"down 8\n" +
				"down 1\n" +
				"forward 8\n" +
				"up 4\n" +
				"up 4\n" +
				"down 8\n" +
				"forward 6\n" +
				"down 2\n" +
				"forward 4\n" +
				"forward 3\n" +
				"forward 2\n" +
				"forward 4\n" +
				"down 4\n" +
				"forward 6\n" +
				"down 9\n" +
				"up 7\n" +
				"up 5\n" +
				"down 7\n" +
				"down 4\n" +
				"up 3\n" +
				"forward 4\n" +
				"down 9\n" +
				"forward 6\n" +
				"forward 4\n" +
				"forward 4\n" +
				"down 9\n" +
				"forward 3\n" +
				"forward 2\n" +
				"up 7\n" +
				"forward 3\n" +
				"down 1\n" +
				"down 3\n" +
				"up 5\n" +
				"down 8\n" +
				"down 3\n" +
				"down 4\n" +
				"forward 7\n" +
				"forward 9\n" +
				"up 2\n" +
				"forward 3\n" +
				"up 4\n" +
				"down 5\n" +
				"up 3\n" +
				"up 9\n" +
				"down 6\n" +
				"down 2\n" +
				"down 5\n" +
				"up 4\n" +
				"up 6\n" +
				"forward 4\n" +
				"forward 6\n" +
				"up 5\n" +
				"up 5\n" +
				"forward 8\n" +
				"down 6\n" +
				"forward 6\n" +
				"down 7\n" +
				"down 5\n" +
				"down 3\n" +
				"down 8\n" +
				"forward 6\n" +
				"forward 9\n" +
				"forward 9\n" +
				"up 9\n" +
				"down 3\n" +
				"up 5\n" +
				"forward 4\n" +
				"down 7\n" +
				"forward 5\n" +
				"down 7\n" +
				"down 4\n" +
				"forward 2\n" +
				"forward 9\n" +
				"down 8\n" +
				"up 3\n" +
				"up 7\n" +
				"down 7\n" +
				"up 7\n" +
				"forward 3\n" +
				"down 2\n" +
				"forward 7\n" +
				"down 4\n" +
				"forward 1\n" +
				"down 6\n" +
				"forward 1\n" +
				"up 4\n" +
				"down 7\n" +
				"up 3\n" +
				"forward 7\n" +
				"forward 5\n" +
				"forward 7\n" +
				"forward 6\n" +
				"up 2\n" +
				"down 4\n" +
				"down 8\n" +
				"down 4\n" +
				"up 3\n" +
				"forward 3\n" +
				"up 3\n" +
				"up 3\n" +
				"down 7\n" +
				"down 2\n" +
				"down 3\n" +
				"forward 7\n" +
				"down 6\n" +
				"down 9\n" +
				"up 1\n" +
				"down 8\n" +
				"down 6\n" +
				"down 3\n" +
				"up 2\n" +
				"up 6\n" +
				"forward 9\n" +
				"forward 5\n" +
				"forward 4\n" +
				"forward 9\n" +
				"down 9\n" +
				"forward 2\n" +
				"up 7\n" +
				"down 4\n" +
				"down 8\n" +
				"up 2\n" +
				"forward 6\n" +
				"up 6\n" +
				"up 4\n" +
				"down 2\n" +
				"forward 6\n" +
				"forward 4\n" +
				"up 3\n" +
				"down 6\n" +
				"forward 5\n" +
				"forward 3\n" +
				"up 4\n" +
				"down 7\n" +
				"down 2\n" +
				"down 6\n" +
				"up 7\n" +
				"forward 2\n" +
				"forward 1\n" +
				"forward 3\n" +
				"down 2\n" +
				"forward 1\n" +
				"forward 2\n" +
				"forward 4\n" +
				"down 2\n" +
				"down 5\n" +
				"down 7\n" +
				"down 8\n" +
				"down 1\n" +
				"up 1\n" +
				"up 1\n" +
				"forward 9\n" +
				"down 3\n" +
				"down 1\n" +
				"forward 4\n" +
				"up 6\n" +
				"up 8\n" +
				"forward 7\n" +
				"forward 9\n" +
				"down 3\n" +
				"forward 9\n" +
				"down 9\n" +
				"forward 6\n" +
				"down 1\n" +
				"forward 7\n" +
				"down 9\n" +
				"forward 1\n" +
				"down 8\n" +
				"forward 8\n" +
				"up 7\n" +
				"forward 4\n" +
				"up 5\n" +
				"up 9\n" +
				"forward 1\n" +
				"forward 4\n" +
				"forward 3\n" +
				"down 3\n" +
				"down 8\n" +
				"up 3\n" +
				"forward 1\n" +
				"up 5\n" +
				"forward 5\n" +
				"up 6\n" +
				"forward 8\n" +
				"forward 1\n" +
				"down 7\n" +
				"forward 2\n" +
				"down 9\n" +
				"forward 3\n" +
				"forward 7\n" +
				"forward 2\n" +
				"down 4\n" +
				"forward 2\n" +
				"up 6\n" +
				"down 7\n" +
				"up 3\n" +
				"forward 7\n" +
				"down 8\n" +
				"down 3\n" +
				"forward 2\n" +
				"up 7\n" +
				"down 2\n" +
				"down 8\n" +
				"up 6\n" +
				"forward 7\n" +
				"forward 1\n" +
				"down 3\n" +
				"forward 2\n" +
				"forward 8\n" +
				"down 8\n" +
				"forward 1\n" +
				"down 7\n" +
				"down 1\n" +
				"up 5\n" +
				"up 3\n" +
				"forward 5\n" +
				"down 5\n" +
				"up 9\n" +
				"up 9\n" +
				"down 3\n" +
				"up 3\n" +
				"down 4\n" +
				"down 6\n" +
				"up 7\n" +
				"forward 3\n" +
				"up 5\n" +
				"down 3\n" +
				"forward 4\n" +
				"down 1\n" +
				"up 1\n" +
				"up 6\n" +
				"down 8\n" +
				"forward 5\n" +
				"up 2\n" +
				"down 5\n" +
				"forward 6\n" +
				"forward 4\n" +
				"forward 9\n" +
				"down 9\n" +
				"down 5\n" +
				"forward 5\n" +
				"down 7\n" +
				"down 7\n" +
				"down 8\n" +
				"forward 3\n" +
				"down 6\n" +
				"forward 5\n" +
				"forward 5\n" +
				"down 6\n" +
				"forward 3\n" +
				"down 7\n" +
				"up 4\n" +
				"up 3\n" +
				"down 5\n" +
				"forward 9\n" +
				"forward 9\n" +
				"up 9\n" +
				"down 1\n" +
				"up 2\n" +
				"up 3\n" +
				"down 7\n" +
				"forward 3\n" +
				"down 7\n" +
				"down 4\n" +
				"down 5\n" +
				"down 1\n" +
				"down 4\n" +
				"up 9\n" +
				"forward 1\n" +
				"up 8\n" +
				"forward 7\n" +
				"up 6\n" +
				"down 1\n" +
				"up 2\n" +
				"forward 2\n" +
				"up 9\n" +
				"down 6\n" +
				"forward 4\n" +
				"down 2\n" +
				"up 5\n" +
				"forward 1\n" +
				"forward 4\n" +
				"down 6\n" +
				"down 2\n" +
				"up 8\n" +
				"forward 2\n" +
				"forward 8\n" +
				"forward 4\n" +
				"down 9\n" +
				"up 3\n" +
				"forward 5\n" +
				"forward 9\n" +
				"forward 4\n" +
				"down 2\n" +
				"up 4\n" +
				"up 9\n" +
				"down 5\n" +
				"up 2\n" +
				"forward 6\n" +
				"up 2\n" +
				"down 6\n" +
				"up 5\n" +
				"up 3\n" +
				"up 9\n" +
				"forward 8\n" +
				"down 2\n" +
				"forward 7\n" +
				"up 8\n" +
				"down 9\n" +
				"forward 2\n" +
				"forward 2\n" +
				"down 6\n" +
				"forward 9\n" +
				"forward 2\n" +
				"forward 8\n" +
				"up 3\n" +
				"forward 5\n" +
				"down 4\n" +
				"forward 2\n" +
				"down 7\n" +
				"up 6\n" +
				"forward 7\n" +
				"down 6\n" +
				"down 8\n" +
				"down 3\n" +
				"up 4\n" +
				"up 5\n" +
				"down 2\n" +
				"down 9\n" +
				"forward 2\n" +
				"down 7\n" +
				"forward 2\n" +
				"forward 3\n" +
				"forward 9\n" +
				"down 6\n" +
				"down 1\n" +
				"forward 6\n" +
				"down 5\n" +
				"forward 2\n" +
				"down 5\n" +
				"down 1\n" +
				"forward 5\n" +
				"down 4\n" +
				"down 6\n" +
				"down 5\n" +
				"forward 9\n" +
				"up 6\n" +
				"up 5\n" +
				"up 2\n" +
				"down 1\n" +
				"down 8\n" +
				"forward 4\n" +
				"down 2\n" +
				"forward 5\n" +
				"down 1\n" +
				"forward 7\n" +
				"down 8\n" +
				"down 9\n" +
				"down 7\n" +
				"up 1\n" +
				"forward 2\n" +
				"up 8\n" +
				"down 9\n" +
				"down 2\n" +
				"down 1\n" +
				"down 9\n" +
				"down 2\n" +
				"down 5\n" +
				"forward 9\n" +
				"forward 1\n" +
				"down 1\n" +
				"forward 9\n" +
				"forward 7\n" +
				"down 6\n" +
				"down 1\n" +
				"down 7\n" +
				"forward 4\n" +
				"forward 1\n" +
				"forward 4\n" +
				"forward 5\n" +
				"forward 5\n" +
				"down 2\n" +
				"forward 7\n" +
				"forward 6\n" +
				"forward 3\n" +
				"forward 9\n" +
				"up 1\n" +
				"down 5\n" +
				"down 4\n" +
				"down 2\n" +
				"forward 1\n" +
				"up 7\n" +
				"forward 2";

	}

}
