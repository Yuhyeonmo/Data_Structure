import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 연속된 부분, 그리고 원형인 것을 알아야 한다.
 */
public class BOJ_2632 {
	static ArrayList<Integer> Asum,Bsum;
	static int target;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		target = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int A[] = new int [m];
		int B[] = new int [n];
		
		for(int i=0;i<m;i++){
			A[i] = Integer.parseInt(br.readLine());
		}
		for(int i=0;i<n;i++){
			B[i] = Integer.parseInt(br.readLine());
		}
		
		Asum = new ArrayList<Integer>();
		Bsum = new ArrayList<Integer>();
		// A, B에 대해서 부분합을 구함.
		for(int i=0;i<m;i++){
			
			boolean [] check = new boolean [m];
			dfs(A,i,i+1, m,A[i], check, Asum);
		}
		for(int i=0;i<n;i++){
			boolean [] check = new boolean [n];
			dfs(B,i,i+1, n,B[i], check, Bsum);
		}
		// 아무것도 선택하지 않은 경우 0이므로 넣어줌.
		Asum.add(0);
		Bsum.add(0);
		
		
		int left = 0;
		int right = Bsum.size()-1;
		int flag = 0;
		int ans = 0;
		
		Collections.sort(Asum);
		Collections.sort(Bsum);
		while(left<Asum.size() && right>=0){
			int lv = Asum.get(left);
			int rv = Bsum.get(right);
			if(lv+rv==target){
				int lc=0;
				int rc=0;
				while(left<Asum.size() && Asum.get(left)==lv){
					left++;
					lc++;
				}
				while(right>=0 && Bsum.get(right)==rv){
					right--;
					rc++;
				}
				ans += lc*rc;
				
			}
			else if(lv+rv>target){
				right--;
			}
			else {
				left++;
			}
		}
	
		System.out.println(ans);
		
	}
	// arr : 기준이 되는 배열, start : 시작점 , idx : 현재점 , n : 배열의 크기 , sum : 합계, check : 방문여부 , list : 합계값을 저장하는 list
	public static void dfs(int [] arr ,int start, int idx, int n, int sum, boolean check [], ArrayList<Integer> list){
		// idx 처음 값(원판)
		if(idx==n){
			idx = 0;
		}
		list.add(sum);
		// check 되지 않고, 한바퀴 다돌지 않으면서, target 값도 크지 않은 경우만 sum에 저장
		if(check[idx] == false && idx!=start-1 && sum<=target){
			check[idx] = true;
			dfs(arr, start, idx+1, n, sum+arr[idx], check, list);
		}
		else {
			return ;
		}
		
	}

}
