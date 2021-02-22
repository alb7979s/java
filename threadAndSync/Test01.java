package threadAndSync;

class MyThread2 implements Runnable {

	@Override
	public void run() {
		for (int i = 1; i <= 4; i++) {
			for (char c = 'A'; c <= 'Z'; c++) {
				System.out.print(c + "\t");
			}
			System.out.println();
		}
	}
}

class MyThread1 extends Thread {
	public void run() {
		System.out.println(getName());
		for (int i = 1; i <= 100; i++) {
			System.out.print(i + "\t");
		}
		System.out.println();
	}
}

public class Test01 {
	public static void main(String[] args) throws Exception {
		System.out.println(Thread.currentThread().getName());
		Thread t1 = new MyThread1();
		t1.setName("t1쓰레드");
		Runnable r = new MyThread2();
		Thread t2 = new Thread(r);

//		t1.run();
//		r.run();

		t1.start();
//		r.start();	
		t2.start();
	}
}
