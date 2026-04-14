t = int(input())
 
for tc in range(1, t+1):
    n = int(input())
 
    arr = [list(map(int, input().split())) for i in range(n)]
    delta1 = [1, 1, -1, -1] #마름모가 만들어지기 위해서는 오른쪽 아래 -> 왼쪽 아래 -> 왼쪽 위 -> 오른쪽 위 방향
    delta2 = [1, -1, -1, 1] #혹은 왼쪽 아래 -> 오른쪽 아래 -> 오른쪽 위 -> 왼쪽 위로 이동해야 함. 지금 선택한 것은 전자.

    result = -1
    visited = [False] * 101
 
 
    def func(x, y, eat, level):
        global result
 
        nx, ny = x + delta1[level], y + delta2[level] #다음 이동 지점 계산
 
        if 0 <= nx < n and 0 <= ny < n:
            if level == 3: #만약 level이 3. 즉 오른쪽 위 방향이라면
                if start_x == nx and start_y == y + 1: #그리고 첫 시작점과 다음 이동할 지점이 같다면? -> 마름모 완성
                    result = max(result, eat) #result 갱신
                    return
 
            if not visited[arr[nx][ny]]: #만약 다음 이동 지점을 방문하지 않았다면
                visited[arr[nx][ny]] = True #방문 처리
                func(nx, ny, eat+1, level) #같은 방향으로 dfs 돌리기
 
                if level < 3: #level이 3 미만일 때만 방향 전환 가능
                    func(nx, ny, eat+1, level+1) # 다음 방향으로 전환해서 dfs 돌리기
                visited[arr[nx][ny]] = False #백트래킹
 
 
    for i in range(n):
        for j in range(n):
            start_x, start_y = i, j
            visited[arr[i][j]] = True
            func(i, j, 1, 0) #각 지점에 대해서 함수 호출
            visited[arr[i][j]] = False
 
    print(f'#{tc} {result}')