import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, goalCnt;
	static int[][] board, visit;
	static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
	static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int bfs(Queue<Node> q) {
		int cnt = 0;
		while (!q.isEmpty()) {
			Node node = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if( nx < 0 || ny < 0 || nx > n-1 || ny > m-1 || visit[nx][ny] != -1 || board[nx][ny] != 0 )continue;
				visit[nx][ny] = visit[node.x][node.y] + 1;
				cnt++;
				if( cnt == goalCnt) return visit[nx][ny];
				q.add(new Node(nx, ny));
			}
		}
		return -1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		m = stoi(st.nextToken());
		n = stoi(st.nextToken());
		board = new int[n][m];
		visit = new int[n][m];
		Queue<Node> q = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				board[i][j] = stoi(st.nextToken());
				visit[i][j] = -1;			// visit -1로 초기화 해줘야하는데 자바 한번에 하는거 없어서 그냥 여기에 껴넣었어요
				if (board[i][j] == 1) {
					visit[i][j] = 0;
					q.add(new Node(i, j));
				} else if (board[i][j] == 0) {
					goalCnt++;
				}
			}
		}
		System.out.println(goalCnt==0 ? 0: bfs(q));
	}
}