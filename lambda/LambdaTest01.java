package day07;

@FunctionalInterface
interface Sub01 {
	void call(String msg);
}

public class LambdaTest01 {
	// @FunctionalInterface만이 람다 표현식의 대상이 됨(인터페이스 안에 메소드 하나만 정의 되어야 함)
	public static void main(String[] args) throws Exception {
		Sub01 s = new Sub01() {
			public void call(String msg) {
				System.out.println(msg);
			}
		};
		s.call("^^");
		s.call("*^^*");
		s.call("^^*");
		// 위에걸 람다로, 뒤에 new하는 타입 어차피 앞에 타입 와야함, 메소드도 오버라이딩 해줘야하는데 어차피 하나면 그거밖에 올게 없잖아 그래서 생략하고 간편하게 만들어준거
		Sub01 s2 = (String msg) -> {
				System.out.println(msg);
		};	//실행문장이니 ;로 마무리

		// 잘 생각해보면 파라미터 부분도 인터페이스 보고 추론이 가능 것도 생략!
		Sub01 s3 = (msg) -> {
			System.out.println(msg);
		};	

		// 매개변수가 하나일땐 "()" 빼도 됨(여러개면 생략 안됨)
		Sub01 s4 = msg -> {
			System.out.println(msg);
		};

		// 실행문장이 한문장일 경우에는 "{}"도 생략 가능
		Sub01 s5 = msg -> System.out.println(msg);
		s5.call(":)");

		// 하나 더, 아래 Sub02를 받아볼까여? => return type도 생략! (return이 있는 한문장에서 "{}"을 생략할 경우 return 키워드를 빼야함)
		// 반대로, return이 있는 한문장에서 "{}"을 생략하지 않을 경우 return 키워드를 빼면 안됨
		Sub02 s6 = msg -> msg;
		Sub02 s7 = msg -> {return msg; };
		s6.call("s6");
		s7.call("s7");
	}

}
interface Sub02 {
	String call(String msg);
}