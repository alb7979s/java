/*
 * 예외 처리
 * throw, throws
 * try, catch
 * Runtime Exception(unchecked)
 * 사용자 정의
 * 예외범위는 부모보다 작게
 */
package part02.exception;

import java.util.ArrayList;

public class Test01 {
	public static void main(String[] args) {
		// Nullpointer
		String s = null;
		System.out.println(s.length());
		
		// ArrayIndexOutOfBoundsException
		int[] a = new int[0];
		System.out.println(a[0]);
		
		// ClassCastException
		Object obj = new ArrayList();
		String str = (String)obj;
		
	}
}
