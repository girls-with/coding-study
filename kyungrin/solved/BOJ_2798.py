# 1. 입력값 받기
N, M = map(int, input().split())
arr = list(map(int, input().split()))

# 2. M과 가까우면서, 넘지 않는 정수 3의 합 구하기
# 2-1. 모든 경우의 수를 생성하여 리스트에 저장한다. 또한, 여기서 순서는 의미없다.
    # 최악의 경우이더라도 100*99*98는 300만을 넘지 못하므로 Brute하게 풀이 가능하다. 
    # 또한, 리스트의 요소를 리스트로 설정하여 3번의 처리를 쉽게 한다.
result_list = []
for i in range(N-2): # 0~N-2
    for j in range(i+1, N-1): # i~N-1
        for k in range(j+1, N): # j~N
            sum_v = arr[i] + arr[j] + arr[k]
            # M보다 큰 합은 리스트에 저장하지 않는다.
            if sum_v <= M:
                result_list.append([sum_v, 0])
            
# 3. M-SUM(3요소)의 값을 result_list의 요소 1번 째에 추가한다.
for i in range(len(result_list)):
    result_list[i][1] = M - result_list[i][0]

# 4. result_list를 순회하며, 차이가 가장 적은 값을 찾는다.
# !! 두 가지로 분리 했으면서 하나로 했다. 주의!!! !! [요소, 차이]
result = 0
diff = 3000000
for i in range(len(result_list)):
    if diff > result_list[i][1] :
        diff = result_list[i][1]
        result = result_list[i][0]

# 5. 결과 출력하기
print(result)