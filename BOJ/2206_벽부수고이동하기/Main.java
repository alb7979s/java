import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, visit[][][];
	static int dx[] = { 0, 1, 0, -1 }, dy[] = { 1, 0, -1, 0 };
	static class Node {
		int x, y, depth;

		Node(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}
	static char board[][];
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static Queue<Node> q;
	static int bfs() {
		q = new LinkedList<>();
		q.add(new Node(0, 0, 0));
		visit[0][0][0] = 1;
		while(!q.isEmpty()) {
			Node node = q.poll();
			if (node.x == n-1 && node.y == m-1) return visit[node.x][node.y][node.depth];
			for(int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if ( nx < 0 || ny < 0 || nx > n-1 || ny > m-1 ) continue;
				if(board[nx][ny] == '0' && visit[nx][ny][node.depth]==0) {
					visit[nx][ny][node.depth] = visit[node.x][node.y][node.depth] + 1;
					q.add(new Node(nx, ny, node.depth));
				}
				else if(board[nx][ny] == '1' && node.depth==0 && visit[nx][ny][node.depth+1]==0) {
					visit[nx][ny][node.depth+1] = visit[node.x][node.y][node.depth] + 1;
					q.add(new Node(nx, ny, node.depth+1));
				}
			}
		}
		return -1;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		board = new char[n][m];
		visit = new int[n][m][2];
		for (int i = 0; i < n; i++) {
			board[i] = br.readLine().toCharArray();
		}
		System.out.println(bfs());
	}
}
