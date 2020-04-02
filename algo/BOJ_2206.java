import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author kora1492
 */
public class BOJ_2206 {
	static int n,m;
	static int map[][];
	// 방문 여부를 확인
	// * 벽을 뚫어서 간 경로와 벽을 아직 뚫지 않고 진행한 경로를 구분해주어야 한다.
	static boolean checkMap[][][];
	static int dx[] = {0,0,1,-1};
	static int dy[] = {-1,1,0,0};
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int [n+1][m+1];
		checkMap = new boolean[n+1][m+1][2];
		for(int i=1;i<=n;i++){
			String input = br.readLine();
			for(int j=1;j<=m;j++){
				map[i][j] = input.charAt(j-1)-'0';
			}
		}
		// (주의) 시작 지점과 골인 지점이 일치하는 경우 - 예외로 그냥 빼주었다.
		if(n==1 && m==1){
			System.out.println(1);
		}
		else{
			if(bfs()){
				System.out.println(cnt);
			}
			else {
				System.out.println(-1);
			}
		}
	}
	static int cnt;
	public static boolean bfs(){
		
		Queue<Point> que = new LinkedList<Point>();
		que.add(new Point(1,1));
		checkMap[1][1][0] = true;
		checkMap[1][1][1] = true;
		// bfs 진행 단계로 거리 구함.
		cnt = 1;
		while(!que.isEmpty()){
			int size = que.size();
			
			for(int q=0;q<size;q++){
				Point now = que.poll();

				for(int i=0;i<4;i++){
					int x = now.x + dx[i];
					int y = now.y + dy[i];
					if(x==n && y==m){
						cnt++;
						return true;
					}
					// 이동할 수 있는 위치일 때
					if(x>=1 && x<=n && y>=1 && y<=m){
						// 벽을 뚫은 지 여부 확인
						if(now.check){
							// 벽을 뚫고 진행한 경로 + 벽을 뚫지 않은 경로에 대해서 방문 여부를 확인하고, 벽이 아니라면 이동함.
							if(!checkMap[x][y][0] && !checkMap[x][y][1] && map[x][y]==0){
								que.add(new Point(x,y,now.check));
								// 벽을 뚫고 진행한 경로의 방문했음을 찍어줌.
								checkMap[x][y][1] = true;
							}
							
						}
						else {// 벽을 뚫지 않은 경우,
							
						   if(map[x][y]==1){
							   // 벽인 경우, 뚫어주고 true(벽을 뚫었는 지 상태 체크)
							   que.add(new Point(x,y,true));
						   }
						   else {
							   // 벽이 아닌 경우 벽을 뚫지 않은 경로에  대해서 방문 여부를 체크
							   if(!checkMap[x][y][0]){
								   que.add(new Point(x,y,now.check));
							   }
						   }
						   // 벽을 뚫지 않고 진행한 위치 체크
						   checkMap[x][y][0] = true;
						}
					}
				}
				
				
			}
			// 이동거리
			cnt++;
			
		}
		// n, m 지점에 도달하지 못함.
		return false;
		
	}
	public static class Point{
		int x;
		int y;
		boolean check = false;
		Point(int x, int y, boolean c){
			this.x = x;
			this.y = y;
			this.check = c;
		}
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

}
