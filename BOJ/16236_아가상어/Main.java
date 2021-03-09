import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, board[][];
	static int dx[] = { 0, 1, 0, -1 }, dy[] = { 1, 0, -1, 0 };
	static class Node implements Comparable<Node> {
		int d, x, y;
		Node(int d, int x, int y) {
			this.d = d;
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Node o) {
			// 난잡행? 기냥 d, x, y 우선순위로 비교
			return this.d == o.d? (this.x == o.x? this.y - o.y: this.x - o.x): this.d - o.d; 
		}
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = stoi(br.readLine());
		board = new int[n][n];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[][] visit = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = stoi(st.nextToken());
				if(board[i][j] == 9) {
					pq.add(new Node(0, i, j));
					board[i][j] = 0;
					visit[i][j] = true;
				}
			}
		}
		int size=2, eated=0, res=0;
		while(!pq.isEmpty()) {
			int qSize = pq.size();
			for(int i = 0; i < qSize; i++) {
				Node node = pq.poll();
				if(board[node.x][node.y] != 0 && board[node.x][node.y] < size) {
					pq.clear();
					visit = new boolean[n][n];
					visit[node.x][node.y] = true;
					board[node.x][node.y] = 0;
					eated += 1;
					if (eated == size) {
						size += 1;
						eated = 0;
					}
					res += node.d;
					node.d = 0;
				}
				for(int d = 0; d < 4; d++) {
					int nx = node.x + dx[d];
					int ny = node.y + dy[d];
					if(nx < 0 || ny < 0 || nx > n-1 || ny > n-1 || visit[nx][ny] || board[nx][ny] > size)continue;
					pq.add(new Node(node.d+1, nx, ny));
					visit[nx][ny] = true;
				}
			}
		}
		System.out.println(res);
	}
}
