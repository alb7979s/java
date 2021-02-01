import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int stoi(String s) {return Integer.parseInt(s);}
	static int n, m;
	static int[] dx = {0, 0, -1, 1}, dy = {1, -1, 0, 0};
	static int[][] board, visit;
	static class Node{
		int x, y;
		public Node() {}
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static void bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x, y));
		visit[x][y] = 1;
		while(!q.isEmpty()) {
			Node loc = q.poll(); 
			for(int i = 0; i < 4; i++) {
				int nx = loc.x + dx[i];
				int ny = loc.y + dy[i];
				if( nx < 0 || ny < 0 || nx > n-1 || ny > m-1 || visit[nx][ny]==1 || board[nx][ny]!=1)continue;
				visit[nx][ny] = 1;
				q.add(new Node(nx, ny));
			}
		}
	}
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = stoi(br.readLine()); 
		for(int tc = 0; tc < testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			m = stoi(st.nextToken());
			n = stoi(st.nextToken());
			board = new int[n][m];
			visit = new int[n][m];
			int k = stoi(st.nextToken());
			for(int i=0; i<k; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int y = stoi(st.nextToken());
				int x = stoi(st.nextToken());
				board[x][y] = 1;
			}
			int cnt = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(visit[i][j] == 0 && board[i][j] == 1) {
						bfs(i, j);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}
}