package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 메모리 사용량: 22016 kb
// 시간 : 312 ms
/**
 * 총 N명 (5 ≤ N ≤ 2000)
 * 0번부터 N-1번 (0 ≤ a, b ≤ N-1, a ≠ b)
 * M (1 ≤ M ≤ 2000)
 *
 * 다음과 같은 친구 관계를 가진 사람 A, B, C, D, E가 존재하는지 구해보려고 한다.
 *
 * 친구 관계가 두 번 이상 주어지는 경우는 없다.
 *
 * ---
 * 아마도... 이어지는 5개의 노드가 있는지 확인하라는 문제같음
 * [접근법] 무방향 그래프 +  모든 노드를 시작점으로 하여 DFS 하면서 cnt해서 5개 되면 1출력
 * [근거]
 * 1. 친구 관계는 무방향 관계
 *  => 밀집 보단 희소가 될 가능성이 높아서... 기본적으로 간선의 개수가 작다.
 *  => 행렬 말고 노드 리스트로 저장하기
 *
 * 2. "이어짐"을 확인해야 해서 직관적으로 DFS,
 *      => 그러나 BFS로도 가능할듯. (너비의 개수를 세는 방향으로)
 * **/
public class BOJ_13023 {
  static int N, M;
  static ArrayList<Integer>[] adjList;
  //
  static boolean[] visited;
  static boolean flag;
   //
  static StringTokenizer st;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    adjList = new ArrayList[N];
    visited = new boolean[N];
    for(int i = 0; i < N; i++){
      adjList[i] = new ArrayList<>();
    }
    for(int i = 0; i < M; i++){
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      adjList[from].add(to);
      adjList[to].add(from);
    }
    //
    flag = false;
    for(int i = 0; i < N; i++) {
      dfs(i, 0);
    }
    if (flag) {
      System.out.println(1);
    } else {
      System.out.println(0);
    }
    //
  }
  private static void dfs(int cur, int cnt){ // 현재 노드, 거쳐온 노드의 개수
    if(cnt == 5){
      flag = true;
      return;
    }

    for(int next: adjList[cur]){
      if(visited[next]) continue;
      visited[next] = true;
      dfs(next, cnt + 1);
      visited[next] = false;

      if(flag) return;
    }
  }
}
