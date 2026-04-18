package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_23802 {
  static int N, P;
  static int[] f1;
  static int[] f2;
  //
  static int result;
  //
  static StringTokenizer st;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    for(int tc = 1; tc < T+1; tc++) {
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      P = Integer.parseInt(st.nextToken());
      st = new StringTokenizer(br.readLine());
      f1 = new int[N];
      for(int i = 0; i < N; i++) {
        f1[i] = Integer.parseInt(st.nextToken());
      }
      st = new StringTokenizer(br.readLine());
      f2 = new int[N];
      for(int i = 0; i < N; i++) {
        f2[i] = Integer.parseInt(st.nextToken());
      }
      result = 0;

      subset(0, 0, -1);

      sb.append("#").append(tc).append(" ").append(result).append("\n");
    }
    // 3. 출력하기
    System.out.println(sb);
  }
  private static void subset(int idx, int sum, int pref) { // 비료를 고르고 있는 화분 idx, 현재까지의 나무 높이의 합, 이전에 사용했던 비료의 번호
    // 기저
    if(idx == N) {
      result = Math.max(result, sum);
      return;
    }

    // 1. 선택함 -> 1번 비료
    int growing = pref == 1 ? f1[idx] - P : f1[idx];
    subset(idx + 1, sum + growing, 1);

    // 2. 선택하지 않음 -> 2번 비료
    growing = pref == 2 ? f2[idx] - P : f2[idx];
    subset(idx + 1, sum + growing, 2);
  }
}
