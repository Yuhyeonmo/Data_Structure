import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author kora1492
 *
 */
public class BOJ_11559 {
	// 뿌요뿌요 맵
	static char map[][] = new char[12][6];
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	static int cnt = 0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<12;i++){
			String input = br.readLine();
			for(int j=0;j<6;j++){
				map[i][j] = input.charAt(j);
			}
		}
		// 뿌요 연쇄 시작
		run_Pyuo();
		System.out.println(cnt);
	}
	public static void run_Pyuo(){
		
		// 연쇄 여부를 체크
		boolean check = false;
		
		for(int i=0;i<12;i++){
			
			for(int j=0;j<6;j++){
				boolean flag;
				if(map[i][j]!='.'){
					// 빈 칸이 아닌 색깔 , remove 라는 BFS 실행
					// flag = true 이면 뿌요가 터진 경우이다.
					flag = remove(i,j);
					if(flag==true) check = true;
				}
			}
		}
		
		
		
		if(check){
			// 연쇄 카운트
			cnt++;
			// 뿌요를 떨어트림
			down_Pyuo();
//			for(int i=0;i<12;i++){
//				for(int j=0;j<6;j++){
//					System.out.print(" "+map[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			// 재귀 호출
			run_Pyuo();
		}
	}
	public static boolean remove(int x, int y){
		
		char nChar = map[x][y];
		Queue<Point> que = new LinkedList<Point>();
		que.add(new Point(x,y));
		boolean check[][] = new boolean [12][6];
		int len = 1;
		while(!que.isEmpty()){
			
			int size = que.size();
			for(int q=0;q<size;q++){	
			Point t = que.poll();
			check[t.x][t.y] = true;
			for(int i=0;i<4;i++){

				int nx = t.x + dx[i];
				int ny = t.y + dy[i];

				if(nx<0 || ny<0 || nx>=12 || ny>=6 || map[nx][ny]!=nChar || check[nx][ny]==true) continue;

				check[nx][ny] = true;
				que.add(new Point(nx,ny));
				len++;

				}
			}		
		}
		// 현 위치에서 4개 이상 탐색이 된다는 것은 4개 이상 이어짐을 의미하므로 해당 위치의 점들을 '.' 으로 바꿔준다.
		if(len>=4){
			for(int i=0;i<12;i++){
				for(int j=0;j<6;j++){
					if(check[i][j]){
						map[i][j] = '.';
					}	
				}	
			}
			return true;
		}
		return false;
	}
	
	public static void down_Pyuo(){
		// 아래에서 위로 탐색 
		for(int i=11;i>=0;i--){
			for(int j=0;j<6;j++){
				// 빈 칸이 아닌 점들에 대해서 다운
				if(map[i][j]!='.'){
					down(i,j);
				}
			}
		}
		
	}
	public static void down(int x, int y){
		char c = map[x][y];
		
		for(int i=x+1;i<12;i++){
			
			if(map[i][y]!='.') {
				map[x][y] = '.';
				map[i-1][y] = c; 
				return;
			}
		}
		// 맨 아래쪽까지 떨어질 수 있는 경우 이므로, 맨 아래칸에 해당 뿌요를 떨어트림.
		map[x][y] = '.';
		map[11][y] = c;
		
	}
	private static class Point {
		int x;
		int y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
