import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static final String VOWEL = "aeiou";
	static int l, c;
	static char arr[];
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	// String call by value라서 파라미터로는 막 써도 될듯?
	static void solve(int pos, String str) {
		if(pos == c) {
			if(str.length() == l) {
				int cnt = 0;
				for (int i = 0; i < l; i++) {
					if(VOWEL.indexOf(str.charAt(i)) != -1) cnt++;
				}
				if(cnt == 0 || l - cnt < 2) return;
				else System.out.println(str);
			}
			return;
		}
		solve(pos+1, str + arr[pos]);
		solve(pos+1, str);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		l = stoi(st.nextToken());
		c = stoi(st.nextToken());
		arr = new char[c];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < c; i++) {
			arr[i] = st.nextToken().charAt(0); 
		}
		Arrays.sort(arr);
		solve(0, "");
	}
}
