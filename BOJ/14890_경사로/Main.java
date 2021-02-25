import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, L, board[][];
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static int check(int x, int y, int dx, int dy) {
		int currLength = 0;
		for(int i = 0; i < n - 1; i++) {
			currLength++;
			x += dx;
			y += dy;
			int diff = board[x][y] - board[x-dx][y-dy];
			if(Math.abs(diff) >= 2) return 0;
			// 뒤쪽이 1큼
			if(diff == 1) {
				if(currLength < L) return 0;
				currLength = 0;
			}
			// 뒤쪽이 1작음
			else if(diff == -1) {
				if(currLength < 0) return 0;
				currLength = -L;
			}
		}
		// 길이를 처음에 하나 늘려주고 시작하는데 그러면 마지막 길이를 안세어주길래 1 더해줌
		if (currLength + 1 < 0) return 0;
		return 1;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		L = stoi(st.nextToken());
		board = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				board[i][j] = stoi(st.nextToken());
			}
		}
		int res = 0;
		for(int i = 0; i < n; i++) {
			res += check(i, 0, 0, 1);
			res += check(0, i, 1, 0);
		}
		System.out.println(res);
	}
}