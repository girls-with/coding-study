package kyungrin.unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

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
 * [접근법] 아기 상어가 물고기를 먹을 때마다 BFS - 델타는 상좌\하우
 * [근거]
 * 아기 상어의 이동 규칙을 성립시키기 위해서는
 * 1. 움직이기 이전 맵 전체의 상황을 파악
 * 2. 동시에 움직여서 "동일한 가까운 거리"를 찾는 것
 * 둘 중 하나 필요하다.
 *
 * 때문에 아기 상어가 물고기를 먹어, 좌표를 이동했을 때 마다
 * - BFS
 * - 크기 update
 * - map update
 * - result update (너비를 더한다)
 * 가 필요하다.
 *
 * **/
public class BOJ_16236 {
  static int N;
  static int[][] map;
  //
  static int[] mr = {-1, 0, 1, 0}; // 상좌하우
  static int[] mc = {0, -1, 0, 1}; // 상좌하우
  static int fcnt;
  static int size;
  static Queue<int[]> start; // 시작 좌표
  //
  static int result;
  //
  static StringTokenizer st;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    map = new int[N][N];
    start = new ArrayDeque<>();
    fcnt = -1;
    for(int  i =0; i < N ; i++){
      st = new StringTokenizer(br.readLine());
      for(int j =0; j < N ; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if(map[i][j] == 9) {
          start.add(new int[] {i, j});
        }
        if(map[i][j] != 0){
          fcnt++;
        }
      }
      size = 2;
      result = 0;

      //

      while(fcnt > 0){


      }
      //

      System.out.println(result);
    }
  }
}
