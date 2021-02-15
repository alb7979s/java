import java.util.Arrays;
import java.util.Scanner;

public class NextPermutation {
	static int n;
	static int[] input;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		input = new int[n];
		for(int i = 0; i < n; i++) {
			input[i] = sc.nextInt();
		}
		
		Arrays.sort(input); 		// 오름차순 정렬하여 가장 작은 순열의 상태로 만듦
		do {
			System.out.println(Arrays.toString(input));
		} while(nextPermutation());
		sc.close();
	}
	
	private static void swap(int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}
	
	public static boolean nextPermutation() {
		// 꼭대기 찾음
		int i = n - 1;
		// 앞쪽에 있는 녀석이 같거나 클 때 => 전진(i>0인 동안)
		while( i > 0 && input[i-1] >= input[i] ) i--;
		// 더이상 앞자리가 없는 상황: 현 순열의 상태가 가장 큰순열(마지막 순열)
		if(i==0) return false;
		// 꼭대기 찾음
		int j = n - 1;
		while( input[i-1] >= input[j] ) j--;
		
		swap(i-1, j);
		
		int k = n - 1;
		while( i < k ) {
			swap(i++, k--);
		}
		return true;
	}
}
