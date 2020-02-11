import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author YuhyeonMo
 * 
입력
첫째 줄에 R, C, T (6 ≤ R, C ≤ 50, 1 ≤ T ≤ 1,000) 가 주어진다.

둘째 줄부터 R개의 줄에 Ar,c (-1 ≤ Ar,c ≤ 1,000)가 주어진다. 공기청정기가 설치된 곳은 Ar,c가 -1이고, 나머지 값은 미세먼지의 양이다. -1은 2번 위아래로 붙어져 있고, 가장 윗 행, 아랫 행과 두 칸이상 떨어져 있다.

출력
첫째 줄에 T초가 지난 후 구사과 방에 남아있는 미세먼지의 양을 출력한다.

풀이과정 : 그냥 마구잡이로 품.
확산 => 미세먼지들에 대해서 4방향으로 -1이 아니고 이차원배열 범위 안에 든 경우 확산의 규칙에 따라 확산
공기청정기 => 위쪽 , 아래쪽 구분해서 경우에 따라 이동시킴.

문제점 : 풀이 시간이 너무 오래 걸림 / 공기 청정기 이동 로직을 너무 마구잡이로 짜다보니 틀린 부분을 찾기가 힘들었음.
      
 */
public class BOJ_17144 {
	
	static int R,C,T;
	static int map[][];
	static int dx[] = {1,-1,0,0};
	static int dy[] = {0,0,-1,1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();
		
		map = new int [R][C];
		ArrayList<Point> air = new ArrayList<Point>();
		ArrayList<Point> mise = new ArrayList<Point>();
		for(int i=0;i<R;i++){
			
			for(int j=0;j<C;j++){
				int input = sc.nextInt();
				map[i][j] = input;
				if(input==-1){
					air.add(new Point(i,j,-1));
				} else if (input!=0){
					mise.add(new Point(i,j,input));
				}
			}
		}
		int sum = 0;
		
		while(true){
			if(T==0){
				for(Point idx : mise){
					sum+= idx.v;
				}
				System.out.println(sum);
				break;
			}
			mise = Spread(mise);
			mise = wind(air, mise);
			T--;
		}
	}
	public static int [][] copyMap(int [][] map){
		int copy [][] = new int [R][C];
		for(int i=0;i<R;i++){
			for(int j=0;j<C;j++){
				copy[i][j] = map[i][j];
			}
		}
		return copy;
	}
	
	public static ArrayList<Point> wind(ArrayList<Point> air, ArrayList<Point> mise){
		int upX = air.get(0).x;
		int upY = air.get(0).y;
		
		int downX = air.get(1).x;
		int downY = air.get(1).y;
		
		int [][] copy = new int [R][C];
		ArrayList<Point> newMise = new ArrayList<Point>();
		copy[upX][upY] = -1;
		copy[downX][downY] = -1;
//		int count = 0;
		for(Point idx : mise){
//			count++;

			int x = idx.x;
			int y = idx.y;
			if(x<=upX){
			if(x==upX){
				if(y==C-1){
					copy[x-1][y] = map[x][y];				
				}
				else if((y+1)!=upY){
					 copy[x][y+1] = map[x][y];				 
				} 
			}
			else if(x < upX && y==C-1){
				if(x==0) {
					copy[x][y-1] = map[x][y];
					
				}
				else if(x-1>=0){
					copy[x-1][y] = map[x][y];
					
				}
			} else if(x==0){
				if(y==upY){
					copy[x+1][y] = map[x][y]; 
				}
				else {
				copy[x][y-1] = map[x][y];
				}
				
			}  
			else if(x< upX && y==upY){
				if(x+1==upX) {
					
				} else {
					copy[x+1][y] = map[x][y];
					
				}
			} 
			else {
				copy[x][y] = map[x][y];
			}
			}
			else{
			if(x==downX){
				if(y==C-1){
					copy[x+1][y] = map[x][y];
					
				}
				else if((y+1)!=upY){
					 copy[x][y+1] = map[x][y];
					 
				} 
			}
			else if(x > downX && y==C-1){
				if(x==R-1) {
					copy[x][y-1] = map[x][y];
					
				}
				else if(x+1<R){
					copy[x+1][y] = map[x][y];
					
				}
			} else if(x==R-1 && y!=upY){
				copy[x][y-1] = map[x][y];
				
			} else if(x> downX && y==downY){
				if(x-1==downX) {
					
				} else {
					copy[x-1][y] = map[x][y];
					
				}
			}
			else {
				copy[x][y] = map[x][y];
			}
			}
		}
		map = copyMap(copy);
		for(int i=0;i<R;i++){
			for(int j=0;j<C;j++){
				
//				System.out.print(map[i][j]+" ");
				if(map[i][j]!=0 && map[i][j]!=-1){
					
					newMise.add(new Point(i,j,map[i][j]));
				}
			}
//			System.out.println();
		}
		
		return newMise;
	}
	
	
	
	public static ArrayList<Point> Spread(ArrayList<Point> mise){
		int copy[][] = new int [R][C];
		ArrayList<Point> newMise = new ArrayList<Point>();
		for(Point idx : mise){
			int cnt = 0;
			int v = 0;
			v = map[idx.x][idx.y] / 5;
			for(int i=0;i<4;i++){
				int x = idx.x - dx[i];
				int y = idx.y - dy[i];
				
				if(x>=0 && y>=0 && x<R && y<C && map[x][y]!=-1){
					copy[x][y] += v;
					cnt++;
				}
			}
			copy [idx.x][idx.y] = copy [idx.x][idx.y] - v * cnt;
			if(copy[idx.x][idx.y]<0){
				copy[idx.x][idx.y] = 0;
			}
		}
		
		map = copyMap(copy);
		
		for(int i=0;i<R;i++){
			for(int j=0;j<C;j++){
				if(map[i][j]!=0 && map[i][j]!=-1){
					newMise.add(new Point(i,j,map[i][j]));
				}
			}
		}
		
		return newMise;
	}
	
	public static class Point {
		int x;
		int y;
		int v;
		
		Point(int x, int y, int v){
			this.x = x;
			this.y = y;
			this.v = v;
		}
	}

}
