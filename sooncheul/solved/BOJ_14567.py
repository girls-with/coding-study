'''
상당부분 ai가 짠 코드입니다.
갈수록 자료구조를 배우며 만들어야 하는 빈리스트 및 변수가 많아짐에 따라
머리가 따라가기 벅차는 중인듯... 그래도 숙지는 된 듯 합니당
'''
n, m = map(int, input().split())

graph = [[] for _ in range(n + 1)]
indeg = [0] * (n + 1)

for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    indeg[b] += 1

ans = [0] * (n + 1)

# 리스트로 큐 구현
queue = []
front = 0

# 선수과목 없는 과목 → 1학기
for i in range(1, n + 1):
    if indeg[i] == 0:
        ans[i] = 1
        queue.append(i)

while front < len(queue):
    cur = queue[front]
    front += 1

    for nxt in graph[cur]:
        if ans[nxt] < ans[cur] + 1:
            ans[nxt] = ans[cur] + 1

        indeg[nxt] -= 1
        if indeg[nxt] == 0:
            queue.append(nxt)

print(*ans[1:])