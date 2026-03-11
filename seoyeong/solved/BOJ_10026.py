import sys
sys.setrecursionlimit(100000)

# n = int(input())
#
# arr = [list(map(str, input())) for i in range(n)]
#
# delta1 = [1, -1, 0, 0]
# delta2 = [0, 0, 1, -1]
#
# visited = [[False] * n for i in range(n)]
# used = [[False] * n for i in range(n)]
#
# result1 = 0
# result2 = 0
#
# def normal(x, y, color):
#     for i in range(4):
#         x1 = x + delta1[i]
#         y1 = y + delta2[i]
#
#         if 0 <= x1 < n and 0 <= y1 < n:
#             if not visited[x1][y1] and arr[x1][y1] == color:
#                 visited[x1][y1] = True
#                 normal(x1, y1, color)
#
# def non_normal(x, y, color):
#     for i in range(4):
#         x1 = x + delta1[i]
#         y1 = y + delta2[i]
#
#         if 0 <= x1 < n and 0 <= y1 < n:
#             if not used[x1][y1]:
#                 if color == 'R' or color == 'G':
#                     if arr[x1][y1] == 'R' or arr[x1][y1] == 'G':
#                         used[x1][y1] = True
#                         non_normal(x1, y1, color)
#                 else:
#                     if arr[x1][y1] == color:
#                         used[x1][y1] = True
#                         non_normal(x1, y1, color)
#
# for x in range(n):
#     for y in range(n):
#         if not visited[x][y]:
#             result1 += 1
#             normal(x, y, arr[x][y])
#
# for x in range(n):
#     for y in range(n):
#         if not used[x][y]:
#             result2 += 1
#             non_normal(x, y, arr[x][y])
#
# print(result1, result2)

from collections import deque

n = int(input())

arr = [list(map(str, input())) for i in range(n)]
delta1 = [1, -1, 0, 0]
delta2 = [0, 0, 1, -1]

result1 = 0
result2 = 0

que = deque([])

visited = [[False] * n for i in range(n)]
used = [[False] * n for i in range(n)]

for i in range(n):
    for j in range(n):
        if not visited[i][j]:
            result1 += 1
            que.append((i, j))

            while que:
                x, y = que.popleft()

                for k in range(4):
                    x1 = x + delta1[k]
                    y1 = y + delta2[k]

                    if 0 <= x1 < n and 0 <= y1 < n:
                        if not visited[x1][y1] and arr[x1][y1] == arr[x][y]:
                            visited[x1][y1] = True
                            que.append((x1, y1))

for i in range(n):
    for j in range(n):
        if not used[i][j]:
            result2 += 1
            que.append((i, j))

            while que:
                x, y = que.popleft()

                for k in range(4):
                    x1 = x + delta1[k]
                    y1 = y + delta2[k]

                    if 0 <= x1 < n and 0 <= y1 < n:
                        if not used[x1][y1]:
                            if arr[x][y] == 'R' or arr[x][y] == 'G':
                                if arr[x1][y1] == 'R' or arr[x1][y1] == 'G':
                                    used[x1][y1] = True
                                    que.append((x1, y1))

                            else:
                                if arr[x1][y1] == arr[x][y]:
                                    used[x1][y1] = True
                                    que.append((x1, y1))

print(result1, result2)
