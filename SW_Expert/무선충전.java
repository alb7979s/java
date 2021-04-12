import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
class Solution {
    static int n = 10, m, k, move[][];
    static boolean visit[][];
    static Info BC[][][];
    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Info implements Comparable<Info>{
        int num, p;
        Info(int num, int p){
            this.num = num;
            this.p = p;
        }
        @Override
        public int compareTo(Info o) {
            return (this.p - o.p)*-1;
        }
    }
    static int dx[] = { 0, -1, 0, 1, 0 }, dy[] = { 0, 0, 1, 0, -1 };
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = stoi(br.readLine());
        for(int tc = 1; tc <= testCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = stoi(st.nextToken());
            k = stoi(st.nextToken());
            BC = new Info[n][n][2];
            move = new int[2][m];
            for(int i = 0; i < n; i++) 
                for(int j = 0; j < n; j++)
                    for(int l = 0; l < 2; l++) BC[i][j][l] = new Info(-1, 0);
            for(int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < m; j++) {
                    move[i][j] = stoi(st.nextToken());
                }
            }
            for(int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = stoi(st.nextToken())-1;
                int y = stoi(st.nextToken())-1;
                makeBC(y, x, stoi(st.nextToken()),stoi(st.nextToken()), i);
            }
            int res = 0;
            int rx = 0, ry = 0, bx = n - 1, by = n - 1;
            // 사용자 지도 밖으로 이동하는 경우 없음 체크 안해줘도 됨
            for(int i = 0; i < m; i++) {
                res += cal(rx, ry, bx, by);
                int rd = move[0][i];
                int bd = move[1][i];
                rx += dx[rd];
                ry += dy[rd];
                bx += dx[bd];
                by += dy[bd];
            }
            res += cal(rx, ry, bx, by);
            sb.append("#" + tc + " " + res + "\n");
        }
        System.out.println(sb);
    }
    static int stoi(String s) {
        return Integer.parseInt(s);
    }
    static void makeBC(int x, int y, int c, int p, int num) {
        Queue<Node> q = new LinkedList<>();
        visit = new boolean[n][n];
        visit[x][y] = true;
        q.add(new Node(x, y));
        insertBC(x, y, new Info(num, p));
        for(int i = 0; i < c; i++) {
            int qSize = q.size();
            for(int j = 0; j < qSize; j++) {
                Node node = q.poll();
                for(int d = 1; d < 5; d++) {
                    int nx = node.x + dx[d];
                    int ny = node.y + dy[d];
                    if(nx < 0 || ny < 0 || nx > n-1 || ny > n-1 || visit[nx][ny]) continue;
                    insertBC(nx, ny, new Info(num, p));
                    visit[nx][ny] = true;
                    q.add(new Node(nx, ny));
                }
            }
        }
    }
    static void insertBC(int x, int y, Info info) {
        if( BC[x][y][0].p <= info.p ){
            BC[x][y][1] = BC[x][y][0];
            BC[x][y][0] = info;
        } else {
            if(BC[x][y][1].p <= info.p) {
                BC[x][y][1] = info;
            }
        }
    }
    static int max(int a, int b) {
        return a > b? a : b;
    }
    static int cal(int rx, int ry, int bx, int by) {
        Info r = BC[rx][ry][0];
        Info b = BC[bx][by][0];
        if(r.num == -1) return b.p;
        if(b.num == -1) return r.p;
        if(r.num == b.num) {
            Info nr = BC[rx][ry][1];
            Info nb = BC[bx][by][1];
            int nextMax = max(r.p + nr.p, b.p + nb.p);
            return max(r.p, nextMax);
        }else {
            return r.p + b.p;
        }
    }
}
