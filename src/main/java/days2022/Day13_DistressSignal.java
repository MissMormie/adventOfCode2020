package days2022;

import com.beust.ah.A;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import com.google.gson.Gson;
import helpers.InputProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day13_DistressSignal {
	public static int day = 13;

	public static int year = 2022;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day))); // not 5938
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		UnmodifiableIterator<List<String>> partitions = Iterators.partition(input.iterator(), 3);

		int i = 1; // starting from index 1;
		int sum = 0;
		while (partitions.hasNext()) {
			if (areInRightOrder(partitions.next())) {
				sum += i;
				System.out.println(i);
			}
			i++;
		}
		return sum;
	}


	public static int runB(Stream<String> input) {
		return 0;
	}

	private static boolean areInRightOrder(List<String> input) {
		Gson gson = new Gson();
		ArrayList packet1 = gson.fromJson(input.get(0), ArrayList.class);
		ArrayList packet2 = gson.fromJson(input.get(1), ArrayList.class);

		return isPacketRightOrder(packet1, packet2);
	}


	// Returns true if in the correct order, according to the rules packet comes before other.
	private static boolean isPacketRightOrder(ArrayList packet, ArrayList other) {

		if (packet.isEmpty() && other.isEmpty()) {
			return true;
		} else if (packet.isEmpty() || other.isEmpty()) {
			// If the left list runs out of items first, the inputs are in the right order.
			// If the right list runs out of items first, the inputs are not in the right order.
			return packet.isEmpty();
		}

		// If both values are integers, the lower integer should come first. If the left integer is lower than the right
		// integer, the inputs are in the right order. If the left integer is higher than the right integer, the inputs
		// are not in the right order. Otherwise, the inputs are the same integer; continue checking the next part of the input.
		if (packet.get(0) instanceof Double && other.get(0) instanceof Double) {
			Double removedPacket = (Double) packet.remove(0);
			Double otherPacket = (Double) other.remove(0);
			if (removedPacket < otherPacket) {
				return true;
			} else if (removedPacket > otherPacket) {
				return false;
			}
		} else if (packet.get(0) instanceof ArrayList && other.get(0) instanceof ArrayList) {
			// If both values are lists, compare the first value of each list, then the second value,
			// and so on.
			// If the lists are the same length and no comparison makes a decision about the order,
			// continue checking the next part of the input.
			ArrayList packetList = (ArrayList) packet.remove(0);
			ArrayList otherList = (ArrayList) other.remove(0);
			boolean packetListShortest = packetList.size() < otherList.size();

			if (!isPacketRightOrder(packetList, otherList)) {
				return false;
			} else {
				return packetListShortest;
			}
		} else {
			// If exactly one value is an integer, convert the integer to a list which contains that integer as its only
			// value, then retry the comparison. For example, if comparing [0,0,0] and 2, convert the right value to [2]
			// (a list containing 2); the result is then found by instead comparing [0,0,0] and [2].
			if (packet.get(0) instanceof Double) {
				ArrayList arrayList = new ArrayList();
				arrayList.add(packet.get(0));
				packet.set(0, arrayList); // transform single element to list.
			} else {
				ArrayList arrayList = new ArrayList();
				arrayList.add(other.get(0));
				other.set(0, arrayList); // transform single element to list.
			}
		}

		return isPacketRightOrder(packet, other);
	}
}
