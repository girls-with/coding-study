'''
N장 중에 3장을 골라야 함!!
N^3의 시간 복잡도로 풀 것이냐 가 문제다
첫번째 카드랑 2번째 카드랑 3번째 카드의 모든 경우를 조합해서
M보다 작은 수들을 골라서 리스트로 추가
'''
N, M = map(int, input().split())
cards = list(map(int, input().split()))

value = []
triple_sum = 0
for first in range(N-2):
    for second in range(first+1, N-1):
        for third in range(second+1, N):
            triple_sum += cards[first] + cards[second] + cards[third]
            if triple_sum <= M:
                value.append(triple_sum)
            triple_sum = 0
value.sort()
print(value[-1])