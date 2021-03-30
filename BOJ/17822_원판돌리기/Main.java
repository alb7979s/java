import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, t, board[][];
	static int dx[] = { 0, 1, 0, -1 }, dy[] = { 1, 0, -1, 0 };
	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		t = stoi(st.nextToken());
		board = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				board[i][j] = stoi(st.nextToken());
			}
		}
		for(int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			rotate(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()));
			adjCheck();
		}
		int res = 0;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < m; j++)
				res += board[i][j];
		System.out.println(res);
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static void rotate(int x, int d, int k) {
		for(int i = x; i <= n; i+=x) {		// 배수 구해야해서 숫자 1부터 쓰는데 이 안에 사용하는 인덱스들은 -1해서 0부터 사용
			int[] temp  = new int[m];
			for(int j = 0; j < m; j++) {
				if(d == 0) {		//시계
					temp[j] = board[i-1][(j+(m-k))%m];
				}else {				//반시계
					temp[j] = board[i-1][(j+k)%m];
				}
			}
			for(int j = 0; j < m; j++) {
				board[i-1][j] = temp[j];
			}
		}
	}
	static void adjCheck() {
		int sum = 0, cnt = 0;
		boolean isAdj = false;
		Queue<Node> q = new ArrayDeque<>();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				int currVal = board[i][j];
				if(currVal != 0) {
					q.add(new Node(i, j));
					cnt++;
					sum += currVal;
					while(!q.isEmpty()) {
						Node node = q.poll();
						for(int d = 0; d < 4; d++) {
							int nx = node.x + dx[d];	
							int ny = ((node.y + dy[d] + m) % m);				//m더해서 m나머지 하면 -1일때 m-1되고, m일때 0됨
							if( nx < 0 || nx > n-1 || board[nx][ny] != currVal) continue;	//0인거 체크 안해줘도 됨 위에서 거름
							//여기 온거면 인접함 하나씩 지우면 출발점이나 끝점이 애매해져서 그냥 둘다 지워
							board[node.x][node.y] = 0;
							board[nx][ny] = 0;
							q.add(new Node(nx, ny));
							isAdj = true;
							}
						}
					}
				}
			}
		if(!isAdj) {
			float avg = (float)sum / (float)cnt;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(board[i][j] != 0) {
						if(avg < board[i][j]) board[i][j]--;
						else if(avg > board[i][j]) board[i][j]++;
					}
				}
			}
		}
	}
}

