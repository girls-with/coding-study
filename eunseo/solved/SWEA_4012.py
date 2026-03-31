T = int(input())

for tc in range(1, T + 1):
    N = int(input())
    arr = [list(map(int, input().split())) for _ in range(N)]

    ans = float('inf')

    # 부분집합 (N개의 재료를 반으로 나누기)
    for i in range(1 << N):
        team1 = []
        team2 = []

        for j in range(N):
            if i & (1 << j):
                team1.append(j)

            else:
                team2.append(j)

        # 두 팀 크기가 같을 때만
        if len(team1) != N // 2:
            continue 

        # 시너지 계산 함수
        def get_score(team):
            score = 0

            for x in team:
                for y in team:
                    if x != y:
                        score += arr[x][y]

            return score 
        
        diff = abs(get_score(team1) - get_score(team2))
        ans = min(ans, diff)


    print(f"#{tc} {ans}") 