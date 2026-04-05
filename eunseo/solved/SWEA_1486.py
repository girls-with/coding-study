# SWEA 1486 장훈이의 높은 선반
# [출력] 만들 수 있는 높이가 B 이상인 탑 중에서 탑의 높이와 B의 차이가 가장 작은 것 

# 점원들이 쌓는 탑은 점원 1명 이상으로 이루어져 있다
# 탑의 높이는 점원이 1명일 경우 그 점원의 키와 같고,
# 2명 이상일 경우 탑을 만든 모든 점원의 키의 합과 같다 
# 탑의 높이가 B 이상인 경우 선반 위의 물건을 사용할 수 있는데
# 탑의 높이가 높을수록 더 위험하므로 높이가 B 이상인 탑 중에서 높이가 가장 낮은 탑을 알아내려고 한다

# 이 문제가 부분집합으로 푸는 문제인 이유:
# 사람이 N명이면 각 사람은 선택 / 선택 안함 이렇게 2가지의 선택옵션이 주어짐 -> 2^N


T = int(input())

for tc in range(1, T + 1):
    # N: 사람 수 | B: 탑의 높이 
    N, B = map(int, input().split())

    # 각 점원의 키를 나타낸 배열
    arr = list(map(int, input().split()))

    # 차이가 가장 작은 값을 구하는 것이므로 최솟값 기본설정
    ans = float('inf')

    # 모든 부분집합
    for i in range(1 << N): # 부분집합의 개수 2^N개만큼 i 반복
        height = 0

        for j in range(N):
            if i & (1 << j):
                height += arr[j]

        # B 이상인 경우만 최소 차이 갱신
        if height >= B:
            # 차이: height - B 
            ans = min(ans, height - B)

    print(f'#{tc} {ans}') 


