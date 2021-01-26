import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static boolean[] visit;
	static int cnt;
	static ArrayList<Integer>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		adj = new ArrayList[n];
		visit = new boolean[n];
		for(int i=0; i<n; i++) adj[i] = new ArrayList<Integer>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u, v;
			u = Integer.parseInt(st.nextToken()) - 1;
			v = Integer.parseInt(st.nextToken()) - 1;
			adj[u].add(v);
			adj[v].add(u);
		}
		for(int i=0; i<n; i++) {
			if(!visit[i]) {
				cnt++;
				dfs(i);
			}
		}
		System.out.println(cnt);
	}
	static void dfs(int u) {
		visit[u] = true;
		for(int i=0; i<adj[u].size(); i++) {
			int v = adj[u].get(i);
			if(!visit[v]) dfs(v);
		}
	}
}
