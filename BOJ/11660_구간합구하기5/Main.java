import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = stoi(st.nextToken());
		int m = stoi(st.nextToken());
		int[][] arr = new int[n][n];
		int[][] pSum = new int[n+1][n+1];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = stoi(st.nextToken());
				pSum[i+1][j+1] = pSum[i][j+1] + pSum[i+1][j] - pSum[i][j] + arr[i][j];
			}
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x1, x2, y1, y2;
			x1 = stoi(st.nextToken());
			y1 = stoi(st.nextToken());
			x2 = stoi(st.nextToken());
			y2 = stoi(st.nextToken());
			// 그려보면 알 수 있음!
			sb.append(pSum[x2][y2] - pSum[x1-1][y2] - pSum[x2][y1-1] + pSum[x1-1][y1-1] + "\n");
		}
		System.out.println(sb);
	}
}