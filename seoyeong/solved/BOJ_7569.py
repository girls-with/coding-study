from collections import deque

m,n, h = map(int,input().split())

arr = [list(list(map(int, input().split())) for i in range(n))for j in range(h)]
delta1 = [1, -1, 0, 0]
delta2 = [0, 0, 1, -1]
delta3 = [1, -1]
que = deque([])

for i in range(h):
    for j in range(n):
        for k in range(m):
            if arr[i][j][k] == 1:
                que.append((i, j, k))

while que:
    v = que.popleft()
    hh, x, y = v[0], v[1], v[2]
    for i in range(2):
        h1 = hh + delta3[i]
        if 0 <= h1 < h and arr[h1][x][y] == 0:
            arr[h1][x][y] = arr[hh][x][y] + 1
            que.append((h1, x, y))
    for i in range(4):
        x1 = x + delta1[i]
        y1 = y + delta2[i]
        if 0 <= x1 < n and 0 <= y1 < m:
            if arr[hh][x1][y1] == 0:
                arr[hh][x1][y1] = arr[hh][x][y] + 1
                que.append((hh, x1, y1))

idx = True
for i in range(h):
    for j in range(n):
        for k in range(m):
            if arr[i][j][k] == 0:
                idx = False

if not idx:
    print(-1)
else:
    max_v = max(max(max(row) for row in page) for page in arr)
    print(max_v-1)
