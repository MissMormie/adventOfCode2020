package days2020;

import java.util.Arrays;

public class Day2 {

	public static void main(String[] args) {
		System.out.println("answer A: " + runA(getInput()));
		System.out.println("answer B: " + runB(getInput()));
	}

	public static long runA(String input) {
		return Arrays.stream(input.split("\n"))
				.filter(Day2::isValid)
				.count();
	}

	public static long runB(String input) {
		return Arrays.stream(input.split("\n"))
				.filter(Day2::isValidPolicy2)
				.count();
	}

	public static boolean isValid(String inputLine) {
		// 3-4 c: cscckcxcn
		int min = Integer.parseInt(inputLine.substring(0, inputLine.indexOf('-')));
		int max = Integer.parseInt(inputLine.substring(inputLine.indexOf('-') + 1, inputLine.indexOf(' ')));
		char c = inputLine.charAt(inputLine.indexOf(':') - 1);
		String passwword = inputLine.substring(inputLine.indexOf(": ") + 2, inputLine.length());
		long count = passwword.chars().filter(ch -> ch == c).count();
		return count >= min && count <= max;
	}

	public static boolean isValidPolicy2(String inputLine) {
		int position1 = Integer.parseInt(inputLine.substring(0, inputLine.indexOf('-'))) - 1;
		int position2 = Integer.parseInt(inputLine.substring(inputLine.indexOf('-') + 1, inputLine.indexOf(' '))) - 1;
		char c = inputLine.charAt(inputLine.indexOf(':') - 1);
		String password = inputLine.substring(inputLine.indexOf(": ") + 2);

		return (password.charAt(position1) == c && password.charAt(position2) != c) ||
				(password.charAt(position1) != c && (password.charAt(position2) == c));
	}

