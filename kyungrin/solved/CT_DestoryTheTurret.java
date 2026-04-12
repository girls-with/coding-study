package kyungrin.solved;


// 147ms, 14MB
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
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Queue;



public class CT_DestoryTheTurret {
  static int N, M, K;
  static int[][] map;
  //
  static int[] attacker;
  static int[][] attackTime;
  static int[] target;
  static boolean possible;
  static boolean[][] releatedAttact;
  static int[] mr = {0, 1, 0, -1, 1, 1, -1, -1}; // 우>하>좌>상
  static int[] mc = {1, 0, -1, 0, 1, -1, 1, -1};
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
      releatedAttact = new boolean[N][M];

      // 시간 흐름
      for(int i = 0; i < N; i++){
        for(int j = 0; j < M; j++){
          attackTime[i][j]++;
        }
      }
      attacker = new int[2];
      target = new int[2];

      // 1. 공격자 선정
//      System.out.println("selectAttacker");
      selectAttacker();
      map[attacker[0]][attacker[1]] += N+M;
//      System.out.println("attacker: " + Arrays.toString(attacker));

      // 2. 대상 선정
//      System.out.println("selectTarget");
      selectTarget();

//      System.out.println("target: " + Arrays.toString(target));

      // 공격과 연관된 포탑들 체크
      releatedAttact[attacker[0]][attacker[1]] = true;
      releatedAttact[target[0]][target[1]] = true;

      // 3. 공격
//      System.out.println("Attack");
      possible = false;
      laser();
//      System.out.println("bomb : " + !possible);
      if(!possible) bomb();
      // -> 포탄 부서짐 처리함께


      // 5. 포탑 회복
      // -> 포탑 종료 조건 동시에 체크
//      System.out.println("Heal");
      int end = healing();
      if(end <= 1) break;
    }

    // 출력
    int max = 0;
