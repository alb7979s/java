import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int dx[] = {-1, -1, -1, 0, 0, 1, 1, 1}, dy[] = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int n, m, k, energy[][], bonusEnergy[][];
	static Deque<Integer> trees[][], temp;
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static void springAndSummer() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				temp = new ArrayDeque<>();
				int died = 0;
				while(!trees[i][j].isEmpty()) {
					int age = trees[i][j].pollFirst();
					if(age <= energy[i][j]) {
						energy[i][j] -= age;
						temp.add(age+1);
					}
					else {
						died += age/2;
					}
				}
				while(!temp.isEmpty()) {
					trees[i][j].addLast(temp.poll());
				}
				energy[i][j] += died;
			}
		}
	}
	static void autumnAndWiter() {
		for(int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for(int age: trees[i][j]) {
					if(age % 5 == 0) {
						for(int d = 0; d < 8; d++) {
							int nx = i + dx[d];
							int ny = j + dy[d];
							if (nx < 0 || ny < 0 || nx > n-1 || ny > n-1) continue;
							trees[nx][ny].addFirst(1);
						}
					}
				}
				energy[i][j] += bonusEnergy[i][j];
			}
		}
	}
	static void flow() {
		springAndSummer();
		autumnAndWiter();
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		k = stoi(st.nextToken());
		energy = new int[n][n];
		bonusEnergy = new int[n][n];
		//음.. 2차원 배열에 deque 넣는거는 이런식으로 선언하네 자바는 알면 알수록 모르겠네
		trees = new ArrayDeque[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				trees[i][j] = new ArrayDeque<>();					
			}
		}
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				//2중포문 돌리는김에 양분 초기화도 같이
				energy[i][j] = 5;
				bonusEnergy[i][j] = stoi(st.nextToken());
			}
		}
		for(int i = 0; i < m; i++) {
			int x, y, z;
			st = new StringTokenizer(br.readLine());
			x = stoi(st.nextToken())-1;
			y = stoi(st.nextToken())-1;
			z = stoi(st.nextToken());
			trees[x][y].addLast(z);
		}
		for(int i = 0; i < k; i++) {
			flow();
		}
		int res = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				res += trees[i][j].size();
			}
		}
		System.out.println(res);
	}
}
