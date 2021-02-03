import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int dx[] = { 0, 1, 0, -1 }, dy[] = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = stoi(br.readLine());
		int k = stoi(br.readLine());
		int[][] board = new int[n][n];
		Queue<Node> tail = new LinkedList<>();
		char[] turn = new char[10001]; 
		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = stoi(st.nextToken()) - 1;
			int y = stoi(st.nextToken()) - 1;
			board[x][y] = 1;
		}
		k = stoi(br.readLine());
		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			turn[stoi(st.nextToken())] = st.nextToken().charAt(0); 
		}
		int x = 0, y = 0, d = 0;
		tail.add(new Node(x, y));
		int time = 0;
		while (true) {
			x += dx[d];
			y += dy[d];
			time++;
			if (x < 0 || y < 0 || x > n - 1 || y > n - 1 || board[x][y] == 2)
				break;
			if (board[x][y] == 0) {
				Node node = tail.poll();
				board[node.x][node.y] = 0;
			}
			board[x][y] = 2;
			tail.add(new Node(x, y));
			if(time <= 10000) {
				if (turn[time] == 'L') d = (d+3)%4;
				if (turn[time] == 'D') d = (d+1)%4;
			}
		}
		System.out.println(time);
	}
}