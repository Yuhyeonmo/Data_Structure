import java.util.Scanner;

/**
 * 
 * @author Yuhyeonmo
 문제
상담원으로 일하고 있는 백준이는 퇴사를 하려고 한다.

오늘부터 N+1일째 되는 날 퇴사를 하기 위해서, 남은 N일 동안 최대한 많은 상담을 하려고 한다.

백준이는 비서에게 최대한 많은 상담을 잡으라고 부탁을 했고, 비서는 하루에 하나씩 서로 다른 사람의 상담을 잡아놓았다.

각각의 상담은 상담을 완료하는데 걸리는 기간 Ti와 상담을 했을 때 받을 수 있는 금액 Pi로 이루어져 있다.

N = 7인 경우에 다음과 같은 상담 일정표를 보자.

 	1일	2일	3일	4일	5일	6일	7일
Ti	3	5	1	1	2	4	2
Pi	10	20	10	20	15	40	200
1일에 잡혀있는 상담은 총 3일이 걸리며, 상담했을 때 받을 수 있는 금액은 10이다. 5일에 잡혀있는 상담은 총 2일이 걸리며, 받을 수 있는 금액은 15이다.

상담을 하는데 필요한 기간은 1일보다 클 수 있기 때문에, 모든 상담을 할 수는 없다. 예를 들어서 1일에 상담을 하게 되면, 2일, 3일에 있는 상담은 할 수 없게 된다. 2일에 있는 상담을 하게 되면, 3, 4, 5, 6일에 잡혀있는 상담은 할 수 없다.

또한, N+1일째에는 회사에 없기 때문에, 6, 7일에 있는 상담을 할 수 없다.

퇴사 전에 할 수 있는 상담의 최대 이익은 1일, 4일, 5일에 있는 상담을 하는 것이며, 이때의 이익은 10+20+15=45이다.

상담을 적절히 했을 때, 백준이가 얻을 수 있는 최대 수익을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N (1 ≤ N ≤ 15)이 주어진다.

둘째 줄부터 N개의 줄에 Ti와 Pi가 공백으로 구분되어서 주어지며, 1일부터 N일까지 순서대로 주어진다. (1 ≤ Ti ≤ 5, 1 ≤ Pi ≤ 1,000)

출력
첫째 줄에 백준이가 얻을 수 있는 최대 이익을 출력한다.
 
 */
public class BOJ_14501_완전탐색 {
	static int N;
	static int ti[]; 
	static int pi[];
	static int Max = 0;
	static boolean visited[];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		ti = new int [N];
		pi = new int [N];
		visited = new boolean [N];
		for(int i=0;i<N;i++){
			int t = sc.nextInt();
			int p = sc.nextInt();
			ti[i] = t;
			pi[i] = p;
		}
		
		dfs(0,0,0);
		System.out.println(Max);
		
	}
	
	public static void dfs(int d, int idx, int sum){
		if(d==N){
			if(sum>Max){
				Max = sum;
			}
			return ;
		} if(idx==N) {
			if(sum>Max){
				Max = sum;
			}
			return ;
		}
		else {
			int flag = 0;
			for(int i=idx;i<N;i++){
				if(!visited[i] && i+ti[i]<=N){
					visited[i] = true;
					dfs(d+1, i+ti[i], sum+pi[i]);
					visited[i] = false;
					flag =1;
				}
			}
			if(flag==0){
				if(sum>Max){
					Max = sum;
				}
				return ;
			}
		}
	}

}
