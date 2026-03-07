'''
높이만큼 연산을 돌립시다
bfs를 for 구문 안에 넣어서 하면 될 듯
1 - 100까지는 좀 너무하고
리스트 만들고 배열 탐색하면서 숫자들을 넣고 낫인으로
리스트 만큼 반복으로 하는걸로
'''

N = int(input())
matrix = [list(map(int, input().split())) for _ in range(N)]

lst = [0]
for i in range(N):
    for j in range(N):
        if matrix[i][j] not in lst:
            lst.append(matrix[i][j])

di = [-1, 1, 0, 0]
dj = [0, 0, -1, 1]

total = 0

for num in lst:
    visited = [[0] * N for _ in range(N)]
    cnt = 0
    for i in range(N):
        for j in range(N):
            q = []
            front = -1
            if matrix[i][j] > num and visited[i][j] == 0:
                q.append([i, j])
                visited[i][j] = 1
                cnt += 1

                while True:
                    if front == len(q) - 1:
                        break

                    front += 1
                    idx = q[front][0]
                    jdx = q[front][1]

                    for dx in range(4):
                        ni = idx + di[dx]
                        nj = jdx + dj[dx]
                        if 0 <= ni < N and 0 <= nj < N:
                            if matrix[ni][nj] > num and visited[ni][nj] == 0:
                                q.append([ni, nj])
                                visited[ni][nj] = 1

    if total < cnt:
        total = cnt

print(total)