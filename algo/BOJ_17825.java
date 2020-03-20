import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author kora1492
처음에는 시작 칸에 말 4개가 있다.
말은 게임판에 그려진 화살표의 방향대로만 이동할 수 있다. 말이 파란색 칸에서 이동을 시작하면 파란색 화살표를 타야 하고, 이동하는 도중이거나 파란색이 아닌 칸에서 이동을 시작하면 빨간색 화살표를 타야 한다. 
말이 도착 칸으로 이동하면 주사위에 나온 수와 관계 없이 이동을 마친다.
게임은 10개의 턴으로 이루어진다. 매 턴마다 1부터 5까지 한 면에 하나씩 적혀있는 5면체 주사위를 굴리고, 도착 칸에 있지 않은 말을 하나 골라 주사위에 나온 수만큼 이동시킨다.
말이 이동을 마치는 칸에 다른 말이 있으면 그 말은 고를 수 없다. 단, 이동을 마치는 칸이 도착 칸이면 고를 수 있다.
말이 이동을 마칠 때마다 칸에 적혀있는 수가 점수에 추가된다.
주사위에서 나올 수 10개를 미리 알고 있을 때, 얻을 수 있는 점수의 최댓값을 구해보자.

입력
첫째 줄에 주사위에서 나올 수 10개가 순서대로 주어진다.

2
4
6
8
10 13 16 19 25 30 35 40
12
14
16
18
20 - 22 24 25 30 35 40
22
24
26
28
30 - 28 27 26 25 30 35 40
32
34
36
38
40

출력
얻을 수 있는 점수의 최댓값을 출력한다.
 *
 */
public class BOJ_17825 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<10;i++){
			input[i] = Integer.parseInt(st.nextToken());
		}
		max = Integer.MIN_VALUE;
		for(int i=0;i<4;i++){
			horse[i] = new Horse(i);
		}
		calc(0);
		System.out.println(max);
	}
    static int[][] lines = {
        {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40},
        {10, 13, 16, 19},
        {30, 28, 27, 26},
        {20, 22, 24, 25, 30, 35, 40}
};
	static Horse horse [] = new Horse[4];
	static int max;
	static int input [] = new int [10];
	static int moves[] = new int [10];
	public static void calc(int turn){
		if(turn==10){
			int calc = 0;
			for(int i=0;i<4;i++){
				horse[i].l = 0;
				horse[i].pos = 0;
			}
			int now_line, now_idx;
			for(int i=0;i<10;i++){
				boolean ok = horse[moves[i]].move(input[i]);
				if(ok){
					now_line = horse[moves[i]].l;
					now_idx = horse[moves[i]].pos;
					
					if(now_line ==3 && now_idx == 7){
						continue;
					}
					
					else {
						calc += lines[now_line][now_idx];
					}
				}
				else {
					return ;
				}
			}
			
			if(calc>max){
				max = calc;
			}
			return ;
		}	
		
		for(int i=0;i<4;i++){
			moves[turn] = i;
			calc(turn+1);
		}
		
	}
	
	public static class Horse{
		int pos;
		int l;
		int num;
		public Horse(int num){
			this.num = num;
			this.pos = 0;
			this.l =0;
		}
		
        public boolean move(int m) {
            int next = pos + m;
            int nl = l;
            if(l == 0) {
                if(next == 5) {
                    nl = 1;
                    next = 0;
                }else if(next == 10) {
                    nl = 3;
                    next = 0;
                }else if(next == 15) {
                    nl = 2;
                    next = 0;
                }else if(next == 20) {
                    nl = 3;
                    next = 6;
                }else if(next > 20) {
                    nl = 3;
                    next = 7;
                }
            }else if(l == 1) {
                if(next>3) {
                    nl = 3;
                    next--;
                    if(next > 6) {
                        next = 7;
                    }
                }
            }else if(l==2) {
                if(next>3) {
                    nl = 3;
                    next--;
                    if(next > 6) {
                        next = 7;
                    }
                }
            }else if(l == 3) {
                if(next>6) {
                    next = 7;
                }
            }
            
            if(nl == 3 && next == 7) {
                l = nl;
                pos = next;
                return true;
            }
			
			for(int i=0;i<4;i++){
				if(num==i){
					continue;
				}
				if(horse[i].l==3 && horse[i].pos == 7){
					continue;
				}
				if(horse[i].l == 0 && horse[i].pos == 0){
					continue;
				}
				if(horse[i].l == nl && horse[i].pos == next){
					return false;
				}
			}
			l = nl;
			pos = next;
			return true;
		}
	}

