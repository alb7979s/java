import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static final int SIZE = 100;
	static int x, y, k, maxRow = 3, maxCol = 3, arr[][], cnt[];

	static class Node implements Comparable<Node> {
		int num, cnt;

		Node(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}

		public int compareTo(Node o) {
			return this.cnt == o.cnt ? this.num - o.num : this.cnt - o.cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		x = stoi(st.nextToken())-1;
		y = stoi(st.nextToken())-1;
		k = stoi(st.nextToken());
		arr = new int[SIZE][SIZE];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = stoi(st.nextToken());
			}
		}
		System.out.println(solve());
	}

	static int stoi(String s) {
		return Integer.parseInt(s);
	}

	// 지금 생각나는 방법이,
	// 1. hash이용해서 배열[key] = new Node() 이렇게 처리해주던가
	// 2. 100밖에 안되니까 cnt[101]배열 만들어서 개수 0 아닌거 노드에 넣고 정렬 후 처리
	static int solve() {
		for (int time = 0; time <= 100; time++) {
			if (arr[x][y] == k)
				return time;
			// row 연산
			if (maxRow >= maxCol) {
				for (int i = 0; i < maxRow; i++) {
					cnt = new int[SIZE + 1];
					List<Node> nodeList = new ArrayList<>();
					for (int j = 0; j < maxCol; j++) {
						if(arr[i][j]!=0) {
							cnt[arr[i][j]]++;
							arr[i][j] = 0;
						}
					}
					for (int j = 0; j < SIZE + 1; j++) {
						if (cnt[j] != 0) {
							nodeList.add(new Node(j, cnt[j]));
						}
					}
					Collections.sort(nodeList);
					int idx = 0;
					for (Node node : nodeList) {
						arr[i][idx++] = node.num;
						arr[i][idx++] = node.cnt;
						if (idx >= SIZE)
							break;
					}
					maxCol = Math.max(maxCol, idx);
				}
			} else { // column 연산
				for (int i = 0; i < maxCol; i++) {
					cnt = new int[SIZE + 1];
					List<Node> nodeList = new ArrayList<>();
					for (int j = 0; j < maxRow; j++) {
						if(arr[j][i]!=0) {
							cnt[arr[j][i]]++;
							arr[j][i] = 0;
						}
					}
					for (int j = 0; j < SIZE + 1; j++) {
						if (cnt[j] != 0) {
							nodeList.add(new Node(j, cnt[j]));
						}
					}
					Collections.sort(nodeList);
					int idx = 0;
					for (Node node : nodeList) {
						arr[idx++][i] = node.num;
						arr[idx++][i] = node.cnt;
						if (idx >= SIZE)
							break;
					}
					maxRow = Math.max(maxRow, idx);
				}
			}
		}
		return -1;
	}
}
