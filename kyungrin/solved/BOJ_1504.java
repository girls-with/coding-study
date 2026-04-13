package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 68.792 mb
// 548 ms
/** 특정한 최단 경로
 *  임의로 주어진 두 정점은 반드시 통과
 *  - 한번 이동했던 정점은 물론, 한번 이동했던 간선도 다시 이동할 수 있다.
 *
 *  // 종료 조건
 *    - N 도착
 *     - 도착했을 때 2개의 정점을 방문했음
 *      -> 아니면 계속 진행
 *
 *  아이디어 AI 사용 -> 다익스트라 최단 거리에서 "반드시 거쳐야하는 노드"의 의미 = 다익스트라 쪼개서 돌리기
 *
 *  1 -> 2 -> 3 -> 4
 *  1 -> 3 -> 2 -> 4
 * **/
public class BOJ_1504 {
  // 간선 길이 200 < 800*800/2 -> 인접 리스트로 관리
  static int N, E;
  static class Vertex{
    int to, weight;
    public Vertex(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }
  }
  static ArrayList<Vertex>[] adjList;
  static int num1, num2;
  //
  static class Node implements Comparable<Node>{
    int num;
    int weight;
    public Node(int num, int weight) {
      this.num = num;
      this.weight = weight;
    }
    @Override
    public int compareTo(Node o) {
      return this.weight - o.weight;
    }
  }
  static PriorityQueue<Node> pq;
  static int[] dist;
  static final int INF = Integer.MAX_VALUE;
  //
  static StringTokenizer st;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());

    adjList = new ArrayList[N+1]; // 1-BASAED

    for(int i = 1; i < N+1; i++) adjList[i] = new ArrayList<>();

    for(int i = 0; i < E; i++){
      st = new StringTokenizer(br.readLine());

      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());

      adjList[from].add(new Vertex(to, weight));
      adjList[to].add(new Vertex(from, weight));
    }

    st = new StringTokenizer(br.readLine());
    num1 = Integer.parseInt(st.nextToken());
    num2 = Integer.parseInt(st.nextToken());

    // ----------------------------------------------------------------------------

    int[] dist1 = dijkstra(1);
    int[] dist2 = dijkstra(num1);
    int[] dist3 = dijkstra(num2);

    long case1 = INF;
    if(dist1[num1] != INF && dist2[num2] != INF && dist3[N] != INF){
      case1 = dist1[num1] + dist2[num2] + dist3[N];
    }
    long case2 = INF;
    if(dist1[num2] != INF && dist3[num2] != INF && dist2[N] != INF){
      case2 = dist1[num2] + dist3[num1] + dist2[N];
    }

    long answer = Math.min(case1, case2);
    if (answer == INF) System.out.println(-1);
    else System.out.println(answer);

  }

  private static int[] dijkstra(int start){
    pq = new PriorityQueue<>();
    dist = new int[N+1];
    Arrays.fill(dist, INF);

    pq.add(new Node(start, 0));
    dist[start] = 0;

    while(!pq.isEmpty()){
      Node now = pq.poll();

      if(dist[now.num] < now.weight) continue;

      for(Vertex v : adjList[now.num]){

        int newCost = now.weight + v.weight;
        if(dist[v.to] <= newCost) continue;
        dist[v.to] = newCost;
        pq.offer(new Node(v.to, newCost));
      }
    }

    return dist;
  }

}
