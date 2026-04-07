package kyungrin.unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/** 치즈가 모두 녹아 없어지는 데 걸리는 시간,  모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수
 * --------------------------------------------------
 *  공기와 접촉된 칸은 한 시간이 지나면 녹아 없어진다.
 *  처음 제시될 때 있는 구멍에는 공기가 없다.
 * --------------------------------------------------
 *
 * int size;
 * int t;
 *
 *
 *
 *1~N 초 마다 치즈 업데이트
 *  남아있는 치즈 ArrayList
 *
 *  BFS로 치즈 찾기
 *    - 사방으로 치즈(1) 찾기
 *         boolean 사방 중 하나가 0? = (0인지 알아보는 함수)
 *         IF 사방 중 하나가 0? : 녹아서 없어지는 치즈
 *         ELSE 녹는 치즈
 *
 *  size = 남아있는 치즈 개수
 *  남아있는 치즈가 없으면 -> 반복 종료
 *  맵 = 새맵 업데이트
 *  
 *  끝나면
 *  size 
 *  t 
 *  출력
 *
 *  0인지 알아보는 함수
 *    cnt = 0;
 *    위아래양옆 범위 벗어날 때까지 좌표 이동
 *    (해당 좌표의 위아래양옆에 전부 1이 이 있으면)
      cnt가 4면 0아님 false
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
      ArrayList<Integer> remainCheese = new ArrayList<>();

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
          if(map[nr][nc] == 0) continue;




        }

      }

//       *  맵
//          *  남아있는 치즈 ArrayList
// *        size = 남아있는 치즈 개수
// *
// *        BFS로 치즈 찾기
//          *    - 사방으로 치즈(1) 찾기
//          *         boolean 사방 중 하나가 0? = (0인지 알아보는 함수)
// *         IF 사방 중 하나가 0? : 녹아서 없어지는 치즈
//          *         ELSE 녹는 치즈
// *
// *        남아있는 치즈가 없으면 -> 반복 종료
//          *   맵 = 새맵 업데이트



      t++;
    }

  }

  private static boolean is0(int x, int y) {
    int cnt = 0;
    for(int d = 0; d < 4; d++){
      int cnt2 = 1;
      while(true) {
        int nx = x + mr[d]*cnt2;
        int ny = y + mc[d]*cnt2;

        if(nx < 0 || nx >= N || ny < 0 || ny >= M) break;
        if(map[nx][ny] == 1) {
          cnt++;
          break;
        }
        cnt2++;
      }
    }
    if(cnt == 4) return true;
    return false;
  }
}
