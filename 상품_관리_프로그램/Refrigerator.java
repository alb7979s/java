public class Refrigerator extends Product {
	private int capacity;
	public Refrigerator() {}
	public Refrigerator(int num, String name, int price, int cnt, int capacity) {
		super(num, name, price, cnt);
		this.capacity = capacity;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append("\t").append(capacity);
		return sb.toString();
	}
}
