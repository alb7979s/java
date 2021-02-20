package part02.exception;

class IceFullException extends /*Exception은 컴파일 시점에 에러 잡는거라 안됨*/ RuntimeException {	
	// 에러 메세지 이런식으로 넘겨줬었지~
	IceFullException(String msg){
		super(msg);
	}
}
class Ice {
	
}

class IceMng {
	Ice[] arr = new Ice[2];
	int pos;
	public void add(Ice ice) throws IceFullException {
		// 크기 넘어갈 때 예외 발생시킬거
		if(pos == arr.length) {
			// 사용자 정의 예외 발생
			throw new IceFullException("아이스가 꽉 찼습니다");
		}
		arr[pos++] = ice;
	}
}

public class Test02 {
	public static void main(String[] args) {
		IceMng mng = new IceMng();
		try {
			mng.add(new Ice());
			mng.add(new Ice());
			mng.add(new Ice());
		} catch (IceFullException ife) {		// try - catch 썼는데 오류가 안나? RuntimeException으로 만들어져 있겠구나 생각할 수 있음 
			ife.printStackTrace();
		}
	}
}
