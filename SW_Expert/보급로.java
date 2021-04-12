import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
 
class Solution {
    static final int INF = (int)1e9;
    static int n, board[][], dist[][];
    static int dx[] = { 0, 1, 0, -1 }, dy[] = { 1, 0, -1, 0 };
    static class Node implements Comparable<Node>{
        int x, y, d;
 
        Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
        public int compareTo(Node o) {
            return this.d - o.d;
        }
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = stoi(br.readLine());
        for(int tc = 1; tc <= testCase; tc++) {
            n = stoi(br.readLine());
            board = new int[n][n];
            for(int i = 0; i < n ; i++) {
                String temp = br.readLine();
                for(int j = 0; j < n; j++) {
                    board[i][j] = temp.charAt(j) - '0';
                }
            }
            System.out.printf("#%d %d%n", tc, dijkstra());
        }
    }
    static int stoi(String s) {
        return Integer.parseInt(s);
    }
    static int dijkstra() {
        dist = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                dist[i][j] = INF;
            }
        }
        dist[0][0] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, 0));
        while(!pq.isEmpty()) {
            Node curr = pq.poll();
            if(dist[curr.x][curr.y] < curr.d) continue;
            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if(nx < 0 || ny < 0 || nx > n-1 || ny > n-1) continue;
                if(dist[nx][ny] > curr.d + board[nx][ny]) {
                    int nd = curr.d + board[nx][ny];
                    dist[nx][ny] = nd;
                    pq.add(new Node(nx, ny, nd));
                }
            }
        }
        return dist[n-1][n-1];
    }
}
