package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 변을 공유하는 두 개의 사각형은 다른 색이다. = 체스판에 색칠하는 경우는 두 가지 뿐. 흰색 시작 / 검은색 시작
 * <p>
 * [1] 특정 좌표에서 "이동"(행+1 OR 열+1)한다. [2] = 즉, 좌표의 이동이 "좌표 합의 홀짝 변화"를 일으킨다. [3] 홀짝에 따라 다른 색을 지정할 수 있다.
 * <p>
 * 시작점이 W일 때와 B일 때 경우 전부 색칠을 해볼 수 있다.
 **/
public class BOJ_1018 {

  static int N, M;
  static Character[][] map;
  //
  static int minCnt;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new Character[N][M];
    for (int i = 0; i < N; i++) {
      String temp = br.readLine();
      for (int j = 0; j < M; j++) {
        map[i][j] = temp.charAt(j);
      }
    }
    //

    // 체스판의 범위를 지정(*시작점 지정)한 뒤, 변경해야하는 색을 cnt한다.
    minCnt = Integer.MAX_VALUE;
    for (int i = 0; i <= N - 8; i++) { // 0 ~ N-8
      for (int j = 0; j <= M - 8; j++) { // 0 ~ M-8
        // 시작점 생성 완료

        int cnt1 = 0; // 짝수가 B, 홀수가 W
        int cnt2 = 0; // 짝수가 W, 홀수가 B

        // 시작점부터 8 범위 내로 순회
        for (int k = i; k < i + 8; k++) { // i ~ i+7
          for (int q = j; q < j + 8; q++) { // j ~ j+7
            if ((k + q) % 2 == 0) { // 현재 좌표 합이 짝수
              if (map[k][q] != 'B')
                cnt1++;
              if (map[k][q] != 'W')
                cnt2++;
            } else {
              if (map[k][q] != 'W')
                cnt1++;
              if (map[k][q] != 'B')
                cnt2++;
            }
          }
        }
        minCnt = Math.min(minCnt, Math.min(cnt1, cnt2));
      }
    }
    System.out.println(minCnt);
  }
}
