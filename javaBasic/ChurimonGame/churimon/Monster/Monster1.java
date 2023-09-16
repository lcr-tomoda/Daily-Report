package churimon.Monster;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;

public class Monster1 {

	// フィールド----------------------
	String character = "(unknown)"; // 種族
	String trainer = "(wild)"; // トレーナー
	String name = "(noname)"; // なまえ
	int lv = 1; // レベル
	int hp = 80; // HP
	int atk = 15; // こうげき
	int def = 10; // ぼうぎょ
	int spd = 10; // すばやさ
	int hpMax = 80; // HP初期値
	String wazaNm = "たいあたり"; // わざ(なまえ)
	String wazaDmgRate = "1.0"; // わざ(ダメージ倍率)

	// メソッド-------------------------
	@Override
	public String toString() {
		return "character:" + character + "/trainer:" + trainer
				+ "/name:" + name + "/lv:" + lv + "/hp:" + hp + "/atk:" + atk
				+ "/def:" + def + "/spd:" + spd + "/hpMax:" + hpMax
				+ "/wazaNm:" + wazaNm + "/wazaDmgRate:" + wazaDmgRate;
	}

	// 上昇レベルに従ってステータスを上昇させる
	public void levelUp(int lvUp) {
		this.lv += lvUp * 1;
		this.hp += lvUp * 30;
		this.atk += lvUp * 5;
		this.def += lvUp * 5;
		this.spd += lvUp * 5;
		this.hpMax = hp;
	}

	// わざに関する情報を設定する
	public void setWaza(String wzName, String wzDmg) {
		boolean isMatched = Pattern.matches("^[0-9]+(\\.[0-9])$", wzDmg);
		if (isMatched == true) {
			this.wazaNm = wzName;
			this.wazaDmgRate = wzDmg;
		} else {
			System.out.print("[ERROR]わざの設定に失敗しました");
		}
	}

	// ステータスを表示する(ステーサス情報を文字列で返す)
	public String getStatus() {
		String msg = this.name + "lv" + this.lv + "HP" + this.hp + "/" + this.hpMax;
		return msg;
	}

	// わざを使用して相手にダメージを与える
	public int useWaza() {
		BigDecimal bd_Atk = new BigDecimal(atk);
		BigDecimal bd_wzDmgRate = new BigDecimal(wazaDmgRate);
		BigDecimal bd_DmgVal = bd_Atk.multiply(bd_wzDmgRate);
		int dmgVal = bd_DmgVal.intValue();

		return dmgVal;
	}

	// 値渡しされたダメージから実際に受けるダメージを計算し、HPから減算する
	// 戻り値として実際に受けるダメージを返す
	public int damaged(int dmgVal) {
		// ダメージ減算率:1 / (1+ぼうぎょ÷120) ※小数第3位切り捨て
		BigDecimal bd_Def = new BigDecimal(def);
		BigDecimal bd1 = new BigDecimal("120");
		BigDecimal bd2 = new BigDecimal("1");

		BigDecimal divide1 = bd_Def.divide(bd1, 3, RoundingMode.DOWN); //0.083
		BigDecimal add = divide1.add(bd2); //1.083
		BigDecimal bd_DmgDwRate = bd2.divide(add, 3, RoundingMode.DOWN); //0.923

		// 実際に受けるダメージ:値渡しされたダメージ値×ダメージ減算率
		BigDecimal bd_DmgVal = new BigDecimal(dmgVal);
		BigDecimal ans = bd_DmgVal.multiply(bd_DmgDwRate);
		int actDmg = ans.intValue(); // 実際に受けるダメージ (intに変換するため小数点以下は切り捨て）

		// 値渡しされたダメージから実際に受けるダメージを計算し、HPから減算する
		if (actDmg < hp) {
			hp -= actDmg;
		} else {
			hp = 0;
		}
		return actDmg;
	}
}
