import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

1부터 N까지 정수 N개로 이루어진 순열을 나타내는 방법은 여러 가지가 있다. 
예를 들어, 8개의 수로 이루어진 순열 (3, 2, 7, 8, 1, 4, 5, 6)을 배열을 이용해 표현하면  와 같다. 
또는, Figure 1과 같이 방향 그래프로 나타낼 수도 있다.

순열을 배열을 이용해  로 나타냈다면, i에서 πi로 간선을 이어 그래프로 만들 수 있다.

Figure 1에 나와있는 것 처럼, 순열 그래프 (3, 2, 7, 8, 1, 4, 5, 6) 에는 총 3개의 사이클이 있다. 이러한 사이클을 "순열 사이클" 이라고 한다.

N개의 정수로 이루어진 순열이 주어졌을 때, 순열 사이클의 개수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스의 첫째 줄에는 순열의 크기 N (2 ≤ N ≤ 1,000)이 주어진다. 둘째 줄에는 순열이 주어지며, 각 정수는 공백으로 구분되어 있다.

출력
각 테스트 케이스마다, 입력으로 주어진 순열에 존재하는 순열 사이클의 개수를 출력한다.
 */
public class BOJ_10451 {
	static int N;
	static boolean check [];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++){
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int graph[][] = new int [N+1][N+1];
			int input [] = new int [N+1];
			for(int i=0;i<N;i++){
				input[i] = Integer.parseInt(st.nextToken());
				graph[i+1][input[i]] = 1;
				
			}
			int ans = 0;
			check = new boolean [N+1];
			for(int i=1;i<=N;i++){
				if(check[i]) continue;
				if(dfs(i, new boolean [N+1], graph, check)) ans++;
			}
			System.out.println(ans);
		}
	}
	
	public static boolean dfs(int idx, boolean [] visited, int [][] g, boolean [] check){
		
		visited[idx] = true;
		check[idx] = true;
		
		for(int i=1;i<=N;i++){
			
			if(g[idx][i]==1){
				if(!visited[i] && dfs(i,visited, g, check)){

					return true;
				}
				else if(check[i]){

					return true;
				}
			}
			
		}
		
		check[idx] = false;
		return false;
	}

}
