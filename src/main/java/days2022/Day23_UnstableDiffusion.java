package days2022;

import helpers.Coordinate;
import helpers.InputProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day23_UnstableDiffusion {
	public static int day = 23;

	public static int year = 2022;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		List<String> collect = input.collect(Collectors.toList());
		List<Elf> elves = new ArrayList<>();
		for (int y = 0; y < collect.size(); y++) {
			for (int x = 0; x < collect.get(0).length(); x++) {
				if ('#' == (collect.get(y).charAt(x))) {
					elves.add(new Elf(x, y));
				}
			}
		}


		for (int i = 0; i < 10; i++) {
			System.out.println("round " + i);
			runRound(elves);
		}

		// To make sure they're on the right track, the Elves like to check after round 10 that they're making good
		// progress toward covering enough ground. To do this, count the number of empty ground tiles contained by the
		// smallest rectangle that contains every Elf. (The edges of the rectangle should be aligned to the N/S/E/W
		// directions; the Elves do not have the patience to calculate arbitrary rectangles.)
		Map<String, Elf> currentLocationElfMap = elves.stream()
				.collect(Collectors.toMap(Coordinate::getCoords, Function.identity()));
		Coordinate.printHashForCoordinate(currentLocationElfMap);

		Integer minX, maxX, minY, maxY;
		minX = maxX = minY = maxY = null;
		for(Map.Entry<String, ? extends Coordinate> entry: currentLocationElfMap.entrySet()) {
			Coordinate coordinate = entry.getValue();
			if(minX == null) {
				minX = maxX = coordinate.x;
				minY = maxY = coordinate.y;
			}
			minX = coordinate.x < minX ? coordinate.x : minX;
			maxX = coordinate.x > maxX ? coordinate.x : maxX;
			minY = coordinate.y < minY ? coordinate.y : minY;
			maxY = coordinate.y > maxY ? coordinate.y : maxY;
		}

		int groundTiles = (maxX - minX) * (maxY - minY);

		return groundTiles;
	}

	public static int runB(Stream<String> input) {
		return 0;
	}

	private static void runRound(List<Elf> elves) {
		Map<String, Elf> currentLocationElfMap = elves.stream()
				.collect(Collectors.toMap(Coordinate::getCoords, Function.identity()));
		Coordinate.printHashForCoordinate(currentLocationElfMap);

		Set<Coordinate> uniques = new HashSet<>();
		Set<Coordinate> duplicates = elves.stream().map(elf -> elf.pickNextSpot(currentLocationElfMap))
				.filter(e -> !uniques.add(e))
				.collect(Collectors.toSet());
		elves.forEach(elf -> elf.moveToNextSpot(duplicates));
		elves.forEach(Elf::rotateMovements);
	}

	private static class Elf extends Coordinate {

		List<Function<Map<String, Elf>, Boolean>> movements;

		boolean n, ne, e, se, s, sw, w, nw;

		private Coordinate nextSpot;

		public Elf(int x, int y) {
			super(x, y);
			movements = new ArrayList<>();
			movements.add(this::canMoveNorth);
			movements.add(this::canMoveSouth);
			movements.add(this::canMoveWest);
			movements.add(this::canMoveEast);
		}

		public void moveToNextSpot(Set<Coordinate> duplicates) {
			if (!duplicates.contains(nextSpot) && nextSpot != null) {
				this.x = nextSpot.x;
				this.y = nextSpot.y;
				nextSpot = null;
			}
		}

		public boolean needsToMove() {
			// if there's an elf in any location, we need to move.
			return n || nw || w || sw || s || se || e || ne;
		}

		public Coordinate pickNextSpot(Map<String, Elf> currentLocationElfMap) {

			n = currentLocationElfMap.containsKey(Coordinate.makeCoordString(x, y - 1));
			nw = currentLocationElfMap.containsKey(Coordinate.makeCoordString(x - 1, y - 1));
			ne = currentLocationElfMap.containsKey(Coordinate.makeCoordString(x + 1, y - 1));
			s = currentLocationElfMap.containsKey(Coordinate.makeCoordString(x, y + 1));
			sw = currentLocationElfMap.containsKey(Coordinate.makeCoordString(x - 1, y + 1));
			se = currentLocationElfMap.containsKey(Coordinate.makeCoordString(x + 1, y + 1));
			e = currentLocationElfMap.containsKey(Coordinate.makeCoordString(x + 1, y));
			w = currentLocationElfMap.containsKey(Coordinate.makeCoordString(x - 1, y));

			if (!needsToMove()) {
				return nextSpot;
			}

			for (Function<Map<String, Elf>, Boolean> movement : movements) {
				if (movement.apply(currentLocationElfMap)) {
					break;
				}
			}
			return nextSpot;

		}

		public void rotateMovements() {
			movements.add(movements.remove(0));
		}

		private boolean canMoveEast(Map<String, Elf> currentLocationElfMap) {
//			If there is no Elf in the E, NE, or SE adjacent positions, the Elf proposes moving east one step.
			if (!e && !ne && !se) {
				nextSpot = new Coordinate(x + 1, y);
				return true;
			}
			return false;
		}

		private boolean canMoveNorth(Map<String, Elf> currentLocationElfMap) {
			if (!n && !ne && !nw) {
				nextSpot = new Coordinate(x, y - 1);
				return true;
			}
			return false;
		}

		private boolean canMoveSouth(Map<String, Elf> currentLocationElfMap) {
			if (!s && !se && !sw) {
//			If there is no Elf in the S, SE, or SW adjacent positions, the Elf proposes moving south one step.
				nextSpot = new Coordinate(x, y + 1);
				return true;
			}
			return false;
		}

		private boolean canMoveWest(Map<String, Elf> currentLocationElfMap) {
			if (!w && !nw && !sw) {
//			If there is no Elf in the W, NW, or SW adjacent positions, the Elf proposes moving west one step.
				nextSpot = new Coordinate(x - 1, y);
				return true;
			}
			return false;
		}
	}

}
