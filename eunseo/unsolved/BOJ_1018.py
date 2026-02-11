N, M = map(int, input().split())
arr = [[list(input()) for _ in range(N)]]

# 다시 칠해야하는 정사각형의 최소 개수
color = 0 # 개수는 0 이상 양수이므로 최소 0으로 기본 설정
min_sum = float('inf')

for i in range(N):
    for j in range(M):
        # 맨 왼쪽 위 칸이 흰색인 경우
        if arr[0][0] == 'W':
            # 교차하는 것이 같다면
            if arr[i][j] == arr[i+1][j+1]:
                # 칠하기
                color += 1
                
        # 맨 왼쪽 위 칸이 검은색인 경우
        if arr[0][0] == 'B':
            # 교차하는 둘이 같은 색이라면
            if arr[i][j] == arr[i+1][j+1]:
                # 칠하기
                color += 1

if min_sum > color:
    min_sum = color

print(min_sum) 