import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(solve(0, 0, 0));
	}

	static int solve(int pos, int cnt, int sum) {
		int res = 0;
		if (sum > m || cnt > 3)
			return 0;
		if (pos == n) {
			if (cnt == 3) {
				res = Math.max(res, sum);
			}
			return res;
		}
		res = Math.max(res, solve(pos + 1, cnt + 1, sum + arr[pos]));
		res = Math.max(res, solve(pos + 1, cnt, sum));
		return res;
	}
}
