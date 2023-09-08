/* (演習EX 1-1-2) 自動販売機のお釣りを表示するプログラム
	10円玉,50円玉,100円玉,500円玉が投入された場合は、SUMに数値を代入
	もしもそれ以外が投入された場合は、警告を表示する
	最後に投入された硬貨の合計金額から購入された商品の値段を差し引いてお釣りのメッセージを表示
*/
public class Jihan2 {

	public static void main(String[] args) {
		
		int inputCoinNumber = 0;
		int sum = 0;
		int price = Integer.parseInt(args[args.length-1]);
		
		for(int n = 0 ; n < args.length-1 ; n++) {
			
			//コマンド引数をInputCoinNumberに代入
			inputCoinNumber = Integer.parseInt(args[n]);
			
			if(inputCoinNumber == 1) {
				//１円玉は警告を表示する
				System.out.println("警告:1円玉は使えません");
			}else if(inputCoinNumber == 5) {
				//5円玉は警告を表示する
				System.out.println("警告:5円玉は使えません");
			}else if(inputCoinNumber == 777) {
				//777円玉は警告を表示する
				System.out.println("警告:777は硬貨として適切な値ではありません");
			}else if(inputCoinNumber == 10){
				//代金をsumに代入
				sum =  sum + inputCoinNumber;
			}else if(inputCoinNumber == 50) {
				//代金をsumに代入
				sum =  sum + inputCoinNumber;
			}else if(inputCoinNumber == 100) {
				//代金をsumに代入
				sum =  sum + inputCoinNumber;
			}else if(inputCoinNumber == 500) {
				//代金をsumに代入
				sum =  sum + inputCoinNumber;
			}
		}
		//お釣りを表示
		sum = sum - price;
		System.out.println(sum + "円のお釣りです。ありがとうございました。");
	}

}
