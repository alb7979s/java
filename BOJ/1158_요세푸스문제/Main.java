import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int stoi(String s) {
		return Integer.parseInt(s);
	}

	public static void main(String[] args) throws Exception {
		Queue<Integer> q = new LinkedList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n, k;
		n = stoi(st.nextToken());
		k = stoi(st.nextToken());
		for (int i = 1; i <= n; i++) {
			q.offer(i);
		}
		sb.append("<");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < k - 1; j++) {
				q.offer(q.poll());
			}
			if( i == n-1) sb.append(q.poll());
			else sb.append(q.poll() + ", ");
		}
		sb.append(">");
		System.out.println(sb);
	}
}