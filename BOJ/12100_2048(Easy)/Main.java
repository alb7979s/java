import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n, res, board[][];
	static int dx[] = { 0, 1, 0, -1 }, dy[] = { 1, 0, -1, 0 }; // RDLU

	static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static void move(int x, int y, int dx, int dy) {
		int nx = x, ny = y;
		int idx = 0;
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (board[nx][ny] != 0) {
				if (list.size() <= idx) {
					list.add(board[nx][ny]);
				} else {
					if (list.get(idx) == board[nx][ny]) {
						list.set(idx, board[nx][ny] * 2);
						res = Math.max(res, list.get(idx));
					}
					else {
						list.add(board[nx][ny]);
					}
					idx++;
				}
				board[nx][ny] = 0;
			}
			nx += dx;
			ny += dy;
		}
		nx = x; ny = y;
		for (int v: list) {
			board[nx][ny] = v;
			nx += dx;
			ny += dy;
		}
	}

	static void solve(int cnt) {
		if (cnt == 5)
			return;
		int[][] temp = new int[n][n];
		// deepCopy 메서드로 빼면 좋은데 귀찮
		for (int i = 0; i < n; i++) {
			temp[i] = board[i].clone();
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0)
					move(j, n - 1, 0, -1);
				if (i == 1)
					move(n - 1, j, -1, 0);
				if (i == 2)
					move(j, 0, 0, 1);
				if (i == 3)
					move(0, j, 1, 0);
			}
			solve(cnt + 1);
			for (int j = 0; j < n; j++) {
				board[j] = temp[j].clone();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = stoi(br.readLine());
		board = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				board[i][j] = stoi(st.nextToken());
				res = Math.max(res, board[i][j]);
			}
		}
		solve(0);
		System.out.println(res);
	}
}