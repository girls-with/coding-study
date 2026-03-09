'''
오늘 재귀에 살짝 자신감 생겼다가 이거때문에 좌절함
'''

def dfs(idx, start, visited, value):
    global min_v

    if value > min_v:
        return
    if idx == N-1:
        value += matrix[start][0]
        if min_v > value:
            min_v = value
        return

    for next_trip in range(N):
        if visited[next_trip] == 0:
            visited[next_trip] = 1
            dfs(idx + 1, next_trip, visited, value + matrix[start][next_trip])
            visited[next_trip] = 0

N = int(input())
matrix = [list(map(int, input().split())) for _ in range(N)]

visited = [0] * N
visited[0] = 1

min_v = float('inf')

dfs(0, 0, visited, 0)

print(min_v)