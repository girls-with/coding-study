# 내가 생각했을 때 분명 로직에 잘못된 곳이 없는데
# 자꾸 다른 답이 나와서 AI 썼어용...
# 근데 ai가 알려준 것 : 
# 님 지금 x1 = x + i, y1 = x + i로 쓰고있음 ㅇㅇ
# 그리고 최솟값 구하는 건데 왜 max 쓰고 있는 것임??

# 내가... 제정신인 상태로 풀었더라면...
# 이딴 거지같은 실수를 안했을 것이며...
# AI를 쓸 일도 없었겠죠...


n, m = map(int, input().split())

arr = [input() for i in range(n)]

min_v = 64 # 64보다 클 순 없으므로 최솟값은 64로 설정



for x in range(0, n-8+1):
    for y in range(0, m-8+1):
        v = 0 # v값 초기화

        if x%2 == 0 and y%2 == 0: #첫 좌표가 x, y 둘 다 짝수면
            idx = 1 #인덱스 = 1
        else:
            idx = 0 #아니면 인덱스 = 0
        
        if idx == 1: 
            if arr[x][y] == 'W': #첫 좌표가 짝수고 그 값이 W면

                for i in range(0, 8):
                    for j in range(0, 8):   
                        x1 = x + i #x1, y1값 할당
                        y1 = y + j
                        if ((x1%2 == 0) and (y1%2 == 0)) or ((x1%2 == 1) and (y1%2 == 1)):
                            if arr[x1][y1] != 'W': #짝수 좌표엔 계속 같은 값이 나와야함. 아니면 v += 1
                                v += 1
                        else:
                            if arr[x1][y1] != 'B': #홀수 좌표엔 계속 다른 값이 나와야함. 아니면 v += 1
                                v += 1
            else: #첫 좌표가 짝수고 그 값이 B면
                    #아래는 동일
                for i in range(0, 8):
                    for j in range(0, 8):   
                        x1 = x + i
                        y1 = y+j
                        if ((x1%2 == 0) and (y1%2 == 0)) or ((x1%2 == 1) and (y1%2 == 1)):
                            if arr[x1][y1] != 'B':
                                v += 1
                        else:
                            if arr[x1][y1] != 'W':
                                v += 1

            min_v = min(v, min_v, 64-v) #min_v와 v값과 64-v값 중 최솟값 구하기

        else: #인덱스가 0일 때. 즉, x, y중 홀수가 하나라도 있을 떄
            #아래는 동일
            if arr[x][y] == 'W':

                for i in range(0, 8):
                    for j in range(0, 8):   
                        x1 = x + i
                        y1 = y + j
                        if ((x1%2 == 0) and (y1%2 == 0)) or ((x1%2 == 1) and (y1%2 == 1)):
                            if arr[x1][y1] != 'B':
                                v += 1
                        else:
                            if arr[x1][y1] != 'W':
                                v += 1
            else:

                for i in range(0, 8):
                    for j in range(0, 8):   
                        x1 = x + i
                        y1 = y + j
                        if ((x1%2 == 0) and (y1%2 == 0)) or ((x1%2 == 1) and (y1%2 == 1)):
                            if arr[x1][y1] != 'W':
                                v += 1
                        else:
                            if arr[x1][y1] != 'B':
                                v += 1

            min_v = min(v, min_v, 64-v)


print(min_v)


