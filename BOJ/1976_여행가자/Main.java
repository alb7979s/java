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
	static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if(u==v) return;
		parent[u] = v;
		return;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = stoi(br.readLine());
		int m = stoi(br.readLine());
		parent = new int[n];
		for(int i = 0; i < n; i++) parent[i] = i;
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				if(stoi(st.nextToken())==1) union(i, j);
			}
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int first = find(stoi(st.nextToken())-1);
		int stlen = st.countTokens();
		boolean res = true;
		for(int i = 0; i < stlen; i++) {
			if (first != find(stoi(st.nextToken())-1)) {
				res = false;
				break;
			}
		}
		if(res) System.out.println("YES");
		else System.out.println("NO");
	}
}
