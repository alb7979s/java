import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int n, m, totalZero, board[][], visit[][], dx[] = { 0, 0, 1, -1 }, dy[] = { -1, 1, 0, 0 };
	static Stack<Node> zeroList, virusList;
	static int INF = (int) 1e9;

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static int bfs() {
		int zero = 0;
		Queue<Node> q = new LinkedList<>();
		visit = new int[n][m];
		for (Node node : virusList) {
			visit[node.x][node.y] = 1;
			q.add(node);
		}
		while (!q.isEmpty()) {
			Node node = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if (nx < 0 || ny < 0 || nx > n - 1 || ny > m - 1 || visit[nx][ny] != 0 || board[nx][ny] != 0)
					continue;
				zero++;
				visit[nx][ny] = visit[node.x][node.y] + 1;
				q.add(new Node(nx, ny));
			}
		}
		return totalZero - zero;
	}

	static int solve(int pos, int cnt) {
		int res = -INF;
		if (cnt > 3)
			return res;
		if (pos == zeroList.size()) {
			if (cnt == 3) {
				res = Math.max(res, bfs());
			}
			return res;
		}
		Node node = zeroList.get(pos);
		board[node.x][node.y] = 1;
		res = Math.max(res, solve(pos + 1, cnt + 1));
		board[node.x][node.y] = 0;
		res = Math.max(res, solve(pos + 1, cnt));
		return res;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		board = new int[n][m];
		zeroList = new Stack<>();
		virusList = new Stack<>();
		totalZero = -3;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				board[i][j] = stoi(st.nextToken());
				if (board[i][j] == 0) {
					zeroList.add(new Node(i, j));
					totalZero++;
				}
				if (board[i][j] == 2) {
					virusList.add(new Node(i, j));
				}
			}
		}
		int res = solve(0, 0);
		System.out.println(res);
	}
}