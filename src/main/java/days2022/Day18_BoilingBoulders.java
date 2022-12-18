package days2022;

import com.beust.ah.A;
import helpers.Coordinate3D;
import helpers.InputProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day18_BoilingBoulders {
	public static int day = 18;
	public static int year = 2022;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		Map<String, Coordinate3D> lavaDroplets = input.map(Coordinate3D::new).collect(Collectors.toMap(Coordinate3D::getCoords, Function.identity()));

		return lavaDroplets.values().stream()
				.map(Coordinate3D::getOctagonalSurroundingCoords)
				.flatMap(Collection::stream)
				.filter(key -> !lavaDroplets.containsKey(key))
				.collect(Collectors.toList())
				.size();
	}


	public static int runB(Stream<String> input) {
		Map<String, Coordinate3D> lavaDroplets = input.map(Coordinate3D::new).collect(Collectors.toMap(Coordinate3D::getCoords, Function.identity()));

		Set<String> airSpaces = lavaDroplets.values().stream()
				.map(Coordinate3D::getOctagonalSurroundingCoords)
				.flatMap(Collection::stream)
				.filter(key -> !lavaDroplets.containsKey(key))
				.collect(Collectors.toSet());

		// make 'clumps' of air, add all coords that touch.
		ArrayList<Set<String>> airClumps = new ArrayList<>();
		while(!airSpaces.isEmpty()) {
			String air = airSpaces.iterator().next();
			airSpaces.remove(air);
			airClumps.add(getSurroundingAirAndRemoveFromSet(air, airSpaces, lavaDroplets.keySet(), true));
		}

		// remove the biggest airclump, that's the outside one.


		return 0;
	}

	private static Set<String> getSurroundingAirAndRemoveFromSet(String air, Set<String> airSpaces, Set<String> lavaDroplets, boolean wasAirRemovedLast) {
		// get all surrounding air. Ie blocks that are not lava, regardless if we identified them before
		Set<String> surroundingAir = new Coordinate3D((air)).getOctagonalSurroundingCoords()
				.stream().filter(o -> !lavaDroplets.contains(o))
				.collect(Collectors.toSet());
		surroundingAir.add(air);

		if(surroundingAir.size() == 5) {
			
		}

		// removed from the air set so we don't check the same spaces again.
		final boolean wasAirRemovedNow = airSpaces.removeAll(surroundingAir);

		// no blocks touching lava were removed, probably working with outside stuff.
		if(!wasAirRemovedNow & !wasAirRemovedLast) {
			return surroundingAir;
		}

		return surroundingAir.stream()
				.map(newAir -> getSurroundingAirAndRemoveFromSet(newAir, airSpaces,lavaDroplets, wasAirRemovedNow))
				.flatMap(Collection::stream)
				.collect(Collectors.toSet());

	}

}
