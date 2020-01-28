import java.util.Arrays;
import java.util.Scanner;

/*
문제

왕비를 피해 일곱 난쟁이들과 함께 평화롭게 생활하고 있던 백설공주에게 위기가 찾아왔다. 일과를 마치고 돌아온 난쟁이가 일곱 명이 아닌 아홉 명이었던 것이다.

아홉 명의 난쟁이는 모두 자신이 "백설 공주와 일곱 난쟁이"의 주인공이라고 주장했다. 뛰어난 수학적 직관력을 가지고 있던 백설공주는, 다행스럽게도 일곱 난쟁이의 키의 합이 100이 됨을 기억해 냈다.

아홉 난쟁이의 키가 주어졌을 때, 백설공주를 도와 일곱 난쟁이를 찾는 프로그램을 작성하시오.

입력
아홉 개의 줄에 걸쳐 난쟁이들의 키가 주어진다. 주어지는 키는 100을 넘지 않는 자연수이며, 아홉 난쟁이의 키는 모두 다르며, 가능한 정답이 여러 가지인 경우에는 아무거나 출력한다.

출력
일곱 난쟁이의 키를 오름차순으로 출력한다. 일곱 난쟁이를 찾을 수 없는 경우는 없다.
 */
public class BOJ_2309 {
	static int nan [] = new int [9];
	
	static boolean check[] = new boolean[9];
	static int flag = 0; // 중복 출력을 방지하기 위한 변수
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int i=0;i<9;i++){
			nan[i] = sc.nextInt();
		}
		Arrays.sort(nan); // 오름차순으로 출력하기 위해서 미리 sort 함
		dfs(0,0);

	}
	
	public static void dfs(int depth , int index){
		if(flag == 1) { // 이미 난쟁이를 발견함
			return ;
		}
		
		if(depth>7) {
			return ;
		}
		else if(depth==7){
			int res = 0;
			if(flag == 0 ) {
				for(int i=0;i<9;i++){
					if(check[i]){
						res+=nan[i];
					}
				}
				
				if(res == 100){
					flag = 1;
					for(int i=0;i<9;i++){
						if(check[i]){
						System.out.println(nan[i]);
						}
					}
				}
			}
			else {
				return ;
			}
		}
		else {
			for(int i=index; i<9;i++){
				// 현재의 index 난쟁이를 선택함.
				check[i] = true;
				dfs(depth+1, index+1);
				
				//현재 index 난쟁이를 선택하지 않고 넘긴 경우.
				check[i] = false;
				dfs(depth, index+1);
			}
		}
	}

}
