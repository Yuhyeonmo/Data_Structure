import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*

문제
하나 이상의 연속된 소수의 합으로 나타낼 수 있는 자연수들이 있다. 
몇 가지 자연수의 예를 들어 보면 다음과 같다.

3 : 3 (한 가지)
41 : 2+3+5+7+11+13 = 11+13+17 = 41 (세 가지)
53 : 5+7+11+13+17 = 53 (두 가지)
하지만 연속된 소수의 합으로 나타낼 수 없는 자연수들도 있는데, 20이 그 예이다. 
7+13을 계산하면 20이 되기는 하나 7과 13이 연속이 아니기에 적합한 표현이 아니다. 
또한 한 소수는 반드시 한 번만 덧셈에 사용될 수 있기 때문에, 3+5+5+7과 같은 표현도 적합하지 않다.

자연수가 주어졌을 때, 이 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 4,000,000)
 */
public class BOJ_1644 {
	static boolean isPrim[];
	static ArrayList<Integer> primArr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		isPrim = new boolean[N+1];
		primArr = new ArrayList<Integer>();
		calcPrimeNumber(N);
		for(int i=2;i<=N;i++){
			if(!isPrim[i]){
				primArr.add(i);
			}
		}
		int left = 0;
		int right =0;
		int sum = 0;
		int cnt = 0;
		if(N<2){
			System.out.println(0);
		}
		else{
			int len = primArr.size();
			while(true){
				if(sum==N) cnt++;
				if(sum>=N){
					
					sum -= primArr.get(left++);
					
				}
				else if(right==len) break;
				
				else {
					sum += primArr.get(right++);
				}
				
			}
			System.out.println(cnt);
		}
	}
	public static void calcPrimeNumber(int N){
		for(int i=2;i*i<=N;i++){
			if(isPrim[i]) continue;
			for(int j=i+i; j<=N;j+=i){
				isPrim[j] = true;
			}
		}
	}
}
