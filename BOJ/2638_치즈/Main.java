import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, board[][], visit[][];
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static int dx[] = { 0, 1, 0, -1 }, dy[] = { 1, 0, -1, 0 };
	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int melt() {
		visit = new int[n][m];
		Queue<Node> q = new LinkedList<>();
		visit[0][0] = 1;
		q.add(new Node(0, 0));
		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if (nx < 0 || ny < 0 || nx > n-1 || ny > m-1) continue;
				if(board[nx][ny] == 1) {
					visit[nx][ny]++;
				}else {
					if(visit[nx][ny] == 0) {
						visit[nx][ny]++;
						q.add(new Node(nx, ny));
					}
				}
			}
		}
		int meltedChease = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(visit[i][j] >= 2) {
					meltedChease++;
					board[i][j] = 0;
				}
			}
		}
		return meltedChease;
	}
	static int solve() {
		int time = 0;
		while(true) {
			if(melt() == 0) return time;
			time++;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		board = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				board[i][j] = stoi(st.nextToken());
			}
		}
		System.out.println(solve());
	}
}
