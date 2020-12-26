package days;

import java.util.ArrayList;
import java.util.List;

public class Day19_MonsterMessages3 {
	static String[] rules;

	public static void main(String[] args) {
		System.out.println("answer A: " + runA(textValidations(), textLines()));
		System.out.println("answer B: " + runB(textValidations(), textLines()));
	}

	public static int runA(String validations, String input) {
		String[] validationArray = validations.split("\n");
		rules = new String[validationArray.length];

		//Add the rules into the array
		for (int i = 0; i < validationArray.length; i++){
			String l = validationArray[i];
			String[] parts = l.split(": ");
			int a = Integer.parseInt(parts[0]);
			rules[a] = parts[1];
		}

		String[] lines = textLines().split("\n");

		//Genarate possible sequences for rule x
		List<String> test = poss1(0);
		int count = 0;
		for(String l: lines) {
			if (test.contains(l)){
				count++;
			}
		}

		return count;
	}


	public static int runB(String validations, String input) {
		String[] validationArray = validations.split("\n");
		//Add the rules into the array
		for (int i = 0; i < validationArray.length; i++){
			String line = validationArray[i];
			String[] parts = line.split(": ");
			int a = Integer.parseInt(parts[0]);
			rules[a] = parts[1];
		}


		//Genarate possible sequences for rule x
		List<String> t42 = poss(42);
		List<String> t31 = poss(31);
		List<String> cases = new ArrayList<>();

		String[] lines = textLines().split("\n");
		for(String l: lines) {
			cases.add(l);
		}

		int count = 0;
		for (int j = 0; j < cases.size(); j++){
			String i = cases.get(j);
			String q;

			//needs to have the first 42;
			q = i.substring(0, 8);
			if (!t42.contains(q)){
				cases.remove(i);
				j--;
			}
			//needs to have the 2nd 42
			q = i.substring(8, 16);
			if (!t42.contains(q)){
				cases.remove(i);
				j--;
			}

			//needs to end with a 31
			q = i.substring(i.length()-8);
			if (!t31.contains(q)){
				cases.remove(i);
				j--;
			}

			//Now it can only have 42's and 31's, and the amount of 31's cannot be higher than the amount of 42's
			q = i.substring(16, i.length()-8);
			int count0 = 0;
			int count1 = 0;
			while (q.length() > 0 && t42.contains(q.substring(0, 8))){
				q = q.substring(8);
				count0++;
			}
			while (q.length() > 0 && t31.contains(q.substring(0, 8))){
				q = q.substring(8);
				count1++;
			}
			if (q.length() > 0 || count1 > count0){
				cases.remove(i);
				j--;
			}
		}
		return cases.size();
	}

	public static List<String> poss(int x){
		List<String> res = new ArrayList<>();
		String rule = rules[x];

		if (rule.contains("a")){
			res.add("a");
			return res;
		}
		else if (rule.contains("b")){
			res.add("b");
			return res;
		}
		String[] r = rule.split(" \\| ");
		for (String s : r) {
			List<List<String>> children = new ArrayList<>();
			String[] ru = s.split(" ");
			for (String value : ru) {
				children.add(poss(Integer.parseInt(value)));
			}
			if (children.size() == 1){
				res.addAll(children.get(0));
			}
			else if (children.size() == 2){
				for (int i = 0; i < children.get(0).size(); i++){
					for (int j = 0; j < children.get(1).size(); j++){
						res.add(children.get(0).get(i) + children.get(1).get(j));
					}
				}
			}
			else if (children.size() > 2){
				System.out.println("error");
				return null;
			}
		}
		return res;
	}

	public static List<String> poss1(int x){
		List<String> res = new ArrayList<>();
		String rule = rules[x];

		if (rule.contains("a")){
			res.add("a");
			return res;
		}
		else if (rule.contains("b")){
			res.add("b");
			return res;
		}
		String[] r = rule.split(" \\| ");
		for (String s : r) {
			List<List<String>> children = new ArrayList<>();
			String[] ru = s.split(" ");
			for (String value : ru) {
				children.add(poss1(Integer.parseInt(value)));
			}
			if (children.size() == 1){
				res.addAll(children.get(0));
			}
			else if (children.size() == 2){
				for (int i = 0; i < children.get(0).size(); i++){
					for (int j = 0; j < children.get(1).size(); j++){
						res.add(children.get(0).get(i) + children.get(1).get(j));
					}
				}
			}
			else if (children.size() > 2){
				System.out.println("error");
				return null;
			}
		}
		return res;
	}

