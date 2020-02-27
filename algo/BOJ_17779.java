import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author kora1492
 재현시의 시장 구재현은 지난 몇 년간 게리맨더링을 통해서 자신의 당에게 유리하게 선거구를 획정했다. 견제할 권력이 없어진 구재현은 권력을 매우 부당하게 행사했고, 심지어는 시의 이름도 재현시로 변경했다. 이번 선거에서는 최대한 공평하게 선거구를 획정하려고 한다.

재현시는 크기가 N×N인 격자로 나타낼 수 있다. 격자의 각 칸은 구역을 의미하고, r행 c열에 있는 구역은 (r, c)로 나타낼 수 있다. 구역을 다섯 개의 선거구로 나눠야 하고, 각 구역은 다섯 선거구 중 하나에 포함되어야 한다. 선거구는 구역을 적어도 하나 포함해야 하고, 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 한다. 구역 A에서 인접한 구역을 통해서 구역 B로 갈 수 있을 때, 두 구역은 연결되어 있다고 한다. 중간에 통하는 인접한 구역은 0개 이상이어야 하고, 모두 같은 선거구에 포함된 구역이어야 한다.

선거구를 나누는 방법은 다음과 같다.

기준점 (x, y)와 경계의 길이 d1, d2를 정한다. (d1, d2 ≥ 1, 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N)
다음 칸은 경계선이다.
(x, y), (x+1, y-1), ..., (x+d1, y-d1)
(x, y), (x+1, y+1), ..., (x+d2, y+d2)
(x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
(x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
경계선과 경계선의 안에 포함되어있는 5번 선거구이다.
5번 선거구에 포함되지 않은 구역 (r, c)의 선거구 번호는 다음 기준을 따른다.
1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
아래는 크기가 7×7인 재현시를 다섯 개의 선거구로 나눈 방법의 예시이다.
 */
public class BOJ_17779 {
	static int N;
	static int d1, d2;
	static int x, y;
	static int map[][];
	static int check[][];
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int [N+1][N+1];
		for(int i=1;i<=N;i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=1;j<=N;j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int x=1;x<=N;x++){
			for(int y=1;y<=N;y++){
				for(int d1 = 1 ; d1 <= N ; ++d1) {
					for(int d2 = 1 ; d2 <= N ; ++d2) {
						if((x + d1 + d2) <= N &&
						   (y - d1) >= 1 && (y - d1) < y &&
						   (y + d2) > y && (y + d2) <= N) {
							
							gary(x, y, d1, d2);
						}
					}
				}
		}
		}
		System.out.println(min);
	}
	

	public static void gary(int x, int y, int d1, int d2){

		check = new int [N+1][N+1];
//		다음 칸은 경계선이다.

//		1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
//		2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
//		3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
//		4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
		
		downY(x, y, d1, d2);
		downY2(x, y, d1, d2);
		upY(x,y,d1,d2);
		upY2(x, y, d1, d2);
		
		for(int r = 1 ; r <= N ; ++r) {
			int left = -1;
			int right = -1;
			
			int idx = 1;
			while(idx <= N) {
				if(check[r][idx] == 5) {
					left = idx;
					break;
				}
				idx++;
			}
			
			idx = N;
			while(idx >= 0) {
				if(check[r][idx] == 5) {
					right = idx;
					break;
				}
				idx--;
			}
			
			if(left != right) {
				for(int i = left ; i < right ; ++i) check[r][i] = 5;
			}
		}
		
		
		for(int r=1;r<=N;r++){
			for(int c=1;c<=N;c++){
					if(check[r][c]!=0) continue;
					
					if(r< (x+d1) && c<=y){
						check[r][c] = 1;
					} else if(r<=(x+d2) && c>y){
						check[r][c] = 2;
					} else if((x+d1)<=r && c< (y-d1+d2)){
						check[r][c] = 3;
					} else if((x+d2)<r && (c>=y-d1+d2)){
						check[r][c] = 4;
					} else {
						check[r][c] = 5;
					}
			}		
		}
		



		int five[] = new int [5];
		for(int i=1;i<=N;i++){
			for(int j=1;j<=N;j++){
				if(check[i][j]==1){
					five[0] += map[i][j];
				} else if(check[i][j]==2){
					five[1] += map[i][j];
				} else if(check[i][j]==3){
					five[2] += map[i][j];
				} else if(check[i][j]==4){
					five[3] += map[i][j];
				} else if(check[i][j]==5 || check[i][j]==0){
					five[4] += map[i][j];
				}
			}
		}
		Arrays.sort(five);
		int tmpMin = five[4] - five[0];
		if(tmpMin<min){
			min = tmpMin;
		}
	}
//	(x, y), (x+1, y-1), ..., (x+d1, y-d1)
//	(x, y), (x+1, y+1), ..., (x+d2, y+d2)
//	(x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
//	(x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)

	public static void downY(int x, int y, int d1, int d2){
		int tmpx= x;
		int tmpy =y;
		while(true){
			if(tmpx<=x+d1 && tmpy>=y-d2){
				check[tmpx][tmpy] = 5;
				tmpx++;
				tmpy--;
			} 
			else {
				break;
			}
		}
	}
	public static void downY2(int x, int y, int d1, int d2){
		int tmpx= x+d2;
		int tmpy =y+d2;
		while(true){
			if(tmpx<=(x+d1+d2) && tmpy>=(y+d2-d1)){
				check[tmpx][tmpy] = 5;
				tmpx++;
				tmpy--;
			} 
			else {
				break;
			}
		}
	}
	public static void upY(int x, int y, int d1, int d2){
		int tmpx= x;
		int tmpy =y;
		while(true){
			if(tmpx<=(x+d1) && tmpy<=(y+d2)){
				check[tmpx][tmpy] = 5;
				tmpx++;
				tmpy++;
			} 
			else {
				break;
			}
		}
	}
	public static void upY2(int x, int y, int d1, int d2){
		int tmpx= x+d1;
		int tmpy =y-d1;
		while(true){
			if(tmpx<=(x+d1+d2) && tmpy<=(y-d1+d2)){
				check[tmpx][tmpy] = 5;
				tmpx++;
				tmpy++;
			} 
			else {
				break;
			}
		}
	}

}
