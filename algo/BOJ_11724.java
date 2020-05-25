import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
문제
방향 없는 그래프가 주어졌을 때, 연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다. (1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2) 둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다. (1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.

출력
첫째 줄에 연결 요소의 개수를 출력한다.
 */
public class BOJ_11724 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int graph[][] = new int [N+1][N+1];
		boolean visited[] = new boolean [N+1];
		for(int i=0;i<M;i++){
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			graph[start][end] = 1;
			graph[end][start] = 1;
		}
		int ans = 0;
		for(int i=1;i<=N;i++){
			if(!visited[i]){
				visited[i] = true;
				bfs(graph, visited, i, N);
				ans++;
			}
		}
		System.out.println(ans);
	}
	
	public static void bfs(int g[][], boolean visited[], int idx, int n){
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(idx);
		
		while(!q.isEmpty()){
			
			int now = q.poll();
			
			for(int i=1;i<=n;i++){
				if(!visited[i] && i!=idx && g[now][i]==1){
					q.add(i);
					visited[i] = true;
				}
			}
			
		}
	}

}
