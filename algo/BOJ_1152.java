import java.util.Scanner;

/** 백준 1152번 Java
   문제
영어 대소문자와 띄어쓰기만으로 이루어진 문자열이 주어진다. 
이 문자열에는 몇 개의 단어가 있을까? 이를 구하는 프로그램을 작성하시오. 단, 한 단어가 여러 번 등장하면 등장한 횟수만큼 모두 세어야 한다.

   입력
첫 줄에 영어 대소문자와 띄어쓰기로 이루어진 문자열이 주어진다. 
이 문자열의 길이는 1,000,000을 넘지 않는다. 단어는 띄어쓰기 한 개로 구분되며, 공백이 연속해서 나오는 경우는 없다. 또한 문자열의 앞과 뒤에는 공백이 있을 수도 있다. 
  
  해결 
문자열 처리 관련 문제 / 스플릿을 쓰는 부분까지 쉽게 생각할 수 있지만, 조금 고려해야 될 게 있는 문제이다.
1. 스플릿을 쓸 경우 input 이 들어오지 않았을 경우를 처리해야하며,
2. 공백이 앞 뒤로 나올 수 있기에 첫번쩨 단어부터 뛰어쓰기가 있는 경우를 처리해야 함.
 **/
public class BOJ_1152 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		input = input.trim(); // 2. 고려사항 처리함. 처음 단어 앞에 뛰어쓰기 제거함.
		
		String [] sinput = input.split(" ");
		int res = sinput.length;
		if(input.isEmpty()){ // input 이 업을 경우, 0을 출력함.
			System.out.println("0");
		}
		else{
			System.out.println(res);
		}
	}

}
