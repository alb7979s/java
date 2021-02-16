import java.util.Scanner;

// nextPermutation을 활용함
// 숫자 대신에 0011 이런식으로 n개의 위치 중 r개의 1로 뒤쪽에 초기화 시켜주고 nextPermutation처럼 돌리면 됨
public class NextCombination {
	static int n, r;
	static int[] input, picked;
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		r = sc.nextInt();
		input = new int[n];
		picked = new int[n];
		
		for(int i = 0; i < n; i++) {
			input[i] = sc.nextInt();
		}
		
		// 뒤에 자리들 r개만큼 1로 초기화
		int cnt = 0;
		while(++cnt <= r) picked[n-cnt] = 1;
		
		do {
			for(int i = 0; i < n; i++)
				if(picked[i] == 1) System.out.print(input[i] + " ");
			System.out.println();
		} while(nextCombination());
		sc.close();
	}
	
	private static void swap(int i, int j) {
		int temp = picked[i];
		picked[i] = picked[j];
		picked[j] = temp;
	}
	
	public static boolean nextCombination() {
		int i = n - 1;
		while( i > 0 && picked[i-1] >= picked[i] ) i--;
		if(i==0) return false;
		
		int j = n - 1;
		while( picked[i-1] >= picked[j] ) j--;
		swap(i-1, j);
		
		int k = n - 1;
		while( i < k ) {
			swap(i++, k--);
		}
		return true;
	}
}
