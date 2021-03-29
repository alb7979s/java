import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
		int start, end;
		n = stoi(st.nextToken());
		start = stoi(st.nextToken());
		end = stoi(st.nextToken());
		m = stoi(st.nextToken());
		int getMoney[] = new int[n];
		dist = new long[n];
		adj = new List[n];
		for(int i = 0; i < n; i++) {
			dist[i] = -INF;
			adj[i] = new ArrayList<>();
		}
		for(int i = 0; i < m; i++) {
			int u, v, d;
			st = new StringTokenizer(br.readLine());
			u = stoi(st.nextToken());
			v = stoi(st.nextToken());
			d = stoi(st.nextToken());
			adj[u].add(new Node(v, d));
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) getMoney[i] = stoi(st.nextToken());
		dist[start] = getMoney[start];
		Queue<Integer> q = new LinkedList<>();
		for(int i = 0; i < n; i++) {
			for(int u = 0; u < n; u++) {
				for(Node next: adj[u]) {
					if( dist[u] != -INF && (dist[next.u] < dist[u] - next.d + getMoney[next.u])) {
						dist[next.u] = dist[u] - next.d + getMoney[next.u];
						if(i == n-1) {
							q.add(next.u);
						}
					}
				} 
			}
		}
		List<Integer> loopVertex = new ArrayList<>();
		boolean[] check = new boolean[n];
		while(!q.isEmpty()) {
			int u = q.poll();
			loopVertex.add(u);
			check[u] = true;
			for(Node next: adj[u]) {
				if(check[next.u]) continue;
				q.add(next.u);
			}
		}
		if(dist[end] == -INF) System.out.println("gg");
		else if(loopVertex.contains(end)) System.out.println("Gee");
		else System.out.println(dist[end]);
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
