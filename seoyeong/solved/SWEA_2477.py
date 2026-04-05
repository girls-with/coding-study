from collections import deque
T = int(input())
 
for tc in range(1, T+1):
    n, m, k, a, b = map(int, input().split())
 
    A = list(map(int, input().split()))
    B = list(map(int, input().split()))
    t = list(map(int, input().split()))
    history = {} #고객들이 몇 번 창구, 몇 번 정비소를 이용했는지 저장할 딕셔너리
 
    result = 0
    complete = 0 #현재까지 처리된 고객의 수를 저장할 변수
    time = 0 
 
    for i in range(0, k):
        history[i+1] = [0, 0] #일단 딕셔너리 key에 고객번호 다 저장해놓기
     
 
    reception_q = deque() #창구 큐 생성
    repair_q = deque() #정비소 큐 생성
 
    reception_desk = [None] * n #창구 처리를 위한 변수 생성
    repair_desk = [None] * m #정비소 처리를 위한 변수 생성
 
    while complete < k: #현재까지 완료된 고객이 총 고객수와 같아지면 멈추게 됨
        for i in range(m):
            if repair_desk[i]: #만약 i번째 정비소가 비어있지 않다면
                repair_desk[i][0] -= 1 #i번째 정비소 시간을 -1
                if repair_desk[i][0] == 0: #만약 i번째 정비소의 시간이 0이라면
                    repair_desk[i] = None #처리가 완료된 것이므로 i번째 정비소는 다시 None으로 변경
                    complete += 1 #완료된 고객 수 1 증가
 
        finished_reception = [] #현재까지 처리가 완료된 창구의 번호를 저장할 리스트
 
        for i in range(n): 
            if reception_desk[i]: #만약 i번째 창구가 비어있지 않다면
                reception_desk[i][0] -= 1 #i번째 창구의 시간을 -1
                if reception_desk[i][0] == 0: #만약 i번째 창구의 시간이 0이라면
 
                    finished_reception.append([reception_desk[i][1], i+1]) #처리가 완료된 것이므로 i번째 창구를 이용한 고객 번호와 창구 번호를 append
                    reception_desk[i] = None #창구 비우기
 
        for cust_idx, desk_idx in finished_reception:
            repair_q.append([cust_idx, desk_idx]) #처리가 완료된 고객번호와 창구번호는 정비소 대기 큐에 append
 
        for i in range(m):
            if repair_desk[i] is None and repair_q: #만약 i번째 정비소가 비어있고 처리가 완료되어 정비소에 들어갈 수 있는 고객이 있다면
                cust_idx, desk_idx = repair_q.popleft()
                repair_desk[i] = [B[i], cust_idx] # i번째 정비소에서 걸리는 시간과 고객 번호를 넣기
                history[cust_idx][1] = i+1 #고객이 이용한 정비소 번호를 history에 저장
 
        for i in range(k):
            if t[i] == time: #만약 i번째 고객이 도착할 시간이 되었다면
                reception_q.append(i+1) #고객번호를 창구 대기 큐에 append
 
        for i in range(n):
            if reception_desk[i] is None and reception_q: #만약 i번째 창구가 비어있고 고객이 도착하여 창구에 들어갈 수 있는 고객이 있다면
                cust_idx = reception_q.popleft() 
                reception_desk[i] = [A[i], cust_idx] #i번째 창구에서 걸리는 시간과 고객 번호를 넣기
                history[cust_idx][0] = i+1 #고객이 이용한 창구 번호를 history에 저장
 
        time += 1 #시간 1 증가
 
    for idx in range(1, k+1):
        if history[idx][0] == a and history[idx][1] == b: #만약 idx번째 고객이 찾으려는 고객과 이용한 창구번호, 정비소 번호가 같다면
            result += idx #결과값에 고객번호 더하기
 
    if result == 0:
        print(f'#{tc} -1')
    else:
        print(f'#{tc} {result}')
