import java.util.Scanner;

/**
 * 
 * BOJ 1259 펠린드롬 수
 * 펠린드롬인지 여부를 파악하는 문제
 * 너무 어렵게 생각함. 
 * -> 홀짝인지 구별한 다음, 좌우로 나누고 좌 또는 우를 reverse 시켜서 같은 지 검사함.
 * 
 * 조금 더 쉬운 해결법
 * 그냥 문자열의 시작과 끝을 비교하는 함수를 만들어서 다른 경우 나오면 바로 return false를 주도록 만들면 될 거 같음.
 */
public class BOJ_1259 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			String input = sc.nextLine();
			if(input.equals("0"))
			{
				break;
			}
			else
			{
				String left = "";
				StringBuffer right = new StringBuffer();
				if(input.length()%2==0)
				{
					for(int i=0;i<input.length()/2;i++){
						left += input.charAt(i);
					}
					for(int i=input.length()/2; i<input.length();i++)
					{
						right.append(input.charAt(i));
					}
					right.reverse();
					if(left.equals(right.toString()))
					{
						System.out.println("yes");
					}
					else
					{
						System.out.println("no");
					}
					
				}
				else
				{

					if(input.length()%2==1)
					{
						for(int i=0;i<input.length()/2;i++){
							left += input.charAt(i);
						}
						for(int i=input.length()/2+1; i<input.length();i++)
						{
							right.append(input.charAt(i));
						}
						right.reverse();
						if(left.equals(right.toString()))
						{
							System.out.println("yes");
						}
						else
						{
							System.out.println("no");
						}
				}
			}
		}
		
	}
	}

}
