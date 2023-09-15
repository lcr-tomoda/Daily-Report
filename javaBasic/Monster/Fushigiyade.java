package churimon.Monster;

public class Fushigiyade extends Monster3 {

	// コンストラクタ①------------------
	public Fushigiyade() {
		super();
		super.setCharacter("フシギヤデ");
	}

	// コンストラクタ②------------------
	public Fushigiyade(String trnr, String nm) {
		super(trnr, nm);
		super.setCharacter("フシギヤデ");
	}

	// コンストラクタ③------------------(レベル見合った値セットする)
	public Fushigiyade(String trnr, String nm, int defLv) {
		super(trnr, nm, defLv);
		super.setCharacter("フシギヤデ");
	}

	// メソッド-------------------------
	@Override
	public void levelUp(int lvUp) {
		super.lv += lvUp * 1;
		super.hp += lvUp * 31;
		super.atk += lvUp * 6;
		super.def += lvUp * 7;
		super.spd += lvUp * 8;
		super.hpMax = hp;
	}
}
