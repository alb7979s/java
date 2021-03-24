import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int k, n, m, board[][], visit[][][];
	static int dx[] = { 0, 1, 0, -1 }, dy[] = { 1, 0, -1, 0 }, hx[] = {-2, -2, -1, 1, 2, 2, 1, -1}, hy[] = {-1, 1, 2, 2, 1, -1, -2, -2};
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static class Node {
		int x, y, chance;

		Node(int x, int y, int chance) {
			this.x = x;
			this.y = y;
			this.chance = chance;
		}
	}
	static boolean scope(int nx, int ny, int chance) {
		if(nx < 0 || ny < 0 || nx > n-1 || ny > m-1 || visit[nx][ny][chance]!=0 || board[nx][ny]==1) return false;
		return true;
	}
	static int solve() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 0));
		visit[0][0][0] = 1;
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(node.x == n-1 && node.y == m-1) return visit[node.x][node.y][node.chance]-1;
			if (node.chance < k) {
				for(int i = 0; i < 8; i++) {
					int nx = node.x + hx[i];
					int ny = node.y + hy[i];
					int nc = node.chance + 1;
					if(scope(nx, ny, nc)) {
						q.add(new Node(nx, ny, nc));
						visit[nx][ny][nc] = visit[node.x][node.y][node.chance] + 1; 
					}
				}
			}
			for(int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if(scope(nx, ny, node.chance)) {
					q.add(new Node(nx, ny, node.chance));
					visit[nx][ny][node.chance] = visit[node.x][node.y][node.chance] + 1;
				}
			}
		}
		return -1;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = stoi(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = stoi(st.nextToken());
		n = stoi(st.nextToken());
		board = new int[n][m];
		visit = new int[n][m][k+1];
		for(int i = 0; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				board[i][j] = stoi(st.nextToken());
			}
		}
		System.out.println(solve());
	}
}
