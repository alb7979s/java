package day07;
// 기존에 있는 interface 사용해보는 예제

// Runnable => FunctionalInterface
class MyRunnable implements Runnable {
	@Override
	public void run() {
		System.out.println("run 호출");
	}
}
public class LambdaTest02 {
	public static void main(String[] args) throws Exception {
		// 가장 노멀하게는 위에 클래스 정의하고 이렇게 씀
		Thread t = new Thread(new MyRunnable());

		// 익명클래스 이용 
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				System.out.println("run 익명");
			}
		});

		// 람다식 이용
		Thread t3 = new Thread(() -> System.out.println("run 람다"));
	}
}