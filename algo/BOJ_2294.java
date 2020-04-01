import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
문제
준규가 가지고 있는 동전은 총 N종류이고, 각각의 동전을 매우 많이 가지고 있다.

동전을 적절히 사용해서 그 가치의 합을 K로 만들려고 한다. 이때 필요한 동전 개수의 최솟값을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N과 K가 주어진다. (1 ≤ N ≤ 10, 1 ≤ K ≤ 100,000,000)

둘째 줄부터 N개의 줄에 동전의 가치 Ai가 오름차순으로 주어진다. (1 ≤ Ai ≤ 1,000,000, A1 = 1, i ≥ 2인 경우에 Ai는 Ai-1의 배수)

출력
첫째 줄에 K원을 만드는데 필요한 동전 개수의 최솟값을 출력한다.
 *
 */
public class BOJ_2294 {
	// dp[0] = 0
	// 1,2,5  => 1원으로 만드는 개수 1,2,3,4,5,6,7,8,9,10,11,12, ''''  k원까지 
	//        -> 2원으로                     1 2 2 3 3 4
	//        ->                       1                            
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int coin[] = new int [n];
		int dp[] = new int [100001];
		Arrays.fill(dp, 100001);
		for(int i=0;i<n;i++){
			st = new StringTokenizer(br.readLine());
			coin[i] = Integer.parseInt(st.nextToken());
			
		}
		dp[0] = 0;
		for(int i=0;i<n;i++){
			
			for(int j=coin[i];j<=k;j++){
				dp[j] = Math.min(dp[j], dp[j-coin[i]]+1);
			}
			
		}
		if(dp[k]==100001){
			System.out.println(-1);
		}else {
			System.out.println(dp[k]);
		}
	}

}
