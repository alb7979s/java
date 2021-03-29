import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int INF = (int)1e9;
	static int n, m, dist[][];
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = stoi(br.readLine());
		m = stoi(br.readLine());
		dist = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i == j) dist[i][j] = 0;
				else dist[i][j] = INF; 
			}
		}
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u, v, d;
			u = stoi(st.nextToken())-1;
			v = stoi(st.nextToken())-1;
			d = stoi(st.nextToken());
			dist[u][v] = Math.min(dist[u][v], d);
		}
		for(int k = 0; k < n; k++) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.printf("%d ", dist[i][j] != INF ? dist[i][j] : 0);
			}
			System.out.println();
		}
	}
}
