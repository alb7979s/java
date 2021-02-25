import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static final int U = 1 << 0, R = 1 << 1, D = 1 << 2, L = 1 << 3;
	static int n, m, board[][], dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0, -1};
	static int cctv[][] = {{}, 
			{U, R, D, L}, 
			{U|D, L|R}, 
			{U|R, R|D, D|L, L|U},
			{U|R|D, R|D|L, D|L|U, L|U|R},
			{U|R|D|L}};
	static List<Node> cctvList;
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
	static int cal() {
		int cnt = 0;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < m; j++) 
				if(board[i][j] == 0) cnt++;
		return cnt;
	}
	static void deepCopy(int[][] src, int[][] dest) {
		for(int i = 0; i < n ; i++)
			for(int j = 0; j < m; j++)
				dest[i][j] = src[i][j];
	}
	static void draw(int x, int y, int d) {
		while(true) {
			x += dx[d];
			y += dy[d];
			if( x < 0 || y < 0 || x > n-1 || y > m-1 || board[x][y]==6) return;
			if(board[x][y]==0) board[x][y] = 9;
		}
	}
	static int solve(int pos) {
		int res = Integer.MAX_VALUE;
		if(pos == cctvList.size()) {
			return cal();
		}
		int temp[][] = new int [n][m];
		deepCopy(board, temp);
		Node node = cctvList.get(pos);
		for(int dir: cctv[board[node.x][node.y]]) {
			for(int i = 0; i < 4; i++) {
				if((dir & (1 << i)) == (1 << i))
					draw(node.x, node.y, i);
			}
			res = Math.min(res, solve(pos+1));
			deepCopy(temp, board);
		}
		return res;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		board = new int[n][m];
		cctvList = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				board[i][j] = stoi(st.nextToken());
				if(0 < board[i][j] && board[i][j] < 6) cctvList.add(new Node(i, j));
			}
		}
		System.out.println(solve(0));
		
	}
}