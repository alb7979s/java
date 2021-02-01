import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main{
	static String[] board;
	static boolean[][] visit;
	static int dx[] = {0, 0, -1, 1}, dy[] = {1, -1, 0, 0};
	static int n;
	static class Node{
		int x, y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static int bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		int cnt = 1;
		visit[x][y] = true;
		q.add(new Node(x, y));
		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if (nx < 0 || ny < 0 || nx > n-1 || ny > n-1 || visit[nx][ny] || board[nx].charAt(ny) != '1') continue;
				q.add(new Node(nx, ny));
				visit[nx][ny] = true;
				cnt++;
			}
		}
		return cnt;
	}
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new String[n];
		visit = new boolean[n][n];
		for(int i=0; i<n; i++) {
			board[i] = br.readLine();
		}
		List<Integer> res = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j ++) {
				if( !visit[i][j] && board[i].charAt(j) == '1') {
					res.add(bfs(i, j));
				}
			}
		}
		Collections.sort(res);
		System.out.println(res.size());
		for(int r: res) {
			System.out.println(r);
		}
	}
}