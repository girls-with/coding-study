# AI사용했습니다! 함수 정의할 때 num 계산하는 과정에서 조금 도움 받았어용

n = int(input())
arr = [list(map(int, input())) for i in range(n)]

delta1 = [1, -1, 0, 0]
delta2 = [0, 0, 1, -1]

cnt = []


def func(x, y):
    num = 1 # 1이 발견된 순간부터 함수는 한번만 돌아가니 num = 1로 설정

    for i in range(4):

        x1 = x + delta1[i] # x1, y1 계산
        y1 = y + delta2[i]

        if 0 <= x1 < n and 0 <= y1 < n: # x1, y1이 범위 내에 있고
            if arr[x1][y1] == 1: # arr[x1][y1] == 1이면
                arr[x1][y1] = 0 # 같은 범위 재탐색을 방지하기 위해 arr[x1][y1] = 0으로 바꾸고
                num += func(x1, y1) # 재귀 돌리기. 돌 때마다 num에 값이 쌓임
    return num # return값은 최종 num

        
for x in range(n):
    for y in range(n):
        if arr[x][y] == 1: # arr[x][y] = 1이면
            arr[x][y] = 0 # arr[x][y] = 0으로 바꾸고
            cnt.append(func(x, y)) # num 값을 저장할 cnt 리스트에 어펜드

cnt.sort() # cnt 리스트 정렬

print(len(cnt)) # 구역의 개수는 cnt의 길이와 같음!
for i in cnt:
    print(i) #cnt 내 원소를 하나씩 출력

