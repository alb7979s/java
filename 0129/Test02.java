import java.util.Scanner;

class JuminNumberInvalidException extends Exception {	//RuntimeException 했음 아닌데,  Exception만 하면 컴파일 때 에러남, try로 처리해줘야함 
	JuminNumberInvalidException(){
		super("주민번호는 13자리 입니다.");		// Exception(String msg)가 기본 형태라서 이렇게 생성자에 부모 String 값 넣어줌
		//똑같은 코드가 반복되네? this("주민번호는 13자리 입니다."); 로 바꿔주면 더 좋음
	}
	// msg가 오는 값에 따라 출력해주도록 오버로딩 해준거.
	JuminNumberInvalidException(String msg){
		super(msg);		
	}
	
}
public class Test02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("주민번호 입력 : ");
		String juminNo = sc.nextLine();
		try {
//			System.out.println( 1 / 0);	// 이렇게 하면 / by zero 출력됨 우리가 만든것도 예외 메시지를 주자!(위 클래스 바디부분)
			if (juminNo.length() != 13) {
				// 예외 강제 발생
				throw new JuminNumberInvalidException();		//만나는 순간 종료됨, 정상종료 하거든 try - catch 쓰던가, throws로 간접처리 해주던가 
			}
			if(juminNo.charAt(6) == '9' || juminNo.charAt(6) == '0')
				throw new JuminNumberInvalidException("유요하지 않은 주민번호 입니다.");		// 이래버리면 또 주민번호 13자리 출력함, 이러면 안되잖아요 따라서 위 클래스 부분에 저렇게 fixed해버리면 좋지 않음. 멘트의 결정을 호출하는 쪽에서 하도록 해줄거
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			sc.close();
		}
//		System.out.println("정상 종료");		//이거 이클립스에서 실행하면 실행할때마다 위치 다른데, 스레드를 이용한 처리 방식때문에 그렇대
	}
}
