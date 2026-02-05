package kyungrin.solved;
import java.util.Scanner;

public class BOJ_17478 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    // 1회 풀이
    recursion(n, n);
    // 2회 리팩토링
    refactorRecursion1(n, n);
    refactorRecursion2(0, n);
  }

  // !특징! 현재 재귀의 횟수와 비례한 "____"을 문자열 앞 단에 붙여야 한다.
  public static void recursion(int n, int k){
    if (k == n){
      System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
    }
    // 1. 재귀의 파생 중지
    if(k <= 0){
      for (int i = 0; i < (n - k); i++) {
        System.out.print("____");
      }
      System.out.println("\"재귀함수가 뭔가요?\"");
      for (int i = 0; i < (n - k); i++) {
        System.out.print("____");
      }
      System.out.println("\"재귀함수는 자기 자신을 호출하는 함수라네\"");
      for (int i = 0; i < (n - k); i++) {
        System.out.print("____");
      }
      System.out.println("라고 답변하였지.");
      return;
    }
    // 2. 반복되는 작은 일
    for (int i = 0; i < (n - k); i++) {
      System.out.print("____");
    }
    System.out.println("\"재귀함수가 뭔가요?\"");
    for (int i = 0; i < (n - k); i++) {
      System.out.print("____");
    }
    System.out.println("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
    for (int i = 0; i < (n - k); i++) {
      System.out.print("____");
    }
    System.out.println("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
    for (int i = 0; i < (n - k); i++) {
      System.out.print("____");
    }
    System.out.println("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
    recursion(n, k-1);
    for (int i = 0; i < (n - k); i++) {
      System.out.print("____");
    }
    System.out.println("라고 답변하였지.");
  }

  // 문자열 생성 최적화
  // 1. 매번 다시 생성하지 않도록 하여, 시간복잡도와 공간복잡도를 고려한다.
  // 2. 문자열의 메서드인 .repeat()을 사용해 본다.
  public static void refactorRecursion1(int n, int k){
    /* String.repeat(n)
     * String 객체의 메서드이다. 정수형 데이터를 인자로 받는다.
     * String 을 n번 반복하여 반환한다.
     * **/
    String indent = "____".repeat(n-k);
    if (k == n){
      System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
    }

    // 종료 조건과 반복 행위에서 공통적으로 반복되는 질문
    System.out.println(indent + "\"재귀함수가 뭔가요?\"");

    if (k <= 0){
      System.out.println(indent + "\"재귀함수는 자기 자신을 호출하는 함수라네\"");
      System.out.println(indent + "라고 답변하였지.");
      return;
    }

    System.out.println(indent + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
    System.out.println(indent + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
    System.out.println(indent + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");

    refactorRecursion1(n, k-1);

    System.out.println(indent + "라고 답변하였지.");

  }

  // 재귀 가독성 개선
  /*
  * 이전 코드:
  * 사용자 입력 n을 기준으로, 현재 상태를 k=n로 두고,
  * 깊이가 증가할 수록, k값을 1씩 감소시킨다.
  * n-k 를 통해 현재 깊이를 구한다.
  * e.g. 사용자 입력: 2 -> 첫 번째: 0(2-2) 깊이
  *
  * 아래 코드:
  * 사용자 입력을 maxDepth로 설정한다.
  * depth는 0부터 시작하여, 깊이가 0에서 시작한 재귀함수 임을 알린다.
  *
  * 재귀의 깊이를 계산하는 방식이 다르다.
  * **/
  public static void refactorRecursion2(int depth, int maxDepth){
    String indent = "____".repeat(depth);

    if (depth == 0){
      System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
    }
    System.out.println(indent + "\"재귀함수가 뭔가요?\"");
    if (depth == maxDepth){
      System.out.println(indent + "\"재귀함수는 자기 자신을 호출하는 함수라네\"");
      System.out.println(indent + "라고 답변하였지.");
      return;
    }

    System.out.println(indent + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
    System.out.println(indent + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
    System.out.println(indent + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");

    refactorRecursion2(++depth, maxDepth);

    System.out.println(indent + "라고 답변하였지.");

  }
}
