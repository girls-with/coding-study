#제시된 블럭들의 모양은 전구 level 4의 재귀로 만들 수 있는 모양임을 알아야 함!

n, m = map(int, input().split())

arr = [list(map(int, input().split())) for i in range(n)]
visited = [[False] * m for i in range(n)]
delta1 = [1, -1, 0, 0]
delta2 = [0, 0, 1, -1]

result = 0

def func(x, y, v, level):
    global result
    if level == 4: #만약 level이 4에 도달했을 시
        result = max(result, v) #result에 max값 할당
        return

    for i in range(4):
        x1 = x + delta1[i]
        y1 = y + delta2[i]

        if 0 <= x1 < n and 0 <= y1 < m and not visited[x1][y1]: #x1, y1이 범위 내에 있고 아직 방문하지 않았다면
            visited[x1][y1] = True #방문처리
            func(x1, y1, v + arr[x1][y1], level+1) #재귀

            if level == 2: #ㅗ, ㅓ 모양의 블럭은 level == 2일 때 원래의 x, y자리에서 재귀를 돌려야 생성 가능함
                func(x, y, v + arr[x1][y1], level+1) #재귀

            visited[x1][y1] = False #백트래킹(방문 처리 해제)

for i in range(n):
    for j in range(m):
        visited[i][j] = True #방문처리
        func(i, j, arr[i][j], 1) #각각의 좌표에 대해서 함수 돌리기
        visited[i][j] = False #백트래킹

print(result)