package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_25679 {
  static int N;
  static int[][] map;
  //
  static int[] mr = {-1, 1, 0, 0};
  static int[] mc = {0, 0, -1, 1};
  static int[] po;
  static boolean[][] catchEgg;
  //
  static int eggCnt;
  //
  static StringTokenizer st;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    for(int tc = 1; tc < T+1; tc++) {
      // 1. 입력값 받기
      N = Integer.parseInt(br.readLine());
      map = new int[N][N];
      catchEgg = new boolean[N][N];
      for(int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for(int j = 0; j < N; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
          if(map[i][j] == 2) {
            po = new int[] {i, j};
//        				map[i][j] = 0; // 1일 때 밖에 체크안하지만 우선 혹시 모르니 제거
          }
        }
      }

      // 2. 3회까지 포를 움직였을 때 먹을 수 있는 알의 개수 세기
      eggCnt = 0;
      getCnt(0, po);

      // 3. 출력하기
      sb.append("#").append(tc).append(" ").append(eggCnt).append("\n");
    }
    // 3. 출력하기
    System.out.println(sb);
  }

  // 매개변수 : 포가 움직인 횟수, 현재 포의 위치
  private static void getCnt(int cnt, int[] nowPo) {
    // 기저
    if(cnt == 3) return;

    // 유도
    for(int d = 0; d < 4; d++) {
      int pr = nowPo[0];
      int pc = nowPo[1];

      int findEggCnt = 0; // 해당 방향에서 발견한 알의 개수
      while(true) {
        pr += mr[d];
        pc += mc[d];

        if(pr < 0 || pr >= N || pc < 0 || pc >= N) break;
        // 1. 현재까지 발견한 알의 수가 1일 때 : 포가 이동할 수 있는 자리
        if(findEggCnt == 1) {
          getCnt(cnt + 1, new int[] {pr, pc});
        }
        // 첫 번쨰 알의 위치에서 재귀하면 X
        if(map[pr][pc] == 1) findEggCnt++;

        // 2. 현재까지 발견한 알의 수가 2일 때 : 포가 이동하고, 알을 먹고, 해당 겸로 탐색을 멈춰야 하는 자리
        if(findEggCnt == 2) {
          if(!catchEgg[pr][pc]) {
            eggCnt++;
            catchEgg[pr][pc] = true;
          }
          map[pr][pc] = 0;
          getCnt(cnt + 1, new int[] {pr, pc});
          map[pr][pc] = 1;
          break;
        }
      }
    }
  }
}
