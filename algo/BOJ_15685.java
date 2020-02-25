import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * @author kora1492
 * 백준 드래곤 커브
 * 패턴 찾기(그려지는 패턴이 존재) / 시뮬레이션 (규칙대로 이동시킴)
 */
public class BOJ_15685 {

	static int dy[] = {0,-1,0,1};
	static int dx[] = {1,0,-1,0};
	
	static int N;
	static int [][] map;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int [101][101];
		
		// 커브
		for(int i=0;i<N;i++){
			StringTokenizer st= new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			ArrayList<Integer> arr =new ArrayList<Integer>();
			
			// 방향 기록 
			arr.add(d);
			
			for(int j=0;j<g;j++){
				int size = arr.size();
				// 드래곤 커브 규칙 -> 이전 세대에서 가장 최근에 그린 방향에 대해서 반시계로 돌림.
				for(int k=size-1;k>=0;k--){
					arr.add((arr.get(k)+1)%4);
				}
			}
			// 좌표 기록
			map[y][x] = 1;
			
			for(int j=0;j<arr.size();j++){
				int curd = arr.get(j);
				
				x = x+dx[curd];
				y = y+dy[curd];
				
				if(x>=0 && y>=0 && x<101 && y<101){
					map[y][x] = 1;
				}
			}
		}
		int ans = 0;
		for(int i=0;i<100;i++){
			for(int j=0;j<100;j++){
				// 사각형 탐색
				if(map[i][j]==1 && map[i][j+1]==1 && map[i+1][j]==1 && map[i+1][j+1]==1){
					ans++;
				}
			}
		}
		System.out.println(ans);
	}

}
