package churimon;

public class Hitokake extends Monster3 {

	//Monster3クラスを継承
	// コンストラクタ①------------------
	Hitokake() {
		super();
		super.setCharacter("ヒトカケ");
	}

	// コンストラクタ②------------------
	Hitokake(String trnr, String nm) {
		super(trnr, nm);
		super.setCharacter("ヒトカケ");
	}

	// コンストラクタ③------------------(レベル見合った値セットする)
	Hitokake(String trnr, String nm, int defLv) {
		super(trnr, nm, defLv);
		super.setCharacter("ヒトカケ");
	}

	// メソッド-------------------------
	@Override
	public void levelUp(int lvUp) {
		super.lv += lvUp * 1;
		super.hp += lvUp * 29;
		super.atk += lvUp * 8;
		super.def += lvUp * 5;
		super.spd += lvUp * 9;
		super.hpMax = hp;
	}
}
