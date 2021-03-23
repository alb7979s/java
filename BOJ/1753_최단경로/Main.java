import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int V, E, start, dist[];
	static final int INF = Integer.MAX_VALUE;
	static List<Node> adj[];
	
	static class Node implements Comparable<Node>{
		int d, u;
		Node(int d, int u) {
			this.d = d;
			this.u = u;
		}
		@Override
		public int compareTo(Node o) {
			return this.d - o.d;
		}
	}

	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static void dijkstra(int V, int E, int start) {
		dist = new int[V+1];
		for(int i = 0; i < V+1; i++) dist[i] = INF;
		dist[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, start));
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int d = node.d;
			int u = node.u;
			if(dist[u] != d) continue;
			for(Node adjNode: adj[u]) {
				int ad = adjNode.d;
				int v = adjNode.u;
				if( d + ad < dist[v]) {
					dist[v] = d + ad;
					pq.add(new Node(d+ad, v));
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < V+1; i++) {
			sb.append(dist[i] != INF ? dist[i] : "INF").append("\n");
		}
		System.out.println(sb);
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = stoi(st.nextToken());
		E = stoi(st.nextToken());
		start = stoi(br.readLine());
		adj = new List[V+1];
		for (int i = 0; i < V+1; i++) {
			adj[i] = new ArrayList<>(); 
		}
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a, b, c;
			a = stoi(st.nextToken());
			b = stoi(st.nextToken());
			c = stoi(st.nextToken());
			adj[a].add(new Node(c, b));
		}
		dijkstra(V, E, start);
	}
}
