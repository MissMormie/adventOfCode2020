package days2022;

import helpers.InputProvider;

import java.io.IOException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day21_MonkeyMath {
	public static int day = 21;

	public static int year = 2022;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day))); //75147370123646
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
		// 8112670909931 too high
	}

	public static long runA(Stream<String> input) {
		Map<String, Monkey> monkeyMap = input.map(Monkey::new)
				.collect(Collectors.toMap(monkey -> monkey.split[0], Function.identity()));

		return monkeyMap.get("root").getNumber(monkeyMap);
	}


	public static long runB(Stream<String> input) {
		Map<String, Monkey> monkeyMap = input.map(Monkey::new)
				.collect(Collectors.toMap(monkey -> monkey.split[0], Function.identity()));
		// set human monkey to 0, so we can figure out what we need later.
		monkeyMap.get("humn").number = 342328_0003000l;

		Monkey root = monkeyMap.get("root");
		root.getNumber(monkeyMap);
		root.calculateWorksWithHuman(monkeyMap);

		long variableM = root.variableMonkey.number;
		long staticNum = root.staticMonkey.number;
//		return root.variableMonkey.balanceNumber(root.staticMonkey.number);
		return 0l;
	}

	private static class Monkey {
		Long number = null;

		String[] split;

		Monkey variableMonkey;
		Monkey staticMonkey;

		boolean isHuman;

		public long balanceNumber(long desiredNumber) {
			if(isHuman) {
				System.out.println(desiredNumber);
				number = desiredNumber;
				return number;
			}
			long staticNum = staticMonkey.number;
			// Doing it stupidly with code repetition. It matters if first monkey is variable, or second.
			if(split[1].equals(variableMonkey.split[0])) {
				// first monkey (A) is variable.
				switch (split[2]) {
					case "+":
						number = desiredNumber - staticNum;
						break;
					case "-":
						number = desiredNumber + staticNum;
						break;
					case "/":
						number = desiredNumber * staticNum;
						break;
					case "*":
						number = desiredNumber / staticNum;
						break;
					default:
						return 0l;
				}
			} else {
				switch (split[2]) {
					case "+":
						number = desiredNumber - staticNum;
						break;
					case "-":
						number = desiredNumber + staticNum;
						break;
					case "/":
						number = staticNum / desiredNumber;
						break;
					case "*":
						number = desiredNumber / staticNum;
						break;
					default:
						return 0l;
				}
			}

			return variableMonkey.balanceNumber(number);

		}

		public Monkey(String line) {
			this.split = line.split("(: | )");
			if(split[0].equals("humn")) {
				isHuman = true;
			}
		}

		public long getNumber(Map<String, Monkey> monkeyMap) {
			if (number != null) {
				return number;
			}
			if (split.length == 2) {
				number = Long.parseLong(split[1]);
				return number;
			}
			long num1 = monkeyMap.get(split[1]).getNumber(monkeyMap);
			long num2 = monkeyMap.get(split[3]).getNumber(monkeyMap);
			switch (split[2]) {
				case "+":
					number = num1 + num2;
					break;
				case "-":
					number = num1 - num2;
					break;
				case "/":
					number = num1 / num2;
					break;
				case "*":
					number = num1 * num2;
					break;
				default:
					return 0l;
			}
			return number;
		}

		public boolean calculateWorksWithHuman(Map<String, Monkey> monkeyMap){
			if(split[0].equals("humn")) {
				return true;
			}

			if(split.length == 2)  {
				return false;
			}

			Monkey monkey1 = monkeyMap.get(split[1]);
			Monkey monkey2 = monkeyMap.get(split[3]);
			if(monkey1.calculateWorksWithHuman(monkeyMap)) {
				variableMonkey = monkey1;
				staticMonkey = monkey2;
			} else if(monkey2.calculateWorksWithHuman(monkeyMap)) {
				variableMonkey = monkey2;
				staticMonkey = monkey1;
			}

			return variableMonkey != null;
		}

	}

}
