/* (演習EX 1-2-1) ケーキ屋の注文プログラム
 */
public class CalcCakeSum {

	public static void main(String[] args) {
		
		String name = "";		//商品名
		int cnt = 0;			//個数
		int price = 0;			//値段
		int sum = 0;			//商品ごとの代金
		int total = 0;			//合計金額
		double discountRate  = 0.8;		//割引率 
		double taxRate = 1.08;	//消費税8%
		int taxIncludedPrice = 0;	//消費税込みの合計金額
		
		for(int n = 0 ; n < args.length ; n += 2) {
			
			name = (String)args[n];
			cnt = Integer.parseInt(args[n+1]);
			
			switch (name){
			  case "ショートケーキ":
				price = 320;
			  	sum = sum + (price * cnt);
			    
			    break;
			  case "モンブラン":
				price =  350;
			  	sum = sum + (price * cnt);
			    
			    break;
			  case "チョコレートケーキ":
					price =  370;
				  	sum = sum + (price * cnt);
				 break;
			  case "いちごのタルト":
					price =  400;
				  	sum = sum + (price * cnt);
				  break;
			  case "チーズケーキ":
					price =  300;
				  	sum = sum + (price * cnt);
				  break;
			}
			total = total + sum;
			sum = 0;
		}
		
		// 合計金額が1000円以上であれば、２割引きする
		if(1000 <= total) {
			total = (int)(total * discountRate);
		}
		
		// 消費税の計算
		taxIncludedPrice = (int) (total * taxRate);
		System.out.println("合計金額は、" + taxIncludedPrice + "円です。");
	}
}
