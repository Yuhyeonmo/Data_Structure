import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*

문제
독일 로또는 {1, 2, ..., 49}에서 수 6개를 고른다.

로또 번호를 선택하는데 사용되는 가장 유명한 전략은 49가지 수 중 k(k>6)개의 수를 골라 집합 S를 만든 다음 그 수만 가지고 번호를 선택하는 것이다.

예를 들어, k=8, S={1,2,3,5,8,13,21,34}인 경우 이 집합 S에서 수를 고를 수 있는 경우의 수는 총 28가지이다. 
([1,2,3,5,8,13], [1,2,3,5,8,21], [1,2,3,5,8,34], [1,2,3,5,13,21], ..., [3,5,8,13,21,34])

집합 S와 k가 주어졌을 때, 수를 고르는 모든 방법을 구하는 프로그램을 작성하시오.

입력
입력은 여러 개의 테스트 케이스로 이루어져 있다. 
각 테스트 케이스는 한 줄로 이루어져 있다. 첫 번째 수는 k (6 < k < 13)이고, 다음 k개 수는 집합 S에 포함되는 수이다. S의 원소는 오름차순으로 주어진다.

입력의 마지막 줄에는 0이 하나 주어진다. 

출력
각 테스트 케이스마다 수를 고르는 모든 방법을 출력한다. 이때, 사전 순으로 출력한다.

각 테스트 케이스 사이에는 빈 줄을 하나 출력한다.
 */
public class BOJ_6603 {
	static int k;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int input = Integer.parseInt(st.nextToken());
			if(input==0){
				return ;
			}
			k = input;
			int [] arr = new int [k];
			
			
			for(int i=0;i<k;i++){
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);	
			combi(k,6,0,arr, new boolean [k]);
			System.out.println();
		}
	}
	public static void combi(int n, int r, int idx, int [] a, boolean visit[]){
		if(r==0){
			print(a, visit, n);
		}
		for(int i=idx;i<n;i++){
			visit[i]= true;
			combi(n,r-1,i+1,a, visit);
			visit[i] = false;
		}
	}
	public static void print(int [] arr, boolean visit[], int n){
		for(int i=0;i<n;i++){
			if(visit[i]){
				System.out.print(arr[i]+" ");
			}
		}
		System.out.println();
	}

}
