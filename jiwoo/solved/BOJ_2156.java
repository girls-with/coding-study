import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *   연속 3잔 불가능, 최대 포도주 양 구하기 -> DP (27분)
 *   메모리: 13,684KB
 *   시간: 92ms
 *   
 *   AI 활용: 반례 테스트 케이스 생성
 *   (입력)
 *   6
 *   2
 *   2
 *   1
 *   1
 *   2
 *   2
 *   (출력)
 *   8
 */
public class Main_B_2156_포도주시식 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] wine = new int[n+1];
		for(int i = 1; i <= n; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		
		
		int[][] dp = new int[n+1][3];
		dp[1][0] = 0;  // 해당 잔을 마시지 않는 경우
		dp[1][1] = wine[1];  // dp[i][1]: 이전 잔은 마시지 않고 해당 잔은 마심
		dp[1][2] = wine[1];  // dp[i][2]: 이전 잔도 마시고 해당 잔도 마심
		int maxWine = wine[1];
		for(int i = 2; i <= n; i++) {
			dp[i][0] = Math.max(Math.max(dp[i-1][0], dp[i-1][1]), dp[i-1][2]);
			dp[i][1] = Math.max(Math.max(dp[i-2][0], dp[i-2][1]), dp[i-2][2]) + wine[i];
			dp[i][2] = dp[i-1][1] + wine[i];
			maxWine = Math.max(Math.max(maxWine, dp[i][0]), Math.max(dp[i][1], dp[i][2]));
		}
		
		System.out.println(maxWine);
	}

}
