// 직렬화(Serialize), ObjectOutputStream, transient
// 역직렬화(deserialize), ObjectInputStream
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


//Serializable 사용하고 재정의 안해도 에러 안남 => 이렇게 메서드 없는 인터페이스를 marker 인터페이스 라고함
class User implements Serializable {	
	// 이게 버전 설정하는거
//	private static final long serialVersionUID = 1L;
	
	private String id;
	// transient 하면 이 부분은 직렬화 필드에서 제외!! 그럼 password는 안넘김
	private transient String password;
	//만약 이런식으로 구조 바꼈을 때, 다시해보면 에러남, serialize 버전 해시값 달라서, 그래서 원해서 바꾼거라고 표시할 수 있음 serialize 버전을 설정해주면 됨 그렇게하면 실행시 버전 동일하면 같은 필드값만 설정해줌
	/*
	private String email;  
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
*/
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	User(){}	//default 생성자
	User (String id, String password){
		this.id = id;
		this.password = password;
	}
}
public class Test08 {
	public static void main(String[] args) {
		//read()에서 예외 던졌으니 여기서 처리해줘야함
		try {
//			write();
			read();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static void read() throws Exception {
		//deSerialize, 근데 사실 용량이 커서 이렇게 잘 안하고 json 많이 활용한대
		FileInputStream fis = new FileInputStream("user.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		@SuppressWarnings("unchecked")
		ArrayList<User> list = (ArrayList<User>)ois.readObject();	//읽을 때 기본적으로 Object로 읽음, 캐스팅 해줘야함
		System.out.println(list.size());
		for(User user: list) System.out.println(user.getId() + ", " + user.getPassword());
		ois.close();
		fis.close();
	}
	// 범위 선택하고, 우클릭, refactor - Extract Method 하면 이렇게 뽑아짐 개쩐다..
	private static void write() {
		User u1 = new User("u1", "1234");
		User u2 = new User("u2", "5678");
		ArrayList<User> list = new ArrayList<>();		//그냥 하면 User가 직렬화 안된다고 에러남, 그래서 class에 Serializable 인터페이스 받아와야함 
		list.add(u1);
		list.add(u2);
		try {
			FileOutputStream fos = new FileOutputStream("user.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			oos.close();
			fos.close();
			System.out.println("객체 저장 성공!");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
