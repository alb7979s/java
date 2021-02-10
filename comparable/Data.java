public class Data implements Comparable<Data> {
	private String title;
	private String name;
	private int year;
	private int order = 1;

	public void setOrder(int order) {
		// 초기 => 오름차순, -1로 바꾸면 내림차순
		this.order = order;
	}
	public Data(String title, String name, int year) {
		this.title = title;
		this.name = name;
		this.year = year;
	}
	public String toString() {
		return title + "\t" + name + "\t" + year; 
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	@Override
	public int compareTo(Data o) {
		// TODO Auto-generated method stub
		System.out.println("Data-compareTo");
		// this 내 데이터, o 비교 대상 데이터 (음수 => 앞, 양수 => 뒤로 오게끔)
		return this.getYear() - o.getYear() * order;
	}
}