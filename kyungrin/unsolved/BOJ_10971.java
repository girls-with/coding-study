package kyungrin.unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
 * 기저(시작점에서 시작점으로 오다)에서 최소 비용*을 갱신한다.
 * **/
public class BOJ_10971 {
  static int N;
  static int[][] map; // i->j 와 j->i는 다름
  //
  static boolean[] visited; // 상태 저장
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
//    for 시작점 전달 4번
    for(int i = 0; i < N; i++) {
      visited[i] = true;
      dfs( i, N+1, 0);
      visited[i] = false;
    }
    //

    System.out.println(min);
  }

  private static void dfs(int depart, int pre, int total){

    boolean flag = true;
    for(boolean isVisited : visited){
      if(!isVisited) {
        flag = false;
        break;
      }
    }

    if(flag){
      System.out.println("----------");
      min = Math.min(min, total);
      return;
    }

    // 현재 위치에서 갈 노드를 탐색한다.
    for(int i = 0; i < 4; i++){
      if(map[depart][i] != 0 && !visited[i] && pre != i){
        System.out.println(depart + " -> " + i);
        visited[i] = true;
        dfs(i, depart, map[depart][i]+total);
        visited[i] = false;
      }
    }
  }
}
