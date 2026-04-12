/*
 *   시뮬레이션
 *   메모리: KB
 *   시간: ms
 *   
 *   AI 활용:
 *   	1. ArrayDeque(정렬 불가능), List 중 포탄 정보를 저장할 자료구조 선택 과정
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_C_포탑부수기 {
	
	private static class Node implements Comparable<Node>{
		int r;
		int c;
		int power; // 포탑의 공격력
		int time;  // 가장 마지막으로 공격한 시점
		
		Node(int r, int c, int power) {
			this.r = r;
			this.c = c;
			this.power = power;  
			this.time = 0;
		}

		@Override
		public int compareTo(Node o) {  // 공격력이 가장 낮은 포탑부터 오름차순 정렬
			if(this.r + this.c == o.r + o.c) return o.c - this.c;
			else if (this.time == o.time) return (o.r + o.c) - (this.r + this.c);
			else if(this.power == o.power) return o.time - this.time;
			return this.power - o.power;
		}
	}
	
	private static int N, M, K;
	private static int[][] map;
	private static List<Node> turret;  // 포탑 리스트
	private static int totalTurret;  // 포탑 개수
	private static boolean[][] broken;
	
	// 우 하 좌 상 우하 좌하 좌상 우상
	private static int[] dr = {0, 1, 0, -1, 1, 1, -1, -1};
	private static int[] dc = {1, 0, -1, 0, 1, -1, -1, 1};

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		turret = new LinkedList<>();
		broken = new boolean[N][M];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				if(map[r][c] == 0) broken[r][c] = true;  // 공격력이 0이면 부서진 포탑 (해당 위치는 지나갈 수 없음)
				else turret.add(new Node(r, c, map[r][c]));  // 아니면 포탑 리스트에 저장
			}
		}
		
		totalTurret = turret.size();
		
		for(int i = 0; i < K; i++) {
			
			turret.sort(null);  // 공격력 기준 오름차순 정렬
			
			if(raser(turret.get(i))) {}  // 레이저 공격 (BFS)
			else {}  // 포탄 공격
		}
	}
	
	private static boolean raser(Node start) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(start);
	}

}
