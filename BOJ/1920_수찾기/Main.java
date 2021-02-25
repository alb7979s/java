import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, arr[];
	static public int binarySearch(int target) {
		int left = 0, right = n - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			// 찾으려는게 더 작음 => 중간보다 왼쪽에 있음
			if(arr[mid] == target) return 1;
			if( arr[mid] > target) {
				right = mid - 1;
			}
			else if (arr[mid] < target) {
				left = mid + 1;
			}
		}
		return 0;
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = stoi(br.readLine());
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = stoi(st.nextToken());
		}
		Arrays.sort(arr);
		int m = stoi(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			sb.append(binarySearch(stoi(st.nextToken())) + "\n");
		}
		System.out.println(sb);
	}
}