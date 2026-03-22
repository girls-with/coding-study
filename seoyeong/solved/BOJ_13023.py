#bfs인 줄 알았는데 dfs였어요 샤갈!!!!!!!!!!!!
n, m = map(int, input().split())

arr = [[] for i in range(n)] #관계를 입력받을 arr생성
visited = [False] * n 
result = 0 #기본 결과값은 0

for i in range(m):
    a, b = map(int, input().split())
    arr[a].append(b) # 숫자 2개 입력받아서 arr에 각각 할당
    arr[b].append(a)

def dfs(v, depth):
    global result
    if depth == n-1: #만약 전부 연결되어있다는게 확인됐으면
        result = 1 #결과를 1로 변경
        return
    
    for i in arr[v]: #v와 연결되어 있는 친구 중
        if not visited[i]: #아직 방문하지 않은 친구가 있다면
            visited[i] = True #방문 처리
            dfs(i, depth+1) #dfs 돌리기
            visited[i] = False #백트래킹

for i in range(n): #각각의 친구에 대해
    visited[i] = True #방문처리
    dfs(i, 0) #dfs돌리기
    visited[i] = False #백트래킹

print(result)
