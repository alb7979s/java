import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static final int MAX = 101;
	static int dx[] = { 0, -1, 0, 1 }, dy[] = { 1, 0, -1, 0 };
	static int n;
	static boolean board[][];
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws Exception {
		List<Integer> curves = new ArrayList<>();
		curves.add(0);
		for (int i = 0; i < 11; i++) {
			int curveLen = curves.size();
			for(int j = curveLen - 1; j >= 0; j--) {
				curves.add((curves.get(j)+1)%4);
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = stoi(br.readLine());
		board = new boolean[MAX][MAX];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y, x, d, g;
			y = stoi(st.nextToken());
			x = stoi(st.nextToken());
			d = stoi(st.nextToken());
			g = stoi(st.nextToken());
			board[x][y] = true;
			for(int j = 0; j < (1 << g); j++) {
				int nd = (curves.get(j) + d)%4;
				x += dx[nd];
				y += dy[nd];
				board[x][y] = true;
			}
		}
		int cnt = 0;
		for(int i = 0; i < MAX-1; i++) {
			for(int j = 0; j < MAX-1; j++) {
				if(board[i][j] && board[i+1][j] && board[i][j+1] && board[i+1][j+1]) cnt++;
			}
		}
		System.out.println(cnt);
	}
}