from collections import deque

m,n = map(int,input().split())

arr = [list(map(int, input().split())) for i in range(n)]
delta1 = [1, -1, 0, 0]
delta2 = [0, 0, 1, -1]
que = deque([])

for x in range(n):
    for y in range(m):
        if arr[x][y] == 1:
            que.append((x, y))

while que:
    v = que.popleft()

    x, y = v[0], v[1]

    for i in range(4):
        x1 = x + delta1[i]
        y1 = y + delta2[i]
        if 0 <= x1 < n and 0 <= y1 < m:
            if arr[x1][y1] == 0:
                arr[x1][y1] = arr[x][y]+1
                que.append((x1, y1))

idx = True
for x in range(n):
    for y in range(m):
        if arr[x][y] == 0:
            idx = False

if not idx:
    print(-1)
else:
    max_v = max(max(i) for i in arr) -1
    print(max_v)