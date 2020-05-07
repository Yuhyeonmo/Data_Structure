import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
문제
N개의 정수로 이루어진 배열 A가 주어진다. 이때, 배열에 들어있는 정수의 순서를 적절히 바꿔서 다음 식의 최댓값을 구하는 프로그램을 작성하시오.

|A[0] - A[1]| + |A[1] - A[2]| + ... + |A[N-2] - A[N-1]|

입력
첫째 줄에 N (3 ≤ N ≤ 8)이 주어진다. 둘째 줄에는 배열 A에 들어있는 정수가 주어진다. 배열에 들어있는 정수는 -100보다 크거나 같고, 100보다 작거나 같다.

출력
첫째 줄에 배열에 들어있는 수의 순서를 적절히 바꿔서 얻을 수 있는 식의 최댓값을 출력한다.

 */
public class BOJ_10819 {
	static int arr[];
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int [N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int a[] = new int [N];
		shuffle(arr, 0, N);
		System.out.println(max);
	}
	
	public static void shuffle(int [] a, int depth, int n){
		
		if(depth==n){

			int res = calc(a);
			if(max<res){
				max = res;
			}
		}
		
		for(int i=depth;i<n;i++){
			swap(a, depth, i);
			shuffle(a, depth+1, n);
			swap(a, depth, i);
		}
		
	}
	public static void swap(int a[], int depth, int i){
		int tmp = a[i];
		a[i] = a[depth];
		a[depth] = tmp;
	}
	public static int calc(int [] a){
		int sum = 0;
		for(int i=0;i<a.length-1;i++){
			sum+= Math.abs(a[i] - a[i+1]);
		}
		return sum;
	}

}
