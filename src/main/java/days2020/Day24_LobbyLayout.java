package days2020;

import helpers.Coordinate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Day24_LobbyLayout {

	public static void main(String[] args) {
		System.out.println("answer A: " + runA(textInput()));
		System.out.println("answer B: " + runB(textInput()));
	}

	public static long runA(String input) {
		Map<String, Coordinate> coordinateMap = new HashMap<>();
		Arrays.stream(input.split("\n"))
				.forEach(line -> doInstruction(line, coordinateMap));

		return coordinateMap.values().stream().filter(coordinate -> coordinate.booleanValue).count();
	}
	public static void doInstruction(String line, Map<String, Coordinate> coordinateMap ) {
		int x = 0;
		int y = 0;
		// cut input to instructions
		for (int i = 0; i < line.length(); i++) {
			switch (line.charAt(i)) {
				case 'e': x += 2; break;
				case 'w': x -= 2; break;
				case 'n': y -= 1;
					if(line.charAt(i + 1) == 'w') {
						x -= 1;
					} else {
						x += 1;
					}
					i++;
					break;
				case 's': y += 1;
					if(line.charAt(i + 1) == 'w') {
						x -= 1;
					} else {
						x += 1;
					}
					i++;
					break;
			}
		}

		String coordString = Coordinate.makeCoordString(x, y);
		if(coordinateMap.containsKey(coordString)) {

			coordinateMap.get(coordString).booleanValue = !coordinateMap.get(coordString).booleanValue;
			return;
		}

		Coordinate coordinate = new Coordinate(x, y);
		coordinate.booleanValue = true;
		coordinateMap.put(coordinate.getCoords(), coordinate);

	}


	public static long runB(String input) {
		// Set up tiles as before.
		Map<String, Coordinate> coordinateMap = new HashMap<>();
		Arrays.stream(input.split("\n"))
				.forEach(line -> doInstruction(line, coordinateMap));

		for(int i= 0; i < 100; i++) {
			System.out.println("Day " + i + ": " + coordinateMap.values().stream().filter(coordinate -> coordinate.booleanValue).count());
			// make adjacent tiles for all black tiles.
			List<Coordinate> coordinatesToBeAdded = coordinateMap.values().stream().filter(coordinate -> coordinate.booleanValue)
					.map(coordinate -> getAdjacentTiles(coordinate, coordinateMap))
					.flatMap(Collection::stream)
					.filter(optinal -> optinal.isPresent())
					.map(optional -> optional.get())
					.collect(Collectors.toList());

			// Can't add them at ones while looping over values, that will give a concurrentmodifcationException. Guess how I found out?
			coordinatesToBeAdded.stream().forEach(coordinate -> coordinateMap.put(coordinate.getCoords(), coordinate));

			flipTiles(coordinateMap);

		}

		return coordinateMap.values().stream().filter(coordinate -> coordinate.booleanValue).count();
	}

	private static void flipTiles(Map<String, Coordinate> coordinateMap) {
		coordinateMap.values().stream().forEach(coordinate -> {
			ArrayList<String> adjacents = new ArrayList<>();
			adjacents.add(Coordinate.makeCoordString(coordinate.x + 2, coordinate.y)); // e
			adjacents.add(Coordinate.makeCoordString(coordinate.x - 2, coordinate.y)); // w
			adjacents.add(Coordinate.makeCoordString(coordinate.x + 1, coordinate.y + 1)); // se
			adjacents.add(Coordinate.makeCoordString(coordinate.x - 1, coordinate.y + 1)); // sw
			adjacents.add(Coordinate.makeCoordString(coordinate.x + 1, coordinate.y - 1)); // ne
			adjacents.add(Coordinate.makeCoordString(coordinate.x - 1, coordinate.y - 1)); // nw

			long numAdjacentBlackTiles = adjacents.stream()
					.filter(coordinateMap::containsKey) // only tiles adjacent to black ones have been added, so a tile next to a white one may not exist.
					.map(coordinateMap::get)
					.filter(adjacent -> adjacent.booleanValue)
					.count();
			// Any black tile with zero or more than 2 black tiles immediately adjacent to it is flipped to white.
			if(coordinate.booleanValue) {
				coordinate.newBooleanValue = numAdjacentBlackTiles != 0 && numAdjacentBlackTiles <= 2;
			} else {
				// Any white tile with exactly 2 black tiles immediately adjacent to it is flipped to black.
				coordinate.newBooleanValue = numAdjacentBlackTiles == 2;
			}
		});

		coordinateMap.values().stream().forEach(coordinate -> coordinate.booleanValue = coordinate.newBooleanValue);
	}

	private static List<Optional<Coordinate>> getAdjacentTiles(Coordinate coordinate, Map<String, Coordinate> coordinateMap) {
		List<Optional<Coordinate>> coordList = new ArrayList<>();
		coordList.add(getCoordIfNotExist(coordinateMap, coordinate.x + 2, coordinate.y)); // e
		coordList.add(getCoordIfNotExist(coordinateMap, coordinate.x - 2, coordinate.y)); // w
		coordList.add(getCoordIfNotExist(coordinateMap, coordinate.x + 1, coordinate.y + 1)); // se
		coordList.add(getCoordIfNotExist(coordinateMap, coordinate.x - 1, coordinate.y + 1)); // sw
		coordList.add(getCoordIfNotExist(coordinateMap, coordinate.x + 1, coordinate.y - 1)); // ne
		coordList.add(getCoordIfNotExist(coordinateMap, coordinate.x - 1, coordinate.y - 1)); // nw
		return coordList;
	}

	private static Optional<Coordinate> getCoordIfNotExist(Map<String, Coordinate> coordinateMap, int x, int y) {
		String coordString = Coordinate.makeCoordString(x, y);
		if(coordinateMap.containsKey(coordString)) {
			return Optional.empty();
		}
		return Optional.of(new Coordinate(x, y));
	}

	private static String textInput() {
		return "swsenenwneswnewseswwseswnwsweeswnw\n" +
				"esweeeeneeeneeeweeeenenenee\n" +
				"ewewsewswnewnwnewwwwsew\n" +
				"nwnwnwnwnenwwnwsenwnwnwnwnwnwnw\n" +
				"nwswseswneswseswswneswswseneseswswsenwswse\n" +
				"swswswneswswswswwneseswwswsw\n" +
				"newnwwnwnenenenenwsweenenwnenwnenese\n" +
				"senwsewseneneseneenenwwneneeswnewsw\n" +
				"eeeeneweeeseeeeeenewneswe\n" +
				"wnwswwewwwsewnwnwwnwnwwwnwnwnww\n" +
				"nenwnwnwnesenwwnwnwwneswnwnenesweneenwnw\n" +
				"nwwnwwwwenwwswnwwwwwewsesew\n" +
				"swnwswwswswewswseswnwne\n" +
				"wswnewswnwwwwenewsewwwwneswe\n" +
				"nwnwnenenewneneneneneenenesenwneswneswse\n" +
				"nwewsenwnwneseswwwwwwswwswwwwe\n" +
				"nesenwseneswseswnwneseseseseseswnwse\n" +
				"wnesweseswenwnenwnweseeseseeswnwse\n" +
				"swswnenenenenewneneeeneneeneneenenene\n" +
				"seseseseeweseeesee\n" +
				"wwwwwwwwwnewnwwwswww\n" +
				"sesenwesenwseeseseseewswneseseesesw\n" +
				"wnewwwsewnwwnwnwewnwswnwwwwnwe\n" +
				"nwnwnenwnwneneenewswnwnenwswnwnesenwsw\n" +
				"eeswwneeneneeneeneeeeesw\n" +
				"seneenesewneeneswwnenenweeenenewwe\n" +
				"seswseswsenwswswseswswswswenwswsesesw\n" +
				"nwnwnenwnenwnenwnwnwnenwswnenwwwnwese\n" +
				"wnwwwwwnwwwnwwnwnwseneenwnwnww\n" +
				"ewnwnwnwnwnwnenwnwnwnenenwsenenwnwnene\n" +
				"neneweeeeneeneesweseswswneseswse\n" +
				"seswseeneswswswseseewsewnwswswwnwne\n" +
				"swwneneneweseswnenwneeneneeeeeeee\n" +
				"swwwwwwwwwwwwswswwswswnesew\n" +
				"eewenwseeeeeweeeeneeese\n" +
				"neneneneneeneswneeenee\n" +
				"sewnwnwwswnwseenwwnenenwwnw\n" +
				"swsweswswswswsewseneseswswsesweswnwsw\n" +
				"nenewewwwwwwwwwnewwswsesewww\n" +
				"enwsweneseseeesese\n" +
				"swneseseseneeseesenwwseneswseseseseew\n" +
				"nwswswneeeewneseenwnwnesewwswnwesw\n" +
				"nenenwneenwneneneneneneneneneswenewne\n" +
				"nwneneseeenwnwnwneneneswneswwnenwnwnenw\n" +
				"neseenenwneeneneneeeeneene\n" +
				"sesesewseseseswneseesesesesenewseswsese\n" +
				"senwnewseneneeeneeeeeenesenwnene\n" +
				"eeweneneeenweeeseesweeeee\n" +
				"esesenwswnwseewseswseeeseswenenwse\n" +
				"neeneeeseswseeesweneeseeesweee\n" +
				"swweswnwneswswwswswneswswswswswsw\n" +
				"wwswwwnwnwnenewnwenwswnwsesenwnwe\n" +
				"neseneenwneeeeeneneeeewneeene\n" +
				"swsenwsesesweswsewswswswsenwneseswsesw\n" +
				"wewwwwwnwwwwwnwwwnw\n" +
				"swseweeenwswneseseesenese\n" +
				"senwsesesesesesesewseesewe\n" +
				"sewnwnwnweswnwnwsenwnwnwnwsenwnwnwnwnw\n" +
				"swswswswswswswswswseswswswswswswwswnesw\n" +
				"wswnwnwsewwnwewwewwnesewwne\n" +
				"enenenwnewnenwnenenwwneswneseswnenwsenwnw\n" +
				"seseseswswseswsewnwsewswswseeseeswse\n" +
				"nwseswnweswnwneseweswsesweseseswne\n" +
				"seseswwnwswneneseneneseeseesenwswnwww\n" +
				"wswswwwwswwneswseswnwswswsew\n" +
				"senwnwwnwnwesewswnewswwsenwwnwnwwnw\n" +
				"swsewneswswswswwswswwswswswsw\n" +
				"eneseeeswneswneeseesenwewsenwewnwnw\n" +
				"neneneeeneweneneeswe\n" +
				"neeeseeenwnweewswnewneseneenenee\n" +
				"senewesesenweseseeesewsesesesesesee\n" +
				"eseeeseseseenwseeneseseeewse\n" +
				"nwnwseseenwenwnenwwswnwnwnwwnwnwnwnwnw\n" +
				"senewweseeenwsesesenesewsesesesee\n" +
				"enenenwswnenewswenwnwneenwwnwswnenene\n" +
				"eswseseneswsenwswseseseswsesesewsenwsesee\n" +
				"wnwneswneseneneneneeneneswenenenenewne\n" +
				"seswneewsenesesewnwwswnesewswesenwne\n" +
				"eswwesenwwenwnwnweseseeeeeese\n" +
				"neeneswneeswnenesenwnwneswnenwneneswsene\n" +
				"swswneseseswswswswwewnwsenwesweneswnwe\n" +
				"nwenwwwnwnwnenwnenwnwnwnwnwsweneswe\n" +
				"seseneneeseseeeeswsenwwwesesenwe\n" +
				"sweswseswwnwsesesewseseseseswseneswnene\n" +
				"seswseseseseseswwseswsenesesesesesesese\n" +
				"sesesenwswswseswswsw\n" +
				"senwseeeeseseeseseseenwewswesee\n" +
				"wwsenwewwnewwswwwwwswwwwwne\n" +
				"nwnwnwneswnwnwnwsenwwwnwwnwnwnwenwse\n" +
				"swwwswswswwwsewnenwswwswwswseww\n" +
				"enweenesesesenewnwnwwswswswseseese\n" +
				"swnenenenenenenenwnenesenewnenwnenenwnene\n" +
				"enenesweeeeseneeneeeneeneeew\n" +
				"wnweseeswnwwenwnenwnwnwwenwnwsenwwnw\n" +
				"neswswswseswswswwswnesw\n" +
				"neswseeneseswsewseseswseseswswwswswse\n" +
				"seeneswsesenweeeseeseswsewenesee\n" +
				"wewewwnenesewsenwwnenwnwseswsww\n" +
				"nwnwsesewnwseeeswnwnwseewseenw\n" +
				"nwwwwwnwwnwnwwwwnwsenwwwwsew\n" +
				"eenwneseeeneeneenenwseeeeeee\n" +
				"nwenwnwnenwswnwneesenwwwnwnwnwsenwnw\n" +
				"swswswswseswswswswwseswswswswswswswswne\n" +
				"swwwwwwwwwwnwwewwwnwwww\n" +
				"esweeeeeeneeenwnwenesw\n" +
				"eeenweeeseese\n" +
				"wwwnewwsewewwwwwwwwww\n" +
				"seseseeseseesesenwesewseeseseseswnesese\n" +
				"senwnwwneswwswwnenwwwewnwnwwnww\n" +
				"swnwsesewneenwenwseenwneeseeseewsw\n" +
				"eneeneneneneneneswsewswsenenenwnenenenw\n" +
				"wneesweseswswwsewswnweswswewswswse\n" +
				"neseneneeewneneenewnenenenenenenenene\n" +
				"wswnesewwsenewswswswswneswswswsweesw\n" +
				"swswsesenwseswsewseswneseseseswsesw\n" +
				"seswwswwwseswswswswswweswneswswnewsww\n" +
				"neneesenewewnenwneneneswnwweswswnese\n" +
				"swswswwwswswswswswwneswswwwswseww\n" +
				"neenenenenenenenenenwsewneneneneswnenene\n" +
				"eneeeeseenenwwesenesweeseeesw\n" +
				"swswswswswswwswnwswswwswseswswswsw\n" +
				"eneseseseswsesesewsesesesesenwseseswenese\n" +
				"nenesweenenenewneneeeneswnweenene\n" +
				"eneswsenwseenwenwene\n" +
				"swsewnenwenwwwwseseseswnwwne\n" +
				"seeseseseseseseswsewsesesenwseseseewse\n" +
				"swenewswnenwswsweswneswswswse\n" +
				"wnwwnwwwswwwwnwsenenwnwwwnwwww\n" +
				"eswswwswswnwswswwwswswsewsweeswww\n" +
				"nwwnwenwnwnwwnwsewnwwwnwnenwnwnwnwnw\n" +
				"seswwseeswswseenwneseswnewwswseswsw\n" +
				"enewwwswesewwnenwseswseweenwnwnew\n" +
				"swseswseesenwseseseseseseseswseswwsee\n" +
				"neneneneneneneneneneneenwnewneswe\n" +
				"wnwswswnewnwnwwwsenewweeswnwnenww\n" +
				"swenweenweeneswneeeeneneee\n" +
				"nesweswswseeswswsenwswsewwseneneswse\n" +
				"nwneswewnwnenwnwnenesenenenwnwnwneswnene\n" +
				"wenwswnwnwnwnwnenwnwnwenenwnwnwnwnwnwnwne\n" +
				"seseneesewnwnwneenwseeswswsesewnese\n" +
				"swnewswnwswnwnwnwwenwnwseewnwnenenww\n" +
				"seesesewenwneeeenwnesenenwsweseswnwsw\n" +
				"swnenenwnwnwnenenwnwnwnwswnenenenenwnwse\n" +
				"ewewnwwswesewsweswnweswswnwnwwnesw\n" +
				"neseneewwswwwwnwwwewwwwwsww\n" +
				"enwnwnwwswnesewwnwnwsewswnw\n" +
				"sewwwwwsweswnwswswnwwwwewsesw\n" +
				"neneneseweneenenwesewnenenenenenese\n" +
				"eeeenweeeeswseewnweewneswnew\n" +
				"wnwnenwnweneneswnenwenenenwnesenwne\n" +
				"eseseseseswsenwseseswsesesesesesesesese\n" +
				"eseeseeseeesesenwwseene\n" +
				"nenwnwnwnewnwnesenwnwsenwnwneneseesw\n" +
				"ewwnwnewnwwnwnwnwnwnwswswswwwnwenw\n" +
				"senwwneeneeeneneneenewneswenwnene\n" +
				"nwnenwnenwewenwnenewseeeenwswswwswe\n" +
				"wwewwwwnwwnwwwnw\n" +
				"eeeeeeseseeeeeeeeeew\n" +
				"wnwneeeseeneeneeneeneweseeenesw\n" +
				"nwwnwsweswwwseww\n" +
				"swswwswneswseswswswseswswseswsesweswwsw\n" +
				"newneneeneseenenenenenenenenenenewwne\n" +
				"nesewwwwswwnwwwnwwwneewwwsee\n" +
				"enwseseseseeseseseeeseesese\n" +
				"seeseseseseseeseseswneseswseseesesesenwse\n" +
				"nwseeseeesesewswseswwsenwseswneseesw\n" +
				"wneseenwnenwenwneeneneneneneeseesee\n" +
				"nwneesenwsweeneneneeseeneesenwww\n" +
				"nenenwswnwswnwnwnwnwnwnenwnenenwnenwnwnwnw\n" +
				"nwnwnwenwnwnwnwnwnwnwnwnewnenwnwswnwnw\n" +
				"wwwwnwwnwwnwewnwnwnwwnwwwswnw\n" +
				"neewneenewseneneeneneneneneneneene\n" +
				"neneneneneneneneneneswnenwnenenwnesenwnene\n" +
				"eeeeeeeseeeeeeeneeweee\n" +
				"ewwsweswsweseswswswswnwnwewnwnesw\n" +
				"weswswseseenweneesenenwnwnwneswswse\n" +
				"nwswswseswwswswswswwwswsww\n" +
				"seswseswwseseswswswneseswseswswswneseswswsw\n" +
				"eneeneewnenwneeseeneneeneneneseeee\n" +
				"eenwnweswenwenenwswswneeeewese\n" +
				"nenenenesweneswneswnenwne\n" +
				"enweseeseeneesweswnwsee\n" +
				"swnesesenenwwswnwsesenweneenenweswwew\n" +
				"nenwnwnwnwnwnwnwnwnwwnwnesenwswnwenwnw\n" +
				"nwwnenwnwnwswnwswsenwnwenwnwswnwenwnwe\n" +
				"swweeenwneswnwnwwswsewwswswseneew\n" +
				"esewnwseenwseseenweseseseesesesenwse\n" +
				"enweseeeeseseeneeseeeseweee\n" +
				"eeseeeeseseseeeewneeeseeeswe\n" +
				"swswwwnewswwswwswwswwswsw\n" +
				"eewseeenwsweenweneesewnweew\n" +
				"wswswsenweswnwsenwnwswswwneseweneww\n" +
				"nenewswnenesenenenenenwnwnenewneenenene\n" +
				"nwnwnwnwnwenenwnwnwwnwnwnw\n" +
				"sweweesweseeeswnweenwseneeenwee\n" +
				"ewesesenweseenwseseseeseneseeeswe\n" +
				"eneenenewnenenenenesenenene\n" +
				"eeeesesweeewseseeeneesewenesew\n" +
				"wswwwswswswwswswnwsweswswwswnewseswsw\n" +
				"swswswseswswswswswswswswswseseseswswenw\n" +
				"neenenwenenweseweneenesenwswneswnwswne\n" +
				"seswwwwwwwwwwwwwwwwnesenewne\n" +
				"swswswswswsweswswseswswswswswswseenwnwswsw\n" +
				"eneeeseeseseeseeseewseseseseee\n" +
				"swneswswwnewewswswsewwswwswnewse\n" +
				"wnwnwwwswwnwwewwwwwseewnewsw\n" +
				"senweeneeseeewenwsew\n" +
				"nwnwnwnwseswnwnwnwnwenwnwenwnwnwnwwwwsw\n" +
				"eeseneeeswewenwneeseeeeeee\n" +
				"wnwenwwnwnwnwnwe\n" +
				"wswwswswwwwwwsewswwneswswswsww\n" +
				"nwnwnwnwnenwnwnenenesw\n" +
				"nenewwnwnwswnwesenwneseneenenwneewne\n" +
				"nwneneswnwnenenwnwwsenwnenwnenenwnenenw\n" +
				"swwwwswswnenwsweswenwswseneswwswnese\n" +
				"eeeeewneweeeeswsewesweenwene\n" +
				"seswsewnwwnenwwsesenesewnwweeswswnwse\n" +
				"senweeseseswnewseseswsenesese\n" +
				"nwsenwnwnwnwnwnwenwnwnwnwwnwwwnwww\n" +
				"wseseseseseswswswswneswse\n" +
				"nwneneswnenenenenwnenwne\n" +
				"esenwseeswseseseseseseswsesenesenwswse\n" +
				"seswswswswnenwswenewenwneswseswwswswswse\n" +
				"sweswwwnwswwswwnweswww\n" +
				"swwswsewswwswwswwswswwnewneww\n" +
				"swseneswwnwweswswseswseeswwneswswswew\n" +
				"eseseseesesesenweeseseseweeseswese\n" +
				"swswnwnwnweswswswswswswswee\n" +
				"swseneswsesenesweewwseneswseswswnwnw\n" +
				"sewseeseseseeeseseesesesenese\n" +
				"seswnesesesesesesewseseseswsesesesesese\n" +
				"seseseseswseswseseseseswsesewseswsenese\n" +
				"enwnenwenwnenwnwswsenwnwswne\n" +
				"enenewnenwneneneneneneneewnenenenene\n" +
				"nwwsweneswwswwsewswwswwswwenwswsw\n" +
				"swsewseseneswswswseseswseeswseswsesese\n" +
				"neneseewneseneneneeneneenwnenewe\n" +
				"wwnwwwewswewwwwwwwswwswww\n" +
				"eeswneneneneneneneeneneneswswnenenwnw\n" +
				"nwnwnenenwnwsenwnwnenenwnenw\n" +
				"eeseseseesenwseseeesesesewseseseseesw\n" +
				"wewwwswwwswwwwwwswnwsewwnw\n" +
				"nwwnwnwwwnwnwewnwnwwnwswswnwwnwe\n" +
				"nenwnenwnwnenwnwnwswnwnwwnwnesenwnweswnenw\n" +
				"wwneweenenwswnesesewnewswswwwse\n" +
				"seswswsesesesewswseseseseseseneseswsese\n" +
				"swswseswseseswseswseneeswswseswsesewse\n" +
				"eneneenweeewnwesenenwseswseewwe\n" +
				"swenenweeeneeeneewseeswenwee\n" +
				"enesenewwwswseseswsese\n" +
				"eswneseweneneeneneeenweneneeee\n" +
				"eseseeseseeweswswenwneeeeneewne\n" +
				"wnwwneenwwwwsenwwwwnwwwwww\n" +
				"nwnweseenweewneswesweeeeeneew\n" +
				"swswswwnewswwswseneswwwswwwswsww\n" +
				"nenwswnwnwnwenwnwnwnenenwnwnenwnwnwnene\n" +
				"nwnwwwwnwnwwnwnewnwnwnwnwsenwwww\n" +
				"wswnwwseswwswwswwnwwewswsewswew\n" +
				"wneeweneneneseneswnewnenesesenenwnee\n" +
				"sesesesenwseswseswsesesesenwsewseneesese\n" +
				"swseswsenwsweseswsewswsweswse\n" +
				"nesesesweewwsweswseseswnwnwnwswsee\n" +
				"nenwnenwnwnenenwneswseseenw\n" +
				"sesenwswsenenwseswnew\n" +
				"sewseseseseeseseseseseesesesesenwseseswse\n" +
				"sewwenwnwnwwnenwnewswnwwwwnwwwnww\n" +
				"nwnenesewnwnwnwnwnwnenwsenwnwnwnwnwnwnwnw\n" +
				"nenwseneswnweseseswneweswnesenweewne\n" +
				"sesenwseseseewseseewseneseesesesesw\n" +
				"wwnwwnwnwwseswwnwenwnwwwnwnwewww\n" +
				"seseswsenwseswswseswwneseswsese\n" +
				"sweeswnenenenwneneenweeeneeeeene\n" +
				"seseseseswneswseswseswsenwseseseswsese\n" +
				"enenenenenenwnwnwnwnenesenenwnwneswnwnene\n" +
				"swwsweswwswwwswswswsw\n" +
				"wwswwnwsewswwnenwwwwwnwenwnww\n" +
				"eenwnwseseseseeeeeeeeeweeswswe\n" +
				"nwsenwnwnwnwnwnwnwnwnenwnwnenwwne\n" +
				"swwswswsweswswswswwesweseswswswswsww\n" +
				"seseesenwseweseeeseseewseseswsese\n" +
				"nwseeeesweeeeeseeweeenweeee\n" +
				"seeswneseseseseseswsesesesesesesesenw\n" +
				"eswenewsenwwneeeneeneeneenenee\n" +
				"wswenwnwenwwnenwwswesewseneweewsw\n" +
				"sweswenwswnwswswnwnwwnweenenwnwe\n" +
				"nwenweeswswwww\n" +
				"nwneneneswnwnenenenwnwneneswnwnwnesenenene\n" +
				"swseeesweeewsesenwweseenweenee\n" +
				"nenwneswneswneneenesenesewnwnesenewnenene\n" +
				"sewsesesesesesesenwseseeseneseewwsese\n" +
				"seswswswswwswwneswwswswswswwswwwnw\n" +
				"eeesenweeewseweeeswweneswnenw\n" +
				"eeeeeneeeweseeweeeeeeee\n" +
				"seswnesesewwseseeenesenewseswwnesw\n" +
				"eeeeeeseeewseeeeeneseeee\n" +
				"nwwwnwenwwswwnwenwwsenesenwwnw\n" +
				"wwwwwwwwwsenwwnwwnwwnwwwsew\n" +
				"seswswswseswseseswswswseswnwesenwseswsesw\n" +
				"eneeneeneneeeeweneneneseweneee\n" +
				"swwwwewswnwswewnwswwswwswwwswsw\n" +
				"eswneseeneneewneswseeweeeneenwnenw\n" +
				"swswnwwwseswnwnesewseswwnwswsenwswe\n" +
				"swswswwsewnwwwewswwneswsewwwnw\n" +
				"nwnwwnwnwwnwnwsenwnwnwnwnwnwseenwnwnwnw\n" +
				"seseseewsesesesesesenenewsesesesesesesese\n" +
				"nesenenenenenwnwwnwnenwnenenewneneneene\n" +
				"wesesesenweseseseswseseswsenwswsesewse\n" +
				"eseeswseeeswenweeseseeeneeee\n" +
				"neswwnwwwwnwwwewwnwswwenwww\n" +
				"nwswnwnenwnwnwnwnwnwnweswne\n" +
				"nwwwsenwnwnwwnwsenenwswnenwwnwnewwse\n" +
				"seenwseeenwweseeeweesweeeese\n" +
				"swswsweswswswneswswseswswwwswswswswneswne\n" +
				"ewwwswwwwswwwwnwwwwwswww\n" +
				"seswsenwseseseswsesesese\n" +
				"neneeswewnenwswneneseswenwseewenene\n" +
				"swnwnwenenenwnwnwnwseenwnwsenenwnwwnw\n" +
				"nwneswneneneneneneseneneswnenenwnenw\n" +
				"nwnwnwnwnwnwsewnwnwseenwnwnwnenwnwswnww\n" +
				"seswseseswseseswneswneswswseswswseswswsese\n" +
				"wnwnwnwnwnwnwnwnenwnwnwnwnwnwsesenwnwnwnw\n" +
				"seseseeesesewsenenwseweeseesesee\n" +
				"nwnenwwweseswnwwwnewwswseneseww\n" +
				"swnwseseseesesesesesewseneseseseseswsesw\n" +
				"seseseseesewneseseseneseeeseseeswsese\n" +
				"nwnwwnwnenwewwnwneseswwwwnwww\n" +
				"esesesesewseseseseseeseesenwsesesese\n" +
				"eswswnwswswneswswswseweseseswswswswne\n" +
				"swneenenenenenwnenenenenenwnenwnesenene\n" +
				"nwwswnwswswnwnenweeenenesenwwsenesw\n" +
				"eeeeeeeeeenweeeeenwswswee\n" +
				"nwnenenenwswnenenwnenenenw\n" +
				"nwwwnwnwenwnwnwsenwwwnwwnwwnwnwnwnw\n" +
				"ewneweseeseseseseese\n" +
				"nwnenwnwnwnewsesenwnw\n" +
				"nenenwnewnwnwnenwsenwswneenese\n" +
				"wnwwwwwnwwwwnewsewwwnwwswwnenw\n" +
				"enweseeeeseseeeee\n" +
				"wesweseneswsewnewwneewenenenwnwse\n" +
				"eseeswseeseeseseseseeseenweeee\n" +
				"wnwnweweswsesenweseseseseseswewsese\n" +
				"eeeneeseeenwseeeneenwseweeee\n" +
				"esenwseswseswswseswseseseswseseseswswse\n" +
				"wwseswnewwwswewwwswnewwwew\n" +
				"eeeneeeeeeenweeseenweeswee\n" +
				"sesenwneewswweneswnewsenenwenwswnw\n" +
				"nwnwneseenenewewnenewswneesenenene\n" +
				"swweswswwswswwnwwwswswwwswnwwesww\n" +
				"swnenenenwneneenwnwnwnenesweswnwnenenwnw\n" +
				"nenwnwwewwnwnwnwsewswneenwswswnwnw\n" +
				"nwwwsewwnwnewnesewwwwnwnwnwnww\n" +
				"wswwnwsenwswnweswweswswwwwenenw\n" +
				"neswswswneseswwswwwsewswwswnwnwneesw\n" +
				"newewwswwswwewwwwwwwwseswne\n" +
				"neenenweesweneneeneneneneneneneenese\n" +
				"eseseseeswseweenweseseeesesesesee\n" +
				"swseswseswswswseswseswswswnwsw\n" +
				"nenenenenenesewneewsenenenenenenenenene\n" +
				"eeswneseenweseeeeeeseewsewseee\n" +
				"nwewswswwswswwswswwswswswswswswnwse\n" +
				"nwnwnwnwnwnenwnwnwnenwswnwnwnw\n" +
				"eeeseeeeeeweenweseseseeese\n" +
				"swswwwswnewsewswwwnwswswsenwswsesw\n" +
				"seseswsesewsenesenesenw\n" +
				"seseswswseneswswswneswseswswnwsesesesesese\n" +
				"nwnwwnwnwnwenwnwnwnwnwnwnwnwnwnwnwnw\n" +
				"neeneseeneneneneeneenewneneneswnenew\n" +
				"nwsenwnwnwnwwnwsenwnwnwnwnwenwnwnw\n" +
				"swswswnwswswswswneswneswswswswewseswnwsw\n" +
				"seswneswswswswswswswseswswswswswswenwsw\n" +
				"wwwwnewnewnewswwswwswwwseswww\n" +
				"swenweswseswseneseswswsesewsese\n" +
				"ewswseswwsewsweswwswwswsenenenesene\n" +
				"swwnwnwnwseneseenenenwneswwwwwseswse\n" +
				"nesweneneeseneneneneneenenenenenwnwnenee\n" +
				"enwnwwwnwnwnwnwnwnwnwnenwsenwnwwwnwnw\n" +
				"wwwwwswwwwwnwwwewwnew\n" +
				"nenenesenenwnwswenwnwnenenwneneneneeswne\n" +
				"eeneeneneesweeneneneneeeeewene\n" +
				"eeseseenwnwsesesenwesenwesesesewese\n" +
				"neeeeswswneeeeeeenweeene\n" +
				"seseesewnesewseseeseseswseneseswnwsene\n" +
				"sesesewseeeneeseeseseseseeseeese\n" +
				"wswwnwwnewsewnwnwnwesewwnwnesene\n" +
				"eseeneenenewnenenwseesenwnwnesewnene\n" +
				"eseeneeswnwneesesewwneswewwneesw\n" +
				"neneswseneenwnenenenewneneneneneneseneene\n" +
				"sesesweseswsesesewseswsesewsesweswse\n" +
				"sewsesesesesewseseseneseeseneesesese\n" +
				"swwwenwwnwswnwnwneswwwnenwsewnwww\n" +
				"swwswswseseswswnwseswswswswsweseswsesw\n" +
				"senwenwnewweneneswswnwswwsenwenwenwnw\n" +
				"nenwnenenenenenenenenwneneneneneneenwwsw\n" +
				"wwnwwsewsewswnewsenwneesw\n" +
				"nenwnwseswnwnwenwesewnwneeswswnwnwswnw\n" +
				"nenwswnenenenenweneseneewseeneewswene\n" +
				"esenwwsenenenewnwnwswnwnwnwwwwnwnwnww\n" +
				"sesenweeeseseeeseseeseeeseswese\n" +
				"neneneneneenenenewsenenenenenenenenene\n" +
				"eeeseeeesweneneneeeeeenenwe\n" +
				"eswwwwnwneewwswwnwwneswwseswswwsw\n" +
				"wseseeeesesesesenweeneseseseseeee\n" +
				"esesesenweeeeeweeeswenwne\n" +
				"wwwwswneenwsww\n" +
				"seseswseneswswswseswseswswsewswswswseese\n" +
				"weseswseeeneseseewnweseesesesee\n" +
				"swswswswswswswswneswswsw\n" +
				"swwswswswswswswswwswswenwwswww\n" +
				"nweneneneeeneneeneeseneneneneene\n" +
				"swwswwwneneswswwswwswwseswnewswsw\n" +
				"eeeewenwweeeesweeeee\n" +
				"wnwseeewswswwwswsewwswnewnenew\n" +
				"ewseneeswswneswenweswnwwwwneswwe\n" +
				"eswseswnenwswseswwseseesesesenesesesw\n" +
				"senweeseseesesesesesw\n" +
				"nwsesesewsewnweweseseeseswsesesw\n" +
				"sewwwseseswswneenewenenwsenwseww\n" +
				"nwnenwnwneswswsenweswneneneenenenenene\n" +
				"eswnweeeeneeseneeeswenweneenw\n" +
				"wsenwnwwnwnwnwnwnwnwnwnwwnw\n" +
				"nwnwenesewenwnwnwwwnwwnwnwwnwew\n" +
				"nwsenwnenwnesenwnwnwnww\n" +
				"wnewneswnwwsesenwwweweseneneww\n" +
				"swseseseneeseswsewse\n" +
				"nenwsenwnwsenenwwnwwnwnenwnesenwnwnwnw\n" +
				"neswswswswnweewnenwswswswswswswseswswne\n" +
				"nwseseseseseeeseseseeeeseeeswsese\n" +
				"wnwnwnwwwenwsewnwnwwnwnwwnwnwnenw\n" +
				"wwwwwnwwewnwww\n" +
				"nwswswswswswswswswswswswswswneswseswneswsw\n" +
				"nenesweneneeneneneneneeneneenenee\n" +
				"swswswswswswwseswwswwswsweswswswnwwsw\n" +
				"nwwewswwnwwwwnewwnw\n" +
				"eeewenweeeseneeeseeeeeeeswe\n" +
				"swswenweswseseswneswswswsewsesesenenww\n" +
				"eeseeeeeeneswseseseeeweeeee\n" +
				"eeeseseeeeeeeeseenwenenweswesw\n" +
				"neswseswswseseseswsesw\n" +
				"sesenwwseneesewseneseseewneswswneswne\n" +
				"senenwswswwwsewwsenenwwenwewnew\n" +
				"neeseeweeeneeenenweeseeeee\n" +
				"nwswwswswswseswwswswswnewswswwswsesw\n" +
				"swneeneneeneswnwsesesesewnwesewsesesw\n" +
				"swswenwneneewneswwswswesesewsesw\n" +
				"nwnwsenenwnenwsenwnwnenwneneeswwnenewnwnw\n" +
				"swsenenewswswsewwnewwwnwsewswwsw\n" +
				"swenenenwseseneweenene\n" +
				"wswwswneswsewswswswswswswsw\n" +
				"wsenwsenwwwsewwnwswwnenwwnwnwnwew\n" +
				"nesweweeswwseseseseeeeneenwew\n" +
				"wseseneswnwswseseswseswswsenwwswswswesw\n" +
				"wwnwnwnewswenweswenwnwenwnwswnww\n" +
				"seswneswswseswwseswswseneswswnweswswswsesw\n" +
				"swwwnewswwswswwwwwsww\n" +
				"enwswswneeneneeneneneeneee\n" +
				"swswwswswswswswswswneswswswswswsw\n" +
				"sweseneswwseeeneeenwnenweswnenwne\n" +
				"eseeeeweneeseesewseswnewe\n" +
				"enwesweseenwseseeeesweeeenese\n" +
				"eswsweesenwenweeeeseeeeeenwee\n" +
				"eeeesenweseeeeeesweeseeee\n" +
				"wwseenenwnwwnwnwwwwnwswsenwwnwenw\n" +
				"sesenwseseseeseseswseswseswseswnwsenwsese\n" +
				"eeeeneeneswnenwee\n" +
				"seswneseswseswnwswswesesewseswswswswsenwse\n" +
				"nwnwwnwwenwnwnwnw\n" +
				"eenenenwneneeswneeneneseneneenenene\n" +
				"seeseseseswseseesesene\n" +
				"seseseswseseswnwswswwseswsweseswsesesenw\n" +
				"nenenenenenesenenwnenenenenenesenenenewnenw\n" +
				"nwenwnenenwnwenwswnwnwnwnwnwnwneneswsw\n" +
				"nwneswswnenenwnwnenwnenwnenwnwswnenwnwnw\n" +
				"eneneneeeeneeeweneeneenene\n" +
				"enenenenwnenenesenenenenewneenenenene\n" +
				"seseseseseswsesesesesesesesesesewnenesese\n" +
				"nwnwenwwnwenwwwenwnwnwnwwwnwwsw\n" +
				"eeseeesweeeseeeseeeeewnwenee\n" +
				"sweneneeneeeeneeewneneeneneenw\n" +
				"nenewnwneseseneneenwwswneneenenwwse\n" +
				"nwswwwwneswsewwswwneweweswnwsew\n" +
				"swswnwswswsweswsesw\n" +
				"nwwnwnwnenwnwnwswnwnwwnwnwnwnwwnwwew\n" +
				"swwwwwswswwwwneswwswsewwwwsw\n" +
				"seswsenenenewewnenenwnenewenenenenesee\n" +
				"wwnwwewnwnwenwwnwnwwnwswwwsww\n" +
				"wswswswswswswneswswseswswswswswswwswsw\n" +
				"sweseeeeeeeeenwneneeeenweee\n" +
				"eneneneeneneneswnenenenewenenenenwnesw\n" +
				"swseeesewsenwswsesesw\n" +
				"eeneneenenwneeneweswsweneneeene\n" +
				"swseseseseseseseseseeeenwswseesesesenese\n" +
				"eneweeneeneneseenenenewseeeneenew\n" +
				"eeswswseenenwnewe\n" +
				"ewenwwsesesesenwnwwesenenwnesesew\n" +
				"wwwwnwwwwnwenwwwnwwwwsenww\n" +
				"swnwsesewnenwsesesesene\n" +
				"sewnenenenwneenewsenwnenewneeesesw\n" +
				"swswswswswswswswseswnwswsww\n" +
				"seswswsenwwswseswnesweseseswswseseesenwse\n" +
				"eswswwswswnwseswswswswswsweswswswswsw\n" +
				"nwnweswwnesenwnwnwnweswnwnwnw\n" +
				"eswnewwswesewwwnwewwesenwnwwsww\n" +
				"eswnwneneneeeeswnesewnesweeeee\n" +
				"ewswwnwnwnwwwnwnw\n" +
				"eeneneeeneneneeenweneesweswnese\n" +
				"seswseseseswseneseseswweseseswswswseswsw\n" +
				"nwnwnwnwnenwwwenwenwnwnwesenwwnwnw\n" +
				"sewwnwwenwswnwnww\n" +
				"wseneseswseseswswenwseseswswswswswwse\n" +
				"seseeneseweneneswnwseenwswnenenenwnene\n" +
				"nenewswnwseseneeneneneseneneneewnenw\n" +
				"eneeeneeneeeeneeneweese\n" +
				"wwewwswwwwwwswswwww\n" +
				"seswseseseseseswseseneseswnesesesesese\n" +
				"neneswseswnwwseswswswwswswsweswnwwswe\n" +
				"newwswswswswwswswneeswnewswsewese\n" +
				"eseeeseeseeeeeseewe\n" +
				"enenewnenesesenewnwswsenesenenwneneneesw\n" +
				"neeseeeeesesweseseseeseseenweese\n" +
				"nweswswswswswseeneswwsewswswswswswe\n" +
				"wwwwwwsenwwwnwwenewwwnwwsww\n" +
				"swsesewnwswswswneweswswseeswswneswsew\n" +
				"swseswswswswswswswnesweeswswswsenwswswnw\n" +
				"eeeneseeneweneeeweeeneeneenee\n" +
				"nwnwnwnwnwenwnwnwnwswnwenwnwnwnewnwswswnw\n" +
				"nwwnenwwenwnwwwsenwnwnwswnwwneww\n" +
				"senewswswnenenenwneeneneneneneeenenenene\n" +
				"eeseseeseesesenweseeseeseewse\n" +
				"neswnesenenwswnenwswnenwswnwneswswnewseee\n" +
				"senwnwnenenwnwnwnenenwnwnenwnwnenene\n" +
				"wwnwwwwwwwwsenewnwww\n" +
				"nwnwnwnwwnwnenwnenwnwneneenwswenwnwse\n" +
				"wwwwnwsewswswwswenwswwswswwwww\n" +
				"nwwwwnwwswenwwwnwenwnwnwnwswnww\n" +
				"nenwesenwewneneseeseenwenewnenee";

	}

}
