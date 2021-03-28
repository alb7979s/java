import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static final int dx[] = { 0, 0, -1, 1 }, dy[] = { 1, -1, 0, 0 };	//RLUD
	static final int changeD[] = { 1, 0, 3, 2 };
	static int n, k, board[][];
	static Node horse[];
	static ArrayDeque<Integer> q;
	static Stack<Integer> stack[][];
	static class Node {
		int x, y, d;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		Node(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
		// 디버깅용
		public String toString() {
			return x + " " + y + " " + d; 
		}
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		k = stoi(st.nextToken());
		horse = new Node[k];
		board = new int [n][n];
		stack = new Stack[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = stoi(st.nextToken());
				stack[i][j] = new Stack<>();
			}
		}
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x, y, d;
			x = stoi(st.nextToken())-1;
			y = stoi(st.nextToken())-1;
			d = stoi(st.nextToken())-1;
			horse[i] = new Node(x, y, d);
			stack[x][y].add(i);
		}
		System.out.println(solve());
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static void move(int x, int y, int dir) {
		// 1정방향, -1역방향 
		while(!q.isEmpty()) {
			int num = -1;
			if(dir == 1) {
				num = q.pollLast();
			}
			else {
				num = q.pollFirst();
			}
			Node h = horse[num];
			stack[x][y].add(num);
			h.x = x;
			h.y = y;
		}
	}
	static boolean correctScope(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx > n-1 || ny > n-1 || board[nx][ny]==2) return false;
		return true;
	}
	static int solve() {
		for(int time = 1; time <= 1000; time++) {
			// 말 이동
			for(int i = 0; i < k; i++) {
				Node h = horse[i];
				q = new ArrayDeque<>();
				// 뒤에서부터 빼는데, 옮기려는 말 찾으면 break;
				while(!stack[h.x][h.y].isEmpty()) {
					int num = stack[h.x][h.y].pop();
					q.add(num);
					if(num == i) break;
				}
				int nx = h.x + dx[h.d];
				int ny = h.y + dy[h.d];
				// 파랑 처리
				if(!correctScope(nx, ny)){
					h.d = changeD[h.d];
					nx = h.x + dx[h.d];
					ny = h.y + dy[h.d];
					if(!correctScope(nx, ny)) {
						move(h.x, h.y, 1);
						continue;
					}
				}
				if(board[nx][ny] == 1) {							// 빨강
					move(nx, ny, -1);
				}else {												// 하양, 파랑
					move(nx, ny, 1);
				}
				if(stack[nx][ny].size() >= 4) return time;
			}
		}
		return -1;
	}
}
