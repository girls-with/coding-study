# AI 사용 X

n, m = map(int, input().split()) # n, m값 입력받는 부분

result_arr = [0]*(n+1) # 결과를 저장할 행렬 생성
sub_arr = [[] for i in range(n+1)] # 선수과목 목록을 저장할 행렬 생성

for i in range(m): 
    a, b = map(int, input().split()) # m번 동안 a, b를 입력받아서
    sub_arr[b].append(a) # 선수과목 행렬 b번 순서에 a 어펜드.
    # 즉, b번 과목의 선수과목이 sub_arr[b]에 차곡차곡 쌓임(1번은 0임)

for i in range(1, n+1):
    if len(sub_arr[i]) == 0: # 만약 sub_arr[i]의 길이가 0이면 즉, 선수과목이 없다면
        result_arr[i] = 1 # result_arr[i]에 1 대입

for i in range(1, n+1):
    if result_arr[i] == 0: # result_arr[i]에 1이 대입되지 않고 여전히 초기값이면 즉, 선수과목이 있다면
        idx = 1 # 선수과목들을 전부 이수했는지 확인하기 위한 인덱스
        
        for j in range(0, len(sub_arr[i])):
            if result_arr[sub_arr[i][j]] == 0: # 선수과목들 중 하나라도 result_arr = 0의 값을 갖는다면
                idx = 0 # 인덱스 0으로 변경

        
        if idx == 1: # 만약 인덱스가 1이라면, 즉 선수과목들을 모두 이수했다면
            max_v = 0 # max_v 값 초기화
            for j in range(0, len(sub_arr[i])):
                max_v = max(max_v, result_arr[sub_arr[i][j]]) # 선수과목들 중 가장 늦게 이수하는 과목의 학기를 max_v에 저장

            result_arr[i] = max_v + 1 # 가장 늦게 이수하는 선수과목의 학기를 이수한 후에 과목을 들으므로 대입하는 값은 +1 해주기

print(*result_arr[1:])