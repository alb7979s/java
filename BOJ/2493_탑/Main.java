import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main{
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static class Node{
		int idx, height;
		Node(int idx, int height){
			this.idx = idx;
			this.height = height;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = stoi(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Stack<Node> stack = new Stack<>();
		int[] visit = new int[n];
		int[] arr = new int[n];
		for(int i = 0 ;i < n; i++) {
			arr[i] = stoi(st.nextToken());
			if (!stack.isEmpty() && stack.peek().height >= arr[i]) {
				visit[i] = stack.peek().idx + 1;
			}
			else {
				// 사이에 작은것들 쌓을 필요가 없음
				while(!stack.isEmpty()) {
					if(stack.peek().height <= arr[i]) {
						stack.pop();
					}
					else {
						visit[i] = stack.peek().idx + 1;
						break;
					}
				}
			}
			stack.push(new Node(i, arr[i]));
		}
		StringBuilder sb = new StringBuilder();
		for(int x: visit) sb.append(x + " ");
		System.out.println(sb);
	}
}