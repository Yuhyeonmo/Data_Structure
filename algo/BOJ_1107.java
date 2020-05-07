import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
문제
수빈이는 TV를 보고 있다. 수빈이는 채널을 돌리려고 했지만, 버튼을 너무 세게 누르는 바람에, 일부 숫자 버튼이 고장났다.

리모컨에는 버튼이 0부터 9까지 숫자, +와 -가 있다. +를 누르면 현재 보고있는 채널에서 +1된 채널로 이동하고, -를 누르면 -1된 채널로 이동한다. 채널 0에서 -를 누른 경우에는 채널이 변하지 않고, 채널은 무한대 만큼 있다.

수빈이가 지금 이동하려고 하는 채널은 N이다. 어떤 버튼이 고장났는지 주어졌을 때, 채널 N으로 이동하기 위해서 버튼을 최소 몇 번 눌러야하는지 구하는 프로그램을 작성하시오. 

수빈이가 지금 보고 있는 채널은 100번이다.

입력
첫째 줄에 수빈이가 이동하려고 하는 채널 N (0 ≤ N ≤ 500,000)이 주어진다.  둘째 줄에는 고장난 버튼의 개수 M (0 ≤ M ≤ 10)이 주어진다. 고장난 버튼이 있는 경우에는 셋째 줄에는 고장난 버튼이 주어지며, 같은 버튼이 여러 번 주어지는 경우는 없다.

출력
첫째 줄에 채널 N으로 이동하기 위해 버튼을 최소 몇 번 눌러야 하는지를 출력한다.
 */
public class BOJ_1107 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		int n = Integer.parseInt(input);
		int m = sc.nextInt();
		int[] target = new int[6];
		boolean[] disable = new boolean[10];
		
		// + , - 로만 채널 N으로 갈 수 있는 횟수를 기본값으로 정함
		int times = Math.abs(n - 100);

		for (int i = 0; i < m; i++) {
			disable[sc.nextInt()] = true;
		}
		
		// 888888 이 가장 큰 값이 되는 지
		// 500,000(이동하려고 하는 채널 중 가장 큰 값)이고 만약 숫자가 고장났을 때 9빼고 고장났다면 999999일 것이다.  
		// 하지만 이는 100에서 +로 이동하는 경우가 더 작게 나오므로 고려하지 않아도 됨. 따라서 888888을 상한으로 잡고 계산한다.          	
		for(int i=0;i<=888888;i++){
			
			String a = String.valueOf(i);
			boolean check = false;
			for(int j=0;j<a.length();j++){
				int c = a.charAt(j) - '0';
				// 고장난 자릿수가 있을 경우,
				if(disable[c]==true) {
					check = true;
					break;
				}
			}
			// 채널을 버튼으로 이동 가능한 경우
			if(!check){
				// 버튼으로 이동한 값(자릿수) + (+,-를 해서 이동한 값) 을 더함.
				int tmp = Math.abs(i-n) + a.length();
				if(tmp<times){
					times = tmp;
				}
			}

		}
		System.out.println(times);
		
	}
	
}
