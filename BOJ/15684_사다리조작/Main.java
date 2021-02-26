import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, k, m, minCnt=Integer.MAX_VALUE;
	static boolean[][] board;
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static boolean check() {
		for(int i = 0; i < m; i++) {
			int currY = i;
			for(int j = 0; j < n; j++) {
				if(board[j][currY]) {
					currY++;
				}
				else if(currY - 1 >= 0 && board[j][currY-1]) {
					currY--;
				}
			}
			if(currY != i) return false;
		}
		return true;
	}
	static void solve(int posX, int posY, int cnt) {
		//뭔가 조건들 되게 지저분하네 깔끔하겐 안되나?
		if(minCnt <= cnt) return;
		if(check()) {
			minCnt = cnt;
			return;
		}
		if(posX == n || cnt == 3) return;
		//선택
		if (!board[posX][posY]) {
			board[posX][posY] = true;
			int nx = posX;
			int ny = posY + 2;
			if(ny >= m-1) {
				nx++;
				ny = 0;
			}
			solve(nx, ny, cnt+1);
			board[posX][posY] = false;
		}
		// 선택 안하고 넘김
		int nx = posX;
		int ny = posY + 1;
		if(ny >= m-1) {
			nx++;
			ny = 0;
		}
		solve(nx, ny, cnt);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = stoi(st.nextToken());
		k = stoi(st.nextToken());
		n = stoi(st.nextToken());
		board = new boolean[n][m];
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken())-1;
			int y = stoi(st.nextToken())-1;
			board[x][y] = true;
		}
		solve(0, 0, 0);
		System.out.println(minCnt == Integer.MAX_VALUE ? -1 : minCnt);
	}
}