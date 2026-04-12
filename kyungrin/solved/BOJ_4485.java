package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 메모리 초과가.. 났었습니다.
// 18652 KB
// 188 ms
/**
 * 잃을 수밖에 없는 최소 금액 N x N 크기의 동굴의 제일 왼쪽 -> [0][0]번 칸 제일 오른쪽 아래 칸인 -> [N-1][N-1]까지 이동 동굴의 각 칸마다 도둑루피가
 * 있는데, 이 칸을 지나면 해당 도둑루피의 크기만큼 소지금을 잃게 된다.
 *
 * (2 ≤ N ≤ 125) N = 0인 입력 -> 종료 상하좌우 인접한 곳으로 1칸씩 이동
 *
 * 모든 정수는 0 이상 9 이하 ----------------
 *
 * [다익스트라] (0, 0) -> (N-1, N-1)로 "최단 거리"로 이동하되, *여기서 최단 거리: 도둑루피의 최소 이동에 드는 "거리의 가중치"가 다르다.
 *
 * // AI 사용 -> 입력된 MAP을 정점과 간선 데이터로 그대로 사용하기 2차원 배열 dist를 만들어 최단 거리를 구한다.
 * 현재 위치에 도달할 때 최단 거리를 구하는 방법 -> now.cost + next.cost 로 최단만 기록
 *
 * 최종적으로 map[N-1][N-1] 을 보면 된다.
 **/
public class BOJ_4485 {

  static int N;
  static int[][] map;
  //
  static class Node implements Comparable<Node> {
    int r, c;
    int value;
    public Node(int r, int c, int value) {
      this.r = r;
      this.c = c;
      this.value = value;
    }

    @Override
    public int compareTo(Node o) {
      return this.value - o.value;
    }
  }
  static int[] mr = {-1, 1, 0, 0};
  static int[] mc = {0, 0, -1, 1};
  static PriorityQueue<Node> pq;
  static int[][] dist;
  //
  static int result;
  //
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tc = 0;
    StringBuilder sb = new StringBuilder();
    while (true) {
      tc++;
      N = Integer.parseInt(br.readLine());
      if (N == 0) break;

      map = new int[N][N];
      dist = new int[N][N];
      int INF = Integer.MAX_VALUE;

      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
          dist[i][j] = INF;
        }
      }

      result = 0;

      pq = new PriorityQueue<>();
      pq.offer(new Node(0, 0, map[0][0]));
      dist[0][0] = map[0][0];


      while (!pq.isEmpty()) {
        Node now = pq.poll();
        int nowr = now.r;
        int nowc = now.c;
        int nowv = now.value;

        if(dist[nowr][nowc] < nowv) continue;
        if(nowr == N-1 && nowc == N-1) break;

        for (int d = 0; d < 4; d++) {
          int nextr = nowr + mr[d];
          int nextc = nowc + mc[d];

          if (nextr < 0 || nextr >= N || nextc < 0 || nextc >= N) continue;

          int newCost = nowv + map[nextr][nextc];
          /**
           * AI 사용
           *
           * 새로 계산한 거리가
           * 기록된 최단 거리보다
           * 크면 진행하지 않는다. -> "같다"조건 추가
           *
           * (2,3)까지 비용 10으로 가는 경로가 이미 존재하는데,
           * 또 다시 (2, 3)에 10으로 도착했는데도 들어간다.
           *
           * 이동 경로가 많고, 가중치가 작아 겹치는 수가 많아 메모리가 부족할 수 있다.
           * **/
          if (dist[nextr][nextc] <= newCost) continue;

          dist[nextr][nextc] = newCost;

          pq.offer(new Node(nextr, nextc, newCost));
        }
      }

      result = dist[N-1][N-1];
      sb.append("Problem ").append(tc).append(": ").append(result).append("\n");
    }
    System.out.println(sb);
  }
}

