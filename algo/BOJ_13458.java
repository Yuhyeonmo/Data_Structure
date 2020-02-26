import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author kora1492
 *	문제
총 N개의 시험장이 있고, 각각의 시험장마다 응시자들이 있다. i번 시험장에 있는 응시자의 수는 Ai명이다.

감독관은 총감독관과 부감독관으로 두 종류가 있다. 총감독관은 한 방에서 감시할 수 있는 응시자의 수가 B명이고, 부감독관은 한 방에서 감시할 수 있는 응시자의 수가 C명이다.

각각의 시험장에 총감독관은 오직 1명만 있어야 하고, 부감독관은 여러 명 있어도 된다.

각 시험장마다 응시생들을 모두 감시해야 한다. 이때, 필요한 감독관 수의 최솟값을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 시험장의 개수 N(1 ≤ N ≤ 1,000,000)이 주어진다.

둘째 줄에는 각 시험장에 있는 응시자의 수 Ai (1 ≤ Ai ≤ 1,000,000)가 주어진다.

셋째 줄에는 B와 C가 주어진다. (1 ≤ B, C ≤ 1,000,000)

출력
각 시험장마다 응시생을 모두 감독하기 위해 필요한 감독관의 최소 수를 출력한다.

풀이 : 생각보다 너무 쉬운 문제 / 정답률에 속지 말 것.
          그냥 조건대로 넣으면 끝. / 총감독관은 무조건 1명은 방에 들어가야 한다.
 */
public class BOJ_13458 {
	static int N;
	static int examRoom[];
	static int b, c;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		examRoom = new int [N+1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=1;i<=N;i++){
			examRoom[i] = Integer.parseInt(st.nextToken());
			
		}
		st = new StringTokenizer(br.readLine(), " ");
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		long sum= 0;
		for(int i=1;i<=N;i++){
			if(examRoom[i] - b <=0){
				sum++; // 총감독관 한명으로 감독 가능
			} else {
				examRoom[i] -= b; // 총 감독관이 들어가고 감독해야 할 인원
				
				// 나누어 떨어지는 경우  
				if(examRoom[i] % c == 0){
					sum += 1 + examRoom[i] / c;
				} else {
					// 나누어 떨어지지 않기 때문에 부 감독관이 한명 더 들어가야 함.
					sum += 2 + examRoom[i] / c;
				}
			}
		}

		System.out.println(sum);
	}

}
