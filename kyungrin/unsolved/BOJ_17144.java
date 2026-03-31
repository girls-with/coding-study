import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/** T초가 지난 후 구사과의 방에 남아있는 미세먼지의 양
 * R×C인 격자판
 * - 공기청정기는 항상 1번 열에 설치 => 크기는 두 행
 * - 공기청정기가 설치되어 있지 않은 칸에는 미세먼지 => 미세먼지의 양은 Ar,c
 * 
 * 1초 동안
 * 1. 미세먼지가 확산된다.
 * - 모든 칸에서 동시
 * - 인접한 네 방향으로 확산
 * 	- 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산 X
 * 	- 확산되는 양은 Ar,c/5이고 소수점은 버린다.
 * 		=> (r, c)에 남은 미세먼지의 양은 Ar,c - ⌊Ar,c/5⌋ × (확산된 방향의 개수) 
 * 2. 공기청정기가 작동한다.
 * - 공기청정기에서는 바람이 나온다.
 *	- 위쪽 공기청정기의 바람은 반시계방향으로 순환
 *	- 아래쪽 공기청정기의 바람은 시계방향으로 순환
 *	- 바람 -> 바람의 방향대로 모두 한 칸씩 이동
 *	- 공기청정기로 들어간 미세먼지는 모두 정화된다.
 * ----
 * 1. 확산 -> 시뮬레이션?이라 하는 게 맞나
 * 	미세먼지가 존재하는 모든 칸 (Q. 사전에 미세먼지가 없는 칸을 거를 것이냐, 아니면 Q에 전부 넣고 확산할 때 나누는 값으로 할 것이냐.)
 * 	사방으로 1회 퍼짐(연속 퍼짐X)
 * 	<계산> 동시에 퍼지는 것이기 때문에 => r, c 칸 보존을 위해서 (e.g. 1칸 완료 -> 2칸 완료 할 시 r, c 기준 데이터가 오염될 수 있음) new map1
 * 		- r, c = r, c 칸에 있는 먼지량 - r, c 칸에 있는 먼지량/5
 * 		- 사방의 칸 -> 사방의 칸 += r, c 칸에 있는 먼지량/5
 * 2. 작동 -> 시뮬레이션
 * 	new map1 기준 -> new map2 생성
 * 	(공청기 1 좌표)의 1. (row, 1~C-1) => 오른쪽으로 밀기 & 끝은 위로 올리기 | 2. (row ~(--)~ 0, C-1) => 위로 밀기 끝은 왼쪽으로 | 3. (row:0, C-1 ~ 0) 왼쪽으로 밀기 & 끝은 아래 | 4. (0~공청기row-1, col:0) 
 * 	(공청기 2 좌표)의 1. (row, 1~C-1) => 오른쪽으로 밀기 & 끝은 아래로 내리기 | ... 유사 ...
 * ----
 * **/
public class BOJ_17144 {
	static int R, C, T;
	static int[][] map;
	//
	static int[] mr = {-1, 1, 0, 0};
	static int[] mc = {0, 0, -1, 1};
	static int[][] airconditional;
	static int total;
	//
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		airconditional = new int[2][2];
		Queue<int[]> dusts = new ArrayDeque<>();
		int idx = 0;
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) airconditional[idx++] = new int[] {i, j};
				if(map[i][j] > 0) dusts.offer(new int[] {i, j});
			}
		}
		total = 0;
		//
		while(T > 0) {
			
			int[][] temp1 = new int[R][C];
			// 1. 미세먼지 확산
			while(!dusts.isEmpty()) {
				int[] dust = dusts.poll();
				
				int r = dust[0];
				int c = dust[1];
				
				int dustv = map[r][c];
				int v = 0;
				for(int d = 0; d < 4; d++) {
					int nextr = r + mr[d];
					int nextc = c + mr[d];
					
					if(nextr >= 0 || nextr < R || nextc >= 0 || nextc < C) continue;
					
					temp1[nextr][nextc] += dustv/5; // 몫
					v += dustv/5;
				}
				temp1[r][c] = dustv/5;
			}
			
			// 2. 작동
			int[][] temp2 = new int[R][C];
//			 * 	(공청기 1 좌표)의 1. (row, 1~C-1) => 오른쪽으로 밀기 & 끝은 위로 올리기 | 2. (row ~(--)~ 0, C-1) => 위로 밀기 끝은 왼쪽으로 | 3. (row:0, C-1 ~ 0) 왼쪽으로 밀기 & 끝은 아래 | 4. (0~공청기row-1, col:0) 
//			 * 	(공청기 2 좌표)의 1. (row, 1~C-1) => 오른쪽으로 밀기 & 끝은 아래로 내리기 | ... 유사 ...
			// 공청1
			int[] a1 = airconditional[0];
			for(int j = 1; j < C-1; j++) {
				temp2[a1[0]][j] = temp1[a1[0]][j-1]; 
			}
			temp2[a1[0]-1][C-1] = temp1[a1[0]][C-1];
			///// 우선 여기까지 40분 /////
			
			// 공청2
			int[] a2 = airconditional[1];
			
			
			T--;
		}
	}

}
