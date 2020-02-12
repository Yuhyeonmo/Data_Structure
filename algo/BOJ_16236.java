import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;



/**
 * 
 * @author Yuhyeonmo
N×N 크기의 공간에 물고기 M마리와 아기 상어 1마리가 있다. 공간은 1×1 크기의 정사각형 칸으로 나누어져 있다. 한 칸에는 물고기가 최대 1마리 존재한다.

아기 상어와 물고기는 모두 크기를 가지고 있고, 이 크기는 자연수이다. 가장 처음에 아기 상어의 크기는 2이고, 아기 상어는 1초에 상하좌우로 인접한 한 칸씩 이동한다.

아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 모두 지나갈 수 있다. 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다. 따라서, 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 칸은 지나갈 수 있다.

아기 상어가 어디로 이동할지 결정하는 방법은 아래와 같다.

더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
아기 상어의 이동은 1초 걸리고, 물고기를 먹는데 걸리는 시간은 없다고 가정한다. 즉, 아기 상어가 먹을 수 있는 물고기가 있는 칸으로 이동했다면, 이동과 동시에 물고기를 먹는다. 물고기를 먹으면, 그 칸은 빈 칸이 된다.

아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다. 예를 들어, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이 된다.

공간의 상태가 주어졌을 때, 아기 상어가 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 공간의 크기 N(2 ≤ N ≤ 20)이 주어진다.

둘째 줄부터 N개의 줄에 공간의 상태가 주어진다. 공간의 상태는 0, 1, 2, 3, 4, 5, 6, 9로 이루어져 있고, 아래와 같은 의미를 가진다.

0: 빈 칸
1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
9: 아기 상어의 위치
아기 상어는 공간에 한 마리 있다.
소요 시간 : 1시간 30분 ~ 50분 
 */
public class BOJ_16236 {
	static int map[][];
	static int N;
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,-1,0,1};
	static int eatCount = 0;
	static int time=0;
	static int flag=0;
	public static void main(String args[]){
	
	Scanner sc = new Scanner(System.in);
	
	N = sc.nextInt();
	map = new int [N][N];
	int sharkX = 0;
	int sharkY = 0;
	ArrayList<Fish> fishes = new ArrayList<Fish>();
	Fish shrak = null;
	for(int i=0;i<N;i++){
		for(int j=0;j<N;j++){
			map[i][j] = sc.nextInt();
			if(map[i][j]==9){
				shrak = new Fish(i,j,2);
				map[i][j] = 0;
			} else if(map[i][j]!=0) {
				fishes.add(new Fish(i,j,map[i][j]));
			}
		}
	}
	
	if(fishes.size()==0){
		System.out.println("0");
	} else {
		while(isEat(shrak)){
			shrak = choice(shrak);
			if(flag==1){
				break;
			}
		}
		System.out.println(time);
		
	}
	
	}
	
	
	public static Fish choice(Fish Shark){
		Queue<Fish> que = new LinkedList<Fish>();
		boolean [][] checked = new boolean [N][N];
		int size = Shark.size;
		ArrayList<Fish> eatFish = new ArrayList<Fish>();
		que.add(Shark);
		checked [Shark.x][Shark.y] = true;
		int step = 1;
		while(!que.isEmpty()){
			int queSize = que.size();
			

			int flag = 0;
			
			while(queSize>0){
			Fish idx = que.poll();
			int x = idx.x;
			int y = idx.y;
			
			for(int i=0;i<4;i++){
				int tx = x-dx[i];
				int ty = y-dy[i];
				if(tx>=0 && ty>=0 && tx<N && ty<N && !checked[tx][ty] && map[tx][ty]<=size){
					if(map[tx][ty]==0){
						checked[tx][ty] = true;
						que.add(new Fish(tx,ty, size));
					}
					else if(map[tx][ty]<size){
						eatFish.add(new Fish(tx,ty, step));
						checked[tx][ty] = true;
					} else {
						checked[tx][ty] = true;
						que.add(new Fish(tx,ty, size));
					}
				}
			}
			queSize--;
		}
			step++;
	
		}
		Fish newShark = null;
		if(eatFish.size()>0){
		Collections.sort(eatFish);
		time+=eatFish.get(0).size;
		map[eatFish.get(0).x][eatFish.get(0).y] = 0;
		eatCount++;
		if(eatCount == size) {
			size++;
			eatCount = 0;
		}

		newShark = new Fish(eatFish.get(0).x, eatFish.get(0).y, size);
		}
		else {
			flag =1;
		}
		
		
		return newShark;
		
	}
	public static boolean isEat(Fish Shark){
		int size = Shark.size;
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(size>map[i][j]){
					return true;
				}
			}
		}
		return false;
	}
	
	public static class Fish implements Comparable<Fish> {
		int x;
		int y;
		int size;
		
		Fish(int x, int y, int size){
			this.x = x;
			this.y = y;
			this.size = size;
		}

		@Override
		public int compareTo(Fish o) {
			// TODO Auto-generated method stub
		if(this.size>o.size){	
			return 1;
		} else if(this.size==o.size){
			if(this.x > o.x){
				return 1;
			} else if(this.x==o.x){
				if(this.y>o.y) {
					return 1;
				}
				else {
					return -1;
				}			
			} else {
				
				return -1;
			}
		}
		
		else {
			return -1;
		}
		
		
	}
	}
}

