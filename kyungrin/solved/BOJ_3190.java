package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 112ms
/** 이 게임이 몇 초에 끝나는지 계산
 * NxN 정사각 보드위
 * 몇몇 칸에는 사과
 * 보드의 상하좌우 끝에 벽
 * 
 * 사과를 먹으면 뱀 길이가 늘어난다.
 * 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.
 * 
 * 뱀의 위치 (1, 1)
 * 뱀의 길이 1
 * 뱀의 방향 오른쪽
 * 
 * 뱀 이동 규칙
 * - 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
 * - 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
 * - 약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
 * 
 * 맵 1-based
 * 
 * -----
 * 시뮬레이션
 * 규칙을 지켜 구현한다.
 * ----
 * 
 * 뱀의 머리 방향
 * 뱀의 몸체 위치를 기록하는 boolean 2차원 배열
 * 	- 사있/사없에 따라 false/true 상태 잘 기록하기
 * 현재까지 걸린 시간
 * 
 * 1. 입력값
 * 	- N *맵은 1-based
 * 	- K개
 * 		- 사과 위치 기록
 * 	- L개 
 * 		- 방향 전환 정보 (X초, L:왼쪽으로 \ D: 오른쪽 90도)
 * 		- HashMap[X] => 방향으로 정보 저장 : 선입선출로 확인 시 버리기
 * 
 * 2. 구현
 * 	while(true)
 * 	- 뱀의 현재 방향*으로 1칸 씩 이동
 * 		(있어야 하는 정보 : 뱀의 머리 위치, 뱀의 머리 방향, 뱀의 꼬리)
 * 		만약 현재 위치가
 * 			1. r=0 r=N-1 c=0 c=N-1이면 break
 * 			2. 뱀의 몸체 위치 is true이면 break
 * 
 * 		- 이동한 부근(next-head) "머리"에 사과가 있을 시
 * 			- apples 삭제 : 맵에서 사과 삭제 (true -> false)
 * 			- 뱀의 몸체 위치 : "머리" 위치 상태 갱신
 * 		- 없을 시
 * 			- 뱀의 몸체 위치 : "머리", "꼬리" 위치 상태 갱신
 * 		
 * 		현재까지 걸린 시간++
 * 		현재까지 걸린 시간 == 방향 전환 정보 X초
 * 			L또는 D를 확인하고 뱀의 머리 방향 교체
 * 
 * 3. 출력
 * 	현재까지 걸린 시간
 * **/
public class BOJ_3190 {
	static int N;
	static int K;
	static boolean[][] apples;
	static int L;
	static Map<Integer, Integer> direction;
	//
	static int nowDrt; // 0, 1, 2, 3 = 상 우 하 좌
	static boolean[][] isSnake;
	static int size;
	static ArrayList<int[]> snake;
	//
	static int time;
	//
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		apples = new boolean[N+2][N+2]; // 1-based
		K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			apples[r][c] = true;
		}
		L = Integer.parseInt(br.readLine()); 
		direction = new HashMap<>();
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			String td = st.nextToken();
			int d = 0;
			if(td.equals("D")) d = 1; 
			direction.put(X, d); // L=>0, D =>1
		}
		nowDrt = 1; // 오른쪽
		isSnake = new boolean[N+2][N+2];
		size = 1; 
		snake = new ArrayList<>();
		time = 0;
		//
		int nowr = 1;
		int nowc = 1;
		snake.add(new int[]{nowr, nowc});
		isSnake[nowr][nowc] = true;
		while(true) {
			time++;
			int mover = 0;
			int movec = 0;
			
			if(nowDrt == 0) mover -= 1;
			else if(nowDrt == 1) movec += 1;
			else if(nowDrt == 2) mover += 1;
			else if(nowDrt == 3) movec -= 1;
			
			int nextr = nowr + mover;
			int nextc = nowc + movec;

			if(nextr <= 0 || nextr >= N+1 || nextc <= 0 || nextc >= N+1) break;
			if(isSnake[nextr][nextc]) break;
			
			if(apples[nextr][nextc]) {
				// 사과 삭제
				apples[nextr][nextc] = false;
				// 머리 처리
				isSnake[nextr][nextc] = true;
				snake.add(0, new int[] {nextr, nextc});
				size++;
				
				nowr = nextr;
				nowc = nextc;
			}
			else {
				// 꼬리 처리
				isSnake[snake.get(size-1)[0]][snake.get(size-1)[1]] = false;
				snake.remove(size-1);
				// 머리 처리
				isSnake[nextr][nextc] = true;
				snake.add(0, new int[] {nextr, nextc});
				
				nowr = nextr;
				nowc = nextc;	
			}
			
			if(direction.containsKey(time)) {
				change(direction.get(time));
			}
		}
		//
		System.out.println(time);
	}
	
	private static void change(int rotation) { // 0: 왼쪽으로 90도, 1: 오른쪽으로 90도
		if(rotation == 0) {
			if(nowDrt == 0) nowDrt = 3;
			else if(nowDrt == 1) nowDrt = 0;
			else if(nowDrt == 2) nowDrt = 1;
			else if(nowDrt == 3) nowDrt = 2;
		} else {
			if(nowDrt == 0) nowDrt = 1;
			else if(nowDrt == 1) nowDrt = 2;
			else if(nowDrt == 2) nowDrt = 3;
			else if(nowDrt == 3) nowDrt = 0;
		}
	}
}
