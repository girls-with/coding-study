# SWEA 22653 차윤이의 RC카 (배틀싸피 easy stage 대비)
# 다익스트라 아님, 단순 시뮬레이션 문제 
 
# 목적지에 도달할 수 있으면 1, 없으면 0 출력 
 
# G: 이동 가능한 통로
# T: 이동 불가능한 벽
# X: 출발 
# Y: 도착 
# A: 앞으로 이동
# L: 현재 바라보고 있는 방향에서 왼쪽으로 90도 회전
# R: 현재 바라보고 있는 방향에서 오른쪽으로 90도 회전 
 
# 차윤이는 RC카를 항상 위를 바라보는 방향으로부터 조종을 시작한다
# 차윤이가 RC카를 조종한 커멘드가 주어졌을 때, 목적지에 도달할 수 있는지 구하라
# 커맨드 종료 시에 목적지에 위치해 있어야 한다.
 
# 시작점 찾기
def find_start(N):
    for i in range(N):
        for j in range(N):
            if field[i][j] == 'X':
                return i, j 
 
def get_road(si, sj, command):
    # 상 우 하 좌
    dirs = [(-1, 0), (0, 1), (1, 0), (0, -1)]
     
    # 처음은 위
    d = 0
 
    i, j = si, sj 
 
    for cmd in command:
        if cmd == "L":
            d = (d - 1) % 4 
 
        elif cmd == "R":
            d = (d + 1) % 4
 
        elif cmd == "A":
            ni, nj = i + dirs[d][0], j + dirs[d][1] 
 
            # 이동 가능할 때만 이동 
            if 0 <= ni < N and 0 <= nj < N and field[ni][nj] != "T":
                i, j = ni, nj 
 
    # 마지막 위치가 Y인지 확인
    if field[i][j] == "Y":
        return 1 
     
    return 0 
 
T = int(input())
for tc in range(1, T + 1):
    N = int(input())
    field = [list(input().strip()) for _ in range(N)]
 
    Q = int(input())
     
    sti, stj = find_start(N)
 
    results = []  # 결과 저장
 
    for _ in range(Q):
        length, command = input().split()
        length = int(length)
 
        ans = get_road(sti, stj, command)
        results.append(str(ans))  # 문자열로 저장
 
    print(f"#{tc} {' '.join(results)}")