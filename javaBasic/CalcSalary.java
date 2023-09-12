import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/*-< 演習： 1ヵ月間の給与の総額を算出するプログラム>---------------------------------
「ReadFileSample.java」をベースにしてcsvデータを読み込み、1ヵ月間の給与の総額を算出して出力するプログラムを作成してください。
----------------------------------------------------*/
public class CalcSalary {

	public static void main(String[] args) {
		//  WorkingResult.csvのパス ※「C:\WorkSpace」直下に配置していない場合は適宜変更してください。
		final String WORKING_RESULT_FILE_PATH = "../JavaEX/src/WorkingResult.csv";
		// コンマ
		final String COMMA = ",";

		// 計算用の数値を定数で用意
		final long ONE_HOUR_BY_MILLI_SEC = 1000 * 60 * 60; // 1時間のミリ秒換算
		final long ONE_MIN_BY_MILLI_SEC = 1000 * 60; // 1分のミリ秒換算
		final int ONE_HOUR_BY_MIN = 60; // 1時間の分換算
		//下記は新たに追加した変数
		final int salaryHour = 900; // 時給
		final int salaryMin = salaryHour / 60; // 分給
		final int salary8h = 8 * salaryHour; // 8時間分の給与（超過計算時に使用）
		int monthlySalary = 0; // 1カ月間の給与

		List<String> workingResults = new ArrayList<String>(); //ファイルから読み込んだデータの格納用

		//  WorkingResult.csvを読み込む
		try {
			// WorkingResult.csvの読み込み準備
			File workingResultFile = new File(WORKING_RESULT_FILE_PATH);
			BufferedReader br = new BufferedReader(new FileReader(workingResultFile));

			// WorkingResult.csvを1行ずつ読み込んでArrayListに格納する
			String recode = br.readLine();
			while (recode != null) {
				workingResults.add(recode);
				recode = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}

		// ArrayListから1行ずつ取り出して日付/出勤時間/退勤時間に振り分け
		for (int i = 0; i < workingResults.size(); i++) {

			String workingRecode = workingResults.get(i); // 1行ずつ文字列を取り出す
			String[] forSplitRecode = workingRecode.split(COMMA); // splitメソッドを用いてカンマ区切りで文字列を分解＆配列にそれぞれ格納

			//Date workingDate = Date.valueOf(forSplitRecode[0]); // 出勤日
			Time startTime = Time.valueOf(forSplitRecode[1]); // 出勤時間
			Time finishTime = Time.valueOf(forSplitRecode[2]); // 退勤時間

			// getTimeメソッドを使って労働時間をミリ秒（0.001秒単位）で取得する
			long workingTime = finishTime.getTime() - startTime.getTime();

			// ミリ秒で取得した労働時間を○時間△分の形式に直す
			int workingHour = (int) (workingTime / ONE_HOUR_BY_MILLI_SEC); // 時間に換算
			int workingMin = (int) ((workingTime / ONE_MIN_BY_MILLI_SEC) % ONE_HOUR_BY_MIN); // 分に換算

			//calcRestTimesメソッドで実労働時間を求める
			int overWkMin = 0; // 超過時間
			int dailyPay = 0; // 日給
			int overWkPay = 0; // 超過時間分の賃金

			int noRestWkMin = calcRestTimes(workingHour, workingMin);

			//金額計算を行う(労働時間が８時間以上なら1分単位で残業代を支給する（1.25倍）)
			if (480 <= noRestWkMin) {
				//時給計算(8時間以上で超過残業含むの場合)
				overWkMin = noRestWkMin - 480;
				overWkPay = (int) ((overWkMin * salaryMin) * 1.25);
				dailyPay = salary8h + overWkPay;
			} else {
				//超過残業なしで時給計算する場合
				dailyPay = noRestWkMin * salaryMin;
			}
			//日給をmonthlySalaryに代入
			monthlySalary += dailyPay;
		}
		// 出力
		System.out.println("1カ月間の給与は" + monthlySalary + "円です。");
	}

	public static int calcRestTimes(int wkHour, int wkMin) {
		//休憩時間を引いた実労働時間を算出,6時間超~8時間以下の場合は45分、8時間を超える場合は1時間引く
		int restMin = 0; // 休憩時間
		int allWkTime = 0; // 勤務時間（休憩時間含む）
		allWkTime = (wkHour * 60) + wkMin;

		if (360 < allWkTime && allWkTime <= 480) {
			//労働時間6時間超～8時間以下の場合
			restMin = 45;
		} else if (480 < allWkTime) {
			//労働時間8時間超～の場合
			restMin = 60;
		}
		//実労働時間算出
		int noRestWkTimes = allWkTime - restMin;

		return noRestWkTimes;
	}

}
