

import java.util.*;
// 다시 풀어야 됨. 시간 초과 났었고 아래 코드는 카피
// https://m.blog.naver.com/PostView.nhn?blogId=kerochuu&logNo=221521083734&proxyReferer=http%3A%2F%2F172.16.59.112%3A8888%2F%3Fca_test%3Dhttps%253A%252F%252F172.16.59.112%253A8443%26target%3Dhttp%253A%252F%252Fm.blog.naver.com%252Fkerochuu%252F221521083734

public class BOJ_16234 {
	private static class Node {
		int x, y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int size, minNum, maxNum, nodeSum, result = 0;
	static int[][] map;
	static Queue<Node> q;
	static ArrayList<Node> saveNode;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visit;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		size = input.nextInt();
		minNum = input.nextInt();
		maxNum = input.nextInt();

		map = new int[size][size];
		visit = new boolean[size][size];
		q = new LinkedList<>();
		saveNode = new ArrayList<>();

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				map[i][j] = input.nextInt();
			}
		}
//		System.out.println("최초 map = ");
//		print();

		while (cycle()) {
			visit = new boolean[size][size]; // 사이클 한번 돌때마다 visit 초기화
			result++; // 한타임마다 result깂 하나씩 늘려줌
		}

		System.out.println(result);

	}

	private static boolean cycle() {
		boolean endCheck = false;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(visit[i][j]) continue; // 이미 체크한 노드면 스킵
				q.clear(); // 혹시 모를 q 초기화
				saveNode.clear(); // 혹시 모를 saveNode list 초기화
				q.add(new Node(i, j)); // 큐에 현재노드좌표 하나 넣고
				nodeSum = map[i][j]; // nodeSum에 현재노드값 넣어둠
				visit[i][j] = true;
				saveNode.add(new Node(i, j)); // 나중에 nodeSum값 뿌려줄 노드값들 저장할 배열
				if (!bfs()) { // 
					endCheck = true;
//					print();
				}
			}
		}
		return endCheck; 
		// 이중for문 돌면서 맵 전부 탐색할동안 endCheck가 false면 main()의 while문 종료됨
	}

	private static boolean bfs() {
		Node temp = null;
		boolean atOnce = true; 
		// bfs() 돌때 인근노드로 퍼져나가지 않고 바로 끝나면 bfs()를 true로 끝냄
		
		while (!q.isEmpty()) {
			temp = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];

				if (nx >= 0 && ny >= 0 && nx < size && ny < size) {
					if (!visit[nx][ny] 
							&& Math.abs(map[temp.x][temp.y] - map[nx][ny]) >= minNum
							&& Math.abs(map[temp.x][temp.y] - map[nx][ny]) <= maxNum) {
					// 방문안한 노드 && 문제에 주어진 "최소값 <= 다음노드값 <= 최대값" 만족할 때~ 
						
						atOnce = false; // 한번이라도 퍼지면 bfs()를 false로 끝냄
						visit[nx][ny] = true;
						saveNode.add(new Node(nx, ny));
						nodeSum += map[nx][ny];
						q.add(new Node(nx, ny));
					}
				}
			}
		}

		if (!atOnce) { // bfs()가 한번이라도 퍼졌었다? -> 값들을 뿌려줘야된다
			migration();
		}
		return atOnce;
	}

	private static void migration() {
		nodeSum /= saveNode.size(); 
		// nodeSum에는 "연합국"의 모든 인구수가 저장되어있다
		// saveNode에는 "연합국"의 모든 좌표가 저장되어있으므로... size만큼 나누면 각 국가의 인구수가 나온다
		for (Node temp : saveNode) { // "연합국"의 주소값들 전부 출력
			map[temp.x][temp.y] = nodeSum; // 맵에 평균값을 넣어준다
		}
	}
	
	private static void print() {
		for (int[] temp1 : map) {
			for (int temp2 : temp1) {
				System.out.format("%3d ", temp2);
			}
			System.out.println();
		}
		System.out.println("--------------------");
	}

}