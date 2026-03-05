# 벽: 1, 0: 통로, 2: 술래
# NxN 행렬
# 먼저 술래의 위치를 찾고 해당 위치에서 방향 탐색
# 방향 탐색하기 위한 조건이 필요 (3가지 조건)
# 술래의 위치에서 인접한 정점이 범위에 벗어나면 탐색 중단
# 술래의 위치에서 인접한 정점이 벽을 만나면 중단
# 술래의 위치에서 인접한 정점이 통로면 (== 0) 그건 감시를 피할 수 없는 통로이기 때문에 다른 숫자 설정 하여 감시를 피할 수 없는 칸으로 설정
# 이동할 수 있는 칸의 개수와 감시를 피할 수 있는 칸의 개수 설정 c, cnt


T = int(input())
for tc in range(1, T + 1):
    N = int(input())
    arr = [list(map(int, input().split())) for _ in range(N)]

    # 술래 위치 찾기
    for i in range(N):
        for j in range(N):
            if arr[i][j] == 2:

                # 술래 위치에서 방향 탐색
                for di, dj in [[-1, 0], [1, 0], [0, -1], [0, 1]]:

                    # 이동할 수 있는 칸의 개수 설정
                    c = 1
                    while True:
                        ni, nj = i + di * c, j + dj * c
                        # 2차원 배열 안에 있지 않으면 탐색 중단
                        if not (0 <= ni < N and 0 <= nj < N):
                            break

                        # 벽을 만나면 탐색 중단
                        if arr[ni][nj] == 1:
                            break

                        # 통로면 다른 숫자로 치환해서 감시를 피할 수 없는 칸 수 설정
                        # 감시를 피할 수 없는 칸을 9로 설정
                        # 9로 설정한 이유는 0, 1, 2만 아니면 되어서 그럼. 9가 아닌 다른 숫자여도 무방
                        # 여기서는 9로 설정하기로 함
                        if arr[ni][nj] == 0:
                            arr[ni][nj] = 9

                        c += 1

    # 감시를 피할 수 있는 안전 칸 수 cnt
    cnt = 0

    for i in range(N):
        for j in range(N):
            # 배열의 원소가 1, 2, 9가 아니면
            # 즉, 통로면
            if arr[i][j] == 0:
                # 그 정점은 안전하므로 칸 수 1씩 증가
                cnt += 1

    print(f'#{tc} {cnt}')

