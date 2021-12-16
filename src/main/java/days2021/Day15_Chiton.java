package days2021;

import helpers.Coordinate;
import helpers.InputProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day15_Chiton {

	public static int day = 15;

	public static int year = 2021;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		List<String> collect = input.collect(Collectors.toList());
		Map<String, CaveVertex> caveVertexMap = new HashMap<>();
		int maxX = collect.size() - 1;
		int maxY = 0;
		for (int x = 0; x < collect.size(); x++) {
			char[] chars = collect.get(x).toCharArray();
			maxY = chars.length -1;
			for (int y = 0; y < chars.length; y++) {
				CaveVertex caveVertex = new CaveVertex(x, y, chars[y], 0);
				caveVertexMap.put(caveVertex.getCoords(), caveVertex);
			}
		}

		return findShortestPath(caveVertexMap, maxX, maxY);
		// too high 2147483647
	}

	private static int findShortestPath(Map<String, CaveVertex> caveVertexMap, int maxX, int maxY) {
		CaveVertex start = caveVertexMap.get(Coordinate.makeCoordString(0,0));
		CaveVertex end = caveVertexMap.get(Coordinate.makeCoordString(maxX, maxY));
		start.shortestDistance = 0;

		List<CaveVertex> caveVertices = caveVertexMap.values().stream().map(caveVertex -> {
			caveVertex.getNeighbours(caveVertexMap);
			return caveVertex;
		}).collect(Collectors.toList());

		while(!caveVertices.isEmpty()) {
			caveVertices.sort(CaveVertex.distanceComparator);
			CaveVertex caveVertex = caveVertices.remove(0);
			caveVertex.updateNeighbours();
		}

		return end.shortestDistance;
	}


	public static int runB(Stream<String> input) {
		List<String> collect = input.collect(Collectors.toList());
		Map<String, CaveVertex> caveVertexMap = new HashMap<>();
		int maxX = collect.size() * 5 - 1;
		int maxY = 0;
		for (int x = 0; x < collect.size() * 5; x++) {
			char[] chars = collect.get(x % collect.size()).toCharArray();
			maxY = chars.length * 5 -1;
			for (int y = 0; y < chars.length * 5; y++) {
				int difficultyIncrease = x / collect.size() + y / chars.length;
				CaveVertex caveVertex = new CaveVertex(x, y, chars[y%chars.length], difficultyIncrease);
				caveVertexMap.put(caveVertex.getCoords(), caveVertex);
			}
		}

		return findShortestPath(caveVertexMap, maxX, maxY);

	}

	public static class CaveVertex extends Coordinate {
		int difficulty;
		int shortestDistance = Integer.MAX_VALUE;
		boolean visited = false;
		List<CaveVertex> adjacentCavern = new ArrayList<>();

		public CaveVertex(int x, int y, char difficulty, int difficultyIncrease) {
			super(x, y);
			this.difficulty = (difficulty - '0' + difficultyIncrease) % 9;
			if(this.difficulty == 0) {
				this.difficulty = 9;
			}
		}

		public void updateDistance(int distance) {
			if(visited) {
				return;
			}
			if(distance + difficulty < shortestDistance) {
				shortestDistance = distance + difficulty;
			}
		}

		public void getNeighbours(Map<String, CaveVertex> map) {
			adjacentCavern = (List<CaveVertex>) this.getOctagonalAdjacentCoords(map);
		}

		public void updateNeighbours() {
			adjacentCavern.forEach(coord -> coord.updateDistance(shortestDistance));
			visited = true;
		}

		public static Comparator<CaveVertex> distanceComparator = Comparator.comparingInt(o -> o.shortestDistance);
	}
}
