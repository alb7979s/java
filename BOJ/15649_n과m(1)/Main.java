import java.io.BufferedReader;
import java.util.StringTokenizer;

public class Main {
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static String[] arr;
	static int[] visit;
	static turn(int x, int d) {
		visit[x] = true;
		if( x - 1 >= 0 && !visit[x-1] && arr[x-1].charAt(2) != arr[x].charAt(6)) {
			solve(x-1, -d);
		}
		if( x + 1 < 4 && !visit[x+1] && arr[x].charAt(2) != arr[x+1].charAt(6)) {
			solve(x+1, -d);
		}
	}
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new String[4];
		for(int i = 0 ; i < 4; i++) {
			arr[i] = br.readLine();
		}
		int k = stoi(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++) {
			int x, d;
			x = stoi(st.nextToken());
			d = stoi(st.nextToken());
			visit = new int[4];
			turn(x, d);
		}
	}
}