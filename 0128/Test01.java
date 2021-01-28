// 주소 비교하는거 equals()로 재정의 
// hash()비교 
// equals()랑 hash()비교랑 같은거라 생각했음 좋겠대유
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Student{
	String no;
	String name;
	Student(String no, String name){
		this.no = no;
		this.name = name;
	}
	// equals() 재정의, 이거하고 다시 실행하면 찾음
	public boolean equals(Object o) {		
		// 기준 학생의 학번과 비교 대상 학생의 학번이 같으면 true
		if(o instanceof Student) {
			Student other = (Student)o;
			// 문자열 비교니까 얘가 갖고있는 equals()로 비교
			if(this.no.equals(other.no)) return true; 
		}
		return false;
	}
	// 근데 이럴일 웹에선 거의 없음 그냥 동작원리 알라고
	public int hashCode() {
		return this.no.hashCode() * 31 + this.name.hashCode() * 31;	//우연히 같은 해시코드값 들어오는 확률 줄여주려고 그냥 소수 곱해준거
	}
}
public class Test01 {
	public static void main(String[] args) {
		
		List<Student> list = new ArrayList<>();
		list.add(new Student("std1001", "홍길동"));
		list.add(new Student("std1002", "김길동"));
		
		int index = list.indexOf(new Student("std1002", "홍길동"));
		System.out.println(index); 		// -1임. 주소를 비교 하기 때문에!! 못찾음!, 그래서 위에 equals() 오버라이딩 해줌
//		int index = list.indexOf(new Random());	// 객체를 받는거니 이렇게 올 수도 있잖아 그래서 위에 (Student)o 해주려면 Student여야함, 그래서 instanceof 사용해줌

		
		Map<Student, String> map = new HashMap<>();
		map.put(new Student("std1001", "홍길동"), "홍길동");
		map.put(new Student("std1002", "김길동"), "김길동");
		System.out.println(map);
		
		map.remove(new Student("std1001", "홍길동"));
		System.out.println(map); 				// 안지워짐! key가 같은거라 판단 안함 즉, 위에 만든 equals()만으로 객체를 못찾았다!! 해시는 equals() + hashCode 값도 같아야함, 그래서 위에 hashCode()추가
	}
}
