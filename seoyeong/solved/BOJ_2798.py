# n, m 입력받기
# arr 생성
n, m = map(int, input().split())
arr = list(map(int, input().split()))

# 추후에 값을 저장할 p와 p_arr 생성
p = []
p_arr = []

# 세 원소의 합으로 나올 수 있는 모든 경우의 수를 p에 저장
for i in range(0, n):
    for j in range(0, n):
        for k in range(0, n):
            if i!= j and i!=k and j!=k:
                p.append(arr[i]+arr[j]+arr[k])

# p에 있는 원소들 중 m보다 작거나 같은 값들을 다시 추출하여 p_arr에 저장
for i in range(0, len(p)):
    if p[i] <= m:
        p_arr.append(p[i])

# p_arr 중 가장 큰 값을 프린트
print(max(p_arr))