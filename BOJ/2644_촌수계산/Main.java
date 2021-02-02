import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, source, sink;
	static boolean[][] connected;
	static boolean[] visit;

	static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static int bfs() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(source, 0));
		visit[source] = true;
		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node.cur == sink) return node.cnt;
			for (int next = 0; next < n; next++) {
				if (connected[node.cur][next] && !visit[next]) {
					visit[next] = true;
					q.add(new Node(next, node.cnt + 1));
				}
			}
		}
		return -1;
	}

	static class Node {
		int cur, cnt;
		Node(int cur, int cnt) {
			this.cur = cur;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = stoi(br.readLine());
		connected = new boolean[n][n];
		visit = new boolean[n];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		source = stoi(st.nextToken()) - 1;
		sink = stoi(st.nextToken()) - 1;
		int m = stoi(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = stoi(st.nextToken()) - 1;
			int v = stoi(st.nextToken()) - 1;
			connected[u][v] = true;
			connected[v][u] = true;
		}
		System.out.println(bfs());
	}
}