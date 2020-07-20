import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
 * 문제
아기 상어가 성장해 청소년 상어가 되었다.

4×4크기의 공간이 있고, 크기가 1×1인 정사각형 칸으로 나누어져 있다. 
공간의 각 칸은 (x, y)와 같이 표현하며, x는 행의 번호, y는 열의 번호이다. 한 칸에는 물고기가 한 마리 존재한다. 각 물고기는 번호와 방향을 가지고 있다. 
번호는 1보다 크거나 같고, 16보다 작거나 같은 자연수이며, 두 물고기가 같은 번호를 갖는 경우는 없다. 방향은 8가지 방향(상하좌우, 대각선) 중 하나이다.

오늘은 청소년 상어가 이 공간에 들어가 물고기를 먹으려고 한다. 청소년 상어는 (0, 0)에 있는 물고기를 먹고, (0, 0)에 들어가게 된다. 상어의 방향은 (0, 0)에 있던 물고기의 방향과 같다. 이후 물고기가 이동한다.

물고기는 번호가 작은 물고기부터 순서대로 이동한다. 
물고기는 한 칸을 이동할 수 있고, 이동할 수 있는 칸은 빈 칸과 다른 물고기가 있는 칸, 이동할 수 없는 칸은 상어가 있거나, 공간의 경계를 넘는 칸이다. 
각 물고기는 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전시킨다. 만약, 이동할 수 있는 칸이 없으면 이동을 하지 않는다. 그 외의 경우에는 그 칸으로 이동을 한다. 
물고기가 다른 물고기가 있는 칸으로 이동할 때는 서로의 위치를 바꾸는 방식으로 이동한다.

물고기의 이동이 모두 끝나면 상어가 이동한다. 상어는 방향에 있는 칸으로 이동할 수 있는데, 한 번에 여러 개의 칸을 이동할 수 있다. 
상어가 물고기가 있는 칸으로 이동했다면, 그 칸에 있는 물고기를 먹고, 그 물고기의 방향을 가지게 된다. 이동하는 중에 지나가는 칸에 있는 물고기는 먹지 않는다. 물고기가 없는 칸으로는 이동할 수 없다. 
상어가 이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 간다. 상어가 이동한 후에는 다시 물고기가 이동하며, 이후 이 과정이 계속해서 반복된다.


상어가 먹을 수 있는 물고기 번호의 합의 최댓값을 구해보자.

입력
첫째 줄부터 4개의 줄에 각 칸의 들어있는 물고기의 정보가 1번 행부터 순서대로 주어진다. 물고기의 정보는 두 정수 ai, bi로 이루어져 있고, ai는 물고기의 번호, bi는 방향을 의미한다. 
방향 bi는 8보다 작거나 같은 자연수를 의미하고, 1부터 순서대로 ↑, ↖, ←, ↙, ↓, ↘, →, ↗ 를 의미한다.
 */
public class BOJ_19236 {
	
	static int dx[] = {0,-1,-1,0,1,1,1,0,-1};
	static int dy[] = {0,0,-1,-1,-1,0,1,1,1};
	static int sharkX, sharkY;
	static int ans = -10;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		ArrayList<Fishes> fishes = new ArrayList<Fishes>();
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				int num = sc.nextInt();
				int dir = sc.nextInt();
				fishes.add(new Fishes(num, dir, i, j));
			}	
		}
		ans = fishes.get(0).number;
		Collections.sort(fishes);
		run (fishes, 0,0,0,0);
		System.out.println(ans);
	}
	
	public static void run(ArrayList<Fishes> f, int x, int y, int dir, int sum){
		
		int res = isEat(f,x,y);
		if(res==-1){
			if(ans<sum){
				ans = sum;
			}
			return ;
		}
		
		int n = f.get(res).number;
		sum+=n;
		dir = f.get(res).dir;
		sharkX = f.get(res).x;
		sharkY = f.get(res).y;
		x = f.get(res).x;
		y = f.get(res).y;
		f.remove(res);
		
		moveFish(f);
		
		for(int i=1;i<=3;i++){
			
			ArrayList<Fishes> copy = new ArrayList<Fishes>();
			copy = copyArr(f);
		
			int nsX = x + i*dx[dir];
			int nsY = y + i*dy[dir];
			if(nsX<0 || nsY<0 || nsX>=4 || nsY>=4) continue;
			run(copy, nsX, nsY, dir, sum);
			
		}
		if(sum>ans) ans = sum;
	}
	public static ArrayList<Fishes> copyArr(ArrayList<Fishes> f){
		
		ArrayList<Fishes> copy = new ArrayList<Fishes>();
		
		for(int i=0;i<f.size();i++){
			Fishes fish = f.get(i);
			int x = fish.x;
			int y = fish.y;
			int dir = fish.dir;
			int number = fish.number;
			copy.add(new Fishes(number, dir, x, y));
		}
		
		return copy;
	}
	public static void moveFish(ArrayList<Fishes> f){
		
		Collections.sort(f);
		for(int i=1;i<=16;i++){
			Fishes fish = new Fishes(0,0,0,0);
			for(Fishes ftmp : f){
				if(i==ftmp.number) {
					fish = ftmp;
					break;
				}
			}
			if(fish.number==0) continue;
			int dir = fish.dir;
			while(true){
				
				int r = isMove(f, i, fish.x, fish.y, dir);
				
				if(r==-1){
					dir++;
					if(dir>8){
						dir = 1;
					}
				}
				else if(r==-2){
					int nx = fish.x + dx[dir];
					int ny = fish.y + dy[dir];
					fish.x = nx;
					fish.y = ny;
					fish.dir = dir;
					
					break;
				}
				
				else {

					int tmpNumber = fish.number;
					
					Fishes swapFish = f.get(r);
					
					fish.number = swapFish.number;
					fish.dir = swapFish.dir;
					
					swapFish.number = tmpNumber;
					swapFish.dir = dir;
					
					break;
				}
			}
			
		}
		
		
	}
	public static int isMove(ArrayList<Fishes> f, int idx, int x, int y, int dir){
		
		int nx = x+dx[dir];
		int ny = y+dy[dir];
		
		if(nx<0 || ny<0 || nx>=4 || ny>=4 || (nx==sharkX && ny==sharkY)) return -1;
		
		for(int i=0;i<f.size();i++){
			if(f.get(i).number==idx) continue;
			Fishes fish = f.get(i);
			if(nx==fish.x && ny==fish.y) return i;
		}
		
		return -2;
	}
	public static int isEat(ArrayList<Fishes> f, int x, int y){
		
		for(int i=0;i<f.size();i++){
			
			Fishes fish = f.get(i);
			if(fish.x == x && fish.y == y) return i;
			
		}
		
		
		return -1;
	}
	
	private static class Fishes implements Comparable<Fishes>{
		
		int number;
		int dir;
		int x;
		int y;
		
		Fishes(int number, int dir, int x, int y){
			this.number = number;
			this.dir = dir;
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Fishes o) {
			// TODO Auto-generated method stub
			return this.number - o.number;
		}
		
	}
}
