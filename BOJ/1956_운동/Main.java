import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int INF = (int)1e9;
	static int n, m, dist[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		dist = new int[n][n];
		for(int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(i != j) dist[i][j] = INF;
			}
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u, v, d;
			u = stoi(st.nextToken())-1;
			v = stoi(st.nextToken())-1;
			d = stoi(st.nextToken());
			dist[u][v] = d;
		}
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		int res = INF;
		for(int i = 0; i < n; i++) {
			for(int j = i+1; j < n; j++) {
				res = Math.min(res, dist[i][j] + dist[j][i]);
			}
		}
		System.out.println(res < INF ? res : -1);
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
