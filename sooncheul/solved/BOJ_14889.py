
def dfs(idx, depth):
    global answer

    if depth == N // 2:
        start_team = []
        link_team = []

        for i in range(N):
            if selected[i]:
                start_team.append(i)
            else:
                link_team.append(i)

        start_sum = 0
        link_sum = 0

        for i in range(N // 2):
            for j in range(i + 1, N // 2):
                a, b = start_team[i], start_team[j]
                start_sum += board[a][b] + board[b][a]

                c, d = link_team[i], link_team[j]
                link_sum += board[c][d] + board[d][c]

        answer = min(answer, abs(start_sum - link_sum))
        return

    for i in range(idx, N):
        if not selected[i]:
            selected[i] = True
            dfs(i + 1, depth + 1)
            selected[i] = False


N = int(input())
board = [list(map(int, input().split())) for _ in range(N)]

selected = [False] * N
answer = float('inf')

dfs(0, 0)

print(answer)