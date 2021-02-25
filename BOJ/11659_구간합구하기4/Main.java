import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int arr[];
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		int pSum[] = new int[n + 1];
		for(int i = 0; i < n; i++) {
			arr[i] = stoi(st.nextToken());
			pSum[i+1] = pSum[i] + arr[i];
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int left = stoi(st.nextToken())-1;
			int right = stoi(st.nextToken());
			sb.append((pSum[right] - pSum[left]) + "\n");
		}
		System.out.println(sb);
	}
}