	public static String getInput() {
		return "13-17 s: ssssssssssssgsssj\n" +
				"7-9 p: pnlzhcppvl\n" +
				"5-6 z: zzbwrv\n" +
				"7-15 w: wwwwwcqwwwwwwwww\n" +
				"8-9 h: hhhhhhhhsh\n" +
				"12-13 l: mtrkqfllrglll\n" +
				"3-4 t: nntt\n" +
				"10-14 g: svgggggfhqggghlg\n" +
				"3-10 x: djxxxpmcxx\n" +
				"1-10 z: ztzzzgzzzz\n" +
				"3-4 j: jjjjj\n" +
				"3-4 c: fbhnsccbc\n" +
				"2-4 q: thql\n" +
				"15-18 w: tcwzpwzfwwqftvczbw\n" +
				"5-7 l: lblwblbmllqnlbl\n" +
				"3-4 b: bvbfnbb\n" +
				"4-5 f: ffgzf\n" +
				"7-9 c: cctcccccc\n" +
				"5-6 k: szkgsk\n" +
				"2-5 n: nqvnmgnqnsxfn\n" +
				"2-10 s: dsfsbsssltss\n" +
				"12-14 s: ssskrssssssfsxpsqsp\n" +
				"13-15 j: jjjjjjjjjjjjjjjj\n" +
				"16-17 l: jllclhllkgcpljzwk\n" +
				"6-9 f: fsffwffbffbfff\n" +
				"5-9 s: xtzscxsstcss\n" +
				"6-9 h: hmnhshhhhqhrthmh\n" +
				"3-6 x: xxxxxx\n" +
				"9-11 m: mcmmmktmtmmmmm\n" +
				"2-6 r: grrprrsmr\n" +
				"15-16 v: vvvvvvvvvvvvvvwdv\n" +
				"2-14 j: jmjdbhjjjjjjjxnjj\n" +
				"5-9 r: rrwrrrrrr\n" +
				"3-9 b: wbbcbvbtbbbbb\n" +
				"2-9 r: rrtjlplkrgmrrrrxq\n" +
				"9-12 s: sssshsmsssss\n" +
				"2-14 t: qzxxvthtthtthv\n" +
				"2-11 p: pvlsppwmgcspl\n" +
				"12-15 s: dsjqsshdssbssstsssss\n" +
				"3-9 k: tkkkpkkklk\n" +
				"7-15 s: sssssssssssssgsssss\n" +
				"6-9 x: xxxsxzxglpwxl\n" +
				"2-3 x: xxmh\n" +
				"7-9 g: ggsggggggg\n" +
				"4-17 t: vtttrrrhtghfjvnqtttj\n" +
				"1-2 n: jgnpn\n" +
				"11-12 k: vlkkbkkkkklk\n" +
				"9-12 v: vvvvvvvvwvvvv\n" +
				"10-11 j: jjjjljjjjvm\n" +
				"1-5 x: vvxlx\n" +
				"3-4 l: flll\n" +
				"10-11 q: qqqqqqqqqlq\n" +
				"4-5 d: vdvhmpdtrdhdzfdsgk\n" +
				"14-16 b: bbqbbbxbhrbpbtmhbbb\n" +
				"18-19 d: dzpnbxngpgsjtnvtcdd\n" +
				"2-16 v: jtrhtlfhvkbbflfq\n" +
				"15-17 q: qqqsqqqqqqjqqqlqm\n" +
				"5-6 q: tqrhtqqspdqvzqbx\n" +
				"4-8 b: lbbzbwtv\n" +
				"10-13 f: hrsnsfflfffsfjff\n" +
				"10-14 k: kkjkkkkkkkkkkkkkkhk\n" +
				"4-9 c: ccwcccccc\n" +
				"5-7 r: grkrrrwjrrgbdtd\n" +
				"8-12 p: tgspnljpfhtr\n" +
				"5-6 v: vvvvvvm\n" +
				"5-7 v: vvvvvvv\n" +
				"6-8 q: qqqqqdqqq\n" +
				"2-4 v: xfvqvlp\n" +
				"3-4 g: gghb\n" +
				"3-14 z: xflgzzrkzqzxzzxgd\n" +
				"1-7 g: ldsgswgdgdmjlggb\n" +
				"5-10 h: rhhmhhnbdhhqhwp\n" +
				"16-18 m: mmmmmmwmmmmmmmmmmmm\n" +
				"5-11 j: scssgcjjjjpzjjn\n" +
				"13-14 s: wznksvkfvfskfs\n" +
				"3-9 q: qdqqkqtbcnqqqsrklqbf\n" +
				"7-9 c: cccchcmcvdcczwt\n" +
				"15-16 g: gggdgggggggggggg\n" +
				"1-5 k: kkkkkk\n" +
				"7-10 q: qqqqqqqqqqqqq\n" +
				"3-12 b: bbbrhbspbrbfbbqbbbb\n" +
				"3-5 r: gczrgr\n" +
				"2-6 h: whnlvhsftp\n" +
				"8-20 h: fhhhsdfhhfzgmhpqhhjh\n" +
				"15-16 z: sbzzzzzzdzjbwhgb\n" +
				"4-8 h: jgtbmhwb\n" +
				"8-9 s: hssksrnhlsdslssvcsbs\n" +
				"2-6 v: vvvvvvv\n" +
				"13-14 x: xxxxxxxxxxxxnsbx\n" +
				"12-13 l: pgllllltpdfpxlll\n" +
				"2-17 v: vqnncnxkssmhvkzstzgp\n" +
				"4-5 r: rrrhw\n" +
				"8-11 p: pppdppphppp\n" +
				"6-7 t: lrmqtts\n" +
				"8-12 h: hhlthhhhhhhhhtrzh\n" +
				"9-10 g: jpgpmpcrwfkz\n" +
				"12-13 w: wwwwwwwwwwhwwww\n" +
				"5-13 l: jwtlqglllllxkllls\n" +
				"2-4 c: ncpcgd\n" +
				"3-7 l: llslllll\n" +
				"2-5 n: jdfwnn\n" +
				"3-5 z: xctxd\n" +
				"4-6 l: lllrll\n" +
				"6-7 q: fqqqqqqzqqqqq\n" +
				"9-12 c: gccwgwqlcxccchchcrcb\n" +
				"7-12 p: pppgpppppppps\n" +
				"10-16 g: xrgfggzzghtgxgbggg\n" +
				"7-11 d: tdddddqdwdgxldj\n" +
				"11-14 p: pppppbpppppppppp\n" +
				"1-2 j: jhjj\n" +
				"8-9 v: pvvvvvvvcgvq\n" +
				"5-6 n: qcrhnnkktxvdxcp\n" +
				"7-12 x: lfnxxxdxxxxp\n" +
				"10-12 s: sssssssssbsgs\n" +
				"2-3 x: rqhzxmjnjxx\n" +
				"13-14 w: wwwwvwwwwwwwqhww\n" +
				"10-11 t: dgttttgttttt\n" +
				"7-9 w: wdzwwwwjj\n" +
				"4-10 v: vvsjvvbvvncvvvvxv\n" +
				"6-8 w: wwwwwpwq\n" +
				"7-9 j: jtjjjjrjmxg\n" +
				"5-11 z: zzdgztshstzczw\n" +
				"1-4 s: ssss\n" +
				"3-10 p: pqppgrphrpkdmp\n" +
				"9-12 x: xxxqxxxlxxsx\n" +
				"3-4 v: nvswzv\n" +
				"3-9 d: vdjvdcxdkdd\n" +
				"2-10 v: vvjcvvvzvkb\n" +
				"11-14 g: gggzggggggjgghg\n" +
				"4-19 p: pppqppppppppppppppvq\n" +
				"3-5 n: ngznkgdpn\n" +
				"13-14 t: tttwtbtttttxxmttt\n" +
				"3-7 q: qnqqqqqtqqz\n" +
				"3-4 r: rkgr\n" +
				"9-12 l: lqkfljzllqtl\n" +
				"5-8 m: vpmmsnfmtxbcknlmpb\n" +
				"15-19 s: ssptssnhsksnszsdpqss\n" +
				"13-14 j: jsjjjjnjjjjjjjj\n" +
				"6-9 p: ppppppppz\n" +
				"5-8 r: tqzbnvgsvfrx\n" +
				"7-9 h: hhhhhhhhp\n" +
				"8-11 s: ssssssssssss\n" +
				"3-10 t: ttttttttttttt\n" +
				"4-5 g: ngtgg\n" +
				"7-8 n: tnnnnnnqn\n" +
				"12-16 x: bxxxxxzxxxxxxxbxx\n" +
				"1-3 z: zzzt\n" +
				"11-14 x: xxgxxxxxxxwcxfx\n" +
				"6-11 v: rgkkhpkjvvf\n" +
				"16-17 n: nfgnnnnnnnnnnnngvn\n" +
				"3-7 w: wkwwwww\n" +
				"5-16 g: ggggcggwgggggggggg\n" +
				"1-3 l: sjvjlkzrpn\n" +
				"17-19 p: pppppppppppppppptpd\n" +
				"2-6 b: vjjwbqbdbpwsbw\n" +
				"4-8 g: gggggjvgd\n" +
				"2-6 w: pzzpwdm\n" +
				"3-10 g: ggggcggggggg\n" +
				"1-3 v: vxvjcvcpslpfn\n" +
				"9-10 r: qrrwrrjrrr\n" +
				"5-10 m: qbmdzvqmxnnmmmtk\n" +
				"2-14 q: vqttpdjhqvkqzqxz\n" +
				"12-13 r: rrxbrrrrrcrptjrr\n" +
				"13-15 s: cscssssssssssssss\n" +
				"7-10 z: zxbrzxbdbhzzzfzz\n" +
				"2-16 n: skqkxqmqbvbrnspnrgq\n" +
				"14-18 b: bbxsbbdpbnbbbnbbbpbb\n" +
				"5-8 b: bmcrdhbbk\n" +
				"11-13 f: fffffffzfffwffb\n" +
				"5-6 w: wwbwlww\n" +
				"12-14 d: gzxclqbnmnxjlzd\n" +
				"13-19 z: fzzzctzzdsbgzzzzwzpb\n" +
				"2-12 w: lcwwtwwpwffdjqwms\n" +
				"9-10 x: hznxnxxxxxxxgkxx\n" +
				"2-4 g: cwfjtlgf\n" +
				"11-12 k: bqfkkkklkkkkb\n" +
				"2-4 l: llllgxk\n" +
				"5-9 m: cpwmqkzhxs\n" +
				"8-15 b: bbwlwkblvbwdvrjbgsb\n" +
				"3-4 f: ffmff\n" +
				"4-10 c: nqxcqcfcqc\n" +
				"10-12 d: ddqddddddtdhd\n" +
				"3-4 n: dghnnqnn\n" +
				"6-15 s: rsbxbrfmssdknppw\n" +
				"2-13 m: mmkmmmmqdmkmmm\n" +
				"8-12 n: nnnnnnnnnnnn\n" +
				"2-5 d: mdkwd\n" +
				"14-15 l: hllllllllllllgl\n" +
				"6-11 g: jgccfnggggpgkt\n" +
				"4-6 j: jjjtjjjjjj\n" +
				"9-12 v: hgpjjlvhvhljb\n" +
				"2-4 g: jfwdgfr\n" +
				"4-5 f: fhffffchf\n" +
				"16-17 j: jjjjjjjjjjjjjjjjj\n" +
				"1-4 t: tcttwtttttrkt\n" +
				"6-13 x: qmxfxxwxrxdxxxfvgv\n" +
				"6-7 c: cckpccc\n" +
				"3-10 m: qwmmpknsgmdmfvrcvw\n" +
				"2-4 r: rszv\n" +
				"1-7 q: qqqqqqqq\n" +
				"1-6 l: lzlmjl\n" +
				"11-12 q: mqqqqxbqqrqqqdqqbkq\n" +
				"8-11 h: jtvbhxhhphhhh\n" +
				"9-11 b: bbbbfbzbzbb\n" +
				"5-7 l: gljssxddzscvhwrlrb\n" +
				"3-6 c: fxcbccddxxcn\n" +
				"3-5 s: ssssz\n" +
				"17-18 n: jctqdsjchwknkffpnn\n" +
				"8-10 c: crtwbwcfcqkf\n" +
				"3-5 r: qrzqc\n" +
				"7-12 z: zzzzzzzzsfzsz\n" +
				"13-15 p: pppppppppzrllplp\n" +
				"11-12 h: nhhhhjhqhhplhhh\n" +
				"4-5 l: lllll\n" +
				"4-10 g: gggzgggggg\n" +
				"15-16 h: qhhhshrhhfhhhhch\n" +
				"5-7 k: jsrvnjlkq\n" +
				"16-17 p: pppplppppppppppml\n" +
				"13-14 m: xqhmjdmrmmmffr\n" +
				"6-11 c: cccccgccccrccccccc\n" +
				"9-10 l: llllllllxllt\n" +
				"8-9 b: bbbpbbbwbdnkbb\n" +
				"5-9 m: sfmmpmmmzn\n" +
				"5-15 k: kkkkkkkkkkkkkkkckkkk\n" +
				"3-4 h: hhhh\n" +
				"15-19 g: gggwgggrslgxjgfgbfs\n" +
				"12-13 d: dddddddddddxb\n" +
				"6-14 k: kkrgdvhscffwwm\n" +
				"14-16 m: zmmmmqmmmmmmmgmm\n" +
				"8-9 w: wwwwgwwww\n" +
				"1-12 x: xxxxxxxxxxxx\n" +
				"1-5 b: bwrbbfwgh\n" +
				"1-3 t: ftttttttttttttt\n" +
				"10-11 m: mwmmmwkmxsf\n" +
				"8-9 t: tqtttttttdtttt\n" +
				"7-8 z: hzbkpzfg\n" +
				"2-3 h: htblhdhh\n" +
				"3-4 p: pptnlpc\n" +
				"4-7 l: lqlnqhljt\n" +
				"12-14 n: nnnnnnnnnnjnnt\n" +
				"1-4 d: dszd\n" +
				"12-13 f: fvrffffffffkfff\n" +
				"9-15 t: bkzstqvtgtwxtzqttd\n" +
				"6-7 v: vrvvvkgv\n" +
				"6-9 n: knbpnnjnnsn\n" +
				"5-8 n: nznkzzqrbn\n" +
				"9-12 s: ssscssssgsqwsspssp\n" +
				"4-16 q: slqqqqqhqqqqqqdqc\n" +
				"1-3 d: gdttdd\n" +
				"2-6 h: hhqzmh\n" +
				"4-9 k: lkckkpbzkl\n" +
				"8-17 b: bbbbkbbpbbbbbbbbn\n" +
				"1-2 r: bqmrw\n" +
				"8-9 s: ztssqssbz\n" +
				"1-13 s: stbqmhspsdgjs\n" +
				"8-10 z: trnsmlxzmzzzz\n" +
				"6-8 v: vlxvgzbzvvv\n" +
				"3-6 c: xddhwtcbpcf\n" +
				"13-19 g: rjvsgpnfjvzlxxzzgxgm\n" +
				"5-9 m: qffpmxmfmk\n" +
				"14-15 c: dcfwcpxcmvpxxtcbmzhc\n" +
				"6-10 k: jkpbkvghpxksfkkk\n" +
				"2-3 j: jmgjjjj\n" +
				"7-12 z: szrznmkjxnzj\n" +
				"4-9 s: ssssssssss\n" +
				"2-4 h: hkqvfgpsxlnhtrqr\n" +
				"6-11 g: gggglrlgggwv\n" +
				"1-6 v: vrbvlvrvv\n" +
				"4-10 j: jjjjjjjjjz\n" +
				"3-12 k: kkjkkkkkfckgkkkkk\n" +
				"3-15 x: xxrxxxxxxxwxpxg\n" +
				"5-8 v: vvhvvdzvqfrvgqvt\n" +
				"5-17 z: zznzzzzzzzzzzzzvf\n" +
				"4-12 n: fdrncrggjlbgbmwv\n" +
				"6-17 d: tcddhdbdvtsnrdstd\n" +
				"1-10 m: mqljmgchlmsx\n" +
				"4-6 l: fbllbl\n" +
				"8-9 p: kpmtpphwp\n" +
				"3-6 j: sjjjjj\n" +
				"5-6 n: nnnnbs\n" +
				"7-12 p: ppqppngcphpk\n" +
				"5-15 x: xxxxgxxxxxxxxxqx\n" +
				"1-3 p: ppppm\n" +
				"3-6 w: wwwkwww\n" +
				"1-4 r: rrrx\n" +
				"3-4 z: zzznp\n" +
				"12-14 n: tnzwtmnnnnndnfnnn\n" +
				"9-10 d: ddfdddtdddd\n" +
				"4-14 l: hvllsgncmhxhwl\n" +
				"10-15 j: kxftfsmjvjgslsjpp\n" +
				"2-10 g: gtgvggqggggxggdg\n" +
				"1-9 v: cvnxhhffnbcv\n" +
				"1-14 c: ccccccccccccccc\n" +
				"3-8 q: fnqmkbhqtbjqvczq\n" +
				"2-4 v: fvphvgv\n" +
				"10-11 k: kkkkkkkkkpt\n" +
				"1-10 d: dddldkddmdd\n" +
				"4-6 x: xxxxxxxx\n" +
				"4-11 k: kxkfkkkskkrkmk\n" +
				"1-6 n: nlmngnkqww\n" +
				"1-9 j: jjjjjjjjsj\n" +
				"5-6 b: bbbbbb\n" +
				"9-11 q: qqnqqmqqqpq\n" +
				"4-7 r: drrrrsrrz\n" +
				"10-15 n: nnhncnpsnxtnpnqnnghn\n" +
				"3-6 d: ddddddd\n" +
				"4-7 x: bxvxbxjwxgx\n" +
				"1-2 n: cmpwsbjzghgndj\n" +
				"2-4 r: przczfjfrbwj\n" +
				"3-17 k: qbvmqxxffdfpkwxdgxv\n" +
				"7-12 v: llmjvscvjvpvp\n" +
				"2-7 z: vzxzxxz\n" +
				"9-18 r: srdrjcrrrvhrcqrrrr\n" +
				"7-8 f: rffffvtrfsqff\n" +
				"5-8 w: pdcwwvmwwwkzwwjwxks\n" +
				"3-4 c: cccc\n" +
				"15-19 c: cccccccnccclbnplccqc\n" +
				"2-13 f: fffffffffffffff\n" +
				"3-9 t: thrkzdttb\n" +
				"6-11 p: pppppgppppjp\n" +
				"5-8 q: qqqrqfqs\n" +
				"7-11 r: crprgtwwrzp\n" +
				"2-10 f: qfhgfftfdbfnlffffff\n" +
				"4-5 h: dhvvlcw\n" +
				"3-6 s: sffsfws\n" +
				"2-7 b: jrvxbhbp\n" +
				"10-11 w: wwwgwwwwwkb\n" +
				"5-9 d: dddddddddddddkdv\n" +
				"4-14 b: bbbbbbbbbbbbbbb\n" +
				"4-8 x: xxxxdxbqxc\n" +
				"5-9 l: phlkhlllbc\n" +
				"1-2 s: ssgskqvrsrwdt\n" +
				"19-20 x: xcxqxvxxxtstnqxhxxxx\n" +
				"6-7 x: gxxxxgb\n" +
				"4-5 c: cccccc\n" +
				"3-10 q: pzzjqjqpfbqws\n" +
				"4-10 t: ttdttsccttpt\n" +
				"14-18 c: ccrccccccccccccccrc\n" +
				"2-5 x: xxzqx\n" +
				"5-11 c: qbckpccckccccws\n" +
				"5-6 g: gggggg\n" +
				"3-5 n: wknbnnlnnq\n" +
				"9-13 j: jjxjjjnjjjdqjjj\n" +
				"5-9 s: smsxsrfss\n" +
				"2-4 v: vxphvvk\n" +
				"6-7 x: xxxqxxbbx\n" +
				"1-3 g: zrgtghjwd\n" +
				"4-5 n: nmntwnn\n" +
				"6-8 g: ggznggggg\n" +
				"4-5 k: tkkzs\n" +
				"9-10 g: hqdhggggqggk\n" +
				"12-16 w: wwztwmwwspwhmwwz\n" +
				"3-5 m: mmmvmjmmm\n" +
				"3-4 j: jjjj\n" +
				"6-16 z: kzbtzqrkzzcfhvpwjszx\n" +
				"3-9 f: ffwffffffff\n" +
				"13-15 b: bbbmsbbbbbbwbbbbwb\n" +
				"11-14 n: nnnnnnnnnnnnnnv\n" +
				"8-9 v: bvvflvvbjvbtjqrvsxf\n" +
				"1-5 g: gjgnggg\n" +
				"15-16 n: nnnnnnnnnnnnnnwvqnn\n" +
				"12-13 l: lllhllrlllllllll\n" +
				"5-6 l: llmlll\n" +
				"8-9 z: zzqzdzvzzzz\n" +
				"3-4 d: ntdd\n" +
				"4-5 b: sbndbkjxldqpzfbzbqx\n" +
				"2-5 t: bwcfzdlfslsd\n" +
				"4-5 d: dddddd\n" +
				"1-3 t: jtpt\n" +
				"10-12 q: qqqqgqqqqqqmqq\n" +
				"10-19 m: mmmmmmmmmsmmmmmmmgs\n" +
				"2-14 x: xxxxxgxvxmxxxbx\n" +
				"3-13 l: tzljvxspwxdnl\n" +
				"6-10 p: lhnppkpswrppgppzpppn\n" +
				"2-6 j: djgwjjhvpwkcgsfbf\n" +
				"10-11 p: ppppppppprpppppp\n" +
				"7-12 l: lvgcbnlclvjlk\n" +
				"6-11 z: zzzzzzzzzzn\n" +
				"3-5 x: xzvhxnpwbxgxx\n" +
				"4-6 h: hhlrhnl\n" +
				"8-10 b: brngbslhqhb\n" +
				"6-11 s: ssqjxsssdsssss\n" +
				"2-5 s: dbjdjknspbdldfbjd\n" +
				"10-16 z: ctzzzzznzcwzztzl\n" +
				"14-15 j: jjjjjjjjjjjjjjjj\n" +
				"9-13 d: qvdpwddddddddd\n" +
				"10-12 c: ccccchcccccq\n" +
				"3-5 x: xxxxx\n" +
				"13-14 t: ttstttztttttltt\n" +
				"3-16 k: qvfjklgzlwfwnbjklqdx\n" +
				"12-13 g: ggggggtgggggggvg\n" +
				"11-15 j: vqzjvzvxjhjnjtj\n" +
				"7-8 t: tttttttg\n" +
				"6-7 x: xxxxfmn\n" +
				"10-13 m: mmlmmgvfmmmdmmcmmq\n" +
				"7-14 w: wwkwwwwwwwmptwww\n" +
				"17-18 b: bbbbbbbbbbbbbbbbbb\n" +
				"2-8 c: kbvcnrpx\n" +
				"16-17 n: nnnnnnnnpnnnnnngl\n" +
				"10-15 k: kbkjkdkwpkkhkkksklx\n" +
				"2-12 p: pjhdkvwdltvqrhm\n" +
				"2-4 x: vxhxxk\n" +
				"3-5 m: mmfmf\n" +
				"6-8 n: ngnggfnwnn\n" +
				"9-16 b: bnbbbtcbbbvbbrbbbbb\n" +
				"2-8 p: ppphpzppcgptpprhpp\n" +
				"9-12 x: xxxxxxxxxxxx\n" +
				"5-7 d: dknbddd\n" +
				"7-9 r: rrrrrrrrj\n" +
				"3-6 f: fjmfxk\n" +
				"3-4 n: jnnnbjckggnpjnddhx\n" +
				"6-12 d: dznpsfdtdsdndjddvcdd\n" +
				"7-12 x: xxxxxxhqxxxbxbsx\n" +
				"1-2 k: kkfkkkk\n" +
				"4-7 l: lprlqllll\n" +
				"10-13 x: qxxqkjxxxxxxmz\n" +
				"3-8 f: rxfffwcff\n" +
				"4-5 s: cpscszdssc\n" +
				"5-6 t: tttftt\n" +
				"2-6 p: kpvtkprdqhxzpxrbfp\n" +
				"6-12 s: ksnsklwqfssdsbsss\n" +
				"6-7 l: lzlflfl\n" +
				"7-9 t: ttktttttt\n" +
				"8-14 d: hdddzndhsddpfpdxt\n" +
				"8-10 d: ddnddrddvxbzskddzd\n" +
				"11-13 v: mvhvrtvvvvzvrvdv\n" +
				"4-5 g: gggjw\n" +
				"3-6 m: xhmrpm\n" +
				"7-13 k: kkkkkkkkkkkkk\n" +
				"10-13 n: nnnnnnmnngnnnnnn\n" +
				"7-15 m: kcmtmjtmwjmcsbbms\n" +
				"13-16 j: jjjjjjjjjjjjpjjlj\n" +
				"2-4 n: nnmnkn\n" +
				"12-14 j: jjjjjjjjjjjdjj\n" +
				"1-2 c: cchc\n" +
				"9-12 m: mmmqbmlwmmjmzmmml\n" +
				"3-8 r: rrkkrrrt\n" +
				"4-5 k: wckknxkpkktmks\n" +
				"9-10 d: ddddpdddnpx\n" +
				"1-8 q: qmpmxzfqnqctwpprm\n" +
				"10-12 q: sgkfxhqjkqqq\n" +
				"5-11 p: rwpppdlmhpptkbrlp\n" +
				"1-2 d: lzrbxnlcsqhssgdpvjs\n" +
				"5-6 c: scccck\n" +
				"4-5 s: ssshxsssws\n" +
				"3-11 l: nslwgmxmtblzttxxg\n" +
				"10-11 z: zzzzzzzzzzz\n" +
				"7-8 j: bjjjjjqdbjj\n" +
				"7-8 d: glpfjbpdxd\n" +
				"2-4 k: wkvwrk\n" +
				"1-5 c: vtcgcbc\n" +
				"16-17 x: xxxxxxxxxxxxxxxxm\n" +
				"2-11 f: ffffffsfffffffffffff\n" +
				"4-8 m: mrmmmmmmmmmmmm\n" +
				"2-3 x: xvzxx\n" +
				"2-4 h: hxpn\n" +
				"13-17 x: xxxxxxxxxxxrxpxxxx\n" +
				"4-13 q: wcqqtwwmwqqtqmqtqrqq\n" +
				"10-12 d: dddtdqdnjxdzdfdd\n" +
				"13-14 f: jffffsffmffqjff\n" +
				"6-7 d: sxdvddddwqxd\n" +
				"5-11 d: ddddfdddddcdddd\n" +
				"11-14 b: bbbbbbbbbhlbbj\n" +
				"3-4 p: pfpp\n" +
				"4-7 j: jjjgwkgwnfffvfzfl\n" +
				"6-9 l: gknrmjgxtlzcbvkgxb\n" +
				"4-10 n: nnnnnnnnntnnnnnnxn\n" +
				"1-4 r: rjrr\n" +
				"3-4 m: nkmr\n" +
				"9-10 f: fffffffffff\n" +
				"2-6 r: rnhtml\n" +
				"3-12 q: qqkqqqqqqqqkqqqqq\n" +
				"3-10 r: rrnrrrrrrrrrrrrr\n" +
				"6-8 m: bmmmdmmmmdmh\n" +
				"10-12 g: ppdvvxgkhfskfqc\n" +
				"2-10 v: txpvkgvcbqvqfk\n" +
				"1-5 p: tqcvbtp\n" +
				"6-7 z: zzzzzzz\n" +
				"7-16 z: fdjbbkrzjzdbdblhxb\n" +
				"2-4 x: xlkz\n" +
				"4-11 m: glhknzmvqzfmnsbn\n" +
				"6-8 q: qhmdgqvq\n" +
				"8-16 t: tttdtttdtttttttbvt\n" +
				"4-7 v: vptcmgvvjvvvfvvmht\n" +
				"7-8 t: ttttttngt\n" +
				"5-15 t: ztgbttrntttdvgtptjdb\n" +
				"10-12 b: bvndbbbbbbbbbbbbtb\n" +
				"2-3 t: sbqts\n" +
				"12-16 w: wwwwwwwwwwwwwnwww\n" +
				"2-3 m: kmmr\n" +
				"12-15 k: kkmkrkkkkzkdkrgkk\n" +
				"5-13 h: hhhhzhhhhhhhzhhhh\n" +
				"16-17 l: lllllllllllllcllq\n" +
				"1-3 b: bbjb\n" +
				"2-5 t: vtjhmf\n" +
				"4-5 q: vzdqfd\n" +
				"2-3 n: nnnn\n" +
				"7-14 j: jpjjjjjjljjjjjjjj\n" +
				"12-15 j: crsjpjjjqjjfjkv\n" +
				"10-11 t: ttttttttttttt\n" +
				"1-2 j: jjbjsjvhbfssznf\n" +
				"9-13 f: ffmfffffzfffgfff\n" +
				"15-18 g: sgblrqdftpwzggvgqt\n" +
				"19-20 r: rrrrrrrrrrrrrrrrrrvj\n" +
				"1-4 j: mjlt\n" +
				"3-10 x: jxxxxxxxxgxxx\n" +
				"6-7 p: ppptppp\n" +
				"7-8 b: jbmnbmsc\n" +
				"2-6 f: zshfcbhsh\n" +
				"2-5 r: rrrqwtrvclf\n" +
				"3-8 g: chgtwlbg\n" +
				"11-15 n: knnhnnhnnnpnlknnkhnn\n" +
				"3-8 z: zkqlzzpmjph\n" +
				"5-10 b: bbbbhbbbbbbbbbbbbb\n" +
				"6-15 c: ccccdkrncwrcclc\n" +
				"6-7 c: ckcccwdhcc\n" +
				"12-13 t: ntttktttttftttt\n" +
				"5-8 j: jpjzcjjjjj\n" +
				"7-8 k: kpkkksbkkkkkr\n" +
				"9-12 s: sqssssbssxcs\n" +
				"3-7 p: pbbplhtfpktpnppx\n" +
				"5-7 n: ncnnnsn\n" +
				"4-6 s: sssrss\n" +
				"3-16 v: kvvvvvvczvvvvvvvkww\n" +
				"3-6 k: kkkpmk\n" +
				"8-9 h: hhhhhhhmb\n" +
				"7-9 c: czwcccmtckccc\n" +
				"5-7 t: tstglltkt\n" +
				"2-6 c: cccccv\n" +
				"11-14 z: zzzkzqzzzzzvzzz\n" +
				"3-4 d: ddch\n" +
				"4-6 j: jjjjjvj\n" +
				"5-9 c: cccccccccccccc\n" +
				"7-9 j: hjkwpjjbjfsq\n" +
				"4-6 t: tttlcdb\n" +
				"13-15 z: zzzzzzzzzsqzzgzzzkz\n" +
				"8-10 t: ptckdjtsptlmzrktwcw\n" +
				"2-5 n: tnbhnnkvnq\n" +
				"11-12 m: fqmmmmmqmtmkcmmvnwmm\n" +
				"7-8 z: zzzzztvl\n" +
				"6-7 j: jbpjjjjpxmqxcbwsjrjj\n" +
				"2-8 t: ttkdzfwdtflfswlkntt\n" +
				"6-11 j: rnjjjjjjjjj\n" +
				"10-13 f: fwhkbfdfffvfs\n" +
				"5-8 l: pflplntf\n" +
				"3-5 r: rrrrr\n" +
				"16-20 g: gggggggggggggggggggg\n" +
				"1-12 t: xtnsxbjtttxtt\n" +
				"7-9 r: zrrrrrkrnrrrrrrrrr\n" +
				"2-11 n: nrjnnnwnnprnnn\n" +
				"10-12 z: zzzkxzzhznzdz\n" +
				"7-13 d: pdcjdddqddlddcdkdxk\n" +
				"10-15 r: rrrbrrrmvrcphrrqr\n" +
				"1-3 l: rsll\n" +
				"7-8 x: xxxqvrxx\n" +
				"5-14 p: ppcdvppbppppjpbg\n" +
				"5-11 x: dxdxdhkxxvxqxxxfx\n" +
				"6-12 r: pvbfrhqrhftqrrxcrr\n" +
				"4-7 t: zdbtzst\n" +
				"1-13 k: kkkpkghkqkskk\n" +
				"5-17 g: gvggqgggggqmgxggfgg\n" +
				"12-13 l: llllwllllllwf\n" +
				"2-11 b: bbbbbbbbbbbbb\n" +
				"7-17 h: hhhhhhhhhhhhhhhhhh\n" +
				"2-3 w: lwww\n" +
				"2-3 s: vstcs\n" +
				"4-6 s: vssssxss\n" +
				"14-16 r: fmrrrrrrrrrrwwrvrr\n" +
				"6-7 g: dgwgggp\n" +
				"12-16 g: wggxgggrggggzgbgggg\n" +
				"7-9 s: tjhxrscnsscssssnms\n" +
				"3-12 n: nnnnnnnnjnnlnnnnn\n" +
				"7-8 b: zncbmbglqbbbbpgm\n" +
				"12-18 l: lllllllllllvlllllwl\n" +
				"10-16 q: qqqgqfqqqqqrrqgqq\n" +
				"1-7 n: nnnsnrnnnp\n" +
				"10-11 q: qqqqqbqqqqq\n" +
				"5-8 k: bnsfzkkwfkknkccwqkm\n" +
				"4-5 c: ccdcccc\n" +
				"5-9 z: nlsgzzzdz\n" +
				"2-3 k: xkgxhqkpftx\n" +
				"10-13 d: ddddddddhdddddddd\n" +
				"4-6 x: xmjxwxx\n" +
				"14-16 z: zzzzzzzzzzzzzrzq\n" +
				"3-14 n: qnznjvlzdnnrgwfr\n" +
				"7-13 s: sssxsspsssssrz\n" +
				"4-11 k: pkkkcklbkkkk\n" +
				"9-11 x: xxxxxxxxxxtx\n" +
				"4-6 z: zzjzzfz\n" +
				"3-6 d: zddddddz\n" +
				"10-20 c: smccccvcpbccbqcxbccq\n" +
				"13-15 m: mmmmmvmmmjmnnmlpsm\n" +
				"12-13 j: jjjjjjjjjjjjj\n" +
				"3-5 m: pmmbm\n" +
				"7-17 m: mmmmmwcmmdfqrmmhlmm\n" +
				"3-17 g: gcggnggbgdggggggg\n" +
				"10-16 b: sqpsqkfbnbqzswbb\n" +
				"3-4 w: wwwn\n" +
				"5-6 d: dddddpd\n" +
				"12-15 m: mmmmmmdmmmqlmnvms\n" +
				"13-15 j: jjjjjtjjjjjjzjcj\n" +
				"17-18 t: ttttttttjtttttttttt\n" +
				"17-18 d: dcddmchdwbqrllsxjdv\n" +
				"16-17 h: hshghhndnhhfhhhhh\n" +
				"3-5 g: ggggch\n" +
				"6-13 c: ccvccpccccccvcccccc\n" +
				"2-4 h: hbfh\n" +
				"3-9 l: lllptphml\n" +
				"2-3 s: xwrqssfzr\n" +
				"9-13 m: mktmmwmmmmrmmrqmmmcm\n" +
				"8-11 l: rkvxvrglltlc\n" +
				"1-8 q: jfqqxngqnqchq\n" +
				"2-6 x: zxgjxx\n" +
				"5-14 g: mlbgvdglrgcqjgz\n" +
				"4-5 t: tqgtv\n" +
				"2-6 s: ssfnzs\n" +
				"9-11 m: mxwhmmmmmmzm\n" +
				"1-5 d: dbhjzd\n" +
				"2-16 f: fffffffffffkffffff\n" +
				"6-10 b: dbbbbbbbbbbb\n" +
				"5-6 x: xxxmxxcx\n" +
				"7-8 l: lllpllcb\n" +
				"16-17 d: dzdddddddddddddddd\n" +
				"3-10 t: tklkqftnwcj\n" +
				"9-15 g: jccxpmfjgntpptgkkcvt\n" +
				"1-8 r: sdrwgcvprt\n" +
				"4-5 v: nhbwx\n" +
				"17-18 z: zzzzzzzzzzzzzzzzxsz\n" +
				"5-13 l: cbdklqhnklkmwhpp\n" +
				"4-5 f: fffff\n" +
				"1-4 q: qpwq\n" +
				"9-11 x: vxxgrgxxfvvgx\n" +
				"3-4 r: rrfd\n" +
				"3-16 x: sxqxxxxxxxxxxxxwxxx\n" +
				"5-8 s: ssssssss\n" +
				"5-13 b: jbbhqqbblbbbbfcb\n" +
				"17-18 p: ppppppppprpppwppphp\n" +
				"3-9 p: kdpnpfphppvffpwf\n" +
				"7-8 r: jvrrrrrnrpqrn\n" +
				"5-6 m: ncmmdxmm\n" +
				"10-11 v: vvvvvvvvvdl\n" +
				"3-8 g: jlsscggg\n" +
				"1-3 m: gwms\n" +
				"4-5 f: fpkvnf\n" +
				"6-15 j: zfdrxjgxtbkbbjjctmsk\n" +
				"5-6 v: qnnbvvwqvbwqblqd\n" +
				"2-5 v: kvhvt\n" +
				"2-6 x: lxrgsxg\n" +
				"6-8 s: sssxswsjsssxss\n" +
				"1-6 t: dxwjttnqkt\n" +
				"13-14 s: sssssssssssvjg\n" +
				"1-2 t: rtttnf\n" +
				"9-17 h: hjhhhvhhbhhwhhhhphd\n" +
				"2-3 m: mmmwcm\n" +
				"10-12 v: vvvvvvvvvvvv\n" +
				"6-10 j: flcjtzwhwsnjjjrjrj\n" +
				"3-8 d: ddmrddddd\n" +
				"5-8 c: ccccslcrwrc\n" +
				"13-14 s: hpsqtxvkrsssshh\n" +
				"11-15 t: hrxtjgtwtlmpqfx\n" +
				"11-14 l: llbxlsllllqrqlllzlb\n" +
				"3-4 c: crlcvcxwd\n" +
				"3-9 s: vjbsxhwwdvshfxstc\n" +
				"7-8 f: jfhnffbgsfjfwf\n" +
				"15-16 x: ksxqvfsvnxsgvwgpjzl\n" +
				"16-19 g: gggggggggggggggdggg\n" +
				"9-11 h: hhhhhchhzmqhh\n" +
				"2-6 f: kfdlcfrxftzgq\n" +
				"2-8 p: ppnppkpwwmgp\n" +
				"1-2 g: ggfgrg\n" +
				"1-7 n: nnnnnnqwnnn\n" +
				"5-6 h: hzjgthqphwhnjh\n" +
				"10-15 l: xlllllllvlllllll\n" +
				"3-4 l: lzjb\n" +
				"4-6 l: ldzlql\n" +
				"7-12 b: mzlbqzbrqjjbddr\n" +
				"10-13 t: tttttxtttpttt\n" +
				"4-6 d: dddddd\n" +
				"4-5 k: kzvkkrkf\n" +
				"1-2 h: fqml\n" +
				"17-18 f: jhtfdfgmnchprbwbfrf\n" +
				"3-10 s: sjsnsksbdsss\n" +
				"1-17 j: jgcbjmjjjjjcxsjjjjhj\n" +
				"15-16 k: tkjkjbkkrkkkklmgkj\n" +
				"2-13 k: kkkkfkqkkkkkkkkpkk\n" +
				"4-13 d: ddkkdtddzdnnddjdd\n" +
				"1-3 v: fxvnxdvxnrbjs\n" +
				"8-11 s: sssssssssspssssss\n" +
				"1-8 k: khkkkkkkhk\n" +
				"8-9 k: kkktmkkzk\n" +
				"4-9 w: wrqdfwxwnfwwwnmzww\n" +
				"3-5 l: lgtcvl\n" +
				"13-18 n: nnnnnnnnnnqnnnvnnnn\n" +
				"15-17 z: zzzzzzzzzhzzzzqzv\n" +
				"3-5 q: lxnqsvk\n" +
				"8-13 g: rzzwdlzgbcmggct\n" +
				"10-14 d: ddvdbddddnddpd\n" +
				"6-10 d: dphsszqrvz\n" +
				"8-16 b: bnbgfbkbbbqlbcbbgqbb\n" +
				"13-15 d: dddjddmqndddddddvjdd\n" +
				"8-11 q: tnhgqkqfpjhwqgktq\n" +
				"1-3 n: nnnbn\n" +
				"11-12 f: fwfffbfcvflff\n" +
				"7-8 s: sssglsvssvls\n" +
				"2-5 z: zzzzzzzzzqjzz\n" +
				"2-11 m: mhzrwphzxgj\n" +
				"4-6 k: kkkwkd\n" +
				"8-15 m: mrmmmmmzmmmmzmgmm\n" +
				"1-7 w: wwwwwwxwwww\n" +
				"1-3 l: nllllllll\n" +
				"4-5 m: zbshj\n" +
				"3-9 x: xssxqxxxsxkzx\n" +
				"5-9 d: dnpfhsdmrxbvgxqrs\n" +
				"7-12 r: vhrgcnxrvksg\n" +
				"8-10 c: ccccccctzv\n" +
				"16-17 l: lllllllllllllllhl\n" +
				"6-15 n: nnnnnnngnnfhwwzsnnn\n" +
				"6-19 z: zzzzzlzzzzzzzzzzzjv\n" +
				"7-12 b: bxfbbwsjtbbvbqvbmbpb\n" +
				"5-14 g: gggcjggggggggmgg\n" +
				"5-8 d: wdddddkdpd\n" +
				"4-17 x: xtxxxxxxxxxxxxxxmkx\n" +
				"4-10 c: cccccccccjcscv\n" +
				"9-11 v: ttjzfjldvvmswpqt\n" +
				"5-8 r: tjrwhzgfrkgfq\n" +
				"5-8 m: cvhsmnzmncsfmbqmm\n" +
				"7-8 z: krctfpzz\n" +
				"2-6 w: mlnlswvh\n" +
				"12-15 t: zrntxzttqlthfdttt\n" +
				"1-8 r: pkcsjkrrrzxxsfnjw\n" +
				"5-15 p: xbhpkpnngpqvpcwdppg\n" +
				"3-4 t: tttt\n" +
				"8-11 j: wjjjmjjjjjqp\n" +
				"8-14 h: gjslnklhhkhthxh\n" +
				"2-6 q: qqlqqq\n" +
				"3-6 p: pppgppppppppppp\n" +
				"7-11 l: llhllllrllllxldllll\n" +
				"11-12 d: lddkdddddddddd\n" +
				"4-12 z: zwlzzqsvvclw\n" +
				"15-18 c: tflkxflqccwkmlckck\n" +
				"8-12 h: hhhhrhhhhfwhhh\n" +
				"6-9 r: rrrmrjrrvkrrjrdr\n" +
				"8-19 k: tmdlkcktbkkskvkbkbz\n" +
				"1-13 s: ssssssssssssss\n" +
				"4-5 v: xtcfvx\n" +
				"1-15 s: shtssssswghsfsss\n" +
				"2-4 k: krkl\n" +
				"7-12 q: dxqqqhqqqtqj\n" +
				"1-5 r: rrrvvrrrr\n" +
				"5-9 t: ttrttftxztnhtvkcmtth\n" +
				"7-9 g: gngkcggrg\n" +
				"12-13 z: zzzzzzzzzzzbk\n" +
				"3-4 l: lslllc\n" +
				"4-5 t: pqchpk\n" +
				"5-6 h: hhhhml\n" +
				"15-16 h: qhhhhhhhhhhhhhhhhhmh\n" +
				"5-6 d: ddddfs\n" +
				"1-6 d: dthfdzddfnsdddm\n" +
				"14-15 d: ddddddddndhtddsdnddh\n" +
				"5-7 j: qjjjpjplljjc\n" +
				"2-6 q: qqqqqqq\n" +
				"3-4 b: bbbb\n" +
				"5-6 s: ssssjjs\n" +
				"1-6 b: bbbbfb\n" +
				"7-13 m: cpvmbbnnrmzwf\n" +
				"2-4 f: fnhn\n" +
				"6-7 d: gdktlddrdlvmqdtddpzv\n" +
				"4-5 w: cwlww\n" +
				"6-9 z: zmdrgzlmzx\n" +
				"5-10 m: mjbjmmmlmmwxm\n" +
				"2-4 s: ssnhht\n" +
				"8-16 t: tttttttdtttdtttjtt\n" +
				"3-5 z: wxgzhtswb\n" +
				"13-14 l: lllllblpllllchll\n" +
				"4-8 g: vggqzvggnggggggggg\n" +
				"8-9 h: hhthkshhpnhqhgh\n" +
				"4-7 l: hglxklpl\n" +
				"2-3 w: mkmgkwzwmw\n" +
				"6-7 h: hhhhhrz\n" +
				"4-6 f: tdffff\n" +
				"1-7 l: lmfhgqschjqglrvwwnnz\n" +
				"12-13 g: gggggggggggfhggg\n" +
				"2-10 m: ntmmmdjmmmsvmm\n" +
				"1-2 s: sqsssssssssss\n" +
				"3-8 f: frnthfcfxfft\n" +
				"7-16 w: wwwwwwmwwnwwwwmk\n" +
				"11-13 j: jjkjrjjjjjjjjjd\n" +
				"3-4 s: ssvc\n" +
				"3-13 t: ddttttnnqpqztzbbdv\n" +
				"3-5 g: gggggg\n" +
				"3-4 r: rrrzlklfljqvz\n" +
				"5-12 m: kdgmmrszqpfsbmz\n" +
				"11-14 g: gqgvzgxhgbtngxggh\n" +
				"7-16 k: qqptmhhfkhsgkhbmkx\n" +
				"5-8 p: ppppptppp\n" +
				"2-17 x: vxtxpvnxvlctrcpfxxx\n" +
				"2-3 w: bwmwjwwwwwtnqwxc\n" +
				"4-5 g: gggggg\n" +
				"4-7 z: znzwzzzs\n" +
				"4-5 v: kvvvv\n" +
				"10-14 r: rrrrrrrrrbrrrx\n" +
				"4-5 t: xtttt\n" +
				"4-11 w: wwwwwwdcmkrwx\n" +
				"6-7 c: ccccccjc\n" +
				"12-14 w: wkwwwwwwwhwlwwwk\n" +
				"5-9 s: hmssssssssss\n" +
				"2-4 r: rrrjsh\n" +
				"12-13 m: mmmmmmmmmmmmmx\n" +
				"3-5 s: sshsvssss\n" +
				"4-5 l: xllwnshchfdfk\n" +
				"16-18 w: wwpwwpwwwmwwzwwtwwww\n" +
				"2-9 c: zsfgbrrprlszrr\n" +
				"10-11 n: thnmfndnnbnnbhnnnn\n" +
				"13-14 v: vvvcvvvvvvvvvvv\n" +
				"3-4 n: rwcm\n" +
				"14-15 t: tttttztttttttpjt\n" +
				"5-8 j: jjjjwhjdj\n" +
				"5-8 f: fffffffm\n" +
				"6-7 z: zzqzzzz\n" +
				"12-19 j: kjrfnsjnhjdzgqpcjzvh\n" +
				"3-7 p: pxpdvcqdptxpvk\n" +
				"1-4 c: wccqj\n" +
				"1-2 w: pwsqhwjdhcm\n" +
				"2-3 b: bfbn\n" +
				"10-19 q: qqqqqqqqqtqqqsqrqqq\n" +
				"5-8 p: pppppppppppppppp\n" +
				"10-13 g: rjghslgbggkgjdgvp\n" +
				"3-9 z: fnnfzzdnf\n" +
				"9-16 g: tlgtgggggggggggqm\n" +
				"3-12 r: rrrvccrrvbdbncrrr\n" +
				"1-4 v: jvhbv\n" +
				"15-16 v: vvvdvvcvvvpvvvvvvvv\n" +
				"3-4 g: ggggg\n" +
				"7-8 w: wwwwwwmpw\n" +
				"4-9 s: gkxshzkpssc\n" +
				"2-8 q: pqttnpqqqfwqs\n" +
				"3-4 n: nlnp\n" +
				"3-4 h: phhh\n" +
				"16-18 g: hqsxhmmpfgggmgpqhrjg\n" +
				"15-17 w: wwgwwwwwwwwwwwmwl\n" +
				"7-9 x: xxxxxxmxv\n" +
				"1-7 s: ssssssbs\n" +
				"1-3 x: dxlxxx\n" +
				"3-6 p: wpxppq\n" +
				"5-7 c: clcclcfcbccc\n" +
				"3-5 s: sbmst\n" +
				"3-5 d: xqmdn\n" +
				"5-10 z: zbzwfddkzzc\n" +
				"18-19 d: dpdddddddddddddddwcv\n" +
				"3-4 r: rrrr\n" +
				"8-9 f: ffnffffwd\n" +
				"12-16 h: hxnlxghzkpcpdhqqvl\n" +
				"6-7 m: jngclmm\n" +
				"5-13 m: rmmmpmrmmmmmjmmmm\n" +
				"1-3 f: dfwfs\n" +
				"7-8 l: lclllwllll\n" +
				"16-18 j: jjjjjjjjjjpjnjjgjbj\n" +
				"12-15 f: fffffffffffnfftfffff\n" +
				"9-13 w: qbnpmklbwxdbbwkklpwb\n" +
				"1-3 j: mjkj\n" +
				"1-3 h: hphwdh\n" +
				"3-5 x: vrxxb\n" +
				"1-15 v: mvvvvvvvvvvvvvj\n" +
				"8-12 v: lvzfvzvvnjtvvvvvv\n" +
				"4-6 g: gggvggggg\n" +
				"13-20 l: zdllslnlxslqpnvgwlwm\n" +
				"2-4 l: lllhll\n" +
				"3-14 k: kckzddtzbmvkpkhb\n" +
				"4-5 k: hbkgbxzj\n" +
				"2-4 p: lppp\n" +
				"2-11 r: rrrrrbrrbrrrrwrrrwr\n" +
				"4-7 k: knkqkkk\n" +
				"7-8 r: njrrrrrrrr\n" +
				"4-7 h: phhhnhdhc\n" +
				"4-5 r: nsnrrfktwbbhrrrh\n" +
				"4-5 q: qqzvzsvkq\n" +
				"6-7 b: jbbbbbbbkbgb\n" +
				"5-11 f: ffffcfffffgff\n" +
				"12-16 v: vfvvqvvdvvvlhvzjvmzv\n" +
				"9-10 v: vvzvvtvvjgvv\n" +
				"1-7 v: vvvvvvvv\n" +
				"3-12 z: zmncxhrdzdmtcbxtlrzq\n" +
				"3-7 r: rbfbrjrr\n" +
				"12-13 n: nnnnnnnnpnnldnn\n" +
				"5-12 j: jjjjjjjjjjjlj\n" +
				"3-6 s: sssfsgss\n" +
				"13-17 h: hhhfhhhhhhhhvhhhhhhh\n" +
				"5-10 g: gggxkggggvg\n" +
				"1-4 b: hbbtb\n" +
				"14-15 t: tttttttttttttnb\n" +
				"7-14 d: ddddddddddddddhdw\n" +
				"15-16 d: rbdddbdwmjdhmpdd\n" +
				"7-13 s: kxpdntprmskcs\n" +
				"3-5 m: gmdmpj\n" +
				"4-5 j: jjjjjvjsj\n" +
				"1-14 q: qmqqqntqcqxmqsqkqq\n" +
				"4-5 q: tqxqq\n" +
				"8-11 m: mmmxmmmvmmv\n" +
				"5-8 q: qqqqrqqw\n" +
				"5-15 d: dqdddddddddddvspd\n" +
				"1-4 t: tttttt\n" +
				"12-15 z: dhzzxwfjgnzhzxt\n" +
				"8-11 v: kdnmfnmqvvdvqdlvk\n" +
				"16-18 s: sssssssssssssssjstss\n" +
				"2-5 c: ccwdccc\n" +
				"3-11 x: nxjxxxxxxxxxxx\n" +
				"13-15 v: vvvvfvvqvlvvvvvv\n" +
				"4-11 s: sssszssnqjsbsvs\n" +
				"9-18 j: gxhjjjjjnjjsjjjrjjjj\n" +
				"3-4 t: ttfttt\n" +
				"9-10 s: qzgxjhpsss\n" +
				"9-10 q: qqqqnrtxrqqsqqq\n" +
				"6-13 k: kkkrgspkkkpwjshmk\n" +
				"9-12 h: xhhhmhbhhhdhh\n" +
				"3-12 g: gfgggggjgggggggggg\n" +
				"13-15 z: zzfzzszzzzzzvzzzhzw\n" +
				"5-15 m: mzrmmmmndchfzmmrr\n" +
				"5-10 z: zzzzzzmzzh\n" +
				"4-5 s: wscfc\n" +
				"3-6 m: kmmtmm\n" +
				"5-7 h: hhhhchh\n" +
				"4-14 w: wwgwwwqwsfrjvmbwj\n" +
				"2-9 v: vwvvwvmjcqnxv\n" +
				"9-10 x: qxxhfxchrx\n" +
				"17-18 x: xxxxxxxxxxxxxxxxwp\n" +
				"4-6 v: vvvvvtv\n" +
				"11-14 l: lcqlclllllrlddxlzll\n" +
				"1-4 r: trrn\n" +
				"10-13 h: thnsxphnfgvhvq\n" +
				"11-12 s: sssssssssshsss\n" +
				"10-11 z: rqpzszzmzdz\n" +
				"3-6 q: qrqqqq\n" +
				"5-6 h: hhkhhhh\n" +
				"8-16 h: wqhjvhlgwtsgvlpf\n" +
				"3-6 f: gqfpcfsfhfjgrbqv\n" +
				"3-5 l: lqllsrjlv\n" +
				"6-10 v: vvvvvvkvjv\n" +
				"3-8 t: gtttcbnkxxstttd\n" +
				"2-6 j: jjjjbjz\n" +
				"10-15 j: ftwjtfzjmjsvwjj\n" +
				"8-11 d: ddsksddddzfd\n" +
				"2-3 b: bhngbb\n" +
				"14-16 c: mllmcbfxwxrqlcjcw\n" +
				"7-8 q: qqqqqqdv\n" +
				"5-6 s: slkzlfvg\n" +
				"3-5 q: qqqqq\n" +
				"3-12 p: pddpdpszrppxcpjgv\n" +
				"11-18 r: rrrrrrrrrrprqrrrrr\n" +
				"3-4 c: cscckcxcn\n" +
				"11-13 g: gvgwmlngggqggrtgtkg\n" +
				"5-7 b: smfpbbb\n" +
				"7-18 l: sqmlklvkqfjrgtqhzr\n" +
				"6-7 k: kkpqqfgz\n" +
				"7-9 n: fnndnnxnndn\n" +
				"11-12 c: ccccccccccxv\n" +
				"6-9 d: dddvdddddddvd\n" +
				"1-4 v: nvjfv\n" +
				"3-8 g: gggggggg\n" +
				"6-7 x: xxxxxxx\n" +
				"4-5 c: scccc\n" +
				"6-13 q: nchlfqqqlqnqqqtq\n" +
				"1-8 r: srrrrrrnrrrrrrr\n" +
				"4-5 p: sdpscpppp\n" +
				"9-11 r: rrrdrkrrrqg\n" +
				"3-5 m: xwmscmmm\n" +
				"7-11 r: rrrfrrrrkfrvrf\n" +
				"4-6 b: bbfbbb\n" +
				"4-9 v: rblrvrvpvz\n" +
				"2-8 w: wwwvwgwww\n" +
				"8-9 w: wwwwwwwwzw\n" +
				"16-18 t: ttttttttttttttttttt\n" +
				"8-9 n: nfrzsdjxr\n" +
				"1-2 w: wwfwwmfbww\n" +
				"2-4 s: dfsksft\n" +
				"1-13 d: dwdrccnddqmndcl\n" +
				"1-4 f: qfflf\n" +
				"3-7 d: dnddjpdfgc\n" +
				"3-5 l: lkglx\n" +
				"1-13 j: jjjjgbjtpjjhd\n" +
				"15-16 j: jjjjjjvjjjjjjjjjdjj\n" +
				"4-13 t: tttxztttttttkt\n" +
				"4-5 m: mmmmm\n" +
				"4-6 j: jgjrjwjfjx\n" +
				"8-11 t: ttbjttvttsttdqtn\n" +
				"14-15 w: wwwwwwwwwwwwwkjw\n" +
				"1-6 x: nrscxn\n" +
				"3-13 w: wwwwwvgnwbwwwwwwv\n" +
				"12-15 p: pppppppppppjpppppppp\n" +
				"5-11 v: vvvkvbvwkwnvvvsxvv\n" +
				"1-11 m: mmmmmmmmmmtm\n" +
				"10-11 c: cccccccccwc\n" +
				"5-8 f: xvgstwfxfhxknds\n" +
				"12-13 t: tttttztttttttt\n" +
				"4-17 k: vkpkfkkkqnkqnkgkkknk\n" +
				"9-12 k: kkwpzpdzxhxk\n" +
				"14-17 v: fgvvvvvvvvvvvjpvv\n" +
				"7-8 k: kndnqkkk\n" +
				"7-11 c: gfcbccccjvcskcmrcxc\n" +
				"4-5 n: ngtnr\n" +
				"3-12 k: mtcfszkdhkdkd";
	}

}
