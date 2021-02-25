import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m, days[];
	static boolean canSatisfy(int price) {
		int tempM = m;
		int currPrice = 0;
		for(int i = 0; i < n; i++) {
			// 쓸 돈 보다 가지고 있는 돈이 더 적으면
			if(currPrice < days[i]) {
				tempM--;
				currPrice = price; 
			}
			if(currPrice < days[i]) return false;
			currPrice -= days[i];
		}
		if (tempM < 0) return false;
		return true;
	}
	static int lower_bound(int left, int right) {
		while(left <= right) {
			int mid = (left + right) / 2;
			if(canSatisfy(mid)) right = mid - 1;
			else left = mid + 1;
		}
		return left;
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		days = new int[n];
		int leftMax = 0;
		for(int i = 0; i < n ; i++) {
			days[i] = stoi(br.readLine());
			leftMax = Math.max(leftMax, days[i]);
		}
		System.out.println(lower_bound(leftMax, (int)1e10));
	}
}