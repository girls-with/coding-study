package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 메모리: 14316 kb
 * 시간: 108 ms
 *
 * 2 <= 각 입력의 길이 <= 50
 * <p>
 * 여는 괄호 STK 보관. 닫는 괄호는 POP 1. 닫는 괄호를 받았는데 STK이 EMPTY할 시 UVPS 2. 모든 순회가 끝났는데 남아있으면 UVPS
 * <p>
 * 그외의 경우는 VPS이다.
 **/

// 디버깅 시 AI 사용
public class BOJ_9012 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < N; i++) { // N회, 0 ~ N-1회 반복
      Stack<Character> stk = new Stack<>();
      String temp = br.readLine();
      boolean isUVPS = false;

      for (int j = 0; j < temp.length(); j++) {

        char p = temp.charAt(j); // AI: j 대신 i를 넣고 있었다.
        if (p == '(') {
          stk.push(p);
        } else if (p == ')') {
          if (stk.isEmpty()) {
            isUVPS = true;
            break;
          }
          stk.pop();
        }
      }

      if (!stk.isEmpty() || isUVPS) { // or 연산자(||)가 아닌 비트마스크 연산자(|)를 쓰고 있었다.
        sb.append("NO").append("\n");
      } else {
        sb.append("YES").append("\n");
      }
    }

    System.out.println(sb);
  }
}
