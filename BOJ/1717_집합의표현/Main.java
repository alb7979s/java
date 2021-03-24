import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int parent[];
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static int find(int u) {
		if(u == parent[u]) return u;
		return parent[u] = find(parent[u]);
	}
	static boolean union(int u, int v, int flag) {
		u = find(u);
		v = find(v);
		if(u==v) return true;
		if (flag==0) parent[u] = v;
		return false;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = stoi(st.nextToken())+1;
		int m = stoi(st.nextToken());
		parent = new int[n];
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) parent[i] = i;
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int flag, u, v;
			flag = stoi(st.nextToken());
			u = stoi(st.nextToken());
			v = stoi(st.nextToken());
			if(flag==1){
				if(union(u, v, flag)) sb.append("YES\n");
				else sb.append("NO\n");
			}else {
				union(u, v, flag);
			}
		}
		System.out.println(sb);
	}
}
