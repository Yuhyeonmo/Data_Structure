import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * @author kora1492
 * 
1번 CCTV는 한 쪽 방향만 감시할 수 있다. 2번과 3번은 두 방향을 감시할 수 있는데, 2번은 감시하는 방향이 서로 반대방향이어야 하고, 3번은 직각 방향이어야 한다. 4번은 세 방향, 5번은 네 방향을 감시할 수 있다.

CCTV는 감시할 수 있는 방향에 있는 칸 전체를 감시할 수 있다. 사무실에는 벽이 있는데, CCTV는 벽을 통과할 수 없다. CCTV가 감시할 수 없는 영역은 사각지대라고 한다.

CCTV는 회전시킬 수 있는데, 회전은 항상 90도 방향으로 해야 하며, 감시하려고 하는 방향이 가로 또는 세로 방향이어야 한다.
4 6
0 0 0 0 0 0
0 0 0 0 0 0
0 0 1 0 6 0
0 0 0 0 0 0

20

첫째 줄에 사무실의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 8)

둘째 줄부터 N개의 줄에는 사무실 각 칸의 정보가 주어진다. 0은 빈 칸, 6은 벽, 1~5는 CCTV를 나타내고, 문제에서 설명한 CCTV의 종류이다. 

CCTV의 최대 개수는 8개를 넘지 않는다.
 */
public class BOJ_15683 {
	static int map[][];
	static int N, M;
	static int size;
	static int min = Integer.MAX_VALUE;
	static ArrayList<CCTV> cctv = new ArrayList<CCTV>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int [N][M];
		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++){
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]!=0 && map[i][j]!=6){
					cctv.add(new CCTV(map[i][j], i, j));
				}
			}
		}
		size = cctv.size();
		calc(0);
		System.out.println(min);
	}
	public static void calc(int n){
		if(n==size){
			int cnt = count();
			if(min>cnt){
				min = cnt;
			}
			return ;
		}
		
		for(int i=0;i<4;i++){
			cctv.get(n).d = i;
			calc(n+1);
		}
	}
	public static int count(){
		int tmp [][] = new int [N][M];
		for(CCTV c : cctv){
			tmp[c.r][c.c] = -1;
			if(c.type==1){	
				if(c.d==0){
					see(tmp, c.r, c.c, 0);
				} else if(c.d==1){
					see(tmp, c.r, c.c, 1);
				} else if(c.d==2){
					see(tmp, c.r, c.c, 2);
				} else if(c.d==3){
					see(tmp, c.r, c.c, 3);
				}
			}
			else if(c.type==2){
				if(c.d==1 || c.d==3){
					see(tmp, c.r, c.c, 1);
					see(tmp, c.r, c.c, 3);

				} else if(c.d==0  || c.d==2){
					see(tmp, c.r, c.c, 0);
					see(tmp, c.r, c.c, 2);
				}
			} 
			else if(c.type==3){
				if(c.d==0){
					see(tmp, c.r, c.c, 0);
					see(tmp, c.r, c.c, 1);

				} else if(c.d==1){
					see(tmp, c.r, c.c, 1);
					see(tmp, c.r, c.c, 2);

				} else if(c.d==2){
					see(tmp, c.r, c.c, 2);
					see(tmp, c.r, c.c, 3);

				} else if(c.d==3){
					see(tmp, c.r, c.c, 3);
					see(tmp, c.r, c.c, 0);
				}
			} 
			else if(c.type==4){
				if(c.d==0){
					see(tmp, c.r, c.c, 0);
					see(tmp, c.r, c.c, 1);
					see(tmp, c.r, c.c, 3);

				} else if(c.d==1){
					see(tmp, c.r, c.c, 1);
					see(tmp, c.r, c.c, 0);
					see(tmp, c.r, c.c, 2);


				} else if(c.d==2){
					see(tmp, c.r, c.c, 2);
					see(tmp, c.r, c.c, 1);
					see(tmp, c.r, c.c, 3);

				} else if(c.d==3){
					see(tmp, c.r, c.c, 3);
					see(tmp, c.r, c.c, 2);
					see(tmp, c.r, c.c, 0);

				}
			}
			else if(c.type==5){
				see(tmp, c.r, c.c, 0);
				see(tmp, c.r, c.c, 1);
				see(tmp, c.r, c.c, 2);
				see(tmp, c.r, c.c, 3);
			}		
		}
		
		int count =0;
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				if(tmp[i][j]==0 && map[i][j]!=6){
					count++;
				}
			}
		}
		
		return count;
	}
	
	public static void see(int [][] arr, int r, int c, int d){
		if(d==0){
			for(int i=c;i>=0;i--){
				if(map[r][i]==6){
					break;
				} else {
					arr[r][i] = -1;
				}
			}
		} else if(d==1){
			for(int i=r;i>=0;i--){
				if(map[i][c]==6){
					break;
				} else {
					arr[i][c] = -1;
				}
			}
		} else if(d==2) {
			for(int i=c;i<M;i++){
				if(map[r][i]==6){
					break;
				} else {
					arr[r][i] = -1;
				}
			}
		} else if(d==3) {
			for(int i=r;i<N;i++){
				if(map[i][c]==6){
					break;
				} else {
					arr[i][c] = -1;
				}
			}
		}
	}
	
	static class CCTV{
		int type;
		int d;
		int r;
		int c;
		
		CCTV(int t, int r, int c){
			this.type = t;
			this.r = r;
			this.c = c;
		}
	}

}
