package kyungrin.unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모리 사용량 : 128516 kb
 * 실행 시간: 596 ms
 * 
 * [문풀] : 1번 과목부터 N번 과목까지 차례대로 최소 몇 학기에 이수하는지 출력
 * 선수과목이 있어 해당되는 모든 과목을 먼저 이수해야만 해당 과목을 이수.
 *
 * - 한 학기에 들을 수 있는 과목 수에는 제한이 없다.
 * - 모든 과목은 매 학기 항상 개설
 *
 *  과목의 수 N(1 ≤ N ≤ 1000)
 *  조건의 수 M(0 ≤ M ≤ 500000)
 *  정수 A B 형태로 주어진다. A번 과목이 B번 과목의 선수과목. (1 ≤ A < B ≤ N)
 *----------------------------------------------
 * 접근법: 위상정렬 알고리즘
 * 접근법의 근거:
 * 선수 과목이 있고, 그 순서를 지켜 이수한다는 건
 * 어떤 사물들 사이에 순서를 지켜 정렬한다는 것과 동일하므로 위상정렬 알고리즘을 사용한다.
 *
 * 과목 = 노드
 * 선수 과목 A와 B = A와 B는 인접하며, A에서 B로 진입하는 유향 간선을 가진다.
 * =>
 * 너비 별로 진입 간선 수가 0이 되는 노드들을 탐색한다.
 * 인접한 노드이더라도, 진입 간선 수의 조건을 충족하지 않으면 해당 너비에 탐색하지 않는다.
 * **/
public class BOJ_14567 {
  static int N, M; // 과목의 수, 조건의 수
  static ArrayList<Integer>[] adjList; // 인접 리스트
  //
  static int[] indegree; // 각 노드의 진입 간선 수
  //
  static int[] term; // 각 과목 당 학기 = 노드의 탐색 너비
  //
  static StringTokenizer st;
  public static void main(String[] args) throws IOException {
    // 1. 입력값 받기
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    adjList = new ArrayList[N+1];
    for(int i  = 1; i < N+1 ; i++){
      adjList[i] = new ArrayList<>();
    }
    indegree = new int[N+1];
    for(int i = 0; i < M; i++){
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      adjList[from].add(to); // from-to 노드 관계성 설정
      indegree[to]++; // to 노드의 진입 간선 수 증가시키기
    }

    term = new int[N+1];
    
    // 2. 조건있는 BFS 탐색
      // 유향 그래프라 방문 배열 X
    int breadth = 1;
    Queue<Integer> q = new ArrayDeque<>();
    for(int i = 1; i < N+1; i++){
      if (indegree[i] == 0){
        q.offer(i);
        term[i] = breadth; // 선수 과목이 없는 최초 과목들에게 1학기 지정
      }
    }


    while(!q.isEmpty()){
      int size = q.size();
      // 2-1. 너비를 증가시킨다. - while 1회 = 너비 1회
      breadth++;
      // 2-2. 큐의 크기 만큼 poll한다.
      for(int  i = 0; i < size; i++){
        int cur = q.poll();
        for(int next : adjList[cur]){
          // !! 선수 과목 하나를 탐색 완료했다면, 진입 간선을 하나 지운다 !!
          indegree[next]--;
          // !! 인접 노드이더라도, 진입 간선이 0개인 노드만 탐색한다. !!
          if (indegree[next] == 0){
            q.offer(next);
            term[next] = breadth;
          }
        }
      }
    }

    for(int i = 1; i < N+1; i++){
      System.out.print(term[i] + " ");
    }
  }
}
