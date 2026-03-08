package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 실행 시간 : 292ms
/** 스타트 팀과 링크 팀의 능력치의 차이의 최솟값
 * N(4 ≤ N ≤ 20, N은 짝수)
 * N/2명으로 이루어진 (스타트 팀|링크 팀)으로 사람들을 나눠야 한다.
 *
 * Sij는 i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치
 *
 * 팀의 능력치: 팀에 속한 모든 쌍의 능력치 Sij의 합
 *
 * 특이 사항
 * - Sij는 Sji와 다를 수도 있음.
 * - i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치는 Sij와 Sji
 * -------------------------------------------
 *
 * [접근법] N/2명을 뽑을 때 조합, 안 뽑은 팀은 링크 팀.
 * 스타트 팀에 뽑은 인원을 boolean 배열*로 체크한 후,
 * 기저에서 스타트 팀, 링크 팀으로 나누어 계산한다.
 *
 * 최소값*을 갱신한다.
 *
 * [근거]
 * 순서 상관없이 n/2 명을 뽑으면 되기 때문.
 * 조합은 최악의 경우 20C10이므로, 30C15(약 1.5억) 보단 작을 예정.
 *
 * **/
public class BOJ_14889 {
  static int N;
  static int[][] power;
  //
  static boolean[] selected;
  //
  static int min;
  //
  static StringTokenizer st;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    power = new int[N][N];

    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < N; j++){
        power[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    selected = new boolean[N];
    min = Integer.MAX_VALUE;

    //
    combination(0, 0);
    //
    System.out.println(min);
  }

  // cnt번 째에 스타트 팀이 될 사람을 뽑는다. 그리고 cnt+1번 째에 스타트 팀이 될 사람을 뽑는다.
  private static void combination(int cnt, int start){

    // 기저 부분
    if(cnt == N/2){

//      for(int  i = 0; i < N; i++){
//        System.out.println(i + ": " + selected[i]);
//      }
//      System.out.println("------------------------------");

      int startSum = 0;
      int[] steam = new int[N/2];
      int rinkSum = 0;
      int[] rteam = new int[N/2];

      // 스타트 팀과 링크 팀 구분
      int temp1 = 0;
      int temp2 = 0;
      for(int i = 0; i < N; i++){
        if(selected[i]) {
          steam[temp1] = i;
          temp1++;
        } else {
          rteam[temp2] = i;
          temp2++;
        }
      }

      // 스타트 팀의 능력치
        // 선택된 사람의 번호를 기준으로,
        // 또 다른 선택된 사람과의 능력치(2회) 다가가 더한다.
      /** 틀린 부분 검토에 AI 사용
       *
       * 내가 원했던 것:
       *  1 : 1 2 3 4 와의 시너지를 합한다.
       *  2 : 1 2 3 4 와의 시너지를 합한다.
       *  ...
       *
       *  실제로 내가 한 것(중복적으로 합했다.):
       *    1 : 1 2** 3 4와의 시너지를 합한다.
       *    그리고 1: 1,  2: 1, 3: 1, 4: 1 일 때도 시너지를 합한다.
       *    2 : 1 2 3 4와의 시너지를 합한다.
       *    그리고 1: 2**,  2: 2, 3: 2, 4: 2 일 때도 시너지를 합한다.
       *    ...
       *
       *    **: 중복합 발생 했었음 !!
       *
       * **/
      for(int i = 0; i < N/2; i++) {
        int p1 = steam[i];
        for (int j = 0; j < N / 2; j++) {
          int p2 = steam[j];
          startSum += power[p1][p2];
        }
      }

      // 링크 팀의 능력치
      for(int i = 0; i < N/2; i++) {
        int p1 = rteam[i];
        for (int j = 0; j < N / 2; j++) {
          int p2 = rteam[j];
          rinkSum += power[p1][p2];
        }
      }

      min = Math.min(min, Math.abs(startSum - rinkSum));

      return;
    }

    // 유도 부분
    for(int i = start; i < N; i++){
      if(selected[i]) continue;
      selected[i] = true;
      combination(cnt+1, i);
      selected[i] = false;
    }

  }
}
