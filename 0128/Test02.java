// CompareTo

import java.util.Arrays;

class SsafyArea implements Comparable<SsafyArea>{
	int cnt;
	String city;
	SsafyArea(int cnt, String city){
		this.cnt = cnt;
		this.city = city;
	}
	@Override
	public int compareTo(SsafyArea o) {
		//cnt로 정렬할거
		
		// if 문은 cnt 같을때 city로 정렬 시키는거
		if (this.cnt == o.cnt) {
			this.city.compareTo(o.city);
		}
		return this.cnt - o.cnt; 			//기준 - 비교대상, 만약 50 - 250 이면 결과 -200으로 기준이 앞에 오게됨 
//		return (this.cnt - o.cnt) * -1; 	//반대로 정렬 
		
	}
}
public class Test02 {
	public static void main(String[] args) {
		SsafyArea a1 = new SsafyArea(250, "서울");
		SsafyArea a2 = new SsafyArea(50, "구미");
		SsafyArea a3 = new SsafyArea(150, "대전");
		SsafyArea a4 = new SsafyArea(50, "광주");
		
		SsafyArea[] arr = {a1, a2, a3, a4};
		
		//Arrays.sort()로 정렬할껀데, 두가지 타입이니까 에러남! SsafyArea -> Comparable 형변환 에러, Comparable 들어가면 CompareTo() 있음
		Arrays.sort(arr);
		
		
		// compareTo test, 출력해보면 1(기준 > 비교대상), 0(기준 == 비교), -1(기준 < 비교대상) 
		// 즉, 음수(기준이 비교대상보다 앞쪽), 0 (그대로), 양수(기준이 비교대상보다 뒤쪽)
//		String data = "b";
//		System.out.println(data.compareTo("a"));
//		System.out.println(data.compareTo("b"));
//		System.out.println(data.compareTo("c"));
	}
}
