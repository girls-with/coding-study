'''
외웠다.
이해 못했다. 하지만 주석 달아두겠다.
이상
일단 인풋을 받고
코어를 모두 찾아서 코어에 저장(가에 있는 코어들은 넣지 않는다)
함수 dfs정의
    코어 한개 한개 연산하면서
    모두 연산할 경우 연결한 코어가 최대라면
        최대 코어 갱신, 조건에 맞추어 최소길이 갱신
    코어의 i, j로
    di, dj를 만들어서 4방향으로
        ni, nj를 만들고 해당 범위가 유효범위라면
        += di, += dj 를 해 주면서 path에 추가 해 줌
        혹시 중간에 길이 아닌 다른 코어나 다른 줄을 만난다면
        path를 비우고 반환

    혹시 path가 비지 않았다면,
        path에서 i, j를 가지고 줄을 표시하고
            다음 dfs로 줄 연결
        그리고, 줄을 다시 지움

    현제의 코어를 무시하고 다음 코어를 연산하기 위해 dfs에 idx + 1만 하여 한번 더 호출

    프린트
    -> 슈도 코드임 함수 정의 할때마다 위로 올라가서 해석해야 할거임
    일단 내가 작성하는 순서는 저렇단 거고, 이해 40프로에 외움 90프로 정도
'''


di = [-1, 1, 0, 0]
dj = [0, 0, -1, 1]
def set_line(path, value):
    '''
    선 연결하는 함수
    :param path: 선 연결 자리 i, j의 모음
    :param value: 선 = 2, 지우기 = 0
    :return: 바뀐 보드 return값 딱히 없음
    '''
    for i, j in path:
        board[i][j] = value
    return

def dfs(idx, connected, length):
    '''
    코드의 핵심 dfs연산
    :param idx: 처리된!!( 연결 된 X ) 코어의 수
    :param connected: 연결 된 코어의 수
    :param length: 총 보드에 표기된 줄의 크기
    :return: 리턴값은 없음, global의 max_connected, min_len 갱신
    '''
    global max_connected, min_len
    if connected + (len(cores) - idx) < max_connected:
        # 이거 가지치기임 처리할 코어랑 연결된 코어가 이미 최고값 연결된 코어 수보다
        # 낮으면 더 연결할 가치가 없는 연산이므로 바로 리턴
        return

    if idx == len(cores):
        if max_connected < connected:
            max_connected = connected
            min_len = length
        elif max_connected == connected and min_len > length:
            min_len = length
        return

    i, j = cores[idx]

    for d in range(4):
        ni = i + di[d]
        nj = j + dj[d]
        path = []
        while 0 <= ni < N and 0 <= nj < N:
            if board[ni][nj] != 0:
                path = []
                break

            path.append((ni, nj))
            ni += di[d]
            nj += dj[d]

        if path:
            # peth 가 있다면 선 연결, 모든 경우 연산, 선 지우기
            set_line(path, 2)
            dfs(idx + 1, connected + 1, length + len(path))
            set_line(path, 0)


    dfs(idx + 1, connected, length)
    # 현제의 코어를 무시하고 배제하고 다음 코어로 연산해보기

T = int(inpt())
for test_case in range(1, T + 1):
    N = int(input())
    board = [list(map(int, input().split())) for _ in range(N)]

    cores = []

    for i in range(1, N - 1):
        for j in range(1, N - 1):
            if board[i][j] == 1:
                cores.append((i, j))

    max_connected = 0
    min_len = N * N + 1

    dfs(0, 0, 0)

    print(f"#{test_case} {min_len}")