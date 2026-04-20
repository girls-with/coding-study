import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *   비트마스킹 (30분)
 *   메모리: 27,008 kb
 *   시간: 1,423 ms
 */
public class Solution_화분과비료_비트마스킹 {
    
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
            bitShift();
            
            sb.append("#").append(tc).append(" ").append(totalHeight).append("\n");
        }
        System.out.println(sb);
    }
    
    private static void bitShift() {
        
        for(int i = 0; i < (1 << N); i++) {
            
            int height = 0;
            
            if((i & 1) == 1) height = fertilizer1[0];
            else height = fertilizer2[0];
            
            for(int j = 1; j < N; j++) {
                
                if((i & (1 << j-1)) != 0 && (i & (1 << j)) != 0) height += fertilizer1[j] - P;
                else if((i & (1 << j-1)) == 0 && (i & (1 << j)) != 0) height += fertilizer1[j];
                else if((i & (1 << j-1)) != 0 && (i & (1 << j)) == 0) height += fertilizer2[j];
                else if((i & (1 << j-1)) == 0 && (i & (1 << j)) == 0) height += fertilizer2[j] - P;
            
            }
            
            totalHeight = Math.max(totalHeight, height);
        }
    }

}