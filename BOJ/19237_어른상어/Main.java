import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 상어 냄새 뿌림
 * 2. 상어 이동
 * 3. 냄새 업데이트
 */
class Main {
	static int n, m, k, dir[], priorityDir[][][], visit[][];
	static Smell smell[][];
	static Queue<Shark> q;
	static class Smell {
		int life, num;
		Smell(int life, int num){
			this.life = life;
			this.num = num;
		}
	}
	// 여기 d 넣을라 했는데 문제 입력에서 d를 나중에 줘서 그냥 dir로 따로 뺌 
	static class Shark {
		int x, y, num;
		Shark(int x, int y, int num){
			this.x = x;
			this.y = y;
			this.num = num;
		}
		public String toString() {
			return (x+1) + " " + (y+1) + " " + dir[num-1] + " " + num;
		}
	}
	static int dx[] = { -1, 1, 0, 0 }, dy[] = { 0, 0, -1, 1 };	//UDLR
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		k = stoi(st.nextToken());
		smell = new Smell[n][n];
		dir = new int[m];
		q = new ArrayDeque<>();
		priorityDir = new int[m][4][4];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				int x = stoi(st.nextToken());
				if (x != 0) {
					q.add(new Shark(i, j, x));
					smell[i][j] = new Smell(k, x);
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) dir[i] = stoi(st.nextToken())-1;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for(int l = 0; l < 4; l++) {
					priorityDir[i][j][l] = stoi(st.nextToken())-1;
				}
			}
		}
		System.out.println(solve());
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static void smellUpdate() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(smell[i][j] == null) continue;
				if (--smell[i][j].life == 0) smell[i][j] = null;
			}
		}
	}
	static boolean move() {
		visit = new int[n][n];
		while(!q.isEmpty()) {
			boolean isMove = false;
			Shark shark = q.poll();
			int sharkD = dir[shark.num-1];		//방향은 idx 0부터 시작해서 num-1
			// 냄새 없는 칸 확인
			for(int i = 0; i < 4; i++) {
				int d = priorityDir[shark.num-1][sharkD][i];	
				int nx = shark.x + dx[d];
				int ny = shark.y + dy[d];
				if(nx < 0 || ny < 0 || nx > n-1 || ny > n-1 || smell[nx][ny] != null) continue;
				if(visit[nx][ny] == 0 || (visit[nx][ny] != 0 && visit[nx][ny] >= shark.num)) {
					dir[shark.num-1] = d;
					visit[nx][ny] = shark.num;
				}
				isMove = true;
				break;
			}
			if(isMove) continue;
			for(int i = 0; i < 4; i++) {
				int d = priorityDir[shark.num-1][sharkD][i];
				int nx = shark.x + dx[d];
				int ny = shark.y + dy[d];
				if(nx < 0 || ny < 0 || nx > n-1 || ny > n-1) continue;
				if(smell[nx][ny] != null && smell[nx][ny].num != shark.num) continue;
				if(visit[nx][ny] == 0 || (visit[nx][ny] != 0 && visit[nx][ny] >= shark.num)) {
					dir[shark.num-1] = d;
					visit[nx][ny] = shark.num;
				}
				break;
			}
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++){
				if(visit[i][j] != 0) {
					smell[i][j] = new Smell(k+1, visit[i][j]);
					q.add(new Shark(i, j, visit[i][j]));
				}
			}
		}
		if(q.size() == 1) return false;
		return true;
	}
	static int solve() {
		for(int t = 1; t <= 1000; t++) {
			if(!move()) return t;
			smellUpdate();
		}
		return -1;
	}
}
