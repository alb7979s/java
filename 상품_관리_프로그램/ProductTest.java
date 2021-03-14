import java.util.List;
import java.util.Scanner;

public class ProductTest {
	// num, name, price, cnt, size(TV), type(TV), capacity(냉장고)
	IProductMgr productMgr = ProductMgrImpl.getInstance();
	String stars = "********************";
	Scanner sc = new Scanner(System.in);

	String getString(String msg) {
		System.out.println(msg);
		return sc.nextLine();
	}

	int getInt(String msg) {
		return Integer.parseInt(getString(msg));
	}

	void printProducts(List<Product> products) {
		if (products.isEmpty())
			System.out.println("상품이 존재하지 않습니다. ");
		else {
			System.out.println("번호" + "\t" + "이름" + "\t" + "가격" + "\t" + "재고" + "\t" + "크기|용량" + "\t" + "타입");
			for (Product product : products)
				System.out.println(product);
		}
	}

	void add() {
		int select = getInt("TV 추가: 1번, 냉장고 추가: 2번");
		// TV 추가
		if (select == 1)
			productMgr.add(new TV(getInt("번호 입력 : "), getString("이름 입력 : "), getInt("가격 입력 : "), getInt("개수 입력 : "),
					getInt("크기 입력 : "), getString("타입 입력 : ")));
		if (select == 2)
			productMgr.add(new Refrigerator(getInt("번호 입력 : "), getString("이름 입력 : "), getInt("가격 입력 : "),
					getInt("개수 입력 : "), getInt("용량 입력 : ")));
	}

	void searchList() {
		printProducts(productMgr.list());
	}

	void searchNum() {
		Product product = productMgr.list(getInt("검색할 상품 번호 : "));
		if (product == null)
			System.out.println("상품이 존재하지 않습니다. ");
		else {
			System.out.println("번호" + "\t" + "이름" + "\t" + "가격" + "\t" + "재고" + "\t" + "크기|용량" + "\t" + "타입");
			System.out.println(product);
		}
	}

	void searchName() {
		printProducts(productMgr.list(getString("검색할 상품 이름 : ")));
	}

	void searchTV() {
		printProducts(productMgr.searchTV());
	}

	void searchRef() {
		printProducts(productMgr.searchRef());
	}

	void searchSizeTV() {
		printProducts(productMgr.searchTV(50));
	}

	void searchSizeRef() {
		printProducts(productMgr.searchRef(400));
	}

	void change() {
		System.out.println(
				productMgr.change(getInt("상품 번호 입력 : "), getInt("변경할 가격 입력 : ")) ? "변경되었습니다." : "번호를 찾지 못했습니다.");
	}

	void remove() {
		System.out.println(productMgr.remove(getInt("삭제할 상품 번호 : ")) ? "삭제되었습니다." : "삭제할 정보가 없습니다.");
	}

	void totalSum() {
		System.out.println("전체 가격 : " + productMgr.totalSum());
	}

	void exit() {
		System.out.println("종료 합니다.");
		System.exit(0);
	}

	String menu() {
		while (true) {
			System.out.println(stars);
			System.out.println("1. 상품추가(저장)");
			System.out.println("2. 전체검색");
			System.out.println("3. 번호검색");
			System.out.println("4. 상품명 검색(부분 검색 가능)");
			System.out.println("5. TV 검색");
			System.out.println("6. 냉장고 검색");
			System.out.println("7. 400L 이상의 냉장고 검색");
			System.out.println("8. 50inch 이상의 TV검색");
			System.out.println("9. 상품 변경");
			System.out.println("10. 번호로 삭제");
			System.out.println("11. 전체 재고 상품 금액");
			System.out.println("0. 종료");
			System.out.println(stars);
			return sc.nextLine();
		}
	}

	void execute() {
		while (true) {
			switch (menu()) {
			case "1":
				add();
				break;
			case "2":
				searchList();
				break;
			case "3":
				searchNum();
				break;
			case "4":
				searchName();
				break;
			case "5":
				searchTV();
				break;
			case "6":
				searchRef();
				break;
			case "7":
				searchSizeRef();
				break;
			case "8":
				searchSizeTV();
				break;
			case "9":
				change();
				break;
			case "10":
				remove();
				break;
			case "11":
				totalSum();
				break;
			case "0":
				exit();
			}
		}
	}

	public static void main(String[] args) {
		new ProductTest().execute();
	}
}
