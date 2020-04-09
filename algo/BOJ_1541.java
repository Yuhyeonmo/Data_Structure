import java.util.Scanner;

/*
세준이는 양수와 +, -, 그리고 괄호를 가지고 길이가 최대 50인 식을 만들었다. 그리고 나서 세준이는 괄호를 모두 지웠다.

그리고 나서 세준이는 괄호를 적절히 쳐서 이 식의 값을 최소로 만들려고 한다.

괄호를 적절히 쳐서 이 식의 값을 최소로 만드는 프로그램을 작성하시오.

입력
첫째 줄에 식이 주어진다. 식은 ‘0’~‘9’, ‘+’, 그리고 ‘-’만으로 이루어져 있고, 가장 처음과 마지막 문자는 숫자이다. 그리고 연속해서 두 개 이상의 연산자가 나타나지 않고, 5자리보다 많이 연속되는 숫자는 없다. 수는 0으로 시작할 수 있다.
// 40 - 50 - 50 - 50 - 50 + 40 + 40 + 40 =
 * 50 - 40 - 40 - 40 - 40 - 40 - 40 -( 40 - 40 )- 40 - 40 - 40 - 40 =
 * 40 - 50 + 30 
 * 40 - 140 
출력
첫째 줄에 정답을 출력한다.
 */
public class BOJ_1541 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		int ans = 0;
		
		int preminus = 0;
		
		StringBuilder sb = new StringBuilder();
		for(int idx=0;idx<input.length();idx++){
			
			char c = input.charAt(idx);
			if(c=='+' || c=='-'){
				if(preminus==1){
					ans-=Integer.parseInt(sb.toString()); 
				}
				else {
					ans+=Integer.parseInt(sb.toString());
				}
				
				if(c=='-') preminus=1;
				
				sb = new StringBuilder();
			}
		
			else {
				sb.append(c);
			}
			
		}
		if(preminus==1){
			ans-=Integer.parseInt(sb.toString());
		}
		else {
			ans+=Integer.parseInt(sb.toString());
		}
		System.out.println(ans);
	}

}
