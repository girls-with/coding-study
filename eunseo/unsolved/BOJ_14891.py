# 4개의 톱니바퀴 상태 입력
gears = [list(input().strip()) for _ in range(4)]

# 회전 횟수
K = int(input())

# 회전 정보 입력
for _ in range(K):
    num, direction = map(int, input().split())
    
    # 각 톱니바퀴가 회전할 방향 저장
    # 처음에는 다 0(안 도는 상태)
    rotate = [0] * 4
    
    # 입력받은 톱니바퀴는 일단 회전
    rotate[num - 1] = direction

