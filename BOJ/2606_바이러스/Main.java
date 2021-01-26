import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int n, m, cnt;
	static ArrayList<Integer>[] adj;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		visit = new boolean[n];
		adj = new ArrayList[n];
		for(int i=0; i<n; i++) adj[i] = new ArrayList<Integer>();
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int u, v;
			u = Integer.parseInt(st.nextToken()) - 1;
			v = Integer.parseInt(st.nextToken()) - 1;
			adj[u].add(v);
			adj[v].add(u);
		}
		dfs(0);
		System.out.println(cnt-1);
	}

	static void dfs(int u) {
		visit[u] = true;
		cnt++;
		// 이거 왜 안돼요??ㅜㅜㅜ
		/*
		if(int v : adj[u]) {
			if(!visit[v]) {
				dfs(v);
			}
		}
		*/
		for(int i=0; i<adj[u].size(); i++) {
			int v = adj[u].get(i);
			if(!visit[v]) dfs(v);
		}
	}
}
