import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int dx[] = { 0, 1, 0, -1 }, dy[] = { 1, 0, -1, 0 };
	static int n, total, board[][], check[][], score[];
	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = stoi(br.readLine());
		board = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = stoi(st.nextToken());
				total += board[i][j];
			}
		}
		int res = Integer.MAX_VALUE;
		for(int x = 0; x < n-1; x++) {
			for(int y = 1; y < n-1; y++) {
				for(int d1=1; y-d1 >= 0; d1++) {
					for(int d2=1; y+d2 < n; d2++) {
						if(x + d1 + d2 >= n) break;
						indexing(x, y, d1, d2);
						res = Math.min(res, cal(x, y, d1, d2));
					}
				}
			}
		}
		System.out.println(res);
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static void print() {
		for(int i = 0; i < n; i ++) {
			for (int j = 0; j < n; j++) {
				System.out.printf("%d ", check[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	static boolean correctScope(int nx, int ny) {
		if(nx < 0 || ny < 0 || nx > n-1 || ny > n-1 || check[nx][ny]==5 ) return false;
		return true;
	}
	static boolean scopeCheck(int nx, int ny, int x, int y, int d1, int d2, int flag) {
		if(flag == 0) {
			if(0 <= nx && nx < x+d1 && 0 <= ny && ny <= y) return true;
		}
		else if(flag == 1) {
			if(0 <= nx && nx <= x+d2 && y < ny && ny < n) return true;
		}
		else if(flag == 2) {
			if(x+d1 <= nx && nx < n && 0 <= ny && ny < y-d1+d2) return true;
		}
		else {
			if(x+d2 < nx && nx < n && y-d1+d2 <= ny && ny < n) return true;
		}
		return false;
	}
	static int rangeCal(int sx, int sy, int x, int y, int d1, int d2, int flag) {
		Queue<Node> q = new ArrayDeque<>();
		// 1
		q.add(new Node(sx, sy));
		int sum = board[sx][sy];
		check[sx][sy] = 5;
		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if(!correctScope(nx, ny)) continue;
				if(scopeCheck(nx, ny, x, y, d1, d2, flag)) {
					q.add(new Node(nx, ny));
					sum += board[nx][ny];
					check[nx][ny] = 5;
				}
			}
		}
		return sum;
	}
	static void indexing(int x, int y, int d1, int d2) {
		check = new int[n][n];
		int dx[] = { 1, 1, -1, -1}, dy[] = {-1, 1, 1, -1};
		check[x][y] = 5;
		for(int i = 0; i < 4; i++) {
			if( i == 0 || i == 2) {
				for(int j = 0; j < d1; j++) {
					x += dx[i];
					y += dy[i];
					check[x][y] = 5;
				}
			}else {
				for(int j = 0; j < d2; j++) {
					x += dx[i];
					y += dy[i];
					check[x][y] = 5;
				}
			}
		}
	}
	// 일단 풀고 최적화를 생각
	static int cal(int x, int y, int d1, int d2) {
		score = new int[6];
		score[5] = total;
		int max = -total, min = total;
		int[][] start = new int[][] {{0, 0}, {0, n-1}, {n-1, 0}, {n-1, n-1}};
		for(int i = 0; i < 4; i++) {
			score[i] = rangeCal(start[i][0], start[i][1], x, y, d1, d2, i);
			max = Math.max(max, score[i]);
			min = Math.min(min, score[i]);
			score[5] -= score[i];
		}
//		System.out.println(Arrays.toString(score) + " " + max + " " + min);
		max = Math.max(max, score[5]);
		min = Math.min(min, score[5]);
		return max - min;
	}
}
