
import java.util.Scanner;

public class MyStackTest {
	Scanner sc = new Scanner(System.in);
	MyStack<String> stack = new MyStack<>();
	
	private int menu() {
		System.out.println("메뉴 선택");
		System.out.println("-------------------");
		System.out.println("1. 입력 ");
		System.out.println("2. 꺼내기(삭제) ");
		System.out.println("3. 꺼내기(유지) ");
		System.out.println("4. 전체보기 ");
		System.out.println("0. 종료");
		System.out.println("-------------------");
		System.out.println("선택 : ");
		return sc.nextInt();
	}
	private void addData() {
		System.out.println("입력 값 : ");
		stack.push(sc.next());
	}
	private void getDataAndRemove() {
		try {
			System.out.println("가져온 값 : " + stack.pop());
		} catch(EmptyException e) {
			System.out.println("에러 발생");
			e.printStackTrace();
		}
	}
	private void getData() {
		try {
			System.out.println("가져온 값 : " + stack.peek());
		} catch(EmptyException e) {
			System.out.println("에러 발생");
			e.printStackTrace();
		}
	}
	private void showData() {
		//toString 만들어놨으니 그냥 stack
		System.out.println(stack);
	}
	private void start() {
		while(true) {
			switch (menu()) {
			case 1: addData(); break;
			case 2: getDataAndRemove(); break;
			case 3: getData(); break;
			case 4: showData(); break;
			case 0:	System.exit(0);
			}
		}
	}
	public static void main(String[] args) {
		new MyStackTest().start();
	}
}
