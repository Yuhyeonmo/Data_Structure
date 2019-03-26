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
	// 1. ���� �� �ƹ��ų� �ϴ� ���� ���� ����
	// 2. �� ������ ����� ������ �� �ּҺ�� ���� ���� -> ���� ���󰡸� �ִ� ������ �湮�Ѱ� ��
	// 3. �տ��� ������ ��� �����鿡 ����� ���� �� �ּ� ��� ���� ã�Ƽ� �� ����.
	// 4. ��� N���� ������ �� ���õ� �� ���� 2-3 �ݺ�.
	static int N;
	static int E;
	
	static LinkedList<Edge> [] graph;
	static boolean visit[]; // ������ �湮 ����
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
		PriorityQueue<Edge> pq = new PriorityQueue<>(new EdgeComparator()); // ������
		Queue<Integer> que = new LinkedList<>(); // ����
		que.add(1); // 1������ ����
		
		
		while(!que.isEmpty())
		{
			int nowV = que.poll(); // ���� vertex �湮
			visit[nowV] = true;
			
			LinkedList<Edge> nowEdges = graph[nowV]; // ���� �������� ���� ������ ����
			for(Edge e : nowEdges)
			{
				if(!visit[e.end]) // ����� ������ �߿� �� ���� �ִ� ������ �湮�� �� ���� ������ �����ؼ�
				{
					pq.add(e); // �켱���� ť�� ����־��.
				}
			}
			while(!pq.isEmpty())
			{
				Edge s = pq.poll(); // �ĺ� �������� �ּ��� �ϳ��� ����
				if(!visit[s.end]) {
					visit[s.end] = true;
					mst.add(s);
					que.add(s.end); // ���� ���õ� ������ �湮 ť�� �־� ����.
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
