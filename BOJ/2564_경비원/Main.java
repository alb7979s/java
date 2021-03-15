import java.util.Scanner;

public class Main {
	static int m, n, k, x, y;
	static int dx[] = { -1, 0, 1, 0 }, dy[] = { 0, 1, 0, -1 }; 	// URDL
	static int[][] map;
	static int search(int target, boolean clock, int dir) {
		int d=-1, cnt=0;
		// 시계 방향이면
		if (clock) {
			// 순서대로 상, 하, 좌, 우
			if(dir == 1) d = 1;
			if(dir == 2) d = 3;
			if(dir == 3) d = 0;
			if(dir == 4) d = 2;
		} else {
			if(dir == 1) d = 3;
			if(dir == 2) d = 1;
			if(dir == 3) d = 2;
			if(dir == 4) d = 0;
		}
		int nx = x;
		int ny = y;
		for(int i = 0; i < 4; i++) {
			while(true) {
				nx += dx[d];
				ny += dy[d];
				if(nx < 0 || ny < 0 || nx > n-1 || ny > m-1 ) {
					nx -= dx[d];
					ny -= dy[d];
					if(clock) d = (d + 1)%4;
					else d = (d + 3)%4;
					break;
				}
				cnt++;
				if(map[nx][ny]==target) return cnt;
			}
		}
		return cnt;
	}
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt() + 1;
		n = sc.nextInt() + 1;
		k = sc.nextInt();
		int directions[] = {0, 0, n-1, 0, m-1};
		map = new int[n][m]; 
		for(int i = 1; i <= k; i++) {
			int dir, dist;
			dir = sc.nextInt();
			dist = sc.nextInt();
			if( dir == 1 || dir == 2) {
				map[directions[dir]][dist] = i;
			}
			else {
				map[dist][directions[dir]] = i;
			}
		}
		int dir = sc.nextInt();
		int dist = sc.nextInt();
		if(dir == 1 || dir == 2) {
			x = directions[dir];
			y = dist;
		}
		else {
			x = dist;
			y = directions[dir];
		}
//		map[x][y] = 9;
		int res = 0;
		for(int i = 1; i <= k; i++) {
//			System.out.println(res);
			int right = search(i, true, dir);
			int left = search(i, false, dir);
			res += Math.min(left, right);
		}
		System.out.println(res);
		sc.close();
	}
}
