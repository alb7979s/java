import java.io.FileNotFoundException;
import java.io.FileReader;

//자식이 예외 부모보다 넓어지면 안됨!!(접근제한자랑 반대) 꼭 기억하세요~
//class Parent {
//	void call() throws RuntimeException;{
//		
//	}
//}
//class Child extends Parent{
//	public call() throws Exception;{	
//		
//	}
//}
public class Test03 {
	public static void main(String[] args) {
		
		
		//checked Exception
		try {
		// Unhandled exception type FileNotFoundException 예외 처리 안돼서 에러남
		FileReader fr = new FileReader("a.txt");
		} catch(FileNotFoundException e) {
			System.out.println("파일이 존재하지 않아서 예외 발생");
			// 예외 좀 더 상세하게 뽑아낼 때
			e.printStackTrace();
		}
		
		//unchecked Exception
		try {
			// runtimeException은 컴파일 시점에 모르네?! 
			// ArithmeticException
			System.out.println(1 / 0);
			String s = null;
			// NullPointerException
			s.length();
		} catch(ArithmeticException e) {
			
		} catch(NullPointerException e) {
			// 위처럼 하면 catch 너무 많아지니까 그냥 부모인 Exception으로 받아버릴수도 있음
		} catch( Exception e) {		// 근데 얘가 쟤네 위로가면 또 에러남 그래서 부모가 자식들 아래 위치하게끔 해줘야함
			
		}
	}
	
}
