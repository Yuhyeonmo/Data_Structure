package test;

import java.util.Arrays;
import java.util.Scanner;

public class Dijkstra {
	// 인접행렬을 가지고 있다. 즉 간선 정보 그래프를 가지고 있는 상태엣 최단 거리  계산함.
	// 1. 모든 정점까지의 거리정보를 일단 줄 수 있는 최대 값으로 세팅
	// 2. 시작 정점이 결정되면 해당 정점까지의 거리는 0으로 표시, 그리고 시작 정점을 방문 표시
	// 3. 시작 노드와 인접한 노드들의 거리 값을 갱신
	// 4. 아직 방문하지 않은 노드 중 거리가 가장 가까운 노드(Minimum Node)를 찾기.
	// 5. 찾은 노드(minNode)에 방문한 다음 minNode에서 인접한 정점들의 거리 정보를 또 갱신
	// dist[minNode] + (minNode~min의 인접 노드까지의 거리)랑 인접 행렬에 기록된 값이랑 비교
	
	static int N, E;
	static int maps[][];
	static int dist[];
	static boolean visit[];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		E = sc.nextInt();
		
		maps = new int[N+1][N+1];
		dist = new int [N+1];
		visit = new boolean[N+1];
		for(int e=0;e<E;e++)
		{
			int st = sc.nextInt();
			int ed = sc.nextInt();
			int w = sc.nextInt();
			
			maps[st][ed] =  w;
			 maps[ed][st] = w;
			
		}
		dijkstra(2);
		System.out.println(Arrays.toString(dist));
	}
	static void dijkstra(int start)
	{
		for(int i=1;i<=N;i++)
		{
			dist[i] = Integer.MAX_VALUE;
		}
		dist[start] = 0;
		visit[start] = true;
		
		for(int i=1;i<=N;i++)
		{
			if(!visit[i] && maps[start][i]!=0)
			{
				dist[i] = maps[start][i];
			}
		}
	
			
		while(true) {
			if(isAllVisit())
			{
				break;
			}
			int min = Integer.MAX_VALUE;
			int min_i = -1;
			
			for(int i=1;i<=N;i++)
			{
				if(!visit[i] && dist[i]!=Integer.MAX_VALUE && dist[i]<min)
				{
					min = dist[i];
					min_i = i;
				}
			}
			
			
			visit[min_i] = true;
			for(int i=1;i<=N;i++)
			{
				if(!visit[i] && maps[min_i][i]!=0 && dist[i]>dist[min_i]+maps[min_i][i])
				{
					dist[i] = dist[min_i]+maps[min_i][i];
				}
			}
		}
	}
	static boolean isAllVisit()
	{
		for(int i=1;i<=N;i++)
		{
			if(visit[i]==false)
			{
				return false;
			}
		}
		return true;
	}


}
