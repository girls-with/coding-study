#변수 입력
arr = [list(map(int, input())) for i in range(4)]
k = int(input())

for _ in range(k):
    num, idx = map(int, input().split())
    num -= 1 #톱니바퀴 번호는 1부터 시작이지만 파이썬은 0부터 시작이므로 -1
    turn = [0] * 4 #회전 방향을 저장할 리스트
    turn[num] = idx #맨 처음 입력받은 번호와 회전 방향 입력

    for i in range(1, 4): #입력받은 바퀴 기준 오른쪽 먼저 확인
        if 0 <= num + i < 4: #만약 확인하려는 바퀴의 번호가 범위를 벗어나지 않는다면
            if arr[num+i-1][2] != arr[num+i][-2]: #그리고 맞닿아있는 자석이 다르다면
                turn[num+i] = -turn[num+i-1] #회전 리스트에 '-전 바퀴의 회전방향' 저장 
            else: #맞닿아있는 자석이 같으면 break
                break
    
    for i in range(1, 4): #입력받은 바퀴 기준 왼쪽 확인
        if 0 <= num - i < 4:
            if arr[num-i+1][-2] != arr[num-i][2]: 
                turn[num-i] = -turn[num-i+1] 
            else:
                break
    
    for i in range(4): #회전 for 문
        if turn[i] == 1: #만약 시계방향이면
            arr[i].insert(0, arr[i].pop()) #가장 끝에 있는 자석을 빼서 맨 앞으로 붙이기
        elif turn[i] == -1: #만약 반시계방향이면
            arr[i].append(arr[i].pop(0)) #맨 앞에 있는 자석을 빼서 맨 끝으로 붙이기

result = 0
score = 1
for i in arr: #점수 계산
    if i[0] == 1:
        result += score
    score *= 2

print(result)