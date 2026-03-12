# 단어 N개가 주어지는데 그 중에서 접두어 관계가 없는 단어들만 골라서 최대한
# 많이 골라라
    
N = int(input())
words = [input() for _ in range(N)]

# 정렬 시작하고
words.sort()

ans = 0

# 마지막 전 단어까지 검사
for i in range(N - 1):
    now = words[i]
    nxt = words[i + 1]

    # now가 nxt의 접두어인지 확인
    if len(now) <= len(nxt) and now == nxt[:len(now)]:
        continue

    ans += 1

# 마지막 단어는 항상 고를 수 있음
ans += 1

print(ans)
