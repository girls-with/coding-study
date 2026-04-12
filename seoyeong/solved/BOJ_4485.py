import heapq

t = 0
while True:
    n = int(input())
    t += 1

    if n == 0: #만약 입력받은 n의 값이 0이라면  break
        break

    arr = [list(map(int, input().split())) for i in range(n)]
    delta1 = [1, -1, 0, 0]
    delta2 = [0, 0, 1, -1]

    q = []
    visited = [[float('inf')]*n for i in range(n)] #최솟값을 관리할 리스트
    visited[0][0] = arr[0][0] #[0, 0]위치의 최솟값은 arr[0][0]과 같음
    heapq.heappush(q, (arr[0][0], 0, 0)) #다익스트라 이용

    while q:
        v, x, y = heapq.heappop(q)

        if x == n-1 and y == n-1: #도착하기만 하면 다익스트라는 최솟값을 보장하므로 break
            break

        for i in range(4):
            x1 = x + delta1[i]
            y1 = y + delta2[i]

            if 0 <= x1 < n and 0 <= y1 < n and visited[x1][y1] > v + arr[x1][y1]:
                visited[x1][y1] = v + arr[x1][y1] #만약 현재까지의 값이 이전까지의 최솟값보다 작다면 갱신
                heapq.heappush(q, (v+arr[x1][y1], x1, y1)) #그리고 q에 넣기

    print(f'Problem {t}: {visited[-1][-1]}')