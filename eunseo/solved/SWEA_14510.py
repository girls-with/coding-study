def can_finish(days, need1, need2):
    odd = (days + 1) // 2   # 홀수 날 개수 -> +1 가능
    even = days // 2        # 짝수 날 개수 -> +2 가능

    # +1이 꼭 필요한 횟수도 못 채우면 불가능
    if odd < need1:
        return False

    # need1 처리 후 남는 홀수 날 2개로 +2 하나를 대체 가능
    extra_odd = odd - need1
    replace_two = extra_odd // 2

    # 실제로 짝수 날에 처리해야 하는 +2 개수
    remain_need2 = max(0, need2 - replace_two)

    return even >= remain_need2


T = int(input()) 
for tc in range(1, T + 1):
    N = int(input())
    trees = list(map(int, input().split()))

    target = max(trees)

    need1 = 0  # 꼭 필요한 +1 횟수
    need2 = 0  # 기본적으로 필요한 +2 횟수

    for h in trees:
        diff = target - h
        need1 += diff % 2
        need2 += diff // 2

    # 이미 모두 같은 높이면 0일
    if need1 == 0 and need2 == 0:
        print(f"#{tc} 0")
        continue

    # 넉넉한 상한
    left, right = 0, 2 * (need1 + need2) + 1

    while left < right:
        mid = (left + right) // 2
        if can_finish(mid, need1, need2):
            right = mid
        else:
            left = mid + 1

    print(f"#{tc} {left}")