class Ex2_02_1_Player {

	// フィールド---------------------------
	String name; // プレイヤー名
	String handStatus; //ジャンケンの手

	// コンストラクタ-------------------------------
	Ex2_02_1_Player(String nm, String hs) {
		name = nm;
		handStatus = hs;
	}

	// メソッド------------------------------------
	void makeHandStatus() {
		// handStatusにランダムでグーチョキパーを設定
		String[] janken = { "グー", "チョキ", "パー" };
		int num = (int) (Math.random() * (janken.length));
		handStatus = janken[num];
	}
}
