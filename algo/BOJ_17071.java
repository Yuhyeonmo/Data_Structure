import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author kora1492
문제
수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 500,000)에 있고, 동생은 점 K(0 ≤ K ≤ 500,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다. 동생은 항상 걷기만 한다. 
동생은 항상 매 초마다 이동을 하며, 이동은 가속이 붙는다. 동생이 이동하는 거리는 이전에 이동한 거리보다 1을 더한 만큼 이동한다. 
즉, 동생의 처음 위치는 K, 1초가 지난 후 위치는 K+1, 2초가 지난 후 위치는 K+1+2, 3초가 지난 후의 위치는 K+1+2+3이다.

수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오. 동생을 찾는 위치는 정수 좌표이어야 하고, 수빈이가 0보다 작은 좌표로, 50만보다 큰 좌표로 이동하는 것은 불가능하다.

입력
첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.

출력
수빈이가 동생을 찾는 가장 빠른 시간을 출력한다. 수빈이가 동생을 찾을 수 없거나, 찾는 위치가 500,000을 넘는 경우에는 -1을 출력한다.
 */
public class BOJ_17071 {
	 
	static int N,K;
	static int [][] check = new int [500001][2];
	static int time =0;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		if(N!=K){
			bfs();
			System.out.println(time);
		}
		else {
			System.out.println(0);
		}
	}
	public static void bfs(){
		Queue<Integer> su = new LinkedList<Integer>();
		su.add(N);
		int now = 0;
		while(!su.isEmpty()){
			int size = su.size();
			time++;
			int mod = time%2;
			
			for(int i=0;i<size;i++){
				
				now = su.poll();
				if(now+1<500001 && check[now+1][mod]==0){
					su.add(now+1);
					check[now+1][mod]= time;
				}
				if(now-1>=0 && check[now-1][mod]==0){
					su.add(now-1);
					check[now-1][mod]= time;
				}
				if(now*2<500001 && check[now*2][mod]==0){
					su.add(now*1);
					check[now*2][mod]= time;
				}
				
			}
			int young = K+sum(time);
			if(young>500000){
				time = -1;
				return ;
			}
			if(check[young][mod]!=0){			
				return ;
			}
		
		}
		time = -1;

	}
	public static int sum(int t){	
		return K+(t+1)/2;
	}
	public static class Point {
		int x;
		int time;
		
		Point(int x, int t){
			this.x = x;
			this.time = t;
		}
	}
}
