T = int(input())

for tc in range(1, T + 1):
    N, p = map(int, input().split())
    A = list(map(int, input().split()))
    B = list(map(int, input().split()))

    dp0 = A[0]  # 1번 비료
    dp1 = B[0]  # 2번 비료

    for i in range(1, N):
        new_dp0 = max(
            dp0 + A[i] - p,  # 같은 비료 → 현재에서 패널티
            dp1 + A[i]
        )
        new_dp1 = max(
            dp1 + B[i] - p,
            dp0 + B[i]
        )

        dp0, dp1 = new_dp0, new_dp1

    ans = max(dp0, dp1)
    print(f"#{tc} {ans}")