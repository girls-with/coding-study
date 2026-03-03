package kyungrin.unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [문해] : 가능성 있는 암호들을 모두 구하라
 * 서로 다른 L개의 알파벳 소문자들로 구성
 *
 * - 최소 한 개의 모음(a, e, i, o, u)
 * - 최소 두 개의 자음
 * - 알파벳이 암호에서 증가하는 순서
 *
 * - 조교들이 암호로 사용했을 법한 문자의 종류 C가지
 *
 * -----------------------------------------
 * [접근법] : 순열 경우의 수 + 조건(최소 한 개의 모음, 최소 두 개의 자음, 증가함)
 * 1. 입력값
 *  - L개, C개
 *  - C개의 알파벳
 * 2. 가능한 암호 구하기
 *    - 모든 순열(저장:password)을 생성*한 후 : 짜피 출력해서 바로바로 못함
 *    - 최소 한 개의 모음이 있지 않으면 out
 *    - 최소 두 개의 자음이 있지 않으면 out
 *
 *    - 각각의 요소*char를 대소 비교하여 => 해당 부분 가지치기 가능할듯 함.
 *    - 매개변수로 이전의 선택을 전달하여 해당 값이 더 크면, 그 가지는 더 이상 탐색하지 않는다.
 *       - 대상 요소가 이후 요소보다 크면 out
 *
 * [순열의 근거]
 * C개의 알파벳 중 L개의 알파벳을 순서있게 고르는 작업이기 때문이다.
 *
 * **/
public class BOJ_1759 {
  static int L, C; // 뽑는 수, 주어진 수
  static char[] alpha;
  //
  static char[] mo ={'a', 'e', 'i', 'o', 'u'};
  static boolean[] visited;
  static char[] password;
  //
  static StringTokenizer st;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    L = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    alpha = new char[C];
    visited = new boolean[C];
    st = new StringTokenizer(br.readLine());
    for(int i =0; i < C; i++){
      alpha[i] = st.nextToken().charAt(0);
    }
    // 사전식 출력을 위해 오름차순 정렬
    Arrays.sort(alpha);
//    System.out.println(alpha[0]);
    password = new char[L];
    //
    permutation(0);
    //
  }

  private static void permutation(int idx){ // 현재 dix
    //
    if(idx == L){

      for(int i = 0; i < L-1; i++){
        if(password[i] > password[i+1]) return;
      }
      int mocnt = 0;
      for(int i = 0; i < L; i++){ // for i: 선택한 암호의 idx;
        for(char m : mo){
          if(password[i] == m) mocnt++;
        }
      }
      if(mocnt < 1 || mocnt == L-1) return; // 모음 하나 이상, 자음 두 개 이상

      for(int i = 0; i < L; i++){ // for i: 선택한 암호의 idx;
        System.out.print(password[i]);
      }
      System.out.println();
      return;
    }

    //
    for(int i = 0; i < C; i++){ // for i: 주어진 숫자의 idx
      if(visited[i]) continue;
      visited[i] = true;
      password[idx] = alpha[i];

      permutation(idx+1);

      visited[i] = false;
    }
  }
}
