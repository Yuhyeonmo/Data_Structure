import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
DFS로 찾아줌.
사전순이므로 미리 입력된 알파벳을 정렬 시킴.
 */
public class BOJ_1759 {
	static int L,C;
	static char [] in;
	
	static char [] mo = {'a','e','i','o','u'};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		String input = br.readLine();
		st = new StringTokenizer(input); 
		in = new char [C];
		for(int i=0;i<C;i++){
			in[i] = st.nextToken().charAt(0);
		}
		// 사전식 정렬
		Arrays.sort(in);
		
		
		for(int i=0;i<=C-L;i++){
			char start = in[i];
			int flag =0;
			for(char c : mo){
				if(c==start){
					calc(1,0,i,""+start);
					flag =1;
				}
			}
			if(flag==0){
				calc(0,1,i,""+start);
			}
		}
		
	}
	public static void calc(int cntM, int cntJ, int idx, String input){
		
			// 조건에 해당하는 경우 출력
			if(input.length()==L && cntM>=1 && cntJ>=2){
				System.out.println(input);
			}
			else {
				for(int ni=idx+1;ni<C;ni++){
				if(idx<C){
					
					char next = in[ni];
					int flag = 0;
					for(char c : mo){
						if(c==next){
							calc(cntM+1,cntJ,ni,input+next);
							flag =1;
						}
					}
					if(flag==0){
						calc(cntM,cntJ+1,ni,input+next);
					}
				}
			}
			}
		
	}
}
