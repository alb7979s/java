import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static final int COVER_SIZE = (int) 1e6;
	static int N, M, K;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 1 ,1 ,1, 0, -1 , -1, -1};
	static int spreadSet[][] = {{}, {0, 2, 4, 6}, {1, 3, 5, 7}};
	static Queue<Node> q = new LinkedList<>();
	static Map<String, Node> map = new HashMap<>();
	static class Node {
		// spread 0:안합침, 1:모두 짝 or 홀, 2: 그 외 
		int x, y, m, d, s, spread, cnt;
		Node(int x, int y, int m, int d, int s) {
			this(x, y, m, d, s, 0, 1);
		}
		Node(int x, int y, int m, int d, int s, int spread, int cnt) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.d = d;
			this.s = s;
			this.spread = spread;
			this.cnt = cnt;
		}
		public String toString() {
			return x + " " + y + " " + m + " " + d + " " + s;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x, y, m, d, s;
			x = stoi(st.nextToken())-1;
			y = stoi(st.nextToken())-1;
			m = stoi(st.nextToken());
			s = stoi(st.nextToken());
			d = stoi(st.nextToken());
			q.add(new Node(x, y, m, d, s));
		}
		for(int i = 0; i < K; i++) {
			move();
			spread();
		}
		int res = 0;
		while(!q.isEmpty()) {
			res += q.poll().m;
		}
		System.out.println(res);
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static void merge(Node prevNode, Node currNode) {
		prevNode.m += currNode.m;
		prevNode.s += currNode.s;
		prevNode.cnt++;
		if(prevNode.spread == 2) return;
		if(prevNode.d % 2 == currNode.d % 2) prevNode.spread = 1;
		else prevNode.spread = 2;
	}
	static void move() {
		map.clear();
		while(!q.isEmpty()) {
			Node node = q.poll();
			int nx = (node.x + dx[node.d] * node.s + COVER_SIZE*N) % N;
			int ny = (node.y + dy[node.d] * node.s + COVER_SIZE*N) % N;
			if(map.containsKey(nx+","+ny)) {
				merge(map.get(nx+","+ny), node);  
			} else {
				node.x = nx;
				node.y = ny;
				map.put(nx+","+ny, node);
			}
		}
	}
	static void spread() {
		for(Map.Entry<String, Node> entry: map.entrySet()) {
			Node node = entry.getValue();
			if(node.spread > 0) {
				node.m = node.m / 5;
				node.s = (node.s / node.cnt);
				if(node.m == 0) continue;
				for(int d: spreadSet[node.spread]) {
					q.add(new Node(node.x, node.y, node.m, d, node.s));
				}
			} else {
				q.add(new Node(node.x, node.y, node.m, node.d, node.s));
			}
		}
	}
}