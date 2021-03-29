import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static final long INF = (long)1e18;
	static int n, m;
	static long dist[];
	static List<Node>[] adj;
	static class Node {
		int u;
		long d;
		Node(int u, long d) {
			this.u = u;
			this.d = d;
		}
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		dist = new long[n];
		adj = new List[n];
		for(int i = 0; i < n; i++) {
			dist[i] = INF;
			adj[i] = new ArrayList<>();
		}
		for(int i = 0; i < m; i++) {
			int u, v, d;
			st = new StringTokenizer(br.readLine());
			u = stoi(st.nextToken())-1;
			v = stoi(st.nextToken())-1;
			d = stoi(st.nextToken());
			adj[u].add(new Node(v, d));
		}
		boolean loop = false;
		dist[0] = 0;
		for(int i = 0; i < n; i++) {
			for (int u = 0; u < n; u++) {
				for(Node next: adj[u]) {
					if((dist[u] != INF) && (dist[next.u] > (dist[u] + next.d))) {
						dist[next.u] = dist[u] + next.d;
						if(i == n-1) loop = true;
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		if(loop) sb.append(-1);
		else {
			for(int i = 1 ; i < n; i++) {
				sb.append(dist[i] != INF ? dist[i] : -1).append("\n");
			}
		}
		System.out.println(sb);
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
