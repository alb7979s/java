import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int V, E, parent[];
	static int find(int u) {
		if(parent[u] == u) return u;
		return parent[u] = find(parent[u]);
	}
	static boolean union(int u, int v) {
		u = find(u);
		v = find(v);
		if(u==v) return true;
		parent[u] = v;
		return false;
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static class Node implements Comparable<Node>{
		int u, v, dist;
		public Node(int u, int v, int dist) {
			this.u = u;
			this.v = v;
			this.dist = dist;
		}
		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = stoi(st.nextToken());
		E = stoi(st.nextToken());
		parent = new int[V];
		for(int i = 0; i < V; i++) parent[i] = i;
		Node[] nodeList = new Node[E];
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u, v, dist;
			u = stoi(st.nextToken())-1;
			v = stoi(st.nextToken())-1;
			dist = stoi(st.nextToken());
			nodeList[i] = new Node(u, v, dist);
		}
		Arrays.sort(nodeList);
		int sum = 0;
		for(Node node: nodeList) {
			if(!union(node.u, node.v)) sum += node.dist;
		}
		System.out.println(sum);
	}
}
