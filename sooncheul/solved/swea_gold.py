T = int(input())
for test_case in range(1, T+1):
    N = int(input())
    matrix = [list(map(int, input().split())) for _ in range(N)]
    visited = [[0] * N for _ in range(N)]

    # 델타 데이터 상하좌우
    di = [-1, 1, 0, 0]
    dj = [0, 0, -1, 1]

    # 맥스 골드랑 구역을 저장할 그릇
    total = 0
    max_rng = 0

    # 광산을 순회하면서 금광을 찾으면 그 자리에서 bfs를 사용해 모든 영역을 탐색하고 카운트
    # 금광에서 금의 생산량이 같다면, 금광의 크기가 작은것을 가지고 출력하도록 수정
    for i in range(N):
        for j in range(N):
            if matrix[i][j] != 0 and visited[i][j] == 0:
                q = []
                front = -1
                q.append([i, j])
                # 시작점을 큐에 담아서 bfs로 탐색

                rng = 0
                gold = 0

                while True:
                    # print(q)
                    if front == len(q) - 1:
                        break
                    front += 1
                    idx = q[front][0]
                    jdx = q[front][1]
                    gold += matrix[idx][jdx]
                    rng += 1
                    visited[idx][jdx] = 1

                    for dx in range(4):
                        ni = idx + di[dx]
                        nj = jdx + dj[dx]

                        if 0 <= ni < N and 0 <= nj < N:
                            if matrix[ni][nj] != 0 and visited[ni][nj] == 0:
                                visited[ni][nj] = 1
                                q.append([ni, nj])

                if total == gold:
                    # 골드 생산량이 같을 경우 범위가 작은 것으로 교체
                    if max_rng > rng:
                        max_rng = rng

                if total < gold:
                    total = gold
                    max_rng = rng

    # for i in range(N):
        # print(visited[i], "   ", matrix[i])

    print(f"#{test_case} {total} {max_rng}")