//    System.out.println("Check");
    for(int i = 0; i < N; i++){
      for(int j = 0; j < M; j++){
//        System.out.println(i + ", " + j  + " : "+ map[i][j]);
        max = Math.max(max, map[i][j]);
      }
    }

    System.out.println(max);
  }

  private static void selectAttacker(){
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

    /**
     * AI 사용 - 선정 완료 후 초기화해야 했음(이전에는 선정이 진행 중에 체크했다.)
     * **/
    attackTime[weaker[0]][weaker[1]] = 0;
    attacker = weaker;
    // System.out.println(Arrays.toString(attacker));

  }
  private static void selectTarget(){

    // 2. 공격자의 공격 : 가장 강한 포탑을 공격
    //     - 선정 방식
    //     공격력이 가장 높은 포탑 > 공격한지 가장 오래된 포탑 > 행과 열의 합이 가장 작은 > 열 값이 가장 작은

    int[] stronger = new int[2];
    int strongerV = -1;
    for(int i = 0; i < N; i++){
      for(int j = 0; j < M; j++){
        if(map[i][j] <= 0) continue;
        /**
         * AI 사용
         * 공격자는 대상에서 제외
         * **/
        if(attacker[0] == i && attacker[1] == j) continue;

        // ---------------------------
        if(strongerV < map[i][j]){ // 공격력이 가장 높은 포탑
          stronger = new int[] {i, j};
          strongerV = map[i][j];
        }
        else if (strongerV == map[i][j]) { // 공격한지 가장 오래된 포탑
          if(attackTime[stronger[0]][stronger[1]] < attackTime[i][j]) {
            stronger = new int[] {i, j};
            strongerV = map[i][j];
          }
          else if (attackTime[stronger[0]][stronger[1]] == attackTime[i][j]) { // 행과 열의 합이 가장 작은 포탑
            if((i + j) < (stronger[0] + stronger[1])){
              stronger = new int[] {i, j};
              strongerV = map[i][j];
            }
            else if ((i + j) == (stronger[0] + stronger[1])) { // 열 값이 가장 작은
              if(j < stronger[1]){
                stronger = new int[] {i, j};
                strongerV = map[i][j];
              }
            }
          }
        }
        // ---------------------------

      }
    }

    target = stronger;
    // System.out.println(Arrays.toString(target));
  }

  /**
   * AI 사용
   * parent[N][M][2(이전 칸 좌표)] : 현재 칸이 어디서 왔는지에 대해 관리하는 배열
   *
   * BFS를 통해 최단 거리를 알아내고, 최단 거리의 경로를 기록한다.
   * **/
  // 레이저
  private static void laser(){

    int[][][] parent = new int[N][M][2];

    Queue<int[]> q = new ArrayDeque<>();
    q.offer(attacker);
    boolean[][] visited = new boolean[N][M];
    visited[attacker[0]][attacker[1]] = true;

    while(!q.isEmpty()){
      int[] cur = q.poll();
      int cr = cur[0];
      int cc = cur[1];

      for(int d = 0; d < 4; d++){
        int nr = cr + mr[d];
        int nc = cc + mc[d];

        if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
          int[] crc = change(nr, nc);
          nr = crc[0];
          nc = crc[1];
        }

        if(nr == target[0] && nc == target[1]){ // 최단 거리 체크
          possible = true;
          parent[nr][nc] = new int[] {cr, cc};
          // 경로 추적
          backtrack(parent);
          return;
        }

        if(map[nr][nc] <= 0) continue;
        if(visited[nr][nc]) continue;

        q.offer(new int[] {nr, nc});
        visited[nr][nc] = true;
        parent[nr][nc] = new int[] {cr, cc};
      }
    }
  }

  private static void backtrack(int[][][] parent){
    ArrayList<int[]> area = new ArrayList<>();

    int pr = target[0];
    int pc = target[1];


//    System.out.println("parent[pr][pc]" + ", " + Arrays.toString(parent[pr][pc]));
    while(true){
      int[] next = parent[pr][pc];

      if(next[0] == attacker[0] && next[1] == attacker[1]) break;
      area.add(next);
      pr = next[0];
      pc = next[1];
    }

    attack(area);
  }

  // 3-2. 포탄 공격(후시도)
  private static void bomb(){
    // System.out.println("bomb");
    ArrayList<int[]> area = new ArrayList<>();

    for(int d = 0; d < 8; d++){
      int nr = target[0] + mr[d];
      int nc = target[1] + mc[d];

      if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
        int[] crc = change(nr, nc);
        nr = crc[0];
        nc = crc[1];
      }
      if(map[nr][nc] <= 0) continue;
      if(nr == attacker[0] && nc == attacker[1]) continue;

      area.add(new int[] {nr, nc});
    }

    attack(area);
  }

  private static void attack(ArrayList<int[]> area){
    int damage = map[attacker[0]][attacker[1]];

    // 타겟
    map[target[0]][target[1]] = map[target[0]][target[1]] - damage;
    if(map[target[0]][target[1]] < 0) map[target[0]][target[1]] = 0;
    // 타격 영역
    for(int[] a : area) {
//      System.out.println(Arrays.toString(a));
      releatedAttact[a[0]][a[1]] = true;

      map[a[0]][a[1]] = map[a[0]][a[1]] - damage/2;
      if(map[a[0]][a[1]] < 0) map[a[0]][a[1]] = 0;
//      System.out.println(": " + map[a[0]][a[1]]);
    }
  }

  /**
   * AI 사용
   * 범위를 벗어난 좌표를 반대편으로 수학적으로 계산하여 넘기기
   * **/
  private static int[] change(int nr, int nc){
    nr = (nr + N) % N;
    nc = (nc + M) % M;
    return new int[] {nr, nc};
  }

  private static int healing(){
    // System.out.println("heal");

    int cnt = 0;
    for(int i = 0; i < N; i++){
      for(int j = 0; j < M; j++){
        if(map[i][j] <= 0) continue;
        cnt++;
        if(releatedAttact[i][j]) continue;
        map[i][j]++;
//        System.out.println("... healing ... : " + map[i][j]);
      }
    }



    return cnt;
  }
}
