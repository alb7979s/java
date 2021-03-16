import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		HashMap<String, Integer> map = new HashMap<>();
		Comparator<String> comparator = (o1, o2) -> o1.compareTo(o2);
		TreeMap<String, Integer> map = new TreeMap<>(comparator);
		String name = "";
		int total = 0;
		while((name = br.readLine()) != null) {
			if(name.length()==0) break;		// 예제 테스트 하려고 넣은 구문 
			total++;
			if(map.containsKey(name)){
				map.put(name, map.get(name)+1);
			}
			else {
				map.put(name, 1);
			}
		}
		
		for(Map.Entry<String, Integer> entry: map.entrySet()) {
			// integer / integer 인데 float으로 출력해서 에러남..
			System.out.printf("%s %.4f\n", entry.getKey(), (float)((double)(entry.getValue() * 100) / total));
		}
	}
}
