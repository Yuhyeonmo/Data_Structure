import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import javax.management.Query;

/**
 * 
 * @author YuhyeonMo
 * 
 * 문제
강호는 코딩 교육을 하는 스타트업 스타트링크에 지원했다. 오늘은 강호의 면접날이다. 하지만, 늦잠을 잔 강호는 스타트링크가 있는 건물에 늦게 도착하고 말았다.

스타트링크는 총 F층으로 이루어진 고층 건물에 사무실이 있고, 스타트링크가 있는 곳의 위치는 G층이다. 강호가 지금 있는 곳은 S층이고, 이제 엘리베이터를 타고 G층으로 이동하려고 한다.

보통 엘리베이터에는 어떤 층으로 이동할 수 있는 버튼이 있지만, 강호가 탄 엘리베이터는 버튼이 2개밖에 없다. U버튼은 위로 U층을 가는 버튼, D버튼은 아래로 D층을 가는 버튼이다. (만약, U층 위, 또는 D층 아래에 해당하는 층이 없을 때는, 엘리베이터는 움직이지 않는다)

강호가 G층에 도착하려면, 버튼을 적어도 몇 번 눌러야 하는지 구하는 프로그램을 작성하시오. 만약, 엘리베이터를 이용해서 G층에 갈 수 없다면, "use the stairs"를 출력한다.

입력
첫째 줄에 F, S, G, U, D가 주어진다. (1 ≤ S, G ≤ F ≤ 1000000, 0 ≤ U, D ≤ 1000000) 건물은 1층부터 시작하고, 가장 높은 층은 F층이다.

출력
첫째 줄에 강호가 S층에서 G층으로 가기 위해 눌러야 하는 버튼의 수의 최솟값을 출력한다. 만약, 엘리베이터로 이동할 수 없을 때는 "use the stairs"를 출력한다.
 */
public class BOJ_5014 {
	static int F,U,D;
	static int min = 1000001;
	static int cnt[];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		F = sc.nextInt(); // 최고높이
		int S = sc.nextInt(); // 현재
		int G = sc.nextInt(); // 목적지
		U = sc.nextInt(); // 올라가는 
		D = sc.nextInt(); // 내려가는
		
		updown(G, S);
		// 찾지 못했을 경우.
		if(min==1000001){
			System.out.println("use the stairs");
		}
		else{
		System.out.println(min);
		}
	}
	public static void updown(int target, int now){
		
		
		Queue<Integer> que = new LinkedList<Integer>();
		boolean visited[] = new boolean [F+1];
		visited[now] = true;
		cnt = new int [F+1];
		que.add(now);
		cnt[now] = 0;
		int size = que.size();
		int idx;
		
		while(!que.isEmpty()){
			idx = que.poll();
			if(idx==target){
				min = Math.min(min, cnt[target]);
				return ;
			}
			if(idx+U<=F && !visited[idx+U]){
				cnt[idx+U] = cnt[idx] + 1;
				visited[idx+U] = true;
				que.add(idx+U);
			}
			if(idx-D>=1 && !visited[idx-D]){
				cnt[idx-D] = cnt[idx] + 1;
				visited[idx-D] = true;
				que.add(idx-D);
			}
			
		}
		
	}

}
