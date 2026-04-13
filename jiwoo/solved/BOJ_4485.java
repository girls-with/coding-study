import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 *   서로 다른 가중치, 최솟값 구하기 -> 다익스트라 (25분)
 *   메모리: 22,304KB
 *   시간: 268ms
 */
public class Main_B_4485_녹색옷입은애가젤다지 {
	
	static class Node {
		int r;
		int c;
		int rupee;
		
		Node(int r, int c, int rupee) {
			this.r = r;
			this.c = c;
			this.rupee = rupee;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		int tc = 1;
		int N = Integer.parseInt(br.readLine());
		while(N != 0) {
			
			int[][] map = new int[N][N];
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] minRupee = new int[N][N];
			for(int r = 0; r < N; r++) Arrays.fill(minRupee[r], Integer.MAX_VALUE);
			minRupee[0][0] = map[0][0];
			
			boolean[][] visited = new boolean[N][N];
			visited[0][0] = true;
			
			PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
				return a.rupee - b.rupee;
			});
			pq.add(new Node(0, 0, map[0][0]));
			
			while(!pq.isEmpty()) {
				Node cur = pq.poll();
				
				if(cur.r == N-1 && cur.c == N-1) break;
				
				for(int dir = 0; dir < 4; dir++) {
					int nr = cur.r + dr[dir];
					int nc = cur.c + dc[dir];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					if(visited[nr][nc]) continue;
					if(minRupee[nr][nc] <= minRupee[cur.r][cur.c] + map[nr][nc]) continue;
					
					minRupee[nr][nc] = cur.rupee + map[nr][nc];
					pq.add(new Node(nr, nc, minRupee[nr][nc]));
					visited[nr][nc] = true;
				}
			}
			
			sb.append("Problem ").append(tc++).append(": ").append(minRupee[N-1][N-1]).append("\n");
		
			N = Integer.parseInt(br.readLine());
		}
		System.out.println(sb);
	}

}
