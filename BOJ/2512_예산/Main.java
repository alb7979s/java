import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m, prices[];
	static boolean canSatisfy(int price) {
		long usedMoney = 0;
		for(int i = 0; i < n; i++) {
			if(prices[i] > price) usedMoney += price;
			else usedMoney += prices[i];
		}
		// 예산 터지면 상한 줄이기
		if (usedMoney > m) return false;
		return true;
	}
	static int upper_bound(int left, int right) {
		while(left <= right) {
			int mid = (left + right) / 2;
			if(!canSatisfy(mid)) right = mid - 1;
			else left = mid + 1;
		}
		return right;
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = stoi(br.readLine());
		prices = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int totalPrice = 0, maxPrice = 0;
		for(int i = 0; i < n ; i++) {
			prices[i] = stoi(st.nextToken());
			totalPrice += prices[i];
			maxPrice = Math.max(maxPrice, prices[i]);
		}
		m = stoi(br.readLine());
		if(totalPrice <= m) System.out.println(maxPrice);
		else System.out.println(upper_bound(0, (int)1e9));
	}
}