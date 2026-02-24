t = int(input())

for tc in range(1, t+1):
    n = int(input())
    arr = [list(map(int, input().split())) for i in range(n)]

    dict = {}
    cnt = 0

    for x in range(n):
        for y in range(n):
            if arr[x][y] < 0:
                dict[arr[x][y]] = (x, y)
                cnt += 1
            elif arr[x][y] > 0:
                dict[arr[x][y]] = (x, y)

    new_arr = []
    idx_arr = []

    def func(level, cnt):
        if level == cnt * 2:
            idx_arr.append(new_arr[:])
            return
        for i in range(-cnt, cnt+1):
            if i != 0 and i not in new_arr:
                new_arr.append(i)
                func(level+1, cnt)
                new_arr.pop()

    func(0, cnt)

    lst = []

    for i in idx_arr:
        idx = 0
        for j in range(1, cnt+1):
            if i.index(j) > i.index(-j):
                idx = 1

        if idx == 0:
            lst.append(i)

    
    min_v = float('inf')

    for i in lst:
        x = 0
        y = 0
        v = 0
        for j in i:
            v += abs(x - dict[j][0]) + abs(y - dict[j][1])
            x = dict[j][0]
            y = dict[j][1]

        min_v = min(min_v, v)

    print(f'#{tc} {min_v}')