/*
 * import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
 
public class Main {
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i = 0 ; i < 10 ; i++) {
            dices[i] = Integer.parseInt(st.nextToken());
        }
        
        // 초기화! 
        max = Integer.MIN_VALUE;
        for(int i = 0 ; i < 4; i++) {
            horses[i] = new horse(i);
        }
        
        dfs(0);
        System.out.println(max);
    }
    static int[][] lines = {
            {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40},
            {10, 13, 16, 19},
            {30, 28, 27, 26},
            {20, 22, 24, 25, 30, 35, 40}
    };
    static int[] dices = new int[10];
    
    static int max;
    // moves[i]: i번째 주사위가 나왔을 때, 이동할 말의 index
    static int[] moves = new int[10];
    static void dfs(int pos) {
        
        if(pos == 10) {
            
            // 말 초기화!!
            for(int i = 0 ; i < 4 ; i++) {
                horses[i].l = 0;
                horses[i].pos = 0;
            }
            int score = 0;
            int h_l=0, h_pos;
            for(int i = 0 ; i < 10; i++) {
                boolean ok = horses[moves[i]].move(dices[i]);
                if(ok) {
                    h_l = horses[moves[i]].l;
                    h_pos = horses[moves[i]].pos;
                    // line 3, index(pos) 7을 도착 지점이라고 정했다. 
                    if(h_l == 3 && h_pos == 7)
                        continue;
                    else
                        score += lines[h_l][h_pos];
                }else
                    return ;
            }
            if(max < score) {
                max = score;
            }
            return;
        }
        
        for(int h = 0 ; h < 4 ; h++) {
            moves[pos] = h;
            dfs(pos+1);
        }
    }
    
    static horse[] horses = new horse[4];
    static class horse {
        int l ;
        int pos ;
        int num;
        
        public horse(int num) {
            this.num = num;
            this.l = 0;
            this.pos = 0;
        }
        
        public boolean move(int m) {
            int next = pos + m;
            int nl = l;
            if(l == 0) {
                if(next == 5) {
                    nl = 1;
                    next = 0;
                }else if(next == 10) {
                    nl = 3;
                    next = 0;
                }else if(next == 15) {
                    nl = 2;
                    next = 0;
                }else if(next == 20) {
                    nl = 3;
                    next = 6;
                }else if(next > 20) {
                    nl = 3;
                    next = 7;
                }
            }else if(l == 1) {
                if(next>3) {
                    nl = 3;
                    next--;
                    if(next > 6) {
                        next = 7;
                    }
                }
            }else if(l==2) {
                if(next>3) {
                    nl = 3;
                    next--;
                    if(next > 6) {
                        next = 7;
                    }
                }
            }else if(l == 3) {
                if(next>6) {
                    next = 7;
                }
            }
            
            if(nl == 3 && next == 7) {
                l = nl;
                pos = next;
                return true;
            }
            
            // 중복 처리 
            for(int i = 0 ; i < 4 ; i++) {
                if(num == i)
                    continue;
                if(horses[i].l == 3 && horses[i].pos == 7)
                    continue;
                if(horses[i].l == 0 && horses[i].pos == 0)
                    continue;
                
                if(horses[i].l == nl && horses[i].pos == next ) {
                    return false;
                }
            }
            l = nl;
            pos = next;
            return true;
        }
    }
}
 */
		
}
