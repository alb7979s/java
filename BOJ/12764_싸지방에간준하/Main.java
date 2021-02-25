import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int start, end;

		Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	static class Info implements Comparable<Info> {
		int end, idx;

		Info(int end, int idx) {
			this.end = end;
			this.idx = idx;
		}

		@Override
		public int compareTo(Info o) {
			return this.end - o.end;
		}
	}

	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Node> list = new ArrayList<>();
		int n = stoi(br.readLine());
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Node(stoi(st.nextToken()), stoi(st.nextToken())));
		}
		Collections.sort(list, (o1, o2) -> o1.start - o2.start);
		// 사용중인 정보 끝나는 순서 빠른순으로, idx정보 추가
		PriorityQueue<Info> pq = new PriorityQueue<>();
		// 남는 자리 인덱스 빠른순으로
		PriorityQueue<Integer> SurplusSeats = new PriorityQueue<>();
		List<Integer> res = new ArrayList<>();
		int resIdx = 0;
		for(int i = 0; i < n ; i++) {
			while(!pq.isEmpty() && pq.peek().end < list.get(i).start) {
				SurplusSeats.add(pq.poll().idx);
			}
			// 여유 자리 있으면 그 자리에 배정
			if(!SurplusSeats.isEmpty()) {
				int idx = SurplusSeats.poll();
				pq.add(new Info(list.get(i).end, idx));
				res.set(idx, res.get(idx)+1);
			}
			// 여유 자리 없으면 자리 새로 생성
			else {
				res.add(1);
				pq.add(new Info(list.get(i).end, resIdx++));
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(resIdx + "\n");
		for(int x: res) {
			sb.append(x + " ");
		}
		System.out.println(sb);
	}
}