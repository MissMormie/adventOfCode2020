package days;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day13_ShuttleSearch {

	public static void main(String[] args) {
		System.out.println("answer A: " + runA(textInput(), timestamp())); // 2165
		System.out.println("answer B: " + runB(textInput())); // 534035653563227
	}

	public static int runA(String input, int timestamp) {
		Map.Entry<String, Integer> mapEntry = Arrays.stream(input.split(","))
				.filter(s -> !"x".equals(s))
				.collect(Collectors.toMap(id -> id, id -> Integer.parseInt(id) - (timestamp % Integer.parseInt(id))))
				.entrySet()
				.stream().min(Comparator.comparingLong(Map.Entry::getValue)).get();
		return Integer.parseInt(mapEntry.getKey()) * mapEntry.getValue();
	}


	public static long runB(String input) {
		String[] instructions = input.split(",");
		List<Bus> busses = IntStream.range(0, instructions.length)
				.filter(i -> !"x".equals(instructions[i]))
				.mapToObj(index -> new Bus(Integer.parseInt(instructions[index]), index))
				.collect(Collectors.toList());

		Bus highestIdBus = busses.stream().max(Comparator.comparingLong(bus -> bus.id)).get();
		long interval = highestIdBus.id;
		long timestamp = highestIdBus.id - highestIdBus.offset;

		do {
			if(allBussesFitSchedule(timestamp,busses)) {
				return timestamp;
			}
			timestamp += interval;
		} while (true);
	}

	private static boolean allBussesFitSchedule(long timestamp, List<Bus> busses) {
		return busses.stream().allMatch(bus -> bus.fitsSchedule(timestamp));
	}

	private static class Bus {
		long id;
		long offset;

		public Bus(long id, long offset) {
			this.id = id;
			this.offset = offset;
		}

		public boolean fitsSchedule(long timeStamp) {
			return ((timeStamp + offset) % id) == 0;
		}
	}



	private static String textInput() {
		return "29,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,37,x,x,x,x,x,433,x,x,x,x,x,x,x,x,x,x,x,x,13,17,x,x,x,x,19,x,x,x,23,x,x,x,x,x,x,x,977,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,41";
	}

	private static int timestamp() {
		return 1007153;
	}
}
