package days2022;

import helpers.Coordinate;
import helpers.InputProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Day14_RegolithReservoir {
	public static int day = 14;

	public static int year = 2022;

	private static int lowestPoint = 1000;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		Map<String, Cave> coordinateMap = new HashMap<>();

		input.forEach(line -> {
			String[] split = line.split(" -> ");
			for (int i = 1; i < split.length; i++) {
				addAllCavesInLine(split[i - 1], split[i], coordinateMap);
			}});

		int droppedSand = 0;
		while(dropSand(coordinateMap)) {
			droppedSand++;
		}

		return droppedSand;
	}

	public static int runB(Stream<String> input) {
		Map<String, Cave> coordinateMap = new HashMap<>();

		input.forEach(line -> {
			String[] split = line.split(" -> ");
			for (int i = 1; i < split.length; i++) {
				addAllCavesInLine(split[i - 1], split[i], coordinateMap);
			}});

		// lowest point is two below lowest found point
		lowestPoint = coordinateMap.values().stream().mapToInt(coord -> coord.y).max().getAsInt() + 2;

		// add bottom line.
		for(int x = -10; x <= 1010; x++){
			coordinateMap.put(Coordinate.makeCoordString(x, lowestPoint), new Cave(x, lowestPoint));
		}

//		Coordinate.printValuesForCoordinateMap(coordinateMap, coord -> "#");
		int droppedSand = 0;
		String sandDropPoint = Coordinate.makeCoordString(500,0);

		while(!coordinateMap.containsKey(sandDropPoint)) {
			dropSand(coordinateMap);
			droppedSand++;
		}
		Coordinate.printValuesForCoordinateMap(coordinateMap, coord -> "#");

		return droppedSand;
	}


	private static boolean dropSand(Map<String,Cave> coordinateMap) {
		// could add something smart here, so that any point every passed through by the
		// sand becomes the new starting point. Saves a lot of repetition. Doing that if this
		// is too slow.
		Cave sand = new Cave(500, 0);
		while(sand.y < lowestPoint) {
			if(!coordinateMap.containsKey(sand.getPointBelow())){
				sand.y++;
				continue;
			}
			if(!coordinateMap.containsKey(sand.getPointLeftBelow())) {
				sand.y++;
				sand.x--;
				continue;
			}
			if(!coordinateMap.containsKey(sand.getPointRightBelow())) {
				sand.y++;
				sand.x++;
				continue;
			}
			coordinateMap.put(sand.getCoords(), sand);
			return true;
		}
		return false;
	}

	private static void addAllCavesInLine(String startString, String finishString,
												Map<String, Cave> coordinateMap) {
		Cave start = new Cave(startString);
		Cave finish = new Cave(finishString);

		// all straight lines, include start and finish because it's easy
		if (start.x == finish.x) {
			for (int y = start.y; y <= finish.y; y++) {
				coordinateMap.put(Coordinate.makeCoordString(start.x, y), new Cave(start.x, y));
			}
			for (int y = start.y; y >= finish.y; y--) {
				coordinateMap.put(Coordinate.makeCoordString(start.x, y), new Cave(start.x, y));
			}
			return;
		}

		if (start.y == finish.y) {
			for (int x = start.x; x <= finish.x; x++) {
				coordinateMap.put(Coordinate.makeCoordString(x, start.y), new Cave(x, start.y));
			}
			for (int x = start.x; x >= finish.x; x--) {
				coordinateMap.put(Coordinate.makeCoordString(x, start.y), new Cave(x, start.y));
			}

			return;
		}

		throw new IllegalStateException("No straight line");
	}

	private static class Cave extends Coordinate {

		public Cave(int x, int y) {
			super(x, y);
		}

		public Cave(String xCommaY) {
			super(xCommaY);
		}

		public String getPointBelow() {
			return makeCoordString(x, y +1);
		}

		public String getPointLeftBelow() {
			return makeCoordString(x -1, y +1);
		}

		public String getPointRightBelow() {
			return makeCoordString(x +1, y +1);
		}

	}
}
