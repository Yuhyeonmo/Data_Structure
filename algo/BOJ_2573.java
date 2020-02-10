import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author YuhyeonMo
 *
지구 온난화로 인하여 북극의 빙산이 녹고 있다. 빙산을 그림 1과 같이 2차원 배열에 표시한다고 하자. 
빙산의 각 부분별 높이 정보는 배열의 각 칸에 양의 정수로 저장된다. 
빙산 이외의 바다에 해당되는 칸에는 0이 저장된다. 그림 1에서 빈칸은 모두 0으로 채워져 있다고 생각한다.

그림 1. 행의 개수가 5이고 열의 개수가 7인 2차원 배열에 저장된 빙산의 높이 정보

빙산의 높이는 바닷물에 많이 접해있는 부분에서 더 빨리 줄어들기 때문에, 배열에서 빙산의 각 부분에 해당되는 칸에 있는 높이는 일년마다 그 칸에 동서남북 네 방향으로 붙어있는 0이 저장된 칸의 개수만큼 줄어든다. 단, 각 칸에 저장된 높이는 0보다 더 줄어들지 않는다. 바닷물은 호수처럼 빙산에 둘러싸여 있을 수도 있다. 따라서 그림 1의 빙산은 일년후에 그림 2와 같이 변형된다.

그림 3은 그림 1의 빙산이 2년 후에 변한 모습을 보여준다. 2차원 배열에서 동서남북 방향으로 붙어있는 칸들은 서로 연결되어 있다고 말한다. 따라서 그림 2의 빙산은 한 덩어리이지만, 그림 3의 빙산은 세 덩어리로 분리되어 있다.



한 덩어리의 빙산이 주어질 때, 이 빙산이 두 덩어리 이상으로 분리되는 최초의 시간(년)을 구하는 프로그램을 작성하시오. 그림 1의 빙산에 대해서는 2가 답이다. 만일 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않으면 프로그램은 0을 출력한다.

첫 줄에는 이차원 배열의 행의 개수와 열의 개수를 나타내는 두 정수 N과 M이 한 개의 빈칸을 사이에 두고 주어진다. N과 M은 3 이상 300 이하이다. 그 다음 N개의 줄에는 각 줄마다 배열의 각 행을 나타내는 M개의 정수가 한 개의 빈 칸을 사이에 두고 주어진다. 각 칸에 들어가는 값은 0 이상 10 이하이다. 배열에서 빙산이 차지하는 칸의 개수, 즉, 1 이상의 정수가 들어가는 칸의 개수는 10,000 개 이하이다. 배열의 첫 번째 행과 열, 마지막 행과 열에는 항상 0으로 채워진다.


첫 줄에 빙산이 분리되는 최초의 시간(년)을 출력한다. 만일 빙산이 다 녹을 때까지 분리되지 않으면 0을 출력한다.

1. 빙산을 줄임.
2. 빙산 덩어리 확인. 2개 이상으로 분리되면 종료. 횟수 출력. 
   => BFS로 탐색하면서, 빙산의 갯수와 탐색한 횟수를 비교해서 같으면 한 덩어리 다를 경우는 한 덩어리가 아닌 경우로 찾음. 
-- 효율성 측면에서 너무 구리게 품....
   런타임 에러가 났는데, ArrayList가 빈 경우에도 get(i)를 써서 넣을려고 했기 때문에 발생함. 주의
  map[][] 전역변 수인데, ice 녹이고 높이를 내렸는데, tmap이 줄어들어도 전역으로 되어 있는 map의 값도 같이 바뀜. 
  자바는 call by value 이지만, 참조자료변수를 쓸 때는 value에 레퍼런스값을 직접 넣어서 넘긴다고 함?! 그래서 같이 바뀌는 듯 하다.
  다음에 풀 때 조금 더 최적화하면서 하는 풀이가 필요 할듯.
 */
public class BOJ_2573 {
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int N,M;
	
	static int map[][];
	static int res; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		res = 0;
		map = new int [N][M];
		
		ArrayList<Point> list = new ArrayList<Point>();
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				map[i][j] = sc.nextInt();
				if(map[i][j]!=0){
					list.add(new Point(i,j));
				}
			}
		}
		
		istwo(list);
		
		System.out.println(res);
		
	}
	
	public static class Point {
		int x;
		int y;
		
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void istwo(ArrayList<Point> xy){
		Queue<Point> que = new LinkedList<Point>();
		boolean [][] check = new boolean [N][M];
        if(xy.size()==0){
			res = 0;
			return ;
		}
		que.add(xy.get(0));
		int cnt = xy.size();
		int tcnt = 0;

		while(!que.isEmpty()){
			
			Point idx = que.poll();
			int x = idx.x;
			int y = idx.y;
			check[x][y] = true;
			for(int i=0;i<4;i++){
				int tx = x-dx[i];
				int ty = y-dy[i];
				if(tx>=0 && ty>=0 && tx<map.length && ty<map[0].length && !check[tx][ty] && map[tx][ty] !=0){
					check[tx][ty] = true;
					que.add(new Point(tx,ty));
				}	
			}
			tcnt++;
		}
		
		if(cnt!=tcnt){
			return ;
		} else {
			ArrayList<Point> list = new ArrayList<Point>();
			int copy[][] = new int [N][M];
			copy = copyMap(map);
			list = ice(copy, xy);
			
			istwo(list);
		}
	}
	public static int [][] copyMap(int [][] tmap){
		int copy[][] = new int [N][M];
		
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				copy[i][j] = tmap[i][j];
			}
		}
		return copy;
	}
	public static ArrayList<Point> ice(int tmap[][], ArrayList<Point> xy){
		ArrayList<Point> list = new ArrayList<Point>();
		
		for(Point tmp : xy){
			int cnt = 0;
			for(int i=0;i<4;i++){
				int x = tmp.x - dx[i];
				int y = tmp.y - dy[i];
				
				if(x>=0 && y>=0 && x<map.length && y<map[0].length && map[x][y]==0){
					cnt++;
				}			
			}
			if(tmap[tmp.x][tmp.y]>cnt){
				list.add(tmp);
				tmap[tmp.x][tmp.y] -= cnt;
			} else {
				tmap[tmp.x][tmp.y]  = 0;
			}
		}
		
		
		map = copyMap(tmap);
		res++;
		return list;
	}

}
