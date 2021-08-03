import java.util.ArrayList;

// DP 
// 실제로 수기로 해당 숫자로 만들어지는 모양으로 분석유형 (O) 
// 또는 전/전전의 데이터를 활용해서 이루어지는 유형
// 재귀적으로 도는 모양을 찾아 정규화 점화식.
public class Npresent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(2, 11));
		
	}

	public static int solution(int N, int number) {
        int answer = -1;
        
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        String nS = "";
        for(int i=1;i<=8;i++){
        	nS+=N;
        	arr.add(new ArrayList<Integer>());
        	arr.get(i-1).add(Integer.parseInt(nS));
        	
        }
        
        for(int i=1;i<8;i++){
        	for(int j=0;j<i;j++){
        		for(int op1 : arr.get(j)){
        			for(int op2 : arr.get(i-j-1)){
        				arr.get(i).add(op1+op2);
        				arr.get(i).add(op1-op2);
        				arr.get(i).add(op1*op2);
        				if(op2!=0) arr.get(i).add(op1/op2);
        			}
        		}  			
        	}
        	
        	for(int ans : arr.get(i)){
        		if(number==ans) return i+1;
        	}
        }
        return answer;
    }
}
