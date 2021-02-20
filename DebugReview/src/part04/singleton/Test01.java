/*
 * 싱글톤 패턴
 * - 객체를 하나만 만들어서 사용
 * - 생성자를 private로
 */
package part04.singleton;

class Board{}
interface BoardMng {
	void add(Board b);
}
class BoardMngImpl implements BoardMng {
	private BoardMngImpl() {} // 요롷게
	private static BoardMngImpl instance = getInstance();
	// 클래스 로딩할 때 만드는게 아니라 실제 getInstance 요청 때 만드는 방법. lazy 방식이래 개있어보인다..
	// 근데 이건 다같이 동시에 접근하려할 때 문제가 된대 그때 쓰는 키워드가 synchronized
	public synchronized static BoardMngImpl getInstance() {
		if(instance == null) instance = new BoardMngImpl();
		return instance;
	}
	@Override
	public void add(Board b) {}
	
}
public class Test01 {
	public static void main(String[] args) throws Exception {
//		BoardMng mng = new BoardMngImpl();
	}
}
