import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
	static int n, dn, k, life[];
	static boolean visit[];
	static List<Integer> robot;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		k = stoi(st.nextToken());
		dn = 2*n;
		life = new int[dn];
		robot = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < dn; i++) {
			life[i] = stoi(st.nextToken());
		}
		System.out.println(solve());
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static void rotateBelt() {
		int removeIdx = -1;
		visit = new boolean[dn];
		for(int i = 0; i < robot.size(); i++) {
			int next = (robot.get(i) + 1) % dn;
			robot.set(i, next);
			visit[next] = true;
			if(next == n-1) {
				removeIdx = i;
				visit[next] = false;
			}
		}
		if(removeIdx != -1) robot.remove(removeIdx);
		int temp[] = new int[dn];
		for(int i = 0; i < dn; i++) {
			temp[i] = life[(i+(dn-1))%dn];
		}
		for(int i = 0; i < dn; i++) {
			life[i] = temp[i];
		}
	}
	static void moveRobot() {
		int removeIdx = -1;
		for(int i = 0; i < robot.size(); i++) {
			int curr = robot.get(i);
			int next = (curr + 1) % dn;
			if(life[next] > 0 && !visit[next]) {
				life[next]--;
				visit[curr] = false;
				visit[next] = true;
				robot.set(i, next);
				if(life[next] == 0) k--;
				if(next == n-1) {
					removeIdx = i;
					visit[next] = false;
				}
			}
		}
		if(removeIdx != -1) robot.remove(removeIdx);
		if(life[0] > 0 && !visit[0]) {
			life[0]--;
			if(life[0] == 0)k--;
			robot.add(0);
		}
	}
	static int solve() {
		int res = 1;
		while(true) {
			rotateBelt();
			moveRobot();
			if(k <= 0) return res;
			res++;
		}
	}
}
