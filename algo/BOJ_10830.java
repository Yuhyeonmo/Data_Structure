import java.util.Scanner;

/**
 * 
 * @author kora1492
문제
크기가 N*N인 행렬 A가 주어진다. 이때, A의 B제곱을 구하는 프로그램을 작성하시오. 수가 매우 커질 수 있으니, A^B의 각 원소를 1,000으로 나눈 나머지를 출력한다.

입력
첫째 줄에 행렬의 크기 N과 B가 주어진다. (2 ≤ N ≤  5, 1 ≤ B ≤ 100,000,000,000)

둘째 줄부터 N개의 줄에 행렬의 각 원소가 주어진다. 행렬의 각 원소는 1,000보다 작거나 같은 자연수 또는 0이다.

출력
첫째 줄부터 N개의 줄에 걸쳐 행렬 A를 B제곱한 결과를 출력한다.

 */
public class BOJ_10830 {
	static int N;
	static int ans[][];
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		long B = sc.nextLong();
		int [][] A= new int [N][N];
		ans = new int [N][N];
		for(int i=0;i<N;i++){
			
			for(int j=0;j<N;j++){
				A[i][j] = sc.nextInt();
			}
			ans[i][i] = 1;
		}
		A = divide(B, A);
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				sb.append(A[i][j]%1000+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static int [][] divide(long b, int [][] a){
		if(b==1){
			return a;
		}
		if(b==0){
			return ans;
		}
		
		if(b%2==0){
			int [][] c = divide(b/2, a);
			return mulMatrix(c, c);
		}
		else {
			int [][] c= divide(b-1,a);
			return mulMatrix(c, a);
		}
		
		
	}
	
	public static int[][] mulMatrix(int[][] a, int [][] b){
		
		int res [][] = new int [N][N];
		
		for(int i=0;i<N;i++){
			
			for(int j=0;j<N;j++){
				int sum  = 0;
				for(int k=0;k<N;k++){
					sum += a[i][k] * b[k][j];
					
				}	
				sum%=1000;
				res[i][j] = sum;	
			}
		}
		
		return res;
	}

}
