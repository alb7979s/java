import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, operator[], operand[];	// 연산자(operator): +, -, ... 피연산자(operand): x, y, ...
	static int MAX = (int)1e9;
	static int max = -MAX, min = MAX;
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static int cal(int x, int y, int i) {
		if (i == 0) return x + y;
		else if (i == 1) return x - y;
		else if (i == 2) return x * y;
		else return x / y;	// 원래 y 0으로 나누는거 안되도록 처리해줘야함
	}
	static void solve(int pos, int total) {	// total이 합의 의미로 쓴건라 sum이라 써도 되는데, 파이썬에선 sum이 예약어라 전 주로 total로 써요
		if(pos == n) {
			max = Math.max(max, total);
			min = Math.min(min, total);
			return;
		}
		for(int i = 0; i < 4; i++) {
			if(operator[i] > 0) {
				operator[i]--;
				solve(pos+1, cal(total, operand[pos], i));
				operator[i]++;
			}
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = stoi(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		operand = new int[n];
		operator = new int[4];
		for(int i = 0; i < n; i++) {
			operand[i] = stoi(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < 4; i++) {
			operator[i] = stoi(st.nextToken());
		}
		solve(1, operand[0]);
		System.out.println(max);
		System.out.println(min);
	}
}