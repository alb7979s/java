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
		int u, v, d;

		Node(int v, int d) {
			this.v = v;
			this.d = d;
		}
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken())+1;
		m = stoi(st.nextToken());
		int k = stoi(st.nextToken());
		List<Node> nodeList = new ArrayList<>();
		parent = new int[n];
		for(int i = 0; i < n; i++) parent[i] = i;
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < n; i++) nodeList.add(new Node(i, stoi(st.nextToken())));
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = stoi(st.nextToken());
			int v = stoi(st.nextToken());
			union(u, v);
		}
		Collections.sort(nodeList, (o1, o2)->(o1.d - o2.d));
		int res = 0;
		for(Node node: nodeList) {
			if(!union(node.u, node.v)) res += node.d;
		}
		System.out.println(res <= k ? res : "Oh no");
	}
}
