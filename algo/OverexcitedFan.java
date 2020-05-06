import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 맨해튼 거리 이내에 있는 교차점에 도달할 수 있음을 이용. 
 * 페퍼르의 투어를 시뮬레이션
 * 방문한 i번째 교차로에서 i분 내에 도달할 수 있는 지 확인 후 필요한 시간 내에 도착할 수 업는 경우, 불가능
 */

public class OverexcitedFan {
	static int x, y;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++){
			StringTokenizer input = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(input.nextToken());
			int y = Integer.parseInt(input.nextToken());
			String road = input.nextToken();
			
			
			if(x==0 && y ==0) {
				System.out.println("Case #"+tc+": IMPOSSIBLE");
			} else {
				boolean find = false;
				for(int i=0;i<=road.length();i++){
					if(Math.abs(x)+Math.abs(y) <= i){
						System.out.println("Case #"+tc+": "+i);
						find = true;
						break;
					}
					if(i<road.length()){
						if(road.charAt(i)=='W'){
							x--;
						} else if(road.charAt(i)=='E'){
							x++;
						} else if(road.charAt(i)=='S'){
							y--;
						} else if(road.charAt(i)=='N'){
							y++;
						}
					}
				}
				if(!find){
					System.out.println("Case #"+tc+": IMPOSSIBLE");
				}
			}
		}
	}
//
//			
//			if(r.charAt(time)=='W'){
//				qx++;
//			} else if(r.charAt(time)=='E'){
//				qx--;
//			} else if(r.charAt(time)=='S'){
//				qy++;
//			} else if(r.charAt(time)=='N'){
//				qy--;
//			}

}
