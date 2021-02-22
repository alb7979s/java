package threadAndSync;

class Test extends Thread {
	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class Test03 {
	public static void main(String[] args) throws Exception {

		System.out.println("메인 스레드 시작");
		Test st = new Test();
		System.out.println(st.getState().name()); // new

		st.start();
		System.out.println(st.getState().name()); // runnable

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(st.getState().name()); // timed_waiting

		// st 스레드가 종료된 후의 상태 확인
		try {
			st.join(); // st 종료될때까지 대기한다는 뜻
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(st.getState().name()); // terminated
	}

}