import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static int stoi(String s) {
        return Integer.parseInt(s);
    }
 
    static int U = 0, R = 1, D = 2, L = 3;
    static int dx[] = { -1, 0, 1, 0 }, dy[] = { 0, 1, 0, -1 }; // URDL
    static int n, m, x, y, d;
    static String commands, tank = "^>v<", dir = "URDL";
    static char board[][];
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = stoi(br.readLine());
        StringTokenizer st;
        for (int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            n = stoi(st.nextToken());
            m = stoi(st.nextToken());
            board = new char[n][m];
            for (int i = 0; i < n; i++) {
                String str = br.readLine();
                for (int j = 0; j < m; j++) {
                    board[i][j] = str.charAt(j);
                    if (board[i][j] == '^' || board[i][j] == '>' || board[i][j] == 'v' || board[i][j] == '<') {
                        x = i;
                        y = j;
                        d = tank.indexOf(board[i][j]);
                    }
                }
            }
            int commandLen = stoi(br.readLine());
            commands = br.readLine();
            for (int i = 0; i < commandLen; i++) {
                char command = commands.charAt(i);
                if (command == 'S') {
                    int sx = x, sy = y;
                    while (true) {
                        sx += dx[d];
                        sy += dy[d];
                        if (sx < 0 || sy < 0 || sx > n - 1 || sy > m - 1 || board[sx][sy]=='#')
                            break;
                        if(board[sx][sy] == '*') {
                            board[sx][sy] = '.';
                            break;
                        }
                    }
                } else {
                    d = dir.indexOf(command);
                    board[x][y] = tank.charAt(d);
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (nx < 0 || ny < 0 || nx > n - 1 || ny > m - 1 || board[nx][ny] != '.')
                        continue;
                    board[nx][ny] = tank.charAt(d);
                    board[x][y] = '.';
                    x = nx;
                    y = ny;
                }
            }
            System.out.print("#" + tc + " ");
            for(int i = 0; i < n; i++) {
                for(int j = 0; j <m; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
        }
    }
}