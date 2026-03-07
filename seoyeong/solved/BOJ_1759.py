l, c = map(int, input().split())
txt = list(map(str, input().split()))
txt.sort()

arr = []
pass_arr = []

def func(level, idx):
    if level == l:
        a = ''
        for i in arr:
            a += i
        pass_arr.append(a)

    for i in range(idx, c):
        arr.append(txt[i])
        func(level + 1, i+1)
        arr.pop()

func(0, 0)

idx_arr = []

for i in pass_arr:
    a = 0
    b = 0
    for j in i:
        if j in ['a', 'e', 'o', 'i', 'u']:
            a += 1
        else:
            b += 1
    if a < 1 or b < 2:
        pass
    else:
        idx_arr.append(i)

for i in idx_arr:
    print(i)
print()
