package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_14510 {
  static int N;
  static int[] trees;
  //
  static int highest;
  static int tEven;
  static int tOdd;
  //
  static int minDay;
  //
  static StringTokenizer st;
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    for(int tc = 1; tc < T+1; tc++) {
      N = Integer.parseInt(br.readLine());
      trees = new int[N];
      highest = 0; // 모든 나무는 0보다 크다

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) {
        trees[i] = Integer.parseInt(st.nextToken());
        highest = Math.max(highest, trees[i]);
      }
      tEven = 0;
      tOdd = 0;

      for (int i = 0; i < N; i++) {
        int need = highest - trees[i];

        tEven += need / 2;
        tOdd += need % 2;
      }

      while (tEven - tOdd > 1) {
        tEven--;
        tOdd += 2;
      }

      if(tEven > tOdd) minDay = tEven * 2;
      else if(tEven == tOdd) minDay = tEven + tOdd;
      else minDay = tOdd * 2 -1;


      sb.append("#").append(tc).append(" ").append(minDay).append("\n");
    }
    System.out.println(sb);
  }
}
