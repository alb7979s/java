
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static int n;
	static String[] arr;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static boolean[][] visit;
	static int cnt = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> ans = new ArrayList<Integer>();
		n = Integer.parseInt(br.readLine());

		arr = new String[n];
		visit = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			arr[i] = br.readLine();
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// arr[i][j] 이렇게 안되고 스트링 charAt()으로 찾아야함
				if (arr[i].charAt(j) == '1' && !visit[i][j]) {
					cnt = 0;
					dfs(i, j);
					ans.add(cnt);
				}
			}
		}
		// 아니 이건 또 왜 Collections.sort()야 ㅠㅜㅜㅠ Arrays.sort()는 왜 안되냐고 진짜 자바
		Collections.sort(ans);
		System.out.println(ans.size());
		for (int a : ans)
			System.out.println(a);
	}

	static void dfs(int x, int y) {
		visit[x][y] = true;
		cnt++;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1 || visit[nx][ny] || arr[nx].charAt(ny) == '0')
				continue;
			dfs(nx, ny);
		}
	}
}
