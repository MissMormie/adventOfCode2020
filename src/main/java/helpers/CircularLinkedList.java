package helpers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class CircularLinkedList<T> {

	private Node<T> currentNode = null;

	private int numNodes;

	private Node<T> firstNode;

	public CircularLinkedList() {

	}

	/**
	 * Adds object between current and next item. CurrentNode is added node.
	 * @param object T
	 */
	public CircularLinkedList<T> addObject(T object) {
		numNodes++;

		if(currentNode == null) {
			addFirstObject(object);
			return this;
		}

		Node<T> nextNode = currentNode.getNextNode();
		Node<T> newNode = new Node<>(object, nextNode, currentNode);

		currentNode.setNextNode(newNode);
		nextNode.setPreviousNode(newNode);

		// Set created node as current node.
		currentNode = newNode;
		if(currentNode.nextNode == currentNode) {
			System.out.println("Only ok for first node");
		}
		return this;
	}

	public CircularLinkedList<T> addBefore(T object) {
		previous();
		addObject(object);
		next();
		return this;
	}

	public CircularLinkedList<T> clone() {
		CircularLinkedList<T> clonedList = new CircularLinkedList<>();
		IntStream.range(0, numNodes).forEach(i -> clonedList.addObject(getNext()));
		return clonedList;
	}

	public int size() {
		return numNodes;
	}

	public List<Integer> asList() {
		List list = new ArrayList();
		Node listStartNode = currentNode;
		do {
			list.add(currentNode.object);
			next();
		} while(listStartNode != currentNode);
		return list;
	}

	private void addFirstObject(T object) {
		Node<T> newNode = new Node<>(object);
		newNode.setPreviousNode(newNode);
		newNode.setNextNode(newNode);
		firstNode = newNode;
		currentNode = newNode;
	}

	public CircularLinkedList<T> next() {
		if(currentNode == null) {
			return null;
		}
		currentNode = currentNode.getNextNode();
		return this;
	}

	public CircularLinkedList<T> next(int steps) {
		for(int i = 0; i < steps; i++) {
			next();
		}
		return this;
	}

	public CircularLinkedList<T> previous(int steps) {
		for(int i = 0; i < steps; i++) {
			previous();
		}
		return this;
	}


	public CircularLinkedList<T> previous() {
		if(currentNode == null) {
			return null;
		}
		currentNode = currentNode.getPreviousNode();
		return this;
	}

	public T getNext() {
		if(currentNode == null) {
			return null;
		}
		currentNode = currentNode.getNextNode();
		return currentNode.get();
	}

	public T getNodeXStepsForwardDontMovepointer(int steps) {
		Node<T> steppedNode = currentNode;
		for(int i = 0; i < steps; i++) {
			steppedNode = steppedNode.getNextNode();
		}
		return steppedNode.get();
	}

	public T getPrevious() {
		if(currentNode == null) {
			return null;
		}
		currentNode = currentNode.getPreviousNode();
		return getCurrent();
	}

	public void moveToFirst() {
		currentNode = firstNode;
	}

	public T getAndMoveToFirst() {
		moveToFirst();
		if(currentNode == null) {
			return null;
		}
		return getCurrent();
	}

	/**
	 * Next node is the new current node
	 * @return T
	 */
	public T removeCurrent() {

		// Only a single node exists
		if(currentNode.nextNode == currentNode) {
			Node<T> tempNode = currentNode;
			currentNode = null;
			firstNode = null;
			numNodes--;
			return tempNode.get();
		}
		if((int) currentNode.get() == 940962) {

			System.out.println("Issues");
		}

		if(currentNode == firstNode) {
			firstNode = currentNode.nextNode;
		}
		Node<T> tempNode = currentNode;
		Node<T> previousNode = currentNode.getPreviousNode();
		Node<T> nextNode = currentNode.getNextNode();
		// Stich previous and next nodes together.
		previousNode.setNextNode(nextNode);
		nextNode.setPreviousNode(previousNode);
		currentNode = nextNode;
		numNodes--;

		if(currentNode.nextNode == currentNode) {
			System.out.println("Issues");
		}
		return tempNode.get();
	}

	public T getCurrent() {
		return currentNode.object;
	}

	/**
	 * This has the potential to break everything. Use in production code with extreme care.
	 * @param object
	 */
	public CircularLinkedList<T> setCurrent(Node<T> object) {
		currentNode = object;
		return this;
	}

	public String getStringFromList(String joining) {
		StringBuilder sb = new StringBuilder();
		previous();
		IntStream.range(0, size()).forEach(i -> sb.append(getNext().toString() + joining));
		next();
		return sb.toString();
	}

	public Map<T, Node<T>> getNodeMap() {
		Map<T, Node<T>> nodeMap = new HashMap<>();
		IntStream.range(0, size()).forEach(i -> nodeMap.put(getNext(), currentNode));
		return nodeMap;
	}

	/**
	 * Reverses the order for the nodes for x num of nodes.
	 */
	public void reverseOrder(int numNodesToBeReversed) {
		Node startNode = currentNode;
		Node previousNode = startNode.previousNode;

		Node<T> newStartNode = currentNode.reverse(numNodesToBeReversed);
		startNode.nextNode = newStartNode.nextNode;
		newStartNode.previousNode = previousNode;
	}

	/**
	 * This has the potential to break everything. Don't use in production code.
	 */
	public Node<T> getNode() {
		return currentNode;
	}

	/**
	 * Update object for current items.
	 * @param object T
	 */
	public void updateObject(T object) {
		if(currentNode == null) {
			return;
		}
		currentNode.setObject(object);
	}

	public class Node<T> {
		private T object;
		private Node<T> nextNode;
		private Node<T> previousNode;

		Node(T object) {
			this.object = object;
		}

		Node(T object, Node nextNode, Node previousNode) {
			this(object);
			this.nextNode = nextNode;
			this.previousNode = previousNode;
		}

		public T get() {
			return object;
		}

		public void setObject(T object) {
			this.object = object;
		}

		void setNextNode(Node<T> nextNode) {
			this.nextNode = nextNode;
		}

		void setPreviousNode(Node<T> previousNode) {
			this.previousNode = previousNode;
		}

		Node<T> getNextNode() {
			return nextNode;
		}

		Node<T> getPreviousNode() {
			return previousNode;
		}

		Node<T> reverse(int nodesToReverse) {
			Node oldNextnode = nextNode;
			nextNode = previousNode;
			previousNode = oldNextnode;
			if(nodesToReverse == 0) {
				return this;
			}
			return oldNextnode.reverse(nodesToReverse - 1);
		}
	}
}
