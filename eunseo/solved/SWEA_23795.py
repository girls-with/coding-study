T = int(input())
for tc in range(1, T+1):
    N = int(input())

    arr = [list(map(int, input().split())) for _ in range(N)]

    # 델타 방향 탐색: 상하좌우 
    di = [-1, 1, 0, 0]
    dj = [0, 0, -1, 1]

    # 괴물 위치 찾기
    for i in range(N):
        for j in range(N):
            if arr[i][j] == 2:
                mon_i = i
                mon_j = j
    
    # 괴물 위치에서 시작해야 함
    for d in range(4):
        ni = mon_i + di[d]
        nj = mon_j + dj[d]

        while 0 <= ni < N and 0 <= nj < N and arr[ni][nj] != 1:

            if arr[ni][nj] == 0:
                arr[ni][nj] = -1 
            
            ni += di[d]
            nj += dj[d]

    # 안전 칸 세기
    safe_cnt = 0
    for i in range(N):
        for j in range(N):
            if arr[i][j] == 0:
                safe_cnt += 1
    
    print(f'#{tc} {safe_cnt}')