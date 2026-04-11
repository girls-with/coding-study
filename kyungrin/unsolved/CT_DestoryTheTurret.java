package kyungrin.unsolved;

/** 종료된 후 남아있는 포탑 중 가장 강한 포탑의 공격력을 출력
 N×M
 모든 위치에는 포탑

 포탑:
 - 공격력
 - 공격력이 0 이하 : 부숴짐
 - 최초에 공격력이 0인 포탑 즉, 부서진 포탑이 존재할 수 있습니다.

 반복(K번) :
 - 1턴 당 하는 5가지 액션
 1. 공격자 선정 : 가장 약한 포탑 선정
 -> 가장 약한 포탑 : 핸디캡이 적용되어 N+M만큼의 공격력이 증가
 - 선정 방식
 공격력이 가장 낮은 포탑 > 가장 최근에 공격한 포탑 > 행과 열의 합이 가장 큰 포탑 > 열 값이 가장 큰 포탑

 2. 공격자의 공격 : 가장 강한 포탑을 공격
 - 선정 방식
 공격력이 가장 높은 포탑 > 공격한지 가장 오래된 포탑 > 행과 열의 합이 가장 작은 > 열 값이 가장 작은

 3. 공격 시도
 3-1. 레이저 공격(선시도)
 공격자의 위치에서 공격 대상 포탑까지의 <최단 경로>로 공격
 -> 경로 없을 시 3-2.로
 -> 두 개 이상일 시 <우/하/좌/상>의 우선순위대로 먼저 움직인 경로
 이동 규칙
 - 상하좌우의 4개의 방향
 - 부서진 포탑이 있는 위치 못감
 - 가장자리 이동시 반대편으로 좌표 이동
 결과:
 -> 공격 대상 - 공격력
 -> 경로에 있는 포탑들 - 공격력/2

 3-2. 포탄 공격(후시도)
 규칙:
 - 가장자리에 떨어질 시 반대편으로 좌표 이동
 결과:
 -> 공격 대상 - 공격력
 -> 주위 8개의 방향에 있는 포탑도 피해 - 공격력/2
 (공격자일 경우 제외)

 4. 포탑 부서짐 : 공격력 0이하
 5. 부서지지 않은 포탑 중, "공격과 무관한" 포탑 공격력 + 1

 종료조건:
 - K번 다 돌다
 - 포탑이 1개가 된다면 그 즉시 중지

 **/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class CT_DestoryTheTurret {
  static int N, M, K;
  static int[][] map;
  //
  static int[] attacker;
  static int[][] attackTime;
  static int[] target;
  //
  static StringTokenizer st;
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    map = new int[N][M];
    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < M; j++){
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    attackTime = new int[N][M];
    while(K-- > 0){

      // 시간 흐름
      for(int i = 0; i < N; i++){
        for(int j = 0; j < M; j++){
          attackTime[i][j]++;
        }
      }
      attacker = new int[2];
      target = new int[2];

      // 1. 공격자 선정
      selectAttacker();

      // 2. 대상 선정
      // 3. 공격
      // -> 포탄 부서짐 처리함께
      // 5. 포탑 회복
      // -> 포탑 종료 조건 동시에 체크
    }
  }

  private static void selectAttacker(){
    // 1. 공격자 선정 : 가장 약한 포탑 선정
    //     -> 가장 약한 포탑 : 핸디캡이 적용되어 N+M만큼의 공격력이 증가
    //     - 선정 방식
    //     공격력이 가장 낮은 포탑 > 가장 최근에 공격한 포탑 > 행과 열의 합이 가장 큰 포탑 > 열 값이 가장 큰 포탑
    int[] weaker = new int[2];
    int weakerV = 5001;
    for(int i = 0; i < N; i++){
      for(int j = 0; j < M; j++){
        if(map[i][j] <= 0) continue;

        // ---------------------------
        if(weakerV > map[i][j]){ // 공격력이 가장 낮은 포탑
          weaker = new int[] {i, j};
          weakerV = map[i][j];
        }
        else if (weakerV == map[i][j]) { // 가장 최근에 공격한 포탑
          if(attackTime[weaker[0]][weaker[1]] > attackTime[i][j]) {
            weaker = new int[] {i, j};
            weakerV = map[i][j];
          }
          else if (attackTime[weaker[0]][weaker[1]] == attackTime[i][j]) { // 행과 열의 합이 가장 큰 포탑
            if((i + j) > (weaker[0] + weaker[1])){
              weaker = new int[] {i, j};
              weakerV = map[i][j];
            }
            else if ((i + j) == (weaker[0] + weaker[1])) { // 열 값이 가장 큰 포탑
              if(j > weaker[1]){
                weaker = new int[] {i, j};
                weakerV = map[i][j];
              }
            }
          }
        }
        // ---------------------------
      }
    }

    attacker = weaker;
    // System.out.println(Arrays.toString(attacker));
  }
  private static void selectTarget(){

  }
  private static void laser(int[][] start){

  }
  private static void bomb(){
    System.out.println("bomb");
  }
  private static void healing(){
    System.out.println("heal");
  }
}
