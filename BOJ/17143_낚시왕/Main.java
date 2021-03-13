import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, k, diedX, diedY;
	static int dx[] = { -1, 1, 0, 0 }, dy[] = { 0, 0, 1, -1};
	static int changeD[] = { 1, 0, 3, 2};
	static Queue<Shark> q;
	static HashMap<String, Shark> visit; 
	static List<Node> fishingList;
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static class Node implements Comparable<Node>{
		int x, y, size;
		Node(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}
		@Override
		public int compareTo(Node o) {
			return this.x - o.x;
		}
	}

	static class Shark{
		int x, y, speed, d, size;

		public Shark(int x, int y, int speed, int d, int size) {
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.d = d;
			this.size = size;
		}

	}
	static int fishing() {
		diedX = -1;
		diedY = -1;
		if(!fishingList.isEmpty()) {
			Collections.sort(fishingList);
			Node node = fishingList.get(0);
			diedX = node.x;
			diedY = node.y;
			return node.size;
		}
		return 0;
	}
	static void move(int nextTarget) {
		visit = new HashMap<>();
		fishingList = new ArrayList<>();
		while(!q.isEmpty()) {
			Shark shark = q.poll();
			if(diedX == shark.x && diedY == shark.y) continue;
			for(int j = 0; j < shark.speed; j++) {
				shark.x += dx[shark.d];
				shark.y += dy[shark.d];
				if(shark.x < 0 || shark.y < 0 || shark.x > n-1 || shark.y > m-1) {
					shark.d = changeD[shark.d];
					shark.x += dx[shark.d]*2;
					shark.y += dy[shark.d]*2;
				}
			}
			String key = shark.x+","+shark.y;
			Shark visitShark = visit.get(key);
			if(visitShark != null && visitShark.size >= shark.size) continue;
			visit.put(key, shark);
		}
		for(String key: visit.keySet()) {
			Shark shark = visit.get(key);
			q.add(shark);
			if(shark.y == nextTarget) fishingList.add(new Node(shark.x, shark.y, shark.size));
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		k = stoi(st.nextToken());
		q = new LinkedList<>();
		fishingList = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x, y, speed, d, size;
			x = stoi(st.nextToken())-1;
			y = stoi(st.nextToken())-1;
			speed = stoi(st.nextToken());
			d = stoi(st.nextToken())-1;
			size = stoi(st.nextToken());
			q.add(new Shark(x, y, speed, d, size));
			if(y == 0) fishingList.add(new Node(x, y, size));
		}
		int res = 0;
		for(int i = 0; i < m; i++) {
			res += fishing();
			move(i+1);
		}
		System.out.println(res);
	}
}