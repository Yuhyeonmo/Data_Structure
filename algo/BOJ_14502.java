import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author YuHyeonmo
 * 문제
인체에 치명적인 바이러스를 연구하던 연구소에서 바이러스가 유출되었다. 다행히 바이러스는 아직 퍼지지 않았고, 바이러스의 확산을 막기 위해서 연구소에 벽을 세우려고 한다.

연구소는 크기가 N×M인 직사각형으로 나타낼 수 있으며, 직사각형은 1×1 크기의 정사각형으로 나누어져 있다. 연구소는 빈 칸, 벽으로 이루어져 있으며, 벽은 칸 하나를 가득 차지한다. 

일부 칸은 바이러스가 존재하며, 이 바이러스는 상하좌우로 인접한 빈 칸으로 모두 퍼져나갈 수 있다. 새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.

예를 들어, 아래와 같이 연구소가 생긴 경우를 살펴보자.

2 0 0 0 1 1 0
0 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 0 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0
이때, 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 곳이다. 아무런 벽을 세우지 않는다면, 바이러스는 모든 빈 칸으로 퍼져나갈 수 있다.

2행 1열, 1행 2열, 4행 6열에 벽을 세운다면 지도의 모양은 아래와 같아지게 된다.

2 1 0 0 1 1 0
1 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 1 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0
바이러스가 퍼진 뒤의 모습은 아래와 같아진다.

2 1 0 0 1 1 2
1 0 1 0 1 2 2
0 1 1 0 1 2 2
0 1 0 0 0 1 2
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0
벽을 3개 세운 뒤, 바이러스가 퍼질 수 없는 곳을 안전 영역이라고 한다. 위의 지도에서 안전 영역의 크기는 27이다.

연구소의 지도가 주어졌을 때 얻을 수 있는 안전 영역 크기의 최댓값을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 8)

둘째 줄부터 N개의 줄에 지도의 모양이 주어진다. 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치이다. 2의 개수는 2보다 크거나 같고, 10보다 작거나 같은 자연수이다.

빈 칸의 개수는 3개 이상이다.

출력
첫째 줄에 얻을 수 있는 안전 영역의 최대 크기를 출력한다.

소요 시간 : 1시간 가량
풀이  : 
완전탐색 / BFS 
바이러스와 벽을 세울 수 있는 것을 ArrayList에 추가.
1. 3중 for문을 통해서 3개를 추출(벽 세울 수 있는 곳) 
2. 추출한 인덱스들을 통해 copyMap에다가 벽을 세운 뒤, Virus들에 대해서 모두 BFS를 돌리면서, 값을 2로 변경
3. 끝난 다음, copyMap에 0의 개수와 max값을 구해서 max값을 찾음. 
이 과정을 1로 돌아간 다음 계속 돌림. 1의 for문이 끝나면 실행이 끝나고 max값을 출력하도록 구현. 

 */
public class BOJ_14502 {
	static ArrayList<Point> wall = new ArrayList<Point>(); //  벽 세울 수 있는 거
	static ArrayList<Point> virus = new ArrayList<Point>();
	static int N;
	static int M;
	static int map[][];
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	static int max = 0;
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		
		map = new int [N][M];
		sc.nextLine();
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				map[i][j] = sc.nextInt();
				if(map[i][j]==2){
					virus.add(new Point(i,j));
				}
				if(map[i][j]==0){
					wall.add(new Point(i,j));
				}
			}
		}
		int size=wall.size();
		for(int i=0;i<size;i++){
			for(int j=i+1;j<size;j++){
				for(int k=j+1;k<size;k++){
					count(i,j,k);
				}
			}
		}
		
		System.out.println(max);
	}
	
	public static void count(int i, int j, int k)
	{
		int copyMap[][] = new int [N][M];
		for(int h=0;h<N;h++){
			for(int g=0;g<M;g++){
				copyMap[h][g] = map[h][g];
			}
		}
		copyMap[wall.get(i).i][wall.get(i).j] = 1;
		copyMap[wall.get(j).i][wall.get(j).j] = 1;
		copyMap[wall.get(k).i][wall.get(k).j] = 1;
		
		for(int c=0;c<virus.size();c++){
			bfs(virus.get(c), copyMap);
		}
		int cnt = 0;
		for(int h=0;h<N;h++){
			for(int g=0;g<M;g++){
				if(copyMap[h][g]==0){
					cnt++;
				}
			}
		}
		if(cnt>max){
			max = cnt;
		}
	}
	public static void bfs(Point t, int tmpMap[][]){
		Queue<Point> que = new LinkedList<Point>();
		que.add(t);
		
		while(!que.isEmpty()){
			Point idx = que.poll();
			
			int x = idx.i;
			int y = idx.j;
			
			for(int i=0;i<4;i++){
				int tx = x - dx[i];
				int ty = y - dy[i];
				
				if(tx<0 || ty<0 || tx>=N || ty>=M){
					continue;
				}
				else {
					if(tmpMap[tx][ty]==0){
						tmpMap[tx][ty] = 2;
						que.add(new Point(tx,ty));
					}
				}
			}
		}
	}
	
	
	public static class Point {
		int i; 
		int j;
		
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
}
