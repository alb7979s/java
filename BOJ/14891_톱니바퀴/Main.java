import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static char[][] arr;
	static int[] visit;
	static void check(int x, int d) {
		visit[x] = d;
		if( x - 1 >= 0 && visit[x-1]==0 && arr[x-1][2] != arr[x][6]) {
			check(x-1, -d);
		}
		if( x + 1 < 4 && visit[x+1]==0 && arr[x][2] != arr[x+1][6]) {
			check(x+1, -d);
		}
	}
	static void rotate() {
		for(int i = 0 ; i < 4; i++) {
			if (visit[i] == 0) continue;
			Stack<Character> stack = new Stack<>();
			for(int j = 0; j < 8; j++) {
				if(visit[i] == 1) {
					stack.push(arr[i][((j+7)%8)]);
				}
				if(visit[i] == -1) {
					stack.push(arr[i][((j+1)%8)]);
				}
			}
			for(int j = 0 ; j < 8; j++) {
				arr[i][j] = stack.get(j);
			}
		}
	}
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new char[4][8];
		for(int i = 0 ; i < 4; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		int k = stoi(br.readLine());
		StringTokenizer st;
		for(int i = 0; i < k; i++) {
			int x, d;
			st = new StringTokenizer(br.readLine());
			x = stoi(st.nextToken()) - 1 ;
			d = stoi(st.nextToken());
			visit = new int[4];
			check(x, d);
			rotate();
		}
		int res = 0;
		for(int i = 0; i < 4; i++) {
			if (arr[i][0] == '1') res += (1 << i); 
		}
		System.out.println(res);
	}
}