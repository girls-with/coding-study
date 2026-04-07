import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  BFS(확산) - 32분
 *  메모리: 12,824KB
 *  실행 시간: 92ms
 */
public class Main_2636_치즈 {
	
	private static int R, C;
	private static int[][] grid;
	private static int totalCheese;  // 판에 있는 모든 치즈의 개수
	private static int remain;  // 모두 녹기 한 시간 전에 남아있던 치즈의 개수
	
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	
	private static boolean hasCheese(int sr, int sc) {  // 한 시간 뒤 치즈가 남아 있으면 true, 남아 있지 않으면 false 반환
		boolean visited[][] = new boolean[R][C];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		int meltingCheese = 0;  // 한 시간 동안 녹아 없어지는 치즈의 개수
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int nr = cur[0] + dr[dir];
				int nc = cur[1] + dc[dir];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;  // 판의 경계를 벗어난 경우
				if(visited[nr][nc]) continue;  // 이미 방문한 칸인 경우
				
				visited[nr][nc] = true;
				
				if(grid[nr][nc] == 1) {  // 치즈 덩어리 외벽의 치즈가 공기와 만난 경우
					grid[nr][nc] = 0;
					meltingCheese++;
					continue;
				}
				
				queue.add(new int[] {nr, nc});
			}
		}
		
		totalCheese -= meltingCheese;
		if(totalCheese == 0) {  // 모든 치즈가 녹은 경우
			remain = meltingCheese;  // 마지막에 녹은 치즈의 개수 저장
			return false;
		} else return true;  // 치즈가 남아있는 경우
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		grid = new int[R][C];
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				grid[r][c] = Integer.parseInt(st.nextToken());
				
				if(grid[r][c] == 1) totalCheese++;
			}
		}
		
		int time = 0;  // 모든 치즈가 녹는 데 걸리는 시간
		
		if(totalCheese != 0) {  // 판에 치즈가 존재하는 경우에만
			do {
				time++;
			} while(hasCheese(0, 0));  // 치즈가 모두 녹을 때까지 반복
		}
		
		sb.append(time).append("\n").append(remain);
		
		System.out.println(sb);
	}

}
