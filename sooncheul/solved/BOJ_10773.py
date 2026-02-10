'''
1 <= K <= 100,000
정수는 0 ~ 1,000,000
0일경우
가장 최근에 쓴 수를 지우고, 아닐경우 해당 수를 쓴다!
0일 경우 지울 수 있음을 보장!

0을 외치면 top 감소하면서 스택에서 pop
값이 들어오면 top 증가하면서 스택[top]에 값 저장
마무리까지 하고 나서 top + 1만큼 for문을 써서 스택의 총 합 출력
'''
K = int(input())
arr = [int(input()) for _ in range(K)]

steck = [0] * K   # K 만큼 넉넉한 스택 만들기
top = -1   # top의 초기 설정

for i in arr:
    if i == 0:   # 만약 0을 외치면
        top -= 1   # top을 하나 감소하여 더미데이터로 버림
        continue

    top += 1    # 만약 0이 아니면 top을 증가하고
    steck[top] = i    # 스택[top]에 저장

max_v = 0    # 다 더한 값 넣을 바구니

for idx in range(top + 1):
    max_v += steck[idx]   # 스택의 top까지의 모든 값을 더함

print(max_v)