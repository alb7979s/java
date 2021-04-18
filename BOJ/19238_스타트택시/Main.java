import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int n, m, fuel, sx, sy, passanger[][], board[][];
	static HashSet<Integer> destination[][];	// 조건에 목적지는 여러개 올 수 있어서
	static boolean visit[][];
	static class Node implements Comparable<Node>{
		int x, y, dist;
		Node(int x, int y){
			this(x, y, 0);
		}
		Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		@Override
		public int compareTo(Node o) {
			return this.dist == o.dist ? (this.x == o.x ? this.y - o.y : this.x - o.x): this.dist - o.dist;  
		}
	}
	static int dx[] = { 0, 1, 0, -1 }, dy[] = { 1, 0, -1, 0 };
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		fuel = stoi(st.nextToken());
		passanger = new int[n][n];
		destination = new HashSet[n][n];
		board = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				board[i][j] = stoi(st.nextToken());
				destination[i][j] = new HashSet<>();
			}
		}
		st = new StringTokenizer(br.readLine());
		// start x, start y
		sx = stoi(st.nextToken())-1;
		sy = stoi(st.nextToken())-1;
		for(int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			passanger[stoi(st.nextToken())-1][stoi(st.nextToken())-1] = i;
			destination[stoi(st.nextToken())-1][stoi(st.nextToken())-1].add(i);
		}
		if(solve())System.out.println(fuel);
		else System.out.println(-1);
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static boolean canGo(int nx, int ny, int nd) {
		if(nx < 0 || ny < 0 || nx > n-1 || ny > n-1 || visit[nx][ny] || 
				board[nx][ny]==1 || nd > fuel) return false;
		return true;
	}
	static boolean dSearch(Node node) {
		fuel -= node.dist;
		int x = node.x;
		int y = node.y;
		int destNum = passanger[x][y];
		passanger[x][y] = 0;
		visit = new boolean[n][n];
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(x, y, 0));
		visit[x][y] = true;
		while(!q.isEmpty()) {
			node = q.poll();
			if(destination[node.x][node.y].contains(destNum)) {
				fuel += node.dist;
				sx = node.x;
				sy = node.y;
				return true; 
			}
			for(int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				int nd = node.dist + 1;
				if(!canGo(nx, ny, nd)) continue;
				visit[nx][ny] = true;
				q.add(new Node(nx, ny, nd));
			}
		}
		return false;
	}
	static boolean pSearch(int x, int y) {
		visit = new boolean[n][n];
		// pq 글로벌 선언하고 clear해서 쓰면 메모리 더 적게드나?
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(x, y, 0));
		visit[x][y] = true;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			// pq에서 뽑은 단계에서 검사하므로 뽑았을 때는 항상 d, x, y 정렬 유지 보장, pq에 넣는 순서는 상관없음
			if(passanger[node.x][node.y] != 0) {
				return dSearch(node);
			}
			for(int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				int nd = node.dist + 1; 
				if(!canGo(nx, ny, nd)) continue;
				visit[nx][ny] = true;
				pq.add(new Node(nx, ny, nd));
			}
		}
		return false;
	}
	static boolean solve() {
		for(int i = 0; i < m; i++) {
			if(!pSearch(sx, sy)) return false;
		}
		return true;
	}
}
