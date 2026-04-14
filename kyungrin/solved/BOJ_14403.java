package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 16872 kb
// 164 ms
/** i에서 j로 가는 길이가 양수인 경로가 있는지 없는지
 *
 * 가중치 없는, 방향 그래프
 * 모든 정점 (i, j)에서 모든 정점으로 갈 수 있는 길이 존재하는지 아닌지
 *
 * 특이점 : 동일한 정점으로 가는 길이 있는지도 조사해야한다.
 * ------------------------
 * [풀이법] : 플루이드 워셜
 * 모든 정점에서 모든 정점으로의 거리 존재 유무 체크이므로
 *
 * 1. 행렬 입력값을 받는다.
 * 2. 3중 for문으로 dist 업데이트
 * 3. 출력용 인접 행렬을 생성
 *  - 값이 존재하면 1
 *  - 갱신되지 않았다면 0
 *
 * **/
public class BOJ_14403 {
  static int N;
  static int[][] adjMatrix;
  //
  static int INF = 10000; // 모든 정점 -> 모든 정점이 경로를 가짐
  static int[][] dist;
  //
  static int[][] result;
  //
  static StringTokenizer st;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    adjMatrix = new int[N][N];
    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < N; j++) {
        adjMatrix[i][j] = Integer.parseInt(st.nextToken());
      }
//      System.out.println(Arrays.toString(adjMatrix[i]));
    }
    dist = new int[N][N];
    for(int i = 0; i < N; i++){
      for(int j = 0; j < N; j++) {
        if(adjMatrix[i][j] == 1) dist[i][j] = 1;
        else dist[i][j] = INF;
      }
    }
    //
    for(int k = 0; k < N; k++){
      for(int i = 0; i < N; i++){
        for(int j = 0; j < N; j++) {
          if(dist[i][j] > dist[i][k] + dist[k][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];
//            System.out.println(i + ", " + j + ": " + dist[i][j]);
          }
        }
      }
    }
    //

    result = new int[N][N];
    for(int i = 0; i < N; i++){
      for(int j = 0; j < N; j++) {
        if(dist[i][j] == 10000) result[i][j] = 0;
        else result[i][j] = 1;
//        System.out.println(Arrays.toString(dist[i]));
      }
    }

    StringBuilder sb = new StringBuilder();

    for(int i = 0; i < N; i++){
      for(int j = 0; j < N; j++) {
        sb.append(result[i][j]).append(" ");
      }
      sb.append("\n");
    }
    System.out.println(sb);
  }
}
