# 정렬 + 완전 탐색
# 같은 크기의 당근은 같은 상자에 들어있어야 한다 = 정렬
# 구간 위치를 두개 정하기 소|중|대 
# 소: 0 ~ i
# 중: i+1 ~ j
# 대: j+1 ~ N-1
# 조건이 필요함: 빈 상자면 안되고 같은 크기를 다른 구간에 넣을 수 없음

T = int(input())
for tc in range(1, T + 1):
    N = int(input())
    arr = list(map(int, input().split()))

    arr.sort() # 오름차순 정렬 필수 !! 

    # 차이의 최솟값 설정
    min_diff = float('inf')

    # 구간 나누기
    for i in range(N-2):
        for j in range(i + 1, N-1):
            # 같은 크기의 당근이 다른 상자에 있으면 안됨
            if arr[i] == arr[i + 1]:
                continue
            if arr[j] == arr[j + 1]:
                continue

            # 각 상자에 들어가는 당근 개수
            sm = i + 1
            md = j - i
            lg = N - j - 1

            # 3상자 모두 비어있으면 무효
            if sm == 0 or md == 0 or lg == 0:
                continue
            
            # 최대 개수 - 최소 개수
            diff = max(sm, md, lg) - min(sm, md, lg)

            if min_diff > diff:
                min_diff = diff 

    # 끝까지 못 팢으면 -1
    if min_diff == float('inf'):
        min_diff = -1

    
    print(f'#{tc} {min_diff}') 