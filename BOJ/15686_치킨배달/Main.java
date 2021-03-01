import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static final int INF = (int)1e9;
	static int n, m;
	static List<Node> storeList, houseList;
	static Stack<Node> pickedList = new Stack<>();
	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static int cal() {
		int sum = 0;
		for(int i = 0; i < houseList.size(); i++) {
			int min = INF;
			for(Node p: pickedList) {
				min = Math.min(min, Math.abs(houseList.get(i).x - p.x) + Math.abs(houseList.get(i).y - p.y));
			}
			sum += min;
		}
		return sum;
	}
	static int solve(int pos, int cnt) {
		int res = INF;
		if(cnt > m) return res;
		if(pos == storeList.size()) {
			if(cnt == m) return cal();
			return res;
		}
		pickedList.add(storeList.get(pos));
		res = Math.min(res, solve(pos+1, cnt+1));
		pickedList.pop();
		res = Math.min(res, solve(pos+1, cnt));
		return res;
	}
	public static void main(String[] args) throws Exception {
		storeList = new ArrayList<>();
		houseList = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken()); 
		m = stoi(st.nextToken()); 
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				int x = stoi(st.nextToken());
				if(x == 1) houseList.add(new Node(i, j));
				else if(x == 2) storeList.add(new Node(i, j));
			}
		}
		System.out.println(solve(0, 0));
	}
}