import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;


/**
 * 
 * @author kora1492

문제
2차원 평면상에 n개의 점이 주어졌을 때, 이 점들 중 가장 가까운 두 점을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 자연수 n(2 ≤ n ≤ 100,000)이 주어진다. 
다음 n개의 줄에는 차례로 각 점의 x, y좌표가 주어진다. 각각의 좌표는 절댓값이 10,000을 넘지 않는 정수이다. 같은 점이 여러 번 주어질 수도 있다.

출력
첫째 줄에 가장 가까운 두 점의 거리의 제곱을 출력한다.

 */
public class BOJ_2261 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		Point list[] = new Point[n];	
		// y좌표를 기준으로 오름차순으로 정렬됨, 똑같은 경우 x 좌표
		TreeSet<Point> set = new TreeSet<>((a, b) -> ((a.y == b.y) ? a.x - b.x : a.y - b.y));
		
		for(int i=0;i<n;i++){
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list[i] = new Point(x,y);
		}
		// 리스트를 x 좌표가 오름차순으로 정렬해줌. 
		Arrays.sort(list);
		
		// candidate로 TreeSet을 사용해서 0,1 두개의 점을 우선 넣음.
		set.add(list[0]);
		set.add(list[1]);
		
		// 그리고 초기 거리 ans를 구함. 
		long ans = dist(list[0], list[1]);
		 
		int start = 0;
		for(int i=2;i<n;i++){
			// 현재 위치
			Point now = list[i];
			
			// 끝구간 ~ 현재 위치
			while(start < i){
				// 끝구간에 있는 점 
				Point p = list[start];
				int x = now.x - p.x;
				// ans = x*x + y*y 꼴이 되므로,
				if(x*x > ans){
					// 끝구간 이동
					start+=1;
					// 제거
					set.remove(p);
				}
				// 범위안에 들어옴(다음 부분은 다 들어옴)
				else {
					break;
				}
			}
			
			// 제곱근
			int d = (int) Math.sqrt(ans) + 1;
			
			// 현재 위치에서의 lower, upper 좌표를 구함. 
			// d의 범위 안에 들어오는 Point p만 계산하면 됨.
			Point lower_point = new Point(-10001, now.y-d);
			Point upper_point = new Point(10001, now.y+d);
			
			for(Point p : set.subSet(lower_point, upper_point)){
				long dist = dist(p,now);
				ans = Math.min(dist, ans);
			}
			set.add(now);
		}
		System.out.println(ans);
	}
	public static long dist(Point p1, Point p2){
		return (p2.x-p1.x) * (p2.x-p1.x) + (p2.y-p1.y) * (p2.y-p1.y);
	}


	public static class Point implements Comparable<Point>{
		int x;
		int y;
		
		Point(int x,int y){
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			return this.x-o.x;
		}
	}
}
