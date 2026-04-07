#자꾸 알 수 없는 인덱스 에러가 나서 그거 잡는다고 ai 썼어용
#인덱스 에러 원인은 t = 1일 때 치즈가 다 녹는 경우에 cnt에 들어간 값이 없는데 계속 cnt[-1]로 값을 빼내려고 해서...
#생각보다 너무 간단한 에러라서 힘이 빠졌지만 그래도 빨리 고쳤습니당

from collections import deque

n, m = map(int ,input().split())
arr = [list(map(int, input().split())) for i in range(n)]
delta1 = [1, -1, 0, 0]
delta2 = [0, 0, 1, -1]

q = deque()
t = 0 
cnt = [] #녹은 치즈의 양을 시간별로 저장할 리스트

v = 0 
for i in range(n):
    for j in range(m):
        if arr[i][j] == 1: #원본 데이터의 치즈 양을 구해서
            v += 1

cnt.append(v) #cnt에 append(이러면 t=1일 때 while문이 종료되어도 cnt[-1]이 가능)

if v != 0: #v = 0일때는 볼 필요가 없으니 v!=0일 때만 실행
    while True:
        t += 1

        q.append((0, 0))
        visited = [[False] * m for i in range(n)]
        visited[0][0] = True

        while q: #bfs 돌리기
            x, y = q.popleft()

            for i in range(4): #델타 탐색
                x1 = x + delta1[i]
                y1 = y + delta2[i]

                if 0 <= x1 < n and 0 <= y1 < m and not visited[x1][y1]:
                    visited[x1][y1] = True
                    if arr[x1][y1] == 1: #치즈라면
                        arr[x1][y1] = 0 #값을 변경하지만 q에 넣지는 않음
                    else: #빈칸이라면
                        q.append((x1, y1)) #q에 넣기

        v = 0
        for i in range(n):
            for j in range(m):
                if arr[i][j] == 1: #치즈가 녹은 후에 남아있는 치즈의 개수 구하기
                    v += 1

        if v == 0: #만약 치즈가 다 녹았다면 브레이크
            break
        else: #치즈가 남았다면 현재까지 남은 양을 cnt에 append
            cnt.append(v)

print(t)
print(cnt[-1])