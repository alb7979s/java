import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ctrl + shift + f 자동정렬, 일단 자바 언어 익히는데에 집중하고 나중에 익숙해지면 코딩하면서 신경써주기
public class Main {
	// 문자열 받아서 정수로 뱉는 함수, 그냥 Interger.parseInt()라는 내장함수가 있네유
	static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static int n;
	static boolean[] visited;
	static int[][] arr;
	static int INF = (int) 1e9;

	// throws: 메서드 내부 소스코드에서 에러가 발생했을시 이 메서드를 사용하는 곳으로 책임을 전가하는 행위
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer: 지정된 구분자를 기준으로 문자열 슬라이싱
		StringTokenizer st;

		n = stoi(br.readLine());
		// 와 멤버변수서 선언해준거 이렇게 동적할당해서 쓰는거구먼
		visited = new boolean[n];
		arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = stoi(st.nextToken());
			}
		}
		System.out.println(solve(0, 0));
	}

	static int solve(int pos, int cnt) {
		int res = INF;
		if (cnt > (n / 2))
			return res;
		if (pos == n) {
			if (cnt == (n / 2)) {
				res = Math.min(res, cal());
			}
			return res;
		}
		visited[pos] = true;
		res = Math.min(res, solve(pos + 1, cnt + 1));
		visited[pos] = false;
		res = Math.min(res, solve(pos + 1, cnt));
		return res;
	}

	static int cal() {
		int lTeam = 0, rTeam = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (visited[i] && visited[j])
					lTeam += arr[i][j] + arr[j][i];
				if (!visited[i] && !visited[j])
					rTeam += arr[i][j] + arr[j][i];
			}
		}
		return Math.abs(lTeam - rTeam);
	}
}
