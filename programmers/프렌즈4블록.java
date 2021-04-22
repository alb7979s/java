import java.util.LinkedList;
import java.util.Stack;

class Solution {
	static char arr[][];
	static boolean visit[][];
    static public int solution(int n, int m, String[] board) {
    	arr = new char[n][m];
    	for(int i = 0; i < n; i++) {
    		arr[i] = board[i].toCharArray();
    	}
    	int ans = 0;
    	while(true) {
	    	check(n, m);
	    	int temp = gravity(n, m);
	    	if(temp == 0)break;
	    	ans += temp;
    	}
    	return ans;
    }
    static void check(int n, int m) {
    	visit = new boolean[n][m];
    	for(int i = 0; i < n-1; i++) {
    		for(int j = 0; j < m-1; j++) {
    			char p = arr[i][j];
    			if(p == ' ') continue;
    			if(p == arr[i][j+1] && p == arr[i+1][j] && p == arr[i+1][j+1]) {
    				visit[i][j] = true;
    				visit[i][j+1] = true;
    				visit[i+1][j] = true;
    				visit[i+1][j+1] = true;
    			}
    		}
    	}
    }
    static int gravity(int n, int m) {
    	int cnt = 0;
    	Stack<Character> stack = new Stack<>();
    	for(int i = 0; i < m; i++) {
    		for(int j = 0; j < n; j++) {
    			if(visit[j][i]) {
    				cnt++;
    			} else if(arr[j][i] != ' '){
    				stack.add(arr[j][i]);
    			}
    			arr[j][i] = ' ';
    		}
    		int j = n - 1;
    		while(!stack.isEmpty()) arr[j--][i] = stack.pop();
    	}
    	return cnt;
    }
    public static void main(String[] args) throws Exception {
		System.out.println(solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"}));
	}
}
