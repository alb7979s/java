import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String str = br.readLine();
		Stack<Character> left = new Stack<>();
		Stack<Character> right = new Stack<>();
		for (int i = 0; i < str.length(); i++) {
			left.push(str.charAt(i));
		}
		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			char command = st.nextToken().charAt(0);
			if(command == 'L') 
				if(!left.isEmpty()) 
					right.push(left.pop());
			if(command == 'D') 
				if(!right.isEmpty())
					left.push(right.pop());
			if(command == 'B')
				if(!left.isEmpty())
					left.pop();
			if(command == 'P') {
				left.push(st.nextToken().charAt(0));
			}
		}
		StringBuilder sb = new StringBuilder();
		for(char x : left) sb.append(x);
		while(!right.isEmpty()) sb.append(right.pop());
		System.out.println(sb);
	}
}