import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
// 자동으로 닫아주는거!! try resource
class MyClose implements AutoCloseable{

	// 이거 보니까 다른 애들은 close()가 있는 애들이겠구먼! 이라 생각할 수 있대
	@Override
	public void close() throws Exception {
		System.out.println("MyClose 호출");
	}
}
public class Test09 {
	public static void main(String[] args) {
		
		// try() 이래버리면 되는구먼! 개꿀~
		try (
				FileReader fr = new FileReader("test03.txt");
				BufferedReader br = new BufferedReader(fr);
				FileWriter fw = new FileWriter("test09.txt");
				BufferedWriter bw = new BufferedWriter(fw);
				) 
		{
			// 이곳에 close() 각각 해주면 중간에 에러났을때 안닫힘 즉, 예외가 났을때도 close() 해줘야함 그래서 어차피 해줘야하는거면 
			// finally에서 해주려니까 선언 위치때문에 try문 위에 FileReader fr = null; 이런식으로 다 선언해주고 해야함
			// 그럼 또 br, bw, fr, fw 얘네들도 다 에러날수 있으니 다 try로 묶어주고, 또 이게 널값 오면 안되니까 각각 if(br != null) 이거 추가해줘야함
			// 그래서 close를 편하게 할 수 있는거를 자바에서 만들어줌!
		} catch(Exception e) {
			e.printStackTrace();
		} 
		
		// 주의
		// 아래처럼 하면 the resource type MyClose does not implement java.lang.AutoCloseable 오류남, 이 안에 들어가려면 implements AutoCloseable 해줘야함 
		try(MyClose mc = new MyClose();){
			System.out.println(1);
		} catch(Exception e) {
			System.out.println(2);			
		}
		System.out.println(3);
	}
}
