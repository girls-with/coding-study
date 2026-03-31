package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 100ms
/** 최종 톱니바퀴의 상태
 * 톱니바퀴(4개): 8개의 톱니 > N극 또는 S극 중 하나
 *
 *  총 K번 회전
 *  - 톱니바퀴의 회전은 한 칸을 기준
 *  - 시계 방향
 *  - 반시계 방향
 *
 * 회전 규칙
 * - A를 돌리는데, 맞닿은 B의 톱니의 극이 다름 :B는  A와 반대로 회전
 * - 같은 극 : 회전하지 않음
 * *톱니바퀴 돌->돌->돌X /돌-돌-돌(동시에)
 *
 * 1번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 1점
 * 2번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 2점
 * 3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점
 * 4번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점
 * ---
 * 입력값 :
 *  - 12시방향부터 시계방향 순서대로 주어진다.(8개) N극은 0, S극은 1
 *  - 회전 횟수 k
 *  - (회전시킨 톱니바퀴의 번호, 두 번째 정수는 방향(1 : 시계, -1 : 반시계))
 * ---
 *
 * 시뮬레이션 + 1차원 배열 자료구조
 *
 * 0. 모든 톱니바퀴 회전 여부 판단 (재귀) * boolean 1차원 배열 필요
 * 0-1. 시작 톱니바퀴
 *  왼 : 현재 톱니의 6번과 next 톱니의 2번 비교 -> 다른 극 -> 회전 TRUE-> 재귀
 *  오 : 현재 톱니의 2번과 next 톱니의 6번 비교 -> 다른 극 -> 회전 TRUE-> 재귀
 *  종료 조건 없어도 됨. 왼오에 톱니바퀴가 없으면 끝날 것.
 *
 * 1. true로 되어 있는 톱니바퀴 전부 회전
 * 1-1. 시계 방향(1) 회전 => idx++
 *  - 가장 뒤에 있는 요소 back을 변수에 저장.
 *  - 0번부터 6번까지 한 칸 씩 이동
 *  - 0번 인덱스에 back 갱신
 * 1-2. 반시계 방향(-1) 회전 => idx--
 *  - 가장 앞에 있는 요소 front를 변수에 저장
 *  - 1번부터 7번 요소 앞으로 한 칸 씩 이동
 *  - 7번 인덱스에 front 갱신
 *
 * Deque가 아무래도 더 편하려나? 아무튼
 * **/
public class BOJ_14891 {

  static int[][] wheels; // 크기 : (5, 8) // 톱니바퀴 번호 1-based
  static int K;
  //
  static int[][] turn; // 1-based. => (회전 여부(0->X, 1->O), 방향(1->시계, -1->반시계))
  //
  static int result;
  //
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    wheels = new int[5][8];
    for (int i = 1; i < 5; i++) {
      String temp = br.readLine();
      for (int j = 0; j < 8; j++) {
        wheels[i][j] = temp.charAt(j) - '0';
      }
    }
    K = Integer.parseInt(br.readLine());
    result = 0;
    //
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());

      turn = new int[5][2];
      //
      isTurn(start, direction);

      //
      for (int j = 1; j < 5; j++) {
        if (turn[j][0] == 1) {
//          System.out.println(j + "-p-");
//          System.out.println("turn[j][0]" + turn[j][0]);
//          System.out.println("turn[j][1]" + turn[j][1]);
          move(j, turn[j][1]);
        }
      }
    }
    //
    score();
    System.out.println(result);
  }

  // 아 헐 맞네
  // 계속 양 옆으로 영원히 움직이는 코드가 됨...
  // 재귀로 하고 싶었으면
  // 왼쪽, 오른쪽 한 번으로 해야했다
  private static void isTurn(int start, int direction) { // 톱니바퀴 번호(1~4), 회전 방향
    turn[start] = new int[]{1, direction};

    // 왼쪽으로
    int now = start;
    int leftDir = direction;
    for(int next = start-1; next >= 1; next--){
      if(wheels[now][6] != wheels[next][2]){
        if(leftDir == 1) leftDir = -1;
        else if(leftDir == -1) leftDir = 1;
        //
        turn[next] = new int[]{1, leftDir};
        now = next;
      } else { break; } // 극이 같으면 전파 X
    }

    // 오른쪽으로
    now = start;
    int rightDir = direction;
    for(int next = start+1; next < 5; next++) {
      if (wheels[now][2] != wheels[next][6]) {
        if (rightDir == 1) rightDir = -1;
        else if (rightDir == -1) rightDir = 1;
        //
        turn[next] = new int[]{1, rightDir};
        now = next;
      } else { break; }
    }
  }

  private static void move(int n, int dir){ // 휠 번호, 방향(1 or -1)
//    System.out.println(n + "번 wheel");
    if(dir == 1){
      int[] temp = new int[8];
      temp[0] = wheels[n][7];
      for(int i = 1; i < 8; i++){
//        System.out.println("temp:" + i  + "번 째 -> " + wheels[n][i-1]);
        temp[i] = wheels[n][i-1];
      }

      wheels[n] = temp;
    }
    else if (dir == -1){
      int[] temp = new int[8];
      temp[7] = wheels[n][0];
      for(int i = 0; i < 7; i++){
//        System.out.println("temp:" + i  + "번 째 -> " + wheels[n][i+1]);
        temp[i] = wheels[n][i+1];
      }
//      System.out.println("temp:" + 7  + "번 째 -> " + temp[7]);

      wheels[n] = temp;
    }
  }

  private static void score(){
//    System.out.println(Arrays.toString(wheels[1]));
//    System.out.println(Arrays.toString(wheels[2]));
//    System.out.println(Arrays.toString(wheels[3]));
//    System.out.println(Arrays.toString(wheels[4]));
    if(wheels[1][0] == 1) result += 1;
    if(wheels[2][0] == 1) result += 2;
    if(wheels[3][0] == 1) result += 4;
    if(wheels[4][0] == 1) result += 8;
  }
}
