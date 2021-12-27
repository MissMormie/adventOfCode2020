package days2021;

import helpers.InputProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Day16_PacketDecoder {
	public static int day = 16;
	public static int year = 2021;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		String s = input.map(Day16_PacketDecoder::hexToBin).findAny().get();
		return extractPacketsA(s);
	}

	public static long runB(Stream<String> input) {
		String s = input.map(Day16_PacketDecoder::hexToBin).findAny().get();
		List<Long> longs = extractPackets(s);
		return longs.get(0);
	}

	private static List<Long> extractPackets(String s) {
		LinkedList<Character> characters = new LinkedList<>();
		for(char c : s.toCharArray()) {
			characters.add(c);
		}

		List<Long> numList = new ArrayList<>();
		while(characters.size()> 10) {
			numList.add(extractPacket(characters));
		}
		return numList;
	}

	private static long extractPacket(LinkedList<Character> characters) {
		int version = Integer.parseInt(removeCharsFromList(characters, 3),2);
		String type = removeCharsFromList(characters, 3);

		// To be able to strip out extra 0's we need to keep track of how many chars are in the instruction.
		switch (type) {
			// Literal number
			case "100": //  type 4
				boolean repeat = true;
				StringBuilder sb = new StringBuilder("");
				while (repeat) {
					String bitGroup = removeCharsFromList(characters, 5);
					repeat = bitGroup.charAt(0) != '0';
					sb.append(bitGroup.substring(1));
				}
				long l = Long.parseLong(sb.toString(), 2);
				return l;
			default :
				int length = removeCharsFromList(characters, 1).equals("0") ? 15 : 11;
				List<Long> numList = new ArrayList<>();
				if(length == 15) {
					int subPacketLength = Integer.parseInt(removeCharsFromList(characters, length), 2);
					numList = extractPackets(removeCharsFromList(characters,subPacketLength));
				} else {
					int numOfSubPackets = Integer.parseInt(removeCharsFromList(characters, length), 2);
					for(int i = 0; i < numOfSubPackets; i++) {
						numList.add(extractPacket(characters));
					}
				}
				switch(type) {
					case "000": return numList.stream().reduce(Long::sum).get();
					case "001": return numList.stream().reduce(1l, (a,b) -> a *b);
					case "010": return numList.stream().min(Comparator.comparing(Long::longValue)).get();
					case "011": return numList.stream().max(Comparator.comparing(Long::longValue)).get();
					case "101": return numList.get(0) > numList.get(1) ? 1 : 0;
					case "110": return numList.get(0) < numList.get(1) ? 1 : 0;
					case "111": return numList.get(0).equals(numList.get(1)) ? 1 : 0;
				}
		}
		throw new RuntimeException("Shouldn't come here");
	}

	private static int extractPacketsA(String s) {
		LinkedList<Character> characters = new LinkedList<>();
		for(char c : s.toCharArray()) {
			characters.add(c);
		}

		int versionSum =0;
		while(characters.size()> 10) {
			versionSum += extractPacketA(characters);
		}
		return versionSum;
	}

	private static int extractPacketA(LinkedList<Character> characters) {
		int version = Integer.parseInt(removeCharsFromList(characters, 3),2);
		int versionSum = version;
		String type = removeCharsFromList(characters, 3);
		int length;

		// To be able to strip out extra 0's we need to keep track of how many chars are in the instruction.
		switch (type) {
			// Literal number
			case "100": //  type 4
				boolean repeat = true;
				while (repeat) {
					String bitGroup = removeCharsFromList(characters, 5);
					repeat = bitGroup.charAt(0) != '0';
				}
				break;
			default :
				length = removeCharsFromList(characters, 1).equals("0") ? 15 : 11;
				if(length == 15) {
					int subPacketLength = Integer.parseInt(removeCharsFromList(characters, length), 2);
					versionSum += extractPacketsA(removeCharsFromList(characters,subPacketLength));
				} else {
					int numOfSubPackets = Integer.parseInt(removeCharsFromList(characters, length), 2);
					for(int i = 0; i < numOfSubPackets; i++) {
						versionSum += extractPacketA(characters);
					}
				}
				break;
		}
		return versionSum;
	}

	public static String removeCharsFromList(LinkedList<Character> list, int length) {
		StringBuilder sb = new StringBuilder();
		for(int i =0; i < length; i++) {
			sb.append(list.removeFirst());
		}
		return sb.toString();
	}

	public static String hexToBin(String hex){
		hex = hex.replaceAll("0", "0000");
		hex = hex.replaceAll("1", "0001");
		hex = hex.replaceAll("2", "0010");
		hex = hex.replaceAll("3", "0011");
		hex = hex.replaceAll("4", "0100");
		hex = hex.replaceAll("5", "0101");
		hex = hex.replaceAll("6", "0110");
		hex = hex.replaceAll("7", "0111");
		hex = hex.replaceAll("8", "1000");
		hex = hex.replaceAll("9", "1001");
		hex = hex.replaceAll("A", "1010");
		hex = hex.replaceAll("B", "1011");
		hex = hex.replaceAll("C", "1100");
		hex = hex.replaceAll("D", "1101");
		hex = hex.replaceAll("E", "1110");
		hex = hex.replaceAll("F", "1111");
		return hex;
	}
}
