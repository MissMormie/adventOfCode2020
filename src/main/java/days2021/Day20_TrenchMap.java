package days2021;

import helpers.Coordinate;
import helpers.InputProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day20_TrenchMap {
	public static int day = 20;
	public static int year = 2021;
	public static String enhancementAlgorithm = "#.#.#.#.#......#.#.#.#.##..#.##.##..#..##...#.#.#.#...##.##.##.###....#..#...#.#..###.#...#..##.#.###..#..####.###...#.#.#..##..##.##..##..###..#....#.#....#####.#...###...#.#....###...#..##.##..#..#.##..###..#.##.###..#.####...#.##.....#.###...#.##.##.#.#######...#.###..##..##..#.#.#.#####...#....#.....##.#.#...##.######....#..#......#.#.#.#.##...######.#.#####..#####..#.#.#.#.###.#.#....#..##..#..#.#.#..##....##..#.#.......##...#..####.####.#.#..#.###..#...#......###...#...#.##.#.####..#.#....###.####..#.";

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static long runA(Stream<String> input) {
		return enhanceImage(input, 2);
	}

	private static long enhanceImage(Stream<String> input, int runTimes) {
		Map<String, ImagePixel> pixelMap = new HashMap<>();
		List<String> collect = input.collect(Collectors.toList());

		int stretch = runTimes * 2;
		// do stupid stuff to deal with infinite grid. So we're making a fake infinite grid by making it too large
		// on all sides
		for(int x = -stretch; x < collect.size() + stretch; x++) {
			for (int y = -stretch; y < collect.get(0).length() + stretch; y++) {
				ImagePixel imagePixel = new ImagePixel(x, y, '.');
				pixelMap.put(imagePixel.getCoords(), imagePixel);
			}
		}

		// add actual pixels
		for(int x = 0; x < collect.size(); x++) {
			char[] chars = collect.get(x).toCharArray();
			for(int y = 0; y < chars.length; y++) {
				if(chars[y] =='#') {
					ImagePixel imagePixel = new ImagePixel(x, y, chars[y]);
					pixelMap.put(imagePixel.getCoords(), imagePixel);
				}
			}
		}

		// run enhancement
		for (int i = 0; i < runTimes; i++) {
			char unknownPixelState = getUnknownPixelState(i);
			pixelMap.values().forEach(imagePixel -> imagePixel.calculateNewState(pixelMap, unknownPixelState));
			pixelMap.values().forEach(ImagePixel::processNewState);
		}

		return pixelMap.values().stream().filter(pixel -> pixel.pixelState == '1').count();
	}

	public static char getUnknownPixelState(int i) {
		if(enhancementAlgorithm.charAt(0) == '.') {
			return '0';
		}

		if(i % 2 == 0) {
			return '0';
		}
		return '1';
	}


	public static long runB(Stream<String> input) {
		return enhanceImage(input, 50);
	}

	public static class ImagePixel extends Coordinate {
		char pixelState = '0';
		char newPixelState;

		public ImagePixel(int x, int y, char c) {
			super(x, y);
			if(c == '#') {
				pixelState = '1';
			}
		}

		public void calculateNewState(Map<String, ImagePixel> pixelMap, char unknownPixelState) {
			StringBuilder sb = new StringBuilder();
			for(int x = -1; x < 2; x++) {
				for(int y = -1; y < 2; y++) {
					if(pixelMap.containsKey(Coordinate.makeCoordString(this.x + x, this.y + y))) {
						sb.append(pixelMap.get(Coordinate.makeCoordString(this.x + x, this.y + y)).pixelState);
					} else {
						sb.append(unknownPixelState);
					}
				}
			}
			String s = sb.toString();
			int i = Integer.parseInt(s, 2);
			char c = Day20_TrenchMap.enhancementAlgorithm.charAt(i);
			if (c == '#') {
				newPixelState = '1';
			} else {
				newPixelState = '0';
			}
		}

		public void processNewState() {
			pixelState = newPixelState;
		}
	}
}
