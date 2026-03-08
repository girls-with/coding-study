'''
외웠다.
이해 못했다. 하지만 주석 달아두겠다.
이상
'''


di = [-1, 1, 0, 0]
dj = [0, 0, -1, 1]
def set_line(path, value):
    for i, j in path:
        board[i][j] = value

def dfs(idx, connected, length):
    global max_connected, min_len
    if connected + (len(cores) - idx) < max_connected:
        return

    if idx == len(cores):
        if max_connected < connected:
            max_connected = connected
            min_len = length
        elif max_connected == connected and min_len > length:
            min_len = length
        return

    i, j = cores[idx]

    for d in range(4):
        ni = i + di[d]
        nj = j + dj[d]
        path = []
        while 0 <= ni < N and 0 <= nj < N:
            if board[ni][nj] != 0:
                path = []
                break

            path.append((ni, nj))
            ni += di[d]
            nj += dj[d]

        if path:
            set_line(path, 2)
            dfs(idx + 1, connected + 1, length + len(path))
            set_line(path, 0)


    dfs(idx + 1, connected, length)

T = int(input())
for test_case in range(1, T + 1):
    N = int(input())
    board = [list(map(int, input().split())) for _ in range(N)]

    cores = []

    for i in range(1, N - 1):
        for j in range(1, N - 1):
            if board[i][j] == 1:
                cores.append((i, j))

    max_connected = 0
    min_len = N * N + 1

    dfs(0, 0, 0)

    print(f"#{test_case} {min_len}")