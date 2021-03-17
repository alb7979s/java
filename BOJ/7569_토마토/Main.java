import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, h, board[][][], zeroCnt;
	static int[] dx = {0, 0, 0, 0, 1, -1}, dy = {0, 0, 1, -1, 0, 0}, dz = {1, -1, 0, 0, 0, 0};
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static class Node {
		int x, y, h;

		Node(int x, int y, int h) {
			this.x = x;
			this.y = y;
			this.h = h;
		}
	}
	static int bfs(Queue<Node> q) {
		int cnt = 0, res = 1;
		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int i = 0; i < 6; i++) {
				int nh = node.h + dz[i];
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if( nx < 0 || ny < 0 || nh < 0 || nx > n-1 || ny > m-1 || nh > h-1 || board[nh][nx][ny]!=0)continue;
				board[nh][nx][ny] = board[node.h][node.x][node.y] + 1;
				res = Math.max(res, board[nh][nx][ny]);
				q.add(new Node(nx, ny, nh));
				cnt++;
			}
		}
		if (cnt == zeroCnt) return res-1;
		return -1;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = stoi(st.nextToken());
		n = stoi(st.nextToken());
		h = stoi(st.nextToken());
		board = new int[h][n][m];
		Queue<Node> q = new LinkedList<>();
		for(int k = 0; k < h; k++) {
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < m; j++) {
					board[k][i][j] = stoi(st.nextToken());
					if(board[k][i][j]==1) q.add(new Node(i ,j, k));
					else if(board[k][i][j]==0) zeroCnt++;
				}
			}
		}
		System.out.println(bfs(q));
	}
}
