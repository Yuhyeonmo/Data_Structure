import java.util.Scanner;

/**@author : YuHyeonMo
 * 가장 긴 증가하는 부분 수열 
 문제 
수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.

예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.

입력
6
10 20 10 30 20 50


첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.

둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)

출력
첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.

풀이고민

DP를 풀기로 결정.
dp 배열을 어떻게 만들지에 대한 고민 -> 해당 위치까지의 가장 긴 길이를 나타낸다고 생각함.

초기값은 우선 못해도 자신은 포함될 것이므로, 1로 초기화
그리고 비교하면서 찾아감.
1) i번째의 값과 j번째를 비교했을 시, i번째의 값이 크면서(array[i]>array[j]),  2) dp[j](j까지의  부분수열)+1 한 값이 d[i]의 값보다 큰 경우를 찾음.
=> 즉, array[i]의 값이 array[j] 보다 크다는 것은 증가하는 부분에 일치함과 동시에 현재 d[i](i-1번까지의 위치에서 가장 긴 길이) 보다 d[j] + 1(오름차순에 해당되기때문에)이 크면 d[i]의 값을 바꿔주는 것이다.

 이렇게 값을 찾아나가면서 dp 배열안에서 가장 큰 값을 뽑아내면 답이 됨.
 O(N^2)의 성능을 보임. O(nlogn)의 풀이법도 존재하는 듯. 
**/
public class BOJ_11053 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int d[] = new int [N]; // dp에 사용할 배열
		int array[] = new int [N];
		for(int i=0;i<N;i++){
			array[i] = sc.nextInt();
			d[i] = 1;
		}
		int maxLen = -1;
		for(int i=0;i<N;i++){
			for(int j=0;j<i;j++){
				// dp 조건부분
				if(array[j]<array[i] && d[j]+1 > d[i]){
					d[i] = d[j] + 1;
				} 
			}
			
			maxLen = Math.max(maxLen, d[i]);
			
		}
		System.out.println(maxLen);
	}

}
