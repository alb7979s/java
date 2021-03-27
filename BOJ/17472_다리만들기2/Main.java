import java.io.*;
import java.util.*;

public class Main {
	static int n, m, board[][], parent[];
	static int dx[] = { 0, 1, 0, -1 }, dy[] = { 1, 0, -1, 0 };
	static List<Node> nodeList = new ArrayList<>();		//MST 구하기 위해 여기에 정보 다 저장해요
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static class Node implements Comparable<Node>{
		int x, y, dist;
		// 인덱싱 할 때 x, y 좌표가 필요해서 만듦
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		// 노드 따로 만들기 귀찮아서 그냥 전에 쓴 노드 오버로딩, x => u(이전 정점), y => v(다음 정점), dist => 거리
		Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		// 정렬해주는거, 전 Comparable이 편해서 여기 정의해뒀어요 
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
	}
	static int find(int u) {
		if(u == parent[u]) return u;
		return parent[u] = find(parent[u]);
	}
	// union 되면 true로 표시해줌, 이미 같아서 안되면 false, 나중에 MST 구할때 이 정보 활용
	static boolean union(int u, int v) {
		u = find(u);
		v = find(v);
		if(u == v) return false;
		parent[u] = v;
		return true;
	}
	// 범위 체크하는 메소드, 그냥 두번 나오길래 빼줌
	static boolean correctScope(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx > n-1 || ny > m-1) return false;
		return true;
	}
	// 섬별로 번호 붙여줌
	static void indexing(int x, int y, int cnt) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(x, y));
		board[x][y] = cnt;
		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if (!correctScope(nx, ny) || board[nx][ny] != 1) continue;
				board[nx][ny] = cnt;
				q.add(new Node(nx, ny));
			}
		}
	}
	// 다리 연결 prev 이용해서 다리 연결 가능하면(prev, board[x][y]로 두 지점이 있고, 연결된 connected가 2 이상이면) 
	static void connect(int x, int y, int dx, int dy) {
		int prev = -1, connected = 0;
		while(true) {
			if(board[x][y] != 0) {
				if(prev != -1 && connected >= 2) {
					nodeList.add(new Node(prev, board[x][y], connected));
				}
				prev = board[x][y];
				connected = 0;
			}
			x += dx;
			y += dy;
			if(!correctScope(x, y)) return;
			if(board[x][y] == 0) connected++;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		board = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				board[i][j] = stoi(st.nextToken());
			}
		}
		// 인덱싱 2부터 하는데(1로 초기화, ++cnt로 2부터 사용), visit배열 만들기 세상 귀찮기 때문.
		int cnt = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 1) {
					indexing(i, j, ++cnt);
				}
			}
		}
		// 가로로 연결
		for (int i = 0; i < n; i++) {
			connect(i, 0, 0, 1);
		}
		// 세로로 연결
		for (int i = 0; i < m; i++) {
			connect(0, i, 1, 0);
		}
		// MST 구함(크루스칼)
		parent = new int[cnt+1];
		for(int i = 0; i < cnt+1; i++) parent[i] = i;
		Collections.sort(nodeList);
		int res = 0;
		boolean complete = false;
		for(Node node: nodeList) {
			if(union(node.x, node.y)) {
				res += node.dist;
				cnt--;
				if (cnt == 2) {	//2부터 시작했으니 2가 되면 MST 완성, MST 완성 되면 표시하고 종료
					complete = true;
					break;	
				}
			}
		}
		System.out.println(complete?res:-1); // MST 완성 안되었으면 -1
	}
}

