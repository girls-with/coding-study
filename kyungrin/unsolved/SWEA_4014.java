package kyungrin.unsolved;

/** 활주로를 건설할 수 있는 경우의 수
 * N * N 크기의 절벽지대
 * 셀의 숫자는 그 지형의 높이
 *
 * 활주로
 * - 가로 또는 세로 방향으로 건설
 * - 높이가 동일한 구간에서만 가능
 *
 * 경사로 설치
 * - 높이 차이가 1 이고, 낮은 지형의 높이가 동일하게 경사로의 길이만큼 연속되는 곳
 *
 * 경사로의 높이: 1
 * 경사로의 길이: x ( 2 ≤ X ≤ 4 )
 * -----
 * [접근법]
 * 	- 행 우선 순회, 열 우선 순회 + 시뮬레이션
 * 	- 연속된 셀 검사
 * 		- 셀에서의 max값, min값 저장
 * 		- max, min의 최초값을 연속된 셀의 첫 값으로 저장한다. (동일코드메서드로 분리)
 *
 * 		- max와 min이 동일하면 활주로
 * 		- max와 min의 차이가 1이라면
 * 			- 연속된 셀에서 첫번 째 min의 위치를 찾아서,
 * 			- min의 위치부터,x만큼 오른쪽으로,또는 아래로 움직여 +1
 * 				- 그 과정에서 map을 벗어나면 해당 셀은 활주로x break
 * 			- 과정이 완료된 후 모두 동일한 값인지 확인(동일코드메서드로 분리)
 * 			 	=> 동일할시 결과값++
 *
 * [근거] 규칙에 따른 코드 구현이므로
 *
 *
 *
 * **/
public class SWEA_4014{
  public static void main(String[] args) {

  }

//  private static boolean isPossible(int i, boolean isrow) { // 행 또는 열의 시작점
//    int max, min;
//
////    if(isrow) {
////      max = map[i][0];
////      min = map[i][0];
////      for(int j = 0; j < N; j++) {
////        max = Math.max(max, map[i][j]);
////        min = Math.min(min, map[i][j]);
////      }
////    } else {
////      max = map[0][i];
////      min = map[0][i];
////      for(int j = 0; j < N; j++) {
////        max = Math.max(max, map[j][i]);
////        min = Math.min(min, map[j][i]);
////      }
////    }
//
////    if(max != min) return false;
////    return true;
//  }
}
