import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static int[] getPi(int[] pi, String p) {
		int m = p.length();
		int j = 0;
		for(int i = 1; i < m; i++) {
			while(j > 0 && p.charAt(i) != p.charAt(j)) {
				j = pi[j-1];
			}
			if(p.charAt(i) == p.charAt(j)) {
				j++;
				pi[i] = j;
			}
		}
		return pi;
	}
	static List<Integer> kmp(String s, String p){
		int n = s.length();
		int m = p.length();
		int j = 0;
		int pi[] = new int[m+1];
		pi = getPi(pi, p);
		List<Integer> ans = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			while( j > 0 && s.charAt(i) != p.charAt(j)) {
				j = pi[j-1];
			}
			if(s.charAt(i) == p.charAt(j)) {
				if ( j == m - 1) {
					ans.add(i - j);
					j = pi[j];
				}
				else {
					j++;
				}
			}
		}
		return ans;
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a, b;
		a = br.readLine();
		b = br.readLine();
		List<Integer> ans = kmp(a, b);
		System.out.println(ans.size());
		StringBuilder sb = new StringBuilder();
		for(int x: ans) {
			sb.append(x+1 + " ");
		}
		System.out.println(sb);
	}
}
