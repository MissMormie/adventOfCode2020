package days2021;

import com.google.common.collect.Lists;
import com.google.common.primitives.Chars;
import helpers.InputProvider;

import java.io.IOException;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day18_SnailFish {
	public static int day = 18;
	public static int year = 2021;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		List<SnailFishNumber> collect = input
				.map(line -> Lists.newLinkedList(Chars.asList(line.toCharArray())))
				.map(list -> new SnailFishNumber(list, null))
				.collect(Collectors.toList());


		SnailFishNumber snailFishNumberSum = collect.get(0);
		for(int i = 1; i < collect.size(); i++) {
			snailFishNumberSum = addAndNormalizeNumber(snailFishNumberSum, collect.get(i));
		}

		return snailFishNumberSum.getMagnitude();
	}

	private static SnailFishNumber addAndNormalizeNumber(SnailFishNumber number1, SnailFishNumber number2) {
		number1 = new SnailFishNumber(number1, number2);
		number1.number1.parent = number1;
		number1.number2.parent = number1;

		boolean result = true;
		while(result) {
			result = number1.explode(0);
			if(!result) {
				result = number1.split();
			}
		}
		return number1;
	}


	public static int runB(Stream<String> input) {
		List<String> stringList = input.collect(Collectors.toList());

		List<String> clonedList = stringList.stream().collect(Collectors.toList());
		return stringList.stream().map(number ->
			 clonedList.stream()
					.map(secondNumber -> getMagnitudeForNumbers(number, secondNumber))
					.mapToInt(v -> v).max().orElse(0))
				.mapToInt(v -> v)
				.max().orElse(0);

	}

	public static int getMagnitudeForNumbers(String number1, String number2) {
		if(number1.equals(number2)) {
			return 0;
		}
		SnailFishNumber SnailFishNumber1 = new SnailFishNumber(Lists.newLinkedList(Chars.asList(number1.toCharArray())), null);
		SnailFishNumber snailFishNumber2 = new SnailFishNumber(Lists.newLinkedList(Chars.asList(number2.toCharArray())), null);

		SnailFishNumber snailFishNumber = addAndNormalizeNumber(SnailFishNumber1, snailFishNumber2);
		return snailFishNumber.getMagnitude();
	}

	public static class SnailFishNumber {
		SnailFishNumber number1 = null;
		SnailFishNumber number2 = null;
		Integer firstNum = null;
		Integer secondNum = null;
		SnailFishNumber parent;

		public SnailFishNumber(SnailFishNumber number1, SnailFishNumber number2) {
			this.number1 = number1;
			this.number2 = number2;
		}

		public SnailFishNumber(Integer firstNum, Integer secondNum, SnailFishNumber parent) {
			this.firstNum = firstNum;
			this.secondNum = secondNum;
			this.parent = parent;
		}

		public SnailFishNumber(LinkedList<Character> list, SnailFishNumber parent) {
			this.parent = parent;
			if(parent == null) {
				// if this has no parent the [ has not yet been removed, so doing that now.
				list.removeFirst();
			}
			Character character = list.removeFirst();
			if(character == '[') {
				number1 = new SnailFishNumber(list, this);
			} else {
				firstNum = (int) character - 48;
			}

			// removing ,
			list.removeFirst();

			character = list.removeFirst();
			if(character == '[') {
				number2 = new SnailFishNumber(list, this);
			} else {
				secondNum = (int) character - 48;
			}
			// removing closing ];
			list.removeFirst();

		}

		public void printNumber() {
			System.out.print("[");
			if (number1 != null) {
				number1.printNumber();
			} else {
				System.out.print(firstNum);
			}

			System.out.print(",");
			if (number2 != null) {
				number2.printNumber();
			} else {
				System.out.print(secondNum);
			}

			System.out.print("]");

		}

		public boolean split() {
			if (number1 != null) {
				if(number1.split()) {
					return true;
				}
			} else if ( firstNum > 9) {
				number1 = createSplitNum(firstNum);
				firstNum = null;
				return true;
			}

			if (number2 != null) {
				return number2.split();
			} else if (secondNum > 9) {
				number2 = createSplitNum(secondNum);
				secondNum = null;
				return true;
			}
			return false;
		}

		private SnailFishNumber createSplitNum(int num) {
			int i = num / 2;
			return new SnailFishNumber( i, num -i,this);
		}

		public boolean explode(int level){
			if(number1 != null && number1.explode(level + 1)) {
				return true;
			}
			if (number2!= null && number2.explode(level + 1)) {
				return true;
			}

			if (level == 4) {
				parent.explodeLeft(firstNum, this);
				parent.explodeRight(secondNum, this);
				parent.setZero(this);
//				System.out.println("Exploded: [" + firstNum + "," + secondNum+ "]");
				// explode
				return true;
			}
			return false;
		}
		// [[[[4,0],[5,4]],[[7,7],[6,[6,7]]]],[10,[[11,9],[11,0]]]]

		public void setZero(SnailFishNumber child) {
			if(child.equals(number1)) {
				number1 = null;
				firstNum = 0;
			} else {
				number2 = null;
				secondNum = 0;
			}
		}

		public int getMagnitude() {
			int magnitudeLeft = number1 == null ? firstNum : number1.getMagnitude();
			int magnitudeRight = number2 == null ? secondNum : number2.getMagnitude();
			return 3 * magnitudeLeft + 2 * magnitudeRight;
		}

		public void explodeRight(int num, SnailFishNumber previousNumber) {
			if(secondNum != null) {
				secondNum += num;
			} else if(number2 != null &&  !number2.equals(previousNumber)) {
				number2.explodeRightUp(num);
			} else {
				if(parent != null) {
					parent.explodeRight(num, this);
				}
			}
		}

		public void explodeRightUp(int num) {
			if(number1 != null) {
				number1.explodeRightUp(num);
			} else {
				firstNum += num;
			}
		}

		public void explodeLeftUp(int num) {
			if(number2 != null) {
				number2.explodeLeftUp(num);
			} else {
				secondNum += num;
			}
		}

		public void explodeLeft(int num, SnailFishNumber previousNumber) {
			if(firstNum != null) {
				firstNum += num;
			} else if(number1 != null && !number1.equals(previousNumber)){
				number1.explodeLeftUp(num);
			} else{
				if(parent != null) {
					parent.explodeLeft(num, this);
				}
			}
		}
	}
}
