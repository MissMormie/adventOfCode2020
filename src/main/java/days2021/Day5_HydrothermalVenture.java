package days2021;

import helpers.Coordinate;
import helpers.Line2D;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Day5_HydrothermalVenture {
	public static void main(String[] args) {
		System.out.println("answer A: " + runA(textInput()));
		System.out.println("answer B: " + runB(textInput()));
	}

	public static long runA(String input) {
		return countCrossingVents(input, false);
	}

	public static long runB(String input) {
		return countCrossingVents(input, true);
	}

	private static long countCrossingVents(String input, boolean includeDiagonals) {
		Map<Coordinate, Integer> coordinateCountMap = new HashMap<>();
		Stream<Line2D> line2DStream = Arrays.stream(input.split("\n")).map(Line2D::new);
		if(!includeDiagonals) {
			 line2DStream = line2DStream.filter(Line2D::isHorizontalOrVertical);
		}
		line2DStream.forEach(line2D -> addCoordinatesToMap(line2D, coordinateCountMap));

		return coordinateCountMap.entrySet().stream()
				.filter(entry -> entry.getValue() > 1)
				.count();
	}

	public static void addCoordinatesToMap(Line2D line, Map<Coordinate, Integer> coordinateCountMap) {
		line.getCoordinatesOnStraightOr45DegreeLine().forEach(coord -> {
			if (coordinateCountMap.containsKey(coord)) {
				coordinateCountMap.put(coord, coordinateCountMap.get(coord) + 1);
			} else {
				coordinateCountMap.put(coord, 1);
			}
		});
	}

	private static String textInput() {
		return "348,742 -> 620,742\n" +
				"494,864 -> 494,484\n" +
				"193,136 -> 301,136\n" +
				"342,692 -> 342,538\n" +
				"234,525 -> 102,393\n" +
				"72,964 -> 847,189\n" +
				"639,430 -> 99,970\n" +
				"398,791 -> 398,187\n" +
				"181,144 -> 205,168\n" +
				"607,617 -> 416,617\n" +
				"98,339 -> 98,437\n" +
				"763,119 -> 176,119\n" +
				"450,848 -> 450,664\n" +
				"293,133 -> 293,98\n" +
				"308,98 -> 308,15\n" +
				"157,21 -> 405,269\n" +
				"792,471 -> 409,471\n" +
				"42,955 -> 956,41\n" +
				"895,269 -> 895,884\n" +
				"629,635 -> 392,635\n" +
				"169,23 -> 273,127\n" +
				"250,847 -> 250,665\n" +
				"709,56 -> 709,626\n" +
				"938,599 -> 669,868\n" +
				"913,46 -> 458,501\n" +
				"844,417 -> 844,424\n" +
				"178,651 -> 640,189\n" +
				"226,266 -> 149,266\n" +
				"654,421 -> 654,499\n" +
				"368,780 -> 368,38\n" +
				"183,743 -> 497,429\n" +
				"942,34 -> 387,589\n" +
				"987,461 -> 987,348\n" +
				"485,275 -> 330,275\n" +
				"33,460 -> 456,883\n" +
				"606,304 -> 606,628\n" +
				"755,170 -> 755,187\n" +
				"342,862 -> 250,954\n" +
				"866,125 -> 151,840\n" +
				"795,489 -> 599,685\n" +
				"905,525 -> 905,457\n" +
				"99,620 -> 99,339\n" +
				"899,507 -> 699,707\n" +
				"627,444 -> 923,444\n" +
				"798,80 -> 419,459\n" +
				"580,237 -> 580,907\n" +
				"947,336 -> 947,727\n" +
				"491,466 -> 546,411\n" +
				"335,221 -> 458,221\n" +
				"547,70 -> 407,210\n" +
				"520,80 -> 647,80\n" +
				"835,58 -> 835,300\n" +
				"574,239 -> 589,239\n" +
				"219,670 -> 219,22\n" +
				"512,69 -> 255,69\n" +
				"83,701 -> 239,701\n" +
				"874,109 -> 48,935\n" +
				"980,892 -> 159,71\n" +
				"154,879 -> 53,879\n" +
				"782,37 -> 89,730\n" +
				"481,736 -> 481,614\n" +
				"377,886 -> 569,694\n" +
				"777,403 -> 726,454\n" +
				"728,952 -> 728,972\n" +
				"495,643 -> 879,643\n" +
				"425,564 -> 187,326\n" +
				"443,110 -> 386,167\n" +
				"529,279 -> 529,103\n" +
				"985,983 -> 16,14\n" +
				"145,112 -> 877,844\n" +
				"914,34 -> 121,827\n" +
				"409,229 -> 784,229\n" +
				"719,86 -> 479,86\n" +
				"929,984 -> 218,984\n" +
				"985,928 -> 299,242\n" +
				"168,159 -> 168,743\n" +
				"189,784 -> 189,498\n" +
				"401,58 -> 390,58\n" +
				"47,283 -> 452,688\n" +
				"246,292 -> 246,461\n" +
				"869,901 -> 643,675\n" +
				"879,593 -> 108,593\n" +
				"707,417 -> 336,417\n" +
				"99,401 -> 99,502\n" +
				"583,764 -> 583,668\n" +
				"493,934 -> 493,78\n" +
				"643,570 -> 643,990\n" +
				"278,765 -> 600,443\n" +
				"940,437 -> 940,488\n" +
				"812,896 -> 812,594\n" +
				"727,711 -> 25,711\n" +
				"173,586 -> 667,92\n" +
				"169,879 -> 466,582\n" +
				"361,650 -> 361,276\n" +
				"725,818 -> 725,662\n" +
				"48,119 -> 781,852\n" +
				"921,62 -> 921,647\n" +
				"135,613 -> 135,295\n" +
				"702,969 -> 194,461\n" +
				"306,709 -> 274,709\n" +
				"117,182 -> 117,856\n" +
				"608,635 -> 608,854\n" +
				"448,250 -> 448,641\n" +
				"635,552 -> 341,552\n" +
				"528,432 -> 51,432\n" +
				"711,763 -> 987,487\n" +
				"43,931 -> 917,57\n" +
				"980,841 -> 526,387\n" +
				"279,412 -> 377,412\n" +
				"334,107 -> 509,282\n" +
				"164,15 -> 164,345\n" +
				"708,139 -> 708,846\n" +
				"323,930 -> 323,282\n" +
				"256,902 -> 922,236\n" +
				"586,453 -> 244,111\n" +
				"21,92 -> 840,911\n" +
				"282,888 -> 839,331\n" +
				"278,439 -> 712,873\n" +
				"110,595 -> 696,595\n" +
				"95,531 -> 804,531\n" +
				"800,206 -> 800,881\n" +
				"396,244 -> 396,441\n" +
				"334,60 -> 122,60\n" +
				"640,331 -> 640,467\n" +
				"757,126 -> 731,126\n" +
				"430,245 -> 932,245\n" +
				"160,655 -> 753,62\n" +
				"502,432 -> 751,432\n" +
				"434,378 -> 434,287\n" +
				"907,374 -> 552,729\n" +
				"548,864 -> 632,864\n" +
				"616,961 -> 746,831\n" +
				"116,316 -> 666,866\n" +
				"78,44 -> 950,916\n" +
				"873,570 -> 355,570\n" +
				"202,859 -> 885,176\n" +
				"449,348 -> 904,348\n" +
				"143,179 -> 760,796\n" +
				"478,823 -> 478,55\n" +
				"790,446 -> 790,257\n" +
				"34,888 -> 736,888\n" +
				"630,696 -> 437,889\n" +
				"350,589 -> 118,821\n" +
				"803,373 -> 808,373\n" +
				"490,778 -> 818,450\n" +
				"487,725 -> 487,836\n" +
				"794,502 -> 794,114\n" +
				"136,743 -> 136,634\n" +
				"572,103 -> 194,103\n" +
				"488,614 -> 528,614\n" +
				"18,878 -> 539,357\n" +
				"889,35 -> 889,271\n" +
				"436,419 -> 21,419\n" +
				"422,319 -> 422,821\n" +
				"176,935 -> 923,188\n" +
				"398,398 -> 436,436\n" +
				"327,45 -> 820,538\n" +
				"643,428 -> 890,675\n" +
				"743,714 -> 143,114\n" +
				"203,359 -> 189,359\n" +
				"346,672 -> 346,190\n" +
				"263,804 -> 263,249\n" +
				"93,683 -> 93,453\n" +
				"19,180 -> 238,399\n" +
				"47,941 -> 888,100\n" +
				"525,888 -> 236,599\n" +
				"624,397 -> 126,397\n" +
				"382,119 -> 382,981\n" +
				"972,753 -> 336,117\n" +
				"682,978 -> 48,344\n" +
				"639,477 -> 639,408\n" +
				"545,966 -> 12,966\n" +
				"275,234 -> 401,234\n" +
				"912,20 -> 101,20\n" +
				"54,109 -> 809,864\n" +
				"242,149 -> 242,283\n" +
				"664,353 -> 828,353\n" +
				"117,539 -> 26,630\n" +
				"794,554 -> 624,554\n" +
				"828,536 -> 43,536\n" +
				"746,717 -> 746,420\n" +
				"540,930 -> 224,930\n" +
				"862,229 -> 862,212\n" +
				"492,917 -> 835,574\n" +
				"960,496 -> 960,618\n" +
				"148,530 -> 148,485\n" +
				"415,974 -> 494,974\n" +
				"694,118 -> 654,158\n" +
				"672,210 -> 672,81\n" +
				"548,692 -> 668,812\n" +
				"891,50 -> 10,931\n" +
				"639,652 -> 844,652\n" +
				"579,522 -> 556,545\n" +
				"231,880 -> 170,880\n" +
				"443,12 -> 443,775\n" +
				"562,185 -> 562,984\n" +
				"619,313 -> 74,858\n" +
				"371,502 -> 642,231\n" +
				"969,694 -> 714,949\n" +
				"943,157 -> 179,921\n" +
				"821,305 -> 821,427\n" +
				"782,579 -> 874,671\n" +
				"378,943 -> 378,956\n" +
				"315,636 -> 572,893\n" +
				"544,357 -> 315,357\n" +
				"51,158 -> 16,158\n" +
				"151,428 -> 508,428\n" +
				"818,939 -> 90,939\n" +
				"99,396 -> 451,396\n" +
				"602,834 -> 695,834\n" +
				"974,145 -> 974,919\n" +
				"327,630 -> 254,630\n" +
				"858,166 -> 900,166\n" +
				"537,681 -> 564,681\n" +
				"672,674 -> 672,619\n" +
				"544,408 -> 750,614\n" +
				"84,664 -> 210,664\n" +
				"204,886 -> 469,886\n" +
				"475,940 -> 475,592\n" +
				"868,922 -> 614,922\n" +
				"987,939 -> 367,939\n" +
				"854,498 -> 956,498\n" +
				"863,148 -> 22,989\n" +
				"565,580 -> 948,197\n" +
				"231,952 -> 798,385\n" +
				"935,832 -> 113,10\n" +
				"631,362 -> 631,329\n" +
				"405,143 -> 303,143\n" +
				"807,831 -> 891,831\n" +
				"450,976 -> 450,247\n" +
				"488,676 -> 508,676\n" +
				"170,328 -> 636,794\n" +
				"626,815 -> 626,938\n" +
				"217,558 -> 708,558\n" +
				"598,549 -> 532,549\n" +
				"824,307 -> 304,307\n" +
				"539,932 -> 390,932\n" +
				"542,786 -> 435,893\n" +
				"735,42 -> 389,388\n" +
				"677,448 -> 65,448\n" +
				"929,915 -> 29,15\n" +
				"870,344 -> 870,287\n" +
				"187,138 -> 754,138\n" +
				"729,353 -> 508,132\n" +
				"919,821 -> 317,219\n" +
				"272,459 -> 272,72\n" +
				"599,413 -> 342,413\n" +
				"919,862 -> 199,142\n" +
				"229,476 -> 893,476\n" +
				"438,516 -> 150,228\n" +
				"914,132 -> 173,873\n" +
				"905,815 -> 315,815\n" +
				"766,136 -> 766,207\n" +
				"548,546 -> 451,643\n" +
				"836,231 -> 381,231\n" +
				"484,854 -> 852,854\n" +
				"736,209 -> 307,209\n" +
				"751,156 -> 809,156\n" +
				"659,937 -> 659,333\n" +
				"74,899 -> 485,488\n" +
				"843,329 -> 843,584\n" +
				"793,944 -> 910,827\n" +
				"67,298 -> 610,841\n" +
				"43,15 -> 960,932\n" +
				"633,527 -> 912,806\n" +
				"782,246 -> 716,312\n" +
				"30,46 -> 912,928\n" +
				"473,833 -> 473,189\n" +
				"903,506 -> 61,506\n" +
				"85,404 -> 85,898\n" +
				"944,682 -> 944,465\n" +
				"420,695 -> 391,666\n" +
				"560,110 -> 810,360\n" +
				"246,368 -> 25,368\n" +
				"399,397 -> 781,15\n" +
				"452,618 -> 452,340\n" +
				"467,854 -> 890,854\n" +
				"77,314 -> 77,780\n" +
				"523,501 -> 523,646\n" +
				"67,284 -> 637,284\n" +
				"88,13 -> 988,913\n" +
				"173,20 -> 844,691\n" +
				"667,821 -> 722,821\n" +
				"384,840 -> 792,432\n" +
				"270,36 -> 270,352\n" +
				"311,16 -> 311,334\n" +
				"736,900 -> 736,264\n" +
				"613,827 -> 748,962\n" +
				"41,81 -> 885,925\n" +
				"408,884 -> 756,884\n" +
				"380,612 -> 402,634\n" +
				"82,645 -> 82,708\n" +
				"934,331 -> 316,331\n" +
				"634,116 -> 634,891\n" +
				"942,56 -> 942,642\n" +
				"441,260 -> 955,774\n" +
				"925,980 -> 314,369\n" +
				"317,893 -> 577,893\n" +
				"362,137 -> 433,137\n" +
				"434,928 -> 434,623\n" +
				"819,18 -> 819,488\n" +
				"185,348 -> 615,778\n" +
				"895,357 -> 492,357\n" +
				"742,645 -> 742,151\n" +
				"522,855 -> 366,699\n" +
				"867,390 -> 57,390\n" +
				"95,566 -> 389,272\n" +
				"887,793 -> 887,871\n" +
				"351,251 -> 351,30\n" +
				"309,448 -> 865,448\n" +
				"190,690 -> 70,570\n" +
				"383,805 -> 383,607\n" +
				"140,157 -> 140,164\n" +
				"685,222 -> 705,222\n" +
				"192,949 -> 192,358\n" +
				"856,768 -> 856,967\n" +
				"337,344 -> 771,344\n" +
				"919,86 -> 53,952\n" +
				"137,723 -> 887,723\n" +
				"33,108 -> 873,948\n" +
				"195,690 -> 195,184\n" +
				"136,505 -> 136,520\n" +
				"509,69 -> 509,800\n" +
				"255,742 -> 255,740\n" +
				"650,105 -> 650,483\n" +
				"985,451 -> 268,451\n" +
				"68,346 -> 171,346\n" +
				"472,28 -> 574,28\n" +
				"660,308 -> 660,382\n" +
				"341,553 -> 341,419\n" +
				"430,304 -> 807,304\n" +
				"333,258 -> 795,258\n" +
				"853,637 -> 252,36\n" +
				"881,893 -> 237,249\n" +
				"552,160 -> 826,160\n" +
				"728,446 -> 728,905\n" +
				"529,326 -> 529,860\n" +
				"564,759 -> 564,737\n" +
				"533,688 -> 78,233\n" +
				"445,314 -> 329,198\n" +
				"935,597 -> 652,597\n" +
				"955,958 -> 174,177\n" +
				"178,522 -> 178,457\n" +
				"124,351 -> 74,351\n" +
				"265,840 -> 451,654\n" +
				"771,914 -> 771,332\n" +
				"72,14 -> 72,574\n" +
				"970,828 -> 197,55\n" +
				"631,744 -> 631,746\n" +
				"765,759 -> 119,113\n" +
				"260,882 -> 262,882\n" +
				"676,760 -> 61,145\n" +
				"680,168 -> 959,168\n" +
				"466,811 -> 466,745\n" +
				"566,242 -> 566,471\n" +
				"768,975 -> 768,75\n" +
				"391,550 -> 391,591\n" +
				"781,678 -> 771,678\n" +
				"731,620 -> 951,620\n" +
				"973,290 -> 973,664\n" +
				"838,691 -> 835,691\n" +
				"294,240 -> 378,240\n" +
				"390,708 -> 908,190\n" +
				"521,933 -> 963,933\n" +
				"358,46 -> 919,46\n" +
				"431,410 -> 431,297\n" +
				"833,706 -> 330,706\n" +
				"13,910 -> 876,910\n" +
				"619,567 -> 619,398\n" +
				"165,779 -> 165,941\n" +
				"21,139 -> 21,535\n" +
				"921,420 -> 358,420\n" +
				"629,111 -> 608,111\n" +
				"654,244 -> 654,500\n" +
				"982,386 -> 982,841\n" +
				"252,127 -> 144,235\n" +
				"144,518 -> 144,344\n" +
				"581,589 -> 130,138\n" +
				"927,835 -> 882,790\n" +
				"859,658 -> 320,119\n" +
				"110,326 -> 110,168\n" +
				"149,64 -> 917,832\n" +
				"651,771 -> 897,771\n" +
				"788,66 -> 231,623\n" +
				"710,608 -> 370,268\n" +
				"927,175 -> 778,26\n" +
				"957,970 -> 53,66\n" +
				"350,313 -> 350,192\n" +
				"115,505 -> 129,505\n" +
				"414,163 -> 182,163\n" +
				"874,334 -> 532,676\n" +
				"441,790 -> 441,976\n" +
				"66,162 -> 226,162\n" +
				"839,453 -> 310,982\n" +
				"63,974 -> 132,974\n" +
				"51,383 -> 297,137\n" +
				"343,954 -> 262,873\n" +
				"726,474 -> 637,563\n" +
				"449,205 -> 449,279\n" +
				"855,845 -> 722,845\n" +
				"28,750 -> 33,750\n" +
				"272,175 -> 212,235\n" +
				"428,172 -> 710,172\n" +
				"824,834 -> 824,557\n" +
				"25,864 -> 855,34\n" +
				"66,425 -> 84,425\n" +
				"456,812 -> 456,785\n" +
				"286,294 -> 73,294\n" +
				"400,329 -> 525,204\n" +
				"888,160 -> 888,124\n" +
				"879,742 -> 761,742\n" +
				"893,751 -> 858,786\n" +
				"621,765 -> 10,154\n" +
				"162,280 -> 162,889\n" +
				"96,830 -> 646,280\n" +
				"646,720 -> 250,324\n" +
				"367,586 -> 695,258\n" +
				"298,353 -> 566,621\n" +
				"813,256 -> 813,367\n" +
				"559,672 -> 471,672\n" +
				"951,187 -> 234,904\n" +
				"868,753 -> 80,753\n" +
				"91,937 -> 803,225\n" +
				"112,142 -> 18,236\n" +
				"936,281 -> 936,976\n" +
				"95,867 -> 753,209\n" +
				"421,735 -> 833,735\n" +
				"830,755 -> 963,755\n" +
				"355,272 -> 355,70\n" +
				"309,479 -> 309,45\n" +
				"951,145 -> 676,145\n" +
				"513,143 -> 713,143\n" +
				"491,565 -> 988,68\n" +
				"797,742 -> 596,541\n" +
				"581,621 -> 277,317\n" +
				"132,480 -> 101,480\n" +
				"140,72 -> 375,72\n" +
				"524,221 -> 897,594\n" +
				"694,549 -> 179,34\n" +
				"689,729 -> 689,527\n" +
				"27,953 -> 27,32\n" +
				"913,774 -> 913,705\n" +
				"669,861 -> 669,360\n" +
				"454,250 -> 454,344\n" +
				"863,95 -> 13,945\n" +
				"341,50 -> 341,795\n" +
				"863,50 -> 212,50\n" +
				"73,689 -> 143,689\n" +
				"944,20 -> 31,933\n" +
				"22,988 -> 873,137\n" +
				"798,552 -> 798,774\n" +
				"935,583 -> 935,393\n" +
				"462,176 -> 986,176\n" +
				"739,400 -> 972,400\n" +
				"441,31 -> 441,37\n" +
				"628,381 -> 628,339\n" +
				"545,345 -> 615,345\n" +
				"91,736 -> 373,736\n" +
				"825,738 -> 825,687\n" +
				"541,877 -> 541,569\n" +
				"750,739 -> 390,739\n" +
				"873,384 -> 873,665\n" +
				"597,61 -> 385,61\n" +
				"518,62 -> 229,62\n" +
				"623,192 -> 502,192\n" +
				"163,861 -> 732,292\n" +
				"748,304 -> 873,429\n" +
				"375,436 -> 375,655\n" +
				"146,228 -> 146,91\n" +
				"808,844 -> 243,844\n" +
				"174,115 -> 982,923\n" +
				"598,384 -> 598,956\n" +
				"932,736 -> 843,736\n" +
				"772,110 -> 883,110\n" +
				"298,960 -> 852,406\n" +
				"744,772 -> 693,823\n" +
				"605,639 -> 731,639\n" +
				"126,770 -> 732,164\n" +
				"13,986 -> 988,11\n" +
				"756,37 -> 756,164\n" +
				"132,900 -> 984,48\n" +
				"806,524 -> 331,524\n" +
				"84,645 -> 84,195\n" +
				"329,447 -> 939,447\n" +
				"684,566 -> 793,566\n" +
				"747,595 -> 747,181\n" +
				"293,121 -> 430,121\n" +
				"42,918 -> 826,918\n" +
				"32,312 -> 833,312\n" +
				"42,309 -> 42,752\n" +
				"332,74 -> 736,74\n" +
				"699,209 -> 495,209\n" +
				"297,113 -> 297,827\n" +
				"515,973 -> 515,35\n" +
				"774,951 -> 774,290\n" +
				"471,921 -> 919,921\n" +
				"297,282 -> 297,524\n" +
				"477,825 -> 477,343\n" +
				"220,665 -> 351,796\n" +
				"483,128 -> 376,128";

	}

}
