import java.util.ArrayList;
import java.util.List;

class DuplicateException extends Exception {
	private static final long serialVersionUID = 1L;

	DuplicateException() {
		super("이미 존재하는 상품번호 입니다.");
	}

	DuplicateException(String msg) {
		super(msg);
	}
}

class CodeNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	CodeNotFoundException() {
		super("해당 상품 번호가 존재하지 않습니다.");
	}

	CodeNotFoundException(String msg) {
		super(msg);
	}
}

class ProductNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	ProductNotFoundException() {
		super("상품이 존재하지 않습니다.");
	}

	ProductNotFoundException(String msg) {
		super(msg);
	}
}

public class ProductMgrImpl implements IProductMgr {

	List<Product> products = new ArrayList<>();

	private ProductMgrImpl() {
	}

	public static IProductMgr instance;

	public static IProductMgr getInstance() {
		if (instance == null)
			instance = new ProductMgrImpl();
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssafy.hw07.IProductMgr#add(com.ssafy.hw07.Product)
	 */
	@Override
	public void add(Product product) {
		try {
			for (Product p : products) {
				if (p.getNum() == product.getNum()) {
					throw new DuplicateException();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		products.add(product);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssafy.hw07.IProductMgr#list()
	 */
	@Override
	public List<Product> list() {
		return products;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssafy.hw07.IProductMgr#list(int)
	 */
	@Override
	public Product list(int num) {
		try {
			for (int i = 0; i < products.size(); i++) {
				if (products.get(i).getNum() == num)
					return products.get(i);
			}
			throw new CodeNotFoundException();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssafy.hw07.IProductMgr#list(java.lang.String)
	 */
	@Override
	public List<Product> list(String name) {
		List<Product> searchList = new ArrayList<>();
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getName().contains(name))
				searchList.add(products.get(i));
		}
		return searchList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssafy.hw07.IProductMgr#searchTV()
	 */
	@Override
	public List<Product> searchTV() {
		List<Product> searchList = new ArrayList<>();
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i) instanceof TV)
				searchList.add(products.get(i));
		}
		return searchList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssafy.hw07.IProductMgr#searchTV(int)
	 */
	@SuppressWarnings("finally")
	@Override
	public List<Product> searchTV(int inch) {
		List<Product> searchList = new ArrayList<>();
		try {
			for (int i = 0; i < products.size(); i++) {
				if (products.get(i) instanceof TV) {
					if (((TV) products.get(i)).getSize() >= inch)
						searchList.add(products.get(i));
				}
			}
			if (searchList.size() == 0)
				throw new ProductNotFoundException("50inch 이상의 TV가 존재하지 않습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return searchList;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssafy.hw07.IProductMgr#searchRef()
	 */
	@Override
	public List<Product> searchRef() {
		List<Product> searchList = new ArrayList<>();
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i) instanceof Refrigerator)
				searchList.add(products.get(i));
		}
		return searchList;
	}

	// 특정 용량(L) 이상 검색
	@SuppressWarnings("finally")
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssafy.hw07.IProductMgr#searchRef(int)
	 */
	@Override
	public List<Product> searchRef(int capacity) {
		List<Product> searchList = new ArrayList<>();
		try {
			for (int i = 0; i < products.size(); i++) {
				if (products.get(i) instanceof Refrigerator) {
					if (((Refrigerator) products.get(i)).getCapacity() >= capacity)
						searchList.add(products.get(i));
				}
			}
			if (searchList.size() == 0) {
				throw new ProductNotFoundException("400L 이상의 냉장고가 존재하지 않습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return searchList;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssafy.hw07.IProductMgr#change(int, int)
	 */
	@Override
	public boolean change(int num, int price) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getNum() == num) {
				products.get(i).setPrice(price);
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssafy.hw07.IProductMgr#remove(int)
	 */
	@Override
	public boolean remove(int num) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getNum() == num) {
				products.remove(i);
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssafy.hw07.IProductMgr#totalSum()
	 */
	@Override
	public int totalSum() {
		int total = 0;
		for (int i = 0; i < products.size(); i++) {
			total += products.get(i).getPrice();
		}
		return total;
	}
}
