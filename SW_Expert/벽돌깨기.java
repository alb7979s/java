import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Solution {
	static final int INF = (int)1e9;
	static int n, m, k, block, board[][];
	boolean visit[][];
	static int dx[] = { 0, 1, 0, -1 }, dy[] = { 1, 0, -1, 0 };
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = stoi(br.readLine());
		for(int tc = 1; tc <= testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			k = stoi(st.nextToken());
			m = stoi(st.nextToken());
			n = stoi(st.nextToken());
			board = new int[n][m];
			block = 0;
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < m; j++) {
					board[i][j] = stoi(st.nextToken());
					if(board[i][j] != 0) block++;
				}
			}
			System.out.println("#" + tc + " " + solve(0, 0));
		}
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static void deepCopy(int dest[][], int src[][]) {
		for(int i = 0; i < n; i++)
			for(int j = 0; j < m; j++)
				dest[i][j] = src[i][j];
	}
	static int min(int a, int b) {
		return a < b ? a : b;
	}
	static int remove(int x, int y) {
		int sum = 1;
		int len = board[x][y];
		board[x][y] = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 1; j < len; j++) {
				int nx = x + dx[i] * j;
				int ny = y + dy[i] * j;
				if(nx < 0 || ny < 0 || nx > n-1 || ny > m-1 || board[nx][ny]==0) continue;
				sum += remove(nx, ny);
			}
		}
		return sum;
	}
	static void print() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				System.out.printf(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	static void gravity() {
		for(int i = 0; i < m; i++) {
			Stack<Integer> st = new Stack<>();
			for(int j = 0; j < n; j++) {
				if(board[j][i] != 0) st.add(board[j][i]);
				board[j][i] = 0;
			}
			for(int j = n-1; j >= 0; j--) {
				if(st.isEmpty()) break;
				board[j][i] = st.pop();
			}
		}
	}
	static int solve(int pos, int removeCnt) {
		gravity();
		int res = INF;
		if(pos == k) {
			return block - removeCnt;
		}
		int[][] temp = new int[n][m];
		deepCopy(temp, board);
		for(int i = 0; i < m; i++) {
			int x = -1;
			for(int j = 0; j < n; j++) {
				if(board[j][i] != 0) {
					x = j;
					break;
				}
			}
			if(x == -1) continue;
			res = min(res, solve(pos + 1, removeCnt + remove(x, i)));
			deepCopy(board, temp);
		}
		res = min(res, solve(pos+1, removeCnt));
		return res;
	}
}
