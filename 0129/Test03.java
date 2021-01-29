import java.io.FileWriter;

// I/O
public class Test03 {
	public static void main(String[] args) {
		try {
//			FileOutputStream fos = new FileOutputStream("test03.txt");	
			FileWriter fos = new FileWriter("test03.txt");		//이러면 한글 잘 나옴, 즉 한글같은 1byte가 넘는 것들을 불러올땐 char단위인 Writer로
			fos.write(100);
			fos.write('d');
			fos.write('한');			//한글은 1byte로 표현이 안돼서 안나옴, 영어는 가능
			fos.close();			//일단 임시로 close()그냥 씀
			System.out.println("파일 생성 완료");		//프로젝트 폴더에 생김
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
