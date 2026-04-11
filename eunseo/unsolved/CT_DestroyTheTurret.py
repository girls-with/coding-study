from collections import deque

# 4방향 (우, 하, 좌, 상) → 레이저 우선순위 때문에 순서 중요!
dr4 = [0, 1, 0, -1]
dc4 = [1, 0, -1, 0]

# 8방향 (포탄 공격용)
dr8 = [-1, -1, -1, 0, 0, 1, 1, 1]
dc8 = [-1, 0, 1, -1, 1, -1, 0, 1]


def solve():
    N, M, K = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(N)]

    # 각 포탑의 "마지막 공격 시점" 저장
    last_attack = [[0] * M for _ in range(N)]

    # 살아있는 포탑 개수 세기
    def alive_count():
        cnt = 0
        for r in range(N):
            for c in range(M):
                if board[r][c] > 0:
                    cnt += 1
        return cnt

    # -------------------------------
    # 1. 공격자 선정
    # -------------------------------
    def choose_attacker():
        candidates = []

        for r in range(N):
            for c in range(M):
                if board[r][c] > 0:  # 살아있는 포탑만
                    candidates.append(
                        (
                            board[r][c],              # 공격력 (작을수록 좋음)
                            -last_attack[r][c],       # 최근 공격 (클수록 좋음 → -붙임)
                            -(r + c),                 # 행+열 (클수록 좋음 → -붙임)
                            -c,                       # 열 (클수록 좋음 → -붙임)
                            r, c
                        )
                    )

        candidates.sort()
        return candidates[0][4], candidates[0][5]

    # -------------------------------
    # 2. 공격 대상 선정
    # -------------------------------
    def choose_target(ar, ac):
        candidates = []

        for r in range(N):
            for c in range(M):
                if board[r][c] <= 0:
                    continue
                if r == ar and c == ac:
                    continue

                candidates.append(
                    (
                        -board[r][c],             # 공격력 (클수록 좋음 → -붙임)
                        last_attack[r][c],        # 오래된 공격 (작을수록 좋음)
                        (r + c),                  # 행+열 (작을수록 좋음)
                        c,                        # 열 (작을수록 좋음)
                        r, c
                    )
                )

        candidates.sort()
        return candidates[0][4], candidates[0][5]

    # -------------------------------
    # 레이저 경로 찾기 (BFS)
    # -------------------------------
    def bfs_laser_path(sr, sc, tr, tc):
        visited = [[False] * M for _ in range(N)]
        parent = [[None] * M for _ in range(N)]  # 경로 복원용

        q = deque()
        q.append((sr, sc))
        visited[sr][sc] = True

        while q:
            r, c = q.popleft()

            # 목표 도착 시 종료
            if r == tr and c == tc:
                break

            # ⭐ 우 → 하 → 좌 → 상 순서 (문제 조건 핵심!)
            for d in range(4):
                # ⭐ wrap 처리 (격자 끝 → 반대편)
                nr = (r + dr4[d]) % N
                nc = (c + dc4[d]) % M

                # ⭐ 부서진 포탑은 못 지나감
                if board[nr][nc] <= 0:
                    continue

                if visited[nr][nc]:
                    continue

                visited[nr][nc] = True
                parent[nr][nc] = (r, c)  # 이전 위치 저장
                q.append((nr, nc))

        # 목표 못 갔으면 레이저 불가능
        if not visited[tr][tc]:
            return None

        # ⭐ 경로 복원 (target → attacker 역추적)
        path = []
        cur = (tr, tc)

        while cur != (sr, sc):
            path.append(cur)
            cur = parent[cur[0]][cur[1]]

        path.append((sr, sc))
        path.reverse()  # attacker → target 순서로 변환

        return path

    # -------------------------------
    # 메인 시뮬레이션
    # -------------------------------
    for turn in range(1, K + 1):

        # 포탑 1개 남으면 종료
        if alive_count() <= 1:
            break

        # 1. 공격자 선정
        ar, ac = choose_attacker()

        # 핸디캡 적용
        board[ar][ac] += (N + M)

        # 공격 시점 기록
        last_attack[ar][ac] = turn

        # 2. 공격 대상 선정
        tr, tc = choose_target(ar, ac)

        # 공격에 관련된 포탑 기록
        involved = set()
        involved.add((ar, ac))

        power = board[ar][ac]

        # -------------------------------
        # 레이저 공격 시도
        # -------------------------------
        path = bfs_laser_path(ar, ac, tr, tc)

        if path is not None:
            # ⭐ 레이저 공격 성공

            # 공격 대상 → 전체 피해
            board[tr][tc] -= power
            involved.add((tr, tc))

            splash = power // 2

            # ⭐ 경로 중간 포탑 → 절반 피해
            # path = [attacker, ..., target]
            for i in range(1, len(path) - 1):  # attacker, target 제외
                r, c = path[i]
                board[r][c] -= splash
                involved.add((r, c))

        else:
            # -------------------------------
            # 포탄 공격
            # -------------------------------
            board[tr][tc] -= power
            involved.add((tr, tc))

            splash = power // 2

            for d in range(8):
                nr = (tr + dr8[d]) % N
                nc = (tc + dc8[d]) % M

                # 공격자는 피해 안 받음
                if nr == ar and nc == ac:
                    continue

                if board[nr][nc] <= 0:
                    continue

                board[nr][nc] -= splash
                involved.add((nr, nc))

        # -------------------------------
        # 3. 포탑 부서짐 처리
        # -------------------------------
        for r in range(N):
            for c in range(M):
                if board[r][c] < 0:
                    board[r][c] = 0

        # -------------------------------
        # 4. 포탑 정비
        # -------------------------------
        for r in range(N):
            for c in range(M):
                # 살아 있고, 공격과 무관한 포탑만 +1
                if board[r][c] > 0 and (r, c) not in involved:
                    board[r][c] += 1

    # 최종 최대 공격력 출력
    ans = 0
    for r in range(N):
        for c in range(M):
            ans = max(ans, board[r][c])

    print(ans)


solve()