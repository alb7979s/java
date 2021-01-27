import java.util.Arrays;

// public 말고 default로, 여기 패키지에서만 쓰려고
class ArrayList<E> implements List<E> {
	private int MAX_SIZE = 2;
	private int pos = 0;
	// 배열엔 안됨!! 리플렉션 이용해서 바꿔줘야함 E elements = new E[MAX_SIZE];는 에러!
	private Object[] elements = new Object[MAX_SIZE];
	
	//ArrayList 전용이라 내부클래스 하나 만들거임
	class MyIterator implements Iterator<E> {
		int index = 0;
		@Override
		public boolean hasNext() {
			return index < pos;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			return (E)elements[index++];
		}
		
	}
	//오버라이딩 할 때 인터페이스 재정의니까 public 줘야함
	public Iterator<E> iterator() {
		return new MyIterator();
	}

	// 리스트에 데이터 추가
	/* (non-Javadoc)
	 * @see com.ssafy.day06.List#add(java.lang.Object)
	 */
	@Override
	public void add(E o) {
		// 아래랑 기능이 비슷하네?! 묶어!
		/*
		 * if (pos == elements.length) { elements = Arrays.copyOf(elements, pos * 2); }
		 * elements[pos++] = o;
		 */
		add(pos, o);
	}

	// 특정한 위치에 끼워넣기
	/* (non-Javadoc)
	 * @see com.ssafy.day06.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, E o) {
		if (pos == elements.length) {
			elements = Arrays.copyOf(elements, pos * 2);
		}
		int moveCnt = pos - index;
		if (moveCnt > 0)
			System.arraycopy(elements, index, elements, index + 1, moveCnt);
		elements[index] = o;
		pos++;
	}

	// 삭제, 삭제 하면서 삭제된 데이터 반환
	/* (non-Javadoc)
	 * @see com.ssafy.day06.List#remove(int)
	 */
	@SuppressWarnings("unchecked")		//return (E)o 내가 원해서 쓴거니까 노란줄 주지마요
	@Override
	public E remove(int index) {
		Object o = elements[index];
		int moveCnt = pos - index - 1;
		if (moveCnt > 0) {
			System.arraycopy(elements, index + 1, elements, index, moveCnt);
		}
		elements[--pos] = null;
		return (E)o;		//object를 E에 들어온 타입으로 바꿔줘야함
	}

	// 리스트 맥주있니?
	/* (non-Javadoc)
	 * @see com.ssafy.day06.List#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return pos == 0;
	}

	// 리스트 크기 반환
	/* (non-Javadoc)
	 * @see com.ssafy.day06.List#size()
	 */
	@Override
	public int size() {
		return pos;
	}

	// 리스트의 특정 위치값 가져오기
	/* (non-Javadoc)
	 * @see com.ssafy.day06.List#get(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		return (E)elements[index];
	}

	// 리스트의 전체 데이터 삭제하기
	/* (non-Javadoc)
	 * @see com.ssafy.day06.List#clear()
	 */
	@Override
	public void clear() {
		for (int i = 0; i < pos; i++) {
			elements[i] = null;
		}
		pos = 0;
	}
	
	/* (non-Javadoc)
	 * @see com.ssafy.day06.List#toString()
	 */
	@Override
	public String toString() {
		// ArrayList[apple, banana, c]
		if(isEmpty()) return "ArrayList[]";
		String res = "ArrayList[" + elements[0];
		for(int i=1; i < pos; i++) {
			res += ", " + elements[i];
		}
		res += "]";
		return res;
	}
	//generic 적용하고 사용하려면 요렇게
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("banana");
		
		// API-Iterator(순환자), 객체 기준으로! 메서드: hasNext() 데이터가 있는지 확인, next() 데이터 꺼내고 위치 하나 이동
		Iterator<String> ite = list.iterator();
		while(ite.hasNext()){
			String data = ite.next();
			System.out.println(data);
			}
	}
	/*
	public static void main(String[] args) {
		List list = new ArrayList();	 	// 인터페이스 다 만들었으면 그래서 이런식으로 씀!!
		list = new LinkedList();			// 얘도 가능 
		
		// generic 기법 쓰면
//		List<String> list2 = new ArrayList<>(); // 이런식으로 가능
	}
	*/
	
	/*
	// test용 main
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		
		list.add("apple");
		list.add("banana");
		list.add("c");
		
		System.out.println(list); 		// 이러면 주소값 나올거임, 보통 자체 데이터 갖고 있는 클래스들은 객체 찍었을때 데이터 출력되도록 만들어줌
		
		System.out.println(list.isEmpty());
		System.out.println(list.size());
		// 리스트의 데이터 보여주기
		for (int i = 0; i < list.size(); i++) {
			// 길이값 나타내고 싶어
//			System.out.println(list.get(i).length); 		//이러고 싶은데, Object는 length 없음
			String s = (String)list.get(i);					//일단 이렇게 형변환 해서 길이 출력, 나중에 generic 기법 쓰면!! 됨!
			System.out.println(s.length());
			System.out.println(list.get(i));		// 이건 toString이 String 클래스에서 재정의 되어있기 때문에 됨
		}
		
		String data = (String)list.remove(0);
		System.out.println(data);
		
		// 전체 데이터 삭제
		list.clear();
		System.out.println(list.size());
	}
	 */
}
