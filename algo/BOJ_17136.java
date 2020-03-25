import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author kora1492
문제
<그림 1>과 같이 정사각형 모양을 한 다섯 종류의 색종이가 있다. 색종이의 크기는 1×1, 2×2, 3×3, 4×4, 5×5로 총 다섯 종류가 있으며, 각 종류의 색종이는 5개씩 가지고 있다.

색종이를 크기가 10×10인 종이 위에 붙이려고 한다. 종이는 1×1 크기의 칸으로 나누어져 있으며, 각각의 칸에는 0 또는 1이 적혀 있다. 1이 적힌 칸은 모두 색종이로 덮여져야 한다.
색종이를 붙일 때는 종이의 경계 밖으로 나가서는 안되고, 겹쳐도 안 된다. 또, 칸의 경계와 일치하게 붙여야 한다. 0이 적힌 칸에는 색종이가 있으면 안 된다.

종이가 주어졌을 때, 1이 적힌 모든 칸을 붙이는데 필요한 색종이의 최소 개수를 구해보자.

입력
총 10개의 줄에 종이의 각 칸에 적힌 수가 주어진다.

0 0 0 0 0 0 0 0 0 0
0 1 1 0 0 0 0 0 0 0
0 1 1 1 0 0 0 0 0 0
0 0 1 1 1 1 1 0 0 0
0 0 0 1 1 1 1 0 0 0
0 0 0 0 1 1 1 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0


0 0 0 0 0 0 0 0 0 0
0 1 1 1 1 1 0 0 0 0
0 1 1 1 1 1 0 0 0 0
0 0 1 1 1 1 0 0 0 0
0 0 1 1 1 1 0 0 0 0
0 1 1 1 1 1 1 1 1 1
0 1 1 1 0 1 1 1 1 1
0 1 1 1 0 1 1 1 1 1
0 0 0 0 0 1 1 1 1 1
0 0 0 0 0 1 1 1 1 1

출력
모든 1을 덮는데 필요한 색종이의 최소 개수를 출력한다. 1을 모두 덮는 것이 불가능한 경우에는 -1을 출력한다.
 */
public class BOJ_17136 {
	static int map[][] = new int [10][10];
	static int paper[] = {0,5,5,5,5,5};
	static int min = 26;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0;i<10;i++){
			StringTokenizer st =new StringTokenizer(br.readLine());
			for(int j=0;j<10;j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0,0);
		if(min==26) {
			min = -1;
		}
		System.out.println(min);
	}
	
	static void dfs(int idx, int cnt){
		if(idx==100){
			min = Math.min(min, cnt);
			return ;
		}
		
		int r = idx/10;
		int c = idx%10;
		
		if(map[r][c]==1){			
			for(int i=5;i>0;i--){
				if(paper[i]>0){
					if(isFill(r,c,i)){
						fill(r,c,i,0);
						paper[i]--;
						dfs(idx+1, cnt+1);
						fill(r,c,i,1);
						paper[i]++;
					}
				}
			}
			
		} else {
			dfs(idx+1, cnt);
		}
	}
	static void fill(int r, int c, int m, int value){
		for(int i=r;i<r+m;i++){
			for(int j=c;j<c+m;j++){
				map[i][j] = value;
			}
		}
	}
	static boolean isFill(int r, int c, int m){
		if(r+m>10 || c+m>10) return false;
		for(int i=r;i<r+m;i++){
			for(int j=c;j<c+m;j++){
				if(map[i][j]!=1){
					return false;
				}
			}
		}	
		return true;
	}

}
