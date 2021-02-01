import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static int MAX = (int)1e5+1;
	static class Node{
		int cur, cnt;
		Node(int cur, int cnt){
			this.cur = cur;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = stoi(st.nextToken());
		int k = stoi(st.nextToken());
		Queue<Node> q = new LinkedList<>();
		boolean[] visit = new boolean[MAX];
		q.add(new Node(n, 0));
		visit[n] = true;
		while(!q.isEmpty()) {
			Node node = q.poll();
			if ( node.cur == k ) {
				System.out.println(node.cnt);
				System.exit(0);
			}
			for(int next: new int[] {node.cur-1, node.cur+1, node.cur*2} ) {
				if (next >= 0 && next < MAX && !visit[next]) {
					q.add(new Node(next, node.cnt+1));
					visit[next] = true;
				}
			}
		}
	}
}