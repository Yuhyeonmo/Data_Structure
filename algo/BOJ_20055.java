import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 *  너무 어렵게 생각함... belt 그냥 이동시키고 시작-끝 부분만 신경쓸것.
 *  Robot은 아래쪽에 있을 순 없으니 이동처리만 제대로 확인하면서 벨트를 내구도 체크
 *  진짜 쉽긴 함...
 */

public class BOJ_20055 {

	static int N,K, cntK;
	static int belt[];
	static boolean robot[];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		cntK= 0;
		belt = new int [2*N];
		robot = new boolean [N];
		st = new StringTokenizer(br.readLine());
		int cnt = st.countTokens();
		for(int i=0;i<cnt;i++){
			belt[i] = Integer.parseInt(st.nextToken());
		}
		int times = 0;
		while(true){
			times++;

			// 벨드회전
			int lastBelt = belt[N*2-1];
			for(int i = N*2-1;i>0;i--){
				belt[i] = belt[i-1];
			}
			belt[0] = lastBelt;

			 // 벨트 위에 있는 로봇도 이동
			for(int i=N-2;i>0;i--){
				robot[i] = robot[i-1];
			}
			robot[0] = robot[N-1];
			
			// 로봇이 스스로 이동
			for(int i = N-2;i>=0;i--){
				if(robot[i]){
					if(belt[i+1]>0 && !robot[i+1]){
						robot[i+1] = true;
						robot[i] = false;
						belt[i+1]--;
						if(belt[i+1]==0) cntK++;
					}
				}
			}
			// 로봇 올리기
			if(!robot[0] && belt[0]>0) {
				robot[0] = true;
				belt[0]--;
				if(belt[0]==0) cntK++;
			}
			// 로봇 떨구기
			if(robot[N-1]) robot[N-1] = false;
			
			if(cntK >= K) break;
		}
		System.out.println(times);
		

	}



}
