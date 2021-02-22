package threadAndSync;

//동기화 메서드로 처리(예제 cell은 객체단위로 처리)
//공유 객체
class Sync {
	// synchronized 붙이기 전에는 섞여 나옴
	synchronized void a() {
		for (int i = 0; i < 30; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("a");
		}
	}

	synchronized void b() {
		for (int i = 0; i < 30; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("b");
		}
	}

	synchronized void c() {
		for (int i = 0; i < 30; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("c");
		}
	}
}

//쓰레드 객체
class SyncThread extends Thread {
	Sync s;
	int num;

	SyncThread(int num, Sync s) {
		this.num = num;
		this.s = s;
	}

	public void run() {
		switch (num) {
		case 1:
			s.a();
			break;
		case 2:
			s.b();
			break;
		case 3:
			s.c();
			break;
		}
	}

}

public class Test04 {
	public static void main(String[] args) {
		Sync s = new Sync(); // 동기화 사용 객체
		SyncThread s1 = new SyncThread(1, s);
		SyncThread s2 = new SyncThread(2, s);
		SyncThread s3 = new SyncThread(3, s);
		s1.start();
		s2.start();
		s3.start();
	}
}
