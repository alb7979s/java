import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static int n, m, k, max;
	static int board[][];
	static int sx[], sy[];
	static int clockDelta[] = {1, 3, 0, 2}, counterClockDelta[] = {2, 0, 3, 1};
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static void swap(int x1, int y1, int x2, int y2) {
		int temp = board[x1][y1];
		board[x1][y1] = board[x2][y2];
		board[x2][y2] = temp;
	}
	static void reverseUpAndDown() {
		for(int i = 0 ; i < n/2; i++) {
			for(int j = 0; j < m; j++) {
				swap(n-1-i, j, i, j);
			}
		}
	}
	static void reverseLeftAndRight() {
		for(int i = 0; i < m/2; i++)
			for(int j = 0; j < n; j++)
				swap(j, m-1-i, j, i);
	}
	static void rotateRight(int cnt) {
		int[][] temp = new int[max][max];
		// 귀찮아 반시계는 세번 돌려
		for(int c = 0; c < cnt; c++) {
			for(int i = 0; i < n; i++) 
				for(int j = 0; j < m; j++) 
					temp[j][n-1-i] = board[i][j];
			int swap = n;
			n = m;
			m = swap;
			board = new int[n][m];
			for(int i = 0; i < n; i++)
				for(int j = 0; j < m; j++)
					board[i][j] = temp[i][j];
			
		}
	}
	static void splitMove(boolean clockWise) {
		sx = new int[] {0, 0, n/2, n/2};
		sy = new int[] {0, m/2, 0, m/2};
		int temp[][] = new int[n][m];
		int dx, dy;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				temp[i][j] = board[i][j];
			}
		}
		for(int d = 0; d < 4; d++) {
			if(clockWise) {
				dx = sx[clockDelta[d]];
				dy = sy[clockDelta[d]];
			}
			else {
				dx = sx[counterClockDelta[d]];
				dy = sy[counterClockDelta[d]];
			}
			for(int i = 0; i < n/2; i++) {
				for(int j = 0; j < m/2; j++) {
					board[i+sx[d]][j+sy[d]] = temp[i+dx][j+dy];
				}
			}
		}
	}
	static void solve(int command) {
		switch(command) {
			case 1: reverseUpAndDown(); break;
			case 2: reverseLeftAndRight(); break;
			case 3: rotateRight(1); break;
			case 4: rotateRight(3); break;
			case 5: splitMove(false); break;
			case 6: splitMove(true); break;
		}
	}
	static void print() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken()); 
		m = stoi(st.nextToken()); 
		k = stoi(st.nextToken());
		board = new int[n][m];
		max = Math.max(n, m);
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				board[i][j] = stoi(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++) {
			solve(stoi(st.nextToken()));
		}
		print();
	}
}