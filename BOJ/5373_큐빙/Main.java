import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, cube[];
	static String color = "wyrogb", dir = "UDFBLR";
	// change => UDFBLR 순서대로 바뀌는 좌표들 나열, 인덱스값으로 직접 사용말고 상대적으로 사용할거임(하나씩 다 그려봐야 만들 수 있음) 
	static int change[][] = {
			{ 18, 19, 20, 45, 46, 47, 27, 28, 29, 36, 37, 38 },
			{ 24, 25, 26, 42, 43, 44, 33, 34, 35, 51, 52, 53 },
			{ 6, 7, 8, 44, 41, 38, 11, 10, 9, 45, 48, 51 },
			{ 36, 39, 42, 2, 1, 0, 53, 50, 47, 15, 16, 17 },
			{ 0, 3, 6, 35, 32, 29, 9, 12, 15, 18, 21, 24 },
			{ 11, 14, 17, 33, 30, 27, 2, 5, 8, 20, 23, 26 }
			};
			
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static int[] init() {
		int[] arr = new int[54];
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 9; j++) {
				arr[i*9+j] = i; 
			}
		}
		return arr;
	}
	static void selfTurn(int d) {
		int temp[][] = new int[3][3];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++)
				temp[j][3-i-1] = cube[d*9 + i*3 + j];
		}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				cube[d*9 + i*3 + j] = temp[i][j];
			}
		}
	}
	// 반시계는 시계 3번돌리자 반시계 따로 만들기는 넘모 귀찮
	static void turn(String command) {
		int d = dir.indexOf(command.charAt(0));
		for(int i = 0; i < (command.charAt(1)=='+'? 1 : 3); i++) {
			// 자기 자신 돌아가는거
			selfTurn(d);
			// 주위 12곳 돌아가는거
			int[] temp = new int[12];
			for(int j = 0; j < 12; j++) {
				temp[j] = cube[change[d][(j+3)%12]];
			}
			for(int j = 0; j < 12; j++) {
				cube[change[d][j]] = temp[j];
			}
		}
	}
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = stoi(br.readLine());
		for(int tc = 0; tc < testCase; tc++) {
			cube = init();
			int cnt = stoi(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < cnt; i++) {
				turn(st.nextToken());
			}
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					sb.append(color.charAt(cube[i*3 + j]));
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
}