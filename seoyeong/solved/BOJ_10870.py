# n입력받기

n = int(input())

# 함수 정의
## 피보나치 수열은 F(0) = 0으로 시작하는 경우도 있지만 F(1) = 1로 시작하는 경우도 존재하기에
## 코드의 간결성을 위해 F(1) = 1부터 계산하는 함수로 작성

def fibonacci(n):
    if n == 1 or n == 2:
        return 1
    else:
        return fibonacci(n-1) + fibonacci(n-2)
    
print(fibonacci(n))