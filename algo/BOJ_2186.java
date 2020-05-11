import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*

문제
알파벳 대문자가 한 칸에 한 개씩 적혀있는 N×M 크기의 문자판이 있다. 편의상 모든 문자는 대문자라 생각하자. 예를 들어 아래와 같은 문자판을 보자.

K	A	K	T
X	E	A	S
Y	R	W	U
Z	B	Q	P
이 문자판의 한 칸(아무 칸이나 상관없음)에서 시작하여 움직이면서, 그 칸에 적혀 있는 문자들을 차례대로 모으면 하나의 단어를 만들 수 있다. 
움직일 때는 상하좌우로 K개의 칸까지만 이동할 수 있다. 예를 들어 K=2일 때 아래의 그림의 가운데에서는 'X' 표시된 곳으로 이동할 수 있다.

 	 	X	 	 
 	 	X	 	 
X	X	 	X	X
 	 	X	 	 
 	 	X	 	 
반드시 한 칸 이상 이동을 해야 하고, 같은 자리에 머물러 있을 수 없다. 또, 같은 칸을 여러 번 방문할 수 있다.

이와 같은 문자판과 K, 그리고 하나의 영단어가 주어졌을 때, 이와 같은 영단어를 만들 수 있는 경로가 총 몇 개 존재하는지 알아내는 프로그램을 작성하시오.

위의 예에서 영단어가 BREAK인 경우에는 다음과 같이 3개의 경로가 존재한다. 앞의 수는 행 번호, 뒤의 수는 열 번호를 나타낸다.

(4, 2) (3, 2) (2, 2) (1, 2) (1, 1)
(4, 2) (3, 2) (2, 2) (1, 2) (1, 3)
(4, 2) (3, 2) (2, 2) (2, 3) (1, 3)
입력
첫째 줄에 N(1 ≤ N ≤ 100), M(1 ≤ M ≤ 100), K(1 ≤ K ≤ 5)가 주어진다. 다음 N개의 줄에는 M개의 알파벳 대문자가 주어지는데, 이는 N×M 크기의 문자판을 나타낸다. 
다음 줄에는 1자 이상 80자 이하의 영단어가 주어진다. 모든 문자들은 알파벳 대문자이며, 공백 없이 주어진다.

출력
첫째 줄에 경로의 개수를 출력한다. 이 값은 int 범위이다.
 */
public class BOJ_2186 {
	static int N, M, K;
	static char [][] map;
	static int check[][][];
	static int dx[] = {0,0,-1,1};
	static int dy[] = {1,-1,0,0};
	static String target= "";
	static int ans;
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for(int i=0;i<N;i++){
			String input = br.readLine();
			map[i] = input.toCharArray();
		}
		target = br.readLine();
		check = new int [N][M][target.length()];
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				Arrays.fill(check[i][j], -1);
			}
		}
		char start = target.charAt(0);
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				if(start==map[i][j]){
					ans+=dfs(0,i,j);
				}
			}
		}
		System.out.println(ans);
	}
	/*
	 * DFS + 메모이제이션(DP) 개념을 사용해야함.
	 * 방문 했던 경로(check) 에서 초기값(-1)이 아닌 값이라면 그 경로를 더 들여다 볼 필요가 없다.
	 */
	public static int dfs(int idx, int x, int y){
		// 이미 해당하는 경로를 찾은 이력이 있으므로 더 이상 확인 안함.
		if(check[x][y][idx]!=-1) {
			return check[x][y][idx];
		}
		// 해당하는 타겟의 문자까지 간경우이므로 1로 값을 변경함.
		if(idx==target.length()-1){
			check[x][y][idx] = 1;
			return 1;
		}
		check[x][y][idx] = 0;
		for(int i=0;i<4;i++){
			for(int j=1;j<=K;j++){
				int tx = x + j*dx[i];
				int ty = y + j*dy[i];
				
				if(tx>=0 && ty>=0 && tx<N && ty<M && map[tx][ty]==target.charAt(idx+1)){
					// 해당 경로에서 나올 수 있는 값들을 더함.
					check[x][y][idx] +=dfs(idx+1, tx, ty);
				}
			}
		}
		
		return check[x][y][idx];
	}
//	public static class Point{
//		int x;
//		int y;
//		
//		Point(int x,int y){
//			this.x = x;
//			this.y = y;
//		}
//	}
}
