import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] connected;
	static boolean[] visit;
	static int n, m, s;

	static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static void dfs(int u) {
		System.out.print(u+1 + " ");
		visit[u] = true;
		for (int v = 0; v < n; v++) {
			if (connected[u][v] && !visit[v]) {
				dfs(v);
				visit[v] = true;
			}
		}
	}

	static void bfs(int u) {
		Queue<Integer> q = new LinkedList<>();
		q.add(u);
		visit[u] = true;
		while (!q.isEmpty()) {
			u = q.poll();
			System.out.print(u+1 + " ");
			for (int v = 0; v < n; v++) {
				if (connected[u][v] && !visit[v]) {
					q.add(v);
					visit[v] = true;
				}
			}
		}
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		s = stoi(st.nextToken()) - 1;
		connected = new boolean[n][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = stoi(st.nextToken()) - 1;
			int v = stoi(st.nextToken()) - 1;
			connected[u][v] = true;
			connected[v][u] = true;
		}
		visit = new boolean[n];
		dfs(s);
		System.out.println();
		visit = new boolean[n];
		bfs(s);
	}
}