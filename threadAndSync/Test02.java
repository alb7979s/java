package threadAndSync;

public class Test02 {
	public static void main(String[] args) throws Exception {
		System.out.println(1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(2);
	}
}
