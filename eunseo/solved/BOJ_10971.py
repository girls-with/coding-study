N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)]

# 최소 비용을 저장할 변수 설정
min_cost = float('inf')

def dfs(start, now, visited_cnt, cost):
    global min_cost 
    
    # 가지치기: 이미 현재 비용이 최소 비용 이상이면 더 볼 필요 없음
    if cost >= min_cost:
        return
    
    # 모든 도시를 다 방문한 경우
    if visited_cnt == N:
        # 현재 도시에서 시작도시로 돌아갈 수 있으면
        if arr[now][start] != 0:
            min_cost = min(min_cost, cost + arr[now][start])
        return
    
    # 현재 도시에서 다음 도시로 이동
    for next in range(N):
        # 아직 방문하지 않았고, 갈 수 있는 길이 있으면
        if visited[next] == 0 and arr[now][next] != 0:
            # 방문 표시 
            visited[next] = 1
            dfs(start, next, visited_cnt + 1, cost + arr[now][next])
            # 백트래킹
            visited[next] = 0

# 시작 도시를 하나씩 정해서 탐색 
for start in range(N):
    visited = [0] * N
    visited[start] = 1
    dfs(start, start, 1, 0)

print(min_cost)



    # 풀면서 문제가 3개 있었음 
    # 방문 배열 2차원 리스트로 생성한게 문제
    # # visited 방문 배열 생성
    # visited = [[0] * N for _ in range(N)]
    # # 시작점은 방문했다고 표시
    # visited[start] = 1

    # 델타 탐색으로 풀려해서 문제
    # # 현재 위치에서 갈 수 있는 도시 탐색
    # for di, dj in [[-1, 0], [1, 0], [0, -1], [0, 1]]:
    #     ni, nj = i + di, j + dj
    #     # 범위 내에 있고, 방문하지 않았으며, 길이 있는 경우
    #     if 0 <= ni < N and 0 <= nj < N and visited[ni][nj] == 0 and arr[ni][nj] != 0:
    #         # 방문 표시
    #         visited[ni][nj] = 1
    #         # 비용 계산
    #         new_cost = cost + arr[ni][nj]
    #         # 다음 도시로 이동
    #         dfs(start, (ni, nj), visited, new_cost)
    #         # 방문 해제
    #         visited[ni][nj] = 0
    #

    # 시작 도시를 정해서 하나씩 탐색 하는 부분 들여쓰기 잘못해서 문제
    

