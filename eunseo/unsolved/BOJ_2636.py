from collections import deque

N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs():
    visited = [[False] * M for _ in range(N)]
    q = deque()
    q.append((0, 0))
    visited[0][0] = True

    melt = []

    while q:
        x, y = q.popleft()

        for d in range(4):
            nx = x + dx[d]
            ny = y + dy[d]

            if 0 <= nx < N and 0 <= ny < M and not visited[nx][ny]:
                visited[nx][ny] = True

                if board[nx][ny] == 0:
                    q.append((nx, ny))
                else:
                    melt.append((nx, ny))  # 치즈면 녹일 대상

    return melt


time = 0
last = 0

while True:
    melt_list = bfs()

    if not melt_list:
        break

    last = len(melt_list)

    for x, y in melt_list:
        board[x][y] = 0

    time += 1

print(time)
print(last)