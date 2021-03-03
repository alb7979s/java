import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int dx[] = { 0, 1, 0, -1 }, dy[] = { 1, 0, -1, 0 };
	static int n, l, r, board[][];
	static boolean occur, visit[][];
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
	static boolean bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		List<Node> union = new ArrayList<>();
		visit[x][y] = true;
		q.add(new Node(x, y));
		union.add(new Node(x, y));
		int sum = board[x][y];
		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if(nx < 0 || ny < 0 || nx > n-1 || ny > n -1 || visit[nx][ny]) continue;
				int diff = Math.abs(board[nx][ny] - board[node.x][node.y]);
				if( l <= diff && diff <= r ) {
					visit[nx][ny] = true;
					q.add(new Node(nx, ny));
					union.add(new Node(nx, ny));
					sum += board[nx][ny];
				}
			}
		}
		int cnt = union.size();
		if(cnt == 1) return false;
		int population = sum / cnt;
		for(Node node: union) {
			board[node.x][node.y] = population;
		}
		return true;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		l = stoi(st.nextToken());
		r = stoi(st.nextToken());
		board = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = stoi(st.nextToken());
			}
		}
		int res = 0;
		while(true) {
			visit = new boolean[n][n];
			occur = false;
			for(int i = 0; i < n; i++) 
				for(int j = 0; j < n; j++) 
					if(!visit[i][j]) occur |= bfs(i, j);
			if(occur) res++;
			else break;
		}
		System.out.println(res);
	}
}