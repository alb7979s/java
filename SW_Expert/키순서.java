import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
	static int n, m, cnt[];
	static boolean visit[];
	static List<Integer> in[], out[];
	static Queue<Integer> q;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = stoi(br.readLine());
		for(int tc = 1; tc <= testCase; tc++) {
			n = stoi(br.readLine());
			m = stoi(br.readLine());
			in = new List[n];
			out = new List[n];
			cnt = new int[n];
			for(int i = 0; i < n; i++) {
				in[i] = new ArrayList<>();
				out[i] = new ArrayList<>();
			}
			for(int i = 0; i < m; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken())-1;
				int b = stoi(st.nextToken())-1;
				in[a].add(b);
				out[b].add(a);
			}
			for(int i = 0; i < n; i++) {
				bfs(i, in);
				bfs(i, out);
			}
			int res = 0;
			for(int i = 0; i < n; i++) {
				if(cnt[i] == n-1) res++;
			}
			System.out.printf("#%d %d%n", tc, res);
		}
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static void init() {
		visit = new boolean[n];
		q = new ArrayDeque<>();
	}
	static void bfs(int i, List<Integer> adj[]) {
		init();
		visit[i] = true;
		q.add(i);
		while(!q.isEmpty()) {
			int u = q.poll();
			for(int v: adj[u]) {
				if(!visit[v]) {
					visit[v] = true;
					cnt[v]++;
					q.add(v);
				}
			}
		}
	}
}


