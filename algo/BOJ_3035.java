import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author kora1492
문제
사악한 암흑의 군주 이민혁은 드디어 마법 구슬을 손에 넣었고, 그 능력을 실험해보기 위해 근처의 티떱숲에 홍수를 일으키려고 한다. 
이 숲에는 고슴도치가 한 마리 살고 있다. 고슴도치는 제일 친한 친구인 비버의 굴로 가능한 빨리 도망가 홍수를 피하려고 한다.

티떱숲의 지도는 R행 C열로 이루어져 있다. 
비어있는 곳은 '.'로 표시되어 있고, 물이 차있는 지역은 '*', 돌은 'X'로 표시되어 있다. 비버의 굴은 'D'로, 고슴도치의 위치는 'S'로 나타내어져 있다.

매 분마다 고슴도치는 현재 있는 칸과 인접한 네 칸 중 하나로 이동할 수 있다. 
(위, 아래, 오른쪽, 왼쪽) 물도 매 분마다 비어있는 칸으로 확장한다. 
물이 있는 칸과 인접해있는 비어있는 칸(적어도 한 변을 공유)은 물이 차게 된다. 물과 고슴도치는 돌을 통과할 수 없다. 또, 고슴도치는 물로 차있는 구역으로 이동할 수 없고, 물도 비버의 소굴로 이동할 수 없다.

티떱숲의 지도가 주어졌을 때, 고슴도치가 안전하게 비버의 굴로 이동하기 위해 필요한 최소 시간을 구하는 프로그램을 작성하시오.

고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 즉, 다음 시간에 물이 찰 예정인 칸으로 고슴도치는 이동할 수 없다. 이동할 수 있으면 고슴도치가 물에 빠지기 때문이다. 

입력
첫째 줄에 50보다 작거나 같은 자연수 R과 C가 주어진다.

다음 R개 줄에는 티떱숲의 지도가 주어지며, 문제에서 설명한 문자만 주어진다. 'D'와 'S'는 하나씩만 주어진다.

출력
첫째 줄에 고슴도치가 비버의 굴로 이동할 수 있는 가장 빠른 시간을 출력한다. 만약, 안전하게 비버의 굴로 이동할 수 없다면, "KAKTUS"를 출력한다.
 */
public class BOJ_3035 {
	static int R, C;
	static char map[][];
	// 고슴도치 이동(중복 방지)
	static boolean check[][];
	// 4방향
	static int dx[] = {0,0,-1,1};
	static int dy[] = {1,-1,0,0};
	static int sec = 1;
	// 물
	static Queue<Point> water = new LinkedList<Point>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		check = new boolean[R][C];
		int x = 0, y = 0;
		for(int i=0;i<R;i++){
			
			String line = br.readLine();		
			for(int j=0;j<C;j++){
				map[i][j]=line.charAt(j);
				if(map[i][j]=='*'){
					water.add(new Point(i,j));
				}
				if(map[i][j]=='S'){
					x = i;
					y = j;
					
				}
			}

		}
		// 시작위치로부터 
		bfs(x,y);
		// MAX_VALUE = > 제대로 된 시간안에 찾지 못함.
		if(sec==Integer.MAX_VALUE) System.out.println("KAKTUS");
		else{
			System.out.println(sec);
		}
	}
	
	public static void bfs(int x, int y){
		Queue<Point> que = new LinkedList<Point>();
		
		que.add(new Point(x,y));
		
		int flag = 0;
		// 더 이상 이동 하지 못하는 경우(큐가 빈 경우)
		while(!que.isEmpty()){
			
			int waterCnt = water.size();
			int size = que.size();
			// 물부터 인접한 곳에 이동여부를 확인
			for(int i=0;i<waterCnt;i++){		
				Point tmpW =  water.poll();
				for(int j=0;j<4;j++){
					
					int tx = tmpW.x - dx[j];
					int ty = tmpW.y - dy[j];
					
					if(tx<0 || ty<0 || tx>=R || ty >=C){
						continue;
					}
					// 빈 공간이면 이동
					if(map[tx][ty]=='.'){
						
						water.add(new Point(tx,ty));
						// 물을 채움.
						map[tx][ty] = '*';
					}
				}				
			}
			
			for(int i=0;i<size;i++){
				
				Point tmpG = que.poll();
				for(int j=0;j<4;j++){
					
					int tx = tmpG.x - dx[j];
					int ty = tmpG.y - dy[j];
					
					if(tx<0 || ty<0 || tx>=R || ty >=C){
						continue;
					}
					// 빈 공간이면서, 방문한적이 없는 곳
					if(map[tx][ty]=='.' && !check[tx][ty]){
						que.add(new Point(tx,ty));
						check[tx][ty] = true;
					}
					// 도착
					if(map[tx][ty]=='D'){
						
						return ;
					}
				}

			}			
			sec++;
		}
		sec = Integer.MAX_VALUE;
			
	}
	
	public static class Point{
		int x;
		int y; 
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
