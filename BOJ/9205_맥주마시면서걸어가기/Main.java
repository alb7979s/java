import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final int INF = (int)1e9;
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static class Node implements Comparable<Node>{
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node o) {
			return this.x - o.x;
		}
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = stoi(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc <= testCase; tc++) {
			int n = stoi(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sx = stoi(st.nextToken());
			int sy = stoi(st.nextToken());
			List<Node> coords = new ArrayList<>();
			coords.add(new Node(sx, sy));
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				coords.add(new Node(stoi(st.nextToken()), stoi(st.nextToken())));
			}
			st = new StringTokenizer(br.readLine());
			int ex = stoi(st.nextToken());
			int ey = stoi(st.nextToken());
			coords.add(new Node(ex, ey));
			int[] dist = new int[n+2];
			Arrays.fill(dist, INF);
			List<Integer>[] adj = new List[n+2];
			for(int i = 0; i < n+2; i++) adj[i] = new ArrayList<>();
			for(int i = 0; i < n+2; i++) {
				for(int j = i+1; j < n+2; j++) {
					int ix, iy, jx, jy;
					ix = coords.get(i).x;
					iy = coords.get(i).y;
					jx = coords.get(j).x;
					jy = coords.get(j).y;
					if(Math.abs(ix - jx) + Math.abs(iy - jy) <= 1000) {
						adj[i].add(j);
						adj[j].add(i);
					}
				}
			}
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(0, 0));
			while(!pq.isEmpty()) {
				Node node = pq.poll();
				for(int v: adj[node.y]) {
					if(dist[v] > node.x) {
						dist[v] = node.x;
						pq.add(new Node(dist[v], v));
					}
				}
			}
			sb.append(dist[n+1]!=INF ?"happy" : "sad").append("\n");
		}
		System.out.println(sb);
	}
}
