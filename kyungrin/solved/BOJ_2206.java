package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 메모리 사용량: 82516 kb
// 실행 시간: 576 ms
/** 최단 경로
 * N×M의 행렬로 표현되는 맵 (1 ≤ N ≤ 1,000), (1 ≤ M ≤ 1,000)
 * 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳
 * 
 * (1, 1)에서 (N, M)의 위치까지 이동
 * 최단 경로로 이동(시작하는 칸과 끝나는 칸도 포함)
 * 
 * 벽을 한 개 까지 부수고 이동
 * 한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸
 * 
 * ---
 * BFS & 방문 여부 확인의 차원 분리
 * Idea : 벽을 한 번 부쉈을 때, 아닐 때의 경로를 분리한다.
 *
 * isBroken 변수를 가진 요소를 큐에 넣어서, 앞으로 벽을 부술 수 있는지 아닌지에 대한 여부를 판단하도록 한다.
 * visited[0/1][r][c] 를 통해서, 상태(뿌숨 여부/좌표)가 같은 경로의 조각들 중 가장 짧은 거리가 오도록 한다.
 * 
 * BFS의 철학 중 하나는, 먼저 도착했다면 가장 빠르게 도착한 것이므로 더 이상 볼 필요가 없다는 것임을 기억할 것.
 * 벽 부수기 BFS에서는 이 철학에 "망치 있음 여부"라는 상태가 추가되어 또한 분리해야함을 더한 것
 * r, c 또한 하나의 상태 값들이다.
 *
 * **/
public class BOJ_2206 {
	static int N, M;
	static int[][] map;
	//
	static boolean[][][] visited; // 벽부숨여부, 좌표에 대한 상태
	static int[] mr = {-1, 1, 0, 0};
	static int[] mc = {0, 0, -1, 1};
	 //
	static int result;
	//
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = temp.charAt(j) - '0';
			}
		}
		result = -1;

		//////////////////////////
		visited = new boolean[2][N][M]; // 벽부숨여부, 좌표
		// BFS
		Queue<int[]> q = new ArrayDeque<>(); // 큐에 들어가는 요소 {좌표, 거리, 벽부숨여부}
		q.offer(new int[]{0, 0, 1, 0}); // (0, 0)에서 거리 1로 시작했다. 벽을 부순 적 없다.(0)
		visited[0][0][0] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cr = cur[0];
			int cc = cur[1];
			int dist = cur[2];
			int isBroken = cur[3];

			if(cr == N-1 && cc == M-1) {
				System.out.println(dist); //////////////////////////
				return;
			}

			for(int d = 0; d < 4; d++) {
				int nextr = cr + mr[d];
				int nextc = cc + mc[d];

				if (nextr < 0 || nextr >= N || nextc < 0 || nextc >= M) continue;

				// 현재 가려는 좌표에 벽이 있을 때, 이 벽을 부술 수 있는지에 대한 여부를 확인한다.
				// 큐에 들어가는 요소는 좌표/거리/벽부숨여부 를 가진다.
				// 방문 체크는 각 상태가 동일할 때를 기준으로 갱신한다.

				// 1. 다음 칸이 빈 곳(0)인 경우
				if (map[nextr][nextc] == 0){
					// 망치 사용 여부 유지
					if(visited[isBroken][nextr][nextc]) continue;
					visited[isBroken][nextr][nextc] = true;
					q.offer(new int[] {nextr, nextc, dist + 1, isBroken});
				}
				// 2. 다음 칸이 벽(1)인 경우
				else if (map[nextr][nextc] == 1){
					// 아직 망치를 쓰지 않는 경우에만 망치를 쓴다. isBroken (0 -> 1)
					if(isBroken == 0 && !visited[1][nextr][nextc]){
						visited[1][nextr][nextc] = true;
						q.offer(new int[] {nextr, nextc, dist+1, 1});
					}
				}
			}
		}
		//////////////////////////
		System.out.println(-1);
	}
}
