package kyungrin.unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Queue;

/**
 * 메모리 사용량: 14128 kb 실행 시간:  104 ms
 * <p>
 * [문풀] : (1) 연결된 집을 묶어 하나의 단지로 정의 (2) 단지 개수 - 단지내 집의 수를 오름차순 출력 정사각형 모양의 지도 (5≤N≤25) 1은 집이 있는 곳을,
 * 0은 집이 없는 곳
 * <p>
 * 연결된 집의 모임인 단지를 정의, 단지에 번호를 붙이려 한다. 연결 = 상하좌우 연결
 * ---------------------------------------------------------------------------- 접근법: Flood Fill
 * 알고리즘(BFS 이용)
 * <p>
 * 접근법의 근거: 특정 집을 기준으로, 인접한 모든 집을 탐색한다. => 시작 칸과 연결된 모든 칸을 탐색한다 - 라는 말과 동일하기 때문에 Flood Fill이다.
 * <p>
 * BFS, DFS로 모두 풀이 가능하지만, 속도 면에서 비교적 빠른 BFS로 풀이한다.
 **/
public class BOJ_2667 {

  static int N;
  static int[][] map;
  //
  static int[] mr = {-1, 1, 0, 0};
  static int[] mc = {0, 0, -1, 1};
  static boolean[][] visited; // 방문 결과
  //
  static int cnt;// 단지 개수 카운트
  static ArrayList<Integer> homes; // 각 단지 내 집의 수 -> 최적화 도모

  public static void main(String[] args) throws IOException {
    // 1. 입력값 받기 및 변수 초기화
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    map = new int[N][N];

    for (int i = 0; i < N; i++) {
      String temp = br.readLine();
      for (int j = 0; j < N; j++) {
        map[i][j] = temp.charAt(j) - '0';
      }
    }

    visited = new boolean[N][N];
    cnt = 0;
    homes = new ArrayList<>();

    StringBuilder sb = new StringBuilder();

    // --------------------------------------------------------

    // 2. 연결된 단지 찾기
    // 2-1. 시작점 설정
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        // 2-2. 시작점을 방문하지 않았을 때, 1일 때 탐색 시작
        if (visited[i][j]) continue;
        if (map[i][j] == 1) {
          cnt++; // !! 단지 수 카운트 !!

          int tempCnt = 1; // 한 단지 내 집의 수

          // 2-3. BFS 탐색 시작
          Queue<int[]> q = new ArrayDeque<>();
          q.offer(new int[]{i, j}); // 큐에 시작 좌표 넣기
          visited[i][j] = true; // 방문 처리

          while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curr = cur[0];
            int curc = cur[1];

            for (int d = 0; d < 4; d++) { // 상하좌우로 움직여 인접 칸을 탐색
              int nextr = curr + mr[d];
              int nextc = curc + mc[d];
              if (!isNotInRange(nextr, nextc) && map[nextr][nextc] == 1
                  && !visited[nextr][nextc]) { // 인접의 조건: 범위 안에 있으며, 1이며, 방문하지 않은.
                q.offer(new int[]{nextr, nextc}); // 큐에 인접 좌표 넣기
                visited[nextr][nextc] = true; // 방문 처리
                tempCnt++; // !!  단지 내 집의 수 카운트 !!
              }
            }
          }
          homes.add(tempCnt); // 시작점과 연결된 칸을 탐색 완료 하면, 단지 내 수 배열리스트에 추가.
        }
      }
    }

    homes.sort(Comparator.naturalOrder());
    sb.append(cnt).append("\n");
    for (int i = 0; i < homes.size(); i++) {
      sb.append(homes.get(i)).append("\n");
    }

    // 3. 출력
    System.out.println(sb);
  }

  private static boolean isNotInRange(int r, int c) {
    return r < 0 || r >= N || c < 0 || c >= N ? true : false;
  }

}
