import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 문제
한 배열 A[1], A[2], …, A[n]에 대해서, 부 배열은 A[i], A[i+1], …, A[j-1], A[j] (단, 1 ≤ i ≤ j ≤ n)을 말한다. 
이러한 부 배열의 합은 A[i]+…+A[j]를 의미한다. 각 원소가 정수인 두 배열 A[1], …, A[n]과 B[1], …, B[m]이 주어졌을 때, 
A의 부 배열의 합에 B의 부 배열의 합을 더해서 T가 되는 모든 부 배열 쌍의 개수를 구하는 프로그램을 작성하시오.


예를 들어 A = {1, 3, 1, 2}, B = {1, 3, 2}, T=5인 경우, 부 배열 쌍의 개수는 다음의 7가지 경우가 있다.

T(=5) = A[1] + B[1] + B[2] = 1 + 4
      = A[1] + A[2] + B[1] = 4 + 1
      = A[2] + B[3]        = 3 + 2
      = A[2] + A[3] + B[1] = 4 + 1
      = A[3] + B[1] + B[2] = 1 + 4
      = A[3] + A[4] + B[3] = 3 + 2
      = A[4] + B[2]        = 2 + 3
      = 
입력
첫째 줄에 T(-1,000,000,000 ≤ T ≤ 1,000,000,000)가 주어진다. 
다음 줄에는 n(1 ≤ n ≤ 1,000)이 주어지고, 그 다음 줄에 n개의 정수로 A[1], …, A[n]이 주어진다. 
다음 줄에는 m(1≤m≤1,000)이 주어지고, 그 다음 줄에 m개의 정수로 B[1], …, B[m]이 주어진다. 각각의 배열 원소는 절댓값이 1,000,000을 넘지 않는 정수이다.

출력
첫째 줄에 답을 출력한다. 가능한 경우가 한 가지도 없을 경우에는 0을 출력한다.
 */
public class BOJ_2143 {
	static ArrayList<Integer> A,B;
	static int T;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		int arrA[] = new int [N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++){
			arrA[i] = Integer.parseInt(st.nextToken());
		}
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int arrB[] = new int [m];
		for(int i=0;i<m;i++){
			arrB[i] = Integer.parseInt(st.nextToken());
		}
		A = new ArrayList<Integer>();
		B = new ArrayList<Integer>();
		// 부 배열의 합들을 구함. 
		for(int i=0;i<N;i++){
			int sum = arrA[i];
			A.add(sum);
			for(int j=i+1;j<N;j++){
				sum += arrA[j];
				A.add(sum);
			}
			
		}
		
		for(int i=0;i<m;i++){
			int sum = arrB[i];
			B.add(sum);
			for(int j=i+1;j<m;j++){
				sum += arrB[j];
				B.add(sum);
			}		
		}
		

		Collections.sort(A);
		Collections.sort(B);

		int left = 0;
		int right = B.size()-1;
		int sum = 0;
		long ans = 0;
		while(left<A.size() && right>=0){
			
			int lv = A.get(left);
			int rv = B.get(right);
			if(lv==0){
				left++; 
				continue;
			}
			if(rv==0){
				right--;
				continue;
			}
			if(lv+rv == T){
				long lc = 0;
				long rc = 0;
				
				while(left<A.size() && A.get(left)==lv){
					left++;
					lc++;
				}
				while(right>=0 && B.get(right)==rv){
					right--;
					rc++;
				}
				ans += lc*rc;
			}
			if(lv+rv>T){
				right--;
			}
			if(lv+rv<T){
				left++;
			}
						
		}
		System.out.println(ans);
	}

}
