import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *   부분집합 (18분)
 *   메모리: 26,752 kb
 *   시간: 133 ms
 */
public class Solution_화분과비료_부분집합 {
	
	static int N, P;
	static int[] fertilizer1, fertilizer2;
	static int totalHeight;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			
			fertilizer1 = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) fertilizer1[i] = Integer.parseInt(st.nextToken());
			fertilizer2 = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) fertilizer2[i] = Integer.parseInt(st.nextToken());
			
			totalHeight = 0;
			subset(0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(totalHeight).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void subset(int idx, int sum, int prev) {
		
		if(idx == N) {
			totalHeight = Math.max(totalHeight, sum);
			return;
		}
		
		if(prev == 1) {  // 이전 화분에 1번 비료를 줬을 때
			subset(idx+1, sum+fertilizer1[idx]-P, 1);
			subset(idx+1, sum+fertilizer2[idx], 2);
		} else if(prev == 2) {  // 이전 화분에 2번 비료를 줬을 때
			subset(idx+1, sum+fertilizer1[idx], 1);
			subset(idx+1, sum+fertilizer2[idx]-P, 2);
		} else {  // 가장 처음 화분인 경우
			subset(idx+1, sum+fertilizer1[idx], 1);
			subset(idx+1, sum+fertilizer2[idx], 2);
		}
	}

}
