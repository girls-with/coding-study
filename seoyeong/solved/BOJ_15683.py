n, m = map(int, input().split())

arr = [list(map(int, input().split())) for i in range(n)]
delta_dict = {1: [[[1], [0]], [[-1], [0]], [[0], [-1]], [[0], [1]]],
              2: [[[1, -1], [0, 0]], [[0, 0], [1, -1]]],
              3: [[[-1, 0], [0, 1]], [[0, 1], [1, 0]], [[1, 0], [0, -1]], [[0, -1], [-1, 0]]],
              4: [[[-1, 0, 0], [0, 1, -1]], [[1, -1, 0], [0, 0, 1]],
                  [[1, 0, 0], [0, 1, -1]], [[1, -1, 0], [0, 0, -1]]],
              5: [[[1, -1, 0, 0], [0, 0, 1, -1]]]}

cctv = []

for x in range(n):
    for y in range(m):
        if arr[x][y] in [1, 2, 3, 4, 5]:
            cctv.append((x, y, arr[x][y]))

min_v = float('inf')

def func(idx, arr):
    global min_v
    if idx == len(cctv):
        v = 0
        for i in range(n):
            for j in range(m):
                if arr[i][j] == 0:
                    v += 1
        min_v = min(min_v, v)
        return


    x, y, num = cctv[idx][0], cctv[idx][1], cctv[idx][2]
    for delta in delta_dict[num]:
        office = [row[:] for row in arr]
        delta1, delta2 = delta[0], delta[1]
        for i in range(len(delta1)):
            j = 0
            while j < max(n, m):
                j += 1
                x1 = x + delta1[i] * j
                y1 = y + delta2[i] * j

                if 0 <= x1 < n and 0 <= y1 < m:
                    if office[x1][y1] == 6:
                        break
                    elif office[x1][y1] == 0:
                        office[x1][y1] = 9
                else:
                    break

        func(idx+1, office)

func(0, arr)
print(min_v)
