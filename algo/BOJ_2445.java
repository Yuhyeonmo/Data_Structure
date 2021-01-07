import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BOJ_2445 {
/*
*        *
**      **
***    ***
****  ****
**********
****  ****
***    ***
**      **
*        *


*    *   
**  **      
******        
**  **      
*    *   
N = 5        
1 8
2 6
3 4
4 2
5 0
6 2
7 4
8 6
9 8  

N = 1 
1 0

N = 3
1 4
2 2
3 0
4 0

1 1
2 2
3 3
4 4
5 5
6 4
7 3
8 2
 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0;i<2*N-1;i++){
			for(int j=N;j>Math.abs(N-i-1);j--){
				System.out.print("*");
			}
			for(int j=0;j<(Math.abs(N-1-i))*2;j++){
				System.out.print(" ");
			}
			for(int j=N;j>Math.abs(N-i-1);j--){
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
