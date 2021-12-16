package days2021;

import helpers.Coordinate;
import helpers.InputProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day9_SmokeBasin {
	public static int day = 9;
	public static int year = 2021;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		Map<String, LavaTube> lavaTubeMap = getLavaTubeMap(input);

		return lavaTubeMap.entrySet().stream()
				.map(tube -> tube.getValue().getRisk(lavaTubeMap))
				.mapToInt(v-> v).sum();
	}

	public static int runB(Stream<String> input) {
		// starting the maps without 9 means I only have to count connected lavatubes.
		Map<String, LavaTube> lavaTubeMap = getLavaTubeMapWithout9(input);

		// Start with a random lavaTube, check adjacent coords if any lavatubes connect and adds them to the basin.
		// Keeps doing this until no more new lavatubes connect to the basin.
		// This could've been a funky iterative function. But it's not.
		List<Integer> basinSizeList = new ArrayList<>();
		while(!lavaTubeMap.isEmpty()) {
			Set<LavaTube> basin = new HashSet<>();
			basin.add(lavaTubeMap.entrySet().iterator().next().getValue());
			int basinStartSize ;
			do {
				basinStartSize = basin.size();
				Set<LavaTube> addToBasin = new HashSet<>();

				basin.stream().map(lavaTube -> lavaTube.getOctagonalAdjacentCoords(lavaTubeMap))
						.flatMap(Collection::stream)
						.forEach(coordinates -> {
							addToBasin.add(lavaTubeMap.get(coordinates.getCoords()));
							lavaTubeMap.remove(coordinates.getCoords());
						});
				basin.addAll(addToBasin);
			} while (basin.size() != basinStartSize);

			basinSizeList.add(basin.size());
		}

		basinSizeList.sort(Integer::compareTo);
		int size = basinSizeList.size();
		return basinSizeList.get(size -3) * basinSizeList.get(size -2) * basinSizeList.get(size -1);
	}

	private static Map<String, LavaTube> getLavaTubeMap(Stream<String> input) {
		Map<String, LavaTube> lavaTubeMap = new HashMap<>();
		List<String> lines = input.collect(Collectors.toList());
		for(int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
			char[] chars = lines.get(lineIndex).toCharArray();
			for (int charIndex = 0; charIndex < chars.length; charIndex++) {
				LavaTube lavaTube = new LavaTube(lineIndex, charIndex, chars[charIndex]);
				lavaTubeMap.put(lavaTube.getCoords(), lavaTube);
			}
		}
		return lavaTubeMap;
	}


	private static Map<String, LavaTube> getLavaTubeMapWithout9(Stream<String> input) {
		Map<String, LavaTube> lavaTubeMap = new HashMap<>();
		List<String> lines = input.collect(Collectors.toList());
		for(int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
			char[] chars = lines.get(lineIndex).toCharArray();
			for (int charIndex = 0; charIndex < chars.length; charIndex++) {
				if(chars[charIndex] != '9') {
					LavaTube lavaTube = new LavaTube(lineIndex, charIndex, chars[charIndex]);
					lavaTubeMap.put(lavaTube.getCoords(), lavaTube);
				}
			}
		}
		return lavaTubeMap;
	}

	public static class LavaTube extends Coordinate{

		public LavaTube(int x, int y, char value) {
			super(x,y);
			this.intValue = Integer.parseInt(Character.toString(value));
		}

		public int getRisk(Map<String, LavaTube> lavaTubeMap) {
			List<? extends Coordinate> octagonalAdjacentCoords = getOctagonalAdjacentCoords(lavaTubeMap);
			return octagonalAdjacentCoords.stream().filter(coordinate -> coordinate.intValue <= this.intValue).findAny()
					.map(optional -> 0)
					.orElse(intValue +1);
		}
	}

}
