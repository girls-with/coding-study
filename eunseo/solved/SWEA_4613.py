# 새롭게 칠할 칸의 최소 칸 수 출력

T = int(input())
for tc in range(1, T + 1):
    N, M = map(int, input().split())
    arr = [list(input()) for _ in range(N)]

    # 새로 칠해야하는 최소 칸 수 cnt 
    min_cnt = float('inf')

    # # 방문 배열 생성해놓고 여기에 1 표시된 칸수를 구하면 됨
    # visited = [[0] * M for _ in range(N)]

    # w: 하얀색 줄, b: 파란색 줄 r: 빨간색 줄
    for w in range(1, N-1):
        for b in range(1, N-w):
            r = N-w-b
            # 빨간색 줄이 1보다 작아지면 pass 하라
            if r < 1:
                continue 

            # 방문 배열 생성해놓고 여기에 1 표시된 칸수를 구하면 됨
            visited = [[0] * M for _ in range(N)]
            
            for i in range(w):
                for j in range(M):
                    if arr[i][j] != 'W':
                        visited[i][j] = 1

            for i in range(w, w+b):
                for j in range(M):
                    if arr[i][j] != 'B':
                        visited[i][j] = 1
            
            for i in range(w+b, N):
                for j in range(M):
                    if arr[i][j] != 'R':
                        visited[i][j] = 1

            
            # 칸 수 세기
            cnt = 0
            for i in range(N):
                for j in range(M):
                    if visited[i][j] == 1:
                        cnt += 1

            min_cnt = min(min_cnt, cnt)

    
    print(f'#{tc} {min_cnt}')
