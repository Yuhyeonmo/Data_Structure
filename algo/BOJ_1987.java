import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
입력
첫째 줄에 R과 C가 빈칸을 사이에 두고 주어진다. (1 ≤ R,C ≤ 20) 둘째 줄부터 R개의 줄에 걸쳐서 보드에 적혀 있는 C개의 대문자 알파벳들이 빈칸 없이 주어진다.

출력
첫째 줄에 말이 지날 수 있는 최대의 칸 수를 출력한다.
 */
public class BOJ_1987 {
	static int r,c;
	
	static int dx[] = {0,0,-1,1};
	static int dy[] = {1,-1,0,0};
	static char [][] map;
	static int max = 1;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		for(int i=0;i<r;i++){
			map[i] = br.readLine().toCharArray();
		}
		HashSet<Character> start = new HashSet<Character>();
		start.add(map[0][0]);
		dfs(start, 0, 0);
		System.out.println(max);
 	}
	
	public static void dfs(HashSet<Character> set, int dr, int dc){
		
		int flag = 0;
		
		for(int i=0;i<4;i++){
			
			int tr = dr - dx[i];
			int tc = dc - dy[i];
			
			if(tr>=0 && tc>=0 && tr<r && tc<c && !set.contains(map[tr][tc])){
				set.add(map[tr][tc]);
				dfs(set, tr,tc);
				set.remove(map[tr][tc]);
			}
			
		}
		
		if(set.size() > max) {
			//System.out.println(set.toString());
			max = set.size();
		}
	}

}
