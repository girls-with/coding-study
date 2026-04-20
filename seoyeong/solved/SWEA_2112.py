T = int(input())
 
for tc in range(1, T+1):
    d, w, k = map(int, input().split())
 
    arr = [list(map(int, input().split())) for i in range(d)]
 
    result = k #최대 k번을 바꾸면 조건을 만족하므로 기본 result는 k로 설정
 
    def change(arr, i, ans): #arr의 값을 바꾸는 함수
        for j in range(w):
            arr[i][j] = ans #i번 행에 대해서 정해진 값으로 바꿈
 
    def check(arr): #조건을 만족하는지 판별하는 함수
        if k == 1: #만약 k가 1이라면 굳이 확인할 필요가 없이 가능하므로 바로 True를 리턴
            return True
        result = 0
        for j in range(w): #한 열에 대해서
            possible = 1
            for i in range(1, d): #행 검사
                if arr[i][j] == arr[i-1][j]: #만약 현재 행의 값과 전의 행의 값이 같다면
                    possible += 1 #possible 1 증가
                    if possible >= k: #만약 possible이 k보다 크거나 같아진다면
                        result += 1 #result에 1증가시키고 break
                        break
                else: #만약 현재 행의 값과 전의 행의 값이 같지 않다면
                    possible = 1 #possible을 다시 1로 초기화
        if result == w: #만약 result랑 w의 값이 같다면 모든 열이 조건을 만족하는 것이므로 True리턴
            return True
        else: #아니면 False 리턴
            return False
 
    def dfs(arr, row, cnt):
        global result
        if cnt >= result: #가지치기
            return
 
        if row == d: #만약 검사하는 행이 맨 끝까지 내려왔다면?
            if check(arr): #check함수 돌려서 조건 만족하는지 확인
                result = min(result, cnt) #조건 만족할 떄만 result 갱신
 
            return
 
        temp = arr[row][:] #바꿔질 행을 미리 저장
        dfs(arr, row + 1, cnt) #값을 바꾸지 않고 dfs 돌리기
        change(arr, row, 0) #해당 행을 0으로 바꾸기
        dfs(arr, row+1, cnt+1) #행을 0으로 바꾼 상태에서 dfs
        change(arr, row, 1) #해당 행을 1로 바꾸기
        dfs(arr, row+1, cnt+1) #해당 행을 1로 바꾼 상태에서 dfs
        arr[row] = temp #백트래킹
 
    if check(arr): #만약 입력받을 때부터 조건을 만족하는 arr이 들어왔다면
        print(f'#{tc} {0}') #0 출력
    else: #아니라면
        dfs(arr, 0, 0) #dfs 돌려서 result 갱신
        print(f'#{tc} {result}') #result 출력