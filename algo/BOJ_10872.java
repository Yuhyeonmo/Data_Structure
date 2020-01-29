import java.util.Scanner;

/**
 * 
 * @author YuHyeonMo
 문제
0보다 크거나 같은 정수 N이 주어진다. 이때, N!을 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 정수 N(0 ≤ N ≤ 12)가 주어진다.

출력
첫째 줄에 N!을 출력한다.

주의 ) 0! => 1이다..
 *
 */
public class BOJ_10872 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		if(N==0){
			System.out.println(1);
		}
		else{
		long res = facto(N);
		System.out.println(res);
		}
		
		/* 
		 * DP로 생각해보기
		 * d[0] 0!이므로 1
		 * d[n] = d[n-1] * n 
		 * 
		long d[] = new long[13];
		d[0] = 1;
		for(int i=1;i<=12;i++){
			d[i] = d[i-1] * i;
		}
		System.out.println(d[N]);*/
	}
	public static long facto(int N){
		
		if(N==1 || N==0){
			return N;
		}
		else{
			return N * facto(N-1);
		}
		
	}

}
