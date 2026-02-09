N = int(input())
a = [0, 1] # 초기값 설정
for i in range(2, N+1): 
        a.append(a[i-1] + a[i-2]) # 피보나치는 2부터 시작하므로 범위는 2~N

<<<<<<< HEAD
print(a[N]) # N번째 원소 구하기
=======
print(a[N]) # N번째 원소 구하기
>>>>>>> 58e950c90895e22477c9791fbe5894c1992f033e
