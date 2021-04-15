import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static final int INF = (int)1e9;
	static int n, total, popul[];
	static boolean visit[], team[];
	static List<Integer> adj[];
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = stoi(br.readLine());
		popul = new int[n];
		adj = new List[n];
		team = new boolean[n];
		for(int i = 0; i < n; i++) adj[i] = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			popul[i] = stoi(st.nextToken());
			total += popul[i]; 
		}
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int m = stoi(st.nextToken());
			for(int j = 0; j < m; j++) {
				adj[i].add(stoi(st.nextToken())-1);
			}
		}
		int res = solve(0, 0);
		System.out.println(res == INF ? -1 : res);
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static int abs(int a) {
		return a >= 0 ? a : -a;
	}
	static int min(int a, int b) {
		return a < b ? a : b;
	}
	static int bfs(int u, boolean t) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(u);
		visit[u] = true;
		int cnt = 1;
		while(!q.isEmpty()) {
			u = q.poll();
			for(int v: adj[u]) {
				if(!visit[v] && (team[v] == t)) {
					visit[v] = true;
					cnt++;
					q.add(v);
				}
			}
		}
		return cnt;
		
	}
	static boolean check() {
		visit = new boolean[n];
		int redStart = -1, blueStart = -1;
		for(int i = 0 ; i < n; i++) {
			if(redStart != -1 && blueStart != -1) break;
			if(team[i]) redStart = i;
			else if(!team[i]) blueStart = i;
		}
		if(redStart == -1 || blueStart == -1) return false;
		int cnt = 0;
		cnt += bfs(redStart, true);
		cnt += bfs(blueStart, false);
		return cnt == n ? true : false; 
	}
	static int solve(int pos, int sum) {
		int res = INF;
		if(pos == n) {
			if(check()) res = abs(sum - (total - sum));
			return res;
		}
		team[pos] = true;
		res = min(res, solve(pos+1, sum+popul[pos]));
		team[pos] = false;
		res = min(res, solve(pos+1, sum));
		return res;
	}
}

