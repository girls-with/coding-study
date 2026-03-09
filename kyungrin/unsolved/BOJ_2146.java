package kyungrin.unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/** 가장 짧은 다리 하나를 놓아 두 대륙을 연결할 때, 가장 짧은 다리 개수
 N×N(100이하의 자연수)
 0은 바다, 1은 육지
 한 섬과 다른 섬을 잇는 다리 하나만. 다리를 가장 짧게
 여러 섬: 섬이란 동서남북으로 육지가 붙어있는 덩어
 짧은 다리: 격자에서 차지하는 칸의 수가 가장 작은
 입력값 받기
 어디까지가 섬인지 찾기
 각 섬의 외곽 좌표 + 찾기, 저장(ArrayList[])*섬의 개수 만큼의 배열, 그 안에 외곽을 저장하는 배열리스트
 N은 100이하이기 때문에, 최악이라 해도 약 1억 보다 낮은 K라는 수보다 적다.
 섬의 외곽을 찾는 법:
 사방에 0 바다가 1라도 있으면 외곽이다.
 맨해튼 거리
 for i 섬의 개수
 ArrayList 섬1의 좌표들 = arr[i];
 for(섬1 좌표들 : 섬1의 좌표들 꺼내오기)
 for j i부터 시작하는 섬
 ArrayList 섬2의 좌표들 = arr[i] for (섬2 좌표들: 섬2의 좌표 꺼내오기)
 임시 변수 = 맨해튼 거리(섬1좌표, 섬2좌표) 최소 결과 갱신
 출력
 **/
public class BOJ_2146 {
  static int N;
  static int[][] map;
  //
  static boolean[][] visited;
  static int ilandCnt; // 섬의 개수
  static ArrayList<ArrayList<int[]>> outLines;
  static int[] mr = {-1, 1, 0, 0};
  static int[] mc = {0, 0, -1, 1};
  //
  static int minLen;
  //
  static StringTokenizer st;
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    map = new int[N][N];
    visited = new boolean[N][N];

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    ilandCnt = 0;
    outLines = new ArrayList<>();
    minLen = Integer.MAX_VALUE;


    // 1. 섬의 개수 찾기 // 2. 섬의 외곽 찾기
    // 여기서 cnt 증가시켜야 함
    for(int i = 0; i < N; i++) {
      for(int j = 0; j < N; j++) {
        if(map[i][j] == 0) continue;
        if(visited[i][j]) continue;

        ArrayList<int[]> tempList = new ArrayList<>();
        bfs(i, j, tempList);
        outLines.add(tempList);

        ilandCnt++;
      }
    }

    // 3. 맨해튼 거리 계산
    for(int i = 0; i < ilandCnt; i++){
//      ArrayList 섬1의 좌표들 = arr[i];
//      for(섬1 좌표들 : 섬1의 좌표들 꺼내오기)
//      for j i부터 시작하는 섬
//      ArrayList 섬2의 좌표들 = arr[i] for (섬2 좌표들: 섬2의 좌표 꺼내오기)
//      임시 변수 = 맨해튼 거리(섬1좌표, 섬2좌표) 최소 결과 갱신
      ArrayList<int[]> island1 = outLines.get(i);
      for(int[] pivot : island1){
        int r = pivot[0];
        int c = pivot[1];

        for(int j = i; j < ilandCnt; j++){
          ArrayList<int[]> island2 = outLines.get(j);
          for (int[] temp : island2){
            int tr = temp[0];
            int tc = temp[1];

            minLen = Math.min(minLen, getLen(r, c, tr, tc));
          }
        }

      }
    }

    // 4. 출력
    System.out.println(minLen);
  }

  private static void bfs(int r, int c, ArrayList<int[]> outline) {
    Queue<int[]> q = new ArrayDeque<>();
    q.offer(new int[] {r, c});
    visited[r][c] = true;

    while(!q.isEmpty()) {
      int[] cur = q.poll();
      int cr = cur[0];
      int cc = cur[1];
      boolean flag = false;

      for(int i = 0; i < 4; i++) {
        int nr = cr + mr[i];
        int nc = cc + mc[i];

        if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
        if(map[nr][nc] == 0) {
          flag = true;
          continue;
        }
        if(visited[nr][nc]) continue;

        q.offer(new int[] {nr, nc});
        visited[nr][nc] = true;
      }

      if(flag){
        outline.add(new int[] {cr, cc});
      }
    }
  }

  private static int getLen(int r1, int c1, int r2, int c2) {
    return Math.abs(r1-r2) + Math.abs(c1-c2);
  }
}
