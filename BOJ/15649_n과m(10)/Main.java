// n과m(10)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[] arr;
	static boolean[] visit;
	static Stack<Integer> stack = new Stack<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		visit = new boolean[n];
		arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		solve(0, 0);
		System.out.println(sb);
	}

	public static void solve(int pos, int cnt) {
		if (cnt == m) {
			for (int x : stack) {
				sb.append(x);
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}
		int prev = -1;
		for (int i = pos; i < n; i++) {
			if (!visit[i] && prev != arr[i]) {
				visit[i] = true;
				stack.push(arr[i]);
				solve(i, cnt + 1);
				stack.pop();
				visit[i] = false;
				prev = arr[i];
			}
		}
	}
}