import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *   DFS(조합) + BFS(확산) (50분)
 *   메모리: 77,652 KB
 *   시간: 244 ms
 */
public class Main_14502_연구소 {
	
	static int N, M;
	static int[][] map;
	static List<int[]> virus;
	static int safeArea;
	static int maxSafeArea;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		virus = new ArrayList<>();
		safeArea = 0;
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				if(map[r][c] == 2) virus.add(new int[] {r, c});
				else if(map[r][c] == 0) safeArea++;
			}
		}
		
		safeArea -= 3;
		maxSafeArea = 0;
		comb(0, 0, 0);
		
		System.out.println(maxSafeArea);
	}
	
	private static void comb(int idx, int sr, int sc) {  // 새로 세울 3개의 벽 위치 선택 (64C3)
		if(idx == 3) {
			bfs();
			return;
		}
		
		for(int r = sr; r < N; r++) {
			sc = r == sr ? sc : 0;
			for(int c = sc; c < M; c++) {
				if(map[r][c] != 0) continue;
				
				map[r][c] = 1;
				int nr = c == M-1 ? r+1 : r;
				int nc = nr == r+1 ? 0 : c+1;
				comb(idx+1, nr, nc);
				map[r][c] = 0;
			}
		}
	}

	private static void bfs() {  // 바이러스 확산 -> 안전 영역 = 최초의 안전 영역 - 바이러스가 확산된 영역
		
		Queue<int[]> queue = new ArrayDeque<>();
		for(int i = 0; i < virus.size(); i++) {
			queue.add(new int[] {virus.get(i)[0], virus.get(i)[1]});
		}
		
		boolean[][] visited = new boolean[N][M];
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
						
			visited[cur[0]][cur[1]] = true;
			
			if(safeArea - maxSafeArea < cnt) return;
			
			for(int dir = 0; dir < 4; dir++) {
				int nr = cur[0] + dr[dir];
				int nc = cur[1] + dc[dir];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(map[nr][nc] != 0) continue;
				if(visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				queue.add(new int[] {nr, nc});
				cnt++;
			}
		}
		
		maxSafeArea = Math.max(maxSafeArea, safeArea-cnt);
		
	}
}
