import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int stoi(String s) {return Integer.parseInt(s);}
	static int n, m, k;
	static int[][] arr, visit;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	static void dfs(int x, int y) {
		visit[x][y] = 1;
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<0 || ny<0 || nx>n-1 || ny>m-1 || visit[nx][ny]!=0 || arr[nx][ny]!=1) continue;
			dfs(nx, ny);
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = stoi(br.readLine());
		for(int tc=0; tc < TC; tc++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			n = stoi(st.nextToken());
			m = stoi(st.nextToken());
			k = stoi(st.nextToken());
			arr = new int[n][m];
			visit = new int[n][m];
			for(int i=0; i<k; i++) {
				// 아니 자바도 나눠서 받는 방법이 있을거 아니에요ㅜㅜ
				int x, y;
				st = new StringTokenizer(br.readLine(), " ");
				x = stoi(st.nextToken());
				y = stoi(st.nextToken());
				arr[x][y] = 1;
			}
			int res = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(arr[i][j]==1 && visit[i][j]==0) {
						res++;
						dfs(i, j);
					}
				}
			}
			System.out.println(res);
		}
	}
}