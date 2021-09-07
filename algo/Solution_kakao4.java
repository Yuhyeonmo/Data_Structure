
public class Solution_kakao4 {
	static int N;
	static int map[][];
	
	 public static int solution(int n, int s, int a, int b, int[][] fares) {
	        int answer = 0;
	        map = new int [n+1][n+1];
	        N = n;
	        for(int i=0;i<fares.length;i++){
	        	int n1 = fares[i][0];
	        	int n2 = fares[i][1];
	        	int dist = fares[i][2];
	        	
	        	map[n1][n2] = dist;
	        	map[n2][n1] = dist;
	        	
	        }
	        
	        int sdist [] = dijkstra(s);
	        int adist [] = dijkstra(a);
	        int bdist [] = dijkstra(b);
	        
	        int notSharing = sdist[a]+sdist[b];
	        int min = notSharing;
	        for(int i=1;i<n+1;i++){
	        	
	        	if(sdist[i]==Integer.MAX_VALUE || adist[i]==Integer.MAX_VALUE || bdist[i]==Integer.MAX_VALUE) continue;
	        	
	        	if(min>sdist[i]+adist[i]+bdist[i]){
	        		min = sdist[i]+adist[i]+bdist[i];
	        	}
	        	
	        	
	        }
	        
	        
	        return min;
	    }
	 
	 public static int [] dijkstra(int v){
		 
		 int [] distance = new int [N+1];
		 boolean [] check = new boolean [N+1];
		 
		 for(int i=1;i<N+1;i++){
			 distance[i] = Integer.MAX_VALUE;			 
		 }
		 
		 distance[v] = 0;
		 check[v] = true;
		 
		 for(int i=1;i<N+1;i++){
			 if(!check[i] && map[v][i] != 0){
				 distance[i] = map[v][i];
			 }
		 }
		 
		 for(int i=0;i<N-1;i++){
			 
			 int min = Integer.MAX_VALUE;
			 int min_idx = -1;
			 
			 for(int j=1;j<N+1;j++){
				 
				 if(!check[j] && distance[j]!=Integer.MAX_VALUE){
					 if(distance[j]<min) {
						 min  = distance[j];
						 min_idx =j;
					 }				 
				 }	 
			 }
			 check[min_idx] = true;
			 for(int j=1;j<N+1;j++){
				 
				 if(!check[j] && map[min_idx][j]!=0){
					 
					 if(distance[j]>distance[min_idx]+map[min_idx][j]){
						 distance[j] = distance[min_idx]+map[min_idx][j];
					 }
					 
				 }
				 
			 }
		 }
		 
		 return distance;
	 }
}
