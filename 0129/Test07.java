import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Test07 {
	public static void main(String[] args) {
		try {
			//Buffered로 디스크 쓰고 읽는 시간 단축!(디스크 보다 메모리 단에서 하는게 훨씬 빠르니까) 
			FileReader fr = new FileReader("test03.txt");	//빨대 꽂음
			FileWriter fw = new FileWriter("test07.txt");
			
			
			BufferedReader br = new BufferedReader(fr);	//여기 들어가보면 사이즈 8192(8K)인데 바꿔줄수도 있음, 그냥 두번째 인자에 16 * 1024 이런식으로 주면 됨, 작업하는 파일의 크기에 따라 조절해주면 됨(컴터 성능도 고려)
			BufferedWriter bw = new BufferedWriter(fw);
			while (true) {
				/*
				 * 근데 readLine()은 \n도 받아 없애주니까 \n하려면 따로 처리해줘야함, 그래서 readLine()은 보통 복사용으로 안쓰이고 읽어가며 어떤 처리 해주려고 사용함  
				 * String line = br.redLine();
				 * if (line == null) break;			// 줄 단위로 받으면 끝날때 null값 줌
				 * bw.NewLine(); 					// 운영체제에 맞게 줄넘김 삽입
				 */
				int ch = br.read();
				if(ch == -1) break;
				bw.write(ch);
			}
			/*
			// fr를 br이 감싼 형탠데 fr먼저 close하면 br을 close할때 문제가 발생함
			// close안해줬을때, 만약 5개씩 싣는다고 하면 마지막 4개 남아있으면 안올라감, 그거 올려주도록 알려주는게(버퍼 비우도록 처리해주는게) close(), 그냥 bw.flush()해도 되긴함
			br.close();
			bw.close();
			fr.close();
			fw.close();
			*/
			System.out.println("파일 복사 성공!");
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
