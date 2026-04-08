package kyungrin.solved;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// 152 ms
/** 치즈가 모두 녹아 없어지는 데 걸리는 시간,  모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수
 * --------------------------------------------------
 *  공기와 접촉된 칸은 한 시간이 지나면 녹아 없어진다.
 *  처음 제시될 때 있는 구멍에는 공기가 없다.
 * --------------------------------------------------
 *
 * 아이디어 AI 사용 :
 * !! 치즈 중심이 아닌 공기 중심으로하면 쉽다.!!
 * **/
public class BOJ_2636 {
  static int N, M;
  static int[][] map;
  //
  static int[] mr = {-1, 1, 0, 0};
  static int[] mc = {0, 0, -1, 1};
  //
  static int size;
  static int t;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new int[N][M];
    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    //
    while(true){
    	ArrayList<int[]> melt = new ArrayList<>();
    	int current = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
              if(map[i][j]==1) current++;
            }
        }
        if(current == 0) break;
        size = current; // 저장해두기

        Queue<int[]> q = new ArrayDeque();
        boolean[][] visited = new boolean[N][M];
        q.offer(new int[] {0, 0});
        visited[0][0] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int d = 0; d < 4; d++){
              int nr = cur[0] + mr[d];
              int nc = cur[1] + mc[d];

              if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
              if(visited[nr][nc]) continue;
              if(map[nr][nc] == 1) {
            	  melt.add(new int[] {nr, nc});
            	  continue;
              }
              q.offer(new int[] {nr, nc});
              visited[nr][nc] = true;                  
            }
        }

        t++;
      	for(int[] cheese : melt) {
      		map[cheese[0]][cheese[1]] = 0;
      	}
    }
    System.out.println(t + "\n" + size);
  }
}