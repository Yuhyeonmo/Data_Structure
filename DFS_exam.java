import java.awt.PrintGraphics;
import java.util.Scanner;
import java.util.Stack;


public class DFS_exam {
	private int V; // 정점 Vertex 의 수
	private int[][] Graph; // 인접행렬 그래프
	private boolean [] isVisit; // Vertex 방문 여부 
	
	/*
	 * DFS 예제 생성
	 */
	private DFS_exam(int v) 
	{
		this.V = v;
		
		this.Graph = new int[v+1][v+1];
		
		this.isVisit = new boolean [v+1];
	}
	
	// 그래프 추가 양방향
	public void puts(int x, int y){
		this.Graph[x][y] = this.Graph[y][x] = 1;
	}
	
	// 그래프 추가 단방향
	public void put(int x, int y)
	{
		this.Graph[x][y] = 1;
	}
	
	public void printGraph()
	{
		for (int i=0;i<this.Graph.length;i++)
		{
			for(int j=0;j<this.Graph[i].length;j++)
			{
				System.out.print(" " + this.Graph[i][j]);
			}
			System.out.println();
		}
	}
	
	// 방문 여부 초기화
	public void clearVisit()
	{
		for(int i=0;i<this.isVisit.length;i++)
		{
			this.isVisit[i] = false;
		}
	}
	
	// 재귀
	public void dfs(int idx)
	{
		
		this.isVisit[idx] = true; // 방문 체크
		System.out.print(idx+" "); // 현재 Vertex 출력 
		
		for(int i=1;i<=this.V;i++)
		{
			if(Graph[idx][i]==1 && isVisit[i]==false){ // 인접하면서, 아직 방문하지 않은 경우
				// 재귀 진입
				dfs(i); 
			}
		}
	}
	
	
	// Stack
	public void dfs_stack(int idx){
		Stack<Integer> stack = new Stack();
		
		stack.push(idx); // 시작하는 위치를 Stack에 Push
		
		while(!stack.isEmpty())
		{
			int v = stack.pop(); 
			if(isVisit[v] == false)
			{
				isVisit[v] = true;
				System.out.print(v+" ");
				for(int i=this.V;i>=0;i--){
					if(Graph[v][i] == 1 && isVisit[i]==false){
						stack.push(i);
					}
				}
			}
		}
	}
	
	public static void main(String [] args){
		int nV = 0;
		int nE = 0;
		
		Scanner scan = new Scanner(System.in);
		
		nV = scan.nextInt();
		nE = scan.nextInt();
		DFS_exam dfs = new DFS_exam(nV);
		
		System.out.println("단방향인가?  / 양방향인가? : 1 : 단방향  2 : 양방향 ");
		int direction = scan.nextInt();
		if(direction == 1){
		for(int i=0;i<nE;i++){
			int x = scan.nextInt();
			int y = scan.nextInt();
			
			dfs.put(x, y);
		}
		}
		else if(direction == 2)
		{
			for(int i=0;i<nE;i++){
				int x = scan.nextInt();
				int y = scan.nextInt();
				
				dfs.puts(x, y);
			}
		}
		else
		{
			System.out.println("입력값 에러!!! 1또는 2를 입력하세요");
			return ;
		}
		
//		 1번부터 dfs 탐색 시작
		System.out.println("재귀 실행");
		dfs.dfs(1);
		dfs.clearVisit();
		System.out.println();
		System.out.println("stack 실행");
		dfs.dfs_stack(1);
		
	}
}
