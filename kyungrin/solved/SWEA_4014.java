package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리 사용량: 27,648 kb
// 실행 시간: 93 ms
/**
 * [접근법] 행 우선 순회 + 열 우선 순회 + 시뮬레이션
 * [근거] 규칙을 지켜 풀이를 하는 것. => 시뮬레이션
 * 
 * 1. 입력값 받기
 * 2. 
 * 
 * 	2-1. 행 우선 순회
 * 	2-2. 열 우선 순회
 * 
 *  	- 연속된 셀이 가지고 있는 값이 전부 동일하다는 것을 판단하는 방법: "이전 칸과 현재 칸의 수 비교"
 *  		-> 같으면 -> 진행 -> 문제없이 전부 (+1)
 *  		-> 다르면
 *  			- (+1) 오르막 : 
 *  				- X 만큼 왼쪽으로 움직여서 동일한 수치를 가지고 있는지 확인한다. 
 *  					=> 전부 통과했다면 해당 위치의 visited를 true처리 (+1)
 *  			- (-1) 내리막 :
 *  				- X-1 만큼 오른쪽으로 움직여서 동일한 수치를 가지고 있는지 확인한다.
 *  					=> 전부 통과했다면 해당 위치의 visited를 true처리 (+1)
 *  			- 그 이상 : 탐색 X
 * 
 * 3. 출력하기
 * 
 * **/
public class SWEA_4014 {
	static int N, X;
	static int[][] map;
	//
	static boolean[][] visited;
	//
	static int result;
	//
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc<T+1; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//
			result = 0;
			// 1. 행 우선 순회
			for(int i = 0; i < N; i++) {
				if(isPossible(map[i])) result++;
			}
			
			// 2. 열 순회
			for(int i = 0; i < N; i++) {
				int[] col = new int[N];
				for(int j = 0; j < N; j++) col[j] = map[j][i];
				if(isPossible(col)) result++;
			}
			//
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	private static boolean isPossible(int[] line) { // 행 또는 열 한 줄
		boolean[] used = new boolean[N];
		
		for(int i = 0; i < N-1; i++) {
			// 1. 같은 높이
			if(line[i] == line[i+1]) continue;
			// 2. 높이 차이 2 이상 -> 실패
			if(Math.abs(line[i] - line[i+1]) > 1) return false;
			// 3. 오르막
			if(line[i] + 1 == line[i+1]) {
				for(int j = i; j > i-X; j--) {
					if(j < 0 || line[j] != line[i] || used[j]) return false;
					used[j] = true;
				}
			}
			// 4. 내리막
			else if (line[i] - 1 == line[i+1]) {
				for(int j = i+1; j <= i+X; j++) {
					if(j >= N || line[j] != line[i+1] || used[j]) return false;
					used[j] = true;
				}
			}
		}
		return true;
	}
	
}
