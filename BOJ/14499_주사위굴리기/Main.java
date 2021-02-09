import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static int change[][] =  {{},
							 {3, 1, 0, 5, 4, 2},
	 						 {2, 1, 5, 0, 4, 3},
	 						 {4, 0, 2, 3, 5, 1},
	 						 {1, 5, 2, 3, 0, 4}};
	static int dd[][] = {{},{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n, m, x, y, k;
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		x = stoi(st.nextToken());
		y = stoi(st.nextToken());
		k = stoi(st.nextToken());
		int[] dice = new int[6];
		int[][] arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = stoi(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < k; i++) {
			int d = stoi(st.nextToken());
			int nx = x + dd[d][0];
			int ny = y + dd[d][1];
			if( nx < 0 || ny < 0 || nx > n-1 || ny > m-1) continue;
			List<Integer> list = new ArrayList<>();
			for(int j = 0; j < 6; j++) {
				list.add(dice[change[d][j]]);
			}
			for(int j = 0; j < 6; j++) {
				dice[j] = list.get(j);
			}
			if(arr[nx][ny] == 0) {
				arr[nx][ny] = dice[5];
			}
			else {
				dice[5] = arr[nx][ny];
				arr[nx][ny] = 0;
			}
			x = nx;
			y = ny;
			System.out.println(dice[0]);
		}
		
	}
}