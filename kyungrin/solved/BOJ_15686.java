package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * [문해] : 도시의 치킨 거리의 최솟값
 * N×N (2 ≤ N ≤ 50)
 * 0은 빈 칸, 1은 집, 2는 치킨집
 *
 * M개를 고르고, 나머지 치킨집은 모두 폐업  (1 ≤ M ≤ 13)
 *
 * 특이사항:
 * - 치킨 거리: 집과 가장 가까운 치킨집 사이의 거리
 * 			- 각각의 집은 치킨 거리를 가진다.
 * - 도시의 치킨 거리: SUM(모든 집의 치킨 거리)
 * - 거리는 |r1-r2| + |c1-c2|로 구한다.
 *
 * --
 * [접근법] 조합 경우의 수 + 비교
 * 남겨둘 치킨 집 M개를 중복없이, 순서 상관없이 고른 후*
 * 남겨진 치킨 집들을 기준으로 모든 집들의 거리를 비교하고 가장 작은 값을 총합*에 더한다.
 *
 * 1. 입력값 받기
 * 	- N(변의 크기), M(치킨집 선택 개수)
 * 	- 0, 1, 2의 좌표
 * 		- 집의 좌표 배열 ArrayList int[]
 * 		- 치킨집의 좌표 배열  ArrayList int[]
 * 2. 조합 만들기
 *  - 현재 선택 순서, for i의 start
 *  	- 기저 파트
 *  		M개를 선택하면, 각각 집을 기준으로 각 치킨집까지의 거리를 계산
 *  		최소 거리 갱신
 *  	- 유도 파트
 *  		현재 선택 위치에서, 아직 선택되지 않은 치킨집을 선택한다.*
 * 3. 출력하기
 *
 * [근거]
 * 치킨 집 ?개 중에 M개를 선택한다.
 * 이때, 중복은 허용되지 않으며 선택 순서는 상관없다.
 * => 조합의 경우의 수를 재귀로 생성한다.
 * 치킨집 갯수 최대 13개. 최악의 경우 13C6는 적어도 30C15(1억)보단 작다.
 *
 * **/
public class BOJ_15686 {
  static int N, M;
  static ArrayList<int[]> homes;
  static ArrayList<int[]> chickens;
  //
  static int[] selectednum; // 선택한 치킨 집. 치킨 집의 번호를 저장
  //
  static int min;
  //
  static StringTokenizer st;
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    selectednum = new int[M];

    homes = new ArrayList<>();
    chickens = new ArrayList<>();
    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < N; j++) {
        char temp = st.nextToken().charAt(0);
        if(temp == '1') {
          homes.add(new int[] {i, j});
        }
        else if(temp == '2') {
          chickens.add(new int[] {i, j});
        }
      }
    }
    min = Integer.MAX_VALUE;
    //
    combination(0, 0);
    //
    System.out.println(min);
  }

  private static void combination(int idx, int start) {
    //
    if(idx == M) {
      // 오답 포인트: 교훈 - 설계할 때 구체적으로 잘하자(디버깅 시간이 더 오래 걸림) + 비교 계층이 확실한 변수명 제대로 짓기
      // (e.g. dist-minDist, chickDist-min(여기 너무 불명확))

      // min의 기준은 도시의 치킨 거리
      // chickDist의 기준은 도시의 치킨 거리

      // minDist의 기준은 집(1)마다의 최소 치킨 거리
      // dist의 기준은 집(1)마다의 치킨 거리

      // dist(집 마다의 치킨 거리)중에 최소 구하기  -> minDist
      // minDist(최고 치킨 거리)를 구했으면, "현재 선택된 치킨 집 기준, 도시의 치킨 거리"에 더하기 -> chickDist

      // "현재 선택된 치킨 집 기준, 도시의 치킨 거리"chickDist vs "이전까지 최소 도시의 치킨 거리"min


      int chickDist = 0; // 현재 선택 기준, 도시의 치킨 거리
      for(int[] home : homes) {
        int minDist = Integer.MAX_VALUE; // 0으로 설정하면 ... 모든 거리보다 0이 가장 작으므로 0이 된다...
        for(int i = 0; i < M; i++) { // for i: 선택된 치킨 집의 번호
          int n = selectednum[i];

          int[] chicken = chickens.get(n);

          int dist = getDist(home[0], home[1], chicken[0], chicken[1]);

          minDist = Math.min(minDist, dist);
        }
        chickDist += minDist;
      }
      min = Math.min(min, chickDist);
      return;
    }
    //
    for(int i = start; i < chickens.size(); i++) { // for i: 치킨 집의 번호
      selectednum[idx] = i;
      combination(idx+1, i+1);
    }
  }

  private static int getDist(int x1, int y1, int x2, int y2) {
    return Math.abs(x1 - x2) + Math.abs(y1 - y2);
  }

}
