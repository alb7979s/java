import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws Exception {
		HashMap<String, Boolean> map = new HashMap<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = stoi(st.nextToken());
		int m = stoi(st.nextToken());
		for (int i = 0; i < n; i++) {
			map.put(br.readLine(), true);
		}
		int res = 0;
		for(int i = 0; i < m; i++) {
			if(map.get(br.readLine())!=null) res++;
		}
		System.out.println(res);
	}
}
