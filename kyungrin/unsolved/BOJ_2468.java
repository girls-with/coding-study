package kyungrin.unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문해] 물에 잠기지 않는 안전한 영역의 최대 개수
 * N*N (2 이상 100) 각 원소는 해당 지점의 높이를 표시하는 자연수 (1이상 100 이하의 정수) 물에 잠기지 않는
 * 안전한 영역: 물에 잠기지 않는 지점들이 위, 아래, 오른쪽 혹은 왼쪽으로 인접
 * <p>
 * 특이사항: - 아무 지역도 물에 잠기지 않을 수도 있다. = 높이 미만의 비의 양도 셈한다. (안전영역의 개수: 1)
 * <p>
 * [접근법] (최소 높이+1)~(최대높이-1)를 비의 양이라 생각하고, 반복 + Flood Fill + 최대 개수 찾기
 * 1. 입력값 받기
 * - 한 변의 길이
 * - 맵 정보
 * - 최대/최소 높이 정보 받기 !!
 * 최소 높이 -1~0은 무조건 영역의 개수 1
 * 2. Flood Fill, N번 반복하기
 * - for i: min~max 만큼 반복하기 - i
 * 이하의 높이는 잠겼다. => -1로 변경
 * - BFS 를 통한 탐색.*델타 필요
 *- 영역의 개수 이므로 너비를 셈한다.
 * - i 기우량의 기준으로 최댓값 갱신.
 * 3. 출력
 * [근거] 시작점과 인접한 칸을 모두 찾는 Flood Fill. BFS로 구현
 **/
public class BOJ_2468 {

  static int N;
  static int[][] map;
  static int min, max; // (최소 높이-1)~최대높이
  //
  static boolean[][] visited;
  static int[] mr = {-1, 1, 0, 0};
  static int[] mc = {0, 0, -1, 1};
  //
  static int result;
  //
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    map = new int[N][N];
    min = Integer.MAX_VALUE;
    max = Integer.MIN_VALUE;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        min = Math.min(min, map[i][j]);
        max = Math.max(max, map[i][j]);
      }
    }
//    System.out.println(min);
//    System.out.println(max);
    result = 1;

    //
    for (int i = min; i < max; i++) { // for i: 현재 비의 양
      //
      visited = new boolean[N][N];
      for (int r = 0; r < N; r++) {
        for (int c = 0; c < N; c++) {
          if (map[r][c] <= i) {
            map[r][c] = -1;
          }
        }
      }
      //
      bfs();
      System.out.println(i + ": 현재 비의 양");
      System.out.println(result + ": 결과");

    }
    //
    System.out.println(result);
  }

  private static void bfs() {
    Queue<int[]> q = new ArrayDeque<>();

    int cnt = 0; // 현재 비오는 양의 안전 영역의 개수

    for (int r = 0; r < N; r++) {
      for (int c = 0; c < N; c++) {

        if (map[r][c] != -1 || !visited[r][c]) {
          cnt++;
          q.offer(new int[]{r, c}); // 첫 번째 칸 제공
          visited[r][c] = true;

          // 연결된 것: 인접한 것, -1이 아닌 것, 방문하지 않았던 것, 범위를 벗어나지 않는 것
          while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curr = cur[0];
            int curc = cur[1];

            for (int d = 0; d < 4; d++) {
              int nextr = curr + mr[d];
              int nextc = curc + mc[d];

              if (isNotInRange(nextr, nextc) || map[nextr][nextc] == -1 || visited[nextc][nextc]) {
                continue;
              }
              visited[nextr][nextc] = true;
              q.offer(new int[]{nextr, nextc});
            }
          }
        }

      }
    }

    result = Math.max(cnt, result);
  }

  private static boolean isNotInRange(int r, int c) {
    return r < 0 || r >= N || c < 0 || c >= N ? true : false;
  }
}
