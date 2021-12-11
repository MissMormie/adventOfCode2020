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

public class Day11_DumboOctopus {
	public static int day = 11;
	public static int year = 2021;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day), 100));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input, int steps) {
		List<String> collect = input.collect(Collectors.toList());
		Map<String, Octopus> coordMap = new HashMap<>();
		List<Octopus> octopi = new ArrayList<>();
		getCoordMap(collect, coordMap, octopi);

		octopi.forEach(octopus -> octopus.getSurroundingOctopi(coordMap));
		// do 100 steps
		for (int i = 0; i < steps; i++) {
			octopi.forEach(Octopus::increaseEnergy);
			octopi.forEach(Octopus::resetEnergyIfNeeded);
		}

		return octopi.stream().map(octopus -> octopus.flashes).mapToInt(v ->v).sum();

	}

	private static void getCoordMap(List<String> collect, Map<String, Octopus> coordMap, List<Octopus> octopi) {
		for(int y = 0; y < collect.size(); y++) {
			char[] chars = collect.get(y).toCharArray();
			for (int x = 0; x < chars.length; x++) {
				Octopus octopus = new Octopus(x, y, chars[x] - 48);
				coordMap.put(octopus.getCoords(), octopus);
				octopi.add(octopus);
			}
		}
	}


	public static int runB(Stream<String> input) {
		List<String> collect = input.collect(Collectors.toList());
		Map<String, Octopus> coordMap = new HashMap<>();
		List<Octopus> octopi = new ArrayList<>();
		getCoordMap(collect, coordMap, octopi);

		octopi.forEach(octopus -> octopus.getSurroundingOctopi(coordMap));

		int steps = 0;
		do {
			steps++;
			octopi.forEach(Octopus::increaseEnergy);
		} while(octopi.stream().filter(Octopus::resetEnergyIfNeeded).count() < 100);

		return steps;
	}

	public static class Octopus extends Coordinate {
		int energy;
		int flashes = 0;
		boolean hasJustFlashed = false;
		List<Octopus> octopi = new ArrayList<>();

		public Octopus(int x, int y, int energy) {
			super(x, y);
			this.energy = energy;
		}

		public void getSurroundingOctopi(Map<String, Octopus> coordMap) {
			getAdjacent(coordMap);
			for(Coordinate coordinate : adjacentCoords) {
				octopi.add(coordMap.get(coordinate.getCoords()));
			}
		}

		public Octopus(String xCommaY) {
			super(xCommaY);
		}

		public void increaseEnergy() {
			if(hasJustFlashed) {
				return;
			}
			energy++;
			if (energy ==10) {
				flashes++;
				hasJustFlashed = true;
				octopi.forEach(Octopus::increaseEnergy);
			}
		}

		public boolean hasJustReset() {
			return energy == 0;
		}

		public boolean resetEnergyIfNeeded() {
			if(energy == 10) {
				hasJustFlashed = false;
				energy = 0;
				return true;
			}
			return false;
		}
	}
}
