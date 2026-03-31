r, c = map(int, input().split())
arr = [list(map(str, input())) for i in range(r)]
delta1 = [1, -1, 0, 0]
delta2 = [0, 0, 1, -1]

alphabet = [False] * 26
result = 1

def func(x, y, cnt):
    global result
    if cnt > result:
        result = cnt

    for i in range(4):
        x1 = x + delta1[i]
        y1 = y + delta2[i]

        if 0 <= x1 < r and 0 <= y1 < c:
            if not alphabet[ord(arr[x1][y1])-65]:
                alphabet[ord(arr[x1][y1])-65] = True
                func(x1, y1, cnt+1)
                alphabet[ord(arr[x1][y1])-65] = False
   

alphabet[ord(arr[0][0])-65] = True
func(0, 0, 1)

print(result)