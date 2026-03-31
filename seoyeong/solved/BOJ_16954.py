#ai 썼어용! visited를 초에 따라 다르게 관리해야 한다는 사실을 망각하고
#계속 2차원 배열로 썼더니 테케에서 틀리더라구요...ㅠ
#ai의 도움으로 visited 배열 고치고 패스했습니다!

from collections import deque

arr = [list(map(str, input())) for i in range(8)]
delta1 = [1, -1, 0, 0, 1, 1, -1, -1, 0]
delta2 = [0, 0, 1, -1, 1, -1, 1, -1, 0]
visited = set() #ai 사용한 부분. 초 별로 관리하기 위해서 set 사용
result = 0 #기본값은 0


q = deque()
q.append((7, 0, 0)) #시작점 append
visited.add((7, 0, 0))

while q:
    x, y, t = q.popleft()

    if x == 0 and y == 7: #만약 끝점에 도달했다면
        result = 1 #result를 1로 바꾸고 break
        break

    
    for i in range(9):
        x1 = x + delta1[i]
        y1 = y + delta2[i]

        if 0 <= x1 < 8 and 0 <= y1 < 8 and (x1, y1, t+1) not in visited:
        #만약 x1, y1이 범위내에 있고 같은 시간초 내에서 아직 방문하지 않았다면
            idx = True
            if 0 <= x1-t < 8:
                if arr[x1-t][y1] != '.':
                #t초가 지난 기준 현재 좌표로부터 x-t좌표에 벽이 있으면 안되므로(내가 이동하려는 곳에 벽이 있으면 안되므로)
                    idx = False #만약 x-t 좌표에 벽이 있다면 idx = False
            if 0 <= x1-(t+1) < 8:
                if arr[x1-(t+1)][y1] != '.':
                #그리고 t초가 지난 기준 현재 좌표로부터 x-t+1좌표에도 벽이 있으면 안됨.
                #바로 다음에 벽이 내려와서 어차피 이동이 불가해지기 때문
                    idx = False
            
            if idx: #조건을 만족했을시에만 방문체크, q에 append
                visited.add((x1, y1, t+1))
                q.append((x1, y1, t+1))


print(result)

