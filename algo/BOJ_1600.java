import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
-2,-1 / -2, 1
-1,-2 / -1, 2
1, -2 / 1, 2
2, -1 / 2, 1

 총평 : 조금 더 최적화 해야할 필요성이 있음. 
      중복방지 안해서 메모리 초과뜸.
      말의 횟수로 중복체크를 해줌.
 */
public class BOJ_1600 {
	static int k;
	static int w, h;
	// 4방향
	static int dx[] = {0,0,-1,1};
	static int dy[] = {-1,1,0,0};
	// 말의 이동
	static int hx[] = {-2,-2,-1,-1,1,1,2,2};
	static int hy[] = {-1,1,-2,2,-2,2,-1,1};
	
	static int map[][];
	// 중복 방지
	static boolean check[][][];
	static int cnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new int [h][w];
		for(int i=0;i<h;i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<w;j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		check = new boolean [h][w][k+1];
		bfs();
		System.out.println(cnt);
	}
	
	public static void bfs(){
		
		Queue<Point> q = new LinkedList<Point>();
		
		q.add(new Point(0,0));
		for (int i = 0; i < k; i++) { check[0][0][i] = true; }
		check[0][0][0] = true;
		while(!q.isEmpty()){
			
			int size = q.size();
			
			for(int i=0;i<size;i++){
				
				Point tmp = q.poll();
				if(tmp.x==h-1 && tmp.y==w-1) return ;
			
				for(int j=0;j<4;j++){
					int tx = tmp.x - dx[j];
					int ty = tmp.y - dy[j];
					
					if(tx>=0 && ty>=0 && tx<h && ty<w && map[tx][ty]==0 && !check[tx][ty][tmp.k]){
						check[tx][ty][tmp.k] = true;
						q.add(new Point(tx,ty,tmp.k));
						
					}
				}
				// 말 이동 수 k번이 아닌 경우
				if(tmp.k<k){
					
					for(int j=0;j<8;j++){
						
						int tx = tmp.x - hx[j];
						int ty = tmp.y - hy[j];
						int tk = tmp.k + 1;
						if(tk<=k && tx>=0 && ty>=0 && tx<h && ty<w && map[tx][ty]==0 && !check[tx][ty][tk]){
							check[tx][ty][tk] = true;
							q.add(new Point(tx,ty,tk));
							
						}
					}
				}	
				
			}
			cnt++;	
		}
		// 다 해도 못찾은 경우 -1
		cnt = -1;
		
	}
	public static class Point {
		
		int x;
		int y;
		int k;
		
		Point(int x, int y ){
			this.x = x;
			this.y = y;
			this.k = 0;
		}
		Point(int x, int y, int k){
			this.x =x;
			this.y =y;
			this.k =k;
		}
	}
}
