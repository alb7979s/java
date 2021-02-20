package part06.comp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Food implements Comparable<Food> {
	String name;
	int price;
	public Food(String name, int price) {
		this.name = name;
		this.price = price;
	}
	@Override
	public int compareTo(Food o) {
		return this.price - o.price;
	}
}
public class Test01 {
	public static void main(String[] args) throws Exception {
		List<Food> list = new ArrayList<>();
		list.add(new Food("떡볶이", 2000));
		list.add(new Food("어묵", 1200));
		list.add(new Food("순대", 2000));
		list.add(new Food("튀김", 2000));
		
//		Collections.sort(list);
//		Collections.sort(list, new Comparator<Food>(){
//			public int compare(Food o1, Food o2) {
//				return o1.name.compareTo(o2.name);
//			}
//		});
		
		// 람다식으로도 가능
		Collections.sort(list, (o1, o2) -> o1.name.compareTo(o2.name));
		
		for(Food f: list) {
			System.out.println(f.name + " " + f.price);
		}
	}
}
