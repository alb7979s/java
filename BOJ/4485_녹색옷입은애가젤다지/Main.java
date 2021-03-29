import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final int INF = (int)1e9;
	static int n, board[][], dist[][];
	static class Node implements Comparable<Node>{
		int x, y, d;

		Node(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
		public int compareTo(Node o) {
			return this.d - o.d;
		}
	}
	static int dx[] = { 0, 1, 0, -1 }, dy[] = { 1, 0, -1, 0 };
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 1;
		while((n = stoi(br.readLine())) != 0) {
			board = new int[n][n];
			dist = new int[n][n];
			for(int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					board[i][j] = stoi(st.nextToken());
					dist[i][j] = INF;
				}
			}
			dijkstra(0, 0);
			sb.append("Problem " + tc + ": " + dist[n-1][n-1] + "\n");
			tc++;
		}
		System.out.println(sb);
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static void dijkstra(int x, int y) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(x, y, board[x][y]));
		dist[x][y] = board[x][y];
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(dist[node.x][node.y] < node.d) continue;
			for(int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if(nx < 0 || ny < 0 || nx > n-1 || ny > n-1) continue;
				if( dist[nx][ny] > board[nx][ny] + node.d) {
					dist[nx][ny] = board[nx][ny] + node.d;
					pq.add(new Node(nx, ny, dist[nx][ny]));
				}
			}
		}
	}
}
