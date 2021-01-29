import java.io.FileReader;

public class Test04 {
	public static void main(String[] args) {
		try {
			// test03.txt 파일의 내용을 읽어서 화면에 출력
//			FileInputStream fis = new FileInputStream("test03.txt");	//빨대 꽂음
			FileReader fis = new FileReader("test03.txt");	//빨대 꽂음
//			{
//			int ch = fis.read();									    //마심
//			System.out.print((char)ch);
//			}
//			{//위에만 하면 한글자만 되니까 두글자 출력해볼라고
//				int ch = fis.read();									  
//				System.out.print((char)ch);
//			}
//			{// InputStream은 여전히 한글 깨짐
//				int ch = fis.read();									  
//				System.out.print((char)ch);
//			}
			//위에 애들 반복문으로 처리 EOF!!
			while (true) {
				int ch = fis.read();	//read()마우스 올려보면 파일 끝 가면 -1 리턴해준대
				if(ch == -1) break;
				System.out.print((char)ch);
			}
			fis.close();
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
