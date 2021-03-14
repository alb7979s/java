import java.util.List;

public interface IProductMgr {

	void add(Product product);

	List<Product> list();

	Product list(int num);

	List<Product> list(String name);

	List<Product> searchTV();

	List<Product> searchTV(int inch);

	List<Product> searchRef();

	// 특정 용량(L) 이상 검색
	List<Product> searchRef(int capacity);

	boolean change(int num, int price);

	boolean remove(int num);

	int totalSum();

}