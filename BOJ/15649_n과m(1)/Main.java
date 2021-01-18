// n과m(1)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static boolean[] visit;
	static Stack<Integer> stack = new Stack<>();
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		visit = new boolean[n];
		for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
		solve(0);
	}
	
	public static void solve(int cnt) {
		if(cnt == m ) {
			for(int x : stack) {
				System.out.print(x + " ");
			}
			System.out.println();
			return ;
		}
		for(int i=0; i<n; i++) {
			if (!visit[i]) {
				visit[i] = true;
				stack.push(arr[i]);
				solve(cnt+1);
				stack.pop();
				visit[i] = false;
			}
		}
		return;
	}
}