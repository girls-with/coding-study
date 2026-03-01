import sys
sys.setrecursionlimit(1000000)

n = int(input())
arr = [list(map(int, input().split())) for i in range(n)]
delta1 = [1, -1, 0, 0]
delta2 = [0, 0, 1, -1]

maximun = max(max(i) for i in arr)

max_v = 1

def func(x, y, i):
    
    for j in range(4):
        x1 = x + delta1[j]
        y1 = y + delta2[j]

        if 0 <= x1 < n and 0 <= y1 < n:
            if arr[x1][y1] > i and not visited[x1][y1]:
                visited[x1][y1] = True
                func(x1, y1, i)

for i in range(1, maximun):
    visited = [[False]*n for j in range(n)]
    v = 0
    for x in range(n):
        for y in range(n):
            if arr[x][y] > i and not visited[x][y]:
                visited[x][y] = True
                v += 1
                func(x, y, i)

    max_v = max(max_v, v)

print(max_v)
