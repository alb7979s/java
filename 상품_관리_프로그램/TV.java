public class TV extends Product {
	private int size;
	private String type;
	public TV() {}
	public TV(int num, String name, int price, int cnt, int size, String type) {
		super(num, name, price, cnt);
		this.size = size;
		this.type = type;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append("\t").append(size).append("\t").append(type);
		return sb.toString();
	}
}
