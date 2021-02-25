import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n, m;
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n ; i++) arr[i] = stoi(st.nextToken());
		int left = 0, right = 0;
		int sum = 0, cnt = 0;
		while (true) {
			if(sum == m) cnt++;
			if(sum >= m) sum -= arr[left++];
			else if(right == n) break;
			else sum += arr[right++];
		}
		System.out.println(cnt);
	}
}