package days2022;

import helpers.Coordinate;
import helpers.InputProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12_HillClimbingAlgorithm {
	public static int day = 12;

	public static int year = 2022;

	private static Comparator<MountainPath> comparator = Comparator.comparingInt(o -> o.distance);

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		Map<String, MountainPath> mountainPathMap = getMountainPathMap(input);

		List<MountainPath> paths = new ArrayList<>(mountainPathMap.values());
		MountainPath mountainPath = paths.stream().min(comparator).get();

		// get the path with the lowest distance so far.
		mountainPath = runForStartPoint(paths, mountainPath);

		return mountainPath.distance;
	}

	public static int runB(Stream<String> input) {
		// need to loop over the input multiple times, does not work with stream.
		List<String> collect = input.collect(Collectors.toList());
		Map<String, MountainPath> mountainPathMap = getMountainPathMap(collect.stream());

		List<String> startingpoints = mountainPathMap.values().stream()
				.filter(path -> path.height == 'a')
				.map(Coordinate::getCoords)
				.collect(Collectors.toList());

		List<Integer> distances = new ArrayList<>();
		for (String startPoint : startingpoints) {
			// reset map
			mountainPathMap = getMountainPathMap(collect.stream());
			List<MountainPath> paths = new ArrayList<>(mountainPathMap.values());

			// get the path with the lowest distance so far.
			MountainPath mountainPath = runForStartPoint(paths, mountainPathMap.get(startPoint));
			distances.add(mountainPath.distance);

			// And here should be something smart that checks if a staringpoint is part of the path followed and if so
			// remove it from the list to check.
			// or not, and just wait a bit longer for the answer.. I'm going for that option.
		}
		return distances.stream().mapToInt(i -> i).min().getAsInt();
	}

	private static Map<String, MountainPath> getMountainPathMap(Stream<String> input) {
		List<String> collect = input.collect(Collectors.toList());
		Map<String, MountainPath> mountainPathMap = new HashMap<>();
		for (int y = collect.size() - 1; y >= 0; y--) {
			for (int x = 0; x < collect.get(y).length(); x++) {
				MountainPath mountainPath = new MountainPath(x, y, collect.get(y).charAt(x), false);
				mountainPathMap.put(mountainPath.getCoords(), mountainPath);
			}
		}

		mountainPathMap.values().forEach(path -> path.addAdjacentPaths(mountainPathMap));
		return mountainPathMap;
	}

	private static MountainPath runForStartPoint(List<MountainPath> paths, MountainPath startPoint) {
		MountainPath currentPath = startPoint;
		currentPath.distance = 0;
		while (!currentPath.isEndpoint()) {
			paths.remove(currentPath);
			currentPath.visitAdjacentPaths();
			currentPath = paths.stream().min(comparator).get();
		}
		return currentPath;
	}

	private static class MountainPath extends Coordinate {
		Set<MountainPath> adjacentPaths;

		int distance = 10_000_000;

		char height;

		boolean isEndpoint;

		boolean visited;

		public MountainPath(int x, int y, char height, boolean runB) {
			super(x, y);
			if (height == 'S' && !runB) {
				distance = 0;
				height = 'a';
			} else if (height == 'E') {
				height = 'z';
				isEndpoint = true;
			}
			this.height = height;
			this.intValue = height - 96;
		}

		public void addAdjacentPaths(Map<String, MountainPath> mountainPathMap) {
			adjacentPaths = getOctagonalAdjacentCoords(mountainPathMap).stream().map(coord -> mountainPathMap.get(coord.getCoords())).filter(path -> path.height - 1 <= this.height) // can only move to paths that aren't higher
					.collect(Collectors.toSet());
		}

		public void visitAdjacentPaths() {
			adjacentPaths.forEach(path -> path.updatePath(distance + 1));
			visited = true;
		}

		private boolean isEndpoint() {
			return isEndpoint;
		}

		private void updatePath(int newDistance) {
			if (!visited && newDistance < distance) {
				this.distance = newDistance;
			}
		}

	}
}
