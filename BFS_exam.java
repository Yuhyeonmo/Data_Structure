import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class BFS_exam {
	private int V;
	private int [][] graph;
	private boolean [] isVisit;
	
	public BFS_exam(int v) 
	{
		this.V = v;
		
		this.graph = new int[v+1][v+1];
		
		this.isVisit = new boolean [v+1];
	}
	
	// 그래프 추가 양방향
	public void puts(int x, int y){
		this.graph[x][y] = this.graph[y][x] = 1;
	}
	
	// 그래프 추가 단방향
	public void put(int x, int y)
	{
		this.graph[x][y] = 1;
	}
	
	public void bfs(int idx)
	{
		Queue<Integer> q = new LinkedList<>();
		
		q.add(idx);
		isVisit[idx] = true;
		
		while(!q.isEmpty())
		{
			int v = q.poll();
			System.out.println(v+" ");
			for(int i=1;i<=V;i++)
			{
				if(isVisit[i]==false && graph[v][i] == 1)
				{
					isVisit[i] = true;
					q.add(i);
				}
			}		
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
	
	public static void main(String [] args){
		int nV = 0;
		int nE = 0;
		
		Scanner scan = new Scanner(System.in);
		
		nV = scan.nextInt();
		nE = scan.nextInt();
		BFS_exam bfs = new BFS_exam(nV);
		
		System.out.println("단방향인가?  / 양방향인가? : 1 : 단방향  2 : 양방향 ");
		int direction = scan.nextInt();
		if(direction == 1){
		for(int i=0;i<nE;i++){
			int x = scan.nextInt();
			int y = scan.nextInt();
			
			bfs.put(x, y);
		}
		}
		else if(direction == 2)
		{
			for(int i=0;i<nE;i++){
				int x = scan.nextInt();
				int y = scan.nextInt();
				
				bfs.puts(x, y);
			}
		}
		else
		{
			System.out.println("입력값 에러!!! 1또는 2를 입력하세요");
			return ;
		}
		
//		 1번부터 bfs 탐색 시작
		bfs.bfs(2);
		bfs.clearVisit();
		System.out.println();

		
	}
	
	
}
