import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m, board[][], dx[] = { 0, 0, -1, 1 }, dy[] = { -1, 1, 0, 0 };
	static boolean[][] visit;
	static int INF = (int)1e9;
	static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static int dfs(int x, int y, int cnt, int total) {
		if (cnt == 4) return total;
		int res = 0;
		visit[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if( nx < 0 || ny < 0 || nx > n-1 || ny > m-1 || visit[nx][ny]) continue;
			visit[nx][ny] = true;
			res = Math.max(res, dfs(nx, ny, cnt+1, total+board[nx][ny]));
			visit[nx][ny] = false;
		}
		visit[x][y] = false;
		return res;
	}
	static int tShape(int x, int y) {
		int sum = 0, cnt = 0, min = INF;
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || ny < 0 || nx > n-1 || ny > m-1) continue;
			sum += board[nx][ny];
			cnt++;
			min = Math.min(min, board[nx][ny]);
		}
		return board[x][y] + (cnt == 3 ? sum : sum - min);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		board = new int[n][m];
		visit = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				board[i][j] = stoi(st.nextToken());
			}
		}
		int res = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				res = Math.max(res, dfs(i, j, 1, board[i][j]));
				res = Math.max(res, tShape(i, j));
			}
		}
		System.out.println(res);
	}
}