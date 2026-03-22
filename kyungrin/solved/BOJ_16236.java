package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// AI 사용해서 학습 - 풀이!! 기억할 수 있도록 다시 한 번 해봐야할 것 같습니다.
// 메모리 사용량 : 14580 KB
// 실행 시간 : 108 ms
/** 아기 상어가 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지
 *
 * N×N 크기 (2 ≤ N ≤ 20)
 * 물고기 M마리, 아기 상어 1마리
 * 한 칸에는 물고기가 최대 1마리 존재
 *
 * 처음에 아기 상어의 크기는 2
 * 아기 상어는 1초에 상하좌우로 인접한 한 칸
 *
 * 아기 상어 규칙
 * 크기
 * - 자기 보다 큼 -> 지나갈 수 없고, 먹을 수 없음
 * - 자기랑 같음 -> 지나갈 순 있는데 먹을 수 없음
 * - 자기보다 작음 -> 지나갈 수 있고 먹을 수 있음 (이동과 동시에 먹음)
 * 이동 (자기보다 작은 거 기준)
 * - 거리가 가까운 물고기가 많다면,
 * 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
 *
 *
 * 물고기를 먹으면, 그 칸은 빈 칸
 *
 * 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가
 *-----------------------
 * [접근법] 이동한 위치에서 BFS + 각 너비마다 물고기 후보 보관
 * [근거]
 * 1. "가장 가까운 물고기" = "최단 거리"
 * 2. 격자 이동 => 그래프 => 1칸 이동은 1초 = 가중치 없음
 * **/
public class BOJ_16236 {
  static int N;
  static int[][] map;
  //
  static int[] mr = {-1, 1, 0, 0};
  static int[] mc = {0, 0, -1, 1};
  static int size;
  static int[] start; // 아기 상어의 위치를 담는 Queue
  //
  static int result;
  //
  static StringTokenizer st;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    map = new int[N][N];
    start = new int[2];
    for(int  i =0; i < N ; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if (map[i][j] == 9) {
          start[0] = i;
          start[1] = j;
          map[i][j] = 0; // 맵에서 정리
        }
      }
    }
    size = 2;
    result = 0;
    //
    int cnt = 0; // 물고기를 먹은 횟수
    while(true){ // 1. 현재 위치에서 BFS 시작

      Queue<int[]> q = new ArrayDeque<>();
      q.offer(start);
      boolean[][] visited = new boolean[N][N];
      visited[start[0]][start[1]] = true;

      ArrayList<int[]> fishes = new ArrayList<>();

      int dist = 0; // (시작 위치)레벨 1의 거리
      while (!q.isEmpty()) { // 2. BFS를 통한 탐색
        int qsize = q.size();

        dist++;

        for (int i = 0; i < qsize; i++) {
          int[] cur = q.poll();
          int curr = cur[0];
          int curc = cur[1];

          for (int d = 0; d < 4; d++) {
            int nextr = curr + mr[d];
            int nextc = curc + mc[d];

            if (nextr < 0 || nextr >= N || nextc < 0 || nextc >= N)
              continue;
            if (visited[nextr][nextc])
              continue;
            if (map[nextr][nextc] > size)
              continue;

            if (map[nextr][nextc] < size && map[nextr][nextc] != 0)
              fishes.add(new int[]{nextr, nextc});

            q.offer(new int[]{nextr, nextc});
            visited[nextr][nextc] = true;
          }
        }

        // 현재 거리에서 물고기를 발견했다
        if (!fishes.isEmpty()) {
          result += dist;
          break;
        }
      }

      if(fishes.isEmpty()) break; // (종료조건) 현재 위치에서 시작하여 먹을 수 있는 물고기가 없었을 때

      int eatr = 21;
      int eatc = 21;
      for(int[] fish : fishes){ // 물고기의 위치를 비교하여, "상" -> "좌"에 있는 물고기 선택
        if(eatr > fish[0]) {
          eatr = fish[0];
          eatc = fish[1];
        } else if (eatr == fish[0]){
          if(eatc > fish[1]){
            eatr = fish[0];
            eatc = fish[1];
          }
        }
      }

      // 물고기를 먹는다.
        // 위치 갱신
        // 맵 갱신
        // 먹은 횟수 갱신
        // 크기 갱신
      start[0] = eatr;
      start[1] = eatc;
      map[eatr][eatc] = 0;
      cnt++;
      if(cnt == size) {
        size++;
        cnt = 0;
      }
    }
    //
    System.out.println(result);
  }
}
