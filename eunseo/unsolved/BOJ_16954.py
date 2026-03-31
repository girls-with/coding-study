board = [list(input()) for _ in range(8)]

# 9방향 (제자리 포함)
dirs = [(-1,-1), (-1,0), (-1,1),
        (0,-1),  (0,0),  (0,1),
        (1,-1),  (1,0),  (1,1)]

def can_go():
    queue = [(7, 0, 0)]  # (행, 열, 시간)
    front = 0  # popleft 대신 사용할 인덱스

    while front < len(queue):
        r, c, t = queue[front]
        front += 1

        # 목표 도착
        if (r, c) == (0, 7):
            return 1

        # 8초 이후는 벽이 모두 사라짐 → 무조건 가능
        if t >= 8:
            return 1

        for dr, dc in dirs:
            nr, nc = r + dr, c + dc
            nt = t + 1

            if 0 <= nr < 8 and 0 <= nc < 8:
                # 현재 시간에 벽이 있는 위치인지
                if nr - t >= 0 and board[nr - t][nc] == '#':
                    continue

                # 다음 시간에 벽이 내려오는 위치인지
                if nr - t - 1 >= 0 and board[nr - t - 1][nc] == '#':
                    continue

                # 이동 가능하면 큐에 추가
                queue.append((nr, nc, nt))

    return 0

print(can_go())