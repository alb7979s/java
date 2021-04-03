import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// MVC 배우니까 뭔가 데이터랑 기능을 나누고 싶어짐
public class Main {
	/* data */
	// 8방향, 45도씩 바꿔가며 갈 수 있는곳
	// 노드 x, y, d, died
	// fish<Node> 물고기 정보 저장하는데, 총 16마리 
	// map[x][y] 물고기 번호 저장
	// 상어 좌표도 표시해두면 편할듯
	static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, -1, -1, -1, 0, 1, 1, 1};
	static final int n = 4, maxFish = 17;
	static Fish fish[] = new Fish[maxFish];
	static int board[][] = new int[n][n], sharkX = 0, sharkY = 0;
	static class Fish {
		int x, y, d;
		boolean died;
		Fish(int x, int y, int d, boolean died) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.died = died;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				int fishNum = stoi(st.nextToken());
				int fishDir = stoi(st.nextToken()) - 1;
				fish[fishNum] = new Fish(i, j, fishDir, false);
				board[i][j] = fishNum;
			}
		}
		fish[0] = new Fish(0, 0, 0, true);
		System.out.println(solve(0, 0, 0));
	}
	/* service */
	// 완탐(x, y, sum), 
	// 사냥(잡아먹고 방향 받아옴, 물고기 이동, 받아온 방향으로 갈 수 있는 곳 모두 탐색하는데 이동할 곳 없으면 종료) 
	// 물고기 있으면 자리 바꾸고 없음 이동
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static int max(int a, int b) {
		return a > b ? a : b;
	}
	static void deepcopy(int[][] src, int[][] dest) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				dest[i][j] = src[i][j];
			}
		}
	}
	// 객체 카피 할때 그냥 dest[i] = src[i] 했다가 주소값 넘겨줘서 deepcopy 안되었었음
	static void deepcopy(Fish[] src, Fish[] dest) {
		for(int i = 0; i < maxFish; i++) {
			dest[i] = new Fish(src[i].x, src[i].y, src[i].d,src[i].died);
		}
	}
	static void move(Fish curr, int fishNum) {
		for(int i = 0; i < 8; i++) {
			int d = (curr.d + i) % 8;
			int nx = curr.x + dx[d];
			int ny = curr.y + dy[d];
			if(nx < 0 || ny < 0 || nx > n-1 || ny > n-1 || (sharkX == nx && sharkY == ny)) continue;
			// 물고기 교환 => 교환할 물고기의 x, y 좌표 바뀜, 공통: 자기 x, y, d바뀌고  board 바뀜  
			if(board[nx][ny] != 0) {
				int nextFishNum = board[nx][ny];
				Fish next = fish[nextFishNum];
				next.x = curr.x;
				next.y = curr.y;
			}
			int temp = board[curr.x][curr.y];
			board[curr.x][curr.y]= board[nx][ny];
			board[nx][ny] = temp;
			curr.x = nx;
			curr.y = ny;
			curr.d = d;
			break;
		}
	}
	static void print() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.printf("%d ", board[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	static int solve(int x, int y, int sum) {
		int res = 0;
		// 사냥
		int fishNum = board[x][y];
		Fish curr = fish[fishNum];
		int sharkDir = curr.d; 
		sum += fishNum;
		board[x][y] = 0;
		curr.died = true;
		sharkX = x;
		sharkY = y;
//		System.out.println(x +" "+ y + " " + sharkDir);
//		print();
		// 이동
		for(int i = 0; i < maxFish; i++) {
			curr = fish[i];
			if(!curr.died) move(curr, i);
		}
		// 완탐
		boolean canMove = false;
		Fish tempFish[] = new Fish[maxFish];
		int tempBoard[][] = new int[n][n];
		deepcopy(fish, tempFish);
		deepcopy(board, tempBoard);
		for(int i = 1; i < n; i++) {
			// sharkX, sharkY 글로벌로 바꿔가면서 써서 오류 있었음 
			int nx = x + (dx[sharkDir]*i);
			int ny = y + (dy[sharkDir]*i);
			if( nx < 0 || ny < 0 || nx > n-1 || ny > n-1 || board[nx][ny] == 0) continue;
			canMove = true;
			res = max(res, solve(nx, ny, sum));
			deepcopy(tempFish, fish);
			deepcopy(tempBoard, board);
		}
		if(!canMove) return sum;
		return res;
	}
	
}

