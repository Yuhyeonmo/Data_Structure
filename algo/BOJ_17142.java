import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * 
 * @author YuHyeonMo
 * 
 * 문제 : 연구소 3 
 * 풀이 소요시간 : 1시간 가량
 * 조합을 사용함. 
 * 놓을 수 있는 위치를 리스트에 저장하고, 이 리스트에서 M개 만큼 뽑는 조합
 * 그리고 그 조합 리스트를 BFS에 넣고 탐색 돌리면서, 빈칸이 얼마나 남았는 지 확인하고 빈칸을 다지운 경우의 시간과 현재 최소 시간을 비교해서 최솟값을 찾아나감.
 * 
 * 엄청 큰 건 아니지만, 입력 받는 부분을 Scanner 를 사용하는 것보다, BufferReader를 사용하자.
 *
 */
public class BOJ_17142 {

	static int N;
	static int M;
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	static int map[][];
	static ArrayList<Point> virus;
	static int empty=0;
	static int minTime = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		virus = new ArrayList<Point>();
		
		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<N;j++){
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2){
					virus.add(new Point(i,j));
				} else if(map[i][j]==0){
					empty++;
				}
			}
		}
		
		if(empty==0){
			System.out.println("0");
			return ;
		}
		virusChoice(new int[M], 0, virus.size(), M, 0);
		
		if(minTime==Integer.MAX_VALUE){
			System.out.println("-1");
			return ;
		}
		else {
			System.out.println(minTime);
			return ;
		}
		
	}

	public static void virusChoice(int arr[], int idx, int n, int r, int target){
		if(r==0){
			ArrayList<Point> choice =new ArrayList<Point>();
			for(int i=0;i<arr.length;i++){
				choice.add(virus.get(arr[i]));
			}
			bfs(choice);
		} else if(target==n){
			return ;
		} else {
			arr[idx] = target;
			virusChoice(arr, idx+1, n, r-1, target+1);
			virusChoice(arr, idx, n, r, target+1);
		}
	}
	public static void bfs(ArrayList<Point> virus){

		int tmpE = empty;
		int tmpTime = 0;
		Queue<Point> que = new LinkedList<Point>();
		boolean visited[][] = new boolean [N][N];
		for(Point tmp : virus){
			que.add(tmp);

			visited[tmp.x][tmp.y] = true;			
		}
		
		while(!que.isEmpty()){
			
			int size = que.size();
			tmpTime++;
			while(size>0){
			Point idx = que.poll();
			for(int i=0;i<4;i++){
				int x= idx.x - dx[i];
				int y= idx.y - dy[i];
				
				if(x<0 || y<0 || x>=N || y>=N){
					continue;
				} 
				
				if(map[x][y]==1){
					continue;
				} else if(!visited[x][y]) {
					visited[x][y] = true;
					que.add(new Point(x,y));
					if(map[x][y]==0){
						tmpE--;
						if(tmpE==0){			
							if(tmpTime<minTime){
								minTime = tmpTime;
							}
							return ;
						}
						visited[x][y] = true;
						que.add(new Point(x,y));
					}
				}
			}
			size--;
			}
			
		}
	
		
	}
	public static class Point {
		int x;
		int y;
		
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

}
