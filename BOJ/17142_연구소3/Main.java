import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static int n, m, zeroCnt, board[][];
	static int dx[] = { 0, 1, 0, -1 }, dy[] = { 1, 0, -1, 0 };
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static List<Node> virusList;
	static Stack<Node> activeList;
	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int cal() {
		Queue<Node> q = new LinkedList<>();
		int visit[][] = new int[n][n];
		int cnt = 0;
		if(zeroCnt == 0) return 0;
		for(Node node: activeList) {
			q.add(node);
			visit[node.x][node.y] = 1;
		}
		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if( nx < 0 || ny < 0 || nx > n-1 || ny > n-1 || visit[nx][ny]!=0 || board[nx][ny]==1) continue;
				if (board[nx][ny]==0) cnt++;
				visit[nx][ny] = visit[node.x][node.y] + 1;
				q.add(new Node(nx, ny));
				if(cnt == zeroCnt) return visit[nx][ny]-1;
			}
		}
		return INF;
	}
	static int solve(int pos, int cnt) {
		int res = INF;
		if(cnt > m) return INF;
		if(pos == virusList.size()) {
			if(cnt == m) {
				return cal();
			}
			return res;
		}
		activeList.add(virusList.get(pos));
		res = Math.min(res, solve(pos+1, cnt+1));
		activeList.pop();
		res = Math.min(res,  solve(pos+1, cnt));
		return res;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		board = new int[n][n];
		virusList = new ArrayList<>();
		activeList = new Stack<>();
		for(int i = 0; i < n ; i ++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = stoi(st.nextToken());
				if(board[i][j] == 2) virusList.add(new Node(i, j));
				else if(board[i][j] == 0) zeroCnt++; 
			}
		}
		int res = solve(0, 0);
		System.out.println(res != INF? res: -1);
	}
}

