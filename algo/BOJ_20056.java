import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 어떻게 Fireball들을 관리할지에 대해서 생각 -> 이차원 배열(좌표)에 List에다가 fireball들을 담음.
 * move(이동시키고) -> 합쳐서 쪼개는 순으로 진행
 * Move : 이동일 때 고려할 점. (R,C) 연결되어있음  / 속도 50제한이지만 그걸 하나씩 하면서 돌리며 시간이 오래걸림
 * 
 */

public class BOJ_20056 {

	static int N,M,K;
	static int dx[] = {-1,-1,0,1,1,1,0,-1};
	static int dy[] = {0,1,1,1,0,-1,-1,-1};
	static List<Fireball> map[][];
	
	static private class Fireball{
		int r;
		int c;
		int m;
		int d;
		int s;
		
		Fireball(int r, int c, int m, int s, int d){
			this.r = r;
			this.c = c;
			this.m = m;
			this.d = d;
			this.s = s;
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new ArrayList [N+1][N+1];
		for(int i=0;i<=N;i++)
		{
			for(int j=0;j<=N;j++){
				map [i][j]= new ArrayList<Fireball>();
			}
		}
		for(int i=0;i<M;i++){
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			map[r][c].add(new Fireball(r, c, m, s, d));
		}
		
		for(int i=0;i<K;i++){
			fbsmove();
			sumFire();
		}		
		int ans = 0;
		for(int i=1;i<=N;i++){
			for(int j=1;j<=N;j++){
				if(map[i][j].size()>0){
					for(Fireball f : map[i][j]){
						ans += f.m;
					}
				}
			}
		}
		System.out.println(ans);
	}
	public static void sumFire(){
		for(int i=1;i<=N;i++){
			for(int j=1;j<=N;j++){
				if(map[i][j].size()>1){
					int totM = 0;
					int totS = 0;
					int n = map[i][j].size();
					int even= 0, odd = 0;
					for(Fireball f : map[i][j]){
						totM += f.m;
						totS += f.s;
						if(f.d % 2 == 0){
							even++;
						}
						else {
							odd++;
						}
					}
					map[i][j].clear();
					if (totM / 5 == 0) continue;
					
					if (even==0 || odd==0) {
						for(int k=0;k<4;k++){
							map[i][j].add(new Fireball(i,j,totM/5,totS/n,k*2));
						}
					}
					else {
						for(int k=0;k<4;k++){
							map[i][j].add(new Fireball(i,j,totM/5,totS/n,k*2+1));
						}
					}
				}
			}
		}
	}
	public static void fbsmove(){
		ArrayList<Fireball> tmpList = new ArrayList<Fireball>();
		for(int i=1; i<=N;i++){
			for(int j=1;j<=N;j++){
				
				if(map[i][j].size()>0){
					
					for(int idx = 0;idx < map[i][j].size();idx++){
						
						Fireball tmp = map[i][j].get(idx);
						tmp.r = (tmp.r + dx[tmp.d] * tmp.s) % N;
						tmp.c = (tmp.c + dy[tmp.d] * tmp.s) % N;
						
						if(tmp.r > N) {
							tmp.r = tmp.r % N;
						}
						else if(tmp.r < 1) {
							tmp.r = N - (Math.abs(tmp.r) % N);
						}
						
						if(tmp.c > N) {
							tmp.c = tmp.c % N;
						}
						else if(tmp.c < 1) {
							tmp.c = N - (Math.abs(tmp.c) % N);
						}
						

						tmpList.add(tmp);
						map[i][j].remove(idx--);
						
					}
				}				
			}
		}
		
		for(Fireball f : tmpList){
			map[f.r][f.c].add(f);
		}

	}

}
