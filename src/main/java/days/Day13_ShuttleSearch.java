package days;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day13_ShuttleSearch {

	public static void main(String[] args) {
		System.out.println("answer A: " + runA(textInput(), timestamp())); // 2165
		long startTime = System.nanoTime();
		System.out.println("answer B: " + runB(textInput()));
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;

		System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);
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
		busses.sort(Comparator.comparing(bus -> bus.id));
		Collections.reverse(busses);

		Bus highestBus = busses.get(0);
		Bus secondBus = busses.get(1);


		// Get interval between which the highest two busses id's coincide;
		long timestamp = highestBus.id - highestBus.offset;
		long interval = highestBus.id;
		do {
			if(highestBus.fitsSchedule(timestamp) && secondBus.fitsSchedule(timestamp))	break;
			timestamp += interval;
		} while (true);

		// Timestamp is now first occasion
		long firstMatch = timestamp;
		timestamp += interval;
		do {
			if(highestBus.fitsSchedule(timestamp) && secondBus.fitsSchedule(timestamp))	break;
			timestamp += interval;
		} while (true);
		interval = timestamp - firstMatch;

		// Interval is now how often the highest two busses coincide, skipping lots of options.

		do {
			if(allBussesFitSchedule(timestamp,busses)) {
				return timestamp;
			}
			timestamp += interval;
		} while (true);
//		return 0;
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
