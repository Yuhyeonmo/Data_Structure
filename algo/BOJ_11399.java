import java.util.Arrays;
import java.util.Scanner;

/*
 * 
 */
public class BOJ_11399 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int p[] = new int [n];
		for(int i=0;i<n;i++){
			p[i] = sc.nextInt();
		}
		Arrays.sort(p);
		
		int sum = 0;
		
		// 1 2 3 3 4
		for(int i=n-1;i>=0;i--){
			sum+=p[i];
			for(int j=0;j<i;j++){
				sum+=p[j];
			}
		}
		System.out.println(sum);
		
	}

}
