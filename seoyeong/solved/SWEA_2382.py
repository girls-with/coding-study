T = int(input())
 
for tc in range(1, T+1):
    n, m, k = map(int, input().split())
 
    delta1 = {1 : -1, 2:1, 3:0, 4:0}
    delta2 = {1:0, 2:0, 3:-1, 4:1}
    #1 : 상, 2: 하, 3: 좌, 4: 우
    bacteria = [] #미생물 좌표와 양, 방향을 입력받을 리스트
 
    for i in range(k):
        x, y, v, d = map(int, input().split())
        bacteria.append([x, y, v, d]) #미생물 정보 입력
 
    for _ in range(m):
        new_bacteria = {}
        for x, y, v, d in bacteria: #리스트에 저장된 미생물 정보 하나씩 꺼내오기
            x1 = x + delta1[d]
            y1 = y + delta2[d]
 
            if x1 == 0 or x1 == n-1 or y1 == 0 or y1 == n-1: #만약 격자 끝에 닿았다면
                v //= 2 #미생물 양 감소
                if d == 1: #방향 전환
                    d = 2
                elif d == 2:
                    d = 1
                elif d == 3:
                    d = 4
                else:
                    d = 3
 
            if (x1, y1) not in new_bacteria.keys(): #이전에 같은 좌표에 도달한 다른 미생물이 없다면?
                new_bacteria[(x1, y1)] = [v, v, d] #새로운 박테리아를 저장할 딕셔너리에 key, value 생성
            else: #이미 도달한 다른 미생물이 있다면?
                new_bacteria[(x1, y1)][0] += v #원래 있던 미생물 양에 v 누적
                if new_bacteria[(x1, y1)][1] < v: #만약 현재까지의 최대 미생물 양보다 v가 더 크다면?
                    new_bacteria[(x1, y1)][2] = d #v의 방향으로 방향 바꾸기
                    new_bacteria[(x1, y1)][1] = v #최댓값 갱신
 
        bacteria = [] #리스트 비우기
        for (x, y), [max_v, v, d] in new_bacteria.items():
            bacteria.append([x, y, max_v, d]) #비운 리스트에 새로운 박테리아 정보 넣기
 
    result = 0
    for x, y, v, d in bacteria:
        result += v
 
    print(f'#{tc} {result}')