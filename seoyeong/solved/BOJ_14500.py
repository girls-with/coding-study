n, m = map(int, input().split())

arr = [list(map(int, input().split())) for i in range(n)]
visited = [[False] * m for i in range(n)]
delta1 = [1, -1, 0, 0]
delta2 = [0, 0, 1, -1]

result = 0

def func(x, y, v, level):
    global result
    if level == 4:
        result = max(result, v)
        return

    for i in range(4):
        x1 = x + delta1[i]
        y1 = y + delta2[i]

        if 0 <= x1 < n and 0 <= y1 < m and not visited[x1][y1]:
            visited[x1][y1] = True
            func(x1, y1, v + arr[x1][y1], level+1)

            if level == 2:
                func(x, y, v + arr[x1][y1], level+1)

            visited[x1][y1] = False

for i in range(n):
    for j in range(m):
        visited[i][j] = True
        func(i, j, arr[i][j], 1)
        visited[i][j] = False

print(result)