/*
 *   시뮬레이션 (60분 + @)
 *   메모리: 18MB
 *   시간: 331ms
 *   
 *   AI 활용:
 *   	1. ArrayDeque(정렬 불가능), List 중 포탄 정보를 저장할 자료구조 선택 과정
 *   	2. List 복사 방법
 *   	3. 공격자가 피해 받는 경우는 list에 저장하지 않는 코드 추가
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
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
		List<int[]> path;
		
		Node(int r, int c, int power) {
			this.r = r;
			this.c = c;
			this.power = power;  
			this.time = 0;
			this.path = new ArrayList<>();
		}

		@Override
		public int compareTo(Node o) {  // 공격력이 가장 낮은 포탑부터 오름차순 정렬
			if(this.power == o.power && this.time == o.time && this.r + this.c == o.r + o.c) return o.c - this.c;
			else if (this.power == o.power && this.time == o.time) return (o.r + o.c) - (this.r + this.c);
			else if(this.power == o.power) return o.time - this.time;
			return this.power - o.power;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", power=" + power + ", time=" + time + "]";
		}
	}
	
	private static int N, M, K;
	private static int[][] map;
	private static List<Node> turret;  // 포탑 리스트
	private static int totalTurret;  // 포탑 개수
	private static List<int[]> list;  // 공격에 영향을 받는 포탑 리스트
	
	// 우 하 좌 상 우하 좌하 좌상 우상
	private static int[] dr = {0, 1, 0, -1, 1, 1, -1, -1};
	private static int[] dc = {1, 0, -1, 0, 1, -1, -1, 1};

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		turret = new LinkedList<>();
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				if(map[r][c] == 0) continue;  // 공격력이 0이면 부서진 포탑 (해당 위치는 지나갈 수 없음)
				
				turret.add(new Node(r, c, map[r][c]));  // 아니면 포탑 리스트에 저장
			}
		}
		
		totalTurret = turret.size();
		
		for(int i = 1; i <= K; i++) {
			
			turret.sort(null);  // 공격력 기준 오름차순 정렬
			
			turret.get(0).power += N + M;
			turret.get(0).time = i;
			
			if(laser(turret.get(0))) {  // 레이저 공격 (BFS)
				attack();
				maintain();
			} else {  // 포탄 공격 (8방 탐색)
				list = new ArrayList<>();
				for(int dir = 0; dir < 8; dir++) {
					int nr = (turret.get(totalTurret-1).r + dr[dir] + N) % N;
					int nc = (turret.get(totalTurret-1).c + dc[dir] + M) % M;
					
					if(map[nr][nc] == 0) continue;
					if(nr == turret.get(0).r && nc == turret.get(0).c) continue;
					
					list.add(new int[] {nr, nc});
				}
				list.add(new int[] {turret.get(totalTurret-1).r, turret.get(totalTurret-1).c});
				
				attack();
				maintain();
			}
			
			for(int idx = totalTurret - 1; idx >= 0; idx--) {
				
				if(turret.get(idx).power > 0) map[turret.get(idx).r][turret.get(idx).c]= turret.get(idx).power; 
				else {
					map[turret.get(idx).r][turret.get(idx).c]= 0; 
					turret.remove(idx);
				}
				
			}
			
			totalTurret = turret.size();

			if(totalTurret == 1) break;
		}
		
		turret.sort(null);
		
		System.out.println(turret.get(totalTurret-1).power);
	}
	
	private static boolean laser(Node start) {  // 레이저 공격 가능 여부 판단
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(start);
		
		boolean[][] visited = new boolean[N][M];
		visited[start.r][start.c]= true; 
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			if(cur.r == turret.get(totalTurret-1).r && cur.c == turret.get(totalTurret-1).c) {
				list = cur.path;
				return true;
			}
			
			for(int dir = 0; dir < 4; dir++) {
				int nr = (cur.r + dr[dir] + N) % N;
				int nc = (cur.c + dc[dir] + M) % M;
				
				if(map[nr][nc] == 0) continue;
				if(visited[nr][nc]) continue;
				
				List<int[]> newPath = new ArrayList<>(cur.path);
				newPath.add(new int[] {nr, nc});
				Node nextNode = new Node(nr, nc, map[nr][nc]);
				nextNode.path = newPath;
				queue.add(nextNode);
				visited[nr][nc] = true;
				
			}
		}
		
		return false;
	}
	
	private static void attack() {  // 포탑 공격
		
		int power = turret.get(0).power;
		int halfPower = power / 2;
		
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < totalTurret; j++) {
				
				if(turret.get(j).r == list.get(i)[0] && turret.get(j).c == list.get(i)[1]) {
					if(i == list.size()-1) turret.get(j).power -= power;
					else turret.get(j).power -= halfPower;
				}
				
			}
		}
		
	}
	
	private static void maintain() {  // 공격과 무관한 포탑의 공격력 +1
		
		A: for(int i = 1; i < totalTurret; i++) {
			for(int j = 0; j < list.size(); j++) {
				if(turret.get(i).r == list.get(j)[0] && turret.get(i).c == list.get(j)[1]) continue A;
			}
			turret.get(i).power++;
		}
		
	}

}