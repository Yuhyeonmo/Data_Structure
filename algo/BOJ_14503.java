import java.util.Scanner;

/**
 * 
 * @author YuhyeonMo
 * 문제
 * 
로봇 청소기가 주어졌을 때, 청소하는 영역의 개수를 구하는 프로그램을 작성하시오.

로봇 청소기가 있는 장소는 N×M 크기의 직사각형으로 나타낼 수 있으며, 1×1크기의 정사각형 칸으로 나누어져 있다. 각각의 칸은 벽 또는 빈 칸이다. 청소기는 바라보는 방향이 있으며, 이 방향은 동, 서, 남, 북중 하나이다. 지도의 각 칸은 (r, c)로 나타낼 수 있고, r은 북쪽으로부터 떨어진 칸의 개수, c는 서쪽으로 부터 떨어진 칸의 개수이다.

로봇 청소기는 다음과 같이 작동한다.

현재 위치를 청소한다.
현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
로봇 청소기는 이미 청소되어있는 칸을 또 청소하지 않으며, 벽을 통과할 수 없다

첫째 줄에 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 50)

둘째 줄에 로봇 청소기가 있는 칸의 좌표 (r, c)와 바라보는 방향 d가 주어진다. d가 0인 경우에는 북쪽을, 1인 경우에는 동쪽을, 2인 경우에는 남쪽을, 3인 경우에는 서쪽을 바라보고 있는 것이다.

셋째 줄부터 N개의 줄에 장소의 상태가 북쪽부터 남쪽 순서대로, 각 줄은 서쪽부터 동쪽 순서대로 주어진다. 빈 칸은 0, 벽은 1로 주어진다. 장소의 모든 외곽은 벽이다.

로봇 청소기가 있는 칸의 상태는 항상 빈 칸이다.

로봇 청소기가 청소하는 칸의 개수를 출력한다.

 */
public class BOJ_14503 {
	static int dx [] = {1,-1,0,0};
	static int dy [] = {0,0,1,-1};
	static int cnt = 1;
	static int a=0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int r = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		
		int map[][] = new int [N][M];
 		for(int i=0;i<N;i++){
 			for(int j=0;j<M;j++){
 				map[i][j] = 1;
 			}
 		}
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				map[i][j] = sc.nextInt();
			}
		}
		
		map[r][c] = 2;
		
		dfs(r,c,d,map);
		System.out.println(cnt);
	}
	
	public static void dfs(int r, int c, int d, int map [][])
	{
	
		if(!check(r,c,d,map)){
			if(isBack(r, c, d, map)){
				if(d==0){
					dfs(r+1,c,d,map);
				}
				else if(d==1){
					dfs(r,c-1,d,map);
				}
				else if(d==2){
					dfs(r-1,c,d,map);
				}
				else if(d==3){
					dfs(r,c+1,d,map);
				}
			}
			else {
				return ;
			}
		}

		else{
		if(d==0){
			if(c>0 && map[r][c-1]==0){
				d=3;
				map[r][c-1] = 2;
				cnt++;
				
				dfs(r, c-1, d, map);
				
			}
			else{
				d=3;
				dfs(r,c,d,map);
			}
		}
		else if(d==1){
			if(r>0 && map[r-1][c]==0){
				d=0;
				map[r-1][c] = 2;
				cnt++;
				
				dfs(r-1, c, d, map);
			
			}
			else{
				d=0;
				dfs(r,c,d,map);
			}
		}
		else if(d==2){
			if(c<map[0].length-1 && map[r][c+1]==0){
				d=1;
				map[r][c+1] = 2;
				cnt++;
				
				dfs(r, c+1, d, map);
		
			}
			else{
				d=1;
				dfs(r,c,d,map);
			}
		}
		else if(d==3){
			if(r<map.length-1 && map[r+1][c]==0){
				d=2;
				map[r+1][c] = 2;
				cnt++;
				
				dfs(r+1, c, d, map);
				
			}
			else{
				d=2;
				dfs(r,c,d,map);
			}
		}
		}
	}
	
	public static boolean check(int r, int c, int d, int map[][]){
	
		for(int i=0;i<4;i++){
			int x= r-dx[i];
			int y= c-dy[i];

			if(x>=0 && y>=0 && x<map.length && y<map[0].length && map[x][y]==0){
				return true;
			} 
		}
		
		return false;
		
	}
	public static boolean isBack(int r, int c, int d, int map[][]){
		
		if(d==0){
			if(r+1<map.length && map[r+1][c]!=1){
				return true;
			}
		}
		else if(d==1){
			if(c>0 && map[r][c-1]!=1){
				return true;
			}
		}
		else if(d==2){
			if(r>0 && map[r-1][c]!=1){
				return true;
			}
		}
		else if(d==3){
			if(c<map[0].length-1 && map[r][c+1]!=1){
				return true;
			}
		}
		
		return false;
	}

}
