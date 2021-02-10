import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// 이렇게 만들수도 있는데, 아래 익명클래스로 만들어줄거
/*
class MyComp implements Comparator<Data> {
	@Override
	public int compare(Data o1, Data o2) {
		// "a".compareTo("b"); 문자열 자체는 compareTo 정의되어 있어서 알아서 비교해줌, 이 예시로는 a, b 니까 -1 줌
		if( o1.getYear() == o2.getYear()) {
			return o1.getTitle().compareTo(o2.getTitle());
		}
		return o1.getYear() - o2.getYear();
//		return o1.getTitle().compareTo(o2.getTitle());
	}
}
*/
public class DataTest {
	public static void main(String[] args) throws Exception {
		Data d1 = new Data("스위트홈", "이진욱", 2020);
		Data d2 = new Data("스카이캐슬", "염정아", 2012);
		Data d3 = new Data("겁쟁이패달", "오노다", 2012);
		Data d4 = new Data("꽃보다남자", "구준표", 2014);

		Data[] arr = {d1, d2, d3, d4};

		// 여기서 comparable 에러나서 Data에 implements 해줘서 해결함
//		Arrays.sort(arr);

		// comparator, 이거 Data.java에 implements랑 아래 comparableTo @override 지워줘도 에러 안남! comparable 사용 안하니까!
//		Arrays.sort(arr, new MyComp());	//정렬의 기준으로 Mycomp()를 줌

		// 익명 내부 클래스로 적용 해보면
		Arrays.sort(arr, new Comparator<Data>() {
			@Override
			public int compare(Data o1, Data o2) {
				if( o1.getYear() == o2.getYear()) {
					return o1.getTitle().compareTo(o2.getTitle());
				}
				return o1.getYear() - o2.getYear();
			}
		});
		// 자바 1.8부터 람다식 지원해줌, 그거 적용해보면  ::: Front Javascript -> 화살표 함수랑 똑같음(=> 모양만 이럼)
		Arrays.sort(arr, (o1, o2) -> o1.getYear() == o2.getYear() ? o1.getTitle().compareTo(o2.getTitle()) : o1.getYear() - o2.getYear());


		System.out.println(Arrays.toString(arr));

		List<Data> list = (List<Data>)Arrays.asList(d1, d2, d3, d4);
		Collections.sort(list, (o1, o2) -> o1.getYear() - o2.getYear());
		System.out.println(list);
	}
}