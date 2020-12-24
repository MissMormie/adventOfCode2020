package days;

import helpers.CircularLinkedList;

import java.util.Map;
import java.util.stream.IntStream;

public class Day23_CrabCups {

	public static void main(String[] args) {
//		System.out.println("answer A: " + runA(textInput()));
		long startTime = System.nanoTime();
		System.out.println("answer B: " + runB(textInput()));
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;

		System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);
	}

	public static String runA(String input) {
		CircularLinkedList<Integer> cups = new CircularLinkedList<>();
		input.chars().forEach(c -> cups.addObject(c - 48));
		cups.next();
		Map<Integer, CircularLinkedList<Integer>.Node<Integer>> nodeMap = cups.getNodeMap();

		IntStream.range(0, 100).forEach(i -> move(cups, nodeMap));
		while(cups.getCurrent() != 1) {
			cups.next();
		}
		cups.removeCurrent();
		return cups.getStringFromList("");
	}

	public static void move(CircularLinkedList<Integer> cups, Map<Integer, CircularLinkedList<Integer>.Node<Integer>> nodeMap) {
		CircularLinkedList<Integer>.Node<Integer> currentNode = cups.getNode();

		// The crab picks up the three cups that are immediately clockwise of the current cup.
		// They are removed from the circle; cup spacing is adjusted as necessary to maintain the circle.
		cups.next();
		Integer cup1 = cups.removeCurrent();
		Integer cup2 = cups.removeCurrent();
		Integer cup3 = cups.removeCurrent();
		nodeMap.remove(cup1);
		nodeMap.remove(cup2);
		nodeMap.remove(cup3);
		cups.previous();

		// The crab selects a destination cup: the cup with a label equal to the current cup's label minus one.
		// If this would select one of the cups that was just picked up, the crab will keep subtracting one until it
		// finds a cup that wasn't just picked up. If at any point in this process the value goes below the lowest value
		// on any cup's label, it wraps around to the highest value on any cup's label instead.
		Integer destination = cups.getCurrent() -1;
		while(destination < 1 || destination == cup1 || destination == cup2 || destination == cup3 || destination == cups.getCurrent()) {
			if(destination < 1) {
				destination = cups.size() + 3; // the three removed cups
			} else {
				destination -= 1;
			}
		}

		// The crab places the cups it just picked up so that they are immediately clockwise of the destination cup.
		// They keep the same order as when they were picked up.
		cups.setCurrent(nodeMap.get(destination));

		// Add the removed cups back to the circle, they also need to be updated in the node list.
		cups.addObject(cup1);
		nodeMap.put(cup1, cups.getNode());
		cups.addObject(cup2);
		nodeMap.put(cup2, cups.getNode());
		cups.addObject(cup3);
		nodeMap.put(cup3, cups.getNode());

		// The crab selects a new current cup: the cup which is immediately clockwise of the current cup.
		cups.setCurrent(currentNode);
		cups.next();
	}

	public static Long runB(String input) {
		CircularLinkedList<Integer> cups = new CircularLinkedList<>();
		input.chars().forEach(c -> cups.addObject(c - 48));
		IntStream.rangeClosed(10, 1_000_000).forEach(i -> {
			cups.addObject(i);
		});
		cups.next(); // set to first cup

		Map<Integer, CircularLinkedList<Integer>.Node<Integer>> nodeMap = cups.getNodeMap();

		IntStream.range(0, 10_000_000).forEach(i -> {
			if(i == 1364753) {
				System.out.println("Asadgf");
			}
			move(cups, nodeMap);
		});
		while(cups.getCurrent() != 1) {
			cups.next();
		}
		return cups.getNext() * 1l * cups.getNext();
	}

	private static String textInput() {
		return "193467258";
	}
}
