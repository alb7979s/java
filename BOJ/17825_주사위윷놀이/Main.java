import java.util.Map;
import java.util.Scanner;

// visit
public class Main {
	// data
	// board로 점수 확인(가는 방향에따라 이차원으로 처리)
	// 말 정보(x, y, 도착했는지)
	// visited로 board에 있는지 확인하려 했는데... board가 저렇게 있을땐 visited 크기 어떻게 선언해야함? 최대로?
	static final int BOARD[][] = new int [][]{ 
			{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40},
			{10, 13, 16, 19},	
			{20, 22, 24},		
			{30, 28, 27, 26},	
			{40},
			{25, 30, 35, 40},
	};
	static class Horse {
		int x, y;
		boolean arrived;
		Horse(int x, int y, boolean arrived) {
			this.x = x;
			this.y = y;
			this.arrived = arrived;
		}
		// 디버깅용
		public String toString() {
			return x + ", " + y + ", " + arrived;
		}
	}
	static int dice[];
	static Horse horse[];
	static boolean visit[][] = new boolean[BOARD.length][BOARD[0].length];	//최대로 잡음
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		dice = new int[10];
		horse = new Horse[4];
		for(int i = 0; i < 4; i++) horse[i] = new Horse(0, 0, false);
		for(int i = 0; i < 10; i++) {
			dice[i] = sc.nextInt();
		}
		System.out.println(solve(0, 0));
		sc.close();
	}
	
	// function
	// 완탐
	// 말 이동, 방향 바뀌면 들어가는거 처리
	static int max(int a, int b) {
		return a > b ? a : b;
	}
	static void deepCopy(Horse[] src, Horse[] dest) {
		for(int i = 0; i < dest.length; i++) {
			dest[i] = new Horse(src[i].x, src[i].y, src[i].arrived);
		}
	}
	static void deepCopy(boolean[][] src, boolean[][] dest) {
		for(int i = 0; i < src.length; i++) {
			dest[i] = src[i].clone();
		}
	}
	// 이렇게 해도 되고 생성자 이용해서 해도 됨 new HashMap<String, Boolean>(src); 이런식으로
	// 둘 다 알아두기
	static void deepCopy(Map<String, Boolean>src, Map<String, Boolean>dest) {
		for(Map.Entry<String, Boolean> entry: src.entrySet()) {
			dest.put(entry.getKey(), entry.getValue());
		}
	}
	static int move(Horse h, int d) {
		int nx = h.x;
		int ny = h.y + d;
		if(BOARD[nx].length <= ny) {
			// 도착
			if(nx == 0 || nx == 5) {
				visit[h.x][h.y] = false;
				h.arrived = true;
				return 0;
			} else {
				ny = ny - BOARD[nx].length; 
				nx = 5;
				if(BOARD[nx].length <= ny) {
					visit[h.x][h.y] = false;
					h.arrived = true;
					return 0;
				}
			}
		}
		// 방향 꺾이는거 처리
		if(nx == 0) {
			if(ny % 5 == 0) {
				nx = (ny) / 5;
				ny = 0;
				if(nx == 4) {
					nx = 5;
					ny = BOARD[nx].length-1;
				}
			}
		}
		// 이미 그 위치에 말이 있으면 종료
		if (visit[nx][ny]) {
			return -1;
		}
		// 이동할거라서 지움, x, y 좌표 갱신, visited 갱신
		visit[h.x][h.y] = false;
		h.x = nx;
		h.y = ny;
		visit[nx][ny] = true;
		return BOARD[nx][ny];
	}
	static int solve(int pos, int sum) {
		int res = 0;
		if(pos == dice.length) {
			return sum;
		}
//		Map<String, Boolean> tempVisited = new HashMap<>();
//		deepCopy(visited, tempVisited);
		Horse tempHorse[] = new Horse[4];
		boolean[][] tempVisit = new boolean[visit.length][visit[0].length];
		deepCopy(visit, tempVisit);
		deepCopy(horse, tempHorse);
		for(int i = 0; i < 4; i++) {
			if(horse[i].arrived) continue;
			int add = move(horse[i], dice[pos]);
//			System.out.println(pos + " " + i + " " + horse[i] + " " + add);
			if (add == -1) continue;		//말 있는경우는 다음말로
			res = max(res, solve(pos+1, sum+add));
//			deepCopy(tempVisited, visited);
			deepCopy(tempVisit, visit);
			deepCopy(tempHorse, horse);
		}
		// 10, 10, 10, 10, ... 이면 pos 끝에 못닿음 그거 처리해주려고 중간의 sum과도 비교해줌 
		res = max(res, sum);
		return res;
	}
}
