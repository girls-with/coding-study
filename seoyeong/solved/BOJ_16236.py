#ai 썼어용...! 로직을 어떻게 짜야할지 대략적인 그림은 그려졌는데 구현이 어려워서
#ai한테 어떤 순서로 구현을 해야할지 아이디어(코드X)만 받았습니다

from collections import deque
n = int(input())

arr = [list(map(int, input().split())) for i in range(n)]
delta1 = [1, -1, 0, 0]
delta2 = [0, 0, 1, -1]


q = deque()
x, y, size = 0, 0, 2 #x, y, size 기본값 설정

for i in range(n):
    for j in range(n):
        if arr[i][j] == 9: #상어 위치 발견했을 때
            x, y = i, j #x, y를 상어 위치로 바꾸고
            arr[i][j] = 0 #원래의 상어위치는 0으로 변경

def bfs(x, y, size): #먹을 수 있는 물고기를 찾아서 저장하는 함수
    q.append((x, y, size, 0)) #q에 현재 상어의 x, y좌표와 크기, 거리를 저장
    visited[x][y] = True 
    while q:
        cur_x, cur_y, size, t = q.popleft()

        for i in range(4):
            x1 = cur_x + delta1[i]
            y1 = cur_y + delta2[i]

            #이동한 좌표가 범위내에 있고, 사이즈보다 이하, 아직 미방문이라면
            if 0 <= x1 < n and 0 <= y1 < n and arr[x1][y1] <= size and not visited[x1][y1]:
                visited[x1][y1] = True #방문처리
                q.append((x1, y1, size, t+1)) #q에 저장(1초 이동했으니 t+1로 저장)
                if 0 < arr[x1][y1] < size: #만약에 먹을 수 있는 물고기면
                    fish.append((t+1, x1, y1)) #fish에 저장
    return

v = 0 #크기가 커지기까지 먹은 물고기의 수를 저장할 변수
time = 0 #총 걸린 시간을 나타낼 변수
while True:
    visited = [[False]*n for i in range(n)]
    fish = [] #먹을 수 있는 물고기를 저장할 행렬
    #(먹기 위해 이동하는 시간, 물고기의 x, y좌표 순서로 저장)

    bfs(x, y, size) #먹을 수 있는 물고기 찾기

    if not fish: #만약 먹을 수 있는 물고기가 없다면
        break #while문 탈출(엄마 상어 부르기)

    fish.sort() #fish 정렬. (시간 -> 행 -> 열 순서로 sort됨)
    d, target_x, target_y = fish[0]

    v += 1 #물고기 먹었으니 값 1 오름
    time += d #먹기까지 걸린 시간을 time에 누적
    if v == size: #만약 크기만큼 물고기를 먹었으면
        size += 1 #사이즈는 1커지고
        v = 0 #v는 0으로 초기화

    x, y = target_x, target_y #x, y값은 먹은 물고기의 위치로 수정
    arr[x][y] = 0 #먹은 물고기의 자리는 0으로 변경

print(time)
