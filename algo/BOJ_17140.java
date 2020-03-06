import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;


/**
 * 
 2차원 배열과 연산

문제를 정확히 이해 하는 것이 중요하다.
정렬을 어떤 기준을 할 지 / 그리고 어떻게 출력을 할 것인가에 대해서 고밀해볼 것.
Compareable을 이용해서 숫자와 숫자가 나오는 횟수를 통해 정렬을 함.
시간은 넉넉한 편 .

시간 1~2시간 사이 소요
 *
 */
public class BOJ_17140 {
	static int r,c,k;
	static int lenR, lenC;
	static int time = 0;
	static int flag = 0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		int arr[][] = new int [101][101];
		int countNum [] = new int [101];
		for(int i=1;i<=3;i++){
			st = new StringTokenizer(br.readLine(), " ");
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
			arr[i][3] = Integer.parseInt(st.nextToken());
		}
		// 3X3 초기 값
		lenR = 3;
		lenC = 3;
		find(arr);

		System.out.println(time);
		
	}
	
	// time이 얼마인지 찾기
	public static void find(int arr[][]){
		while(true){
			if(arr[r][c] == k) {
				return ;
			} else if(time>100){
				time = -1;
				return ;
			}
			else {
				time++;
				ChoiceSort(arr);
			}
		}
	}
	// R or C 를 정해서 정렬 시킴.
	public static void ChoiceSort(int arr[][]){
		int tr = lenR;
		int tc = lenC;
		int MaxLen = 0;
		int copyArr[][] = new int [101][101];
		if(tr>=tc){
			for(int i=1;i<=tr;i++){
				int countNum[] = new  int [101];
				for(int j=1;j<=tc;j++){
					// 숫자가 나오는 횟수를 Count
					countNum[arr[i][j]]++;
				}
				// Count 한 숫자를 Number List에 넣음 ( 0을 제외한 )
				ArrayList<Number> tmp = new ArrayList<Number>();
				for(int k=1;k<=100;k++){
					if(countNum[k]!=0){
						tmp.add(new Number(k, countNum[k]));
					}
				}
				// 정렬 기준에 따라 정렬
				java.util.Collections.sort(tmp);
				int tj = 1;
				// 정렬된 값을 arr에 넣어줌.
				for(Number n : tmp){
					if(tj<=100){
					copyArr[i][tj] = n.num;
					copyArr[i][tj+1] = n.count;
					tj+=2;
					} else {
						
					}
				}

				tj--;
				if(MaxLen < tj){
					MaxLen = tj;
				}
			}
			lenC = MaxLen;
			for(int i=1;i<101;i++){
				for(int j=1;j<101;j++){
					arr[i][j] = copyArr[i][j];
				}
			}
		} else {
			for(int j=1;j<=tc;j++){
				int countNum[] = new int [101];
				for(int i=1;i<=tr;i++){
					countNum[arr[i][j]]++;
				}
				ArrayList<Number> tmp = new ArrayList<Number>();
				for(int k=1;k<=100;k++){
					if(countNum[k]!=0){
						tmp.add(new Number(k, countNum[k]));
					} 
				}
				java.util.Collections.sort(tmp);
				int ti = 1;
				for(Number n : tmp){
					if(ti<=100){
					copyArr[ti][j] = n.num;
					copyArr[ti+1][j] = n.count;
					ti+=2;
					} else {
						break;
					}
				}

				ti--;
				if(MaxLen < ti){
					MaxLen = ti;
				}
			}
			lenR = MaxLen;
			for(int i=1;i<101;i++){
				for(int j=1;j<101;j++){
					arr[i][j] = copyArr[i][j];
				}
			}
		}
		
	}
	public static class Number implements Comparable<Number>{
		int num;
		int count;
		
		Number(int n, int c){
			this.num = n;
			this.count = c;
		}

		@Override
		public int compareTo(Number n) {
			// TODO Auto-generated method stub
			if(n.count < this.count){
				return 1;
			} else if(n.count > this.count) {
				return -1;
			} else {
				if(n.num < this.num) {
					return 1;
				} else {
					return -1;
				}
			}
		}
	}
}
