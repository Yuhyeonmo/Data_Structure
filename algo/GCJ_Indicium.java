import java.util.Scanner;

/**
 * 
 * @author kora1492
 * 
 * 답 X 
 * N이 클 때 못잡음. 
 * 다른 풀이는  bipartite matching 을 활용한다고 함...
 */

public class GCJ_Indicium {
	static int N,K;
	static int Ans[][];
	static StringBuilder sb;
	static boolean flag;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc=1;tc<=T;tc++){
			N = sc.nextInt();
			K = sc.nextInt();
			Ans = new int [N][N];
			sb = new StringBuilder();
			flag = false;
			isR(0,0);
			if(flag==true){
				System.out.println("Case #"+tc+":"+" POSSIBLE");
				System.out.println(sb.toString());
			}
			else{
				System.out.println("Case #"+tc+":"+" IMPOSSIBLE");
			}
		}
	}
	public static void isR(int x, int y){
		if(flag==true) return ;
		if(x==N){
			int sum = 0;
			for(int i=0;i<N;i++){
				sum+= Ans[i][i];
				if(sum>K){
					return ;
				}
			}
			if(sum==K){
				flag = true;
				for(int i=0;i<N;i++){
					for(int j=0;j<N;j++){
						sb.append(Ans[i][j]+" ");
					}
					sb.append("\n");		
				}
			}
			return ;
		}
		int x1 = x;
		int y1 = y+1;
		if(y1==N){
			x1++;
			y1=0;
		}
		int [] calc = new int [N+1];
		for(int i=0;i<N;i++){
			calc[Ans[i][y]]++;
			calc[Ans[x][i]]++;
		}
		
		for(int i=1;i<=N;i++){
			if(calc[i]==0){
				Ans[x][y] = i;
				isR(x1,y1); 	
			}
		}
		Ans[x][y] = 0;
	}
}
