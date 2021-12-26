package days2021;

import helpers.InputProvider;

import java.io.IOException;
import java.util.LinkedList;
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
		return extractPackets(s);
	}

	private static int extractPackets(String s) {
		LinkedList<Character> characters = new LinkedList<>();
		for(char c : s.toCharArray()) {
			characters.add(c);
		}

		int versionSum =0;
		while(characters.size()> 10) {
			versionSum += extractPacket(characters);
		}
		return versionSum;
	}

	private static int extractPacket(LinkedList<Character> characters) {
		int version = Integer.parseInt(getStringWithLength(characters, 3),2);
		int versionSum = version;
		String type = getStringWithLength(characters, 3);
		int length;

		// To be able to strip out extra 0's we need to keep track of how many chars are in the instruction.
		switch (type) {
			// Literal number
			case "100": //  type 4
				boolean repeat = true;
				while (repeat) {
					String bitGroup = getStringWithLength(characters, 5);
					repeat = bitGroup.charAt(0) != '0';
				}
				break;
			default :
				length = getStringWithLength(characters, 1).equals("0") ? 15 : 11;
				if(length == 15) {
					int subPacketLength = Integer.parseInt(getStringWithLength(characters, length), 2);
					versionSum += extractPackets(getStringWithLength(characters,subPacketLength));
				} else {
					int numOfSubPackets = Integer.parseInt(getStringWithLength(characters, length), 2);
					for(int i = 0; i < numOfSubPackets; i++) {
						versionSum += extractPacket(characters);
					}
				}
				break;
		}
		return versionSum;
	}

	public static String getStringWithLength(LinkedList<Character> list, int length) {
		StringBuilder sb = new StringBuilder();
		for(int i =0; i < length; i++) {
			sb.append(list.removeFirst());
		}
		return sb.toString();
	}


	public static int runB(Stream<String> input) {
		return 0;
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
