import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *   (33분)
 *   메모리: 26,368 kb
 *   시간: 88 ms
 */
public class Solution_14510_나무높이 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			
			int N = Integer.parseInt(br.readLine());
					
			int[] height = new int[N];
			int maxHeight = 0;
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());

				maxHeight = Math.max(maxHeight, height[i]);
			}
			
			int oddCnt = 0;
			int evenCnt = 0;
			for(int i = 0; i < N; i++) {
				height[i] = maxHeight - height[i];
				
				evenCnt += height[i] / 2;
				if(height[i] % 2 == 1) oddCnt++;
			}
			
			int result = 0;
			if(oddCnt >= evenCnt) {
				result = evenCnt*2;
				oddCnt -= evenCnt;
				if(oddCnt > 0) result += oddCnt*2 - 1;
			} else {
				result = oddCnt*2;
				evenCnt -= oddCnt;
				result += (evenCnt*2 / 3 )*2;
				if(evenCnt*2 % 3 == 1) result++;
				else if(evenCnt*2 % 3 == 2) result += 2;
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

}
