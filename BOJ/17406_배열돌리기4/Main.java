import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n, m, k, board[][];
	static int dx[] = { 0, 1, 0, -1 }, dy[] = { 1, 0, -1, 0 };
	static boolean[] visit;
	static List<Node> list = new ArrayList<>();
	static final int INF = (int)1e9;
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	// 회전 메서드
	static void turn(int x, int y, int s) {
		for(int i = 1; i <= s; i++) {
			int nx = x-i;
			int ny = y-i;
			int temp = board[nx][ny];
			for(int d = 0; d < 4; d++) {
				for(int j = 0; j < 2*i; j++) {
					nx += dx[d];
					ny += dy[d];
					int swap = board[nx][ny];
					board[nx][ny] = temp;
					temp = swap;
				}
			}
		}
	}
	// 행 최솟값 찾는 메서드
	static int cal() {
		int res = INF;
		for(int i = 0; i < n; i++) {
			int temp = 0;
			for(int j = 0; j < m; j++) {
				temp += board[i][j];
			}
			res = Math.min(res, temp);
		}
		return res;
	}
	// 노드 클래스
	static class Node {
		int x, y, k;

		Node(int x, int y, int k) {
			this.x = x;
			this.y = y;
			this.k = k;
		}
	}
	// 배열 복사
	static void deepCopy(int[][] src, int[][] dest) {
		for(int i = 0 ; i < n; i++)
			for(int j = 0 ; j < m; j++)
				dest[i][j] = src[i][j];
	}
	// 순열 (순서 있는 집합) 구하면서 마지막 depth에 닿으면 계산해줬어요
	static int solve(int depth) {
		int res = INF;
		if(depth == k) {
			return cal();
		}
		int[][] temp = new int[n][m];
		deepCopy(board, temp);
		for(int i = 0; i < k; i++) {
			if(! visit[i]) {
				Node node = list.get(i);
				turn(node.x, node.y, node.k);
				visit[i] = true;
				res = Math.min(res, solve(depth + 1));
				deepCopy(temp, board);
				visit[i] = false;
			}
		}
		return res;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		k = stoi(st.nextToken());
		board = new int[n][m];
		visit = new boolean[k];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				board[i][j] = stoi(st.nextToken());
			}
		}
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			// 연산의 정보 모두 list에 담아서
			list.add(new Node(stoi(st.nextToken())-1, stoi(st.nextToken())-1, stoi(st.nextToken())));
		}
		// 순열
		System.out.println(solve(0));
	}
}