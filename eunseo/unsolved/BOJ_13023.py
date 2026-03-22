import sys
input = sys.stdin.readline

# 2개 이상의 선이 연결되어야 친구가 있다고 판단함
# 친구 있으면 1, 없으면 0

N, M = map(int, input().split())
# friends = [list(map(int, input().split())) for _ in range(M)]

# input - output 구조로 
# output이 다음번 반복에서 input이 되면 친구가 존재한다는 것

# 그래프 탐색 자료구조

# 인접리스트 생성
graph = [[] for _ in range(N)]

for _ in range(M):
    a, b = [list(map(int, input().split())) for _ in range(M)]
    graph[a].append(b)
    graph[b].append(a)

# 방문 배열 생성
visited = [0] * N
# 정답 설정
answer = 0 

# DFS 
def dfs(v, depth):
    global answer 

    # 여기서부터 이해가 안감
    if depth == 4:
        answer = 1
        return
    
    visited[v] = 1

    for next_v in graph[v]:
        if not visited[next_v]:
            dfs(next_v, depth + 1)
    
    visited[v] = 0 # 원상복구 

# 모든 사람을 시작점으로 시도
for i in range(N):
    dfs(i, 0)
    if answer:
        print(1)
        break

    else:
        print(0)

