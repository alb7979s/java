// 예외처리 실행 순서~
public class Test01 {
	public static void main(String[] args) {
		try {
			System.out.println("try 시작");
			String s = "hello";
//			s = null;						//이거 키면 catch()서 처리 되어서 예외 발생
			System.out.println(s.length());
		}catch(Exception e) {
			System.out.println("예외 발생함");
//			return ;						// 여기서 return 걸리면, finally는 처리하는데, 밖에 있는 "정상종료"는 출력 안됨
//			System.exit(0);					// 이러면 finally도 안됨 그냥 종료
		}finally {
			System.out.println("무조건 실행");	// 예외가 발생하던 안하던 실행
		}
		System.out.println("정상종료");
	}
}
