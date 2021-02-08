import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		Stack<Character> stack = new Stack<>();
		String operands = "+-*/()";
		int[] priority = new int[] { 0, 0, 1, 1, 2, 2 };
		for (int i = 0; i < str.length(); i++) {
			char cur = str.charAt(i);
			if (operands.indexOf(cur) == -1)
				sb.append(cur);
			else {
				if (cur == ')') {
					while (!stack.isEmpty()) {
						char temp = stack.pop();
						if (temp == '(')
							break;
						sb.append(temp);
					}
				} else {
					int curIdx = operands.indexOf(cur);
					while (!stack.isEmpty() && priority[curIdx] <= priority[operands.indexOf(stack.peek())]) {
						if (stack.peek() == '(') break;
						sb.append(stack.pop());
					}
					stack.push(cur);

				}
			}
		}
		while (!stack.isEmpty())
			sb.append(stack.pop());
		System.out.println(sb);
	}
}