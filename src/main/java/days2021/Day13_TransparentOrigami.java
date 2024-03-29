package days2021;

import helpers.Coordinate;
import helpers.InputProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day13_TransparentOrigami {
	public static int day = 13;
	public static int year = 2021;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: ");
		runB(InputProvider.getInputFor(year, day));
	}

	public static int runA(Stream<String> input) {
		Map<String, Coordinate> coordMap = new HashMap<>();
		List<String> folds = new ArrayList<>();
		createCoordMapAndFoldList(input, folds, coordMap);

		foldMap(coordMap, folds.get(0));

		return coordMap.size();
	}

	public static void runB(Stream<String> input) {
		Map<String, Coordinate> coordMap = new HashMap<>();
		List<String> folds = new ArrayList<>();
		createCoordMapAndFoldList(input, folds, coordMap);

		for (String foldLine : folds) {
			foldMap(coordMap, foldLine);
		}

		Coordinate.printHashForCoordinate(coordMap);
	}

	private static void createCoordMapAndFoldList(Stream<String> input, List<String> folds, Map<String, Coordinate> coordMap) {
		input.forEach(line -> {
			if (line.contains("fold")) {
				folds.add(line);
			} else if (line.contains(",")) {
				Coordinate coordinate = new Coordinate(line);
				coordMap.put(coordinate.getCoords(), coordinate);
			}
		});
	}

	private static void foldMap(Map<String, Coordinate> coordMap, String foldLine) {
		int fold = Integer.parseInt(foldLine.substring(foldLine.indexOf("=") + 1));
		if (foldLine.contains("x")) {
			List<Coordinate> collect = coordMap.entrySet().stream().filter(entry -> entry.getValue().x > fold).map(Map.Entry::getValue).collect(Collectors.toList());
			collect.forEach(coord -> {
						coordMap.remove(coord.getCoords());
						coord.x = fold - (coord.x - fold);
						coordMap.put(coord.getCoords(), coord);
					});
		} else {
			List<Coordinate> collect = coordMap.entrySet().stream().filter(entry -> entry.getValue().y > fold).map(Map.Entry::getValue).collect(Collectors.toList());
			collect.forEach(coord -> {
						coordMap.remove(coord.getCoords());
						coord.y = fold - (coord.y - fold);
						coordMap.put(coord.getCoords(), coord);
					});
		}
	}

}
