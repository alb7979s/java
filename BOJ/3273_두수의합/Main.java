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
		int n = stoi(br.readLine());
		int arr[] = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) arr[i] = stoi(st.nextToken());
		int m = stoi(br.readLine());
		Arrays.sort(arr);
		int left = 0, right = n-1, cnt = 0;
		while(left < right) {
			if(arr[left] + arr[right] <= m) {
				if(arr[left] + arr[right] == m) cnt++;
				left++;
			}
			else {
				right--;
			}
		}
		System.out.println(cnt);
	}
}