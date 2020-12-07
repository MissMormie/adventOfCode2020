package days;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day7_HandyHaversacks {
	public static void main(String[] args) {
		System.out.println("answer A: " + runA(textInput())); // 226
		System.out.println("answer B: " + runB(textInput())); // 9569
	}

	public static int runA(String input) {
		Set<String> colors = new HashSet<>();

		String color = "shiny gold";

		bagsThatContainColor(input, colors, color);

		return colors.size();
	}

	public static int runB(String input) {
		String color = "shiny gold";

		// -1 because it counts shiny golden as well.
		return bagsThatFitInColor(input, color) - 1;
	}

	private static void bagsThatContainColor(String input, Set<String> colorSet, String color) {
		Pattern pattern = Pattern.compile(".*[0-9]+ " + color + ".*");
		Arrays.stream(input.split("\n"))
				.filter(line -> pattern.matcher(line).matches())
				.forEach(line -> {
					String newColor = line.substring(0, line.indexOf(" bags contain"));
					colorSet.add(newColor);
					bagsThatContainColor(input, colorSet, newColor);
				});
	}

	private static int bagsThatFitInColor(String input, String color) {
		IntegerWrapper bagsInside = new IntegerWrapper();
		Arrays.stream(input.split("\n"))
				.filter(line -> line.contains(color + " bags contain"))
				.forEach(line -> {
					String[] parts = line.split("(.*contain | bags, | bags\\.| bag, |bag\\.)");
					if ("no other".equals(parts[1])) {
						return;
					}

					IntStream.range(1, parts.length)
							.forEach(index -> {
								int numBagsInColor = 0;
								numBagsInColor = Integer.parseInt(parts[index].substring(0, parts[index].indexOf(" ")));
								String newColor = parts[index].substring(parts[index].indexOf(" ") + 1);
								int i = numBagsInColor * bagsThatFitInColor(input, newColor);
								bagsInside.num += i;
							});
				});
		return bagsInside.num;
	}

	private static String textInput() {
		return "light green bags contain 2 pale cyan bags.\n" +
				"dim tan bags contain 3 shiny teal bags, 5 bright white bags, 4 striped bronze bags.\n" +
				"dotted magenta bags contain 2 faded cyan bags, 4 bright yellow bags.\n" +
				"dull silver bags contain 3 striped green bags.\n" +
				"posh purple bags contain 2 mirrored lavender bags, 4 light chartreuse bags, 3 shiny green bags.\n" +
				"vibrant black bags contain 2 faded lavender bags, 4 shiny plum bags.\n" +
				"posh fuchsia bags contain 1 striped coral bag, 2 wavy gray bags, 3 posh white bags, 1 mirrored coral bag.\n" +
				"wavy magenta bags contain 2 light salmon bags.\n" +
				"shiny purple bags contain 3 vibrant white bags.\n" +
				"wavy chartreuse bags contain 3 striped purple bags, 3 vibrant blue bags, 2 mirrored fuchsia bags, 2 muted indigo bags.\n" +
				"vibrant cyan bags contain 4 posh teal bags, 1 dull aqua bag.\n" +
				"light plum bags contain 1 dotted blue bag, 2 clear yellow bags, 2 dark indigo bags.\n" +
				"mirrored chartreuse bags contain 4 dull plum bags, 4 light bronze bags, 2 dim red bags, 2 wavy silver bags.\n" +
				"faded purple bags contain 1 shiny maroon bag.\n" +
				"dark maroon bags contain 2 drab brown bags, 2 wavy tomato bags.\n" +
				"clear teal bags contain 1 bright beige bag, 3 dark green bags, 4 light indigo bags.\n" +
				"light gold bags contain 3 mirrored brown bags, 1 muted chartreuse bag, 2 wavy yellow bags.\n" +
				"dull aqua bags contain no other bags.\n" +
				"posh crimson bags contain 3 clear crimson bags, 3 mirrored tan bags, 5 muted coral bags, 4 striped yellow bags.\n" +
				"dotted lime bags contain 2 clear violet bags, 4 dark beige bags, 2 mirrored gray bags, 3 clear chartreuse bags.\n" +
				"bright indigo bags contain 5 muted brown bags.\n" +
				"vibrant crimson bags contain 3 faded olive bags, 2 faded fuchsia bags, 2 dotted chartreuse bags.\n" +
				"pale white bags contain 2 plaid aqua bags.\n" +
				"wavy coral bags contain 2 plaid red bags, 4 bright turquoise bags, 1 shiny silver bag, 2 clear blue bags.\n" +
				"light chartreuse bags contain 4 dotted violet bags, 1 mirrored lavender bag, 2 muted red bags, 1 plaid silver bag.\n" +
				"shiny plum bags contain 3 muted tomato bags, 5 dark bronze bags.\n" +
				"bright violet bags contain 2 striped brown bags, 5 vibrant chartreuse bags, 4 drab turquoise bags, 5 light plum bags.\n" +
				"plaid green bags contain 5 plaid white bags, 2 dull olive bags, 3 light tan bags, 4 vibrant tan bags.\n" +
				"light fuchsia bags contain 1 shiny gold bag, 5 faded beige bags, 2 dark chartreuse bags, 3 light brown bags.\n" +
				"posh beige bags contain 4 mirrored coral bags, 2 muted tomato bags, 1 dull lavender bag.\n" +
				"clear plum bags contain 2 plaid tomato bags.\n" +
				"dark indigo bags contain 5 dotted chartreuse bags, 3 vibrant olive bags, 3 dull blue bags, 4 light tan bags.\n" +
				"striped crimson bags contain 3 muted gray bags, 2 clear white bags.\n" +
				"drab crimson bags contain 3 plaid turquoise bags, 2 clear violet bags, 2 faded lavender bags, 1 dim crimson bag.\n" +
				"clear salmon bags contain 3 dim yellow bags, 1 mirrored white bag, 5 dotted aqua bags.\n" +
				"dim turquoise bags contain 2 bright salmon bags, 2 light salmon bags, 2 dark fuchsia bags, 2 dotted magenta bags.\n" +
				"vibrant tomato bags contain 2 faded indigo bags, 5 wavy yellow bags, 2 light tan bags.\n" +
				"plaid magenta bags contain 5 drab tomato bags, 5 striped crimson bags, 4 muted blue bags, 3 dim chartreuse bags.\n" +
				"light tomato bags contain 1 muted blue bag, 1 clear tan bag.\n" +
				"wavy white bags contain 4 dotted coral bags, 1 dim orange bag.\n" +
				"faded chartreuse bags contain 5 bright green bags.\n" +
				"striped tomato bags contain 5 muted tomato bags.\n" +
				"striped turquoise bags contain 5 striped brown bags.\n" +
				"faded indigo bags contain 1 pale green bag.\n" +
				"posh indigo bags contain 5 light violet bags, 1 shiny indigo bag, 1 striped crimson bag, 1 dotted bronze bag.\n" +
				"drab white bags contain 1 clear green bag.\n" +
				"pale salmon bags contain 4 vibrant white bags, 4 plaid aqua bags.\n" +
				"plaid plum bags contain 1 dim coral bag, 1 light olive bag, 5 shiny bronze bags.\n" +
				"muted crimson bags contain 1 wavy purple bag, 4 striped tomato bags, 5 muted gray bags, 4 bright black bags.\n" +
				"light magenta bags contain 1 pale cyan bag, 2 clear gray bags, 4 dim tomato bags, 2 shiny gold bags.\n" +
				"vibrant red bags contain 1 faded bronze bag, 5 faded red bags, 3 dull olive bags.\n" +
				"wavy silver bags contain 2 striped brown bags, 2 light white bags.\n" +
				"striped tan bags contain 5 light tan bags.\n" +
				"vibrant chartreuse bags contain 3 striped black bags, 4 plaid lime bags, 1 striped tomato bag.\n" +
				"vibrant salmon bags contain 1 shiny lavender bag, 2 shiny green bags, 3 plaid lime bags, 5 plaid plum bags.\n" +
				"clear lavender bags contain 4 dim orange bags, 1 dotted yellow bag, 1 faded fuchsia bag.\n" +
				"striped chartreuse bags contain 1 muted beige bag, 5 bright yellow bags.\n" +
				"bright magenta bags contain 4 light magenta bags, 4 faded lavender bags.\n" +
				"drab lavender bags contain 3 bright maroon bags.\n" +
				"dotted maroon bags contain 1 faded salmon bag, 2 striped black bags, 3 shiny coral bags.\n" +
				"vibrant green bags contain 4 mirrored turquoise bags, 1 wavy maroon bag.\n" +
				"dark violet bags contain 4 posh lavender bags.\n" +
				"clear cyan bags contain 3 wavy black bags, 3 pale cyan bags, 3 dark indigo bags, 4 mirrored turquoise bags.\n" +
				"light cyan bags contain 3 muted chartreuse bags.\n" +
				"drab gold bags contain 1 dark plum bag, 1 striped gray bag, 5 clear maroon bags.\n" +
				"muted lime bags contain 3 dim tomato bags.\n" +
				"pale aqua bags contain 3 muted aqua bags, 2 dark beige bags.\n" +
				"mirrored silver bags contain 5 bright violet bags.\n" +
				"drab yellow bags contain 4 posh coral bags, 2 posh silver bags, 1 bright crimson bag, 3 posh maroon bags.\n" +
				"dull beige bags contain 5 posh gold bags.\n" +
				"dim indigo bags contain 2 light gold bags, 2 clear yellow bags.\n" +
				"dotted bronze bags contain 1 dotted blue bag, 1 plaid chartreuse bag, 4 drab salmon bags.\n" +
				"wavy tomato bags contain 1 bright tan bag, 3 vibrant plum bags, 1 mirrored orange bag, 3 striped white bags.\n" +
				"pale brown bags contain 1 posh coral bag, 1 dotted violet bag, 2 light olive bags, 4 plaid olive bags.\n" +
				"mirrored tan bags contain 5 dotted white bags.\n" +
				"dull salmon bags contain 3 clear green bags, 4 plaid tomato bags, 3 vibrant tomato bags.\n" +
				"mirrored aqua bags contain 3 posh turquoise bags, 4 dotted magenta bags, 3 plaid tomato bags.\n" +
				"wavy plum bags contain 2 dark green bags.\n" +
				"drab indigo bags contain 4 muted lime bags, 1 plaid olive bag, 2 vibrant beige bags, 4 vibrant coral bags.\n" +
				"mirrored crimson bags contain 3 dull silver bags.\n" +
				"wavy teal bags contain 1 faded white bag, 2 dotted gold bags, 4 light violet bags, 2 pale coral bags.\n" +
				"wavy red bags contain 5 dull bronze bags.\n" +
				"striped gray bags contain 1 muted blue bag.\n" +
				"dim orange bags contain 1 mirrored lavender bag, 2 shiny bronze bags, 5 posh gray bags, 3 striped green bags.\n" +
				"mirrored fuchsia bags contain 4 posh gray bags.\n" +
				"bright yellow bags contain 3 faded lime bags, 2 wavy maroon bags, 2 mirrored coral bags, 1 vibrant violet bag.\n" +
				"vibrant lavender bags contain 1 drab chartreuse bag, 2 posh maroon bags.\n" +
				"bright chartreuse bags contain 2 pale plum bags, 3 light lime bags, 1 posh beige bag, 1 light gold bag.\n" +
				"vibrant brown bags contain 1 muted black bag, 4 shiny coral bags, 3 dim chartreuse bags, 1 drab magenta bag.\n" +
				"dotted tan bags contain 1 clear maroon bag, 1 light gray bag, 1 dotted maroon bag, 5 light tan bags.\n" +
				"mirrored magenta bags contain 2 clear brown bags.\n" +
				"light salmon bags contain 4 shiny brown bags.\n" +
				"dark black bags contain 5 posh gold bags, 3 wavy blue bags, 5 vibrant indigo bags.\n" +
				"shiny salmon bags contain 4 pale cyan bags, 1 muted coral bag.\n" +
				"dark silver bags contain 4 plaid brown bags, 4 dark gray bags.\n" +
				"striped magenta bags contain 4 plaid plum bags.\n" +
				"bright tomato bags contain 3 dim maroon bags.\n" +
				"faded lime bags contain 4 shiny violet bags, 4 wavy purple bags.\n" +
				"drab tomato bags contain 4 muted red bags.\n" +
				"plaid crimson bags contain 3 bright gray bags, 2 striped aqua bags.\n" +
				"wavy salmon bags contain 2 mirrored black bags, 4 mirrored coral bags.\n" +
				"vibrant fuchsia bags contain 3 muted cyan bags, 2 drab brown bags, 4 plaid turquoise bags, 2 dotted brown bags.\n" +
				"drab turquoise bags contain 2 wavy yellow bags.\n" +
				"posh lavender bags contain 1 clear tan bag, 2 mirrored lavender bags, 5 muted lime bags, 3 muted tan bags.\n" +
				"shiny gold bags contain 5 mirrored lavender bags, 4 shiny maroon bags, 4 striped yellow bags.\n" +
				"striped beige bags contain 3 mirrored orange bags, 4 dark orange bags, 2 dark white bags.\n" +
				"bright salmon bags contain 2 dark gray bags.\n" +
				"plaid bronze bags contain 2 wavy fuchsia bags, 5 muted fuchsia bags.\n" +
				"dark white bags contain 4 vibrant chartreuse bags, 5 light chartreuse bags.\n" +
				"dull tan bags contain 1 striped yellow bag.\n" +
				"dotted silver bags contain 3 dotted chartreuse bags, 5 drab red bags.\n" +
				"bright silver bags contain 5 dotted lime bags, 2 vibrant tan bags, 5 clear turquoise bags.\n" +
				"plaid chartreuse bags contain 2 vibrant lavender bags.\n" +
				"striped violet bags contain 1 striped black bag, 1 mirrored turquoise bag, 5 shiny bronze bags, 1 posh lavender bag.\n" +
				"vibrant maroon bags contain 5 muted chartreuse bags, 2 dark bronze bags, 3 plaid salmon bags.\n" +
				"wavy violet bags contain 4 bright turquoise bags, 4 muted salmon bags.\n" +
				"faded yellow bags contain 4 wavy maroon bags, 2 dull silver bags.\n" +
				"faded lavender bags contain 3 wavy black bags, 4 dotted chartreuse bags, 2 mirrored turquoise bags, 5 faded fuchsia bags.\n" +
				"dull fuchsia bags contain 3 vibrant green bags.\n" +
				"shiny maroon bags contain 1 mirrored coral bag, 3 bright black bags, 4 light chartreuse bags.\n" +
				"mirrored green bags contain 2 muted red bags, 4 muted beige bags, 4 light cyan bags.\n" +
				"wavy brown bags contain 3 mirrored chartreuse bags, 2 wavy green bags, 3 dim tan bags.\n" +
				"posh black bags contain 5 clear gold bags.\n" +
				"posh brown bags contain 3 bright maroon bags, 2 pale purple bags.\n" +
				"dotted coral bags contain 3 shiny cyan bags, 5 clear black bags, 1 faded olive bag, 1 clear crimson bag.\n" +
				"dotted green bags contain 4 dull silver bags, 4 shiny maroon bags, 2 faded brown bags, 4 muted crimson bags.\n" +
				"dim gold bags contain 3 vibrant indigo bags, 4 dark white bags, 1 dull gray bag.\n" +
				"pale turquoise bags contain 1 plaid bronze bag, 3 bright fuchsia bags.\n" +
				"drab lime bags contain 1 wavy yellow bag, 1 dim orange bag, 3 faded lavender bags.\n" +
				"vibrant gray bags contain 4 posh bronze bags, 5 wavy black bags, 5 mirrored coral bags.\n" +
				"posh tan bags contain 2 bright coral bags, 4 dull gold bags, 3 vibrant lavender bags, 3 dim chartreuse bags.\n" +
				"posh gold bags contain 4 posh lavender bags, 2 muted brown bags.\n" +
				"bright lime bags contain 3 mirrored fuchsia bags, 2 clear plum bags, 3 vibrant bronze bags.\n" +
				"vibrant aqua bags contain 2 mirrored brown bags, 4 dark red bags, 3 wavy indigo bags, 1 dim tomato bag.\n" +
				"wavy blue bags contain 5 clear maroon bags, 5 shiny cyan bags, 1 vibrant white bag.\n" +
				"bright turquoise bags contain 4 dim coral bags.\n" +
				"dull tomato bags contain 5 drab crimson bags.\n" +
				"muted beige bags contain 5 pale cyan bags, 4 striped white bags, 1 muted cyan bag, 2 plaid salmon bags.\n" +
				"dark bronze bags contain 5 drab magenta bags.\n" +
				"light yellow bags contain 2 striped lime bags.\n" +
				"dark lime bags contain 5 mirrored gray bags.\n" +
				"pale teal bags contain 2 plaid violet bags.\n" +
				"plaid lime bags contain 1 light red bag, 1 light tan bag.\n" +
				"drab green bags contain 1 vibrant lavender bag, 2 drab violet bags.\n" +
				"dull red bags contain 4 striped bronze bags, 3 light blue bags.\n" +
				"dim maroon bags contain 3 muted salmon bags, 1 pale plum bag, 2 mirrored coral bags.\n" +
				"drab orange bags contain 3 clear purple bags, 4 wavy yellow bags.\n" +
				"dark cyan bags contain 5 mirrored purple bags, 3 dull cyan bags, 5 light olive bags.\n" +
				"vibrant plum bags contain 5 posh maroon bags.\n" +
				"plaid gold bags contain 2 pale violet bags.\n" +
				"shiny blue bags contain 1 bright salmon bag, 3 dull chartreuse bags, 2 dark salmon bags, 3 dark beige bags.\n" +
				"muted blue bags contain 1 bright purple bag.\n" +
				"dark gray bags contain 1 dull lavender bag, 2 shiny maroon bags, 1 posh beige bag, 5 muted tomato bags.\n" +
				"dull blue bags contain no other bags.\n" +
				"dim bronze bags contain 5 vibrant gray bags, 4 bright indigo bags, 3 dark bronze bags.\n" +
				"striped orange bags contain 2 light crimson bags, 5 vibrant green bags, 5 muted cyan bags, 3 posh brown bags.\n" +
				"bright fuchsia bags contain 2 striped yellow bags, 4 dotted violet bags, 3 dull aqua bags.\n" +
				"shiny coral bags contain 3 drab magenta bags, 2 bright black bags.\n" +
				"dotted violet bags contain 5 shiny green bags.\n" +
				"pale bronze bags contain 3 muted green bags, 4 wavy indigo bags, 4 dim red bags, 4 posh teal bags.\n" +
				"pale fuchsia bags contain 4 drab gray bags, 5 wavy red bags, 4 dark olive bags, 2 pale bronze bags.\n" +
				"pale tan bags contain 1 bright green bag, 2 clear green bags, 4 pale coral bags, 4 dim orange bags.\n" +
				"wavy indigo bags contain 2 wavy tan bags, 1 pale magenta bag.\n" +
				"dotted chartreuse bags contain no other bags.\n" +
				"bright aqua bags contain 4 vibrant beige bags, 5 clear green bags.\n" +
				"dark turquoise bags contain 5 wavy bronze bags, 3 mirrored chartreuse bags.\n" +
				"light crimson bags contain 3 faded magenta bags.\n" +
				"striped silver bags contain 3 faded magenta bags, 3 dim tomato bags.\n" +
				"dark red bags contain 2 dark chartreuse bags.\n" +
				"striped green bags contain 2 wavy black bags, 4 dotted violet bags, 4 dull lavender bags, 5 light tan bags.\n" +
				"muted salmon bags contain 1 faded lavender bag.\n" +
				"dark magenta bags contain 4 striped orange bags, 5 shiny salmon bags.\n" +
				"striped fuchsia bags contain 5 striped purple bags, 2 posh gray bags, 1 striped white bag.\n" +
				"pale red bags contain 1 clear maroon bag.\n" +
				"light black bags contain 3 posh maroon bags.\n" +
				"clear yellow bags contain 4 clear coral bags.\n" +
				"bright coral bags contain 4 mirrored brown bags, 3 pale cyan bags.\n" +
				"wavy turquoise bags contain 1 striped salmon bag, 1 vibrant tan bag, 4 dim blue bags.\n" +
				"muted teal bags contain 3 drab beige bags, 5 dark teal bags.\n" +
				"vibrant blue bags contain 1 vibrant olive bag, 1 clear coral bag, 3 dull aqua bags, 3 mirrored lavender bags.\n" +
				"vibrant bronze bags contain 4 striped lime bags, 1 posh fuchsia bag, 1 dotted aqua bag, 5 clear orange bags.\n" +
				"vibrant tan bags contain 1 vibrant violet bag.\n" +
				"dark tan bags contain 4 dotted white bags.\n" +
				"drab cyan bags contain 2 muted black bags, 1 clear cyan bag, 3 vibrant black bags.\n" +
				"posh turquoise bags contain 3 light tan bags.\n" +
				"mirrored olive bags contain 4 wavy gold bags.\n" +
				"bright red bags contain 2 shiny tomato bags.\n" +
				"pale yellow bags contain 3 striped lime bags, 1 vibrant tan bag, 3 muted white bags.\n" +
				"clear maroon bags contain 3 mirrored salmon bags, 5 vibrant black bags, 4 mirrored white bags.\n" +
				"posh plum bags contain 1 posh cyan bag, 5 dotted coral bags.\n" +
				"faded gray bags contain 1 mirrored tan bag, 3 clear purple bags, 4 clear coral bags, 2 plaid lavender bags.\n" +
				"clear chartreuse bags contain 2 plaid purple bags, 2 bright indigo bags, 1 light olive bag.\n" +
				"shiny gray bags contain 5 shiny brown bags.\n" +
				"faded tomato bags contain 2 dim aqua bags, 2 faded yellow bags.\n" +
				"wavy green bags contain 4 dim bronze bags, 3 drab tomato bags, 2 muted bronze bags.\n" +
				"muted indigo bags contain 2 wavy turquoise bags.\n" +
				"plaid teal bags contain 3 plaid bronze bags, 2 dotted red bags.\n" +
				"muted olive bags contain 2 dotted coral bags, 5 dull olive bags, 1 posh white bag, 5 light cyan bags.\n" +
				"striped aqua bags contain 2 dull lime bags, 3 mirrored violet bags, 4 shiny turquoise bags.\n" +
				"mirrored lime bags contain 1 clear gray bag, 4 shiny crimson bags, 1 wavy turquoise bag, 4 striped salmon bags.\n" +
				"muted black bags contain 2 dim teal bags.\n" +
				"shiny lavender bags contain 2 shiny green bags, 5 bright beige bags.\n" +
				"muted lavender bags contain 5 pale green bags, 5 vibrant beige bags, 1 vibrant brown bag, 5 clear crimson bags.\n" +
				"dull gray bags contain 5 plaid bronze bags.\n" +
				"plaid coral bags contain 5 vibrant blue bags, 4 clear lavender bags, 4 drab black bags.\n" +
				"dim magenta bags contain 4 pale coral bags, 4 vibrant blue bags, 1 vibrant lavender bag.\n" +
				"muted tomato bags contain 5 dark indigo bags, 3 faded olive bags, 3 clear yellow bags, 4 shiny bronze bags.\n" +
				"dull white bags contain 1 wavy tan bag, 1 faded olive bag, 1 pale purple bag.\n" +
				"bright olive bags contain 2 posh purple bags, 1 plaid lime bag.\n" +
				"dark lavender bags contain 3 striped turquoise bags, 5 posh brown bags, 2 muted lime bags, 4 clear brown bags.\n" +
				"dotted tomato bags contain 4 shiny bronze bags, 5 drab lime bags, 4 posh maroon bags, 5 light bronze bags.\n" +
				"dim violet bags contain 2 plaid plum bags.\n" +
				"vibrant coral bags contain 5 pale plum bags.\n" +
				"muted brown bags contain 4 dim yellow bags, 3 vibrant crimson bags, 5 shiny coral bags, 5 shiny gold bags.\n" +
				"shiny silver bags contain 5 striped chartreuse bags, 5 plaid brown bags.\n" +
				"faded black bags contain 3 dim chartreuse bags, 2 wavy turquoise bags, 5 wavy maroon bags, 1 bright yellow bag.\n" +
				"bright beige bags contain 1 striped salmon bag, 4 shiny violet bags, 1 bright magenta bag.\n" +
				"vibrant lime bags contain 4 posh violet bags.\n" +
				"posh olive bags contain 1 shiny gold bag, 3 shiny indigo bags, 5 posh beige bags, 1 clear silver bag.\n" +
				"mirrored blue bags contain 5 clear brown bags, 4 drab plum bags, 3 posh beige bags, 3 muted blue bags.\n" +
				"light tan bags contain no other bags.\n" +
				"posh lime bags contain 1 pale white bag.\n" +
				"dim teal bags contain 5 pale beige bags, 1 faded lavender bag.\n" +
				"pale maroon bags contain 4 mirrored green bags.\n" +
				"faded red bags contain 2 shiny green bags, 2 faded magenta bags, 4 mirrored cyan bags, 2 clear beige bags.\n" +
				"bright green bags contain 4 posh teal bags, 3 dark gray bags, 3 dark bronze bags, 5 posh silver bags.\n" +
				"dark brown bags contain 2 dull lavender bags, 4 faded lavender bags, 5 vibrant lavender bags, 4 plaid violet bags.\n" +
				"shiny crimson bags contain 2 plaid gray bags, 3 dark green bags.\n" +
				"clear aqua bags contain 4 clear silver bags, 5 vibrant cyan bags, 2 wavy plum bags.\n" +
				"drab fuchsia bags contain 3 clear beige bags, 5 pale aqua bags.\n" +
				"dim lavender bags contain 5 posh bronze bags, 3 wavy indigo bags, 4 drab indigo bags.\n" +
				"posh red bags contain 3 striped chartreuse bags, 3 mirrored coral bags, 2 striped turquoise bags, 2 dull magenta bags.\n" +
				"mirrored turquoise bags contain no other bags.\n" +
				"vibrant violet bags contain 1 muted red bag.\n" +
				"bright blue bags contain 2 faded chartreuse bags.\n" +
				"dotted crimson bags contain 4 wavy gold bags, 3 shiny indigo bags, 3 dark turquoise bags.\n" +
				"faded teal bags contain 1 clear lavender bag, 1 clear cyan bag.\n" +
				"drab bronze bags contain 2 muted lime bags, 1 vibrant blue bag.\n" +
				"clear magenta bags contain 5 dull turquoise bags.\n" +
				"pale magenta bags contain 2 muted salmon bags, 3 shiny coral bags, 3 posh gray bags, 3 vibrant violet bags.\n" +
				"pale silver bags contain 3 clear white bags, 3 bright black bags, 2 vibrant maroon bags, 1 vibrant violet bag.\n" +
				"dull teal bags contain 2 dull gold bags, 4 dotted chartreuse bags, 3 dim orange bags.\n" +
				"plaid beige bags contain 5 vibrant turquoise bags, 2 wavy teal bags, 1 light tan bag, 2 wavy cyan bags.\n" +
				"wavy maroon bags contain 3 pale crimson bags, 4 drab tomato bags, 2 drab magenta bags, 3 dull blue bags.\n" +
				"clear green bags contain 3 wavy purple bags, 2 shiny maroon bags, 1 dull tan bag.\n" +
				"clear white bags contain 1 dim yellow bag, 3 dim chartreuse bags, 3 clear tan bags, 2 dotted lavender bags.\n" +
				"dark plum bags contain 5 pale cyan bags.\n" +
				"vibrant magenta bags contain 1 clear coral bag, 3 dull violet bags, 3 striped orange bags.\n" +
				"muted red bags contain no other bags.\n" +
				"light coral bags contain 5 light green bags.\n" +
				"dim gray bags contain 2 striped chartreuse bags.\n" +
				"dull bronze bags contain 3 dull olive bags, 2 drab plum bags.\n" +
				"dotted aqua bags contain 1 clear teal bag, 4 dull lime bags, 1 dark green bag, 2 clear yellow bags.\n" +
				"plaid tomato bags contain 1 shiny turquoise bag, 2 clear tan bags, 2 faded salmon bags, 4 muted lime bags.\n" +
				"drab salmon bags contain 5 drab magenta bags.\n" +
				"muted silver bags contain 3 drab magenta bags, 2 muted black bags, 1 clear tan bag.\n" +
				"shiny fuchsia bags contain 2 vibrant yellow bags, 1 dark bronze bag, 2 posh white bags, 2 dim yellow bags.\n" +
				"clear fuchsia bags contain 3 bright violet bags, 4 wavy beige bags, 4 pale black bags, 4 drab red bags.\n" +
				"shiny indigo bags contain 2 mirrored turquoise bags, 4 posh coral bags, 4 vibrant red bags.\n" +
				"posh bronze bags contain 2 drab magenta bags, 5 dim yellow bags, 4 dotted violet bags, 5 vibrant violet bags.\n" +
				"light bronze bags contain 5 posh green bags, 5 bright purple bags, 5 dark brown bags, 1 dim orange bag.\n" +
				"dotted lavender bags contain 2 vibrant yellow bags, 4 vibrant turquoise bags, 1 muted chartreuse bag, 5 faded chartreuse bags.\n" +
				"light gray bags contain 2 pale magenta bags, 3 mirrored turquoise bags, 4 posh purple bags.\n" +
				"clear turquoise bags contain 2 striped yellow bags, 5 light olive bags, 5 muted salmon bags, 1 faded magenta bag.\n" +
				"plaid red bags contain 2 drab tomato bags.\n" +
				"shiny turquoise bags contain 2 faded lavender bags, 2 plaid silver bags, 3 plaid violet bags, 2 muted gray bags.\n" +
				"shiny green bags contain no other bags.\n" +
				"bright gold bags contain 5 plaid tomato bags, 2 striped maroon bags, 3 dark salmon bags.\n" +
				"clear red bags contain 1 wavy yellow bag, 4 drab tomato bags, 1 dim yellow bag.\n" +
				"faded gold bags contain 4 plaid black bags, 3 plaid indigo bags.\n" +
				"wavy lime bags contain 4 dotted bronze bags.\n" +
				"dark orange bags contain 5 drab bronze bags, 2 drab magenta bags, 5 shiny green bags.\n" +
				"clear brown bags contain 5 mirrored lavender bags, 2 dark chartreuse bags.\n" +
				"pale chartreuse bags contain 4 dotted gray bags, 1 pale purple bag, 5 muted silver bags.\n" +
				"mirrored coral bags contain 3 wavy purple bags, 3 dull blue bags, 2 drab magenta bags, 4 dotted chartreuse bags.\n" +
				"drab olive bags contain 1 posh green bag.\n" +
				"dim purple bags contain 5 dotted aqua bags, 3 pale gray bags.\n" +
				"faded olive bags contain 4 vibrant blue bags, 3 vibrant olive bags, 3 clear coral bags, 5 light tan bags.\n" +
				"wavy purple bags contain 3 wavy black bags, 5 dark indigo bags, 4 dotted violet bags.\n" +
				"vibrant olive bags contain no other bags.\n" +
				"dull indigo bags contain 2 posh beige bags.\n" +
				"dark purple bags contain 2 bright coral bags, 2 faded lavender bags.\n" +
				"pale violet bags contain 1 posh bronze bag, 1 light beige bag, 1 dim teal bag.\n" +
				"muted orange bags contain 3 shiny cyan bags, 4 pale plum bags.\n" +
				"dotted gray bags contain 5 dark indigo bags.\n" +
				"vibrant teal bags contain 4 vibrant cyan bags, 2 mirrored gray bags, 5 striped salmon bags, 2 vibrant orange bags.\n" +
				"light red bags contain 4 dull lavender bags, 4 wavy purple bags, 1 mirrored coral bag, 4 muted crimson bags.\n" +
				"shiny chartreuse bags contain 3 dull olive bags.\n" +
				"mirrored beige bags contain 4 wavy tan bags, 4 faded tan bags.\n" +
				"light indigo bags contain 4 posh beige bags, 4 dull lavender bags.\n" +
				"faded plum bags contain 2 dotted lime bags, 3 dotted chartreuse bags.\n" +
				"dim chartreuse bags contain 2 vibrant plum bags, 4 dark indigo bags.\n" +
				"bright white bags contain 1 pale cyan bag, 2 dim yellow bags, 3 dark aqua bags, 2 plaid tan bags.\n" +
				"muted turquoise bags contain 3 bright silver bags, 2 dotted yellow bags, 1 clear tan bag.\n" +
				"dull purple bags contain 5 posh beige bags, 5 dim lavender bags, 5 vibrant violet bags, 5 bright lime bags.\n" +
				"muted cyan bags contain 4 vibrant violet bags, 5 muted salmon bags, 4 clear tan bags.\n" +
				"dim blue bags contain 3 muted salmon bags.\n" +
				"wavy cyan bags contain 1 vibrant red bag, 2 dim tomato bags.\n" +
				"shiny red bags contain 4 striped white bags, 1 light cyan bag, 2 drab blue bags.\n" +
				"bright crimson bags contain 5 striped turquoise bags.\n" +
				"shiny magenta bags contain 2 dull turquoise bags, 2 wavy yellow bags.\n" +
				"dotted orange bags contain 4 posh tan bags, 2 clear plum bags.\n" +
				"dotted brown bags contain 5 light magenta bags, 5 shiny lavender bags.\n" +
				"bright tan bags contain 5 shiny gold bags, 5 vibrant gray bags, 2 mirrored coral bags.\n" +
				"dim olive bags contain 1 bright black bag.\n" +
				"striped yellow bags contain 5 dark indigo bags.\n" +
				"dull orange bags contain 2 clear chartreuse bags, 1 wavy white bag, 5 dim coral bags.\n" +
				"faded white bags contain 1 posh teal bag.\n" +
				"pale gold bags contain 2 clear blue bags.\n" +
				"faded orange bags contain 3 shiny blue bags, 5 shiny turquoise bags, 2 posh turquoise bags.\n" +
				"drab brown bags contain 3 plaid olive bags, 5 plaid cyan bags, 4 pale coral bags.\n" +
				"light white bags contain 5 mirrored gray bags, 2 dim chartreuse bags, 1 posh lavender bag, 5 vibrant crimson bags.\n" +
				"drab red bags contain 5 bright purple bags, 1 wavy blue bag, 4 shiny magenta bags.\n" +
				"muted coral bags contain 2 dim tomato bags, 1 vibrant crimson bag, 5 faded magenta bags, 5 clear teal bags.\n" +
				"plaid maroon bags contain 5 striped tomato bags.\n" +
				"drab black bags contain 2 pale indigo bags, 1 muted lime bag.\n" +
				"pale green bags contain 3 vibrant white bags.\n" +
				"wavy gray bags contain 3 pale cyan bags, 2 vibrant lavender bags.\n" +
				"clear gold bags contain 3 muted plum bags, 4 clear cyan bags, 3 dull plum bags, 3 pale aqua bags.\n" +
				"pale gray bags contain 5 vibrant white bags, 1 plaid lavender bag.\n" +
				"drab plum bags contain 1 pale green bag, 1 wavy purple bag, 3 muted tan bags, 2 drab magenta bags.\n" +
				"dotted fuchsia bags contain 5 clear beige bags, 3 shiny blue bags, 5 clear red bags, 3 plaid olive bags.\n" +
				"bright plum bags contain 4 muted black bags.\n" +
				"posh orange bags contain 1 dotted black bag, 3 dotted cyan bags.\n" +
				"dim fuchsia bags contain 1 drab purple bag.\n" +
				"dim plum bags contain 4 vibrant gray bags, 4 plaid blue bags, 5 posh black bags, 5 dim cyan bags.\n" +
				"muted purple bags contain 5 shiny purple bags, 2 dim magenta bags.\n" +
				"wavy olive bags contain 5 plaid bronze bags, 2 wavy purple bags, 2 dark aqua bags.\n" +
				"pale plum bags contain 5 posh purple bags.\n" +
				"dull brown bags contain 4 dim blue bags, 5 dull gray bags.\n" +
				"dark blue bags contain 1 drab brown bag, 1 vibrant maroon bag, 5 dotted blue bags, 3 dim teal bags.\n" +
				"striped bronze bags contain 2 clear salmon bags, 1 posh bronze bag, 3 pale turquoise bags, 5 shiny green bags.\n" +
				"drab maroon bags contain 4 pale aqua bags.\n" +
				"pale lavender bags contain 3 dark green bags, 1 clear violet bag, 3 striped turquoise bags.\n" +
				"light blue bags contain 5 muted brown bags, 5 plaid indigo bags, 3 wavy purple bags, 3 dark chartreuse bags.\n" +
				"light violet bags contain 4 dark turquoise bags.\n" +
				"pale purple bags contain 1 dim red bag, 2 clear cyan bags, 3 posh lavender bags, 5 muted tomato bags.\n" +
				"shiny violet bags contain 4 dull blue bags, 1 light gold bag, 4 shiny gold bags, 5 posh teal bags.\n" +
				"shiny bronze bags contain 1 light tan bag, 2 faded fuchsia bags, 3 dull aqua bags, 2 clear coral bags.\n" +
				"dotted yellow bags contain 1 clear coral bag, 2 light magenta bags, 4 striped tomato bags.\n" +
				"dim yellow bags contain 5 muted gray bags, 3 mirrored lavender bags.\n" +
				"dim white bags contain 2 muted tan bags, 2 muted black bags, 4 pale beige bags, 1 dull bronze bag.\n" +
				"clear tomato bags contain 3 clear teal bags.\n" +
				"clear crimson bags contain 1 dotted chartreuse bag.\n" +
				"striped red bags contain 1 muted beige bag, 3 bright gray bags, 1 vibrant gray bag, 3 light coral bags.\n" +
				"muted plum bags contain 2 light gold bags, 1 striped turquoise bag.\n" +
				"posh white bags contain 3 dull olive bags.\n" +
				"dim salmon bags contain 2 faded fuchsia bags, 2 dim cyan bags.\n" +
				"muted magenta bags contain 2 posh cyan bags, 2 clear lavender bags, 3 wavy plum bags.\n" +
				"dark aqua bags contain 4 clear tan bags, 4 shiny purple bags.\n" +
				"dim black bags contain 1 dim orange bag, 3 plaid tan bags, 2 dotted purple bags, 1 dim teal bag.\n" +
				"vibrant indigo bags contain 4 shiny violet bags, 5 light blue bags, 1 faded purple bag.\n" +
				"plaid black bags contain 3 muted cyan bags.\n" +
				"striped black bags contain 5 vibrant violet bags, 4 muted tan bags.\n" +
				"dotted turquoise bags contain 2 clear orange bags, 5 muted plum bags.\n" +
				"wavy fuchsia bags contain 1 dull gold bag.\n" +
				"wavy black bags contain 3 dotted chartreuse bags, 3 clear coral bags.\n" +
				"clear silver bags contain 5 vibrant beige bags, 5 plaid bronze bags, 5 vibrant brown bags.\n" +
				"wavy crimson bags contain 1 plaid turquoise bag.\n" +
				"clear orange bags contain 5 posh teal bags, 5 bright green bags.\n" +
				"light silver bags contain 2 plaid tomato bags, 1 plaid plum bag, 5 dark yellow bags.\n" +
				"pale black bags contain 3 shiny lavender bags, 5 shiny olive bags, 4 light plum bags, 5 drab gray bags.\n" +
				"striped coral bags contain 1 posh gray bag, 3 clear crimson bags, 3 dotted violet bags, 5 shiny plum bags.\n" +
				"clear violet bags contain 2 dull magenta bags.\n" +
				"clear bronze bags contain 2 vibrant indigo bags, 3 drab plum bags, 3 striped lime bags, 2 striped olive bags.\n" +
				"vibrant purple bags contain 1 clear green bag.\n" +
				"plaid yellow bags contain 4 vibrant white bags.\n" +
				"faded aqua bags contain 2 clear yellow bags, 1 mirrored lavender bag, 3 light beige bags, 2 mirrored maroon bags.\n" +
				"shiny teal bags contain 1 posh cyan bag, 2 dull plum bags, 5 plaid olive bags, 2 drab teal bags.\n" +
				"light brown bags contain 4 wavy black bags.\n" +
				"bright gray bags contain 5 striped olive bags, 3 clear gray bags.\n" +
				"plaid violet bags contain 3 wavy black bags, 1 clear cyan bag, 5 dotted chartreuse bags.\n" +
				"dark crimson bags contain 1 dotted aqua bag, 2 drab plum bags.\n" +
				"pale beige bags contain 4 wavy black bags, 2 vibrant gray bags, 5 light tan bags, 5 mirrored coral bags.\n" +
				"faded blue bags contain 2 dim blue bags, 2 dotted coral bags.\n" +
				"dotted red bags contain 2 wavy gold bags, 1 dark silver bag, 5 vibrant lavender bags, 5 dim magenta bags.\n" +
				"dotted blue bags contain 3 dark gray bags, 5 muted lime bags, 3 vibrant crimson bags.\n" +
				"plaid aqua bags contain 5 posh brown bags, 2 vibrant gray bags.\n" +
				"bright maroon bags contain 2 dotted aqua bags.\n" +
				"faded coral bags contain 2 muted orange bags, 5 clear lavender bags, 4 faded magenta bags.\n" +
				"dull plum bags contain 4 dim white bags.\n" +
				"clear coral bags contain no other bags.\n" +
				"light purple bags contain 1 striped violet bag.\n" +
				"dotted white bags contain 2 dim chartreuse bags, 1 wavy blue bag, 1 dull white bag, 1 vibrant indigo bag.\n" +
				"faded bronze bags contain 5 pale beige bags, 1 wavy blue bag.\n" +
				"posh tomato bags contain 1 dotted salmon bag, 2 pale brown bags.\n" +
				"faded fuchsia bags contain 3 vibrant olive bags, 1 dull blue bag, 5 dull aqua bags.\n" +
				"muted fuchsia bags contain 5 dotted yellow bags, 4 plaid tan bags, 4 pale crimson bags.\n" +
				"shiny brown bags contain 3 posh turquoise bags, 5 wavy yellow bags.\n" +
				"clear gray bags contain 1 faded olive bag.\n" +
				"pale indigo bags contain 3 dull lavender bags, 2 vibrant plum bags.\n" +
				"plaid purple bags contain 4 muted lime bags, 5 dotted chartreuse bags, 2 dotted violet bags, 5 light indigo bags.\n" +
				"pale coral bags contain 4 muted crimson bags, 3 vibrant black bags, 4 clear tan bags.\n" +
				"dotted salmon bags contain 1 vibrant red bag.\n" +
				"muted gray bags contain 3 mirrored turquoise bags.\n" +
				"dotted indigo bags contain 1 posh yellow bag, 5 dotted blue bags, 2 faded blue bags.\n" +
				"mirrored orange bags contain 2 posh beige bags, 2 posh chartreuse bags.\n" +
				"dim tomato bags contain 5 posh chartreuse bags, 3 striped yellow bags, 3 muted tomato bags.\n" +
				"drab beige bags contain 1 vibrant white bag.\n" +
				"dull olive bags contain 4 mirrored fuchsia bags, 3 faded lavender bags.\n" +
				"shiny tan bags contain 2 clear yellow bags, 3 wavy aqua bags, 1 pale plum bag.\n" +
				"faded green bags contain 1 dotted salmon bag.\n" +
				"shiny black bags contain 5 dotted lavender bags.\n" +
				"dull yellow bags contain 3 posh beige bags, 1 pale lavender bag, 3 wavy tan bags, 3 dark lavender bags.\n" +
				"dull coral bags contain 2 dark chartreuse bags, 1 striped tomato bag.\n" +
				"striped plum bags contain 2 dark maroon bags.\n" +
				"mirrored brown bags contain 2 pale crimson bags, 1 dim tomato bag, 1 bright black bag.\n" +
				"clear purple bags contain 4 clear tan bags, 4 light cyan bags.\n" +
				"muted chartreuse bags contain 2 pale crimson bags, 4 bright purple bags.\n" +
				"clear olive bags contain 4 muted silver bags.\n" +
				"plaid olive bags contain 5 vibrant olive bags, 2 dark gray bags, 2 pale cyan bags.\n" +
				"bright teal bags contain 1 clear gold bag.\n" +
				"dim coral bags contain 2 shiny turquoise bags, 4 mirrored white bags, 3 striped white bags.\n" +
				"dotted teal bags contain 4 light plum bags.\n" +
				"bright brown bags contain 3 wavy teal bags, 3 light beige bags, 4 mirrored indigo bags, 5 plaid lime bags.\n" +
				"mirrored gold bags contain 4 shiny magenta bags, 1 vibrant magenta bag.\n" +
				"dotted plum bags contain 5 bright orange bags, 4 light black bags, 2 shiny brown bags.\n" +
				"vibrant silver bags contain 2 dim chartreuse bags, 1 plaid brown bag, 4 bright chartreuse bags.\n" +
				"dark olive bags contain 4 clear beige bags, 5 dotted violet bags, 3 dim white bags, 1 pale crimson bag.\n" +
				"dark coral bags contain 4 light salmon bags, 1 shiny coral bag, 3 faded tomato bags.\n" +
				"faded silver bags contain 2 shiny magenta bags.\n" +
				"shiny lime bags contain 2 pale plum bags, 4 dim maroon bags, 2 faded lime bags.\n" +
				"plaid brown bags contain 4 vibrant blue bags, 2 vibrant white bags, 5 plaid gray bags.\n" +
				"mirrored maroon bags contain 3 dotted green bags.\n" +
				"vibrant beige bags contain 5 pale beige bags, 1 mirrored white bag, 4 mirrored lavender bags.\n" +
				"faded brown bags contain 3 drab indigo bags, 1 posh yellow bag, 3 clear yellow bags.\n" +
				"drab aqua bags contain 4 vibrant brown bags, 5 pale salmon bags, 1 vibrant violet bag, 2 dotted aqua bags.\n" +
				"dim green bags contain 1 light orange bag, 1 mirrored orange bag.\n" +
				"dull maroon bags contain 4 muted blue bags, 5 posh red bags.\n" +
				"light olive bags contain 2 vibrant violet bags.\n" +
				"vibrant gold bags contain 2 posh purple bags, 3 dotted black bags, 1 muted maroon bag.\n" +
				"faded violet bags contain 2 clear white bags.\n" +
				"bright bronze bags contain 2 striped bronze bags, 3 bright black bags, 3 vibrant plum bags.\n" +
				"dark fuchsia bags contain 2 dull coral bags, 1 shiny brown bag.\n" +
				"dark teal bags contain 2 clear yellow bags, 2 posh tan bags, 3 plaid indigo bags, 3 faded salmon bags.\n" +
				"striped teal bags contain 4 striped brown bags.\n" +
				"faded turquoise bags contain 2 shiny olive bags.\n" +
				"dim beige bags contain 1 shiny crimson bag, 1 clear green bag, 3 pale indigo bags.\n" +
				"shiny white bags contain 2 dull blue bags, 2 light aqua bags.\n" +
				"wavy beige bags contain 2 dark silver bags, 4 muted gray bags.\n" +
				"light turquoise bags contain 4 dim teal bags, 2 striped olive bags.\n" +
				"bright lavender bags contain 1 light lime bag, 2 faded blue bags, 4 dotted silver bags, 3 vibrant gold bags.\n" +
				"pale tomato bags contain 1 striped olive bag.\n" +
				"clear tan bags contain 5 plaid salmon bags, 5 posh maroon bags, 2 pale crimson bags.\n" +
				"drab gray bags contain 4 posh bronze bags, 1 muted red bag.\n" +
				"striped white bags contain 5 posh turquoise bags, 2 posh maroon bags, 3 vibrant black bags.\n" +
				"drab tan bags contain 2 drab olive bags, 4 bright black bags, 2 shiny coral bags.\n" +
				"dull lime bags contain 5 dim silver bags, 1 muted orange bag, 4 dark indigo bags, 2 posh coral bags.\n" +
				"dull gold bags contain 2 dim maroon bags.\n" +
				"shiny beige bags contain 5 dull purple bags, 4 striped chartreuse bags.\n" +
				"faded cyan bags contain 2 clear tan bags, 4 shiny coral bags, 1 drab olive bag, 3 wavy gold bags.\n" +
				"dim brown bags contain 5 pale coral bags, 5 posh turquoise bags, 4 striped lime bags.\n" +
				"drab teal bags contain 2 shiny tomato bags, 4 drab tomato bags.\n" +
				"muted tan bags contain 3 clear gray bags.\n" +
				"shiny olive bags contain 3 dull white bags, 3 faded chartreuse bags.\n" +
				"bright cyan bags contain 5 pale tan bags, 3 mirrored plum bags, 1 vibrant green bag.\n" +
				"striped salmon bags contain 1 plaid indigo bag, 1 dark chartreuse bag.\n" +
				"pale lime bags contain 3 pale yellow bags, 1 muted olive bag, 4 dull indigo bags, 2 light magenta bags.\n" +
				"muted bronze bags contain 5 dark olive bags, 2 faded lime bags.\n" +
				"drab violet bags contain 3 shiny cyan bags, 4 faded gray bags.\n" +
				"light lime bags contain 2 pale crimson bags, 5 mirrored red bags.\n" +
				"dark salmon bags contain 5 dim yellow bags, 3 mirrored red bags, 4 light gold bags.\n" +
				"posh gray bags contain 4 muted tomato bags, 1 bright black bag, 1 mirrored turquoise bag, 1 faded lavender bag.\n" +
				"dull black bags contain 4 drab olive bags, 2 vibrant fuchsia bags, 3 mirrored maroon bags.\n" +
				"wavy yellow bags contain 2 vibrant olive bags.\n" +
				"light aqua bags contain 2 shiny violet bags.\n" +
				"faded salmon bags contain 3 faded olive bags, 2 pale crimson bags.\n" +
				"dull green bags contain 3 bright orange bags.\n" +
				"mirrored white bags contain 3 clear black bags.\n" +
				"drab silver bags contain 3 dull magenta bags.\n" +
				"faded maroon bags contain 1 mirrored chartreuse bag, 2 drab white bags, 5 striped purple bags.\n" +
				"dark yellow bags contain 4 dotted fuchsia bags.\n" +
				"posh maroon bags contain 4 faded fuchsia bags, 3 vibrant olive bags, 5 dull aqua bags.\n" +
				"vibrant orange bags contain 3 muted gray bags, 1 striped gray bag.\n" +
				"plaid blue bags contain 3 dark purple bags, 3 muted white bags, 4 drab lavender bags.\n" +
				"mirrored salmon bags contain 1 light magenta bag.\n" +
				"plaid fuchsia bags contain 2 plaid brown bags.\n" +
				"wavy orange bags contain 1 dim brown bag, 5 dotted aqua bags, 2 striped black bags.\n" +
				"mirrored cyan bags contain 3 light chartreuse bags, 2 dotted maroon bags, 5 muted red bags, 4 light tan bags.\n" +
				"mirrored red bags contain 2 wavy turquoise bags, 4 striped tomato bags, 5 light red bags, 4 posh purple bags.\n" +
				"shiny orange bags contain 2 plaid gray bags, 1 drab beige bag, 2 plaid red bags.\n" +
				"vibrant white bags contain 5 light tan bags, 3 wavy black bags, 2 mirrored lavender bags.\n" +
				"drab blue bags contain 1 dark purple bag.\n" +
				"dark gold bags contain 1 dim white bag, 5 posh teal bags.\n" +
				"mirrored gray bags contain 1 bright olive bag.\n" +
				"plaid white bags contain 4 striped orange bags, 2 muted beige bags, 2 vibrant fuchsia bags, 4 plaid salmon bags.\n" +
				"muted yellow bags contain 3 striped bronze bags, 4 dull bronze bags, 1 dim crimson bag, 5 posh crimson bags.\n" +
				"shiny yellow bags contain 2 vibrant indigo bags.\n" +
				"mirrored teal bags contain 5 pale crimson bags.\n" +
				"muted white bags contain 5 shiny lavender bags, 4 dotted yellow bags, 5 dotted gray bags.\n" +
				"posh green bags contain 2 mirrored fuchsia bags.\n" +
				"plaid salmon bags contain 2 dull blue bags, 2 vibrant crimson bags, 1 drab magenta bag, 5 plaid silver bags.\n" +
				"shiny tomato bags contain 5 vibrant plum bags, 5 light blue bags, 5 striped magenta bags.\n" +
				"drab magenta bags contain 4 clear yellow bags.\n" +
				"dim crimson bags contain 1 dark beige bag, 2 posh tomato bags.\n" +
				"faded tan bags contain 4 posh fuchsia bags.\n" +
				"dotted olive bags contain 4 striped turquoise bags, 1 muted lavender bag, 5 dotted aqua bags, 1 wavy purple bag.\n" +
				"dull magenta bags contain 4 vibrant indigo bags, 4 mirrored salmon bags, 2 pale cyan bags, 3 muted salmon bags.\n" +
				"muted violet bags contain 2 faded olive bags, 2 posh green bags.\n" +
				"wavy tan bags contain 1 faded fuchsia bag, 4 plaid lime bags, 1 pale magenta bag, 3 dim yellow bags.\n" +
				"vibrant yellow bags contain 1 posh gray bag, 4 mirrored lavender bags.\n" +
				"dull lavender bags contain 3 light chartreuse bags.\n" +
				"posh blue bags contain 5 dim aqua bags, 2 faded crimson bags, 5 wavy yellow bags.\n" +
				"plaid cyan bags contain 4 dark green bags, 2 vibrant cyan bags, 2 clear chartreuse bags, 2 clear blue bags.\n" +
				"muted aqua bags contain 3 bright black bags, 2 striped brown bags, 4 muted brown bags, 5 drab chartreuse bags.\n" +
				"plaid turquoise bags contain 4 faded salmon bags.\n" +
				"shiny cyan bags contain 5 muted lime bags, 1 dim tomato bag.\n" +
				"mirrored indigo bags contain 2 dotted yellow bags, 4 light gray bags.\n" +
				"dull violet bags contain 4 light beige bags.\n" +
				"plaid gray bags contain 3 vibrant lavender bags, 3 light chartreuse bags, 3 vibrant black bags.\n" +
				"dim aqua bags contain 1 posh coral bag.\n" +
				"bright purple bags contain 4 dark chartreuse bags, 2 muted red bags, 4 clear yellow bags.\n" +
				"pale olive bags contain 5 dull blue bags, 5 dotted lavender bags, 1 muted white bag, 5 light maroon bags.\n" +
				"vibrant turquoise bags contain 3 shiny turquoise bags.\n" +
				"faded magenta bags contain 2 posh teal bags, 4 dark indigo bags.\n" +
				"dark chartreuse bags contain 4 posh gray bags, 1 shiny turquoise bag, 5 dotted violet bags, 5 faded olive bags.\n" +
				"mirrored plum bags contain 2 light magenta bags, 1 vibrant tan bag, 2 striped aqua bags.\n" +
				"muted maroon bags contain 1 posh teal bag, 3 posh gray bags.\n" +
				"mirrored lavender bags contain no other bags.\n" +
				"dull crimson bags contain 5 shiny magenta bags.\n" +
				"wavy gold bags contain 5 wavy yellow bags, 2 mirrored coral bags, 4 pale crimson bags, 5 striped brown bags.\n" +
				"striped purple bags contain 1 dim teal bag, 3 dull lime bags, 5 bright magenta bags, 5 clear yellow bags.\n" +
				"muted green bags contain 5 clear tan bags, 5 plaid indigo bags.\n" +
				"posh yellow bags contain 2 dotted yellow bags, 5 faded salmon bags, 5 plaid indigo bags, 5 plaid lime bags.\n" +
				"striped lavender bags contain 5 faded lime bags.\n" +
				"light maroon bags contain 3 vibrant coral bags, 5 dim brown bags.\n" +
				"dim cyan bags contain 4 light chartreuse bags.\n" +
				"posh coral bags contain 3 clear crimson bags.\n" +
				"muted gold bags contain 3 clear beige bags, 2 vibrant olive bags, 1 mirrored gray bag.\n" +
				"light teal bags contain 2 muted brown bags, 5 drab purple bags, 2 wavy tomato bags, 4 dotted crimson bags.\n" +
				"striped olive bags contain 1 muted lime bag, 4 muted gray bags.\n" +
				"clear blue bags contain 3 dull lavender bags.\n" +
				"striped indigo bags contain 3 vibrant purple bags, 2 dim maroon bags, 3 pale lavender bags.\n" +
				"striped gold bags contain 3 dim teal bags.\n" +
				"faded crimson bags contain 4 muted black bags, 1 bright orange bag.\n" +
				"drab chartreuse bags contain 1 bright purple bag, 1 mirrored fuchsia bag.\n" +
				"posh teal bags contain 5 striped green bags, 3 posh purple bags, 1 wavy yellow bag, 5 clear gray bags.\n" +
				"mirrored violet bags contain 2 shiny violet bags, 1 plaid purple bag, 4 light tan bags.\n" +
				"dim red bags contain 2 vibrant crimson bags.\n" +
				"striped blue bags contain 4 dull silver bags.\n" +
				"striped brown bags contain 2 dark chartreuse bags, 2 posh turquoise bags, 2 muted red bags, 1 vibrant tomato bag.\n" +
				"pale blue bags contain 1 pale indigo bag, 4 faded cyan bags, 2 vibrant indigo bags.\n" +
				"plaid tan bags contain 4 dim tomato bags.\n" +
				"dull chartreuse bags contain 4 drab magenta bags.\n" +
				"dim silver bags contain 1 wavy indigo bag, 5 posh lavender bags.\n" +
				"dotted gold bags contain 1 shiny turquoise bag, 2 striped brown bags, 4 striped violet bags, 5 vibrant olive bags.\n" +
				"dark beige bags contain 2 vibrant tan bags, 3 bright aqua bags.\n" +
				"plaid lavender bags contain 2 vibrant violet bags, 4 dull tan bags, 2 light bronze bags.\n" +
				"clear lime bags contain 5 muted aqua bags, 2 muted black bags, 5 plaid maroon bags, 3 vibrant green bags.\n" +
				"pale crimson bags contain 3 wavy black bags.\n" +
				"dotted purple bags contain 5 wavy fuchsia bags, 2 posh lavender bags, 2 striped green bags.\n" +
				"faded beige bags contain 1 vibrant white bag, 2 light beige bags, 4 pale green bags, 4 dotted black bags.\n" +
				"bright orange bags contain 4 dim tomato bags.\n" +
				"light lavender bags contain 1 wavy white bag, 1 plaid red bag.\n" +
				"clear beige bags contain 2 posh lavender bags, 1 dim tomato bag, 3 shiny coral bags.\n" +
				"posh silver bags contain 3 light gray bags, 3 muted chartreuse bags, 3 plaid silver bags, 1 drab salmon bag.\n" +
				"wavy aqua bags contain 1 dull aqua bag.\n" +
				"clear indigo bags contain 2 pale gold bags, 5 dull cyan bags.\n" +
				"dark green bags contain 3 muted tan bags, 4 striped coral bags.\n" +
				"wavy bronze bags contain 1 faded black bag, 3 dark salmon bags, 4 bright magenta bags, 2 dark teal bags.\n" +
				"pale cyan bags contain 1 mirrored lavender bag.\n" +
				"wavy lavender bags contain 3 dotted black bags, 4 pale indigo bags, 3 clear green bags, 4 vibrant plum bags.\n" +
				"bright black bags contain 3 wavy purple bags, 5 pale cyan bags, 4 clear gray bags, 2 dull blue bags.\n" +
				"dotted beige bags contain 1 mirrored violet bag, 2 plaid black bags.\n" +
				"clear black bags contain 4 dark indigo bags, 3 light magenta bags.\n" +
				"dotted cyan bags contain 3 pale magenta bags.\n" +
				"dark tomato bags contain 2 shiny bronze bags, 3 clear bronze bags, 4 wavy purple bags, 2 pale coral bags.\n" +
				"mirrored yellow bags contain 2 dim violet bags, 3 striped tan bags, 2 dull chartreuse bags.\n" +
				"dull cyan bags contain 5 wavy purple bags, 1 mirrored turquoise bag.\n" +
				"posh cyan bags contain 2 vibrant gray bags.\n" +
				"dim lime bags contain 5 muted tomato bags, 5 posh gray bags, 4 pale green bags, 4 striped silver bags.\n" +
				"posh salmon bags contain 2 shiny green bags, 3 dotted black bags, 2 clear bronze bags, 2 muted lavender bags.\n" +
				"shiny aqua bags contain 2 muted salmon bags, 5 plaid indigo bags, 5 posh teal bags.\n" +
				"posh aqua bags contain 4 dim lime bags, 5 striped purple bags.\n" +
				"dull turquoise bags contain 1 dotted coral bag, 4 bright indigo bags, 2 shiny tan bags.\n" +
				"light beige bags contain 5 shiny gold bags, 1 clear yellow bag, 3 striped yellow bags, 4 muted salmon bags.\n" +
				"drab purple bags contain 1 clear brown bag.\n" +
				"mirrored purple bags contain 3 drab cyan bags, 2 light plum bags.\n" +
				"plaid silver bags contain 1 clear coral bag.\n" +
				"mirrored bronze bags contain 3 pale crimson bags, 2 dark brown bags, 1 vibrant tan bag.\n" +
				"pale orange bags contain 4 striped gray bags.\n" +
				"mirrored black bags contain 2 clear crimson bags.\n" +
				"mirrored tomato bags contain 5 light turquoise bags, 1 faded gold bag.\n" +
				"posh magenta bags contain 5 dotted silver bags.\n" +
				"posh chartreuse bags contain 5 dim yellow bags.\n" +
				"dotted black bags contain 3 mirrored turquoise bags.\n" +
				"plaid indigo bags contain 3 mirrored fuchsia bags, 5 wavy yellow bags, 2 plaid silver bags, 3 vibrant blue bags.\n" +
				"drab coral bags contain 4 wavy indigo bags, 3 clear salmon bags, 4 muted teal bags.\n" +
				"plaid orange bags contain 2 dotted plum bags.\n" +
				"light orange bags contain 4 light tomato bags, 4 vibrant blue bags, 3 light coral bags.\n" +
				"striped maroon bags contain 3 dark brown bags, 1 light silver bag.\n" +
				"striped cyan bags contain 4 muted orange bags, 1 light silver bag, 2 pale gold bags, 4 shiny plum bags.\n" +
				"posh violet bags contain 2 dark violet bags, 4 striped olive bags, 1 pale silver bag.\n" +
				"striped lime bags contain 4 dim orange bags.";
	}

	private static class IntegerWrapper {
		int num = 1;
	}
}
