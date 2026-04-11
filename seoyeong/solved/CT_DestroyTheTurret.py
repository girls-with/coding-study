#AI 썼습니다... 레이저 공격 시 경로 찾아가는 로직이랑 자꾸 오답입니다 나오길래 뭐가 잘못된건지 몰라서 찾는데에 AI 사용햇서용

from collections import deque
n, m, k = map(int, input().split())

arr = [list(map(int, input().split())) for i in range(n)]
delta1 = [0, 1, 0, -1]
delta2 = [1, 0, -1, 0]

for x in range(n):
    for y in range(m):
        arr[x][y] = [arr[x][y], 0] #arr에 공격력, 최근 공격 시간을 저장하기 위해 arr[x][y] 값을 배열로 수정

for _ in range(1, k+1):
    turret = [] #현재까지 살아있는 터렛을 저장할 리스트
    active = [[False]*m for i in range(n)] #살아있으면서, 공격에 무관한지 무관하지 않은지 판단할 리스트
    for x in range(n):
        for y in range(m):
            if arr[x][y][0] > 0: #만약 살아있는 터렛이라면
                turret.append((arr[x][y][0], -arr[x][y][1], -(x+y), -y, x)) #터렛에 append. sort시 자동으로 정렬되게 하기 위해 - 이용
                active[x][y] = True #일단 살아있고, 공격에 무관하다(마지막에 +1을 한다)고 가정

    if len(turret) == 1: #만약 살아있는 터렛이 1개라면 break
        break

    turret.sort() #배열 sort -> 자동으로 공격 터렛은 0번에, 방어 터렛은 마지막으로 정렬됨
    turret = deque(turret)

    attack = turret.popleft()
    defence = turret.pop()

    attack_x, attack_y = attack[-1], -attack[-2]
    defence_x, defence_y = defence[-1], -defence[-2]
    attack_v = attack[0] + n + m #공격할 값
    arr[attack_x][attack_y][0] = attack_v #공격하는 터렛의 공격력에 n+m을 해주어야 하므로 값 변경
    arr[attack_x][attack_y][1] = _ #공격 시간 저장

    active[attack_x][attack_y] = False #공격터렛과 방어 터렛은 공격에 무관하지 않으므로 False로 변경
    active[defence_x][defence_y] = False

    q = deque() #레이저 공격을 판단할 큐
    visited = [[False] * m for i in range(n)]
    parent = [[None]*m for i in range(n)] #경로 역추적을 위한 리스트 생성
    found = False #일단 레이저 공격이 불가하다고 가정

    q.append((attack_x, attack_y))
    visited[attack_x][attack_y] = True

    while q:
        x, y = q.popleft()

        if x == defence_x and y == defence_y: #만약 경로 끝까지 올 수 있는 길이 있다면
            arr[defence_x][defence_y][0] -= attack_v #방어 터렛 공격

            curr_x, curr_y = parent[defence_x][defence_y] #역트래킹 시작

            while (curr_x, curr_y) != (attack_x, attack_y): #경로가 attack_x, attack_y에 도착할 때까지
                arr[curr_x][curr_y][0] -= attack_v//2 #공격력//2만큼 감소
                active[curr_x][curr_y] = False #공격에 무관하지 않으므로 False로 변경

                curr_x, curr_y = parent[curr_x][curr_y]
            
            found = True #레이저 공격이 가능하므로 True로 변경
            break

        for i in range(4):
            x1 = x + delta1[i]
            y1 = y + delta2[i]

            if not (0 <= x1 < n and 0 <= y1 < m): #격자 밖으로 빠져나가면 반대편으로 도착하므로
                if x1 < 0: #x1, y1이 격자 밖으로 빠져나갔을 때의 값을 할당
                    x1 = n-1
                elif x1 == n:
                    x1 = 0
                if y1 < 0:
                    y1 = m-1
                elif y1 == m:
                    y1 = 0
            
            if not visited[x1][y1] and arr[x1][y1][0] > 0: #아직 방문하지 않았고, 살아있는 터렛이라면
                visited[x1][y1] = True #방문처리
                parent[x1][y1] = (x, y) #x, y의 부모(경로)를 저장
                q.append((x1, y1))

    if not found: #만약 레이저 공격이 불가할 시, 포탄 공격
        delta11 = [1, -1, 0, 0, 1, 1, -1, -1]
        delta22 = [0, 0, 1, -1, 1, -1, 1, -1]

        for i in range(8): #주변 8칸 탐색
            x1 = defence_x + delta11[i]
            y1 = defence_y + delta22[i]

            if not (0 <= x1 < n and 0 <= y1 < m):
                if x1 < 0:
                    x1 = n-1
                elif x1 == n:
                    x1 = 0
                if y1 < 0:
                    y1 = m-1
                elif y1 == m:
                    y1 = 0
            
            if arr[x1][y1][0] > 0 and (x1, y1) != (attack_x, attack_y): #만약 살아있는 터렛이고, 공격 터렛이 아니라면
                arr[x1][y1][0] -= attack_v//2 #공격력//2만큼 감소
                active[x1][y1] = False #공격과 무관하지 않으므로 False로 변경

        arr[defence_x][defence_y][0] -= attack_v #방어 터렛은 공격터렛의 공격력만큼 감소

    for x in range(n):
        for y in range(m):
            if active[x][y] and arr[x][y][0] > 0: #만약 공격과 무관하고, 살아있는 터렛이라면
                arr[x][y][0] += 1 #공격력 1 증가

result = 0

for x in range(n):
    for y in range(m):
        result = max(result, arr[x][y][0]) #가장 높은 터렛의 공격력 찾기

print(result)