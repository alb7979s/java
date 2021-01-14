import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ctrl + shift + f �ڵ�����, �ϴ� �ڹ� ��� �����µ��� �����ϰ� ���߿� �ͼ������� �ڵ��ϸ鼭 �Ű���ֱ�
public class Main {
	// ���ڿ� �޾Ƽ� ������ ��� �Լ�, �׳� Interger.parseInt()��� �����Լ��� �ֳ���
	static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static int n;
	static boolean[] visited;
	static int[][] arr;
	static int INF = (int) 1e9;

	// throws: �޼��� ���� �ҽ��ڵ忡�� ������ �߻������� �� �޼��带 ����ϴ� ������ å���� �����ϴ� ����
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer: ������ �����ڸ� �������� ���ڿ� �����̽�
		StringTokenizer st;

		n = stoi(br.readLine());
		// �� ��������� �������ذ� �̷��� �����Ҵ��ؼ� ���°ű���
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
