import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m, k, cleanerX, board[][], add[][];
	static int clockDx[] = { 0, 1, 0, -1 }, clockDy[] = { 1, 0, -1, 0 };
	static int counterClockDx[] = { 0, -1, 0, 1 }, counterClockDy[] = { 1, 0, -1, 0 };
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.printf(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	static void spread() { 
		add = new int[n][m];
		for(int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] > 0) {
					int quantity = board[i][j] / 5;
					for(int d = 0; d < 4; d++) {
						int nx = i + clockDx[d];
						int ny = j + clockDy[d];
						if( nx < 0 || ny < 0 || nx > n-1 || ny > m-1 || board[nx][ny]==-1) continue;
						add[nx][ny] += quantity;
						board[i][j] -= quantity;
					}
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				board[i][j] += add[i][j];
			}
		}
	}
	static void play(int x, int dx[], int dy[]) {
		int nx = x;
		int ny = 0;
		int temp = 0;
		for(int i = 0; i < 4; i++) {
			while(true) {
				nx += dx[i];
				ny += dy[i];
				if(nx < 0 || ny < 0 || nx > n-1 || ny > m-1 || board[nx][ny]==-1) {
					nx -= dx[i];
					ny -= dy[i];
					break;
				}
				int swap = board[nx][ny];
				board[nx][ny] = temp;
				temp = swap;
			}
		}
	}
	static void cleanerPlay() {
		play(cleanerX, clockDx, clockDy);
		play(cleanerX-1, counterClockDx, counterClockDy);
	}
	static void flow() {
		spread();
		cleanerPlay();
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		k = stoi(st.nextToken());
		board = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				board[i][j] = stoi(st.nextToken());
				if(board[i][j] == -1) {
					cleanerX = i;
				}
			}
		}
		for (int i = 0; i < k; i++) {
			flow();
		}
		int res = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(board[i][j] != -1) res += board[i][j];
			}
		}
		System.out.println(res);
	}
}
