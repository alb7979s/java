import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, mem[][], house[][];
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static int solve(int pos, int prev) {
		if(pos == n) {
			return 0;
		}
		if(mem[pos][prev] != Integer.MAX_VALUE) return mem[pos][prev];
		if(prev == 0) {
			mem[pos][prev] = Math.min(solve(pos+1, 1)+house[pos][1], solve(pos+1, 2)+house[pos][2]);
		}
		if(prev == 1) {
			mem[pos][prev] = Math.min(solve(pos+1, 0)+house[pos][0], solve(pos+1, 2)+house[pos][2]);
		}
		if(prev == 2) {
			mem[pos][prev] = Math.min(solve(pos+1, 1)+house[pos][1], solve(pos+1, 0)+house[pos][0]);
		}
		return mem[pos][prev];
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = stoi(br.readLine());
		house = new int[n][3];
		mem = new int[n][3];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				house[i][j] = stoi(st.nextToken());
				mem[i][j] = Integer.MAX_VALUE;
			}
		}
		int res = Integer.MAX_VALUE;
		for(int i = 0; i < 3; i++) {
			res = Math.min(res, solve(0, i));
		}
		System.out.println(res);
	}
}
