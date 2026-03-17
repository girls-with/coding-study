from collections import deque

n, m = map(int, input().split())

move = {}

cnt = [float('inf')] * 101
q = deque()
q.append((1, 0))
cnt[1] = 0

for i in range(n):
    a, b = map(int, input().split())
    move[a] = b

for i in range(m):
    a, b = map(int, input().split())
    move[a] = b

while q:
    x, v = q.popleft()

    for i in range(1, 7):
        x1 = x + i

        if x1 <= 100:
            if x1 in move.keys():
                if v+1 < cnt[move[x1]]:
                    cnt[move[x1]] = v+1
                    q.append((move[x1], v+1))
            else:
                if v+1 < cnt[x1]:
                    cnt[x1] = v+1
                    q.append((x1, v+1))

print(cnt[100])
