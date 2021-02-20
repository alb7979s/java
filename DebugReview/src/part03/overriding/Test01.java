/*
 * 오버라이딩 규칙
 * 
 * - 메서드 이름, 매개변수, 리턴타입이 부모의 메서드의 선언된 부분과 동일
 * - 접근제한자: 부모의 접근제한자 보다 좁으면 안된다.
 * - 예외: 부모의 예외보다 넓으면 안된다.
 * 
 * (public - protected - default - private)
 */
package part03.overriding;
class Parent {
	public void call1() throws NullPointerException {}
}

class Child extends Parent {
//	void call1() {}	//접근 범위가 좁아져서 안됨
//	public int call1() {return 1;}	//이름, 매개변수 같으면 오버라이딩인데 반환타입 값이 다르면 규칙 어긋남
//	public void call1() throws Exception {}	// 부모보다 예외 넓어짐 불가능
	public void call1(int i) {}		//이건 오버로딩
}
public class Test01 {

}
