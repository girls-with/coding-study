r, c = map(int, input().split())
arr = [list(map(str, input())) for i in range(r)]
delta1 = [1, -1, 0, 0]
delta2 = [0, 0, 1, -1]

alphabet = [False] * 26 #알파벳 사용 여부를 관리할 리스트
result = 1 #기본 결과값은 1

def func(x, y, cnt): 
    global result
    if cnt > result: #만약 현재까지 방문한 칸의 수가 result보다 크다면
        result = cnt #result에 cnt값 할당

    for i in range(4):
        x1 = x + delta1[i]
        y1 = y + delta2[i]

        if 0 <= x1 < r and 0 <= y1 < c: 
            if not alphabet[ord(arr[x1][y1])-65]: #만약 x1, y1이 범위 내에 있고 아직 사용하지 않은 알파벳이라면
                alphabet[ord(arr[x1][y1])-65] = True #알파벳 사용처리[ord(A)라면 65, ord(B) = 66... 식으로 ord 함수에 알파벳을 넣으면 정수가 되어 나옵니다]
                func(x1, y1, cnt+1) #재귀
                alphabet[ord(arr[x1][y1])-65] = False #백트래킹(사용처리 해제)
   

alphabet[ord(arr[0][0])-65] = True #맨 왼쪽 위 알파벳은 미리 사용처리
func(0, 0, 1) #맨 왼쪽 위에서 함수 시작

print(result)