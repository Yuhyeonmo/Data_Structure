import java.util.Scanner;

/*
 * 123123123123554334334324234234444432423423423423423123123123123123123
 *               3  3        3  33333  3  3  3  3  3 
 *               1  1        1  11111  1  1  1  1  1
 */
public class ForegoneSolution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
	
		for(int tc = 1; tc<=T; tc++){
			String input = sc.next();
			
//			int a = 0;
//			int b = 0;
//			
//			a = Integer.parseInt(input.replace('4', '3'));
//			b = Integer.parseInt(input) - a;
//			
//			System.out.println("Case #"+tc+": "+a +" " + b);
			
			StringBuilder sb= new StringBuilder();
			int flag = 0;
			for(int i=0;i<input.length();i++){
				char c = input.charAt(i);
				if(c!='4'){
					
					if(flag==1) sb.append(0);
					continue;
				}
				else {
					sb.append(1);
					flag = 1;
				}
			}
			String a = input.replace('4', '3');
			String b = sb.toString();
			System.out.println("Case #"+tc+": "+a +" " + b);

		}
	}

}
