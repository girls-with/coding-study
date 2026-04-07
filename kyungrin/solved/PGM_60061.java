package kyungrin.solved;

/** 명령어를 수행한 후 구조물의 상태
 2차원 가상 벽면에 기둥과 보를 이용한 구조물을 설치
 기둥과 보는 길이가 1인 선분으로 표현
 격자 칸의 각 변에 정확히 일치하도록 설치할 수 있습니다.

 n x n 크기 정사각 격자 형태
 격자는 1 x 1 크기

 기둥
 1. 바닥 위에 있거나
 2. 보의 한쪽 끝 부분 위에 있거나
 3. 다른 기둥 위에 있어야 합니다.

 보
 1. 한쪽 끝 부분이 기둥 위에 있거나
 2. 양쪽 끝 부분이 다른 보와 동시에 연결

 + 기둥과 보를 삭제하는 기능
 + 교차점 좌표를 기준으로 보: 오른쪽, 기둥: 위쪽 방향으로 설치 또는 삭제

 출력)
 x좌표 기준 오름차순 -> y 좌표 기준 오름차순 -> 기둥이 먼저

 3차원 배열에 갱신된 구조물들 -> ArrayList로 옮기기 -> list로 옮기기

 -------
 들어온 입력이 규칙에 따라 가능한 설치인지 확인하는 시뮬레이션

 **/
import java.util.ArrayList;
public class PGM_60061 {
  public int[][] solution(int n, int[][] build_frame) {
    // build_frame: [[x, y, a, b], ...]
    // x, y [가로 좌표, 세로 좌표]
    // a는 설치 또는 삭제할 구조물의 종류 : 0은 기둥, 1은 보
    // b는 0은 삭제, 1은 설치

    // 맵의 구조를 갱신하는 자료 구조
    // 5 -> 0, 1, 2, 3, 4, 5 선을 이용해서 총 6개
    boolean[][][] structure = new boolean[n+1][n+1][2]; // 0:기둥/1:보

    for(int[] build : build_frame){
      int x = build[0];
      int y = build[1];
      int a = build[2]; // 기둥?보?
      int b = build[3]; // 삭제?설치?

      // 1. 삭제
      if(b == 0){
        // 0 -> 기둥에서 삭제
        if(a == 0){
          // 삭제해보고 안되면 복구
          structure[x][y][0] = false;
          if(!isVaildAll(n, structure)) structure[x][y][0] = true;
        }
        // 1 -> 보에서 삭제
        else {
          // 삭제해보고 안되면 복구
          structure[x][y][1] = false;
          if(!isVaildAll(n, structure)) structure[x][y][1] = true;
        }
      }
      // 2. 건설
      else {
        if(isVaild(x, y, a, structure, n)) structure[x][y][a] = true;
      }
    }

    ArrayList<int[]> result = new ArrayList<>();
    // 출력 순서 x -> y -> 기둥보
    for(int i = 0; i <= n; i++){
      for(int j = 0; j <= n; j++){
        if(structure[i][j][0]) result.add(new int[] {i, j, 0});
        if(structure[i][j][1]) result.add(new int[] {i, j, 1});
      }
    }

    int[][] answer = new int[result.size()][3];
    for(int i = 0; i < result.size(); i++){
      answer[i] = result.get(i);
    }

    return answer;
  }


  private static boolean isVaild(int x, int y, int a, boolean[][][] structure, int n){
    // a가 0이면 (기둥)
    if(a == 0){
      // 1. y가 0인지 확인
      if(y == 0) return true;
      // 2. 현재 설치된 기둥들 중
      if((y > 0 && structure[x][y - 1][0])) return true; // -> x, y-1 했을 때 있는지
      // 3. 현재 설치된 보들 중
      if(structure[x][y][1] || (x != 0 && structure[x-1][y][1])) return true; // -> x, y | x-1, y가 있는지
    }
    // a가 1이면 보
    else {
      // 1. 현재 설치된 기둥들 중
      if(structure[x][y-1][0] || structure[x+1][y-1][0]) return true;  // -> x, y-1 | x+1 y-1
      // 2. 현재 설치된 보들 중
      if (x > 0 && x < n && structure[x - 1][y][1] && structure[x + 1][y][1]) return true;  // -> y고정 x-1 && x+1인 보가 있는지
    }

    return false;
  }

  private static boolean isVaildAll(int n, boolean[][][] structure){
    for(int i = 0; i <= n; i++){
      for(int j = 0; j <= n; j++){
        if(structure[i][j][0] && !isVaild(i, j, 0, structure, n)) return false;
        if(structure[i][j][1] && !isVaild(i, j, 1, structure, n)) return false;
      }
    }
    return true;
  }
}
