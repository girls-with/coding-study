import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *   DP (6분)
 *   메모리: 24,960 kb
 *   시간: 77 ms
 */
public class Solution_화분과비료_DP {
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			
			int[] fertilizer1 = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) fertilizer1[i] = Integer.parseInt(st.nextToken());
			int[] fertilizer2 = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) fertilizer2[i] = Integer.parseInt(st.nextToken());
			
			int[][] dp = new int[N][2];
			dp[0][0] = fertilizer1[0];
			dp[0][1] = fertilizer2[0];
			for(int i = 1; i < N; i++) {
				dp[i][0] = Math.max(dp[i-1][0] + fertilizer1[i] - P, dp[i-1][1] + fertilizer1[i]);
				dp[i][1] = Math.max(dp[i-1][0] + fertilizer2[i], dp[i-1][1] + fertilizer2[i] - P);
			}
			
			sb.append("#").append(tc).append(" ").append(Math.max(dp[N-1][0], dp[N-1][1])).append("\n");
		}
		System.out.println(sb);
	}

}
