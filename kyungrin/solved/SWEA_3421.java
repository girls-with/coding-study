package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리 사용량: 27,264kb
 * 실행 시간: 120ms
 *
 * [문풀] : 만들 수 있는 버거의 종류 가짓수
 * 사용할 수 있는 재료(1~N 가지)로 햄버거 만들기 (1 ≤ N ≤ 20)
 * - 같은 종류의 재료들을 사용한다면 두 버거는 같은 종류의 버거
 *
 * 특이사항: 서로 어울리지 않는 재료  -> 동시에 포함한 버거는 만들 수 없다.
 *          - 서로 다른 두 개의 숫자 a, b (1 ≤ a, b ≤ N)
 *          - 같은 쌍이 여러 번 주어질 수도 있다.
 * ---------------------------------------------------------------
 * [접근법] : 부분집합, 조건에 맞는 해의 개수 구하기
 * [근거]  : "재료의 개수와 상관없이" 모든 재료의 "조합"을 구한다.
 * 재료 집합의 요소를 포함한 집합을 만드는 것이기 때문에 부분집합에 부합한다.
 *
 * BAD 궁합 -> 2차원 배열로 저장. e.g. 1-2의 조합이 bad 조합이면 bad[1][2] = true이다.
 * 부분 집합을 구하며, 선택하려는 재료에 대해 선택할 수 있는지 확인한다.
 * "이 재료를 선택하려고 하는데, 혹시 이전에 bad한 궁합을 선택하였느냐?"
 *
 * 선택하려는 재료에 대해
 * 1. bad 궁합 배열을 확인하고,
 * 2. 현재까지 선택한 재료들 중에 bad 궁합 재료가 있는지 확인한다. -> 선택의 유무는 boolean 배열에서 확인한다.
 * **/
public class SWEA_3421 {
  static int N, M; // 재료의 개수, 나쁜 궁합의 입력 개수
  static boolean[][] bad; // 나쁜 궁합. 재료는 idx로 표현된다.
  //
  static boolean[] isSelected; // 재료가 선택되었는지 아닌지의 여부를 확인
  //
  static int total; // 가짓수
  //
  static StringTokenizer st;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    for(int tc = 1; tc < T+1; tc++){
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      bad = new boolean[N+1][N+1]; // 1-based
      for(int i = 0; i < M; i++){
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        bad[v1][v2] = true;
        bad[v2][v1] = true;
      }

      isSelected = new boolean[N+1]; // 1-based
      total = 0;

      //
      subset(1);
      //

      sb.append("#").append(tc).append(" ").append(total).append("\n");
    }
    System.out.println(sb);
  }

  // 재료 부분집합을 생성하는 기능을 한다.
  private static void subset(int idx){ // 현재 선택하고 있는 재료
    // 재료 선택 y/n의 횟수가 재료의 총 개수가 된다면
      // 총 개수++
      // return
    if(idx > N){
      total++;
      return;
    }

    // 1. 재료 선택 y/n 정하기
      // 1-1. 나쁜 궁합의 재료가 앞서 선택되지 않았다면
    if(valid(idx)) {
      // 1-2. 선택한다.
      isSelected[idx] = true;
      subset(idx+1);
    }
    // 1-3. 선택하지 않는다.
    isSelected[idx] = false;
    subset(idx+1);
  }
  private static boolean valid(int idx){ // 현재 선택한 재료의 번호
    for(int i = 1; i < N+1; i++){ // for i: 재료의 번호 1부터 N
      // 이미 선택되었는데, 나쁜 조합이면. 선택하지 않도록 한다.
      if(isSelected[i] && bad[i][idx]) return false;
    }
    return true;
  }
}
