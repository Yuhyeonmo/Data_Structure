import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
동물원에서 막 탈출한 원숭이 한 마리가 세상구경을 하고 있다. 
그 녀석은 말(Horse)이 되기를 간절히 원했다. 
그래서 그는 말의 움직임을 유심히 살펴보고 그대로 따라 하기로 하였다. 말은 말이다. 
말은 격자판에서 체스의 나이트와 같은 이동방식을 가진다. 다음 그림에 말의 이동방법이 나타나있다. x표시한 곳으로 말이 갈 수 있다는 뜻이다. 
참고로 말은 장애물을 뛰어넘을 수 있다.

 	x	 	x	 
x	 	 	 	x
 	 	말	 	 
x	 	 	 	x
 	x	 	x	 

  	
 	
근데 원숭이는 한 가지 착각하고 있는 것이 있다. 말은 저렇게 움직일 수 있지만 원숭이는 능력이 부족해서 총 K번만 위와 같이 움직일 수 있고, 그 외에는 그냥 인접한 칸으로만 움직일 수 있다. 대각선 방향은 인접한 칸에 포함되지 않는다.
이제 원숭이는 머나먼 여행길을 떠난다. 격자판의 맨 왼쪽 위에서 시작해서 맨 오른쪽 아래까지 가야한다. 
인접한 네 방향으로 한 번 움직이는 것, 말의 움직임으로 한 번 움직이는 것, 모두 한 번의 동작으로 친다. 격자판이 주어졌을 때, 원숭이가 최소한의 동작으로 시작지점에서 도착지점까지 갈 수 있는 방법을 알아내는 프로그램을 작성하시오.

입력
첫째 줄에 정수 K가 주어진다. 
둘째 줄에 격자판의 가로길이 W, 세로길이 H가 주어진다. 
그 다음 H줄에 걸쳐 W개의 숫자가 주어지는데, 0은 아무것도 없는 평지, 
1은 장애물을 뜻한다. 장애물이 있는 곳으로는 이동할 수 없다. 시작점과 도착점은 항상 평지이다. 
W와 H는 1이상 200이하의 자연수이고, K는 0이상 30이하의 정수이다.

출력
첫째 줄에 원숭이의 동작수의 최솟값을 출력한다. 시작점에서 도착점까지 갈 수 없는 경우엔 -1을 출력한다.
-2,-1 / -2, 1
-1,-2 / -1, 2
1, -2 / 1, 2
2, -1 / 2, 1 
 */
public class BOJ_1600 {
	static int k;
	static int w, h;
	static int dx[] = {0,0,-1,1};
	static int dy[] = {-1,1,0,0};
	
	static int hx[] = {-2,-2,-1,-1,1,1,2,2};
	static int hy[] = {-1,1,-2,2,-2,2,-1,1};
	static int map[][];
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
