package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문해] 자신의 키가 몇 번째인지 알 수 있는 학생들이 모두 몇 명
 * 1번부터 N번까지 번호
 *
 * 체크사항:
 * - N명의 학생들의 키는 모두 다르다.
 * ----------
 * [접근법] 입력값 그래프로 받기 & BFS 탐색
 * 학생들을 노드로 표현하고, 간선은 키 비교 유무, 방향은 대소를 의미한다.*진입 간선을 받는 쪽이 더 큰 쪽
 *
 * 진입 버전의 adjin
 * 진출 버전의 adjout
 * 인접 리스트를 만든다.
 *
 * DFS도 가능하지만, 비교적 빠른 BFS로
 * 각 노드를 시작으로 한 BFS 탐색의 진입과 진출 횟수가  N과 동일하면 그 학생은 자신의 위치를 알 수 있다.
 *
 * 1. 입력값
 *  - 학생들의 수
 *  - 키 비교 횟수
 *  - 키 비교 ~~
 *
 * 2. 학생의 위치 알아내기 로직
 * 2-1. 학생 수 만큼 반복.
 * 2-2. 각 학생을 시작점으로 BFS 탐색을 한다.
 * 2-3. 각 노드의 수를 계산한다.
 * 2-4. 탐색이 완료되었을 때 노드의 수가 N가 동일하다면 know* +1
 *
 * 3. 출력
 *
 * [근거]
 * 우선... 그림에 학생을 노드로 표현하고 키의 대소를 간선으로 표현해봤다. -> 완전 그래프.
 * 그래프이므로, BFS가 일반적으로 알맞다... 이런 근거는 안될텐데. 아무튼.
 *
 * **/
public class SWEA_5643 {
  static int N, M;
  static ArrayList<Integer>[] adjin; // 학생 번호 1부터 시작. 1-based
  static ArrayList<Integer>[] adjout;
  //
  static boolean[] visited; // 각 학생 검사할 때마다 리셋 시켜야 함
  //
  static int know;
  //
  static StringTokenizer st;
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    for(int tc = 1; tc < T+1; tc++) {
      N = Integer.parseInt(br.readLine());
      M = Integer.parseInt(br.readLine());
      adjin = new ArrayList[N+1];
      adjout = new ArrayList[N+1];

      for(int i = 1; i < N+1; i++) {
        adjin[i] = new ArrayList<>();
        adjout[i] = new ArrayList<>();
      }

      for(int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());

        // val1 < val2
        int val1 = Integer.parseInt(st.nextToken());
        int val2 = Integer.parseInt(st.nextToken());

        // 진출 간선: val1은 val2로 진출한다.
        adjout[val1].add(val2);

        // 진입 간선
        adjin[val2].add(val1);
//              System.out.println(Arrays.toString(adjout));
//              System.out.println(Arrays.toString(adjin)); 굿
      }
      know = 0;
      //


//           * 2. 학생의 위치 알아내기 로직
      for(int i = 1; i < N+1; i++) { // for i: 학생의 번호
//           * 2-1. 학생 수 만큼 반복.
//           * 2-2. 각 학생을 시작점으로 BFS 탐색을 한다.
        int cnt = 0;
        int start = i;

        // 진입 간선 기준: adjin
        visited = new boolean[N+1];

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[i] = true;

        while(!q.isEmpty()) {
          int cur = q.poll();

          for(int next : adjin[cur]) {
            if(visited[next]) continue;
            q.offer(next);
            visited[next] = true;
            cnt++;
          }
        }

        //  진입 간선 기준: adjout
        visited = new boolean[N+1];

        q = new ArrayDeque<>();
        q.offer(start);
        visited[i] = true;

        while(!q.isEmpty()) {
          int cur = q.poll();

          for(int next : adjout[cur]) {
            if(visited[next]) continue;
            q.offer(next);
            visited[next] = true;
            cnt++;
          }
        }

//               * 2-4. 탐색이 완료되었을 때 노드의 수가 N-1(나 제외)가 동일하다면 know* +1
        if (cnt == N-1) know++;
      }

      sb.append("#").append(tc).append(" ").append(know).append("\n");
    }
    System.out.println(sb);

  }
}
