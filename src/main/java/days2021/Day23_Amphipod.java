package days2021;

import helpers.Coordinate;
import helpers.InputProvider;

import java.awt.*;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static helpers.Coordinate.*;

public class Day23_Amphipod {
	public static int day = 23;
	public static int year = 2021;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		List<String> collect = input.collect(Collectors.toList());
		getEnergy(collect);

		return minEnergy;
	}

	private static void getEnergy(List<String> collect) {
		minEnergy = Integer.MAX_VALUE;
		Map<String, Location> locationMap = new HashMap<>();
		for(int x =1; x<12; x++) {
			Location hallway;
			if(x == 3 || x== 5 || x==7 || x == 9) {
				hallway = new Location(x, 1, "blocked-hallway");
			} else {
				hallway = new Location(x, 1, "hallway");
			}

			locationMap.put(hallway.getCoords(), hallway);
		}

		for(int y = 2; y < collect.size() -1; y++ ) {
			Location station = new Location(3, y, "A");
			locationMap.put(station.getCoords(), station);

			station = new Location(5, y, "B");
			locationMap.put(station.getCoords(), station);

			station = new Location(7, y, "C");
			locationMap.put(station.getCoords(), station);

			station = new Location(9, y, "D");
			locationMap.put(station.getCoords(), station);
		}

		Map<String, String> amphipodMap = new HashMap<>();
		for(int y = 0; y < collect.size(); y++) {
			char[] chars = collect.get(y).toCharArray();
			for (int x = 0; x < chars.length; x++) {
				if(chars[x] != '#' && chars[x] != '.' && chars[x] != ' ') {
					amphipodMap.put(makeCoordString(x,y), "" + chars[x]);
				}
			}
		}

		locationMap.values().forEach(location -> location.getSurroundingLocations(locationMap));

		Burrow burrow = new Burrow(locationMap);
		burrow.doSteps(amphipodMap,0);
	}


	public static int runB(Stream<String> input) {
		List<String> collect = input.collect(Collectors.toList());
		collect.add(3, "  #D#C#B#A#");
		collect.add(4, "  #D#B#A#C#");
		getEnergy(collect);

		return minEnergy;
	}

	public static int minEnergy = Integer.MAX_VALUE;

	public static class Burrow {
		String[] options = new String[] {"A", "B", "C", "D"};
		Map<String, Location> locationMap;
		Map<String, List<Location>> stationsMap = new HashMap<>();

		public void doSteps(Map<String, String> amphipodMap, int energy) {
			Map<String, Boolean> availabilityMap = getAvailability(amphipodMap);
			Map<String, Set<Location>> possibleMoves = amphipodMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> getPossibleMoves(amphipodMap, entry, availabilityMap)));
			long options = possibleMoves.values().stream().flatMap(Collection::stream).count();

			if(options == 0) {
				if(isSolved(amphipodMap)) {
					if(energy < minEnergy) {
						minEnergy = energy;
					}
				} else {
					return;
				}
			}

			// find if any moves go straight to station, then do that one only.
			Map<String, Set<Location>> map = null;
			for (Map.Entry<String, Set<Location>> movesEntry : possibleMoves.entrySet()) {
				// if station space, theres only 1 value in the list, the rest is already filtered out.
				if (movesEntry.getValue().size() == 1) {
					Location location1 = movesEntry.getValue().stream().findFirst().get();
					if (location1.type.equals(amphipodMap.get(movesEntry.getKey()))) {
						map = new HashMap<>();
						Set<Location> locations = new HashSet<>();
						locations.add(location1);
						map.put(movesEntry.getKey(), locations);
					}
				}
			}
			if(map != null) {
				possibleMoves = map;
			}

			for (Map.Entry<String, Set<Location>> movesEntry : possibleMoves.entrySet()) {
				for (Location location : movesEntry.getValue()) {
					Map<String, String> copyMap = copyMap(amphipodMap);
					String coordOfMovingAmphipod = movesEntry.getKey();
					String amphipodType = copyMap.get(coordOfMovingAmphipod);
					// calculate energy here.
					copyMap.remove(coordOfMovingAmphipod);
					copyMap.put(location.getCoords(), amphipodType);
					doSteps(copyMap, energy + energyCostOfMove(coordOfMovingAmphipod, location, amphipodType));
				}
			}

		}

		public boolean isSolved(Map<String, String> amphipodMap) {
			return amphipodMap.entrySet().stream()
					.allMatch(entry -> locationMap.get(entry.getKey()).type.equals(entry.getValue()));
		}

		public int energyCostOfMove(String startCoord, Location endCoord, String amphipodType) {
			Coordinate coordinate = new Coordinate(startCoord);
			int moveX = Math.abs(coordinate.x - endCoord.x);
			int moveY = coordinate.y -1 + endCoord.y - 1;
			int steps = moveX + moveY;

			switch (amphipodType) {
				case "A": return steps;
				case "B": return steps * 10;
				case "C": return steps * 100;
				case "D": return steps * 1000;
			}
			return 9000000;
		}

		public Map<String,String> copyMap(Map<String, String> amphipodMap) {
			HashMap<String, String> clone = new HashMap<>();
			for(Map.Entry<String, String> entry : amphipodMap.entrySet()) {
				clone.put(entry.getKey(), entry.getValue());
			}
			return clone;

		}

		public Set<Location> getPossibleMoves(Map<String, String> amphipodMap, Map.Entry<String, String> entryCoordType, Map<String, Boolean> availabilityMap) {
			LinkedList<Location> locationsList = new LinkedList<>();
			Location startLocation = locationMap.get(entryCoordType.getKey());
			locationsList.addAll(startLocation.adjacentLocations);
			Set<Location> locations = new HashSet<>();
			Set<Location> consideredLocation = new HashSet<>();
			String amphipodType = entryCoordType.getValue();
			if(availabilityMap.get(amphipodType) && stationsMap.get(amphipodType).contains(startLocation)) {
				// amphipod is already home;
				return locations;
			}

			while(!locationsList.isEmpty()) {
				Location location = locationsList.removeFirst();
				if(consideredLocation.contains(location)) {
				} else if(amphipodMap.containsKey(location.getCoords())) {
					// spot is taken
				} else if(location.type.equals("blocked-hallway")) {
					locationsList.addAll(location.adjacentLocations);
					// filter out already connected location
				} else if(location.type.equals("hallway")) {
					locations.add(location);
					locationsList.addAll(location.adjacentLocations);
				} else if(!location.type.equals(amphipodType)) {
					if(startLocation.type.equals(location.type)) {
						// can only move over station of the wrong type if the amphipod started there.
						locationsList.addAll(location.adjacentLocations);
					}
					// otherwise do nothing.
				} else {
					if (availabilityMap.get(amphipodType)) {
						// location with correct station, can only enter if free
						locations.add(location);
						locationsList.addAll(location.adjacentLocations);
					} else {
						// correct location, but need to leave this first.
						locationsList.addAll(location.adjacentLocations);
					}
				}

				consideredLocation.add(location);
			}
			if (startLocation.type.equals("hallway")) {
				// cannot go to hallway twice.
				locations = locations.stream().filter(location -> !location.type.equals("hallway")).collect(Collectors.toSet());
			}

			// if final station is available go to the last possible place
			Optional<Location> lowestStation = locations.stream().filter(location -> location.type.equals(amphipodType)).max(Comparator.comparing(Location::getY));

			if(lowestStation.isPresent()) {
				HashSet<Location> station = new HashSet<>();
				station.add(lowestStation.get());
				return station;
			}

			return locations;

		}

		public Burrow(Map<String, Location> locationMap) {
			this.locationMap = locationMap;
			Arrays.stream(options).forEach(option -> {
				stationsMap.put(option, locationMap.values().stream().filter(location -> location.type.equals(option)).collect(Collectors.toList()));
			});
		}

		public Map<String, Boolean> getAvailability(Map<String, String> amphipodMap) {
			Map<String, Boolean> availabilityMap = new HashMap<>();
			Arrays.stream(options).forEach(option -> {
				boolean b = stationsMap.get(option).stream().allMatch(location -> emptyOrOfType(amphipodMap, location, option));
				availabilityMap.put(option, b);
			});
			return availabilityMap;
		}

		private boolean emptyOrOfType(Map<String, String> amphipodMap, Location location, String type) {
			boolean b = amphipodMap.containsKey(location.getCoords());
			if(b) {
				return amphipodMap.get(location.getCoords()).equals(type);
			}
			return true;
		}
	}

	public static class Amphipod {
		String type;
		int energyPerStep;


		public Amphipod(Character type) {
			this.type = "" + type;
			switch (this.type) {
				case "A": energyPerStep = 1; break;
				case "B": energyPerStep = 10; break;
				case "C": energyPerStep = 100; break;
				case "D": energyPerStep = 1000; break;
			}
		}
	}

	public static class Location extends Coordinate {
		String type;
		List<Location> adjacentLocations = new ArrayList<>();
		public Location(int x, int y, String type) {
			super(x,y);
			this.type = type;

		}

		public void getSurroundingLocations(Map<String, Location> coordMap) {
			List<? extends Coordinate> octagonalAdjacentCoords = getOctagonalAdjacentCoords(coordMap);
			for(Coordinate coordinate : octagonalAdjacentCoords) {
				adjacentLocations.add(coordMap.get(coordinate.getCoords()));
			}
		}

		public int getY() {
			return y;
		}

	}
}
