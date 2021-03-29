import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final long INF = (long)1e18;
	static int n, m, k;
	static long dist[][];
	static List<Node>[] adj;
	static class Node implements Comparable<Node> {
		int u, k;
		long d;

		Node(int u, long d) {
			this.u = u;
			this.d = d;
		}
		Node(int u, long d, int k) {
			this.u = u;
			this.d = d;
			this.k = k;
		}
		public int compareTo(Node o) {
			return Long.compare(this.d, o.d);
		}
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		k = stoi(st.nextToken())+1;
		dist = new long[n][k];
		adj = new List[n];
		for(int i = 0; i < n; i++) {
			for (int j = 0; j < k; j++) {
				dist[i][j] = INF;
			}
			adj[i] = new ArrayList<>();
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u, v, d;
			u = stoi(st.nextToken())-1;
			v = stoi(st.nextToken())-1;
			d = stoi(st.nextToken());
			adj[u].add(new Node(v, d));
			adj[v].add(new Node(u, d));
		}
		dijkstra(0);
		long res = INF;
		for(int i = 0; i < k; i++) {
			res = Math.min(res, dist[n-1][i]);
		}
//		System.out.println(Arrays.deepToString(dist));
		System.out.println(res);
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0, 0));
		dist[0][0] = 0;
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			int nextK = curr.k + 1;
			if(dist[curr.u][curr.k] < curr.d) continue;
			for(Node next: adj[curr.u]) {
				// 포장 가능
				if( nextK < k) {
					if(dist[next.u][nextK] > curr.d) {
						dist[next.u][nextK] = curr.d;
						pq.add(new Node(next.u, dist[next.u][nextK], nextK));
					}
				}
				// 그냥 감
				if(dist[next.u][curr.k] > curr.d + next.d) {
					dist[next.u][curr.k] = curr.d + next.d;
					pq.add(new Node(next.u, dist[next.u][curr.k], curr.k));
				}
			}
		}
	}
}
