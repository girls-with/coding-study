package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 메모리 사용량: 22016 kb
// 시간 : 220 ms
/**
 * 총 N명 (5 ≤ N ≤ 2000)
 * 0번부터 N-1번 (0 ≤ a, b ≤ N-1, a ≠ b)
 * M (1 ≤ M ≤ 2000)
 *
 * 다음과 같은 친구 관계를 가진 사람 A, B, C, D, E가 존재하는지 구해보려고 한다.
 *
 * 친구 관계가 두 번 이상 주어지는 경우는 없다.
 *
 * ---
 * 아마도... 이어지는 5개의 노드가 있는지 확인하라는 문제같음
 * [접근법] 무방향 그래프 +  모든 노드를 시작점으로 하여 DFS 하면서 cnt해서 5개 되면 1출력
 * [근거]
 * 1. 친구 관계는 무방향 관계
 *  => 밀집 보단 희소가 될 가능성이 높아서... 기본적으로 간선의 개수가 작다.
 *  => 행렬 말고 노드 리스트로 저장하기
 *
 * 2. "이어짐"을 확인해야 해서 직관적으로 DFS,
 *      => 그러나 BFS로도 가능할듯. (너비의 개수를 세는 방향으로)
 * **/
public class BOJ_13023 {
    static int N, M;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    //
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        
       
        for (int i = 0; i < N; i++) {
            visited[i] = true; // 시작 정점 처리
            
            if(dfs(i, 1)) { // 특정 노드에서 5개의 정점이 이어진 경로가 있다면
            	System.out.println(1);
            	return; // main
            }
        
            visited[i] = false;
        }
        
        System.out.println(0);
    }
    
    static boolean dfs(int cur, int depth) {
    	if(depth == 5) return true;
    	
    	// 현재 노드 기준으로 연결된 노드 탐색
    	for(int next : adj[cur]) {
    		if(visited[next]) continue; // 탐색했던 노드는 탐색하지 않는다.
    		
    		visited[next] = true;
    		
    		if(dfs(next, depth+1)) return true; // 해당 경로에서 depth == 5를 찍고 true가 올라오는 것
    		
    		visited[next] = false;
    	}
    	
    	// 현재 노드 기준 연결된 노드를 전부 살펴봤음에도 depth==5 ture 조건이 충족되지 못했다면
    	// 해당 시작 노드에서 시작하여 5개의 정점이 연결되는 경로는 없는 것
    	return false; 
    }
}
