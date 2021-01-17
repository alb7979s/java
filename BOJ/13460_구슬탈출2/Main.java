//자바 진짜 세상에서 제일 불편한거 같은데요ㅜㅠㅜ 나만 그런가ㅜㅜㅜㅠ
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, -1, 0, 1 };
	static int[][][][] visit;
	static char[][] board;

	public static void main(String[] args) throws IOException {
		System.out.println(bfs());
	}

	public static int bfs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new char[n][m];
		visit = new int[n][m][n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				for (int k = 0; k < n; k++)
					for (int l = 0; l < m; l++)
						visit[i][j][k][l] = -1;
		int srx = 0, sry = 0, sbx = 0, sby = 0;
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				board[i][j] = s.charAt(j);
				if (board[i][j] == 'R') {
					srx = i;
					sry = j;
				} else if (board[i][j] == 'B') {
					sbx = i;
					sby = j;
				}
			}
		}
		Queue<Node> q = new LinkedList<Node>();
		visit[srx][sry][sbx][sby] = 0;
		q.add(new Node(srx, sry, sbx, sby));
		while (!q.isEmpty()) {
			Node node = q.poll();
			int rx, ry, bx, by;
			rx = node.rx;
			ry = node.ry;
			bx = node.bx;
			by = node.by;
			if (visit[rx][ry][bx][by] > 10)
				return -1;
			if (board[rx][ry] == 'O')
				return visit[rx][ry][bx][by];
			for (int i = 0; i < 4; i++) {
				Info rInfo = move(rx, ry, dx[i], dy[i]);
				Info bInfo = move(bx, by, dx[i], dy[i]);
				int nrx = rInfo.x;
				int nry = rInfo.y;
				int rd = rInfo.dist;
				int nbx = bInfo.x;
				int nby = bInfo.y;
				int bd = bInfo.dist;
				if (board[nbx][nby] == 'O')
					continue;
				if ((nrx == nbx) && (nry == nby)) {
					if (rd < bd) {
						nbx -= dx[i];
						nby -= dy[i];
					} else {
						nrx -= dx[i];
						nry -= dy[i];
					}
				}
				if (visit[nrx][nry][nbx][nby] == -1) {
					q.add(new Node(nrx, nry, nbx, nby));
					visit[nrx][nry][nbx][nby] = visit[rx][ry][bx][by] + 1;
				}
			}
		}
		return -1;
	}

	public static Info move(int x, int y, int dx, int dy) {
		int moveDist = 0;
		while (true) {
			x += dx;
			y += dy;
			moveDist++;
			if (board[x][y] == '#')
				return new Info(x - dx, y - dy, moveDist - 1);
			if (board[x][y] == 'O')
				return new Info(x, y, moveDist);
		}
	}

	public static class Node {
		int rx, ry, bx, by;

		public Node(int rx, int ry, int bx, int by) {
			this.rx = rx;
			this.bx = bx;
			this.ry = ry;
			this.by = by;
		}
	}

	public static class Info {
		int x, y, dist;

		public Info(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
}