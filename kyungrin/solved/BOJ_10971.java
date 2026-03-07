package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 실행 시간: 404ms
/** 최소 비용을 들이는 여행 계획
 * W[i][j]는 도시 i에서 도시 j로 가기 위한 비용
 *
 * 1번부터 N번까지 번호가 매겨져 있는 도시
 * 어느 한 도시에서 출발해 N개의 도시를 모두 거쳐 다시 원래의 도시로 돌아옴
 *
 * - 단, 한 번 갔던 도시로는 다시 갈 수 없다.
 *   - 맨 마지막은 예외
 *
 * - W[i][j] 는 W[j][i]와 다를 수 있다.
 * - 모든 도시간의 비용은 양의 정수
 * - W[i][i]는 항상 0
 * - 도시 i에서 도시 j로 갈 수 없는 경우도 0
 *
 *----------------------------
 * [접근법] 유방향 그래프와 DFS
 * [근거]
 * 도시를 노드, 갈 수 있는 경우를 방향이 있는 간선으로 표현한다.
 *
 * N개의 시작점을 기준으로
 *  - 갈 수 있는 모든 경로를 탐색한다. DFS
 *    - 갈 수 있는 모든 경로를 탐색한다는 말은,
 *    - 각 노드에서 접근할 수 있는 노드로 전부 깊게 접근해본다는 뜻
 *
 * 상태를 다루는 visited 배열*이 필요하다.
 * dfs에서 매개변수로 현재 경로의 비용을 다룬다.
 * 기저(모든 노드에 방문 했다)에서 최소 비용*을 갱신한다.
 *
 * 마지막 예외에는 기저에서 처리한다.
 * 예외를 위해 시작점의 전역 변수*를 사용한다.
 * **/
public class BOJ_10971 {
  static int N;
  static int[][] map; // i->j 와 j->i는 다름
  //
  static int start; // 시작점의 전역 변수
  static boolean[] visited; // 경로의 수마다 노드 방문 상태 저장
  //
  static int min;
  //
  static StringTokenizer st;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    map = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    visited = new boolean[N];
    min = Integer.MAX_VALUE;

    //
    for(int i = 0; i < N; i++) {
      visited[i] = true;
      start = i;
      dfs( i, 0);
      visited[i] = false;
    }
    //

    System.out.println(min);
  }

  // 해당 dfs의 기능: 현재 depart 노드에서 도착할 노드(=다음 노드)를 선정한다. 그리고 다음 노드에서 도착할 노드를 선정하도록 재귀한다.
  // 모든 노드를 방문했다면, 마지막 예외 처리(끝->시작점) 비용을 합하고 최소 이동 비용을 갱신한다.
  private static void dfs(int depart, int total){ // 현재 노드의 번호, 현재까지의 이동 비용 합

    boolean flag = true;
    for(boolean isVisited : visited){
      if(!isVisited) {
        flag = false;
        break;
      }
    }

    if(flag){
      if (map[depart][start] == 0) return;
      total += map[depart][start];
      min = Math.min(min, total);
      return;
    }

    // 현재 위치에서 갈 노드를 탐색한다.
    for(int i = 0; i < N; i++){
      if(map[depart][i] != 0 && !visited[i]){
        visited[i] = true;
        dfs(i, map[depart][i]+total);
        visited[i] = false;
      }
    }
  }
}
