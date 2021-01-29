import java.io.FileReader;
import java.io.FileWriter;

public class Test06 {
	public static void main(String[] args) {
		try {
			//Buffered로 디스크 쓰고 읽는 시간 단축!(디스크 보다 메모리 단에서 하는게 훨씬 빠르니까) 
			FileReader fr = new FileReader("test03.txt");	//빨대 꽂음
			FileWriter fw = new FileWriter("test06.txt");
			
			char[] buffer = new char[5];
			while (true) {
				int ch = fr.read(buffer);		//이렇게 하면 read() 메서드가 읽은 개수를 뱉어줌
				if(ch == -1) break;
				
				fw.write(buffer, 0, ch);		//0부터 ch개까지 쓰기!, 이렇게 안하면 abcde123de들어갈거임, 배열 기존 저장된 내용 남아있어서!
			}
			fr.close();
			fw.close();
			System.out.println("파일 복사 성공!");
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
