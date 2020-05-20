import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
 * 문제
N개의 정수로 이루어진 수열이 있을 때, 크기가 양수인 부분수열 중에서 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 정수의 개수를 나타내는 N과 정수 S가 주어진다. 
(1 ≤ N ≤ 20, |S| ≤ 1,000,000) 둘째 줄에 N개의 정수가 빈 칸을 사이에 두고 주어진다. 주어지는 정수의 절댓값은 100,000을 넘지 않는다.

출력
첫째 줄에 합이 S가 되는 부분수열의 개수를 출력한다.
 */
public class BOJ_1182 {
	static int S;
	static int ans = 0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		int [] arr =new int [N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++){
			int input = Integer.parseInt(st.nextToken());			
			arr[i] = input;
		}
		dfs(N,0,0,arr);
		if(S==0){
			ans--;
		}
		System.out.println(ans);
	}
	
	public static void dfs(int n, int idx, int sum, int []arr){
		
		
		if(idx == n){
			if(sum==S){
				ans++;
			}
			return ;
		}
		
		
		dfs(n,idx+1, sum+arr[idx],arr);
		dfs(n,idx+1, sum, arr);
		
		
	}

}
