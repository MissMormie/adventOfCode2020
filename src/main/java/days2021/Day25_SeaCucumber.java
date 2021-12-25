package days2021;

import helpers.Coordinate;
import helpers.InputProvider;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day25_SeaCucumber {
	public static int day = 25;

	public static int year = 2021;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		Map<String, SeaBottomSpot> coordMap = new HashMap<>();
		List<String> collect = input.collect(Collectors.toList());
		for (int y = 0; y < collect.size(); y++) {
			char[] chars = collect.get(y).toCharArray();
			for (int x = 0; x < chars.length; x++) {
				SeaBottomSpot seaBottomSpot = new SeaBottomSpot(x, y, chars[x]);
				coordMap.put(seaBottomSpot.getCoords(), seaBottomSpot);
			}
		}

		Collection<SeaBottomSpot> seaBottomSpots = coordMap.values();
		seaBottomSpots.forEach(seaBottomSpot -> seaBottomSpot.getAdjacent(coordMap, collect.get(0).length() - 1, collect.size() - 1));

		int steps = 0;
		long moved = 1;
		while (moved > 0) {
			steps++;
			moved = seaBottomSpots.stream()
					.filter(SeaBottomSpot::moveEast).count();
			seaBottomSpots.forEach(SeaBottomSpot::executeMoves);

			moved += seaBottomSpots.stream().filter(SeaBottomSpot::moveSouth).count();
			seaBottomSpots.forEach(SeaBottomSpot::executeMoves);
		}
		return steps;
	}

	public static class SeaBottomSpot extends Coordinate {
		SeaBottomSpot eastSpot;

		String newStatus = "";

		SeaBottomSpot southSpot;

		String status = "EMPTY";

		public SeaBottomSpot(int x, int y, char c) {
			super(x, y);
			if (c == '>') {
				status = "EAST";
			} else if (c == 'v') {
				status = "SOUTH";
			}
			newStatus = status;
		}

		public void executeMoves() {
			status = newStatus.equals("JUSTMOVED") ? "EMPTY" : newStatus;

		}

		public void getAdjacent(Map<String, SeaBottomSpot> coordMap, int maxX, int maxY) {
			int neighbourX = x == maxX ? 0 : x + 1;
			int neighbourY = y == maxY ? 0 : y + 1;

			southSpot = coordMap.get(Coordinate.makeCoordString(x, neighbourY));
			eastSpot = coordMap.get(Coordinate.makeCoordString(neighbourX, y));
		}

		public boolean isFree() {
			return status.equals("EMPTY");
		}

		public boolean moveEast() {
			if (status.equals("EAST") && eastSpot.isFree()) {
				eastSpot.moveIn(status);
				newStatus = "JUSTMOVED";
				return true;
			}
			return false;
		}

		public void moveIn(String cucumberType) {
			newStatus = cucumberType;
		}

		public boolean moveSouth() {
			if (status.equals("SOUTH") && southSpot.isFree()) {
				southSpot.moveIn(status);
				newStatus = "JUSTMOVED";
				return true;
			}
			return false;
		}
	}
}
