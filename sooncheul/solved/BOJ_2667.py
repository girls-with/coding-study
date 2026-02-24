'''
방문 지도 만들고
매트릭스 만들고 다 순회하면서 1만나면, 방문했는지 판단하고,
함수 정의로 들어가고
    방문했다 표시하고 아파트 몇개로 이루어진 단지인지 카운팅
    카운트를 결과에 추가
결과 리스트 길이로 단지갯수 출력 하고,
오름차순으로 sort하여 출력ㅇㅇ
에드 다시
'''
N = int(input())
matrix = [list(map(int, input())) for _ in range(N)]
visited = [[0]*N for _ in range(N)]

def dfs(i, j):
    visited[i][j] = 1
    count = 1
    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]

    for idx in range(4):
        ni = i + dx[idx]
        nj = j + dy[idx]
        if 0 <= ni < N and 0 <= nj < N:
            if matrix[ni][nj] == 1 and visited[ni][nj] == 0:
                count += dfs(ni, nj)
    return count

result = []

for i in range(N):
    for j in range(N):
        if matrix[i][j] == 1 and not visited[i][j]:
            result.append(dfs(i, j))

result.sort()

print(len(result))
for r in result:
    print(r)