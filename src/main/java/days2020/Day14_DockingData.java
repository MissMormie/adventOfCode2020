package days2020;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day14_DockingData {

	public static void main(String[] args) {
		System.out.println("answer A: " + runA(textInput())); // 2165
		long startTime = System.nanoTime();
		System.out.println("answer B: " + runB(textInput()));
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;

		System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);
	}

	private static Map<String, BitSet> memoryMapA = new HashMap<>();
	private static Map<Long, Long> memoryMapB = new HashMap<>(); // memory adress, value

	public static long runA(String input) {
		Arrays.stream(input.split("mask = "))
				.forEach(s -> handleBlock(s));

		return memoryMapA.values().stream().mapToLong(bitset -> bitset.toLongArray()[0])
				.sum();
	}

	private static List<BitSet> handleBlock(String block) {
		String[] lines = block.split("\n");
		String mask = lines[0];

		return IntStream.range(1, lines.length)
				.mapToObj(i -> getBitSetFromLine(lines[i], mask))
				.collect(Collectors.toList());
	}

	private static BitSet getBitSetFromLine(String line, String mask) {
		// mem[47892] = 675192690
		String[] pieces = line.split("(mem\\[|] = )");
		BitSet bits = getBitSetFromIntString(pieces[2]);
		char[] chars = mask.toCharArray();
		IntStream.range(0, chars.length)
				.forEach(i -> {
					if(chars[i] == 'x') return;
					int bitIndex = mask.length() -1 - i;
					if(chars[i] == '1') bits.set(bitIndex);
					if(chars[i] == '0') bits.clear(bitIndex);
				});

		memoryMapA.put(pieces[1], bits);
		return bits;
	}

	private static BitSet getBitSetFromIntString(String num) {
		return BitSet.valueOf(new long[] { Integer.parseInt(num) });
	}

	private static BitSet applyMaskToLineWithoutFloats(String line, String mask) {
		String[] pieces = line.split("(mem\\[|] = )");
		BitSet bits = getBitSetFromIntString(pieces[1]);
		char[] chars = mask.toCharArray();
		IntStream.range(0, chars.length)
				.forEach(i -> {
					if(chars[i] == 'x' || chars[i] == 0) return;
					int bitIndex = mask.length() -1 - i;
					if(chars[i] == '1') bits.set(bitIndex);
				});
		return bits;
	}

	public static long runB(String input) {
		Arrays.stream(input.split("mask = "))
				.forEach(s -> handleBlockB(s));
		return memoryMapB.values().stream().mapToLong(Long::longValue).sum();

	}

	private static void applyLineToMemory(String line, String mask){
		BitSet bitSet = applyMaskToLineWithoutFloats(line, mask);
		String[] split = line.split(" = ");
		handleFloatingBits(bitSet, mask, Long.parseLong(split[1]));
	}

	private static void handleFloatingBits(BitSet bitSet, String mask, long value) {
		int x = mask.indexOf('X');
		if(x == -1) {
			memoryMapB.put(bitSet.toLongArray()[0], value);
			return;
		}

		String newMask = mask.substring(0, x) + '_' + mask.substring(x + 1);
		// create floating 1
		BitSet clone1 = (BitSet) bitSet.clone();
		clone1.set(mask.length() - x - 1);
		handleFloatingBits(clone1, newMask, value);

		// create floating 0;
		BitSet clone2 = (BitSet) bitSet.clone();
		clone2.clear(mask.length() - x -1);
		handleFloatingBits(clone2, newMask, value);
	}

	private static void handleBlockB(String block) {
		String[] lines = block.split("\n");
		String mask = lines[0];

		IntStream.range(1, lines.length)
				.forEach(i -> applyLineToMemory(lines[i], mask));

	}

	private static String textInput() {
		return "mask = 00111X0X10X0000XX00011111110000011X0\n" +
				"mem[52006] = 4929712\n" +
				"mem[43834] = 524429393\n" +
				"mem[12235] = 5761436\n" +
				"mem[46892] = 35146\n" +
				"mem[31939] = 16563655\n" +
				"mem[59302] = 423\n" +
				"mask = 0110111X1110001X1X10XX1101010011X000\n" +
				"mem[41405] = 37218266\n" +
				"mem[26084] = 35933\n" +
				"mem[56863] = 117475013\n" +
				"mem[62063] = 3066\n" +
				"mask = 0110110110110000X0X00X00X0X011100000\n" +
				"mem[17255] = 1409501\n" +
				"mem[3182] = 922832\n" +
				"mem[612] = 155268\n" +
				"mem[54089] = 238718351\n" +
				"mask = 0011001X0X11100110100011110X001100X0\n" +
				"mem[36228] = 813819599\n" +
				"mem[26530] = 199116285\n" +
				"mem[65466] = 823\n" +
				"mem[21514] = 6079436\n" +
				"mask = X0101000X00X011X010001X01XX110100100\n" +
				"mem[55733] = 400\n" +
				"mem[12132] = 220502402\n" +
				"mem[59992] = 63891518\n" +
				"mem[28423] = 3058\n" +
				"mem[60385] = 721572\n" +
				"mask = 101011011000X110010XX01X001100000010\n" +
				"mem[17718] = 5256\n" +
				"mem[26460] = 112823\n" +
				"mem[5706] = 1814611\n" +
				"mem[63904] = 4222\n" +
				"mem[23015] = 1178098\n" +
				"mem[46616] = 2037\n" +
				"mem[34774] = 229522722\n" +
				"mask = 00101111111X00101010011X01100100X1X0\n" +
				"mem[37506] = 7646\n" +
				"mem[25724] = 7532075\n" +
				"mem[46734] = 1856\n" +
				"mem[56304] = 6237594\n" +
				"mem[17886] = 51040\n" +
				"mem[43844] = 335\n" +
				"mask = 001101011110001XX00001100X00X111110X\n" +
				"mem[28036] = 122944\n" +
				"mem[62839] = 208592302\n" +
				"mem[61962] = 271691652\n" +
				"mask = 0110110X1011X000X00010010XX1101000XX\n" +
				"mem[2761] = 7531352\n" +
				"mem[49376] = 2355483\n" +
				"mem[4216] = 918613\n" +
				"mem[56927] = 5956\n" +
				"mem[63510] = 176827\n" +
				"mask = 0X1011X111100X10101000X0X100X0X1110X\n" +
				"mem[29120] = 2006354\n" +
				"mem[19556] = 3671899\n" +
				"mem[2168] = 5030\n" +
				"mem[1739] = 1419\n" +
				"mem[16584] = 4603711\n" +
				"mem[1274] = 754034\n" +
				"mem[27061] = 284641\n" +
				"mask = 0X111X010011X10011X10010001010100100\n" +
				"mem[38703] = 6183\n" +
				"mem[10881] = 50618\n" +
				"mem[25559] = 4348205\n" +
				"mem[11367] = 105492\n" +
				"mask = 0X1X11X1X01000X010X0000001X0001X01X0\n" +
				"mem[24466] = 281526\n" +
				"mem[2756] = 345582958\n" +
				"mem[27821] = 51329276\n" +
				"mem[3182] = 1004\n" +
				"mem[64312] = 14160\n" +
				"mem[44904] = 25504\n" +
				"mask = 001101X1111000111X0010110XXX00101010\n" +
				"mem[9655] = 8358\n" +
				"mem[58805] = 59610179\n" +
				"mem[4017] = 2556\n" +
				"mem[21076] = 84081646\n" +
				"mem[12544] = 29200337\n" +
				"mem[58825] = 849315134\n" +
				"mask = X0101001101101X0100X0110X0001001110X\n" +
				"mem[52416] = 80677\n" +
				"mem[24809] = 107220\n" +
				"mask = 0X10111X1X10001X101X0000110011X0X11X\n" +
				"mem[30145] = 59849\n" +
				"mem[3316] = 1661693\n" +
				"mem[20518] = 2429070\n" +
				"mask = 0XX010111010110XX01100001X1001101010\n" +
				"mem[10481] = 534671\n" +
				"mem[3232] = 88771\n" +
				"mem[42476] = 829903139\n" +
				"mem[23957] = 82713\n" +
				"mem[59410] = 783351894\n" +
				"mem[54338] = 713731093\n" +
				"mask = 011XX110111000X0101101011000X1001111\n" +
				"mem[49852] = 266900676\n" +
				"mem[27265] = 226\n" +
				"mem[28046] = 160578\n" +
				"mask = 0011XX110011X00X10X0X001110X1010X010\n" +
				"mem[48742] = 401824\n" +
				"mem[63122] = 28688\n" +
				"mem[47126] = 7096560\n" +
				"mem[16772] = 1939\n" +
				"mem[10570] = 10714\n" +
				"mem[61299] = 509453\n" +
				"mask = 101010010000111001001X1X000X11X10X01\n" +
				"mem[46759] = 1542447\n" +
				"mem[40767] = 4698135\n" +
				"mem[61684] = 350\n" +
				"mem[22031] = 270\n" +
				"mask = 1X100001X1011110010010100011X10000X1\n" +
				"mem[47812] = 224148024\n" +
				"mem[59070] = 4675\n" +
				"mem[8910] = 87677\n" +
				"mask = 0110X1110010001X1X100X1X01100111X000\n" +
				"mem[52883] = 15219771\n" +
				"mem[25333] = 112222\n" +
				"mem[3339] = 1843398\n" +
				"mask = 1X101X01101X00X010X001XX0101100X0X11\n" +
				"mem[4298] = 639594\n" +
				"mem[8377] = 9230\n" +
				"mem[10177] = 66175935\n" +
				"mem[10999] = 1732\n" +
				"mem[19417] = 920705\n" +
				"mask = 00XXX1111110000X101X00X10100X1100111\n" +
				"mem[21108] = 579440\n" +
				"mem[61811] = 12022501\n" +
				"mem[4298] = 6241794\n" +
				"mem[15882] = 291892238\n" +
				"mem[34076] = 22758\n" +
				"mem[44997] = 401340\n" +
				"mem[10203] = 4880\n" +
				"mask = 0010X1010X1000XX10X001X111010001X110\n" +
				"mem[10169] = 84875\n" +
				"mem[40007] = 724198522\n" +
				"mem[26317] = 5062\n" +
				"mem[59565] = 14796158\n" +
				"mem[35179] = 9273\n" +
				"mem[11775] = 425020\n" +
				"mem[61734] = 277758\n" +
				"mask = X11010X1X011110X101100X0011X00010000\n" +
				"mem[1616] = 5642\n" +
				"mem[29008] = 40378\n" +
				"mem[54571] = 2275046\n" +
				"mem[56598] = 54180\n" +
				"mem[44904] = 52919006\n" +
				"mask = 011011110010000110X010X0XX0X11X00010\n" +
				"mem[58510] = 826924986\n" +
				"mem[20287] = 2697255\n" +
				"mem[12002] = 58954\n" +
				"mem[23957] = 9816\n" +
				"mem[20851] = 781642\n" +
				"mask = 01X011011011XX00XX011100110X100X011X\n" +
				"mem[35399] = 170571736\n" +
				"mem[7307] = 174037504\n" +
				"mask = XX1011100X00000X10100010X10001X0X0X0\n" +
				"mem[59534] = 32654\n" +
				"mem[50813] = 982116\n" +
				"mem[17460] = 660730376\n" +
				"mask = 0X101X01101X0X001X0X00000X1011X00000\n" +
				"mem[2628] = 179572\n" +
				"mem[37874] = 64485456\n" +
				"mem[2757] = 21090\n" +
				"mem[63548] = 18421\n" +
				"mem[34496] = 25814\n" +
				"mask = X01XX111X110X00X10000001000100100101\n" +
				"mem[29395] = 1469411\n" +
				"mem[39019] = 129148834\n" +
				"mem[34858] = 2143\n" +
				"mem[38555] = 5407018\n" +
				"mem[64659] = 6721809\n" +
				"mem[15877] = 276533\n" +
				"mem[13477] = 1551765\n" +
				"mask = 0X111X11001000XX100001000X0X10011101\n" +
				"mem[27701] = 815118\n" +
				"mem[47892] = 675192690\n" +
				"mem[1921] = 186957\n" +
				"mem[41394] = 104302\n" +
				"mem[46219] = 391\n" +
				"mask = XX1111110010001X10X0011010010X1X1101\n" +
				"mem[18848] = 944747776\n" +
				"mem[65221] = 1488890\n" +
				"mem[8962] = 1787\n" +
				"mask = 01101111X0110X00XX00000110X00101X001\n" +
				"mem[869] = 196011\n" +
				"mem[59565] = 218484\n" +
				"mem[3695] = 30633\n" +
				"mem[29495] = 63295076\n" +
				"mem[41574] = 28810\n" +
				"mask = 0010100X10X00XX0X100000XX00011X00100\n" +
				"mem[26317] = 418\n" +
				"mem[55269] = 194823\n" +
				"mem[2788] = 107445850\n" +
				"mem[58602] = 40976\n" +
				"mem[52055] = 97759722\n" +
				"mask = 100X100100XX0101110100010011X1111X01\n" +
				"mem[33519] = 52748\n" +
				"mem[2628] = 247114902\n" +
				"mem[12920] = 118\n" +
				"mem[19314] = 24512\n" +
				"mem[17460] = 16794\n" +
				"mask = 00X1X0X1X0X011000100010X10111X1X11X1\n" +
				"mem[40330] = 64426\n" +
				"mem[30794] = 88457\n" +
				"mem[46817] = 3402\n" +
				"mem[48648] = 224349\n" +
				"mem[13744] = 16799\n" +
				"mem[36227] = 2196\n" +
				"mask = 00X011X110110X0X1101XX00101011100001\n" +
				"mem[50422] = 1636652\n" +
				"mem[46734] = 566\n" +
				"mem[8709] = 5494892\n" +
				"mem[35996] = 11312587\n" +
				"mem[47720] = 63678\n" +
				"mem[37938] = 21\n" +
				"mask = 001X1000000X0X1001000010001001100XX0\n" +
				"mem[4298] = 789383\n" +
				"mem[49605] = 29507\n" +
				"mem[4850] = 23534344\n" +
				"mask = 10101011101010000X001X001X0110X011X0\n" +
				"mem[36247] = 55966312\n" +
				"mem[47774] = 7827\n" +
				"mem[47104] = 179521\n" +
				"mem[10169] = 8480531\n" +
				"mem[64125] = 1496\n" +
				"mem[9860] = 102302115\n" +
				"mask = XX10X1110010000XXX0010001000010XX10X\n" +
				"mem[63122] = 2529\n" +
				"mem[15234] = 3390\n" +
				"mem[47165] = 131784350\n" +
				"mask = X110XX1X10100XX0101000001110001011XX\n" +
				"mem[42598] = 205125350\n" +
				"mem[45213] = 875385\n" +
				"mem[1630] = 374698\n" +
				"mask = 001X110X10100000110000001X00100X0101\n" +
				"mem[869] = 262\n" +
				"mem[1729] = 7311\n" +
				"mem[38532] = 37873869\n" +
				"mask = 0X10111X111000101XX000010110X1100111\n" +
				"mem[40337] = 110094841\n" +
				"mem[23200] = 5963831\n" +
				"mask = 0XX0X101XX1101X010100X11001X11X00111\n" +
				"mem[39850] = 194184\n" +
				"mem[15113] = 14352\n" +
				"mem[37359] = 62840981\n" +
				"mask = 001010X11X10X100X1000X1X0X0X0X101101\n" +
				"mem[14025] = 1248\n" +
				"mem[4446] = 60798259\n" +
				"mem[54198] = 747825\n" +
				"mem[8222] = 2843610\n" +
				"mem[46819] = 134827\n" +
				"mask = 001X10X11010110X010X01010000011X01X1\n" +
				"mem[4571] = 3\n" +
				"mem[45153] = 354688624\n" +
				"mem[23739] = 1747241\n" +
				"mem[2180] = 1501\n" +
				"mem[31640] = 314996015\n" +
				"mem[22838] = 1456\n" +
				"mem[9279] = 1793\n" +
				"mask = 0110X1X1X0110000110X00001110011X0111\n" +
				"mem[2293] = 860\n" +
				"mem[52148] = 31128529\n" +
				"mem[6212] = 62\n" +
				"mem[43300] = 57169\n" +
				"mask = 1000X001101X010011X100X110100X001X01\n" +
				"mem[2628] = 2308\n" +
				"mem[2849] = 57923736\n" +
				"mem[12286] = 287875\n" +
				"mem[12201] = 72\n" +
				"mem[34425] = 59856\n" +
				"mask = 0110X101101X010011010X0101100X1001X0\n" +
				"mem[7749] = 185738\n" +
				"mem[4446] = 594\n" +
				"mem[51626] = 351621474\n" +
				"mem[24035] = 739934921\n" +
				"mask = 1XX011011011XX001X1X0110X1110X000111\n" +
				"mem[54919] = 2711949\n" +
				"mem[2709] = 208984376\n" +
				"mem[2761] = 28699\n" +
				"mem[45547] = 34108\n" +
				"mem[55552] = 4945\n" +
				"mem[18632] = 150986875\n" +
				"mem[10947] = 400341\n" +
				"mask = 011010X1101XX1X010X1X000X100X00X0000\n" +
				"mem[22250] = 2516\n" +
				"mem[34496] = 31839\n" +
				"mask = 0110X1X000100X00XXX0X00111100X001000\n" +
				"mem[11889] = 506\n" +
				"mem[34264] = 2791\n" +
				"mem[35884] = 3473536\n" +
				"mask = 1X1XX11110X0110011X1001X1001XX01000X\n" +
				"mem[33997] = 1179307\n" +
				"mem[50647] = 669335\n" +
				"mem[61780] = 9982626\n" +
				"mem[13748] = 1981\n" +
				"mem[29233] = 950\n" +
				"mask = 0110111111100X1010X0X00X100000110110\n" +
				"mem[22910] = 1180108\n" +
				"mem[5562] = 21631\n" +
				"mem[1222] = 504800403\n" +
				"mask = 001X100X00X0011X01001010010010000001\n" +
				"mem[24198] = 119566\n" +
				"mem[35001] = 16283323\n" +
				"mem[59436] = 119739774\n" +
				"mem[62948] = 64713\n" +
				"mem[2849] = 255\n" +
				"mem[23156] = 1485\n" +
				"mask = 001X111X0X0X0000X000100100000XX01X1X\n" +
				"mem[35160] = 1583418\n" +
				"mem[43805] = 771\n" +
				"mem[8313] = 216593668\n" +
				"mem[43300] = 4138437\n" +
				"mem[26057] = 513262\n" +
				"mem[3182] = 46056\n" +
				"mem[10789] = 8045\n" +
				"mask = 001X101X1110010XX10010XX011000X00011\n" +
				"mem[46633] = 4097498\n" +
				"mem[41631] = 626\n" +
				"mem[35179] = 922\n" +
				"mem[63510] = 6031\n" +
				"mem[18031] = 6879\n" +
				"mask = 10X0X001100X111011000X1X0010X11XX000\n" +
				"mem[28323] = 16663\n" +
				"mem[55733] = 11506187\n" +
				"mask = 0X1011110X100010X010011X10001010X11X\n" +
				"mem[6698] = 11724271\n" +
				"mem[55597] = 10930\n" +
				"mem[11310] = 56566\n" +
				"mem[4411] = 489\n" +
				"mem[7361] = 431285\n" +
				"mask = 1X10100110110100100100X00000XX010100\n" +
				"mem[16972] = 963\n" +
				"mem[34942] = 1374714\n" +
				"mem[33641] = 34676\n" +
				"mem[53248] = 1039\n" +
				"mem[50381] = 239\n" +
				"mem[41003] = 271150\n" +
				"mem[59255] = 1046488\n" +
				"mask = 10111X01110XX11001001001X0001XX01X11\n" +
				"mem[22876] = 1261577\n" +
				"mem[23514] = 83628146\n" +
				"mem[46492] = 16023174\n" +
				"mem[42168] = 22907486\n" +
				"mem[56233] = 1208\n" +
				"mask = 0X1X111100XX00001000000110XX001X01X0\n" +
				"mem[41579] = 106791107\n" +
				"mem[37293] = 76855011\n" +
				"mem[23712] = 3066\n" +
				"mem[53928] = 206585650\n" +
				"mem[38356] = 58860\n" +
				"mask = 0010X001101X0100X001101X1100100111X0\n" +
				"mem[40721] = 1522\n" +
				"mem[35179] = 41746958\n" +
				"mem[19534] = 58840945\n" +
				"mem[32324] = 147835050\n" +
				"mem[48430] = 245\n" +
				"mask = 00XXX1110001000X00000011101001X01011\n" +
				"mem[47713] = 371239\n" +
				"mem[12557] = 896\n" +
				"mem[23039] = 728213024\n" +
				"mem[39609] = 19414\n" +
				"mem[44321] = 11334054\n" +
				"mem[36247] = 398030\n" +
				"mask = 00101101001X0X0XX0X0001X0X01X110011X\n" +
				"mem[46734] = 676353\n" +
				"mem[60374] = 267786\n" +
				"mem[38508] = 446859055\n" +
				"mem[64904] = 14216866\n" +
				"mem[31959] = 813920705\n" +
				"mem[27255] = 784\n" +
				"mem[36553] = 255261\n" +
				"mask = 0011X1X1X01X0X111000101100000X001000\n" +
				"mem[46633] = 1221\n" +
				"mem[33954] = 680347\n" +
				"mem[21297] = 35894\n" +
				"mem[41405] = 6184\n" +
				"mask = 001X111111100X001XX000X01001XXX0X010\n" +
				"mem[39635] = 477960\n" +
				"mem[39405] = 161170\n" +
				"mem[36252] = 453585\n" +
				"mem[55397] = 2058746\n" +
				"mem[33107] = 2663\n" +
				"mask = 101X100X1001XX00010010001X1X1X010000\n" +
				"mem[35277] = 200785\n" +
				"mem[19680] = 1119384\n" +
				"mem[46603] = 2780262\n" +
				"mask = 111X01110X1000001X0010011000011000X0\n" +
				"mem[30145] = 7700\n" +
				"mem[61472] = 979688\n" +
				"mem[14460] = 21055\n" +
				"mem[16944] = 313655989\n" +
				"mask = 0110111X00X0000X10X00X0X010X000X11X0\n" +
				"mem[2168] = 26991523\n" +
				"mem[5264] = 980832681\n" +
				"mem[36646] = 813667866\n" +
				"mem[48602] = 1783\n" +
				"mask = 0X101000X010001001001110110XX11X111X\n" +
				"mem[23627] = 807818\n" +
				"mem[61811] = 23479\n" +
				"mem[64] = 219255\n" +
				"mem[37128] = 1553397\n" +
				"mem[14691] = 67418150\n" +
				"mask = 1X1010011000110001X01XX0010X100110X0\n" +
				"mem[27135] = 64061097\n" +
				"mem[19834] = 2824449\n" +
				"mem[50521] = 3939\n" +
				"mem[58503] = 8393\n" +
				"mem[28423] = 43394\n" +
				"mask = XX1X1111X01011X011X100010X1100100XX1\n" +
				"mem[23429] = 130936\n" +
				"mem[48602] = 7532488\n" +
				"mem[6436] = 310907\n" +
				"mem[24886] = 27122161\n" +
				"mem[27957] = 50861195\n" +
				"mem[54279] = 180122731\n" +
				"mask = X0X01X1011100X01110XX0X1X11101X00010\n" +
				"mem[14025] = 691\n" +
				"mem[2825] = 249\n" +
				"mem[3925] = 3303251\n" +
				"mask = 00XX111X111000101X10000X10X011X11110\n" +
				"mem[17498] = 634241\n" +
				"mem[15524] = 20855180\n" +
				"mask = 0X1X1101X01X0100110100XX1X10XX100100\n" +
				"mem[30145] = 625\n" +
				"mem[9797] = 1359\n" +
				"mem[12286] = 1127042\n" +
				"mask = 101X100100X011101X00X100X1X1111X1X11\n" +
				"mem[10972] = 42023592\n" +
				"mem[61376] = 3427840\n" +
				"mem[27255] = 6685615\n" +
				"mem[13520] = 10945\n" +
				"mem[55597] = 807895898\n" +
				"mem[60531] = 5121\n" +
				"mask = 0X10100X101X0X0X1X000000X01011100100\n" +
				"mem[10352] = 861247\n" +
				"mem[2656] = 3492337\n" +
				"mem[55397] = 2392591\n" +
				"mem[29495] = 98579\n" +
				"mem[2757] = 3455299\n" +
				"mem[11236] = 4020\n" +
				"mask = 011011100X1XXX00100XX1001X00X0000100\n" +
				"mem[45068] = 386\n" +
				"mem[8960] = 151\n" +
				"mem[17784] = 108694472\n" +
				"mem[26289] = 4159\n" +
				"mem[3665] = 674\n" +
				"mem[54896] = 131398121\n" +
				"mask = 00101X11X110000X1010X0100100X1010110\n" +
				"mem[34512] = 162\n" +
				"mem[36639] = 15024013\n" +
				"mem[34942] = 80023258\n" +
				"mem[24555] = 418619\n" +
				"mem[50642] = 27165886\n" +
				"mem[345] = 110421710\n" +
				"mask = X0101101101100X010000X00111011X00101\n" +
				"mem[1153] = 62642\n" +
				"mem[56846] = 5129\n" +
				"mem[20775] = 4212056\n" +
				"mem[19328] = 216506\n" +
				"mem[29495] = 8683991\n" +
				"mask = 0010111X111000X010100X00X1XX11X0X11X\n" +
				"mem[3771] = 4382805\n" +
				"mem[57881] = 16921\n" +
				"mem[63654] = 6152\n" +
				"mem[23552] = 5702333\n" +
				"mem[6083] = 220540005\n" +
				"mask = X01X1X0110001110010X011XX100X001X110\n" +
				"mem[59847] = 564882739\n" +
				"mem[51385] = 221685661\n" +
				"mem[61811] = 204871661\n" +
				"mem[56244] = 31583475\n" +
				"mem[6980] = 1527\n" +
				"mem[26289] = 15857\n" +
				"mask = X010100XX000X110X100110X0XX1111X0101\n" +
				"mem[4571] = 54901211\n" +
				"mem[57199] = 45702\n" +
				"mem[42452] = 43929335\n" +
				"mem[19680] = 48875\n" +
				"mem[10352] = 122542\n" +
				"mem[34374] = 2882\n" +
				"mem[61522] = 3971\n" +
				"mask = 01X011X10010001010X00110X1000X11X001\n" +
				"mem[25036] = 2952\n" +
				"mem[61299] = 2085542\n" +
				"mem[19117] = 186\n" +
				"mem[53853] = 187\n" +
				"mask = 0011X111X0100X1X10X0110XX00010111110\n" +
				"mem[57399] = 1006550549\n" +
				"mem[63028] = 186530\n" +
				"mem[48554] = 22962\n" +
				"mem[56976] = 47700\n" +
				"mem[6575] = 131534365\n" +
				"mem[52761] = 3853817\n" +
				"mask = XXX0XX0110110100X1010000X010010X0101\n" +
				"mem[11889] = 29237617\n" +
				"mem[17718] = 1630\n" +
				"mem[4636] = 229985\n" +
				"mem[52883] = 84375864\n" +
				"mask = X01010001011X1X0X10XX1010XX101100101\n" +
				"mem[49740] = 7868911\n" +
				"mem[37506] = 4002\n" +
				"mem[32663] = 869910098\n" +
				"mem[8572] = 125350\n" +
				"mem[8342] = 61042\n" +
				"mask = X0101X0100100101X00000101X00XX1100X0\n" +
				"mem[59565] = 1105\n" +
				"mem[27715] = 1745\n" +
				"mem[59206] = 330363729\n" +
				"mask = 01101111X0110000100000X010X01010X10X\n" +
				"mem[44171] = 899\n" +
				"mem[47812] = 21673008\n" +
				"mem[27608] = 13645404\n" +
				"mem[32326] = 491141\n" +
				"mem[63638] = 694\n" +
				"mem[53420] = 426003787\n" +
				"mem[51557] = 3275141\n" +
				"mask = X1101X11001X001010000010XXXX010XX000\n" +
				"mem[54777] = 804\n" +
				"mem[34172] = 6830067\n" +
				"mem[49202] = 63909\n" +
				"mem[13477] = 10986\n" +
				"mask = 101XX0011XXX11X00100101001001X011011\n" +
				"mem[16846] = 27316344\n" +
				"mem[50094] = 16967873\n" +
				"mem[61780] = 900178\n" +
				"mem[15882] = 23418\n" +
				"mem[22876] = 3337\n" +
				"mem[47284] = 230107\n" +
				"mask = 0111110X1X10010X1101001X1001011X0001\n" +
				"mem[12201] = 6745\n" +
				"mem[25284] = 182\n" +
				"mem[44850] = 1569\n" +
				"mem[47949] = 411159\n" +
				"mem[30793] = 845530\n" +
				"mem[47029] = 58274\n" +
				"mask = X11011X11010X100110100001111X0000101\n" +
				"mem[3182] = 10552\n" +
				"mem[48303] = 111746\n" +
				"mem[14883] = 15066\n" +
				"mem[4517] = 28345405\n" +
				"mem[25038] = 178092778\n" +
				"mask = 1X001001X01101XX11X10X01001100100001\n" +
				"mem[22910] = 1977\n" +
				"mem[15113] = 102588\n" +
				"mem[62218] = 1881\n" +
				"mask = X01X101110101XX00X0010100010000X111X\n" +
				"mem[33012] = 795\n" +
				"mem[50671] = 14579873\n" +
				"mem[55556] = 169319\n" +
				"mem[10502] = 3909\n" +
				"mem[36753] = 31795\n" +
				"mem[41712] = 8377123\n" +
				"mem[63904] = 4717\n" +
				"mask = 00110111XXX00X1110000001010XX0X0X010\n" +
				"mem[2564] = 131432438\n" +
				"mem[28323] = 3416844\n" +
				"mem[49852] = 3072\n" +
				"mem[42274] = 404\n" +
				"mask = 011011110X110000000001001000XX1001X1\n" +
				"mem[47316] = 107\n" +
				"mem[12286] = 12644\n" +
				"mem[37518] = 92320\n" +
				"mem[54777] = 2007\n" +
				"mem[54708] = 8481064\n" +
				"mem[48684] = 2372\n" +
				"mask = 01X0011000100100011XX0001110XX001100\n" +
				"mem[5220] = 1685\n" +
				"mem[11442] = 5413142\n" +
				"mask = 01001X0101110X0X10100001X0X0X1XX101X\n" +
				"mem[57860] = 3766\n" +
				"mem[699] = 147751906\n" +
				"mem[16648] = 30301";
	}

}
