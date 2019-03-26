package test;

import java.util.Arrays;
import java.util.Scanner;

public class Dijkstra {
	// ��������� ������ �ִ�. �� ���� ���� �׷����� ������ �ִ� ���¿� �ִ� �Ÿ�  �����.
	// 1. ��� ���������� �Ÿ������� �ϴ� �� �� �ִ� �ִ� ������ ����
	// 2. ���� ������ �����Ǹ� �ش� ���������� �Ÿ��� 0���� ǥ��, �׸��� ���� ������ �湮 ǥ��
	// 3. ���� ���� ������ ������ �Ÿ� ���� ����
	// 4. ���� �湮���� ���� ��� �� �Ÿ��� ���� ����� ���(Minimum Node)�� ã��.
	// 5. ã�� ���(minNode)�� �湮�� ���� minNode���� ������ �������� �Ÿ� ������ �� ����
	// dist[minNode] + (minNode~min�� ���� �������� �Ÿ�)�� ���� ��Ŀ� ��ϵ� ���̶� ��
	
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
