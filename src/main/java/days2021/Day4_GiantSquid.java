package days2021;

import helpers.Coordinate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Day4_GiantSquid {
	public static void main(String[] args) {
		System.out.println("answer A: " + runA(textInput(), bingoNumbers()));
		System.out.println("answer B: " + runB(textInput(), bingoNumbers()));
	}

	public static int runA(String input, String bingoNumbers) {
		List<BingoCard> cards = Arrays.stream(input.split("\n\n")).map(BingoCard::new).collect(Collectors.toList());
		String[] split = bingoNumbers.split(",");
		Optional<BingoCard> winningCard;
		int index = 0;
		do {
			int number = Integer.parseInt(split[index]);
			winningCard = cards.stream().filter(card -> card.isWinningNumber(number)).findFirst();
			index++;
		} while(!winningCard.isPresent());
		return winningCard.get().getScore();

	}


	public static int runB(String input, String bingoNumbers) {
		List<BingoCard> cards = Arrays.stream(input.split("\n\n")).map(BingoCard::new).collect(Collectors.toList());
		String[] split = bingoNumbers.split(",");
		List<BingoCard> lastWinningCards = new ArrayList<>();
		for (int index = 0; index < split.length; index++) {
			int number = Integer.parseInt(split[index]);
			List<BingoCard> winningCards = cards.stream().filter(card -> card.isWinningNumber(number)).collect(Collectors.toList());
			cards.removeAll(winningCards);
			if(!winningCards.isEmpty()) {
				lastWinningCards = winningCards;
			}
		}
		return lastWinningCards.get(0).getScore();
	}


	private static class BingoCard {
		Map<Integer, Coordinate> coordinateMap = new HashMap<>();
		int[] rows = new int[5];
		int[] columns = new int[5];


		public BingoCard(String bingoCard) {
			if(bingoCard.charAt(0) == ' ') {
				bingoCard = bingoCard.substring(1);
			}
			String[] numbers = bingoCard.replace("\n", " ").replace("  ", " ").split(" ");
			for(int x = 0; x < 5; x++){
				for (int y = 0; y < 5; y++) {
					try {
						coordinateMap.put(Integer.parseInt(numbers[(y * 5 + x)]), new Coordinate(x, y));
					} catch (Exception e) {
						e.printStackTrace();;
					}
				}
			}

		}

		Integer winningNumber;
		// side effects much?
		public boolean isWinningNumber(int number) {
			Coordinate coord = coordinateMap.remove(number);
			if(coord == null) {
				return false;
			}
			columns[coord.x] += 1;
			rows[coord.y] += 1;
			if(rows[coord.y] == 5 || columns[coord.x] == 5) {
				winningNumber = number;
				return true;
			}
			return false;
		}

		public int getScore() {
			return coordinateMap.keySet().stream().collect(Collectors.summingInt(Integer::intValue)) * winningNumber;
		}
	}

	private static String bingoNumbers() {
		return "31,50,79,59,39,53,58,95,92,55,40,97,81,22,69,26,6,23,3,29,83,48,18,75,47,49,62,45,35,34,1,88,54,16,56,77,28,94,52,15,0,87,93,90,60,67,68,85,80,51,20,96,61,66,63,91,8,99,70,13,71,17,7,38,44,43,5,25,72,2,57,33,82,78,89,21,30,11,73,84,4,46,14,19,12,10,42,32,64,98,9,74,86,27,24,65,37,41,76,36";
	}

	private static String textInput() {
		return  "31  5 70  8 88\n" +
				"38 63 14 91 56\n" +
				"22 67 17 47 74\n" +
				"93 52 69 29 53\n" +
				"33 66 64 19 73\n" +
				"\n" +
				"35 63 17 48 77\n" +
				"25 58 33 14 96\n" +
				"32 87 90 66 70\n" +
				"16  4 98 72 23\n" +
				"19 74 39 29 59\n" +
				"\n" +
				"40 29 44 17 27\n" +
				"56 98 83 62 70\n" +
				"25 91 20 60 84\n" +
				"42 66 34 77 31\n" +
				"16  8  6 50 28\n" +
				"\n" +
				"13  6 58 39 74\n" +
				" 3 15 69  5 23\n" +
				"81 65  0 85 93\n" +
				"72  7 60 42 52\n" +
				"45 64 47 53 67\n" +
				"\n" +
				"94 21 67  0 14\n" +
				" 2 75 77 15 78\n" +
				"38 25 49 99 92\n" +
				"76 35 69  4 64\n" +
				"42 96 86 84 70\n" +
				"\n" +
				"46  7 74 65 80\n" +
				"99 12  4 38 77\n" +
				"30 90 78 94 21\n" +
				"22 15 72 52 57\n" +
				"11 67 59  3  9\n" +
				"\n" +
				"18 17 63 53 96\n" +
				"16 76 55  5 92\n" +
				"33 82 60 51  8\n" +
				"29 99 87 95 58\n" +
				"88 15 75 61 21\n" +
				"\n" +
				"10 24 79 28 90\n" +
				"64 43 21 48 99\n" +
				"45 85 80 71 94\n" +
				"68 39 57 50 72\n" +
				"47 60  3 62 49\n" +
				"\n" +
				"82 92 58 16 89\n" +
				"76 90 74 61 29\n" +
				" 2 72 43 69 23\n" +
				"84 38  0  4 55\n" +
				" 5 64 49 78 94\n" +
				"\n" +
				"79 60 28 45 95\n" +
				"47  2 93 89 77\n" +
				"56 18 54 97 33\n" +
				"55 91 68 58 90\n" +
				"87 37 88 35 10\n" +
				"\n" +
				"42 76 25 15 38\n" +
				" 6 34 33 88 30\n" +
				"43 10 27 26 72\n" +
				"78 66 62 14 37\n" +
				" 1 65 95 54  5\n" +
				"\n" +
				"14  0 46 72 75\n" +
				"83  2 62 76 26\n" +
				"65 85 19 18 95\n" +
				"57 45 87 51 29\n" +
				"32 37 61 34 43\n" +
				"\n" +
				" 9 75 56 60 50\n" +
				"53 81 41 55 11\n" +
				"86 54 29 85  2\n" +
				"92  6 97 46 84\n" +
				" 5  0 70  3 82\n" +
				"\n" +
				" 3 15 61 94 85\n" +
				"62  7 12 10 45\n" +
				"84 66 18 33 86\n" +
				"91 19 11 22 39\n" +
				"57 16 80 26  9\n" +
				"\n" +
				" 1 69 46 98 61\n" +
				"19  7 63 56 90\n" +
				" 9  3 66 38 73\n" +
				"40 49 72  0 94\n" +
				"42 23 75 89 32\n" +
				"\n" +
				"33 59 17 15 86\n" +
				"62 96 84  2 18\n" +
				"27 63 55  3 82\n" +
				" 0 89 19 73 24\n" +
				"56 93 12 87 41\n" +
				"\n" +
				"31 56 33  3  9\n" +
				"55 52 87 57 30\n" +
				"44 89  6  5 65\n" +
				"15 53 62 51 11\n" +
				"61 35 13  4 46\n" +
				"\n" +
				"15 84 95 88 65\n" +
				"96 18 93 14 21\n" +
				"49 40 46  1 30\n" +
				"50 64 69 80 81\n" +
				" 3 19 87  9 38\n" +
				"\n" +
				"71 68 18 81 41\n" +
				"95 26 45 24 25\n" +
				"42 73 62 36 94\n" +
				" 5 61 98 99 48\n" +
				"31 55  8  1 88\n" +
				"\n" +
				" 0 73 19 12  4\n" +
				"72 22 27 59 48\n" +
				"71 26 28 66 37\n" +
				"54 87  8  2 65\n" +
				"20 60 35 32 29\n" +
				"\n" +
				"44 41 93 76 47\n" +
				"24 33 15 14 61\n" +
				"78 49 81 67 52\n" +
				"74 73 54 23 66\n" +
				"75 10 18  3 60\n" +
				"\n" +
				"94 95 52 23 10\n" +
				"55 14 35 44 56\n" +
				" 0 49 50 60 79\n" +
				"59 54 90 67 46\n" +
				"17 81  4 37 58\n" +
				"\n" +
				"38 29  8 79 77\n" +
				"91 98  1 68 30\n" +
				"86 13 95 46 61\n" +
				"55 36 90 42 97\n" +
				"69  7  0 93 27\n" +
				"\n" +
				"82  5 67 40  3\n" +
				"61 16 56 68 29\n" +
				"55 20 13 15  0\n" +
				"75 28 45 17 77\n" +
				"86 85 10  2 26\n" +
				"\n" +
				"98 73 72 43 17\n" +
				"32 41 56 46 76\n" +
				"85  7 31 69 91\n" +
				"86 21 37 40 82\n" +
				"33 57 15 39 30\n" +
				"\n" +
				"83 18 90 75 29\n" +
				"17 73 32 88 50\n" +
				"51 99  5 23 22\n" +
				"60 47 35 42  2\n" +
				"37 86 82 14 30\n" +
				"\n" +
				"17 47  7 54  0\n" +
				"65 83 33 11 57\n" +
				"85 16 27 75 61\n" +
				"60 90 44 69 81\n" +
				"71 73 38 46 28\n" +
				"\n" +
				"57 79 76 91 99\n" +
				"96 20 13 34 52\n" +
				" 0 21  5 47 63\n" +
				"60 62 55 29 71\n" +
				"44 59 58 78 65\n" +
				"\n" +
				"83 30  7  1 80\n" +
				"90  2 11 41 92\n" +
				" 3 27 33 10 78\n" +
				"63 75  0 38 86\n" +
				"96 76 87 67  6\n" +
				"\n" +
				"63 19 42 56 12\n" +
				"35 89 87 13 81\n" +
				"21 23  4 16 11\n" +
				"64 78 25 92 33\n" +
				"41 10 66 68 99\n" +
				"\n" +
				"56 76 37 18 41\n" +
				"38 27 17 50 14\n" +
				"60 23  0  6  1\n" +
				" 9  8 30 93 44\n" +
				"58 99 16 46 26\n" +
				"\n" +
				"40 77  4 96 68\n" +
				"55 21 30 24 76\n" +
				"81 58 41 91 98\n" +
				"56 46 20 78  0\n" +
				"82 29 87 67 53\n" +
				"\n" +
				"59 33  4 18 95\n" +
				" 2 52 54 96 19\n" +
				"46 72  3 43 81\n" +
				"20 97 87 55 53\n" +
				" 9 80 84 76 29\n" +
				"\n" +
				" 2 25 44 64 46\n" +
				"93 35 17 84 67\n" +
				"14 48 21 73 90\n" +
				"89 62 53 54 52\n" +
				"38 59 69 45 50\n" +
				"\n" +
				"71 96 56 57 72\n" +
				"47 39 25 15 90\n" +
				"29 65 44  3 83\n" +
				"36 30 92 59 95\n" +
				"97 86 48 74 94\n" +
				"\n" +
				"51 91 75 25 28\n" +
				"72 56  9 21 93\n" +
				"88 32 58 24 37\n" +
				"82 48 95 53 23\n" +
				"68 20 43 84 69\n" +
				"\n" +
				"68 75 33 39 96\n" +
				" 0 65 45 27 53\n" +
				"36 71  2 91 97\n" +
				"10 82 44 41  7\n" +
				"11 42 17 26 14\n" +
				"\n" +
				"61 36 56  6 64\n" +
				"72 10 62 77 21\n" +
				"99 73 37 38 25\n" +
				"60  7 44 43 14\n" +
				"81 59 29 19 92\n" +
				"\n" +
				"63 49 13 31 56\n" +
				"59 76 62 83 44\n" +
				"69 24 57 40 91\n" +
				"20 41  2 55  9\n" +
				"50 60 46 15 52\n" +
				"\n" +
				"87 90 97 53 23\n" +
				"85 67  7 71 98\n" +
				" 1 22 48 82 69\n" +
				"15 21 17 91 80\n" +
				"99 57 28 94 79\n" +
				"\n" +
				"24 25 44 95 99\n" +
				"55 97  9 18 27\n" +
				"71 37 13 52 39\n" +
				"30  3 79 14 28\n" +
				"81 62 98 22 31\n" +
				"\n" +
				"49 90 17 65  4\n" +
				"59 42 15 14 54\n" +
				"91 55 67 58  8\n" +
				"13 61 79 32 99\n" +
				"92 28 16 72 20\n" +
				"\n" +
				"73 47 39 94 45\n" +
				"93 53 95 71 27\n" +
				"66 40 65 37 58\n" +
				"15 61 63 50 55\n" +
				"57  8 99 90 85\n" +
				"\n" +
				" 6 45 79  9 14\n" +
				"82 12 42 38 15\n" +
				"31 71 48  5 96\n" +
				"35 81 25 63 19\n" +
				" 2 90 64 22 33\n" +
				"\n" +
				"62 56 60 29 63\n" +
				"47 33 75 77 76\n" +
				"86  7 90 34 46\n" +
				"85  3  0 16 65\n" +
				"71 44 11 40 52\n" +
				"\n" +
				"18 19 73 67 64\n" +
				"66 85 37 10 51\n" +
				"62 46 11 40 35\n" +
				"83 69 57  1 78\n" +
				"81 48 36  6 22\n" +
				"\n" +
				"99  0 41 81 52\n" +
				"68 31 27 30  5\n" +
				"12 62 54 43 50\n" +
				"58 36 10 55 86\n" +
				"61 69 97 22 49\n" +
				"\n" +
				"59 18 91 86 90\n" +
				"87 20 57 61 42\n" +
				"15 99 31 32 73\n" +
				"23 89 64 96 49\n" +
				"79 47 97 19 51\n" +
				"\n" +
				"17 46 19 20 58\n" +
				"67 29  4  8 22\n" +
				"52 18 13 34 70\n" +
				"82 73 71 95 47\n" +
				"35 28 33 42 62\n" +
				"\n" +
				"15 16 78 99 74\n" +
				"61 81 84 72 69\n" +
				"12 11 17 62 94\n" +
				"76 63 96 42 98\n" +
				"89 64 10 32 18\n" +
				"\n" +
				"18 21 63 89 14\n" +
				"95  5 79 19 11\n" +
				"29 82 77 59 90\n" +
				"13  3 31 46 45\n" +
				"80 37 97 78 61\n" +
				"\n" +
				"75  9 94 11 80\n" +
				"93 27 10  2 82\n" +
				"89 32 64 52 60\n" +
				"42 56 23 20 18\n" +
				"70 78 45 76 40\n" +
				"\n" +
				"16 98  0 94 73\n" +
				"28 13 48 90 11\n" +
				"30 43 20  9 78\n" +
				"14 39 89  4  6\n" +
				"15 63 91 45 37\n" +
				"\n" +
				"41 55 44  2 39\n" +
				"38 14 19 72 64\n" +
				"75 95 35  6 47\n" +
				"70  7  1 29 86\n" +
				"83 79 90 96 82\n" +
				"\n" +
				"32 29 42 60  3\n" +
				"85 93 16 41 35\n" +
				"11 90 46 13 58\n" +
				"26 19 79 30 86\n" +
				"72 56 63 18 95\n" +
				"\n" +
				"25 71 26 35 59\n" +
				"80 21 11 24 87\n" +
				"48 29 64 66 34\n" +
				"74 83 84 60 57\n" +
				"44 30 95 20 32\n" +
				"\n" +
				"29 60 31  2  7\n" +
				"51 78 32 45 14\n" +
				"70  6 59 33 81\n" +
				"16 26 11 38 88\n" +
				"49 74 39 46  3\n" +
				"\n" +
				"58 91 95 21 47\n" +
				"61 52 30  2 96\n" +
				"83 19 89  9  1\n" +
				"41 64 28 23 11\n" +
				"62 55 43 60 53\n" +
				"\n" +
				"81 15 33  4 19\n" +
				"51 97 88 70 13\n" +
				"76 38 58 28 82\n" +
				"67  3  6 22 47\n" +
				"57 41 53 31 79\n" +
				"\n" +
				"29 42 12 64 86\n" +
				"23  0 14 28 82\n" +
				"99 63 79  9 17\n" +
				"75 73  3 45  1\n" +
				"93 52 43 54 76\n" +
				"\n" +
				"15 70 19  3 44\n" +
				"47 60 46 93 59\n" +
				"16 87 41 30 68\n" +
				"88  9 26 45 43\n" +
				"28 49 73 98 78\n" +
				"\n" +
				"57 30 65 22 25\n" +
				"99 21 36 47 11\n" +
				"70 83 31 73 16\n" +
				"61 77 94 93 23\n" +
				"91 26 13 87 63\n" +
				"\n" +
				"13 58 63  4 82\n" +
				" 5 89 11 39 51\n" +
				"12 43 75 97  8\n" +
				"15 56 21  0 74\n" +
				"23 66 62 70 44\n" +
				"\n" +
				"37 25 82 80 86\n" +
				"58 89 30 91 38\n" +
				"75 40 87 50 67\n" +
				"78  4 55  0 39\n" +
				"54 64 52 96 45\n" +
				"\n" +
				"61 57 42 74 26\n" +
				"88 48  8 13 70\n" +
				"81 69  7 97 10\n" +
				"16 23 18 55 36\n" +
				"11 46 68 39 27\n" +
				"\n" +
				"37 85 59 41  2\n" +
				" 3 29 91 87 84\n" +
				"65 18 70 78 12\n" +
				" 9 80 97 19 24\n" +
				" 1 75 58 13 92\n" +
				"\n" +
				"62  8 80 34 86\n" +
				"72  6 40 57  3\n" +
				"85 82 48 84 37\n" +
				"79 29 70 21 96\n" +
				"41  5 33 32 94\n" +
				"\n" +
				"58 21 50  7 72\n" +
				"34 71 93 35 90\n" +
				"77 43 79 55 88\n" +
				"57 12 45 95 70\n" +
				"38  9 29 37 52\n" +
				"\n" +
				"57 26  2 22 98\n" +
				"87  4 53 17 97\n" +
				"75 25 70 62 93\n" +
				" 3  9 86 33 28\n" +
				"58 50 72 27  5\n" +
				"\n" +
				"16 15 71 64 94\n" +
				"37 56 84 32  7\n" +
				"54 99 73 20  8\n" +
				"60 19 22 70 27\n" +
				"69 83 79 48 77\n" +
				"\n" +
				"94 38  0 31 19\n" +
				"43 58 22 93 84\n" +
				" 2 88 56 13 50\n" +
				"90 68 46 95 47\n" +
				"32 59 89 42 69\n" +
				"\n" +
				"94 60 26 63 86\n" +
				"21 64 81 47 71\n" +
				"36 32 93 20 67\n" +
				"16 10 68 39 74\n" +
				"75 99 82 27 18\n" +
				"\n" +
				"24 31 35 82 18\n" +
				"49 16 98 90 26\n" +
				" 8 64 25 87 92\n" +
				"54 76  2 22 15\n" +
				" 7 50 44 94 68\n" +
				"\n" +
				" 1 31 72 28 18\n" +
				"76 93 20  4 16\n" +
				"35 54 49 30 10\n" +
				"59 32 53 62 84\n" +
				"99 52 92 75 25\n" +
				"\n" +
				" 1 75 51 22 90\n" +
				"61 83 58 63 28\n" +
				" 9  5 85 43 92\n" +
				"69  8 62 93 48\n" +
				"84 31 21 82 78\n" +
				"\n" +
				"99  6 70 73 75\n" +
				"65 53 29 31 16\n" +
				"78 61 37 90  7\n" +
				"54 64 20 35  4\n" +
				"97 44  1 10  3\n" +
				"\n" +
				"73 50 53 24 49\n" +
				"15 56 94 82 39\n" +
				"31 40 65 79 44\n" +
				"92 70 57 95 30\n" +
				"21 74 55  3 64\n" +
				"\n" +
				"44 42 78 31 37\n" +
				"33 69 71 24 81\n" +
				"12  7 23  8 30\n" +
				"10  9 11 68 29\n" +
				"93 28 94 63 87\n" +
				"\n" +
				"87 53 75 85  0\n" +
				"34 52 37 49 28\n" +
				"11 72  2 86 62\n" +
				"66 17 61 46 45\n" +
				"13 96 18 99 29\n" +
				"\n" +
				"50 76 71 10 92\n" +
				" 4 99 46 39 86\n" +
				"23 94 12 73 40\n" +
				" 5 70 96 43 51\n" +
				"55 61 67 18 15\n" +
				"\n" +
				"51 88 97 58 33\n" +
				"36 12 90 53 85\n" +
				"86  5 42  6  2\n" +
				"95 57 19 34 17\n" +
				"26  1 77 78 20\n" +
				"\n" +
				"61 17 79 97 10\n" +
				"29 65 21 55 63\n" +
				"19 88 74 62  6\n" +
				"73 76 89 33 31\n" +
				"50 30 12 22 39\n" +
				"\n" +
				"18 94 15 17 26\n" +
				"69 37 91 30 85\n" +
				"13 67 25 84 58\n" +
				"95 90 27 82 33\n" +
				"20 56 62  4 65\n" +
				"\n" +
				"58 19 17 83 89\n" +
				"14 82 48 36 97\n" +
				" 5 46 20 50 57\n" +
				"60 92 52 85  9\n" +
				"95 34 31 53 73\n" +
				"\n" +
				"89 47  3 15 21\n" +
				" 2 10 59 76 36\n" +
				" 5 24 14 30 72\n" +
				"37 77 57 48 91\n" +
				"16  9 73 94 26\n" +
				"\n" +
				"62  1 32 85  9\n" +
				"13 52 75 34 84\n" +
				"93 24 95 51 90\n" +
				"86 35 22 72 38\n" +
				" 0 46 96 88  7\n" +
				"\n" +
				"67 80 84 34  4\n" +
				"39 11 37 47 82\n" +
				"29 63 57 86 77\n" +
				"78 64  5 32 96\n" +
				"38 69 55 87 50\n" +
				"\n" +
				"91 26 89 87 54\n" +
				"51 43 22 68 21\n" +
				"74 62 88 38 53\n" +
				"28 92  4 39 40\n" +
				"96 97 73 72 29\n" +
				"\n" +
				"40 83 35  5 91\n" +
				"41 45 57 94 60\n" +
				"61 31 59 47 95\n" +
				"81 89 69 25 33\n" +
				" 3 36 15 93 27\n" +
				"\n" +
				"19 49 37 14 48\n" +
				"96 55 43  6 12\n" +
				"22 21 50 47 75\n" +
				"78 40 51 91 63\n" +
				"59 87 28 93 86\n" +
				"\n" +
				"86 44  3 54 17\n" +
				"71 82 70 88 49\n" +
				"14 43 63 76 15\n" +
				"78 81 61 22 46\n" +
				"84 65  9 29 83\n" +
				"\n" +
				"15 28  5 45 29\n" +
				"12 19 64 93 48\n" +
				"83 40  7 99 74\n" +
				"20 30 85 67 58\n" +
				"22 21 59 76 18\n" +
				"\n" +
				"42  1 46  8 62\n" +
				"69 27 67 68 38\n" +
				"88 35 83 14 84\n" +
				"53 85 82 29 59\n" +
				"61 73 39 74 99\n" +
				"\n" +
				"38 32 50  9 74\n" +
				"75 66 63  3 62\n" +
				"68 15 17 98  6\n" +
				"81 29 52 88 21\n" +
				"58  2 87 96 56\n" +
				"\n" +
				"49 12 26 89 98\n" +
				"92 69 90 50 35\n" +
				"74 40  0 87 48\n" +
				"19 47 65 42 31\n" +
				"17  3 33 28 85\n" +
				"\n" +
				"47 64 43 73 81\n" +
				"32 49 65 42 24\n" +
				"95 93 36 78 62\n" +
				"34 96 79 10  4\n" +
				"39 54 15 17 51\n" +
				"\n" +
				"53 32 41 16 95\n" +
				"54 47 56 69 17\n" +
				"31  0 42 66 13\n" +
				"88  9 43 38 79\n" +
				"21  8 19 98 92\n" +
				"\n" +
				"78 43 38 75 14\n" +
				"36 62 64 45 53\n" +
				"31 96 16 46 44\n" +
				"93 11 57 56 65\n" +
				"27  3 73 74 68\n" +
				"\n" +
				"66 23 39 13 58\n" +
				"40 85 68 50 57\n" +
				"97 37 77 28 83\n" +
				"48 29 51 84 91\n" +
				"95  1 70 78 56\n" +
				"\n" +
				"51 74 68 89 91\n" +
				"49 54  8 83 36\n" +
				"86 65 99 28 47\n" +
				"82 57 32 81 58\n" +
				"13 66  3 94 67";

	}

}
