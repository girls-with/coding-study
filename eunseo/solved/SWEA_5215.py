T = int(input())
for tc in range(1, T + 1):
    # 재료의 수 N
    # 제한 칼로리 L 
    N, L = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(N)]
    
    ans = 0 

    # 모든 부분집합 (2^N)
    for i in range(1 << N):
        taste = 0 # 맛 점수 합
        cal = 0 # 칼로리 합 

        for j in range(N):
            if i & (1 << j): # j번째 재료 선택
                taste += arr[j][0]
                cal += arr[j][1]

        # 칼로리 제한 만족 시 최대값 갱신 
        if cal <= L:
            ans = max(ans, taste)

    print(f"#{tc} {ans}") 

