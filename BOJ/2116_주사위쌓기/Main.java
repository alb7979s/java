import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, dices[][];
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static final int facingSide[] = new int[]{5, 3, 4, 1, 2, 0}; 
	public static int solve(int depth, int downSide, int maxSum) {
		int res = 0;
		if(depth == n) {
			return maxSum;
		}
		int maxSide = 0, upSide = 0;
		for(int i = 0; i < 6; i++) {
			if(dices[depth][i] == downSide) upSide = dices[depth][facingSide[i]];
		}
		for (int i = 0; i < 6; i++) {
			if(dices[depth][i] == downSide || dices[depth][i] == upSide) continue;
			maxSide = Math.max(maxSide, dices[depth][i]); 
		}
		res = Math.max(res, solve(depth+1, upSide, maxSum + maxSide));
		return res;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = stoi(br.readLine());
		dices = new int[n][6];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {
				dices[i][j] = stoi(st.nextToken());
			}
		}
		int res = 0;
		for(int i = 0; i < 6; i++) {
			res = Math.max(res, solve(0, dices[0][i], 0));
//			System.out.println(solve(0, dices[0][i], 0));
		}
		System.out.println(res);
	}
}