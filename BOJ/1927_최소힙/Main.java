import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		int n = stoi(br.readLine());
		for(int i = 0; i < n; i++) {
			int x = stoi(br.readLine());
			if (x == 0) sb.append((pq.peek() == null ? 0 : pq.poll()) + "\n");
			else pq.add(x);
		}
		System.out.println(sb);
	}
}