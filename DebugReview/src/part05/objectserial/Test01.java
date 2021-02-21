/*
 * 객체 직렬화
 * - 메모리의 객체 정보를 바이트 단위로 쪼개는 것
 * 
 */

package part05.objectserial;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Serializable => 마커 인터페이스 (마킹만함)
class User implements Serializable{
//	이렇게 버전정보 주고 클래스가 나중에 String email 이런식으로 바껴도 같은 버전정보면 오류 안나고 바꿀 수 있는 만큼만 바꿔줌
	private static final long serialVersionUID = 1L;		//버전정보 안해주면 해시코드 값으로 비교해줌
	String name;
	transient String password;		// transient 하면 이 값 직렬화 안함
	User(String name, String password){
		this.name = name;
		this.password = password;
	}
}
class UserMng {
	List<User> list = new ArrayList<>();
	public void add(User u) {
		list.add(u);
	}
	// serialize
	public void save() throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.dat"));
		oos.writeObject(list);
		oos.close();
	}
	// deserialize
	@SuppressWarnings("unchecked")
	public void load() throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.dat"));
		list = (List<User>)ois.readObject();
		for(User u: list) System.out.println(u.name + " " + u.password);
		ois.close();
	}
}

public class Test01 {
	public static void main(String[] args) throws Exception {
		UserMng mng = new UserMng();
		mng.add(new User("홍", "홍홍홍"));
		mng.add(new User("강", "강강강"));
		try {
			mng.save();
			System.out.println("저장 완료");
			mng.load();
		} catch(Exception e) {
			System.out.println("에러 발생");
			e.printStackTrace();
		}
	}
}
