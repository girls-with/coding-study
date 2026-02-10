package kyungrin.unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 완전 탐색 42*42*64 최악의 경우: 약 16만 연산
 **/
public class BOJ_1018 {

  static int N = 0; // 행의 수
  static int M = 0; // 열의 수
  static char[][] table; // 체스판의 상태를 저장하는 2차원 배열
  static int min; // 최소 바뀜 횟수를 기록
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    min = Integer.MAX_VALUE;

    table = new char[N][M];

    for (int i = 0; i < N; i++) {
      String temp = br.readLine();
      // AI 사용
      // 공백이 없는 문자열을
      for (int j = 0; j < M; j++) {
        // 1. 문자열의 문자를 인덱스로 추출한다.
        table[i][j] = temp.charAt(j);
      }

    }

    //
    for (int i = 0; i <= N - 8; i++) {
      for (int j = 0; j <= M - 8; j++) {
        char pre = table[i][j];
        int cnt = 0;
        for (int k = i; k < i + 8; k++) {
          for (int q = j; q < j + 8; q++) {
            if (pre == 'B' && table[k][q] == 'B') {
              cnt++;
            }
            if (pre == 'W' && table[k][q] == 'W') {
              cnt++;
            }
            pre = table[k][q];
          }
        }
        min = Math.min(min, cnt);
      }
    }

    //
    System.out.println(min);
  }
}
