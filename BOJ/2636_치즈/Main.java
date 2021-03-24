import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, board[][], visit[][];
	static int dx[] = { 0, 1, 0, -1 }, dy[] = { 1, 0, -1, 0 };
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

	static void melt() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0));
		visit[0][0] = 1;
		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if( nx < 0 || ny < 0 || nx > n-1 || ny > m-1 || visit[nx][ny]!=0)continue;
				if(board[nx][ny] == 1) {
					visit[nx][ny] = 2;
				}else {
					visit[nx][ny] = 1;
					q.add(new Node(nx, ny));
				}
			}
		}
	}
	static int counting() {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(visit[i][j] == 2) {
					cnt++;
					board[i][j] = 0;
				}
			}
		}
		return cnt;
	}
	static int solve() {
		int lastChease = 0, time = 0;
		while(true) {
			visit = new int[n][m];
			melt();
			int chease = counting();
			if(chease == 0) {
				System.out.println(time);
				return lastChease;
			}
			else {
				lastChease = chease;
			}
			time++;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken()); 
		m = stoi(st.nextToken());
		board = new int[n][m];
		boolean beChease = false;
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				board[i][j] = stoi(st.nextToken());
				if(board[i][j] != 0) beChease = true;
			}
		}
		if(beChease)
			System.out.println(solve());
		else {
			System.out.printf("0\n0");
		}
	}
}
