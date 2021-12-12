package days2021;

import helpers.InputProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12_PassagePathing {
	public static int day = 12;
	public static int year = 2021;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		Map<String, Cave> caveMap = getCaveMap(input);


		caveMap.get("start").walkAllPaths(new Path());
		return Cave.possiblePaths.size();
	}

	private static Map<String, Cave> getCaveMap(Stream<String> input) {
		List<String> collect = input.collect(Collectors.toList());
		Map<String, Cave> caveMap = new HashMap<>();
		Set<String> cavesNames = new HashSet();
		collect.forEach(line -> {
			String[] split = line.split("-");
			cavesNames.add(split[0]);
			cavesNames.add(split[1]);
		});
		cavesNames.stream().forEach(name -> caveMap.put(name, new Cave(name)));
		collect.forEach(line -> {
			String[] split = line.split("-");
			caveMap.get(split[0]).addAdjacentCave(split[1], caveMap);
			caveMap.get(split[1]).addAdjacentCave(split[0], caveMap);
		});
		return caveMap;
	}


	public static int runB(Stream<String> input) {
		Map<String, Cave> caveMap = getCaveMap(input);


		caveMap.get("start").walkAllPathsB(new Path());
		return Cave.possiblePathsB.size();
	}

	public static class Cave {
		List<Cave> adjacentCaves = new ArrayList();
		String name;
		boolean isSmallCave;

		public Cave(String name) {
			this.name = name;
			if(name.equals("start") || name.equals("end")) {
				isSmallCave = false;
			} else {
				isSmallCave = name.equals(name.toLowerCase());
			}
		}

		public void addAdjacentCave(String name, Map<String, Cave> caveMap) {
			if(name.equals("start")) {
				// cannot go back to start (i think..)
				return;
			}
			adjacentCaves.add(caveMap.get(name));
		}

		public static List<Path> possiblePaths = new ArrayList<>();
		public void walkAllPaths(Path path) {
			if (name.equals("end")) {
				path.addPathSegment(name, isSmallCave);
				possiblePaths.add(path);
				return;
			}
			if(isSmallCave && path.passedThisSmallCave(name)) {
				// can only pass 1 small cave. Is this correct?
				return;
			}
			path.addPathSegment(name, isSmallCave);
			adjacentCaves.forEach(cave -> {
				cave.walkAllPaths(path.clone());
			});
		}

		public static List<Path> possiblePathsB = new ArrayList<>();
		public void walkAllPathsB(Path path) {
			if (name.equals("end")) {
				path.addPathSegment(name, isSmallCave);
				possiblePathsB.add(path);
				return;
			}
			if(isSmallCave && !path.mayPassThisSmallCave(name)) {
				// can only pass 1 small cave. Is this correct?
				return;
			}
			path.addPathSegment(name, isSmallCave);
			adjacentCaves.forEach(cave -> {
				cave.walkAllPathsB(path.clone());
			});
		}
	}

	public static class Path {
		String path = "";
		List<String> smallCavesPassed = new ArrayList<>();
		boolean passedSmallCaveTwice = false;

		public Path clone() {
			Path newPath = new Path();
			newPath.path = path;
			newPath.smallCavesPassed.addAll(smallCavesPassed);
			newPath.passedSmallCaveTwice = passedSmallCaveTwice;
			return newPath;
		}

		public boolean passedThisSmallCave(String caveName) {
			return smallCavesPassed.contains(caveName);
		}

		public boolean mayPassThisSmallCave(String caveName) {
			return !passedThisSmallCave(caveName) || !passedSmallCaveTwice;
		}

		public void addPathSegment(String segment, boolean isSmallCave) {
			if(isSmallCave) {
				if(passedThisSmallCave(segment)) {
					passedSmallCaveTwice = true;
				} else {
					smallCavesPassed.add(segment);
				}
			}
			path = path + segment + "-";
		}
	}
}
