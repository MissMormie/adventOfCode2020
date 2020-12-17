package days;

import helpers.Coordinate3D;
import helpers.Coordinate4D;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day17_ConwayCubes {

	public static void main(String[] args) {
		System.out.println("answer A: " + runA(textInput(), 6));
		System.out.println("answer B: " + runB(textInput(), 6));
	}

	public static Map<String, Coordinate3DConway> getBigMap(String input, int steps) {
		String[] lines = input.split("\n");
		int xLength = lines.length;
		int yLength = lines[0].length();
		return IntStream.range(-steps, yLength + steps + 1)
				.mapToObj(y ->
						IntStream.range(-steps, xLength + steps + 1)
								.mapToObj(x ->
										IntStream.range(-steps, 1 + steps + 1)
												.mapToObj(z -> new Coordinate3DConway(x, y, z))
												.collect(Collectors.toList()))
								.flatMap(List::stream)
								.collect(Collectors.toList()))
				.flatMap(List::stream)
				.collect(Collectors.toMap(Coordinate3D::getCoords, coord -> coord));
	}

	public static Map<String, Coordinate4DConway> getBigMap4D(String input, int steps) {
		String[] lines = input.split("\n");
		int xLength = lines.length;
		int yLength = lines[0].length();
		return IntStream.range(-steps, yLength + steps + 1)
				.mapToObj(y ->
						IntStream.range(-steps, xLength + steps + 1)
								.mapToObj(x ->
										IntStream.range(-steps, 1 + steps + 1)
												.mapToObj(t ->
														IntStream.range(-steps, 1 + steps + 1)
																.mapToObj(z -> new Coordinate4DConway(x, y, z, t))
																.collect(Collectors.toList()))
												.flatMap(List::stream)
												.collect(Collectors.toList()))
								.flatMap(List::stream)
								.collect(Collectors.toList()))
				.flatMap(List::stream)
				.collect(Collectors.toMap(Coordinate3D::getCoords, coord -> coord));
	}



	public static long runA(String input, int steps) {
		Map<String, Coordinate3DConway> coordinate3DMap = getBigMap(input, steps);
		setInitialCubesActive(input, coordinate3DMap);
		coordinate3DMap.values().stream().forEach(c -> c.setAdjacentCoords(coordinate3DMap));
		for (int i = 0; i < steps; i++) {
			coordinate3DMap.values().stream().forEach(
					c -> c.calculateNewState());
			coordinate3DMap.values().stream().forEach(Coordinate3DConway::updateState);
		}

		return coordinate3DMap.values().stream().filter(c -> c.booleanValue).count();
	}

	public static long runB(String input, int steps) {
		Map<String, Coordinate4DConway> coordinate4DMap = getBigMap4D(input, steps);
		setInitialHyperCubesActive(input, coordinate4DMap);
		coordinate4DMap.values().stream().forEach(c -> c.setAdjacentCoords(coordinate4DMap));
		for (int i = 0; i < steps; i++) {
			coordinate4DMap.values().stream().forEach(
					c -> c.calculateNewState());
			coordinate4DMap.values().stream().forEach(Coordinate4DConway::updateState);
		}

		return coordinate4DMap.values().stream().filter(c -> c.booleanValue).count();
	}

	private static void setInitialCubesActive(String input, Map<String, Coordinate3DConway> map) {
		String[] lines = input.split("\n");

		IntStream.range(0, lines.length)
				.forEach(y -> {
					char[] chars = lines[y].toCharArray();
					IntStream.range(0, chars.length)
							.forEach(x -> {
								if (chars[x] == '#') {
									map.get(Coordinate3D.makeCoordString(x, y, 0)).booleanValue = true;
								}
							});
				});
	}

	private static void setInitialHyperCubesActive(String input, Map<String, Coordinate4DConway> map) {
		String[] lines = input.split("\n");

		IntStream.range(0, lines.length)
				.forEach(y -> {
					char[] chars = lines[y].toCharArray();
					IntStream.range(0, chars.length)
							.forEach(x -> {
								if (chars[x] == '#') {
									map.get(Coordinate4D.makeCoordString(x, y, 0, 0)).booleanValue = true;
								}
							});
				});
	}

	private static String textInput() {
		return "##...#.#\n" +
				"####.#.#\n" +
				"#...####\n" +
				"..#.#.#.\n" +
				"####.#..\n" +
				"#.#.#..#\n" +
				".####.##\n" +
				"..#...##";

	}

	public static class Coordinate3DConway extends Coordinate3D {
		List<Coordinate3DConway> adjacentCoords = new ArrayList<>();

		boolean newState;

		public Coordinate3DConway(int x, int y, int z) {
			super(x, y, z);
		}

		public void calculateNewState() {
			long count = adjacentCoords.stream().filter(c -> c.booleanValue).count();
			newState = false;
			if (booleanValue && (count == 2l || count == 3l)) {
				newState = true;
			} else if (!booleanValue && count == 3l) {
				newState = true;
			}
		}

		public void setAdjacentCoords(Map<String, Coordinate3DConway> coordinate3DMap) {
			this.getSurroundingCoords().stream().forEach(s -> {
				if (coordinate3DMap.containsKey(s)) {
					adjacentCoords.add(coordinate3DMap.get(s));
				}
			});
		}

		public void updateState() {
			booleanValue = newState;
		}

	}

	public static class Coordinate4DConway extends Coordinate4D {
		List<Coordinate4DConway> adjacentCoords = new ArrayList<>();

		boolean newState;

		public Coordinate4DConway(int x, int y, int z, int t) {
			super(x, y, z, t);
		}

		public void calculateNewState() {
			long count = adjacentCoords.stream().filter(c -> c.booleanValue).count();
			newState = false;
			if (booleanValue && (count == 2l || count == 3l)) {
				newState = true;
			} else if (!booleanValue && count == 3l) {
				newState = true;
			}
		}

		public void setAdjacentCoords(Map<String, Coordinate4DConway> coordinate3DMap) {
			this.getSurroundingCoords().stream().forEach(s -> {
				if (coordinate3DMap.containsKey(s)) {
					adjacentCoords.add(coordinate3DMap.get(s));
				}
			});
		}

		public void updateState() {
			booleanValue = newState;
		}
	}
}