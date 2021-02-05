import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, times[], prices[];
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static int solve(int day, int sum) {
		int res = 0;
		if( day > n) return 0;
		if(day == n) {
			res = Math.max(res, sum);
			return res;
		}
		res = Math.max(res, solve(day + times[day], sum + prices[day]));
		res = Math.max(res, solve(day + 1, sum));
		return res;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = stoi(br.readLine());
		times = new int[n];
		prices = new int[n];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = stoi(st.nextToken()), y = stoi(st.nextToken());
			times[i] = x;
			prices[i] = y;
		}
		System.out.println(solve(0, 0));
	}
}