import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author YuhyeonMo
 * 촌수계산
 * 입력 : 첫째 줄에는 전체 사람의 수 n, 둘째 줄에는 촌수를 계산해야 하는 서로 다른 두 사람의 번호, 셋째 줄에는 부모 자식들 간의 관계의 개수 m
 * 출력 : 입력에서 요구한 두 사람의 촌수를 나타내는 정수를 출력한다. 어떤 경우에는 두 사람의 친척 관계가 전혀 없어 촌수를 계산할 수 없을 때가 있다. 이때에는 -1을 출력해야 한다.
 * 
 * 풀이과정 : 두 사람의 번호사이의 경로의 길이를 구하는 것으로 생각함.
 * 		      경로의 길이를 구하기위해서 BFS를 사용함.
 *         주의) 촌수가 없으면 -1을 출력함을 기억
 *   
 */
public class BOJ_2644 {
	static int n; // 전체 사람의 수 n
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		int ansX = scan.nextInt();
		int ansY = scan.nextInt();
		
		int M = scan.nextInt();
		int rel [][] = new int [n+1][n+1];
 		for(int i=0;i<M;i++){
 			int x = scan.nextInt();
 			int y = scan.nextInt();
			rel[x][y] = 1;
			rel[y][x] = 1;
		}
 		System.out.println(count(rel, ansX, ansY));
	}
	
	// 촌수를 세기위한 함수
	public static int count(int rel[][],int x, int y){
		
		Queue q = new LinkedList<Integer>();
		boolean visited [] = new boolean[n+1];
		int count = 0;
		q.add(x);
		int size = q.size();
		while(!q.isEmpty()){
			/*
			 * size를 만들어서 queue의 크기만큼 반복하게 함.
			 * 이렇게 만들면 같은 레벨에 해당하는 것들을 다 찾아볼 수 있고, while 뒤에 나타난 count++에 대한 로직에서 문제가 안생김.
			 * while문이 없다면 경로에 해당되지 않은 사람을 탐색해도 count++ 가 되어서 답보다 더 큰 값이 나오는 경우가 생김 
			 */
			while(size>0){
				int idx = (int) q.poll();
				visited[idx] = true;
			for(int i=1;i<n+1;i++){
				if(rel[idx][i]==1 && !visited[i]){
					q.add(i);
					if(i==y) {
						count++;
						return count;
					}
				}
			}
				size--;
			}
			size = q.size();
			count++;			
		}

		return -1;
	}

}
