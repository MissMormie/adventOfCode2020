package days2022;

import helpers.Coordinate;
import helpers.InputProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day8_TreetopTreeHouse {
	public static int day = 8;
	public static int year = 2022;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	private static int width, height;

	public static long runA(Stream<String> input) {
		Map<String, Tree> coordinateMap = getTreeMap(input);

		checkSeenOnLine(coordinateMap);
		Coordinate.printValuesForCoordinateIfBooleanTrue(coordinateMap);
		return coordinateMap.values().stream().filter(coordinate -> coordinate.booleanValue).count();
	}

	private static Map<String, Tree> getTreeMap(Stream<String> input) {
		Map<String, Tree> coordinateMap = new HashMap<>();
		List<String> lines = input.collect(Collectors.toList());

		width = lines.get(0).length();
		height = lines.size();
		for(int y = 0; y < lines.size(); y++) {
			for(int x = 0; x < lines.get(y).length(); x++) {
				Tree tree = new Tree(x, y);
				tree.intValue = lines.get(y).charAt(x) - '0';
				coordinateMap.put(tree.getCoords(), tree);
			}
		}
		return coordinateMap;
	}

	public static void checkSeenOnLine(Map<String, Tree> coordinateMap) {
		// top to bottom
		for (int x = 0; x < width; x++) {
			int lastHeight = -1;
			for (int y = 0; y < height; y++) {
				lastHeight = checkIfVisible(coordinateMap, y, lastHeight, x);
			}
		}

		// bottom to top
		for (int x = 0; x < width; x++) {
			int lastHeight = -1;
			for (int y = height -1; y >= 0; y--) {
				lastHeight = checkIfVisible(coordinateMap, y, lastHeight, x);
			}
		}

		// left to right
		for (int y = 0; y < height; y++) {
			int lastHeight = -1;
			for (int x = 0; x < width; x++) {
				lastHeight = checkIfVisible(coordinateMap, y, lastHeight, x);
			}
		}

		// right to left
		for (int y = 0; y < height; y++) {
			int lastHeight = -1;
			for (int x = width -1; x >= 0; x--) {
				lastHeight = checkIfVisible(coordinateMap, y, lastHeight, x);
			}
		}

	}

	private static int checkIfVisible(Map<String, Tree> coordinateMap, int y, int lastHeight, int x) {
		Coordinate coordinate = coordinateMap.get(Coordinate.makeCoordString(x, y));
		if (coordinate.intValue > lastHeight) {
			lastHeight = coordinate.intValue;
			coordinate.booleanValue = true;
		}
		return lastHeight;
	}


	public static int runB(Stream<String> input) {
		Map<String, Tree> treeMap = getTreeMap(input);
		return treeMap.values().stream()
				.map(tree -> tree.calculateScenicScore(treeMap))
				.mapToInt(i -> i)
				.max()
				.getAsInt();

	}

	private static class Tree extends Coordinate {

		public Tree(int x, int y) {
			super(x, y);
		}

		public int calculateScenicScore(Map<String, Tree> treeMap) {
			// look up
			int up = 0;
			for(int pathY = y -1; pathY >= 0; pathY--) {
				up++;
				Tree tree = treeMap.get(Coordinate.makeCoordString(x, pathY));
				if(tree.intValue >= intValue) {
					break;
				}
			}

			// look down
			int down = 0;
			for(int pathY = y +1; pathY < height; pathY++) {
				down++;
				Tree tree = treeMap.get(Coordinate.makeCoordString(x, pathY));
				if(tree.intValue >= intValue) {
					break;
				}
			}

			// look right
			int right = 0;
			for(int pathX = x +1; pathX < width; pathX++) {
				right++;
				Tree tree = treeMap.get(Coordinate.makeCoordString(pathX, y));
				if(tree.intValue >= intValue) {
					break;
				}
			}

			// look left
			int left = 0;
			for(int pathX = x - 1; pathX >= 0; pathX--) {
				left++;
				Tree tree = treeMap.get(Coordinate.makeCoordString(pathX, y));
				if(tree.intValue >= intValue) {
					break;
				}
			}

			return up * down * right * left;
		}


	}
}
