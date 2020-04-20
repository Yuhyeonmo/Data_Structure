import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Expogo {
	// 동 서 남 북
	static int dx[] = {0,0,-1,1};
	static int dy[] = {-1,1,0,0};
	static int targetx; 
	static int targety;
	static int ans;
	static HashSet<Point> set;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			targetx = Integer.parseInt(st.nextToken());
			targety = Integer.parseInt(st.nextToken());
			ans = Integer.MAX_VALUE;
			Point p = new Point(0,0);
			set = new HashSet<Point>();
			sb = new StringBuilder();
			dfs(new ArrayList<Point>(), 0, 0, 0);
			System.out.print("CASE #"+tc+": ");
			if(ans==Integer.MAX_VALUE){
				sb.append("IMPOSSIBLE");
			}
			System.out.println(sb.toString());
		}
	}
	
	public static void dfs(ArrayList<Point> road, int step, int x, int y){
		
		if(step>ans){
			return ;
		}
		if(step>8){
			return ;
		}
		if(targetx==x && targety==y){
			if(ans>step){
				sb = new StringBuilder();
				for(int i=0;i<road.size();i++){
					
					sb.append(road.get(i).direct);
				}
				ans = step;
			}
			
			return;
		}

		Point tmp = new Point((int)(x-1*Math.pow(2, step)), y, "W");
		
		road.add(tmp);
		dfs(road, step+1, (int)(x-1*Math.pow(2, step)), y);
		road.remove(tmp);
		
		tmp = new Point(x, (int)(y-1*Math.pow(2, step)), "S");	
		road.add(tmp);
		dfs(road, step+1, x, (int)(y-1*Math.pow(2, step)));
		road.remove(tmp);
		
		tmp = new Point((int)(x+1*Math.pow(2, step)), y, "E");	
		road.add(tmp);
		dfs(road, step+1, (int)(x+1*Math.pow(2, step)), y);
		road.remove(tmp);
		
		tmp = new Point(x, (int)(y+1*Math.pow(2, step)), "N");
		road.add(tmp);
		dfs(road, step+1, x, (int)(y+1*Math.pow(2, step)));
		road.remove(tmp);
		
	}
	
	public static class Point {
		long x;
		long y;
		String direct;
		Point(long x , long y){
			this.x = x;
			this.y = y;
		}
		Point(long x, long y, String d){
			this.x = x;
			this.y = y;
			this.direct = d;
		}
	}

}
