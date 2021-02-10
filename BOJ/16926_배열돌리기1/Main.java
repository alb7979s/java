import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static int dx[] = {1, 0, -1, 0}, dy[] = {0, 1, 0, -1};
	static void print() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	static int n, m, k;
	static boolean visit[][];
	static int board[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		k = stoi(st.nextToken());
		board = new int[n][m];
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < m; j++) {
				board[i][j] = stoi(st.nextToken());
			}
		}
		for(int i = 0; i < k; i++) {
			visit = new boolean[n][m];
			int half = n%2==0 ? n/2 : n/2+1;
			for(int j = 0; j < half; j++) {
				int x, y;
				x = j; y = j;
				if(y >= m) break;
				int temp = board[x][y];
				for(int d = 0; d < 4; d++) {
					while (true) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						if( nx < 0 || ny < 0 || nx > n-1 || ny > m-1 || visit[nx][ny]) break;
						int swap;
						swap = board[nx][ny];
						board[nx][ny] = temp;
						temp = swap;
						visit[nx][ny] = true;
						x = nx;
						y = ny;
					}
				}
			}
		}
		print();
		
	}
}