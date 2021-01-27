//Object였던 부분 E로 (generic으로 개선!)
interface List<E> {
	
	Iterator<E> iterator();
	
	// 리스트에 데이터 추가
	void add(E o);

	// 특정한 위치에 끼워넣기
	void add(int index, E o);

	// 삭제, 삭제 하면서 삭제된 데이터 반환
	E remove(int index);

	// 리스트 맥주있니?
	boolean isEmpty();

	// 리스트 크기 반환
	int size();

	// 리스트의 특정 위치값 가져오기
	E get(int index);

	// 리스트의 전체 데이터 삭제하기
	void clear();

	String toString();

}