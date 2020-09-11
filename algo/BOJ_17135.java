import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 
 * @author kora1492
캐슬 디펜스
문제
캐슬 디펜스는 성을 향해 몰려오는 적을 잡는 턴 방식의 게임이다. 
게임이 진행되는 곳은 크기가 N×M인 격자판으로 나타낼 수 있다. 격자판은 1×1 크기의 칸으로 나누어져 있고, 
각 칸에 포함된 적의 수는 최대 하나이다. 격자판의 N번행의 바로 아래(N+1번 행)의 모든 칸에는 성이 있다.

성을 적에게서 지키기 위해 궁수 3명을 배치하려고 한다. 궁수는 성이 있는 칸에 배치할 수 있고, 하나의 칸에는 최대 1명의 궁수만 있을 수 있다. 각각의 턴마다 궁수는 적 하나를 공격할 수 있고, 모든 궁수는 동시에 공격한다. 
궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고, 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격한다. 
같은 적이 여러 궁수에게 공격당할 수 있다. 공격받은 적은 게임에서 제외된다. 
궁수의 공격이 끝나면, 적이 이동한다. 적은 아래로 한 칸 이동하며, 성이 있는 칸으로 이동한 경우에는 게임에서 제외된다. 모든 적이 격자판에서 제외되면 게임이 끝난다. 

게임 설명에서 보다시피 궁수를 배치한 이후의 게임 진행은 정해져있다. 따라서, 이 게임은 궁수의 위치가 중요하다. 격자판의 상태가 주어졌을 때, 궁수의 공격으로 제거할 수 있는 적의 최대 수를 계산해보자.

격자판의 두 위치 (r1, c1), (r2, c2)의 거리는 |r1-r2| + |c1-c2|이다.

입력
첫째 줄에 격자판 행의 수 N, 열의 수 M, 궁수의 공격 거리 제한 D가 주어진다. 
둘째 줄부터 N개의 줄에는 격자판의 상태가 주어진다. 0은 빈 칸, 1은 적이 있는 칸이다.

출력
첫째 줄에 궁수의 공격으로 제거할 수 있는 적의 최대 수를 출력한다.
 */
public class BOJ_17135 {
	static int N, M, D;
	static int Delcnt = 0;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
	    int map[][]	 = new int [N][M];
	    int copyMap[][]	 = new int [N][M];
	    
	    
		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++){
				map[i][j] = Integer.parseInt(st.nextToken());

			}
		}	
		for(int i=0;i<N;i++){
			
			for(int j=i+1;j<N;j++){
				
				for(int k=j+1;k<N;k++){
					copyMap = copyMap(map);
					run(map, i, j, k);
					
				}
			}
			
		}
	}
	
	public static int [][] copyMap(int [][] map){
		int copyMap[][] = new int [N][M];
		for(int i=0;i<N;i++){
			
			for(int j=0;j<M;j++){
				copyMap[i][j] = map[i][j];
			}
		}
		
		return copyMap;
		
	}
	
	public static void run(int map[][], int a1, int a2, int a3){
		ArrayList<Point> anemy = new ArrayList<Point>();
		// check - 적이 있는지
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				if(map[i][j]==1){
					anemy.add(new Point(i, j, 0));
				}
			}
		}
		
		while(!anemy.isEmpty()){
			
		}
	    // Attack
		// Down

		
	}
	public static void attack(ArrayList<Point> anemy, int a1, int a2, int a3){
		
		ArrayList<Point> attackA1 = new ArrayList<Point>();		
		Collections.sort(anemy);
		
		
	}
	private static class Point implements Comparable<Point>{
		
		int x;
		int y;
		int d;
		Point(int x,int y,int d){
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			if(o.d > this.d){
				return 1;
			}
			else if(o.d==this.d){
				if(o.x > this.x){
					return 1;
				}
				else {
					return -1;
				}
			}
			else {
				return -1;
			}
		}
		
	}
}
