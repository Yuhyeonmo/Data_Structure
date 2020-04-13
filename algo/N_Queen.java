import java.util.Scanner;

/*
 * baaa
 * aaaa
 * aaaa
 * aaaa
 */
public class N_Queen {
	static int N;
	static int col[];
	static int ans = 0;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		col = new int [N];
		dfs(0);
		System.out.println(ans);
	}
	
	public static void dfs(int n){
		
		if(n==N){
			ans++;
			return ;
		}
		for(int i=0;i<N;i++){
			col[n] = i;
			if(isCheck(n)){
				dfs(n+1);
			}
			else {
				col[n] = 0;
			}
		}
		col[n] = 0;
		
	}
	public static boolean isCheck(int c){
		
		for(int i=0;i<c;i++){
			if(col[i]==col[c]) return false;
			
			if(Math.abs(i-c)==Math.abs(col[i]-col[c])) return false;
		}

		return true;
	}

}
