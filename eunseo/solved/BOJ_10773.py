K = int(input())

# 스택을 저장할 배열: 최대길이 K
stack = [0] * K
# 스택이 비어 있음
top = -1
# 최종 합: k는 양의 정수이기 때문에 최종합을 0으로 설정 가능
total = 0

# K개의 숫자를 하나씩 처리 
for _ in range(K):
    c = int(input())
    # 정수가 0일 경우에는
    if c == 0:
        # 최종합에서 현재 top에 있는 값만큼 빼고
        total -= stack[top]

        # 스택에서 제거한다
        top -= 1

    # 정수가 0이 아닐 경우에는
    else:
        # 가장 최근에 쓴 수를 삽입하고
        top += 1
        stack[top] = c 
        # 최종합에 그 수만큼 합한다.
        total += c 

# 결론 도출! 
print(total) 




