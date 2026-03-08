# N명 중에서 N/2 명을 뽑아서 두 팀으로 구성 -> nC2 (조합문제)
# 각 팀의 능력치 합 계산
# 그 차이의 최솟값 출력 

N = int(input())
S = [list(map(int, input().split())) for _ in range(N)]

# 능력치의 최소값을 구해야함
ab_min = float('inf') 

# 방문 리스트를 만들고 방문한건 스타트팀, 안한건 링크팀으로 표시
# 1이면 스타트, 0이면 링크
visited = [0] * N 

# idx: 다음에 볼 사람 번호, cnt: 현재 스타트팀 인원 수 
def dfs(idx, cnt):
    global ab_min

    # 스타트팀 인원을 다 뽑은 경우
    if cnt == (N // 2):
        start = 0
        link = 0

        # 각 팀의 능력치 계산
        for i in range(N):
            for j in range(i + 1, N): # S[i][i] 는 0이니까 볼 필요 X
                # i, j 둘 다 스타트 팀이면, 
                if visited[i] == 1 and visited[j] == 1:
                    start += (S[i][j] + S[j][i])
                
                # i, j 둘 다 링크 팀이면,
                elif visited[i] == 0 and visited[j] == 0:
                    link += (S[i][j] + S[j][i])
        
        # 각 팀의 차이 설정
        diff = abs(start - link)

        if ab_min > diff:
            ab_min = diff 

        return # for i 

    
    # idx번 사람부터 한 명씩 스타트 팀에 넣어보기
    for i in range(idx, N):
        if visited[i] == 0:
            visited[i] = 1 # i번 사람 스타트팀에 넣기
            dfs(i + 1, cnt + 1) # 다음 사람 보기
            visited[i] = 0 # 다시 원상복구

# 0번 사람은 무조건 스타트팀에 넣어서 중복 제거
visited[0] = 1
dfs(1, 1)

print(ab_min)



# start = 0
# link = 0

# # 팀에 더해지는 능력치는 Sij + Sji
# for i in range(N):
#     for j in range(N):
#         start = S[i][j] + S[j][i] -> 여기서 start와 link를 어떻게 나눠서 서로 더할지 몰라서 ai 씀
# 변수에 += 하는게 익숙해질 필요 있는듯