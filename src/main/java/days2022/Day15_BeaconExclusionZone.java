package days2022;

import helpers.Coordinate;
import helpers.InputProvider;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day15_BeaconExclusionZone {
	public static int day = 15;

	public static int year = 2022;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day), 2000000));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day), 4000000));
	}

	public static int runA(Stream<String> input, int row) {
		return input.map(Sensor::new)
				.map(sensor -> sensor.getAllEmptyPointsInViewOnRow(row))
				.flatMap(Set::stream)
				.collect(Collectors.toSet()).size();

	}

	public static long runB(Stream<String> input, int size) {
		List<Sensor> sensors = input.map(Sensor::new).collect(Collectors.toList());

		//Your handheld device indicates that the distress signal is coming from a beacon nearby.
		// The distress beacon is not detected by any sensor, but the distress beacon must have x
		// and y coordinates each no lower than 0 and no larger than 4000000.
		for (int y = 0; y <= size; y++) {
			int emptypointInRow = getEmptypointInRow(y, sensors, size);
			if (emptypointInRow > -1) {
				// To isolate the distress beacon's signal, you need to determine its tuning frequency,
				// which can be found by multiplying its x coordinate by 4000000 and then adding its y coordinate.
				return 4000000l * emptypointInRow + y;
			}
		}

		int x = 0;
		int y = 0;
		return 4000000l * x + y;
	}

	private static int getEmptypointInRow(int row, List<Sensor> sensors, int size) {
		List<Range> minRanges = sensors.stream()
				.filter(sensor -> sensor.containsRange(row))
				.map(sensor -> sensor.getRange(row))
				.sorted(Comparator.comparing(Range::getMinX))
				.collect(Collectors.toList());

		if (minRanges.get(0).minX > 0) {
			return 0;
		}

		for (int x = 0; x < minRanges.size() - 1; x++) {
			if (minRanges.get(x).maxX >= minRanges.get(x + 1).minX) {
				if (minRanges.get(x).maxX > minRanges.get(x + 1).maxX) {
					// if the line completely overlap, increase the range of the smaller one for the next cycle.
					minRanges.get(x + 1).maxX = minRanges.get(x).maxX;
				}
			} else {
				// found not overlapping point
				return minRanges.get(x).maxX + 1;
			}

		}

		List<Range> maxRanges = sensors.stream()
				.map(sensor -> sensor.getRange(row))
				.sorted(Comparator.comparing(Range::getMaxX))
				.collect(Collectors.toList());
		if (maxRanges.get(maxRanges.size() - 1).maxX < size) {
			return size;
		}
		// not able to minify ranges anymore
		return -1;

	}

	public static class Range {
		int maxX;

		int minX;

		public Range(int minX, int maxX) {
			this.minX = minX;
			this.maxX = maxX;
		}

		public int getMaxX() {
			return maxX;
		}

		public int getMinX() {
			return minX;
		}

	}

	public static class Sensor {
		Coordinate beacon;

		Coordinate sensor;

		public Sensor(String line) {
			String[] split = line.split("(Sensor at x=|, y=|: closest beacon is at x=)");
			sensor = new Coordinate(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
			beacon = new Coordinate(Integer.parseInt(split[3]), Integer.parseInt(split[4]));
		}

		public Set<Integer> getAllEmptyPointsInViewOnRow(int row) {
			int manhattanDistance = sensor.getManhattanDistance(beacon);
			int halfRange = manhattanDistance - Math.abs(row - sensor.y);
			// optimalization possible. Just keep the ranges and work from there. Do this if it's too slow
			Set<Integer> seenPositions = IntStream.rangeClosed(sensor.x - halfRange, sensor.x + halfRange)
					.boxed()
					.collect(Collectors.toSet());

			// a beacon may be in view but it's not empty.
			if (beacon.y == row) {
				seenPositions.remove(beacon.x);
			}
			return seenPositions;
		}

		public boolean containsRange(int row) {
			int manhattanDistance = sensor.getManhattanDistance(beacon);
			int halfRange = manhattanDistance - Math.abs(row - sensor.y);
			return halfRange >= 0;
		}

		public Range getRange(int row) {
			int manhattanDistance = sensor.getManhattanDistance(beacon);
			int halfRange = manhattanDistance - Math.abs(row - sensor.y);
			return new Range(sensor.x - halfRange, sensor.x + halfRange);
		}
	}
}
