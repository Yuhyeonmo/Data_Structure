import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class D4_Solution_8934 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bf.readLine());
		for(int T = 0 ; T<tc; T++)
		{
			String input = bf.readLine();
			int abc[] = new int[3];
			// cbcacbc  
			for(int i = 0; i< input.length(); i++)
			{
				System.out.print("#"+(T+1)+" ");
				if(input.charAt(i) == 'a')
				{
					abc[0]++;
				}
				else if(input.charAt(i) == 'b')
				{
					abc[1]++;
				}
				else
				{
					abc[2]++;
				}
			}
			if(input.length()==1)
			{
				System.out.println("YES");
			}
			else
			{
				Arrays.sort(abc);
				int mean = abc[0];
				String output = "YES";
				for(int i=0;i<3;i++)
				{
					if(abc[i]-mean > 1 ){
						output = "NO";
						break;
					}
				}
				
				System.out.println(output);
				
			}
				
			
		}
	}

}
