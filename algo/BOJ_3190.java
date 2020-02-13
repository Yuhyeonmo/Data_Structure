import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author YuHyeonmo
 *
 문제
 'Dummy' 라는 도스게임이 있다. 이 게임에는 뱀이 나와서 기어다니는데, 사과를 먹으면 뱀 길이가 늘어난다. 뱀이 이리저리 기어다니다가 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.

게임은 NxN 정사각 보드위에서 진행되고, 몇몇 칸에는 사과가 놓여져 있다. 보드의 상하좌우 끝에 벽이 있다. 게임이 시작할때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1 이다. 뱀은 처음에 오른쪽을 향한다.

뱀은 매 초마다 이동을 하는데 다음과 같은 규칙을 따른다.

먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
사과의 위치와 뱀의 이동경로가 주어질 때 이 게임이 몇 초에 끝나는지 계산하라.

입력
첫째 줄에 보드의 크기 N이 주어진다. (2 ≤ N ≤ 100) 다음 줄에 사과의 개수 K가 주어진다. (0 ≤ K ≤ 100)

다음 K개의 줄에는 사과의 위치가 주어지는데, 첫 번째 정수는 행, 두 번째 정수는 열 위치를 의미한다. 사과의 위치는 모두 다르며, 맨 위 맨 좌측 (1행 1열) 에는 사과가 없다.

다음 줄에는 뱀의 방향 변환 횟수 L 이 주어진다. (1 ≤ L ≤ 100)

다음 L개의 줄에는 뱀의 방향 변환 정보가 주어지는데,  정수 X와 문자 C로 이루어져 있으며. 게임 시작 시간으로부터 X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전시킨다는 뜻이다. X는 10,000 이하의 양의 정수이며, 방향 전환 정보는 X가 증가하는 순으로 주어진다.

출력
첫째 줄에 게임이 몇 초에 끝나는지 출력한다.
 */
public class BOJ_3190 {
	static int N;
	static ArrayList<Point> apple = new ArrayList<Point>();
	static Queue<Point> baem = new LinkedList<Point>();
	static int d [] = {1,2,3,4};
	static int direct [];
	static int timeX [];
	static int flag = 0;
	static int headx, heady;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		
		N = sc.nextInt();
		int K = sc.nextInt();
		for(int i=0;i<K;i++){
			int x = sc.nextInt();
			int y = sc.nextInt();
			apple.add(new Point(x,y));
		}
		
		int L = sc.nextInt();
		direct = new int[L];
		timeX = new int [10001];
		for(int i=0;i<L;i++){
			int x = sc.nextInt();
			String input = sc.next();
			int c;
			if(input.equals("L")){
				c = 1;
			} else {
				c = 0;
			}
			direct[i] = c;
			timeX [i] = x;
		}
		baem.add(new Point(1,1,1));
		int time = 0;
		int idx = 0;
		int hd = 1;
		headx = 1;
		heady = 1;
		while(true){
			
			time++;
			
			if(idx < L && time==timeX[idx]+1){
				
				if(direct[idx]==1){
					if(hd==1){ // 오른쪽
						hd = 2;
						
					}
					else if(hd==2){ // 상
						hd = 4;
						
					} else if(hd==3){ // 하
						hd = 1;
						
					} else { // 좌
						hd = 3;
						
					}
				} else {
					if(hd==1){ // 오른쪽
						hd = 3;
					
					}
					else if(hd==2){ // 상
						hd = 1;
						
					} else if(hd==3){ // 하
						hd = 4;
					
					} else { // 좌
						hd = 2;
						
					}
				}
				idx++;
			}
			go(hd);
			if(flag==1){
				break;
			}
		}
		System.out.println(time);
	}
	
	public static void go(int direct){
		
	
		if(direct==1){
			heady++;
		} else if(direct==2){
			headx--;
		} else if(direct==3){
			headx++;
		} else {
			heady--;
		}
		
		if(headx == N+1 || heady == N+1 || headx==0 || heady==0) {
			flag = 1;
			return ;
		}
		for(Point i : baem){
			if(i.x==headx && i.y==heady){
				flag = 1;
				return ;
			}
		}
		baem.add(new Point(headx, heady, direct));
		int check = 0;
		for(Point i : apple){
			if(i.x==headx && i.y==heady){
				apple.remove(i);		
				check = 1;
				break;
			}
		}
		if(check==0) {		
			baem.poll();
		}
	}
	
	public static class Point {
		int x;
		int y;
		int direct;
		Point(int x,int y){
			this.x = x;
			this.y = y;
		}
		Point(int x, int y, int direct){
			this.x = x;
			this.y = y;
			this.direct = direct;
		}
	}

}
