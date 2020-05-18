import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
문제 : 거북이의 행위들이 있어서 처음엔 시뮬레이션처럼 다 돌려보는 문제로 파악했었음.
           결국은 펜을 몇번 찍었나가 중요 => 사각형이 겹치는 부분이 있으면 한 붓 그리기로 그리면 될 거 같았음.
           직접 그려보면서 사각형 좌표간의 로직을 확인해보는 게 수월함.
 */
public class BOJ_3108 {
	static int cnt =0;
	static int N;
	static boolean check [];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Point [] p = new Point[N+1];
		check = new boolean [N+1];
		// 먼저 시작한 지점 0,0,0,0
		// Point (x1,y1,x2,y2)
		p[0] = new Point(0,0,0,0);
		for(int i=1;i<=N;i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			// 좌표, 사각형의 대각점을 저장한다.
			p[i] = new Point(x1,y1,x2,y2);
		}
		
		for(int i=0;i<=N;i++){
			// 방문한 점인지 체크
			if(!check[i]){
				check[i] =true;
				// bfs 를 돌며 이어진 점들을 확인함.
				bfs(p, i);
			}
		}
		System.out.println(cnt-1);
	}
	public static void bfs(Point [] p, int idx){
		Queue<Point> q = new LinkedList<Point>();
		q.add(p[idx]);
		// BFS 실행 시 ,펜을 찍음
		cnt++;
		while(!q.isEmpty()){
			Point tmp = q.poll();
			// 현재 사각형의 대각 좌표
			int x1 = tmp.x1;
			int x2 = tmp.x2;
			int y1 = tmp.y1;
			int y2 = tmp.y2;
			for(int i=0;i<=N;i++){
				// 아직 체크되지 않으면서, 자기 자신이 아닌 경우 이어지는 지 확인함.
				if(!check[i] && i!=idx){
					int tx1 = p[i].x1;
					int tx2 = p[i].x2;
					int ty1 = p[i].y1;
					int ty2 = p[i].y2;
					
					// 사각형안에 포함되거나, 사각형을 크게 포함하거나, 또는 아예 왼쪽,오른쪽,위,아래 등으로 벗어난 경우를 확인함
					if((x1>tx1 && y1>ty1 && x2<tx2 && y2<ty2) || (tx1>x1 && ty1>y1 && tx2<x2 && ty2<y2) || (x1>tx2 || y1>ty2 || x2<tx1 || y2<ty1)){
						continue;
					}
					// 위의 경우에 해당되지 않은 경우 이어졌음을 체크하고 큐에 저장
					else{
						check[i] = true;
						q.add(p[i]);
					}
				}
			}
		}
	}
	public static class Point{
		int x1;
		int y1;
		int x2;
		int y2;
		Point (int x1, int y1, int x2, int y2){
			this.x1 =x1;
			this.y1 =y1;
			this.x2 =x2;
			this.y2 =y2;
		}
	}
}
