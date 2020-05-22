import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*

문제
N개의 정수로 이루어진 수열이 있을 때, 크기가 양수인 부분수열 중에서 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 정수의 개수를 나타내는 N과 정수 S가 주어진다. 
(1 ≤ N ≤ 40, |S| ≤ 1,000,000) 둘째 줄에 N개의 정수가 빈 칸을 사이에 두고 주어진다. 주어지는 정수의 절댓값은 100,000을 넘지 않는다.

출력
첫째 줄에 합이 S가 되는 부분수열의 개수를 출력한다.
 */
public class BOJ_1208 {
	static ArrayList<Integer> A,B;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int [] arr = new int [N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		A = new ArrayList<Integer>();
		B = new ArrayList<Integer>();
		
		// 40까지의 합을 구하는 것은 범위가 커서 시간초과 발생
		// 절반으로 나눠서 A(0~N/2), B(N/2~N)로 부분수열의 합을 구함.
		dfs(arr, 0, N/2, 0, A);
		dfs(arr, N/2,N,  0, B);
		
		// 정렬
		Collections.sort(A);
		Collections.sort(B);
		
		// left, right 를 lower_bound, upper_bound를 잡아서 한다.
		// left, right 반대로 해도 상관없었다. 
		
		int left = 0;
		int right = B.size()-1;
		long ans=0;
		while(left<A.size() && right>=0){
			int lv = A.get(left);
			int rv = B.get(right);
			if(lv+rv==S){
				// lv + rv 의 합이 S일 때 처리
				long lc = 0;
				long rc = 0;
				// 왼쪽에서 중복되는 값 개수를 카운팅한다(자기포함).
				while(left<A.size() && A.get(left)==lv){
					lc++;
					left++;
				}
				// 오른쪽에서 중복되는 값 개수를 카운팅한다(자기포함).
				while(right>=0 && B.get(right)==rv){
					rc++;
					right--;
				}
				// 개수를 곱해준다. 해당되는 경우의 수를 구하는 문제이기 때문에
				ans += lc*rc;
			}
			// 구하고자 합보다 큰 경우 right를 뺀다.
			if(lv+rv>S){
				right--;
			}
			// 구하고자 하는 합보다 작은 경우 low를 뺀다.
			if(lv+rv <S) {
				left++;
			}
		}
		// 구하고자 하는 합이 0일 때, 공집합(아무것도 선택하지 않은 값=0)으로 되어 있어서 답에서 -1를 해준다.
		if(S==0){
			System.out.println(ans-1);
		}else {
			System.out.println(ans);
		}
	}
	// 부분수열의 합을 구하는 재귀함수
	public static void dfs(int arr[], int idx, int n, int sum, ArrayList<Integer> list){
		
		if(idx==n){
			list.add(sum);
			return ;
		}
		
		dfs(arr, idx+1, n, sum+arr[idx], list);
		dfs(arr, idx+1, n, sum, list);
		
	}
}

