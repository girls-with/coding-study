package kyungrin.unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/** 가장 오른쪽 윗 칸으로 이동할 수 있는지 없는지 : 도착할 수 있으면 1, 없으면 0을 출력
 *
 * 8×8인 체스판에서 탈출하는 게임
 * 칸은 빈 칸 또는 벽
 * 가장 왼쪽 아랫 칸 -> 가장 오른쪽 윗 칸으로 이동
 *
 * 벽의 특이점 : 벽이 움직인다는 점
 * - 1초마다 모든 벽이 아래에 있는 행으로 한 칸씩
 * - 가장 아래에 있어서 아래에 행이 없다면 벽이 사라지게 된다.
 *
 * 이동 규칙:
 *  인접 한 칸 
 *  대각선 방향으로 인접한 한 칸
 *  제자리에 있기
 *  빈 칸으로만 이동
 *
 *  캐릭터 먼저 이동 -> 벽 이동
 *  벽이 캐릭터가 있는 칸으로 이동하면 더 이상 캐릭터는 이동할 수 없다.
 *
 *  -------
 *  BFS + 맵 이동 후 일치 확인
 *
 *  미로에서의 이동 => 최단거리 (아마도)
 *
**/
public class BOJ_16954 {
  static char[][] map;
  //
  static int[] mr = {0, -1, 1, 0, 0, -1, 1, -1, 1}; // 정지, 상하좌우 ,상좌,하좌,상우,하우
  static int[] mc = {0, 0, 0, -1, 1, -1, -1, 1, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    map = new char[8][8];
    for(int i = 0; i < 8; i++){
      String temp = br.readLine();
      for(int j = 0; j < 8; j++){
        map[i][j] = temp.charAt(j);
      }
    }

    ///////////////////////////////////////
    Queue<int[]> q = new ArrayDeque<>(); // 좌표 값 저장
    boolean[][] visited = new boolean[8][8];
    q.offer(new int[] {7, 0});
    visited[7][0] = true;

    while(!q.isEmpty()){
      int size = q.size();

      char[][] tempMap = new char[8][8];
      ArrayList<int[]> block = new ArrayList<>();
      for(int i = 1; i < 8; i++){
        tempMap[i] = map[i-1].clone(); // 블록이 바닥에 부딪히면 삭제
      }
      for(int i = 0; i < 8; i++){
        for(int j = 0; j < 8; j++) {
          if(tempMap[i][j] == '#') block.add(new int[] {i, j});
        }
      }

      for(int i = 0; i < size; i++) {
        int[] cur = q.poll();
        int curr = cur[0];
        int curc = cur[1];

        for (int d = 0; d < 9; d++) {
          int nextr = curr + mr[d];
          int nextc = curc + mc[d];

          if (nextr < 0 || nextr >= 8 || nextc < 0 || nextc >= 8)
            continue;
          if (visited[nextr][nextc])
            continue;

          for(int[] b : block){ // 블록 위치와 사용자 위치가 겹치는지 확인
            if(b[0] == nextr && b[1] == nextc) continue;
          }

          if(nextr == 0 && nextc == 7) {
            System.out.println(1);
            return;
          }

          visited[nextr][nextc] = true;
          q.offer(new int[]{nextr, nextc});
        }
      }
      // 맵 갱신
      for(int i = 0; i < 8; i++){
        map[i] = tempMap[i].clone();
      }
    }
    ///////////////////////////////////////
    System.out.println(0);
  }
}
