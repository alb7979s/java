/*
 * 생성자
 * - 오버로딩 가능
 * - 반환형이 없다
 * - 클래스와 이름이 같아야함
 * - this(), super()
 */
package part01.constructor;

class Student { 
	private String name;
	private int age;
	public void Student(String name, int age) { 	//생성자 아님
		this.name = name;
		this.age = age;
	}
}

class Animal {
	String name;
	Animal(){}
	
	Animal(String name) {
		this.name = name;
	}
}
class Cat extends Animal {
	Cat(String name){
//		super(); 가 자동 추가됨, 근데 부모보면 문자열 받는거 밖에 없으니 Animal() {} 부모에 생성해주던지, 여기서 직접 super(name)해주던지
		this.name = name;
	}
}

public class Test01 {
	public static void main(String[] args) throws Exception {
//		Student s1 = new Student("홍길동", 22);
		Animal a = new Cat("야옹이");
		System.out.println(a.name);
	}
}
