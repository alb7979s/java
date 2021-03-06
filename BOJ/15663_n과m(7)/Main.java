// n과m(7)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static boolean[] visit;
	static Stack<Integer> stack = new Stack<>();
	static int[] arr;
	static StringBuilder sb; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		sb = new StringBuilder();
		arr = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		solve(0);
		System.out.println(sb);
	}
	
	public static void solve(int cnt) throws IOException {
		if(cnt == m ) {
			for(int x : stack) {
				sb.append(x);
				sb.append(" ");
			}
			sb.append("\n");
			return ;
		}
		for(int i=0; i<n; i++) {
			stack.push(arr[i]);
			solve(cnt+1);
			stack.pop();
			}
		return;
	}
}