from collections import deque

n, m = map(int, input().split())

arr = [list(map(int, input().split())) for i in range(n)]
delta1 = [1, -1, 0, 0]
delta2 = [0, 0, 1, -1]
blank = []
virus = []

max_v = 0
q = deque()

order = []
visited1 = [[False] * m for i in range(n)]

def func(level, start):
    global max_v
    if level == 3:
        v = 0

        visited = [row[:] for row in visited1]
        for vi in virus:
            visited[vi[0]][vi[1]] = True
            q.append((vi[0], vi[1]))

        for i in order:
            x, y = blank[i]
            arr[x][y] = 1

        while q:
            x, y = q.popleft()

            for i in range(4):
                x1 = x + delta1[i]
                y1 = y + delta2[i]

                if 0 <= x1 < n and 0 <= y1 < m:
                    if not visited[x1][y1] and arr[x1][y1] == 0:
                        v += 1
                        visited[x1][y1] = True
                        q.append((x1, y1))

        for i in order:
            x, y = blank[i]
            arr[x][y] = 0

        max_v = max(len(blank) - 3 - v, max_v)
        return

    for i in range(start, len(blank)):
        order.append(i)
        func(level+1, i+1)
        order.pop()

for i in range(n):
    for j in range(m):
        if arr[i][j] == 2:
            virus.append((i, j))
        elif arr[i][j] == 0:
            blank.append((i, j))

func(0, 0)

print(max_v)
