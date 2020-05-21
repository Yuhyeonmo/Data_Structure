import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
입력
첫째 줄에 미로의 크기를 나타내는 가로 크기 M, 세로 크기 N (1 ≤ N, M ≤ 100)이 주어진다. 
다음 N개의 줄에는 미로의 상태를 나타내는 숫자 0과 1이 주어진다. 0은 빈 방을 의미하고, 1은 벽을 의미한다.

(1, 1)과 (N, M)은 항상 뚫려있다.

출력
첫째 줄에 알고스팟 운영진이 (N, M)으로 이동하기 위해 벽을 최소 몇 개 부수어야 하는지 출력한다.
 */
public class BOJ_1261 {
	
	static int visited [][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int ans = Integer.MAX_VALUE;
	static int N,M;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		visited = new int [N+1][M+1];
		int map[][] = new int [N+1][M+1];
		for(int i=1;i<=N;i++){
			String line = br.readLine();
			for(int j=1;j<=M;j++){
				map[i][j] = line.charAt(j-1) - '0';
			}
		}
		for(int i=1;i<=N;i++){
			for(int j=1;j<=M;j++){
				visited[i][j] = 201;
			}
		}
		visited[0][0] = 0;
		dfs(map,1,1,0);
		
		System.out.println(ans);
	}
	public static void dfs(int [][] map, int r, int c, int cnt){

		if(r==N && c==M){
			if(ans>cnt){
				ans = cnt;
			}	
			return ;
		}
		
		for(int i=0;i<4;i++){
			
			int tr = r - dx[i];
			int tc = c - dy[i];
			
			if(tc>=1 && tr>=1 && tr<=N && tc<=M){
				if(map[tr][tc]==0){
					if(visited[tr][tc]>cnt){
						visited[tr][tc] = cnt;
						dfs(map, tr, tc, cnt);
					}
				}
				else {
					if(visited[tr][tc]>cnt+1){
						visited[tr][tc] = cnt+1;
						dfs(map, tr, tc, cnt+1);
					}
				}

			}
			
		}
		
		
		
	}

}
