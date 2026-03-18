package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 메모리 사용량: 17372 kb
// 실행 시간: 148 ms
/** 가장 짧은 다리 하나를 놓아 두 대륙을 연결할 때, 가장 짧은 다리 길이
 * 
 * N×N(100이하의 자연수)
 * 0은 바다, 1은 육지
 * 
 * 한 섬과 다른 섬을 잇는 다리 하나만. 다리를 가장 짧게
 * 
 * 여러 섬: 섬이란 동서남북으로 육지가 붙어있는 덩어
 * 짧은 다리: 격자에서 차지하는 칸의 수가 가장 작은
 * 
 * --------------------------------
 * [접근법] Flood Fill + BFS
 * 
 * 1. Flood Fill을 통해, map에 섬의 번호를 표시한다.
 * 	- cnt 를 외부에 두고, 하나씩 늘려가며 표시하는 방식.
 * 	- 만약에 탐색 중인 위치가 경계 판정되면 -> 큐*에 넣어두기
 * 									- 좌표, 섬 번호
 * 2. BFS를 시작
 * 	거리의 길이*를 표시 하는 2차원 배열
 * 	- 지나간 경로마다 현재 섬 번호 표시
 * 	- 인접 좌표가 이미 타 섬으로 지정되어 있다면 충돌 판정 => 각 거리 배열에 저장된 값 저장(현재 + 인접 좌표의)	
 * 
 * **/

public class BOJ_2146 {
	  static int N;
	  static int[][] map;
	  //
	  static Queue<int[]> bridge;
	  static int[][] dist;
	  static int[] mr = {-1, 1, 0, 0};
	  static int[] mc = {0, 0, -1, 1};
	  //
	  static int minLen;
	  //
	  static StringTokenizer st;
	  public static void main(String[] args) throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    N = Integer.parseInt(br.readLine());
	    map = new int[N][N];
	    dist = new int[N][N];
	    for(int i = 0; i < N; i++) {
	      st = new StringTokenizer(br.readLine());
	      for(int j = 0; j < N; j++) {
	        map[i][j] = Integer.parseInt(st.nextToken());
	      }
	    }
	    
	    bridge = new ArrayDeque<>();
	    minLen = Integer.MAX_VALUE;
	    //
	    // 1. 섬 찾기 Flood Fill(BFS)
	    int num = 1;
	    boolean[][] visited = new boolean[N][N];
	    
	    for(int i = 0; i < N; i++) {
	    	for(int j = 0; j < N; j++) {
	    		
	    		if(map[i][j] == 0) continue;
	    		if(visited[i][j]) continue;
	    		
	    		// 시작점 넘기기
	    		Queue<int[]> q = new ArrayDeque<>();
	    		q.offer(new int[] {i, j});
	    		visited[i][j] = true;
	    		
	    		while(!q.isEmpty()){
	    			int[] cur = q.poll();
	    			int r = cur[0];
	    			int c = cur[1];
	    			map[r][c] = num;
	    			
	    			boolean isBoundary = false;
	    			
	    			for(int d = 0; d < 4; d++) {
	    				int nextr = r + mr[d];
	    				int nextc = c + mc[d];
	    				
	    				if(nextr < 0 || nextr >= N || nextc < 0 || nextc >= N) continue;
	    				if(map[nextr][nextc] == 0) {
	    					isBoundary = true;
	    					continue;
	    				}
	    				if(visited[nextr][nextc]) continue;
	    				visited[nextr][nextc] = true;
	    				
	    				q.offer(new int[] {nextr, nextc});
	    			}
	    			
	    			if(isBoundary) {
	    				bridge.add(new int[] {r, c, num}); // 현재 좌표는 경계이다.
	    			}
	    		}
	    		num++; // 섬 번호 증가
	    	}
	    }
	   
	    // 2. 최소 다리 찾기 (BFS), 큐에 넣는 시점에 dist와 map에 표시
	    	// -> 너비 0 때 큐 안에 존재하는 값들은 전부 "땅"이기 때문에
	    // dist: 현재 좌표에 오기 까지의 길이 기록
	    // map: 충돌 여부를 확인하기 위해 다리를 기록
	    while(!bridge.isEmpty()) {
	    	int[] cur = bridge.poll();
	    	int r = cur[0];
	    	int c = cur[1];
	    	int n = cur[2];
	    	
			for(int d = 0; d < 4; d++) {
				int nextr = r + mr[d];
				int nextc = c + mc[d];
				
				if(nextr < 0 || nextr >= N || nextc < 0 || nextc >= N) continue;
				// 1. 현재 가려는 좌표가 "나의 섬"이라면 아무 동작도 하지 않는다.
				if(map[nextr][nextc] == n) continue;
				// 2. 현재 가려는 좌표가 "다른 섬"이라면 충돌한다.
				if(map[nextr][nextc] != 0) {
					minLen = Math.min(minLen, dist[nextr][nextc] + dist[r][c]); // 2-1. 최소 다리 갱신
					continue;
				}
				// 3. 현재 가려는 좌표가 "바다"면, 다음 좌표의 길이를 현재 좌표 길이 + 1한다.
				dist[nextr][nextc] = dist[r][c] + 1;
				map[nextr][nextc] = n;
				bridge.offer(new int[] {nextr, nextc, n});
			}
	    }
	    
	    // 3. 출력하기
	    System.out.println(minLen);
	}
}