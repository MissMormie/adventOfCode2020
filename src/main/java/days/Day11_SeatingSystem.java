package days;

import helpers.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day11_SeatingSystem {

	public static void main(String[] args) {
		System.out.println("answer A: " + runA(textInput()));
		System.out.println("answer B: " + runB(textInput()));
	}

	public static long runA(String input) {
		String[] lines = input.split("\n");
		Map<String, SeatA> seatMap = IntStream.range(0, lines.length)
				.mapToObj(x -> {
					char[] chars = lines[x].toCharArray();
					return IntStream.range(0, chars.length)
							.filter(y -> chars[y] == 'L')
							.mapToObj(y -> new SeatA(x, y))
							.collect(Collectors.toList());
				})
				.flatMap(List::stream)
				.collect(Collectors.toMap(seat -> seat.getCoords(), seat -> seat));

		seatMap.values().stream().forEach(seat -> seat.setAdjacent(seatMap));

		do {
			seatMap.values().forEach(SeatA::calculateNextState);
			long count = seatMap.values().stream().filter(SeatA::moveToNextStateChanged)
					.count();
			if(count  == 0l) {
				break;
			}

		} while (true);
		long count = seatMap.values().stream().filter(seat -> seat.taken)
				.count();
		return count;
	}

	public static long runB(String input) {
		String[] lines = input.split("\n");
		Map<String, Seat> seatMap = IntStream.range(0, lines.length)
				.mapToObj(x -> {
					char[] chars = lines[x].toCharArray();
					return IntStream.range(0, chars.length)
							.filter(y -> chars[y] == 'L')
							.mapToObj(y -> new Seat(x, y))
							.collect(Collectors.toList());
				})
				.flatMap(List::stream)
				.collect(Collectors.toMap(seat -> seat.getCoords(), seat -> seat));

		seatMap.values().stream().forEach(seat -> seat.setAdjacent(seatMap));

		do {
			seatMap.values().forEach(Seat::calculateNextState);
			long count = seatMap.values().stream().filter(Seat::moveToNextStateChanged)
					.count();
			if(count  == 0l) {
				break;
			}

		} while (true);
		long count = seatMap.values().stream().filter(seat -> seat.taken)
				.count();
		return count;
	}

	private static String textInput() {
		return "LLLLLL.LL.LLLLLL.LLLLLLL.LLLLLL.LLLLL.LLLLLLLLLLLLLLLLLL.LLLL.L.LLL.LLLLLLLL.LLLLLLLL.LLLLL\n" +
				"LLLLLL.L.LL.LLLL.L.LLLL.LLLLLLL.LLLLL.LLLLLLLL.LLLLLL.LLLLLLL.LLLLLLLLLLLLLL.LLLLLLLL.LLLLL\n" +
				"LLLLLL.LLLL.LLLL.LLLLLLLLLLLLLL.LLLLL.LLLL.LLLLLLLLLLL.L.LLLL.LLLLL.LLL..LLL.LLLLLLLLLLLLLL\n" +
				"LLLLLL.LLLL.LLLLLLLLLL.L.LLLLLLLLLLLLLLLLLLL.L.LLLLLLL.L.L.LL.LLLLL..LLLLLLL.LLLLLLLL.LL.LL\n" +
				"LLLLLL.LLLL.LLLL.LLLLLLLLLLLLL..LLLLLLLLLLLLLL.LLLL.LL.LLLLLL.LLLLL.LLL.LLLL.LLLLLLLL.LLLLL\n" +
				"LLLL.L.LLLLLLLLLLLLLLLLLLLLLLLL.LLLLL.LLLLLLLL.LLLLLLL.LLL.L..LLLLL.LLLLLLLL.L.LLLLLL.LLLLL\n" +
				"LLLLLL..LLL.LLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLL.L.LLLL.LLLLLL.LL.LLLLL.LLLLLLLLLLLLLLLLL.LLLLL\n" +
				"LLLLLL.LLLLLLLLLLLL.LLLL.LLLLLL.LLLLL.LL.LLLLLLLLLLLLLLLLLLLL.LLLLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
				"LLLLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLLLLL.LLLLLLLL..LLLLLLLLLLLLL.LLLLL.LLLLLLLL.LLLLLLLL.LLLLL\n" +
				".L.L......LL..LL.....L..L...L...L.L...L.LL.....LL..L..L....L...L....L......L.L.L...L.LLLL..\n" +
				"LLLLLLLLL.L.LLLL.LLLLLLLLLLLLLLLLLLLL.LLLLLLLL.LLLLLLL.LLLLLL.LLLLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
				"LLLLLLLLLLLLLLLL.LLLLLLL.LLLL.L.LLLLL.LLLLLLLL.LLLLLLL.LLLLLLLLLLL..LLLLLLLL.LLLLLLLL.LLLLL\n" +
				"LL.LLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLL.LL.LLLL.LLLLLLLLLLLL.LLLLLLLL.LLLLLLLL.LLLLL\n" +
				".LLLLL.LLLL.LLLL..LLLLLL.L.LLLLLL.LLL.LLLLL.LL.LLLLLLL.LLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLL.LLL\n" +
				"LLLLLL.LLLL.LL.LLLLLLLL.LLLLLLL.LLLLL.LLLLLLLL.LLLLLLL.LLLLLL.LLLLL.LLLLLLLLLLLLL.LLL.LLLLL\n" +
				"LLLLL.LLLLLLL.LL.LLLLLL..LLLLLL..LLLL.LLLLLLLL.L.LLL.L.LLLLLLLLLLLL.LLLLLLLLLLLLLLLLL.LLLLL\n" +
				"LLLLLLLLLLL.LLLL.LLLLLLL.LLLLLLLLLLLL.LLLLLLLL.LLLLLLL.LLLLLLLLLLLL.LLLLL.LLLLLLLLLLL.LLLLL\n" +
				".LLLLL.LLLL.L.LL..LLLLLL.LLLLLL.LLLLL.LLLLLLLLLLLLLLLLLLLLL.L.LLLLLLLLLLLLLLLLLLLLLLL.LLLLL\n" +
				"LLLLLL.LLLLLLLLL.LLLLLLL.LLLLLLLLLLLL.LLLLLLLL.LLLLLLL.L.LLLL.LLLLL.LLLLLLLL..LLLLLLL.LLLLL\n" +
				".L...LL.LLL....LL..LL...L..LLL.LL...L...L........L....LL..L...L.....LL......L...L..LL....L.\n" +
				"LL.LLL.LLLLLLLLL.LLLLLLL.LLLLLL.L.LLL.LLLLLLLL.LLLLLL..LLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL\n" +
				"LLLLLLLLLLL.LLLL.LLLLLLLLLLLLLL.LLLLLLLLLLLL.LLLLLLLLLLLLLLLL.LLLLL.LLLLLLL..LLLLLLL...LLLL\n" +
				"LLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLL.LLLL.LLLLLLLL.LLLLLLL.LLLLLLLLLLLL..LLLLLLL.LLLLLLLL.LLLLL\n" +
				"LLL.LL.LLLLLLL.LLL.LLLLL.LLLLLLLLLLLL.LL.LLLLL..LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLL\n" +
				"LLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLLLLLL.LLLLLLLLL.LLLLL\n" +
				"L..LLL.LLLL.LLLLLLLLLLLL.LL.LLLLLLLLLLLLLLLLLL.LLLLLLLLLL.LLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n" +
				"LLLLLL.LLLL.LLLL.LLLLLLLLLLLLLL.LLLLL.LLLLLLLL.LLLLLLLLLLLLLL.LLLLL.LLLLLLLLLLLLL.LLL.LLLL.\n" +
				"LLLLLL.LLL..LLLLLLLLLLLL.LLLLLLLLLLLLLLLLL.LLL.LLLLLLL.LLLLLL.LLLLL.LLLL.LLL.LLLLLLLL.LLLLL\n" +
				"..LL...LLL....L....L.LLL.L.L...L.LLL..L...L..L..L........L.LL.L.L.LLLLLL...L.L.LL.L....LL..\n" +
				"LLLLLL.LLLLLLLLL.LL.L.LL.LLLLLL.L.LLL..LLLLLLL.LLLLLLL.LLLLLL.LLLLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
				"LLLLLL..LL.LLLLLLLL.LLLL.LLLLLL.LLLLL.L.LLL.LL.LLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLL.L..LL\n" +
				"LLLLLL.LLLLLLLLL.LL.LLLLLLLLLLL.LLLLL.LLLLLLLLLLLLLL.L.LLLLLL.LLLLL.LLLLLLLL.LLLLLLLL.LLLLL\n" +
				"LLLLLL.LLLL.LLLLLLL.LLLLLLLLLLLLLLLLL.LLLLLLLLLLLLL.LL.LLLLLL.LLLLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
				"LLLLLL.LLLLLLLLL.LLLLLLL.LLLLLL.LLLLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
				"LLLLLL.LL...LLLL.LLLLLLL.L.LLLL.LLLLLLLLLLLLLLLLLLLLLL.LLLLLL.LLLLL.LLLLLLLLLLLLLLLLLLLLLLL\n" +
				"L.L.LL.....L.......L.....LLL..L......LL.LLL....L.L.L..........L.LL..LLL..L....LL..L..L..L.L\n" +
				"LLLLLL.LLLLLLLLLLLLLLLLLLLLLLLL.LLLLL.LLLLLLLL.LLL.LLL.LLLLLL.LLLLLLLLLLLLLL.LLLLLLLLLLLLLL\n" +
				"LLLLLL.LLLL.LLLLLLLLLLLL..LLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLL.LL.LL.LLLLLLLLLLLLLLLLL.LL.LL\n" +
				"LLLLLLLLLLL.LLLL.L.LLLLL.LLLLLL.LLLLL.LLLLLLLL.LLLLLLLLLLLLLL.LL.LLLLLLLLLL..LLLLLLLL.LLLLL\n" +
				"LLLLLLLLLLLLLLLL.LL.LLLLLLLLLLL..LLLL.LLLLLL.L.LLLLLLL.LLLLL..LLLLLLLLLL.LLL.LLLL.LLLLLLLLL\n" +
				"LLLLLL.LLLL.LLLLLLLLLLLL.LLLLLL.LLLLLLLLLLLLLL.LLLLLLL.LLLLLL.LLLLLLLLLLLLLL.LLLLLLLLLLLL.L\n" +
				"LLLLL.LLLLLLLLLLLLLLL.LL.LLLL.L.LLLLL.LLLLLLLLLLLLLLLL.LLLLLL.L.LLLLLLLLLLLL.LLLLLLLL.LLLLL\n" +
				"..LLLL.L...LLL.L......L....L.L..L......LL..L.LL.LL.LL................LL.LL....L.........L..\n" +
				"LLLLLL.LLLL.LLLL.LLLLLLL.LLLLLL.LLLLL.LLLLLLLL.LLLLLLL.LLLLLL.LLLLLLLLLLLLL.LLLLLLLLL..LLLL\n" +
				"LLLLLLLLLLLLLLLL.LLLLLLL.LLLLLL..LLLLLLLLLLLLL.LLLLLLL.LLLLLL.LLLLLLLLLLLLLL.L.LL.LLL.LLL.L\n" +
				"LLLLLL.LLLL.LLLL.LLLLLLL.LLLLLL.LLLLLLL.LLLLLL.LLLLLLL.LLLLLLLLLLLLLLLLLLLLL.LLLLLLLL.LLLLL\n" +
				"LLLLLL.LLLL.LL.L.LLLLLLL.LLLLLLLLLLLL.LLLLLLLL.LLLLLLL.LLLLLL.L.L.L.LLLLLLLL.LL.LLLLLLLL.LL\n" +
				"LLL.LL.LLLL.LLLL.LLLLLLL.LLLLL..LLLLL.LLLL.LLL.LLLLLLLLLLLLL..LLLLLLLLLLLLLLLLLLLLLLL.LLLLL\n" +
				"LLLLLLLLLLL.LLLL.LLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLLLLLL..LLLLLL.LLLLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
				"L.LLLL.LLLL.LLLLLLLLLLLL.LLLLLLLL.LLLLLLL.LLLL.LLLLLLL.LLLLLL.LLLLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
				"LLLLLL.LLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLL.L..LLLLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLLLLLL.LLLLL\n" +
				"..L..L.L.LLLL.....LLL.LLL....L..LL.L..L......L.............LL....LL..L.L..L...L.....L.L...L\n" +
				"LLLLLL.LLLL.L.LL.L.LLLLLLLLLLLL.LLLLL.LLLLLLLL.LLLLLLL.LLLLLL.LLLLL.LLLLLLLLLL.LLLLLLLLLLLL\n" +
				"LLLLLL..LLL.LLLL.LLLLLLL.LLLLLL.LLLLL.LLLL.LLL.LLLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLL\n" +
				"LLLLLL.LLLL..LLLLLLLLLLL.LLLLLL.L.LLL.LLLLLLLLLLLLLL.LLLLLLLLLLLLLL.LL.LLL.L.LLLLLLLL.LLLLL\n" +
				"LLLLLL.LLLLLLLLLLLLLLLLL.LLLLLL.LLLLL.LLLLLLLL.LLLLLLL.L.LLL.LLLLLLLLLLLLLLL.LLLLLLLL.LLLLL\n" +
				"LLLLLL.LL.L.LL.L.LLLLLLLLL.LLLLLLLLLL.LLLLLLLL.LLLLLLL.LLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL\n" +
				"LLLLLLLLL.L.LLLLLLLLLL.L.LLLLL.LLLLLL.LLLLLLLL.LLLLLLL.LLLLLL.LLLLL.LLLLLLLLLL.LLLLLLLLLLLL\n" +
				"LLLLLL.LLLLLL.LL.LLLLLLLL.LLLLL.LLLLL.LLLLLLLL.LLLLLLL.LLLLLL.LLLLL.LLLLLLLLL.LLLLLLLLLLLL.\n" +
				"LLLLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLL.LLLLLLL.LLLLLL.LLLLL.LLLLLLLLL.LLLLLLLL.LLLL\n" +
				"....L..L..LL....L.L.LLL.L.L...L...L.L.L..L..L...LLL.......L.......LL..L...LL..L.L....LL.LL.\n" +
				"LLLLLL.LLLL.LL.LLLLLLLLLLLLLLLLLLLLLL.LLLLLLLL.LLLLLLL.LLLLL.LLLLLLLLLLLLLLL.LLLLLLLL.LLLLL\n" +
				"LLLLLL.LLLL.LLLL..LLLLLL.LLLL.L.LLLLL.LLL.LLLL.LLLLLLL.LLLLLLLLLLLL.L.LLLLLL.LLLLLLLLLLLLLL\n" +
				"LLLLLL.LLLL.LLLLLLLLLLLL.LLLLLL.LLLLL.LLLLLLLL.LLLLLLLLLLLLLL.LLLLLLLLLLLLLL.L.LLLLLL..LLLL\n" +
				"LLLLLLLLLLLLLLLL.LLLLLLL.LLLLLL.LLLLL.LLLLLLLLLLLLLLLL.L.LLLL.LLLLL.LLLLL.LL.LLLLLLLL.LLLLL\n" +
				".LLLLL.LLLL.LLLLLLLLLLLL.L.LLLLLLLLLL.LLLLLLLL.LLLLLLLLLLL.LL.L.LLLLLLLLLLLL.LLLLL.LL.LLLLL\n" +
				"LL.LLL.LLLL.LLLL.LLLLLLLLLLLLLL.LLLLL.LLLL.LLL.L.L.LLL.LLLLLL.LLLLL.LLLLLLLL.LLLLLL.L.LLLLL\n" +
				"LLLLLLLLLLLLLL.L.LLLLLLL.LLLLLL.LLLLL.LLL.LLLLLLLLLLLL.L.LLLL.LLLLL.LLLLLLLLLLLLLLLLL.LLLL.\n" +
				"LLLLLL.L.LL.LLLLLLLLLLLL.LL.LLL.LLLLL.LLLLLLLL.LLLLLLL.LLLLLL.L.LLL.LLLLLLLL.LLLLLLL.LLLLLL\n" +
				"LLLLLLLLLLL.LLLLLLLLLLLL.L.L.LL.LLLLL.LLLLLLLLLLLLLLLL.LLLLLLLL.LLL.LLLLL.LLLLLLLLLLL.LLLLL\n" +
				".L...LL..LL....L...L.....LLLL..L....L..LL...L...LLL...L.LLL..L.LL.L.L.LL.L..LL.....LLLL.LL.\n" +
				"LLLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLL.LLLL..LLLLLLLL.LLLLLLLLLLLLLL\n" +
				"LLLLLLLLLLL.L.LLLLLL.LLLLLLLLLL.L.LLL.LLLL.LLLLLLLLLLL.LLLLLL.LLLLL.LLLLLLLLL.LLLLLLL.LLLLL\n" +
				"LLLLLL.LLLL.LLLL.LLLLLLL.LL.LLLLLLLLL.LLLLLLL..LLLLLLL.LLL.LL.LLLLL.LLLLL.LL.LLLLLL.L.LLLLL\n" +
				"LLLLLL.LLLL.LLLL.LLLLLLLLLLL.LL.LLLLLLLLLLLLLL.LLLLLLL.LLLLLLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
				"LLLLLLLLLLL.LLLLLLLLLLLL.LLLLLL.LLLLL..LLLLLLL.LLLLLLL.LLLLLL.LLLLL.LLLLLLLL..LLLLLLL.LLLLL\n" +
				"LLL.LLLLLLL.LLLL.LLLLLLL.LLLLLLLLL.LL.LLLLLLLL.LLLLLLL..LLLLL.LLLLLLLLLLL.LL.LLLLLLLLLLLLL.\n" +
				"LLLLLLLL.LLLLLLL.LLLLLLLLLLLLLL.LLLLL.LLLLLLLL.LLLLLLL.LLLLLLLLLLLL.LLLLL.LLL.LLLLLLLLLLLLL\n" +
				"...L...L.L......L..L..L.LL...L..........L..L.L.L........L..L..L.L.LL..LL..LL.........L.....\n" +
				"LLLL.L.LLLL.LLLL.LLLLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLL.LLLLL\n" +
				"LLLLLLLLLLL.LLLLL.LLLLLLLLLLLLL.LLLLL.LLLLLLLL.LLLLLLLLLLLLLL.LLLLLLLLLL.LLLLLLLLLLLLLLLLLL\n" +
				"LLLLLL.LLLLLLLLLLLLLLLLL.LLLLLL.L.LLL.LL.LLLLLLLLLLLL..LLLLL..LLLLL.LLLLLLL..LLLLLLLLLLLLLL\n" +
				"LLLLLL.LLLL.LLLL.LLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLL.LLLL.LLLLL\n" +
				".LLLLL.LLLLLLLLL.LLLLLLL.LLLLLLLLLLLLLLLLLLLLL.LLLLLLL.LLLLLL.LLLLL.LLLLLLLL.LLLLLLLL.LLLLL\n" +
				"L.LL.LLLLLL.LL.L..LLLLLL.LLLLLL.LLLLLLLLLLLLLL.LL.LLLLLLLLLLL.LLLLLLLLLLLLLLLLLLL.LLL.LLLLL\n" +
				"L..L...L..L...LL...L.L.L..LLL.LL...L......LLL...L.L....L.......L...L.LL.L....L....L...L....\n" +
				"LLLLLL.LLLL.LLLL.L.LLLLL.LLL.LL.LLLLL.LLLL.LLL.LLLLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLLLLLL.LLLLL\n" +
				"LLL.LL.LLLL.LLL...L.LLL..LLLLLL.LLLLL.LLLLLLLL.LLLLLLLLLLLLLL.LLLLLLL.LLLLLLLLLLLLLLL.LLLLL\n" +
				"LLLLLL.LLLL.LLLL.LLLLLLL.LLLLLL.LLLLLLLLLLLLLL.LLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLLLLLLLL.LLLLL\n" +
				"LLLLLLLLLLLLLLLL.LLLLLLL.LLLLLLLLLLLLLLLLLLLLL.LLL.LLL.LLLLLLLLLLLLL.LL.LLLLL.LLLLLLL.LLLLL\n" +
				"LLLLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLL.LLLLLLL.LLLLLL.LLLLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
				"LLLLLLLLLLL.LLLL.LLLLLLL.LLLLLL..LLLL.LLLLLLLL.LLLLLLLLL.LLLLLLLLLL.LLLLLLLL.LLLLLLLL.LLLL.\n" +
				"LLLLLL.LLLLLLLLLLLLLL.LL.L.LLLLLLLLLL.LLL.LLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLL.LLL.L\n" +
				"LLLLLL.LLLLLLLLL.LLLLLLLLLLL.LLLLLLLL.LLLLL.LLLLLLLLLLLLLLLLL.LLLLL.LLLLLL.L.LLLLLLLLLLLLLL\n" +
				"LLLLLL.LLLLLLLLL.LLLLLLLLLLLLLL.LLLLL.LLLLLLLLLL.LLLLL.LLLLLL.LLLLLLLLLLLLLL.LLLLLLLL.LLLLL";

	}

	private static class Seat extends Coordinate {
		public List<Seat> adjacentCoords;

		boolean nextTakenState;

		boolean taken;

		public Seat(int x, int y) {
			super(x, y);
		}

		public void calculateNextState() {
			long occupiedSeats = adjacentCoords.stream()
					.filter(seat -> seat.taken)
					.count();
			// If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
			if (!taken && occupiedSeats == 0l) {
				nextTakenState = true;
				return;
			}
			// If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
			// Otherwise, the seat's state does not change.
			if (taken && occupiedSeats >= 5) {
				nextTakenState = false;
			}
		}

		public boolean moveToNextStateChanged() {
			boolean changed = nextTakenState != taken;
			taken = nextTakenState;
			return changed;
		}

		public void setAdjacent(Map<String, Seat> coordinateMap) {
			adjacentCoords = new ArrayList();
			addNextSeatOnLine(0,1, coordinateMap);
			addNextSeatOnLine(1,1, coordinateMap);
			addNextSeatOnLine(1,0, coordinateMap);
			addNextSeatOnLine(1,-1, coordinateMap);
			addNextSeatOnLine(0,-1, coordinateMap);
			addNextSeatOnLine(-1,1, coordinateMap);
			addNextSeatOnLine(-1,0, coordinateMap);
			addNextSeatOnLine(-1,-1, coordinateMap);


		}

		private void addNextSeatOnLine(int lineX, int lineY, Map<String, Seat> coordinateMap) {
			Optional<Seat> first = IntStream.range(1, 100).mapToObj(i -> {
				String coords = new Coordinate(x + lineX * i, y + lineY * i).getCoords();
				if (coordinateMap.containsKey(coords)) {
					return coordinateMap.get(coords);
				}
				return null;
			}).filter(Objects::nonNull)
					.findFirst();
			if(first.isPresent()) {
				adjacentCoords.add(first.get());
			}
		}
	}


	private static class SeatA extends Coordinate {
		public List<SeatA> adjacentCoords;

		boolean nextTakenState;

		boolean taken;

		public SeatA(int x, int y) {
			super(x, y);
		}

		public void calculateNextState() {
			long occupiedSeats = adjacentCoords.stream()
					.filter(seat -> seat.taken)
					.count();
			// If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
			if (!taken && occupiedSeats == 0l) {
				nextTakenState = true;
				return;
			}
			// If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
			// Otherwise, the seat's state does not change.
			if (taken && occupiedSeats >= 4) {
				nextTakenState = false;
			}
		}

		public boolean moveToNextStateChanged() {
			boolean changed = nextTakenState != taken;
			taken = nextTakenState;
			return changed;
		}

		public void setAdjacent(Map<String, SeatA> coordinateMap) {
			adjacentCoords = new ArrayList();

			for (int x = -1; x < 2; x++) {
				for (int y = -1; y < 2; y++) { // loop adjecent acres
					String coords = makeCoordString(this.x - x, this.y - y);
					if (!coords.equals(getCoords())) {
						SeatA coord = coordinateMap.get(coords);
						if (coord != null) {
							adjacentCoords.add(coord);
						}
					}
				}
			}
		}
	}

}
