import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main {
	static int n, parent[];
	static List<Node> list;
	static List<Edge> edgeList;
	static class Edge {
		int u, v, val;
		Edge(int u, int v, int val) {
			this.u = u;
			this.v = v;
			this.val = val;
		}
	}
	static class Node {
		int x, y, z, idx;
		Node(int x, int y, int z, int idx) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.idx = idx;
		}
	}
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
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = stoi(br.readLine());
		list = new ArrayList<>();
		edgeList = new ArrayList<>();
		parent = new int[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Node(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()), i));
		}
		Collections.sort(list, (o1, o2) -> o1.x - o2.x);
		for(int i = 0; i < n - 1; i++) {
			edgeList.add(new Edge(list.get(i).idx, list.get(i+1).idx, list.get(i+1).x - list.get(i).x));
		}
		Collections.sort(list, (o1, o2) -> o1.y - o2.y);
		for(int i = 0; i < n - 1; i++) {
			edgeList.add(new Edge(list.get(i).idx, list.get(i+1).idx, list.get(i+1).y - list.get(i).y));
		}
		Collections.sort(list, (o1, o2) -> o1.z - o2.z);
		for(int i = 0; i < n - 1; i++) {
			edgeList.add(new Edge(list.get(i).idx, list.get(i+1).idx, list.get(i+1).z - list.get(i).z));
		}
		int sum = 0;
		int cnt = 0;
		for(int i = 0; i < n; i++) parent[i] = i;
		Collections.sort(edgeList, (o1, o2) -> o1.val - o2.val);
		for(Edge edge: edgeList) {
			if(!union(edge.u, edge.v)) {
				sum += edge.val;
				cnt++;
			}
			if(cnt == n-1) break;
		}
		System.out.println(sum);
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
