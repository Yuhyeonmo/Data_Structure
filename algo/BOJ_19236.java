import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
 * 문제 풀이 
 * sw test 당시와 비슷하게 품.

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
		// 초기값을 넣음 = 맨 처음 시작하는 위치는 잡히고 시작
		ans = fishes.get(0).number;
		// 숫자대로 정렬한번해주고,
		Collections.sort(fishes);
		// DFS 재귀 실행
		run (fishes, 0,0,0,0);
		
		System.out.println(ans);
	}
	
	public static void run(ArrayList<Fishes> f, int x, int y, int dir, int sum){
		
		// 먼저 먹을 수 있는지 확인
		int res = isEat(f,x,y);
		// -1, 먹을 수 없다. 재귀 종료
		if(res==-1){
			// ans 값보다 현재의 sum이 더 크면 ans값을 변경
			if(ans<sum){
				ans = sum;
			}
			return ;
		}
		// 물고기 번호를 구해서 sum에 더해줌.
		int n = f.get(res).number;
		sum+=n;
		// 방향을 해당 물고기의 x,y 및 방향으로 설정
		dir = f.get(res).dir;
		sharkX = f.get(res).x;
		sharkY = f.get(res).y;
		x = f.get(res).x;
		y = f.get(res).y;
		// 리스트에서 제거
		f.remove(res);
		
		// 2. 물고기들의 이동
		moveFish(f);
		
		for(int i=1;i<=3;i++){
			
			ArrayList<Fishes> copy = new ArrayList<Fishes>();
			// 물고기 리스트가 재귀돌면서 변경되는 것을 막을려고 값을 복사한 copy 로 재귀 실행
			copy = copyArr(f);
		
			int nsX = x + i*dx[dir];
			int nsY = y + i*dy[dir];
			if(nsX<0 || nsY<0 || nsX>=4 || nsY>=4) continue;
			run(copy, nsX, nsY, dir, sum);
			
		}
		// 해당 위치에서 이동가능한 위치가 없을 경우를 대비해서 만듦
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
		
		// 1번부터 순서대로 이동
		for(int i=1;i<=16;i++){
			Fishes fish = new Fishes(0,0,0,0);
			for(Fishes ftmp : f){
				if(i==ftmp.number) {
					fish = ftmp;
					break;
				}
			}
			// 이미 잡힌 물고기인 경우
			if(fish.number==0) continue;
			int dir = fish.dir;
			while(true){
				// 이동여부 확인 3
				int r = isMove(f, i, fish.x, fish.y, dir);
				// 해당위치가 범위가 밖이거나 상어인 경우 방향을 회전시킴.
				if(r==-1){
					dir++;
					// 8보다 커지면 맨처음으로
					if(dir>8){
						dir = 1;
					}
				}
				// 빈칸 이동
				else if(r==-2){
					int nx = fish.x + dx[dir];
					int ny = fish.y + dy[dir];
					fish.x = nx;
					fish.y = ny;
					fish.dir = dir;
					
					break;
				}
				// swap , 해당 위치의 물고기 번호와 방향만 재설정해서 바꾼다.
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
		
		// 다음 위치가 이동할 수 없는 위치인 경우 -1
		if(nx<0 || ny<0 || nx>=4 || ny>=4 || (nx==sharkX && ny==sharkY)) return -1;
		
		// 해당위치에 물고기가 있는지 찾음.
		for(int i=0;i<f.size();i++){
			if(f.get(i).number==idx) continue;
			Fishes fish = f.get(i);
			if(nx==fish.x && ny==fish.y) return i;
		}
		// 이동할 수는 있지만, 해당 위치가 빈칸인 경우
		return -2;
	}
	
	public static int isEat(ArrayList<Fishes> f, int x, int y){
		// 해당 위치에 물고기가 있는지 확인, 있으면 해당 인덱스를 리턴하도록
		for(int i=0;i<f.size();i++){
			
			Fishes fish = f.get(i);
			if(fish.x == x && fish.y == y) return i;
			
		}
		
		// 없으면 -1
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
