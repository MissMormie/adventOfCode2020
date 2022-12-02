package days2021;

import helpers.Coordinate3D;
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

public class Day19_BeaconScanner {
	public static int day = 19;
	public static int year = 2021;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		List<String> collect = input.collect(Collectors.toList());
		List<Scanner> scanners = new ArrayList<>();
		Scanner scanner = new Scanner("");
		for(String line : collect) {
			if(line.contains("---")) {
				scanner = new Scanner(line);
				scanners.add(scanner);
			} else if(line.contains(",")) {
				scanner.addCoordLine(line);
			}
		}
		scanners.forEach(Scanner::addNeighbours);

		for (Scanner scan : scanners ) {
			scanners.forEach(scanner1 -> scanner1.findOverlap(scan));
		}



		return scanners.stream()
				.map(scanner1 -> scanner1.coords)
				.flatMap(Collection::stream)
				.map(beacon -> beacon.getCount())
				.mapToInt(v -> v)
				.sum();

	}

	public static class Scanner {
		List<BeaconWithNeighbours> coords = new ArrayList<>();
		String name;

		public Scanner(String line) {
			name = line;
		}

		public void addCoordLine(String coords) {
			String[] split = coords.split(",");
			this.coords.add(new BeaconWithNeighbours(Integer.parseInt(split[0]),Integer.parseInt(split[0]),Integer.parseInt(split[0])));
		}

		public void findOverlap(Scanner other) {
			if(other == this) {
				return;
			}

			for(BeaconWithNeighbours coord : coords) {
				// Do I need to find 12 that match, or is one enough? 1 is enough, the others will also match.
				for(BeaconWithNeighbours otherCoord : other.coords) {
					if(coord.matchesCoord(otherCoord)) {
						return;
					}
				}
			}
		}

		public void addNeighbours() {
			for(BeaconWithNeighbours coord : coords ) {
				coord.addNeighbourDistances(coords);
			}
		}
	}

	public static class BeaconWithNeighbours extends Coordinate3D {
		Map<Integer, BeaconWithNeighbours> distanceToNeighbours = new HashMap<>();
		Set<BeaconWithNeighbours> overlapsWith = new HashSet<>();
		boolean isCounted;

		public BeaconWithNeighbours(int x, int y, int z) {
			super(x, y, z);
		}

		public void addNeighbourDistances(List<BeaconWithNeighbours> neighbours) {
			neighbours.forEach(coord -> {
				if(coord == this) {
					return;
				}
				distanceToNeighbours.put(this.getManhattanDistance(coord), coord);
			});
			// includes self
		}

		public int getCount() {
			int count = isCounted ? 0 : 1;
			setCounted();
			return count;
		}

		public void setCounted() {
			if(!isCounted) {
				isCounted = true;
				overlapsWith.forEach(BeaconWithNeighbours::setCounted);
			}
		}

		public boolean matchesCoord(BeaconWithNeighbours otherBeacon) {
			Set<Integer> distanceSet = new HashSet(distanceToNeighbours.keySet());
			// keeping a list of possible overlap. Only true if at least 12 items exist.
			List<Map.Entry<Integer, BeaconWithNeighbours>> overlapsWithinScanner = new ArrayList<>();

			for(Map.Entry<Integer, BeaconWithNeighbours> entry : otherBeacon.distanceToNeighbours.entrySet()) {
				if(distanceSet.contains(entry.getKey())) {
					overlapsWithinScanner.add(entry);
				}
			}
			// overlap of 12, but assuming current coord also overlaps, so 11 is enough.
			if (overlapsWithinScanner.size() >= 11) {
				// add overlapping beacons for neighbours.
				for(Map.Entry<Integer, BeaconWithNeighbours> beaconFromOtherScanner: overlapsWithinScanner) {
					BeaconWithNeighbours neighbour = distanceToNeighbours.get(beaconFromOtherScanner.getKey());
					neighbour.overlapsWith.add(beaconFromOtherScanner.getValue());
					beaconFromOtherScanner.getValue().overlapsWith.add(neighbour);
				}

				// add overlapping beacon for self, by getting beacon that is the same distance away from other beacon
				Map.Entry<Integer, BeaconWithNeighbours> beaconFromOtherScanner = overlapsWithinScanner.get(0);
				BeaconWithNeighbours matchingBeacon = beaconFromOtherScanner.getValue().distanceToNeighbours.get(beaconFromOtherScanner.getKey());
				overlapsWith.add(matchingBeacon);
				matchingBeacon.overlapsWith.add(this);

				return true;
			}
			return false;
		}
	}


	public static int runB(Stream<String> input) {
		return 0;
	}
}
