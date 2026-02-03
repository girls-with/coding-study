N, M = map(int, input().split())
arr = list(map(int, input().split()))

best_sum = 0 # 합의 최댓값 중 M에 근접한 값을 0으로 초기화함
for i in range(1, N-2): # i는 1부터 N-3까지
    for j in range(i+1, N-1): # j는 i+1부터 N-2까지
        for k in range(j+1, N): # k는 j+1(i+2)부터 N-1까지 
            sum = arr[i] + arr[j] + arr[k]
            if sum <= M: # sum 이 M보다 작거나 같으면
                best_sum = max(best_sum, sum) # best_sum에 sum을 재할당하고,
                                              # 재할당된 best_sum과 sum 중 최댓값을 다시 할당하기(반복)
print(best_sum)



