import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  플루이드 워셜 (14분)
 *  메모리: 46,028KB
 *  시간: 612ms
 */
public class Main_B_1613_역사 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());  // 사건의 개수 (정점 개수) 1~400
        int k = Integer.parseInt(st.nextToken());  // 전후 관계의 개수 (간선 개수) 1~50000
        
        int[][] adjMatrix = new int[n+1][n+1];  // adjMatrix[i][j]: i에서 j로 가는 최단 경로
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;
                adjMatrix[i][j] = 400;  // 모든 정점을 지나가더라도 가중치의 합이 399이므로 400으로 초기화 (INF 개념)
            }
        }
        
        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int before = Integer.parseInt(st.nextToken());  // 먼저 일어난 사건
            int after = Integer.parseInt(st.nextToken());  // 나중에 일어난 사건
            
            adjMatrix[before][after] = 1;
        }
        
        for(int r = 1; r <= n; r++) {  // 경유지
            for(int i = 1; i <= n; i++) {  // 출발지
                for(int j = 1; j <= n; j++) {  // 도착지
                    adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][r] + adjMatrix[r][j]);
                }
            }
        }
        
        int s = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < s; i++) {
        	st = new StringTokenizer(br.readLine());
        	int before = Integer.parseInt(st.nextToken());
        	int after = Integer.parseInt(st.nextToken());
        	
        	if(adjMatrix[before][after] < 400) sb.append(-1).append("\n");  // 앞의 사건이 뒤의 사건보다 먼저 일어났으면
        	else if(adjMatrix[after][before] < 400) sb.append(1).append("\n");  // 뒤의 사건이 앞의 사건보다 먼저 일어났으면
        	else sb.append(0).append("\n");  // 유추할 수 없음
        }
        
        System.out.println(sb);
        
    }

}