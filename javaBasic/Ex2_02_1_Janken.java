class Ex2_02_1_Janken {
	public static void main(String[] args) {

		Ex2_02_1_Player player1 = new Ex2_02_1_Player(args[0], ""); // プレイヤー1の名前
		Ex2_02_1_Player player2 = new Ex2_02_1_Player(args[1], ""); // プレイヤー2の名前

		System.out.println("じゃんけん・・・ぽん！！！！！");
		player1.makeHandStatus();
		System.out.println(player1.name + "さんの手：" + player1.handStatus);
		int p1JudgeNum = Integer.parseInt(JudgeNum(player1.handStatus));
		player2.makeHandStatus();
		System.out.println(player2.name + "さんの手：" + player2.handStatus);
		int p2JudgeNum = Integer.parseInt(JudgeNum(player2.handStatus));

		System.out.println("結果は・・・");
		// 勝敗判定
		if ((p1JudgeNum == 0 && p2JudgeNum == 1 || p1JudgeNum == 1 && p2JudgeNum == 2)
				|| (p1JudgeNum == 2 && p2JudgeNum == 0)) {
			System.out.println(player1.name + "さんの勝ち！");
		} else if ((p1JudgeNum == 0 && p2JudgeNum == 2
				|| p1JudgeNum == 1 && p2JudgeNum == 0)
				|| (p1JudgeNum == 2 && p2JudgeNum == 1)) {
			System.out.println(player2.name + "さんの勝ち！");
		} else {
			System.out.println("あいこ！勝負つかず！");
		}
	}

	public static String JudgeNum(String hs) {
		// 勝敗判定のため、文字を数字変換
		if (hs.equals("グー")) {
			hs = "0";
		} else if (hs.equals("チョキ")) {
			hs = "1";
		} else if (hs.equals("パー")) {
			hs = "2";
		}
		return hs;

	}
}
