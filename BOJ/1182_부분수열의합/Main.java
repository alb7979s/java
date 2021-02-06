import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static int n, s, arr[];

	static int solve(int pos, int total) {
		int res = 0;
		if (pos == n) {
			if (total == s)
				return 1;
			return 0;
		}
		res += solve(pos + 1, total + arr[pos]);
		res += solve(pos + 1, total);
		return res;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		s = stoi(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = stoi(st.nextToken());
		}
		System.out.println(s == 0 ? solve(0, 0) - 1 : solve(0, 0));
	}
}