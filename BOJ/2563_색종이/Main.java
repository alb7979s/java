import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static final int MAX = 101;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = stoi(br.readLine());
		boolean[][] board =  new boolean[MAX][MAX];
		for(int i = 0 ;i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken());
			int y = stoi(st.nextToken());
			for(int j = 0; j < 10; j++) {
				for(int k = 0; k < 10; k++) {
					board[j+x][k+y] = true;
				}
			}
		}
		int res = 0;
		for(int i = 0 ; i < MAX; i++) {
			for(int j = 0; j < MAX; j++) {
				if(board[i][j]) res++;
			}
		}
		System.out.println(res);
	}
}