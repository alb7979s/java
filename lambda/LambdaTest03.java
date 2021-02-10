package day07;
// 총 정리

import java.util.HashMap;
import java.util.Map;

interface Board {
	void service(int no);
}
public class LambdaTest03 {
	public static void main(String[] args) throws Exception {
		Map<String, Board> map = new HashMap<>();
		Board list = (no) -> System.out.println("Board list : " + no);
		map.put("list", list);
		map.put("detail", no -> System.out.println("Board detail : " + no));
		map.put("write", no -> System.out.println("Board write : " + no));

		map.get("list").service(111);
		map.get("detail").service(111);
	}

}