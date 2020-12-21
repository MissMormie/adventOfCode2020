package days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day21_AllergenAssessment {

	public static void main(String[] args) {
		System.out.println("answer A: " + runA(textInput()));
		System.out.println("answer B: " + runB(textInput()));
	}

	public static long runA(String input) {
		Map<String, Allergen> allergenMap = getAllergenMap(input);
		List<String> ingredientWithAllergen = determineIngredientWithAllergen(allergenMap);
		// remove ingredients with allergens from below list.

		return Arrays.stream(input.split("( |\n|\\(contains.*)"))
				.filter(ingredient -> !ingredient.equals(""))
				.filter(ingredient -> !ingredientWithAllergen.stream().filter(allergen -> allergen.equals(ingredient)).findAny().isPresent())
		.count();
	}

	private static List<String> determineIngredientWithAllergen(Map<String, Allergen> allergenMap) {
		boolean allFound = false;
		do {
			List<String> allergenIngredients = allergenMap.values().stream()
					.filter(allergen -> allergen.ingredient != null)
					.map(allergen -> allergen.ingredient)
					.collect(Collectors.toList());

			allergenMap.values().stream()
					.forEach(allergen -> allergen.findIngredient(allergenIngredients));

			allFound = allergenMap.values().stream().allMatch(allergen -> allergen.hasIngredient());

			if(allFound) {
				return allergenMap.values().stream()
						.filter(allergen -> allergen.ingredient != null)
						.map(allergen -> allergen.ingredient)
						.collect(Collectors.toList());
			}

		} while(true);
	}

	private static Map<String, Allergen> getAllergenMap(String input) {
		Map<String, Allergen> allergenMap = new HashMap<>();
		Arrays.stream(input.split("\n"))
				.forEach(s -> {
					String[] split = s.split("(\\(contains |\\)|, )");
					IntStream.range(1, split.length)
							.forEach(i -> {
								if(allergenMap.containsKey(split[i])) {
									allergenMap.get(split[i]).addRecipe(split[0]);
								} else {
									allergenMap.put(split[i], new Allergen(split[i], split[0]));
								}
							});

				});
		return allergenMap;
	}

	public static class Allergen {
		String name;
		List<String> recipe = new ArrayList<>();
		String ingredient;
		List<String> possibleIngredients;

		public Allergen(String name, String recipe) {
			this.name = name;
			addRecipe(recipe);
		}

		public void addRecipe(String recipe) {
			this.recipe.add(recipe);
		}

		public boolean hasIngredient() {
			return ingredient != null;
		}

		public boolean findIngredient(List<String> excluding) {
			if(hasIngredient()) {
				return true;
			}
			if(possibleIngredients == null) {
				String base = recipe.get(0);
				// remove the ingredients that contain/are another allergen
				for (int i = 0; i < excluding.size(); i++) {
					base = base.replace(excluding + " ", "");
				}
				String[] parts = base.split(" ");

				possibleIngredients = Arrays.stream(parts).filter(part ->
						IntStream.range(1, recipe.size())
								.allMatch(recipe2 ->
										Arrays.stream(recipe.get(recipe2).split(" ")).filter(ingredient -> ingredient.equals(part)).findFirst().isPresent())
								)
						.collect(Collectors.toList());
			}

			possibleIngredients.removeAll(excluding);

			if(possibleIngredients.size() == 1) {
				ingredient = possibleIngredients.get(0);
				return true;
			}

			return false;

		}
	}

	public static String runB(String input) {
		Map<String, Allergen> allergenMap = getAllergenMap(input);
		determineIngredientWithAllergen(allergenMap);
		return allergenMap.keySet().stream().sorted().map(allergen -> allergenMap.get(allergen).ingredient).collect(Collectors.joining(","));
	}

	private static String textInput() {
		return "rlc lbmf frqz rtxgv ctzz dfkbnvp trnd nbtdp grj sthnsg hbqgv bltlff knqh pzznzt nbgvrls pdjkbf sxbjr mhrp pmqm vthsdx zqtgm pmh dnfgghrh grnrr zvbm qghp zqvthqt vgq qjdqhx ndcssdm cqbp pmdb nkz qvhq tlnz jptplj svr zqtlns lrsbsl mlsmqjln bjpkhx zgqf qfdh shmnj thn rfbtj snhph kjzl xgmrx frvx nsnqf dtlgvqjd bhrj hzpk jjpgkn rvqdx mkczhrp dpmp cdqr qrbnjtj ftllkgg mlpr rfghfc xrdq zccsfk hcdz rrsct qtqmdq rnmxqtx vcqghl nxj gsskhx rljfl ksqfmnl mrgz dbhfd qgqqvxlj sqr hxclz mkpjrjf xmfg (contains soy, sesame, dairy)\n" +
				"rspj hkndh zmfqpn nlqj chcgq dxnn xmfg rtxgv hcdz sthnsg qdxzlz dbhfd bfqr rprqg pmnzl snhph ngss nbgvrls qtqmdq lbdm mhrp qrbjb blq rzdgnv phkvc zdlp bbch jjpgkn lxvknl ccqf grnrr bchq vnschhzm hxclz bjpkhx jptplj qgqqvxlj hzpk bfszscr gdl nbqpf sqr qxqjpk kjzl nxj hprhbs qrbnjtj jcgkg zgqf pgqs ftllkgg zvpzkr htc vfblds rvqdx nkz xrv mlsmqjln pmdb pzfhg zqtgm pdjkbf phbz dtktjd thn jctbdm rlc qzcrtv cdqr bgpbjps hkngs dlhzp pbsv grjdnml ccjq bltlff dcrq khtmm nlltl (contains peanuts)\n" +
				"vnschhzm mhrp bhxd vfblds bchq xdz pdjkbf ggvgpc jcgkg nbtdp ksqfmnl hcdz sqr thn bjpkhx pmqm zccsfk bfqr nlqj rspj dpbq hbqgv frvx ndcssdm xnbvzq vvfcqdd jhjc rprqg vgq nsnqf bbch grnrr pzznzt fqjrtbm zjpbb qrbjb pxb xprjs qxqjpk qzfk snhph sthnsg xrdq hzpk htc bgpbjps mkczhrp zmfqpn bftz qtqmdq bhrj dcrq zvpzkr pgqs rfghfc zgqf rtxgv nbqpf hxclz hsmlvs qrbnjtj zvbm xnbcq plgvd ccqf lrsbsl rzdgnv knqh dpmp lxvknl xcfl qgqqvxlj ffjxl frqz (contains nuts, soy)\n" +
				"pmqm hcdz bchq grjdnml thn dbhfd phkvc ncvh rfbtj pzznzt vbqfd qghp slpfzvv zmfqpn bbch xrv snhph pvv vcqghl qzfk mhts ngss jqrzxr dlxf mkczhrp bgpbjps vvfcqdd khk pjmf jhjc mqxzt nsnqf mlsmqjln rdddzf bjpkhx xgmrx plgvd bltlff rfghfc zsnnbmb sbqf vfpdlb lxvknl rnjfd rnmxqtx nbqpf nnfnm grnrr sthnsg mkpjrjf kjzl qdxzlz ggvgpc nlltl zccsfk vvm (contains dairy)\n" +
				"dpbq cdqr xnbvzq nxj dvz zmfqpn zvpzkr xrv qzfk vnschhzm mrgz rtxgv vcqghl zsnnbmb grnrr tlnz ctzz plgvd pmnzl ggvgpc dbhfd svr mlsmqjln mkpjrjf lxvknl gdl snhph zgqf zvbm dfkbnvp jctbdm rvqdx xprjs cqbp ksqfmnl dctmr nsnqf vfpdlb lbmf pbsv fqjrtbm ffjxl dmqnlv pmdb qrbnjtj thn bjpkhx vvm bfszscr ptn khtmm pmh (contains nuts, soy)\n" +
				"zmfqpn snhph qxqjpk hcdz qvgfgzl thn qghp bfszscr sthnsg zvpzkr mlpr gdl xgmrx pmqm vthsdx bjpkhx vhphlsj trnd xlbnk lrsbsl nnfnm bltlff rprqg dlxf pmdb sthns pxb djtr qtqmdq dbhfd plkn rljfl dvz njtkz khk qzfk nsnqf pgqs bhxd zjpbb vkj (contains fish, soy)\n" +
				"phkvc jjpgkn dtlgvqjd zqvthqt vhphlsj hkngs xlbnk zqtgm bbch svr qrbnjtj pvv xnbvzq xrv ctzz vfblds zvbm phbz zmfqpn hxclz ptn snhph frqz pxvl rprqg nsnqf qvgfgzl chcgq vcqghl scvmh shmnj zjpbb kllsm qnmr hxzfxg ccqf ngss trnd cnvv blq dbhfd nlqj vnschhzm jcgkg xmfg rbvhh gpbbm nxj jqrzxr sthns thn sthnsg xdz hcdz vkj tbffk lndmtz zvpzkr khk qdxzlz (contains peanuts, wheat)\n" +
				"vfblds plgvd nnfnm dctmr mhrp nbvn sqr pmnzl jhjc nsnqf xnbvzq ctzz bkrd dnfgghrh sthnsg pmh plkn mlsmqjln dlhzp mqxzt cnvv qvgfgzl qrbnjtj zmfqpn ngss pgqs grjdnml xrv dbhfd zvbm tlnz qhjnv rfbtj fxc hsmlvs jctbdm rrsct bxcx htc pdjkbf grnrr hprhbs dtktjd xrdq ccqf bfqr mlpr hkngs qdxzlz mjdzb qzcrtv shmnj cqbp zsnnbmb bltlff rprqg phbz kllsm chcgq nkz zccsfk pjmf bjpkhx dvz hxzfxg frvx zjpbb qrbjb thn jqrzxr vcqghl (contains nuts, wheat, eggs)\n" +
				"vvm gzbqjg qhjnv zgqf vbqfd dlxf vfpdlb hxzfxg jqrzxr vfblds bjpkhx khtmm zmfqpn dlhzp qxqjpk thn qrbnjtj sthnsg bkrd zqtlns dnfgghrh dtlgvqjd dbhfd vthsdx mkpjrjf cqbp zvpzkr scvmh mjdzb mhrp qrbjb ptn xrdq nsnqf ccqf nxj ngss (contains sesame)\n" +
				"mhts dpmp zqtlns gnhj dfkbnvp gsskhx qrbnjtj gpbbm mkczhrp chcgq bftz thn ccqf snhph sxbjr dbhfd knqh dtktjd zjpbb sbqf rfghfc lxvknl rnjfd zmfqpn qnmr hkngs nsnqf hkndh xprjs xrv vkj sthns pmdb bhrj cdqr rfbtj grnrr frqz qdxzlz bhxd sthnsg hsmlvs (contains soy)\n" +
				"nsnqf bgpbjps rbvhh hxzfxg hkngs rzdgnv xnbcq hkndh plgvd xmfg rprqg pzfhg chcgq dpmp nlltl ffkts thn fqjrtbm shmnj snhph cqbp bjpkhx phkvc pgqs qrbjb sthnsg slpfzvv mkczhrp pjmf rtxgv bxcx xnbvzq frqz dvz hsmlvs jjpgkn qgqqvxlj bhxd htc qrbnjtj ccjq bftz zmfqpn tlnz gnhj qkvkn vvfcqdd jcgkg (contains eggs, nuts, soy)\n" +
				"sthnsg bkrd rlc hcdz rvqdx bjpkhx nsnqf mkczhrp vvm frqz vfpdlb dbhfd nbvn rtxgv gpbbm zmfqpn rbvhh zdvlmm qjdqhx vcqghl trnd thn bhxd nkz rnjfd ndcssdm xnbcq sqr dlxf ffjxl snhph pxvl lbmf blq zvpzkr cdqr kjzl rljfl (contains nuts, eggs, dairy)\n" +
				"hsmlvs bbch snhph mkczhrp ctzz mjdzb qtqmdq njtkz thn qgqqvxlj dctmr svr kllsm svdlq plkn khk dnfgghrh djtr sxbjr xrv lxvknl kjzl rfbtj pzfhg dtktjd lbmf qrbnjtj lndmtz pjmf rdddzf gnhj qdxzlz dlxf bfszscr zmfqpn hcdz xmfg nbvn bhrj pdjkbf mqxzt zqvthqt qjdqhx mrgz xgmrx xprjs bjpkhx rprqg sbqf rvqdx fxc ngss fqjrtbm jptplj pzznzt shkr nsnqf bgpbjps zjpbb vgq xnbcq ffkts pmh zvbm bchq qzcrtv pxvl qrbjb nnfnm ccqf sthnsg mlpr qfdh knqh (contains nuts)\n" +
				"kjzl mkpjrjf cdqr vfpdlb vbqfd sthnsg qrbnjtj phbz qhjnv rtxgv dxnn lbdm hkngs zgqf zlrxv rnjfd vkj lbmf jptplj dlhzp qxqjpk knqh bhrj mlpr mkczhrp ndcssdm zqtgm rljfl qfdh plkn fxc rbvhh dctmr vvm nlltl sthns qgqqvxlj pmh thn hxclz bjpkhx zccsfk njtkz rdddzf pzznzt zmfqpn dlxf jcgkg ngss ncvh sxbjr qzfk zqtlns dtlgvqjd bltlff rrsct frvx mhrp rzdgnv hsmlvs nbtdp khk ccjq xrdq pbsv nsnqf trnd pgqs cqbp pvv pmdb hzpk nbgvrls lndmtz hxzfxg snhph pxvl xrv sqr hcdz pzfhg (contains wheat)\n" +
				"pmdb dtlgvqjd bjpkhx khk mkpjrjf cnvv grjdnml sqr sbqf fqjrtbm hkndh zvpzkr hsmlvs rbvhh zmfqpn svr blq kllsm nbqpf cqbp pxb thn qrbnjtj pzfhg ccqf ptn qjdqhx ksqfmnl qhjnv ctzz hbqgv bftz qxqjpk ggvgpc mqxzt dtktjd jcgkg xdz khtmm pmh bltlff hxclz knqh jqrzxr mhrp mlsmqjln tbffk gpbbm shmnj ccjq vfblds rvqdx qvgfgzl qtqmdq bxcx hcdz lbmf rrsct bhrj zlrxv njtkz gsskhx mkczhrp rprqg rfghfc dbhfd xcfl lrsbsl rdddzf shkr dcrq xnbvzq xlbnk sthnsg vkj grnrr hkngs ncvh snhph nxj phkvc svdlq trnd dmqnlv vgq qkvkn ndcssdm rlc (contains peanuts)\n" +
				"hkngs hsmlvs dnfgghrh jjpgkn ffjxl htc xprjs rljfl ncvh shkr qtqmdq nbqpf pjmf zmfqpn pmnzl mkczhrp qrbnjtj qxqjpk sxbjr plgvd dcrq pdjkbf knqh lbmf pbsv pvv qhjnv svdlq zvpzkr rprqg jqrzxr phbz nsnqf lxvknl rnmxqtx zlrxv dbhfd jctbdm ptn gsskhx fxc hxzfxg thn lbdm bhxd gpbbm mlsmqjln sthnsg vkj trnd nkz bjpkhx vgq vfpdlb zvbm hzpk mqxzt zgqf hcdz rzdgnv zjpbb sqr bkrd pzfhg xnbvzq (contains wheat, soy)\n" +
				"rdddzf vfblds ngss nxj grjdnml dxnn xrdq bjpkhx qghp hcdz bnqzkgv lxvknl nsnqf bftz dpmp qrbnjtj bhxd qhjnv grnrr cdqr mhts ggvgpc gpbbm htc rfbtj vvfcqdd ptn pjmf ccjq svdlq xdz dtktjd thn kjzl jhjc tlnz shkr bchq mqxzt dcrq qgqqvxlj mjdzb bkrd xcfl vthsdx qzcrtv ccqf pxb pmdb njtkz sxbjr vbqfd vcqghl bhrj rrsct pxvl zmfqpn snhph sthnsg sthns dlhzp xnbcq qfdh frvx mkpjrjf zvpzkr nkz mkczhrp bxcx (contains eggs, dairy, soy)\n" +
				"blq mkpjrjf ftllkgg rlc qjdqhx rdddzf mlsmqjln nlltl vkj snhph dbhfd dcrq chcgq qgqqvxlj lndmtz qvhq dxnn sbqf ffkts ncvh thn mkczhrp lrsbsl grnrr scvmh bkrd nbqpf qkvkn nbgvrls bnqzkgv jcgkg xlbnk frvx gdl xrdq zdlp hsmlvs bftz plgvd nsnqf nbvn pxvl cdqr zgqf qrbnjtj lxvknl zlrxv bbch bjpkhx cnvv hxzfxg xrv sthnsg hxclz gpbbm phbz zccsfk vfpdlb pjmf (contains eggs)\n" +
				"snhph hbqgv qvgfgzl gsskhx ngss qkvkn hzpk vkj xrv rtxgv bjpkhx vvm qvhq gpbbm dtktjd njtkz rljfl bltlff bfszscr ggvgpc hxzfxg bnqzkgv ctzz rspj scvmh mhts mlpr jcgkg gnhj pjmf qghp trnd pzznzt rnmxqtx vhphlsj gdl jjpgkn ccjq bftz qjdqhx cqbp lxvknl bhrj nbvn sthnsg bgpbjps dpbq ccqf pmqm rzdgnv bxcx pmh pdjkbf frqz vgq frvx vthsdx jhjc shkr dtlgvqjd nxj xprjs qzfk slpfzvv dbhfd mhrp tlnz zdlp rprqg phbz nbgvrls qdxzlz rfbtj dnfgghrh htc thn bbch fqjrtbm ftllkgg vvfcqdd qrbnjtj vnschhzm mkczhrp zmfqpn vfblds bkrd (contains peanuts, soy)\n" +
				"dnfgghrh vnschhzm qrbnjtj rljfl pgqs bhrj vcqghl pxb dlxf bchq zmfqpn jptplj zdlp rfghfc frvx qtqmdq rfbtj slpfzvv plkn sthnsg rtxgv qhjnv qfdh qzcrtv mkczhrp qrbjb ggvgpc mkpjrjf ksqfmnl scvmh hcdz rspj jqrzxr khtmm dxnn pzfhg pdjkbf sbqf vgq zgqf khk vfpdlb ffkts ftllkgg pvv mhts bftz bbch shmnj gzbqjg qgqqvxlj zsnnbmb zccsfk zdvlmm qvhq dlhzp bltlff xnbcq jcgkg zjpbb bhxd hkndh njtkz bfszscr phbz qkvkn xprjs gpbbm snhph rrsct bjpkhx svdlq rvqdx lxvknl mqxzt vvm bnqzkgv fxc sthns tbffk svr nsnqf lbdm thn frqz (contains soy, eggs, nuts)\n" +
				"gsskhx lndmtz ksqfmnl snhph zccsfk rtxgv shmnj ccqf hbqgv zmfqpn nbtdp rdddzf xnbcq dcrq lxvknl ffjxl jqrzxr qrbnjtj rrsct knqh hcdz sbqf bjpkhx pxvl zqtlns blq thn svr ggvgpc hkngs vkj qnmr plgvd pgqs rnjfd sthnsg zlrxv sxbjr pbsv rspj pmqm pzfhg dbhfd (contains wheat, fish)\n" +
				"rlc mkczhrp frvx htc nsnqf shkr qzfk dbhfd qnmr gsskhx bftz rspj grnrr nlltl bbch vfpdlb vbqfd bfszscr rvqdx jcgkg hcdz hzpk kjzl fqjrtbm bjpkhx vnschhzm dpmp thn nbqpf dcrq mlpr jptplj qrbjb chcgq jjpgkn vhphlsj pxb dtlgvqjd zvpzkr snhph qxqjpk zmfqpn dvz sthnsg jctbdm hsmlvs gdl gnhj vfblds hxzfxg xdz shmnj nkz zjpbb dmqnlv hprhbs (contains dairy, wheat)\n" +
				"zvbm rfbtj gsskhx kllsm bfszscr ffjxl qhjnv xprjs qrbnjtj lbdm rlc sthnsg rnmxqtx jctbdm nbqpf dtktjd pxb nlqj dfkbnvp nkz vfpdlb hzpk ctzz cdqr xnbvzq mlsmqjln zdvlmm mkczhrp dtlgvqjd nbvn dbhfd trnd zccsfk qxqjpk rzdgnv qzfk plkn rfghfc bjpkhx rbvhh lbmf pmnzl bltlff hsmlvs zmfqpn qgqqvxlj qvhq bftz hcdz hkngs vgq vvm thn bbch ftllkgg snhph dpbq jptplj vthsdx (contains fish, sesame, dairy)\n" +
				"thn pzznzt vvfcqdd fqjrtbm dpmp bkrd zqvthqt bnqzkgv pbsv bbch qkvkn qjdqhx qvgfgzl hxzfxg gnhj fxc dcrq lbmf nsnqf xgmrx shkr nlltl grjdnml qfdh sqr vfblds sthnsg lxvknl pmqm nbgvrls qvhq qgqqvxlj qdxzlz sbqf qghp mqxzt lrsbsl rprqg khk zlrxv nbtdp qhjnv hsmlvs zmfqpn dctmr snhph vfpdlb qrbnjtj dlhzp ngss zjpbb trnd pmnzl bjpkhx qzcrtv hxclz xcfl dfkbnvp rbvhh qrbjb ndcssdm cnvv hkndh mkpjrjf bgpbjps xlbnk pzfhg rspj bfszscr ncvh svr zsnnbmb lbdm zgqf qzfk bftz ffkts pgqs zvpzkr ccqf vcqghl zqtlns hkngs gzbqjg (contains wheat, peanuts, nuts)\n" +
				"pdjkbf lrsbsl qdxzlz bjpkhx vfblds bxcx hprhbs sxbjr qvgfgzl dmqnlv mlsmqjln vnschhzm bhrj bltlff grnrr ffjxl pxb qvhq sthnsg plkn xnbvzq xrv mhrp qgqqvxlj shkr fqjrtbm nbtdp ccqf vvfcqdd zccsfk dctmr jqrzxr nsnqf snhph bgpbjps dvz mhts zgqf svr sbqf bhxd zmfqpn pzfhg jcgkg trnd pmdb pzznzt dfkbnvp cdqr jptplj zqtlns ffkts ftllkgg rlc jhjc pjmf rzdgnv dtlgvqjd djtr rnmxqtx cnvv pmnzl nkz qrbnjtj thn mjdzb (contains peanuts, fish)\n" +
				"sthnsg qzfk bkrd chcgq vnschhzm nsnqf trnd bjpkhx vhphlsj dfkbnvp bftz lrsbsl zlrxv snhph xprjs vcqghl rlc rzdgnv ptn thn qjdqhx jqrzxr gdl bltlff dmqnlv zvbm grjdnml kllsm shkr dxnn scvmh ffkts hkngs mhts vbqfd bfqr plgvd dbhfd dpbq nbvn ksqfmnl xnbvzq rfghfc khtmm rspj zqtlns cdqr xgmrx pgqs zmfqpn nbqpf hsmlvs qdxzlz vfblds gzbqjg jcgkg (contains eggs, nuts)\n" +
				"cnvv lbmf xmfg shmnj bftz zqtlns grjdnml nkz dcrq jqrzxr dxnn shkr hkngs plgvd sthns sthnsg rlc thn dpbq trnd djtr zmfqpn dtlgvqjd qrbnjtj kllsm dctmr pjmf gdl qhjnv bjpkhx bxcx xprjs bchq dlxf chcgq vthsdx svr snhph ffkts qxqjpk dlhzp plkn hbqgv nsnqf njtkz nlltl rfbtj bhxd (contains peanuts, nuts, soy)\n" +
				"jcgkg bgpbjps qkvkn rfghfc sthns xgmrx gdl grjdnml lbmf pzznzt knqh nnfnm qrbnjtj plkn qjdqhx phkvc dtktjd zmfqpn zqvthqt chcgq fxc dvz hzpk bhrj qvgfgzl ffkts xlbnk sqr lrsbsl ndcssdm zvbm dlhzp bjpkhx nbvn rlc qhjnv rtxgv dnfgghrh rrsct shkr blq kllsm vvfcqdd sbqf snhph bfqr ggvgpc mkpjrjf pmh bchq nsnqf cdqr bnqzkgv mrgz rbvhh sthnsg bxcx svdlq jhjc pmqm xdz pgqs jctbdm bkrd bfszscr ptn thn svr zqtlns qfdh (contains soy, dairy)\n" +
				"bchq jqrzxr hxclz pzfhg mjdzb rljfl qzfk svr zccsfk rnmxqtx qrbnjtj xdz phkvc dcrq bftz xcfl qvhq lndmtz rfbtj khtmm knqh bjpkhx ftllkgg xrv rlc rtxgv sbqf trnd gnhj sthnsg jhjc xlbnk nbvn zdvlmm nsnqf pzznzt blq fxc ffkts dvz qvgfgzl xrdq snhph sqr nxj qghp dtktjd zmfqpn dpbq qhjnv thn bgpbjps gdl bnqzkgv qjdqhx rprqg xnbcq vvfcqdd qkvkn fqjrtbm bltlff bfszscr zqtlns jctbdm jjpgkn frqz ncvh qfdh plgvd mkpjrjf xmfg grjdnml slpfzvv vhphlsj (contains wheat, soy)\n" +
				"vgq bjpkhx rzdgnv khk bbch sthnsg vfpdlb fxc htc dctmr qxqjpk hprhbs zdvlmm svdlq nbgvrls qgqqvxlj gzbqjg nbqpf pgqs nnfnm pzfhg qrbnjtj ccqf fqjrtbm chcgq rbvhh zmfqpn blq grjdnml qjdqhx zqtlns qghp bltlff lrsbsl vkj dbhfd xlbnk hkngs nxj mjdzb ncvh svr jcgkg mkczhrp njtkz xgmrx nsnqf shmnj qzcrtv cdqr zccsfk mlsmqjln thn (contains peanuts)\n" +
				"rspj htc bnqzkgv dbhfd dfkbnvp njtkz scvmh zdlp cnvv dvz rrsct qrbnjtj zmfqpn vgq rnjfd slpfzvv pmqm qtqmdq hzpk tlnz rtxgv hsmlvs jhjc zvpzkr qzcrtv plkn vfblds snhph dpbq dmqnlv qkvkn phkvc thn sthnsg pmnzl gdl jptplj fxc zvbm mkpjrjf bhxd rprqg bjpkhx kjzl dctmr bxcx vkj pvv qhjnv zqtlns djtr lxvknl (contains nuts, soy)\n" +
				"qvhq mlsmqjln zgqf qvgfgzl zmfqpn rzdgnv pxb phbz khk dbhfd nbgvrls ftllkgg vvfcqdd sthnsg grnrr bjpkhx frqz njtkz zdvlmm dlxf rtxgv pgqs nbtdp svdlq phkvc mkpjrjf frvx slpfzvv bbch snhph ggvgpc xnbvzq rvqdx dcrq nbvn jptplj tlnz hxclz xdz pzfhg qrbnjtj pmqm dvz rprqg nlltl xnbcq hprhbs xrdq nsnqf dpmp lbmf dnfgghrh bltlff rlc (contains fish, peanuts)\n" +
				"svr rfghfc bnqzkgv xdz hxclz jptplj dpbq zsnnbmb rzdgnv lrsbsl ptn jqrzxr scvmh blq mlpr qjdqhx thn gzbqjg xrdq pdjkbf vfblds ffkts xprjs kllsm pzfhg nbgvrls frqz qgqqvxlj dbhfd zvpzkr mkczhrp nnfnm rlc lbdm grjdnml snhph rspj shkr sbqf shmnj zccsfk mkpjrjf khk ftllkgg bgpbjps mjdzb ndcssdm bfqr pmdb phkvc xlbnk hzpk mhrp qhjnv slpfzvv dtktjd sqr xrv vnschhzm tbffk xnbcq bchq qtqmdq pxvl jhjc rljfl sthnsg dtlgvqjd zmfqpn qrbnjtj bfszscr ncvh dpmp cdqr pxb nbvn grj qvhq trnd bjpkhx ctzz pmh (contains sesame)\n" +
				"bkrd nkz nnfnm svdlq rnmxqtx zqtgm ffkts qvgfgzl vcqghl bgpbjps sxbjr fqjrtbm hsmlvs qgqqvxlj bnqzkgv zdvlmm rfbtj rlc mrgz mjdzb gsskhx snhph nlltl dlhzp tlnz mkczhrp mqxzt xprjs sthnsg frvx qrbnjtj xnbvzq pgqs dcrq svr vvm bbch mlpr ccjq qvhq nbtdp rspj bftz thn cqbp dlxf shkr pmnzl vnschhzm jcgkg zjpbb qfdh nsnqf xlbnk dtlgvqjd zvpzkr nxj cnvv lbdm mkpjrjf rnjfd rzdgnv qjdqhx nbgvrls gzbqjg chcgq blq hprhbs lxvknl qtqmdq lndmtz grnrr pxvl qnmr dbhfd ngss grj hbqgv vbqfd zmfqpn phbz khtmm dxnn pzfhg (contains fish, peanuts, soy)\n" +
				"hprhbs gnhj bchq vcqghl zqtgm bltlff ngss zvpzkr vnschhzm pxb nbtdp sthns ctzz lndmtz dxnn ncvh jctbdm rfbtj hsmlvs qrbnjtj khk nbqpf dbhfd qkvkn grnrr gsskhx zmfqpn vfpdlb jptplj dnfgghrh xrdq lrsbsl nxj qgqqvxlj frqz gzbqjg qxqjpk sthnsg vgq qdxzlz bfszscr thn bfqr grj bhrj rfghfc gdl vthsdx qzcrtv hxclz mkczhrp khtmm xcfl pvv tbffk qzfk ffkts bftz nsnqf pmnzl qnmr ggvgpc rprqg xdz dpbq chcgq vhphlsj pxvl mqxzt snhph (contains sesame, eggs)\n" +
				"lndmtz nlltl jcgkg pgqs scvmh ptn chcgq qvhq fxc xprjs hxclz zlrxv snhph xrv svdlq nbqpf gpbbm mhrp cqbp sxbjr khtmm dvz pxb dnfgghrh ftllkgg zgqf qdxzlz vbqfd tlnz bkrd zmfqpn cdqr bjpkhx qvgfgzl kllsm nsnqf xrdq lbdm ffjxl bfqr ctzz pmqm mjdzb pjmf frqz hbqgv htc jjpgkn nxj dpmp ndcssdm pmdb qnmr sthnsg qhjnv dpbq qrbjb qzfk vnschhzm bgpbjps frvx shmnj qrbnjtj nnfnm rdddzf vgq ggvgpc rprqg rrsct zqvthqt pxvl thn fqjrtbm hxzfxg zdvlmm zjpbb jptplj qxqjpk (contains wheat)\n" +
				"pbsv qjdqhx mjdzb bfqr zjpbb pmh qrbnjtj zdvlmm lbdm nnfnm vfblds bltlff thn vvm nkz pgqs qhjnv rfghfc dtlgvqjd knqh qkvkn zmfqpn hsmlvs bchq lxvknl sxbjr sthnsg khk hbqgv ngss qvgfgzl dlhzp mhrp pmqm rljfl ctzz qxqjpk fxc njtkz ndcssdm tbffk qgqqvxlj ggvgpc ffjxl pzznzt dbhfd snhph gnhj pmdb frqz bjpkhx dxnn bgpbjps lndmtz vvfcqdd cdqr (contains wheat)\n" +
				"vfblds snhph gpbbm jqrzxr qvhq gzbqjg pzfhg rbvhh zmfqpn rzdgnv lxvknl ccjq nbtdp gdl hcdz hzpk mhts vhphlsj grjdnml nsnqf rvqdx bjpkhx ftllkgg nbvn ctzz bxcx kllsm blq pmqm zvpzkr ncvh vthsdx svdlq dbhfd zgqf vvm pxvl grnrr fqjrtbm zqtlns rtxgv htc pvv qrbnjtj pdjkbf rfbtj sthnsg nxj jjpgkn zjpbb xrv pmdb nbgvrls ngss kjzl dpmp dfkbnvp ccqf vcqghl mlsmqjln (contains eggs)\n" +
				"htc mjdzb vcqghl zvpzkr rlc rdddzf pmh lbdm qrbnjtj xrv vhphlsj nkz zmfqpn vfblds nxj xprjs pxb bfqr bjpkhx dpbq snhph dfkbnvp rnmxqtx plkn nbtdp qfdh zvbm hkngs scvmh tlnz hxzfxg hsmlvs mrgz nbgvrls cdqr qkvkn kllsm bhrj pxvl bfszscr hxclz bchq ngss xnbvzq ndcssdm dnfgghrh frvx jqrzxr rfghfc trnd vgq dcrq bnqzkgv bltlff nsnqf dvz dmqnlv pzznzt zdvlmm ccjq sthnsg xdz ggvgpc sqr lndmtz pgqs sxbjr dctmr pzfhg gzbqjg zlrxv chcgq cqbp qrbjb cnvv dlxf ffkts mkczhrp bhxd thn rspj qzfk grjdnml jcgkg slpfzvv pdjkbf vvm mhrp (contains soy)\n" +
				"vfblds vvm snhph qnmr mlpr dmqnlv rrsct dpmp slpfzvv gsskhx sthnsg cnvv nsnqf pzznzt qdxzlz hxclz ffjxl bjpkhx dctmr ggvgpc ncvh qgqqvxlj dvz xlbnk dcrq cqbp zqvthqt phkvc zmfqpn tbffk blq mjdzb rljfl zqtgm lxvknl qfdh vvfcqdd khk rlc hxzfxg grnrr frqz bhxd pmh svr rspj qrbnjtj rfbtj ptn dlhzp pxvl xrdq tlnz qhjnv sxbjr qvhq bchq vbqfd pbsv hkngs hzpk gzbqjg qvgfgzl trnd hbqgv dnfgghrh pmdb knqh frvx rnjfd vkj pmnzl pvv dtlgvqjd rprqg dbhfd hcdz gdl bfszscr svdlq bftz ctzz zvbm ccqf (contains peanuts)\n" +
				"xdz kllsm zmfqpn vhphlsj bnqzkgv qrbnjtj zvbm sthnsg dlxf nbqpf dbhfd qrbjb pxvl dctmr mjdzb qtqmdq grjdnml jcgkg zqtlns nsnqf rfbtj nlltl cnvv nxj bfqr cqbp xlbnk zjpbb snhph pgqs pmqm qvhq plgvd khk dpmp plkn vvfcqdd qdxzlz rrsct qjdqhx gdl mhrp phkvc pmdb thn rzdgnv pmh pzznzt (contains eggs, nuts, wheat)\n" +
				"qghp dbhfd djtr mhts pxb sthnsg hsmlvs cdqr rnjfd mjdzb xprjs hkndh rrsct zgqf rfghfc vfpdlb bhrj pvv rfbtj bftz dlxf nnfnm qzfk jctbdm zmfqpn grnrr rspj pzznzt bjpkhx rnmxqtx xrv snhph mlsmqjln rprqg ccjq vvm qfdh mkczhrp nkz ffjxl dctmr mrgz dvz nsnqf bbch gnhj bkrd knqh nbvn chcgq ccqf jptplj tbffk dpbq rljfl tlnz bltlff rlc ngss mhrp vcqghl gdl zccsfk zdlp mqxzt thn zqvthqt njtkz vfblds hxzfxg qnmr sqr qzcrtv (contains soy)\n" +
				"frvx grj kjzl xnbcq shkr dvz zqtgm bltlff dpmp ptn dbhfd thn nlqj vhphlsj khk snhph qfdh jctbdm sthns dxnn bhrj vkj rljfl nkz qdxzlz sxbjr mkpjrjf zdvlmm qrbnjtj ccjq gnhj dmqnlv ggvgpc sthnsg xcfl qghp nbgvrls bgpbjps hsmlvs cdqr slpfzvv ftllkgg pxvl jcgkg tlnz rdddzf shmnj dpbq rbvhh fxc pxb ndcssdm zmfqpn zlrxv kllsm lbmf ksqfmnl gsskhx nsnqf hkndh pbsv xrv (contains eggs, wheat, soy)\n" +
				"nxj sthnsg zgqf dvz njtkz qxqjpk cnvv dbhfd pjmf mhts lbmf snhph ksqfmnl fxc dxnn bftz ptn nbtdp nbvn sthns gzbqjg jptplj pvv dcrq nsnqf hxclz svdlq plkn rprqg qtqmdq shmnj mlsmqjln cqbp mhrp dpbq jqrzxr gpbbm xnbvzq qrbnjtj cdqr htc pgqs bjpkhx xlbnk sqr jcgkg shkr nkz rfghfc xcfl sxbjr blq xnbcq dpmp zmfqpn qdxzlz grj vfpdlb nlltl lndmtz (contains peanuts)\n" +
				"jctbdm hxclz chcgq dlxf ccjq bjpkhx bbch zmfqpn dnfgghrh pjmf svr kllsm zsnnbmb ggvgpc dlhzp rnjfd qrbnjtj hkndh thn zvpzkr xnbvzq grj plkn zccsfk bkrd xprjs nbtdp zjpbb vbqfd dctmr hcdz rtxgv vvm nsnqf mhrp gsskhx dpmp qghp pvv snhph dbhfd jqrzxr (contains nuts, fish, dairy)";

	}
}
