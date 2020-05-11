import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*

문제
각각 부피가 A, B, C(1≤A, B, C≤200) 리터인 세 개의 물통이 있다. 
처음에는 앞의 두 물통은 비어 있고, 세 번째 물통은 가득(C 리터) 차 있다. 
이제 어떤 물통에 들어있는 물을 다른 물통으로 쏟아 부을 수 있는데, 이때에는 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 물을 부을 수 있다. 이 과정에서 손실되는 물은 없다고 가정한다.

이와 같은 과정을 거치다보면 세 번째 물통(용량이 C인)에 담겨있는 물의 양이 변할 수도 있다. 
첫 번째 물통(용량이 A인)이 비어 있을 때, 세 번째 물통(용량이 C인)에 담겨있을 수 있는 물의 양을 모두 구해내는 프로그램을 작성하시오.

입력
첫째 줄에 세 정수 A, B, C가 주어진다.

출력
첫째 줄에 공백으로 구분하여 답을 출력한다. 각 용량은 오름차순으로 정렬한다.
 */
public class BOJ_2251 {
	static TreeSet<Integer> ans;
	static boolean check[][][];
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		ans = new TreeSet<Integer>();
		check = new boolean [a+1][b+1][c+1];
		check[0][0][c] = true;
		bfs(a,b,c);
		StringBuilder sb = new StringBuilder();
		for(int i : ans){
			sb.append(i+" ");
		}
		System.out.println(sb.toString());
	}
	public static void bfs(int a, int b, int c){
		
		Queue<Bucket> q = new LinkedList<Bucket>();
		q.add(new Bucket(0, 0, c));
		
		while(!q.isEmpty()){
			
			Bucket tmp = q.poll();
			
			if(tmp.a == 0){
				ans.add(tmp.c);
			}
			// A->B , A->C / B->A, B->C / C->A , C->B	
			// A
			if(tmp.a!=0){
				// over
				if(tmp.a+tmp.b>=b){
					int tmpA = tmp.a - (b-tmp.b);
					if(!check[tmpA][b][tmp.c]){
						q.add(new Bucket(tmpA, b, tmp.c));
						check[tmpA][b][tmp.c] = true;
					}
				}
				if(tmp.a<b-tmp.b){
					int tmpB = tmp.b + tmp.a;
					if(!check[0][tmpB][tmp.c]){
						q.add(new Bucket(0, tmpB, tmp.c));
						check[0][tmpB][tmp.c] = true;
					}
				}
				
				if(tmp.a+tmp.c>=c){
					int tmpA = tmp.a - (c-tmp.c);
					if(!check[tmpA][tmp.b][c]){
						q.add(new Bucket(tmpA, tmp.b, c));
						check[tmpA][tmp.b][c] = true;
					}
				}
				if(tmp.a<c-tmp.c){
					int tmpC = tmp.c + tmp.a;
					if(!check[0][tmp.b][tmpC]){
						q.add(new Bucket(0, tmp.b, tmpC));
						check[0][tmp.b][tmpC] = true;
					}
				}
			}
			
			// B
			if(tmp.b!=0){
				// over
				if(tmp.a+tmp.b>=a){
					int tmpB = tmp.b - (a-tmp.a);
					if(!check[a][tmpB][tmp.c]){
						q.add(new Bucket(a, tmpB, tmp.c));
						check[a][tmpB][tmp.c] = true;
					}
				}
				if(tmp.b<a-tmp.a){
					int tmpA = tmp.b + tmp.a;
					if(!check[tmpA][0][tmp.c]){
						q.add(new Bucket(tmpA, 0, tmp.c));
						check[tmpA][0][tmp.c] = true;
					}
				}
				
				if(tmp.c+tmp.b>=c){
					int tmpB = tmp.b - (c-tmp.c);
					if(!check[tmp.a][tmpB][c]){
						q.add(new Bucket(tmp.a, tmpB, c));
						check[tmp.a][tmpB][c] = true;
					}
				}
				if(tmp.b<c-tmp.c){
					int tmpC = tmp.b + tmp.c;
					if(!check[tmp.a][0][tmpC]){
						q.add(new Bucket(tmp.a, 0, tmpC));
						check[tmp.a][0][tmpC] = true;
					}
				}
			}
			
			// C
			if(tmp.c!=0){
				// over
				if(tmp.a+tmp.c>=a){
					int tmpC = tmp.c - (a-tmp.a);
					if(!check[a][tmp.b][tmpC]){
						q.add(new Bucket(a, tmp.b, tmpC));
						check[a][tmp.b][tmpC] = true;
					}
				}
				if(tmp.c<a-tmp.a){
					int tmpA = tmp.c + tmp.a;
					if(!check[tmpA][0][tmp.c]){
						q.add(new Bucket(tmpA, tmp.b, 0));
						check[tmpA][tmp.b][0] = true;
					}
				}
				
				if(tmp.b+tmp.c>=b){
					int tmpC = tmp.c - (b-tmp.b);
					if(!check[tmp.a][b][tmpC]){
						q.add(new Bucket(tmp.a, b, tmpC));
						check[tmp.a][b][tmpC] = true;
					}
				}
				if(tmp.c<b-tmp.b){
					int tmpB = tmp.c + tmp.b;
					if(!check[tmp.a][tmpB][0]){
						q.add(new Bucket(tmp.a, tmpB, 0));
						check[tmp.a][tmpB][0] = true;
					}
				}
			}
		}
	}

	public static class Bucket
	{
		int a;
		int b;
		int c;
		
		public Bucket(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
}
