import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * M개 상어
 * 각자 우선순위가 존재(현재 방향에서 가지 못할 경우)
 * 냄새가 안뿌려진곳으로 이동(K번 이동하면 냄새는 사라짐)
 * 처음에 맵이 주어지고 상어 번호나옴.
 * 1 : 위  , 2 : 아래 , 3: 오른쪽 , 4: 왼쪽
 */
public class BOJ_19237 {
static int dx[] = {0,1,-1,0,0};
static int dy[] = {0,0,0,1,-1};
static int N,M,k;
static int [][] map;
static int [][] smellMap;
static int cnt;
public static void main(String [] args) throws IOException{
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());
	N = Integer.parseInt(st.nextToken());
	M = Integer.parseInt(st.nextToken());
	k = Integer.parseInt(st.nextToken());
	map = new int [N][N];
	smellMap = new int [N][N];
	cnt = 0;
	ArrayList<shark> sharks = new ArrayList<shark>();
	for(int i=0;i<N;i++){
		st = new StringTokenizer(br.readLine());
		for(int j=0;j<N;j++){
			map[i][j] = Integer.parseInt(st.nextToken());
			if(map[i][j]!=0){
				sharks.add(new shark(i,j,map[i][j]));
				smellMap[i][j] = k;
			}		
		}
	}
	Collections.sort(sharks);
	st = new StringTokenizer(br.readLine());
	
	for(int i=0;i<M;i++){
		int d = Integer.parseInt(st.nextToken());
		sharks.get(i).dir = d;
	}
	
	for(int i=0;i<4*M;i++){
		st = new StringTokenizer(br.readLine());
		for(int j=0;j<4;j++){
			sharks.get(i/4).p.get(i%4).add(Integer.parseInt(st.nextToken()));
		}
	}
	
	
}

public static void run(ArrayList<shark> s){
	
	while(s.size()!=1){
		
		cnt++;
		movesharks(s);
		deletesharks(s);
		deleteSmell();
	
	}	
}

public static void movesharks(ArrayList<shark> s){
	
	for(shark tmp : s){
		
		ArrayList<Integer> pr = new ArrayList<Integer>();
		pr = tmp.p.get(tmp.dir);
		
		for(int sdir : pr){
			
			int tx = tmp.x + dx[sdir];
			int ty = tmp.y + dy[sdir];
			if(tx<0 || ty<0 || tx>=N || ty>=N || smellMap[tx][ty]!=0){
				continue;
			}
			else {
				tmp.dir = sdir;
				tmp.x = tx;
				tmp.y = ty;
				break;
			}	
		}	
	}
	
}
public static void deletesharks(ArrayList<shark> s){
	
	for(shark tmp : s){
		
		if(smellMap[tmp.x][tmp.y]!=0){
			s.remove(tmp);
		}
		else {
			smellMap[tmp.x][tmp.y] = k+1;
		}
		
	}
	
}
public static void deleteSmell(){
	for(int i=0;i<N;i++){
		for(int j=0;j<N;j++){
			if(smellMap[i][j]>0){
				smellMap[i][j]--;
			}
		}
	}
}

private static class shark implements Comparable<shark>{
	int x;
	int y;
	int num;
	int dir;
	ArrayList<ArrayList<Integer>> p = new ArrayList<>();
	
	shark(int x, int y, int num){
		this.x = x;
		this.y = y;
		this.num = num;
	}

	@Override
	public int compareTo(shark o) {
		// TODO Auto-generated method stub
		return o.num - this.num;
	}
	
}
}