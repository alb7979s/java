import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
	static int[] arr;
	static StackInteger stack = new Stack();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[9];
		for(int i=0; i9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		solve(0, 0, 0);
	}
	static void solve(int pos, int cnt, int sum) {
		if(pos == 9) {
			if(cnt == 7 && sum==100) {
				for(int x  stack) {
					System.out.println(x);
				}
				System.exit(0);
			}
			return;
		}
		stack.push(arr[pos]);
		solve(pos+1, cnt+1, sum+arr[pos]);
		stack.pop();
		solve(pos+1, cnt, sum);
		return;
	}
}
