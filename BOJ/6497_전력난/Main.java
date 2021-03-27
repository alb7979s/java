import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n, m, parent[];
	static int find(int u) {
		if(u == parent[u]) return u;
		return parent[u] = find(parent[u]); 
	}
	static boolean union(int u, int v) {
		u = find(u);
		v = find(v);
		if(u == v) return true;
		parent[u] = v;
		return false;
	}
	static class Node {
		int x, y, d;

		Node(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			m = stoi(st.nextToken());
			if(n == 0 && m == 0) break;
			parent = new int[n];
			List<Node> nodeList = new ArrayList<>();
			for (int i = 0; i < n; i++) parent[i] = i; 
			int sum = 0;
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int u = stoi(st.nextToken());
				int v = stoi(st.nextToken());
				int d = stoi(st.nextToken());
				nodeList.add(new Node(u, v, d));
				sum += d;
			}
			int mst = 0;
			Collections.sort(nodeList, (o1, o2) -> (o1.d - o2.d));
			for(Node node: nodeList) {
				if(!union(node.x, node.y)) mst += node.d;
			}
			sb.append(sum - mst).append("\n");
		}
		System.out.println(sb);
	}
}
