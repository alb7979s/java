import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[][] visit;
	static String[] board;

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[] dx = { 0, 0, -1, 1 }, dy = { 1, -1, 0, 0 };

	static int bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x, y));
		visit[x][y] = 1;
		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node.x == n - 1 && node.y == m - 1)
				return visit[node.x][node.y];
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if (nx < 0 || ny < 0 || nx > n - 1 || ny > m - 1 || visit[nx][ny] != 0 || board[nx].charAt(ny) != '1')
					continue;
				q.add(new Node(nx, ny));
				visit[nx][ny] = visit[node.x][node.y] + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		board = new String[n];
		visit = new int[n][m];
		for (int i = 0; i < n; i++) {
			board[i] = br.readLine();
		}
		System.out.println(bfs(0, 0));
	}

}