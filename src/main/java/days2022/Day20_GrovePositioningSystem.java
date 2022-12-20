package days2022;

import helpers.CircularLinkedList;
import helpers.InputProvider;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day20_GrovePositioningSystem {
	public static int day = 20;

	public static int year = 2022;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		// 5000 numbers this won't work for bigger numbers
		List<String> numbers = input.collect(Collectors.toList());
		CircularLinkedList<String> linkedList = new CircularLinkedList<>();
		numbers.forEach(linkedList::addObject);

		// possible optimization: save the nodes of the strings to a map so we don't have to loop round.

		for (String num : numbers) {
			int number = Integer.parseInt(num);
			// move to the node we need. Want exact object, not same string.
			while (linkedList.getNext() != num) {
			}
			linkedList.removeCurrent();
			linkedList.previous();

			// possible optimization determine if forward or backwards is faster.
			if (number >= 0) {
				linkedList.next(number);
			} else {
				linkedList.previous(Math.abs(number));
			}
			linkedList.addObject(num);
		}

		// move to zero.
		while (!linkedList.getNext().equals("0")) {

		}
		// can do something smart to not do 1000 steps, but not worth it yet.
		linkedList.next(1000);
		String num1000 = linkedList.getCurrent();
		linkedList.next(1000);
		String num2000 = linkedList.getCurrent();
		linkedList.next(1000);
		String num3000 = linkedList.getCurrent();

		return Integer.parseInt(num1000) + Integer.parseInt(num2000) + Integer.parseInt(num3000);
	}


	public static long runB(Stream<String> input) {
		// 5000 numbers this won't work for bigger numbers
		List<String> numbers = input.map(num -> Long.parseLong(num) * 811589153)
				.map(num -> Long.toString(num))
				.collect(Collectors.toList());
		CircularLinkedList<String> linkedList = new CircularLinkedList<>();
		numbers.forEach(linkedList::addObject);

		int mapSize = numbers.size();
		for (int i = 0; i < 10; i++) {
			for (String num : numbers) {
				long number = Long.parseLong(num);
				// move to the node we need. Want exact object, not same string.
				while (linkedList.getNext() != num) {
				}
				linkedList.removeCurrent();
				linkedList.previous();
				// mapSize -1 because 1 item is removed from the list at this point.
				Long l = number % (mapSize - 1);
				if (number >= 0) {
					linkedList.next(l.intValue());
				} else {
					linkedList.previous(Math.abs(l.intValue()));
				}
				linkedList.addObject(num);
			}
		}

		// move to zero.
		while (!linkedList.getNext().equals("0")) {

		}
		// can do something smart to not do 1000 steps, but not worth it yet.
		linkedList.next(1000);
		String num1000 = linkedList.getCurrent();
		linkedList.next(1000);
		String num2000 = linkedList.getCurrent();
		linkedList.next(1000);
		String num3000 = linkedList.getCurrent();

		return Long.parseLong(num1000) + Long.parseLong(num2000) + Long.parseLong(num3000);
	}
}
