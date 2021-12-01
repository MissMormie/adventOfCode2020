package days2020;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Day8_HandheldHalting {

	public static void main(String[] args) {
		System.out.println("answer A: " + runA(textInput()));
		System.out.println("answer B: " + runB(textInput()));
	}

	public static int runA(String input) {
		String[] instructions = input.split("\n");
		int accumulator = 0;
		Set<Integer> instructionSet = new HashSet<>();
		Integer index = 0;
		do {
			String[] instruction = instructions[index].split(" ");
			if (instructionSet.contains(index)) {
				return accumulator;
			}
			instructionSet.add(index);

			switch (instruction[0]) {
				case "nop":
					index++;
					break;
				case "acc":
					accumulator += Integer.parseInt(instruction[1].replace("+", ""));
					index++;
					break;
				case "jmp":
					index += Integer.parseInt(instruction[1].replace("+", ""));
					break;
			}

		} while (true);
	}

	public static int runB(String input1) {
		int index = 0;
		do {
			String[] split = textInput().split("\n");
			if (split[index].contains("nop")) {
				split[index] = split[index].replace("nop", "jmp");
			} else if (split[index].contains("jmp")) {
				split[index] = split[index].replace("jmp", "nop");
			}

			Optional<Integer> integer = doesProgramTerminate(split);
			if (integer.isPresent()) {
				return integer.get();
			}

			index++;
		} while (true);

	}

	private static Optional<Integer> doesProgramTerminate(String[] instructions) {
		Set<Integer> instructionSet = new HashSet<>();
		int accumulator = 0;

		Integer index = 0;
		do {
			if (index >= instructions.length) {
				return Optional.of(accumulator);
			}
			String[] instruction = instructions[index].split(" ");
			if (instructionSet.contains(index)) {
				return Optional.empty();
			}
			instructionSet.add(index);

			switch (instruction[0]) {
				case "nop":
					index++;
					break;
				case "acc":
					accumulator += Integer.parseInt(instruction[1].replace("+", ""));
					index++;
					break;
				case "jmp":
					index += Integer.parseInt(instruction[1].replace("+", ""));
					break;
			}

		} while (true);

	}

	private static String textInput() {
		return "jmp +11\n" +
				"nop +495\n" +
				"nop +402\n" +
				"jmp +570\n" +
				"jmp +569\n" +
				"jmp +451\n" +
				"acc -12\n" +
				"jmp +364\n" +
				"acc +30\n" +
				"acc +21\n" +
				"jmp +430\n" +
				"jmp +87\n" +
				"acc -12\n" +
				"acc -4\n" +
				"acc +11\n" +
				"jmp +50\n" +
				"jmp +149\n" +
				"jmp +341\n" +
				"nop +1\n" +
				"acc +33\n" +
				"jmp +461\n" +
				"acc +43\n" +
				"acc -15\n" +
				"jmp +440\n" +
				"acc +18\n" +
				"acc +22\n" +
				"acc -14\n" +
				"nop +576\n" +
				"jmp -22\n" +
				"acc +33\n" +
				"acc +0\n" +
				"acc +34\n" +
				"jmp +369\n" +
				"nop +497\n" +
				"nop +469\n" +
				"acc -12\n" +
				"jmp +93\n" +
				"acc -13\n" +
				"jmp +337\n" +
				"acc +5\n" +
				"acc +18\n" +
				"acc +26\n" +
				"nop +115\n" +
				"jmp +67\n" +
				"nop +282\n" +
				"nop -6\n" +
				"nop +289\n" +
				"jmp +303\n" +
				"acc +10\n" +
				"acc -15\n" +
				"jmp +153\n" +
				"nop +445\n" +
				"acc -8\n" +
				"acc +11\n" +
				"jmp +374\n" +
				"acc +35\n" +
				"acc -9\n" +
				"nop +199\n" +
				"jmp +1\n" +
				"jmp +369\n" +
				"jmp +1\n" +
				"acc +22\n" +
				"acc -18\n" +
				"acc +17\n" +
				"jmp +303\n" +
				"acc +37\n" +
				"acc +13\n" +
				"acc +22\n" +
				"nop +307\n" +
				"jmp +154\n" +
				"nop +381\n" +
				"acc -6\n" +
				"nop -54\n" +
				"acc +38\n" +
				"jmp +494\n" +
				"acc +37\n" +
				"acc +15\n" +
				"jmp +475\n" +
				"jmp +474\n" +
				"nop +534\n" +
				"acc +37\n" +
				"jmp +359\n" +
				"jmp +296\n" +
				"acc +40\n" +
				"jmp +157\n" +
				"acc +5\n" +
				"nop +139\n" +
				"acc +49\n" +
				"acc +45\n" +
				"jmp +237\n" +
				"acc +42\n" +
				"acc +8\n" +
				"acc +43\n" +
				"jmp +466\n" +
				"nop +362\n" +
				"acc +43\n" +
				"acc +44\n" +
				"jmp +233\n" +
				"acc +30\n" +
				"acc +42\n" +
				"acc -6\n" +
				"jmp -97\n" +
				"jmp +527\n" +
				"acc +2\n" +
				"acc +13\n" +
				"nop +425\n" +
				"jmp +113\n" +
				"nop +374\n" +
				"acc +31\n" +
				"jmp +48\n" +
				"acc +29\n" +
				"acc +15\n" +
				"acc +35\n" +
				"jmp +357\n" +
				"acc +19\n" +
				"acc -2\n" +
				"jmp +480\n" +
				"acc +1\n" +
				"jmp +385\n" +
				"acc +16\n" +
				"acc +47\n" +
				"jmp +397\n" +
				"jmp +1\n" +
				"jmp +1\n" +
				"acc +5\n" +
				"acc -5\n" +
				"jmp +529\n" +
				"acc +11\n" +
				"jmp +123\n" +
				"acc +44\n" +
				"acc +49\n" +
				"jmp +413\n" +
				"acc +13\n" +
				"acc +10\n" +
				"acc -6\n" +
				"acc +11\n" +
				"jmp -33\n" +
				"acc +25\n" +
				"acc +47\n" +
				"acc +40\n" +
				"acc +23\n" +
				"jmp +39\n" +
				"acc +50\n" +
				"acc +12\n" +
				"jmp +386\n" +
				"acc +23\n" +
				"jmp +464\n" +
				"acc +15\n" +
				"nop +361\n" +
				"acc +30\n" +
				"jmp +346\n" +
				"jmp +1\n" +
				"acc +19\n" +
				"acc +16\n" +
				"acc +23\n" +
				"jmp +441\n" +
				"jmp +69\n" +
				"acc +12\n" +
				"acc +46\n" +
				"acc -5\n" +
				"jmp +427\n" +
				"acc +49\n" +
				"nop +173\n" +
				"acc -12\n" +
				"jmp +249\n" +
				"acc +41\n" +
				"acc +29\n" +
				"nop +168\n" +
				"acc +31\n" +
				"jmp +349\n" +
				"acc +40\n" +
				"acc +8\n" +
				"acc +14\n" +
				"jmp +231\n" +
				"acc -17\n" +
				"jmp +215\n" +
				"nop +202\n" +
				"acc +0\n" +
				"nop +172\n" +
				"jmp +351\n" +
				"acc +21\n" +
				"acc +31\n" +
				"nop +405\n" +
				"acc +14\n" +
				"jmp +272\n" +
				"acc +5\n" +
				"acc +33\n" +
				"acc +31\n" +
				"acc +21\n" +
				"jmp -91\n" +
				"acc -18\n" +
				"acc +35\n" +
				"jmp +23\n" +
				"acc -10\n" +
				"nop +374\n" +
				"acc +27\n" +
				"jmp -157\n" +
				"acc +39\n" +
				"acc +8\n" +
				"acc +34\n" +
				"acc +34\n" +
				"jmp +333\n" +
				"jmp -51\n" +
				"acc -18\n" +
				"acc +26\n" +
				"jmp +377\n" +
				"acc -5\n" +
				"jmp +190\n" +
				"acc +45\n" +
				"jmp +1\n" +
				"acc +29\n" +
				"jmp +202\n" +
				"acc +25\n" +
				"acc +30\n" +
				"jmp +90\n" +
				"acc +49\n" +
				"nop +240\n" +
				"jmp +109\n" +
				"jmp +291\n" +
				"acc +9\n" +
				"jmp +348\n" +
				"acc +39\n" +
				"jmp +3\n" +
				"jmp +273\n" +
				"jmp -218\n" +
				"jmp +150\n" +
				"jmp +1\n" +
				"jmp +148\n" +
				"acc +9\n" +
				"acc +11\n" +
				"acc +20\n" +
				"acc +3\n" +
				"jmp -167\n" +
				"nop +223\n" +
				"jmp +275\n" +
				"nop +309\n" +
				"jmp +20\n" +
				"acc -18\n" +
				"acc -10\n" +
				"acc -2\n" +
				"jmp +173\n" +
				"acc +13\n" +
				"acc -17\n" +
				"nop +132\n" +
				"acc -6\n" +
				"jmp +240\n" +
				"acc +37\n" +
				"acc +4\n" +
				"acc +49\n" +
				"acc -16\n" +
				"jmp +171\n" +
				"jmp +239\n" +
				"nop +300\n" +
				"nop +311\n" +
				"acc -9\n" +
				"jmp -180\n" +
				"acc +32\n" +
				"acc +21\n" +
				"jmp +1\n" +
				"jmp -62\n" +
				"nop +141\n" +
				"acc +46\n" +
				"acc +25\n" +
				"nop -7\n" +
				"jmp +318\n" +
				"acc +3\n" +
				"jmp +185\n" +
				"nop +196\n" +
				"acc +16\n" +
				"acc +18\n" +
				"jmp -47\n" +
				"acc +39\n" +
				"acc +35\n" +
				"acc +21\n" +
				"acc +14\n" +
				"jmp +23\n" +
				"acc +20\n" +
				"acc +20\n" +
				"jmp +97\n" +
				"nop -71\n" +
				"acc +50\n" +
				"acc -11\n" +
				"jmp -145\n" +
				"nop -218\n" +
				"acc +42\n" +
				"acc +23\n" +
				"acc +35\n" +
				"jmp +183\n" +
				"nop +16\n" +
				"nop -206\n" +
				"acc +2\n" +
				"acc +11\n" +
				"jmp +129\n" +
				"acc +37\n" +
				"acc +41\n" +
				"acc +47\n" +
				"nop -280\n" +
				"jmp +93\n" +
				"acc -12\n" +
				"acc +31\n" +
				"jmp +10\n" +
				"acc +2\n" +
				"acc +29\n" +
				"jmp +64\n" +
				"acc -14\n" +
				"nop +308\n" +
				"jmp -251\n" +
				"acc +8\n" +
				"acc +10\n" +
				"jmp +259\n" +
				"acc +38\n" +
				"nop -131\n" +
				"acc +45\n" +
				"jmp +6\n" +
				"acc +18\n" +
				"acc +43\n" +
				"nop -218\n" +
				"nop -94\n" +
				"jmp +178\n" +
				"jmp +1\n" +
				"acc +27\n" +
				"acc +29\n" +
				"jmp +324\n" +
				"acc -12\n" +
				"acc +30\n" +
				"jmp +115\n" +
				"acc -1\n" +
				"jmp -224\n" +
				"jmp +1\n" +
				"jmp +205\n" +
				"acc +47\n" +
				"jmp -66\n" +
				"acc +21\n" +
				"acc +44\n" +
				"jmp +147\n" +
				"acc +2\n" +
				"acc +50\n" +
				"acc -14\n" +
				"acc +50\n" +
				"jmp -165\n" +
				"acc +33\n" +
				"acc +20\n" +
				"acc -5\n" +
				"acc +19\n" +
				"jmp -246\n" +
				"acc +26\n" +
				"acc +44\n" +
				"jmp -96\n" +
				"acc +22\n" +
				"jmp +127\n" +
				"nop +25\n" +
				"acc -14\n" +
				"acc -15\n" +
				"jmp -314\n" +
				"jmp +1\n" +
				"acc +11\n" +
				"acc +12\n" +
				"jmp +71\n" +
				"acc +0\n" +
				"acc +16\n" +
				"acc +26\n" +
				"nop +242\n" +
				"jmp -172\n" +
				"acc +8\n" +
				"acc +15\n" +
				"acc -4\n" +
				"jmp -78\n" +
				"acc +42\n" +
				"jmp +225\n" +
				"acc -7\n" +
				"jmp +243\n" +
				"jmp +242\n" +
				"acc +23\n" +
				"acc +21\n" +
				"jmp +54\n" +
				"acc +25\n" +
				"jmp -18\n" +
				"jmp -42\n" +
				"jmp +26\n" +
				"jmp -368\n" +
				"acc +29\n" +
				"acc +22\n" +
				"acc +1\n" +
				"acc +0\n" +
				"jmp +255\n" +
				"acc +39\n" +
				"jmp +1\n" +
				"nop -332\n" +
				"acc +22\n" +
				"jmp +92\n" +
				"acc +45\n" +
				"acc +29\n" +
				"jmp +12\n" +
				"jmp +1\n" +
				"acc +22\n" +
				"jmp +1\n" +
				"jmp -245\n" +
				"jmp -162\n" +
				"acc -4\n" +
				"acc -4\n" +
				"jmp +28\n" +
				"nop -4\n" +
				"jmp -386\n" +
				"jmp -28\n" +
				"acc -1\n" +
				"acc +25\n" +
				"nop -28\n" +
				"jmp -10\n" +
				"acc +9\n" +
				"acc +45\n" +
				"jmp +1\n" +
				"acc +13\n" +
				"jmp -171\n" +
				"acc +3\n" +
				"acc +19\n" +
				"acc -8\n" +
				"acc +11\n" +
				"jmp -266\n" +
				"acc -18\n" +
				"nop -380\n" +
				"jmp -155\n" +
				"acc +36\n" +
				"acc -13\n" +
				"acc +35\n" +
				"acc -16\n" +
				"jmp -414\n" +
				"nop -408\n" +
				"jmp +36\n" +
				"acc +5\n" +
				"jmp +101\n" +
				"acc -7\n" +
				"acc +17\n" +
				"jmp -204\n" +
				"acc -14\n" +
				"jmp -99\n" +
				"jmp +1\n" +
				"nop -165\n" +
				"acc +10\n" +
				"acc +13\n" +
				"jmp +46\n" +
				"acc -13\n" +
				"jmp -358\n" +
				"acc -7\n" +
				"acc -14\n" +
				"jmp -31\n" +
				"acc +21\n" +
				"acc -9\n" +
				"jmp -46\n" +
				"nop -220\n" +
				"acc -1\n" +
				"jmp -105\n" +
				"acc +42\n" +
				"acc -14\n" +
				"jmp -75\n" +
				"acc +6\n" +
				"jmp -34\n" +
				"nop -391\n" +
				"acc -11\n" +
				"jmp -123\n" +
				"nop -234\n" +
				"acc -9\n" +
				"acc +35\n" +
				"jmp -317\n" +
				"nop +150\n" +
				"acc +11\n" +
				"jmp +138\n" +
				"acc +30\n" +
				"acc -11\n" +
				"acc +31\n" +
				"jmp -134\n" +
				"acc +20\n" +
				"jmp -200\n" +
				"acc +13\n" +
				"acc +14\n" +
				"acc +25\n" +
				"jmp -310\n" +
				"acc +13\n" +
				"acc +18\n" +
				"acc -1\n" +
				"jmp +85\n" +
				"jmp +72\n" +
				"acc +1\n" +
				"jmp -78\n" +
				"acc -8\n" +
				"jmp -149\n" +
				"acc +13\n" +
				"jmp -438\n" +
				"acc +38\n" +
				"nop -25\n" +
				"jmp -264\n" +
				"acc +50\n" +
				"acc +47\n" +
				"nop -241\n" +
				"acc +31\n" +
				"jmp -419\n" +
				"jmp +57\n" +
				"nop -359\n" +
				"jmp -323\n" +
				"acc +48\n" +
				"acc +4\n" +
				"acc -12\n" +
				"acc +42\n" +
				"jmp -167\n" +
				"acc +50\n" +
				"acc -9\n" +
				"jmp -138\n" +
				"nop -171\n" +
				"acc +48\n" +
				"jmp -398\n" +
				"acc -8\n" +
				"acc +41\n" +
				"acc +21\n" +
				"jmp -508\n" +
				"acc +29\n" +
				"acc +41\n" +
				"acc +35\n" +
				"acc +25\n" +
				"jmp -388\n" +
				"jmp -439\n" +
				"acc +26\n" +
				"acc +19\n" +
				"acc +13\n" +
				"acc -16\n" +
				"jmp -165\n" +
				"nop -61\n" +
				"acc +16\n" +
				"acc +20\n" +
				"jmp -312\n" +
				"nop -19\n" +
				"jmp -101\n" +
				"acc +1\n" +
				"jmp -515\n" +
				"acc +19\n" +
				"jmp +96\n" +
				"jmp +1\n" +
				"acc +42\n" +
				"acc +34\n" +
				"acc +33\n" +
				"jmp +80\n" +
				"nop -314\n" +
				"acc +12\n" +
				"acc +36\n" +
				"nop -405\n" +
				"jmp -87\n" +
				"acc +16\n" +
				"nop -100\n" +
				"acc +11\n" +
				"acc +15\n" +
				"jmp -524\n" +
				"nop -369\n" +
				"acc +8\n" +
				"jmp -386\n" +
				"acc +32\n" +
				"jmp -77\n" +
				"acc -7\n" +
				"acc -16\n" +
				"acc +33\n" +
				"acc +30\n" +
				"jmp -213\n" +
				"acc -2\n" +
				"jmp -403\n" +
				"acc +35\n" +
				"nop -81\n" +
				"jmp -340\n" +
				"acc +7\n" +
				"acc +3\n" +
				"jmp -444\n" +
				"jmp -445\n" +
				"jmp -218\n" +
				"acc +39\n" +
				"acc -9\n" +
				"jmp +1\n" +
				"jmp -284\n" +
				"acc +43\n" +
				"acc +1\n" +
				"acc -12\n" +
				"nop -218\n" +
				"jmp -460\n" +
				"jmp -404\n" +
				"jmp +1\n" +
				"jmp -135\n" +
				"jmp -223\n" +
				"jmp +1\n" +
				"acc +30\n" +
				"acc +36\n" +
				"jmp -61\n" +
				"jmp -580\n" +
				"acc -8\n" +
				"jmp -79\n" +
				"acc +18\n" +
				"acc -1\n" +
				"acc +9\n" +
				"jmp -321\n" +
				"jmp -560\n" +
				"acc +2\n" +
				"jmp -182\n" +
				"acc +18\n" +
				"acc +29\n" +
				"acc +28\n" +
				"jmp -129\n" +
				"acc +10\n" +
				"nop -120\n" +
				"jmp +16\n" +
				"acc +23\n" +
				"acc +9\n" +
				"acc +36\n" +
				"acc +24\n" +
				"jmp -589\n" +
				"acc +21\n" +
				"jmp -419\n" +
				"nop -275\n" +
				"jmp -231\n" +
				"jmp -341\n" +
				"acc +33\n" +
				"acc +30\n" +
				"jmp -470\n" +
				"nop -337\n" +
				"jmp -389\n" +
				"jmp +5\n" +
				"acc -14\n" +
				"nop -27\n" +
				"acc +5\n" +
				"jmp -78\n" +
				"acc +40\n" +
				"acc +2\n" +
				"acc -5\n" +
				"nop -205\n" +
				"jmp -537\n" +
				"jmp -318\n" +
				"nop -404\n" +
				"acc +12\n" +
				"acc +0\n" +
				"acc -4\n" +
				"jmp -54\n" +
				"acc +8\n" +
				"acc +32\n" +
				"nop -357\n" +
				"acc +35\n" +
				"jmp -153\n" +
				"acc +42\n" +
				"acc +17\n" +
				"acc -9\n" +
				"jmp -13\n" +
				"nop -222\n" +
				"acc +27\n" +
				"jmp -63\n" +
				"acc +11\n" +
				"acc -17\n" +
				"nop -45\n" +
				"acc +4\n" +
				"jmp -217\n" +
				"acc +5\n" +
				"acc +26\n" +
				"nop -574\n" +
				"jmp -489\n" +
				"acc +29\n" +
				"acc +36\n" +
				"acc +31\n" +
				"nop -407\n" +
				"jmp +1";
	}
}
