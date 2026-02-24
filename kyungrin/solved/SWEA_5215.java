package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리 사용량: 26,496 kb
 * 실행 시간: 146 ms
 *
 *  [문풀] : 칼로리 이하의 조합 중에서 민기가 가장 선호하는 햄버거
 *  햄버거 재료에 대한 점수, 재료에 대한 칼로리 (1 ≤ N ≤ 20, 1 ≤ L ≤ 10^4)
 *
 *  특이사항:
 *  - 같은 재료를 여러 번 사용할 수 없음
 *  ----------------------------------------------------
 *  [접근법] : 재료에 대한 부분집합을 완전 탐색하여 만들어, 제한 칼로리를 넘지 않게 만들기
 *
 *  모든 재료를 개수의 제한 없이 모든 조합을 만들 수 있다는 점에서 부분집합을 착안.
 *  부분집합의 계산은 최악의 경우 (2^20)1억 번 연산. 8초 제한이므로 가능.
 *
 *  + 부분집합의 매개변수에 칼로리를 누적하여, 가지치기하여 최적화 도모.
 *  + 맛도 매개변수로 누적하여 선택 여부 배열 없애기(선택 여부 배열은 기저에서 최종 계산시 이용됨)
 * **/
public class SWEA_5215 {
  static int N, L; // 재료수, 제한 칼로리
  static int[][] ingredients; // 재료(맛, 칼로리) 저장
  //
  static int most; // 가장 맛에 대한 점수가 높은 햄버거의 맛 점수
  //
  static StringTokenizer st;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int T = Integer.parseInt(br.readLine());
    for(int tc = 1; tc < T+1; tc++){
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      L = Integer.parseInt(st.nextToken());
      ingredients = new int[N][2];
      for(int i = 0; i < N; i++){
        st = new StringTokenizer(br.readLine());
        ingredients[i][0] = Integer.parseInt(st.nextToken());
        ingredients[i][1] = Integer.parseInt(st.nextToken());
      }
      most = Integer.MIN_VALUE;

      //
      subset(0, 0, 0);
      //

      sb.append("#").append(tc).append(" ").append(most).append("\n");
    }
    System.out.println(sb);
  }

  private static void subset(int idx, int calsum, int flavsum){ // 현재 재료, 현재까지 칼로리 누적, 현재까지 맛 누적
    // 가지치기
    if(calsum > L) return;
    // 기저
    if(idx == N){
      most = Math.max(most, flavsum);
      return;
    }
    // 유도
    // 1. 현재 재료를 선택한다.
    subset(idx+1, calsum + ingredients[idx][1], flavsum + ingredients[idx][0]);
    // 2. 현재 재료를 선택하지 않는다.
    subset(idx+1, calsum, flavsum);
  }
}
