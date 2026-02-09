package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class BOJ_10773 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    Stack<Integer> stk = new Stack<>();

    for (int i = 0; i < N; i++){
      int temp = Integer.parseInt(br.readLine());
      if (temp == 0){
        stk.pop();
      } else {
        stk.push(temp);
      }
    }

    int result = 0;
    while(!stk.isEmpty()){
      result += stk.pop();
    }

    System.out.println(result);
  }
}
