import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static int n;
	static StringBuilder sb;

	static void hanoi(int cnt, int from, int temp, int to) {
		if (cnt == 1) {
			sb.append(from + " " + to + "\n");
			return;
		}
		hanoi(cnt - 1, from, to, temp);
		sb.append(from + " " + to + "\n");
		hanoi(cnt - 1, temp, from, to);
		return;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		n = stoi(br.readLine());
		// 규칙이 2^n승씩 더해지는 거라서(1<<i 계속 더해주면 됨) 결국, n번 왼쪽으로 shift한 후 1 빼주면 됨
		BigInteger res = BigInteger.ONE.shiftLeft(n).subtract(BigInteger.ONE);
		// 1이 첫번째 기둥, 2가 두번째 기둥, 3이 세번째 기둥
		if (n <= 20)
			hanoi(n, 1, 2, 3);
		System.out.println(res);
		System.out.println(sb);
	}
}