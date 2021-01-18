//n과m(11)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[] arr;
	static Stack<Integer> stack = new Stack<>();
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		sb = new StringBuilder();
		arr = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		solve(0);
		System.out.println(sb);
	}

	public static void solve(int cnt) throws Exception {
		if (cnt == m) {
			for (int x : stack) {
				sb.append(x + " ");
			}
			sb.append("\n");
			return;
		}
		int prev = -1;
		for (int i = 0; i < n; i++) {
			if (prev != arr[i]) {
				stack.push(arr[i]);
				solve(cnt + 1);
				stack.pop();
				prev = arr[i];
			}
		}
	}
}