	public static String textValidations() {
		return "66: 69 116 | 9 115\n" +
				"91: 95 9 | 109 69\n" +
				"14: 110 69 | 15 9\n" +
				"4: 119 9 | 61 69\n" +
				"17: 9 23 | 69 93\n" +
				"37: 118 69 | 94 9\n" +
				"68: 9 80 | 69 19\n" +
				"117: 37 9 | 45 69\n" +
				"132: 9 109\n" +
				"74: 9 25 | 69 126\n" +
				"102: 122 9 | 6 69\n" +
				"98: 89 9 | 99 69\n" +
				"113: 83 9 | 7 69\n" +
				"92: 9 16 | 69 50\n" +
				"33: 9 39 | 69 133\n" +
				"134: 95 69 | 7 9\n" +
				"57: 9 7 | 69 110\n" +
				"31: 9 66 | 69 51\n" +
				"47: 9 3 | 69 83\n" +
				"21: 69 123 | 9 129\n" +
				"104: 9 86 | 69 7\n" +
				"40: 69 24 | 9 34\n" +
				"32: 9 44 | 69 88\n" +
				"45: 49 69 | 48 9\n" +
				"2: 69 88 | 9 23\n" +
				"5: 3 9 | 110 69\n" +
				"108: 20 86\n" +
				"76: 97 9 | 113 69\n" +
				"56: 9 121 | 69 58\n" +
				"29: 69 13 | 9 82\n" +
				"123: 86 20\n" +
				"46: 23 69 | 95 9\n" +
				"19: 7 69 | 109 9\n" +
				"12: 93 9 | 95 69\n" +
				"75: 85 9 | 128 69\n" +
				"127: 93 69 | 83 9\n" +
				"61: 9 7 | 69 23\n" +
				"78: 9 132 | 69 107\n" +
				"121: 88 69 | 23 9\n" +
				"60: 33 69 | 76 9\n" +
				"73: 26 69 | 100 9\n" +
				"116: 9 60 | 69 75\n" +
				"93: 69 9\n" +
				"38: 9 106 | 69 110\n" +
				"107: 9 7 | 69 86\n" +
				"82: 5 69 | 64 9\n" +
				"58: 44 69 | 23 9\n" +
				"34: 69 57 | 9 104\n" +
				"124: 69 106 | 9 44\n" +
				"109: 69 9 | 9 9\n" +
				"48: 69 44 | 9 83\n" +
				"28: 9 83 | 69 7\n" +
				"64: 9 93 | 69 7\n" +
				"54: 9 102 | 69 40\n" +
				"94: 83 69 | 7 9\n" +
				"80: 9 86 | 69 3\n" +
				"62: 86 69 | 83 9\n" +
				"42: 125 69 | 70 9\n" +
				"71: 69 112 | 9 114\n" +
				"8: 42\n" +
				"131: 69 44 | 9 7\n" +
				"88: 69 9 | 9 69\n" +
				"87: 15 69 | 7 9\n" +
				"3: 9 9 | 69 69\n" +
				"9: \"b\"\n" +
				"41: 21 69 | 78 9\n" +
				"65: 9 69 | 69 20\n" +
				"128: 134 69 | 101 9\n" +
				"44: 9 69 | 69 69\n" +
				"51: 9 18 | 69 54\n" +
				"55: 69 79 | 9 5\n" +
				"95: 69 69 | 69 9\n" +
				"110: 9 69\n" +
				"22: 92 69 | 10 9\n" +
				"67: 9 44 | 69 23\n" +
				"7: 9 20 | 69 69\n" +
				"90: 69 96 | 9 68\n" +
				"53: 111 9 | 27 69\n" +
				"125: 69 72 | 9 22\n" +
				"11: 42 31\n" +
				"23: 9 69 | 9 9\n" +
				"114: 69 105 | 9 131\n" +
				"81: 53 69 | 29 9\n" +
				"69: \"a\"\n" +
				"1: 86 9 | 95 69\n" +
				"18: 9 73 | 69 90\n" +
				"83: 20 20\n" +
				"130: 89 69 | 47 9\n" +
				"89: 44 9 | 93 69\n" +
				"15: 9 9\n" +
				"0: 8 11\n" +
				"105: 69 110\n" +
				"13: 135 9 | 14 69\n" +
				"6: 69 105 | 9 132\n" +
				"103: 120 69 | 71 9\n" +
				"85: 1 69 | 2 9\n" +
				"96: 89 9 | 12 69\n" +
				"101: 69 95 | 9 23\n" +
				"39: 69 15 | 9 109\n" +
				"133: 88 69 | 65 9\n" +
				"86: 69 20 | 9 9\n" +
				"122: 127 9 | 43 69\n" +
				"20: 69 | 9\n" +
				"52: 69 109 | 9 3\n" +
				"119: 15 69 | 88 9\n" +
				"77: 9 7 | 69 93\n" +
				"50: 87 9 | 61 69\n" +
				"129: 9 65 | 69 110\n" +
				"97: 69 95 | 9 93\n" +
				"111: 59 69 | 35 9\n" +
				"115: 69 84 | 9 41\n" +
				"84: 36 69 | 4 9\n" +
				"72: 117 9 | 74 69\n" +
				"135: 110 69\n" +
				"112: 62 69 | 124 9\n" +
				"43: 69 3 | 9 15\n" +
				"118: 109 9 | 93 69\n" +
				"49: 69 23 | 9 83\n" +
				"26: 69 67\n" +
				"63: 106 9 | 44 69\n" +
				"70: 103 69 | 81 9\n" +
				"25: 17 9 | 77 69\n" +
				"36: 91 9 | 32 69\n" +
				"10: 69 98 | 9 56\n" +
				"30: 9 109 | 69 3\n" +
				"126: 9 28 | 69 63\n" +
				"16: 9 38 | 69 30\n" +
				"99: 106 9 | 109 69\n" +
				"59: 23 9 | 3 69\n" +
				"120: 130 9 | 55 69\n" +
				"27: 69 91 | 9 108\n" +
				"24: 46 69 | 52 9\n" +
				"35: 9 93 | 69 109\n" +
				"100: 9 101 | 69 80\n" +
				"106: 69 69\n" +
				"79: 44 69 | 65 9";
	}
	public static String textLines() {
		return "bbabbbaaaaaabbabbaabaaabbaababbbabbbabbbababbbbbbbbabbbbbbabaaaa\n" +
				"aabbbabbabaaaababbbaaabb\n" +
				"baababbbaababaaaabbaababbabbbaaabaaaabbbaaaabbaaabbaabba\n" +
				"abbbbaaabbaaaababbbabaaabbbabbbbbabaabab\n" +
				"aaabbbbbabbbbbabbabbaabbaaaaababababbbbaaaaabbbabbbbabbababbaaabaaaababa\n" +
				"bbbabaaaabbaaabbbababababababaaaabaabbbabaaaabbbabbabbabbbaaabbaaabaaaaa\n" +
				"bbaaaabbaabbbaabbabaaaba\n" +
				"abaabbabbaaaaabbbbbabaabbabbbababaaababb\n" +
				"baaaaabbbabbbbbbabababba\n" +
				"baaabbbaabbaabababbbaaab\n" +
				"baaaabaababbbbbababbbbaaabbabbaabbababababababbabbbabaab\n" +
				"baabbaaaaaaaaaababbaaabaaabbabaaabbababaaaabaaaaabbaabaaababaabbbaabbbaaaabbabbbabbbbbbb\n" +
				"ababbbaaabaabaababababbabbbaabbaaaabbbbbababbabaabbbbbaa\n" +
				"abaabbaabbbbbbbaaaabbbbaaaababba\n" +
				"ababaaabbaabbaaabbbaaaba\n" +
				"bababbbbbaabbabbabbabaaaabaababababababababaabab\n" +
				"bbaaaaababbbaababbbbbbaa\n" +
				"abaabababababbbaababaaaa\n" +
				"aabbababaaaababababbbbba\n" +
				"aabaababbbaaababbabbabab\n" +
				"baababababbbabbbbbbbbaba\n" +
				"bbbbaaaabbababaabbbabbabababbbab\n" +
				"baaabbbbbbabbbbbaaaabaaa\n" +
				"ababbbbabbaabbaaaababbaaaababaaabbbbaaabababbabbaababbbb\n" +
				"bbbbaababababbbbbabbaabbbabaaaba\n" +
				"abbbabbababbbaaaababbbbb\n" +
				"abbbbbabbabbabbbbaaabbabbaaaabbbbbaabaaabaabbaba\n" +
				"aabbabbabbbbbbabbbabbaaa\n" +
				"bbbbaaaabaababbbbabbbbab\n" +
				"bbababaaabbaaabbabaaaabb\n" +
				"bbbbbbbabaabababbbbababbbabbbaaaababaaaa\n" +
				"abbbbbaabbabbbaabbbababbababbaabbaaabbab\n" +
				"aaabaabbbbabababaaaabababababaabbbabbabb\n" +
				"abbabbabbbaaaabbabaaaababbbaabbabaaabaaa\n" +
				"babbbabbbbbabaaababaaabb\n" +
				"bbaaaaabaabbaaaabbbabbbb\n" +
				"abaaabbbbaababbaaabaaaab\n" +
				"bababaababbaaabbaaaabaab\n" +
				"bbaabaabbabaaaaabbbbbbbaaabaaabaaaabbbaabbaabbbabbaaaabaaabbabbbbbaaaaaabaabaaaaabbaaaba\n" +
				"abbabababbbbababbbaabaab\n" +
				"ababababaabbaabbbbabbaaa\n" +
				"bbbbbaabbbbabaaabaaaabba\n" +
				"aababaaabababbbabbababababaabbbaabbaaabababbaabbbaabbbaa\n" +
				"aabbaabaabbbbabbaabababb\n" +
				"aabaaaaaabbbbbbbabababbbbabaaaabbbaabbba\n" +
				"bbaaaaabaaabaabbaabaaaab\n" +
				"aaaaaaaaabbaaaaabbbbaaababaaabab\n" +
				"baababbaaaaaababbbbaaabb\n" +
				"abbbbaabaabbaabbbaaabbaaabbbbabbabbbaaaabaaaaaaaaabaabba\n" +
				"baabaabaaabbbabbbbaaaabaabaaaaabaaabaabbbabaabbb\n" +
				"abaaaaabbabbbabbbababbaaaabaaabbaabbababbabbbabababaabaabaabbbaa\n" +
				"aaaaaaabababbbbaaabaaabaababababababbabb\n" +
				"abbbabaabbabaababaaaabaa\n" +
				"abbbbbaabababbbbbabaaaba\n" +
				"aabbaabbbaabaaaabbaaabba\n" +
				"bbabababaaaaabaabbbbaabaaaaababb\n" +
				"aabbaabbbbbbbaabaaabaaba\n" +
				"bbbbbaabbabbbbbbabbbaaab\n" +
				"bbababbaabbbbabbbaabbbab\n" +
				"baaabbbbbababbaaaaabbbab\n" +
				"bbaabbbbbbbbbababaaaabbbbaaaababbbaababa\n" +
				"aabaabababbbbaabaaaaabba\n" +
				"abaabbbbaabbbaaabbbabbba\n" +
				"baabaaaaabbbaabaaabababb\n" +
				"baaabababbbababbaaaaaabb\n" +
				"babaababbaaaababbaabbaaaaaaabababbbbbbaabbaaabbbaaaabbabaaaaaabbaabbaaaaaabbbaaaaaabaaba\n" +
				"aaabaabbaaaababaaaaaaaabaabbbbbb\n" +
				"abbbababababbbbabaaaabab\n" +
				"abaabaabbbabababaaabbaaa\n" +
				"bbbbababaabaaaaaaabbbabbaababaabababaaabbbbabbab\n" +
				"bbbbaaabaabbbabbabbbabbabaababbbbababaaa\n" +
				"bbaabbbbaaabbabbaaababaaaabaaababbaabbba\n" +
				"aabaabbabbabbbaaaaaaabbb\n" +
				"aabbabaababbbabbaabbbbab\n" +
				"baababbbbbbbbbabbabbaaaababaabab\n" +
				"babbaababbaabbaaaaaaaaba\n" +
				"aaabbbbabbaaaabbaaabbaaa\n" +
				"aaaabbbbbaaababaababbbbabababbababaabbab\n" +
				"baaaaabbbaaabaababbaaabbabaabbaabbbbaaaababbaaaa\n" +
				"babbabaabbbbaaaababbbbaabbbbabbb\n" +
				"abbbbabaaabababababaabab\n" +
				"bbaaaaabababaababababbab\n" +
				"ababbaabaabbaaaabaabbaba\n" +
				"bbaabbaaabaababaaaabababbbbbaaba\n" +
				"babbbbaaaabbabbaaabbbbbb\n" +
				"baaabbaabababbaaabababaa\n" +
				"aaaabbbabaabababbabbaababbbbabbaaaabaabbaaaabababbbaabbb\n" +
				"bbbbbbabbaaabbaaabbbbaabbbabaababbbbbaaabbabaaabbabbbbba\n" +
				"baabaabaaabaabbaaabbababbabbabaabbabbaabbaaabaab\n" +
				"babaaaaaabbbbaabbbbbbaba\n" +
				"bbbaababaabbbabbaaababba\n" +
				"bababbbbbaaabbbbabbbabbaababbaababbbaaabbbbaaaba\n" +
				"abbbababbbbbaaabbabbaaab\n" +
				"bababaababaabbbabaaabbbbbaabababbbbaabaa\n" +
				"bbbaabbbaabbbbbaabaaabaaaaabbbbbaababbbbbbababbabbabababaaaababaaaabaabb\n" +
				"baaaaabbabaabaaaabbbbaaaaababababbbbabbb\n" +
				"bbbbaabbbbaaabbbbaabbbaabaaababbbbaababb\n" +
				"abaaaabaaabbababbbaabbba\n" +
				"bbabaabbbbaabbaaababbaabaaababaa\n" +
				"aababbbaabaabbbaaababbaabaaabaaaaabbbbba\n" +
				"babbaabbbbbbaabaaabbabbaaabbabaabbaabbab\n" +
				"aababaabbbbababbaabbbaababbabbabbaaaabaaabbaabaabbbaaabb\n" +
				"abaabbbbabaabbaababaaaba\n" +
				"aabbbaabbaaabababbaaabba\n" +
				"baabaaaaaaaabababaaaabab\n" +
				"bbaaaabbbaaabbbaabababba\n" +
				"bbaaaaabaabaaabbaaaaabbb\n" +
				"baaababaabbaaaaababbbbbbabbbbbaabbbbbbabababbbaa\n" +
				"abbbbabaababaabaaaaabaab\n" +
				"baaababaabbbabbbbaabbaab\n" +
				"abaabbaabababaababbaaaaaababaabb\n" +
				"abaababaaabbabbaabaaabba\n" +
				"bbaababbaabaaabbbababaabbbbabbab\n" +
				"babbaaaaabbabbbaabababbb\n" +
				"aabbaaabbabbbbaaaabbbbba\n" +
				"bababaaababaaababbbabbba\n" +
				"bababbbaabbaabababbbabbbbbababababbbbbaabaaababb\n" +
				"abbabbbababbbbaaaabbaabbaabbaaaaabbabbaa\n" +
				"aaaabababbabbbaaaabaabaaabbabbabbbbbaaaabbaaaababaabbabbbbbbbbbababbabaaaabbbaab\n" +
				"bbbaaaaabbaabbbaaabaabbaabaaababbabbbbbabbbabaab\n" +
				"bbaaaababbbaababbabbbaab\n" +
				"aabaaabaaaabaabbbbabbaaababbababbabaababbbbaabba\n" +
				"aaaabababaababbaaabaaabaababaababbaaabbbbabbbaab\n" +
				"bbaabbaaaaaaabababbabbbabaaaabbb\n" +
				"ababbababbabaababbbabbbb\n" +
				"bbbbbbabbbaabbbbbbbabbbbbbbbbbaaaabbbbabaaabaaba\n" +
				"aabaaababbaabbaababbaabaabbaabbaababbbbb\n" +
				"bbaaaaabbbbaabbabbabbabaabaaaaaababaabab\n" +
				"aababaaabbbbaabbbbababbabbabaaaabaaaababbaaaababbabbbaabbaaabaab\n" +
				"aabbaaaaabbbaaaaabbaaaaabbbabaab\n" +
				"aababbbabbbaababaabababaababbbaaabaaabab\n" +
				"bbbaaaababaaabaaaaaabbabaababbbaabababaaaaaabaabaaababab\n" +
				"abaaaabbabababbaababbbab\n" +
				"bbabaabbaababababaabbbbb\n" +
				"babbabaabbabbbbbaaabaabbbabbaaaabaaabbba\n" +
				"aaaaabaababbabaaabbabbbb\n" +
				"bbaaaabbaabaaabbbaabbaba\n" +
				"abaaababaaabbbbbbbabbababbaaabaa\n" +
				"bbbbaababbbabaaababbabab\n" +
				"ababababaababaaabababbbbaaabbaababbbbabababaabba\n" +
				"babbaabaabaabbbbbaabaaaaaaabaabbbaabbabb\n" +
				"aaabaaaaababbaabaabbabaaabaabbabbbbbbaaabaaabaaa\n" +
				"baaabbaabaabaabbbaabbbab\n" +
				"aaaabbbaabaabbbbabbbbbaabbbbbbbbababbbaa\n" +
				"bbabbbaabababaababbaabaa\n" +
				"bbabaabaabbababaabaaaaabaaaaaaaabbbaabba\n" +
				"bbaababbbaabbaaabaaaaaba\n" +
				"baabbabbabbababbbbbbbbaabbbaaaaabaaaabbb\n" +
				"babaaabaaababbabbabaaaaaaaabbaaaaabbabbabaaabbbabbaabbab\n" +
				"babbabaaabaaaabaabbbababbaaaabba\n" +
				"abbababaaabaabbaabbbaaaabbaabaababbbbbabaabbbaaaaaababbaabbbbbbb\n" +
				"abbabbbaabaabaaaaababaabaaaabbbbabbaaaaababababbbbabbaabaabbababaaaabaabaaabababbbabbaab\n" +
				"bbaababbaaaaabaaaabbbaababbbbbab\n" +
				"babbbabbaaabaaababaabbbabbbaaaba\n" +
				"aaabaaabbaababbaabbbbbaaaabbbaba\n" +
				"bbaaabababaaaabaababaabaaabbbabbbbbbaabbbbaabbaababbbbabbbabbabbbababbab\n" +
				"abbbabbabbbbaaababbbbaabbaaaaaab\n" +
				"babbbabbaabbaabaaabbaaabbbbbaaabbaababbbaaaaaabb\n" +
				"aabbaabaabaabbabbbbbbbbabbbaabbb\n" +
				"abaaaaabbbbbaababaaaabab\n" +
				"abbbbbbaaabaaaaabbabaaaa\n" +
				"bbbbbbbaaabababaaaabaaba\n" +
				"bababbaababaaaaababaaaba\n" +
				"abbbaababaababbbbbbbabbabbbaabaa\n" +
				"abbbbbaabbbababababbbbbbbaaaabaabbbaaaba\n" +
				"abaaaabaabaaabbbbaababaa\n" +
				"bbaabaaaaaaaababaabbaabbabbbbbbabaabbbbabbababba\n" +
				"baabbaabaaaaabaabaabaabbbbabbbab\n" +
				"bbaaabbaaabbabbaaaaaaabbbbbaabbabbaaaaaabaabbbaa\n" +
				"aaaaaaabbbbbbbabbbababaabbababbb\n" +
				"abbbabbbbabbbaaaabababbb\n" +
				"aaabaaabaaaabbabaaaabbbb\n" +
				"babbaaaabbbbbbabbabbabba\n" +
				"ababababaabbabaabbabbbbbbaabbbaa\n" +
				"aabbbaabaaaaaaabaaababbb\n" +
				"aaaaabaaaaabbbbaaaaabbababaabbababaabababaaaabaa\n" +
				"abbbbaabbbabaabbababaaabaaabaaabbaaaabbabbaaabba\n" +
				"aaabbbbabbbbaabaababababbababbbabaabaabbbbbaabbb\n" +
				"abbbababbaababbababaabaa\n" +
				"ababaaababbbbaabbbabbabb\n" +
				"babbbbbbbaababbbbbbabaaaabababbbbaabbabb\n" +
				"aabaaabbaabbaaaabaaaabab\n" +
				"abbbabbbbababbbbbbabababababaabbaabbbbba\n" +
				"bbbabaaaaababaabaaaabbaa\n" +
				"bbbbaaabbabbaababbbaabba\n" +
				"ababababbaaababababaaaaaaaabababbaaabbbbbbaaabaaaaababbabaaaabba\n" +
				"bababbbbbbaaababaababaabaabbababbbbbbbba\n" +
				"baababbaaabaabababbaabba\n" +
				"bbbaababbbbbbbbabbaabbab\n" +
				"babbbbaababbbbbbbaabbbba\n" +
				"aaabaabbabbbbaabbbaababa\n" +
				"abbbaaaaaaaaabaabbabbaab\n" +
				"aabababbababaabaabbbbbbbaabbaababbaaaaaaaaabbbbaaaaabbaa\n" +
				"bababbbbaaababababbabababbbbbbbaababaaaaaaabbaaa\n" +
				"bbbbaaababbabbababababaa\n" +
				"bbbbbaabaaaababbaabbbbba\n" +
				"baabbaaabbbbabbbbbabaabaaabaabbbaabbbbbb\n" +
				"bababbaababbbbbbbaaaaabbbbaaabab\n" +
				"aabbaaabbbbbbbabaababbabbbaaabaa\n" +
				"bbbbaaabbaababbaaababbab\n" +
				"ababbaabaabbababbaaaabaa\n" +
				"baabaabbabbbabbbbaaaabbb\n" +
				"abbabbbabbbaababbbbaabba\n" +
				"abaaabbaaaabbabbababaabbbaaaababbaaabbbbaaabaabb\n" +
				"baabaabbbaaaaabbbabbaaaaaabbabbbbbbaabbb\n" +
				"abbaabababaabababbaababbabbaabaa\n" +
				"bbaaaaaabbaaaabaaabbababababbbab\n" +
				"aabbaaabbababbaabbbbabbbbaababababbaabba\n" +
				"bbbbaabbaababaababbbbaabbbabbbbaababbbaaabbbbbbbbbababbb\n" +
				"aaaaababaaaababaabbbababaabababaabbaaabbbabbbbab\n" +
				"aaabbbbabbabaabbbbaaabaa\n" +
				"bbabababbabaaabbbbbaaaba\n" +
				"aabbabbabbbabababaaabaab\n" +
				"bbbbaabbabbbabbbaabbbbbabaabababaaabbbab\n" +
				"baaabbbbaabbbabbbbaaaaaaabbbaabbaababbbb\n" +
				"bbaaabababbbaabaabbbaababababbabbbbabbab\n" +
				"bbbaaaababbaaababbbabbabbbbbbaaaababaabb\n" +
				"abaababbbabbbbabaababbabbaaaaabbbaababaaabbaabaabbababaaabaababb\n" +
				"ababbaabaabaabbaabbabaaa\n" +
				"bababbaababbaababaabbaabaabaaaaabaabaabaaababbabbbabaaaa\n" +
				"babaaaaaaabbbabbaaabbbaa\n" +
				"abbbbaabbbbbaababbbaababaaaaaaababababbb\n" +
				"bbabababbababbbbbbbbbaaa\n" +
				"aabbbaabbababbaaaabaaababbabbabb\n" +
				"baababbbabbbbbbabbbbbbbaaaabababbaaabbab\n" +
				"bababbbbabaabbaaabbbbaaabaaaabbb\n" +
				"abbaabaaabababbbaaaaaaaaaabbaabbbaabaabb\n" +
				"bbabbbbbbbabbbbabbbbaabbabaaaabbbaabbaba\n" +
				"bbaabbaabbababaabbaaababbabaaabb\n" +
				"bbaaaabbbaabaababbbbabbaababaabb\n" +
				"aaaaaaababaabbbabbbbabaa\n" +
				"bbbbaabaabababbabbaabaaabbababbaababbbbbaaaabbaabbbabbbabbaababb\n" +
				"abaaabbbbbbbababbbaababa\n" +
				"abaaaababbbbabbaabababba\n" +
				"aababbbabbbbaabbbababbbbabaaaaababbbaabbbababbab\n" +
				"bbbbaaaaabaabaabbbbbababbaaaaaba\n" +
				"babbaaaabaabbaabaaaaabba\n" +
				"bbbbaaaabaababbabbbaabba\n" +
				"ababababbbaaaaaaabbabaab\n" +
				"bababbbabbbaababaabbabaaabbabbbabbabbbab\n" +
				"baabaaabbabbbbbbbbbbbaabaabababa\n" +
				"ababbbbaabaaaabababbbbba\n" +
				"aaaaababbbabbbaababaaaba\n" +
				"baabababbaaaaabbbbababbb\n" +
				"bbbbabbabaabaaaababbbbba\n" +
				"aababaaabbabbbabbbbbbabbbbbaabbbbaabbabb\n" +
				"bbabaabaabbabababbbabaaaabaabaaababaaaba\n" +
				"aabbbbaaabbbbabbbabbabab\n" +
				"abaababaababbbbbbababbab\n" +
				"bbbabaaaabbbbabaaaabbabb\n" +
				"aabbaabbabbabbabbabbabba\n" +
				"bbababababaabbbbbabaaaab\n" +
				"bbbbaaaaaabaaabbbaabbbbb\n" +
				"aaaababbbaababababbabbaa\n" +
				"abaabbaaaabaaabababbbbba\n" +
				"aababbaabbaaababbbbbbbbababbaabb\n" +
				"babbbababbbbbaabbaabbbabbaaaababbbaabbabbbbaaabbaabbbabbbaababaa\n" +
				"bbaaaaabbaabaabaabbaaabbababaababaabaabbabbabbaabbaaabbababbbbab\n" +
				"abbabbbabaabaabbabbbaaab\n" +
				"babbababbaabbabaabaaabbabbababbbaaababbb\n" +
				"baababbbbbababaaababaaaabbabbaaa\n" +
				"bbbabaaaaaabbbaaaaaabbabbabababbabbbbaaaaaababab\n" +
				"ababaaabbaaabbbbababaaabbbbbaaaababbaabaabbbbabbbbabbaaa\n" +
				"abaaaaababbaaaaabbabbbaabaaaabaababaaaba\n" +
				"abaabababaabbaabbbbaaaab\n" +
				"babbbbbbbaaabbaaabbbbababbaaabbbabababaabbbaaaba\n" +
				"baabaaabaaaaaaabbbabaabbbbabbaba\n" +
				"aababbaaabbbaaaaabababba\n" +
				"abbbbabaabaabbaaaaaaaaababbbaaaabbabbaaababaaaab\n" +
				"bbaaaabbaababaabaabaaabaabbabbbb\n" +
				"bababaabbabbbaaabbaabaaa\n" +
				"bbbaabababbbbaabbabbabaabbaabaaa\n" +
				"babaaaaaabaaabbbbbababbb\n" +
				"abbbbbaabaabaabababbabba\n" +
				"baababbaaabbababababbbaa\n" +
				"aaabbbbaaaabababbabaabba\n" +
				"abaabbbabbbaabababaaabab\n" +
				"babbbaaaaabaaabababbbaba\n" +
				"abaabaabbabbaabaabaababb\n" +
				"aaaaaaabbabbaabaaaabbbab\n" +
				"bbabbbaabaabaababbaaaaab\n" +
				"bbaaaabbbbbbbbabababaabb\n" +
				"bbaababbbaaabbbabbaabbab\n" +
				"aaababababbabbabbabbabab\n" +
				"aabbaaaababaaaaaabbbabbaabaabbbaababaaaa\n" +
				"aabaaaaaabbaabababaaabab\n" +
				"baabaaabbbbababaababbbbb\n" +
				"ababababbbbbaababbaabaaa\n" +
				"bbbababaaababbbaabaaabab\n" +
				"bbbababbbbababababababbb\n" +
				"baababbbbbbbbbabaabbbbaabbbbbaaa\n" +
				"ababbbbaaabbaabbbbaabbbb\n" +
				"baababbababababbbbbbbbbaabbbbaabbbaaaaabbbaabbab\n" +
				"abaabbbabaababbaabbaabaa\n" +
				"aabbabbabbbbaaaaabaababb\n" +
				"baababbaabbaababaabaaaaabbbabbba\n" +
				"aabaaaaabbbbababbbbbaaaabbbabaabaababbab\n" +
				"aaaaaaaaabbaaabababbbbab\n" +
				"baaabbbbabbbaaaaababbbab\n" +
				"abaababaaababaaabababbaaabbbababbbabbbaaaaababbaaaaaabbaaaabbbbb\n" +
				"aabbbaaaabaabaaabbbbbbababbbbaaabbbabbaa\n" +
				"babbaaaaaabbbaaaabbbbbaaaabbabbabbbaabaa\n" +
				"bbbababbbabbbbaaaabaabaa\n" +
				"babbbabbabbbbbaaabbabbbb\n" +
				"aababaaabbaaaaababaaaaaabbbbbbababababbbbaaaabbabaababaababbabbaabbbbaaababababa\n" +
				"bbbabaaabaababbaaabaaaab\n" +
				"baabaababbabbbbbbaabbbbb\n" +
				"bbbbbbabaaababbbabbabbaabaabbbba\n" +
				"bababbabbaabbaaabaababbbabbbaababbabbaaaaababbabbbbbaaabbabbaaaaabaabbba\n" +
				"abbaaabbabbababaabaaabaa\n" +
				"bbaaababaabbabaaabaaabaa\n" +
				"bbabbbaaaaaaaaaaaaaabaab\n" +
				"bbaaaaabbbababbabbbaaabb\n" +
				"aaaaaaabababbabababaaaab\n" +
				"abbabbabbabbbbaabbabbabb\n" +
				"aabaaaaaabbbaaaaaabbabababaaabbbbbabaaabbabaabaabaaaabaa\n" +
				"bbbbbaabbbaaaaaababbbbbbaaaabbababbaabbbabbbbbabbaaaabab\n" +
				"aaabaaaaabbbabaabbaaaaba\n" +
				"aababaabaabbaaaabaaaaaaa\n" +
				"aaaabbbaaaabababaabbbaababbabaaabaaaaaaa\n" +
				"baabbaabbaabaabbbababbbbbbbabbaa\n" +
				"bbbaababbbbbaaaaaaaabaab\n" +
				"bababaabbabbabaabbabbaaa\n" +
				"baabbaaababaaaaabbaaaaaabbbabbbaaabbbaaabbbabaaaaababbbbbbbaaaab\n" +
				"abbaababbaababababaabbaaababbaababbaabbbaaaaaabb\n" +
				"babbabaaaaababbaabbaabaabbbbabba\n" +
				"babbaabbbabbaabbaaaabaab\n" +
				"aababbaaaaabaaabaabbbaabaabbabbaabbbbbbabbaabbbbbababaaa\n" +
				"aaaabbbababaaaaaaabaabbb\n" +
				"bbaababbabbbaaaaababaaabbbabbbbaaabbbbaababbababbbabbabb\n" +
				"aababbbaaaabaaaaabaababb\n" +
				"baaababbabbbbbbbbabaaabbabaabaabbbabbaba\n" +
				"abbabbabaaaaaabbaaabbabbbbbabaababbababbbbbaabba\n" +
				"abbbbabaaabbababbbbaaaba\n" +
				"abbbaababbabbabbbaabbabbbbabaaaa\n" +
				"abbbbaabaabbbabbaaaabbaa\n" +
				"aaabbbbaaaaabbababbaaabbaaaaabbb\n" +
				"bbbbaaabaaabaaabbaabbbaa\n" +
				"babbbabbbabbabaaabbabbbb\n" +
				"ababbbbaabbbabbbaaabbaaa\n" +
				"bbabbbbbababbaabaaaabbbb\n" +
				"bbbababbbbbbbbbabbbabbbb\n" +
				"abbbabbbbababbbbabbabbbb";

	}

}
