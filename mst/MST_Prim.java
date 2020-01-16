package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class MST_Prim {
	// 1. 정점 중 아무거나 일단 시작 정점 고르기
	// 2. 고른 정점에 연결된 간선들 중 최소비용 간선 고르기 -> 간선 따라가면 있는 정점을 방문한게 됨
	// 3. 앞에서 선택한 모든 정점들에 연결된 간선 중 최소 비용 간선 찾아서 또 선택.
	// 4. 모든 N개의 정점이 다 선택될 때 까지 2-3 반복.
	static int N;
	static int E;
	
	static LinkedList<Edge> [] graph;
	static boolean visit[]; // 정점의 방문 여부
	static ArrayList<Edge> mst;
	static class Edge
	{
		int start;
		int end;
		int w;
		Edge(int s, int e, int w)
		{
			this.start = s;
			this.end = e;
			this.w = w;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "Edge st: "+this.start+" end:"+this.end+" w:"+this.w;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		E = sc.nextInt();
		visit = new boolean[N+1];
		graph = new LinkedList[N+1];
		
		for(int i=1;i<=N;i++)
		{
			graph[i] = new LinkedList<>();
		}
		
		for(int e=0;e<E;e++)
		{
			int st = sc.nextInt();
			int ed = sc.nextInt();
			int w = sc.nextInt();
			
			graph[st].add(new Edge(st, ed, w));
			graph[ed].add(new Edge(ed, st, w));
		}
		
		mst = new ArrayList<>();
		makeMst();
		System.out.println(mst.toString());
	}
	static void makeMst() {
		// TODO Auto-generated method stub
		PriorityQueue<Edge> pq = new PriorityQueue<>(new EdgeComparator()); // 간선들
		Queue<Integer> que = new LinkedList<>(); // 정점
		que.add(1); // 1번부터 시작
		
		
		while(!que.isEmpty())
		{
			int nowV = que.poll(); // 정점 vertex 방문
			visit[nowV] = true;
			
			LinkedList<Edge> nowEdges = graph[nowV]; // 현재 정점에서 선택 가능한 정점
			for(Edge e : nowEdges)
			{
				if(!visit[e.end]) // 연결된 간선들 중에 그 끝에 있는 정점이 방문한 적 없는 간선만 선택해서
				{
					pq.add(e); // 우선순위 큐에 집어넣어보자.
				}
			}
			while(!pq.isEmpty())
			{
				Edge s = pq.poll(); // 후보 간선에서 최소인 하나를 선택
				if(!visit[s.end]) {
					visit[s.end] = true;
					mst.add(s);
					que.add(s.end); // 지금 선택된 정점도 방문 큐에 넣어 놓자.
					break;
				}
			}
		}
	}
	static class EdgeComparator implements Comparator<Edge>
	{

		@Override
		public int compare(Edge o1, Edge o2) {
			// TODO Auto-generated method stub
			
			return o1.w > o2.w? 1: -1;
		}
		
	}

}
