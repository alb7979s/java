import java.io.FileReader;
import java.io.FileWriter;

public class Test05 {
	public static void main(String[] args) {
		try {
			// test03.txt 파일의 내용을 읽어서 test05.txt에 복사
			FileReader fr = new FileReader("test03.txt");	//빨대 꽂음
			FileWriter fw = new FileWriter("test05.txt", true);	//두번째 인자에 true값 주게 되면 덮어쓰기가 아니라 append 됨!!
			while (true) {
				int ch = fr.read();	//read()마우스 올려보면 파일 끝 가면 -1 리턴해준대
				if(ch == -1) break;
				fw.write(ch);
			}
			fr.close();
			fw.close();
			System.out.println("파일 복사 성공!");
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
